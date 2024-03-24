'''******************************************************************
    OPSIM-Tools: Functions for Processing metric	
******************************************************************''' 

import math
import arcpy

# Function to calculate 2D metric of 2 points
def calDistance2D(A, B):
   return math.sqrt(
       math.pow(A.X-B.X, 2)+
       math.pow(A.Y-B.Y, 2));

# Function to calculate 3D metric of 2 points
def calDistance3D(A, B):
   return math.sqrt(
       math.pow(A.X-B.X, 2)+
       math.pow(A.Y-B.Y, 2)+
       math.pow(A.Z-B.Z, 2));
