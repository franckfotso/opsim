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

import cm.opsim.model.FreqBand;
import cm.opsim.model.Project;

/**
 * @author Romuald FOTSO
 *
 */
public class FreqBandDAO extends DAO<FreqBand> implements IFreqBandDAO{
	
	public FreqBandDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public FreqBand create(FreqBand obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM freqband "+
					"WHERE name = '"+obj.getName()+"'"
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("FreqBand exist: start update ");
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
						"WHERE table_name = 'freqband' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
						.prepareStatement(
						"INSERT INTO freqband (	bandNum, name, ul, dl, allocation, bandWidth, duplexingMethod," +
						" createdDate, updatedDate)"+
						"VALUES (?,?,?,?,?,?,?,NOW(),NOW())");
					prepare1.setInt(1, obj.getBandNum());
					prepare1.setString(2, obj.getName());
					prepare1.setString(3, obj.getUl());
					prepare1.setString(4, obj.getDl());
					prepare1.setString(5, obj.getAllocation());
					prepare1.setInt(6, obj.getBandWidth());
					prepare1.setString(7, obj.getDuplexingMethod());
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
	public void delete(FreqBand obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM freqband WHERE id = " +obj.getId()
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
	public FreqBand update(FreqBand obj) {
		try{
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE freqband SET bandNum = ?, name = ?, ul = ?, dl = ?, allocation = ?,"+
							" bandWidth = ?, duplexingMethod = ?, updatedDate = NOW()"+
							" WHERE id = " +obj.getId());
			prepare1.setInt(1, obj.getBandNum());
			prepare1.setString(2, obj.getName());
			prepare1.setString(3, obj.getUl());
			prepare1.setString(4, obj.getDl());
			prepare1.setString(5, obj.getAllocation());
			prepare1.setInt(6, obj.getBandWidth());
			prepare1.setString(7, obj.getDuplexingMethod());
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
	public FreqBand find(int id) {
		FreqBand fb = new FreqBand();
		
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM freqband WHERE id = " +id
					);
			if(result1.first())
				fb = new FreqBand(
				id, 
				result1.getInt("bandNum"), 
				result1.getString("name"),
				result1.getString("ul"),
				result1.getString("dl"),
				result1.getString("allocation"),
				result1.getInt("bandWidth"),
				result1.getString("duplexingMethod"),
				result1.getDate("createdDate"),
				result1.getDate("updatedDate")
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}		
		return fb;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<FreqBand> findAll() {
		ArrayList<FreqBand> listFB = new ArrayList<FreqBand>();
		
		try{
			ResultSet result1 = this.conn
						.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_READ_ONLY
						).executeQuery("SELECT * FROM freqband ORDER BY updatedDate DESC");
			
			if(result1.first()){
				DAO<FreqBand>fbDAO =DAOFactory.getFreqBandDAO();
				result1.beforeFirst();
				while(result1.next() && result1.getLong("id") != 0)
					listFB.add(fbDAO.find(result1.getInt("id")));				
				result1.first();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return listFB;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.IFreqBandDAO#find(java.lang.String)
	 */
	@Override
	public FreqBand find(String name) {
		FreqBand freqBand = null;
		try{
			ResultSet result1 = this.conn
				.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, 
				ResultSet.CONCUR_READ_ONLY
				).executeQuery(
						"SELECT *"+
						" FROM freqband "+
						" WHERE name = '"+name+"'"
				);		
			if(result1.first()){
				System.out.println("FreqBand finded ");
				freqBand = find(result1.getInt("id"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}		
		return freqBand;
	}
}
