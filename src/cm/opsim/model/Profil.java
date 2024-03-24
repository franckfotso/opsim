/**
 * 
 */
package cm.opsim.model;

/**
 * @author Romuald FOTSO
 *
 */
public class Profil extends AbstractModel {

	private int id = 0;
	private boolean showManProj = true;
	
	/**
	 * @param id
	 * @param showManProj
	 * @param user
	 */
	public Profil(int id, boolean showManProj) {
		super();
		this.id = id;
		this.showManProj = showManProj;
	}

	public Profil() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isShowManProj() {
		return showManProj;
	}

	public void setShowManProj(boolean showManProj) {
		this.showManProj = showManProj;
	}
	
	

}
