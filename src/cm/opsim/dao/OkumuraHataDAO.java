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
import cm.opsim.model.GenParameter;
import cm.opsim.model.OkumuraHata;

/**
 * @author Romuald FOTSO
 *
 */
public class OkumuraHataDAO extends DAO<OkumuraHata> {

	public OkumuraHataDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public OkumuraHata create(OkumuraHata obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM okumura "+
					"WHERE id = "+obj.getId()
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("okumura exist: start update ");
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
						"WHERE table_name = 'okumura' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO okumura (frequency, heightUE, heightENB, zoneType, diffAtten, spAtten, fieldOccupAtten," +
							" corrAntenAtten, otherAtten, riseAtten)"+
							"VALUES (?,?,?,?,?,?,?,?,?,?)");
					prepare1.setDouble(1, obj.getFrequency());
					prepare1.setDouble(2, obj.getHeightUE());
					prepare1.setDouble(3, obj.getHeightENB());
					prepare1.setString(4, obj.getZoneType());
					prepare1.setDouble(5, obj.getDiffAtten());
					prepare1.setDouble(6, obj.getSpAtten());
					prepare1.setDouble(7, obj.getFieldOccupAtten());
					prepare1.setDouble(8, obj.getCorrAntenAtten());
					prepare1.setDouble(9, obj.getOtherAtten());
					prepare1.setDouble(10, obj.getRiseAtten());					
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
	public void delete(OkumuraHata obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM okumura WHERE id = " +obj.getId()
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
	public OkumuraHata update(OkumuraHata obj) {
		try{
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE okumura SET frequency = ?, heightUE = ?, heightENB = ?, zoneType = ?, diffAtten = ?," +
							" spAtten = ?, fieldOccupAtten = ?, corrAntenAtten = ?, otherAtten = ?, riseAtten = ?"+
							" WHERE id = " +obj.getId());
			prepare1.setDouble(1, obj.getFrequency());
			prepare1.setDouble(2, obj.getHeightUE());
			prepare1.setDouble(3, obj.getHeightENB());
			prepare1.setString(4, obj.getZoneType());
			prepare1.setDouble(5, obj.getDiffAtten());
			prepare1.setDouble(6, obj.getSpAtten());
			prepare1.setDouble(7, obj.getFieldOccupAtten());
			prepare1.setDouble(8, obj.getCorrAntenAtten());
			prepare1.setDouble(9, obj.getOtherAtten());
			prepare1.setDouble(10, obj.getRiseAtten());					
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
	public OkumuraHata find(int id) {
		OkumuraHata oku = null;
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM okumura WHERE id = " +id
					);
			if(result1.first())
				oku = new OkumuraHata(
				id, 
				result1.getDouble("frequency"),
				result1.getDouble("heightUE"),
				result1.getDouble("heightENB"),
				result1.getString("zoneType"),
				result1.getDouble("diffAtten"),
				result1.getDouble("spAtten"),
				result1.getDouble("fieldOccupAtten"),
				result1.getDouble("corrAntenAtten"),
				result1.getDouble("otherAtten"),
				result1.getDouble("riseAtten")
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return oku;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<OkumuraHata> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
