/**
 * 
 */
package cm.opsim.view;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import com.esri.arcgis.beans.TOC.TOCBean;
import com.esri.arcgis.carto.ClassBreaksRenderer;
import com.esri.arcgis.carto.IActiveView;
import com.esri.arcgis.carto.IGeoFeatureLayer;
import com.esri.arcgis.carto.esriViewDrawPhase;
import com.esri.arcgis.controls.ISymbologyControlEventsAdapter;
import com.esri.arcgis.controls.ISymbologyControlEventsOnDoubleClickEvent;
import com.esri.arcgis.controls.ISymbologyControlEventsOnItemSelectedEvent;
import com.esri.arcgis.controls.SymbologyControl;
import com.esri.arcgis.controls.esriSymbologyStyleClass;
import com.esri.arcgis.display.IColorRamp;
import com.esri.arcgis.display.IEnumColors;
import com.esri.arcgis.display.ISimpleMarkerSymbol;
import com.esri.arcgis.display.IStyleGalleryItem;
import com.esri.arcgis.display.ISymbol;
import com.esri.arcgis.display.SimpleFillSymbol;
import com.esri.arcgis.display.SimpleMarkerSymbol;
import com.esri.arcgis.geodatabase.DataStatistics;
import com.esri.arcgis.geodatabase.ICursor;
import com.esri.arcgis.geodatabase.ICursorProxy;
import com.esri.arcgis.geodatabase.IFeatureClass;
import com.esri.arcgis.geodatabase.IField;
import com.esri.arcgis.geodatabase.IFields;
import com.esri.arcgis.geodatabase.esriFieldType;
import com.esri.arcgis.geometry.esriGeometryType;
import com.esri.arcgis.interop.AutomationException;
import com.esri.arcgis.system.IStatisticsResults;

/**
 * @author Romuald FOTSO
 *
 */
