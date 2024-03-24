package cm.opsim.model;
/***********************************************************************
 * Module:  Configuration.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class Configuration
 ***********************************************************************/

import java.util.*;

/** @pdOid 9339b3e2-9c68-4a7f-8e64-6962c1ebda17 */
public class Configuration  extends AbstractModel{
   /** @pdOid 25b3288c-c2c6-4b6f-9b0d-767b32008d39 */
   protected int id = 0;
   /** @pdOid a9afb3a5-0695-473c-91cb-b349e1a99d47 */
   protected Date createdDate;
   /** @pdOid c4284e96-5e2c-4825-8def-6131696f70be */
   protected Date updatedDate;
   
   /** @pdRoleInfo migr=no name=DriveTestData assc=renvoitA coll=java.util.Collection impl=java.util.HashSet mult=1..* */
   private java.util.Collection<DriveTestData> driveTestData;
   
   private BtsConfig btsConf;
   private EnbConfig enbConf;
   private BackboneConfig backboneConf;
   
	
	/**
 * @param id
 * @param createdDate
 * @param updatedDate
 * @param driveTestData
 * @param btsConf
 * @param enbConf
 * @param backboneConf
 */
public Configuration(int id, Date createdDate, Date updatedDate,
		Collection<DriveTestData> driveTestData, BtsConfig btsConf,
		EnbConfig enbConf, BackboneConfig backboneConf) {
	super();
	this.id = id;
	this.createdDate = createdDate;
	this.updatedDate = updatedDate;
	this.driveTestData = driveTestData;
	this.btsConf = btsConf;
	this.enbConf = enbConf;
	this.backboneConf = backboneConf;
}

	public Configuration(){
		super();
		this.createdDate = new Date();
		this.updatedDate = new Date();
		this.driveTestData = new ArrayList<DriveTestData>();
		this.btsConf = new BtsConfig();
		this.enbConf = new EnbConfig();
		this.backboneConf = new BackboneConfig();
	}
	
	public BtsConfig getBtsConf() {
		return btsConf;
	}

	public void setBtsConf(BtsConfig btsConf) {
		this.btsConf = btsConf;
	}

	public EnbConfig getEnbConf() {
		return enbConf;
	}

	public void setEnbConf(EnbConfig enbConf) {
		this.enbConf = enbConf;
	}

	public BackboneConfig getBackboneConf() {
		return backboneConf;
	}

	public void setBackboneConf(BackboneConfig backboneConf) {
		this.backboneConf = backboneConf;
	}

/** @pdOid 97ab3618-56de-4d6b-8516-750378e97ceb */
   public int getId() {
      return id;
   }
   
   /** @param newId
    * @pdOid 37d4c8c6-d46b-4a17-9740-2484412825ec */
   public void setId(int id) {
      id = id;
      this.btsConf.setId(id);
      this.enbConf.setId(id);
      this.backboneConf.setId(id);
   }
   
   /** @pdOid 5120af24-47d6-43d7-8887-4159d86da3c9 */
   public Date getCreatedDate() {
      return createdDate;
   }
   
   /** @param newCreatedDate
    * @pdOid 21165c08-8a1f-4c29-b5d3-20ff69c459f9 */
   public void setCreatedDate(Date newCreatedDate) {
      createdDate = newCreatedDate;
   }
   
   /** @pdOid 708ca13b-c9c2-4aeb-b2e6-a972c39c6a8a */
   public Date getUpdatedDate() {
      return updatedDate;
   }
   
   /** @param newUpdatedDate
    * @pdOid b6eda1dd-c7d8-47f8-9ba6-4fb4542b8fc0 */
   public void setUpdatedDate(Date newUpdatedDate) {
      updatedDate = newUpdatedDate;
   }
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<DriveTestData> getDriveTestData() {
      if (driveTestData == null)
         driveTestData = new java.util.HashSet<DriveTestData>();
      return driveTestData;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDriveTestData() {
      if (driveTestData == null)
         driveTestData = new java.util.HashSet<DriveTestData>();
      return driveTestData.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDriveTestData */
   public void setDriveTestData(java.util.Collection<DriveTestData> newDriveTestData) {
      removeAllDriveTestData();
      for (java.util.Iterator iter = newDriveTestData.iterator(); iter.hasNext();)
         addDriveTestData((DriveTestData)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDriveTestData */
   public void addDriveTestData(DriveTestData newDriveTestData) {
      if (newDriveTestData == null)
         return;
      if (this.driveTestData == null)
         this.driveTestData = new java.util.HashSet<DriveTestData>();
      if (!this.driveTestData.contains(newDriveTestData))
         this.driveTestData.add(newDriveTestData);
   }
   
   /** @pdGenerated default remove
     * @param oldDriveTestData */
   public void removeDriveTestData(DriveTestData oldDriveTestData) {
      if (oldDriveTestData == null)
         return;
      if (this.driveTestData != null)
         if (this.driveTestData.contains(oldDriveTestData))
            this.driveTestData.remove(oldDriveTestData);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllDriveTestData() {
      if (driveTestData != null)
         driveTestData.clear();
   }

}