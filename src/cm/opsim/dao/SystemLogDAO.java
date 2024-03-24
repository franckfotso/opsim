/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.Project;
import cm.opsim.model.SystemLog;

/**
 * @author Romuald FOTSO
 *
 */
public class SystemLogDAO extends DAO<SystemLog> {

	public SystemLogDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public SystemLog create(SystemLog obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM systemlog "+
					"WHERE name = '"+obj.getId()+"'"
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("SystemLog exist: start update ");
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
						"WHERE table_name = 'systemlog' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
						.prepareStatement(
						"INSERT INTO systemlog (Pro_id)"+
						"VALUES (?)");
					prepare1.setInt(1, obj.getProj_id());
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
	public void delete(SystemLog obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM systemlog WHERE id = " +obj.getId()
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
	public SystemLog update(SystemLog obj) {
		try{
			if(obj.getProj_id() == 0){
				throw new Exception();
			}
			
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE systemlog SET Pro_id = ?"+
							" WHERE id = " +obj.getId());
			prepare1.setInt(1, obj.getProj_id());
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
	public SystemLog find(int id) {
		SystemLog slog = new SystemLog();
		
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM systemlog WHERE id = " +id
					);
			if(result1.first())
				slog = new SystemLog(
				id, 
				result1.getInt("Pro_id")
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<SystemLog> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
