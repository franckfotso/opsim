package cm.opsim.model;
/***********************************************************************
 * Module:  Template.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class Template
 ***********************************************************************/

import java.util.*;

/** @pdOid e03a51c2-1d55-485b-b58e-77850a4623ac */
public class Template extends AbstractModel{
   /** @pdOid a6ded76d-57c9-4ae3-a1ca-b4aac7fef239 */
   private int id = 0;
   /** @pdOid 6b402107-5eb8-4178-a354-1a7b937bbd9d */
   private String name = null;
   /** @pdOid 20285add-08fb-424f-958b-e2f68852947f */
   private String network = null;
   /** @pdOid 16e552e3-1818-4896-9e01-5a7cd1d31255 */
   private String generation = null;
   /** @pdOid 3e91e122-c183-4456-9daa-824192f31d8a */
   private Date createdDate;
   /** @pdOid 43a2c9db-304f-4729-a1d4-5a80d0beabf8 */
   private Date updatedDate;
   
   private String family =  null;
   
   
   
   /**
	 * @param id
	 * @param name
	 * @param network
	 * @param generation
	 * @param createdDate
	 * @param updatedDate
	 */
	public Template(int id, String name, String network, String generation,
			Date createdDate, Date updatedDate) {
		super();
		this.id = id;
		this.name = name;
		this.network = network;
		this.generation = generation;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}
	
	public Template(){
		super();
		this.name = "";
		this.network = "";
		this.generation = "";
		this.createdDate = new Date();
		this.updatedDate = new Date();
	}

	
	
	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

/** @pdOid e5224706-023f-4dec-b805-904e274e045b */
   public int getId() {
      return id;
   }
   
   /** @param newId
    * @pdOid 9d7ea20e-5e57-4f37-9b88-4850acb800fc */
   public void setId(int newId) {
      id = newId;
   }
   
   /** @pdOid 272eab86-1810-4861-9448-a9d3b7dfccc7 */
   public String getName() {
      return name;
   }
   
   /** @param newName
    * @pdOid ecaf8150-79b5-459b-960c-2f59aa13c01c */
   public void setName(String newName) {
      name = newName;
   }
   
   /** @pdOid 8b257cb5-0100-483e-9dda-b4793d6baa01 */
   public String getNetwork() {
      return network;
   }
   
   /** @param newNetwork
    * @pdOid 49f76070-d66d-4aba-b256-085c0a6a6307 */
   public void setNetwork(String newNetwork) {
      network = newNetwork;
   }
   
   /** @pdOid 8ddd51eb-c542-43ec-b964-f5c16ef02b78 */
   public String getGeneration() {
      return generation;
   }
   
   /** @param newGeneration
    * @pdOid d9debe11-3416-4643-bb6e-2df797f2b44c */
   public void setGeneration(String newGeneration) {
      generation = newGeneration;
   }
   
   /** @pdOid 4c547f49-c433-4143-82ba-c9f949c36bd7 */
   public Date getCreatedDate() {
      return createdDate;
   }
   
   /** @param newCreatedDate
    * @pdOid a3078a0a-9d0a-4402-91b5-dbb1711e9975 */
   public void setCreatedDate(Date newCreatedDate) {
      createdDate = newCreatedDate;
   }
   
   /** @pdOid 29951045-2da1-4c63-a2eb-0e791bb1f9ae */
   public Date getUpdatedDate() {
      return updatedDate;
   }
   
   /** @param newUpdatedDate
    * @pdOid b41c78ab-6ff4-4fec-ae7d-6c0cf4a73597 */
   public void setUpdatedDate(Date newUpdatedDate) {
      updatedDate = newUpdatedDate;
   }

}