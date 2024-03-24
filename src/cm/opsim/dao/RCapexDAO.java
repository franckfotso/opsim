/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.RCapex;
import cm.opsim.model.RPlanCapacity;

/**
 * @author Romuald FOTSO
 *
 */
public class RCapexDAO extends DAO<RCapex> {

	/**
	 * @param conn
	 */
	public RCapexDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public RCapex create(RCapex obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM rcapex "+
					"WHERE id = "+obj.getId()
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("rcapex exist: start update ");
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
						"WHERE table_name = 'rcapex' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO rcapex (id, conceptCost, invMatLogi, deployCost, logisCost, capexTotal)"+
							"VALUES (?,?,?,?,?,?)");
						prepare1.setInt(1, obj.getId());
						prepare1.setDouble(2, obj.getConceptCost());
						prepare1.setDouble(3, obj.getInvMatLogi());
						prepare1.setDouble(4, obj.getDeployCost());
						prepare1.setDouble(5, obj.getLogisCost());
						prepare1.setDouble(6, obj.getCapexTotal());
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
	public void delete(RCapex obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM rcapex WHERE id = " +obj.getId()
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
	public RCapex update(RCapex obj) {
		try{
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE rcapex SET conceptCost = ?, invMatLogi = ?, deployCost = ?, logisCost = ?, capexTotal = ?"+
							" WHERE id = " +obj.getId());
			prepare1.setDouble(1, obj.getConceptCost());
			prepare1.setDouble(2, obj.getInvMatLogi());
			prepare1.setDouble(3, obj.getDeployCost());
			prepare1.setDouble(4, obj.getLogisCost());
			prepare1.setDouble(5, obj.getCapexTotal());
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
	public RCapex find(int id) {
		RCapex rcapex = new RCapex();
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM rcapex WHERE id = " +id
					);
			if(result1.first())
				rcapex = new RCapex(
				id, 
				result1.getDouble("conceptCost"),
				result1.getDouble("invMatLogi"),
				result1.getDouble("deployCost"),
				result1.getDouble("logisCost"),
				result1.getDouble("capexTotal")
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return rcapex;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<RCapex> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
