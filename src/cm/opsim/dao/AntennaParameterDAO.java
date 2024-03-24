/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.AntennaParameter;
import cm.opsim.model.AntennaType;
import cm.opsim.model.TrafficModel;

/**
 * @author Romuald FOTSO
 *
 */
public class AntennaParameterDAO extends DAO<AntennaParameter> {

	public AntennaParameterDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public AntennaParameter create(AntennaParameter obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM antennaparameter "+
					"WHERE id = "+obj.getId()
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("antennaparameter exist: start update ");
				obj.setId(id);
				obj = this.update(obj);
			}
			else
			{
				if(obj.getAntennaType().getId() == 0){
					DAO<AntennaType>antTypeDAO =DAOFactory.getAntennaTypeDAO();
					obj.setAntennaType(antTypeDAO.find(1));
				}				
				ResultSet result1 = this.conn
						.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_UPDATABLE
						).executeQuery(
						"SELECT AUTO_INCREMENT "+
						"FROM information_schema.tables "+
						"WHERE table_name = 'antennaparameter' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO antennaparameter (id, type, radDiagram, directivity, Ant_id, gain, efficiency)"+
							"VALUES (?,?,?,?,?,?,?)");
						prepare1.setInt(1, obj.getId());
						prepare1.setString(2, obj.getType());
						prepare1.setString(3, obj.getRadDiagram());
						prepare1.setDouble(4, obj.getDirectivity());
						prepare1.setInt(5, obj.getAntennaType().getId());
						prepare1.setDouble(6, obj.getGain());
						prepare1.setDouble(7, obj.getEfficiency());
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
	public void delete(AntennaParameter obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM antennaparameter WHERE id = " +obj.getId()
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
	public AntennaParameter update(AntennaParameter obj) {
		try{
//			DAO<AntennaType>antTypeDAO =DAOFactory.getAntennaTypeDAO();
//			if(obj.getAntennaType().getId() == 0){
//				obj.setAntennaType(antTypeDAO.create(obj.getAntennaType()));
//			}
//			obj.setAntennaType(antTypeDAO.update(obj.getAntennaType()));
						
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE antennaparameter SET id = ?, type = ?, radDiagram = ?, directivity = ?, Ant_id = ?," +
							" gain = ?, efficiency = ?"+
							" WHERE id = " +obj.getId());
			prepare1.setInt(1, obj.getId());
			prepare1.setString(2, obj.getType());
			prepare1.setString(3, obj.getRadDiagram());
			prepare1.setDouble(4, obj.getDirectivity());
			prepare1.setInt(5, obj.getAntennaType().getId());
			prepare1.setDouble(6, obj.getGain());
			prepare1.setDouble(7, obj.getEfficiency());
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
	public AntennaParameter find(int id) {
		AntennaParameter antParam = new AntennaParameter();
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM antennaparameter WHERE id = " +id
					);
			if(result1.first())
				antParam = new AntennaParameter(
				id, 
				result1.getString("type"),
				result1.getString("radDiagram"),
				result1.getDouble("directivity"),				
				result1.getDouble("gain"),
				result1.getDouble("efficiency"),
				new AntennaTypeDAO(this.conn).find(result1.getInt("Ant_id"))
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
		return antParam;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<AntennaParameter> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
