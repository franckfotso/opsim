/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.PrevIncomings;
import cm.opsim.model.RCapex;
import cm.opsim.model.RDimensionning;
import cm.opsim.model.ROpex;
import cm.opsim.model.RPlanCapacity;
import cm.opsim.model.RPlanCoverage;
import cm.opsim.model.RPlanFrequency;
import cm.opsim.model.Result;

/**
 * @author Romuald FOTSO
 *
 */
public class ResultDAO extends DAO<Result> {

	public ResultDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public Result create(Result obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM result "+
					"WHERE id = '"+obj.getId()+"'"
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("Result exist: start update ");
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
						"WHERE table_name = 'result' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
						.prepareStatement(
						"INSERT INTO result (createdDate, updatedDate)"+
						"VALUES (NOW(),NOW())");
					prepare1.executeUpdate();
					
					obj.setId(id);
					DAO<RPlanCapacity>resCapDAO =DAOFactory.getRPlanCapacityDAO();
					DAO<RPlanCoverage>resCovDAO =DAOFactory.getRPlanCoverageDAO();
					DAO<RPlanFrequency>resFreqDAO =DAOFactory.getRPlanFrequencyDAO();
					DAO<RDimensionning>resDimDAO =DAOFactory.getRDimensionningDAO();
					DAO<RCapex>rcapexDAO =DAOFactory.getRCapexDAO();
					
					obj.setResCap(resCapDAO.create(obj.getResCap()));
					obj.setResCov(resCovDAO.create(obj.getResCov()));
					obj.setResFreq(resFreqDAO.create(obj.getResFreq()));
					obj.setResDim(resDimDAO.create(obj.getResDim()));
					obj.setRcapex(rcapexDAO.create(obj.getRcapex()));
					
					System.out.println("Create: N. ROpex: "+obj.getListROpex().size());
					for(ROpex ropex : obj.getListROpex()){						
						if(ropex.getId() == 0){
							DAO<ROpex> ropexDAO =DAOFactory.getROpexDAO();
							ropex.setResult_id(id);
							ropex = ropexDAO.create(ropex);
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
	public void delete(Result obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM result WHERE id = " +obj.getId()
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
	public Result update(Result obj) {
		try{
			DAO<RPlanCapacity>resCapDAO =DAOFactory.getRPlanCapacityDAO();
			DAO<RPlanCoverage>resCovDAO =DAOFactory.getRPlanCoverageDAO();
			DAO<RPlanFrequency>resFreqDAO =DAOFactory.getRPlanFrequencyDAO();
			DAO<RDimensionning>resDimDAO =DAOFactory.getRDimensionningDAO();
			DAO<RCapex>rcapexDAO =DAOFactory.getRCapexDAO();
			
			obj.setResCap(resCapDAO.update(obj.getResCap()));
			obj.setResCov(resCovDAO.update(obj.getResCov()));
			obj.setResFreq(resFreqDAO.update(obj.getResFreq()));
			obj.setResDim(resDimDAO.update(obj.getResDim()));
			obj.setRcapex(rcapexDAO.update(obj.getRcapex()));
			
			System.out.println("Update: N. ROpex: "+obj.getListROpex().size());
			DAO<ROpex> ropexDAO =DAOFactory.getROpexDAO();
			for(ROpex ropex : obj.getListROpex()){
				if(ropex.getId() == 0){					
					ropex.setResult_id(obj.getId());
					ropex = ropexDAO.create(ropex);
				}					
				else ropexDAO.update(ropex);
			}
			
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE result SET updatedDate = NOW()"+
							" WHERE id = " +obj.getId());
				prepare1.executeUpdate();
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
	public Result find(int id) {
		Result res = new Result();		
		ArrayList<ROpex> listROpex = new ArrayList<ROpex>();
		
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM ropex WHERE Result_id = " +id
					);
			if(result1.first()){
				DAO<ROpex> ropexDAO =DAOFactory.getROpexDAO();		
				result1.beforeFirst();
				while(result1.next() && result1.getLong("id") != 0)
					listROpex.add(ropexDAO.find(result1.getInt("id")));
			}
			
			ResultSet result2 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM result WHERE id = " +id
					);
			if(result2.first())
				res = new Result(
				id, 
				result2.getDate("createdDate"),
				result2.getDate("updatedDate"),
				new RPlanCapacityDAO(this.conn).find(id),
				new RPlanCoverageDAO(this.conn).find(id),
				new RPlanFrequencyDAO(this.conn).find(id),
				new RDimensionningDAO(this.conn).find(id),
				new RCapexDAO(this.conn).find(id),
				listROpex
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
		return res;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<Result> findAll() {
		return null;
	}
}
