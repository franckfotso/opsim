package cm.opsim.model;
/***********************************************************************
 * Module:  Map.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class Map
 ***********************************************************************/

import java.util.*;

/** @pdOid 2da3b0fb-142a-4cac-93e1-53fa14b51228 */
public class Map  extends AbstractModel{
   /** @pdOid 35f536f9-f53e-4063-b286-10828e4fe13b */
   private int id;
   /** @pdOid 223eb7fc-46a3-4f3f-b830-bd6ebc5f437f */
   private String name;
   /** @pdOid 168adad6-5812-4c1c-a5c3-249605a3fb2f */
   private int numLayer;
   /** @pdOid 24b9ae7e-c8b2-44b2-99f9-d3efa610ffd6 */
   private String localization;
   /** @pdOid ad096781-2703-4f05-9021-bd17cbaba38e */
   private String mapset;
   /** @pdOid a4feed28-f15f-48ef-acf6-bbfae0967b0c */
   private Date createdDate;
   /** @pdOid ec8290e8-02b9-4b4e-a13e-f766590b725a */
   private Date updatedDate;   
   /** @pdRoleInfo migr=no name=Layer assc=estCompose coll=java.util.Collection impl=java.util.HashSet mult=1..* */
   public java.util.Hashtable<String,Layer> layer;
   
   public int proj_id;
   
   /**
	 * @param id
	 * @param name
	 * @param numLayer
	 * @param localization
	 * @param mapset
	 * @param createdDate
	 * @param updatedDate
	 * @param layer
	 * @param proj
	 */
	public Map(int id, String name, int numLayer, String localization,
			String mapset, Date createdDate, Date updatedDate,
			Hashtable<String,Layer> layer, int proj_id) {
		super();
		this.id = id;
		this.name = name;
		this.numLayer = numLayer;
		this.localization = localization;
		this.mapset = mapset;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.layer = layer;
		this.proj_id = proj_id;
	}
	
	public Map(){
		super();
		this.name = "";
		this.numLayer = 0;
		this.localization = "";
		this.mapset = "";
		this.createdDate = new Date();
		this.updatedDate = new Date();
		this.layer = new Hashtable<String,Layer>();
		this.proj_id = 0;
	}

/** @pdOid e121a3d1-342d-407c-8270-e5f5d588e969 */
   public int getId() {
      return id;
   }
   
   /** @param newId
    * @pdOid b33965bb-b0b0-4c56-8664-e5a33755d099 */
   public void setId(int newId) {
      id = newId;
   }
   
   /** @pdOid d239693e-de65-436d-a0af-c0d904a9ade7 */
   public String getName() {
      return name;
   }
   
   /** @param newName
    * @pdOid e93d3ff2-1d61-4e0d-ab17-7b25b5170913 */
   public void setName(String newName) {
      name = newName;
   }
   
   /** @pdOid f75f3519-c6ae-4488-8464-7788784cd6dd */
   public int getNumLayer() {
      return numLayer;
   }
   
   /** @param newNumLayer
    * @pdOid b90a6bac-dc2e-4547-a4c4-aa174d964388 */
   public void setNumLayer(int newNumLayer) {
      numLayer = newNumLayer;
   }
   
   /** @pdOid daa2ea47-0421-4ae4-b920-ab3b633f7973 */
   public String getLocalization() {
      return localization;
   }
   
   /** @param newLocalization
    * @pdOid 6d26249f-8da7-4c5f-b3a7-96423c042293 */
   public void setLocalization(String newLocalization) {
      localization = newLocalization;
   }
   
   /** @pdOid 97735763-c3c2-4b3e-a1f1-69fb8de030ab */
   public String getMapset() {
      return mapset;
   }
   
   /** @param newMapset
    * @pdOid e002ff98-9389-4055-90c0-688fe71a5fa7 */
   public void setMapset(String newMapset) {
      mapset = newMapset;
   }
   
   /** @pdOid 62f2e8b5-eff9-49ac-b502-6a08751133fc */
   public Date getCreatedDate() {
      return createdDate;
   }
   
   /** @param newCreatedDate
    * @pdOid 64bcd1a1-b199-4589-9e23-9eb343f031c0 */
   public void setCreatedDate(Date newCreatedDate) {
      createdDate = newCreatedDate;
   }
   
   /** @pdOid 74712293-d270-40bb-8b15-ce69f9395096 */
   public Date getUpdatedDate() {
      return updatedDate;
   }
   
   /** @param newUpdatedDate
    * @pdOid 2cfdf5f1-296f-4e33-8ccc-bd0c084ebabb */
   public void setUpdatedDate(Date newUpdatedDate) {
      updatedDate = newUpdatedDate;
   }   

	public java.util.Hashtable<String, Layer> getLayer() {
		return layer;
	}
	
	public void setLayer(java.util.Hashtable<String, Layer> layer) {
		this.layer = layer;
	}

	public int getProj_id() {
		return proj_id;
	}

	public void setProj_id(int proj_id) {
		this.proj_id = proj_id;
	}
 

}