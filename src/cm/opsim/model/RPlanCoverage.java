package cm.opsim.model;
/***********************************************************************
 * Module:  RPlanCoverage.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class RPlanCoverage
 ***********************************************************************/

import java.util.*;

/** @pdOid c8e7cf48-4144-4f3a-8738-5b1abef114d6 */
public class RPlanCoverage extends AbstractModel{
	private int id = 0;
   /** @pdOid 8a52b997-180b-4499-813a-936a6148d4f1 */
   private double pireUl;
   /** @pdOid a1cc88e1-e72f-49d4-8568-7b83427a1d67 */
   private double pireDl;
   /** @pdOid 9db2ff82-2311-4d8c-873b-d918979129c8 */
   private double sensibilityUL;
   /** @pdOid 7f4166bd-4b17-4513-855c-a931758a179c */
   private double sensibilityDL;
   /** @pdOid c382c439-5c12-4b4b-a900-a1ff88b8570e */
   private double thermicNoiseUL;
   /** @pdOid 0c8ae7a9-7b19-4f48-984c-f58a138f6a38 */
   private double thermicNoiseDL;
   /** @pdOid e71563c9-01dd-403c-99dc-fe9262a27117 */
   private double pathLossUL;
   /** @pdOid 9433d359-dc03-41eb-88be-6c87b93b658a */
   private double pathLossDL;
   /** @pdOid 35ebb4d5-9d40-438f-81f6-5b699aecd71b */
   private double cellRadiusUL;
   /** @pdOid 33b2b9d4-ed09-479d-932e-61800ee1f44a */
   private double cellRadiusDL;
   /** @pdOid ff65c4b6-1176-410b-9354-7e81db2b8ee1 */
   private double cellPrintUL;
   /** @pdOid 82f44764-52b9-48c9-bd02-38ac6d8d9491 */
   private double cellPrintDL;
   /** @pdOid 3d26b727-2099-4991-8893-7f779bf1e38a */
   private double sitePrintUL;
   /** @pdOid c3c94b3a-58a5-454c-910a-39a59358de01 */
   private double sitePrintDL;
   /** @pdOid 24270fd6-ab42-4f1b-a206-fabde1b42fbf */
   private int numENBUl;
   /** @pdOid df8bf1ec-3ccd-423d-920f-696cc93b35e9 */
   private int numENBDl;
   /** @pdOid 6183a466-a742-4343-9135-6d62dec87927 */
   private double interSiteDistUL;
   /** @pdOid 0db32d81-99bc-4b5f-93cb-53e0756a78e7 */
   private double interSiteDistDL;
   
