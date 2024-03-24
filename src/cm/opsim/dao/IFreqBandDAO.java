/**
 * 
 */
package cm.opsim.dao;

import cm.opsim.model.FreqBand;

/**
 * @author Romuald FOTSO
 *
 */
public interface IFreqBandDAO {
	
	public abstract FreqBand find(String name);
}


