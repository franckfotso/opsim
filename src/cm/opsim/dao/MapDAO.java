/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import cm.opsim.model.Layer;
import cm.opsim.model.Map;

/**
 * @author Romuald FOTSO
 *
 */
public class MapDAO extends DAO<Map> {

	public MapDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public Map create(Map obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM map "+
					"WHERE Pro_id = '"+obj.getProj_id()+"' AND name = '"+obj.getName()+"'"
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("Map exist: start update ");
				obj.setId(id);
				obj = this.update(obj);
			}
			else
			{
				if(obj.getProj_id() == 0){
					throw new Exception();
				}
				
				ResultSet result1 = this.conn
						.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_UPDATABLE
						).executeQuery(
						"SELECT AUTO_INCREMENT "+
						"FROM information_schema.tables "+
						"WHERE table_name = 'map' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
						.prepareStatement(
						"INSERT INTO map (Pro_id, name, numLayer, localization, mapset, createdDate, updatedDate)"+
						"VALUES (?,?,?,?,?,NOW(),NOW())");
					prepare1.setInt(1, obj.getProj_id());
					prepare1.setString(2, obj.getName());
					prepare1.setInt(3, obj.getNumLayer());
					prepare1.setString(4, obj.getLocalization());
					prepare1.setString(5, obj.getMapset());
					prepare1.executeUpdate();
					
					obj.setId(id);
					Enumeration<Layer> em = obj.getLayer().elements();
					while(em.hasMoreElements()){
						Layer layer = em.nextElement();						
						if(layer.getId() == 0){
							DAO<Layer>layerDAO =DAOFactory.getLayerDAO();
							layer.setMap_id(id);
							layer = layerDAO.create(layer);
						}					
					}
					obj = this.find(id);
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}		
		catch(Exception e) {
			System.out.println("Error(update): Project Obj is not defined");
		}
		return obj;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Map obj) {
		try {
//			Enumeration<Layer> em = obj.getLayer().elements();			
//			while(em.hasMoreElements()){
//				Layer layer = em.nextElement();	
//				System.out.println("Name lay: "+layer.getName());
//				DAO<Layer>layerDAO =DAOFactory.getLayerDAO();
//				layerDAO.delete(layer);		
//				em = obj.getLayer().elements();
//			}
			
//			Hashtable<String, Layer> list_layer = obj.getLayer();
//			int n = list_layer.size();
//			System.out.println("N. lay: "+n);
//			for(int i=0; i<n; i++){
//				Layer layer = list_layer.get(i);
//				System.out.println("Name lay: "+layer.getName());
//				DAO<Layer>layerDAO =DAOFactory.getLayerDAO();
//				layerDAO.delete(layer);	
//			}		
			
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM layer WHERE Map_id = "+obj.getId()
					);			
			if(result1.first()){
				DAO<Layer>layerDAO =DAOFactory.getLayerDAO();				
				result1.beforeFirst();
				
				while(result1.next() && result1.getInt("id") != 0){
					Layer layer = layerDAO.find(result1.getInt("id"));
					layerDAO.delete(layer);	
				}
			}
			
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM map WHERE id = " +obj.getId()
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
	public Map update(Map obj) {
		try{
			if(obj.getProj_id() == 0){
				throw new Exception();
			}
			
			Enumeration<Layer> em = obj.getLayer().elements();
			DAO<Layer>layerDAO =DAOFactory.getLayerDAO();	
			System.out.println("ID Map obj: "+obj.getId());
			while(em.hasMoreElements()){
				Layer layer = em.nextElement();				
				if(layer.getId() == 0){
					layer.setMap_id(obj.getId());
					layer = layerDAO.create(layer);
				}					
				else layerDAO.update(layer);
			}
			
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE map SET Pro_id = ?, name = ?, numLayer = ?, localization = ?, mapset = ?, updatedDate = NOW()"+
							" WHERE id = " +obj.getId());
			prepare1.setInt(1, obj.getProj_id());
			prepare1.setString(2, obj.getName());
			prepare1.setInt(3, obj.getNumLayer());
			prepare1.setString(4, obj.getLocalization());
			prepare1.setString(5, obj.getMapset());
			prepare1.executeUpdate();
			
			obj = this.find(obj.getId());
		}
		catch(SQLException e) {
			e.printStackTrace();
		}		
		catch(Exception e) {
			System.out.println("Error(create): Project Obj is not defined");
		}
		return obj;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#find(int)
	 */
	@Override
	public Map find(int id) {
		Map map = new Map();
		Hashtable<String,Layer> listLayer = new Hashtable<String,Layer>();
		
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM layer WHERE Map_id = "+id
					);
			if(result1.first()){
				//Enumeration<Layer> em = obj.getLayer().elements();
				DAO<Layer>layerDAO =DAOFactory.getLayerDAO();				
				result1.beforeFirst();

				while(result1.next() && result1.getInt("id") != 0){
					Layer layer = layerDAO.find(result1.getInt("id"));
					listLayer.put(layer.getType(),layer);
				}
			}
						
			ResultSet result2 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM map WHERE id = " +id
					);
			if(result2.first())
				map = new Map(
				id, 
				result2.getString("name"), 
				result2.getInt("numLayer"),
				result2.getString("localization"),
				result2.getString("mapset"),
				result2.getDate("createdDate"),
				result2.getDate("updatedDate"),
				listLayer,
				result2.getInt("Pro_id")
				);			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}		
		return map;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<Map> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
