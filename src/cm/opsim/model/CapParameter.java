/**
 * 
 */
package cm.opsim.model;

/**
 * @author Romuald FOTSO
 *
 */
public class CapParameter extends AbstractModel {

	private int id = 0;
	private double soh_RS_UL, soh_PRACH_UL, soh_PUCCH_UL, cpi_UL, probaQPSK_1on2_UL, effQPSK_1on2_UL, obf_UL,
					proba16QAM_2on3_UL, eff16QAM_2on3_UL, proba64QAM_5on6_UL, eff64QAM_5on6_UL, probaSMgain_1_UL,
					probaSMgain_2_UL, probaSMgain_4_UL;
	private double soh_RS_DL, soh_PSS_SSS_DL, soh_PBCCH_DL, soh_L1_L2_DL, cpi_DL, probaQPSK_1on2_DL, effQPSK_1on2_DL, obf_DL,
					proba16QAM_2on3_DL, eff16QAM_2on3_DL, proba64QAM_5on6_DL, eff64QAM_5on6_DL, probaSMgain_1_DL,
					probaSMgain_2_DL, probaSMgain_4_DL, probaSMgain_8_DL;	
	
	public CapParameter() {
		super();
	}

	/**
	 * @param id
	 * @param soh_RS_UL
	 * @param soh_PRACH_UL
	 * @param soh_PUCCH_UL
	 * @param cpi_UL
	 * @param probaQPSK_1on2_UL
	 * @param effQPSK_1on2_UL
	 * @param obf_UL
	 * @param proba16qam_2on3_UL
	 * @param eff16qam_2on3_UL
	 * @param proba64qam_5on6_UL
	 * @param eff64qam_5on6_UL
	 * @param probaSMgain_1_UL
	 * @param probaSMgain_2_UL
	 * @param probaSMgain_4_UL
	 * @param soh_RS_DL
	 * @param soh_PSS_SSS_DL
	 * @param soh_PBCCH_DL
	 * @param soh_L1_L2_DL
	 * @param cpi_DL
	 * @param probaQPSK_1on2_DL
	 * @param effQPSK_1on2_DL
	 * @param obf_DL
	 * @param proba16qam_2on3_DL
	 * @param eff16qam_2on3_DL
	 * @param proba64qam_5on6_DL
	 * @param eff64qam_5on6_DL
	 * @param probaSMgain_1_DL
	 * @param probaSMgain_2_DL
	 * @param probaSMgain_4_DL
	 * @param probaSMgain_8_DL
	 */
	public CapParameter(int id, double soh_RS_UL, double soh_PRACH_UL,
			double soh_PUCCH_UL, double cpi_UL, double probaQPSK_1on2_UL,
			double effQPSK_1on2_UL, double obf_UL, double proba16qam_2on3_UL,
			double eff16qam_2on3_UL, double proba64qam_5on6_UL,
			double eff64qam_5on6_UL, double probaSMgain_1_UL,
			double probaSMgain_2_UL, double probaSMgain_4_UL, double soh_RS_DL,
			double soh_PSS_SSS_DL, double soh_PBCCH_DL, double soh_L1_L2_DL,
			double cpi_DL, double probaQPSK_1on2_DL, double effQPSK_1on2_DL,
			double obf_DL, double proba16qam_2on3_DL, double eff16qam_2on3_DL,
			double proba64qam_5on6_DL, double eff64qam_5on6_DL,
			double probaSMgain_1_DL, double probaSMgain_2_DL,
			double probaSMgain_4_DL, double probaSMgain_8_DL) {
		super();
		this.id = id;
		this.soh_RS_UL = soh_RS_UL;
		this.soh_PRACH_UL = soh_PRACH_UL;
		this.soh_PUCCH_UL = soh_PUCCH_UL;
		this.cpi_UL = cpi_UL;
		this.probaQPSK_1on2_UL = probaQPSK_1on2_UL;
		this.effQPSK_1on2_UL = effQPSK_1on2_UL;
		this.obf_UL = obf_UL;
		proba16QAM_2on3_UL = proba16qam_2on3_UL;
		eff16QAM_2on3_UL = eff16qam_2on3_UL;
		proba64QAM_5on6_UL = proba64qam_5on6_UL;
		eff64QAM_5on6_UL = eff64qam_5on6_UL;
		this.probaSMgain_1_UL = probaSMgain_1_UL;
		this.probaSMgain_2_UL = probaSMgain_2_UL;
		this.probaSMgain_4_UL = probaSMgain_4_UL;
		this.soh_RS_DL = soh_RS_DL;
		this.soh_PSS_SSS_DL = soh_PSS_SSS_DL;
		this.soh_PBCCH_DL = soh_PBCCH_DL;
		this.soh_L1_L2_DL = soh_L1_L2_DL;
		this.cpi_DL = cpi_DL;
		this.probaQPSK_1on2_DL = probaQPSK_1on2_DL;
		this.effQPSK_1on2_DL = effQPSK_1on2_DL;
		this.obf_DL = obf_DL;
		proba16QAM_2on3_DL = proba16qam_2on3_DL;
		eff16QAM_2on3_DL = eff16qam_2on3_DL;
		proba64QAM_5on6_DL = proba64qam_5on6_DL;
		eff64QAM_5on6_DL = eff64qam_5on6_DL;
		this.probaSMgain_1_DL = probaSMgain_1_DL;
		this.probaSMgain_2_DL = probaSMgain_2_DL;
		this.probaSMgain_4_DL = probaSMgain_4_DL;
		this.probaSMgain_8_DL = probaSMgain_8_DL;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSoh_RS_UL() {
		return soh_RS_UL;
	}

	public void setSoh_RS_UL(double soh_RS_UL) {
		this.soh_RS_UL = soh_RS_UL;
	}

	public double getSoh_PRACH_UL() {
		return soh_PRACH_UL;
	}

	public void setSoh_PRACH_UL(double soh_PRACH_UL) {
		this.soh_PRACH_UL = soh_PRACH_UL;
	}

	public double getSoh_PUCCH_UL() {
		return soh_PUCCH_UL;
	}

	public void setSoh_PUCCH_UL(double soh_PUCCH_UL) {
		this.soh_PUCCH_UL = soh_PUCCH_UL;
	}

	public double getCpi_UL() {
		return cpi_UL;
	}

	public void setCpi_UL(double cpi_UL) {
		this.cpi_UL = cpi_UL;
	}

	public double getProbaQPSK_1on2_UL() {
		return probaQPSK_1on2_UL;
	}

	public void setProbaQPSK_1on2_UL(double probaQPSK_1on2_UL) {
		this.probaQPSK_1on2_UL = probaQPSK_1on2_UL;
	}

	public double getEffQPSK_1on2_UL() {
		return effQPSK_1on2_UL;
	}

	public void setEffQPSK_1on2_UL(double effQPSK_1on2_UL) {
		this.effQPSK_1on2_UL = effQPSK_1on2_UL;
	}

	public double getObf_UL() {
		return obf_UL;
	}

	public void setObf_UL(double obf_UL) {
		this.obf_UL = obf_UL;
	}

	public double getProba16QAM_2on3_UL() {
		return proba16QAM_2on3_UL;
	}

	public void setProba16QAM_2on3_UL(double proba16qam_2on3_UL) {
		proba16QAM_2on3_UL = proba16qam_2on3_UL;
	}

	public double getEff16QAM_2on3_UL() {
		return eff16QAM_2on3_UL;
	}

	public void setEff16QAM_2on3_UL(double eff16qam_2on3_UL) {
		eff16QAM_2on3_UL = eff16qam_2on3_UL;
	}

	public double getProba64QAM_5on6_UL() {
		return proba64QAM_5on6_UL;
	}

	public void setProba64QAM_5on6_UL(double proba64qam_5on6_UL) {
		proba64QAM_5on6_UL = proba64qam_5on6_UL;
	}

	public double getEff64QAM_5on6_UL() {
		return eff64QAM_5on6_UL;
	}

	public void setEff64QAM_5on6_UL(double eff64qam_5on6_UL) {
		eff64QAM_5on6_UL = eff64qam_5on6_UL;
	}

	public double getProbaSMgain_1_UL() {
		return probaSMgain_1_UL;
	}

	public void setProbaSMgain_1_UL(double probaSMgain_1_UL) {
		this.probaSMgain_1_UL = probaSMgain_1_UL;
	}

	public double getProbaSMgain_2_UL() {
		return probaSMgain_2_UL;
	}

	public void setProbaSMgain_2_UL(double probaSMgain_2_UL) {
		this.probaSMgain_2_UL = probaSMgain_2_UL;
	}

	public double getProbaSMgain_4_UL() {
		return probaSMgain_4_UL;
	}

	public void setProbaSMgain_4_UL(double probaSMgain_4_UL) {
		this.probaSMgain_4_UL = probaSMgain_4_UL;
	}

	public double getSoh_RS_DL() {
		return soh_RS_DL;
	}

	public void setSoh_RS_DL(double soh_RS_DL) {
		this.soh_RS_DL = soh_RS_DL;
	}

	public double getSoh_PSS_SSS_DL() {
		return soh_PSS_SSS_DL;
	}

	public void setSoh_PSS_SSS_DL(double soh_PSS_SSS_DL) {
		this.soh_PSS_SSS_DL = soh_PSS_SSS_DL;
	}

	public double getSoh_PBCCH_DL() {
		return soh_PBCCH_DL;
	}

	public void setSoh_PBCCH_DL(double soh_PBCCH_DL) {
		this.soh_PBCCH_DL = soh_PBCCH_DL;
	}

	public double getSoh_L1_L2_DL() {
		return soh_L1_L2_DL;
	}

	public void setSoh_L1_L2_DL(double soh_L1_L2_DL) {
		this.soh_L1_L2_DL = soh_L1_L2_DL;
	}

	public double getCpi_DL() {
		return cpi_DL;
	}

	public void setCpi_DL(double cpi_DL) {
		this.cpi_DL = cpi_DL;
	}

	public double getProbaQPSK_1on2_DL() {
		return probaQPSK_1on2_DL;
	}

	public void setProbaQPSK_1on2_DL(double probaQPSK_1on2_DL) {
		this.probaQPSK_1on2_DL = probaQPSK_1on2_DL;
	}

	public double getEffQPSK_1on2_DL() {
		return effQPSK_1on2_DL;
	}

	public void setEffQPSK_1on2_DL(double effQPSK_1on2_DL) {
		this.effQPSK_1on2_DL = effQPSK_1on2_DL;
	}

	public double getObf_DL() {
		return obf_DL;
	}

	public void setObf_DL(double obf_DL) {
		this.obf_DL = obf_DL;
	}

	public double getProba16QAM_2on3_DL() {
		return proba16QAM_2on3_DL;
	}

	public void setProba16QAM_2on3_DL(double proba16qam_2on3_DL) {
		proba16QAM_2on3_DL = proba16qam_2on3_DL;
	}

	public double getEff16QAM_2on3_DL() {
		return eff16QAM_2on3_DL;
	}

	public void setEff16QAM_2on3_DL(double eff16qam_2on3_DL) {
		eff16QAM_2on3_DL = eff16qam_2on3_DL;
	}

	public double getProba64QAM_5on6_DL() {
		return proba64QAM_5on6_DL;
	}

	public void setProba64QAM_5on6_DL(double proba64qam_5on6_DL) {
		proba64QAM_5on6_DL = proba64qam_5on6_DL;
	}

	public double getEff64QAM_5on6_DL() {
		return eff64QAM_5on6_DL;
	}

	public void setEff64QAM_5on6_DL(double eff64qam_5on6_DL) {
		eff64QAM_5on6_DL = eff64qam_5on6_DL;
	}

	public double getProbaSMgain_1_DL() {
		return probaSMgain_1_DL;
	}

	public void setProbaSMgain_1_DL(double probaSMgain_1_DL) {
		this.probaSMgain_1_DL = probaSMgain_1_DL;
	}

	public double getProbaSMgain_2_DL() {
		return probaSMgain_2_DL;
	}

	public void setProbaSMgain_2_DL(double probaSMgain_2_DL) {
		this.probaSMgain_2_DL = probaSMgain_2_DL;
	}

	public double getProbaSMgain_4_DL() {
		return probaSMgain_4_DL;
	}

	public void setProbaSMgain_4_DL(double probaSMgain_4_DL) {
		this.probaSMgain_4_DL = probaSMgain_4_DL;
	}

	public double getProbaSMgain_8_DL() {
		return probaSMgain_8_DL;
	}

	public void setProbaSMgain_8_DL(double probaSMgain_8_DL) {
		this.probaSMgain_8_DL = probaSMgain_8_DL;
	}
	
	

}
