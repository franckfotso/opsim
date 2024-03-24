package cm.opsim.model;
/***********************************************************************
 * Module:  McsTechnique.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class McsTechnique
 ***********************************************************************/

import java.util.*;

/** @pdOid e15dd678-8220-4dd7-a288-b915e3a55810 */
public class McsTechnique extends AbstractModel{
   /** @pdOid 87b98815-7f86-4d00-b666-f953bc1b420c */
   private int id;
   /** @pdOid 21b1ef05-0685-45ab-a07c-a1d9aea87e30 */
   private int mcsIndex;
   /** @pdOid cf7ab256-649e-4dfd-8f33-fcfceeca8d8d */
   private double rateCodingUL;
   /** @pdOid 241c3d56-c681-4f3a-af25-32223dfdfc65 */
   private double rateCodingDL;
   /** @pdOid ea946d34-bce3-431b-bce6-425b80cd3b24 */
   private double snirUl;
   /** @pdOid 21c3fdad-00b5-4025-919b-63f6c994b76d */
   private double snirDl;
   
   /** @pdRoleInfo migr=no name=ModulationType assc=comprend mult=1..1 */
   public ModulationType modulationType;

   
   
/**
 * @param id
 * @param mcsIndex
 * @param name
 * @param rateCodingUL
 * @param rateCodingDL
 * @param snirUl
 * @param snirDl
 * @param modulationType
 */
public McsTechnique(int id, int mcsIndex, double rateCodingUL,
		double rateCodingDL, double snirUl, double snirDl,
		ModulationType modulationType) {
	super();
	this.id = id;
	this.mcsIndex = mcsIndex;
	this.rateCodingUL = rateCodingUL;
	this.rateCodingDL = rateCodingDL;
	this.snirUl = snirUl;
	this.snirDl = snirDl;
	this.modulationType = modulationType;
}

public McsTechnique(){
	super();
}

/**
 * @return the id
 */
public int getId() {
	return id;
}

/**
 * @param id the id to set
 */
public void setId(int id) {
	this.id = id;
}

/**
 * @return the mcsIndex
 */
public int getMcsIndex() {
	return mcsIndex;
}

/**
 * @param mcsIndex the mcsIndex to set
 */
public void setMcsIndex(int mcsIndex) {
	this.mcsIndex = mcsIndex;
}

/**
 * @return the rateCodingUL
 */
public double getRateCodingUL() {
	return rateCodingUL;
}

/**
 * @param rateCodingUL the rateCodingUL to set
 */
public void setRateCodingUL(double rateCodingUL) {
	this.rateCodingUL = rateCodingUL;
}

/**
 * @return the rateCodingDL
 */
public double getRateCodingDL() {
	return rateCodingDL;
}

/**
 * @param rateCodingDL the rateCodingDL to set
 */
public void setRateCodingDL(double rateCodingDL) {
	this.rateCodingDL = rateCodingDL;
}

/**
 * @return the snirUl
 */
public double getSnirUl() {
	return snirUl;
}

/**
 * @param snirUl the snirUl to set
 */
public void setSnirUl(double snirUl) {
	this.snirUl = snirUl;
}

/**
 * @return the snirDl
 */
public double getSnirDl() {
	return snirDl;
}

/**
 * @param snirDl the snirDl to set
 */
public void setSnirDl(double snirDl) {
	this.snirDl = snirDl;
}

/**
 * @return the modulationType
 */
public ModulationType getModulationType() {
	return modulationType;
}

/**
 * @param modulationType the modulationType to set
 */
public void setModulationType(ModulationType modulationType) {
	this.modulationType = modulationType;
}
   
   

}