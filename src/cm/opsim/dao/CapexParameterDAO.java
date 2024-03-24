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
import cm.opsim.model.EnbParameter;

/**
 * @author Romuald FOTSO
 *
 */
public class CapexParameterDAO extends DAO<CapexParameter> {

	public CapexParameterDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public CapexParameter create(CapexParameter obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM capexparameter "+
					"WHERE id = "+obj.getId()
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("capexparameter exist: start update ");
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
						"WHERE table_name = 'capexparameter' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO capexparameter (id, ingRadio, ingReseau, rechFrs, rechSites, locaSites," +
							" genieCivil, racElec, installStation, racRxIP, equipRx, infraIP, logiApps, infraVOIP," +
							" infraRadio, infraRx, locauxMobilers, suiviProj)"+
							"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					prepare1.setInt(1, obj.getId());
					prepare1.setDouble(2, obj.getIngRadio());
					prepare1.setDouble(3, obj.getIngReseau());
					prepare1.setDouble(4, obj.getRechFrs());
					prepare1.setDouble(5, obj.getRechSites());
					prepare1.setDouble(6, obj.getLocaSites());
					prepare1.setDouble(7, obj.getGenieCivil());
					prepare1.setDouble(8, obj.getRacElec());
					prepare1.setDouble(9, obj.getInstallStation());
					prepare1.setDouble(10, obj.getRacRxIP());
					prepare1.setDouble(11, obj.getEquipRx());
					prepare1.setDouble(12, obj.getInfraIP());
					prepare1.setDouble(13, obj.getLogiApps());
					prepare1.setDouble(14, obj.getInfraVOIP());
					prepare1.setDouble(15, obj.getInfraRadio());
					prepare1.setDouble(16, obj.getInfraRx());
					prepare1.setDouble(17, obj.getLocauxMobilers());
					prepare1.setDouble(18, obj.getSuiviProj());
					
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
	public void delete(CapexParameter obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM capexparameter WHERE id = " +obj.getId()
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
	public CapexParameter update(CapexParameter obj) {
		try{
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE capexparameter SET id = ?, ingRadio = ?, ingReseau = ?, rechFrs = ?, rechSites = ?, locaSites = ?," +
							" genieCivil = ?, racElec = ?, installStation = ?, racRxIP = ?, equipRx = ?, infraIP = ?, logiApps = ?," +
							" infraVOIP = ?, infraRadio = ?, infraRx = ?, locauxMobilers = ?, suiviProj = ?" +
							" WHERE id = " +obj.getId());
			prepare1.setInt(1, obj.getId());
			prepare1.setDouble(2, obj.getIngRadio());
			prepare1.setDouble(3, obj.getIngReseau());
			prepare1.setDouble(4, obj.getRechFrs());
			prepare1.setDouble(5, obj.getRechSites());
			prepare1.setDouble(6, obj.getLocaSites());
			prepare1.setDouble(7, obj.getGenieCivil());
			prepare1.setDouble(8, obj.getRacElec());
			prepare1.setDouble(9, obj.getInstallStation());
			prepare1.setDouble(10, obj.getRacRxIP());
			prepare1.setDouble(11, obj.getEquipRx());
			prepare1.setDouble(12, obj.getInfraIP());
			prepare1.setDouble(13, obj.getLogiApps());
			prepare1.setDouble(14, obj.getInfraVOIP());
			prepare1.setDouble(15, obj.getInfraRadio());
			prepare1.setDouble(16, obj.getInfraRx());
			prepare1.setDouble(17, obj.getLocauxMobilers());
			prepare1.setDouble(18, obj.getSuiviProj());
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
	public CapexParameter find(int id) {
		CapexParameter capexParam = new CapexParameter();
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM capexparameter WHERE id = " +id
					);
			
			if(result1.first())
				capexParam = new CapexParameter(
				id, 
				result1.getDouble("ingRadio"),
				result1.getDouble("ingReseau"),
				result1.getDouble("rechFrs"),
				result1.getDouble("rechSites"),
				result1.getDouble("locaSites"),
				result1.getDouble("genieCivil"),
				result1.getDouble("racElec"),
				result1.getDouble("installStation"),
				result1.getDouble("racRxIP"),
				result1.getDouble("equipRx"),
				result1.getDouble("infraIP"),
				result1.getDouble("logiApps"),
				result1.getDouble("infraVOIP"),
				result1.getDouble("infraRadio"),
				result1.getDouble("infraRx"),
				result1.getDouble("locauxMobilers"),
				result1.getDouble("suiviProj")
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return capexParam;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<CapexParameter> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
