/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.CapacityPlanif;
import cm.opsim.model.CoveragePlanif;

/**
 * @author Romuald FOTSO
 *
 */
public class CapacityPlanifDAO extends DAO<CapacityPlanif> {
	
	public CapacityPlanifDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public CapacityPlanif create(CapacityPlanif obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM capacityplanif "+
					"WHERE id = '"+obj.getId()+"'"
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("CapPlanif exist: start update ");
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
						"WHERE table_name = 'capacityplanif' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
						.prepareStatement(
						"INSERT INTO capacityplanif (id, state)" +
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
	public void delete(CapacityPlanif obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM capacityplanif WHERE id = " +obj.getId()
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
	public CapacityPlanif update(CapacityPlanif obj) {
		try{
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE capacityplanif SET id = ?, state = ?"+
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
	public CapacityPlanif find(int id) {
		CapacityPlanif capPlanif = new CapacityPlanif();
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM capacityplanif WHERE id = " +id
					);
			if(result1.first())
				capPlanif = new CapacityPlanif(
						id,
						result1.getString("state"));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return capPlanif;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<CapacityPlanif> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
