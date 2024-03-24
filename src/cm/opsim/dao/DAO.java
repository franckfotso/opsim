package cm.opsim.dao;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class DAO<T> {

	protected Connection conn = null;
	
	public DAO(Connection conn){
		this.conn = conn;
	}
	
	/**
	* Create method
	* @param obj
	* @return T
	*/
	public abstract T create(T obj);
	
	/**
	* Delete method
	* @param obj
	* @return void
	*/
	public abstract void delete(T obj);
	
	/**
	* Update method
	* @param obj
	* @return T
	*/
	public abstract T update(T obj);
	
	/**
	* Find method
	* @param id
	* @return T
	*/
	public abstract T find(int id);
	
	public abstract ArrayList<T> findAll();
}