public class SymbologyFrame extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private SymbologyControl symbologyControl = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	private JLabel jLabel1 = null;
	private JComboBox jComboBox = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JTextField jTextField1 = null;
	private JTextField jTextField3 = null;
	private JTextField jTextField4 = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JButton but_default = null;
	IFeatureClass featureClass = null;
	IGeoFeatureLayer layer = null;
	IStyleGalleryItem item = null;
	IActiveView activeView = null;
	TOCBean tocBean = null;
	
	private OpsimGUI opsimGUI;
	private Image icone =  Toolkit.getDefaultToolkit().getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/logo.png"));
	private Hashtable<String,ArrayList<Integer>> breakVal = new Hashtable<String,ArrayList<Integer>>();
	private JDialog progressDialog;

	/**
	 * This is the default constructor
	 */
	public SymbologyFrame(IGeoFeatureLayer l, IActiveView view, TOCBean tocBean, OpsimGUI opsimGUI, boolean modal)
	{
		super(opsimGUI, modal);
		initialize();
		layer = l;
		addListener();
		initComponents();
		activeView = view;
		this.tocBean = tocBean;
		this.opsimGUI = opsimGUI;
	}

	private void initComponents()
	{
		try
		{
			//Get the ArcGIS Engine runtime, if it is available
			String runtimeHome = System.getenv("AGSENGINEJAVA");
			
			if(runtimeHome == null){
				runtimeHome = System.getenv("AGSDESKTOPJAVA");
			}
			
			//If no runtime is available, exit application gracefully
			if(runtimeHome == null){
				if(System.getProperty("os.name").toLowerCase().indexOf("win") > -1){
					System.err.println("You must have ArcGIS Engine Runtime or ArcGIS Desktop " + 
							"installed in order to execute this sample.");
					System.err.println("Install one of the products above, then re-run this sample.");
	    			System.err.println("Exiting execution of this sample...");
	    			System.exit(0);
				}else{
					System.err.println("You must have ArcGIS Engine Runtime installed " + 
							"in order to execute this sample.");
					System.err.println("Install the product above, then re-run this sample.");
					System.err.println("Exiting execution of this sample...");
					System.exit(0);	
				}
			}
			
			//Obtain relative path to the ESRI.ServerStyle file
			String esriStylePath = runtimeHome + "Styles" + File.separator + "ESRI.ServerStyle";
//			String opsim_home = System.getenv("OPSIM_HOME");
//			String opsim_styles = opsim_home;
//			opsim_styles = opsim_styles+File.separator+"styles";	
//			String esriStylePath = opsim_styles+File.separator+"ESRI.ServerStyle";
			System.out.println("esriStylePath: "+esriStylePath);

			File styleFile = new File(esriStylePath);

	        //Test to make sure style file is presente
			if(!styleFile.exists()){
	        	System.err.println("The ESRI.ServerStyle was not found in the following location: " +
	                                                styleFile.getParent());
	            System.err.println("Verify that ESRI.ServerStyle can be located in the specified folder.");
	            System.err.println("If not present, try uninstalling your ArcGIS software and reinstalling it.");
	            System.err.println("Exiting execution of this sample...");
	            System.exit(0);
	        }
			
			try{
				// Load the ESRI.ServerStyle file into the SymbologyControl
				symbologyControl.loadStyleFile(esriStylePath);
			}
			catch(AutomationException e){
				
			}			
			
			// Set the style class
			symbologyControl.setStyleClass(esriSymbologyStyleClass.esriStyleClassColorRamps);

			// Setup the ComboBoxes:
			jComboBox.removeAllItems();
			featureClass = layer.getFeatureClass();
			IFields fields = featureClass.getFields();
			for (int i = 0; i < fields.getFieldCount(); i++)
			{
				if ((fields.getField(i).getType() == esriFieldType.esriFieldTypeDouble)
						|| (fields.getField(i).getType() == esriFieldType.esriFieldTypeInteger)
						|| (fields.getField(i).getType() == esriFieldType.esriFieldTypeSingle)
						|| (fields.getField(i).getType() == esriFieldType.esriFieldTypeSmallInteger))
				{
					jComboBox.addItem(fields.getField(i).getName());
				}
			}
			if (jComboBox.getItemCount() == 0)
			{
				JOptionPane.showMessageDialog(null, "THIS FEATURE LAYER DOES NOT CONTAIN NUMERIC DATA");
				this.dispose();
				return;
			}
			else
			{
				jComboBox.setSelectedIndex(0);
//				(new Thread(new Runnable(){
//					public void run(){
//						setupText();
//					}
//				})).start();
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		catch (Error err)
		{
			err.printStackTrace();
		}

	}

	private void setupText()
	{
		try
		{
			//opsimGUI.getBar().setIndeterminate(true);
			// Find the selected field in the feature layer
			this.progressDialog = getProgressDialog("Chargement des valeurs...");
			progressDialog.setVisible(true);
			progressDialog.requestFocus();
			progressDialog.revalidate();
			IField field = featureClass.getFields().getField(
					featureClass.findField(jComboBox.getSelectedItem().toString()));
			// Get a feature cursor
			ICursor cursor = new ICursorProxy(layer.search(null, false));

			// Create a DataStatistics object and initialize properties
			DataStatistics dataStatistics = new DataStatistics();
			dataStatistics.setField(field.getName());
			dataStatistics.setCursorByRef(cursor);
			
			IStatisticsResults statisticsResults = dataStatistics.getStatistics();
			jTextField3.setText(Double.toString(statisticsResults.getMinimum()));
			jTextField4.setText(Double.toString(statisticsResults.getMaximum()));
			jTextField1.setText("10");
			//opsimGUI.getBar().setIndeterminate(false);
			progressDialog.setVisible(false);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setIconImage(icone); 
		this.setSize(580, 307);
		this.setContentPane(getJContentPane());
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Choisir la symbologie pour la couche");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane()
	{
		if (jContentPane == null)
		{
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getSymbologyControl(), null);
			jContentPane.add(getJPanel1(), null);
			jContentPane.add(getJPanel2(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(getJButton2(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes symbologyControl
	 * 
	 * @return com.esri.arcgis.controls.SymbologyControl
	 */
	private SymbologyControl getSymbologyControl()
	{
		if (symbologyControl == null)
		{
			try
			{
				symbologyControl = new SymbologyControl();
				symbologyControl.setBounds(new java.awt.Rectangle(4, 3, 302, 265));
			}
			catch (java.lang.Throwable e)
			{
				System.out.println("Catch Throwable");
				// TODO: Something
			}
		}
		return symbologyControl;
	}

	/**
	 * This method initializes jPanel1
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel1()
	{
		if (jPanel1 == null)
		{
			try
			{
				jLabel1 = new JLabel();
				jLabel1.setText("Selectionner l'attribut:");
				jLabel1.setBounds(new java.awt.Rectangle(10, 25, 151, 20));
				jPanel1 = new JPanel();
				jPanel1.setLayout(null);
				jPanel1.setBounds(new java.awt.Rectangle(316, 6, 243, 86));
				jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Composants de la couche",
						javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
				jPanel1.add(jLabel1, null);
				jPanel1.add(getJComboBox(), null);
			}
			catch (java.lang.Throwable e)
			{
				// TODO: Something
			}
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPanel2
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel2()
	{
		if (jPanel2 == null)
		{
			try
			{
				jLabel4 = new JLabel();
				jLabel4.setBounds(new java.awt.Rectangle(10, 85, 122, 20));
				jLabel4.setText("Valeur maximale:");
				jLabel3 = new JLabel();
				jLabel3.setBounds(new java.awt.Rectangle(10, 55, 121, 20));
				jLabel3.setText("Valeur minimale:");
				jLabel2 = new JLabel();
				jLabel2.setBounds(new java.awt.Rectangle(10, 25, 125, 20));
				jLabel2.setText("Nombre de classes:");
				jPanel2 = new JPanel();
				jPanel2.setLayout(null);
				jPanel2.setBounds(new java.awt.Rectangle(314, 103, 248, 122));
				jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Break",
						javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
				jPanel2.add(jLabel2, null);
				jPanel2.add(jLabel3, null);
				jPanel2.add(jLabel4, null);
				jPanel2.add(getJTextField1(), null);
				jPanel2.add(getJTextField3(), null);
				jPanel2.add(getJTextField4(), null);
			}
			catch (java.lang.Throwable e)
			{
				// TODO: Something
			}
		}
		return jPanel2;
	}

	/**
	 * This method initializes jComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBox()
	{
		if (jComboBox == null)
		{
			try
			{
				jComboBox = new JComboBox();
				jComboBox.setBounds(new java.awt.Rectangle(10, 55, 187, 20));
				jComboBox.addItemListener(new java.awt.event.ItemListener()
				{
					public void itemStateChanged(java.awt.event.ItemEvent e)
					{						
						try {
							(new Thread(new Runnable(){
								public void run(){
									setupText();
								}
							})).start();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
			}
			catch (java.lang.Throwable e)
			{
				// TODO: Something
			}
		}
		return jComboBox;
	}

	/**
	 * This method initializes jTextField1
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField1()
	{
		if (jTextField1 == null)
		{
			try
			{
				jTextField1 = new JTextField();
				jTextField1.setBounds(new java.awt.Rectangle(140, 25, 96, 20));
			}
			catch (java.lang.Throwable e)
			{
				// TODO: Something
			}
		}
		return jTextField1;
	}

	/**
	 * This method initializes jTextField3
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField3()
	{
		if (jTextField3 == null)
		{
			try
			{
				jTextField3 = new JTextField();
				jTextField3.setBounds(new java.awt.Rectangle(140, 55, 96, 20));
			}
			catch (java.lang.Throwable e)
			{
				// TODO: Something
			}
		}
		return jTextField3;
	}

	/**
	 * This method initializes jTextField4
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField4()
	{
		if (jTextField4 == null)
		{
			try
			{
				jTextField4 = new JTextField();
				jTextField4.setBounds(new java.awt.Rectangle(140, 85, 96, 20));
			}
			catch (java.lang.Throwable e)
			{
				// TODO: Something
			}
		}
		return jTextField4;
	}
	
	private JButton getBut_default()
	{
		if (jButton1 == null)
		{
			try
			{
				but_default = new JButton();
				but_default.setBounds(new java.awt.Rectangle(386, 235, 80, 20));
				but_default.setText("min-Max");
				but_default.setEnabled(false);
				but_default.addActionListener(new java.awt.event.ActionListener()
				{
					public void actionPerformed(java.awt.event.ActionEvent e)
					{
						setupText();
					}
				});
			}
			catch (java.lang.Throwable e)
			{
				// TODO: Something
			}
		}
		return but_default;
	}
	/**
	 * This method initializes jButton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton1()
	{
		if (jButton1 == null)
		{
			try
			{
				jButton1 = new JButton();
				jButton1.setBounds(new java.awt.Rectangle(386, 235, 80, 20));
				jButton1.setText("OK");
				jButton1.setEnabled(false);
				jButton1.addActionListener(new java.awt.event.ActionListener()
				{
					public void actionPerformed(java.awt.event.ActionEvent e)
					{
						applychanges();
					}
				});
			}
			catch (java.lang.Throwable e)
			{
				// TODO: Something
			}
		}
		return jButton1;
	}

	protected void applychanges()
	{
		try
		{	
			IGeoFeatureLayer geoLayer = (IGeoFeatureLayer) layer;
			
			ClassBreaksRenderer classBreaksRenderer = new ClassBreaksRenderer();
			classBreaksRenderer.setField(jComboBox.getSelectedItem().toString());
			classBreaksRenderer.setBreakCount(Integer.parseInt(jTextField1.getText().trim()));
			classBreaksRenderer.setMinimumBreak(Double.parseDouble(jTextField3.getText().trim()));

			// Calculate the class interval by a simple mean value
			double interval = (Double.parseDouble(jTextField4.getText().trim()) - classBreaksRenderer.getMinimumBreak())
					/ classBreaksRenderer.getBreakCount();

			// Get the color ramp
			IColorRamp colorRamp = (IColorRamp) item.getItem();
			// Set the size of the color ramp and recreate it
			colorRamp.setSize(Integer.parseInt(jTextField1.getText().trim()));
			boolean createRamp[] =
			{ false };
			colorRamp.createRamp(createRamp);

			// Get the enumeration of colors from the color ramp
			IEnumColors enumColors = colorRamp.getColors();
			enumColors.reset();
			double currentBreak = classBreaksRenderer.getMinimumBreak()+interval;
			
			if (geoLayer.getFeatureClass().getShapeType() == esriGeometryType.esriGeometryPolygon){
				// Polygon symbol
				SimpleFillSymbol simpleFillSymbol;
				// Loop through each class break
				for (int i = 0; i < classBreaksRenderer.getBreakCount(); i++)
				{
					// Set class break
					classBreaksRenderer.setBreak(i, currentBreak);
					// Create simple fill symbol and set color
					simpleFillSymbol = new SimpleFillSymbol();
					simpleFillSymbol.setColor(enumColors.next());
					// Add symbol to renderer
					classBreaksRenderer.setSymbol(i, (ISymbol) simpleFillSymbol);
					currentBreak += interval;
				}
			}
			else if(geoLayer.getFeatureClass().getShapeType() == esriGeometryType.esriGeometryPoint){
				// Point symbol
				ISimpleMarkerSymbol  simpleMarkerSymbol;
				for (int i = 0; i < classBreaksRenderer.getBreakCount(); i++)
				{
					classBreaksRenderer.setBreak(i, currentBreak);
					simpleMarkerSymbol = new SimpleMarkerSymbol();
					simpleMarkerSymbol.setColor(enumColors.next());
					simpleMarkerSymbol.setSize(3.85);
					classBreaksRenderer.setSymbol(i, (ISymbol) simpleMarkerSymbol);
					currentBreak += interval;
				}
			}
			
			if(layer != null){
				layer.setRendererByRef(classBreaksRenderer);
				activeView.partialRefresh(esriViewDrawPhase.esriViewGeography, layer, null);
				// update the toc view
				tocBean.update();
			}
			

			// Hide the form
			this.dispose();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Erreur sur les valeurs definies.\n" +
					"Veuillez les verifier et essayer à nouveau.",
					"Erreur",JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * This method initializes jButton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton2()
	{
		if (jButton2 == null)
		{
			try
			{
				jButton2 = new JButton();
				jButton2.setBounds(new java.awt.Rectangle(480, 235, 80, 20));
				jButton2.setText("Fermer");
				jButton2.addActionListener(this);
			}
			catch (java.lang.Throwable e)
			{
				// TODO: Something
			}
		}
		return jButton2;
	}

	public void actionPerformed(ActionEvent arg0)
	{
		if (arg0.getSource() == jButton2)
		{
			this.dispose();
		}
	}

	public void addListener()
	{
		try
		{
			symbologyControl.addISymbologyControlEventsListener(new ISymbologyControlEventsAdapter()
			{
				private static final long serialVersionUID = 1L;

				public void onDoubleClick(ISymbologyControlEventsOnDoubleClickEvent theEvent)
				{
					try
					{
						item = symbologyControl.hitTest(theEvent.getX(), theEvent.getY());
					}
					catch (Exception e)
					{

					}

				}

				public void onItemSelected(ISymbologyControlEventsOnItemSelectedEvent theEvent)
				{
					// System.out.println("Item Selected1");
					try
					{
						item = (IStyleGalleryItem) theEvent.getStyleGalleryItem();

						jButton1.setEnabled(true);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					// symbologyControl.

				}

			});
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public IGeoFeatureLayer getLayer() {
		return layer;
	}

	public void setLayer(IGeoFeatureLayer layer) {
		this.layer = layer;
	}
	
	private JDialog getProgressDialog(String title)
	{
		JDialog dialog = null;
		if (progressDialog == null)
		{
			//dialog = new JDialog();
			dialog = new JDialog(this,false);
			dialog.setTitle(title);
			dialog.setIconImage(icone);
			
			dialog.setLocationRelativeTo(null);
			//dialog.setLocation(this.getWidth() / 2, this.getHeight() / 2);
			
			JProgressBar bar = new JProgressBar();
			bar.setBorderPainted(true);
			bar.setIndeterminate(true);
			dialog.add(bar, BorderLayout.PAGE_START);
			dialog.pack();
			dialog.setSize(250, 50);
		}
		else dialog = progressDialog;

		return dialog;
	}
	
}
