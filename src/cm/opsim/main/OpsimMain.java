/**
 * 
 */
package cm.opsim.main;

import java.util.ArrayList;
import java.util.Hashtable;

import cm.opsim.controller.AbstractController;
import cm.opsim.view.OpsimGUI;

import com.esri.arcgis.system.AoInitialize;
import com.esri.arcgis.system.EngineInitializer;
import com.esri.arcgis.system.esriLicenseExtensionCode;
import com.esri.arcgis.system.esriLicenseProductCode;
import com.esri.arcgis.system.esriLicenseStatus;

/**
 * @author Romuald FOTSO
 *
 */
public class OpsimMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		final Hashtable<String,AbstractController> listControler = new Hashtable<String,AbstractController>();
		
		//Step 1: Initialize the Java COM Interop.
		EngineInitializer.initializeVisualBeans();
		//Step 2: Initialize a valid License (License used: ArcGIS Advanced - ArcInfo)
		AoInitialize ao = new AoInitialize();
		if (ao.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeEngine) 
				== esriLicenseStatus.esriLicenseAvailable)
			ao.initialize(esriLicenseProductCode.esriLicenseProductCodeEngine);
		else if (ao.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeBasic) 
				== esriLicenseStatus.esriLicenseAvailable)
			ao.initialize(esriLicenseProductCode.esriLicenseProductCodeBasic);
		else if (ao.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeStandard) 
				== esriLicenseStatus.esriLicenseAvailable)
			ao.initialize(esriLicenseProductCode.esriLicenseProductCodeStandard);
		else if (ao.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeAdvanced) 
				== esriLicenseStatus.esriLicenseAvailable)
			ao.initialize(esriLicenseProductCode.esriLicenseProductCodeAdvanced);		
		ao.checkOutExtension(esriLicenseExtensionCode.esriLicenseExtensionCode3DAnalyst);
		
		javax.swing.SwingUtilities.invokeLater(
				new Runnable(){
					public void run(){
						new OpsimGUI(listControler);
					}
				}
		);
	}

}
