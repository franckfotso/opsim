/**
 * 
 */
package cm.opsim.model;

import java.io.Serializable;
import java.util.ArrayList;

import cm.opsim.observer.Observable;
import cm.opsim.observer.Observer;

/**
 * @author Romuald FOTSO
 *
 */
public abstract class AbstractModel implements Observable, Serializable {

	private  ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	@Override
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}

	@Override
	public void removeObserver() {
		this.listObserver = new ArrayList<Observer>();
	}
		
	@Override
	public void notifyObserver() {
		for(Observer obs : listObserver)
			obs.update(this);
	}
}
