package cm.opsim.model;
/***********************************************************************
 * Module:  FinanParameter.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class FinanParameter
 ***********************************************************************/

import java.util.*;

/** Financial budget parameter
 * 
 * @pdOid 37b09fb3-2b98-447d-bcce-8041fd9aaf8c */
public class FinanParameter extends AbstractModel{
	private int id = 0;

	/**
	 * 
	 */
	public FinanParameter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 */
	public FinanParameter(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}