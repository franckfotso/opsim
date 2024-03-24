package cm.opsim.model;
/***********************************************************************
 * Module:  RDimensionning.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class RDimensionning
 ***********************************************************************/

import java.util.*;

/** @pdOid 4b334efd-a2c0-45cd-b3b8-f74b42c833ab */
public class RDimensionning extends AbstractModel{
	private int id = 0;
   /** @pdOid 2ae9667d-c3d8-4a8b-b593-fdd2978abf62 */
   private String serviceClass;
   /** @pdOid e6d5baa7-ce2c-49a7-94d3-55988a0b3a41 */
   private String traficModel;
   /** @pdOid 18ab5a48-92a1-4dd1-9ce3-5637e8415fe7 */
   private int numCustomerByAccess;
   /** @pdOid b43388de-9eed-4a62-9ff5-013167a940c1 */
   private int numMGW;
   /** @pdOid 76bfcc8d-3b83-4754-bca9-edee4564e953 */
   private double mgcfCapacity;
   /** @pdOid 785843a2-47f1-4b52-a9e4-58776d32c998 */
   private double cscfCapacity;
   
   /**
 * 
 */
public RDimensionning() {
	super();
	// TODO Auto-generated constructor stub
}

/**
 * @param id
 * @param serviceClass
 * @param traficModel
 * @param numCustomerByAccess
 * @param numMGW
 * @param mgcfCapacity
 * @param cscfCapacity
 */
public RDimensionning(int id, String serviceClass, String traficModel,
		int numCustomerByAccess, int numMGW, double mgcfCapacity,
		double cscfCapacity) {
	super();
	this.id = id;
	this.serviceClass = serviceClass;
	this.traficModel = traficModel;
	this.numCustomerByAccess = numCustomerByAccess;
	this.numMGW = numMGW;
	this.mgcfCapacity = mgcfCapacity;
	this.cscfCapacity = cscfCapacity;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

/** @pdOid 5170c859-22e2-4626-8733-0e7453318091 */
   public String getServiceClass() {
      return serviceClass;
   }
   
   /** @param newServiceClass
    * @pdOid af290fe6-27d7-4d03-a890-17640d774f68 */
   public void setServiceClass(String newServiceClass) {
      serviceClass = newServiceClass;
   }
   
   /** @pdOid dc818d00-2c9d-462b-9a40-479bebff8921 */
   public String getTraficModel() {
      return traficModel;
   }
   
   /** @param newTraficModel
    * @pdOid c3ce8bde-2655-4387-85f2-001aedd9a9f7 */
   public void setTraficModel(String newTraficModel) {
      traficModel = newTraficModel;
   }
   
   /** @pdOid d48da2a6-92d5-45c8-9075-63de51639c38 */
   public int getNumCustomerByAccess() {
      return numCustomerByAccess;
   }
   
   /** @param newNumCustomerByAccess
    * @pdOid 8bf25b3c-055d-4818-9bff-93373e9f9d46 */
   public void setNumCustomerByAccess(int newNumCustomerByAccess) {
      numCustomerByAccess = newNumCustomerByAccess;
   }
   
   /** @pdOid 1b54f01b-325d-4840-ba08-a03e8809109e */
   public int getNumMGW() {
      return numMGW;
   }
   
   /** @param newNumMGW
    * @pdOid c9ebee36-8f95-4692-9797-dd1f9552dfb4 */
   public void setNumMGW(int newNumMGW) {
      numMGW = newNumMGW;
   }
   
   /** @pdOid 9444ab17-294f-4edd-91ff-4e5b744fd63f */
   public double getMgcfCapacity() {
      return mgcfCapacity;
   }
   
   /** @param newMgcfCapacity
    * @pdOid 73fd84c6-825c-40bf-8c20-04ad7aa25b79 */
   public void setMgcfCapacity(double newMgcfCapacity) {
      mgcfCapacity = newMgcfCapacity;
   }
   
   /** @pdOid 842e66f4-4d2d-4ec4-afae-479bebdc21f8 */
   public double getCscfCapacity() {
      return cscfCapacity;
   }
   
   /** @param newCscfCapacity
    * @pdOid f89187ec-e20d-42d9-b322-fe41294720dd */
   public void setCscfCapacity(double newCscfCapacity) {
      cscfCapacity = newCscfCapacity;
   }

}