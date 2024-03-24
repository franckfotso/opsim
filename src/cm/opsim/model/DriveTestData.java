package cm.opsim.model;
/***********************************************************************
 * Module:  DriveTestData.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class DriveTestData
 ***********************************************************************/

import java.util.*;

/** @pdOid 4f5fda18-1eef-48c5-9142-9ba756d5e4c3 */
public class DriveTestData  extends AbstractModel{
   /** @pdOid 8c5142de-4e88-4e1d-8055-00f4af4460bd */
   private int id;
   /** @pdOid 3de32bfb-ddfe-4e54-b0fa-98f104797cda */
   private double power;
   /** @pdOid fea870a2-4a98-4900-935f-f886f23f8e92 */
   private double field;
   /** @pdOid bb4bbfba-d5c6-4b53-8cf3-e27ec0be4403 */
   private int bound;
   
   private Configuration conf;
   
      
   /**
 * @param id
 * @param power
 * @param field
 * @param bound
 * @param conf
 */
public DriveTestData(int id, double power, double field, int bound,
		Configuration conf) {
	super();
	this.id = id;
	this.power = power;
	this.field = field;
	this.bound = bound;
	this.conf = conf;
}

public DriveTestData(){
	super();
}

/** @pdOid b3c05e1b-a2ce-4f44-bb7c-1701b8858044 */
   public int getId() {
      return id;
   }
   
   /** @param newId
    * @pdOid 73380734-f958-414c-8fb9-466782fa55f4 */
   public void setId(int newId) {
      id = newId;
   }
   
   /** @pdOid 70d6ba8c-a73f-4ef1-a110-2c6b99f47a29 */
   public double getPower() {
      return power;
   }
   
   /** @param newPower
    * @pdOid 53a56ba5-7d9a-4c7f-96b2-fe82a2e0e462 */
   public void setPower(double newPower) {
      power = newPower;
   }
   
   /** @pdOid a778fd18-c6fa-4c2c-b880-ab162ea6d6d9 */
   public double getField() {
      return field;
   }
   
   /** @param newField
    * @pdOid ae55af13-87d1-4509-88d9-b7bb077d5b40 */
   public void setField(double newField) {
      field = newField;
   }
   
   /** @pdOid 5e7182a8-65ea-4208-a0d2-1b2fa4eb68b0 */
   public int getBound() {
      return bound;
   }
   
   /** @param newBound
    * @pdOid bff4008f-3fa8-45e5-953b-b33acf9c4569 */
   public void setBound(int newBound) {
      bound = newBound;
   }

/**
 * @return the conf
 */
public Configuration getConf() {
	return conf;
}

/**
 * @param conf the conf to set
 */
public void setConf(Configuration conf) {
	this.conf = conf;
}
   
   

}