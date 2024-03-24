/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import cm.opsim.model.CustomerParameter;
import cm.opsim.model.Map;
import cm.opsim.model.Subscription;
import cm.opsim.model.TrafficModel;

/**
 * @author Romuald FOTSO
 *
 */
public class CustomerParameterDAO extends DAO<CustomerParameter> {

	public CustomerParameterDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public CustomerParameter create(CustomerParameter obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM customerparameter "+
					"WHERE id = "+obj.getId()
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("customerparameter exist: start update ");
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
						"WHERE table_name = 'customerparameter' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO customerparameter (id, marPart, penRate, growthRate, invTime)"+
							"VALUES (?,?,?,?,?)");
					prepare1.setInt(1, obj.getId());
					prepare1.setDouble(2, obj.getMarPart());
					prepare1.setDouble(3, obj.getPenRate());
					prepare1.setDouble(4, obj.getGrowthRate());
					prepare1.setInt(5, obj.getInvTime());
					prepare1.executeUpdate();
					
					obj.setId(id);
					for(Subscription sub : obj.getListSub()){						
						if(sub.getId() == 0){
							DAO<Subscription>subDAO =DAOFactory.getSubscriptionDAO();
							sub.setCustParam_id(id);
							sub = subDAO.create(sub);
						}					
					}		
					for(TrafficModel tm : obj.getListTM()){						
						if(tm.getId() == 0){
							DAO<TrafficModel>tmDAO =DAOFactory.getTrafficModelDAO();
							tm.setCustParam_id(id);
							tm = tmDAO.create(tm);
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
	public void delete(CustomerParameter obj) {
		try {
			for(Subscription sub : obj.getListSub()){
				DAO<Subscription>subDAO =DAOFactory.getSubscriptionDAO();
				subDAO.delete(sub);								
			}
			for(TrafficModel tm : obj.getListTM()){
				DAO<TrafficModel>tmDAO =DAOFactory.getTrafficModelDAO();
				tmDAO.delete(tm);				
			}
			
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM customerparameter WHERE id = " +obj.getId()
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
	public CustomerParameter update(CustomerParameter obj) {
		try{
			DAO<Subscription>subDAO =DAOFactory.getSubscriptionDAO();
			DAO<TrafficModel>tmDAO =DAOFactory.getTrafficModelDAO();
			
			for(Subscription sub : obj.getListSub()){
				if(sub.getId() == 0){					
					sub.setCustParam_id(obj.getId());
					sub = subDAO.create(sub);
				}					
				else subDAO.update(sub);
			}
			for(TrafficModel tm : obj.getListTM()){						
				if(tm.getId() == 0){					
					tm.setCustParam_id(obj.getId());
					tm = tmDAO.create(tm);
				}	
				else tmDAO.update(tm);
			}
			
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE customerparameter SET id = ?, marPart = ?, penRate = ?, growthRate = ?, invTime = ?"+
							" WHERE id = " +obj.getId());
			prepare1.setInt(1, obj.getId());
			prepare1.setDouble(2, obj.getMarPart());
			prepare1.setDouble(3, obj.getPenRate());
			prepare1.setDouble(4, obj.getGrowthRate());
			prepare1.setInt(5, obj.getInvTime());
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
	public CustomerParameter find(int id) {
		CustomerParameter custParam  = new CustomerParameter();
		ArrayList<Subscription> listSub = new ArrayList<Subscription>();
		ArrayList<TrafficModel> listTM = new ArrayList<TrafficModel>();
		
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM subscription WHERE CustParam_id = " +id
					);
			if(result1.first()){
				DAO<Subscription>subDAO =DAOFactory.getSubscriptionDAO();			
				result1.beforeFirst();
				while(result1.next() && result1.getLong("id") != 0)
					listSub.add(subDAO.find(result1.getInt("id")));
			}
			
			ResultSet result2 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM trafficmodel WHERE CustParam_id = " +id
					);
			if(result2.first()){
				DAO<TrafficModel>tmDAO =DAOFactory.getTrafficModelDAO();			
				result2.beforeFirst();
				while(result2.next() && result2.getLong("id") != 0)
					listTM.add(tmDAO.find(result2.getInt("id")));
			}
			
			ResultSet result3 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM customerparameter WHERE id = " +id
					);
			if(result3.first())
				custParam = new CustomerParameter(
				id,
				result3.getDouble("marPart"),
				result3.getDouble("penRate"),
				result3.getDouble("growthRate"),
				result3.getInt("invTime"),
				listSub,
				listTM
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return custParam;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<CustomerParameter> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
