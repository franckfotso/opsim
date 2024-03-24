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
import cm.opsim.model.UeParameter;

/**
 * @author Romuald FOTSO
 *
 */
public class UeParameterDAO extends DAO<UeParameter> {

	public UeParameterDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public UeParameter create(UeParameter obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM ueparameter "+
					"WHERE id = "+obj.getId()
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("ueparameter exist: start update ");
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
						"WHERE table_name = 'ueparameter' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO ueparameter (id, powerUE, antennaGain, thermicNoiseByRB, targetSNIR, systCharge," +
							" factorF, jumperTmaLoss)"+
							"VALUES (?,?,?,?,?,?,?,?)");
						prepare1.setInt(1, obj.getId());
						prepare1.setDouble(2, obj.getPowerUE());
						prepare1.setDouble(3, obj.getAntennaGain());
						prepare1.setDouble(4, obj.getThermicNoiseByRB());
						prepare1.setDouble(5, obj.getTargetSNIR());
						prepare1.setDouble(6, obj.getSystCharge());
						prepare1.setDouble(7, obj.getFactorF());
						prepare1.setDouble(8, obj.getJumperTmaLoss());
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
	public void delete(UeParameter obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM ueparameter WHERE id = " +obj.getId()
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
	public UeParameter update(UeParameter obj) {
		try{
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE ueparameter SET id = ?, powerUE = ?, antennaGain = ?, thermicNoiseByRB = ?, targetSNIR = ?, systCharge = ?," +
							" factorF = ?, jumperTmaLoss = ?" +
							" WHERE id = " +obj.getId());
			prepare1.setInt(1, obj.getId());
			prepare1.setDouble(2, obj.getPowerUE());
			prepare1.setDouble(3, obj.getAntennaGain());
			prepare1.setDouble(4, obj.getThermicNoiseByRB());
			prepare1.setDouble(5, obj.getTargetSNIR());
			prepare1.setDouble(6, obj.getSystCharge());
			prepare1.setDouble(7, obj.getFactorF());
			prepare1.setDouble(8, obj.getJumperTmaLoss());
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
	public UeParameter find(int id) {
		UeParameter ueParam = new UeParameter();
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM ueparameter WHERE id = " +id
					);
			if(result1.first())
				ueParam = new UeParameter(
				id, 
				result1.getDouble("powerUE"),
				result1.getDouble("antennaGain"),
				result1.getDouble("thermicNoiseByRB"),
				result1.getDouble("targetSNIR"),
				result1.getDouble("systCharge"),
				result1.getDouble("factorF"),
				result1.getDouble("jumperTmaLoss")
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return ueParam;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<UeParameter> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
