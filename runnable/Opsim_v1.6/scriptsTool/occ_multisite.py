# Import arcpy site-package
#
import arcpy
import os
import sys
import math
import time
import multiprocessing

from arcpy import env
from tools.calcoverage import calByOkumuraHata
from tools.calcoverage import calByCost231Hata
from tools.multiThreadTask import calMultiCoverage
from occ_onesite import occOneSiteCal

def occMultiSiteCal(fc_sites, fc_users, fc_dhm, fc_clut, fc_par_tx, fc_PR_Cov, fc_output, folder_out, folder_main, folder_param):   

    try:
        # Checking out ArcGIS Extensions:
        arcpy.CheckOutExtension('3D')
        arcpy.CheckOutExtension("Spatial")
        arcpy.CheckOutExtension("Network")   

        
        if not os.path.exists(folder_main):
            os.makedirs(folder_main)
        
        for the_file in os.listdir(folder_main):
            file_path = os.path.join(folder_main, the_file)
            try:
                if os.path.isfile(file_path):
                    os.unlink(file_path)
            except Exception, e:
                print e

        # Set Workspace Path:
        env.workspace = folder_main

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
        OPSIM-GP: 2 - Creating input FC for each Tx
        ******************************************************************'''
        arcpy.AddMessage("Creating input FC for each Tx: Start")        
        i = 0
        fields_tx = ["SHAPE@XY","POINTID","H_POINT","CLUTTER_ID","SURFACE_ID","SURFACE_H","PXCENTER","PYCENTER","H_DEM","H_eff_r","Surf_eff"]
        with arcpy.da.SearchCursor(fc_sites, fields_tx) as cur_tx:
            for row_tx in cur_tx:
                fc_tx_one = "fc_tx_{0}.shp".format(i)
                whereclause = '"POINTID" = {0}'.format(row_tx[1])
                arcpy.Select_analysis(fc_sites, fc_tx_one, whereclause)
                i = i+1
            del cur_tx, row_tx            
        arcpy.AddMessage("Creating input FC for each Tx: Done")
        n_tx = i
        
        '''******************************************************************
        OPSIM-GP: 3 - Creating Polygons Buffer FC each Tx
        ******************************************************************'''
        arcpy.AddMessage("Creating Polygons Buffer FC for each Tx: Start")
        i = 0
        while i < n_tx:
            in_features = "fc_tx_{0}.shp".format(i)
            out_feature_class = "buff_tx_{0}.shp".format(i)
            rad_cell_eff = rad_cell+(rad_cell*0.5)
            distanceField = "{0} Meters".format(rad_cell_eff)
            #distanceField = "110 Meters"
            #distanceField = "150 Meters"
            sideType = "FULL"
            endType = "ROUND"
            dissolveType = "NONE"
            dissolveField = "#"
            arcpy.Buffer_analysis(in_features, out_feature_class, distanceField, sideType, endType, dissolveType)
            i=i+1
        arcpy.AddMessage("Creating Polygons Buffer FC for each Tx: Done")
        
        '''******************************************************************
        OPSIM-GP: 4 - Spatial Join (buff_txi+tun_dem_point_3D)
        ******************************************************************'''    
        i = 0
        while i < n_tx:
            # Setting up input and output variables:
            fc_targets = fc_users
            fc_features = "buff_tx_{0}.shp".format(i)
            out_feature_class = "tun_DEM_3D_tx_{0}.shp".format(i)
            join_operation = "JOIN_ONE_TO_ONE"
            join_type = "KEEP_COMMON"
            arcpy.AddMessage("Spatial Join (tun_dem_point_3D+buff_tx_{0}): Start".format(i))
            arcpy.SpatialJoin_analysis(fc_targets, fc_features, out_feature_class,
                                       join_operation, join_type)
            arcpy.AddMessage("Spatial Join (tun_dem_point_3D+buff_tx_{0}): Done".format(i))
            i=i+1 
        
        '''******************************************************************
        OPSIM-GP: 5 - Running GP (OpsimCalCoverage for one site) on each Txi
        ******************************************************************'''
        # Method 0: Standard
        id_tx = 0
        jobs = []
        while id_tx < n_tx:
            tb_fc_sites	    = "{0}/fc_tx_{1}.shp".format(folder_main, id_tx)
            tb_fc_users 	= "{0}/tun_DEM_3D_tx_{1}.shp".format(folder_main, id_tx)
            tb_fc_dhm 		= fc_dhm
            tb_fc_clut  	= fc_clut
            tb_fc_par_tx	= fc_par_tx
            tb_fc_PR_Cov	= fc_PR_Cov
            tb_fc_output  	= "{0}/OpsimCalCovOutput_tx_{1}.shp".format(folder_main, id_tx)
            arcpy.AddMessage("Running GP (OpsimCalCoverage for one site) on Tx_{0}: Start".format(id_tx))
            '''# Call Toolbox OccOneSite
            # Import custom toolbox
            arcpy.ImportToolbox("C:\Users\Romuald FOTSO\AppData\Roaming\ESRI\Desktop10.2\ArcToolbox\My Toolboxes\OpsimToolbox.tbx","mytools")
            arcpy.OccOneSite_mytools(tb_fc_sites, tb_fc_users, tb_fc_dhm, tb_fc_clut,
                                        tb_fc_par_tx, tb_fc_PR_Cov, id_tx, tb_fc_output)'''
            occOneSiteCal(tb_fc_sites, tb_fc_users, tb_fc_dhm, tb_fc_clut, tb_fc_par_tx, tb_fc_PR_Cov, id_tx, tb_fc_output,
                            folder_out, folder_param,folder_main)
            arcpy.AddMessage("Running GP (OpsimCalCoverage for one site) on Tx_{0}: Done".format(id_tx))
            
            # Convert to 3D FC
            arcpy.CheckOutExtension('3D')
            arcpy.AddMessage("Convert Output FC to 3D FC: Start on Tx_{0}".format(id_tx))
            fc_output_3D = "{0}/OpsimCalCovOutput3D_tx_{1}.shp".format(folder_main, id_tx)
            arcpy.FeatureTo3DByAttribute_3d(tb_fc_output, fc_output_3D, 'H_DEM')
            arcpy.AddMessage("Convert Output FC to 3D FC: Done on Tx_{0}".format(id_tx))
            arcpy.CheckInExtension("3D")
            id_tx+=1
        #'''
        
        # Method 1: Multi-Processing
        '''id_tx = 0
        jobs = []
        listOutput = list()
        pool = multiprocessing.Pool()
        while id_tx < 1:           
            arcpy.AddMessage("OCC-Multi: Passing %s to processing pool..."%id_tx)
            # send jobs to be processed
            jobs.append(pool.apply_async(calMultiCoverage,(id_tx, fc_dhm, fc_clut, fc_par_tx, fc_PR_Cov, folder_main)))
            id_tx+=1

        for job in jobs: # collect results from the job server (waits until all the processing is complete)
            #listOutput.append([0,0])
            listOutput.append(job.get())
        del jobs 
        pool.close()
        pool.join()
        #'''        
        #"""

        '''******************************************************************
        OPSIM-GP: 6 - Create finale output FC and merging all result
        ******************************************************************'''
        env.workspace = folder_main
        arcpy.CheckOutExtension('3D')
        arcpy.CheckOutExtension("Spatial")
        arcpy.CheckOutExtension("Network") 
        
        # Method 1: Merging All output        
        """listOutput = list()
        #fc_output_final = "OpsimCalCovOutput_final.shp"
        fc_output_final = fc_output
        id_tx = 0
        #folder_main = "D:/ws_temp"
        while id_tx < n_tx:
            listOutput.append("{0}/OpsimCalCovOutput3D_tx_{1}.shp".format(folder_main, id_tx))
            id_tx+=1
        # Merging all output FC    
        fms = arcpy.FieldMappings()
        fm_TxOID = arcpy.FieldMap()
        fm_RxOID = arcpy.FieldMap()
        fm_H_DEM = arcpy.FieldMap()
        fm_RSL = arcpy.FieldMap()
        
        #in_fc = "D:/ws_temp/OpsimCalCovOutput3D_tx_0.shp"
        '''in_fc = "OpsimCalCovOutput3D_tx_0.shp"
        fm_TxOID.addInputField(in_fc, "TxOID")
        fm_RxOID.addInputField(in_fc, "RxOID")
        fm_H_DEM.addInputField(in_fc, "H_DEM")
        fm_RSL.addInputField(in_fc, "OPSIM_RSL")
        fm_RSL.mergeRule = "Max"
        fms.addFieldMap(fm_TxOID)
        fms.addFieldMap(fm_RxOID)
        fms.addFieldMap(fm_H_DEM)
        fms.addFieldMap(fm_RSL)
        arcpy.Merge_management(listOutput, fc_output_final, fms)
        #'''
        #"""
        
        # Method 2: Merging All output
        out_path = folder_main
        out_name = "OpsimCalCovOutput3D.shp"
        geometry_type = "POINT"
        template = "{0}/OpsimCalCovOutput3D_tx_{1}.shp".format(folder_main, 0)
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
            fc_item_output = "{0}/OpsimCalCovOutput3D_tx_{1}.shp".format(folder_main, id_tx)
            fields_out = ["Shape","OPSIM_RSL","RxOID","TxOID","H_DEM"]
            arcpy.AddMessage("Running: {0}".format(fc_item_output))
            with arcpy.da.SearchCursor(fc_item_output, fields_out) as cur_out:
                for row_out in cur_out:
                    RxID = row_out[2]
                    if RxID not in list_RxID:
                        list_RxID.append(RxID)
                        dic_Rx[RxID] = row_out
                    else :
                        RxData = dic_Rx[RxID]
                        if row_out[1] > RxData[1]:
                            dic_Rx[RxID] = row_out                    
                del cur_out, row_out
            id_tx +=1
        arcpy.AddMessage("Merging All output data: Done")
        arcpy.AddMessage("Num. features = {0}".format(len(dic_Rx)))
        
        arcpy.AddMessage("Saving Merging data in output final: Start")
        field_out = ["Shape","OPSIM_RSL","RxOID","TxOID","H_DEM"]
        cur_out = arcpy.da.InsertCursor(out_name, field_out)  
        for key,val in dic_Rx.items():
            row_out = val
            cur_out.insertRow(row_out)
        arcpy.AddMessage("Saving Merging data in output final: Done")
        #"""
        
        arcpy.AddMessage("Convert Output FC to 3D FC: Start")
        fc_output_3D = fc_output
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
    # Read the parameter values:
    '''fc_sites	= arcpy.GetParameterAsText(0)
    fc_users 	= arcpy.GetParameterAsText(1)
    fc_dhm 		= arcpy.GetParameterAsText(2)
    fc_clut  	= arcpy.GetParameterAsText(3)
    fc_par_tx	= arcpy.GetParameterAsText(4)
    fc_PR_Cov	= arcpy.GetParameterAsText(5)
    fc_output  	= arcpy.GetParameterAsText(6)
    #'''
    
    # Read the parameters values from cmd args
    fc_sites	= sys.argv[1]
    fc_users 	= sys.argv[2]
    fc_dhm 		= sys.argv[3]
    fc_clut  	= sys.argv[4]
    fc_par_tx	= sys.argv[5]
    fc_PR_Cov	= sys.argv[6]
    fc_output  	= sys.argv[7]
    folder_out = sys.argv[8]
    folder_main  = sys.argv[9]
    folder_param  = sys.argv[10]
    #'''

    if '' in [fc_sites, fc_users, fc_dhm, fc_clut, fc_par_tx, fc_PR_Cov, fc_output]:
        '''#fc_sites = "D:/Installed/ArcGIS/DeveloperKit10.2/opsim/data_tunisia/site_tx_3D_2PtsMulti.shp"
        fc_sites = "D:/ws_temp/fc_choSite.shp"
        #fc_sites = "D:/ws_temp/fc_tx_2.shp"
        fc_users = "D:/Installed/ArcGIS/DeveloperKit10.2/opsim/data_tunisia/tun_DEM_point_3D.shp"
        #fc_users = "D:/ws_temp/tun_DEM_point_3D_SpatialJoin.shp"
        fc_dhm = "D:/Installed/ArcGIS/DeveloperKit10.2/opsim/data_tunisia/tun_dhm_3D_del.shp"
        fc_clut = "D:/Installed/ArcGIS/DeveloperKit10.2/opsim/data_tunisia/tun_clutter.shp"
        fc_par_tx = "D:/Installed/ArcGIS/DeveloperKit10.2/opsim/data_tunisia/param_tx_3D.shp"
        fc_PR_Cov = "D:/Installed/ArcGIS/DeveloperKit10.2/opsim/data_tunisia/planResultCov.shp"
        fc_output = "D:\ws_out\OpsimCalCovOutput.shp"
        #'''
        '''fc_sites = "C:\Users\Romuald FOTSO\opsim\ws_result\uc0_1500_OpsimSitePosition.shp"
        fc_users = "C:\Users\Romuald FOTSO\Documents\data_cameroon\UseCase0-YDE-PosteCen\yde_pc_uy1_DEM_3D.shp"
        fc_dhm = "C:\Users\Romuald FOTSO\Documents\data_cameroon\UseCase0-YDE-PosteCen\yde_pc_uy1_dhm_3D.shp"
        fc_clut = "C:\Users\Romuald FOTSO\Documents\data_cameroon\UseCase0-YDE-PosteCen\yde_pc_uy1_clutter.shp"
        fc_par_tx = "C:\Users\Romuald FOTSO\opsim\ws_param\param_tx.shp"
        fc_PR_Cov = "C:\Users\Romuald FOTSO\opsim\ws_param\planResultCov.shp"
        fc_output = "C:\Users\Romuald FOTSO\opsim\ws_result\OpsimCalCovOutput_v11.shp"
        folder_out = "C:\Users\Romuald FOTSO\opsim\ws_out"
        folder_main  = "C:\Users\Romuald FOTSO\opsim\ws_main"
        folder_param  = "C:\Users\Romuald FOTSO\opsim\ws_param"
        #'''
    
    # Get starting time
    t_start = time.clock()    
    arcpy.AddMessage("fc_sites : %s" % (fc_sites))
    arcpy.AddMessage("fc_users : %s" % (fc_users))
    arcpy.AddMessage("fc_dhm : %s" % (fc_dhm))
    arcpy.AddMessage("fc_clut : %s" % (fc_clut))
    arcpy.AddMessage("fc_par_tx : %s" % (fc_par_tx))
    arcpy.AddMessage("fc_PR_Cov : %s" % (fc_PR_Cov))
    arcpy.AddMessage("fc_output : %s" % (fc_output))
    arcpy.AddMessage("folder_out : %s" % (folder_out))
    arcpy.AddMessage("folder_main : %s" % (folder_main))
    arcpy.AddMessage("folder_param : %s" % (folder_param))
    #'''
    
    occMultiSiteCal(fc_sites, fc_users, fc_dhm, fc_clut, fc_par_tx, fc_PR_Cov, fc_output, folder_out, folder_main, folder_param)
    # Get ending time
    t_exec = time.clock() - t_start
    if (t_exec/60) < 1:
        arcpy.AddMessage("OCC-Multi complete in : %s sec." % (time.clock() - t_start))
    else:
        arcpy.AddMessage("OCC-Multi complete in : %s Min." % ((time.clock() - t_start)/60))
        