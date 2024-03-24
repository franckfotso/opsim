/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.util.ArrayList;

import cm.opsim.model.ProjInterne;

/**
 * @author Romuald FOTSO
 *
 */
public class ProjInterneDAO extends DAO<ProjInterne> {

	public ProjInterneDAO(Connection conn){
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public ProjInterne create(ProjInterne obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(ProjInterne obj) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#update(java.lang.Object)
	 */
	@Override
	public ProjInterne update(ProjInterne obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#find(int)
	 */
	@Override
	public ProjInterne find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<ProjInterne> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
