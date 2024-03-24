package cm.opsim.model;
/***********************************************************************
 * Module:  GenParameter.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class GenParameter
 ***********************************************************************/

import java.util.*;

/** @pdOid e979e7b3-70fe-4411-817c-52162935da96 */
public class GenParameter extends AbstractModel{
	private int id = 0;
   /** @pdOid ac18738e-8eb9-48fd-8188-d98c370daa6c */
   private double freqBand;
   /** @pdOid e652db05-fb82-4b25-b7c9-b849abf01640 */
   private double bwCellBoardUL;
   /** @pdOid d4e2be83-de42-48dc-b04a-4bf157c072e5 */
   private double bwCellBoardDL;
   /** @pdOid 39ed414c-7f35-413a-bce8-2276c2df7dd1 */
   private int numPRBUl;
   /** @pdOid 6ea8fc7b-3fab-44c2-ae4f-fb5b09c9e401 */
   private String channelModel;
   /** @pdOid e43cf3e2-c9a2-4c56-82cb-b9fbbdd54667 */
   private double coverageProba;
   /** @pdOid d5c882f1-c5e2-4fcf-9345-71dde96e27b1 */
   private double bodyLoss;
   /** @pdOid 83828022-8516-4cc9-a038-be67687904e5 */
   private double buildingLoss;
   /** @pdOid 8e23386e-e36e-40f4-b66a-c51e0f2438cd */
   private double carLoss;
   /** @pdOid 954f0df9-bc77-437d-8d6d-35e33ac3f1d8 */
   private double fadingMargin;
   /** @pdOid 4557759b-ad0e-4af0-af78-38605163bb56 */
   private double figureNoise;   
   private double bwByPRB = 180; 
   private double bwFB;
   
   
   /**
 * 
 */
public GenParameter() {
	super();
	// TODO Auto-generated constructor stub
}


/**
 * @param id
 * @param freqBand
 * @param bwCellBoardUL
 * @param bwCellBoardDL
 * @param numPRBUl
 * @param channelModel
 * @param coverageProba
 * @param bodyLoss
 * @param buildingLoss
 * @param carLoss
 * @param fadingMargin
 * @param figureNoise
 * @param bwByPRB
 * @param bwFB
 */
public GenParameter(int id, double freqBand, double bwCellBoardUL,
		double bwCellBoardDL, int numPRBUl, String channelModel,
		double coverageProba, double bodyLoss, double buildingLoss,
		double carLoss, double fadingMargin, double figureNoise,
		double bwByPRB, double bwFB) {
	super();
	this.id = id;
	this.freqBand = freqBand;
	this.bwCellBoardUL = bwCellBoardUL;
	this.bwCellBoardDL = bwCellBoardDL;
	this.numPRBUl = numPRBUl;
	this.channelModel = channelModel;
	this.coverageProba = coverageProba;
	this.bodyLoss = bodyLoss;
	this.buildingLoss = buildingLoss;
	this.carLoss = carLoss;
	this.fadingMargin = fadingMargin;
	this.figureNoise = figureNoise;
	this.bwByPRB = bwByPRB;
	this.bwFB = bwFB;
}

	
	public double getBwFB() {
		return bwFB;
	}	
	
