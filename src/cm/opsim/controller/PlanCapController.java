/**
 * 
 */
package cm.opsim.controller;

import java.util.ArrayList;
import java.util.List;

import cm.opsim.dao.AntennaTypeDAO;
import cm.opsim.dao.DAOFactory;
import cm.opsim.dao.FreqBandDAO;
import cm.opsim.model.AbstractModel;
import cm.opsim.model.AntennaParameter;
import cm.opsim.model.AntennaType;
import cm.opsim.model.CapParameter;
import cm.opsim.model.CapacityPlanif;
import cm.opsim.model.CoveragePlanif;
import cm.opsim.model.CustomerParameter;
import cm.opsim.model.EnbParameter;
import cm.opsim.model.FreqBand;
import cm.opsim.model.GenParameter;
import cm.opsim.model.OtherParameter;
import cm.opsim.model.Parameter;
import cm.opsim.model.Planification;
import cm.opsim.model.Project;
import cm.opsim.model.RPlanCapacity;
import cm.opsim.model.RPlanCoverage;
import cm.opsim.model.Result;
import cm.opsim.model.Service;
import cm.opsim.model.Subscription;
import cm.opsim.model.TrafficModel;
import cm.opsim.model.UeParameter;
import cm.opsim.model.ZoneParameter;

/**
 * @author Romuald FOTSO
 *
 */
public class PlanCapController  extends AbstractController {

