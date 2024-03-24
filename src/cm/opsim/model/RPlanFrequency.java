package cm.opsim.model;
/***********************************************************************
 * Module:  RPlanFrequency.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class RPlanFrequency
 ***********************************************************************/

import java.util.*;

/** @pdOid e88dc855-3b55-4f06-ba72-83db5222f0f8 */
public class RPlanFrequency extends AbstractModel{
	private int id = 0;
   /** @pdOid aaade0d3-f878-4fa0-a9f3-282b4b987798 */
   private int numFreqByCell;
   /** @pdOid c1e2aa22-63a9-404e-ab3e-0315c33f0cbc */
   private int factorR;
   /** @pdOid c614517d-4491-4804-8b89-638ae000c3f9 */
   private double sir;
   /** @pdOid 65d32aa3-eb1e-4b6f-a9b5-919d68727610 */
   private int numChannel;
   /** @pdOid 0b094f18-ddc5-4c9d-858b-57953c192424 */
   private double area;
   /** @pdOid 382cba9d-d577-46a7-8633-476a6961aa43 */
   private double radius;
   /** @pdOid bb8c9de7-9dbe-4698-a69c-164b766f464c */
   private int numCell;
   /** @pdOid 92fbb4e3-51e6-4538-956a-d17417c4910c */
   private double interSiteDist;
   /** @pdOid 747f74dd-25bf-4d09-adc5-e9c14be9f0af */
   private double distanceR;
   
   /**
 * 
 */
public RPlanFrequency() {
	super();
	// TODO Auto-generated constructor stub
}

/**
 * @param id
 * @param numFreqByCell
 * @param factorR
 * @param sir
 * @param numChannel
 * @param area
 * @param radius
 * @param numCell
 * @param interSiteDist
 * @param distanceR
 */
public RPlanFrequency(int id, int numFreqByCell, int factorR, double sir,
		int numChannel, double area, double radius, int numCell,
		double interSiteDist, double distanceR) {
	super();
	this.id = id;
	this.numFreqByCell = numFreqByCell;
	this.factorR = factorR;
	this.sir = sir;
	this.numChannel = numChannel;
	this.area = area;
	this.radius = radius;
	this.numCell = numCell;
	this.interSiteDist = interSiteDist;
	this.distanceR = distanceR;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

/** @pdOid 7a034fa3-bd0f-4cd7-87a8-4cbffe710eba */
   public int getNumFreqByCell() {
      return numFreqByCell;
   }
   
   /** @param newNumFreqByCell
    * @pdOid 8f2ac7a5-d5dc-48bb-a938-563e6694fc77 */
   public void setNumFreqByCell(int newNumFreqByCell) {
      numFreqByCell = newNumFreqByCell;
   }
   
   /** @pdOid 5de5d598-96fb-492c-8f17-65ea82ebb63a */
   public int getFactorR() {
      return factorR;
   }
   
   /** @param newFactorR
    * @pdOid 86c9046b-89d6-4bfc-8579-5dadc791d3c4 */
   public void setFactorR(int newFactorR) {
      factorR = newFactorR;
   }
   
   /** @pdOid 65c76d12-2d4c-4b37-97bd-dd06e531ebf1 */
   public double getSir() {
      return sir;
   }
   
   /** @param newSir
    * @pdOid 2c5bc619-c4b3-4d56-ab52-9e8180899d85 */
   public void setSir(double newSir) {
      sir = newSir;
   }
   
   /** @pdOid 7120eac6-c411-4e30-8f93-eff59daf40c2 */
   public int getNumChannel() {
      return numChannel;
   }
   
   /** @param newNumChannel
    * @pdOid ddf42427-bdc7-41fa-a95e-e97893ac48c2 */
   public void setNumChannel(int newNumChannel) {
      numChannel = newNumChannel;
   }
   
   /** @pdOid 700bd1fb-22a2-42fa-ad32-dc8d99cb9841 */
   public double getArea() {
      return area;
   }
   
   /** @param newArea
    * @pdOid a5647754-4922-444c-9995-2e80609f901c */
   public void setArea(double newArea) {
      area = newArea;
   }
   
   /** @pdOid 153e727b-fec2-4872-a5de-a5f65729e904 */
   public double getRadius() {
      return radius;
   }
   
   /** @param newRadius
    * @pdOid 6d68dfa4-b4de-4114-92d1-77792d879ae6 */
   public void setRadius(double newRadius) {
      radius = newRadius;
   }
   
   /** @pdOid 68cb121d-a61a-4ba3-a7ce-c835a5e293b7 */
   public int getNumCell() {
      return numCell;
   }
   
   /** @param newNumCell
    * @pdOid 652e6597-d0cc-49b9-b6ef-0320b283a743 */
   public void setNumCell(int newNumCell) {
      numCell = newNumCell;
   }
   
   /** @pdOid 203c3857-91ef-4a34-a888-e686fe2ea061 */
   public double getInterSiteDist() {
      return interSiteDist;
   }
   
   /** @param newInterSiteDist
    * @pdOid 703e858c-0411-4ea4-aafe-a349d69e8ef0 */
   public void setInterSiteDist(double newInterSiteDist) {
      interSiteDist = newInterSiteDist;
   }
   
   /** @pdOid 19643d7d-6d50-4b1c-9050-a87ae69d3276 */
   public double getDistanceR() {
      return distanceR;
   }
   
   /** @param newDistanceR
    * @pdOid e6a0ec5c-6872-4658-bf2e-a6ff88c3d34f */
   public void setDistanceR(double newDistanceR) {
      distanceR = newDistanceR;
   }

}