	public void setBwFB(double bwFB) {
		this.bwFB = bwFB;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getBwByPRB() {
			return bwByPRB;
	}

	public void setBwByPRB(double bwByPRB) {
		this.bwByPRB = bwByPRB;
	}

/** @pdOid 52dd9e5a-39fb-42f6-90a8-b11e5f33fa92 */
   public double getFreqBand() {
      return freqBand;
   }
   
   /** @param newFreqBand
    * @pdOid 6c646210-97e9-4fad-b049-59bab04948bb */
   public void setFreqBand(double newFreqBand) {
      freqBand = newFreqBand;
   }
   
   /** @pdOid 4abefd65-196e-4fc5-87c2-24f0000b8377 */
   public double getBwCellBoardUL() {
      return bwCellBoardUL;
   }
   
   /** @param newBwCellBoardUL
    * @pdOid 9fd9eac3-43d0-4b95-9df6-9dbde6ff63bb */
   public void setBwCellBoardUL(double newBwCellBoardUL) {
      bwCellBoardUL = newBwCellBoardUL;
   }
   
   /** @pdOid 533e5548-f417-43e3-938c-503d5dc2cfce */
   public int getNumPRBUl() {
      return numPRBUl;
   }
   
   /** @param newNumPRBUl
    * @pdOid 1b9cc53a-7772-4ecf-aa33-314973d3b3be */
   public void setNumPRBUl(int newNumPRBUl) {
      numPRBUl = newNumPRBUl;
   }
   
   /** @pdOid 3adf1156-365a-4ed4-a20b-2e9d89c19873 */
   public String getChannelModel() {
      return channelModel;
   }
   
   /** @param newAntennaType
    * @pdOid 40d7b136-0d95-4084-801f-fb332a7f55eb */
   public void setChannelModel(String newChannelModel) {
	   channelModel = newChannelModel;
   }
   
   /** @pdOid df98653e-5e59-4f7e-90b8-bd1a399d97e9 */
   public double getCoverageProba() {
      return coverageProba;
   }
   
   /** @param newCoverageProba
    * @pdOid 436a6bf3-92c2-4c4e-9948-ad3698c51264 */
   public void setCoverageProba(double newCoverageProba) {
      coverageProba = newCoverageProba;
   }
   
   /** @pdOid 791b27f2-37c2-4fbb-a5bc-a7710fec3b22 */
   public double getBodyLoss() {
      return bodyLoss;
   }
   
   /** @param newBodyLoss
    * @pdOid 5363980b-efc7-411b-8a6d-1e499986de9f */
   public void setBodyLoss(double newBodyLoss) {
      bodyLoss = newBodyLoss;
   }
   
   /** @pdOid 435008e4-0b47-4dfa-af96-79f50bb0d5b0 */
   public double getBuildingLoss() {
      return buildingLoss;
   }
   
   /** @param newBuildingLoss
    * @pdOid f6810541-2f75-4149-a4cb-432a8f298b8b */
   public void setBuildingLoss(double newBuildingLoss) {
      buildingLoss = newBuildingLoss;
   }
   
   /** @pdOid 9fbe0dfd-17ce-46a1-bc93-d82cfabf011f */
   public double getCarLoss() {
      return carLoss;
   }
   
   /** @param newCarLoss
    * @pdOid 2e73b0f7-1dad-4a58-b53f-d6f6e61f7904 */
   public void setCarLoss(double newCarLoss) {
      carLoss = newCarLoss;
   }
   
   /** @pdOid 06be1185-d1fc-4b79-bdaa-c70ca8af48a3 */
   public double getFadingMargin() {
      return fadingMargin;
   }
   
   /** @param newFadingMargin
    * @pdOid f8e602d5-787c-414f-b121-3ed2013f4f08 */
   public void setFadingMargin(double newFadingMargin) {
      fadingMargin = newFadingMargin;
   }
   
   /** @pdOid c077f03a-e610-46f2-87a5-6316c9aa5be8 */
   public double getFigureNoise() {
      return figureNoise;
   }
   
   /** @param newFigureNoise
    * @pdOid 61a38e27-c444-4443-9289-ffacef0e3c2f */
   public void setFigureNoise(double newFigureNoise) {
      figureNoise = newFigureNoise;
   }
   
   /** @pdOid f3d4182c-6d7b-46d8-9fe9-012ce50351b0 */
   public double getBwCellBoardDL() {
      return bwCellBoardDL;
   }
   
   /** @param newBwCellBoardDL
    * @pdOid 97012558-f8ec-4be2-bea0-09b3e2e72beb */
   public void setBwCellBoardDL(double newBwCellBoardDL) {
      bwCellBoardDL = newBwCellBoardDL;
   }

}