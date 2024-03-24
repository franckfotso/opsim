/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.RPlanCapacity;
import cm.opsim.model.RPlanCoverage;

/**
 * @author Romuald FOTSO
 *
 */
public class RPlanCoverageDAO extends DAO<RPlanCoverage> {

	public RPlanCoverageDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public RPlanCoverage create(RPlanCoverage obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM rplancoverage "+
					"WHERE id = "+obj.getId()
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("rplancoverage exist: start update ");
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
						"WHERE table_name = 'rplancoverage' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO rplancoverage (id, pireUl, pireDl, sensibilityUL, sensibilityDL, thermicNoiseUL, thermicNoiseDL," +
							" pathLossUL, pathLossDL, cellRadiusUL, cellRadiusDL, cellPrintUL, cellPrintDL, sitePrintUL, sitePrintDL," +
							" numENBUl, numENBDl, interSiteDistUL, interSiteDistDL)"+
							"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
						prepare1.setInt(1, obj.getId());
						prepare1.setDouble(2, obj.getPireUl());
						prepare1.setDouble(3, obj.getPireDl());
						prepare1.setDouble(4, obj.getSensibilityUL());
						prepare1.setDouble(5, obj.getSensibilityDL());
						prepare1.setDouble(6, obj.getThermicNoiseUL());
						prepare1.setDouble(7, obj.getThermicNoiseDL());
						prepare1.setDouble(8, obj.getPathLossUL());
						prepare1.setDouble(9, obj.getPathLossDL());
						prepare1.setDouble(10, obj.getCellRadiusUL());
						prepare1.setDouble(11, obj.getCellRadiusDL());
						prepare1.setDouble(12, obj.getCellPrintUL());
						prepare1.setDouble(13, obj.getCellPrintDL());
						prepare1.setDouble(14, obj.getSitePrintUL());
						prepare1.setDouble(15, obj.getSitePrintDL());
						prepare1.setInt(16, obj.getNumENBUl());
						prepare1.setInt(17, obj.getNumENBDl());
						prepare1.setDouble(18, obj.getInterSiteDistUL());
						prepare1.setDouble(19, obj.getInterSiteDistDL());
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
	public void delete(RPlanCoverage obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM rplancoverage WHERE id = " +obj.getId()
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
	public RPlanCoverage update(RPlanCoverage obj) {
		try{
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE rplancoverage SET pireUl = ?, pireDl = ?, sensibilityUL = ?, sensibilityDL = ?," +
							" thermicNoiseUL = ?, thermicNoiseDL = ?, pathLossUL = ?, pathLossDL = ?, cellRadiusUL = ?," +
							" cellRadiusDL = ?, cellPrintUL = ?, cellPrintDL = ?, sitePrintUL = ?, sitePrintDL = ?," +
							" numENBUl = ?, numENBDl = ?, interSiteDistUL = ?, interSiteDistDL = ?"+
							" WHERE id = " +obj.getId());
			prepare1.setDouble(1, obj.getPireUl());
			prepare1.setDouble(2, obj.getPireDl());
			prepare1.setDouble(3, obj.getSensibilityUL());
			prepare1.setDouble(4, obj.getSensibilityDL());
			prepare1.setDouble(5, obj.getThermicNoiseUL());
			prepare1.setDouble(6, obj.getThermicNoiseDL());
			prepare1.setDouble(7, obj.getPathLossUL());
			prepare1.setDouble(8, obj.getPathLossDL());
			prepare1.setDouble(9, obj.getCellRadiusUL());
			prepare1.setDouble(10, obj.getCellRadiusDL());
			prepare1.setDouble(11, obj.getCellPrintUL());
			prepare1.setDouble(12, obj.getCellPrintDL());
			prepare1.setDouble(13, obj.getSitePrintUL());
			prepare1.setDouble(14, obj.getSitePrintDL());
			prepare1.setInt(15, obj.getNumENBUl());
			prepare1.setInt(16, obj.getNumENBDl());
			prepare1.setDouble(17, obj.getInterSiteDistUL());
			prepare1.setDouble(18, obj.getInterSiteDistDL());
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
	public RPlanCoverage find(int id) {
		RPlanCoverage resCov =  new RPlanCoverage();
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM rplancoverage WHERE id = " +id
					);
			if(result1.first())
				resCov = new RPlanCoverage(
				id, 
				result1.getDouble("pireUl"),
				result1.getDouble("pireDl"),
				result1.getDouble("sensibilityUL"),
				result1.getDouble("sensibilityDL"),
				result1.getDouble("thermicNoiseUL"),
				result1.getDouble("thermicNoiseDL"),
				result1.getDouble("pathLossUL"),
				result1.getDouble("pathLossDL"),
				result1.getDouble("cellRadiusUL"),
				result1.getDouble("cellRadiusDL"),
				result1.getDouble("cellPrintUL"),
				result1.getDouble("cellPrintDL"),
				result1.getDouble("sitePrintUL"),
				result1.getDouble("sitePrintDL"),
				result1.getInt("numENBUl"),
				result1.getInt("numENBDl"),
				result1.getDouble("interSiteDistUL"),
				result1.getDouble("interSiteDistDL")
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return resCov;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<RPlanCoverage> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
