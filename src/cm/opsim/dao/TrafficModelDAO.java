/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.AntennaType;
import cm.opsim.model.TrafficModel;

/**
 * @author Romuald FOTSO
 *
 */
public class TrafficModelDAO extends DAO<TrafficModel> {

	public TrafficModelDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public TrafficModel create(TrafficModel obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM trafficmodel "+
					"WHERE CustParam_id = "+obj.getCustParam_id()+
					" AND class_serv ='"+obj.getClass_serv()+"'"
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("trafficmodel exist: start update ");
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
						"WHERE table_name = 'trafficmodel' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO trafficmodel (CustParam_id, class_serv, bw_ul, bw_dl, poh, tp)"+
							"VALUES (?,?,?,?,?,?)");
						prepare1.setInt(1, obj.getCustParam_id());
						prepare1.setString(2, obj.getClass_serv());
						prepare1.setDouble(3, obj.getBw_ul());
						prepare1.setDouble(4, obj.getBw_dl());
						prepare1.setDouble(5, obj.getPoh());
						prepare1.setDouble(6, obj.getTp());
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
	public void delete(TrafficModel obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM trafficmodel WHERE id = " +obj.getId()
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
	public TrafficModel update(TrafficModel obj) {
		try{
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE trafficmodel SET CustParam_id = ?, class_serv  = ?, bw_ul = ?, bw_dl = ?, poh = ?, tp = ?"+
							" WHERE id = " +obj.getId());
			prepare1.setInt(1, obj.getCustParam_id());
			prepare1.setString(2, obj.getClass_serv());
			prepare1.setDouble(3, obj.getBw_ul());
			prepare1.setDouble(4, obj.getBw_dl());
			prepare1.setDouble(5, obj.getPoh());
			prepare1.setDouble(6, obj.getTp());
			prepare1.executeUpdate();
			
			obj = this.find(obj.getId());
		}
		catch(SQLException e) {
			e.printStackTrace();
		}// TODO Auto-generated method stub
		return obj;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#find(int)
	 */
	@Override
	public TrafficModel find(int id) {
		TrafficModel tm = new TrafficModel();
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM trafficmodel WHERE id = " +id
					);
			if(result1.first())
				tm = new TrafficModel(
				id, 
				result1.getInt("CustParam_id"),
				result1.getString("class_serv"),
				result1.getDouble("bw_ul"),
				result1.getDouble("bw_dl"),
				result1.getDouble("poh"),
				result1.getDouble("tp")
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return tm;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<TrafficModel> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
