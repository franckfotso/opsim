package cm.opsim.model;
/***********************************************************************
 * Module:  CoveragePlanif.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class CoveragePlanif
 ***********************************************************************/

import java.util.Date;
import java.util.Hashtable;

/** @pdOid 04f9bdd2-d07a-44ea-9feb-01d86703f433 */
public class CoveragePlanif extends AbstractModel{
	
	private int id = 0;
	private String state = "INIT";
	private double P_TX_RB, Bi_DL;
	
	public CoveragePlanif() {
		super();
	}	
	
	/**
	 * @param id
	 * @param state
	 */
	public CoveragePlanif(int id, String state) {
		super();
		this.id = id;
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public double calBwByPRB_UL(Parameter param){
		return param.getGenParam().getBwCellBoardUL()/param.getGenParam().getNumPRBUl();
	}	
	public double calBwByPRB_DL(Parameter param){
		return param.getGenParam().getBwCellBoardDL()/param.getGenParam().getNumPRBUl();
	}
	
	/*
	 * Calculate MAPL (Maximum Available Path Loss) UL & DL
	 */
	public double calMAPL_UL(Parameter param){
		double Bi_UL = 1/(1-(param.getUeParam().getTargetSNIR()*param.getUeParam().getSystCharge()
							*param.getUeParam().getFactorF()));
		double P_UE_RB = param.getUeParam().getPowerUE()
							/param.getGenParam().getNumPRBUl();
		
		return P_UE_RB - this.calSensibility_ENB(param) - Bi_UL - param.getGenParam().getFadingMargin()
						- param.getGenParam().getBodyLoss() - param.getGenParam().getCarLoss() 
						- param.getGenParam().getBuildingLoss() + param.getUeParam().getAntennaGain() 
						- param.getUeParam().getJumperTmaLoss();
	}	
	public double calMAPL_DL(Parameter param){
		double P_nom_ref = param.getEnbParam().getPowerTx() ;
		String mimoType = param.getOtherParam().getMimoType();
		switch(mimoType){
			case "MIMO1x1":	
				P_nom_ref = P_nom_ref*1;
				break;
			case "MIMO2x2":	
				P_nom_ref = P_nom_ref*2;
				break;
			case "MIMO3x3":	
				P_nom_ref = P_nom_ref*3;
				break;
			case "MIMO4x4":	
				P_nom_ref = P_nom_ref*4;
				break;
			default:
				P_nom_ref = P_nom_ref*1;
				break;
		}
		
		double Lsa_cellRang = this.calMAPL_UL(param) + param.getGenParam().getBodyLoss() + param.getGenParam().getCarLoss()
								+ param.getGenParam().getBuildingLoss() - param.getUeParam().getAntennaGain()
								+ param.getUeParam().getJumperTmaLoss();		
		this.P_TX_RB = P_nom_ref/param.getGenParam().getNumPRBUl();
		this.Bi_DL = 1 + (P_TX_RB*param.getEnbParam().getSystemCharge()*param.getEnbParam().getFactorFC())
							/(param.getGenParam().getNumPRBUl()*Lsa_cellRang) ;		
		return P_TX_RB - calSensibility_UE(param) - Bi_DL - param.getGenParam().getFadingMargin()
				- param.getGenParam().getBodyLoss() - param.getGenParam().getCarLoss() - param.getGenParam().getBuildingLoss()
				+ param.getEnbParam().getAntennaGain() - param.getEnbParam().getJumperTmaLoss();
	}
	
	public double calPire_UL(Parameter param){
		return param.getUeParam().getPowerUE()+param.getUeParam().getAntennaGain()-param.getGenParam().getBodyLoss();
	}
	public double calPire_DL(Parameter param){
		return param.getEnbParam().getPowerTx()+param.getEnbParam().getAntennaGain()-param.getGenParam().getBodyLoss();
	}
	
	public double calSensibility_ENB(Parameter param){
		return param.getEnbParam().getCellBoardSNIR()+this.calThermNoise_DL(param)+param.getGenParam().getFigureNoise();
	}
	public double calSensibility_UE(Parameter param){
		return param.getUeParam().getTargetSNIR()+this.calThermNoise_UL(param)+param.getGenParam().getFigureNoise();		
	}
	
	public double calThermNoise_UL(Parameter param){
		//return -174+10*Math.log10(param.getGenParam().getNumPRBUl()*param.getGenParam().getBwByPRB());
		return param.getEnbParam().getThermicNoiseByRB();
	}
	public double calThermNoise_DL(Parameter param){
		//return -174+10*Math.log10(param.getGenParam().getNumPRBUl()*param.getGenParam().getBwByPRB());
		return param.getUeParam().getThermicNoiseByRB();
	}
	
	public double calBwCellBoard(Parameter param) throws Exception{
		double SNIR_DL = this.P_TX_RB - calMAPL_DL(param) - param.getGenParam().getNumPRBUl() - this.Bi_DL - param.getGenParam().getFadingMargin()
				- param.getGenParam().getBodyLoss() - param.getGenParam().getCarLoss() - param.getGenParam().getBuildingLoss()
				+ param.getEnbParam().getAntennaGain() - param.getEnbParam().getJumperTmaLoss();
		String anType = param.getAntParam().getAntennaType().getName();
		String chModel = param.getGenParam().getChannelModel();
		double a0, a0_max, a1, a2, a3, a3_max, bwByRB;
		
		if(anType == "SIMO1x2"){
			if(chModel == "EPA5"){a0_max=912.1; a1=27.00; a2=16.03; a3_max=-10.5;}
			else if(chModel == "EVA70"){a0_max=912.4; a1=29.34; a2=15.90; a3_max=-4.4;}
			else if(chModel == "ETU300"){a0_max=799.9; a1=27.75; a2=15.34; a3_max=-5.3;}
			else throw new Exception("Error: Channel Model doest exist");
		}
		else if(anType == "TxDiv2x2"){
			if(chModel == "EPA5"){a0_max=914.2; a1=25.92; a2=16.01; a3_max=-16.2;}
			else if(chModel == "EVA70"){a0_max=913.8; a1=27.17; a2=15.38; a3_max=-6.4;}
			else if(chModel == "ETU300"){a0_max=887.7; a1=27.70; a2=15.49; a3_max=-7.3;}
			else throw new Exception("Error: Channel Model doest exist");
		}
		else if(anType == "OLSM2x2"){
			if(chModel == "EPA5"){a0_max=1583.8; a1=34.03; a2=18.37; a3_max=-18.6;}
			else if(chModel == "EVA70"){a0_max=1409.5; a1=34.99; a2=18.16; a3_max=-10.2;}
			else if(chModel == "ETU300"){a0_max=1162.8; a1=31.93; a2=16.84; a3_max=-8.4;}
			else throw new Exception("Error: Channel Model doest exist");
		}
		else throw new Exception("Error: Antenna type doest exist");
		
		a0 = a0_max;
		a3 = a3_max;		
		if(SNIR_DL < a1)bwByRB = Math.max(0, a3+
				(a0-a3)*Math.exp(-Math.log(2)*Math.pow((SNIR_DL-a1)/a2, 2)));
		else bwByRB = a0;
		
		return bwByRB;
	}
	
	/*
	 *  PM: COST231 Walfish-Ikegami
	 */
	public double calCellRadius_COST231_WI(double f, double H_b, double H_m) throws Exception{
		if(800 <= f && f<=2000){
			if(4 <= H_b && H_b<=50){
				if(1 <= H_m && H_m<=3){
					
					
				}else throw new Exception("Error Propagation Model: Height UE doesnt match");
			}else throw new Exception("Error Propagation Model: Height ENB doesnt match");
		}else throw new Exception("Error Propagation Model: Frequency doesnt match");
		
		return 0.00;
	}
	
	/*
	 *  PM: Okumura Hata
	 */
	public double calCellRadius_OkumuraHata(double f, double H_b, double H_m, OkumuraHata oku, double MAPL) throws Exception{
		double c_rad = 0.00;
		if(150 <= f && f<=1500){
			if(30 <= H_b && H_b<=200){
				if(1 <= H_m && H_m<=100){
					String zoneType = oku.getZoneType();
					double a_Hm, A, B, C, D, E;
					
					switch(zoneType){
						case "Urbain dense":
							if(f <= 200)a_Hm = 8.29*Math.pow(Math.log10(1.54*H_m),2) - 1.1;
							else a_Hm = 3.2*Math.pow(Math.log10(11.75*H_m),2) - 4.9;
							A = 65.5+26.16*Math.log10(f);
							B = 13.82*Math.log10(H_b);
							C = 44.9-6.55*Math.log10(H_b);
							c_rad = Math.pow(10, (MAPL-A+B+a_Hm)/C);
							break;
						case "Urbain":
							a_Hm = (1.1*Math.log10(f)-0.7)*H_m - (1.56*Math.log10(f)-0.8);
							A = 65.5+26.16*Math.log10(f);
							B = 13.82*Math.log10(H_b);
							C = 44.9-6.55*Math.log10(H_b);
							c_rad = Math.pow(10, (MAPL-A+B+a_Hm)/C);
							break;
						case "Sous urbain":
							a_Hm = (1.1*Math.log10(f)-0.7)*H_m - (1.56*Math.log10(f)-0.8);
							A = 65.5+26.16*Math.log10(f);
							B = 13.82*Math.log10(H_b);
							C = 44.9-6.55*Math.log10(H_b);
							D = 2*Math.pow(Math.log10(f/28), 2)+5.4;
							c_rad = Math.pow(10, (MAPL-A+B+a_Hm+D)/C);
							break;
						case "Rural":
							a_Hm = (1.1*Math.log10(f)-0.7)*H_m - (1.56*Math.log10(f)-0.8);
							A = 65.5+26.16*Math.log10(f);
							B = 13.82*Math.log10(H_b);
							C = 44.9-6.55*Math.log10(H_b);
							E = 4.78*Math.pow(Math.log10(f),2)-18.33*Math.log10(f)+40.94;
							c_rad = Math.pow(10, (MAPL-A+B+a_Hm+E)/C);
							break;
						default:
							throw new Exception("Error Propagation Model: Zone type undefined");
						
					}
					
				}else throw new Exception("Error Propagation Model: Height UE doesnt match");
			}else throw new Exception("Error Propagation Model: Height ENB doesnt match");
		}else throw new Exception("Error Propagation Model: Frequency doesnt match");
		if(c_rad == 0.00) throw new Exception("Error Propagation Model: Null cell radius calculated");
		//System.out.println("Cell radius: "+c_rad);
		return c_rad;
	}
	
	/*
	 *  PM: COST231 Hata
	 */
	public double calCellRadius_COST231_Hata(double f, double H_b, double H_m, Cost231Hata co, double MAPL) throws Exception{
		double c_rad = 0.00;
		String zoneType = co.getZoneType();
		if(1500 <= f && f<=2000){
			double a_Hm, A, B, C, Cm;
			switch(zoneType){
				case "Dense urbain": // ville grande
					a_Hm = 3.2*Math.pow(Math.log10(11.75*H_m), 2)-4.97;
					A = 45.6+33.9*Math.log10(f);
					B = 13.82*Math.log10(H_b);
					C = 44.9-6.55*Math.log10(H_b);
					Cm = 3;
					c_rad = Math.pow(10, (MAPL-A+B+a_Hm-Cm)/C);
					break;
				case "Urbain":	// ville moyenne
					a_Hm = (1.1*Math.log10(f)-0.7)*H_m - (1.56*Math.log10(f)-0.8);
					A = 45.6+33.9*Math.log10(f);
					B = 13.82*Math.log10(H_b);
					C = 44.9-6.55*Math.log10(H_b);
					Cm = 0;
					c_rad = Math.pow(10, (MAPL-A+B+a_Hm-Cm)/C);
					break;
				case "Sous urbain": // ville petite
					a_Hm = (1.1*Math.log10(f)-0.7)*H_m - (1.56*Math.log10(f)-0.8);
					A = 45.6+33.9*Math.log10(f);
					B = 13.82*Math.log10(H_b);
					C = 44.9-6.55*Math.log10(H_b);
					Cm = 0;
					c_rad = Math.pow(10, (MAPL-A+B+a_Hm-Cm)/C);
					break;
				default:
					throw new Exception("Error Propagation Model: Zone type undefined");
			}
		}else throw new Exception("Error Propagation Model: Frequency doesnt match");
		
		return c_rad;
	}
	
	/*
	 *  PM: K-Factors
	 */
	public double calData_K_Factors(){
		return 0.00;
	}	
	
	public double calCellPrint(double c_rad){	
		return 2.6*Math.pow(c_rad, 2);
	}
	
	public double calSitePrint(double c_rad, Parameter param) throws Exception{
		double s_print;
		String siteType = param.getEnbParam().getSiteType();
		switch(siteType){
			case "Mono-sectorise":
				s_print = 2.6*Math.pow(c_rad, 2);
				break;
			case "Bi-sectorie":
				s_print = 1.3*2.6*Math.pow(c_rad, 2);
				break;
			case "Tri-sectorise":
				s_print = 1.95*2.6*Math.pow(c_rad, 2);
				break;
			default:
				throw new Exception("Error Propagation Model: Site type undefined");
		}
		return s_print;
	}
	
	public int calNumENB(double s_print, Parameter param){
		System.out.println("Zone Area = "+param.getZoneParam().getArea());
		return (int)(Math.ceil(param.getZoneParam().getArea()/s_print));
	}
	
	public double calInterSiteDist(double c_rad){
		return Math.sqrt(3)*c_rad;
	}
	
}