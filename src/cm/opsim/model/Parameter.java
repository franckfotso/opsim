package cm.opsim.model;
/***********************************************************************
 * Module:  Parameter.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class Parameter
 ***********************************************************************/

import java.util.*;

import cm.opsim.dao.DAO;
import cm.opsim.dao.DAOFactory;


public class Parameter extends AbstractModel{
   /** @pdOid 0b97aa31-e418-4697-bca2-530f3ee53950 */
   protected int id = 0;
   /** @pdOid c967d91d-d448-4695-836a-a51da3429cb5 */
   protected Date createdDate;
   /** @pdOid 317a6f1f-ae98-4cb5-b902-ad075d925d82 */
   protected Date updatedDate;
   
   /** @pdRoleInfo migr=no name=McsTechnique assc=utilise mult=1..1 */
   public McsTechnique mcsTechnique; 
   
   private GenParameter genParam;
   private EnbParameter enbParam;
   private UeParameter ueParam;
   private EnvParameter envParam;
   private ZoneParameter zoneParam;   
   private FinanParameter finanParam;
   private OtherParameter otherParam;
   private AntennaParameter antParam;
   private CustomerParameter custParam; 
   private FreqParameter freqParam;
   private CapParameter capParam;
   private CapexParameter capexParam;
   private OpexParameter opexParam;
 
 /**
 * @param id
 * @param createdDate
 * @param updatedDate
 * @param mcsTechnique
 * @param genParam
 * @param enbParam
 * @param ueParam
 * @param envParam
 * @param zoneParam
 * @param finanParam
 * @param otherParam
 * @param antParam
 * @param custParam
 * @param freqParam
 * @param capParam
 * @param capexParam
 * @param opexParam
 */
public Parameter(int id, Date createdDate, Date updatedDate,
		McsTechnique mcsTechnique, GenParameter genParam,
		EnbParameter enbParam, UeParameter ueParam, EnvParameter envParam,
		ZoneParameter zoneParam, FinanParameter finanParam,
		OtherParameter otherParam, AntennaParameter antParam,
		CustomerParameter custParam, FreqParameter freqParam,
		CapParameter capParam, CapexParameter capexParam,
		OpexParameter opexParam) {
	super();
	this.id = id;
	this.createdDate = createdDate;
	this.updatedDate = updatedDate;
	this.mcsTechnique = mcsTechnique;
	this.genParam = genParam;
	this.enbParam = enbParam;
	this.ueParam = ueParam;
	this.envParam = envParam;
	this.zoneParam = zoneParam;
	this.finanParam = finanParam;
	this.otherParam = otherParam;
	this.antParam = antParam;
	this.custParam = custParam;
	this.freqParam = freqParam;
	this.capParam = capParam;
	this.capexParam = capexParam;
	this.opexParam = opexParam;
}

	public Parameter(){
		super();
		DAO< McsTechnique>mcsDAO =DAOFactory.getMcsTechniqueDAO();
		
		this.createdDate = new Date();
		this.updatedDate = new Date();
		this.mcsTechnique = mcsDAO.find(1);	
		
		this.genParam = new GenParameter();
		this.enbParam = new EnbParameter();
		this.ueParam = new UeParameter();
		this.envParam = new EnvParameter();
		this.zoneParam = new ZoneParameter();
		this.finanParam = new FinanParameter();
		this.otherParam = new OtherParameter();
		this.antParam = new AntennaParameter();
		this.custParam = new CustomerParameter();
		this.freqParam = new FreqParameter();	
		this.capParam = new CapParameter();
		this.capexParam = new CapexParameter();
		this.opexParam = new OpexParameter();
	}	

	public GenParameter getGenParam() {
		return genParam;
	}

	public void setGenParam(GenParameter genParam) {
		this.genParam = genParam;
	}

	public EnbParameter getEnbParam() {
		return enbParam;
	}

	public void setEnbParam(EnbParameter enbParam) {
		this.enbParam = enbParam;
	}

	public UeParameter getUeParam() {
		return ueParam;
	}

	public void setUeParam(UeParameter ueParam) {
		this.ueParam = ueParam;
	}

	public EnvParameter getEnvParam() {
		return envParam;
	}

	public void setEnvParam(EnvParameter envParam) {
		this.envParam = envParam;
	}

	public ZoneParameter getZoneParam() {
		return zoneParam;
	}

	public void setZoneParam(ZoneParameter zoneParam) {
		this.zoneParam = zoneParam;
	}

	public FinanParameter getFinanParam() {
		return finanParam;
	}

	public void setFinanParam(FinanParameter finanParam) {
		this.finanParam = finanParam;
	}

	public OtherParameter getOtherParam() {
		return otherParam;
	}

	public void setOtherParam(OtherParameter otherParam) {
		this.otherParam = otherParam;
	}

	public AntennaParameter getAntParam() {
		return antParam;
	}

	public void setAntParam(AntennaParameter antParam) {
		this.antParam = antParam;
	}

	public CustomerParameter getCustParam() {
		return custParam;
	}

	public void setCustParam(CustomerParameter custParam) {
		this.custParam = custParam;
	}

	public FreqParameter getFreqParam() {
		return freqParam;
	}

	public void setFreqParam(FreqParameter freqParam) {
		this.freqParam = freqParam;
	}
	
	public CapParameter getCapParam() {
		return capParam;
	}

	public void setCapParam(CapParameter capParam) {
		this.capParam = capParam;
	}
	

public CapexParameter getCapexParam() {
		return capexParam;
	}

	public void setCapexParam(CapexParameter capexParam) {
		this.capexParam = capexParam;
	}

	public OpexParameter getOpexParam() {
		return opexParam;
	}

	public void setOpexParam(OpexParameter opexParam) {
		this.opexParam = opexParam;
	}

/** @pdOid 7513b742-9910-41bf-ada5-4385f0111bbb */
   public int getId() {
      return id;
   }
   
   /** @param newId
    * @pdOid 6160de2d-3b9a-4459-ba47-ce5047bcb125 */
   public void setId(int id) {
      id = id;
      this.genParam.setId(id);
      this.enbParam.setId(id);
      this.ueParam.setId(id);
      this.envParam.setId(id);
      this.zoneParam.setId(id);
      this.finanParam.setId(id);
      this.otherParam.setId(id);
      this.antParam.setId(id);
      this.custParam.setId(id);
      this.freqParam.setId(id);
      this.capParam.setId(id);
      this.capexParam.setId(id);
      this.opexParam.setId(id);
   }
   
   /** @pdOid 7ad30177-efb4-44f9-a42d-00cf37167910 */
   public Date getCreatedDate() {
      return createdDate;
   }
   
   /** @param newCreatedDate
    * @pdOid ddb7d2d6-7436-4991-a31f-060750e22147 */
   public void setCreatedDate(Date newCreatedDate) {
      createdDate = newCreatedDate;
   }
   
   /** @pdOid 98764cb0-cd88-4fd8-b270-276c77e9e82c */
   public Date getUpdatedDate() {
      return updatedDate;
   }
   
   /** @param newUpdatedDate
    * @pdOid 8ed07b20-72ce-4356-8aa4-c45cf344f18e */
   public void setUpdatedDate(Date newUpdatedDate) {
      updatedDate = newUpdatedDate;
   }

	/**
	 * @return the mcsTechnique
	 */
	public McsTechnique getMcsTechnique() {
		return mcsTechnique;
	}
	
	/**
	 * @param mcsTechnique the mcsTechnique to set
	 */
	public void setMcsTechnique(McsTechnique mcsTechnique) {
		this.mcsTechnique = mcsTechnique;
	}
	
}