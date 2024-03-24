/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.CapParameter;
import cm.opsim.model.GenParameter;

/**
 * @author Romuald FOTSO
 *
 */
public class CapParameterDAO extends DAO<CapParameter> {

	/**
	 * @param conn
	 */
	public CapParameterDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public CapParameter create(CapParameter obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM capparameter "+
					"WHERE id = "+obj.getId()
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("capparameter exist: start update ");
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
						"WHERE table_name = 'capparameter' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO capparameter (id,soh_RS_UL,soh_PRACH_UL,soh_PUCCH_UL,cpi_UL,probaQPSK_1on2_UL,effQPSK_1on2_UL," +
							"obf_UL,proba16qam_2on3_UL,eff16qam_2on3_UL,proba64qam_5on6_UL,eff64qam_5on6_UL,probaSMgain_1_UL,probaSMgain_2_UL," +
							"probaSMgain_4_UL,soh_RS_DL,soh_PSS_SSS_DL,soh_PBCCH_DL,soh_L1_L2_DL,cpi_DL,probaQPSK_1on2_DL,effQPSK_1on2_DL," +
							"obf_DL,proba16qam_2on3_DL,eff16qam_2on3_DL,proba64qam_5on6_DL,eff64qam_5on6_DL,probaSMgain_1_DL,probaSMgain_2_DL," +
							"probaSMgain_4_DL,probaSMgain_8_DL)"+
							"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");					
						prepare1.setInt(1, obj.getId());
						prepare1.setDouble(2, obj.getSoh_RS_UL());
						prepare1.setDouble(3, obj.getSoh_PRACH_UL());
						prepare1.setDouble(4, obj.getSoh_PUCCH_UL());
						prepare1.setDouble(5, obj.getCpi_UL());
						prepare1.setDouble(6, obj.getProbaQPSK_1on2_UL());
						prepare1.setDouble(7, obj.getEffQPSK_1on2_UL());
						prepare1.setDouble(8, obj.getObf_UL());
						prepare1.setDouble(9, obj.getProba16QAM_2on3_UL());
						prepare1.setDouble(10, obj.getEff16QAM_2on3_UL());
						prepare1.setDouble(11, obj.getProba64QAM_5on6_UL());
						prepare1.setDouble(12, obj.getEff64QAM_5on6_UL());
						prepare1.setDouble(13, obj.getProbaSMgain_1_UL());
						prepare1.setDouble(14, obj.getProbaSMgain_2_UL());
						prepare1.setDouble(15, obj.getProbaSMgain_4_UL());
						prepare1.setDouble(16, obj.getSoh_RS_DL());
						prepare1.setDouble(17, obj.getSoh_PSS_SSS_DL());
						prepare1.setDouble(18, obj.getSoh_PBCCH_DL());
						prepare1.setDouble(19, obj.getSoh_L1_L2_DL());
						prepare1.setDouble(20, obj.getCpi_DL());
						prepare1.setDouble(21, obj.getProbaQPSK_1on2_DL());
						prepare1.setDouble(22, obj.getEffQPSK_1on2_DL());
						prepare1.setDouble(23, obj.getObf_DL());
						prepare1.setDouble(24, obj.getProba16QAM_2on3_DL());
						prepare1.setDouble(25, obj.getEff16QAM_2on3_DL());
						prepare1.setDouble(26, obj.getProba64QAM_5on6_DL());
						prepare1.setDouble(27, obj.getEff16QAM_2on3_DL());
						prepare1.setDouble(28, obj.getProbaSMgain_1_DL());
						prepare1.setDouble(29, obj.getProbaSMgain_2_DL());
						prepare1.setDouble(30, obj.getProbaSMgain_4_DL());
						prepare1.setDouble(31, obj.getProbaSMgain_8_DL());
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
	public void delete(CapParameter obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM capparameter WHERE id = " +obj.getId()
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
	public CapParameter update(CapParameter obj) {
		try{
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE capparameter SET id	= ?,soh_RS_UL = ?,soh_PRACH_UL = ?,soh_PUCCH_UL	= ?,cpi_UL = ?," +
							"probaQPSK_1on2_UL = ?,effQPSK_1on2_UL = ?,obf_UL = ?,proba16qam_2on3_UL = ?,eff16qam_2on3_UL = ?," +
							"proba64qam_5on6_UL	= ?,eff64qam_5on6_UL = ?,probaSMgain_1_UL = ?,probaSMgain_2_UL = ?,probaSMgain_4_UL	= ?," +
							"soh_RS_DL = ?,soh_PSS_SSS_DL = ?,soh_PBCCH_DL = ?,soh_L1_L2_DL	= ?,cpi_DL = ?,probaQPSK_1on2_DL = ?," +
							"effQPSK_1on2_DL = ?,obf_DL	= ?,proba16qam_2on3_DL = ?,eff16qam_2on3_DL = ?,proba64qam_5on6_DL = ?," +
							"eff64qam_5on6_DL = ?,probaSMgain_1_DL = ?,probaSMgain_2_DL = ?,probaSMgain_4_DL = ?,probaSMgain_8_DL = ?"+
							" WHERE id = "+obj.getId());
			prepare1.setInt(1, obj.getId());
			prepare1.setDouble(2, obj.getSoh_RS_UL());
			prepare1.setDouble(3, obj.getSoh_PRACH_UL());
			prepare1.setDouble(4, obj.getSoh_PUCCH_UL());
			prepare1.setDouble(5, obj.getCpi_UL());
			prepare1.setDouble(6, obj.getProbaQPSK_1on2_UL());
			prepare1.setDouble(7, obj.getEffQPSK_1on2_UL());
			prepare1.setDouble(8, obj.getObf_UL());
			prepare1.setDouble(9, obj.getProba16QAM_2on3_UL());
			prepare1.setDouble(10, obj.getEff16QAM_2on3_UL());
			prepare1.setDouble(11, obj.getProba64QAM_5on6_UL());
			prepare1.setDouble(12, obj.getEff64QAM_5on6_UL());
			prepare1.setDouble(13, obj.getProbaSMgain_1_UL());
			prepare1.setDouble(14, obj.getProbaSMgain_2_UL());
			prepare1.setDouble(15, obj.getProbaSMgain_4_UL());
			prepare1.setDouble(16, obj.getSoh_RS_DL());
			prepare1.setDouble(17, obj.getSoh_PSS_SSS_DL());
			prepare1.setDouble(18, obj.getSoh_PBCCH_DL());
			prepare1.setDouble(19, obj.getSoh_L1_L2_DL());
			prepare1.setDouble(20, obj.getCpi_DL());
			prepare1.setDouble(21, obj.getProbaQPSK_1on2_DL());
			prepare1.setDouble(22, obj.getEffQPSK_1on2_DL());
			prepare1.setDouble(23, obj.getObf_DL());
			prepare1.setDouble(24, obj.getProba16QAM_2on3_DL());
			prepare1.setDouble(25, obj.getEff16QAM_2on3_DL());
			prepare1.setDouble(26, obj.getProba64QAM_5on6_DL());
			prepare1.setDouble(27, obj.getEff16QAM_2on3_DL());
			prepare1.setDouble(28, obj.getProbaSMgain_1_DL());
			prepare1.setDouble(29, obj.getProbaSMgain_2_DL());
			prepare1.setDouble(30, obj.getProbaSMgain_4_DL());
			prepare1.setDouble(31, obj.getProbaSMgain_8_DL());
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
	public CapParameter find(int id) {
		CapParameter capParam = new CapParameter();
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM capparameter WHERE id = " +id
					);
			if(result1.first())
				capParam = new CapParameter(
				id, 
				result1.getDouble("soh_RS_UL"),
				result1.getDouble("soh_PRACH_UL"),
				result1.getDouble("soh_PUCCH_UL"),
				result1.getDouble("cpi_UL"),
				result1.getDouble("probaQPSK_1on2_UL"),
				result1.getDouble("effQPSK_1on2_UL"),
				result1.getDouble("obf_UL"),
				result1.getDouble("proba16qam_2on3_UL"),
				result1.getDouble("eff16qam_2on3_UL"),
				result1.getDouble("proba64qam_5on6_UL"),
				result1.getDouble("eff64qam_5on6_UL"),
				result1.getDouble("probaSMgain_1_UL"),
				result1.getDouble("probaSMgain_2_UL"),
				result1.getDouble("probaSMgain_4_UL"),
				result1.getDouble("soh_RS_DL"),
				result1.getDouble("soh_PSS_SSS_DL"),
				result1.getDouble("soh_PBCCH_DL"),
				result1.getDouble("soh_L1_L2_DL"),
				result1.getDouble("cpi_DL"),
				result1.getDouble("probaQPSK_1on2_DL"),
				result1.getDouble("effQPSK_1on2_DL"),
				result1.getDouble("obf_DL"),
				result1.getDouble("proba16qam_2on3_DL"),
				result1.getDouble("eff16qam_2on3_DL"),
				result1.getDouble("proba64qam_5on6_DL"),
				result1.getDouble("eff64qam_5on6_DL"),
				result1.getDouble("probaSMgain_1_DL"),
				result1.getDouble("probaSMgain_2_DL"),
				result1.getDouble("probaSMgain_4_DL"),
				result1.getDouble("probaSMgain_8_DL")
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return capParam;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<CapParameter> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
