/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.Cost231Hata;
import cm.opsim.model.OkumuraHata;
import cm.opsim.model.PmParameter;
import cm.opsim.model.PropaModel;

/**
 * @author Romuald FOTSO
 *
 */
public class PmParameterDAO extends DAO<PmParameter> {

	public PmParameterDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public PmParameter create(PmParameter obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM pmparameter "+
					"WHERE id = "+obj.getId()
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("pmparameter exist: start update ");
				obj.setId(id);
				obj = this.update(obj);
			}
			else
			{
				String namePm = obj.getName();
				switch(namePm){
					case "okumuraHata":
						OkumuraHata oku = (OkumuraHata) obj.getPm();
						if(oku != null && oku.getId() == 0){							
							DAO<OkumuraHata>okuDAO =DAOFactory.getOkumuraHataDAO();
							obj.setPm(okuDAO.create(oku));
						}
						break;
					case "cost231Hata":
						Cost231Hata c231 = (Cost231Hata) obj.getPm();
						if(c231 != null && c231.getId() == 0){							
							DAO<Cost231Hata>c231DAO =DAOFactory.getCost231HataDAO();
							obj.setPm(c231DAO.create(c231));
						}
						break;
					case "cost231WI":
						break;
					case "kfactors":
						break;
					default:
						break;
				}
				
				ResultSet result1 = this.conn
						.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_UPDATABLE
						).executeQuery(
						"SELECT AUTO_INCREMENT "+
						"FROM information_schema.tables "+
						"WHERE table_name = 'pmparameter' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
							.prepareStatement(
							"INSERT INTO pmparameter (name, Pm_id,createdDate, updatedDate)"+
							"VALUES (?,?,NOW(),NOW())");
					prepare1.setString(1, obj.getName());
					prepare1.setInt(2, obj.getPm().getId());
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
	public void delete(PmParameter obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM pmparameter WHERE id = " +obj.getId()
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
	public PmParameter update(PmParameter obj) {
		try{
			String namePm = obj.getName();
			switch(namePm){
				case "okumuraHata":
					OkumuraHata oku = (OkumuraHata) obj.getPm();
					DAO<OkumuraHata>okuDAO =DAOFactory.getOkumuraHataDAO();
					if(oku != null){
						if(oku.getId() == 0){
							obj.setPm(okuDAO.create(oku));
						}
						obj.setPm(okuDAO.update(oku));
					}
					
					break;
				case "cost231Hata":
					Cost231Hata c231 = (Cost231Hata) obj.getPm();
					DAO<Cost231Hata>c231DAO =DAOFactory.getCost231HataDAO();
					if(c231 != null){
						if(c231.getId() == 0){
							obj.setPm(c231DAO.create(c231));
						}
						obj.setPm(c231DAO.update(c231));
					}
					
					break;
				case "cost231WI":
					break;
				case "kfactors":
					break;
				default:
					break;
			}
			
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE pmparameter SET name = ?, updatedDate = NOW()"+
							" WHERE id = " +obj.getId());
			prepare1.setString(1, obj.getName());
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
	public PmParameter find(int id) {
		PmParameter pmParam = new PmParameter();
		
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM pmparameter WHERE id = " +id
					);
			if(result1.first()){
				String namePm = result1.getString("name");
				PropaModel pm = null;
				switch(namePm){
					case "okumuraHata":
						pm = new OkumuraHataDAO(this.conn).find(result1.getInt("Pm_id"));
						break;
					case "cost231Hata":
						pm = new Cost231HataDAO(this.conn).find(result1.getInt("Pm_id"));
						break;
					case "cost231WI":
						break;
					case "kfactors":
						break;
					default:
						break;
				}
				
				pmParam = new PmParameter(
				id, 
				result1.getString("name"),
				result1.getDate("createdDate"),
				result1.getDate("updatedDate"),
				pm
				);
			}
				
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return pmParam;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<PmParameter> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
