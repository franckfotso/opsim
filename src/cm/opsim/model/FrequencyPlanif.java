package cm.opsim.model;
/***********************************************************************
 * Module:  FrequencyPlanif.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class FrequencyPlanif
 ***********************************************************************/

import java.util.*;

/** @pdOid 45c52186-5c4c-4c26-8545-da243ab03683 */
public class FrequencyPlanif extends AbstractModel{
	
	private int id = 0;
	private String state = "INIT";
	
	/**
	 * @param id
	 * @param state
	 */
	public FrequencyPlanif(int id, String state) {
		super();
		this.id = id;
		this.state = state;
	}

	/**
	 * 
	 */
	public FrequencyPlanif() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}