package cm.opsim.model;
/***********************************************************************
 * Module:  TrafficModel.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class TrafficModel
 ***********************************************************************/

import java.util.*;

/** @pdOid d31a5ed6-6653-4294-bdcc-e4a26b3a53a9 */
public class TrafficModel extends AbstractModel{
   /** @pdOid 7735e318-b474-4651-9d40-edead0220a4a */
   private int id = 0;
   private int CustParam_id;
   private String class_serv;
   private double bw_ul;
   private double bw_dl;
   private double poh;
   private double tp;
   
   /**
	 * 
	 */
	public TrafficModel() {
		super();
	}	

	/**
 * @param id
 * @param custParam_id
 * @param class_serv
 * @param bw_ul
 * @param bw_dl
 * @param poh
 * @param tp
 */
public TrafficModel(int id, int custParam_id, String class_serv, double bw_ul,
		double bw_dl, double poh, double tp) {
	super();
	this.id = id;
	CustParam_id = custParam_id;
	this.class_serv = class_serv;
	this.bw_ul = bw_ul;
	this.bw_dl = bw_dl;
	this.poh = poh;
	this.tp = tp;
}

	public int getCustParam_id() {
		return CustParam_id;
	}

	public void setCustParam_id(int custParam_id) {
		CustParam_id = custParam_id;
	}

	public String getClass_serv() {
		return class_serv;
	}

	public void setClass_serv(String class_serv) {
		this.class_serv = class_serv;
	}

	public double getBw_ul() {
		return bw_ul;
	}

	public void setBw_ul(double bw_ul) {
		this.bw_ul = bw_ul;
	}

	public double getBw_dl() {
		return bw_dl;
	}

	public void setBw_dl(double bw_dl) {
		this.bw_dl = bw_dl;
	}

	public double getPoh() {
		return poh;
	}

	public void setPoh(double poh) {
		this.poh = poh;
	}

	public double getTp() {
		return tp;
	}

	public void setTp(double tp) {
		this.tp = tp;
	}

/** @pdOid 06b108aa-9ac4-4a0b-b843-eec21d865130 */
   public int getId() {
      return id;
   }
   
   /** @param newId
    * @pdOid 4fdabe21-ac0b-44d6-bc6e-604135d33335 */
   public void setId(int newId) {
      id = newId;
   }

}