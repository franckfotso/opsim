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

import cm.opsim.model.AttrTable;
import cm.opsim.model.Layer;
import cm.opsim.model.Map;
import cm.opsim.model.Project;

/**
 * @author Romuald FOTSO
 *
 */
public class LayerDAO extends DAO<Layer> {

	public LayerDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public Layer create(Layer obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM layer "+
					"WHERE Map_id = '"+obj.getMap_id()+"' AND type = '"+obj.getType()+"'"
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("Layer exist: start update ");
				obj.setId(id);
				obj = this.update(obj);
			}
			else
			{
				if(obj.getMap_id() == 0){
					throw new Exception();
				}
				if(obj.getAttrTable().getId() == 0){
					DAO<AttrTable>attrTabDAO =DAOFactory.getAttrTableDAO();
					obj.setAttrTable(attrTabDAO.create(obj.getAttrTable()));
				}
				
				ResultSet result1 = this.conn
						.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_UPDATABLE
						).executeQuery(
						"SELECT AUTO_INCREMENT "+
						"FROM information_schema.tables "+
						"WHERE table_name = 'layer' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					System.out.println("ID AttrTab = "+obj.getAttrTable().getId());
					
					PreparedStatement prepare1 = this.conn
						.prepareStatement(
						"INSERT INTO layer (Map_id, Att_id, name, type, description, dataSource, symbology, baseHeight, createdDate, updatedDate)"+
						"VALUES (?,?,?,?,?,?,?,?,NOW(),NOW())");
					prepare1.setInt(1, obj.getMap_id());
					prepare1.setInt(2, obj.getAttrTable().getId());
					prepare1.setString(3, obj.getName());
					prepare1.setString(4, obj.getType());
					prepare1.setString(5, obj.getDescription());
					prepare1.setString(6, obj.getDataSource());
					prepare1.setString(7, obj.getSymbology());
					prepare1.setDouble(8, obj.getBaseHeight());
					prepare1.executeUpdate();
					
					obj = this.find(id);
				}				
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			//System.out.println("Error(create): Map Obj is not defined");
			e.printStackTrace();
		}
		return obj;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Layer obj) {
		
		
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM layer WHERE id = " +obj.getId()
					);
			
			DAO<AttrTable>attrTabDAO =DAOFactory.getAttrTableDAO();
			attrTabDAO.delete(obj.getAttrTable());
		}
		catch(SQLException e) {
			e.printStackTrace();
		}		
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#update(java.lang.Object)
	 */
	@Override
	public Layer update(Layer obj) {
		try{
			if(obj.getMap_id() == 0){
				throw new Exception();
			}
			
			DAO<AttrTable>attrTabDAO =DAOFactory.getAttrTableDAO();
			if(obj.getAttrTable().getId() == 0){				
				obj.setAttrTable(attrTabDAO.create(obj.getAttrTable()));
			}
			attrTabDAO.update(obj.getAttrTable());
			
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE layer SET Map_id = ?, Att_id = ?, name = ?, type = ?, description = ?, dataSource = ?,"+
							" symbology = ?, baseHeight = ?, updatedDate = NOW()"+
							" WHERE id = " +obj.getId());
			prepare1.setInt(1, obj.getMap_id());
			prepare1.setInt(2, obj.getAttrTable().getId());
			prepare1.setString(3, obj.getName());
			prepare1.setString(4, obj.getType());
			prepare1.setString(5, obj.getDescription());
			prepare1.setString(6, obj.getDataSource());
			prepare1.setString(7, obj.getSymbology());
			prepare1.setDouble(8, obj.getBaseHeight());
			prepare1.executeUpdate();
			
			obj = this.find(obj.getId());
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Error(update): Map Obj is not defined");
		}
		return obj;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#find(int)
	 */
	@Override
	public Layer find(int id) {
		Layer layer = new Layer();
		
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM layer WHERE id = " +id
					);
			if(result1.first())
				layer = new Layer(
				id, 
				result1.getString("name"), 
				result1.getString("description"),
				result1.getString("dataSource"),
				result1.getString("symbology"),
				result1.getDouble("baseHeight"),
				result1.getDate("createdDate"),
				result1.getDate("updatedDate"),
				new AttrTableDAO(this.conn).find(result1.getInt("Att_id")),
				result1.getInt("Map_id"),
				result1.getString("type")
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
		return layer;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<Layer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
