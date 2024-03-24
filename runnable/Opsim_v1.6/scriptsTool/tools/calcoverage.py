'''******************************************************************
    OPSIM-Tools: Functions for Processing coverage	
******************************************************************''' 

import math
import arcpy
import threading
import multiprocessing
import pp

from calobstructions import execGetObstruction
from calobstructions import execGetFresnelObstr
from calmetric import calDistance3D
from multiThreadTask import calRSL

# Function to calculate coverage based on OkumuraHata
def calByOkumuraHata(fc_sites, fc_users, fc_dhm, fc_clut, fc_par_tx, fc_PR_Cov, I_Tx, folder_out, folder_param, fc_output):    
    # Getting Tx Parameters
    fc_listParam = fc_par_tx
    fields_par = ["OID@","POINTID","H_ant","Frequency","Power","SiteType","Gain"]
    whereclause = '"FID" = 0'
    with arcpy.da.SearchCursor(fc_listParam, fields_par, whereclause) as cur_par:
        for row_par in cur_par:
            H_ant = row_par[2]
            freq = row_par[3]
            Pt = row_par[4] # Transmitter power in dBmW
            siteType = row_par[5]
            Gt = row_par[6]
                            
    # Calculate obstructions on line of sight
    #fc_ob = execGetObstruction(fc_sites, fc_users, fc_dhm, fc_clut, fc_par_tx, fc_PR_Cov, I_Tx, folder_out)
    fc_ob = ""
    # Calculate obstructions on Fresnel speroid
    listObFres = list()
    #listObFres = execGetFresnelObstr(fc_sites, fc_users, fc_dhm, fc_clut, fc_par_tx, fc_PR_Cov, I_Tx, folder_out)
    
    fields_tx = ["OID@","SHAPE@XY","SHAPE@Z","POINTID","Surf_eff","H_DEM","H_eff_r"]
    whereclause = '"FID" = 0' 
    with arcpy.da.SearchCursor(fc_sites, fields_tx, whereclause) as cur_tx:
        for row_tx in cur_tx:
            x_tx, y_tx = row_tx[1]
            z_tx = row_tx[6]
            id_tx = row_tx[3]
            p_tx = arcpy.Point(x_tx, y_tx, z_tx, 0, id_tx)
    
    listRSL_Rx = list()
    # Method 0: Standard
    '''i_job = 0
    fields_rx = ["OID@","SHAPE@XY","SHAPE@Z","POINTID","Surf_eff","H_DEM","H_eff_r", "H_POINT"]
    with arcpy.da.SearchCursor(fc_users, fields_rx) as cur_rx:
        for row_rx in cur_rx:
            id_rx = row_rx[0]
            x_rx, y_rx = row_rx[1]
            z_rx = row_rx[6]   
            H_dtm = row_rx[7]
            POINTID = row_rx[3]
            listRSL_Rx.append(calRSL(i_job, x_tx, y_tx, z_tx, id_tx, row_rx, Pt, Gt, freq, fc_ob, listObFres, fc_clut))
            i_job+=1            
        del cur_rx, row_rx
        #'''        
    
    # Method 1: Multi-threading
    '''i_th = 0    
    listThread = list()
    fields_rx = ["OID@","SHAPE@XY","SHAPE@Z","POINTID","Surf_eff","H_DEM","H_eff_r", "H_POINT"]
    with arcpy.da.SearchCursor(fc_users, fields_rx) as cur_rx:
        for row_rx in cur_rx:
            t = RSLThread(i_th,  x_tx, y_tx, z_tx, id_tx, row_rx, Pt, Gt, freq,
                        "{0}/{1}".format(folder_out,fc_ob), listObFres, fc_clut)        
            listThread.append(t)
            t.start()        
            i_th+=1
        del row_rx, cur_rx
        
        
    for th in listThread:
        th.join()
        listRSL_Rx.append(th.listResult)
    #'''
    
    # Method 2: Multi-Processing (finally work)
    i_job = 0
    jobs = []
    arcpy.AddMessage("Utilising Python Multiprocessing library:")
    pool = multiprocessing.Pool()    
    fields_rx = ["OID@","SHAPE@XY","SHAPE@Z","POINTID","Surf_eff","H_DEM","H_eff_r", "H_POINT"]
    with arcpy.da.SearchCursor(fc_users, fields_rx) as cur_rx:
        for row_rx in cur_rx:
            arcpy.AddMessage("RSL: Passing %s to processing pool..."%i_job)
            # send jobs to be processed
            jobs.append(pool.apply_async(calRSL,(i_job, x_tx, y_tx, z_tx, id_tx, row_rx, Pt, Gt, freq,
                        "{0}/{1}".format(folder_out,fc_ob), listObFres, fc_clut, folder_param)))
            i_job+=1
            del row_rx
        del cur_rx

    for job in jobs: # collect results from the job server (waits until all the processing is complete)
        listRSL_Rx.append(job.get())
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
    fields_rx = ["OID@","SHAPE@XY","SHAPE@Z","POINTID","Surf_eff","H_DEM","H_eff_r", "H_POINT"]
    with arcpy.da.SearchCursor(fc_users, fields_rx) as cur_rx:
        for row_rx in cur_rx:
            arcpy.AddMessage("RSL: Passing %s to processing pool..."%i_job)
            # send jobs to be processed
            jobs.append(job_server.submit(calRSL, (i_job, x_tx, y_tx, z_tx, id_tx, row_rx, Pt, Gt, freq,
                        "{0}/{1}".format(folder_out,fc_ob), listObFres, fc_clut), (), ("arcpy",)))
            i_job+=1
        del row_rx, cur_rx

    for job in jobs: # collect results from the job server (waits until all the processing is complete)
        listRSL_Rx.append(job())
    del jobs
    #"""
    
    return listRSL_Rx
    
class RSLThread(threading.Thread):
    #listResult = list()
    def __init__(self, threadID, x_tx, y_tx, z_tx, id_tx, row_rx, Pt, Gt, freq, fc_ob, listObFres, fc_clut):
        threading.Thread.__init__(self)  
        self.listResult = list()
        self.threadID = threadID
        self.x_tx = x_tx
        self.y_tx = y_tx
        self.z_tx = z_tx
        self.id_tx = id_tx      
        self.row_rx = row_rx
        self.Pt = Pt
        self.Gt = Gt
        self.freq = freq
        self.fc_ob = fc_ob
        self.listObFres = listObFres
        self.fc_clut = fc_clut
        
    def run(self):
        arcpy.AddMessage("RSLThread-{0}: Starting".format(self.threadID))
        self.listResult = calRSL(self.threadID, self.x_tx, self.y_tx, self.z_tx, self.id_tx, self.row_rx, self.Pt, self.Gt,
                                    self.freq, self.fc_ob, self.listObFres, self.fc_clut)
        arcpy.AddMessage("RSLThread-{0}: Exit".format(self.threadID))
        #'''
        
# Function to calculate coverage based on Cost231
def calByCost231Hata(fc_sites, fc_users, fc_dhm, fc_clut, fc_par_tx, fc_PR_Cov, id_tx, folder_out,fc_output):
    listRSL_Rx = list()
    
    return listRSL_Rx
