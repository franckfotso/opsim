# Import arcpy site-package
#
import arcpy
import os
import sys
import time
import math

from arcpy import env
from tools.calmetric import *
from occ_interfOne import occInterfOne

def occInterfMulti(fc_sites, fc_users, fc_dhm, fc_clut, fc_par_tx, fc_PR_Cov,
 fc_out_interf, folder_out, folder_param, folder_main, folder_interf):
 
    try:
        # Checking out ArcGIS Extensions:
        arcpy.CheckOutExtension('3D')
        arcpy.CheckOutExtension("Spatial")
        arcpy.CheckOutExtension("Network") 
        
        if not os.path.exists(folder_interf):
            os.makedirs(folder_interf)
        
        for the_file in os.listdir(folder_interf):
            file_path = os.path.join(folder_interf, the_file)
            try:
                if os.path.isfile(file_path):
                    os.unlink(file_path)
            except Exception, e:
                print e

        # Set Workspace Path:
        env.workspace = folder_interf
        
        '''******************************************************************
        OPSIM-GP: 1 - Getting param_tx_3D
        ******************************************************************'''
        # Setting up coverage planning parameters
        fields_pr = ["OID@","PIRE","S_UL","S_DL","PathLoss","CellRadCov",
                     "CellPrint","SitePrint","NumSite","IS_Dist","PM_ID"]
        whereclause = '"FID" = 0'
        with arcpy.da.SearchCursor(fc_PR_Cov, fields_pr) as cur_pr:
            for row_pr in cur_pr:
                pm_id = row_pr[10]
                rad_cell = row_pr[5]
                
        '''******************************************************************
        OPSIM-GP: 2 - Creating input FC for each Targeted Tx
        ******************************************************************'''
        arcpy.AddMessage("Creating input FC for each Targeted Tx: Start")        
        i = 0
        fields_tx = ["SHAPE@XY","POINTID","H_POINT","CLUTTER_ID","SURFACE_ID","SURFACE_H","PXCENTER","PYCENTER","H_DEM","H_eff_r","Surf_eff"]
        with arcpy.da.SearchCursor(fc_sites, fields_tx) as cur_tx:
            for row_tx in cur_tx:
                fc_target_tx = "fc_target_tx_{0}.shp".format(i)
                whereclause = '"POINTID" = {0}'.format(row_tx[1])
                arcpy.Select_analysis(fc_sites, fc_target_tx, whereclause)
                i = i+1
            del cur_tx, row_tx            
        arcpy.AddMessage("Creating input FC for each Targeted Tx: Done")
        n_tx = i
        
        '''******************************************************************
        OPSIM-GP: 3 - Running GP (OpsimCalInterOne) on each targeted Txi
        ******************************************************************'''
        # Method 0: Standard
        i = 0
        jobs = []
        while i < n_tx:
            tb_fc_sites     = fc_sites
            tb_fc_site_one  = "{0}/fc_target_tx_{1}.shp".format(folder_interf, i)
            tb_fc_users 	= fc_users
            tb_fc_dhm 		= fc_dhm
            tb_fc_clut  	= fc_clut
            tb_fc_par_tx	= fc_par_tx
            tb_fc_PR_Cov	= fc_PR_Cov
            tb_fc_output  	= "InterfOutput_tx"
            arcpy.AddMessage("Running GP (OpsimCalInterOne) on Tx_{0}: Start".format(i))
            occInterfOne(tb_fc_sites, tb_fc_site_one, tb_fc_users, tb_fc_dhm, tb_fc_clut, tb_fc_par_tx, tb_fc_PR_Cov, i,
                              tb_fc_output, folder_out, folder_param, folder_main, folder_interf)
            arcpy.AddMessage("Running GP (OpsimCalInterOne) on Tx_{0}: Done".format(i))
            i+=1
        #"""
        '''******************************************************************
        OPSIM-GP: 4 - Create finale output FC and merging all result
        ******************************************************************'''
        env.workspace = folder_interf
        arcpy.CheckOutExtension('3D')
        arcpy.CheckOutExtension("Spatial")
        arcpy.CheckOutExtension("Network") 
        
        # Method 2: Merging All output
        out_path = folder_interf
        out_name = "Pre_InterfOutput_tx.shp"
        geometry_type = "POINT"
        template = "{0}/InterfOutput_tx_{1}.shp".format(folder_interf, 0)
        #template = "{0}/InterfOutput_tx_{1}.shp".format("D:/ws_temp", 0)
        has_m = "DISABLED"
        has_z = "ENABLED"
        spatial_reference = arcpy.Describe(fc_users).spatialReference
        arcpy.AddMessage("Creating Final Output FC: Start")
        arcpy.CreateFeatureclass_management(out_path, out_name, geometry_type,
                                    template, has_m, has_z, spatial_reference)
        arcpy.AddMessage("Creating Final Output FC: Done") 
        
        dic_Rx = {}
        list_RxID = list()
        id_tx = 0
        arcpy.AddMessage("Merging All output data: Start")
        while id_tx < n_tx:
        #while id_tx < 5:
            fc_item_output = "{0}/InterfOutput_tx_{1}.shp".format(folder_interf, id_tx)
            #fc_item_output = "{0}/InterfOutput_tx_{1}.shp".format("D:/ws_temp", id_tx)
            fields_out = ["Shape","OPSIM_RSL","RxOID","TxOID","H_DEM","SINR"]
            arcpy.AddMessage("Running: {0}".format(fc_item_output))
            with arcpy.da.SearchCursor(fc_item_output, fields_out) as cur_out:
                for row_out in cur_out:
                    RxID = row_out[2]
                    if RxID not in list_RxID:
                        list_RxID.append(RxID)
                        dic_Rx[RxID] = row_out
                    else :
                        RxData = dic_Rx[RxID]
                        if row_out[5] > RxData[5]:
                            dic_Rx[RxID] = row_out                    
                del cur_out, row_out
            id_tx +=1
        arcpy.AddMessage("Merging All output data: Done")
        arcpy.AddMessage("Num. features = {0}".format(len(dic_Rx)))
        
        arcpy.AddMessage("Saving Merging data in output final: Start")
        field_out = ["Shape","OPSIM_RSL","RxOID","TxOID","H_DEM","SINR"]
        cur_out = arcpy.da.InsertCursor(out_name, field_out)  
        for key,val in dic_Rx.items():
            row_out = val
            cur_out.insertRow(row_out)
        arcpy.AddMessage("Saving Merging data in output final: Done")
        #"""
        
        arcpy.AddMessage("Convert Output FC to 3D FC: Start")
        fc_output_3D = fc_out_interf
        arcpy.FeatureTo3DByAttribute_3d(out_name, fc_output_3D, 'H_DEM')
        arcpy.AddMessage("Convert Output FC to 3D FC: Done")
        #'''
        
        arcpy.GetMessages(0)
        arcpy.CheckInExtension("3D")
        arcpy.CheckInExtension("Spatial")
        arcpy.CheckInExtension("Network")
        
    except arcpy.ExecuteError:
        print arcpy.GetMessages()
 
