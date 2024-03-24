'''******************************************************************
    OPSIM-Tools: Functions for Processing obstructions	
******************************************************************'''

import arcpy
import math
import threading
import multiprocessing
import pp

from calspheroid import calSperoidRadiusAtPt
from calspheroid import calEllipsoidPoint
from calmetric import calDistance3D
from calmetric import calDistance2D
from multiThreadTask import calListOb

def execGetObstruction(fc_sites, fc_users, fc_dhm, fc_clut, fc_par_tx, fc_PR_Cov, I_Tx, folder_out):
   
    '''******************************************************************
    OPSIM-GP: 1 - Contruct line of sight
    ******************************************************************'''
    # Setting up input and output variables:
    obs = fc_sites
    tar = fc_users
    sightlines = "con_SL_tx_{0}.shp".format(I_Tx)
    height = "H_Eff_r"
    join_field = "<None>"
    sampling = 1
    direction = "NOT_OUTPUT_THE_DIRECTION"        
    arcpy.AddMessage("Building sightlines: Start on Tx_{0}".format(I_Tx))
    arcpy.ddd.ConstructSightLines(obs, tar, sightlines, height, height, 
                                  join_field, sampling, direction)
    arcpy.AddMessage("Building sightlines: Done on Tx_{0}".format(I_Tx))

    '''******************************************************************
    OPSIM-GP: 2 - Spatial Join (con_SL + tun_dhm_3D_del)
    ******************************************************************'''
    # Setting up input and output variables:
    fc_targets      = fc_dhm
    fc_features     = "con_SL_tx_{0}.shp".format(I_Tx)
    out_feature_class     = "spJoin_sline_del_tx_{0}.shp".format(I_Tx)
    join_operation  = "JOIN_ONE_TO_ONE"
    join_type       = "KEEP_COMMON"
    arcpy.AddMessage("Spatial Join (con_SL + tun_dhm_3D_del): Start on Tx_{0}".format(I_Tx))
    arcpy.SpatialJoin_analysis(fc_targets, fc_features, out_feature_class,
                               join_operation, join_type)
    arcpy.AddMessage("Spatial Join (con_SL + tun_dhm_3D_del): Done on Tx_{0}".format(I_Tx))

    '''******************************************************************
    OPSIM-GP: 3 - Clip con_SL on spJoin_sline_del
    ******************************************************************'''
    in_features = "con_SL_tx_{0}.shp".format(I_Tx)
    clip_features = "spJoin_sline_del_tx_{0}.shp".format(I_Tx)
    out_feature_class = "con_SL_clip_tx_{0}.shp".format(I_Tx)
    arcpy.AddMessage("Clip con_SL on spJoin_sline_del: Start on Tx_{0}".format(I_Tx))
    arcpy.Clip_analysis(in_features, clip_features, out_feature_class)
    arcpy.AddMessage("Clip con_SL on spJoin_sline_del: Done on Tx_{0}".format(I_Tx))

    '''******************************************************************
    OPSIM-GP: 4 - Split Line At Vertices on con_SL_clip
    ******************************************************************'''
    in_features = "con_SL_clip_tx_{0}.shp".format(I_Tx)
    out_feature_class = "con_SL_sline_tx_{0}.shp".format(I_Tx)
    arcpy.AddMessage("Split Line At Vertices on con_SL_clip: Start on Tx_{0}".format(I_Tx))
    arcpy.SplitLine_management(in_features, out_feature_class)
    arcpy.AddMessage("Split Line At Vertices on con_SL_clip: Done on Tx_{0}".format(I_Tx))

    '''******************************************************************
    OPSIM-GP: 5 - Feature Vertices To Points  on con_SL_sline
    ******************************************************************'''
    inFeatures = "con_SL_sline_tx_{0}.shp".format(I_Tx)
    outFeatureClass = "con_SL_v2p_tx_{0}.shp".format(I_Tx)
    arcpy.AddMessage("Feature Vertices To Points  on con_SL_sline: Start on Tx_{0}".format(I_Tx))
    arcpy.FeatureVerticesToPoints_management(inFeatures, outFeatureClass, "ALL")
    arcpy.AddMessage("Feature Vertices To Points  on con_SL_sline: Done on Tx_{0}".format(I_Tx))

    '''******************************************************************
    OPSIM-GP: 6 - Spatial Join (con_SL_v2p + spJoin_sline_del)
    ******************************************************************'''
    # Setting up input and output variables:
    fc_targets      = "con_SL_v2p_tx_{0}.shp".format(I_Tx)
    fc_features     = "spJoin_sline_del_tx_{0}.shp".format(I_Tx)
    out_feature_class     = "spJoin_vp_tx_{0}.shp".format(I_Tx)
    join_operation  = "JOIN_ONE_TO_ONE"
    join_type       = "KEEP_COMMON"
    arcpy.AddMessage("Spatial Join (con_SL_v2p + spJoin_sline_del): Start on Tx_{0}".format(I_Tx))
    arcpy.SpatialJoin_analysis(fc_targets, fc_features, out_feature_class,
                               join_operation, join_type)
    arcpy.AddMessage("Spatial Join (con_SL_v2p + spJoin_sline_del): Done on Tx_{0}".format(I_Tx))
    #"""
    
    '''******************************************************************
    OPSIM-GP: 7 - Calculate obstruction between Tx & Rx	
    ******************************************************************'''
    # Setting up input and output variables:
    out_path = folder_out
    out_name = "spJoin_ob_tx_{0}.shp".format(I_Tx)
    geometry_type = "POINT"
    template = "#"
    has_m = "DISABLED"
    has_z = "ENABLED"
    spatial_reference = arcpy.Describe("spJoin_vp_tx_{0}.shp".format(I_Tx)).spatialReference
    arcpy.AddMessage("Create FC - spJoin_ob: Start on Tx_{0}".format(I_Tx))
    arcpy.CreateFeatureclass_management(out_path, out_name, geometry_type,
                                template, has_m, has_z, spatial_reference)
    arcpy.AddMessage("Create FC - spJoin_ob: Done on Tx_{0}".format(I_Tx))

    # Adding New fields    
    arcpy.AddMessage("Adding New Field to spJoin_ob: Start on Tx_{0}".format(I_Tx))
    inFeatures = "spJoin_ob_tx_{0}.shp".format(I_Tx)
    arcpy.AddField_management(inFeatures, "Hi_ob", "DOUBLE")
    arcpy.AddField_management(inFeatures, "Ri_ob", "DOUBLE")
    arcpy.AddField_management(inFeatures, "H_DEM", "DOUBLE")
    arcpy.AddField_management(inFeatures, "Tx_OID", "SHORT")
    arcpy.AddField_management(inFeatures, "Rx_OID", "SHORT")
    arcpy.AddMessage("Adding New Field to spJoin_ob: Done on Tx_{0}".format(I_Tx))

    # Adding & Calculate SHAPE@Z Field
    fc_sj_vp = "spJoin_vp_tx_{0}.shp".format(I_Tx)
    arcpy.AddMessage("Adding & Calculate SHAPE@Z Field: Start on Tx_{0}".format(I_Tx))
    arcpy.AddField_management(fc_sj_vp, "Z_vp", "DOUBLE")
    arcpy.CalculateField_management(fc_sj_vp, "Z_vp", 
                                    "!SHAPE.CENTROID.Z!",
                                    "PYTHON_9.3")
    arcpy.AddMessage("Adding & Calculate SHAPE@Z Field: Done on Tx_{0}".format(I_Tx))
    #"""

    # Processing Obstructions
    arcpy.AddMessage("Processing Obstructions over LOS: Start on Tx_{0}".format(I_Tx))    
    fc_sj_vp = "spJoin_vp_tx_{0}.shp".format(I_Tx)
    fc_sj_vp_sel = "spJoin_vp_sel_tx_{0}.shp".format(I_Tx)
    fields_vp = ["OID@","SHAPE@XY","Z_vp","H_DEM","OID_OBSERV","OID_TARGET","ORIG_FID","SURFACE_ID"]
    whereclause = '"Z_vp" < "H_DEM"'   
    arcpy.Select_analysis(fc_sj_vp, fc_sj_vp_sel, whereclause)
    
    n_ob = 0
    listRxID = list()
    listOb = list()
    with arcpy.da.SearchCursor(fc_sj_vp_sel,fields_vp) as cur_item:
        for row_item in cur_item:
            oid_rx = row_item[5]
            if oid_rx not in listRxID:
                listRxID.append(oid_rx)
                n_ob+=1                
    n_ob = 0
    arcpy.AddMessage("Num. Rx = {0}".format(len(listRxID)))
    
    # Method 0: Standard
    '''i_th = 0
    for id_rx in listRxID:        
        calListOb(i_th, id_rx, fields_vp, fc_users, fc_sites, fc_par_tx, fc_sj_vp_sel)
        i_th+=1 
    #'''
    
    # Method 1: Multi-threading
    '''listThread = list()
    i_th = 0
    for id_rx in listRxID:
        t = ObstrThread(i_th, id_rx, fields_vp, fc_users, fc_sites, fc_par_tx,
                        "{0}/{1}".format(folder_out,fc_sj_vp_sel))        
        listThread.append(t)
        t.start()        
        i_th+=1        
        
    for th in listThread:
        th.join()
        listOb += th.listResult
    #'''    
    
    # Method 2: Multi-Processing
    jobs = []
    arcpy.AddMessage("Utilising Python Multiprocessing library:")
    pool = multiprocessing.Pool()
    i_job = 0
    for id_rx in listRxID: # For this specific example
        arcpy.AddMessage("Obstr: Passing %s to processing pool..."%i_job)
        jobs.append(pool.apply_async(calListOb, (i_job, id_rx, fields_vp, fc_users, fc_sites, fc_par_tx,
                                                "{0}/{1}".format(folder_out,fc_sj_vp_sel)))) # send jobs to be processed
        i_job+=1

    for job in jobs: # collect results from the job server (waits until all the processing is complete)
        listOb+=job.get()
    del jobs 
    pool.close()
    pool.join()
    #'''
    
    """# Method 3: Parallel Python
    i_job = 0
    jobs = []
    arcpy.AddMessage("Utilising Parallel Python library:")
    ppservers=()
    job_server = pp.Server(ppservers=ppservers)   
    for id_rx in listRxID: # For this specific example
        arcpy.AddMessage("Obstr: Passing %s to processing pool..."%i_job)
        # send jobs to be processed
        jobs.append(job_server.submit(calListOb, (i_job, id_rx, fields_vp, fc_users, fc_sites, fc_par_tx,
                                            "{0}/{1}".format(folder_out,fc_sj_vp_sel)), (), ("arcpy",)))
        i_job+=1

    for job in jobs: # collect results from the job server (waits until all the processing is complete)
        listOb+=job()
    del jobs
    #"""
    
    arcpy.AddMessage("Nb. listOb = {0}".format(len(listOb)))
    
    # Writing parameters in FC file   
    n_ob = 0
    fc_ob = "spJoin_ob_tx_{0}.shp".format(I_Tx)
    field_E_ob = ["Shape","Hi_ob","Ri_ob","H_DEM","Tx_OID","Rx_OID"]
    cur_E_ob = arcpy.da.InsertCursor(fc_ob, field_E_ob)
    arcpy.AddMessage("Store listOb to FC: Start on Tx_{0}".format(I_Tx))
    for listIt in listOb:
        p_ob = arcpy.Point(listIt[0][0], listIt[0][1], listIt[0][2], listIt[0][3], listIt[0][4])
        pntGeo = arcpy.PointGeometry(p_ob)
        cur_E_ob.insertRow([pntGeo, listIt[1], listIt[2], listIt[3], listIt[4], listIt[5]])
        n_ob = n_ob+1
    del cur_E_ob
    '''fc_ob_3D = "spJoin_ob_3D_tx_{0}.shp".format(I_Tx)
    arcpy.FeatureTo3DByAttribute_3d(fc_ob, fc_ob_3D, 'H_DEM')'''
    arcpy.AddMessage("Store listOb to FC: Done on Tx_{0}".format(I_Tx))
                                
    arcpy.AddMessage("Processing Obstructions over LOS: Done on Tx_{0}".format(I_Tx))
    #'''    
    return fc_ob
    
