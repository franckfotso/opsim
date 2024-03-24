/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.Service;
import cm.opsim.model.Subscription;

/**
 * @author Romuald FOTSO
 *
 */
public class ServiceDAO extends DAO<Service> {
	
	/**
	 * @param conn
	 */
	public ServiceDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public Service create(Service obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM service "+
					"WHERE name = '"+obj.getName()+"'"
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("service exist: start update ");
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
						"WHERE table_name = 'service' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO service (name, description)"+
							"VALUES (?,?)");
					prepare1.setString(1, obj.getName());
					prepare1.setString(2, obj.getDescription());
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
	public void delete(Service obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM service WHERE id = " +obj.getId()
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
	public Service update(Service obj) {
		try{
			DAO<Service>servDAO =DAOFactory.getServiceDAO();
			
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE service SET name = ?, description = ?"+
							" WHERE id = " +obj.getId());
			prepare1.setString(1, obj.getName());
			prepare1.setString(2, obj.getDescription());
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
	public Service find(int id) {
		Service serv = new Service();
		try{			
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM service WHERE id = " +id
					);
			if(result1.first())
				serv = new Service(
				id,
				result1.getString("name"),
				result1.getString("description")
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return serv;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<Service> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
