/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.EnvParameter;
import cm.opsim.model.PmParameter;

/**
 * @author Romuald FOTSO
 *
 */
public class EnvParameterDAO extends DAO<EnvParameter> {

	public EnvParameterDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public EnvParameter create(EnvParameter obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM envparameter "+
					"WHERE id = "+obj.getId()
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("envparameter exist: start update ");
				obj.setId(id);
				obj = this.update(obj);
			}
			else
			{
				if(obj.getPmParameter().getId() == 0){
					DAO<PmParameter>pmParamDAO =DAOFactory.getPmParameterDAO();
					obj.setPmParameter(pmParamDAO.create(obj.getPmParameter()));
				}
				
				ResultSet result1 = this.conn
						.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_UPDATABLE
						).executeQuery(
						"SELECT AUTO_INCREMENT "+
						"FROM information_schema.tables "+
						"WHERE table_name = 'envparameter' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO envparameter (id, marginInterf, mhag, cco, Pmp_id)"+
							"VALUES (?,?,?,?,?)");
						prepare1.setInt(1, obj.getId());
						prepare1.setDouble(2, obj.getMarginInterf());
						prepare1.setDouble(3, obj.getMhag());
						prepare1.setDouble(4, obj.getCco());
						prepare1.setInt(5, obj.getPmParameter().getId());
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
	public void delete(EnvParameter obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM envparameter WHERE id = " +obj.getId()
					);
			
			DAO<PmParameter>pmParamDAO =DAOFactory.getPmParameterDAO();
			pmParamDAO.delete(obj.getPmParameter());
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#update(java.lang.Object)
	 */
	@Override
	public EnvParameter update(EnvParameter obj) {
		try{
			DAO<PmParameter>pmParamDAO =DAOFactory.getPmParameterDAO();
			if(obj.getPmParameter().getId() == 0){				
				obj.setPmParameter(pmParamDAO.create(obj.getPmParameter()));
			}
			obj.setPmParameter(pmParamDAO.update(obj.getPmParameter()));
			
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE envparameter SET id = ?, marginInterf = ?, mhag = ?, cco = ?, Pmp_id = ?"+
							" WHERE id = " +obj.getId());
			prepare1.setInt(1, obj.getId());
			prepare1.setDouble(2, obj.getMarginInterf());
			prepare1.setDouble(3, obj.getMhag());
			prepare1.setDouble(4, obj.getCco());
			prepare1.setInt(5, obj.getPmParameter().getId());
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
	public EnvParameter find(int id) {
		EnvParameter envParam = new EnvParameter();
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM envparameter WHERE id = " +id
					);
			if(result1.first())
				envParam = new EnvParameter(
				id, 
				result1.getDouble("marginInterf"),
				result1.getDouble("mhag"),
				result1.getDouble("cco"),
				new PmParameterDAO(this.conn).find(result1.getInt("Pmp_id"))
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return envParam;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<EnvParameter> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
