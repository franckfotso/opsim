'''******************************************************************
    OPSIM-Tools: Functions for Processing site position	
******************************************************************''' 

from calmetric import *

'''def getMaxListIndex(list_site): 
    max_key = -1
    max_H = 0
    i = 0
    for p_site in list_site:
        if p_site.Z > max_H:
            max_H = p_site.Z
            max_key = i
        i+=1    
    return max_key'''
    
def getMaxHeightRow(fc_pnt_can):
    fields_can = ["OID@","SHAPE@XY","SHAPE@Z","POINTID","CLUTTER_ID","Surf_eff","H_DEM","H_eff_r"]
    list_H = ()
    list_next_can = {}
    arcpy.AddMessage("Collect Candiate site list: Start")
    with arcpy.da.SearchCursor(fc_pnt_can, fields_can) as cur_can:
        i = 0
        for row_can in cur_can:
            list_H.append(row_can[7])
            list_next_can[i] = row_can
            i+=1
    arcpy.AddMessage("Collect Candiate site list: Done")
    arcpy.AddMessage("Num. can site: {0}".format(len(list_next_can)))
    
    max_list = getMaxListIndex(list_H)
    index_max = max_list[0]
    H_el = max_list[1]
    row_el = list_next_can[index_max]
    del list_next_can[index_max]
    
    return [H_el, row_el]

def getNextSiteEl(H_el, row_el, r_site):
    # Create FC for site elected
    arcpy.AddMessage("Create FC for site elected: Start")
    fc_site_el = "fc_site_el.shp"
    whereclause = '"POINTID" = {0}'.format(row_el[3])
    arcpy.Select_analysis(fc_selOn_DSafe, fc_site_el, whereclause)
    arcpy.AddMessage("Collect Candiate site list: Done")
    
    D_IS = r_site*2-(5/100)*r_site    
    D_lim_max = D_IS+(5/100)*r_site/2
    D_lim_min = D_IS-(5/100)*r_site/2
    
    # Create buffer round on D_lim_max
    arcpy.AddMessage("Create buffer round on D_lim_max: Start")
    in_features = fc_site_el
    out_feature_class = "buff_max_el.shp"
    distanceField = "{0} Meters".format(D_lim_max) 
    sideType = "FULL"
    endType = "ROUND"
    dissolveType = "NONE"
    dissolveField = "#"
    arcpy.Buffer_analysis(in_features, out_feature_class, distanceField, sideType, endType, dissolveType)
    arcpy.AddMessage("Create buffer round on D_lim_max: Done")
    
    # Create buffer round on D_lim_min
    arcpy.AddMessage("Create buffer round on D_lim_min: Start")
    in_features = fc_site_el
    out_feature_class = "buff_min_el.shp"
    distanceField = "{0} Meters".format(D_lim_min) 
    sideType = "FULL"
    endType = "ROUND"
    dissolveType = "NONE"
    dissolveField = "#"
    arcpy.Buffer_analysis(in_features, out_feature_class, distanceField, sideType, endType, dissolveType)
    arcpy.AddMessage("Create buffer round on D_lim_min: Done")
    
    #  Getting DTM points on buffer_max
    fc_targets = fc_selOn_DSafe
    fc_features = "buff_max_el.shp"
    out_feature_class = "fc_pnt_buffMax.shp"
    join_operation = "JOIN_ONE_TO_ONE"
    join_type = "KEEP_COMMON"
    arcpy.AddMessage("Getting DTM points on buffer_max: Start")
    arcpy.SpatialJoin_analysis(fc_targets, fc_features, out_feature_class,
                               join_operation, join_type)
    arcpy.AddMessage("Getting DTM points on buffer_max: Done")    

    # Erase points on buffer_min
    arcpy.AddMessage("Erase points on buffer_min: Start")
    in_features = "fc_pnt_buffMax.shp"
    erase_features = "buff_min_el.shp"
    eraseOutput = "fc_pnt_can.shp"
    arcpy.Erase_analysis(in_features, erase_features, eraseOutput)
    arcpy.AddMessage("Erase points on buffer_min: Done")
    
    # Getting point with the most height
    fc_pnt_can = "fc_pnt_can.shp"
    res = getMaxHeightRow(fc_pnt_can)
    
    return fc_site_el    
    
def validateSite(p_can, dic_choSite, D_IS):
    isValid = 1    
    for p_cho in dic_choSite.values():
        D = calDistance2D(p_can, p_cho)
        arcpy.AddMessage("D = {0}".format(D))
        arcpy.AddMessage("D_IS = {0}".format(D_IS))
        if D < D_IS:
            isValid = 0
    return isValid
    