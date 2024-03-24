/**
 * 
 */
package cm.opsim.model;

import java.util.ArrayList;

/**
 * @author Romuald FOTSO
 *
 */
public class OpexParameter extends AbstractModel{

	private int id = 0;
	private double nbResident;
	private double percentCust;
	private double growingRate;
	private ArrayList<PrevIncomings> listPrevIncomings;
	private double marketing;
	private double interWeb;
	private double adminFGen;
	private double assurance;
	private double locaSiteRadio;
	private double locaFreq;
	private double enerElec;
	private double locaInfra;
	private double formaPerso;
	private double suiviRx;
	private double factAb;
	private double txImpot;
	
	/**
	 * 
	 */
	public OpexParameter() {
		super();
		this.listPrevIncomings = new ArrayList<PrevIncomings>();
	}
	

	/**
	 * @param id
	 * @param nbResident
	 * @param percentCust
	 * @param growingRate
	 * @param listPrevIncomings
	 * @param marketing
	 * @param interWeb
	 * @param adminFGen
	 * @param assurance
	 * @param locaSiteRadio
	 * @param locaFreq
	 * @param enerElec
	 * @param locaInfra
	 * @param formaPerso
	 * @param suiviRx
	 * @param factAb
	 * @param txImpot
	 */
	public OpexParameter(int id, double nbResident, double percentCust,
			double growingRate, ArrayList<PrevIncomings> listPrevIncomings,
			double marketing, double interWeb, double adminFGen,
			double assurance, double locaSiteRadio, double locaFreq,
			double enerElec, double locaInfra, double formaPerso,
			double suiviRx, double factAb, double txImpot) {
		super();
		this.id = id;
		this.nbResident = nbResident;
		this.percentCust = percentCust;
		this.growingRate = growingRate;
		this.listPrevIncomings = listPrevIncomings;
		this.marketing = marketing;
		this.interWeb = interWeb;
		this.adminFGen = adminFGen;
		this.assurance = assurance;
		this.locaSiteRadio = locaSiteRadio;
		this.locaFreq = locaFreq;
		this.enerElec = enerElec;
		this.locaInfra = locaInfra;
		this.formaPerso = formaPerso;
		this.suiviRx = suiviRx;
		this.factAb = factAb;
		this.txImpot = txImpot;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public double getNbResident() {
		return nbResident;
	}
	public void setNbResident(double nbResident) {
		this.nbResident = nbResident;
	}
	
	public double getPercentCust() {
		return percentCust;
	}
	public void setPercentCust(double percentCust) {
		this.percentCust = percentCust;
	}
	
	public double getGrowingRate() {
		return growingRate;
	}
	public void setGrowingRate(double growingRate) {
		this.growingRate = growingRate;
	}
	
	public ArrayList<PrevIncomings> getListPrevIncomings() {
		return listPrevIncomings;
	}
	public void setListPrevIncomings(ArrayList<PrevIncomings> listPrevIncomings) {
		this.listPrevIncomings = listPrevIncomings;
	}
	
	public double getMarketing() {
		return marketing;
	}
	public void setMarketing(double marketing) {
		this.marketing = marketing;
	}
	
	public double getInterWeb() {
		return interWeb;
	}
	public void setInterWeb(double interWeb) {
		this.interWeb = interWeb;
	}
	
	public double getAdminFGen() {
		return adminFGen;
	}
	public void setAdminFGen(double adminFGen) {
		this.adminFGen = adminFGen;
	}
	
	public double getAssurance() {
		return assurance;
	}
	public void setAssurance(double assurance) {
		this.assurance = assurance;
	}
	
	public double getLocaSiteRadio() {
		return locaSiteRadio;
	}
	public void setLocaSiteRadio(double locaSiteRadio) {
		this.locaSiteRadio = locaSiteRadio;
	}
	
	public double getLocaFreq() {
		return locaFreq;
	}
	public void setLocaFreq(double locaFreq) {
		this.locaFreq = locaFreq;
	}
	
	public double getEnerElec() {
		return enerElec;
	}
	public void setEnerElec(double enerElec) {
		this.enerElec = enerElec;
	}
	
	public double getLocaInfra() {
		return locaInfra;
	}
	public void setLocaInfra(double locaInfra) {
		this.locaInfra = locaInfra;
	}
	
	public double getFormaPerso() {
		return formaPerso;
	}
	public void setFormaPerso(double formaPerso) {
		this.formaPerso = formaPerso;
	}
	
	public double getSuiviRx() {
		return suiviRx;
	}
	public void setSuiviRx(double suiviRx) {
		this.suiviRx = suiviRx;
	}
	
	public double getFactAb() {
		return factAb;
	}
	public void setFactAb(double factAb) {
		this.factAb = factAb;
	}
	
	public double getTxImpot() {
		return txImpot;
	}
	public void setTxImpot(double txImpot) {
		this.txImpot = txImpot;
	}
	
	
}
