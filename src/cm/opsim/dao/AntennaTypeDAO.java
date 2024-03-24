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
import cm.opsim.model.FreqBand;
import cm.opsim.model.GenParameter;

/**
 * @author Romuald FOTSO
 *
 */
public class AntennaTypeDAO extends DAO<AntennaType> implements IAntennaTypeDAO{

	public AntennaTypeDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public AntennaType create(AntennaType obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM antennatype "+
					"WHERE id = "+obj.getId()
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("antennatype exist: start update ");
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
						"WHERE table_name = 'antennatype' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO antennatype (name, description)"+
							"VALUES (?,?)");
						prepare1.setString(1, obj.getName());
						prepare1.setString(2, obj.getDescription());
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
	public void delete(AntennaType obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM antennatype WHERE id = " +obj.getId()
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
	public AntennaType update(AntennaType obj) {
		try{
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE antennatype SET name = ?, description = ?"+
							" WHERE id = " +obj.getId());
			prepare1.setString(1, obj.getName());
			prepare1.setString(2, obj.getDescription());
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
	public AntennaType find(int id) {
		AntennaType antType = new AntennaType();
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM antennatype WHERE id = " +id
					);
			if(result1.first())
				antType = new AntennaType(
				id, 
				result1.getString("name"),
				result1.getString("description")
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return antType;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<AntennaType> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.IAntennaTypeDAO#find(java.lang.String)
	 */
	@Override
	public AntennaType find(String name) {
		AntennaType antenType = null;
		try{
			ResultSet result1 = this.conn
				.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, 
				ResultSet.CONCUR_READ_ONLY
				).executeQuery(
						"SELECT *"+
						" FROM antennatype "+
						" WHERE name = '"+name+"'"
				);		
			if(result1.first()){
				System.out.println("AntennaType finded ");
				antenType = find(result1.getInt("id"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}		
		return antenType;
	}
}
