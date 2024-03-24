/**
 * 
 */
package cm.opsim.model;

/**
 * @author Romuald FOTSO
 *
 */
public class Service extends AbstractModel{

	private int id = 0;
	private String name;
	private String description;
	
	
	public Service() {
		super();
	}


	/**
	 * @param id
	 * @param sub_id
	 * @param name
	 * @param description
	 */
	public Service(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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
}
