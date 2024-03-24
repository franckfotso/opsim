package cm.opsim.model;
/***********************************************************************
 * Module:  EnbParameter.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class EnbParameter
 ***********************************************************************/

import java.util.*;

/** @pdOid d94bafe6-c19e-4f28-8c08-4c20904e91e4 */
public class EnbParameter extends AbstractModel{
	private int id = 0;
   /** @pdOid 14fb03a3-569b-47c2-bbba-f06f2756c3e0 */
   private double cableLoss;
   /** @pdOid b6b7ab72-966b-4d50-91dd-645ab814f40e */
   private double antennaGain;
   /** @pdOid d8d24d01-6732-499d-b1c4-3f8d4ad7abcb */
   private double thermicNoiseByRB;
   /** @pdOid 0b87814a-a633-4347-8f24-a3dcccdd85eb */
   private double powerTx;
   /** @pdOid 1e5bea2c-fb6d-4aaf-9fae-00865fcfa7d1 */
   private double cellBoardSNIR;
   /** @pdOid b30ef2b5-d1f4-4114-93eb-8afc61268c76 */
   private double systemCharge;
   /** @pdOid caca2996-a8c1-41d1-b8b7-9e12bd57a7c2 */
   private double factorFC;
   /** @pdOid 75cc3e06-636c-48bc-88c2-5d09d641eedf */
   private double jumperTmaLoss;
   /** @pdOid 4fc8c845-c42f-4921-818e-9f7eb84228f6 */
   private String siteType;
   
   
   
   /**
 * 
 */
public EnbParameter() {
	super();
	// TODO Auto-generated constructor stub
}

/**
 * @param id
 * @param cableLoss
 * @param antennaGain
 * @param thermicNoiseByRB
 * @param powerTx
 * @param cellBoardSNIR
 * @param systemCharge
 * @param factorFC
 * @param jumperTmaLoss
 * @param siteType
 */
public EnbParameter(int id, double cableLoss, double antennaGain,
		double thermicNoiseByRB, double powerTx, double cellBoardSNIR,
		double systemCharge, double factorFC, double jumperTmaLoss,
		String siteType) {
	super();
	this.id = id;
	this.cableLoss = cableLoss;
	this.antennaGain = antennaGain;
	this.thermicNoiseByRB = thermicNoiseByRB;
	this.powerTx = powerTx;
	this.cellBoardSNIR = cellBoardSNIR;
	this.systemCharge = systemCharge;
	this.factorFC = factorFC;
	this.jumperTmaLoss = jumperTmaLoss;
	this.siteType = siteType;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

/** @pdOid c8edacb6-611d-45c3-af70-2f13f6f51a73 */
   public double getCableLoss() {
      return cableLoss;
   }
   
   /** @param newCableLoss
    * @pdOid 030f4d81-c45e-486f-8116-220d157568e9 */
   public void setCableLoss(double newCableLoss) {
      cableLoss = newCableLoss;
   }
   
   /** @pdOid ec635a29-6b01-4de5-9712-16e094a7b38b */
   public double getAntennaGain() {
      return antennaGain;
   }
   
   /** @param newAntennaGain
    * @pdOid bf6bdcbd-3e35-42e8-aac0-6b07cbdbd68e */
   public void setAntennaGain(double newAntennaGain) {
      antennaGain = newAntennaGain;
   }
   
   /** @pdOid 9ba458e0-ae34-4861-880f-71f0247e6a97 */
   public double getThermicNoiseByRB() {
      return thermicNoiseByRB;
   }
   
   /** @param newThermicNoiseByRB
    * @pdOid 7e32e9a2-7329-411b-ae23-b5302772b956 */
   public void setThermicNoiseByRB(double newThermicNoiseByRB) {
      thermicNoiseByRB = newThermicNoiseByRB;
   }
   
   /** @pdOid a5b60777-0cd4-4733-aba7-17d14ff640c7 */
   public double getPowerTx() {
      return powerTx;
   }
   
   /** @param newPowerTx
    * @pdOid 5da005ca-d0f4-402b-8575-5d15b1b6748b */
   public void setPowerTx(double newPowerTx) {
      powerTx = newPowerTx;
   }
   
   /** @pdOid cf0038b3-66d7-4011-aa60-43d0ff4f451f */
   public double getCellBoardSNIR() {
      return cellBoardSNIR;
   }
   
   /** @param newCellBoardSNIR
    * @pdOid 467ea1c5-596b-4715-a2c3-276bb7345466 */
   public void setCellBoardSNIR(double newCellBoardSNIR) {
      cellBoardSNIR = newCellBoardSNIR;
   }
   
   /** @pdOid 90407790-94bd-40b8-b841-d076de3b111a */
   public double getSystemCharge() {
      return systemCharge;
   }
   
   /** @param newSystemCharge
    * @pdOid 0a9cafd6-f4de-488d-bed5-6631e14810fa */
   public void setSystemCharge(double newSystemCharge) {
      systemCharge = newSystemCharge;
   }
   
   /** @pdOid 99d331d4-b6f4-48ae-87c2-881261c605d5 */
   public double getFactorFC() {
      return factorFC;
   }
   
   /** @param newFactorFC
    * @pdOid 806507b1-ac49-4ebd-a97d-8ba3a6fb2f0d */
   public void setFactorFC(double newFactorFC) {
      factorFC = newFactorFC;
   }
   
   /** @pdOid e8062d21-ebd3-43b0-93da-edd68fca1f7d */
   public double getJumperTmaLoss() {
      return jumperTmaLoss;
   }
   
   /** @param newJumperTmaLoss
    * @pdOid 641c062d-2092-45c1-b6f2-311693319b1b */
   public void setJumperTmaLoss(double newJumperTmaLoss) {
      jumperTmaLoss = newJumperTmaLoss;
   }
   
   /** @pdOid 171bd549-688b-4a37-8e03-c8764be6a0b3 */
   public String getSiteType() {
      return siteType;
   }
   
   /** @param newSiteType
    * @pdOid b91acef0-ddaa-40a4-9693-7dc8b8c3ac9b */
   public void setSiteType(String newSiteType) {
      siteType = newSiteType;
   }

}