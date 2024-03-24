package cm.opsim.model;
/***********************************************************************
 * Module:  FreqParameter.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class FreqParameter
 ***********************************************************************/

import java.util.*;

/** @pdOid e3b04788-b84d-4aad-a2be-9bf7cc44c48e */
public class FreqParameter extends AbstractModel{
	private int id = 0;
   /** @pdOid 13d14a09-c697-4f15-8b96-60b1b32dfb6e */
   private int numFreq;
   /** @pdOid be1667bf-5ae6-4ac9-bac3-c5e0eedd64ec */
   private int sizePattern;
   /** @pdOid 977da895-5fa6-483e-886c-353038535b65 */
   private double attenModel;
   
   /**
 * 
 */
public FreqParameter() {
	super();
	// TODO Auto-generated constructor stub
}

/**
 * @param id
 * @param numFreq
 * @param sizePattern
 * @param attenModel
 */
public FreqParameter(int id, int numFreq, int sizePattern, double attenModel) {
	super();
	this.id = id;
	this.numFreq = numFreq;
	this.sizePattern = sizePattern;
	this.attenModel = attenModel;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

/** @pdOid 11da8d35-c36e-472a-b612-476b9f5500eb */
   public int getNumFreq() {
      return numFreq;
   }
   
   /** @param newNumFreq
    * @pdOid 894c9885-2344-4629-93b8-ffe8faba91d6 */
   public void setNumFreq(int newNumFreq) {
      numFreq = newNumFreq;
   }
   
   /** @pdOid 0a2c4d8f-31bc-4630-9a1b-17b4793afdd5 */
   public int getSizePattern() {
      return sizePattern;
   }
   
   /** @param newSizePattern
    * @pdOid ecd59b80-fff4-4091-aa94-df3f242ed59f */
   public void setSizePattern(int newSizePattern) {
      sizePattern = newSizePattern;
   }
   
   /** @pdOid 4efe6932-84b8-4047-a98f-17f1245e09e7 */
   public double getAttenModel() {
      return attenModel;
   }
   
   /** @param newAttenModel
    * @pdOid 4179342e-1671-4b5a-9823-cd0b6c988c33 */
   public void setAttenModel(double newAttenModel) {
      attenModel = newAttenModel;
   }

}