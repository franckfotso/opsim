/**
 * 
 */
package cm.opsim.controller;

import cm.opsim.dao.AntennaTypeDAO;
import cm.opsim.dao.DAOFactory;
import cm.opsim.dao.FreqBandDAO;
import cm.opsim.model.AbstractModel;
import cm.opsim.model.AntennaParameter;
import cm.opsim.model.AntennaType;
import cm.opsim.model.Cost231Hata;
import cm.opsim.model.CoveragePlanif;
import cm.opsim.model.EnbParameter;
import cm.opsim.model.EnvParameter;
import cm.opsim.model.FreqBand;
import cm.opsim.model.GenParameter;
import cm.opsim.model.OkumuraHata;
import cm.opsim.model.OtherParameter;
import cm.opsim.model.Parameter;
import cm.opsim.model.Planification;
import cm.opsim.model.PmParameter;
import cm.opsim.model.Project;
import cm.opsim.model.RPlanCoverage;
import cm.opsim.model.Result;
import cm.opsim.model.UeParameter;
import cm.opsim.model.ZoneParameter;

/**
 * @author Romuald FOTSO
 *
 */
public class PlanCovController extends AbstractController {

	/**
	 * @param model
	 */
	public PlanCovController(AbstractModel model) {
		super(model);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.controller.AbstractController#control()
	 */
	@Override
	public void control() {
		// Control level 2
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
		res.setResCov(new RPlanCoverage());
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
		genParam.setBodyLoss(3);
		genParam.setBuildingLoss(18);
		genParam.setCarLoss(0);
		genParam.setFigureNoise(2.5);
		genParam.setFadingMargin(4.2);
		antParam.setAntennaType(antenType);
		param.setGenParam(genParam);
		param.setAntParam(antParam);
		
		// 2nd step:
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
		
		// 3rd step:
		EnvParameter envParam = new EnvParameter();
		PmParameter pmParam = new PmParameter();	
		
		envParam.setMarginInterf(12);
		envParam.setMhag(0);
		envParam.setCco(0);
		
		OkumuraHata oku = new OkumuraHata();
		oku.setFrequency(150);
		oku.setHeightUE(1.5);
		oku.setHeightENB(30);
		oku.setZoneType("Urbain dense");
		
		pmParam.setPm(oku);
		envParam.setPmParameter(pmParam);
		OtherParameter oParam = new OtherParameter();
		ZoneParameter zoneParam = new ZoneParameter();
		zoneParam.setArea(68953);
		zoneParam.setDensity(3098044);
		oParam.setMimoType("MIMO1x1");
		param.setEnvParam(envParam);
		param.setOtherParam(oParam);
		param.setZoneParam(zoneParam);
		
		proj.setParameter(param);		
		PlanCovController planCon = new PlanCovController(new CoveragePlanif());
		res =  planCon.calResult(proj);
		RPlanCoverage resCov = res.getResCov();	
		
		System.out.println("PIRE (dBm) [UL,DL]: ["+round(resCov.getPireUl(),3)+","+round(resCov.getPireDl(),3)+"]");
		System.out.println("Sensibilié (dBm) [UL,DL]: ["+round(resCov.getSensibilityUL(),3)+","+round(resCov.getSensibilityDL(),3)+"]");
		System.out.println("Bruit termique (dB) [UL,DL]: ["+round(resCov.getThermicNoiseUL(),3)+","+round(resCov.getThermicNoiseDL(),3)+"]");
		System.out.println("Path Loss (dBm) [UL,DL]: ["+round(resCov.getPathLossUL(),3)+","+round(resCov.getPathLossDL(),3)+"]");
		System.out.println("Rayon d'une cellule (km) [UL,DL]: ["+round(resCov.getCellRadiusUL(),3)+","+round(resCov.getCellRadiusDL(),3)+"]");
		System.out.println("Empreinte d'une cellule (km²) [UL,DL]: ["+round(resCov.getCellPrintUL(),3)+","+round(resCov.getCellPrintDL(),3)+"]");
		System.out.println("Empreinte d'un site (km²) [UL,DL]: ["+round(resCov.getSitePrintUL(),3)+","+round(resCov.getSitePrintDL(),3)+"]");
		System.out.println("Nombre d'Enode-B [UL,DL]: ["+resCov.getNumENBUl()+","+resCov.getNumENBDl()+"]");
		System.out.println("Distance inter-sites finale (km) [UL,DL]: ["+round(resCov.getInterSiteDistUL(),3)+
				","+round(resCov.getInterSiteDistDL(),3)+"]");		
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	@Override
	public Result calResult(AbstractModel model){
		Project proj = (Project) model;
		Parameter param = proj.getParameter();
		PmParameter pmParam = param.getEnvParam().getPmParameter();
		Result res = proj.getPlanification().getResult();		
		CoveragePlanif covPlanif = (CoveragePlanif) this.model;
		RPlanCoverage resCov = res.getResCov();
		
		resCov.setPireUl(covPlanif.calPire_UL(param));
		resCov.setPireDl(covPlanif.calPire_DL(param));
		resCov.setSensibilityUL(covPlanif.calSensibility_ENB(param));
		resCov.setSensibilityDL(covPlanif.calSensibility_UE(param));
		resCov.setThermicNoiseUL(covPlanif.calThermNoise_UL(param));
		resCov.setThermicNoiseDL(covPlanif.calThermNoise_DL(param));
		resCov.setPathLossUL(covPlanif.calMAPL_UL(param));
		resCov.setPathLossDL(covPlanif.calMAPL_DL(param));
		if(pmParam.getPm() instanceof OkumuraHata){
			OkumuraHata oku = (OkumuraHata) pmParam.getPm();
			try {
				resCov.setCellRadiusUL(covPlanif.calCellRadius_OkumuraHata(
						oku.getFrequency(),
						oku.getHeightENB(),
						oku.getHeightUE(),
						oku,
						resCov.getPathLossUL()));
				resCov.setCellRadiusDL(covPlanif.calCellRadius_OkumuraHata(
						oku.getFrequency(),
						oku.getHeightENB(),
						oku.getHeightUE(),
						oku,
						resCov.getPathLossDL()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(pmParam.getPm() instanceof Cost231Hata){
			Cost231Hata c231 = (Cost231Hata) pmParam.getPm();
			try {
				resCov.setCellRadiusUL(covPlanif.calCellRadius_COST231_Hata(
						c231.getFrequency(),
						c231.getHeightENB(),
						c231.getHeightUE(),
						c231,
						resCov.getPathLossUL()));
				resCov.setCellRadiusDL(covPlanif.calCellRadius_COST231_Hata(
						c231.getFrequency(),
						c231.getHeightENB(),
						c231.getHeightUE(),
						c231,
						resCov.getPathLossDL()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		resCov.setCellPrintUL(covPlanif.calCellPrint(resCov.getCellRadiusUL()));
		resCov.setCellPrintDL(covPlanif.calCellPrint(resCov.getCellRadiusDL()));
		try {
			resCov.setSitePrintUL(covPlanif.calSitePrint(resCov.getCellRadiusUL(), param));
			resCov.setSitePrintDL(covPlanif.calSitePrint(resCov.getCellRadiusDL(), param));
		} catch (Exception e) {
			e.printStackTrace();
		}
		resCov.setNumENBUl(covPlanif.calNumENB(resCov.getSitePrintUL(), param));
		resCov.setNumENBDl(covPlanif.calNumENB(resCov.getSitePrintDL(), param));
		resCov.setInterSiteDistUL(covPlanif.calInterSiteDist(resCov.getCellRadiusUL()));
		resCov.setInterSiteDistDL(covPlanif.calInterSiteDist(resCov.getCellRadiusDL()));		
		res.setResCov(resCov);
		return res;
	}

}
