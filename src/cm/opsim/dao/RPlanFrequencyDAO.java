/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.RPlanCapacity;
import cm.opsim.model.RPlanFrequency;

/**
 * @author Romuald FOTSO
 *
 */
public class RPlanFrequencyDAO extends DAO<RPlanFrequency> {

	public RPlanFrequencyDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public RPlanFrequency create(RPlanFrequency obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM rplanfrequency "+
					"WHERE id = "+obj.getId()
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("rplanfrequency exist: start update ");
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
						"WHERE table_name = 'rplanfrequency' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO rplanfrequency (id,numFreqByCell, factorR, sir, numChannel, area," +
							" radius, numCell, interSiteDist, distanceR)"+
							"VALUES (?,?,?,?,?,?,?,?,?,?)");
						prepare1.setInt(1, obj.getId());
						prepare1.setInt(2, obj.getNumFreqByCell());
						prepare1.setInt(3, obj.getFactorR());
						prepare1.setDouble(4, obj.getSir());
						prepare1.setInt(5, obj.getNumChannel());
						prepare1.setDouble(6, obj.getArea());
						prepare1.setDouble(7, obj.getRadius());
						prepare1.setInt(8, obj.getNumCell());
						prepare1.setDouble(9, obj.getInterSiteDist());
						prepare1.setDouble(10, obj.getDistanceR());
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
	public void delete(RPlanFrequency obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM rplanfrequency WHERE id = " +obj.getId()
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
	public RPlanFrequency update(RPlanFrequency obj) {
		try{
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE rplanfrequency SET numFreqByCell = ?, factorR = ?, sir = ?, numChannel = ?," +
							" area = ?, radius = ?, numCell = ?, interSiteDist = ?, distanceR = ?"+
							" WHERE id = " +obj.getId());
			prepare1.setInt(1, obj.getNumFreqByCell());
			prepare1.setInt(2, obj.getFactorR());
			prepare1.setDouble(3, obj.getSir());
			prepare1.setInt(4, obj.getNumChannel());
			prepare1.setDouble(5, obj.getArea());
			prepare1.setDouble(6, obj.getRadius());
			prepare1.setInt(7, obj.getNumCell());
			prepare1.setDouble(8, obj.getInterSiteDist());
			prepare1.setDouble(9, obj.getDistanceR());
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
	public RPlanFrequency find(int id) {
		RPlanFrequency resFreq = new RPlanFrequency();
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM rplanfrequency WHERE id = " +id
					);
			if(result1.first())
				resFreq = new RPlanFrequency(
				id, 
				result1.getInt("numFreqByCell"),
				result1.getInt("factorR"),
				result1.getDouble("sir"),
				result1.getInt("numChannel"),
				result1.getDouble("area"),
				result1.getDouble("radius"),
				result1.getInt("numCell"),
				result1.getDouble("interSiteDist"),
				result1.getDouble("distanceR")
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return resFreq;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<RPlanFrequency> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
