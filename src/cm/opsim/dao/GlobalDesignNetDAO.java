/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.*;
/**
 * @author Romuald FOTSO
 *
 */
public class GlobalDesignNetDAO extends DAO<GlobalDesignNet> {

	public GlobalDesignNetDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public GlobalDesignNet create(GlobalDesignNet obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM globaldesignnet "+
					"WHERE name = '"+obj.getName()+"'"
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("GlobalDesignNet exist: start update ");
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
						"WHERE table_name = 'globaldesignnet' "+
						"AND table_schema = 'opsim'"
						);
				
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
						.prepareStatement(
						"INSERT INTO globaldesignnet (name, result, simulation, map)"+ 
						"VALUES (?,?,?,?)");
					prepare1.setString(1, obj.getName());
					prepare1.setInt(2, obj.getResult());
					prepare1.setInt(3, obj.getSimulation());
					prepare1.setInt(4, obj.getMap());
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
	public void delete(GlobalDesignNet obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM globaldesignnet WHERE id = " +obj.getId()
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
	public GlobalDesignNet update(GlobalDesignNet obj) {
		try{
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE globaldesignnet SET name = ?, result = ?, simulation = ?, map = ? "+
							"WHERE id = " +obj.getId());
				prepare1.setString(1, obj.getName());
				prepare1.setInt(2, obj.getResult());
				prepare1.setInt(3, obj.getSimulation());
				prepare1.setInt(4, obj.getMap());
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
	public GlobalDesignNet find(int id) {
		GlobalDesignNet gdn = new GlobalDesignNet();
		
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM globaldesignnet WHERE id = " +id
					);
			if(result1.first())
				gdn = new GlobalDesignNet(
				id, 
				result1.getString("name"), 
				result1.getInt("result"),
				result1.getInt("simulation"),
				result1.getInt("map")
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return gdn;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<GlobalDesignNet> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
