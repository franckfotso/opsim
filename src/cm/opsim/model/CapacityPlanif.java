package cm.opsim.model;
/***********************************************************************
 * Module:  CapacityPlanif.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class CapacityPlanif
 ***********************************************************************/

import java.util.ArrayList;

/** @pdOid 5250e71a-6583-40f0-92ac-f07ed4bdca4e */
public class CapacityPlanif extends AbstractModel{
	
	private int id = 0;
	private String state = "INIT";
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	/**
	 * @param id
	 * @param state
	 */
	public CapacityPlanif(int id, String state) {
		super();
		this.id = id;
		this.state = state;
	}

	/**
	 * 
	 */
	public CapacityPlanif() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int calNumSubscriber(Parameter param){
		int numSub = 0;
		double densitySub = param.getZoneParam().getDensity();
		double area = param.getZoneParam().getArea();
		double marPart = param.getCustParam().getMarPart();
		double penRate = param.getCustParam().getPenRate();
		double growthRate = param.getCustParam().getGrowthRate();
		int i = param.getCustParam().getInvTime();
		numSub = (int) Math.ceil(densitySub*area*marPart*(1+i*growthRate)*penRate);
		System.out.println("Num. Subscriber: "+numSub);
		return numSub;
	}
	
	public double calTotalCapToSet_UL(Parameter param){
		double densitySub = param.getZoneParam().getDensity();
		double[][] tab_bw_ul = new double[3][5];
		double[][] poh = new double[3][5];
		double[][] penRate = new double[3][5];		
		int n = param.getCustParam().getListSub().size();
		int m = param.getCustParam().getListTM().size();
		
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				tab_bw_ul[i][j] = param.getCustParam().getListTM().get(j).getBw_ul();
				poh[i][j] = param.getCustParam().getListTM().get(j).getPoh()/100;
				penRate[i][j] = param.getCustParam().getListTM().get(j).getTp()/100;
			}
		}
	
		double cap_UL = 0.00;
		double capBySub_UL[] = new double[3];