	/**
	 * @param model
	 */
	public PlanCapController(AbstractModel model) {
		super(model);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.controller.AbstractController#control()
	 */
	@Override
	void control() {
		
	}
	
	public static void main(String[] args)
	{
		/*
		 *  Checking Default params & formulas
		 */
		FreqBandDAO fbDAO = (FreqBandDAO) DAOFactory.getFreqBandDAO();
		AntennaTypeDAO antDAO = (AntennaTypeDAO) DAOFactory.getAntennaTypeDAO();
		
		Project proj = new Project();
		Planification planif = new Planification();
		Result res = new Result();
		res.setResCap(new RPlanCapacity());
		planif.setResult(res);
		proj.setPlanification(planif);
		Parameter param = new Parameter();
		
		// 1st step:
		GenParameter genParam = new GenParameter();
		AntennaParameter antParam = new AntennaParameter();
		
		FreqBand freqBand = fbDAO.find("LTE ETRAN FB 41 - TDD");
		genParam.setBwCellBoardUL(500);
		genParam.setBwCellBoardDL(5000);
		genParam.setNumPRBUl(5);
		AntennaType antenType = antDAO.find(1);
		genParam.setCoverageProba(96);		
		genParam.setChannelModel("EPA5");
		genParam.setBwFB(1.4);
		genParam.setBodyLoss(3);
		genParam.setBuildingLoss(18);
		genParam.setCarLoss(0);
		genParam.setFigureNoise(2.5);
		genParam.setFadingMargin(4.2);
		antParam.setAntennaType(antenType);
		param.setGenParam(genParam);
		param.setAntParam(antParam);
		
		// 2nd step:
		CustomerParameter custParam = new CustomerParameter();
		
		ArrayList<TrafficModel> listMT = new ArrayList<TrafficModel>();
    	ArrayList<Subscription> listSub = new ArrayList<Subscription>();
    	
    	TrafficModel tm = new TrafficModel();
		tm.setClass_serv(" VoIP");
		tm.setBw_dl(26.90);
		tm.setBw_ul(26.90);
		tm.setPoh(3.63);
		tm.setTp(20);
		listMT.add(tm);		
		tm = new TrafficModel();
		tm.setClass_serv(" HTTP Navigation");
		tm.setBw_dl(250.11);
		tm.setBw_ul(62.53);
		tm.setPoh(5.3);
		tm.setTp(100);
		listMT.add(tm);		
		tm = new TrafficModel();
		tm.setClass_serv(" FTP");
		tm.setBw_dl(750.34);
		tm.setBw_ul(140.69);
		tm.setPoh(9.8);
		tm.setTp(20);
		listMT.add(tm);
		tm = new TrafficModel();
		tm.setClass_serv(" Interactive Streaming");
		tm.setBw_dl(134.90);
		tm.setBw_ul(134.90);
		tm.setPoh(2.2);
		tm.setTp(10);
		listMT.add(tm);
		tm = new TrafficModel();
		tm.setClass_serv(" Background Services");
		tm.setBw_dl(15.69);
		tm.setBw_ul(15.69);
		tm.setPoh(1);
		tm.setTp(50);
		listMT.add(tm);
		
		List listAbonPublic = new ArrayList();
		listAbonPublic.add("VoIP");
		listAbonPublic.add("Vidéo Streaming");
		listAbonPublic.add("Jeu");
		listAbonPublic.add("Web Browsing");
		
		List listAbonPriv = new ArrayList();
		listAbonPriv.add("VoIP");
		listAbonPriv.add("Vidéo Streaming");
		listAbonPriv.add("Jeu");
		listAbonPriv.add("Web Browsing");
		listAbonPriv.add("E-Mail");
		
		List listAbonBusiness = new ArrayList();
		listAbonBusiness.add("VoIP");
		listAbonBusiness.add("Vidéo Streaming");
		listAbonBusiness.add("Web Browsing");
		listAbonBusiness.add("FTP");
		listAbonBusiness.add("E-Mail");
		listAbonBusiness.add("Vidéo Conférence");
		
		ArrayList listItems_sub1 = (ArrayList) listAbonPublic;
    	ArrayList listItems_sub2 = (ArrayList) listAbonPriv;
    	ArrayList listItems_sub3 = (ArrayList) listAbonBusiness;    	
    	ArrayList<Service> listServ_sub1 = new ArrayList<Service>();
    	ArrayList<Service> listServ_sub2 = new ArrayList<Service>();
    	ArrayList<Service> listServ_sub3 = new ArrayList<Service>();
    	
    	for(Object obj : listItems_sub1){
    		Service serv = new Service();
    		serv.setName(obj.toString());
    		listServ_sub1.add(serv);
    	}
    	for(Object obj : listItems_sub2){
    		Service serv = new Service();
    		serv.setName(obj.toString());
    		listServ_sub2.add(serv);
    	}
    	for(Object obj : listItems_sub3){
    		Service serv = new Service();
    		serv.setName(obj.toString());
    		listServ_sub3.add(serv);
    	}
		
    	Subscription sub1 = new Subscription();    	
    	sub1.setCustParam_id(custParam.getId());
    	sub1.setName("Sub 1");
    	sub1.setDescription("Abonnement public");
    	sub1.setPc_sub(45);
    	sub1.setListServ(listServ_sub1);
    	
    	Subscription sub2 = new Subscription();
    	sub2.setCustParam_id(custParam.getId());
    	sub2.setName("Sub 2");
    	sub2.setDescription("Abonnement privilegie");
    	sub2.setPc_sub(35);
    	sub2.setListServ(listServ_sub2);
    	
    	Subscription sub3 = new Subscription();
    	sub3.setCustParam_id(custParam.getId());
    	sub3.setName("Sub 3");
    	sub3.setDescription("Abonnement Business");
    	sub3.setPc_sub(20);
    	sub3.setListServ(listServ_sub3);
    	
    	listSub.add(sub1);
    	listSub.add(sub2);
    	listSub.add(sub3);    	
    	custParam.setListSub(listSub);
    	custParam.setListTM(listMT);
    	custParam.setMarPart(33);
    	custParam.setPenRate(9);
    	custParam.setGrowthRate(30);
    	custParam.setInvTime(10);
    	
    	param.setCustParam(custParam);
    	
    	// 3rd step:
    	CapParameter capParam = new CapParameter();
    	
    	capParam.setSoh_RS_UL(1);
		capParam.setSoh_PRACH_UL(6);
		capParam.setSoh_PUCCH_UL(1);
		capParam.setCpi_UL(6);
		capParam.setProbaQPSK_1on2_UL(0.6631);
		capParam.setEffQPSK_1on2_UL(1);
		capParam.setObf_UL(0.38);
		capParam.setProba16QAM_2on3_UL(0.6426);
		capParam.setEff16QAM_2on3_UL(1);
		capParam.setProba64QAM_5on6_UL(0.9258);
		capParam.setEff64QAM_5on6_UL(1);
		capParam.setProbaSMgain_1_UL(1);
		capParam.setProbaSMgain_2_UL(1);
		capParam.setProbaSMgain_4_UL(1);
		
		capParam.setSoh_RS_DL(1);
		capParam.setSoh_PSS_SSS_DL(1);
		capParam.setSoh_PBCCH_DL(1);
		capParam.setSoh_L1_L2_DL(1);
		capParam.setCpi_DL(7);
		capParam.setProbaQPSK_1on2_DL(0.555);
		capParam.setEffQPSK_1on2_DL(1);
		capParam.setObf_DL(0.22);
		capParam.setProba16QAM_2on3_DL(0.535);
		capParam.setEff16QAM_2on3_DL(1);
		capParam.setProba64QAM_5on6_DL(0.7717);
		capParam.setEff64QAM_5on6_DL(1);
		capParam.setProbaSMgain_1_DL(1);
		capParam.setProbaSMgain_2_DL(1);
		capParam.setProbaSMgain_4_DL(1);
		capParam.setProbaSMgain_8_DL(1);
		
		EnbParameter enbParam = new EnbParameter();
		UeParameter ueParam = new UeParameter();
		
		enbParam.setCableLoss(2);
		enbParam.setAntennaGain(18.5);
		enbParam.setThermicNoiseByRB(-114.4);
		enbParam.setPowerTx(46.02);
		enbParam.setCellBoardSNIR(-5.32);
		enbParam.setSystemCharge(0.35);
		enbParam.setFactorFC(2.3);
		enbParam.setJumperTmaLoss(0);
		enbParam.setSiteType("Mono-sectorise");
		
		ueParam.setPowerUE(23);
		ueParam.setAntennaGain(18.5);
		ueParam.setThermicNoiseByRB(-118.9);
		ueParam.setTargetSNIR(1.06);
		ueParam.setSystCharge(0.32);
		ueParam.setFactorF(0.7);
		ueParam.setJumperTmaLoss(0);
		
		param.setEnbParam(enbParam);
		param.setUeParam(ueParam);
		
		OtherParameter oParam = new OtherParameter();
		ZoneParameter zoneParam = new ZoneParameter();
		zoneParam.setArea(68953);
		zoneParam.setDensity(3098044);
		oParam.setMimoType("MIMO1x1");
		param.setCapParam(capParam);
		param.setOtherParam(oParam);
		param.setZoneParam(zoneParam);
		
		proj.setParameter(param);
		PlanCapController planCap = new PlanCapController(new CapacityPlanif());
		res =  planCap.calResult(proj);
		RPlanCapacity resCap = res.getResCap();
		
		System.out.println(" Capacité d'une cellule (Mbps)[UL,DL]: ["+round(resCap.getCellCapacityUL()/1000000,3)+
				","+round(resCap.getCellCapacityDL()/1000000,3)+"]");
		System.out.println(" Trafic Total à Véhiculer (Mbps)[UL,DL]: ["+round(resCap.getTrafficUL()/1000000,3)+
				","+round(resCap.getTrafficDL()/1000000,3)+"]");
		System.out.println(" Capacité en nombre d'abonnés[UL,DL]: ["+round(resCap.getNumCustomerByCellUL(),3)+
				","+round(resCap.getNumCustomerByCellDL(),3)+"]");
		System.out.println(" Nombre d'Enode-B[UL,DL]: ["+resCap.getNumENBUl()+
				","+resCap.getNumENBDl()+"]");
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.controller.AbstractController#calResult(cm.opsim.model.AbstractModel)
	 */
	@Override
	public Result calResult(AbstractModel model) {
		Project proj = (Project) model;
		Parameter param = proj.getParameter();
		ZoneParameter zoneParam = param.getZoneParam();
		Result res = proj.getPlanification().getResult();		
		CapacityPlanif capPlanif = (CapacityPlanif) this.model;
		RPlanCapacity resCap = res.getResCap();
		
		resCap.setCellCapacityUL(capPlanif.calTotalCapByCell_UL(param));
		resCap.setCellCapacityDL(capPlanif.calTotalCapByCell_DL(param));
		resCap.setTrafficUL(capPlanif.calTotalCapToSet_UL(param));
		resCap.setTrafficDL(capPlanif.calTotalCapToSet_DL(param));
		resCap.setNumCustomerByCellUL(capPlanif.calNumCustomer_UL(param));
		resCap.setNumCustomerByCellDL(capPlanif.calNumCustomer_DL(param));
		try {
			resCap.setNumENBUl(capPlanif.calNumSite_UL(param));
			resCap.setNumENBDl(capPlanif.calNumSite_DL(param));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		double rad_cell_UL = Math.sqrt((zoneParam.getArea()/resCap.getNumENBUl())/2.6);	
		double rad_cell_DL = Math.sqrt((zoneParam.getArea()/resCap.getNumENBDl())/2.6);		
//		double rad_cell_UL = proj.getPlanification().getResult().getResCov().getCellRadiusUL();
//		double rad_cell_DL = proj.getPlanification().getResult().getResCov().getCellRadiusDL();
		resCap.setCellRadius(Math.max(rad_cell_UL, rad_cell_DL));
		resCap.setInterSiteDist(capPlanif.calInterSiteDist(resCap.getCellRadius()));
		
		res.setResCap(resCap);		
		return res;
	}

}
