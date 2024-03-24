/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.EnbParameter;
import cm.opsim.model.GenParameter;

/**
 * @author Romuald FOTSO
 *
 */
public class EnbParameterDAO extends DAO<EnbParameter> {

	public EnbParameterDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public EnbParameter create(EnbParameter obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM enbparameter "+
					"WHERE id = "+obj.getId()
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("enbparameter exist: start update ");
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
						"WHERE table_name = 'enbparameter' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO enbparameter (id, cableLoss, antennaGain, thermicNoiseByRB, powerTx, cellBoardSNIR," +
							" systemCharge, factorFC, jumperTmaLoss, siteType)"+
							"VALUES (?,?,?,?,?,?,?,?,?,?)");
					prepare1.setInt(1, obj.getId());
					prepare1.setDouble(2, obj.getCableLoss());
					prepare1.setDouble(3, obj.getAntennaGain());
					prepare1.setDouble(4, obj.getThermicNoiseByRB());
					prepare1.setDouble(5, obj.getPowerTx());
					prepare1.setDouble(6, obj.getCellBoardSNIR());
					prepare1.setDouble(7, obj.getSystemCharge());
					prepare1.setDouble(8, obj.getFactorFC());
					prepare1.setDouble(9, obj.getJumperTmaLoss());
					prepare1.setString(10, obj.getSiteType());
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
	public void delete(EnbParameter obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM enbparameter WHERE id = " +obj.getId()
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
	public EnbParameter update(EnbParameter obj) {
		try{
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE enbparameter SET id = ?, cableLoss = ?, antennaGain = ?, thermicNoiseByRB = ?, powerTx = ?, cellBoardSNIR = ?," +
							" systemCharge = ?, factorFC = ?, jumperTmaLoss = ?, siteType = ?" +
							" WHERE id = " +obj.getId());
			prepare1.setInt(1, obj.getId());
			prepare1.setDouble(2, obj.getCableLoss());
			prepare1.setDouble(3, obj.getAntennaGain());
			prepare1.setDouble(4, obj.getThermicNoiseByRB());
			prepare1.setDouble(5, obj.getPowerTx());
			prepare1.setDouble(6, obj.getCellBoardSNIR());
			prepare1.setDouble(7, obj.getSystemCharge());
			prepare1.setDouble(8, obj.getFactorFC());
			prepare1.setDouble(9, obj.getJumperTmaLoss());
			prepare1.setString(10, obj.getSiteType());
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
	public EnbParameter find(int id) {
		EnbParameter enbParam = new EnbParameter();
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM enbparameter WHERE id = " +id
					);
			if(result1.first())
				enbParam = new EnbParameter(
				id, 
				result1.getDouble("cableLoss"),
				result1.getDouble("antennaGain"),
				result1.getDouble("thermicNoiseByRB"),
				result1.getDouble("powerTx"),
				result1.getDouble("cellBoardSNIR"),
				result1.getDouble("systemCharge"),
				result1.getDouble("factorFC"),
				result1.getDouble("jumperTmaLoss"),
				result1.getString("siteType")
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return enbParam;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<EnbParameter> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
