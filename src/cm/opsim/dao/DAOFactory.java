package cm.opsim.dao;

import java.sql.Connection;

import cm.opsim.connection.OpsimConnection;
import cm.opsim.model.*;

public class DAOFactory {

	protected static final Connection conn = OpsimConnection.getInstance();
	
	public static DAO<Project> getProjectDAO(){
		return new ProjectDAO(conn);
	}
	
	public static DAO<User> getUserDAO(){
		return new UserDAO(conn);
	}
	
	public static DAO<Template> getTemplateDAO(){
		return new TemplateDAO(conn);
	}
	
	public static DAO<GlobalDesignNet> getGlobalDesignNetDAO(){
		return new GlobalDesignNetDAO(conn);
	}
	
	public static DAO<FreqBand> getFreqBandDAO(){
		return new FreqBandDAO(conn);
	}
	
	public static DAO<Parameter> getParameterDAO(){
		return new ParameterDAO(conn);
	}
	
	public static DAO<Configuration> getConfigurationDAO(){
		return new ConfigurationDAO(conn);
	}
	
	public static DAO<Planification> getPlanificationDAO(){
		return new PlanificationDAO(conn);
	}
	
	public static DAO<SystemLog> getSystemLogDAO(){
		return new SystemLogDAO(conn);
	}
	
	public static DAO<Map> getMapDAO(){
		return new MapDAO(conn);
	}
	
	public static DAO<Simulation> getSimulationDAO(){
		return new SimulationDAO(conn);
	}
	
	public static DAO<OptionSystem> getOptionSystemDAO(){
		return new OptionSystemDAO(conn);
	}
	
	public static DAO<McsTechnique> getMcsTechniqueDAO(){
		return new McsTechniqueDAO(conn);
	}
	
	public static DAO<DriveTestData> getDriveTestDataDAO(){
		return new DriveTestDataDAO(conn);
	}
	
	public static DAO<Result> getResultDAO(){
		return new ResultDAO(conn);
	}
	
	public static DAO<Layer> getLayerDAO(){
		return new LayerDAO(conn);
	}
	
	public static DAO<ModulationType> getModulationTypeDAO(){
		return new ModulationTypeDAO(conn);
	}
	
	public static DAO<AttrTable> getAttrTableDAO(){
		return new AttrTableDAO(conn);
	}
	
	public static DAO<Profil> getProfilDAO(){
		return new ProfilDAO(conn);
	}
	
	public static DAO<ProjInterne> getProjInterneDAO(){
		return new ProjInterneDAO(conn);
	}
	
	public static DAO<ProjExterne> getProjExterneDAO(){
		return new ProjExterneDAO(conn);
	}
	
	public static DAO<CoveragePlanif> getCoveragePlanifDAO(){
		return new CoveragePlanifDAO(conn);
	}
	
	public static DAO<CapacityPlanif> getCapacityPlanifDAO(){
		return new CapacityPlanifDAO(conn);
	}
	
	public static DAO<FrequencyPlanif> getFrequencyPlanifDAO(){
		return new FrequencyPlanifDAO(conn);
	}
	
	public static DAO<CapexOpexPlanif> getCapexOpexPlanifDAO(){
		return new CapexOpexPlanifDAO(conn);
	}
	
	public static DAO<GenParameter> getGenParameterDAO(){
		return new GenParameterDAO(conn);
	}
	
	public static DAO<EnbParameter> getEnbParameterDAO(){
		return new EnbParameterDAO(conn);
	}
	
	public static DAO<UeParameter> getUeParameterDAO(){
		return new UeParameterDAO(conn);
	}
	
	public static DAO<EnvParameter> getEnvParameterDAO(){
		return new EnvParameterDAO(conn);
	}
	
	public static DAO<ZoneParameter> getZoneParameterDAO(){
		return new ZoneParameterDAO(conn);
	}
	
	public static DAO<FinanParameter> getFinanParameterDAO(){
		return new FinanParameterDAO(conn);
	}
	
	public static DAO<OtherParameter> getOtherParameterDAO(){
		return new OtherParameterDAO(conn);
	}
	
	public static DAO<AntennaParameter> getAntennaParameterDAO(){
		return new AntennaParameterDAO(conn);
	}
	
	public static DAO<CustomerParameter> getCustomerParameterDAO(){
		return new CustomerParameterDAO(conn);
	}
	
	public static DAO<FreqParameter> getFreqParameterDAO(){
		return new FreqParameterDAO(conn);
	}
	
	public static DAO<PmParameter> getPmParameterDAO(){
		return new PmParameterDAO(conn);
	}
	
	public static DAO<OkumuraHata> getOkumuraHataDAO(){
		return new OkumuraHataDAO(conn);
	}
	
	public static DAO<Cost231Hata> getCost231HataDAO(){
		return new Cost231HataDAO(conn);
	}
	
	public static DAO<TargetZone> getTargetZoneDAO(){
		return new TargetZoneDAO(conn);
	}
	
	public static DAO<AntennaType> getAntennaTypeDAO(){
		return new AntennaTypeDAO(conn);
	}
	
	public static DAO<TrafficModel> getTrafficModelDAO(){
		return new TrafficModelDAO(conn);
	}
	
	public static DAO<RPlanCapacity> getRPlanCapacityDAO(){
		return new RPlanCapacityDAO(conn);
	}
	
	public static DAO<RPlanCoverage> getRPlanCoverageDAO(){
		return new RPlanCoverageDAO(conn);
	}
	
	public static DAO<RPlanFrequency> getRPlanFrequencyDAO(){
		return new RPlanFrequencyDAO(conn);
	}
	
	public static DAO<RDimensionning> getRDimensionningDAO(){
		return new RDimensionningDAO(conn);
	}
	
	public static DAO<BtsConfig> getBtsConfigDAO(){
		return new BtsConfigDAO(conn);
	}
	
	public static DAO<EnbConfig> getEnbConfigDAO(){
		return new EnbConfigDAO(conn);
	}
	
	public static DAO<BackboneConfig> getBackboneConfigDAO(){
		return new BackboneConfigDAO(conn);
	}
	
	public static DAO<CapParameter> getCapParameterDAO(){
		return new CapParameterDAO(conn);
	}
	
	public static DAO<Subscription> getSubscriptionDAO(){
		return new SubscriptionDAO(conn);
	}
	
	public static DAO<Service> getServiceDAO(){
		return new ServiceDAO(conn);
	}
	
	public static DAO<CapexParameter> getCapexParameterDAO(){
		return new CapexParameterDAO(conn);
	}
	
	public static DAO<OpexParameter> getOpexParameterDAO(){
		return new OpexParameterDAO(conn);
	}
	
	public static DAO<PrevIncomings> getPrevIncomingsDAO(){
		return new PrevIncomingsDAO(conn);
	}
	
	public static DAO<RCapex> getRCapexDAO(){
		return new RCapexDAO(conn);
	}
	
	public static DAO<ROpex> getROpexDAO(){
		return new ROpexDAO(conn);
	}
}
