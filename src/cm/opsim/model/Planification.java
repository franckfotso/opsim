package cm.opsim.model;
/***********************************************************************
 * Module:  Planification.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class Planification
 ***********************************************************************/

import java.util.*;

/** @pdOid 8c38b0a0-7fda-452b-ac6d-33159db314fe */
public class Planification extends AbstractModel{
   /** @pdOid a9f322bd-62a5-41ca-a398-520952060d3a */
   private int id = 0;
   /** @pdOid 1deb580a-2054-4dc4-8380-2a8cad45260f */
   private Date createdDate;
   /** @pdOid 327d945e-0403-4eac-9bee-bd9c2a2429a8 */
   private Date updatedDate;
   
   /** @pdRoleInfo migr=no name=Result assc=aboutit mult=1..1 */
   private Result result;   
   
   private CoveragePlanif covPlanif;
   private CapacityPlanif capPlanif;
   private FrequencyPlanif freqPlanif;
   private CapexOpexPlanif coPlanif;
  
	/**
	 * @param id
	 * @param createdDate
	 * @param updatedDate
	 * @param result
	 * @param covPlanif
	 * @param capPlanif
	 * @param freqPlanif
	 * @param coPlanif
	 */
	public Planification(int id, Date createdDate, Date updatedDate, Result result,
			CoveragePlanif covPlanif, CapacityPlanif capPlanif,
			FrequencyPlanif freqPlanif, CapexOpexPlanif coPlanif) {
		super();
		this.id = id;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.result = result;
		this.covPlanif = covPlanif;
		this.capPlanif = capPlanif;
		this.freqPlanif = freqPlanif;
		this.coPlanif = coPlanif;
	}

	public Planification(){
		super();
		this.createdDate = new Date();
		this.updatedDate = new Date();
		this.result = new Result();
		this.covPlanif = new CoveragePlanif(this.id,"INIT");
		this.capPlanif = new CapacityPlanif(this.id,"INIT");
		this.freqPlanif = new FrequencyPlanif(this.id,"INIT");
		this.coPlanif = new CapexOpexPlanif(this.id,"INIT");
	}

	public CoveragePlanif getCovPlanif() {
		return covPlanif;
	}

	public void setCovPlanif(CoveragePlanif covPlanif) {
		this.covPlanif = covPlanif;
	}

	public CapacityPlanif getCapPlanif() {
		return capPlanif;
	}

	public void setCapPlanif(CapacityPlanif capPlanif) {
		this.capPlanif = capPlanif;
	}

	public FrequencyPlanif getFreqPlanif() {
		return freqPlanif;
	}

	public void setFreqPlanif(FrequencyPlanif freqPlanif) {
		this.freqPlanif = freqPlanif;
	}

	public CapexOpexPlanif getCoPlanif() {
		return coPlanif;
	}

	public void setCoPlanif(CapexOpexPlanif coPlanif) {
		this.coPlanif = coPlanif;
	}

/** @pdOid 98966487-943d-46f7-b48c-38db449d08e2 */
   public int getId() {
      return id;
   }
   
   /** @param newId
    * @pdOid dbd88912-279f-4c7b-bb03-ffc057b9de1b */
   public void setId(int id) {
      id = id;
      this.covPlanif.setId(id);
      this.capPlanif.setId(id);
      this.freqPlanif.setId(id);
      this.coPlanif.setId(id);
   }
   
   /** @pdOid 316ca9bc-c49b-42ea-b82e-a1df80cfe451 */
   public Date getCreatedDate() {
      return createdDate;
   }
   
   /** @param newCreatedDate
    * @pdOid 95c6eee1-8bcc-488f-b85d-14362d828287 */
   public void setCreatedDate(Date newCreatedDate) {
      createdDate = newCreatedDate;
   }
   
   /** @pdOid 768e4aa1-59e5-48d2-93fb-4d43d9b4a3a8 */
   public Date getUpdatedDate() {
      return updatedDate;
   }
   
   /** @param newUpdatedDate
    * @pdOid 88a06e99-d0c4-4a4e-954e-aa1a83d79a90 */
   public void setUpdatedDate(Date newUpdatedDate) {
      updatedDate = newUpdatedDate;
   }

/**
 * @return the result
 */
public Result getResult() {
	return result;
}

/**
 * @param result the result to set
 */
public void setResult(Result result) {
	this.result = result;
}
   
   
}