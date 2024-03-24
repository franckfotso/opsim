/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.util.ArrayList;

import cm.opsim.model.BtsConfig;

/**
 * @author Romuald FOTSO
 *
 */
public class BtsConfigDAO extends DAO<BtsConfig> {

	/**
	 * @param conn
	 */
	public BtsConfigDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public BtsConfig create(BtsConfig obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(BtsConfig obj) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#update(java.lang.Object)
	 */
	@Override
	public BtsConfig update(BtsConfig obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#find(int)
	 */
	@Override
	public BtsConfig find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<BtsConfig> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
