/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.Cost231Hata;
import cm.opsim.model.OkumuraHata;

/**
 * @author Romuald FOTSO
 *
 */
public class Cost231HataDAO extends DAO<Cost231Hata> {

	public Cost231HataDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public Cost231Hata create(Cost231Hata obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM cost231 "+
					"WHERE id = "+obj.getId()
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("cost231 exist: start update ");
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
						"WHERE table_name = 'cost231' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO cost231 (frequency, heightUE, heightENB, heightRoof, bldSeparation," +
							" incidenceAngle, widthString, zoneType, subTypeCost231)"+
							"VALUES (?,?,?,?,?,?,?,?,?)");
					prepare1.setDouble(1, obj.getFrequency());
					prepare1.setDouble(2, obj.getHeightUE());
					prepare1.setDouble(3, obj.getHeightENB());
					prepare1.setDouble(4, obj.getHeightRoof());
					prepare1.setDouble(5, obj.getBldSeparation());
					prepare1.setDouble(6, obj.getIncidenceAngle());
					prepare1.setDouble(7, obj.getWidthString());
					prepare1.setString(8, obj.getZoneType());
					prepare1.setString(9, obj.getSubTypeCost231());				
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
	public void delete(Cost231Hata obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM cost231 WHERE id = " +obj.getId()
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
	public Cost231Hata update(Cost231Hata obj) {
		try{
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE cost231 SET frequency = ?, heightUE = ?, heightENB = ?, heightRoof = ?, bldSeparation = ?," +
							" incidenceAngle = ?, widthString = ?, zoneType = ?, subTypeCost231 = ?"+
							" WHERE id = " +obj.getId());
			prepare1.setDouble(1, obj.getFrequency());
			prepare1.setDouble(2, obj.getHeightUE());
			prepare1.setDouble(3, obj.getHeightENB());
			prepare1.setDouble(4, obj.getHeightRoof());
			prepare1.setDouble(5, obj.getBldSeparation());
			prepare1.setDouble(6, obj.getIncidenceAngle());
			prepare1.setDouble(7, obj.getWidthString());
			prepare1.setString(8, obj.getZoneType());
			prepare1.setString(9, obj.getSubTypeCost231());				
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
	public Cost231Hata find(int id) {
		Cost231Hata c231 = new Cost231Hata();
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM cost231 WHERE id = " +id
					);
			if(result1.first())
				c231 = new Cost231Hata(
				id, 
				result1.getDouble("frequency"),
				result1.getDouble("heightUE"),
				result1.getDouble("heightENB"),
				result1.getDouble("heightRoof"),
				result1.getDouble("bldSeparation"),
				result1.getDouble("incidenceAngle"),
				result1.getDouble("widthString"),
				result1.getString("zoneType"),
				result1.getString("subTypeCost231")
				);
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return c231;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<Cost231Hata> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
