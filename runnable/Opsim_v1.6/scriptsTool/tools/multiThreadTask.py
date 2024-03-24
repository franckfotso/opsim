'''******************************************************************
    OPSIM-Tools: Functions for Multi-Threading	
******************************************************************''' 

import arcpy
import math

from calmetric import calDistance3D
from calspheroid import calSperoidRadiusAtPt
from calattenuation import calObstrAttenuation
from calattenuation import calOccFieldAttenuation
from calattenuation import calMobileAntCorrection

def calListOb(threadID, item, fields_vp, fc_users, fc_sites, fc_par_tx, fc_sj_vp_sel):
    whereclause = '"OID_TARGET" = {0}'.format(item)
    listSurfID = list()
    listOb = list()
    with arcpy.da.SearchCursor(fc_sj_vp_sel,fields_vp,whereclause) as cur_vp:
        for row_vp in cur_vp:
            surfID = row_vp[7]
            TxID = row_vp[4]
            if surfID not in listSurfID:
                listSurfID.append(surfID)
                # Getting Pnt ob
                oid_ob = row_vp[0]
                x_ob, y_ob = row_vp[1]
                z_ob = row_vp[2]
                p_ob = arcpy.Point(x_ob, y_ob, z_ob, 0, oid_ob)
                #print "%s.[OID_TARGET,SurfID] = [%s,%s]" %(threadID,item,surfID)
                arcpy.AddMessage("{0}.[OID_TARGET,SurfID] = [{1},{2}]".format(threadID,item,surfID))
                #arcpy.AddMessage("{3}. Ob[s_ID,x,y,z] = [{4},{0},{1},{2}]".format(p_ob.X,p_ob.Y,p_ob.Z,threadID,surfID))
                
                # Getting Pnt rx
                oid_rx = row_vp[5]
                #arcpy.AddMessage("oid_rx = {0}".format(oid_rx))
                fields_rx = ["OID@","SHAPE@XY","SHAPE@Z","POINTID","Surf_eff","H_DEM","H_eff_r"]
                whereclause = '"FID" = {0}'.format(oid_rx);
                with arcpy.da.SearchCursor(fc_users, fields_rx, whereclause) as cur_rx:
                    for row_rx in cur_rx:                        
                        x_rx, y_rx = row_rx[1]
                        #z_rx = row_rx[2]
                        z_rx = row_rx[6]            
                        p_rx = arcpy.Point(x_rx, y_rx, z_rx, 0, oid_rx)
                        #arcpy.AddMessage(whereclause)
                        #arcpy.AddMessage("Rx[id,x,y,z] = [{0},{1},{2},{3}]".format(p_rx.ID,p_rx.X,p_rx.Y,p_rx.Z))
                    del cur_rx, row_rx
                        
                # Getting Pnt tx
                oid_tx = row_vp[4]
                fields_tx = ["OID@","SHAPE@XY","SHAPE@Z","POINTID","Surf_eff","H_DEM","H_eff_r"]
                whereclause = '"FID" = {0}'.format(oid_tx);
                with arcpy.da.SearchCursor(fc_sites, fields_tx, whereclause) as cur_tx:
                    for row_tx in cur_tx:
                        x_tx, y_tx = row_tx[1]
                        #z_tx = row_tx[2]
                        z_tx = row_tx[6]            
                        p_tx = arcpy.Point(x_tx, y_tx, z_tx, 0, oid_tx)
                        pntID_tx = row_tx[3]
                        #arcpy.AddMessage("Tx[id,x,y,z] = [{0},{1},{2},{3}]".format(p_tx.ID,p_tx.X,p_tx.Y,p_tx.Z))
                    del cur_tx, row_tx
                
                # Calculate inputs function Dist & r(Fresnel Speroid radius)
                fields_par = ["OID@","POINTID","H_ant","Frequency","Power","SiteType"]
                whereclause = '"FID" = 0'
                with arcpy.da.SearchCursor(fc_par_tx, fields_par, whereclause) as cur_par:
                    for row_par in cur_par:
                        freq = row_par[3]
        
                Dist = calDistance3D(p_tx, p_rx)
                A = Dist*(3.00*math.pow(10,8))
                B = freq*math.pow(10,6)
                r = 0.5*math.sqrt(A/B)
                #arcpy.AddMessage(whereclause)
                #arcpy.AddMessage("[Dist,r] = [{0},{1}]".format(Dist,r))

                # Calculate [Hi,Ri] Ob parameters:            
                Hi = row_vp[3]-row_vp[2]
                #Ri = calSperoidRadiusAtPt(p_tx, p_rx, p_ob, Dist, r)
                Ri = r
                #arcpy.AddMessage("[Hi,Ri] = [{0},{1}]".format(Hi,Ri))
                listItem = list()                
                listItem.append([x_ob, y_ob, z_ob, 0, oid_ob])
                listItem.append(Hi)
                listItem.append(Ri)
                listItem.append(z_ob)
                listItem.append(TxID)
                listItem.append(item)
                listOb.append(listItem)
                del row_vp
        del cur_vp
        '''#'''
    return listOb

