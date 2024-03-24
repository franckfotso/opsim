/**
 * 
 */
package cm.opsim.dao;

import cm.opsim.model.AntennaType;

/**
 * @author Romuald FOTSO
 *
 */
public interface IAntennaTypeDAO {
	
	public abstract AntennaType find(String name);
}


