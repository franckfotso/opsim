/**
 * 
 */
package cm.opsim.controller;

import java.util.ArrayList;
import cm.opsim.model.AbstractModel;
import cm.opsim.model.Project;
import cm.opsim.model.Result;

/**
 * @author Romuald FOTSO
 *
 */
public abstract class AbstractController {

	protected AbstractModel model;
	/**
	 * 
	 */
	public AbstractController(AbstractModel model) {
		this.model = model;
	}
	
	abstract void control();

	public AbstractModel getModel() {
		return model;
	}

	public void setModel(AbstractModel model) {
		this.model = model;
	}
	
	abstract public Result calResult(AbstractModel model);
	

}
