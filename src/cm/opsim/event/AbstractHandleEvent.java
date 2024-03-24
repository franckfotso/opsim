/**
 * 
 */
package cm.opsim.event;

import javax.swing.JDialog;

import cm.opsim.view.OpsimGUI;

/**
 * @author Romuald FOTSO
 *
 */
public abstract class AbstractHandleEvent {
	protected OpsimGUI opsimGUI;
	protected JDialog dialog;
	/**
	 * 
	 */
	public AbstractHandleEvent() {
		// TODO Auto-generated constructor stub
	}
	
	public AbstractHandleEvent(OpsimGUI opsimGUI) {
		this.opsimGUI = opsimGUI;
	}
	
	public AbstractHandleEvent(JDialog dialog) {
		this.dialog = dialog;
	}

}
