package cm.opsim.model;
/***********************************************************************
 * Module:  AntennaType.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class AntennaType
 ***********************************************************************/

import java.util.*;

/** @pdOid c1410507-27cb-4f8c-9638-033fed690902 */
public class AntennaType  extends AbstractModel{
   /** @pdOid 5d3caa54-6bd5-4eb7-b414-480087bd8775 */
   private int id;
   /** @pdOid 0634b27b-a9ae-4bae-89ec-853a9d211f20 */
   private String name;
   /** @pdOid 76720400-4100-499a-8cdb-82376afe5ad6 */
   private String description;
   
/**
 * 
 */
public AntennaType() {
	super();
	// TODO Auto-generated constructor stub
}
/**
 * @param id
 * @param name
 * @param description
 */
public AntennaType(int id, String name, String description) {
	super();
	this.id = id;
	this.name = name;
	this.description = description;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
   
   

}