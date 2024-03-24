package cm.opsim.model;
/***********************************************************************
 * Module:  OptionSystem.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class OptionSystem
 ***********************************************************************/


/** @pdOid fa03c614-20e5-4cdb-8bec-60f211b462cb */
public class OptionSystem extends AbstractModel{
   /** @pdOid ed39a820-c4b1-4961-9a52-5cc0200aed0b */
   private int id;
   
   private User user;

	/**
	 * @param id
	 * @param user
	 */
	public OptionSystem(int id, User user) {
		super();
		this.id = id;
		this.user = user;
	}
	
	public OptionSystem(){
		super();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}


}