//		System.out.println("N. sub: "+n);
//		System.out.println("N. serv: "+m);
		
		for(int i=0;i<n;i++){
			capBySub_UL[i] = 0;			
			for(int j=0;j<m;j++){
				capBySub_UL[i] += tab_bw_ul[i][j]*(1+poh[i][j])*(penRate[i][j]);
			}
		}		
		for(int k=0;k<n;k++){
			double pc_sub = param.getCustParam().getListSub().get(k).getPc_sub()/100;
//			System.out.println("CapBySub_UL: "+capBySub_UL[k]);
//			System.out.println("DensitySub: "+densitySub);
//			System.out.println("Pc_sub: "+pc_sub);
			cap_UL += capBySub_UL[k]*densitySub*pc_sub;
		}
		
		System.out.println("Trafic_UL (Mbit/s): "+cap_UL/1000000);
		return cap_UL;
	}
	
	public double calTotalCapToSet_DL(Parameter param){
		double densitySub = param.getZoneParam().getDensity();
		double[][] tab_bw_dl = new double[3][5];
		double[][] poh = new double[3][5];
		double[][] penRate = new double[3][5];
		
		int i = 0; 
		for(Subscription sub : param.getCustParam().getListSub()){
			int j = 0;
			for(TrafficModel tm : param.getCustParam().getListTM()){
				tab_bw_dl[i][j] = tm.getBw_dl();
				poh[i][j] = tm.getPoh()/100;
				penRate[i][j] = tm.getTp()/100;
				j++;
			}
			i++;
		}
		
		double cap_DL = 0.00;
		double capBySub_DL[] = new double[3];
		int n = param.getCustParam().getListSub().size();
		int m = param.getCustParam().getListTM().size();
		
		for(int k=0;k<n;k++){
			capBySub_DL[k] = 0;			
			for(int l=0;l<m;l++){
				capBySub_DL[k] += tab_bw_dl[k][l]*(1+poh[k][l])*penRate[k][l];
			}
		}		
		for(int k=0;k<n;k++){
			double pc_sub = param.getCustParam().getListSub().get(k).getPc_sub()/100;
			cap_DL += capBySub_DL[k]*densitySub*pc_sub;
		}
		System.out.println("Trafic_DL (Mbit/s): "+cap_DL/1000000);
		return cap_DL;
	}
	
	public double calCostRefSignal_UL(Parameter param){		
		CapParameter capParam = param.getCapParam();	
		double SOH_RS_UL = capParam.getSoh_PUCCH_UL()/capParam.getCpi_UL();
		System.out.println("SOH_RS_UL: "+SOH_RS_UL);
		return SOH_RS_UL;
	}
	
	public double calCostRefSignal_DL(Parameter param){
		double SOH_RS_DL = 0.00;
		int N_RS_DL = 0;
		CapParameter capParam = param.getCapParam();
		String mimoType = param.getOtherParam().getMimoType();
		switch(mimoType){
			case "MIMO1x1":	
				N_RS_DL = 4;
				break;
			case "MIMO2x2":	
				N_RS_DL = 8;
				break;
			case "MIMO4x4":	
				N_RS_DL = 14;
				break;	
			case "MIMO8x8":	
				N_RS_DL = 20;
				break;	
			default:	
				N_RS_DL = 4;
				break;
		}
		SOH_RS_DL = N_RS_DL/(capParam.getCpi_DL()*12);
		System.out.println("SOH_RS_DL: "+SOH_RS_DL);
		return SOH_RS_DL;
	}
	
	public double calCostChannelPUCCH_UL(Parameter param){
		double SOH_PUCCH_UL = 0.00;
		GenParameter genParam = param.getGenParam();
		double bwFB = genParam.getBwFB();
		double num_PRB_PUCCH = 0.0;
		//System.out.println("bwFB: "+Double.toString(bwFB));
		switch(Double.toString(bwFB)){
			case "1.4":
				num_PRB_PUCCH = 1;
				break;
			case "3":
				num_PRB_PUCCH = 2;
				break;
			case "5":
				num_PRB_PUCCH = 2;
				break;
			case "10":
				num_PRB_PUCCH = 4;
				break;
			case "15":
				num_PRB_PUCCH = 6;
				break;
			case "20":
				num_PRB_PUCCH = 8;
				break;
			default:
				num_PRB_PUCCH = 1;
				break;
		}		
		SOH_PUCCH_UL = num_PRB_PUCCH/genParam.getNumPRBUl();
//		System.out.println("num_PRB_PUCCH: "+num_PRB_PUCCH);
//		System.out.println("NumPRBUl: "+genParam.getNumPRBUl());
		System.out.println("SOH_PUCCH_UL: "+SOH_PUCCH_UL);
		return SOH_PUCCH_UL;
	}
	
	public double calSynChannel_PSS_SSS_DL(Parameter param){
		double SOH_PSS_SSS_DL = 0.00;
		GenParameter genParam = param.getGenParam();
		CapParameter capParam = param.getCapParam();
		SOH_PSS_SSS_DL = (72*2)/(genParam.getNumPRBUl()*12*capParam.getCpi_DL()*20);
		System.out.println("SOH_PSS_SSS_DL: "+SOH_PSS_SSS_DL);
		return SOH_PSS_SSS_DL;
	}
	
	public double calChannelPBCH_DL(Parameter param){
		double SOH_PBCH_DL = 0.00;
		GenParameter genParam = param.getGenParam();
		CapParameter capParam = param.getCapParam();
		SOH_PBCH_DL = (72*4*7)/(genParam.getNumPRBUl()*12*capParam.getCpi_DL()*20);
		System.out.println("SOH_PBCH_DL: "+SOH_PBCH_DL);
		return SOH_PBCH_DL;
	}
	
	public double calCostL1L2OverHead_DL(Parameter param){
		double SOH_L1L2_DL = 0.00;
		GenParameter genParam = param.getGenParam();
		CapParameter capParam = param.getCapParam();
		SOH_L1L2_DL = (34*10)/(12*capParam.getCpi_DL()*20);
		System.out.println("SOH_L1L2_DL: "+SOH_L1L2_DL);
		return SOH_L1L2_DL;
	}
	
	public double calCostChannelPRACH_UL(Parameter param){
		double SOH_PRACH_UL = 0.00;
		GenParameter genParam = param.getGenParam();
		CapParameter capParam = param.getCapParam();
		SOH_PRACH_UL = (6*capParam.getSoh_PRACH_UL())/(genParam.getNumPRBUl()*12*capParam.getCpi_DL()*20);
//		System.out.println("NumPRB_UL: "+genParam.getNumPRBUl());
//		System.out.println("Cpi_DL: "+capParam.getCpi_DL());
		System.out.println("SOH_PRACH_UL: "+SOH_PRACH_UL);
		return SOH_PRACH_UL;
	}
	
	public double calCellCapacity_UL(Parameter param){	
		double SOH_UL = 0.00;
		double SOH_RS_UL = this.calCostRefSignal_UL(param);
		double SOH_PRACH_UL = this.calCostChannelPRACH_UL(param);
		double SOH_PUCCH_UL = this.calCostChannelPUCCH_UL(param);
		SOH_UL = SOH_RS_UL+SOH_PRACH_UL+SOH_PUCCH_UL;
		System.out.println("SOH_UL: "+SOH_UL);
		return SOH_UL;
	}
	
	public double calCellCapacity_DL(Parameter param){
		double SOH_DL = 0.00;
		CapParameter capParamter = param.getCapParam();
		double SOH_RS_DL = this.calCostRefSignal_DL(param);
		double SOH_PSS_SSS_DL = this.calSynChannel_PSS_SSS_DL(param);
		double SOH_PBCH_DL = this.calChannelPBCH_DL(param);
		double SOH_L1L2_DL = this.calCostL1L2OverHead_DL(param);	
		SOH_DL = SOH_RS_DL+SOH_PSS_SSS_DL+SOH_PBCH_DL+SOH_L1L2_DL;
		System.out.println("SOH_DL: "+SOH_DL);
		return SOH_DL;
	}
	
	public double calTotalCapByCell_UL(Parameter param){
		double Cap_UL = 0.00;
		GenParameter genParam = param.getGenParam();
		CapParameter capParam = param.getCapParam();
		double effCell_UL = 1-this.calCellCapacity_UL(param);
		double[] eff_MCS_UL = new double[29];
		double[] proba_MCS_UL = new double[29];
		double A = 0.00;
		double B = 0.00;
		//System.out.println("ID CapParam: "+capParam.getId());
		//System.out.println("EffQPSK_1on2_UL: "+capParam.getEffQPSK_1on2_UL());
		for(int i = 0;i<10;i++){
			eff_MCS_UL[i] = capParam.getEffQPSK_1on2_UL();
			//eff_MCS_UL[i] = 1.0*(1.0/2.0);
			proba_MCS_UL[i] = capParam.getProbaQPSK_1on2_UL();
		}
		for(int i = 10;i<17;i++){
			eff_MCS_UL[i] = capParam.getEff16QAM_2on3_UL();			
			//eff_MCS_UL[i] = 2.0*(2.0/3.0);
			proba_MCS_UL[i] = capParam.getProba16QAM_2on3_UL();
		}
		for(int i = 17;i<29;i++){
			eff_MCS_UL[i] = capParam.getEff64QAM_5on6_UL();
			//eff_MCS_UL[i] = 9.0*(5.0/6.0); 
			proba_MCS_UL[i] = capParam.getProba64QAM_5on6_UL();
		}
		for(int i = 0;i<29;i++){
			A += eff_MCS_UL[i]*proba_MCS_UL[i];
		}
		
		double b1, b2, b4;
		b1 = 1*capParam.getProbaSMgain_1_UL()*effCell_UL*12*genParam.getNumPRBUl()*capParam.getCpi_UL()*Math.pow(10,3);
		b2 = 2*capParam.getProbaSMgain_2_UL()*effCell_UL*12*genParam.getNumPRBUl()*capParam.getCpi_UL()*Math.pow(10,3);
		b4 = 4*capParam.getProbaSMgain_4_UL()*effCell_UL*12*genParam.getNumPRBUl()*capParam.getCpi_UL()*Math.pow(10,3);
		B = b1+b2+b4;
		Cap_UL = A*B;
//		System.out.println("A: "+A);
//		System.out.println("B: "+B);
		System.out.println("Cap_UL(Mbit/s): "+Cap_UL/1000000);
		return Cap_UL;
	}
	
	public double calTotalCapByCell_DL(Parameter param){
		double Cap_DL = 0.00;
		GenParameter genParam = param.getGenParam();
		CapParameter capParam = param.getCapParam();
		double effCell_DL = 1-this.calCellCapacity_DL(param);
		double[] eff_MCS_DL = new double[29];
		double[] proba_MCS_DL = new double[29];
		double A = 0.00;
		double B = 0.00;
		for(int i = 0;i<10;i++){
			eff_MCS_DL[i] = capParam.getEffQPSK_1on2_DL();
			//eff_MCS_DL[i] = 1.0*(1.0/2.0);
			proba_MCS_DL[i] = capParam.getProbaQPSK_1on2_DL();
		}
		for(int i = 10;i<17;i++){
			eff_MCS_DL[i] = capParam.getEff16QAM_2on3_DL();
			//eff_MCS_DL[i] = 2.0*(2.0/3.0);
			proba_MCS_DL[i] = capParam.getProba16QAM_2on3_DL();
		}
		for(int i = 17;i<29;i++){
			eff_MCS_DL[i] = capParam.getEff64QAM_5on6_DL();
			//eff_MCS_DL[i] = 24.0*(5.0/6.0);
			proba_MCS_DL[i] = capParam.getProba64QAM_5on6_DL();
		}
		for(int i = 0;i<29;i++){
			A += eff_MCS_DL[i]*proba_MCS_DL[i];
		}
		
		double b1, b2, b4, b8;
		b1 = 1*capParam.getProbaSMgain_1_DL()*effCell_DL*12*genParam.getNumPRBUl()*capParam.getCpi_DL()*Math.pow(10,3);
		b2 = 2*capParam.getProbaSMgain_2_DL()*effCell_DL*12*genParam.getNumPRBUl()*capParam.getCpi_DL()*Math.pow(10,3);
		b4 = 4*capParam.getProbaSMgain_4_DL()*effCell_DL*12*genParam.getNumPRBUl()*capParam.getCpi_DL()*Math.pow(10,3);
		b8 = 8*capParam.getProbaSMgain_8_DL()*effCell_DL*12*genParam.getNumPRBUl()*capParam.getCpi_DL()*Math.pow(10,3);
		B = b1+b2+b4+b8;
		Cap_DL = A*B;
		System.out.println("Cap_DL(Mbit/s): "+Cap_DL/1000000);
		return Cap_DL;
	}
	
	public int calNumSector_UL(Parameter param){
		int N_sec_UL = 0;
		CapParameter capParam = param.getCapParam();		
		double cap_UL = this.calTotalCapToSet_UL(param);
		double capByCell_UL = this.calTotalCapByCell_UL(param);
		double OBF_UL = capParam.getObf_UL();
		N_sec_UL = (int) Math.ceil(cap_UL/(capByCell_UL*(1-OBF_UL)));
		System.out.println("N_sec_UL: "+N_sec_UL);
		return N_sec_UL;
	}
	
	public int calNumSector_DL(Parameter param){
		int N_sec_DL = 0;
		CapParameter capParam = param.getCapParam();
		double cap_DL = this.calTotalCapToSet_DL(param);
		double capByCell_DL = this.calTotalCapByCell_DL(param);
		double OBF_DL = capParam.getObf_DL();
		N_sec_DL = (int) Math.ceil(cap_DL/(capByCell_DL*(1-OBF_DL)));
		System.out.println("N_sec_DL: "+N_sec_DL);
		return N_sec_DL;
	}
	
	public int calNumSite_UL(Parameter param) throws Exception{
		int num_Enb_UL = 0;
		int num_sec_UL = this.calNumSector_UL(param);
		int num_secByEnb_UL = 0;
		String siteType = param.getEnbParam().getSiteType();
		switch(siteType){
			case "Mono-sectorise":
				num_secByEnb_UL = 1;
				break;
			case "Bi-sectorie":
				num_secByEnb_UL = 2;
				break;
			case "Tri-sectorise":
				num_secByEnb_UL = 3;
				break;
			default:
				throw new Exception("Error Propagation Model: Site type undefined");
		}
		num_Enb_UL = (int) Math.ceil((num_sec_UL-1)/num_secByEnb_UL);	
		System.out.println("num_Enb_UL: "+num_Enb_UL);
		return num_Enb_UL;
	}
	
	public int calNumSite_DL(Parameter param) throws Exception{
		int num_Enb_DL = 0;
		int num_sec_DL = this.calNumSector_DL(param);
		int num_secByEnb_DL = 0;
		String siteType = param.getEnbParam().getSiteType();
		switch(siteType){
			case "Mono-sectorise":
				num_secByEnb_DL = 1;
				break;
			case "Bi-sectorie":
				num_secByEnb_DL = 2;
				break;
			case "Tri-sectorise":
				num_secByEnb_DL = 3;
				break;
			default:
				throw new Exception("Error Propagation Model: Site type undefined");
		}
		num_Enb_DL = (int) Math.ceil((num_sec_DL-1)/num_secByEnb_DL);	
		System.out.println("num_Enb_DL: "+num_Enb_DL);
		return num_Enb_DL;
	}
	
	public int calNumCustomer_UL(Parameter param){
		int nb_ab_UL = 0;
		CapParameter capParam = param.getCapParam();
		double cap_UL = this.calTotalCapToSet_UL(param);
		double A = cap_UL*(1-capParam.getObf_UL());
		
		double[][] tab_bw_ul = new double[3][5];
		double[][] poh = new double[3][5];
		double[][] penRate = new double[3][5];
		
		int i = 0; 
		for(Subscription sub : param.getCustParam().getListSub()){
			int j = 0;
			for(TrafficModel tm : param.getCustParam().getListTM()){
				tab_bw_ul[i][j] = tm.getBw_ul();
				poh[i][j] = tm.getPoh()/100;
				penRate[i][j] = tm.getTp()/100;
				j++;
			}
			i++;
		}
		
		double B = 0.00;
		double capBySub_UL[] =  new double[3];
		int n = param.getCustParam().getListSub().size();
		int m = param.getCustParam().getListTM().size();
		
		for(int k=0;k<n;k++){
			capBySub_UL[k] = 0;			
			for(int l=0;l<m;l++){
				capBySub_UL[k] += tab_bw_ul[k][l]*(1+poh[k][l])*penRate[k][l];
			}
		}		
		for(int k=0;k<n;k++){
			B += capBySub_UL[k]*param.getCustParam().getListSub().get(k).getPc_sub()/100;
		}
		nb_ab_UL = (int) Math.ceil(A/B);
		System.out.println("A: "+A);
		System.out.println("B: "+B);
		System.out.println("nb_ab_UL: "+nb_ab_UL);
		return nb_ab_UL;
	}
	
	public int calNumCustomer_DL(Parameter param){
		int nb_ab_DL = 0;
		CapParameter capParam = param.getCapParam();
		double cap_DL = this.calTotalCapToSet_DL(param);
		double A = cap_DL*(1-capParam.getObf_DL());
		
		double[][] tab_bw_dl = new double[3][5];
		double[][] poh = new double[3][5];
		double[][] penRate = new double[3][5];
		
		int i = 0; 
		for(Subscription sub : param.getCustParam().getListSub()){
			int j = 0;
			for(TrafficModel tm : param.getCustParam().getListTM()){
				tab_bw_dl[i][j] = tm.getBw_dl();
				poh[i][j] = tm.getPoh()/100;
				penRate[i][j] = tm.getTp()/100;
				j++;
			}
			i++;
		}
		
		double B = 0.00;
		double capBySub_DL[] =  new double[3];
		int n = param.getCustParam().getListSub().size();
		int m = param.getCustParam().getListTM().size();
		
		for(int k=0;k<n;k++){
			capBySub_DL[k] = 0;			
			for(int l=0;l<m;l++){
				capBySub_DL[k] += tab_bw_dl[k][l]*(1+poh[k][l])*penRate[k][l];
			}
		}		
		for(int k=0;k<n;k++){
			B += capBySub_DL[k]*param.getCustParam().getListSub().get(k).getPc_sub()/100;
		}
		nb_ab_DL = (int) Math.ceil(A/B);
		System.out.println("nb_ab_DL: "+nb_ab_DL);
		return nb_ab_DL;
	}
	
	public double calInterSiteDist(double c_rad){
		double dist_IS = Math.sqrt(3)*c_rad;
		System.out.println("dist_IS: "+dist_IS);
		return dist_IS;
	}
}