/**
 * 
 */
package cm.opsim.model;

import java.util.ArrayList;

/**
 * @author Romuald FOTSO
 *
 */
public class Subscription extends AbstractModel{

	private int id = 0;
	private int CustParam_id;
	private String name;
	private String description;	
	private double pc_sub;
	private ArrayList<Service> listServ;	
	
	public Subscription() {
		super();
	}

	/**
	 * @param id
	 * @param custParam_id
	 * @param name
	 * @param description
	 * @param pc_sub
	 * @param listServ
	 */
	public Subscription(int id, int custParam_id, String name,
			String description, double pc_sub, ArrayList<Service> listServ) {
		super();
		this.id = id;
		CustParam_id = custParam_id;
		this.name = name;
		this.description = description;
		this.pc_sub = pc_sub;
		this.listServ = listServ;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Service> getListServ() {
		return listServ;
	}

	public void setListServ(ArrayList<Service> listServ) {
		this.listServ = listServ;
	}

	public int getCustParam_id() {
		return CustParam_id;
	}

	public void setCustParam_id(int custParam_id) {
		CustParam_id = custParam_id;
	}

	public double getPc_sub() {
		return pc_sub;
	}

	public void setPc_sub(double pc_sub) {
		this.pc_sub = pc_sub;
	}
	
}
