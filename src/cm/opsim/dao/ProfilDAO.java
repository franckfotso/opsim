/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.OptionSystem;
import cm.opsim.model.Profil;
import cm.opsim.model.User;

/**
 * @author Romuald FOTSO
 *
 */
public class ProfilDAO extends DAO<Profil> {

	public ProfilDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public Profil create(Profil obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Profil obj) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#update(java.lang.Object)
	 */
	@Override
	public Profil update(Profil obj) {
		try{
						
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE profil SET showManProj = ?"+
							" WHERE id = " +obj.getId());
				prepare1.setBoolean(1, obj.isShowManProj());
				prepare1.executeUpdate();
			
			obj = this.find(obj.getId());
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#find(int)
	 */
	@Override
	public Profil find(int id) {
		Profil profil = new Profil();
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM profil WHERE id = " +id
					);
			if(result1.first())
				profil = new Profil(
				id, 
				result1.getBoolean("showManProj")
				);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return profil;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<Profil> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