if __name__ == '__main__':
    '''fc_sites	    = arcpy.GetParameterAsText(0)
    fc_users 	    = arcpy.GetParameterAsText(1)
    fc_dhm 		    = arcpy.GetParameterAsText(2)
    fc_clut  	    = arcpy.GetParameterAsText(3)
    fc_par_tx	    = arcpy.GetParameterAsText(4)
    fc_PR_Cov	    = arcpy.GetParameterAsText(5)
    fc_out_interf 	= arcpy.GetParameterAsText(6)
    folder_out      = arcpy.GetParameterAsText(7)
    folder_main     = arcpy.GetParameterAsText(8)
    folder_param    = arcpy.GetParameterAsText(9)
    folder_interf   = arcpy.GetParameterAsText(10)
    #'''
    
    # Read the parameters values from cmd args
    fc_sites	    = sys.argv[1]
    fc_users 	    = sys.argv[2]
    fc_dhm          = sys.argv[3]
    fc_clut         = sys.argv[4]
    fc_par_tx       = sys.argv[5]
    fc_PR_Cov       = sys.argv[6]
    fc_out_interf 	= sys.argv[7]
    folder_out      = sys.argv[8]
    folder_param    = sys.argv[9]
    folder_main     = sys.argv[10]
    folder_interf   = sys.argv[11]
    #'''
    
    if '' in [fc_sites, fc_users, fc_dhm, fc_clut, fc_par_tx, fc_PR_Cov, fc_out_interf]:
        '''fc_sites	    = "D:/ws_temp/OpsimSitePosition.shp"
        fc_users 	    = "D:/Workspaces/CityEngine/CityEngine2014/OPSIM-CM-Creation/data/UseCase0-YDE-PosteCen/yde_pc_uy1_DEM_3D.shp"
        fc_dhm          = "D:/Workspaces/CityEngine/CityEngine2014/OPSIM-CM-Creation/data/UseCase0-YDE-PosteCen/yde_pc_uy1_dhm_3D.shp"
        fc_clut         = "D:/Workspaces/CityEngine/CityEngine2014/OPSIM-CM-Creation/data/UseCase0-YDE-PosteCen/yde_pc_uy1_clutter.shp"
        fc_par_tx       = "D:/Workspaces/CityEngine/CityEngine2014/OPSIM-CM-Creation/data/UseCase0-YDE-PosteCen/param_tx_3D.shp"
        fc_PR_Cov       = "D:/Workspaces/CityEngine/CityEngine2014/OPSIM-CM-Creation/data/UseCase0-YDE-PosteCen/planResultCov.shp"
        fc_out_interf 	= "InterfOutput_tx"
        folder_out      = "D:/ws_out"
        folder_main     = "D:/ws_main"
        folder_param    = "D:/Installed/ArcGIS/DeveloperKit10.2/opsim/data_tunisia"
        folder_interf     = "D:/ws_interf"
        #'''
                
    # Get starting time
    t_start = time.clock()    
    arcpy.AddMessage("fc_sites : %s" % (fc_sites))
    arcpy.AddMessage("fc_users : %s" % (fc_users))
    arcpy.AddMessage("fc_dhm : %s" % (fc_dhm))
    arcpy.AddMessage("fc_clut : %s" % (fc_clut))
    arcpy.AddMessage("fc_par_tx : %s" % (fc_par_tx))
    arcpy.AddMessage("fc_PR_Cov : %s" % (fc_PR_Cov))
    arcpy.AddMessage("fc_out_interf : %s" % (fc_out_interf))
    arcpy.AddMessage("folder_out : %s" % (folder_out))
    arcpy.AddMessage("folder_param : %s" % (folder_param))
    arcpy.AddMessage("folder_main : %s" % (folder_main))
    arcpy.AddMessage("folder_interf : %s" % (folder_interf))
    #'''
    
    occInterfMulti(fc_sites, fc_users, fc_dhm, fc_clut, fc_par_tx, fc_PR_Cov,
                              fc_out_interf, folder_out, folder_param, folder_main, folder_interf)
    # Get ending time
    t_exec = time.clock() - t_start
    if (t_exec/60) < 1:
        arcpy.AddMessage("OCC-Multi complete in : %s sec." % (time.clock() - t_start))
    else:
        arcpy.AddMessage("OCC-Multi complete in : %s Min." % ((time.clock() - t_start)/60))        