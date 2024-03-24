/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.util.ArrayList;

import cm.opsim.model.ProjExterne;

/**
 * @author Romuald FOTSO
 *
 */
public class ProjExterneDAO extends DAO<ProjExterne> {

	public ProjExterneDAO(Connection conn){
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public ProjExterne create(ProjExterne obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(ProjExterne obj) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#update(java.lang.Object)
	 */
	@Override
	public ProjExterne update(ProjExterne obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#find(int)
	 */
	@Override
	public ProjExterne find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<ProjExterne> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
