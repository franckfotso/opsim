/**
 * 
 */
package cm.opsim.model;

/**
 * @author Romuald FOTSO
 *
 */
public class ProjExterne extends AbstractModel{
	
	private int id = 0;
	private String pathOnDisk = null;
	
	/**
	 * @param id
	 * @param pathOnDisk
	 */
	public ProjExterne(int id, String pathOnDisk) {
		super();
		this.id = id;
		this.pathOnDisk = pathOnDisk;
	}

	public ProjExterne() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPathOnDisk() {
		return pathOnDisk;
	}

	public void setPathOnDisk(String pathOnDisk) {
		this.pathOnDisk = pathOnDisk;
	}
	
	

}
