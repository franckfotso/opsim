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
import cm.opsim.model.ROpex;

/**
 * @author Romuald FOTSO
 *
 */
public class ROpexDAO extends DAO<ROpex> {

	/**
	 * @param conn
	 */
	public ROpexDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public ROpex create(ROpex obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM ropex "+
					"WHERE id = "+obj.getId()
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("ropex exist: start update ");
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
						"WHERE table_name = 'ropex' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO ropex (id, Result_id, year, opex, inCom, benef)"+
							"VALUES (?,?,?,?,?,?)");
						prepare1.setInt(1, obj.getId());
						prepare1.setInt(2, obj.getResult_id());
						prepare1.setDouble(3, obj.getYear());
						prepare1.setDouble(4, obj.getOpex());
						prepare1.setDouble(5, obj.getInCom());
						prepare1.setDouble(6, obj.getBenef());
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
	public void delete(ROpex obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM ropex WHERE id = " +obj.getId()
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
	public ROpex update(ROpex obj) {
		try{
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE ropex SET Result_id = ?, year = ?, opex = ?, inCom = ?, benef = ?"+
							" WHERE id = " +obj.getId());
			prepare1.setInt(1, obj.getResult_id());
			prepare1.setDouble(2, obj.getYear());
			prepare1.setDouble(3, obj.getOpex());
			prepare1.setDouble(4, obj.getInCom());
			prepare1.setDouble(5, obj.getBenef());
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
	public ROpex find(int id) {
		ROpex ropex = new ROpex();
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM ropex WHERE id = " +id
					);
			if(result1.first())
				ropex = new ROpex(
				id, 
				result1.getInt("Result_id"),
				result1.getInt("year"),
				result1.getDouble("opex"),
				result1.getDouble("inCom"),
				result1.getDouble("benef")
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return ropex;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<ROpex> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
