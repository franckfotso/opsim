/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.Simulation;

/**
 * @author Romuald FOTSO
 *
 */
public class SimulationDAO extends DAO<Simulation> {

	public SimulationDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public Simulation create(Simulation obj){
		try{		
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM simulation "+
					"WHERE name = '"+obj.getId()+"'"
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("Simulation exist: start update ");
				obj.setId(id);
				obj = this.update(obj);
			}
			else
			{
				if(obj.getProj_id() == 0){
					throw new Exception();
				}
				
				ResultSet result1 = this.conn
						.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_UPDATABLE
						).executeQuery(
						"SELECT AUTO_INCREMENT "+
						"FROM information_schema.tables "+
						"WHERE table_name = 'simulation' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
						.prepareStatement(
						"INSERT INTO simulation (Pro_id, startDate, endDate, errorRate, createdDate, updatedDate)"+
						"VALUES (?,?,?,?,NOW(),NOW())");
					prepare1.setInt(1, obj.getProj_id());
					prepare1.setDate(2, (Date)obj.getStartDate());
					prepare1.setDate(3, (Date)obj.getEndDate());
					prepare1.setDouble(4, obj.getErrorRate());
					prepare1.executeUpdate();
					
					obj = this.find(id);
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Error(create): Project Class is not defined");
		}
		return obj;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Simulation obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM simulation WHERE id = " +obj.getId()
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
	public Simulation update(Simulation obj) {
		try{
			if(obj.getProj_id() == 0){
				throw new Exception();
			}
			
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE simulation SET Pro_id = ?, startDate = ?, isDynamic = ?, errorRate = ?, updatedDate = NOW()"+
							" WHERE id = " +obj.getId());
			prepare1.setInt(1, obj.getProj_id());
			prepare1.setDate(2, (Date)obj.getStartDate());
			prepare1.setDate(3, (Date)obj.getEndDate());
			prepare1.setDouble(4, obj.getErrorRate());
			prepare1.executeUpdate();
			
			obj = this.find(obj.getId());
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Error(update): Project Class is not defined");
		}
		return obj;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#find(int)
	 */
	@Override
	public Simulation find(int id) {
		Simulation simu = new Simulation();
		
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM simulation WHERE id = " +id
					);
			if(result1.first())
				simu = new Simulation(
				id, 
				result1.getDate("startDate"),
				result1.getDate("endDate"),
				result1.getBoolean("isDynamic"),
				result1.getFloat("errorRate"),
				result1.getDate("createdDate"),
				result1.getDate("updatedDate"),
				result1.getInt("Pro_id")
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return simu;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<Simulation> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
