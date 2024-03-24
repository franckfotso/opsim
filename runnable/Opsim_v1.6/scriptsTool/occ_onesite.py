# Import arcpy site-package
#
import arcpy, arcinfo
import os
import sys
import math
import threading
import time
import multiprocessing

from arcpy import env
from tools.calcoverage import calByOkumuraHata
from tools.calcoverage import calByCost231Hata

def occOneSiteCal(fc_sites, fc_users, fc_dhm, fc_clut, fc_par_tx, fc_PR_Cov, id_tx, fc_output, folder_out, folder_param, folder_main=None):        
    try:
        arcpy.overwriteOutputs = True
        
        # Checking out ArcGIS Extensions:
        arcpy.CheckOutExtension('3D')
        arcpy.CheckOutExtension("Spatial")
        arcpy.CheckOutExtension("Network")   
        
        if not os.path.exists(folder_out):
            os.makedirs(folder_out)
        
        for the_file in os.listdir(folder_out):
            file_path = os.path.join(folder_out, the_file)
            try:
                if os.path.isfile(file_path):
                    os.unlink(file_path)
            except Exception, e:
                print e

        # Set Worspace Path:
        env.workspace = folder_out
        
        '''******************************************************************
        OPSIM-GP: 1 - Getting param_tx_3D
        ******************************************************************'''
        # Setting up coverage planning parameters
        fields_pr = ["OID@","PIRE","S_UL","S_DL","PathLoss","CellRadCov",
                     "CellPrint","SitePrint","NumSite","IS_Dist","PM_ID"]
        whereclause = '"FID" = 0'
        with arcpy.da.SearchCursor(fc_PR_Cov, fields_pr, whereclause) as cur_pr:
            for row_pr in cur_pr:
                pm_id = row_pr[10]
                S_DL = row_pr[3]    

        '''******************************************************************
        OPSIM-GP: 2 - Processing Coverage Planning
        ******************************************************************'''
        arcpy.AddMessage("Processing Coverage Planning: Start on Tx_{0}".format(id_tx))
        listRSL_Rx = list()
        if pm_id == 0:
            # PM: okumuraHata
            #listRSL_Rx = list()
            listRSL_Rx = calByOkumuraHata(fc_sites, fc_users, fc_dhm, fc_clut, fc_par_tx, fc_PR_Cov, id_tx, folder_out, folder_param, fc_output)
        elif pm_id == 1:
            # PM: cost231Hata
            listRSL_Rx = calByCost231Hata(fc_sites, fc_users, fc_dhm, fc_clut, fc_par_tx, fc_PR_Cov, id_tx, folder_out, folder_param, fc_output)
        elif pm_id == 2:
            # PM: cost231WI
            listRSL_Rx = list()
        elif pm_id == 3:
            # PM: kfactors
            listRSL_Rx = list()
        else:
            arcpy.AddError("Error in PM input")
        arcpy.AddMessage("Processing Coverage Planning: Done on Tx_{0}".format(id_tx))
        
        '''******************************************************************
        OPSIM-GP: 3 - Create output FC and saving all result
        ******************************************************************'''        
        # Setting up input and output variables:
        out_path = folder_out
        out_name = "OpsimCalCovOutput_tx_{0}.shp".format(id_tx)
        geometry_type = "POINT"
        template = "#"
        has_m = "DISABLED"
        has_z = "ENABLED"
        spatial_reference = arcpy.Describe(fc_users).spatialReference
        arcpy.AddMessage("Creating Output FC: Start on Tx_{0}".format(id_tx))
        arcpy.CreateFeatureclass_management(out_path, out_name, geometry_type,
                                    template, has_m, has_z, spatial_reference)
        arcpy.AddMessage("Creating Output FC: Done on Tx_{0}".format(id_tx))
        
        # Adding new field
        arcpy.AddMessage("Adding New Field to Output FC: Start on Tx_{0}".format(id_tx))
        arcpy.AddField_management(out_name, "OPSIM_RSL", "DOUBLE")
        arcpy.AddField_management(out_name, "RxOID", "LONG")
        arcpy.AddField_management(out_name, "TxOID", "LONG")
        arcpy.AddField_management(out_name, "H_DEM", "DOUBLE")
        arcpy.AddMessage("Adding New Field to Output FC: Done on Tx_{0}".format(id_tx))
        
        # Fill field RSCP in output FC
        arcpy.AddMessage("Filling Output FC: Start on Tx_{0}".format(id_tx))
        field_out = ["Shape","OPSIM_RSL","RxOID","TxOID","H_DEM"]
        cur_out = arcpy.da.InsertCursor(out_name, field_out)        
        for listRSL in listRSL_Rx:
            RSL = listRSL[0][1]
            #arcpy.AddMessage("RSL = {0}".format(RSL))  
            if RSL <= S_DL:
                RSL = -9999
            T_Tx = listRSL[1]
            T_Rx = listRSL[2]
            p_tx = arcpy.Point(T_Tx[0], T_Tx[1], T_Tx[2], 0, T_Tx[3])
            p_rx = arcpy.Point(T_Rx[0], T_Rx[1], T_Rx[2], 0, T_Rx[3])
            pntGeoRx = arcpy.PointGeometry(p_rx)
            cur_out.insertRow([pntGeoRx, RSL, p_rx.ID, p_tx.ID, p_rx.Z])            
        arcpy.AddMessage("Filling Output FC: Done on Tx_{0}".format(id_tx))
        
        # Convert to 3D FC
        arcpy.AddMessage("Convert Output FC to 3D FC: Start on Tx_{0}".format(id_tx))
        fc_output_3D = "OpsimCalCovOutput3D_tx_{0}.shp".format(id_tx)
        arcpy.FeatureTo3DByAttribute_3d(out_name, fc_output_3D, 'H_DEM')
        arcpy.AddMessage("Convert Output FC to 3D FC: Done on Tx_{0}".format(id_tx))
        #'''
        
        # Create output FC backup to main
        if folder_main != None:
            arcpy.AddMessage("Create output FC backup to main: Start on Tx_{0}".format(id_tx))
            #arcpy.Copy_management(fc_output_3D, "{0}/{1}".format(folder_main,fc_output_3D))
            arcpy.Copy_management(fc_output_3D, fc_output)
            arcpy.AddMessage("Create output FC backup to main: Done on Tx_{0}".format(id_tx))
        #'''
        
        arcpy.GetMessages(0)
        arcpy.CheckInExtension("3D")
        arcpy.CheckInExtension("Spatial")
        arcpy.CheckInExtension("Network")	
    except arcpy.ExecuteError:
        print arcpy.GetMessages()

