/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.CapexOpexPlanif;
import cm.opsim.model.FrequencyPlanif;
import cm.opsim.model.OptionSystem;

/**
 * @author Romuald FOTSO
 *
 */
public class CapexOpexPlanifDAO extends DAO<CapexOpexPlanif> {
	
	public CapexOpexPlanifDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public CapexOpexPlanif create(CapexOpexPlanif obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM capexopexplanif "+
					"WHERE id = '"+obj.getId()+"'"
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("coPlanif exist: start update ");
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
						"WHERE table_name = 'capexopexplanif' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
						.prepareStatement(
						"INSERT INTO capexopexplanif (id, state)" +
						"VALUES (?,?)");
					prepare1.setInt(1, obj.getId());
					prepare1.setString(2, obj.getState());
					prepare1.executeUpdate();								
					obj = this.find(id);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(CapexOpexPlanif obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM capexopexplanif WHERE id = " +obj.getId()
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
	public CapexOpexPlanif update(CapexOpexPlanif obj) {
		try{
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE capexopexplanif SET id = ?, state = ?"+
							" WHERE id = " +obj.getId());
				prepare1.setInt(1, obj.getId());
				prepare1.setString(2, obj.getState());
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
	public CapexOpexPlanif find(int id) {
		CapexOpexPlanif coPlanif = new CapexOpexPlanif();
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM capexopexplanif WHERE id = " +id
					);
			if(result1.first())
				coPlanif = new CapexOpexPlanif(id, result1.getString("state"));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return coPlanif;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<CapexOpexPlanif> findAll() {
		
		return null;
	}
}
