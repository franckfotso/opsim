/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.util.ArrayList;

import cm.opsim.model.FinanParameter;

/**
 * @author Romuald FOTSO
 *
 */
public class FinanParameterDAO extends DAO<FinanParameter> {

	public FinanParameterDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public FinanParameter create(FinanParameter obj) {
		// TODO Auto-generated method stub
		return obj;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(FinanParameter obj) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#update(java.lang.Object)
	 */
	@Override
	public FinanParameter update(FinanParameter obj) {
		// TODO Auto-generated method stub
		return obj;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#find(int)
	 */
	@Override
	public FinanParameter find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<FinanParameter> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
