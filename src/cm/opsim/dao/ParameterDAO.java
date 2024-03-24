/**
 * 
 */
package cm.opsim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cm.opsim.model.AntennaParameter;
import cm.opsim.model.CapParameter;
import cm.opsim.model.CapexParameter;
import cm.opsim.model.CustomerParameter;
import cm.opsim.model.EnbParameter;
import cm.opsim.model.EnvParameter;
import cm.opsim.model.FinanParameter;
import cm.opsim.model.FreqParameter;
import cm.opsim.model.GenParameter;
import cm.opsim.model.OpexParameter;
import cm.opsim.model.OtherParameter;
import cm.opsim.model.Parameter;
import cm.opsim.model.UeParameter;
import cm.opsim.model.ZoneParameter;

/**
 * @author Romuald FOTSO
 *
 */
public class ParameterDAO extends DAO<Parameter> {
	
	public ParameterDAO(Connection conn) {
		super(conn);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public Parameter create(Parameter obj) {
		try{
			ResultSet result0 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeQuery(
					"SELECT *"+
					"FROM parameter "+
					"WHERE Mcs_id = '"+obj.getMcsTechnique()+"' AND id = "+obj.getId()
					);
			if(result0.first()){
				int id =result0.getInt("id");				
				System.out.println("Parameter exist: start update ");
				obj.setId(id);
				obj = this.update(obj);
			}
			else
			{
//				if(obj.getMcsTechnique().getId() == 0){
//					DAO<McsTechnique>mcsDAO =DAOFactory.getMcsTechniqueDAO();
//					obj.setMcsTechnique(mcsDAO.create(obj.getMcsTechnique()));
//				}				
				
				ResultSet result1 = this.conn
						.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_UPDATABLE
						).executeQuery(
						"SELECT AUTO_INCREMENT "+
						"FROM information_schema.tables "+
						"WHERE table_name = 'parameter' "+
						"AND table_schema = 'opsim'"
						);
				if(result1.first()){
					int id =result1.getInt("AUTO_INCREMENT");
					
					PreparedStatement prepare1 = this.conn
						.prepareStatement(
						"INSERT INTO parameter (Mcs_id, createdDate, updatedDate)"+
						"VALUES (?,NOW(),NOW())");
					prepare1.setInt(1, obj.getMcsTechnique().getId());
					prepare1.executeUpdate();	
					
					obj.setId(id);
					DAO<GenParameter>genParamDAO =DAOFactory.getGenParameterDAO();
					DAO<EnbParameter>enbParamDAO =DAOFactory.getEnbParameterDAO();
					DAO<UeParameter>ueParamDAO =DAOFactory.getUeParameterDAO();
					DAO<EnvParameter>envParamDAO =DAOFactory.getEnvParameterDAO();
					DAO<ZoneParameter>zoneParamDAO =DAOFactory.getZoneParameterDAO();
					DAO<FinanParameter>finanParamDAO =DAOFactory.getFinanParameterDAO();
					DAO<OtherParameter>otherParamDAO =DAOFactory.getOtherParameterDAO();
					DAO<AntennaParameter>antParamDAO =DAOFactory.getAntennaParameterDAO();
					DAO<CustomerParameter>custParamDAO =DAOFactory.getCustomerParameterDAO();
					DAO<FreqParameter>freqParamDAO =DAOFactory.getFreqParameterDAO();
					DAO<CapParameter>capParamDAO =DAOFactory.getCapParameterDAO();
					DAO<CapexParameter>capexParamDAO =DAOFactory.getCapexParameterDAO();
					DAO<OpexParameter>opexParamDAO =DAOFactory.getOpexParameterDAO();
					
					obj.setGenParam(genParamDAO.create(obj.getGenParam()));
					obj.setEnbParam(enbParamDAO.create(obj.getEnbParam()));
					obj.setUeParam(ueParamDAO.create(obj.getUeParam()));
					obj.setEnvParam(envParamDAO.create(obj.getEnvParam()));
					obj.setZoneParam(zoneParamDAO.create(obj.getZoneParam()));
					obj.setFinanParam(finanParamDAO.create(obj.getFinanParam()));
					obj.setOtherParam(otherParamDAO.create(obj.getOtherParam()));
					obj.setAntParam(antParamDAO.create(obj.getAntParam()));
					obj.setCustParam(custParamDAO.create(obj.getCustParam()));
					obj.setFreqParam(freqParamDAO.create(obj.getFreqParam()));		
					obj.setCapParam(capParamDAO.create(obj.getCapParam()));
					obj.setCapexParam(capexParamDAO.create(obj.getCapexParam()));
					obj.setOpexParam(opexParamDAO.create(obj.getOpexParam()));
					
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
	public void delete(Parameter obj) {
		try {
					
			DAO<GenParameter>genParamDAO =DAOFactory.getGenParameterDAO();
			DAO<EnbParameter>enbParamDAO =DAOFactory.getEnbParameterDAO();
			DAO<UeParameter>ueParamDAO =DAOFactory.getUeParameterDAO();
			DAO<EnvParameter>envParamDAO =DAOFactory.getEnvParameterDAO();
			DAO<ZoneParameter>zoneParamDAO =DAOFactory.getZoneParameterDAO();
			DAO<FinanParameter>finanParamDAO =DAOFactory.getFinanParameterDAO();
			DAO<OtherParameter>otherParamDAO =DAOFactory.getOtherParameterDAO();
			DAO<AntennaParameter>antParamDAO =DAOFactory.getAntennaParameterDAO();
			DAO<CustomerParameter>custParamDAO =DAOFactory.getCustomerParameterDAO();
			DAO<FreqParameter>freqParamDAO =DAOFactory.getFreqParameterDAO();	
			DAO<CapParameter>capParamDAO =DAOFactory.getCapParameterDAO();
			DAO<CapexParameter>capexParamDAO =DAOFactory.getCapexParameterDAO();
			DAO<OpexParameter>opexParamDAO =DAOFactory.getOpexParameterDAO();
			
			genParamDAO.delete(obj.getGenParam());
			enbParamDAO.delete(obj.getEnbParam());
			ueParamDAO.delete(obj.getUeParam());
			envParamDAO.delete(obj.getEnvParam());
			zoneParamDAO.delete(obj.getZoneParam());
			finanParamDAO.delete(obj.getFinanParam());
			otherParamDAO.delete(obj.getOtherParam());
			antParamDAO.delete(obj.getAntParam());
			custParamDAO.delete(obj.getCustParam());
			freqParamDAO.delete(obj.getFreqParam());
			capParamDAO.delete(obj.getCapParam());
			capexParamDAO.delete(obj.getCapexParam());
			opexParamDAO.delete(obj.getOpexParam());
			
			this.conn
			.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					).executeUpdate(
					"DELETE FROM parameter WHERE id = " +obj.getId()
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
	public Parameter update(Parameter obj) {
		try{	
//			DAO<McsTechnique>mcsDAO =DAOFactory.getMcsTechniqueDAO();
//			if(obj.getMcsTechnique().getId() == 0){				
//				obj.setMcsTechnique(mcsDAO.create(obj.getMcsTechnique()));
//			}
//			mcsDAO.update(obj.getMcsTechnique());
			
			PreparedStatement prepare1 = this.conn
					.prepareStatement(
							"UPDATE parameter SET Mcs_id = ?, updatedDate = NOW()"+
							" WHERE id = " +obj.getId());
			prepare1.setInt(1, obj.getMcsTechnique().getId());
			prepare1.executeUpdate();
			
			DAO<GenParameter>genParamDAO =DAOFactory.getGenParameterDAO();
			DAO<EnbParameter>enbParamDAO =DAOFactory.getEnbParameterDAO();
			DAO<UeParameter>ueParamDAO =DAOFactory.getUeParameterDAO();
			DAO<EnvParameter>envParamDAO =DAOFactory.getEnvParameterDAO();
			DAO<ZoneParameter>zoneParamDAO =DAOFactory.getZoneParameterDAO();
			DAO<FinanParameter>finanParamDAO =DAOFactory.getFinanParameterDAO();
			DAO<OtherParameter>otherParamDAO =DAOFactory.getOtherParameterDAO();
			DAO<AntennaParameter>antParamDAO =DAOFactory.getAntennaParameterDAO();
			DAO<CustomerParameter>custParamDAO =DAOFactory.getCustomerParameterDAO();
			DAO<FreqParameter>freqParamDAO =DAOFactory.getFreqParameterDAO();		
			DAO<CapParameter>capParamDAO =DAOFactory.getCapParameterDAO();
			DAO<CapexParameter>capexParamDAO =DAOFactory.getCapexParameterDAO();
			DAO<OpexParameter>opexParamDAO =DAOFactory.getOpexParameterDAO();
			
			obj.setGenParam(genParamDAO.update(obj.getGenParam()));
			obj.setEnbParam(enbParamDAO.update(obj.getEnbParam()));
			obj.setUeParam(ueParamDAO.update(obj.getUeParam()));
			obj.setEnvParam(envParamDAO.update(obj.getEnvParam()));
			obj.setZoneParam(zoneParamDAO.update(obj.getZoneParam()));
			obj.setFinanParam(finanParamDAO.update(obj.getFinanParam()));
			obj.setOtherParam(otherParamDAO.update(obj.getOtherParam()));
			obj.setAntParam(antParamDAO.update(obj.getAntParam()));
			obj.setCustParam(custParamDAO.update(obj.getCustParam()));
			obj.setFreqParam(freqParamDAO.update(obj.getFreqParam()));	
			obj.setCapParam(capParamDAO.update(obj.getCapParam()));
			obj.setCapexParam(capexParamDAO.update(obj.getCapexParam()));
			obj.setOpexParam(opexParamDAO.update(obj.getOpexParam()));
			
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
	public Parameter find(int id) {
		Parameter param = new Parameter();
		
		try{
			ResultSet result1 = this.conn
					.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
					"SELECT * FROM parameter WHERE id = " +id
					);
			if(result1.first())
				param = new Parameter(
				id, 
				result1.getDate("createdDate"),
				result1.getDate("updatedDate"),
				new McsTechniqueDAO(this.conn).find(result1.getInt("Mcs_id")),				
				new GenParameterDAO(this.conn).find(id),
				new EnbParameterDAO(this.conn).find(id),
				new UeParameterDAO(this.conn).find(id),
				new EnvParameterDAO(this.conn).find(id),
				new ZoneParameterDAO(this.conn).find(id),
				new FinanParameterDAO(this.conn).find(id),
				new OtherParameterDAO(this.conn).find(id),
				new AntennaParameterDAO(this.conn).find(id),
				new CustomerParameterDAO(this.conn).find(id),
				new FreqParameterDAO(this.conn).find(id),
				new CapParameterDAO(this.conn).find(id),
				new CapexParameterDAO(this.conn).find(id),
				new OpexParameterDAO(this.conn).find(id)
				);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}		
		return param;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.dao.DAO#findAll()
	 */
	@Override
	public ArrayList<Parameter> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
