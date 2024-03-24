package cm.opsim.model;
/***********************************************************************
 * Module:  Cost231.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class Cost231
 ***********************************************************************/

import java.util.*;

/** @pdOid 13443b67-e917-4f52-8611-d69e37feeaf9 */
public class Cost231Hata extends PropaModel{
   /** @pdOid 8c95f712-4da9-4fc3-82b1-3b05b8d212d7 */
   private double frequency;
   /** @pdOid b3aa15d4-a445-4364-b743-e4d36a2c4f63 */
   private double heightUE;
   /** @pdOid 952b01c6-b974-4c8a-9add-0b066fcd3552 */
   private double heightENB;
   /** @pdOid 374a132c-a03f-4d0c-96cb-8226a3cad4a9 */
   private double heightRoof;
   /** @pdOid baf13684-2f96-4df9-abd1-6d8051d09796 */
   private double bldSeparation;
   /** @pdOid c7c6c73e-616f-43f8-9366-bfefc956845f */
   private double incidenceAngle;
   /** @pdOid c481edf1-7601-4fb3-8156-d1815fc7bb98 */
   private double widthString;
   /** @pdOid 8c4f406c-b788-44ad-911b-2203f2fa4dce */
   private String zoneType;
   /** @pdOid 36c99ea4-21de-4f36-81e1-e1863d9833ec */
   private String subTypeCost231;
   
   /**
 * 
 */
public Cost231Hata() {
	super();
	// TODO Auto-generated constructor stub
}

/**
 * @param id
 * @param frequency
 * @param heightUE
 * @param heightENB
 * @param heightRoof
 * @param bldSeparation
 * @param incidenceAngle
 * @param widthString
 * @param zoneType
 * @param subTypeCost231
 */
public Cost231Hata(int id, double frequency, double heightUE, double heightENB,
		double heightRoof, double bldSeparation, double incidenceAngle,
		double widthString, String zoneType, String subTypeCost231) {
	super();
	this.id = id;
	this.frequency = frequency;
	this.heightUE = heightUE;
	this.heightENB = heightENB;
	this.heightRoof = heightRoof;
	this.bldSeparation = bldSeparation;
	this.incidenceAngle = incidenceAngle;
	this.widthString = widthString;
	this.zoneType = zoneType;
	this.subTypeCost231 = subTypeCost231;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

/** @pdOid 1d28a36c-3a82-4860-b8ea-48cc907b0eb5 */
   public double getFrequency() {
      return frequency;
   }
   
   /** @param newFrequency
    * @pdOid 559b3a3d-b194-49c7-9c78-16ba869325c0 */
   public void setFrequency(double newFrequency) {
      frequency = newFrequency;
   }
   
   /** @pdOid 7d44ad31-7962-481e-b073-ccc1b11691c0 */
   public double getHeightUE() {
      return heightUE;
   }
   
   /** @param newHeightUE
    * @pdOid cfe437c7-f550-4dec-9eab-35b53dce1016 */
   public void setHeightUE(double newHeightUE) {
      heightUE = newHeightUE;
   }
   
   /** @pdOid d858b20b-6823-4ff3-8d72-ce83708b9b9f */
   public double getHeightENB() {
      return heightENB;
   }
   
   /** @param newHeightENB
    * @pdOid e23b9a9a-cc21-491b-aa6b-29b8c75e236a */
   public void setHeightENB(double newHeightENB) {
      heightENB = newHeightENB;
   }
   
   /** @pdOid 4154288a-42aa-4b78-a1cb-6a1919b455b1 */
   public double getHeightRoof() {
      return heightRoof;
   }
   
   /** @param newHeightRoof
    * @pdOid d16daa09-8158-417f-acf7-9eb3ad16e1fa */
   public void setHeightRoof(double newHeightRoof) {
      heightRoof = newHeightRoof;
   }
   
   /** @pdOid 23aaeaa4-de70-4f44-8b4e-9bea767185f4 */
   public double getBldSeparation() {
      return bldSeparation;
   }
   
   /** @param newBldSeparation
    * @pdOid 6362b046-4545-448f-9edf-adba0a2e5c7d */
   public void setBldSeparation(double newBldSeparation) {
      bldSeparation = newBldSeparation;
   }
   
   /** @pdOid b2ae7a7e-ef26-44d8-a9f4-ea7b2a75c738 */
   public double getIncidenceAngle() {
      return incidenceAngle;
   }
   
   /** @param newIncidenceAngle
    * @pdOid 42184f68-abc4-4004-9680-6704d0d133a9 */
   public void setIncidenceAngle(double newIncidenceAngle) {
      incidenceAngle = newIncidenceAngle;
   }
   
   /** @pdOid fbf8a499-5111-4e8d-a463-8045678e2847 */
   public double getWidthString() {
      return widthString;
   }
   
   /** @param newWidthString
    * @pdOid f4fc4ea0-e429-47f3-b247-05281859f5b1 */
   public void setWidthString(double newWidthString) {
      widthString = newWidthString;
   }
   
   /** @pdOid 5fc12779-e963-4b52-b48f-10cfbe2e3708 */
   public String getZoneType() {
      return zoneType;
   }
   
   /** @param newZoneType
    * @pdOid fb81d259-f37e-42ba-9330-ff1adeba925c */
   public void setZoneType(String newZoneType) {
      zoneType = newZoneType;
   }
   
   /** @pdOid df073465-0dbe-4938-8daa-6071ac09a25d */
   public String getSubTypeCost231() {
      return subTypeCost231;
   }
   
   /** @param newSubTypeCost231
    * @pdOid af434522-d747-4882-8eff-0edb334434bd */
   public void setSubTypeCost231(String newSubTypeCost231) {
      subTypeCost231 = newSubTypeCost231;
   }

}