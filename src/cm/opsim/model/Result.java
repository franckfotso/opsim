package cm.opsim.model;
/***********************************************************************
 * Module:  Result.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class Result
 ***********************************************************************/

import java.util.*;

/** @pdOid 55fef066-aba4-4326-846d-5e72c43a565e */
public class Result  extends AbstractModel{
   /** @pdOid c47b9599-9a44-44e9-a271-6c0a12fcaec2 */
   protected int id = 0;
   /** @pdOid 8b8edcd0-1055-4e4e-a23e-bd9b1d4a1cfb */
   protected Date createdDate;
   /** @pdOid e4a3fe7b-e56a-4a0a-932b-614d89e3c78d */
   protected Date updatedDate;
   
   private RPlanCapacity resCap;
   private RPlanCoverage resCov;
   private RPlanFrequency resFreq;
   private RDimensionning resDim;
   private RCapex rcapex;
   private ArrayList<ROpex> listROpex;


/**
 * @param id
 * @param createdDate
 * @param updatedDate
 * @param resCap
 * @param resCov
 * @param resFreq
 * @param resDim
 * @param rcapex
 * @param listROpex
 */
public Result(int id, Date createdDate, Date updatedDate, RPlanCapacity resCap,
		RPlanCoverage resCov, RPlanFrequency resFreq, RDimensionning resDim,
		RCapex rcapex, ArrayList<ROpex> listROpex) {
	super();
	this.id = id;
	this.createdDate = createdDate;
	this.updatedDate = updatedDate;
	this.resCap = resCap;
	this.resCov = resCov;
	this.resFreq = resFreq;
	this.resDim = resDim;
	this.rcapex = rcapex;
	this.listROpex = listROpex;
}

/**
 * 
 */
public Result() {
	super();
	this.createdDate = new Date();
	this.updatedDate = new Date();
	this.resCap = new RPlanCapacity();
	this.resCov = new RPlanCoverage();
	this.resFreq = new RPlanFrequency();
	this.resDim = new RDimensionning() ;
	this.rcapex = new RCapex();
	this.listROpex = new ArrayList<ROpex>();
}

public RPlanCapacity getResCap() {
	return resCap;
}

public void setResCap(RPlanCapacity resCap) {
	this.resCap = resCap;
}

public RPlanCoverage getResCov() {
	return resCov;
}

public void setResCov(RPlanCoverage resCov) {
	this.resCov = resCov;
}

public RPlanFrequency getResFreq() {
	return resFreq;
}

public void setResFreq(RPlanFrequency resFreq) {
	this.resFreq = resFreq;
}

public RDimensionning getResDim() {
	return resDim;
}

public void setResDim(RDimensionning resDim) {
	this.resDim = resDim;
}

/** @pdOid c1a3e4ba-8ac8-459e-b4e7-10895ee56416 */
   public int getId() {
      return id;
   }
   
   /** @param newId
    * @pdOid 4273a4e0-f8a3-4a3b-b712-0ac7f34ca7ce */
   public void setId(int id) {
      id = id;
      this.resCap.setId(id);
  	  this.resCov.setId(id);
  	  this.resFreq.setId(id);
  	  this.resDim.setId(id);
  	  this.rcapex.setId(id);
   }
   
   /** @pdOid 9fc451cf-6f65-4d7a-9f49-2db2fd8e78cd */
   public Date getCreatedDate() {
      return createdDate;
   }
   
   /** @param newCreatedDate
    * @pdOid 1ccac3f6-aecb-46fc-990d-f04f693f7e38 */
   public void setCreatedDate(Date newCreatedDate) {
      createdDate = newCreatedDate;
   }
   
   /** @pdOid 030752f0-aabf-4a30-bf42-fd1789d3955c */
   public Date getUpdatedDate() {
      return updatedDate;
   }
   
   /** @param newUpdatedDate
    * @pdOid c6841089-75dc-4fc0-8c59-3258a076e774 */
   public void setUpdatedDate(Date newUpdatedDate) {
      updatedDate = newUpdatedDate;
   }

public RCapex getRcapex() {
	return rcapex;
}

public void setRcapex(RCapex rcapex) {
	this.rcapex = rcapex;
}

public ArrayList<ROpex> getListROpex() {
	return listROpex;
}

public void setListROpex(ArrayList<ROpex> listROpex) {
	this.listROpex = listROpex;
}

   
   

}