   /**
 * 
 */
public RPlanCoverage() {
	super();
	// TODO Auto-generated constructor stub
}

/**
 * @param id
 * @param pireUl
 * @param pireDl
 * @param sensibilityUL
 * @param sensibilityDL
 * @param thermicNoiseUL
 * @param thermicNoiseDL
 * @param pathLossUL
 * @param pathLossDL
 * @param cellRadiusUL
 * @param cellRadiusDL
 * @param cellPrintUL
 * @param cellPrintDL
 * @param sitePrintUL
 * @param sitePrintDL
 * @param numENBUl
 * @param numENBDl
 * @param interSiteDistUL
 * @param interSiteDistDL
 */
public RPlanCoverage(int id, double pireUl, double pireDl,
		double sensibilityUL, double sensibilityDL, double thermicNoiseUL,
		double thermicNoiseDL, double pathLossUL, double pathLossDL,
		double cellRadiusUL, double cellRadiusDL, double cellPrintUL,
		double cellPrintDL, double sitePrintUL, double sitePrintDL,
		int numENBUl, int numENBDl, double interSiteDistUL,
		double interSiteDistDL) {
	super();
	this.id = id;
	this.pireUl = pireUl;
	this.pireDl = pireDl;
	this.sensibilityUL = sensibilityUL;
	this.sensibilityDL = sensibilityDL;
	this.thermicNoiseUL = thermicNoiseUL;
	this.thermicNoiseDL = thermicNoiseDL;
	this.pathLossUL = pathLossUL;
	this.pathLossDL = pathLossDL;
	this.cellRadiusUL = cellRadiusUL;
	this.cellRadiusDL = cellRadiusDL;
	this.cellPrintUL = cellPrintUL;
	this.cellPrintDL = cellPrintDL;
	this.sitePrintUL = sitePrintUL;
	this.sitePrintDL = sitePrintDL;
	this.numENBUl = numENBUl;
	this.numENBDl = numENBDl;
	this.interSiteDistUL = interSiteDistUL;
	this.interSiteDistDL = interSiteDistDL;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

/** @pdOid 9115545f-3670-4a56-8376-5b331f2d5539 */
   public double getPireUl() {
      return pireUl;
   }
   
   /** @param newPireUl
    * @pdOid 57f29078-3843-4a21-bf16-1f3bb95bc8f8 */
   public void setPireUl(double newPireUl) {
      pireUl = newPireUl;
   }
   
   /** @pdOid 74ed69e5-2411-4296-a116-4c94ecc25a11 */
   public double getPireDl() {
      return pireDl;
   }
   
   /** @param newPireDl
    * @pdOid 2348afda-1da7-4075-b3bd-ac98e0c1e3e2 */
   public void setPireDl(double newPireDl) {
      pireDl = newPireDl;
   }
   
   /** @pdOid 1b2899b7-43f7-4d10-b55f-027679ab5234 */
   public double getSensibilityUL() {
      return sensibilityUL;
   }
   
   /** @param newSensibilityUL
    * @pdOid 47bdee98-de82-48f7-82c4-86ec145f42ec */
   public void setSensibilityUL(double newSensibilityUL) {
      sensibilityUL = newSensibilityUL;
   }
   
   /** @pdOid 366f1856-1895-4b75-9558-fad2d6e143ae */
   public double getSensibilityDL() {
      return sensibilityDL;
   }
   
   /** @param newSensibilityDL
    * @pdOid 1496fbca-ea34-47ed-b37e-867c7609207c */
   public void setSensibilityDL(double newSensibilityDL) {
      sensibilityDL = newSensibilityDL;
   }
   
   /** @pdOid 0c2fa599-ee53-4eeb-a414-70d24eb6ff90 */
   public double getThermicNoiseUL() {
      return thermicNoiseUL;
   }
   
   /** @param newThermicNoiseUL
    * @pdOid 418a434b-a146-4915-ab5b-c3672e45a538 */
   public void setThermicNoiseUL(double newThermicNoiseUL) {
      thermicNoiseUL = newThermicNoiseUL;
   }
   
   /** @pdOid 85906928-61cc-4ea7-8be0-7333fec1be06 */
   public double getThermicNoiseDL() {
      return thermicNoiseDL;
   }
   
   /** @param newThermicNoiseDL
    * @pdOid 43beae8a-c10a-4f49-b9a3-2fa8bf1d72c0 */
   public void setThermicNoiseDL(double newThermicNoiseDL) {
      thermicNoiseDL = newThermicNoiseDL;
   }
   
   /** @pdOid cb63508d-5eb7-4bfa-ac08-9dd532ab7c4c */
   public double getPathLossUL() {
      return pathLossUL;
   }
   
   /** @param newPathLossUL
    * @pdOid 0738a59c-2414-443f-a3f3-17e87cf06588 */
   public void setPathLossUL(double newPathLossUL) {
      pathLossUL = newPathLossUL;
   }
   
   /** @pdOid a5a6e389-2120-4aba-bc5a-865663ed79bd */
   public double getPathLossDL() {
      return pathLossDL;
   }
   
   /** @param newPathLossDL
    * @pdOid 37015612-24bc-43e9-b984-7b98438c61de */
   public void setPathLossDL(double newPathLossDL) {
      pathLossDL = newPathLossDL;
   }
   
   /** @pdOid 3b8914e6-6bb5-4d87-a764-2b467e169ffa */
   public double getCellRadiusUL() {
      return cellRadiusUL;
   }
   
   /** @param newCellRadiusUL
    * @pdOid d42fb8a3-a78f-4086-a3e3-e80493195e0c */
   public void setCellRadiusUL(double newCellRadiusUL) {
      cellRadiusUL = newCellRadiusUL;
   }
   
   /** @pdOid a9e1dd7e-efef-42e4-9e7d-c6744a77441a */
   public double getCellRadiusDL() {
      return cellRadiusDL;
   }
   
   /** @param newCellRadiusDL
    * @pdOid 017c28dc-4ef6-484a-a330-082d7a74f7fb */
   public void setCellRadiusDL(double newCellRadiusDL) {
      cellRadiusDL = newCellRadiusDL;
   }
   
   /** @pdOid 740f9e53-1a74-4e97-8606-2974c65356d1 */
   public double getCellPrintUL() {
      return cellPrintUL;
   }
   
   /** @param newCellPrintUL
    * @pdOid cdb3b898-f858-4485-8929-1f492b30a372 */
   public void setCellPrintUL(double newCellPrintUL) {
      cellPrintUL = newCellPrintUL;
   }
   
   /** @pdOid a5f2dfe7-5936-405a-b7ad-7ae3a718ae67 */
   public double getCellPrintDL() {
      return cellPrintDL;
   }
   
   /** @param newCellPrintDL
    * @pdOid f9f1d5c5-6ae2-4996-b5ba-be9e4f7089ab */
   public void setCellPrintDL(double newCellPrintDL) {
      cellPrintDL = newCellPrintDL;
   }
   
   /** @pdOid 88789744-4ee0-4209-a173-f040da41c840 */
   public double getSitePrintUL() {
      return sitePrintUL;
   }
   
   /** @param newSitePrintUL
    * @pdOid f70250f1-4cd2-43e2-a17f-dd38e7b9ef55 */
   public void setSitePrintUL(double newSitePrintUL) {
      sitePrintUL = newSitePrintUL;
   }
   
   /** @pdOid a10c69c9-7b06-41a3-a1df-bcda526fb463 */
   public double getSitePrintDL() {
      return sitePrintDL;
   }
   
   /** @param newSitePrintDL
    * @pdOid 9a7705cd-7bdd-4100-9d11-ed30e98a675d */
   public void setSitePrintDL(double newSitePrintDL) {
      sitePrintDL = newSitePrintDL;
   }
   
   /** @pdOid 5cf5d2de-4663-4b76-8684-1de107c26f1b */
   public int getNumENBUl() {
      return numENBUl;
   }
   
   /** @param newNumENBUl
    * @pdOid 88a6944b-1539-4236-9fa1-3d9cf87f1f0c */
   public void setNumENBUl(int newNumENBUl) {
      numENBUl = newNumENBUl;
   }
   
   /** @pdOid 525feaad-febe-4bc1-b761-b12a92823d2a */
   public int getNumENBDl() {
      return numENBDl;
   }
   
   /** @param newNumENBDl
    * @pdOid 487e3e58-eb32-4e88-ae42-601cff671ef8 */
   public void setNumENBDl(int newNumENBDl) {
      numENBDl = newNumENBDl;
   }
   
   /** @pdOid 653f5d6a-2cc4-4ba6-90a6-e12e95538263 */
   public double getInterSiteDistUL() {
      return interSiteDistUL;
   }
   
   /** @param newInterSiteDistUL
    * @pdOid cd572611-04fb-4bb1-a1db-dd00b762c868 */
   public void setInterSiteDistUL(double newInterSiteDistUL) {
      interSiteDistUL = newInterSiteDistUL;
   }
   
   /** @pdOid cdbd8c06-dfe9-4c1d-af9c-cdffa069a444 */
   public double getInterSiteDistDL() {
      return interSiteDistDL;
   }
   
   /** @param newInterSiteDistDL
    * @pdOid 4f8dc09f-74b7-4803-9a4d-06cf56f763db */
   public void setInterSiteDistDL(double newInterSiteDistDL) {
      interSiteDistDL = newInterSiteDistDL;
   }

}