/**
 * 
 */
package cm.opsim.geoprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.ArrayList;

import cm.opsim.view.DialogTasksGP;

import com.esri.arcgis.carto.FeatureLayer;
import com.esri.arcgis.carto.IFeatureLayer;
import com.esri.arcgis.carto.ISimpleRenderer;
import com.esri.arcgis.carto.SimpleRenderer;
import com.esri.arcgis.datasourcesGDB.FileGDBWorkspaceFactory;
import com.esri.arcgis.datasourcesfile.ShapefileWorkspaceFactory;
import com.esri.arcgis.display.IEnumStyleGalleryItem;
import com.esri.arcgis.display.IStyleGallery;
import com.esri.arcgis.display.IStyleGalleryItem;
import com.esri.arcgis.display.IStyleGalleryStorage;
import com.esri.arcgis.display.ISymbol;
import com.esri.arcgis.display.ServerStyleGallery;
import com.esri.arcgis.geodatabase.Feature;
import com.esri.arcgis.geodatabase.IFeature;
import com.esri.arcgis.geodatabase.IFeatureClass;
import com.esri.arcgis.geodatabase.IFeatureCursor;
import com.esri.arcgis.geodatabase.IGPMessages;
import com.esri.arcgis.geodatabase.IWorkspaceFactory;
import com.esri.arcgis.geodatabase.Workspace;
import com.esri.arcgis.geometry.GeometryEnvironment;
import com.esri.arcgis.geometry.IExtrude;
import com.esri.arcgis.geometry.IGeometry;
import com.esri.arcgis.geoprocessing.GeoProcessor;
import com.esri.arcgis.system.AoInitialize;
import com.esri.arcgis.system.Cleaner;
import com.esri.arcgis.system.EngineInitializer;
import com.esri.arcgis.system.IPropertySet;
import com.esri.arcgis.system.PropertySet;
import com.esri.arcgis.system.VarArray;
import com.esri.arcgis.system.esriLicenseExtensionCode;
import com.esri.arcgis.system.esriLicenseProductCode;
import com.esri.arcgis.system.esriLicenseStatus;

/**
 * @author Romuald FOTSO
 *
 */
public class OCCToolRunner {

	private String ws_main = null;
	private String ws_out = null;
	private String ws_param = null;
	private String toolName = null;
	private ArrayList<String> parameters = new ArrayList<String>();
	private DialogTasksGP dialogGP;
	private Process p;
	private static GeoProcessor GP = null;
	
	public OCCToolRunner(){
		
	}
	
