package cm.opsim.model;
/***********************************************************************
 * Module:  CustomerParameter.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class CustomerParameter
 ***********************************************************************/

import java.util.ArrayList;

/** @pdOid 5db49cdd-1f1e-4248-8bf2-98199b6f2b51 */
public class CustomerParameter extends AbstractModel{
	private int id = 0;     
	private double marPart;
	private double penRate;
	private double growthRate;
	private int invTime;
	private ArrayList<Subscription> listSub;
	private ArrayList<TrafficModel> listTM;
   
   /**
	 * 
	 */
	public CustomerParameter() {
		super();
		this.listSub = new ArrayList<Subscription>();
		this.listTM = new ArrayList<TrafficModel>();
	}

	/**
	 * @param id
	 * @param marPart
	 * @param penRate
	 * @param growthRate
	 * @param invTime
	 * @param listSub
	 * @param listTM
	 */
	public CustomerParameter(int id, double marPart, double penRate,
			double growthRate, int invTime, ArrayList<Subscription> listSub,
			ArrayList<TrafficModel> listTM) {
		super();
		this.id = id;
		this.marPart = marPart;
		this.penRate = penRate;
		this.growthRate = growthRate;
		this.invTime = invTime;
		this.listSub = listSub;
		this.listTM = listTM;
	}
	
	public ArrayList<TrafficModel> getListTM() {
		return listTM;
	}

	public void setListTM(ArrayList<TrafficModel> listTM) {
		this.listTM = listTM;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Subscription> getListSub() {
		return listSub;
	}

	public void setListSub(ArrayList<Subscription> listSub) {
		this.listSub = listSub;
	}


	public double getMarPart() {
		return marPart;
	}


	public void setMarPart(double marPart) {
		this.marPart = marPart;
	}


	public double getPenRate() {
		return penRate;
	}


	public void setPenRate(double penRate) {
		this.penRate = penRate;
	}


	public double getGrowthRate() {
		return growthRate;
	}


	public void setGrowthRate(double growthRate) {
		this.growthRate = growthRate;
	}


	public int getInvTime() {
		return invTime;
	}


	public void setInvTime(int invTime) {
		this.invTime = invTime;
	}
	
}