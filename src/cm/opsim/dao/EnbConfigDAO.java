/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.util.ArrayList;

import cm.opsim.model.EnbConfig;

/**
 * @author Romuald FOTSO
 *
 */
public class EnbConfigDAO extends DAO<EnbConfig> {

	/**
	 * @param conn
	 */
	public EnbConfigDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public EnbConfig create(EnbConfig obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(EnbConfig obj) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#update(java.lang.Object)
	 */
	@Override
	public EnbConfig update(EnbConfig obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#find(int)
	 */
	@Override
	public EnbConfig find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<EnbConfig> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
