/**
 * 
 */
package cm.opsim.model;

/**
 * @author Romuald FOTSO
 *
 */
public class CapexParameter  extends AbstractModel{

	private int id = 0;
	private double ingRadio;
	private double ingReseau;
	private double rechFrs;
	private double rechSites;
	private double locaSites;
	private double genieCivil;
	private double racElec;
	private double installStation;
	private double racRxIP;
	private double equipRx;
	private double infraIP;
	private double logiApps;
	private double infraVOIP;
	private double infraRadio;
	private double infraRx;
	private double locauxMobilers;
	private double suiviProj;
	/**
	 * 
	 */
	public CapexParameter() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param ingRadio
	 * @param ingReseau
	 * @param rechFrs
	 * @param rechSites
	 * @param locaSites
	 * @param genieCivil
	 * @param racElec
	 * @param installStation
	 * @param racRxIP
	 * @param equipRx
	 * @param infraIP
	 * @param logiApps
	 * @param infraVOIP
	 * @param infraRadio
	 * @param infraRx
	 * @param locauxMobilers
	 * @param suiviProj
	 */
	public CapexParameter(int id, double ingRadio, double ingReseau,
			double rechFrs, double rechSites, double locaSites,
			double genieCivil, double racElec, double installStation,
			double racRxIP, double equipRx, double infraIP, double logiApps,
			double infraVOIP, double infraRadio, double infraRx,
			double locauxMobilers, double suiviProj) {
		super();
		this.id = id;
		this.ingRadio = ingRadio;
		this.ingReseau = ingReseau;
		this.rechFrs = rechFrs;
		this.rechSites = rechSites;
		this.locaSites = locaSites;
		this.genieCivil = genieCivil;
		this.racElec = racElec;
		this.installStation = installStation;
		this.racRxIP = racRxIP;
		this.equipRx = equipRx;
		this.infraIP = infraIP;
		this.logiApps = logiApps;
		this.infraVOIP = infraVOIP;
		this.infraRadio = infraRadio;
		this.infraRx = infraRx;
		this.locauxMobilers = locauxMobilers;
		this.suiviProj = suiviProj;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getIngRadio() {
		return ingRadio;
	}
	public void setIngRadio(double ingRadio) {
		this.ingRadio = ingRadio;
	}
	public double getIngReseau() {
		return ingReseau;
	}
	public void setIngReseau(double ingReseau) {
		this.ingReseau = ingReseau;
	}
	public double getRechFrs() {
		return rechFrs;
	}
	public void setRechFrs(double rechFrs) {
		this.rechFrs = rechFrs;
	}
	public double getRechSites() {
		return rechSites;
	}
	public void setRechSites(double rechSites) {
		this.rechSites = rechSites;
	}
	public double getLocaSites() {
		return locaSites;
	}
	public void setLocaSites(double locaSites) {
		this.locaSites = locaSites;
	}
	public double getGenieCivil() {
		return genieCivil;
	}
	public void setGenieCivil(double genieCivil) {
		this.genieCivil = genieCivil;
	}
	public double getRacElec() {
		return racElec;
	}
	public void setRacElec(double racElec) {
		this.racElec = racElec;
	}
	public double getInstallStation() {
		return installStation;
	}
	public void setInstallStation(double installStation) {
		this.installStation = installStation;
	}
	public double getRacRxIP() {
		return racRxIP;
	}
	public void setRacRxIP(double racRxIP) {
		this.racRxIP = racRxIP;
	}
	public double getEquipRx() {
		return equipRx;
	}
	public void setEquipRx(double equipRx) {
		this.equipRx = equipRx;
	}
	public double getInfraIP() {
		return infraIP;
	}
	public void setInfraIP(double infraIP) {
		this.infraIP = infraIP;
	}
	public double getLogiApps() {
		return logiApps;
	}
	public void setLogiApps(double logiApps) {
		this.logiApps = logiApps;
	}
	public double getInfraVOIP() {
		return infraVOIP;
	}
	public void setInfraVOIP(double infraVOIP) {
		this.infraVOIP = infraVOIP;
	}
	public double getInfraRadio() {
		return infraRadio;
	}
	public void setInfraRadio(double infraRadio) {
		this.infraRadio = infraRadio;
	}
	public double getInfraRx() {
		return infraRx;
	}
	public void setInfraRx(double infraRx) {
		this.infraRx = infraRx;
	}
	public double getLocauxMobilers() {
		return locauxMobilers;
	}
	public void setLocauxMobilers(double locauxMobilers) {
		this.locauxMobilers = locauxMobilers;
	}
	public double getSuiviProj() {
		return suiviProj;
	}
	public void setSuiviProj(double suiviProj) {
		this.suiviProj = suiviProj;
	}
	
	
	
}
