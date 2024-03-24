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
import java.util.Date;
import java.util.HashSet;

import cm.opsim.model.Map;
import cm.opsim.model.OptionSystem;
import cm.opsim.model.Project;
import cm.opsim.model.User;

/**
 * @author Romuald FOTSO
 *
 */
public class UserDAO extends DAO<User>{

	/**
	 * 
	 */
	public UserDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public User create(User obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM user "+
					"WHERE name = '"+obj.getName()+"'"
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("User exist: start update ");
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
						"WHERE table_name = 'user' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
						.prepareStatement(
						"INSERT INTO user (name, username, password, salt, isEnabled, createdDate, updatedDate) " +
						"VALUES (?,?,?,?,?,NOW(),NOW())");
					prepare1.setString(1, obj.getName());
					prepare1.setString(2, obj.getUsername());
					prepare1.setString(3, obj.getPassword());
					prepare1.setString(4, obj.getSalt());
					prepare1.setBoolean(5, obj.getIsEnabled());
					prepare1.executeUpdate();
					
					for(OptionSystem os : obj.getOptionSystem()){
						if(os.getId() == 0){
							DAO<OptionSystem>osDAO =DAOFactory.getOptionSystemDAO();
							os = osDAO.create(os);
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
	public void delete(User obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM user WHERE id = " +obj.getId()
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
	public User update(User obj) {
		try{
			DAO<OptionSystem>osDAO =DAOFactory.getOptionSystemDAO();
			for(OptionSystem os : obj.getOptionSystem()){				
				if(os.getId() == 0)
					os = osDAO.create(os);
				else
					osDAO.update(os);
			}
			
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE user SET name = ?, username = ?, password = ?, 	salt = ?, isEnabled = ?, updatedDate = NOW()"+
							" WHERE id = " +obj.getId());
				prepare1.setString(1, obj.getName());
				prepare1.setString(2, obj.getUsername());
				prepare1.setString(3, obj.getPassword());
				prepare1.setString(4, obj.getSalt());
				prepare1.setBoolean(5, obj.getIsEnabled());
				prepare1.executeUpdate();
			
			obj = this.find(obj.getId());
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return obj;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#find(int)
	 */
	@Override
	public User find(int id) {
		User user = new User();
		Collection<OptionSystem> listOptionSys = new HashSet<OptionSystem>();
		
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM optionsystem WHERE Use_id = " +id
					);
			if(result1.first()){
				DAO<OptionSystem>osDAO =DAOFactory.getOptionSystemDAO();			
				result1.beforeFirst();
				while(result1.next() && result1.getLong("id") != 0)
					listOptionSys.add(osDAO.find(result1.getInt("id")));				
				result1.first();
			}
			
			ResultSet result2 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM user WHERE id = " +id
					);
			if(result2.first())
				user = new User(
				id, 
				result2.getString("name"), 
				result2.getString("username"),
				result2.getString("password"),
				result2.getString("salt"),
				result2.getBoolean("isEnabled"),
				result2.getDate("createdDate"),
				result2.getDate("updatedDate"),
				listOptionSys,
				new ProfilDAO(this.conn).find(id)
				);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return user;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
