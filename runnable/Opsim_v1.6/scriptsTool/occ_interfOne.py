# Import arcpy site-package
#
import arcpy
import os
import sys
import time
import math

from arcpy import env
from tools.calmetric import *
from occ_onesite import occOneSiteCal

def occInterfOne(fc_sites, fc_site_one, fc_users, fc_dhm, fc_clut, fc_par_tx, fc_PR_Cov, id_tx,
  fc_out_interf, folder_out, folder_param, folder_main, folder_interf=None):

    try:
        arcpy.overwriteOutputs = True        
        
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
        OPSIM-GP: 2 - Calculate foreign site for each site
        ******************************************************************'''
        arcpy.AddMessage("Calculate foreign site for each site: Start")
        list_site = list()
        list_foreign = list()
        i = 0
        fields_tx = ["OID@","SHAPE@XY","SHAPE@Z","POINTID","CLUTTER_ID","Surf_eff","H_DEM","H_eff_r"]
        with arcpy.da.SearchCursor(fc_sites, fields_tx) as cur_tx:
            for row_tx in cur_tx:
                oid_tx = row_tx[3]
                x_tx, y_tx = row_tx[1]
                z_tx = row_tx[7]            
                p_tx = arcpy.Point(x_tx, y_tx, z_tx, 0, oid_tx)
                list_site.append(p_tx)
                i = i+1
            del row_tx, cur_tx
        n_tx = i
        
        rad_cell_eff = rad_cell+(rad_cell*0.5)
        D_foreign = rad_cell_eff*2
        arcpy.AddMessage("  D_foreign: {0}".format(D_foreign))
        with arcpy.da.SearchCursor(fc_site_one, fields_tx) as cur_one:
            for row_one in cur_one:
                oid_one = row_one[3]
                x_one, y_one = row_one[1]
                z_one = row_one[7]            
                p_one = arcpy.Point(x_one, y_one, z_one, 0, oid_one)
                                
                i = 0
                while i < n_tx :
                    dist = calDistance3D(p_one,list_site[i])
                    arcpy.AddMessage("  Dist. Cal: {0}".format(dist))
                    if dist > 0 and dist < D_foreign:
                        list_foreign.append(list_site[i])
                    i+=1
                          
        arcpy.AddMessage("Calculate foreign site for each site: Done")
        n_for = len(list_foreign)
        arcpy.AddMessage("N. list_foreign: {0}".format(n_for))
        '''******************************************************************
        OPSIM-GP: 3 - Creating input FC for each Foreign Tx
        ******************************************************************'''        
        arcpy.AddMessage("Creating input FC for each Foreign Tx: Start") 
        fields_tx = ["SHAPE@XY","POINTID","H_POINT","CLUTTER_ID","SURFACE_ID","SURFACE_H","PXCENTER","PYCENTER","H_DEM","H_eff_r","Surf_eff"]
        with arcpy.da.SearchCursor(fc_site_one, fields_tx) as cur_tx:
            for row_tx in cur_tx: 
                i = 1
                while i < (n_for+1):
                    fc_for_tx = "fc_for_tx_{0}.shp".format(i)
                    p_for = list_foreign[i-1]
                    whereclause = '"POINTID" = {0}'.format(p_for.ID)
                    arcpy.Select_analysis(fc_sites, fc_for_tx, whereclause) 
                    i+=1
            del cur_tx, row_tx            
        arcpy.AddMessage("Creating input FC for each Foreign Tx: Done")
        
        '''******************************************************************
        OPSIM-GP: 4 - Creating Polygon Buffer FC for target Tx
        ******************************************************************'''
        arcpy.AddMessage("Creating Polygon Buffer FC for target Tx: Start")        
        in_features = fc_site_one
        out_feature_class = "buff_one_tx.shp"
        rad_cell_eff = rad_cell+(rad_cell*0.5)
        distanceField = "{0} Meters".format(rad_cell_eff)
        sideType = "FULL"
        endType = "ROUND"
        dissolveType = "NONE"
        dissolveField = "#"
        arcpy.Buffer_analysis(in_features, out_feature_class, distanceField, sideType, endType, dissolveType)        
        arcpy.AddMessage("Creating Polygon Buffer FC for target Tx: Done")
        
        '''******************************************************************
        OPSIM-GP: 5 - Spatial Join (buff_one_tx+fc_users)
        ******************************************************************'''        
        # Setting up input and output variables:
        fc_targets = fc_users
        fc_features = "buff_one_tx.shp"
        out_feature_class = "DEM_3D_one_tx.shp"
        join_operation = "JOIN_ONE_TO_ONE"
        join_type = "KEEP_COMMON"
        arcpy.AddMessage("Spatial Join (buff_one_tx+fc_users): Start")
        arcpy.SpatialJoin_analysis(fc_targets, fc_features, out_feature_class,
                                   join_operation, join_type)
        arcpy.AddMessage("Spatial Join (buff_one_tx+fc_users): Done")
        
        '''******************************************************************
        OPSIM-GP: 6 - Running GP (OpsimCalCoverage for one site) on target Tx
        ******************************************************************'''
        # Method 0: Standard
        i = 1
        jobs = []
        while i < (n_for+1):
            tb_fc_sites	    = "{0}/fc_for_tx_{1}.shp".format(folder_main, i)
            tb_fc_users 	= "{0}/DEM_3D_one_tx.shp".format(folder_main)
            tb_fc_dhm 		= fc_dhm
            tb_fc_clut  	= fc_clut
            tb_fc_par_tx	= fc_par_tx
            tb_fc_PR_Cov	= fc_PR_Cov
            tb_fc_output  	= "{0}/CovOutput_tx_{1}.shp".format(folder_main, i)
            arcpy.AddMessage("Running GP (OpsimCalCoverage for one site) on foreign Tx_{0}: Start".format(i))            
            occOneSiteCal(tb_fc_sites, tb_fc_users, tb_fc_dhm, tb_fc_clut, tb_fc_par_tx, tb_fc_PR_Cov, i, tb_fc_output,
                            folder_out, folder_param,folder_main)
            arcpy.AddMessage("Running GP (OpsimCalCoverage for one site) on foreign Tx_{0}: Done".format(i))
            i+=1
            
        # Calculate outputCov on target site
        tb_fc_sites	    = fc_site_one
        tb_fc_output  	= "{0}/CovOutput_one_tx.shp".format(folder_main)
        arcpy.AddMessage("Running GP (OpsimCalCoverage for one site) on target Tx: Start")
        occOneSiteCal(tb_fc_sites, tb_fc_users, tb_fc_dhm, tb_fc_clut, tb_fc_par_tx, tb_fc_PR_Cov, 0, tb_fc_output,
                            folder_out, folder_param, folder_main)
        arcpy.AddMessage("Running GP (OpsimCalCoverage for one site) on target Tx: Done")
        #"""
        
        '''******************************************************************
        OPSIM-GP: 7 - Collect all Interf data before processing
        ******************************************************************'''
        env.workspace = folder_main
        arcpy.CheckOutExtension('3D')
        arcpy.CheckOutExtension("Spatial")
        arcpy.CheckOutExtension("Network") 
        
        arcpy.AddMessage("Collect RSL data on target site: Start")
        list_SINR_All = list()
        list_SINR_For = list()
        list_SNIR = list()
        fields_out = ["Shape","OPSIM_RSL","RxOID","TxOID","H_DEM"]
        fc_item_output = "{0}/CovOutput_one_tx.shp".format(folder_main)
        #fc_item_output = "{0}/CovOutput_one_tx.shp".format("D:/ws_temp")
        with arcpy.da.SearchCursor(fc_item_output, fields_out) as cur_out:
            for row_out in cur_out:
                list_SINR_For.append(row_out[1])
                list_SNIR.append(row_out)
            del row_out, cur_out
        list_SINR_All.append(list_SINR_For)        
        arcpy.AddMessage("Collect RSL data on target site: Done")
        
        #n_for = 4
        i = 1
        while i < (n_for+1):            
            arcpy.AddMessage("Collect Interf[{0}] data before processing: Start".format(i))
            list_SINR_For = list()
            fc_item_output = "{0}/CovOutput_tx_{1}.shp".format(folder_main, i)
            #fc_item_output = "{0}/CovOutput_tx_{1}.shp".format("D:/ws_temp", i)
            with arcpy.da.SearchCursor(fc_item_output, fields_out) as cur_out:
                for row_out in cur_out:
                    list_SINR_For.append(row_out[1])
                del row_out, cur_out
            list_SINR_All.append(list_SINR_For)            
            arcpy.AddMessage("Collect Interf[{0}] data before processing: Done".format(i))
            i+=1
        arcpy.AddMessage("N. list_SINR_All: {0}".format(len(list_SINR_All)))
        arcpy.AddMessage("N. list_SINR_For: {0}".format(len(list_SINR_For)))
        
        '''******************************************************************
        OPSIM-GP: 8 - Calculating C/I Level avalaible on target site
        ******************************************************************'''
        arcpy.AddMessage("Calculating SINR(C/I) Level avalaible on target site: Start")
        n_tar = len(list_SINR_For)
        R1 = 0.015
        R2 = 0.00001
        R3 = 0.000001        
        i = 0
        while i < n_tar:
            A = math.pow(10,9.0/10)
            #A = 0.0
            B = 0.0
            j = 1
            while j < n_for:
                if j == 1:
                    p = list_SINR_All[j][i]/10
                    A += R1*math.pow(10,p)
                elif j == 2:
                    p = list_SINR_All[j][i]/10
                    A += R2*math.pow(10,p)
                else :
                    p = list_SINR_All[j][i]/10
                    A += R3*math.pow(10,p)
                j+=1
            p = list_SINR_All[0][i]/10
            B = math.pow(10,p)/A
            B = 10*math.log10(B)
            #arcpy.AddMessage("[C/I] = [{0}/{1}]".format(list_SINR_All[0][i], A))
            arcpy.AddMessage("SINR[{0}]: {1}".format(i, B))
            row = list(list_SNIR[i])
            row.append(B)
            list_SNIR[i] = row
            i+=1
        arcpy.AddMessage("Calculating SINR(C/I) Level avalaible on target site: Done")
        
        out_path = folder_main
        out_name = "{0}_{1}.shp".format(fc_out_interf, id_tx)
        geometry_type = "POINT"
        template = "{0}/CovOutput_one_tx.shp".format(folder_main)
        #template = "{0}/CovOutput_one_tx.shp".format("D:/ws_temp")
        has_m = "DISABLED"
        has_z = "ENABLED"
        spatial_reference = arcpy.Describe(fc_users).spatialReference
        arcpy.AddMessage("Creating SINR Output FC: Start")
        arcpy.CreateFeatureclass_management(out_path, out_name, geometry_type,
                                    template, has_m, has_z, spatial_reference)
        arcpy.AddMessage("Creating SINR Output FC: Done")
        
        arcpy.AddMessage("Adding SINR Field to Output FC: Start")
        arcpy.AddField_management(out_name, "SINR", "DOUBLE")
        arcpy.AddMessage("Adding SINR Field to Output FC: Done")
        
        arcpy.AddMessage("Saving SINR(C/I) on FC: Start")
        field_out = ["Shape","OPSIM_RSL","RxOID","TxOID","H_DEM", "SINR"]
        cur_out = arcpy.da.InsertCursor(out_name, field_out)  
        i = 0
        while i < n_tar:
            row_out = list_SNIR[i]
            cur_out.insertRow(row_out)
            i+=1
        arcpy.AddMessage("Saving SNIR(C/I) on FC: Done")
        
        # Convert to 3D FC
        arcpy.AddMessage("Convert Output FC to 3D FC: Start on Tx_{0}".format(id_tx))
        fc_output_3D = "InterfOutput3D_tx_{0}.shp".format(id_tx)
        arcpy.FeatureTo3DByAttribute_3d(out_name, fc_output_3D, 'H_DEM')
        arcpy.AddMessage("Convert Output FC to 3D FC: Done on Tx_{0}".format(id_tx))
        #'''
        
        # Create output FC backup to ws_interf
        if folder_interf != None:
            arcpy.AddMessage("Create output FC backup to main: Start on Tx_{0}".format(id_tx))
            arcpy.Copy_management(fc_output_3D, "{0}/{1}".format(folder_interf,out_name))
            arcpy.AddMessage("Create output FC backup to main: Done on Tx_{0}".format(id_tx))
        #'''
        
        arcpy.GetMessages(0)
        arcpy.CheckInExtension("3D")
        arcpy.CheckInExtension("Spatial")
        arcpy.CheckInExtension("Network")
        
    except arcpy.ExecuteError:
        print arcpy.GetMessages()