class ObstrThread (threading.Thread):
    #listResult = list()
    def __init__(self, threadID, OID_Rx, fields_vp, fc_users, fc_sites, fc_par_tx, fc_sj_vp_sel):
        threading.Thread.__init__(self)  
        self.listResult = list()
        self.threadID = threadID
        self.OID_Rx = OID_Rx        
        self.fields_vp = fields_vp
        self.fc_users = fc_users
        self.fc_sites = fc_sites
        self.fc_par_tx = fc_par_tx
        self.fc_sj_vp_sel = fc_sj_vp_sel
        
    def run(self):
        arcpy.AddMessage("ObstrThread-{0}: Starting".format(self.threadID))
        self.listResult = calListOb(self.threadID, self.OID_Rx, self.fields_vp, self.fc_users, self.fc_sites, self.fc_par_tx, self.fc_sj_vp_sel)
        arcpy.AddMessage("ObstrThread-{0}: Exit".format(self.threadID))
        

def execGetFresnelObstr(fc_sites, fc_users, fc_dhm, fc_clut, fc_par_tx, fc_PR_Cov):
    
    '''*******************************************************************************
    OPSIM-GP: 1 - Generate an Speroid Fresnel between Tx - Rx (based on clipped point)
    *******************************************************************************'''
    fc_sj_vp = "spJoin_vp.shp"
	#fc_sj_vp = "D:/spJoin_vp.shp"
    fields_vp = ["OID@","SHAPE@XY","Z_vp","H_DEM","OID_OBSERV","OID_TARGET","ORIG_FID","SURFACE_ID"]
    listRxID = list()
    arcpy.AddMessage("Searching for RxID in spJoin_vp: Start")
    with arcpy.da.SearchCursor(fc_sj_vp,fields_vp) as cur_item:
        for row_item in cur_item:
            oid_rx = row_item[5]
            if oid_rx not in listRxID:
                listRxID.append(oid_rx)    
    arcpy.AddMessage("Searching for RxID in spJoin_vp: Done")
    arcpy.AddMessage("len listRxID = {0}".format(len(listRxID)))
    
    listEllipPnt = list()
    n_ob = 0
    for item in listRxID:        
        whereclause = '"OID_TARGET" = {0}'.format(item)
        listObByRx = list()
        listSurfID = list()
        with arcpy.da.SearchCursor(fc_sj_vp,fields_vp,whereclause) as cur_vp:
            for row_vp in cur_vp:
                # Getting Pnt on line LOS
                oid_ob = row_vp[0]
                x_ob, y_ob = row_vp[1]
                z_ob = row_vp[2]
                p_onLine = arcpy.Point(x_ob, y_ob, z_ob, 0, oid_ob)
                
                # Getting Pnt rx
                oid_rx = row_vp[5]
                fields_rx = ["OID@","SHAPE@XY","SHAPE@Z","POINTID","Surf_eff","H_DEM","H_eff_r"]
                whereclause = '"FID" = {0}'.format(oid_rx);
                with arcpy.da.SearchCursor(fc_users, fields_rx, whereclause) as cur_rx:
                    for row_rx in cur_rx:                        
                        x_rx, y_rx = row_rx[1]
                        z_rx = row_rx[6]            
                        p_rx = arcpy.Point(x_rx, y_rx, z_rx, 0, oid_rx)
                        
                # Getting Pnt tx
                oid_tx = row_vp[4]
                fields_tx = ["OID@","SHAPE@XY","SHAPE@Z","POINTID","Surf_eff","H_DEM","H_eff_r"]
                whereclause = '"FID" = {0}'.format(oid_tx);
                with arcpy.da.SearchCursor(fc_sites, fields_tx, whereclause) as cur_tx:
                    for row_tx in cur_tx:
                        x_tx, y_tx = row_tx[1]
                        z_tx = row_tx[6]            
                        p_tx = arcpy.Point(x_tx, y_tx, z_tx, 0, oid_tx)
                        pntID_tx = row_tx[3]
                
                fields_par = ["OID@","POINTID","H_ant","Frequency","Power","SiteType"]
                whereclause = '"FID" = 0'
                with arcpy.da.SearchCursor(fc_par_tx, fields_par, whereclause) as cur_par:
                    for row_par in cur_par:
                        freq = row_par[3]
							
                dist = calDistance3D(p_tx,p_rx)
                A = dist*(3.00*math.pow(10,8))
                B = freq*math.pow(10,6)
                r = 0.5*math.sqrt(A/B)

                p_mid = arcpy.Point((p_tx.X+p_rx.X)/2,
                                    (p_tx.Y+p_rx.Y)/2,
                                    (p_tx.Z+p_rx.Z)/2,
                                    0, 0)                
                listEllipPnt = calEllipsoidPoint(listEllipPnt, p_onLine, p_tx, p_rx, p_mid, dist, r)
                arcpy.AddMessage("listEllip: N. {0} len[{1}]".format(n_ob, len(listEllipPnt)))
                n_ob = n_ob +1
				
    arcpy.AddMessage("len listEllipPnt = {0}".format(len(listEllipPnt)))
	
    # Create FC fc_ellip_3D: fc_ellip_3D.shp
    arcpy.AddMessage("Create FC fc_ellip: Start")
    out_path = "D:/ws_out"
    out_name = "fc_ellip.shp"
    geometry_type = "POINT"
    template = "#"
    has_m = "DISABLED"
    has_z = "ENABLED"
    spatial_reference = arcpy.Describe("spJoin_vp.shp").spatialReference
    #spatial_reference = arcpy.Describe("D:/spJoin_vp.shp").spatialReference
    arcpy.CreateFeatureclass_management(out_path, out_name, geometry_type,
                                template, has_m, has_z, spatial_reference)
    arcpy.AddField_management(out_name, "Z_E", "DOUBLE")
    arcpy.AddField_management(out_name, "Tx_OID", "SHORT")
    arcpy.AddField_management(out_name, "Rx_OID", "SHORT")
    arcpy.AddMessage("Create FC fc_ellip: Done")

    # Store listEllipPnt to FC: fc_ellip_3D.shp
    arcpy.AddMessage("Store listEllipPnt to FC: Start")
    arcpy.AddMessage("len of listEllipPnt = {0}".format(len(listEllipPnt)))
    n_ob = 0
    fc_ellip = "fc_ellip.shp"
    field_E_ob = ["Shape","Z_E","Tx_OID","Rx_OID"]
    cur_E_ob = arcpy.da.InsertCursor(fc_ellip, field_E_ob)
    for listItem in listEllipPnt:
        arcpy.AddMessage("N. Ob = {0}".format(n_ob))
        p_E = listItem[0]
        arcpy.AddMessage("p_E[x,y,z] = [{0},{1},{2}]".format(p_E.X, p_E.Y, p_E.Z))
        pntGeo = arcpy.PointGeometry(p_E)
        cur_E_ob.insertRow([pntGeo, listItem[1], listItem[2], listItem[3]])
        n_ob = n_ob+1
    del cur_E_ob
    arcpy.FeatureTo3DByAttribute_3d('fc_ellip.shp', 'fc_ellip_3D.shp', 'Z_E')
    arcpy.AddMessage("Store listEllipPnt to FC: Done")

    listObFres = list()
    '''*******************************************************************************
    OPSIM-GP: 2 - Searching for Spheroid Fresnel Pnts matching Polygons Obstructions
    *******************************************************************************'''
    # Processing Spatial Join: fc_ellip_3D & spJoin_sline_del
    fc_targets      = "fc_ellip_3D.shp"
    fc_features     = "spJoin_sline_del.shp"
    out_feature_class     = "spJoin_ellip_3D.shp"
    join_operation  = "JOIN_ONE_TO_ONE"
    join_type       = "KEEP_COMMON"
    arcpy.AddMessage("Spatial Join (fc_ellip_3D + spJoin_sline_del): Start")
    arcpy.SpatialJoin_analysis(fc_targets, fc_features, out_feature_class,
                               join_operation, join_type)
    arcpy.AddMessage("Spatial Join (fc_ellip_3D + spJoin_sline_del): Done")

    # Processing Obstructions on Speroid Fresnel

    field_E = ["OID@","SHAPE@XY","SHAPE@Z","Tx_OID","Rx_OID","SURFACE_ID"]
    whereclause = '"Z_E" < "H_DEM"'
    fc_sjEllip3D = "spJoin_ellip_3D.shp"
    arcpy.AddMessage("Selecting in spJoin_ellip_3D on whereclause: Start")
    arcpy.Select_analysis(fc_sjEllip3D, "spJoin_ellip3D_sel.shp", whereclause)
    arcpy.AddMessage("Selecting in spJoin_ellip_3D on whereclause: Done")

    # Getting list OID of Rx
    listRxID = list()
    with arcpy.da.SearchCursor("spJoin_ellip3D_sel.shp",field_E) as cur_item:
        for row_item in cur_item:
            oid_rx = row_item[4]
            if oid_rx not in listRxID:
                listRxID.append(oid_rx)

    # Calculate line of ob_fres
    listObFres = list()
    n_ob = 0
    arcpy.AddMessage("Num. Rx = {0}".format(len(listRxID)))
    for item in listRxID:        
        whereclause = '"Rx_OID" = {0}'.format(item)
        listObByRx = list()
        listSurfID = list()
        with arcpy.da.SearchCursor("spJoin_ellip3D_sel.shp", field_E, whereclause) as cur_vp:
            for row_vp in cur_vp:
                surfID = row_vp[5]
                if surfID not in listSurfID:
                    listSurfID = list()
                    listSurfID.append(surfID)
                    listObBySurf = list()
                    oid_ob = row_vp[0]
                    x_ob, y_ob = row_vp[1]
                    z_ob = row_vp[2]
                    p_ob = arcpy.Point(x_ob, y_ob, z_ob, 0, oid_ob)
                    listObBySurf.append(p_ob)
                else :
                    oid_ob = row_vp[0]
                    x_ob, y_ob = row_vp[1]
                    z_ob = row_vp[2]
                    p_ob = arcpy.Point(x_ob, y_ob, z_ob, 0, oid_ob)
                    listObBySurf.append(p_ob)
                listObByRx.append(listObBySurf)

        listLenByRx = list()
        listLenOb = list() 
        n = 0
        for list_ObBySurf in listObByRx:
            n = len(list_ObBySurf)
            pnt_start = list_ObBySurf[0]
            pnt_end = list_ObBySurf[n-1]
            len_ob = calDistance2D(pnt_start,pnt_end)
            listLenOb.append(len_ob)
            arcpy.AddMessage("Ob: N. {0} len_ob[{1}]".format(n, len_ob))
            n = n+1

        listLenByRx.append(item)
        listLenByRx.append(listLenOb)
        listObFres.append(listLenByRx)
        
        arcpy.AddMessage("listObFres: N. {0} len[{1}]".format(n_ob, len(listLenOb)))
        n_ob = n_ob +1
    #'''
    return listObFres
