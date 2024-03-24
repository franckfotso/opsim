/**
 * 
 */
package cm.opsim.model;

/**
 * @author Romuald FOTSO
 *
 */
public class PrevIncomings extends AbstractModel{

	private int id = 0;
	private int opexParam_id;
	private double inByCust;
	private double custRate;
	/**
	 * 
	 */
	public PrevIncomings() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param id
	 * @param opexParam_id
	 * @param inByCust
	 * @param custRate
	 */
	public PrevIncomings(int id, int opexParam_id, double inByCust,
			double custRate) {
		super();
		this.id = id;
		this.opexParam_id = opexParam_id;
		this.inByCust = inByCust;
		this.custRate = custRate;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getOpexParam_id() {
		return opexParam_id;
	}

	public void setOpexParam_id(int opexParam_id) {
		this.opexParam_id = opexParam_id;
	}

	public double getInByCust() {
		return inByCust;
	}
	public void setInByCust(double inByCust) {
		this.inByCust = inByCust;
	}
	public double getCustRate() {
		return custRate;
	}
	public void setCustRate(double custRate) {
		this.custRate = custRate;
	}
	
	
}
