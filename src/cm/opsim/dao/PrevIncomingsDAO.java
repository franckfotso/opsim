/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.CapexParameter;
import cm.opsim.model.PrevIncomings;

/**
 * @author Romuald FOTSO
 *
 */
public class PrevIncomingsDAO extends DAO<PrevIncomings> {

	public PrevIncomingsDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public PrevIncomings create(PrevIncomings obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					" FROM previncomings "+
					" WHERE id = "+obj.getId()
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("previncomings exist: start update ");
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
						"WHERE table_name = 'previncomings' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO previncomings (OpexParam_id, inByCust, custRate)"+
							"VALUES (?,?,?)");
					prepare1.setInt(1, obj.getOpexParam_id());
					prepare1.setDouble(2, obj.getInByCust());
					prepare1.setDouble(3, obj.getCustRate());					
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
	public void delete(PrevIncomings obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM previncomings WHERE id = " +obj.getId()
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
	public PrevIncomings update(PrevIncomings obj) {
		try{
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE previncomings SET OpexParam_id = ?, inByCust = ?, custRate = ?" +
							" WHERE id = " +obj.getId());
			prepare1.setInt(1, obj.getOpexParam_id());
			prepare1.setDouble(2, obj.getInByCust());
			prepare1.setDouble(3, obj.getCustRate());	
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
	public PrevIncomings find(int id) {
		PrevIncomings prevIn = new PrevIncomings();
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM previncomings WHERE id = " +id
					);
			
			if(result1.first())
				prevIn = new PrevIncomings(
				id, 
				result1.getInt("OpexParam_id"),
				result1.getDouble("inByCust"),
				result1.getDouble("custRate")
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return prevIn;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<PrevIncomings> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
