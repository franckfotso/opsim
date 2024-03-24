/**
 * 
 */
package cm.opsim.dao;

import cm.opsim.model.Project;

/**
 * @author Romuald FOTSO
 *
 */
public interface IProjectDAO {
	
	public abstract Project find(String name);
}


