package cm.opsim.model;
/***********************************************************************
 * Module:  UeParameter.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class UeParameter
 ***********************************************************************/

import java.util.*;

/** @pdOid 65a8d33b-03e8-46ff-9149-ec948b07efd0 */
public class UeParameter extends AbstractModel{
	private int id = 0;
   /** @pdOid 8dee3d0a-140b-46e3-b6ba-50a7273882e6 */
   private double powerUE;
   /** @pdOid 1daafdbb-b645-463f-8ac4-199f4ac4ae36 */
   private double antennaGain;
   /** @pdOid ea0c1065-f306-4263-8456-fb1cac05ca12 */
   private double thermicNoiseByRB;
   /** @pdOid a5a468e7-cdc2-4c25-aec9-b50061002074 */
   private double targetSNIR;
   /** @pdOid e7e944cd-57d3-4269-b579-f99ec91a18fa */
   private double systCharge;
   /** @pdOid 8ed73ca6-d4fa-4f2a-b55f-3696ecbbdee8 */
   private double factorF;
   /** @pdOid 40ba58db-80b3-4ab1-969a-9c398bbf1056 */
   private double jumperTmaLoss;
   
   
   
   /**
 * 
 */
public UeParameter() {
	super();
	// TODO Auto-generated constructor stub
}

/**
 * @param id
 * @param powerUE
 * @param antennaGain
 * @param thermicNoiseByRB
 * @param targetSNIR
 * @param systCharge
 * @param factorF
 * @param jumperTmaLoss
 */
public UeParameter(int id, double powerUE, double antennaGain,
		double thermicNoiseByRB, double targetSNIR, double systCharge,
		double factorF, double jumperTmaLoss) {
	super();
	this.id = id;
	this.powerUE = powerUE;
	this.antennaGain = antennaGain;
	this.thermicNoiseByRB = thermicNoiseByRB;
	this.targetSNIR = targetSNIR;
	this.systCharge = systCharge;
	this.factorF = factorF;
	this.jumperTmaLoss = jumperTmaLoss;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

/** @pdOid 1a9a10e4-dacb-47fc-93dc-c6ae22f0f73d */
   public double getPowerUE() {
      return powerUE;
   }
   
   /** @param newPowerUE
    * @pdOid 711f62d0-cac9-4f5f-8f96-715bd1e55e0d */
   public void setPowerUE(double newPowerUE) {
      powerUE = newPowerUE;
   }
   
   /** @pdOid 26180180-33bb-4e89-9297-04a4e505b46a */
   public double getAntennaGain() {
      return antennaGain;
   }
   
   /** @param newAntennaGain
    * @pdOid 7289a125-d790-415c-89e6-fd98a377ed78 */
   public void setAntennaGain(double newAntennaGain) {
      antennaGain = newAntennaGain;
   }
   
   /** @pdOid 36045100-5037-4a89-89de-e0f2ddaf3ab4 */
   public double getThermicNoiseByRB() {
      return thermicNoiseByRB;
   }
   
   /** @param newThermicNoiseByRB
    * @pdOid e72841c3-bc46-4227-b470-bdbdcf0c26dd */
   public void setThermicNoiseByRB(double newThermicNoiseByRB) {
      thermicNoiseByRB = newThermicNoiseByRB;
   }
   
   /** @pdOid b6758d14-91cf-4889-a891-3fd5c040bd83 */
   public double getTargetSNIR() {
      return targetSNIR;
   }
   
   /** @param newTargetSNIR
    * @pdOid a9695b60-f40e-4247-8b5c-ae5753891a73 */
   public void setTargetSNIR(double newTargetSNIR) {
      targetSNIR = newTargetSNIR;
   }
   
   /** @pdOid b60763bd-18eb-4acb-b56e-e7de83ac8a26 */
   public double getSystCharge() {
      return systCharge;
   }
   
   /** @param newSystCharge
    * @pdOid 4ddc75da-d3d8-4ec3-a80e-22bd0cc2bf86 */
   public void setSystCharge(double newSystCharge) {
      systCharge = newSystCharge;
   }
   
   /** @pdOid 530af1e5-ce8e-45c3-8845-ac5e736f677f */
   public double getFactorF() {
      return factorF;
   }
   
   /** @param newFactorF
    * @pdOid f08312d5-7fc3-4e61-942e-f295ae5037be */
   public void setFactorF(double newFactorF) {
      factorF = newFactorF;
   }
   
   /** @pdOid 08d10f1a-585e-47fc-a919-4034f0bb6eaf */
   public double getJumperTmaLoss() {
      return jumperTmaLoss;
   }
   
   /** @param newJumperTmaLoss
    * @pdOid c974f4c1-0cb8-4ddb-97cb-0092027fb0c8 */
   public void setJumperTmaLoss(double newJumperTmaLoss) {
      jumperTmaLoss = newJumperTmaLoss;
   }

}