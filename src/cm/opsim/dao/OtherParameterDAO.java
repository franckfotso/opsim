/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.GenParameter;
import cm.opsim.model.OtherParameter;

/**
 * @author Romuald FOTSO
 *
 */
public class OtherParameterDAO extends DAO<OtherParameter> {

	public OtherParameterDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public OtherParameter create(OtherParameter obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM otherparameter "+
					"WHERE id = "+obj.getId()
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("otherparameter exist: start update ");
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
						"WHERE table_name = 'otherparameter' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO otherparameter (id,modulation, mimoType)"+
							"VALUES (?,?,?)");
						prepare1.setInt(1, obj.getId());
						prepare1.setString(2, obj.getModulation());
						prepare1.setString(3, obj.getMimoType());
						prepare1.executeUpdate();
						
						obj = this.find(id);
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}// TODO Auto-generated method stub
		return obj;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(OtherParameter obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM otherparameter WHERE id = " +obj.getId()
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
	public OtherParameter update(OtherParameter obj) {
		try{
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE otherparameter SET id = ?, modulation = ?, mimoType = ?"+
							" WHERE id = " +obj.getId());
			prepare1.setInt(1, obj.getId());
			prepare1.setString(2, obj.getModulation());
			prepare1.setString(3, obj.getMimoType());
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
	public OtherParameter find(int id) {
		OtherParameter otherParam = new OtherParameter();
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM otherparameter WHERE id = " +id
					);
			if(result1.first())
				otherParam = new OtherParameter(
				id, 
				result1.getString("modulation"),
				result1.getString("mimoType")
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return otherParam;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<OtherParameter> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
