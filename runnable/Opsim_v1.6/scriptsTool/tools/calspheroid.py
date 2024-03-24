'''******************************************************************
    OPSIM-Tools: Functions for Processing speroid	
******************************************************************''' 

import math
import arcpy
from calmetric import calDistance3D

# Function to calculate speroid radius of ob at one point
def calSperoidRadiusAtPt(p_tx, p_rx, p_ob, Dist, r):
    a = Dist/2
    b = Dist/2
    c = r
    Ri_ob = 0.0

    p_mid = arcpy.Point(
        (p_tx.X+p_rx.X)/2,
        (p_tx.Y+p_rx.Y)/2,
        (p_tx.Z+p_rx.Z)/2,
        0,
        0)
    A = math.pow(p_mid.X-p_mid.X, 2)/math.pow(a, 2)
    B = math.pow(p_mid.Y-p_mid.Y, 2)/math.pow(b, 2)	
    z_mid = p_mid.Z+math.sqrt(math.pow(c, 2)*(1-A-B))

    p_mid_E = arcpy.Point(
        p_mid.X,
        p_mid.Y,
        z_mid,
        0,
        0)
    G1 = calDistance3D(p_mid, p_mid_E)

    alpha = math.acos((p_mid.Z-p_rx.Z)/(Dist/2))
    beta = (math.pi/2)-alpha
    g = r*math.cos(beta)
    q = (g)/G1

    A = math.pow(p_ob.X-p_mid.X, 2)/math.pow(a, 2)
    B = math.pow(p_ob.Y-p_mid.Y, 2)/math.pow(b, 2)
    D = A-B
    if D > 1.0:
        D = 1.0
    z_ob = p_mid.Z+math.sqrt(math.pow(c, 2)*(1-D))

    p_ob_E = arcpy.Point(
        p_ob.X,
        p_ob.Y,
        z_ob,
        0,
        0)
    G2 = calDistance3D(p_ob, p_ob_E)
    Ri_ob = (q*G2)/math.cos(beta)
    
    return Ri_ob/2;

# Function to calculate Ellipsoid mathing points
def calEllipsoidPoint(listEllipPnt, p_onLine, p_tx, p_rx, p_mid, dist, r):
    n_z = int(p_tx.Z-p_rx.Z+r);
    a = dist/2;
    b = dist/2;
    #b = (Dist/2)*0.6;
    c = r;

    listItem = list()
    k = 0.0
    while k < n_z:
        p_E = arcpy.Point(p_onLine.X,
                          p_onLine.Y,
                          (p_onLine.Z-r)+k,
                          0,0)
        
        A = math.pow(p_E.X-p_mid.X, 2)/math.pow(a, 2)
        B = math.pow(p_E.Y-p_mid.Y, 2)/math.pow(b, 2)
        C = math.pow(p_E.Z-p_mid.Z, 2)/math.pow(c, 2)
        T = A+B+C
        arcpy.AddMessage("k({4}) [A,B,C,T] = [{0},{1},{2},{3}]".format(A, B, C, T,k))
        if T >= 0.85 and T <=0.99999:
            if p_E.Z <= p_onLine.Z:
                #arcpy.AddMessage("p_E[x,y,z] = [{0},{1},{2}]".format(p_E.X, p_E.Y, p_E.Z))
                listItem.append(p_E)
                listItem.append(p_E.Z)
                listItem.append(p_tx.ID)
                listItem.append(p_rx.ID) 
                listEllipPnt.append(listItem)
        k=k+0.25        
    return listEllipPnt
