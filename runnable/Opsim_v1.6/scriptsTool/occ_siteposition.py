# Import arcpy site-package
#
import arcpy
import os
import sys
import math
import time
import multiprocessing
import operator

from tools.calposition import *
from tools.calmetric import *

from arcpy import env

def occSitePosition(fc_users, fc_dhm, fc_clut, fc_par_tx, fc_PR_Cov, fc_output, folder_out, D_safe=None):
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
        OPSIM-GP: 1 - Selecting points matched with valid cluter
        ******************************************************************'''
        # Get Parameters result coverage planning
        fields_pr = ["CellRadCov"]
        whereclause = '"FID" = 0'
        with arcpy.da.SearchCursor(fc_PR_Cov, fields_pr, whereclause) as cur_pr:
            for row_pr in cur_pr:
                r_site = row_pr[0]
            del row_pr
        del cur_pr
        
        fields_par = ["H_ant"]
        whereclause = '"FID" = 0'
        with arcpy.da.SearchCursor(fc_par_tx, fields_par, whereclause) as cur_par:
            for row_par in cur_par:
                H_ant = row_par[0]     
            del row_par
        del cur_par
        
        # Select points which belong to valid clutter && remove those with Surf > H_ant
        arcpy.AddMessage("1.1-Select points which belong to valid clutter: Start")
        whereclause = '"SURFACE_H" < {0}'.format(H_ant)
        whereclause += ' AND ( "CLUTTER_ID" = 1 OR "CLUTTER_ID" = 4 OR "CLUTTER_ID" = 5 OR "CLUTTER_ID" = 6 OR "CLUTTER_ID" = 7'
        whereclause += ' OR "CLUTTER_ID" = 8 OR "CLUTTER_ID" = 10 OR "CLUTTER_ID" = 13'
        whereclause += ' OR "CLUTTER_ID" = 14 OR "CLUTTER_ID" = 15 OR "CLUTTER_ID" = 16 OR "CLUTTER_ID" = 19 )'
        fc_point2sel = fc_users
        fc_selOnClut = "fc_selOn_Clut.shp"
        #arcpy.AddMessage("1.1-whereclause: {0}".format(whereclause))
        arcpy.Select_analysis(fc_point2sel, fc_selOnClut, whereclause)
        #'''
        arcpy.AddMessage("1.1-Select points which belong to valid clutter: Done")
        
        
        # Select points which belong to forbidden clutter: For this sample we just use one cluter id "9"
        arcpy.AddMessage("1.2-Select points which belong to forbidden clutter: Start")
        listForbidPnt = [9]
        whereclause = ""
        i = 0
        for clut_id in listForbidPnt:
            if i == (len(listForbidPnt)-1):
                whereclause += " CLUTTER_ID = {0}".format(clut_id)
            else :
                whereclause += " CLUTTER_ID = {0} OR ".format(clut_id)
            i+=1
        fc_forbidPnt =  "fc_forbidPnt.shp"       
        arcpy.Select_analysis(fc_point2sel, fc_forbidPnt, whereclause)
        arcpy.AddMessage("1.2-Select points which belong to forbidden clutter: Done")
        
        '''******************************************************************
        OPSIM-GP: 2 - Removing forbidden points and all points arround D_Safe
        ******************************************************************'''
        # Create round buffer on forbidden points
        arcpy.AddMessage("2.1-Create round buffer on forbidden points: Start")
        if D_safe == None:            
            D_safe = r_site+(0.17*r_site)
        in_features = fc_forbidPnt
        out_feature_class = "buff_fb.shp"
        distanceField = "{0} Meters".format(D_safe)
        sideType = "FULL"
        endType = "ROUND"
        dissolveType = "ALL"
        dissolveField = "#"
        arcpy.Buffer_analysis(in_features, out_feature_class, distanceField, sideType, endType, dissolveType)           
        arcpy.AddMessage("2.1-Create round buffer on forbidden points: Done")
        
        # Remove candidate points in D_safe radius
        arcpy.AddMessage("2.2-Remove candidate points in D_safe: Start")
        in_features = fc_selOnClut
        erase_features = "buff_fb.shp"
        eraseOutput = "fc_selOn_DSafe.shp"
        arcpy.Erase_analysis(in_features, erase_features, eraseOutput)
        arcpy.AddMessage("2.2-Remove candidate points in D_safe: Done")    
        #
        
        '''******************************************************************
        OPSIM-GP: 3 - Placing site on the best candidate position
        ******************************************************************''' 
        # Looking for candidate site with max Height
        fc_selOn_DSafe = "fc_selOn_DSafe.shp"
        fields_can = ["OID@","SHAPE@XY","SHAPE@Z","POINTID","CLUTTER_ID","Surf_eff","H_DEM","H_eff_r"]
        dic_H = {}
        dic_canSite = {}
        dic_choSite = {}
        dic_finSite = {} # for method 2 only
        arcpy.AddMessage("3.1-Collect Candiate site list: Start")
        with arcpy.da.SearchCursor(fc_selOn_DSafe, fields_can) as cur_can:
            for row_can in cur_can:
                oid_can = row_can[3]
                x_can, y_can = row_can[1]
                z_can = row_can[7]            
                p_can = arcpy.Point(x_can, y_can, z_can, 0, oid_can)
                dic_H[p_can.ID] = p_can.Z
                dic_canSite[p_can.ID] = p_can
                
        arcpy.AddMessage("3.1-Collect Candiate site list: Done")
        
        arcpy.AddMessage("Num. candidate site: {0}".format(len(dic_canSite)))
        
        D_IS = (r_site*2)-(0.05*r_site)
        arcpy.AddMessage("r_site = {0}".format(r_site))
        arcpy.AddMessage("D_IS = {0}".format(D_IS))
        
        # Method 1: based on max height of building in area
        arcpy.AddMessage("3.2-Method 1 - based on max height of building: Start")
        while len(dic_canSite) > 0:
            max_key = max(dic_H.iteritems(), key=operator.itemgetter(1))[0]
            
            p_can = dic_canSite[max_key]
            state = validateSite(p_can, dic_choSite, D_IS)
            if state == 1:                    
                dic_choSite[max_key] = dic_canSite[max_key]
                del dic_canSite[max_key]
                del dic_H[max_key]
                arcpy.AddMessage("siteCan[{0}]: ADD".format(max_key))
            else :
                del dic_canSite[max_key]
                del dic_H[max_key]
                arcpy.AddMessage("siteCan[{0}]: DEL".format(max_key))
                
            arcpy.AddMessage("Num. canSite remaining: {0}".format(len(dic_canSite)))
            arcpy.AddMessage("Num. choSite founded: {0}".format(len(dic_choSite)))
            
        arcpy.AddMessage("3.2-Method 1 - based on max height of building: Done")
        #'''
        
        # Method 2: based on max height of building around on site
        
        '''******************************************************************
            OPSIM-GP: 4 - Store or merge all final/choosen site in output FC
        ******************************************************************'''       
        # Method 1: based on max height of building in area
        arcpy.AddMessage("4.1-Store or merge all final/choosen site: start")
        #fc_choSite = "fc_choSite.shp"
        fc_choSite = fc_output
        i = 0
        whereclause = ""
        for p_cho in dic_choSite.values():
            if i == (len(dic_choSite)-1):
                whereclause += " POINTID = {0}".format(p_cho.ID)
            else :
                whereclause += " POINTID = {0} OR ".format(p_cho.ID)
            i+=1        
        arcpy.Select_analysis(fc_selOn_DSafe, fc_choSite, whereclause)
        arcpy.AddMessage("4.1-Store or merge all final/choosen site: Done")
        
        arcpy.AddMessage("4.2-Update height of choosen site: start")
        arcpy.AddField_management(fc_choSite, "Tower_H", "DOUBLE")
        arcpy.CalculateField_management(fc_choSite, "Surf_eff", H_ant, "PYTHON_9.3")
        arcpy.CalculateField_management(fc_choSite, "H_eff_r", "!H_POINT!+{}".format(H_ant), "PYTHON_9.3")
        arcpy.CalculateField_management(fc_choSite, "Tower_H", "!Surf_eff!+!SURFACE_H!", "PYTHON_9.3")
        arcpy.AddMessage("4.2-Update height of choosen site: Done")
        #'''"""            
        
        arcpy.GetMessages(0)
        arcpy.CheckInExtension("3D")
        arcpy.CheckInExtension("Spatial")
        arcpy.CheckInExtension("Network")
        
    except arcpy.ExecuteError:
        print arcpy.GetMessages()

