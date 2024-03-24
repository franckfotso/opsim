/**
 * 
 */
package cm.opsim.view.cmds;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import cm.opsim.view.OpsimGUI;
import cm.opsim.view.SymbologyFrame;

import com.esri.arcgis.beans.TOC.TOCBean;
import com.esri.arcgis.carto.IGeoFeatureLayer;
import com.esri.arcgis.carto.IGeoFeatureLayerProxy;
import com.esri.arcgis.controls.BaseCommand;
import com.esri.arcgis.controls.HookHelper;
import com.esri.arcgis.controls.IMapControl3;
import com.esri.arcgis.controls.IToolbarControl;
import com.esri.arcgis.interop.AutomationException;

/**
 * @author Romuald FOTSO
 *
 */
public class ShowSymbologyCommand extends BaseCommand {
	private static final long serialVersionUID = 1L;
	private OpsimGUI opsimGUI;

	HookHelper hookHelper;

	IMapControl3 mapControl = null;
	TOCBean tocBean=null;
	
	public ShowSymbologyCommand(TOCBean tocBean, OpsimGUI opsimGUI) {
		caption = "Changer la symbologie";
		toolTip = "Ouvrir la configuration du rendu";
		enabled = true;
		this.tocBean=tocBean;
		this.opsimGUI = opsimGUI;				
	}

	public void onCreate(Object obj) {
		try {
			hookHelper = new HookHelper();
			hookHelper.setHookByRef(obj);
			IToolbarControl tb = (IToolbarControl) hookHelper.getHook();
			mapControl = (IMapControl3) tb.getBuddy();
		} catch (IOException ex) {
			ex.printStackTrace();

		}
	}

	public void onClick() {
		// TODO Auto-generated method stub
		super.onClick();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Object obj = mapControl.getCustomProperty();
					IGeoFeatureLayer layer = new IGeoFeatureLayerProxy(obj);
					//System.out.println("Layer name: "+layer.getName().toString());
					//opsimGUI.getBar().setIndeterminate(true);
					SymbologyFrame dialogSymb = new SymbologyFrame(layer, hookHelper.getActiveView(), tocBean, opsimGUI, true);
					dialogSymb.setSize(575, 307);
					dialogSymb.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
