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
import cm.opsim.model.Parameter;

/**
 * @author Romuald FOTSO
 *
 */
public class GenParameterDAO extends DAO<GenParameter> {
	
	public GenParameterDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public GenParameter create(GenParameter obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM genparameter "+
					"WHERE id = "+obj.getId()
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("genparameter exist: start update ");
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
						"WHERE table_name = 'genparameter' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO genparameter (id, freqBand, bwCellBoardUL, bwCellBoardDL, numPRBUl, channelModel," +
							" coverageProba, bodyLoss, buildingLoss, carLoss, fadingMargin, figureNoise, bwByPRB, bwFB)"+
							"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
						prepare1.setInt(1, obj.getId());
						prepare1.setDouble(2, obj.getFreqBand());
						prepare1.setDouble(3, obj.getBwCellBoardUL());
						prepare1.setDouble(4, obj.getBwCellBoardDL());
						prepare1.setDouble(5, obj.getNumPRBUl());
						prepare1.setString(6, obj.getChannelModel());
						prepare1.setDouble(7, obj.getCoverageProba());
						prepare1.setDouble(8, obj.getBodyLoss());
						prepare1.setDouble(9, obj.getBuildingLoss());
						prepare1.setDouble(10, obj.getCarLoss());
						prepare1.setDouble(11, obj.getFadingMargin());
						prepare1.setDouble(12, obj.getFigureNoise());
						prepare1.setDouble(13, obj.getBwByPRB());
						prepare1.setDouble(14, obj.getBwFB());
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
	public void delete(GenParameter obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM genparameter WHERE id = " +obj.getId()
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
	public GenParameter update(GenParameter obj) {
		try{
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE genparameter SET id = ?, freqBand = ?, bwCellBoardUL = ?, bwCellBoardDL = ?, numPRBUl = ?, channelModel = ?," +
							" coverageProba = ?, bodyLoss = ?, buildingLoss = ?, carLoss = ?, fadingMargin = ?, figureNoise = ?," +
							" bwByPRB = ?, bwFB = ?"+
							" WHERE id = " +obj.getId());
			prepare1.setInt(1, obj.getId());
			prepare1.setDouble(2, obj.getFreqBand());
			prepare1.setDouble(3, obj.getBwCellBoardUL());
			prepare1.setDouble(4, obj.getBwCellBoardDL());
			prepare1.setDouble(5, obj.getNumPRBUl());
			prepare1.setString(6, obj.getChannelModel());
			prepare1.setDouble(7, obj.getCoverageProba());
			prepare1.setDouble(8, obj.getBodyLoss());
			prepare1.setDouble(9, obj.getBuildingLoss());
			prepare1.setDouble(10, obj.getCarLoss());
			prepare1.setDouble(11, obj.getFadingMargin());
			prepare1.setDouble(12, obj.getFigureNoise());
			prepare1.setDouble(13, obj.getBwByPRB());
			prepare1.setDouble(14, obj.getBwFB());
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
	public GenParameter find(int id) {
		GenParameter genParam = new GenParameter();
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM genparameter WHERE id = " +id
					);
			if(result1.first())
				genParam = new GenParameter(
				id, 
				result1.getDouble("freqBand"),
				result1.getDouble("bwCellBoardUL"),
				result1.getDouble("bwCellBoardDL"),
				result1.getInt("numPRBUl"),
				result1.getString("channelModel"),
				result1.getDouble("coverageProba"),
				result1.getDouble("bodyLoss"),
				result1.getDouble("buildingLoss"),
				result1.getDouble("carLoss"),
				result1.getDouble("fadingMargin"),
				result1.getDouble("figureNoise"),
				result1.getDouble("bwByPRB"),
				result1.getDouble("bwFB")
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}		
		return genParam;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<GenParameter> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