if __name__ == '__main__':
    # Read the parameter values from Arc:
    '''fc_users 	= arcpy.GetParameterAsText(0)
    fc_dhm 		= arcpy.GetParameterAsText(1)
    fc_clut  	= arcpy.GetParameterAsText(2)
    fc_par_tx	= arcpy.GetParameterAsText(3)
    fc_PR_Cov	= arcpy.GetParameterAsText(4)
    D_safe      = arcpy.GetParameterAsText(5)
    fc_output  	= arcpy.GetParameterAsText(6)
    #'''
    
    # Read the parameters values from cmd args
    fc_users 	= sys.argv[1]
    fc_dhm 		= sys.argv[2]
    fc_clut  	= sys.argv[3]
    fc_par_tx	= sys.argv[4]
    fc_PR_Cov	= sys.argv[5]
    D_safe      = None
    fc_output  	= sys.argv[7]
    folder_out  = sys.argv[8]
    #'''

    if '' in [fc_users, fc_dhm, fc_clut, fc_par_tx, fc_PR_Cov, fc_output]:
        '''
        fc_users = "D:\Installed\ArcGIS\DeveloperKit10.2\opsim\data_tunisia\\tun_DEM_point_3D.shp"
        fc_dhm = "D:\Installed\ArcGIS\DeveloperKit10.2\opsim\data_tunisia\\tun_dhm_3D_del.shp"
        fc_clut = "D:\Installed\ArcGIS\DeveloperKit10.2\opsim\data_tunisia\\tun_clutter.shp"
        fc_par_tx = "D:\Installed\ArcGIS\DeveloperKit10.2\opsim\data_tunisia\param_tx_3D.shp"
        fc_PR_Cov = "D:\Installed\ArcGIS\DeveloperKit10.2\opsim\data_tunisia\\planResultCov.shp"
        D_safe = None
        fc_output = "D:\ws_out\OpsimSitePosition.shp"
        #'''
        '''fc_users = "C:\Users\Romuald FOTSO\Documents\data_tunisia/tun_DEM_point_3D.shp"
        fc_dhm = "C:\Users\Romuald FOTSO\Documents\data_tunisia/tun_dhm_3D_del.shp"
        fc_clut = "C:\Users\Romuald FOTSO\Documents\data_tunisia/tun_clutter.shp"
        fc_par_tx = "C:\Users\Romuald FOTSO\opsim\ws_param/param_tx.shp"
        fc_PR_Cov = "C:\Users\Romuald FOTSO\opsim\ws_param/planResultCov.shp"
        D_safe = None
        fc_output = "C:\Users\Romuald FOTSO\opsim\ws_result/uc1_tunis_OpsimSitePosition_1500mhz.shp"
        folder_out = "C:\Users\Romuald FOTSO\opsim\ws_out"
        #'''
    
    # Get starting time
    t_start = time.clock()
    arcpy.AddMessage("fc_users : %s" % (fc_users))
    arcpy.AddMessage("fc_dhm : %s" % (fc_dhm))
    arcpy.AddMessage("fc_clut : %s" % (fc_clut))
    arcpy.AddMessage("fc_par_tx : %s" % (fc_par_tx))
    arcpy.AddMessage("fc_PR_Cov : %s" % (fc_PR_Cov))
    arcpy.AddMessage("D_safe : %s" % (D_safe))
    arcpy.AddMessage("fc_output : %s" % (fc_output))
    arcpy.AddMessage("folder_out : %s" % (folder_out))
    #'''
    
    occSitePosition(fc_users, fc_dhm, fc_clut, fc_par_tx, fc_PR_Cov, fc_output, folder_out, D_safe)
    # Get ending time
    t_exec = time.clock() - t_start
    if (t_exec/60) < 1:
        arcpy.AddMessage("OCC-SitePosition complete in : %s sec." % (time.clock() - t_start))
    else:
        arcpy.AddMessage("OCC-SitePosition complete in : %s Min." % ((time.clock() - t_start)/60))
        #'''        