package cm.opsim.model;
/***********************************************************************
 * Module:  OtherParameter.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class OtherParameter
 ***********************************************************************/

import java.util.*;

/** @pdOid d55da925-146f-4e63-aa7a-844c69068cf1 */
public class OtherParameter extends AbstractModel{
	private int id = 0;
   /** @pdOid 09f72087-ae5d-4c29-9db1-7186a44f00fe */
   private String modulation;
   /** @pdOid 9e6f5f07-95c1-4480-b11e-8250996e3ec8 */
   private String mimoType;
   
/**
 * @param id
 * @param modulation
 * @param mimoType
 */
public OtherParameter(int id, String modulation, String mimoType) {
	super();
	this.id = id;
	this.modulation = modulation;
	this.mimoType = mimoType;
}
/**
 * 
 */
public OtherParameter() {
	super();
	// TODO Auto-generated constructor stub
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getModulation() {
	return modulation;
}
public void setModulation(String modulation) {
	this.modulation = modulation;
}
public String getMimoType() {
	return mimoType;
}
public void setMimoType(String mimoType) {
	this.mimoType = mimoType;
}
   
   
}