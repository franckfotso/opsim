/**
 * 
 */
package cm.opsim.model;

/**
 * @author Romuald FOTSO
 *
 */
public class ROpex extends AbstractModel{

	private int id = 0;
	private int Result_id;
	private int year;
	private double opex;
	private double inCom;
	private double benef;
	/**
	 * 
	 */
	public ROpex() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * @param id
	 * @param result_id
	 * @param year
	 * @param opex
	 * @param inCom
	 * @param benef
	 */
	public ROpex(int id, int result_id, int year, double opex, double inCom,
			double benef) {
		super();
		this.id = id;
		Result_id = result_id;
		this.year = year;
		this.opex = opex;
		this.inCom = inCom;
		this.benef = benef;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getResult_id() {
		return Result_id;
	}
	public void setResult_id(int result_id) {
		Result_id = result_id;
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public double getOpex() {
		return opex;
	}
	public void setOpex(double opex) {
		this.opex = opex;
	}
	public double getInCom() {
		return inCom;
	}
	public void setInCom(double inCom) {
		this.inCom = inCom;
	}
	public double getBenef() {
		return benef;
	}
	public void setBenef(double benef) {
		this.benef = benef;
	}	
}
