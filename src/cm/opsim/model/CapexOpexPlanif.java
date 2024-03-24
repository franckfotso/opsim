package cm.opsim.model;
/***********************************************************************
 * Module:  CapexOpexPlanif.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class CapexOpexPlanif
 ***********************************************************************/

import java.util.*;

/** @pdOid a68dc395-5097-4f12-b17a-85ce981a8353 */
public class CapexOpexPlanif extends AbstractModel{
	
	private int id = 0;
	private String state = "INIT";
	/**
	 * 
	 */
	public CapexOpexPlanif() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @param id
	 * @param state
	 */
	public CapexOpexPlanif(int id, String state) {
		super();
		this.id = id;
		this.state = state;
	}


	public Hashtable<String,Double> calResultCapex(Parameter param, Project proj){
		Hashtable<String,Double> hashRCapex = new Hashtable<String,Double>();
		
		CapexParameter capexParam = param.getCapexParam();
		int nbENB = proj.getPlanification().getResult().getResCap().getNumENBUl();
		double s1 = capexParam.getIngRadio()+capexParam.getIngReseau()+capexParam.getRechFrs()+capexParam.getRechSites();
		double s2 = capexParam.getEquipRx()+capexParam.getInfraIP()+capexParam.getLogiApps()+capexParam.getInfraVOIP()+
				(nbENB*capexParam.getInfraRadio())+(nbENB*capexParam.getInfraRx());
		double s3 = (capexParam.getLocaSites()+capexParam.getGenieCivil()+capexParam.getRacElec()+
				capexParam.getInstallStation()+capexParam.getRacRxIP())*nbENB;
		double s4 = capexParam.getLocauxMobilers()+capexParam.getSuiviProj();
		double capex = s1+s2+s3+s4;
		
		hashRCapex.put("cout_conception", s1);
		hashRCapex.put("inv_mat_logi", s2);
		hashRCapex.put("cout_deploiement", s3);
		hashRCapex.put("cout_logistique", s4);
		hashRCapex.put("total_capex", capex);		
		
		return hashRCapex;
	}
	
	public Hashtable<String,ArrayList<Double>> calResultOpex(Parameter param, Project proj){
		Hashtable<String,ArrayList<Double>> hashROpex = new Hashtable<String,ArrayList<Double>>();
		
		RCapex rcapex = proj.getPlanification().getResult().getRcapex();
		OpexParameter opexParam = param.getOpexParam();
		ArrayList<PrevIncomings> listPrevIn = opexParam.getListPrevIncomings();
		ArrayList<Double> listInCom = new ArrayList<Double>();
		ArrayList<Double> listOpex = new ArrayList<Double>();
		ArrayList<Double> listBenef = new ArrayList<Double>();
		
		for(int i=0; i<5; i++){
			double inCom = opexParam.getNbResident()*opexParam.getPercentCust();
			double A = 0.00;
			for(PrevIncomings prevIn: listPrevIn){
				A += prevIn.getInByCust()*prevIn.getCustRate();
			}
			inCom *= A*Math.pow((1+opexParam.getGrowingRate()), (i+1))*12;
			listInCom.add(inCom);					
		}
		
		for(int i=0; i<5; i++){
			double opex = opexParam.getMarketing()+
					opexParam.getInterWeb()+
					opexParam.getAdminFGen()+
					opexParam.getAssurance()+
					opexParam.getLocaSiteRadio()+
					opexParam.getLocaFreq()+
					opexParam.getEnerElec()+
					opexParam.getFormaPerso()+
					opexParam.getSuiviRx()+
					(opexParam.getFactAb()*Math.pow((1+opexParam.getGrowingRate()), (i+1)))+
					(opexParam.getTxImpot()*listInCom.get(i));
			listOpex.add(opex);
		}
		
		double inv = rcapex.getCapexTotal()+listOpex.get(0);
		listBenef.add(listInCom.get(0)-inv);
		listBenef.add(listInCom.get(1)+listBenef.get(0)-listOpex.get(1));
		listBenef.add(listInCom.get(2)+listBenef.get(1)-listOpex.get(2));
		listBenef.add(listInCom.get(3)+listBenef.get(2)-listOpex.get(3));
		listBenef.add(listInCom.get(4)+listBenef.get(3)-listOpex.get(4));
		
		hashROpex.put("listInCom", listInCom);
		hashROpex.put("listOpex", listOpex);
		hashROpex.put("listBenef", listBenef);
		
		return hashROpex;		
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