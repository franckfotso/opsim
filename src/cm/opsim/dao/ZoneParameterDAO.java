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
import cm.opsim.model.ZoneParameter;

/**
 * @author Romuald FOTSO
 *
 */
public class ZoneParameterDAO extends DAO<ZoneParameter> {

	public ZoneParameterDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public ZoneParameter create(ZoneParameter obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM zoneparameter "+
					"WHERE id = "+obj.getId()
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("zoneparameter exist: start update ");
				obj.setId(id);
				obj = this.update(obj);
			}
			else
			{
				if(obj.getTargetZone().getId() == 0){
					DAO<TargetZone>tzoneDAO =DAOFactory.getTargetZoneDAO();
					obj.setTargetZone(tzoneDAO.create(obj.getTargetZone()));
				}
				
				ResultSet result1 = this.conn
						.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_UPDATABLE
						).executeQuery(
						"SELECT AUTO_INCREMENT "+
						"FROM information_schema.tables "+
						"WHERE table_name = 'zoneparameter' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO zoneparameter (id,Tar_id, penetrationRate, area, density)"+
							"VALUES (?,?,?,?,?)");
						prepare1.setInt(1, obj.getId());
						prepare1.setInt(2, obj.getTargetZone().getId());
						prepare1.setDouble(3, obj.getPenetrationRate());
						prepare1.setDouble(4, obj.getArea());
						prepare1.setDouble(5, obj.getDensity());
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
	public void delete(ZoneParameter obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM zoneparameter WHERE id = " +obj.getId()
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
	public ZoneParameter update(ZoneParameter obj) {
		try{
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE zoneparameter SET id = ?, Tar_id = ?, penetrationRate = ?, area = ?, density = ?"+
							" WHERE id = " +obj.getId());
			prepare1.setInt(1, obj.getId());
			prepare1.setInt(2, obj.getTargetZone().getId());
			prepare1.setDouble(3, obj.getPenetrationRate());
			prepare1.setDouble(4, obj.getArea());
			prepare1.setDouble(5, obj.getDensity());
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
	public ZoneParameter find(int id) {
		ZoneParameter zoneParam = new ZoneParameter();
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM zoneparameter WHERE id = " +id
					);
			if(result1.first())
				zoneParam = new ZoneParameter(
				id,				
				result1.getDouble("penetrationRate"),
				result1.getDouble("area"),
				result1.getDouble("density"),
				new TargetZoneDAO(this.conn).find(result1.getInt("Tar_id"))
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return zoneParam;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<ZoneParameter> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
