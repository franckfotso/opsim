package cm.opsim.model;
/***********************************************************************
 * Module:  AntennaParameter.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class AntennaParameter
 ***********************************************************************/

import java.util.*;

/** @pdOid f2d38a48-db26-4ff0-a5d7-9bcc65c3f0c2 */
public class AntennaParameter extends AbstractModel{
	private int id = 0;
   /** @pdOid 64061bf8-9221-4507-9bc5-1276ffd14f33 */
   private String type;
   /** @pdOid e9c2d48e-506d-43d0-939c-9dd917605ab1 */
   private String radDiagram;
   /** @pdOid 54b14857-e70a-4015-a236-daa16ee174d7 */
   private double directivity;
   /** @pdOid 7df3ec58-3071-4fb5-8f80-253f045a97d1 */
   private double gain;
   /** @pdOid 01ee5cdc-bbb4-4680-a94f-b4f55e7c1541 */
   private double efficiency;
   
   /** @pdRoleInfo migr=no name=AntennaType assc=estSpecifiqueA mult=1..1 */
   public AntennaType antennaType;
   
   
   
   /**
 * 
 */
public AntennaParameter() {
	super();
	this.type = "";
	this.radDiagram = "";
	this.directivity = 0.00;
	this.gain = 0.00;
	this.efficiency = 0.00;
	this.antennaType = new AntennaType();
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

/**
 * @param id
 * @param type
 * @param radDiagram
 * @param directivity
 * @param gain
 * @param efficiency
 * @param antennaType
 * @param trafficModel
 */
public AntennaParameter(int id, String type, String radDiagram,
		double directivity, double gain, double efficiency,
		AntennaType antennaType) {
	super();
	this.id = id;
	this.type = type;
	this.radDiagram = radDiagram;
	this.directivity = directivity;
	this.gain = gain;
	this.efficiency = efficiency;
	this.antennaType = antennaType;
}

public AntennaType getAntennaType() {
	return antennaType;
}

public void setAntennaType(AntennaType antennaType) {
	this.antennaType = antennaType;
}

/** @pdOid b17c24d6-bab3-4b75-9ec5-bc7d3e304f13 */
   public String getType() {
      return type;
   }
   
   /** @param newType
    * @pdOid 804e946f-2194-4f68-9cd5-69411f492e55 */
   public void setType(String newType) {
      type = newType;
   }
   
   /** @pdOid 586d0614-08cc-4885-a63d-4bf1b8d0c2dc */
   public String getRadDiagram() {
      return radDiagram;
   }
   
   /** @param newRadDiagram
    * @pdOid 1f39a175-bbf9-4d61-bd9c-85a6c1299e82 */
   public void setRadDiagram(String newRadDiagram) {
      radDiagram = newRadDiagram;
   }
   
   /** @pdOid aa8f2a88-4bae-479f-b10c-3f6a5865fa6f */
   public double getDirectivity() {
      return directivity;
   }
   
   /** @param newDirectivity
    * @pdOid 1e909cb5-dcc7-480e-9284-086674dbac9b */
   public void setDirectivity(double newDirectivity) {
      directivity = newDirectivity;
   }
   
   /** @pdOid 6e62ebc7-bcfc-4428-a32b-964de9d540ad */
   public double getGain() {
      return gain;
   }
   
   /** @param newGain
    * @pdOid 227bd885-fcdd-4ba1-ab6c-3d7cd3a4e999 */
   public void setGain(double newGain) {
      gain = newGain;
   }
   
   /** @pdOid bcd0082b-a718-4429-be08-7208a64aa1e6 */
   public double getEfficiency() {
      return efficiency;
   }
   
   /** @param newEfficiency
    * @pdOid 1e7a2dee-917a-46c3-8c8a-732b9e6b6df6 */
   public void setEfficiency(double newEfficiency) {
      efficiency = newEfficiency;
   }

}