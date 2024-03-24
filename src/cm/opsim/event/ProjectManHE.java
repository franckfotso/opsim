/**
 * 
 */
package cm.opsim.event;

import cm.opsim.view.DialogManProject;
import cm.opsim.view.OpsimGUI;

/**
 * @author Romuald FOTSO
 *
 */
public class ProjectManHE extends AbstractHandleEvent{

	
	public ProjectManHE(DialogManProject dialog) {
		super(dialog);
		this.handleProjectEvent();
	}
	
	/*
	 * Handle All ManProject Events
	 */
	public void handleProjectEvent(){
		
	}
}
