/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.DriveTestData;
import cm.opsim.model.Project;

/**
 * @author Romuald FOTSO
 *
 */
public class DriveTestDataDAO extends DAO<DriveTestData> {

	public DriveTestDataDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public DriveTestData create(DriveTestData obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM drivetestdata "+
					"WHERE name = '"+obj.getId()+"'"
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("DriveTestData exist: start update ");
				obj.setId(id);
				obj = this.update(obj);
			}
			else
			{
				if(obj.getConf().getId() == 0){
					throw new Exception();
				}
				
				ResultSet result1 = this.conn
						.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_UPDATABLE
						).executeQuery(
						"SELECT AUTO_INCREMENT "+
						"FROM information_schema.tables "+
						"WHERE table_name = 'drivetestdata' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
						.prepareStatement(
						"INSERT INTO drivetestdata (Con_id, power, field, bound)"+
						"VALUES (?,?,?,?)");
					prepare1.setInt(1, obj.getConf().getId());
					prepare1.setDouble(2, obj.getPower());
					prepare1.setDouble(3, obj.getField());
					prepare1.setInt(4, obj.getBound());
					prepare1.executeUpdate();
					
					obj = this.find(id);
				}
				
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
		catch(Exception e) {
			System.out.println("Error(create): DT Class is not defined");
		}
		return obj;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(DriveTestData obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM drivetestdata WHERE id = " +obj.getId()
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
	public DriveTestData update(DriveTestData obj) {
		try{
			if(obj.getConf().getId() == 0){
				throw new Exception();
			}
			
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE drivetestdata SET Con_id = ?, power = ?, field = ?, bound = ?"+
							" WHERE id = " +obj.getId());
			prepare1.setInt(1, obj.getConf().getId());
			prepare1.setDouble(2, obj.getPower());
			prepare1.setDouble(3, obj.getField());
			prepare1.setInt(4, obj.getBound());
			prepare1.executeUpdate();
			
			obj = this.find(obj.getId());
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Error(update): DT Class is not defined");
		}
		return obj;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#find(int)
	 */
	@Override
	public DriveTestData find(int id) {
		DriveTestData dt = new DriveTestData();
		
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM drivetestdata WHERE id = " +id
					);
			if(result1.first())
				dt = new DriveTestData(
				id, 
				result1.getDouble("power"), 
				result1.getDouble("field"),
				result1.getInt("bound"),
				new ConfigurationDAO(this.conn).find(result1.getInt("Con_id"))
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
		return dt;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<DriveTestData> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
