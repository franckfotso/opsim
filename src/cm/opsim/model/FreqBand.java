package cm.opsim.model;

/***********************************************************************
 * Module:  FreqBand.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class FreqBand
 ***********************************************************************/

import java.util.*;

/** @pdOid 8f9ce17e-05fb-4f56-84e0-aecc61cfbe0e */
public class FreqBand  extends AbstractModel{
   /** @pdOid 41bca03f-2012-4369-954a-85ae7fb405d1 */
   private int id = 0;
   private int bandNum;
   private String name;
   private String ul;
   private String dl;
   private String allocation;   
   private int bandWidth;
   private String duplexingMethod; 
   private Date createdDate;
   private Date updatedDate;

	
	public FreqBand(){
		super();
	}

	/**
	 * @param id
	 * @param bandNum
	 * @param name
	 * @param ul
	 * @param dl
	 * @param allocation
	 * @param bandWidth
	 * @param duplexingMethod
	 * @param createdDate
	 * @param updatedDate
	 */
	public FreqBand(int id, int bandNum, String name, String ul, String dl,
			String allocation, int bandWidth, String duplexingMethod,
			Date createdDate, Date updatedDate) {
		super();
		this.id = id;
		this.bandNum = bandNum;
		this.name = name;
		this.ul = ul;
		this.dl = dl;
		this.allocation = allocation;
		this.bandWidth = bandWidth;
		this.duplexingMethod = duplexingMethod;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBandNum() {
		return bandNum;
	}

	public void setBandNum(int bandNum) {
		this.bandNum = bandNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUl() {
		return ul;
	}

	public void setUl(String ul) {
		this.ul = ul;
	}

	public String getDl() {
		return dl;
	}

	public void setDl(String dl) {
		this.dl = dl;
	}

	public String getAllocation() {
		return allocation;
	}

	public void setAllocation(String allocation) {
		this.allocation = allocation;
	}

	public int getBandWidth() {
		return bandWidth;
	}

	public void setBandWidth(int bandWidth) {
		this.bandWidth = bandWidth;
	}

	public String getDuplexingMethod() {
		return duplexingMethod;
	}

	public void setDuplexingMethod(String duplexingMethod) {
		this.duplexingMethod = duplexingMethod;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
	
}