package cm.opsim.model;
/***********************************************************************
 * Module:  SystemLog.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class SystemLog
 ***********************************************************************/

import java.util.*;

/** @pdOid 17fc097d-43a1-4062-a311-846ebe0d6656 */
public class SystemLog extends AbstractModel{
   /** @pdOid a210e992-5ef3-4d83-9394-dcbacbb040d6 */
   private int id = 0;
   
   private int proj_id;
   
	
	/**
 * @param id
 * @param proj
 */
public SystemLog(int id, int proj_id) {
	super();
	this.id = id;
	this.proj_id = proj_id;
}

	public SystemLog(){
		super();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public int getProj_id() {
		return proj_id;
	}

	public void setProj_id(int proj_id) {
		this.proj_id = proj_id;
	}
	
	

}