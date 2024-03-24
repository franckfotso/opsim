package cm.opsim.model;
/***********************************************************************
 * Module:  Layer.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class Layer
 ***********************************************************************/

import java.util.*;

/** @pdOid fc60565f-f5d1-4e55-8924-664e81c90d56 */
public class Layer  extends AbstractModel{
   /** @pdOid 97683431-7d20-46ff-8fd5-61e2a5d70a8d */
   protected int id;
   /** @pdOid c75e54a4-0938-4851-aec4-58f235664469 */
   protected String name;
   /** @pdOid 9d2195b6-b744-4c53-87c8-e384563bf55a */
   protected String description;
   /** @pdOid b4464666-d4f7-44af-a596-b4a7029f6fd7 */
   protected String dataSource;
   /** @pdOid b674d5b7-412d-4d9f-8f10-7125e17faccb */
   protected String symbology;
   /** @pdOid 90063277-6579-4357-9111-edb661f25be7 */
   protected double baseHeight;
   /** @pdOid 7b83e798-b132-442f-845b-d1029e33c971 */
   protected Date createdDate;
   /** @pdOid 0d90a312-4922-4cdb-9b83-2ad362c6365c */
   protected Date updatedDate;
   
   /** @pdRoleInfo migr=no name=AttrTable assc=possede mult=1..1 */
   public AttrTable attrTable;
   
   private int map_id;
   private String type;
   
   
   /**
	 * @param id
	 * @param name
	 * @param description
	 * @param dataSource
	 * @param symbology
	 * @param baseHeight
	 * @param createdDate
	 * @param updatedDate
	 * @param attrTable
	 * @param map
	 */
	public Layer(int id, String name, String description, String dataSource,
			String symbology, double baseHeight, Date createdDate, Date updatedDate,
			AttrTable attrTable, int map_id, String type) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.dataSource = dataSource;
		this.symbology = symbology;
		this.baseHeight = baseHeight;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.attrTable = attrTable;
		this.map_id = map_id;
		this.type = type;
	}
	
	public Layer(){
		super();
		this.name = "";
		this.description = "";
		this.dataSource = "";
		this.symbology = "";
		this.baseHeight = 0.00;
		this.createdDate = new Date();
		this.updatedDate = new Date();
		this.attrTable = new AttrTable();
		this.map_id = 0;
		this.type = "";
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/** @pdOid ee5a744d-d9e6-4ef4-adab-d609a6bed609 */
   public int getId() {
      return id;
   }
   
   /** @param newId
    * @pdOid e9f2944b-23e7-41d8-a287-e23ab003fb1a */
   public void setId(int newId) {
      id = newId;
   }
   
   /** @pdOid 233de842-d682-425c-8e87-26bdf2e55895 */
   public String getName() {
      return name;
   }
   
   /** @param newName
    * @pdOid b78eda22-29c8-4390-81a6-a7c412578d33 */
   public void setName(String newName) {
      name = newName;
   }
   
   /** @pdOid 9eadbcab-0f51-41f0-893c-5b82c24b06d3 */
   public String getDescription() {
      return description;
   }
   
   /** @param newDescription
    * @pdOid 775a60d4-0256-4fbb-bdfa-672f07cfec8f */
   public void setDescription(String newDescription) {
      description = newDescription;
   }
   
   /** @pdOid ea6940e9-a975-44ac-a156-de704823f627 */
   public String getDataSource() {
      return dataSource;
   }
   
   /** @param newDataSource
    * @pdOid a8e50e63-d1f3-4b1b-b6ab-08e7bb3513cc */
   public void setDataSource(String newDataSource) {
      dataSource = newDataSource;
   }
   
   /** @pdOid 9c649a7c-31c0-4845-8672-9636e7f4fa7e */
   public String getSymbology() {
      return symbology;
   }
   
   /** @param newSymbology
    * @pdOid f59fa593-688b-4573-b7a8-ec01e71120ea */
   public void setSymbology(String newSymbology) {
      symbology = newSymbology;
   }
   
   /** @pdOid fc26ebed-49cb-4ab7-9e20-8bf4e9260636 */
   public double getBaseHeight() {
      return baseHeight;
   }
   
   /** @param newBaseHeight
    * @pdOid 90041c85-2518-41c6-b355-9875c5517457 */
   public void setBaseHeight(double newBaseHeight) {
      baseHeight = newBaseHeight;
   }
   
   /** @pdOid 71b68641-53af-417b-8104-86349013ad30 */
   public Date getCreatedDate() {
      return createdDate;
   }
   
   /** @param newCreatedDate
    * @pdOid 963ab111-5324-4989-bdc7-c3685b18016a */
   public void setCreatedDate(Date newCreatedDate) {
      createdDate = newCreatedDate;
   }
   
   /** @pdOid 22be1b5d-c69f-4c2f-b351-e14762d8b51c */
   public Date getUpdatedDate() {
      return updatedDate;
   }
   
   /** @param newUpdatedDate
    * @pdOid 20f4e704-30e0-4c32-b28f-ee97a4b6dedc */
   public void setUpdatedDate(Date newUpdatedDate) {
      updatedDate = newUpdatedDate;
   }

	/**
	 * @return the attrTable
	 */
	public AttrTable getAttrTable() {
		return attrTable;
	}

	/**
	 * @param attrTable the attrTable to set
	 */
	public void setAttrTable(AttrTable attrTable) {
		this.attrTable = attrTable;
	}
	
	public int getMap_id() {
		return map_id;
	}
	
	public void setMap_id(int map_id) {
		this.map_id = map_id;
	}

   

}