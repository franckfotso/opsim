'''******************************************************************
    OPSIM-Tools: Functions for Processing Attenuation	
******************************************************************''' 
import arcpy
import math

from calmetric import calDistance3D

# Function to calculate Mobile antenna height correction: Ah
def calMobileAntCorrection(p_tx, p_rx, freq, folder_param):
    Ah = 0.0
    
    fields_oku = ["zoneType"]
    fc_oku = "{0}/okumuraHata.shp".format(folder_param);
    #arcpy.AddMessage("fc_oku: {0}".format(fc_oku))
    with arcpy.da.SearchCursor(fc_oku, fields_oku) as cur_oku:
        for row_oku in cur_oku:
            zoneType = row_oku[0]
            
    if zoneType == "sub_urban":
        if freq <= 200:
            Ah = 8.29*math.pow(math.log10(1.54*(p_rx.Z+1.5)),2)-1.1
        elif freq >= 400:
            Ah = 3.2*math.pow(math.log10(11.75*(p_rx.Z+1.5)),2)-4.97
        else :
            arcpy.AddError("Incorrect Frequency Specified.")
    elif zoneType == "urban": # small city & medium size city
        Ah = (1.1*math.log10(freq)-0.7)*(p_rx.Z+1.5)-(1.56*math.log10(freq)-0.8)
    else :
        arcpy.AddError("Incorrect Zone type Specified.")
    
    return Ah
	
# Function to calculate obstructions attenuation: An
def calObstrAttenuation(fc_ob, listObFres, p_tx, p_rx, dist, freq, folder_param):
    An = 0.0
    
    Lp = calDiffractionLoss(fc_ob, p_tx, p_rx, freq)
    #Lsp = calMultiPathLoss(fc_ob, listObFres, p_tx, p_rx, dist, freq, folder_param)
    Lsp = 0.0
    An = Lp+Lsp
    
    return An

