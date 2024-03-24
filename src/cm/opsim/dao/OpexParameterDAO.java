/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.CustomerParameter;
import cm.opsim.model.OpexParameter;
import cm.opsim.model.PrevIncomings;
import cm.opsim.model.Subscription;
import cm.opsim.model.TrafficModel;

/**
 * @author Romuald FOTSO
 *
 */
public class OpexParameterDAO extends DAO<OpexParameter> {

	public OpexParameterDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public OpexParameter create(OpexParameter obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM opexparameter "+
					"WHERE id = "+obj.getId()
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("opexparameter exist: start update ");
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
						"WHERE table_name = 'opexparameter' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO opexparameter (id, nbResident, percentCust, growingRate, marketing, interWeb, adminFGen," +
							" assurance, locaSiteRadio, locaFreq, enerElec, locaInfra, formaPerso, suiviRx, factAb, txImpot)"+
							"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					prepare1.setInt(1, obj.getId());
					prepare1.setDouble(2, obj.getNbResident());
					prepare1.setDouble(3, obj.getPercentCust());
					prepare1.setDouble(4, obj.getGrowingRate());
					prepare1.setDouble(5, obj.getMarketing());
					prepare1.setDouble(6, obj.getInterWeb());
					prepare1.setDouble(7, obj.getAdminFGen());
					prepare1.setDouble(8, obj.getAssurance());
					prepare1.setDouble(9, obj.getLocaSiteRadio());
					prepare1.setDouble(10, obj.getLocaFreq());
					prepare1.setDouble(11, obj.getEnerElec());
					prepare1.setDouble(12, obj.getLocaInfra());
					prepare1.setDouble(13, obj.getFormaPerso());
					prepare1.setDouble(14, obj.getSuiviRx());
					prepare1.setDouble(15, obj.getFactAb());
					prepare1.setDouble(16, obj.getTxImpot());
					prepare1.executeUpdate();
					
					obj.setId(id);
					for(PrevIncomings prevIn : obj.getListPrevIncomings()){						
						if(prevIn.getId() == 0){
							DAO<PrevIncomings>prevInDAO =DAOFactory.getPrevIncomingsDAO();
							prevIn.setOpexParam_id(id);
							prevIn = prevInDAO.create(prevIn);
						}					
					}					
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
	public void delete(OpexParameter obj) {
		try {
			for(PrevIncomings prevIn : obj.getListPrevIncomings()){
				DAO<PrevIncomings>prevInDAO =DAOFactory.getPrevIncomingsDAO();
				prevInDAO.delete(prevIn);								
			}
			
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM opexparameter WHERE id = " +obj.getId()
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
	public OpexParameter update(OpexParameter obj) {
		try{
			DAO<PrevIncomings>prevInDAO =DAOFactory.getPrevIncomingsDAO();
			
			for(PrevIncomings prevIn : obj.getListPrevIncomings()){
				if(prevIn.getId() == 0){					
					prevIn.setOpexParam_id(obj.getId());
					prevIn = prevInDAO.create(prevIn);
				}					
				else prevInDAO.update(prevIn);
			}
			
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE opexparameter SET id = ?, nbResident = ?, percentCust = ?, growingRate = ?, " +
							"marketing = ?, interWeb = ?, adminFGen = ?, assurance = ?, locaSiteRadio = ?, locaFreq = ?," +
							" enerElec = ?, locaInfra = ?, formaPerso = ?, suiviRx = ?, factAb = ?, txImpot = ?"+
							" WHERE id = " +obj.getId());
			prepare1.setInt(1, obj.getId());
			prepare1.setDouble(2, obj.getNbResident());
			prepare1.setDouble(3, obj.getPercentCust());
			prepare1.setDouble(4, obj.getGrowingRate());
			prepare1.setDouble(5, obj.getMarketing());
			prepare1.setDouble(6, obj.getInterWeb());
			prepare1.setDouble(7, obj.getAdminFGen());
			prepare1.setDouble(8, obj.getAssurance());
			prepare1.setDouble(9, obj.getLocaSiteRadio());
			prepare1.setDouble(10, obj.getLocaFreq());
			prepare1.setDouble(11, obj.getEnerElec());
			prepare1.setDouble(12, obj.getLocaInfra());
			prepare1.setDouble(13, obj.getFormaPerso());
			prepare1.setDouble(14, obj.getSuiviRx());
			prepare1.setDouble(15, obj.getFactAb());
			prepare1.setDouble(16, obj.getTxImpot());
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
	public OpexParameter find(int id) {
		OpexParameter opexParam  = new OpexParameter();
		ArrayList<PrevIncomings> listPrevIncomings = new ArrayList<PrevIncomings>();
		
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM previncomings WHERE OpexParam_id = " +id
					);
			if(result1.first()){
				DAO<PrevIncomings>prevInDAO =DAOFactory.getPrevIncomingsDAO();			
				result1.beforeFirst();
				while(result1.next() && result1.getLong("id") != 0)
					listPrevIncomings.add(prevInDAO.find(result1.getInt("id")));
			}			
			ResultSet result2 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM opexparameter WHERE id = " +id
					);
			if(result2.first())
				opexParam = new OpexParameter(
				id,
				result2.getDouble("nbResident"),
				result2.getDouble("percentCust"),
				result2.getDouble("growingRate"),
				listPrevIncomings,
				result2.getDouble("marketing"),
				result2.getDouble("interWeb"),
				result2.getDouble("adminFGen"),
				result2.getDouble("assurance"),
				result2.getDouble("locaSiteRadio"),
				result2.getDouble("locaFreq"),
				result2.getDouble("enerElec"),
				result2.getDouble("locaInfra"),
				result2.getDouble("formaPerso"),
				result2.getDouble("suiviRx"),
				result2.getDouble("factAb"),
				result2.getDouble("txImpot")
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return opexParam;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<OpexParameter> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
