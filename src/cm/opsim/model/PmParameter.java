package cm.opsim.model;
/***********************************************************************
 * Module:  PmParameter.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class PmParameter
 ***********************************************************************/

import java.util.Date;

/** Model Propagation Parmeter
 * 
 * @pdOid 3ef3b612-7c12-4872-a2fb-59c2d375ce57 */
public class PmParameter extends AbstractModel{
   /** @pdOid 5c9483c0-2158-4b49-8fd0-3f2fc7134f22 */
   protected int id;
   /** @pdOid da6307f6-72f0-4a6f-aadd-1aaa2e0aa772 */
   protected String name;
   /** @pdOid 88aaa8dc-4fe3-42a8-81a3-9dfda062c411 */
   protected Date createdDate;
   /** @pdOid 8a12c2de-cfde-431e-be96-96337668ed0f */
   protected Date updatedDate;
   
   private PropaModel pm;
//   private OkumuraHata okumuraHata;
//   private Cost231Hata cost231Hata;
//   private Cost231WI cost231WI;
//   private KFactors kfactors;
   
   /**
 * @param id
 * @param name
 * @param createdDate
 * @param updatedDate
 * @param pm
 */
public PmParameter(int id, String name, Date createdDate, Date updatedDate,PropaModel pm) {
	super();
	this.id = id;
	this.name = name;
	this.createdDate = createdDate;
	this.updatedDate = updatedDate;
	this.pm = pm;
}

	/**
	 * 
	 */
	public PmParameter() {
		super();
		this.name = "okumuraHata";
		this.createdDate = new Date();
		this.updatedDate = new Date();
		this.pm = new OkumuraHata();
	}
	
	public PropaModel getPm() {
		return pm;
	}
	
	public void setPm(PropaModel pm) {
		this.pm = pm;
	}

	/** @pdOid 9772380e-b228-4f0d-b6cc-1c98f63737f8 */
   public int getId() {
      return id;
   }
   
   /** @param newId
    * @pdOid 2cd23148-2fbc-4e42-ba3c-aa258f34678d */
   public void setId(int newId) {
      id = newId;
   }
   
   /** @pdOid 9bfa9ac7-8f9b-4e17-8e2d-2f2026822356 */
   public String getName() {
      return name;
   }
   
   /** @param newName
    * @pdOid f919ff7e-0478-4a63-b8fa-b129cfab081b */
   public void setName(String newName) {
      name = newName;
   }
   
   /** @pdOid da7c5fc3-bbc4-4acf-8a63-a56233027273 */
   public Date getCreatedDate() {
      return createdDate;
   }
   
   /** @param newCreatedDate
    * @pdOid e94eb990-7888-49ec-b5b0-a127ce3e4514 */
   public void setCreatedDate(Date newCreatedDate) {
      createdDate = newCreatedDate;
   }
   
   /** @pdOid 8b5677a0-b688-4bae-8f77-5d0e083c16c5 */
   public Date getUpdatedDate() {
      return updatedDate;
   }
   
   /** @param newUpdatedDate
    * @pdOid 4ca37911-ea77-4596-8c94-730276c6bd9d */
   public void setUpdatedDate(Date newUpdatedDate) {
      updatedDate = newUpdatedDate;
   }

}