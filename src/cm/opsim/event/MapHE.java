/**
 * 
 */
package cm.opsim.event;

import java.io.IOException;
import java.net.UnknownHostException;

import cm.opsim.view.OpsimGUI;

import com.esri.arcgis.carto.Map;
import com.esri.arcgis.carto.esriViewDrawPhase;
import com.esri.arcgis.controls.IMapControlEvents2Adapter;
import com.esri.arcgis.controls.IMapControlEvents2OnAfterDrawEvent;
import com.esri.arcgis.controls.IMapControlEvents2OnMapReplacedEvent;
import com.esri.arcgis.display.DisplayTransformation;
import com.esri.arcgis.display.ITransformEventsAdapter;
import com.esri.arcgis.display.ITransformEventsVisibleBoundsUpdatedEvent;
import com.esri.arcgis.display.RgbColor;
import com.esri.arcgis.display.SimpleFillSymbol;
import com.esri.arcgis.display.SimpleLineSymbol;
import com.esri.arcgis.geometry.IEnvelope;
import com.esri.arcgis.interop.AutomationException;


/**
 * @author Romuald FOTSO
 *
 */
public class MapHE extends AbstractHandleEvent{

	SimpleFillSymbol fillSymbol; // The symbol used to draw the envelope.
    IEnvelope currentExtent; // The envelope drawn on the MapBean.
    Map focusMap; // The PageLayoutBean's focus map.
	
	/**
	 * 
	 */
	public MapHE(OpsimGUI gui) {
		super(gui);
		this.handleMapEvent();
		this.createOverviewSymbol();
	}
	
	public void handleMapEvent(){		
		try {
			this.opsimGUI.getOpsimMap().addIMapControlEvents2Listener(
					new IMapControlEvents2Adapter(){
						public void onMapReplaced
			            (IMapControlEvents2OnMapReplacedEvent evt)throws IOException{
						opsimGUI.getS_opsimMap().loadMxFile(opsimGUI.getOpsimMap().getDocumentFilename(), null, null);
						opsimGUI.getS_opsimMap().setExtent(opsimGUI.getS_opsimMap().getFullExtent()); 
			            focusMap = new Map(opsimGUI.getOpsimMap().getActiveView().getFocusMap());
			            currentExtent = focusMap.getExtent(); 
			            DisplayTransformation dt = new DisplayTransformation
			                    (focusMap.getScreenDisplay().getDisplayTransformation());
			                    dt.addITransformEventsListener(new ITransformEventsAdapter(){
			                        public void visibleBoundsUpdated
			                            (ITransformEventsVisibleBoundsUpdatedEvent evt)throws IOException{
			                            // Set currentExtent to the new visible extent.
			                            currentExtent = evt.getSender().getVisibleBounds(); 
			                            // Refresh the map components foreground phase.
			                            opsimGUI.getS_opsimMap().refresh(esriViewDrawPhase.esriViewForeground, null, null); 
			                        }
			                    });
						}
					});
			
			opsimGUI.getS_opsimMap().addIMapControlEvents2Listener(new IMapControlEvents2Adapter(){
	            public void onAfterDraw(IMapControlEvents2OnAfterDrawEvent evt)throws
	                IOException{
	                if (evt.getViewDrawPhase() == esriViewDrawPhase.esriViewForeground){
	                    try{
	                        // Draw the shape on the MapBean.
	                    	opsimGUI.getS_opsimMap().drawShape(currentExtent, fillSymbol); 
	                    }
	                    catch (Exception e){
	                        System.err.println("Error in drawing shape on small opsimMap"); 
	                    }
	                }
	            }
	        });
			
		} catch (IOException e) {
			System.out.println("Error: "+e+" caught on Handle map event");
		}
	}
	
	private void createOverviewSymbol(){
        try {
			RgbColor color = new RgbColor();
			color.setRed(255);
			color.setGreen(0);
			color.setBlue(0);
			color.setTransparency((byte)255);
			SimpleLineSymbol outline = new SimpleLineSymbol();
			outline.setColor(color);
			fillSymbol = new SimpleFillSymbol();
			color.setTransparency((byte)0);
			fillSymbol.setColor(color);
			fillSymbol.setOutline(outline);
		} catch (IOException e) {
			System.out.println("Error: "+e+" caught on createOverviewSymbol");
		}
    }

}
