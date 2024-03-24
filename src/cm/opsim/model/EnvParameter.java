package cm.opsim.model;
/***********************************************************************
 * Module:  EnvParameter.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class EnvParameter
 ***********************************************************************/

import java.util.*;

/** @pdOid 9c1137da-ef1f-4e17-8c06-71b9a2e34ac1 */
public class EnvParameter extends AbstractModel{
	private int id = 0;
   /** @pdOid 8dd62ffe-23b3-4a1d-91d0-a6abf883a5e4 */
   private double marginInterf;
   /** @pdOid 9a1a60af-0572-4203-ba2f-7809028e1f11 */
   private double mhag;
   private double cco;
   
   /** @pdRoleInfo migr=no name=PmParameter assc=estLier mult=1..1 */
   public PmParameter pmParameter;
   

	
	/**
	 * @param id
	 * @param marginInterf
	 * @param mhag
	 * @param cco
	 * @param pmParameter
	 */
	public EnvParameter(int id, double marginInterf, double mhag, double cco,
			PmParameter pmParameter) {
		super();
		this.id = id;
		this.marginInterf = marginInterf;
		this.mhag = mhag;
		this.cco = cco;
		this.pmParameter = pmParameter;
	}

	/**
	 * 
	 */
	public EnvParameter() {
		super();
		this.marginInterf = 0.00;
		this.mhag = 0.00;
		this.cco = 0.00;
		this.pmParameter = new PmParameter();
	}
	
	public double getCco() {
		return cco;
	}

	public void setCco(double cco) {
		this.cco = cco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PmParameter getPmParameter() {
		return pmParameter;
	}

	public void setPmParameter(PmParameter pmParameter) {
		this.pmParameter = pmParameter;
	}

/** @pdOid 3501725e-88c3-4a4d-9212-f79cc7ebb00d */
   public double getMarginInterf() {
      return marginInterf;
   }
   
   /** @param newMarginInterf
    * @pdOid 835d5018-554d-4e27-a9f2-144886642aa3 */
   public void setMarginInterf(double newMarginInterf) {
      marginInterf = newMarginInterf;
   }
   
   /** @pdOid 94cb6f6d-5a7a-48ca-b6ac-7d4e81b8893e */
   public double getMhag() {
      return mhag;
   }
   
   /** @param newMhag
    * @pdOid 18cd27db-949f-499e-8d04-8c09d3923a57 */
   public void setMhag(double newMhag) {
      mhag = newMhag;
   }

}