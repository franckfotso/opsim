/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.Map;
import cm.opsim.model.Project;
import cm.opsim.model.Simulation;
import cm.opsim.model.SystemLog;
import cm.opsim.model.Template;

/**
 * @author Romuald FOTSO
 *
 */
public class TemplateDAO extends DAO<Template>{

	/**
	 * 
	 */
	public TemplateDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public Template create(Template obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM template "+
					"WHERE generation = '"+obj.getGeneration()+
					"' AND family = '"+obj.getFamily()+
					"' AND network = '"+obj.getNetwork()+"'"
					);
			
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("Template exist: start update ");
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
						"WHERE table_name = 'template' "+
						"AND table_schema = 'opsim'"
						);
				
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
						.prepareStatement(
						"INSERT INTO template (name, network, generation, family, createdDate, updatedDate)" +
						"VALUES (?,?,?,?,NOW(),NOW())");
					prepare1.setString(1, obj.getName());
					prepare1.setString(2, obj.getNetwork());
					prepare1.setString(3, obj.getGeneration());
					prepare1.setString(4, obj.getFamily());
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
	public void delete(Template obj) {
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM template WHERE id = " +obj.getId()
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
	public Template update(Template obj) {
		try{
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE template SET name = ?, network = ?, generation = ?, updatedDate = NOW()"+
							" WHERE id = " +obj.getId());
				prepare1.setString(1, obj.getName());
				prepare1.setString(2, obj.getNetwork());
				prepare1.setString(3, obj.getGeneration());
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
	public Template find(int id) {
		Template tpl = new Template();;
		
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM template WHERE id = " +id
					);
			if(result1.first())
				tpl = new Template(
				id, 
				result1.getString("name"), 
				result1.getString("network"),
				result1.getString("generation"),
				result1.getDate("createdDate"),
				result1.getDate("updatedDate")
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}		
		return tpl;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<Template> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
