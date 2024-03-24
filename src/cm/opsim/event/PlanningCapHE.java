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
public class PlanningCapHE extends AbstractHandleEvent{

	/**
	 * 
	 */
	public PlanningCapHE(OpsimGUI gui) {
		super(gui);
	}
	
	public PlanningCapHE(JDialog dialog) {
		super(dialog);
	}

}