# Function to calculate field occupation attenuation: Az
def calOccFieldAttenuation(p_tx, p_rx, freq, H_dtm, fc_clut, folder_param):
    Az = 0.0
    
    fields_clut = ["OID@","SHAPE@XY","CLUTTER_ID"]
    with arcpy.da.SearchCursor(fc_clut, fields_clut) as cur_clut:
        for row_clut in cur_clut:
            clutID = row_clut[2]

    fields_oku = ["Diffr_Meth","Opt_clut","Atten_clut","zoneType","MP_Meth"]
    fc_oku = "{0}/okumuraHata.shp".format(folder_param);
    
    with arcpy.da.SearchCursor(fc_oku, fields_oku) as cur_oku:
        for row_oku in cur_oku:
            diffrMeth = row_oku[0]
            optClut = row_oku[1]
            attenClut = row_oku[2]
            zoneType = row_oku[3]
            mpMeth = row_oku[4]

    # optClut:
    if optClut == "over_clutter":
        H_Rx = p_rx.Z+1.5
    elif optClut == "over_ground":
        H_Rx = H_dtm
    else :
        arcpy.AddError("Incorrect Clutter option Specified.")

    # attenClut:
    if attenClut == "atten_UIT":
        if clutID == 0:
            if freq<=40:
                Lclut = 3 #dB
            elif 40<freq and freq<=40:
                Lclut = 5
            elif 160<freq and freq<=450:
                Lclut = 7
            elif freq>450:
                Lclut = 9
            else :
                arcpy.AddError("Attenuation clutter 1: Incorrect frequency Specified.")
        if clutID == 1:
	    if freq<=40:
                Lclut = 9 #dB
	    elif 40<freq and freq<=40:
                Lclut = 12
	    elif 160<freq and freq<=450:
                Lclut = 15
	    elif freq>450:
                Lclut = 18
	    else :
                arcpy.AddError("Attenuation clutter 1: Incorrect frequency Specified.")
	if clutID == 2:
	    if freq<=40:
                Lclut = 19 #dB
	    elif 40<freq and freq<=40:
                Lclut = 22
	    elif 160<freq and freq<=450:
                Lclut = 25
	    elif freq>450:
                Lclut = 28
	    else :
                arcpy.AddError("Attenuation clutter 1: Incorrect frequency Specified.")
	if clutID == 3:
	    if freq<=40:
                Lclut = 22 #dB
	    elif 40<freq and freq<=40:
                Lclut = 25
	    elif 160<freq and freq<=450:
                Lclut = 28
	    elif freq>450:
                Lclut = 31
            else :
                arcpy.AddError("Attenuation clutter 1: Incorrect frequency Specified.")
        if clutID == 4:
            if freq<=40:
                Lclut = 25 #dB
            elif 40<freq and freq<=40:
                Lclut = 28
            elif 160<freq and freq<=450:
                Lclut = 31
            elif freq>450:
                Lclut = 34
            else :
                arcpy.AddError("Attenuation clutter 1: Incorrect frequency Specified.")
        if clutID == 5:
            if freq<=40:
                Lclut = 27 #dB
            elif 40<freq and freq<=40:
                Lclut = 27
            elif 160<freq and freq<=450:
                Lclut = 27
            elif freq>450:
                Lclut = 27
            else :
                arcpy.AddError("Attenuation clutter 1: Incorrect frequency Specified.")
        if clutID == 6:
            if freq<=40:
                Lclut = 0 #dB
            elif 40<freq and freq<=40:
                Lclut = 0
            elif 160<freq and freq<=450:
                Lclut = 0
            elif freq>450:
                Lclut = 0
            else :
                arcpy.AddError("Attenuation clutter 1: Incorrect frequency Specified.")
        if clutID == 7:
            if freq<=40:
                Lclut = 29 #dB
            elif 40<freq and freq<=40:
                Lclut = 32
            elif 160<freq and freq<=450:
                Lclut = 35
            elif freq>450:
                Lclut = 38
            else :
                arcpy.AddError("Attenuation clutter 1: Incorrect frequency Specified.")
        if clutID == 8:
            if freq<=40:
                Lclut = 3 #dB
            elif 40<freq and freq<=40:
                Lclut = 5
            elif 160<freq and freq<=450:
                Lclut = 7
            elif freq>450:
                Lclut = 9
            else :
                arcpy.AddError("Attenuation clutter 1: Incorrect frequency Specified.")
        if clutID == 9:
            if freq<=40:
                Lclut = 0 #dB
            elif 40<freq and freq<=40:
                Lclut = 0
            elif 160<freq and freq<=450:
                Lclut = 0
            elif freq>450:
                Lclut = 0
            else :
                arcpy.AddError("Attenuation clutter 1: Incorrect frequency Specified.")
        else:
            if freq<=40:
                Lclut = 0 #dB
            elif 40<freq and freq<=40:
                Lclut = 0
            elif 160<freq and freq<=450:
                Lclut = 0
            elif freq>450:
                Lclut = 0
            else :
                arcpy.AddError("Attenuation clutter 1: Incorrect frequency Specified.")        
    elif attenClut == "atten_UER":
        if clutID == 0:
            if freq<300:
                Lclut = 2 #dB
            elif freq>=300:
                Lclut = 4
            else :
                arcpy.AddError("Attenuation clutter 2: Incorrect frequency Specified.")
        if clutID == 1:
            if freq<300:
                Lclut = 6 #dB
            elif freq>=300:
                Lclut = 8
            else :
                arcpy.AddError("Attenuation clutter 2: Incorrect frequency Specified.")
        if clutID == 2:
            if freq<300:
                Lclut = 8 #dB
            elif freq>=300:
                Lclut = 12
            else :
                arcpy.AddError("Attenuation clutter 2: Incorrect frequency Specified.")
        if clutID == 3:
            if freq<300:
                Lclut = 8 #dB
            elif freq>=300:
                Lclut = 12
            else :
                arcpy.AddError("Attenuation clutter 2: Incorrect frequency Specified.")
        if clutID == 4:
            if freq<300:
                Lclut = 8 #dB
            elif freq>=300:
                Lclut = 12
            else :
                arcpy.AddError("Attenuation clutter 2: Incorrect frequency Specified.")
        if clutID == 5:
            if freq<300:
                Lclut = 4 #dB
            elif freq>=300:
                Lclut = 8
            else :
                arcpy.AddError("Attenuation clutter 2: Incorrect frequency Specified.")
        if clutID == 6:
            if freq<300:
                Lclut = 0 #dB
            elif freq>=300:
                Lclut = 0
            else :
                arcpy.AddError("Attenuation clutter 2: Incorrect frequency Specified.")
        if clutID == 7:
            if freq<300:
                Lclut = 8 #dB
            elif freq>=300:
                Lclut = 12
            else :
                arcpy.AddError("Attenuation clutter 2: Incorrect frequency Specified.")
        if clutID == 8:
            if freq<300:
                Lclut = 4 #dB
            elif freq>=300:
                Lclut = 8
            else :
                arcpy.AddError("Attenuation clutter 2: Incorrect frequency Specified.")
        if clutID == 9:
            if freq<300:
                Lclut = 0 #dB
            elif freq>=300:
                Lclut = 0
            else :
                arcpy.AddError("Attenuation clutter 2: Incorrect frequency Specified.")
        else :
            if freq<300:
                Lclut = 0 #dB
            elif freq>=300:
                Lclut = 0
            else :
                arcpy.AddError("Attenuation clutter 2: Incorrect frequency Specified.")
    elif attenClut == "atten_CST_USER":
        if clutID == 0:
            if freq<300:
                Lclut = 2 #dB
            elif freq>=300:
                Lclut = 4
            else :
                arcpy.AddError("Attenuation clutter 2: Incorrect frequency Specified.")
        if clutID == 1:
            if freq<300:
                Lclut = 6 #dB
            elif freq>=300:
                Lclut = 8
            else :
                arcpy.AddError("Attenuation clutter 2: Incorrect frequency Specified.")
        if clutID == 2:
            if freq<300:
                Lclut = 8 #dB
            elif freq>=300:
                Lclut = 12
            else :
                arcpy.AddError("Attenuation clutter 2: Incorrect frequency Specified.")
        if clutID == 3:
            if freq<300:
                Lclut = 8 #dB
            elif freq>=300:
                Lclut = 12
            else :
                arcpy.AddError("Attenuation clutter 2: Incorrect frequency Specified.")
        if clutID == 4:
            if freq<300:
                Lclut = 8 #dB
            elif freq>=300:
                Lclut = 12
            else :
                arcpy.AddError("Attenuation clutter 2: Incorrect frequency Specified.")
        if clutID == 5:
            if freq<300:
                Lclut = 4 #dB
            elif freq>=300:
                Lclut = 8
            else :
                arcpy.AddError("Attenuation clutter 2: Incorrect frequency Specified.")
        if clutID == 6:
            if freq<300:
                Lclut = 0 #dB
            elif freq>=300:
                Lclut = 0
            else :
                arcpy.AddError("Attenuation clutter 2: Incorrect frequency Specified.")
        if clutID == 7:
            if freq<300:
                Lclut = 8 #dB
            elif freq>=300:
                Lclut = 12
            else :
                arcpy.AddError("Attenuation clutter 2: Incorrect frequency Specified.")
        if clutID == 8:
            if freq<300:
                Lclut = 4 #dB
            elif freq>=300:
                Lclut = 8
            else :
                arcpy.AddError("Attenuation clutter 2: Incorrect frequency Specified.")
        if clutID == 9:
            if freq<300:
                Lclut = 0 #dB
            elif freq>=300:
                Lclut = 0
            else :
                arcpy.AddError("Attenuation clutter 2: Incorrect frequency Specified.")
        else:
            if freq<300:
                Lclut = 0 #dB
            elif freq>=300:
                Lclut = 0
            else :
                arcpy.AddError("Attenuation clutter 2: Incorrect frequency Specified.")
    elif attenClut == "atten_LIN_USER":
        #Atten. defined by user SUM[Lcluti = Ci*Di)]
        Lclut = 0.00
    else :
        arcpy.AddError(500, "Incorrect Clutter attenuation Specified.")

    if zoneType == "sub_urban":
        A = 5.4+2*math.pow(math.log10(freq/28),2) #dB
    elif zoneType == "urban":
        A = 4.78*math.pow(math.log10(freq),2)-18.33*math.log10(freq)+40.94 #dB
    else :
        arcpy.AddError("Incorrect Zone type Specified.")
        
    Az = A+Lclut    
    return Az

