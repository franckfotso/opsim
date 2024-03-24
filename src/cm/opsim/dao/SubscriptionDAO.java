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
import cm.opsim.model.Service;
import cm.opsim.model.Subscription;

/**
 * @author Romuald FOTSO
 *
 */
public class SubscriptionDAO extends DAO<Subscription> {

	/**
	 * @param conn
	 */
	public SubscriptionDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public Subscription create(Subscription obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM subscription "+
					"WHERE CustParam_id = "+obj.getCustParam_id()+
					" AND name ='"+obj.getName()+"'"
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("subscription exist: start update ");
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
						"WHERE table_name = 'subscription' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO subscription (CustParam_id, name, description, pc_sub)"+
							"VALUES (?,?,?,?)");
					prepare1.setInt(1, obj.getCustParam_id());
					prepare1.setString(2, obj.getName());
					prepare1.setString(3, obj.getDescription());
					prepare1.setDouble(4, obj.getPc_sub());
					prepare1.executeUpdate();
					
					obj.setId(id);
					for(Service serv : obj.getListServ()){						
						if(serv.getId() == 0){
							DAO<Service>servDAO =DAOFactory.getServiceDAO();
							//serv.setSub_id(id);
							serv = servDAO.create(serv);
						}
						ResultSet result2 = this.conn
								.createStatement(
								ResultSet.TYPE_SCROLL_INSENSITIVE, 
								ResultSet.CONCUR_UPDATABLE
								).executeQuery(
								"SELECT AUTO_INCREMENT "+
								"FROM information_schema.tables "+
								"WHERE table_name = 'sub_serv' "+
								"AND table_schema = 'opsim'"
								);
						if(result2.first()){
							int j_id = result2.getInt("AUTO_INCREMENT");
							
							PreparedStatement prepare2 = this.conn
									.prepareStatement(
									"INSERT INTO sub_serv (id, sub_id, serv_id)"+
									"VALUES (?,?,?)");
							prepare2.setInt(1, j_id);
							prepare2.setInt(2, id);
							prepare2.setInt(3, serv.getId());
							prepare2.executeUpdate();
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
	public void delete(Subscription obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM sub_serv WHERE sub_id = " +obj.getId()
					);	
			
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM subscription WHERE id = " +obj.getId()
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
	public Subscription update(Subscription obj) {
		try{
			DAO<Service>servDAO =DAOFactory.getServiceDAO();
			
			for(Service serv : obj.getListServ()){
				if(serv.getId() == 0){
					//serv.setSub_id(obj.getId());
					serv = servDAO.create(serv);
					
					ResultSet result2 = this.conn
							.createStatement(
							ResultSet.TYPE_SCROLL_INSENSITIVE, 
							ResultSet.CONCUR_UPDATABLE
							).executeQuery(
							"SELECT AUTO_INCREMENT "+
							"FROM information_schema.tables "+
							"WHERE table_name = 'sub_serv' "+
							"AND table_schema = 'opsim'"
							);
					if(result2.first()){
						int j_id = result2.getInt("AUTO_INCREMENT");
						
						PreparedStatement prepare2 = this.conn
								.prepareStatement(
								"INSERT INTO sub_serv (id, sub_id, serv_id)"+
								"VALUES (?,?,?)");
						prepare2.setInt(1, j_id);
						prepare2.setInt(2, obj.getId());
						prepare2.setInt(3, serv.getId());
						prepare2.executeUpdate();
					}					
				}					
				else servDAO.update(serv);
			}
			
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE subscription SET CustParam_id = ?, name = ?, description = ?, pc_sub = ?"+
							" WHERE id = " +obj.getId());
			prepare1.setInt(1, obj.getCustParam_id());
			prepare1.setString(2, obj.getName());
			prepare1.setString(3, obj.getDescription());
			prepare1.setDouble(4, obj.getPc_sub());
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
	public Subscription find(int id) {
		Subscription sub = new Subscription();
		ArrayList<Service> listServ = new ArrayList<Service>();
		
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM subscription "+
					" LEFT JOIN sub_serv ON sub_id = subscription.id AND subscription.id = "+id+
					" INNER JOIN service ON serv_id = service.id"
					);
			if(result1.first()){
				DAO<Service>servDAO =DAOFactory.getServiceDAO();			
				result1.beforeFirst();
				while(result1.next() && result1.getLong("serv_id") != 0)
					listServ.add(servDAO.find(result1.getInt("serv_id")));
				
				result1.first();
				sub = new Subscription(
						id,
						result1.getInt("CustParam_id"),
						result1.getString("name"),
						result1.getString("description"),
						result1.getDouble("pc_sub"),
						listServ						
						);
			}				
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return sub;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<Subscription> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
