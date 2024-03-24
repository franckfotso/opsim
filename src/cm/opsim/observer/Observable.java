/**
 * 
 */
package cm.opsim.observer;

/**
 * @author Romuald FOTSO
 *
 */
public interface Observable {

	public void addObserver(Observer obs);
	
	public void removeObserver();
	
	public void notifyObserver();
}
