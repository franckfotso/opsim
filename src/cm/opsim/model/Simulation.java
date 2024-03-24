package cm.opsim.model;
/***********************************************************************
 * Module:  Simulation.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class Simulation
 ***********************************************************************/

import java.util.*;

/** @pdOid 4dee4d2b-9eb9-41ca-ae77-6189ed75c2de */
public class Simulation extends AbstractModel{
   /** @pdOid f8497193-33b3-497f-9e24-aad185407afe */
   protected int id;
   /** @pdOid 6ed88c44-2734-4785-a561-d96906b73d3e */
   protected Date startDate;
   /** @pdOid f95dc88f-e374-4910-b2b7-950fbee525e6 */
   protected Date endDate;
   /** @pdOid 7379739f-9a26-47e0-9e9e-5744494fde0d */
   protected boolean isDynamic;
   /** @pdOid 65156129-cefd-49ec-90ea-e18f73c3b840 */
   protected double errorRate;
   /** @pdOid 0ce34ca2-afd6-4dbc-a294-1f0a5c6f3ce8 */
   protected Date createdDate;
   /** @pdOid 8650f025-85cd-426a-a24e-cbb9d521fb48 */
   protected Date updatedDate;
   
   private  int proj_id;
   
	
	/**
 * @param id
 * @param startDate
 * @param endDate
 * @param isDynamic
 * @param errorRate
 * @param createdDate
 * @param updatedDate
 * @param proj
 */
public Simulation(int id, Date startDate, Date endDate, boolean isDynamic,
		double errorRate, Date createdDate, Date updatedDate, int proj_id) {
	super();
	this.id = id;
	this.startDate = startDate;
	this.endDate = endDate;
	this.isDynamic = isDynamic;
	this.errorRate = errorRate;
	this.createdDate = createdDate;
	this.updatedDate = updatedDate;
	this.proj_id = proj_id;
}

	public Simulation(){
		super();
	}

/** @pdOid ac25517a-faa8-4fcb-8f6e-cbe0ae407a2c */
   public int getId() {
      return id;
   }
   
   /** @param newId
    * @pdOid 7b62b66f-e45d-46ef-a779-fc54cf7dc21e */
   public void setId(int newId) {
      id = newId;
   }
   
   /** @pdOid 39fe2926-a2d7-4266-91f7-d281831c1edb */
   public Date getStartDate() {
      return startDate;
   }
   
   /** @param newStartDate
    * @pdOid 9da8a434-c528-4ea7-baa6-86d5d66edc21 */
   public void setStartDate(Date newStartDate) {
      startDate = newStartDate;
   }
   
   /** @pdOid 4aeee974-cdfb-4416-a775-ade4f5799aa1 */
   public Date getEndDate() {
      return endDate;
   }
   
   /** @param newEndDate
    * @pdOid 747818ee-587a-4430-85ee-12235b8ddb88 */
   public void setEndDate(Date newEndDate) {
      endDate = newEndDate;
   }
   
   /** @pdOid 5ab8fa8b-dfbb-45f3-be31-a55b4288f453 */
   public boolean getIsDynamic() {
      return isDynamic;
   }
   
   /** @param newIsDynamic
    * @pdOid 5c7a2099-a0c8-4cc3-8ce9-aa7dbc25b642 */
   public void setIsDynamic(boolean newIsDynamic) {
      isDynamic = newIsDynamic;
   }
   
   /** @pdOid 41a85ffe-f63a-4be2-8952-376d043802d1 */
   public double getErrorRate() {
      return errorRate;
   }
   
   /** @param newErrorRate
    * @pdOid 82f1c224-f70a-43c5-b837-01736780a1b0 */
   public void setErrorRate(double newErrorRate) {
      errorRate = newErrorRate;
   }
   
   /** @pdOid fdd93193-1dc5-4fca-8ef0-df3077756920 */
   public Date getCreatedDate() {
      return createdDate;
   }
   
   /** @param newCreatedDate
    * @pdOid e4f6a2ee-08d5-411d-8e07-32d5222ebebf */
   public void setCreatedDate(Date newCreatedDate) {
      createdDate = newCreatedDate;
   }
   
   /** @pdOid 1c345ca5-fdc1-4758-b2d7-3da2d1ea34e2 */
   public Date getUpdatedDate() {
      return updatedDate;
   }
   
   /** @param newUpdatedDate
    * @pdOid 8557b38d-7a60-4284-a450-0c6abeeaf544 */
   public void setUpdatedDate(Date newUpdatedDate) {
      updatedDate = newUpdatedDate;
   }

public int getProj_id() {
	return proj_id;
}

public void setProj_id(int proj_id) {
	this.proj_id = proj_id;
}


   

}