if __name__ == '__main__':
    '''fc_sites	    = arcpy.GetParameterAsText(0)
    fc_site_one     = arcpy.GetParameterAsText(1)
    fc_users 	    = arcpy.GetParameterAsText(2)
    fc_dhm 		    = arcpy.GetParameterAsText(3)
    fc_clut  	    = arcpy.GetParameterAsText(4)
    fc_par_tx	    = arcpy.GetParameterAsText(5)
    fc_PR_Cov	    = arcpy.GetParameterAsText(6)
    fc_out_interf 	= arcpy.GetParameterAsText(7)
    folder_out      = arcpy.GetParameterAsText(8)
    folder_main     = arcpy.GetParameterAsText(9)
    folder_param    = arcpy.GetParameterAsText(10)
    #'''
    
    # Read the parameters values from cmd args
    fc_sites	    = sys.argv[1]
    fc_site_one     = sys.argv[2]
    fc_users 	    = sys.argv[3]
    fc_dhm 		    = sys.argv[4]
    fc_clut  	    = sys.argv[5]
    fc_par_tx	    = sys.argv[6]
    fc_PR_Cov	    = sys.argv[7]
    fc_out_interf 	= sys.argv[8]
    folder_out      = sys.argv[9]
    folder_main     = sys.argv[10]
    folder_param    = sys.argv[11]
    #'''
    
    if '' in [fc_sites, fc_site_one, fc_users, fc_dhm, fc_clut, fc_par_tx, fc_PR_Cov, fc_out_interf]:
        '''fc_sites	    = "D:/ws_temp/OpsimSitePosition.shp"
        fc_site_one     = "D:/ws_temp/site_one.shp"
        fc_users 	    = "D:/Workspaces/CityEngine/CityEngine2014/OPSIM-CM-Creation/data/UseCase0-YDE-PosteCen/yde_pc_uy1_DEM_3D.shp"
        fc_dhm          = "D:/Workspaces/CityEngine/CityEngine2014/OPSIM-CM-Creation/data/UseCase0-YDE-PosteCen/yde_pc_uy1_dhm_3D.shp"
        fc_clut         = "D:/Workspaces/CityEngine/CityEngine2014/OPSIM-CM-Creation/data/UseCase0-YDE-PosteCen/yde_pc_uy1_clutter.shp"
        fc_par_tx       = "D:/Workspaces/CityEngine/CityEngine2014/OPSIM-CM-Creation/data/UseCase0-YDE-PosteCen/param_tx_3D.shp"
        fc_PR_Cov       = "D:/Workspaces/CityEngine/CityEngine2014/OPSIM-CM-Creation/data/UseCase0-YDE-PosteCen/planResultCov.shp"
        fc_out_interf 	= "InterfOutput_tx"
        folder_out      = "D:/ws_out"
        folder_main     = "D:/ws_main"
        folder_param    = "D:/Installed/ArcGIS/DeveloperKit10.2/opsim/data_tunisia"
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
    arcpy.AddMessage("folder_main : %s" % (folder_main))
    #'''
    
    occInterfOne(fc_sites, fc_site_one, fc_users, fc_dhm, fc_clut, fc_par_tx, fc_PR_Cov, 0,
                              fc_out_interf, folder_out, folder_param, folder_main)
    # Get ending time
    t_exec = time.clock() - t_start
    if (t_exec/60) < 1:
        arcpy.AddMessage("OCC-Multi complete in : %s sec." % (time.clock() - t_start))
    else:
        arcpy.AddMessage("OCC-Multi complete in : %s Min." % ((time.clock() - t_start)/60))
    
    