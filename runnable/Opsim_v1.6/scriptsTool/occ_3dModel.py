# Import arcpy site-package
#
import arcpy
import os
import sys
import math
import time

from arcpy import env

def occCal3dModel(fc_dtm, fc_dhm, gdb_in, gdb_out, folder_out):
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
        
        # Spatial Join of fc_dhm on fc_dtm
        arcpy.AddMessage("Spatial Join of fc_dhm on fc_dtm: Start")
        fc_targets          = fc_dhm
        fc_features         = fc_dtm
        out_feature_class   = "spJoin_dhm_dtm.shp"
        join_operation      = "JOIN_ONE_TO_ONE"
        join_type           = "KEEP_COMMON"
        arcpy.SpatialJoin_analysis(fc_targets, fc_features, out_feature_class,
                                   join_operation, join_type)
        arcpy.AddMessage("Spatial Join of fc_dhm on fc_dtm: Done")
        
        # Extract building polygons
        arcpy.AddMessage("Extract building polygons: Start")
        whereclause = '"CLUTTER_ID" = 7 AND SURFACE_H >= 4'
        #whereclause = '"CLUTTER_ID" >= 0 AND SURFACE_H >= 0'
        fc_sel_in = out_feature_class
        fc_sel_out = "fc_building.shp"
        arcpy.Select_analysis(fc_sel_in, fc_sel_out, whereclause)
        arcpy.AddMessage("Extract building polygons: Done")
        
        # Extrude building fc to SURFACE_H
        '''Create a personnal gdb'''
        ws_gdb = "{0}/{1}".format(folder_out,gdb_out)
        if not os.path.exists(ws_gdb):
            arcpy.AddMessage("Create a personnal gdb: Start")
            arcpy.CreateFileGDB_management(folder_out, gdb_out)
            arcpy.AddMessage("Create a personnal gdb: Done")
        else :
            arcpy.AddMessage("Delete features in fc_mp_dhm: Start")
            fc_del_in = "{0}/{1}".format(ws_gdb, "fc_mp_dhm")
            arcpy.Delete_management(fc_del_in)
            arcpy.AddMessage("Delete features in fc_mp_dhm: Done")
        
        '''Copy MultiPatch FC fc_mp_dhm from gdb_in'''
        arcpy.AddMessage("Copy MultiPatch FC fc_mp_dhm from gdb_in: Start")
        fc_copy_in  = "{0}/fc_mp_dhm".format(gdb_in)
        fc_copy_out = "{0}/{1}/fc_mp_dhm".format(folder_out, gdb_out)
        arcpy.CopyFeatures_management(fc_copy_in, fc_copy_out)
        arcpy.AddMessage("Copy MultiPatch FC fc_mp_dhm from gdb_in: Done")
        
        '''Extrud MultiPatch FC fc_mp_dhm'''
        """arcpy.AddMessage("Extrud MultiPatch FC fc_mp_dhm: Start")
        fc_building = fc_sel_out
        fc_mp_dhm = fc_copy_out
        fields_bld = ["OID@","SHAPE@XY","SHAPE@Z","CLUTTER_ID","SURFACE_ID","SURFACE_H"]
        with arcpy.da.SearchCursor(fc_building,fields_bld) as cur_bld:
            for row_bld in cur_bld
                surf_H = row_bld[5]                       
                
        arcpy.AddMessage("Extrud MultiPatch FC fc_mp_dhm: Done")
        #""" 
    except arcpy.ExecuteError:
        print arcpy.GetMessages()
        
if __name__ == '__main__':
    # Read the parameter values from Arc:
    '''fc_dtm 	= arcpy.GetParameterAsText(0)
    fc_dhm 		= arcpy.GetParameterAsText(1)
    gdb_in 		= arcpy.GetParameterAsText(2)
    gdb_out  	= arcpy.GetParameterAsText(2)
    folder_out  = arcpy.GetParameterAsText(4)
    #'''
    
    # Read the parameters values from cmd args
    fc_dtm      = sys.argv[1]
    fc_dhm 	    = sys.argv[2]
    gdb_in 		= sys.argv[3]
    gdb_out  	= sys.argv[4]
    folder_out  = sys.argv[5] 
    #'''
    
    if '' in [fc_dtm, fc_dhm, gdb_in, gdb_out]:
        fc_dtm      = "C:\Users\Romuald FOTSO\Documents\data_cameroon\UseCase0-YDE-PosteCen\yde_pc_uy1_DEM_3D.shp"
        fc_dhm 	    = "C:\Users\Romuald FOTSO\Documents\data_cameroon\UseCase0-YDE-PosteCen\yde_pc_uy1_dhm_3D.shp"
        gdb_in 		= "D:\Workspaces\eclipse-dev\eclipse-j2se\OPSIM\gdb\opsim.gdb"
        gdb_out  	= "opsim.gdb"
        folder_out  =  "C:\Users\Romuald FOTSO\opsim\ws_3DModel"
        #'''
    
        # Get starting time
    t_start = time.clock()
    arcpy.AddMessage("fc_dtm : %s" % (fc_dtm))
    arcpy.AddMessage("fc_dhm : %s" % (fc_dhm))
    arcpy.AddMessage("gdb_in : %s" % (gdb_in))
    arcpy.AddMessage("gdb_out : %s" % (gdb_out))
    arcpy.AddMessage("folder_out : %s" % (folder_out))
    #'''
    
    occCal3dModel(fc_dtm, fc_dhm, gdb_in, gdb_out, folder_out)
    # Get ending time
    t_exec = time.clock() - t_start
    if (t_exec/60) < 1:
        arcpy.AddMessage("OCC-Cal3dModel complete in : %s sec." % (time.clock() - t_start))
    else:
        arcpy.AddMessage("OCC-Cal3dModel complete in : %s Min." % ((time.clock() - t_start)/60))
        #'''  