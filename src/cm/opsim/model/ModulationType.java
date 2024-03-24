package cm.opsim.model;
/***********************************************************************
 * Module:  ModulationType.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class ModulationType
 ***********************************************************************/

import java.util.*;

/** @pdOid 312c1179-08be-48f3-9d40-c7744e94fd84 */
public class ModulationType  extends AbstractModel{
   /** @pdOid fed5121c-d2d3-419e-a8d3-39725d2caa97 */
   private int id;
   /** @pdOid fd569296-f58d-4c35-952f-f75cda966cb1 */
   private String name;
   /** @pdOid fb2966d8-25c6-4ef4-97d9-bf3ae5bf2cda */
   private String description;
   
   /**
 * @param id
 * @param name
 * @param description
 */
public ModulationType(int id, String name, String description) {
	super();
	this.id = id;
	this.name = name;
	this.description = description;
}

/**
 * 
 */
public ModulationType() {
	super();
	// TODO Auto-generated constructor stub
}

/** @pdOid ac32bd41-dbba-40b8-8712-14df5ad05903 */
   public int getId() {
      return id;
   }
   
   /** @param newId
    * @pdOid d8f07bc9-2aa8-4ff4-9add-13f5d335cffa */
   public void setId(int newId) {
      id = newId;
   }
   
   /** @pdOid 45df1b45-2594-4872-aba2-0a3197b6dc7f */
   public String getName() {
      return name;
   }
   
   /** @param newName
    * @pdOid 55683e43-4563-4adf-b0f1-a921dd570d66 */
   public void setName(String newName) {
      name = newName;
   }
   
   /** @pdOid 5b8ba051-6378-4804-8f7a-b65bb0527099 */
   public String getDescription() {
      return description;
   }
   
   /** @param newDescription
    * @pdOid 3fc9327d-d7b5-40f0-af34-2cc466417e64 */
   public void setDescription(String newDescription) {
      description = newDescription;
   }

}