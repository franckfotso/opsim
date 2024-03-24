package cm.opsim.model;
/***********************************************************************
 * Module:  ZoneParameter.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class ZoneParameter
 ***********************************************************************/

import java.util.*;

/** @pdOid 0d5cc2d2-6bcd-42ef-acc3-fefb2d1174b6 */
public class ZoneParameter extends AbstractModel{
	private int id = 0;
   /** @pdOid ddc75f62-a038-4466-83af-d31340c33e13 */
   private double penetrationRate = 0.00;
   /** @pdOid e646cae5-bfee-4474-8658-948dd170d850 */
   private double area = 0.00;
   /** @pdOid 4c53ce20-8ea2-407b-a974-93aed9f41a5a */
   private double density = 0.00;
   /** @pdRoleInfo migr=no name=TargetZone assc=definit mult=1..1 */
   public TargetZone targetZone = null;
   /**
 * 
 */
public ZoneParameter() {
	super();
	this.targetZone = new TargetZone();
	
	// TODO Auto-generated constructor stub
}

/**
 * @param id
 * @param penetrationRate
 * @param area
 * @param density
 * @param targetZone
 */
public ZoneParameter(int id, double penetrationRate, double area,
		double density, TargetZone targetZone) {
	super();
	this.id = id;
	this.penetrationRate = penetrationRate;
	this.area = area;
	this.density = density;
	this.targetZone = targetZone;
}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public TargetZone getTargetZone() {
		return targetZone;
	}
	
	public void setTargetZone(TargetZone targetZone) {
		this.targetZone = targetZone;
	}
   
   /** @pdOid 502c2b8e-cd22-47bb-96e8-0fbf156f182a */
   public double getPenetrationRate() {
      return penetrationRate;
   }
   
   /** @param newPenetrationRate
    * @pdOid dd2edd43-311f-4e8b-bbce-6a8863ed5687 */
   public void setPenetrationRate(double newPenetrationRate) {
      penetrationRate = newPenetrationRate;
   }
   
   /** @pdOid 88c87371-9bce-4ae1-b28e-94b1a4bacffc */
   public double getArea() {
      return area;
   }
   
   /** @param newArea
    * @pdOid 442bc2bd-f0a6-4e8d-a790-9e4ca271098c */
   public void setArea(double newArea) {
      area = newArea;
   }
   
   /** @pdOid d450f100-afbc-4a42-9aa3-72244ff0d0b9 */
   public double getDensity() {
      return density;
   }
   
   /** @param newDensity
    * @pdOid f8a8fe19-a623-4c1e-ada1-2b9ec8c47ce1 */
   public void setDensity(double newDensity) {
      density = newDensity;
   }

}