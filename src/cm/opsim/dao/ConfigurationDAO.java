/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import cm.opsim.model.*;

/**
 * @author Romuald FOTSO
 *
 */
public class ConfigurationDAO extends DAO<Configuration> {

	public ConfigurationDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public Configuration create(Configuration obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM configuration "+
					"WHERE id = '"+obj.getId()+"'"
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("Configuration exist: start update ");
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
						"WHERE table_name = 'configuration' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
						.prepareStatement(
						"INSERT INTO configuration (createdDate, updatedDate)"+
						"VALUES (NOW(),NOW())");
					prepare1.executeUpdate();
					
					for(DriveTestData dt : obj.getDriveTestData()){
						if(dt.getId() == 0){
							DAO<DriveTestData>dtDAO =DAOFactory.getDriveTestDataDAO();
							dt = dtDAO.create(dt);
						}					
					}
					
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
	public void delete(Configuration obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM configuration WHERE id = " +obj.getId()
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
	public Configuration update(Configuration obj) {
		try{
			DAO<DriveTestData>dtDAO =DAOFactory.getDriveTestDataDAO();
			
			for(DriveTestData dt : obj.getDriveTestData()){				
				if(dt.getId() == 0)
					dt =dtDAO.create(dt);
				else
					dtDAO.update(dt);
			}
			
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE configuration SET updatedDate = NOW()"+
							" WHERE id = " +obj.getId());
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
	public Configuration find(int id) {
		Configuration conf = new Configuration();
		Collection<DriveTestData> listDT = new HashSet<DriveTestData>();
		
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM drivetestdata WHERE Con_id = " +id
					);
			if(result1.first()){
				DAO<DriveTestData>dtDAO =DAOFactory.getDriveTestDataDAO();				
				result1.beforeFirst();
				while(result1.next() && result1.getLong("id") != 0)
					listDT.add(dtDAO.find(result1.getInt("id")));				
				result1.first();
			}
			
			ResultSet result2 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM configuration WHERE id = " +id
					);
			if(result2.first())
				conf = new Configuration(
				id, 
				result2.getDate("createdDate"),
				result2.getDate("updatedDate"),
				listDT,
				new BtsConfigDAO(this.conn).find(id),
				new EnbConfigDAO(this.conn).find(id),
				new BackboneConfigDAO(this.conn).find(id)
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}		
		return conf;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<Configuration> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