# Function to calculate diffraction loss
def calDiffractionLoss(fc_ob, p_tx, p_rx, freq):
    Lp = 0.0
    
    #arcpy.AddMessage(" Rx:{0} N. Obstr = {1}".format(p_rx.ID,len(listOb)))
    '''field_E_ob = ["Shape","Hi_ob","Ri_ob","H_DEM","Tx_OID","Rx_OID"]
    whereclause = '"Rx_OID" = {0}'.format(p_rx.ID)
    with arcpy.da.SearchCursor(fc_ob, field_E_ob, whereclause) as cur_E_ob:
        for row_E_ob in cur_E_ob:
            x_ob, y_ob = row_E_ob[0]
            z_ob = row_E_ob[3]
            Hi_ob = row_E_ob[1]
            Ri_ob = row_E_ob[2]
            p_ob = arcpy.Point(x_ob, y_ob, z_ob, 0, 0)
            
            d1 = calDistance3D(p_tx, p_ob)
            d2 = calDistance3D(p_ob, p_rx)            
            #Vi = (Hi_ob/Ri_ob)*math.sqrt(2)
            A = 3*math.pow(10,8)/freq*math.pow(10,6)
            Vi = Hi_ob*math.sqrt(2*(d1+d2)/(A)*d1*d2)
            #arcpy.AddMessage(" Vi = {0}".format(Vi))
            if Vi < -0.7:
                Vi = 0
            Lp = Lp + (6.9 + 20*math.log10((Vi-0.1)+math.sqrt(1+math.pow(Vi-0.1, 2))))
    #'''    
    #arcpy.AddMessage(" Lp = {0}".format(Lp))
    return Lp

