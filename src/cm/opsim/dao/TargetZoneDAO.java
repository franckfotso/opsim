/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.GenParameter;
import cm.opsim.model.TargetZone;

/**
 * @author Romuald FOTSO
 *
 */
public class TargetZoneDAO extends DAO<TargetZone> {

	public TargetZoneDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public TargetZone create(TargetZone obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					" FROM targetzone "+
					" WHERE id = "+obj.getId()
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("targetzone exist: start update ");
				obj.setId(id);
				obj = this.update(obj);
			}
			else
			{
				ResultSet result1 = this.conn
						.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_UPDATABLE
						).executeQuery(
						"SELECT AUTO_INCREMENT "+
						"FROM information_schema.tables "+
						"WHERE table_name = 'targetzone' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO targetzone (name, country, province, aim, chiefProject, lon, lat)"+
							"VALUES (?,?,?,?,?,?,?)");
						prepare1.setString(1, obj.getName());
						prepare1.setString(2, obj.getCountry());
						prepare1.setString(3, obj.getProvince());
						prepare1.setString(4, obj.getAim());
						prepare1.setString(5, obj.getChiefProject());
						prepare1.setDouble(6, obj.getLon());
						prepare1.setDouble(7, obj.getLat());
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
	public void delete(TargetZone obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM targetzone WHERE id = " +obj.getId()
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
	public TargetZone update(TargetZone obj) {
		try{
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE targetzone SET name = ?, country = ?, province = ?, aim = ?, chiefProject = ?," +
							" lon = ?, lat = ?"+
							" WHERE id = " +obj.getId());
			prepare1.setString(1, obj.getName());
			prepare1.setString(2, obj.getCountry());
			prepare1.setString(3, obj.getProvince());
			prepare1.setString(4, obj.getAim());
			prepare1.setString(5, obj.getChiefProject());
			prepare1.setDouble(6, obj.getLon());
			prepare1.setDouble(7, obj.getLat());
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
	public TargetZone find(int id) {
		TargetZone tzone = new TargetZone();
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM targetzone WHERE id = " +id
					);
			if(result1.first())
				tzone = new TargetZone(
				id, 
				result1.getString("name"),
				result1.getString("country"),
				result1.getString("province"),
				result1.getString("aim"),
				result1.getString("chiefProject"),
				result1.getDouble("lon"),
				result1.getDouble("lat")
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return tzone;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<TargetZone> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
