/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.OptionSystem;
import cm.opsim.model.Project;

/**
 * @author Romuald FOTSO
 *
 */
public class OptionSystemDAO extends DAO<OptionSystem> {

	public OptionSystemDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public OptionSystem create(OptionSystem obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM optionsystem "+
					"WHERE name = '"+obj.getId()+"'"
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("OptionSystem exist: start update ");
				obj.setId(id);
				obj = this.update(obj);
			}
			else
			{
				if(obj.getUser().getId() == 0){
					throw new Exception();
				}
				
				ResultSet result1 = this.conn
						.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_UPDATABLE
						).executeQuery(
						"SELECT AUTO_INCREMENT "+
						"FROM information_schema.tables "+
						"WHERE table_name = 'optionsystem' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
						.prepareStatement(
						"INSERT INTO optionsystem (Use_id)"+
						"VALUES (?)");
					prepare1.setInt(1, obj.getUser().getId());
					prepare1.executeUpdate();
					
					obj = this.find(id);
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Error(create): User Class is not defined");
		}
		return obj;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(OptionSystem obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM optionsystem WHERE id = " +obj.getId()
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
	public OptionSystem update(OptionSystem obj) {
		try{
			if(obj.getUser().getId() == 0){
				throw new Exception();
			}
			
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE optionsystem SET Use_id = ?"+
							" WHERE id = " +obj.getId());
				prepare1.setInt(1, obj.getUser().getId());
				prepare1.executeUpdate();
			
			obj = this.find(obj.getId());
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Error(create): User Class is not defined");
		}
		return obj;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#find(int)
	 */
	@Override
	public OptionSystem find(int id) {
		OptionSystem os = new OptionSystem();
		
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM optionsystem WHERE id = " +id
					);
			if(result1.first())
				os = new OptionSystem(
				id, 
				new UserDAO(this.conn).find(result1.getInt("Use_id"))
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return os;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<OptionSystem> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
