package cm.opsim.model;
/***********************************************************************
 * Module:  Okumura.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class Okumura
 ***********************************************************************/

import java.util.*;

/** @pdOid dee79664-7cbe-4a67-9e9c-989ab11434ca */
public class OkumuraHata extends PropaModel{
   /** @pdOid 9c6888b1-8370-4ef1-9024-f4a956671421 */
   private double frequency;
   /** @pdOid b7d0175f-452c-42f5-b421-04bf6baafb4a */
   private double heightUE;
   /** @pdOid 2dcf562f-065c-4cf4-8e08-eaab26df04b0 */
   private double heightENB;
   /** @pdOid ccf49be7-7dd4-4cca-b9a6-bf0129c13b70 */
   private String zoneType;
   /** @pdOid 0e671443-1d2c-4c56-941c-8fab18c4cd87 */
   private double diffAtten;
   /** @pdOid 4aa29d3c-26c2-48f5-bc40-98d4b49e6515 */
   private double spAtten;
   /** @pdOid c9a15aa9-e1bb-41ed-8341-625efaeab858 */
   private double fieldOccupAtten;
   /** @pdOid 9b0dbce1-1ad6-4df0-bbf4-05c347355383 */
   private double corrAntenAtten;
   /** @pdOid e7ce46af-14f0-4387-8f39-dce782352094 */
   private double otherAtten;
   /** @pdOid aebaaca9-4702-4be8-a999-860eab334bb8 */
   private double riseAtten;
   
   /**
 * 
 */
public OkumuraHata(){
	super();
	// TODO Auto-generated constructor stub
}

/**
 * @param id
 * @param frequency
 * @param heightUE
 * @param heightENB
 * @param zoneType
 * @param diffAtten
 * @param spAtten
 * @param fieldOccupAtten
 * @param corrAntenAtten
 * @param otherAtten
 * @param riseAtten
 */
public OkumuraHata(int id, double frequency, double heightUE, double heightENB,
		String zoneType, double diffAtten, double spAtten,
		double fieldOccupAtten, double corrAntenAtten, double otherAtten,
		double riseAtten) {
	super();
	this.id = id;
	this.frequency = frequency;
	this.heightUE = heightUE;
	this.heightENB = heightENB;
	this.zoneType = zoneType;
	this.diffAtten = diffAtten;
	this.spAtten = spAtten;
	this.fieldOccupAtten = fieldOccupAtten;
	this.corrAntenAtten = corrAntenAtten;
	this.otherAtten = otherAtten;
	this.riseAtten = riseAtten;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

/** @pdOid a1ba55a3-44a3-41a8-b6f9-0a0d244c60bb */
   public double getFrequency() {
      return frequency;
   }
   
   /** @param newFrequency
    * @pdOid b3fd44cf-62cc-48de-aee3-95fa38d6890b */
   public void setFrequency(double newFrequency) {
      frequency = newFrequency;
   }
   
   /** @pdOid 7e782bac-665f-411d-86ff-bbe7373e56a2 */
   public double getHeightUE() {
      return heightUE;
   }
   
   /** @param newHeightUE
    * @pdOid 444d2295-c92e-4acd-9a03-8ebaa828b39a */
   public void setHeightUE(double newHeightUE) {
      heightUE = newHeightUE;
   }
   
   /** @pdOid b20a6f27-efa3-4eea-a147-17840fb7457c */
   public double getHeightENB() {
      return heightENB;
   }
   
   /** @param newHeightENB
    * @pdOid 26cbc4dd-38c2-4675-b732-c8d644f7a1db */
   public void setHeightENB(double newHeightENB) {
      heightENB = newHeightENB;
   }
   
   /** @pdOid d69ecdd9-b92b-44f1-abd1-852b24930e26 */
   public String getZoneType() {
      return zoneType;
   }
   
   /** @param newZoneType
    * @pdOid 48f63691-a925-4429-8be3-22c9d2761f57 */
   public void setZoneType(String newZoneType) {
      zoneType = newZoneType;
   }
   
   /** @pdOid 0a3e335b-4097-4be5-9c11-7c1e38494395 */
   public double getDiffAtten() {
      return diffAtten;
   }
   
   /** @param newDiffAtten
    * @pdOid 0b2a9548-e054-44ad-bf46-dd51ee4f6ffe */
   public void setDiffAtten(double newDiffAtten) {
      diffAtten = newDiffAtten;
   }
   
   /** @pdOid de098b58-6372-4eed-a101-8c1dbd739bb9 */
   public double getSpAtten() {
      return spAtten;
   }
   
   /** @param newSpAtten
    * @pdOid 2127a0da-fb44-4dba-9268-60ac9dc809ad */
   public void setSpAtten(double newSpAtten) {
      spAtten = newSpAtten;
   }
   
   /** @pdOid b8564484-7d82-4b72-9864-d0aff66becce */
   public double getFieldOccupAtten() {
      return fieldOccupAtten;
   }
   
   /** @param newFieldOccupAtten
    * @pdOid 7b10841f-ec45-4ed5-8c67-4568ae32c478 */
   public void setFieldOccupAtten(double newFieldOccupAtten) {
      fieldOccupAtten = newFieldOccupAtten;
   }
   
   /** @pdOid 4ef79587-0798-4a8a-aca2-e2035cda6a6f */
   public double getCorrAntenAtten() {
      return corrAntenAtten;
   }
   
   /** @param newCorrAntenAtten
    * @pdOid 865835a1-952b-4274-adf3-6a1388741b5e */
   public void setCorrAntenAtten(double newCorrAntenAtten) {
      corrAntenAtten = newCorrAntenAtten;
   }
   
   /** @pdOid c986dfa8-1870-48e4-b111-355c985f5127 */
   public double getOtherAtten() {
      return otherAtten;
   }
   
   /** @param newOtherAtten
    * @pdOid fc9f7730-a5f5-4b49-9213-ed71e71169a3 */
   public void setOtherAtten(double newOtherAtten) {
      otherAtten = newOtherAtten;
   }
   
   /** @pdOid 82480384-61ac-4445-a945-f93a46841b82 */
   public double getRiseAtten() {
      return riseAtten;
   }
   
   /** @param newRiseAtten
    * @pdOid 7ea6fe37-e87e-438a-a7ab-a14b2d712884 */
   public void setRiseAtten(double newRiseAtten) {
      riseAtten = newRiseAtten;
   }

}