if __name__ == '__main__':
    # Read the parameters values from Arc:
    '''fc_sites	= arcpy.GetParameterAsText(0)
    fc_users 	= arcpy.GetParameterAsText(1)
    fc_dhm 		= arcpy.GetParameterAsText(2)
    fc_clut  	= arcpy.GetParameterAsText(3)
    fc_par_tx	= arcpy.GetParameterAsText(4)
    fc_PR_Cov	= arcpy.GetParameterAsText(5)
    id_tx       = arcpy.GetParameterAsText(6)
    fc_output  	= arcpy.GetParameterAsText(7)
    #'''
    
    # Read the parameters values from cmd args
    fc_sites	= sys.argv[1]
    fc_users 	= sys.argv[2]
    fc_dhm 		= sys.argv[3]
    fc_clut  	= sys.argv[4]
    fc_par_tx	= sys.argv[5]
    fc_PR_Cov	= sys.argv[6]
    id_tx       = sys.argv[7]
    fc_output  	= sys.argv[8]
    folder_out  = sys.argv[9]
    folder_param  = sys.argv[10]
    #'''

    if '' in [fc_sites, fc_users, fc_dhm, fc_clut, fc_par_tx, fc_PR_Cov, fc_output]:
        '''fc_sites = "D:/Installed/ArcGIS/DeveloperKit10.2/opsim/data_tunisia/site_tx_3D.shp"
        #fc_sites = "D:/ws_temp/fc_tx_0.shp"
        fc_users = "D:/Installed/ArcGIS/DeveloperKit10.2/opsim/data_tunisia/user_rx_3D.shp"
        #fc_users = "D:/Installed/ArcGIS/DeveloperKit10.2/opsim/data_tunisia/tun_DEM_point_Range100m_3D.shp"
        #fc_users = "D:/ws_temp/OpsimCalCovOutput3D_tx_0_err.shp"
        fc_dhm = "D:/Installed/ArcGIS/DeveloperKit10.2/opsim/data_tunisia/tun_dhm_3D_del.shp"
        fc_clut = "D:/Installed/ArcGIS/DeveloperKit10.2/opsim/data_tunisia/tun_clutter.shp"
        fc_par_tx = "D:/Installed/ArcGIS/DeveloperKit10.2/opsim/data_tunisia/param_tx_3D.shp"
        fc_PR_Cov = "D:/Installed/ArcGIS/DeveloperKit10.2/opsim/data_tunisia/planResultCov.shp"
        id_tx = 0
        fc_output = "D:\ws_out\OpsimCalCovOutput_tx_{0}.shp".format(id_tx)
        folder_out = "D:\ws_out"
        folder_param  = "D:/Installed/ArcGIS/DeveloperKit10.2/opsim/data_tunisia"
        #'''
    
    # Get starting time
    t_start = time.clock()
    arcpy.AddMessage("fc_sites : %s" % (fc_sites))
    arcpy.AddMessage("fc_users : %s" % (fc_users))
    arcpy.AddMessage("fc_dhm : %s" % (fc_dhm))
    arcpy.AddMessage("fc_clut : %s" % (fc_clut))
    arcpy.AddMessage("fc_par_tx : %s" % (fc_par_tx))
    arcpy.AddMessage("fc_PR_Cov : %s" % (fc_PR_Cov))
    arcpy.AddMessage("id_tx : %s" % (id_tx))
    arcpy.AddMessage("fc_output : %s" % (fc_output))
    arcpy.AddMessage("folder_out : %s" % (folder_out))
    arcpy.AddMessage("folder_param : %s" % (folder_param))
    #'''
    
    occOneSiteCal(fc_sites, fc_users, fc_dhm, fc_clut, fc_par_tx, fc_PR_Cov, id_tx, fc_output, folder_out, folder_param)
    # Get ending time
    t_exec = time.clock() - t_start
    if (t_exec/60) < 1:
        arcpy.AddMessage("OCC complete in : %s sec." % (time.clock() - t_start))
    else:
        arcpy.AddMessage("OCC complete in : %s Min." % ((time.clock() - t_start)/60))

