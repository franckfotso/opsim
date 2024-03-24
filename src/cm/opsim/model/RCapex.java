/**
 * 
 */
package cm.opsim.model;

/**
 * @author Romuald FOTSO
 *
 */
public class RCapex extends AbstractModel{

	private int id = 0;
	private double conceptCost;
	private double invMatLogi;
	private double deployCost;
	private double logisCost;
	private double capexTotal;
	/**
	 * 
	 */
	public RCapex() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param conceptCost
	 * @param invMatLogi
	 * @param deployCost
	 * @param logisCost
	 * @param capexTotal
	 */
	public RCapex(int id, double conceptCost, double invMatLogi,
			double deployCost, double logisCost, double capexTotal) {
		super();
		this.id = id;
		this.conceptCost = conceptCost;
		this.invMatLogi = invMatLogi;
		this.deployCost = deployCost;
		this.logisCost = logisCost;
		this.capexTotal = capexTotal;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getConceptCost() {
		return conceptCost;
	}
	public void setConceptCost(double conceptCost) {
		this.conceptCost = conceptCost;
	}
	public double getInvMatLogi() {
		return invMatLogi;
	}
	public void setInvMatLogi(double invMatLogi) {
		this.invMatLogi = invMatLogi;
	}
	public double getDeployCost() {
		return deployCost;
	}
	public void setDeployCost(double deployCost) {
		this.deployCost = deployCost;
	}
	public double getLogisCost() {
		return logisCost;
	}
	public void setLogisCost(double logisCost) {
		this.logisCost = logisCost;
	}
	public double getCapexTotal() {
		return capexTotal;
	}
	public void setCapexTotal(double capexTotal) {
		this.capexTotal = capexTotal;
	}
	
	
}