	public OCCToolRunner(String toolName, ArrayList<String> parameters, DialogTasksGP dialogGP) {
		
		// initialize the GPUtilities object, Geoprocessor and all names
		try
		{
			this.toolName = toolName;
			this.parameters = parameters;
			this.dialogGP = dialogGP;
			this.ws_main = this.getOutputDir("ws_main");
			this.ws_out = this.getOutputDir("ws_out");
			this.ws_param = this.getOutputDir("ws_param");
			
			this.createFolder(ws_main);
			this.createFolder(ws_out);
			
			this.emptyFolder(this.ws_main);
			this.emptyFolder(this.ws_out);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public IFeatureClass buildMultiPathFC(String str_fc_bld, String folder_out){
		IFeatureClass fc_mp_dhm = null;
		GeoProcessor GP = null;
		
		try{
			GP = new GeoProcessor();
			GP.setOverwriteOutput(true);
			GP.resetEnvironments();
			GP.clearMessages();
			
			String opsim_gdb = folder_out+File.separator+"opsim.gdb";
			
			ShapefileWorkspaceFactory sf_wsf = new ShapefileWorkspaceFactory();
			Workspace sf_ws = (Workspace) sf_wsf.openFromFile(folder_out, 0);	
			sf_ws.startEditing(false);
			sf_ws.startEditOperation();
			
			IFeatureClass fc_building = sf_ws.openFeatureClass(str_fc_bld);
			
			sf_ws.stopEditOperation();
			sf_ws.stopEditing(true);
			sf_ws = null;	
			
			IPropertySet propertySet = new PropertySet();
		    propertySet.setProperty("DATABASE", opsim_gdb);
		    IWorkspaceFactory workspaceFactory = new FileGDBWorkspaceFactory();
			Workspace  gdb_ws = (Workspace) workspaceFactory.open(propertySet, 0);
			gdb_ws.startEditing(false);
			gdb_ws.startEditOperation();
			
			fc_mp_dhm = gdb_ws.openFeatureClass("fc_mp_dhm");
			
			gdb_ws.stopEditOperation();
			gdb_ws.stopEditing(true);
			Cleaner.release(gdb_ws);
			
			System.out.println("Extrud MultiPatch FC fc_mp_dhm: Start");
			IExtrude geoEnv = new GeometryEnvironment();
			IFeatureCursor cur_bld = fc_building.search(null, false);
			Feature f_bld = (Feature) cur_bld.nextFeature();
			while(f_bld != null){			
				double surf_H = Double.parseDouble(f_bld.getValue(fc_building.findField("SURFACE_H")).toString());
				IGeometry geoExtrud = geoEnv.extrude(surf_H, f_bld.getShape());
				
				IFeature f_mp_dhm = fc_mp_dhm.createFeature();
				f_mp_dhm.setShapeByRef(geoExtrud);
				f_mp_dhm.store();
				Cleaner.release(f_bld);
				Cleaner.release(f_mp_dhm);
				Cleaner.release(geoExtrud);
				f_bld = (Feature) cur_bld.nextFeature();
			}
			Cleaner.release(f_bld);
			Cleaner.release(cur_bld);
			Cleaner.release(fc_building);
			
			Cleaner.release(fc_mp_dhm);
			System.out.println("Extrud MultiPatch FC fc_mp_dhm: Stop");
		}
		catch (Exception e){
			try{
				IGPMessages gpMessages = GP.getReturnMessages();
				for (int i = 0; i < gpMessages.getCount(); i++)
					System.out.println(i+". "+gpMessages.getMessage(i).getDescription());
			}
			catch (Exception e2){}
			e.printStackTrace();
		}
		
		return fc_mp_dhm;
	}
	
	public void runTool(){
		
		String opsim_home = System.getenv("OPSIM_HOME");
		String scriptTool = opsim_home;
		
		String outputFile = "";
		
		switch(this.toolName){
			case "OccOneSite":			
				scriptTool = scriptTool+File.separator+"scriptsTool/occ_onesite.py";
				outputFile = "OpsimCalCovOutput_tx_"+this.parameters.get(6)+".shp";
				this.parameters.add(this.ws_out+File.separator+outputFile);
				this.parameters.add(this.ws_out);
				this.parameters.add(this.ws_param);
				break;
			case "OccMultiSite":
				scriptTool = scriptTool+File.separator+"scriptsTool/occ_multisite.py";
				break;
			case "OccInterference":
				scriptTool = scriptTool+File.separator+"scriptsTool/occ_interfMulti.py";
				break;
			case "OccLinkProfile":
				scriptTool = scriptTool+File.separator+"scriptsTool/occ_linkprofile.py";
				break;
			case "OccSitePosition":
				scriptTool = scriptTool+File.separator+"scriptsTool/occ_siteposition.py";
				break;
			case "OccCal3DModel_basic":
				scriptTool = scriptTool+File.separator+"scriptsTool/occ_3dModel.py";
				break;
			default:
				break;
		}
		
		try {
			System.out.println("Running Script Tool Python: Start");
			String listArgs = "";
			int n = this.parameters.size();
			for(int i=0; i<n; i++){
				listArgs +="\""+this.parameters.get(i)+"\" ";
			}				
			
			scriptTool = "\""+scriptTool+"\"";
			System.out.println("scriptTool: "+scriptTool);
			System.out.println("listArgs: "+listArgs);
			
			this.p = Runtime.getRuntime().exec("python "+scriptTool+" "+listArgs);
			BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = "";
			while((line = bfr.readLine()) != null) {
				System.out.println(line);
				this.dialogGP.getTa_output().append(line+"\n");
				this.dialogGP.getTa_output().setCaretPosition(
						this.dialogGP.getTa_output().getDocument().getLength());
			}
			if(this.toolName == "OccCal3DModel_basic"){
				String msg_out = "Extrud MultiPatch FC fc_mp_dhm: Start";
				this.dialogGP.getTa_output().append(msg_out+"\n");
				this.dialogGP.getTa_output().setCaretPosition(
						this.dialogGP.getTa_output().getDocument().getLength());
				
				IFeatureClass fc_mp_dhm = this.buildMultiPathFC("fc_building.shp", this.parameters.get(4));
				
				msg_out = "Extrud MultiPatch FC fc_mp_dhm: Done";
				this.dialogGP.getTa_output().append(msg_out+"\n");
				this.dialogGP.getTa_output().setCaretPosition(
						this.dialogGP.getTa_output().getDocument().getLength());
			}
			System.out.println("Running Script Tool Python: Done");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stopTool(){
		this.p.destroy();
	}
	
	/**
	 * Returns output path
	 * 
	 * @return String
	 * @throws Exception
	 */
	public static String getOutputDir(String folder) {
		String userDir;
		
		if(System.getProperty("os.name").toLowerCase().indexOf("win") > -1){
			userDir = System.getenv("UserProfile");
		}else{
			userDir = System.getenv("HOME");
		}			
		String outputDir = userDir+File.separator+".OpsimPro"+File.separator+folder;		
		System.out.println("getOutputDir - " + outputDir);		
		//outputDir = "c:/OpsimOutput"+File.separator + folder;
		
		new File(outputDir).mkdir();		
		return outputDir;
	}
	
	/**
	 * Creates a new directory
	 * 
	 * @param pathName
	 */
	public static void createFolder(String pathName)
	{
		File f = new File(pathName);
		if (!f.exists())
		{
			f.mkdir();
		}
	}
	
	
	/**
	 * @param dirName
	 */
	public static void emptyFolder(String dirName)
	{
		File src = new File(dirName);
		if (src.isDirectory() && src.exists())
		{
			File list[] = src.listFiles();
			for (int i = 0; i < list.length; i++)
			{
				if (list[i].isDirectory())
				{
					emptyFolder(list[i].getPath());
				}
				else
				{
					list[i].delete();
				}
			}
			//src.delete();
		}
		else
		{
			src.delete();
		}
	}
	
	/**
	 * Empties specified directory of all files
	 * 
	 * @param dirName
	 *            String
	 */
	public static void deleteFolder(String dirName)
	{
		File src = new File(dirName);
		if (src.isDirectory() && src.exists())
		{
			File list[] = src.listFiles();
			for (int i = 0; i < list.length; i++)
			{
				if (list[i].isDirectory())
				{
					emptyFolder(list[i].getPath());
					list[i].delete();
				}
				else
				{
					list[i].delete();
				}
			}
			src.delete();
		}
		else
		{
			src.delete();
		}
	}
	
	void initializeArcGISLicenses() {
		try {
			com.esri.arcgis.system.AoInitialize ao = new com.esri.arcgis.system.AoInitialize();
			if (ao.isProductCodeAvailable(com.esri.arcgis.system.esriLicenseProductCode.esriLicenseProductCodeAdvanced) == com.esri.arcgis.system.esriLicenseStatus.esriLicenseAvailable)
				ao.initialize(com.esri.arcgis.system.esriLicenseProductCode.esriLicenseProductCodeAdvanced);
			ao.checkOutExtension(com.esri.arcgis.system.esriLicenseExtensionCode.esriLicenseExtensionCode3DAnalyst);
			ao.checkOutExtension(com.esri.arcgis.system.esriLicenseExtensionCode.esriLicenseExtensionCodeTracking);
			ao.checkOutExtension(com.esri.arcgis.system.esriLicenseExtensionCode.esriLicenseExtensionCodeNetwork);
			ao.checkOutExtension(com.esri.arcgis.system.esriLicenseExtensionCode.esriLicenseExtensionCodeSpatialAnalyst);
			ao.checkOutExtension(com.esri.arcgis.system.esriLicenseExtensionCode.esriLicenseExtensionCodeDataInteroperability);
			ao.checkOutExtension(com.esri.arcgis.system.esriLicenseExtensionCode.esriLicenseExtensionCodeMLE);
			ao.checkOutExtension(com.esri.arcgis.system.esriLicenseExtensionCode.esriLicenseExtensionCodeSchematics);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initializes the lowest available ArcGIS License
	 */
	private static void initializeArcGISLicenses(AoInitialize aoInit)
	{
		try
		{
			AoInitialize ao = new AoInitialize();
			if (ao.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeAdvanced) 
					== esriLicenseStatus.esriLicenseAvailable)
				ao.initialize(esriLicenseProductCode.esriLicenseProductCodeAdvanced);	
			else if (ao.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeStandard) 
					== esriLicenseStatus.esriLicenseAvailable)
				ao.initialize(esriLicenseProductCode.esriLicenseProductCodeStandard);
			else if (ao.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeBasic) 
					== esriLicenseStatus.esriLicenseAvailable)
				ao.initialize(esriLicenseProductCode.esriLicenseProductCodeBasic);
			else if (ao.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeEngine) 
					== esriLicenseStatus.esriLicenseAvailable)
				ao.initialize(esriLicenseProductCode.esriLicenseProductCodeEngine);		
			
			ao.checkOutExtension(esriLicenseExtensionCode.esriLicenseExtensionCode3DAnalyst);
			ao.checkOutExtension(esriLicenseExtensionCode.esriLicenseExtensionCodeArcScan);
			ao.checkOutExtension(esriLicenseExtensionCode.esriLicenseExtensionCodeGeoStats);
			ao.checkOutExtension(esriLicenseExtensionCode.esriLicenseExtensionCodeNetwork);
			ao.checkOutExtension(esriLicenseExtensionCode.esriLicenseExtensionCodePublisher);
			ao.checkOutExtension(esriLicenseExtensionCode.esriLicenseExtensionCodeSchematics);
			ao.checkOutExtension(esriLicenseExtensionCode.esriLicenseExtensionCodeSpatialAnalyst);
			ao.checkOutExtension(esriLicenseExtensionCode.esriLicenseExtensionCodeTracking);			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