# Function to calculate multipath loss
def calMultiPathLoss(fc_ob, listObFres, p_tx, p_rx, dist, freq, folder_param):
    Lsp = 0.0

    fields_oku = ["Diffr_Meth","Opt_clut","Atten_clut","zoneType","MP_Meth"]
    fc_oku = "{0}/okumuraHata.shp".format(folder_param);
    with arcpy.da.SearchCursor(fc_oku, fields_oku) as cur_oku:
        for row_oku in cur_oku:
            mpMeth = row_oku[4]

    FZ = 0.0
    ratio_d_fre = 0.0
    Lgr = 20*math.log10(75000*dist/1000)-20*math.log10(math.pi*p_tx.Z*p_rx.Z*freq)

    if mpMeth == "standard":
        FZ = 0.6
        T_d_fre = 0.0
        for listLenByRx in listObFres:
            # calculte length of sheroid ob
            Rx_ID = listLenByRx[0]
            listLenOb = listLenByRx[1]
            if Rx_ID == p_rx.ID:
                for len_ob in listLenOb:
                    T_d_fre = T_d_fre+len_ob
        ratio_d_fre = T_d_fre/dist
        
        Lsp = FZ*ratio_d_fre*Lgr
    elif mpMeth == "coarse_integr":
        field_E_ob = ["Shape","Hi_ob","Ri_ob","H_DEM","Tx_OID","Rx_OID"]
        whereclause = '"Rx_OID" = {0}'.format(p_rx.ID)
        with arcpy.da.SearchCursor(fc_ob, field_E_ob, whereclause) as cur_E_ob:
            for row_E_ob in cur_E_ob:
                Hi_ob = row_E_ob[1]
                Ri_ob = row_E_ob[2]
                Vi = (Hi_ob/Ri_ob)*math.sqrt(2)
                Lp = Lp + (6.9 + 20*math.log10((Vi-0.1)+math.sqrt(1+math.pow(Vi-0.1, 2))))
                Lsp = max(Lsp, Lp)
    elif mpMeth == "area":
        Lp = 0.0
        H_ob = 0.0
        R_ob = 0.0
        for listIt in listOb:
            H_ob = H_ob+listIt[1]
            R_ob = R_ob+listIt[2]
            
        Vi = (H_ob/R_ob)*math.sqrt(2)
        Lp = 6.9 + 20*math.log10((Vi-0.1)+math.sqrt(1+math.pow(Vi-0.1, 2)))

        Lsp = Lp
    elif mpMeth == "free_ellipsoid":
        Lsp = 0.0
    else :
        arcpy.AddError("Incorrect MultiPath Method Specified.")       
    #arcpy.AddMessage(" Lsp = {0}".format(Lsp))
    return Lsp
