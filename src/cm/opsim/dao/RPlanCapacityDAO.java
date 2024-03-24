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
import cm.opsim.model.RPlanCapacity;

/**
 * @author Romuald FOTSO
 *
 */
public class RPlanCapacityDAO extends DAO<RPlanCapacity> {

	public RPlanCapacityDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public RPlanCapacity create(RPlanCapacity obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM rplancapacity "+
					"WHERE id = "+obj.getId()
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("rplancapacity exist: start update ");
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
						"WHERE table_name = 'rplancapacity' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO rplancapacity (id, cellCapacityUL, cellCapacityDL, trafficUL, trafficDL, numCustomerByCellUL," +
							" numCustomerByCellDL, numENBUl, numENBDl, cellRadius, interSiteDist)"+
							"VALUES (?,?,?,?,?,?,?,?,?,?,?)");
						prepare1.setInt(1, obj.getId());
						prepare1.setDouble(2, obj.getCellCapacityUL());
						prepare1.setDouble(3, obj.getCellCapacityDL());
						prepare1.setDouble(4, obj.getTrafficUL());
						prepare1.setDouble(5, obj.getTrafficDL());
						prepare1.setInt(6, obj.getNumCustomerByCellUL());
						prepare1.setInt(7, obj.getNumCustomerByCellDL());
						prepare1.setInt(8, obj.getNumENBUl());
						prepare1.setInt(9, obj.getNumENBDl());
						prepare1.setDouble(10, obj.getCellRadius());
						prepare1.setDouble(11, obj.getInterSiteDist());
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
	public void delete(RPlanCapacity obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM rplancapacity WHERE id = " +obj.getId()
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
	public RPlanCapacity update(RPlanCapacity obj) {
		try{
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE rplancapacity SET cellCapacityUL = ?, cellCapacityDL = ?, trafficUL = ?, trafficDL = ?," +
							" numCustomerByCellUL = ?, numCustomerByCellDL = ?, numENBUl = ?, numENBDl = ?, cellRadius = ?, interSiteDist = ?"+
							" WHERE id = " +obj.getId());
			prepare1.setDouble(1, obj.getCellCapacityUL());
			prepare1.setDouble(2, obj.getCellCapacityDL());
			prepare1.setDouble(3, obj.getTrafficUL());
			prepare1.setDouble(4, obj.getTrafficDL());
			prepare1.setInt(5, obj.getNumCustomerByCellUL());
			prepare1.setInt(6, obj.getNumCustomerByCellDL());
			prepare1.setInt(7, obj.getNumENBUl());
			prepare1.setInt(8, obj.getNumENBDl());
			prepare1.setDouble(9, obj.getCellRadius());
			prepare1.setDouble(10, obj.getInterSiteDist());
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
	public RPlanCapacity find(int id) {
		RPlanCapacity resCap = new RPlanCapacity();
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM rplancapacity WHERE id = " +id
					);
			if(result1.first())
				resCap = new RPlanCapacity(
				id, 
				result1.getDouble("cellCapacityUL"),
				result1.getDouble("cellCapacityDL"),
				result1.getDouble("trafficUL"),
				result1.getDouble("trafficDL"),
				result1.getInt("numCustomerByCellUL"),
				result1.getInt("numCustomerByCellDL"),
				result1.getInt("numENBUl"),
				result1.getInt("numENBDl"),
				result1.getDouble("cellRadius"),
				result1.getDouble("interSiteDist")
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return resCap;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<RPlanCapacity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
