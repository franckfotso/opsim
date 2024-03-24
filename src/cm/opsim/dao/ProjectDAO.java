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
import java.util.Date;

import cm.opsim.model.Configuration;
import cm.opsim.model.GlobalDesignNet;
import cm.opsim.model.Map;
import cm.opsim.model.Parameter;
import cm.opsim.model.Planification;
import cm.opsim.model.ProjExterne;
import cm.opsim.model.Project;
import cm.opsim.model.Simulation;
import cm.opsim.model.SystemLog;
import cm.opsim.model.Template;
import cm.opsim.model.User;

/**
 * @author Romuald FOTSO
 *
 */
public class ProjectDAO extends DAO<Project> implements IProjectDAO{

	/**
	 * 
	 */
	public ProjectDAO(Connection conn){
		super(conn);
	}

	@Override
	public Project create(Project obj) {		
		try{
			// check if obj exist
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					" FROM project "+
					" WHERE name = '"+obj.getName()+"'"
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("Project exist: start update ");
				obj.setId(id);
				obj = this.update(obj);
			}
			else
			{
//				if(obj.getUser().getId() == 0){
//					DAO<User>userDAO =DAOFactory.getUserDAO();
//					obj.setUser(userDAO.create(obj.getUser()));
//				}
				if(obj.getTemplate().getId() == 0){
					DAO<Template>templateDAO =DAOFactory.getTemplateDAO();
					obj.setTemplate(templateDAO.create(obj.getTemplate()));
				}
				if(obj.getGlobalDesignNet().getId() == 0){
					DAO<GlobalDesignNet>globalDesignNetDAO =DAOFactory.getGlobalDesignNetDAO();
					obj.setGlobalDesignNet(globalDesignNetDAO.create(obj.getGlobalDesignNet()));
				}
//				if(obj.getFreqBand().getId() == 0){
//					DAO<FreqBand>freqBandDAO =DAOFactory.getFreqBandDAO();
//					obj.setFreqBand(freqBandDAO.create(obj.getFreqBand()));
//				}
				if(obj.getParameter().getId() == 0){
					DAO<Parameter>parameterDAO =DAOFactory.getParameterDAO();
					obj.setParameter(parameterDAO.create(obj.getParameter()));
				}
				if(obj.getConfiguration().getId() == 0){
					DAO<Configuration>configurationDAO =DAOFactory.getConfigurationDAO();
					obj.setConfiguration(configurationDAO.create(obj.getConfiguration()));
				}
				if(obj.getPlanification().getId() == 0){
					DAO<Planification>planificationDAO =DAOFactory.getPlanificationDAO();
					obj.setPlanification(planificationDAO.create(obj.getPlanification()));
				}
				if(obj.getProjExterne() != null){
					DAO<ProjExterne>projExterneDAO =DAOFactory.getProjExterneDAO();
					obj.setProjExterne(projExterneDAO.create(obj.getProjExterne()));
				}
				
				ResultSet result1 = this.conn
						.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_UPDATABLE
						).executeQuery(
						"SELECT AUTO_INCREMENT "+
						"FROM information_schema.tables "+
						"WHERE table_name = 'project' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
						.prepareStatement(
						"INSERT INTO project (Fre_id, Tem_id, Par_id, Pla_id, Glo_id, Con_id, Use_id," +
						" name, progress, isOver, createdDate, updatedDate)"+
						" VALUES (?,?,?,?,?,?,?,?,?,?,NOW(),NOW())");
					prepare1.setInt(1, obj.getFreqBand().getId());
					prepare1.setInt(2, obj.getTemplate().getId());
					prepare1.setInt(3, obj.getParameter().getId());
					prepare1.setInt(4, obj.getPlanification().getId());
					prepare1.setInt(5, obj.getGlobalDesignNet().getId());
					prepare1.setInt(6, obj.getConfiguration().getId());
					prepare1.setInt(7, obj.getUser().getId());
					prepare1.setString(8, obj.getName());
					prepare1.setDouble(9, obj.getProgress());
					prepare1.setBoolean(10, obj.getIsOver());
					prepare1.executeUpdate();
					
					obj.setId(id);
					for(Map map : obj.getMap()){						
						if(map.getId() == 0){
							DAO<Map>mapDAO =DAOFactory.getMapDAO();
							map.setProj_id(id);
							map = mapDAO.create(map);
						}					
					}
					for(SystemLog slog : obj.getSystemLog()){
						if(slog.getId() == 0){
							DAO<SystemLog>slogDAO =DAOFactory.getSystemLogDAO();
							slog.setProj_id(id);
							slog = slogDAO.create(slog);
						}					
					}
					for(Simulation simu : obj.getSimulation()){
						if(simu.getId() == 0){
							DAO<Simulation>simuDAO =DAOFactory.getSimulationDAO();
							simu.setProj_id(id);
							simu = simuDAO.create(simu);
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

	@Override
	public void delete(Project obj) {
		
		for(SystemLog slog : obj.getSystemLog()){
			DAO<SystemLog>slogDAO =DAOFactory.getSystemLogDAO();
			slogDAO.delete(slog);				
		}
		for(Simulation simu : obj.getSimulation()){
			DAO<Simulation>simuDAO =DAOFactory.getSimulationDAO();
			simuDAO.delete(simu);				
		}
		for(Map map : obj.getMap()){
			DAO<Map>mapDAO =DAOFactory.getMapDAO();
			mapDAO.delete(map);				
		}
		
		try {
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM project WHERE id = " +obj.getId()
					);		
		
		
//			DAO<Template>templateDAO =DAOFactory.getTemplateDAO();
//			templateDAO.delete(obj.getTemplate());
		
		DAO<GlobalDesignNet>globalDesignNetDAO =DAOFactory.getGlobalDesignNetDAO();
		globalDesignNetDAO.delete(obj.getGlobalDesignNet());
		
		DAO<Parameter>parameterDAO =DAOFactory.getParameterDAO();
		parameterDAO.delete(obj.getParameter());
		
		DAO<Configuration>configurationDAO =DAOFactory.getConfigurationDAO();
		configurationDAO.delete(obj.getConfiguration());
		
		DAO<Planification>planificationDAO =DAOFactory.getPlanificationDAO();
		planificationDAO.delete(obj.getPlanification());
		
		if(obj.getProjExterne() != null){
			DAO<ProjExterne>projExterneDAO =DAOFactory.getProjExterneDAO();
			projExterneDAO.delete(obj.getProjExterne());
		}
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Project update(Project obj) {
		try{
			/*
			 *  update of 1..1 objects
			 */
			DAO<User>userDAO =DAOFactory.getUserDAO();
			if(obj.getUser().getId() == 0){				
				obj.setUser(userDAO.create(obj.getUser()));
			}
			userDAO.update(obj.getUser());
			
			DAO<Template>templateDAO =DAOFactory.getTemplateDAO();
			if(obj.getTemplate().getId() == 0){				
				obj.setTemplate(templateDAO.create(obj.getTemplate()));
			}
			templateDAO.update(obj.getTemplate());
			
			DAO<GlobalDesignNet>globalDesignNetDAO =DAOFactory.getGlobalDesignNetDAO();			
			if(obj.getGlobalDesignNet().getId() == 0){				
				obj.setGlobalDesignNet(globalDesignNetDAO.create(obj.getGlobalDesignNet()));
			}
			globalDesignNetDAO.update(obj.getGlobalDesignNet());
			
			// no add, no update cause it's just a select operation
//			DAO<FreqBand>freqBandDAO =DAOFactory.getFreqBandDAO();
//			if(obj.getFreqBand().getId() == 0){				
//				obj.setFreqBand(freqBandDAO.create(obj.getFreqBand()));
//			}
//			freqBandDAO.update(obj.getFreqBand());
			
			DAO<Parameter>parameterDAO =DAOFactory.getParameterDAO();
			if(obj.getParameter().getId() == 0){				
				obj.setParameter(parameterDAO.create(obj.getParameter()));
			}
			parameterDAO.update(obj.getParameter());
			
			DAO<Configuration>configurationDAO =DAOFactory.getConfigurationDAO();
			if(obj.getConfiguration().getId() == 0){				
				obj.setConfiguration(configurationDAO.create(obj.getConfiguration()));
			}
			configurationDAO.update(obj.getConfiguration());
			
			DAO<Planification>planificationDAO =DAOFactory.getPlanificationDAO();
			if(obj.getPlanification().getId() == 0){				
				obj.setPlanification(planificationDAO.create(obj.getPlanification()));
			}
			planificationDAO.update(obj.getPlanification());
			
			DAO<ProjExterne>projExterneDAO =DAOFactory.getProjExterneDAO();
			if(obj.getProjExterne() != null){
				projExterneDAO.update(obj.getProjExterne());
			}			
			
			/*
			 * update of 1..n objects 
			 */
			DAO<Map>mapDAO =DAOFactory.getMapDAO();
			DAO<SystemLog>slogDAO =DAOFactory.getSystemLogDAO();
			DAO<Simulation>simuDAO =DAOFactory.getSimulationDAO();
			
			System.out.println("Num Map: "+obj.getMap().size());
			for(Map map : obj.getMap()){
				if(map.getId() == 0){
					map.setProj_id(obj.getId());
					map = mapDAO.create(map);
				}					
				else mapDAO.update(map);
			}
			for(SystemLog slog : obj.getSystemLog()){				
				if(slog.getId() == 0){
					slog.setProj_id(obj.getId());
					slog = slogDAO.create(slog);
				}
				else
					slogDAO.update(slog);
			}
			for(Simulation simu : obj.getSimulation()){				
				if(simu.getId() == 0){
					simu.setProj_id(obj.getId());
					simu = simuDAO.create(simu);
				}
				else
					simuDAO.update(simu);
			}

			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE project SET Fre_id = ?, Tem_id = ?, Par_id = ?, Pla_id = ?, Glo_id = ?,"+
							" Con_id = ?, Use_id = ?, name = ?, progress = ?, isOver = ?, updatedDate = NOW()"+
							" WHERE id = " +obj.getId());
				prepare1.setInt(1, obj.getFreqBand().getId());
				prepare1.setInt(2, obj.getTemplate().getId());
				prepare1.setInt(3, obj.getParameter().getId());
				prepare1.setInt(4, obj.getPlanification().getId());
				prepare1.setInt(5, obj.getGlobalDesignNet().getId());
				prepare1.setInt(6, obj.getConfiguration().getId());
				prepare1.setInt(7, obj.getUser().getId());
				prepare1.setString(8, obj.getName());
				prepare1.setDouble(9, obj.getProgress());
				prepare1.setBoolean(10, obj.getIsOver());
				prepare1.executeUpdate();
			
			obj = this.find(obj.getId());
			
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return obj;
	}

	@Override
	public Project find(int id) {
		Project proj = new Project();
		Collection<Map> listMap = new ArrayList<Map>();
		Collection<SystemLog> listSysLog = new ArrayList<SystemLog>();
		Collection<Simulation> listSimu = new ArrayList<Simulation>();
		
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM map WHERE Pro_id = " +id
					);
			if(result1.first()){
				DAO<Map>mapDAO =DAOFactory.getMapDAO();				
				result1.beforeFirst();
				while(result1.next() && result1.getLong("id") != 0)
					listMap.add(mapDAO.find(result1.getInt("id")));
			}
			
			ResultSet result2 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM simulation WHERE Pro_id = " +id
					);
			if(result2.first()){
				DAO<Simulation>simuDAO =DAOFactory.getSimulationDAO();			
				result2.beforeFirst();
				while(result2.next() && result2.getLong("id") != 0)
					listSimu.add(simuDAO.find(result2.getInt("id")));
			}
			
			ResultSet result3 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM systemlog WHERE Pro_id = " +id
					);
			if(result3.first()){
				DAO<SystemLog>slogDAO =DAOFactory.getSystemLogDAO();			
				result3.beforeFirst();
				while(result3.next() && result3.getLong("id") != 0)
					listSysLog.add(slogDAO.find(result3.getInt("id")));	
			}
			
			ResultSet result4 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM project WHERE id = " +id
					);		
			
			if(result4.first())
				proj = new Project(
				id, 
				result4.getString("name"), 
				result4.getDouble("progress"),
				result4.getBoolean("isOver"),
				(Date)result4.getTimestamp("createdDate"),
				(Date)result4.getTimestamp("updatedDate"),
				new UserDAO(this.conn).find(result4.getInt("Use_id")),
				new TemplateDAO(this.conn).find(result4.getInt("Tem_id")),
				new GlobalDesignNetDAO(this.conn).find(result4.getInt("Glo_id")),
				new FreqBandDAO(this.conn).find(result4.getInt("Fre_id")),
				listMap,
				listSysLog,
				new ParameterDAO(this.conn).find(result4.getInt("Par_id")),
				new ConfigurationDAO(this.conn).find(result4.getInt("Con_id")),
				new PlanificationDAO(this.conn).find(result4.getInt("Pla_id")),
				listSimu
				);
			
			ResultSet result5 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM projexterne WHERE id = " +id
					);
			if(result5.first())proj.setProjExterne(new ProjExterneDAO(this.conn).find(id));
			
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return proj;		
	}
	
	public ArrayList<Project> findAll(){
		ArrayList<Project> listProj = new ArrayList<Project>();
	
		try{
			ResultSet result1 = this.conn
						.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_READ_ONLY
						).executeQuery("SELECT * FROM project ORDER BY updatedDate DESC");
			
			if(result1.first()){
				DAO<Project>projDAO =DAOFactory.getProjectDAO();
				result1.beforeFirst();
				while(result1.next() && result1.getLong("id") != 0)
					listProj.add(projDAO.find(result1.getInt("id")));				
				result1.first();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return listProj;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.IProjectDAO#find(java.lang.String)
	 */
	@Override
	public Project find(String name) {
		Project proj = null;
		try{
			ResultSet result1 = this.conn
				.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, 
				ResultSet.CONCUR_READ_ONLY
				).executeQuery(
						"SELECT *"+
						" FROM project "+
						" WHERE name = '"+name+"'"
				);		
			if(result1.first()){
				System.out.println("Project finded ");
				proj = find(result1.getInt("id"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}		
		return proj;
	}
}
