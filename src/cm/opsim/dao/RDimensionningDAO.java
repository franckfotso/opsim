/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.RDimensionning;
import cm.opsim.model.RPlanCapacity;

/**
 * @author Romuald FOTSO
 *
 */
public class RDimensionningDAO extends DAO<RDimensionning> {

	public RDimensionningDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public RDimensionning create(RDimensionning obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM rdimensionning "+
					"WHERE id = "+obj.getId()
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("rdimensionning exist: start update ");
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
						"WHERE table_name = 'rdimensionning' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO rdimensionning (id, serviceClass, traficModel, numCustomerByAccess, numMGW," +
							" mgcfCapacity, cscfCapacity)"+
							"VALUES (?,?,?,?,?,?,?)");
						prepare1.setInt(1, obj.getId());	
						prepare1.setString(2, obj.getServiceClass());
						prepare1.setString(3, obj.getTraficModel());
						prepare1.setInt(4, obj.getNumCustomerByAccess());
						prepare1.setInt(5, obj.getNumMGW());
						prepare1.setDouble(6, obj.getMgcfCapacity());
						prepare1.setDouble(7, obj.getCscfCapacity());
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
	public void delete(RDimensionning obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM rdimensionning WHERE id = " +obj.getId()
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
	public RDimensionning update(RDimensionning obj) {
		try{
			System.out.println("ID Dim: "+obj.getId());
			
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE rdimensionning SET serviceClass = ?, traficModel = ?, numCustomerByAccess = ?, numMGW = ?," +
							" mgcfCapacity = ?, cscfCapacity = ?"+
							" WHERE id = " +obj.getId());
			prepare1.setString(1, obj.getServiceClass());
			prepare1.setString(2, obj.getTraficModel());
			prepare1.setInt(3, obj.getNumCustomerByAccess());
			prepare1.setInt(4, obj.getNumMGW());
			prepare1.setDouble(5, obj.getMgcfCapacity());
			prepare1.setDouble(6, obj.getCscfCapacity());
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
	public RDimensionning find(int id) {
		RDimensionning resDim = new RDimensionning();
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM rdimensionning WHERE id = " +id
					);
			if(result1.first())
				resDim = new RDimensionning(
				id, 
				result1.getString("serviceClass"),
				result1.getString("traficModel"),
				result1.getInt("numCustomerByAccess"),
				result1.getInt("numMGW"),
				result1.getDouble("mgcfCapacity"),
				result1.getDouble("cscfCapacity")
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return resDim;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<RDimensionning> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
