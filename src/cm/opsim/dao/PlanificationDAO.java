/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import cm.opsim.model.*;

/**
 * @author Romuald FOTSO
 *
 */
public class PlanificationDAO extends DAO<Planification> {

	public PlanificationDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public Planification create(Planification obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM planification "+
					"WHERE id = '"+obj.getId()+"'"
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("Planification exist: start update ");
				obj.setId(id);
				obj = this.update(obj);
			}
			else
			{
				if(obj.getResult().getId() == 0){
					DAO<Result>resDAO =DAOFactory.getResultDAO();
					obj.setResult(resDAO.create(obj.getResult()));
				}				
				
				ResultSet result1 = this.conn
						.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_UPDATABLE
						).executeQuery(
						"SELECT AUTO_INCREMENT "+
						"FROM information_schema.tables "+
						"WHERE table_name = 'planification' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
						.prepareStatement(
						"INSERT INTO planification (Res_id, createdDate, updatedDate)"+
						"VALUES (?,NOW(),NOW())");
					prepare1.setInt(1, obj.getResult().getId());
					prepare1.executeUpdate();
					
					obj.setId(id);
					DAO<CoveragePlanif>covPlanifDAO =DAOFactory.getCoveragePlanifDAO();
					DAO<CapacityPlanif>capPlanifDAO =DAOFactory.getCapacityPlanifDAO();
					DAO<FrequencyPlanif>freqPlanifDAO =DAOFactory.getFrequencyPlanifDAO();
					DAO<CapexOpexPlanif>coPlanifDAO =DAOFactory.getCapexOpexPlanifDAO();
						
					obj.setCovPlanif(covPlanifDAO.create(obj.getCovPlanif()));						
					obj.setCapPlanif(capPlanifDAO.create(obj.getCapPlanif()));						
					obj.setFreqPlanif(freqPlanifDAO.create(obj.getFreqPlanif()));						
					obj.setCoPlanif(coPlanifDAO.create(obj.getCoPlanif()));
					
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
	public void delete(Planification obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM planification WHERE id = " +obj.getId()
					);
			
			DAO<CoveragePlanif>covPlanifDAO =DAOFactory.getCoveragePlanifDAO();
			DAO<CapacityPlanif>capPlanifDAO =DAOFactory.getCapacityPlanifDAO();
			DAO<FrequencyPlanif>freqPlanifDAO =DAOFactory.getFrequencyPlanifDAO();
			DAO<CapexOpexPlanif>coPlanifDAO =DAOFactory.getCapexOpexPlanifDAO();
				
			covPlanifDAO.delete(obj.getCovPlanif());						
			capPlanifDAO.delete(obj.getCapPlanif());						
			freqPlanifDAO.delete(obj.getFreqPlanif());						
			coPlanifDAO.delete(obj.getCoPlanif());
		}
		catch(SQLException e) {
			e.printStackTrace();
		}		
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#update(java.lang.Object)
	 */
	@Override
	public Planification update(Planification obj) {
		try{			
			DAO<Result>resDAO =DAOFactory.getResultDAO();
			if(obj.getResult().getId() == 0){				
				obj.setResult(resDAO.create(obj.getResult()));
			}
			resDAO.update(obj.getResult());
			
			DAO<CoveragePlanif>covPlanifDAO =DAOFactory.getCoveragePlanifDAO();
			DAO<CapacityPlanif>capPlanifDAO =DAOFactory.getCapacityPlanifDAO();
			DAO<FrequencyPlanif>freqPlanifDAO =DAOFactory.getFrequencyPlanifDAO();
			DAO<CapexOpexPlanif>coPlanifDAO =DAOFactory.getCapexOpexPlanifDAO();
				
			obj.setCovPlanif(covPlanifDAO.update(obj.getCovPlanif()));						
			obj.setCapPlanif(capPlanifDAO.update(obj.getCapPlanif()));						
			obj.setFreqPlanif(freqPlanifDAO.update(obj.getFreqPlanif()));						
			obj.setCoPlanif(coPlanifDAO.update(obj.getCoPlanif()));
			
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE planification SET Res_id = ?, updatedDate = NOW()"+
							" WHERE id = " +obj.getId());
				prepare1.setInt(1, obj.getResult().getId());
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
	public Planification find(int id) {
		Planification planif = new Planification();
		
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM planification WHERE id = " +id
					);			
			if(result1.first());
				planif = new Planification(
				id, 
				result1.getDate("createdDate"),
				result1.getDate("updatedDate"),
				new ResultDAO(this.conn).find(result1.getInt("Res_id")),
				new CoveragePlanifDAO(this.conn).find(id),
				new CapacityPlanifDAO(this.conn).find(id),
				new FrequencyPlanifDAO(this.conn).find(id),
				new CapexOpexPlanifDAO(this.conn).find(id)
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}		
		return planif;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<Planification> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
