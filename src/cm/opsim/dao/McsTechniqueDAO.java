/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.*;

/**
 * @author Romuald FOTSO
 *
 */
public class McsTechniqueDAO extends DAO<McsTechnique> {

	public McsTechniqueDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public McsTechnique create(McsTechnique obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM mcstechnique "+
					"WHERE id = '"+obj.getId()+"'"
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("Mcs exist: start update ");
				obj.setId(id);
				obj = this.update(obj);
			}
			else
			{
				if(obj.getModulationType().getId() == 0){
					DAO<ModulationType>mcsDAO =DAOFactory.getModulationTypeDAO();
					obj.setModulationType(mcsDAO.create(obj.getModulationType()));
				}
				
				ResultSet result1 = this.conn
						.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_UPDATABLE
						).executeQuery(
						"SELECT AUTO_INCREMENT "+
						"FROM information_schema.tables "+
						"WHERE table_name = 'mcstechnique' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
						.prepareStatement(
						"INSERT INTO mcstechnique (Mod_id, mcsIndex, rateCodingUL, rateCodingDL, snirUl, snirDl)");
					prepare1.setInt(1, obj.getModulationType().getId());
					prepare1.setInt(2, obj.getMcsIndex());
					prepare1.setDouble(3, obj.getRateCodingUL());
					prepare1.setDouble(4, obj.getRateCodingDL());
					prepare1.setDouble(5, obj.getSnirUl());
					prepare1.setDouble(6, obj.getSnirDl());
					prepare1.executeUpdate();
					
					obj = this.find(id);
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(McsTechnique obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM mcstechnique WHERE id = " +obj.getId()
					);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}		
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#update(java.lang.Object)
	 */
	@Override
	public McsTechnique update(McsTechnique obj) {
		try{			
			DAO<ModulationType>mcsDAO =DAOFactory.getModulationTypeDAO();
			if(obj.getModulationType().getId() == 0){				
				obj.setModulationType(mcsDAO.create(obj.getModulationType()));
			}
			mcsDAO.update(obj.getModulationType());
			
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE mcstechnique SET Mod_id = ?, mcsIndex = ?, rateCodingUL = ?, rateCodingDL = ?, snirUl = ?, 	snirDl = ?"+
							" WHERE id = " +obj.getId());
			prepare1.setInt(1, obj.getModulationType().getId());
			prepare1.setInt(2, obj.getMcsIndex());
			prepare1.setDouble(3, obj.getRateCodingUL());
			prepare1.setDouble(4, obj.getRateCodingDL());
			prepare1.setDouble(5, obj.getSnirUl());
			prepare1.setDouble(6, obj.getSnirDl());
			prepare1.executeUpdate();
			
			obj = this.find(obj.getId());
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#find(int)
	 */
	@Override
	public McsTechnique find(int id) {
		McsTechnique mcs = new McsTechnique();
		
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM mcstechnique WHERE id = " +id
					);
			if(result1.first())
				mcs = new McsTechnique(
				id,  
				result1.getInt("mcsIndex"),
				result1.getDouble("rateCodingUL"),
				result1.getDouble("rateCodingDL"),
				result1.getDouble("snirUl"),
				result1.getDouble("snirDl"),
				new ModulationTypeDAO(this.conn).find(result1.getInt("Mod_id"))
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return mcs;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<McsTechnique> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