def calRSL(threadID, x_tx, y_tx, z_tx, id_tx, row_rx, Pt, Gt, freq, fc_ob, listObFres, fc_clut, folder_param):
    listRSL = list()
    x_rx, y_rx = row_rx[1]
    z_rx = row_rx[6]            
    p_tx = arcpy.Point(x_tx, y_tx, z_tx, 0, id_tx)
    p_rx = arcpy.Point(x_rx, y_rx, z_rx, 0, row_rx[3])
    H_dtm = row_rx[7]

    # Control site height
    if p_tx.Z >= 30 and p_tx.Z <= 200:
        Heff = p_tx.Z
    elif p_tx.Z < 30:
        Heff = 30
        p_tx.Z = 30
    elif p_tx.Z > 200:
        Heff = 200
        p_tx.Z = 200

    dist = calDistance3D(p_tx, p_rx)
    try:
        # 1.1. Calculate obstructions attenuation: An
        An = calObstrAttenuation(fc_ob, listObFres, p_tx, p_rx, dist, freq, folder_param)
        
        # 1.2. Calculate field occupation attenuation: Az
        Az = calOccFieldAttenuation(p_tx, p_rx, freq, H_dtm, fc_clut, folder_param)
        
        # 1.3. Calculate Mobile antenna height correction: Ah
        Ah = calMobileAntCorrection(p_tx, p_rx, freq, folder_param)
        
        # 1.4. Calculate slope attenuation: Ap
        Ap = 0.0
        # 1.5. Calculate others attenuation: Aa
        Aa = 0.0
    except arcpy.ExecuteError:
        print arcpy.GetMessages()
    
    # Received signal level in dBm
    A = 26.16*math.log10(freq)+13.82*math.log10(Heff)
    B = (44.9-6.55*math.log10(Heff))*math.log10(dist/1000)
    '''if dist <= 1:
        B = 0
        #'''
    '''arcpy.AddMessage("{0}.Dist = {1} m".format(threadID,dist))
    arcpy.AddMessage("{0}.Pt = {1}".format(threadID,Pt))
    arcpy.AddMessage("{0}.Gt = {1}".format(threadID,Gt))
    arcpy.AddMessage("{0}.Ah = {1}".format(threadID,Ah))
    arcpy.AddMessage("{0}.Az = {1}".format(threadID,Az))
    arcpy.AddMessage("{0}.An = {1}".format(threadID,An))    
    arcpy.AddMessage("{0}.A = {1}".format(threadID,A))
    arcpy.AddMessage("{0}.B = {1}".format(threadID,B))
    #'''
    RSL = Pt+Gt-69.55-A+Ah-B+Az+An+Ap+Aa
    arcpy.AddMessage("{0}.RSL[{1}] = {2}".format(threadID,p_rx.ID,RSL))
    listRSL.append(row_rx[3])
    listRSL.append(RSL)
    
    return [listRSL,[p_tx.X,p_tx.Y,p_tx.Z,id_tx],[p_rx.X,p_rx.Y,p_rx.Z,p_rx.ID]]
    
def calMultiCoverage(id_tx, fc_dhm, fc_clut, fc_par_tx, fc_PR_Cov, folder_main):

    arcpy.ImportToolbox("C:\Users\Romuald FOTSO\AppData\Roaming\ESRI\Desktop10.2\ArcToolbox\My Toolboxes\OpsimToolbox.tbx","mytools")
    
    tb_fc_sites	    = "{0}/fc_tx_{1}.shp".format(folder_main, id_tx)
    tb_fc_users 	= "{0}/tun_DEM_3D_tx_{1}.shp".format(folder_main, id_tx)
    tb_fc_dhm 		= fc_dhm
    tb_fc_clut  	= fc_clut
    tb_fc_par_tx	= fc_par_tx
    tb_fc_PR_Cov	= fc_PR_Cov
    tb_fc_output  	= "{0}/OpsimCalCovOutput_tx_{1}.shp".format(folder_main, id_tx)
    
    arcpy.AddMessage("Running GP (OpsimCalCoverage for one site) on Tx_{0}: Start".format(id_tx))
    '''arcpy.AddMessage("tb_fc_sites: {0}".format(tb_fc_sites))
    arcpy.AddMessage("tb_fc_users: {0}".format(tb_fc_users))
    arcpy.AddMessage("tb_fc_dhm: {0}".format(tb_fc_dhm))
    arcpy.AddMessage("tb_fc_clut: {0}".format(tb_fc_clut))
    arcpy.AddMessage("tb_fc_par_tx: {0}".format(tb_fc_par_tx))
    arcpy.AddMessage("tb_fc_PR_Cov: {0}".format(tb_fc_PR_Cov))
    arcpy.AddMessage("tb_fc_output: {0}".format(tb_fc_output))'''
    arcpy.OccOneSite_mytools(tb_fc_sites, tb_fc_users, tb_fc_dhm, tb_fc_clut,
                                    tb_fc_par_tx, tb_fc_PR_Cov, id_tx, tb_fc_output)
    arcpy.AddMessage("Running GP (OpsimCalCoverage for one site) on Tx_{0}: Done".format(id_tx))
    return tb_fc_output