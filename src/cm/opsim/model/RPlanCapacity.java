package cm.opsim.model;
/***********************************************************************
 * Module:  RPlanCapacity.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class RPlanCapacity
 ***********************************************************************/

import java.util.*;

/** @pdOid ae08aa86-bfd3-49a7-97e9-27480e2d1151 */
public class RPlanCapacity extends AbstractModel{
	private int id = 0;
   /** @pdOid c9ee4d9c-1eac-4432-98a5-e89c10727747 */
   private double cellCapacityUL;
   /** @pdOid dec3c19a-3b2b-4378-8d92-7975809b3ef8 */
   private double cellCapacityDL;
   /** @pdOid 3afe7829-7b32-4446-949d-5f44e3b7a889 */
   private double trafficUL;
   /** @pdOid 0a7ce24b-bd28-4394-894b-208b9c1fbda4 */
   private double trafficDL;
   /** @pdOid b64e1f03-78ad-4844-8d12-ffa5013cec77 */
   private int numCustomerByCellUL;
   /** @pdOid 8034f095-4d6e-47c2-bd07-2863de179cd0 */
   private int numCustomerByCellDL;
   /** @pdOid 6f3f1f57-0569-46c9-862f-34f4e748b6a8 */
   private int numENBUl;
   /** @pdOid 86ed4b72-9b62-4db5-80e5-b6dc3f205c26 */
   private int numENBDl;
   /** @pdOid 199548dd-bf72-47a7-97d6-90cae8a73af6 */
   private double cellRadius;
   /** @pdOid 2889a647-0f24-4b04-bfdc-ec9c1cd4a1f2 */
   private double interSiteDist;
   
   /**
 * 
 */
public RPlanCapacity() {
	super();
	// TODO Auto-generated constructor stub
}

/**
 * @param id
 * @param cellCapacityUL
 * @param cellCapacityDL
 * @param trafficUL
 * @param trafficDL
 * @param numCustomerByCellUL
 * @param numCustomerByCellDL
 * @param numENBUl
 * @param numENBDl
 * @param cellRadius
 * @param interSiteDist
 */
public RPlanCapacity(int id, double cellCapacityUL, double cellCapacityDL,
		double trafficUL, double trafficDL, int numCustomerByCellUL,
		int numCustomerByCellDL, int numENBUl, int numENBDl, double cellRadius,
		double interSiteDist) {
	super();
	this.id = id;
	this.cellCapacityUL = cellCapacityUL;
	this.cellCapacityDL = cellCapacityDL;
	this.trafficUL = trafficUL;
	this.trafficDL = trafficDL;
	this.numCustomerByCellUL = numCustomerByCellUL;
	this.numCustomerByCellDL = numCustomerByCellDL;
	this.numENBUl = numENBUl;
	this.numENBDl = numENBDl;
	this.cellRadius = cellRadius;
	this.interSiteDist = interSiteDist;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

/** @pdOid a8a85b1c-b109-43fa-a6c0-a3664f751045 */
   public double getCellCapacityUL() {
      return cellCapacityUL;
   }
   
   /** @param newCellCapacityUL
    * @pdOid 3a7b8dc0-cfcf-42fd-8710-5282c4065b36 */
   public void setCellCapacityUL(double newCellCapacityUL) {
      cellCapacityUL = newCellCapacityUL;
   }
   
   /** @pdOid dfee5ffe-af16-44e5-990a-25399614645c */
   public double getCellCapacityDL() {
      return cellCapacityDL;
   }
   
   /** @param newCellCapacityDL
    * @pdOid 9bf22d9e-1274-4609-aed0-4c34bfa629a6 */
   public void setCellCapacityDL(double newCellCapacityDL) {
      cellCapacityDL = newCellCapacityDL;
   }
   
   /** @pdOid 1d3907c7-1ebe-476d-b1c1-fedad82cc1e9 */
   public double getTrafficUL() {
      return trafficUL;
   }
   
   /** @param newTrafficUL
    * @pdOid 8b29cd48-9a44-4bf4-bf88-7131d2e75d7d */
   public void setTrafficUL(double newTrafficUL) {
      trafficUL = newTrafficUL;
   }
   
   /** @pdOid 00009585-3095-41ce-9c0a-075a2375e6a3 */
   public double getTrafficDL() {
      return trafficDL;
   }
   
   /** @param newTrafficDL
    * @pdOid 69f14cd3-413c-495d-a487-7737a9a7a0ad */
   public void setTrafficDL(double newTrafficDL) {
      trafficDL = newTrafficDL;
   }
   
   /** @pdOid 885ae7b4-3c1e-4c8f-81e2-a66742685951 */
   public int getNumCustomerByCellUL() {
      return numCustomerByCellUL;
   }
   
   /** @param newNumCustomerByCellUL
    * @pdOid 7f68d8e0-6345-4514-8c32-7f3e5c8b9182 */
   public void setNumCustomerByCellUL(int newNumCustomerByCellUL) {
      numCustomerByCellUL = newNumCustomerByCellUL;
   }
   
   /** @pdOid e7f5b8ec-8795-4293-aff1-10f182f29554 */
   public int getNumCustomerByCellDL() {
      return numCustomerByCellDL;
   }
   
   /** @param newNumCustomerByCellDL
    * @pdOid 0363d22a-c7ef-4944-8797-d33f47d5fb41 */
   public void setNumCustomerByCellDL(int newNumCustomerByCellDL) {
      numCustomerByCellDL = newNumCustomerByCellDL;
   }
   
   /** @pdOid ba37023a-d6a1-4d5a-babb-e3d652ca7d29 */
   public int getNumENBUl() {
      return numENBUl;
   }
   
   /** @param newNumENBUl
    * @pdOid 64efdc71-18de-43f2-8964-4119c22795e4 */
   public void setNumENBUl(int newNumENBUl) {
      numENBUl = newNumENBUl;
   }
   
   /** @pdOid 8965404a-a21d-45fb-a770-d953a9b9ef29 */
   public int getNumENBDl() {
      return numENBDl;
   }
   
   /** @param newNumENBDl
    * @pdOid 176c2ec4-fcb4-4d0b-9a30-32717256c290 */
   public void setNumENBDl(int newNumENBDl) {
      numENBDl = newNumENBDl;
   }
   
   /** @pdOid c0cfcb31-ace4-401d-85be-ddc7466c3ec7 */
   public double getCellRadius() {
      return cellRadius;
   }
   
   /** @param newCellRadius
    * @pdOid 1ff6206a-d7e1-4433-918c-3930d7029b8c */
   public void setCellRadius(double newCellRadius) {
      cellRadius = newCellRadius;
   }
   
   /** @pdOid 341e8f7b-8c62-4df2-b505-37484554f900 */
   public double getInterSiteDist() {
      return interSiteDist;
   }
   
   /** @param newInterSiteDist
    * @pdOid 3b44a5b6-e9bb-463f-a9e5-9f24f4764123 */
   public void setInterSiteDist(double newInterSiteDist) {
      interSiteDist = newInterSiteDist;
   }

}