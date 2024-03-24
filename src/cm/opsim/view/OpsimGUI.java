package cm.opsim.view;


import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.TreeCheckingModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import cm.opsim.controller.AbstractController;
import cm.opsim.controller.PlanCapController;
import cm.opsim.controller.PlanCostController;
import cm.opsim.controller.PlanCovController;
import cm.opsim.controller.ProjectController;
import cm.opsim.dao.DAO;
import cm.opsim.dao.DAOFactory;
import cm.opsim.event.AbstractHandleEvent;
import cm.opsim.event.DimenHE;
import cm.opsim.event.MainHE;
import cm.opsim.event.MapHE;
import cm.opsim.event.ParameterHE;
import cm.opsim.event.PlanningCostHE;
import cm.opsim.event.PlanningFreqHE;
import cm.opsim.geoprocessing.OCCToolRunner;
import cm.opsim.model.Cost231Hata;
import cm.opsim.model.EnbParameter;
import cm.opsim.model.FreqBand;
import cm.opsim.model.Layer;
import cm.opsim.model.Map;
import cm.opsim.model.OkumuraHata;
import cm.opsim.model.Parameter;
import cm.opsim.model.PmParameter;
import cm.opsim.model.Project;
import cm.opsim.model.RPlanCoverage;
import cm.opsim.model.Site;
import cm.opsim.model.User;
import cm.opsim.observer.Observable;
import cm.opsim.observer.Observer;
import cm.opsim.view.cmds.CreateBookmarkCommand;
import cm.opsim.view.cmds.ShowSymbologyCommand;

import com.esri.arcgis.analyst3d.I3DProperties;
import com.esri.arcgis.analyst3d.IScene;
import com.esri.arcgis.analyst3d.Raster3DProperties;
import com.esri.arcgis.analyst3d.RasterSurface;
import com.esri.arcgis.analyst3d.esriBaseOption;
import com.esri.arcgis.beans.TOC.TOCBean;
import com.esri.arcgis.beans.map.MapBean;
import com.esri.arcgis.beans.scene.SceneBean;
import com.esri.arcgis.beans.toolbar.ToolbarBean;
import com.esri.arcgis.carto.ClassBreaksRenderer;
import com.esri.arcgis.carto.FeatureLayer;
import com.esri.arcgis.carto.IFeatureLayer;
import com.esri.arcgis.carto.IFeatureRenderer;
import com.esri.arcgis.carto.IFeatureSelection;
import com.esri.arcgis.carto.IGeoFeatureLayer;
import com.esri.arcgis.carto.ILayer;
import com.esri.arcgis.carto.ILayerExtensions;
import com.esri.arcgis.carto.IRasterLayer;
import com.esri.arcgis.carto.ISimpleRenderer;
import com.esri.arcgis.carto.RasterLayer;
import com.esri.arcgis.carto.SimpleRenderer;
import com.esri.arcgis.carto.esriViewDrawPhase;
import com.esri.arcgis.controls.ControlsAddDataCommand;
import com.esri.arcgis.controls.ControlsClearSelectionCommand;
import com.esri.arcgis.controls.ControlsEditingClearCommand;
import com.esri.arcgis.controls.ControlsEditingCopyCommand;
import com.esri.arcgis.controls.ControlsEditingCutCommand;
import com.esri.arcgis.controls.ControlsEditingPasteCommand;
import com.esri.arcgis.controls.ControlsFullScreenCommand;
import com.esri.arcgis.controls.ControlsMapFullExtentCommand;
import com.esri.arcgis.controls.ControlsMapPanTool;
import com.esri.arcgis.controls.ControlsMapZoomInFixedCommand;
import com.esri.arcgis.controls.ControlsMapZoomInTool;
import com.esri.arcgis.controls.ControlsMapZoomOutFixedCommand;
import com.esri.arcgis.controls.ControlsMapZoomOutTool;
import com.esri.arcgis.controls.ControlsMapZoomToLastExtentBackCommand;
import com.esri.arcgis.controls.ControlsMapZoomToLastExtentForwardCommand;
import com.esri.arcgis.controls.ControlsMapZoomToolControl;
import com.esri.arcgis.controls.ControlsOpenDocCommand;
import com.esri.arcgis.controls.ControlsRedoCommand;
import com.esri.arcgis.controls.ControlsSaveAsDocCommand;
import com.esri.arcgis.controls.ControlsSceneExpandFOVCommand;
import com.esri.arcgis.controls.ControlsSceneFlyTool;
import com.esri.arcgis.controls.ControlsSceneFullExtentCommand;
import com.esri.arcgis.controls.ControlsSceneNarrowFOVCommand;
import com.esri.arcgis.controls.ControlsSceneNavigateTool;
import com.esri.arcgis.controls.ControlsSceneOpenDocCommand;
import com.esri.arcgis.controls.ControlsScenePanTool;
import com.esri.arcgis.controls.ControlsSceneSelectFeaturesTool;
import com.esri.arcgis.controls.ControlsSceneSelectGraphicsTool;
import com.esri.arcgis.controls.ControlsSceneSetObserverTool;
import com.esri.arcgis.controls.ControlsSceneTargetCenterTool;
import com.esri.arcgis.controls.ControlsSceneTargetZoomTool;
import com.esri.arcgis.controls.ControlsSceneZoomInOutTool;
import com.esri.arcgis.controls.ControlsSceneZoomInTool;
import com.esri.arcgis.controls.ControlsSceneZoomOutTool;
import com.esri.arcgis.controls.ControlsSelectFeaturesTool;
import com.esri.arcgis.controls.ControlsSelectTool;
import com.esri.arcgis.controls.ControlsUndoCommand;
import com.esri.arcgis.controls.IMapControlEvents2Adapter;
import com.esri.arcgis.controls.IMapControlEvents2OnDoubleClickEvent;
import com.esri.arcgis.controls.IMapControlEvents2OnMouseMoveEvent;
import com.esri.arcgis.controls.ToolbarMenu;
import com.esri.arcgis.datasourcesGDB.FileGDBWorkspaceFactory;
import com.esri.arcgis.datasourcesfile.ShapefileWorkspaceFactory;
import com.esri.arcgis.datasourcesraster.IRasterBandCollection;
import com.esri.arcgis.datasourcesraster.IRasterWorkspace;
import com.esri.arcgis.datasourcesraster.IRasterWorkspaceProxy;
import com.esri.arcgis.datasourcesraster.RasterWorkspaceFactory;
import com.esri.arcgis.display.AlgorithmicColorRamp;
import com.esri.arcgis.display.HsvColor;
import com.esri.arcgis.display.IEnumColors;
import com.esri.arcgis.display.IEnumStyleGalleryItem;
import com.esri.arcgis.display.ISimpleMarkerSymbol;
import com.esri.arcgis.display.IStyleGallery;
import com.esri.arcgis.display.IStyleGalleryItem;
import com.esri.arcgis.display.IStyleGalleryStorage;
import com.esri.arcgis.display.ISymbol;
import com.esri.arcgis.display.ServerStyleGallery;
import com.esri.arcgis.display.SimpleMarkerSymbol;
import com.esri.arcgis.geodatabase.DataStatistics;
import com.esri.arcgis.geodatabase.Feature;
import com.esri.arcgis.geodatabase.ICursor;
import com.esri.arcgis.geodatabase.ICursorProxy;
import com.esri.arcgis.geodatabase.IFeature;
import com.esri.arcgis.geodatabase.IFeatureClass;
import com.esri.arcgis.geodatabase.IFeatureCursor;
import com.esri.arcgis.geodatabase.IField;
import com.esri.arcgis.geodatabase.IGPMessages;
import com.esri.arcgis.geodatabase.IRasterDataset;
import com.esri.arcgis.geodatabase.IRow;
import com.esri.arcgis.geodatabase.ISelectionSet;
import com.esri.arcgis.geodatabase.ISurface;
import com.esri.arcgis.geodatabase.IWorkspaceFactory;
import com.esri.arcgis.geodatabase.Workspace;
import com.esri.arcgis.geometry.Point;
import com.esri.arcgis.geoprocessing.GeoProcessor;
import com.esri.arcgis.geoprocessing.tools.datamanagementtools.AddField;
import com.esri.arcgis.system.Cleaner;
import com.esri.arcgis.system.EngineInitializer;
import com.esri.arcgis.system.IPropertySet;
import com.esri.arcgis.system.IStatisticsResults;
import com.esri.arcgis.system.PropertySet;
import com.esri.arcgis.system.VarArray;
import com.esri.arcgis.systemUI.esriCommandStyles;


/**
 * @author Romuald FOTSO
 * @date 20 mai 2014 14:17:02
 */
public class OpsimGUI extends JFrame implements Observer{

	private Hashtable<String,AbstractController> listControler;
	private User user = new User();
	private Image icone =  Toolkit.getDefaultToolkit().getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/logo.png"));
	private JPanel 	conPane = new JPanel();
			
	private JMenuBar menuBar = new JMenuBar();
	private JToolBar toolBar1 = new JToolBar();
	private JToolBar toolBar2 = new JToolBar();
	private JToolBar toolBar3 = new JToolBar();
	private JToolBar toolBar4 = new JToolBar();
	private JToolBar toolBar5 = new JToolBar();
	
	// All Menus
	private JMenu   men_File = new JMenu(" Fichier "),
					men_Edit = new JMenu(" Edition "),
					men_Planning = new JMenu(" Planification "),
					men_Map = new JMenu(" Cartographie "),
					men_Run = new JMenu(" Executer "),
					men_Tool = new JMenu(" Outils "),
				    men_Window = new JMenu(" Fenêtre "),
				    men_Option = new JMenu(" Option "),
				    men_Help = new JMenu(" Aide "),
				    men_Import = new JMenu("Importer"),
				    men_Export = new JMenu("Exporter"),
				    men_Print = new JMenu("Imprimer"),
				    men_PlanFreq = new JMenu("Fréquence"),
				    men_Display3D = new JMenu("Affichage 3D"),
				    men_Display2D = new JMenu("Affichage 2D"),
				    men_Gen3DModel = new JMenu("Génération de modèles 3D"),
				    men_PrefEquip = new JMenu("Equipement");
	
	// Menu 1 - MenuItems
	private JMenuItem 	menIt_ManProject = new JMenuItem("Gestionnaire de projet...",new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/manproj.png")))),
						menIt_AchProject = new JMenuItem("Achivage du projet...", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/archive.png")))),
						menIt_ReloadProject = new JMenuItem("Recharger projet", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/reload.png")))),
						menIt_Save = new JMenuItem("Enregistrer", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/save.png")))),
						menIt_SaveAs = new JMenuItem("Enregistrer sous...", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/saveas.png")))),						
						menIt_Import_OnMap = new JMenuItem("Sur la cartographie..."),
						menIt_Import_OnCover = new JMenuItem("Sur la couverture..."),
						menIt_Import_ListSite = new JMenuItem("Liste des sites..."),						
						menIt_Export_Map = new JMenuItem("Cartographie..."),
						menIt_Export_Cover = new JMenuItem("Couverture..."),
						menIt_Export_Planif = new JMenuItem("Planification..."),								
						menIt_Publish = new JMenuItem("Publier...", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/publish.png")))),
						menIt_Print_Report = new JMenuItem("Rapport..."),
						menIt_Print_Map = new JMenuItem("Cartographie..."),
						menIt_Print_Cover = new JMenuItem("Couverture..."),
						menIt_Print_Site = new JMenuItem("Site..."),
						menIt_Close = new JMenuItem("Fermer", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/close.png")))),
						menIt_Pref = new JMenuItem("Preferences...", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/pref.png")))),
						menIt_Exit = new JMenuItem("Quitter", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/exit.png"))));
	// Menu 2 - MenuItems
	private JMenuItem 	menIt_Undo = new JMenuItem("Annuler", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/prev.png")))),
						menIt_Redo = new JMenuItem("Rétablir", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/next.png")))),
						menIt_Cut = new JMenuItem("Couper", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/cut.png")))),
						menIt_Copy = new JMenuItem("Copier", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/copy.png")))),
						menIt_Paste = new JMenuItem("Coller", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/paste.png")))),
						menIt_Delete = new JMenuItem("Supprimer", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/undo.png")))),
						menIt_Search = new JMenuItem("Rechercher", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/search.png"))));

	// Menu 3 - MenusItems
	private JMenuItem 	menIt_PlanCover = new JMenuItem("Couverture...", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/plancov.png")))),
						menIt_PlanCap = new JMenuItem("Capacité...", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/plancap.png")))),						
						menIt_PlanFreq_PF = new JMenuItem("Géneration Plan de fréqunces...", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/genpf.png")))),
						menIt_PlanFreq_AssF = new JMenuItem("Assignation des fréquences...", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/assignfreq.png")))),
						menIt_PlanCost = new JMenuItem("Capex/Opex...", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/plancost.png")))),
						menIt_PlanPros = new JMenuItem("Prospective...");
	// Menu 4 - MenusItems
	private JMenuItem 	menIt_Display3D_MneOnImg = new JMenuItem("MNE sur couche Image"),
						menIt_Display3D_MneOnCov = new JMenuItem("MNE sur couche Couverture"),
						menIt_Display3D_MneOnSinr = new JMenuItem("MNE sur couche SINR"),
						menIt_Display3D_MneOnMns = new JMenuItem("MNE sur couche MNS"),
						menIt_Display3D_MneOnSite = new JMenuItem("MNE sur couche Site"),
						menIt_Display3D_MneOnCE = new JMenuItem("MNE sur couche CE"),
						menIt_Display3D_ClutOnMne = new JMenuItem("MNE sur couche Clutter"),								
						menIt_Display2D_Img = new JMenuItem("Couche Image"),
						menIt_Display2D_Clut = new JMenuItem("Couche Clutter"),
						menIt_Display2D_MNS = new JMenuItem("Couche MNS"),
						menIt_Display2D_Field = new JMenuItem("Couche Champ EM"),
						menIt_Display2D_Power = new JMenuItem("Couche Puissance"),
						menIt_Display2D_SIR = new JMenuItem("Couche SIR"),
						menIt_Display2D_SINR = new JMenuItem("Couche SINR"),
						menIt_Display2D_Site = new JMenuItem("Couche Site"),
						menIt_PatColor = new JMenuItem("Palette de couleur", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/tabcolor.png"))));
	// Menu 5 - MenusItems
	private JMenuItem 	menIt_NewWindow = new JMenuItem("Nouvelle Fenêtre", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/newwin.png")))),
						menIt_NewMap = new JMenuItem("Nouvelle Carte", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/newmap.png"))));
	// Menu 6 - MenusItems
	private JMenuItem 	menIt_CalPL = new JMenuItem("Calcul du Path Loss...", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/calpl.png")))),
						menIt_Gen3DModel_Basic = new JMenuItem("Basique...", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/3dmod_basic.png")))),
						menIt_Gen3DModel_CE = new JMenuItem("CityEngine...", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/3dmod_ce.png")))),
						menIt_SelSite = new JMenuItem("Sélection des sites...", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/selsite.png")))),
						menIt_CalCovTx = new JMenuItem("Prédiction de couverture radio...", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/drawprofil.png")))),
						menIt_DrawProfil = new JMenuItem("Tracer profil de la liaison...", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/drawprofil.png")))),
						menIt_AnaInterf = new JMenuItem("Analyse d'interférence...", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/anainterf.png")))),
						menIt_AssignFreq = new JMenuItem("Assignation de fréquence...", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/assignfreq.png")))),
						menIt_generatePF = new JMenuItem("Génération Plan de fréquence...", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/genpf.png"))));
	// Menu 7 - MenusItems
	private JMenuItem 	menIt_CalMP = new JMenuItem("Calibration modèle..."),
						menIt_GeoProcessing = new JMenuItem("GeoProcessing..."),
						menIt_Dimenensionning = new JMenuItem("Dimensionnement...");
	// Menu 8 - MenusItems
	private JMenuItem 	menIt_PrefProject = new JMenuItem("Projet..."),
						menIt_PrefGen = new JMenuItem("Generale..."),
						menIt_PrefEquip_ENB = new JMenuItem("E-NodeB..."),
						menIt_PrefTargetZone = new JMenuItem("Zone d'étude..."),
						menIt_PrefModelTraf = new JMenuItem("Modèle de Trafic..."),
						menIt_PrefDriveTest = new JMenuItem("Drive Test..."),
						menIt_PrefCost = new JMenuItem("Capex/Opex...");	
	// Menu 9 - MenusItems
	private JMenuItem 	menIt_OpsimHelp = new JMenuItem("Aide OPSIM", new ImageIcon(Toolkit.getDefaultToolkit().
			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/help.png")))),
						menIt_OpsimAbout = new JMenuItem("A-propos de OPSIM", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/about.png")))),
						menIt_OpsimTeam = new JMenuItem("Qui sommes-nous ?", new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/team.png"))));	
	
	// Toolbar buttons
	private JButton tb_but_manProj = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().
			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/toolbar/manproj.png"))));
	private JButton tb_but_save = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().
			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/toolbar/save.png"))));

	
	private JButton tb_but_plancov = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().
			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/toolbar/plancov.png"))));
	private JButton tb_but_plancap = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().
			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/toolbar/plancap.png"))));
	private JButton tb_but_planfreq = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().
			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/toolbar/planfreq.png"))));
	private JButton tb_but_plancost = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().
			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/toolbar/plancost.png"))));
	private JButton tb_but_calpl = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().
			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/toolbar/calpl.png"))));
	private JButton tb_but_selsite = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().
			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/toolbar/selsite.png"))));
	private JButton tb_but_anainterf = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().
			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/toolbar/anainterf.png"))));
	private JButton tb_but_assignfreq = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().
			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/toolbar/assignfreq.png"))));
	private JButton tb_but_genpf = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().
			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/toolbar/genpf.png"))));
	private JButton tb_tasks = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().
			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/tasks.png"))));
	
	private JPanel 	conToolBar = new JPanel();
	private JPanel conGroupTB = new JPanel();
	private JPanel conGroup_leftCore = new JPanel();
	private JPanel conGroup_mainCore = new JPanel();
	private JPanel conGroup_map = new JPanel();
//	private ImagePanel conGroup_map = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().
//			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/bg.jpg"))).getImage());
	private JPanel conGroup_rightCore = new JPanel();
	private JPanel conGroup_stateBar = new JPanel();
	private JPanel conTab_layer = new JPanel();
	private JPanel conTab_explorer = new JPanel();
	private JPanel conTab_config = new JPanel();
	private JPanel conTab_rltPlan = new JPanel();
	private JPanel conTab_rltDim = new JPanel();
	private JPanel conTab_site = new JPanel();
	private JPanel conTab_recep = new JPanel();
	
	private JTabbedPane tabPane_left_top = new JTabbedPane();	
	private JTabbedPane tabPane_left_bottom = new JTabbedPane();
	private JTabbedPane tabPane_right_top = new JTabbedPane();
	private JTabbedPane tabPane_right_bottom = new JTabbedPane();
	private JTabbedPane tabPane_map = new JTabbedPane();
	
	private JSplitPane split_left;
	private JSplitPane split_center;
	private JSplitPane split_right;
	private JSplitPane split_global;
	
	private CheckboxTree tree_tabLayer;
	private JTree tree_tabExplore, tree_tabConfig, tree_tabDGR, tree_tabReport, tree_tabTx, tree_tabRx;
	
	private JLabel lab_taskBar, lab_state, lab_x, lab_y, lab_z, lab_clut, lab_rscp, lab_sir, lab_snir;
	private JTextField tf_state, tf_x, tf_y, tf_z, tf_clut, tf_rscp, tf_sir, tf_snir;
	private JProgressBar bar;
	
	private AbstractHandleEvent mainHE, dimHE, mapHE, paramHE, planCostHE, planFreqHE;
	
	private MapBean opsimMap, s_opsimMap, prevMap;
	private SceneBean opsimScene, s_opsimScene;
	private String CURRENT_MAP_CURSOR = "HAND";
	private TOCBean toc;
	
	private ToolbarBean tb_ags_standard;
	private ToolbarBean tb_ags_option_2D;
	private ToolbarBean tb_ags_option_3D;
	private ToolbarBean tb_ags_3danalyst;
	
	private ToolbarMenu tbm_toc;
	
	private DialogManProject dialog_manProj;
	private DialogNewProj dialog_newProj;
	private DialogPlanCov dialog_planCov;
	private DialogPlanCap dialog_planCap;
	private DialogPlanCost dialog_planCost;
	private JDialog progressDialog = null;
	
	private String defTitle = "OPSIM Pro 1.6.1";
	private OpsimGUI This = this;
				
	public OpsimGUI(Hashtable<String,AbstractController> list){
		this.loginUser(this.user);
		this.setTitle("OPSIM Pro 1.6.1 - ["+this.user.getName()+"]");		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(1000,600));		
		this.setLocationRelativeTo(null);
		this.listControler = list;
		
		// init Map & all layers
		this.initMap();
		//configure GUI
		this.initComponent();		
		this.initEvent();
		// Use Look & Feel System
		try {
	           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	           SwingUtilities.updateComponentTreeUI(this);
	    } 
		catch (InstantiationException e) {} 
		catch (ClassNotFoundException e) {}
		catch (UnsupportedLookAndFeelException e) {} 
		catch (IllegalAccessException e) {}
		// customize of JtabbedPane
		this.tabPane_left_top.setUI(new AquaBarTabbedPaneUI());
		this.tabPane_left_bottom.setUI(new AquaBarTabbedPaneUI());
		this.tabPane_right_top.setUI(new AquaBarTabbedPaneUI());
		this.tabPane_right_bottom.setUI(new AquaBarTabbedPaneUI());
		this.tabPane_map.setUI(new PlasticTabbedPaneUI());
		
		this.initController();		
		//set GUI to contentPane
		this.setContentPane(conPane);
		//this.pack();
		this.setVisible(true);
		if(this.user.getProfil().isShowManProj()){
			dialog_manProj = new DialogManProject(this,true);
		}
			
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{				
				try
				{	
					DAO<Project> projDAO = DAOFactory.getProjectDAO();
					ProjectController projConWS = (ProjectController)listControler.get("ProjectController");
					Project projWS = (Project) projConWS.getModel();
					Project projDBB = projDAO.find(projWS.getId());
					Date dateWS = projWS.getUpdatedDate();
					Date dateDBB = projDBB.getUpdatedDate();
					
					if((projWS != null && projDBB != null) || (dateWS != null && dateDBB != null)){					
						System.out.println("WS:0- Proj Date = "+dateWS);
						System.out.println("DBB:0- Proj Date = "+dateDBB);
						if(!projDBB.getUpdatedDate().equals(projWS.getUpdatedDate())){
							int option = 99;
							option = JOptionPane.showConfirmDialog(null, "Le projet en cours dispose des données non enregistrées.\n" +
									 "Voulez-vous les enregistrer avant de fermer OPSIM ?", "Fermeture de OPSIM",
									 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
							System.out.println("WS:1- Proj Date = "+projWS.getUpdatedDate());
							System.out.println("DBB:1- Proj Date = "+projDBB.getUpdatedDate());
							if(option != JOptionPane.NO_OPTION &&
									option != JOptionPane.CANCEL_OPTION &&
								option != JOptionPane.CLOSED_OPTION){						
								projWS = projDAO.update(projWS);
								EngineInitializer.releaseAll();
								System.exit(0);
							}
							else if(option == JOptionPane.NO_OPTION){
								EngineInitializer.releaseAll();
								System.exit(0);
							}
							else This.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE );
						}
						else{
							int option = 99;
							option = JOptionPane.showConfirmDialog(null, "Etes-vous sure de vouloir quitter OPSIM ?", "Fermeture de OPSIM",
									 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							if(option != JOptionPane.NO_OPTION &&
									option != JOptionPane.CLOSED_OPTION){	
								EngineInitializer.releaseAll();
								System.exit(0);
							}
							else This.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE );
						}
					}
					else{
						int option = 99;
						option = JOptionPane.showConfirmDialog(null, "Etes-vous sure de vouloir quitter OPSIM ?", "Fermeture de OPSIM",
								 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if(option != JOptionPane.NO_OPTION &&
								option != JOptionPane.CLOSED_OPTION){	
							EngineInitializer.releaseAll();
							System.exit(0);
						}
						else This.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE );
					}
				}
				catch(NullPointerException ex){
					ex.printStackTrace();
					int option = 99;
					option = JOptionPane.showConfirmDialog(null, "Etes-vous sure de vouloir quitter OPSIM ?", "Fermeture de OPSIM",
							 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(option != JOptionPane.NO_OPTION &&
							option != JOptionPane.CLOSED_OPTION){	
						EngineInitializer.releaseAll();
						System.exit(0);
					}
					else This.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE );
				}
			}
		});	
	}	
	
	public void loginUser(User user){
		DAO<User> userDAO = DAOFactory.getUserDAO();
		this.user = userDAO.find(1);	
	}
	
	public void initController(){
		this.listControler.put("ProjectController", new ProjectController(null));
		this.listControler.put("PlanCovController", new PlanCovController(null));
		this.listControler.put("PlanCapController", new PlanCapController(null));
		this.listControler.put("PlanCostController", new PlanCostController(null));
	}
	
	public void initComponent(){
		this.setIconImage(icone);
		this.conPane.setLayout(new BorderLayout());
		
		this.initMenuBar();
		this.initToolBar();
		this.initLeftCore();
		this.initRightCore();
		this.initMainCore();
		
		this.initStateBar();		
		
		this.setContentPane(conPane);
		//pack();
	}
	
	public void initMap(){
		this.opsimMap = new MapBean();
		this.s_opsimMap = new MapBean();
		this.prevMap = new MapBean();
		this.opsimScene = new SceneBean();
		this.toc = new TOCBean();
		
		this.tb_ags_standard = new ToolbarBean();
		this.tb_ags_option_2D = new ToolbarBean();
		this.tb_ags_option_3D = new ToolbarBean();
		
        final String devKitHome = System.getenv("AGSDEVKITJAVA");
        
        try {			
	        this.tb_ags_standard.setBuddyControl(this.opsimMap);
	        this.tb_ags_option_2D.setBuddyControl(this.opsimMap);	        
	        this.toc.setBuddyControl(this.opsimMap);
	        
	        tbm_toc = new ToolbarMenu();
	        tbm_toc.addItem(new ShowSymbologyCommand(this.toc, this), 0, -1, false, esriCommandStyles.esriCommandStyleTextOnly);
	        tbm_toc.setHook(this.tb_ags_standard.getObject());
	        
	        opsimMap.addIMapControlEvents2Listener(new MapControlListener());       
	        
		} catch (Exception e) {
			System.out.println("Error: "+e+" catch on initMap");
		}	    
	}
	
	/*
	 * Init MenuBar Component
	 */
	public void initMenuBar(){
		// Create lightweight-disabled menu
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		
		this.men_File.add(this.menIt_ManProject);
		this.menIt_AchProject.setEnabled(false);
		this.men_File.add(this.menIt_AchProject);
		this.menIt_ReloadProject.setEnabled(false);
		this.men_File.add(this.menIt_ReloadProject);
		this.men_File.addSeparator();
		this.menIt_Save.setEnabled(false);
		this.menIt_SaveAs.setEnabled(false);
		this.men_File.add(this.menIt_Save);
		this.men_File.add(this.menIt_SaveAs);
		
		this.men_Import.add(this.menIt_Import_OnMap);
		this.men_Import.add(this.menIt_Import_OnCover);
		this.men_Import.add(this.menIt_Import_ListSite);
		this.men_Import.setEnabled(false);
		this.men_File.add(this.men_Import);
		this.menIt_Export_Planif.setEnabled(false);
		this.menIt_Export_Cover.setEnabled(false);
		this.men_Export.add(this.menIt_Export_Planif);
		this.men_Export.add(this.menIt_Export_Map);
		this.men_Export.add(this.menIt_Export_Cover);
		this.men_Export.setEnabled(false);
		this.men_File.add(this.men_Export);
		this.menIt_Publish.setEnabled(false);
		this.men_File.add(this.menIt_Publish);
		this.men_File.addSeparator();
		this.men_Print.add(this.menIt_Print_Report);
		this.men_Print.add(this.menIt_Print_Map);
		this.men_Print.add(this.menIt_Print_Cover);
		this.men_Print.add(this.menIt_Print_Site);
		this.men_Print.setEnabled(false);
		this.men_File.add(this.men_Print);
		this.men_File.addSeparator();	
		this.menIt_Close.setEnabled(false);
		this.men_File.add(this.menIt_Close);
		this.men_File.addSeparator();	
		this.men_File.add(this.menIt_Pref);
		this.men_File.addSeparator();		
		this.men_File.add(this.menIt_Exit);
		
		this.menIt_Undo.setEnabled(false);
		this.menIt_Redo.setEnabled(false);
		this.men_Edit.add(this.menIt_Undo);
		this.men_Edit.add(this.menIt_Redo);
		this.men_Edit.addSeparator();
		this.menIt_Cut.setEnabled(false);
		this.menIt_Copy.setEnabled(false);
		this.menIt_Paste.setEnabled(false);
		this.men_Edit.add(this.menIt_Cut);
		this.men_Edit.add(this.menIt_Copy);
		this.men_Edit.add(this.menIt_Paste);
		this.men_Edit.addSeparator();
		this.menIt_Delete.setEnabled(false);
		this.menIt_Search.setEnabled(false);
		this.men_Edit.add(this.menIt_Delete);
		this.men_Edit.add(this.menIt_Search);
		
		this.menIt_PlanCover.setEnabled(false);
		this.menIt_PlanCap.setEnabled(false);
		this.menIt_PlanCost.setEnabled(false);
		this.men_PlanFreq.setEnabled(false);
		this.men_Planning.add(this.menIt_PlanCover);
		this.men_Planning.add(this.menIt_PlanCap);
		this.menIt_PlanFreq_AssF.setEnabled(false);
		this.menIt_PlanFreq_PF.setEnabled(false);
		this.men_PlanFreq.add(this.menIt_PlanFreq_AssF);
		this.men_PlanFreq.add(this.menIt_PlanFreq_PF);
		this.men_Planning.add(this.men_PlanFreq);
		this.men_Planning.add(this.menIt_PlanCost);
		this.menIt_PlanPros.setEnabled(false);
		this.men_Planning.add(this.menIt_PlanPros);
			
		this.menIt_Display2D_MNS.setEnabled(false);
		this.menIt_Display2D_Field.setEnabled(false);
		this.menIt_Display2D_Power.setEnabled(false);
		this.menIt_Display2D_SIR.setEnabled(false);
		this.menIt_Display2D_SINR.setEnabled(false);
		this.menIt_Display2D_Site.setEnabled(false);
		this.men_Display2D.add(this.menIt_Display2D_MNS);
		this.men_Display2D.add(this.menIt_Display2D_Clut);
		this.men_Display2D.add(this.menIt_Display2D_Img);
		this.men_Display2D.addSeparator();
		this.men_Display2D.add(this.menIt_Display2D_Field);
		this.men_Display2D.add(this.menIt_Display2D_Power);
		this.men_Display2D.addSeparator();
//		this.men_Display2D.add(this.menIt_Display2D_SIR);
		this.men_Display2D.add(this.menIt_Display2D_SINR);
		this.men_Display2D.addSeparator();
		this.men_Display2D.add(this.menIt_Display2D_Site);	
		this.men_Display2D.setEnabled(false);
		this.men_Map.add(this.men_Display2D);
		this.menIt_Display3D_MneOnImg.setEnabled(false);
		this.menIt_Display3D_MneOnCov.setEnabled(false);
		this.menIt_Display3D_MneOnSinr.setEnabled(false);
		this.menIt_Display3D_ClutOnMne.setEnabled(false);
		this.menIt_Display3D_MneOnMns.setEnabled(false);
		this.menIt_Display3D_MneOnSite.setEnabled(false);
		this.menIt_Display3D_MneOnCE.setEnabled(false);
		this.men_Display3D.add(this.menIt_Display3D_MneOnImg);
		this.men_Display3D.add(this.menIt_Display3D_MneOnCov);
		this.men_Display3D.add(this.menIt_Display3D_MneOnSinr);
		this.men_Display3D.add(this.menIt_Display3D_ClutOnMne);		
		this.men_Display3D.add(this.menIt_Display3D_MneOnMns);
		this.men_Display3D.add(this.menIt_Display3D_MneOnCE);
		this.men_Display3D.add(this.menIt_Display3D_MneOnSite);
		this.men_Display3D.setEnabled(false);
		this.menIt_PatColor.setEnabled(false);
		this.men_Map.add(this.men_Display3D);
		this.men_Map.add(this.menIt_PatColor);
		
		this.menIt_CalPL.setEnabled(false);
		this.menIt_DrawProfil.setEnabled(false);
//		this.men_Run.add(this.menIt_CalPL);menIt_Gen3DModel_Basic
		this.men_Run.add(this.menIt_DrawProfil);
		this.men_Run.addSeparator();
		this.menIt_Gen3DModel_Basic.setEnabled(false);
		this.menIt_Gen3DModel_CE.setEnabled(false);
		this.men_Gen3DModel.setEnabled(false);
		this.men_Gen3DModel.add(this.menIt_Gen3DModel_Basic);
		this.men_Gen3DModel.add(this.menIt_Gen3DModel_CE);
		this.men_Run.add(this.men_Gen3DModel);
		this.men_Run.addSeparator();
		this.menIt_SelSite.setEnabled(false);
		this.men_Run.add(this.menIt_SelSite);
		this.men_Run.addSeparator();
		this.menIt_CalCovTx.setEnabled(false);
		this.men_Run.add(this.menIt_CalCovTx);
		this.men_Run.addSeparator();
		this.menIt_AnaInterf.setEnabled(false);
		this.menIt_AssignFreq.setEnabled(false);
		this.menIt_generatePF.setEnabled(false);
		this.men_Run.add(this.menIt_AnaInterf);
//		this.men_Run.add(this.menIt_AssignFreq);
//		this.men_Run.add(this.menIt_generatePF);
		
		this.menIt_CalMP.setEnabled(false);
		this.menIt_GeoProcessing.setEnabled(false);
		this.menIt_Dimenensionning.setEnabled(false);
		this.men_Tool.add(this.menIt_CalMP);
		this.men_Tool.add(this.menIt_GeoProcessing);
		this.men_Tool.add(this.menIt_Dimenensionning);
		
		this.menIt_NewWindow.setEnabled(false);
		this.menIt_NewMap.setEnabled(false);
		this.men_Window.add(this.menIt_NewWindow);
		this.men_Window.add(this.menIt_NewMap);
		
		
		this.men_Option.add(this.menIt_PrefProject);
		this.men_Option.add(this.menIt_PrefGen);
		this.men_PrefEquip.add(this.menIt_PrefEquip_ENB);
		this.men_Option.add(this.men_PrefEquip);
		this.men_Option.add(this.menIt_PrefTargetZone);
		this.men_Option.add(this.menIt_PrefModelTraf);
		this.men_Option.add(this.menIt_PrefDriveTest);
		this.men_Option.add(this.menIt_PrefCost);
		
		this.men_Help.add(this.menIt_OpsimHelp);		
		this.men_Help.add(this.menIt_OpsimTeam);
		this.men_Help.addSeparator();
		this.men_Help.add(this.menIt_OpsimAbout);
		
		this.menuBar.add(men_File);
		this.menuBar.add(men_Edit);
		this.menuBar.add(men_Planning);
		this.menuBar.add(men_Map);
		this.menuBar.add(men_Run);
		this.menuBar.add(men_Tool);
		this.menuBar.add(men_Window);
		this.menuBar.add(men_Option);
		this.menuBar.add(men_Help);
		
		
		this.setJMenuBar(menuBar);
	}
	
	/*
	 * Init ToolBar Component
	 */
	public void initToolBar(){
		/*
		 *  Project Toolbar
		 */
		this.tb_but_manProj.setFocusable(false);	
		this.tb_but_manProj.setToolTipText("Gestionnaire de projets (Ctrl+Maj+P)");
		this.toolBar1.add(this.tb_but_manProj);
		
		this.tb_but_save.setFocusable(false);	
		this.tb_but_save.setToolTipText("Enregistrer le projet (Ctrl+S)");
		this.tb_but_save.setEnabled(false);
		this.toolBar1.add(this.tb_but_save);
		
		/*
		 *  Standard ToolBar
		 */		
		try {
			this.tb_ags_standard.addItem(new ControlsOpenDocCommand(), 0,  - 1, false, 0,
			        esriCommandStyles.esriCommandStyleIconOnly);
			this.tb_ags_standard.addItem(new ControlsSaveAsDocCommand(), 0,  - 1, false, 0,
			        esriCommandStyles.esriCommandStyleIconOnly);
			this.tb_ags_standard.addItem(new ControlsEditingCutCommand(), 0,  - 1, true, 0,
			        esriCommandStyles.esriCommandStyleIconOnly);
			this.tb_ags_standard.addItem(new ControlsEditingCopyCommand(), 0,  - 1, false, 0,
			        esriCommandStyles.esriCommandStyleIconOnly);
			this.tb_ags_standard.addItem(new ControlsEditingPasteCommand(), 0,  - 1, false, 0,
			        esriCommandStyles.esriCommandStyleIconOnly);
			this.tb_ags_standard.addItem(new ControlsEditingClearCommand(), 0,  - 1, true, 0,
			        esriCommandStyles.esriCommandStyleIconOnly);
			this.tb_ags_standard.addItem(new ControlsUndoCommand(), 0,  - 1, false, 0,
			        esriCommandStyles.esriCommandStyleIconOnly);
			this.tb_ags_standard.addItem(new ControlsRedoCommand(), 0,  - 1, false, 0,
			        esriCommandStyles.esriCommandStyleIconOnly);
			this.tb_ags_standard.addItem(new ControlsAddDataCommand(), 0,  - 1, true, 0,
			        esriCommandStyles.esriCommandStyleIconOnly);
			this.tb_ags_standard.addItem(new ControlsMapZoomToolControl(), 0,  - 1, false, 0,
			        esriCommandStyles.esriCommandStyleIconOnly);
			this.tb_ags_standard.addItem(new ControlsFullScreenCommand(), 0,  - 1, false, 0,
			        esriCommandStyles.esriCommandStyleIconOnly);
			
			ToolbarMenu toolbarMenu = new ToolbarMenu();
			toolbarMenu.setCaption("Signets");
			toolbarMenu.addItem(new CreateBookmarkCommand(), -1, -1, false, esriCommandStyles.esriCommandStyleTextOnly);
			toolbarMenu.addMultiItem(new SpatialBookmarks(), -1, true, esriCommandStyles.esriCommandStyleTextOnly);
			this.tb_ags_standard.addItem(toolbarMenu, -1, -1, true, 0, esriCommandStyles.esriCommandStyleMenuBar);
		} 
		catch (IOException e) {
			System.out.println("Error: "+e+" caught on init Standard Toolbar");
		}
		//this.tb_ags_standard1.setPreferredSize(new Dimension(10,30));
		this.toolBar2.add(this.tb_ags_standard);
		this.toolBar2.setPreferredSize(new Dimension(475,30));
		
		/*
		 *  Option ToolBar for Map 2D
		 */
		try {
			this.tb_ags_option_2D.addItem(new ControlsMapZoomInTool(), 0,  - 1, false, 0,
			        esriCommandStyles.esriCommandStyleIconOnly);
			this.tb_ags_option_2D.addItem(new ControlsMapZoomOutTool(), 0,  - 1, false, 0,
			        esriCommandStyles.esriCommandStyleIconOnly);
			this.tb_ags_option_2D.addItem(new ControlsMapPanTool(), 0,  - 1, false, 0,
			        esriCommandStyles.esriCommandStyleIconOnly);
			this.tb_ags_option_2D.addItem(new ControlsMapFullExtentCommand(), 0,  - 1, false, 0,
			        esriCommandStyles.esriCommandStyleIconOnly);
			this.tb_ags_option_2D.addItem(new ControlsMapZoomInFixedCommand(), 0,  - 1, true, 0,
			        esriCommandStyles.esriCommandStyleIconOnly);
			this.tb_ags_option_2D.addItem(new ControlsMapZoomOutFixedCommand(), 0,  - 1, false, 0,
			        esriCommandStyles.esriCommandStyleIconOnly);
			this.tb_ags_option_2D.addItem(new ControlsMapZoomToLastExtentBackCommand(), 0,  - 1, true, 0,
			        esriCommandStyles.esriCommandStyleIconOnly);
			this.tb_ags_option_2D.addItem(new ControlsMapZoomToLastExtentForwardCommand(), 0,  - 1, false, 0,
			        esriCommandStyles.esriCommandStyleIconOnly);
			this.tb_ags_option_2D.addItem(new ControlsSelectFeaturesTool(), 0,  - 1, true, 0,
			        esriCommandStyles.esriCommandStyleIconOnly);
			this.tb_ags_option_2D.addItem(new ControlsClearSelectionCommand(), 0,  - 1, false, 0,
			        esriCommandStyles.esriCommandStyleIconOnly);
			this.tb_ags_option_2D.addItem(new ControlsSelectTool(), 0,  - 1, true, 0,
			        esriCommandStyles.esriCommandStyleIconOnly);
//			this.tb_ags_option.addItem(new ControlsMapIdentifyTool(), 0,  - 1, true, 0,
//			        esriCommandStyles.esriCommandStyleIconOnly);
//			this.tb_ags_option.addItem(new ControlsMapFindCommand(), 0,  - 1, false, 0,
//			        esriCommandStyles.esriCommandStyleIconOnly);
		} 
		catch (IOException e) {
			System.out.println("Error: "+e+" caught on init Option Toolbar");
		}
		/*
		 *  Option Toolbar for Scene 3D
		 */
		try {
			//tb_ags_option_3D.addItem(new ControlsAddDataCommand(), 0,  - 1, true, 0,esriCommandStyles.esriCommandStyleIconOnly);
			tb_ags_option_3D.addItem(ControlsSceneOpenDocCommand.getClsid(), 0, -1, false, 1, 1); // Open
			
			tb_ags_option_3D.addItem(ControlsSceneNavigateTool.getClsid(), 0, -1, true, 1, 1); // Navigate
			tb_ags_option_3D.addItem(ControlsSceneFlyTool.getClsid(), 0, -1, false, 1, 1); // Fly
			tb_ags_option_3D.addItem(ControlsSceneZoomInOutTool.getClsid(), 0, -1, false, 1, 1); // ZoomInOut
			tb_ags_option_3D.addItem(ControlsSceneZoomInTool.getClsid(), 0, -1, false, 1, 1); // ZoomIn
			tb_ags_option_3D.addItem(ControlsSceneZoomOutTool.getClsid(), 0, -1, false, 1, 1); // ZoomOut
			tb_ags_option_3D.addItem(ControlsSceneFullExtentCommand.getClsid(), 0, -1, false, 1, 1); // FullExtent
			tb_ags_option_3D.addItem(ControlsScenePanTool.getClsid(), 0, -1, false, 1, 1); // Pan

			tb_ags_option_3D.addItem(ControlsSceneExpandFOVCommand.getClsid(), 0, -1, true, 1, 1); // ExpandFOV
			tb_ags_option_3D.addItem(ControlsSceneNarrowFOVCommand.getClsid(), 0, -1, false, 1, 1); // NarrowFOV

			tb_ags_option_3D.addItem(ControlsSceneSelectGraphicsTool.getClsid(), 0, -1, true, 1, 1); // SelectGraphics
			tb_ags_option_3D.addItem(ControlsSceneSelectFeaturesTool.getClsid(), 0, -1, false, 1, 1); // SelectFeatures
			tb_ags_option_3D.addItem(ControlsSceneSetObserverTool.getClsid(), 0, -1, false, 1, 1); // SetObserver
			tb_ags_option_3D.addItem(ControlsSceneTargetCenterTool.getClsid(), 0, -1, false, 1, 1); // TargetCenter
			tb_ags_option_3D.addItem(ControlsSceneTargetZoomTool.getClsid(), 0, -1, false, 1, 1); // TargetZoom
		} 
		catch (IOException e) {
			System.out.println("Error: "+e+" caught on init Option Toolbar");
		}
		this.toolBar3.add(this.tb_ags_option_2D);
		this.toolBar3.setPreferredSize(new Dimension(305,30));
		
		/*
		 * Planning ToolBar
		 */		
		//JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		
		this.tb_but_plancov.setFocusable(false);
		this.tb_but_plancov.setToolTipText("Planification en couverture");
		this.tb_but_plancov.setEnabled(false);
		this.toolBar4.add(this.tb_but_plancov);
		
		this.tb_but_plancap.setFocusable(false);
		this.tb_but_plancap.setToolTipText("Planification en capacité");
		this.tb_but_plancap.setEnabled(false);
		this.toolBar4.add(this.tb_but_plancap);
		
//		this.tb_but_planfreq.setFocusable(false);
//		this.tb_but_planfreq.setToolTipText("Planification de fréquence");
//		this.tb_but_planfreq.setEnabled(false);
//		this.toolBar4.add(this.tb_but_planfreq);
		
		this.tb_but_plancost.setFocusable(false);
		this.tb_but_plancost.setToolTipText("Planification Capex/Opex");
		this.tb_but_plancost.setEnabled(false);
		this.toolBar4.add(this.tb_but_plancost);
//		toolBar4.addSeparator(new Dimension(5,25));
		
//		this.tb_but_calpl.setFocusable(false);
//		this.tb_but_calpl.setToolTipText("Calculer Path Loss");
//		this.tb_but_calpl.setEnabled(false);
//		this.toolBar4.add(this.tb_but_calpl);
		
//		this.tb_but_selsite.setFocusable(false);
//		this.tb_but_selsite.setToolTipText("Sélection des sites");
//		this.tb_but_selsite.setEnabled(false);
//		this.toolBar4.add(this.tb_but_selsite);
//		toolBar4.addSeparator(new Dimension(5,25));
		
//		this.tb_but_anainterf.setFocusable(false);
//		this.tb_but_anainterf.setToolTipText("Analyse d'interférence");
//		//this.tb_but_next.setEnabled(false);
//		this.toolBar4.add(this.tb_but_anainterf);
//		
//		this.tb_but_assignfreq.setFocusable(false);
//		this.tb_but_assignfreq.setToolTipText("Assignation des fréquences");
//		//this.tb_but_next.setEnabled(false);
//		this.toolBar4.add(this.tb_but_assignfreq);
//		
//		this.tb_but_genpf.setFocusable(false);
//		this.tb_but_genpf.setToolTipText("Génération d'un plan de fréquence");
//		//this.tb_but_next.setEnabled(false);
//		this.toolBar4.add(this.tb_but_genpf);
		
		this.conToolBar.add(this.toolBar1);
		this.conToolBar.add(this.toolBar2);
		this.conToolBar.add(this.toolBar3);
		this.conToolBar.add(this.toolBar4);
		
		this.conGroupTB.setLayout(new BorderLayout());
		this.conGroupTB.add(conToolBar, BorderLayout.WEST);
		
		this.conPane.add(this.conGroupTB,BorderLayout.NORTH);
	}
	
	/*
	 * Init left Core
	 */
	public void initLeftCore(){		
		//this.tabPane_left_top.setUI(new PlasticTabbedPaneUI());
		
		this.tree_tabLayer = this.buildTreeLayer();	
		this.tabPane_left_top.addTab("Couches", new JScrollPane(this.toc,JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		
		this.tree_tabConfig = this.buildTreeConfig();	
		this.tree_tabConfig.expandRow(1);
		this.tree_tabConfig.expandRow(4);
		this.tree_tabConfig.expandRow(11);
		this.tabPane_left_top.addTab("Configuration", new JScrollPane(tree_tabConfig,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));				
		
		this.split_left = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tabPane_left_top, s_opsimMap);
		this.split_left.setPreferredSize(new Dimension(240, 600));
		this.split_left.setMaximumSize(new Dimension(300, 600));
		this.split_left.setMinimumSize(new Dimension(230, 600));
		this.split_left.setResizeWeight(0.5);
	}
	
	/*
	 * Init right Core
	 */
	public void initRightCore(){		
		this.tree_tabTx = this.buildTreeTx();
		this.tree_tabTx.expandRow(1);
		this.tree_tabTx.expandRow(3);
		this.tree_tabTx.expandRow(5);
		this.tabPane_right_top.addTab("Emetteurs", new JScrollPane(tree_tabTx,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
		this.tree_tabRx = this.buildTreeRx();	
		this.tabPane_right_top.addTab("Recepteurs", new JScrollPane(tree_tabRx,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
		this.tree_tabDGR = this.buildTreeDGR();
		this.tree_tabDGR.expandRow(1);
		this.tree_tabDGR.expandRow(7);
		this.tree_tabDGR.expandRow(10);
		this.tabPane_right_bottom.addTab("Design global réseau", new JScrollPane(tree_tabDGR,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
		this.tree_tabReport = this.buildTreeReport();	
		this.tabPane_right_bottom.addTab("Données", new JScrollPane(tree_tabReport,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));	
		
		this.split_right = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tabPane_right_top, tabPane_right_bottom);
		this.split_right.setPreferredSize(new Dimension(250, 600));
		this.split_right.setMaximumSize(new Dimension(250, 600));
		this.split_right.setMinimumSize(new Dimension(240, 600));
		this.split_right.setResizeWeight(0.5);
	}
	
	/*
	 * Init main Core
	 */
	public void initMainCore(){
		
//		this.tabPane_map.addTab("Affichage 2D", new JScrollPane(this.opsimMap,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
//				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		this.tabPane_map.addTab("Affichage 2D",this.opsimMap);
		this.tabPane_map.addTab("Affichage 3D",this.opsimScene);
		
		
		
		this.tabPane_map.setPreferredSize(new Dimension(1000, 600));
		this.tabPane_map.setMaximumSize(new Dimension(1000, 600));
		this.tabPane_map.setMinimumSize(new Dimension(900, 600));
		
		this.split_center = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		this.split_center.setLeftComponent(this.tabPane_map);
		this.split_center.setRightComponent(this.split_right);
		this.split_center.setResizeWeight(0.92);
		this.split_center.setPreferredSize(new Dimension(1180, 600));
		this.split_center.setMaximumSize(new Dimension(1180, 600));
		this.split_center.setMinimumSize(new Dimension(1100, 600));
		
		this.split_global = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		this.split_global.setLeftComponent(this.split_left);
		this.split_global.setRightComponent(this.split_center);
		
		this.conPane.add(split_global, BorderLayout.CENTER);
	}
	
	/*
	 * Init State Bar
	 */
	public void initStateBar(){
		this.toolBar5.setFloatable(false);
		
		this.lab_state = new JLabel("  Etat:    ");
		this.toolBar5.add(this.lab_state);
		this.bar = new JProgressBar();
		this.bar.setMaximum(100);
		this.bar.setMinimum(0);
		this.bar.setStringPainted(false);
		this.toolBar5.add(this.bar);
		this.tb_tasks.setToolTipText("Voir les détails d'exécution");
		this.tb_tasks.setEnabled(false);
		this.toolBar5.add(this.tb_tasks);
		
		this.lab_taskBar = new JLabel("");
		this.lab_taskBar.setPreferredSize(new Dimension(20,20));
		this.toolBar5.add(this.lab_taskBar);
		this.toolBar5.addSeparator(new Dimension(5,25));
		
		this.lab_x = new JLabel("   X : ");
		this.tf_x = new JTextField("0.0000");
		this.tf_x.setPreferredSize(new Dimension(60,15));
		this.tf_x.setHorizontalAlignment(JTextField.CENTER);
		this.tf_x.setEditable(false);
		this.toolBar5.add(this.lab_x);
		this.toolBar5.add(this.tf_x);
		this.toolBar5.add(this.tf_x);
		this.toolBar5.add(new JLabel(" Meters "));
		this.toolBar5.addSeparator(new Dimension(5,25));
		
		this.lab_y = new JLabel(" Y : ");
		this.tf_y = new JTextField("0.0000");
		this.tf_y.setPreferredSize(new Dimension(60,15));
		this.tf_y.setHorizontalAlignment(JTextField.CENTER);
		this.tf_y.setEditable(false);
		this.toolBar5.add(this.lab_y);
		this.toolBar5.add(this.tf_y);
		this.toolBar5.add(this.tf_y);
		this.toolBar5.add(new JLabel(" Meters "));
		this.toolBar5.addSeparator(new Dimension(5,25));
		
		this.lab_z = new JLabel(" Z : ");
		this.tf_z = new JTextField("0.0000");
		this.tf_z.setPreferredSize(new Dimension(60,15));
		this.tf_z.setHorizontalAlignment(JTextField.CENTER);
		this.tf_z.setEditable(false);
		this.toolBar5.add(this.lab_z);
		this.toolBar5.add(this.tf_z);
		this.toolBar5.add(this.tf_z);
		this.toolBar5.add(new JLabel(" Meters "));
		this.toolBar5.addSeparator(new Dimension(5,25));
		
		this.lab_clut = new JLabel(" Clut : ");
		this.tf_clut = new JTextField("0.0000");
		this.tf_clut.setPreferredSize(new Dimension(60,15));
		this.tf_clut.setHorizontalAlignment(JTextField.CENTER);
		this.tf_clut.setEditable(false);
		this.toolBar5.add(this.lab_clut);
		this.toolBar5.add(this.tf_clut);
		this.toolBar5.add(this.tf_clut);
		this.toolBar5.add(new JLabel("  "));
		this.toolBar5.addSeparator(new Dimension(5,25));
		
		this.lab_rscp = new JLabel(" RSL : ");
		this.tf_rscp = new JTextField("0.0000");
		this.tf_rscp.setPreferredSize(new Dimension(60,15));
		this.tf_rscp.setHorizontalAlignment(JTextField.CENTER);
		this.tf_rscp.setEditable(false);
		this.toolBar5.add(this.lab_rscp);
		this.toolBar5.add(this.tf_rscp);
		this.toolBar5.add(this.tf_rscp);
		this.toolBar5.add(new JLabel(" dBm "));
		this.toolBar5.addSeparator(new Dimension(5,25));
		
//		this.lab_sir = new JLabel(" SIR : ");
//		this.tf_sir = new JTextField("0.0000");
//		this.tf_sir.setPreferredSize(new Dimension(60,15));
//		this.tf_sir.setHorizontalAlignment(JTextField.CENTER);
//		this.tf_sir.setEditable(false);
//		this.toolBar5.add(this.lab_sir);
//		this.toolBar5.add(this.tf_sir);
//		this.toolBar5.add(this.tf_sir);
//		this.toolBar5.add(new JLabel("  "));
//		this.toolBar5.addSeparator(new Dimension(5,25));
		
		this.lab_snir = new JLabel(" SINR : ");
		this.tf_snir = new JTextField("0.0000");
		this.tf_snir.setPreferredSize(new Dimension(60,15));
		this.tf_snir.setHorizontalAlignment(JTextField.CENTER);
		this.tf_snir.setEditable(false);
		this.toolBar5.add(this.lab_snir);
		this.toolBar5.add(this.tf_snir);
		this.toolBar5.add(this.tf_snir);
		this.toolBar5.add(new JLabel(" dBm "));
		
		
		this.conGroup_stateBar.setLayout(new BorderLayout());
		this.conGroup_stateBar.add(this.toolBar5, BorderLayout.WEST);
		this.conPane.add(conGroup_stateBar, BorderLayout.SOUTH);
	}
	
	public CheckboxTree buildTreeLayer(){
		DefaultMutableTreeNode      root = new DefaultMutableTreeNode("Couches");
        DefaultMutableTreeNode      parent;

        parent = new DefaultMutableTreeNode("Affichage 2D");
        root.add(parent);
        parent.add(new DefaultMutableTreeNode("MNE"));
        parent.add(new DefaultMutableTreeNode("Image"));
        parent.add(new DefaultMutableTreeNode("Clutter"));
        parent.add(new DefaultMutableTreeNode("Batiment"));
        parent.add(new DefaultMutableTreeNode("Champ E.M."));
        parent.add(new DefaultMutableTreeNode("RSCP"));
        parent.add(new DefaultMutableTreeNode("SIR"));
        parent.add(new DefaultMutableTreeNode("SNIR"));
        parent.add(new DefaultMutableTreeNode("Site"));

        parent = new DefaultMutableTreeNode("Affichage 3D");
        root.add(parent);
        parent.add(new DefaultMutableTreeNode("MNE sur Image"));
        parent.add(new DefaultMutableTreeNode("MNE sur Couverture"));
        parent.add(new DefaultMutableTreeNode("Couverture Radio"));
        parent.add(new DefaultMutableTreeNode("Clutter sur MNE"));
		
		CheckboxTree checkboxTree = new CheckboxTree(new DefaultTreeModel(root),"treeLayer");		
		checkboxTree.getCheckingModel().setCheckingMode(TreeCheckingModel.CheckingMode.PROPAGATE);
	    checkboxTree.expandAll();
	    
		return checkboxTree;
	}
	
	public void buildTreeExplore(){
		
	}
	
	public JTree buildTreeConfig(){
		DefaultMutableTreeNode      root = new DefaultMutableTreeNode("Paramètres");
        DefaultMutableTreeNode      parent;
        DefaultMutableTreeNode      sub_parent;
        DefaultTreeCellRenderer		tcell = new DefaultTreeCellRenderer();
        JTree						tree;

        parent = new DefaultMutableTreeNode("OPSIM");
        root.add(parent);
        parent.add(new DefaultMutableTreeNode("Generaux"));
        parent.add(new DefaultMutableTreeNode("Projet"));
        
        sub_parent = new DefaultMutableTreeNode("Equipements");
        sub_parent.add(new DefaultMutableTreeNode("Antennes"));
        sub_parent.add(new DefaultMutableTreeNode("Sites"));
        parent.add(sub_parent);
        
        parent.add(new DefaultMutableTreeNode("Zone d'étude"));
        parent.add(new DefaultMutableTreeNode("Modèle de trafic"));
        parent.add(new DefaultMutableTreeNode("Mesures Drive Test"));
        parent.add(new DefaultMutableTreeNode("Couts"));

        parent = new DefaultMutableTreeNode("Utilisateur");
        root.add(parent);
        parent.add(new DefaultMutableTreeNode("Profil"));
        parent.add(new DefaultMutableTreeNode("Modèle de propagation"));
        
        tcell.setOpenIcon(new ImageIcon(Toolkit.getDefaultToolkit().
	  			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/config.png"))));
        tcell.setClosedIcon(new ImageIcon(Toolkit.getDefaultToolkit().
	  			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/config.png"))));
        tree = new JTree(root);
        tree.setCellRenderer(tcell);
        return tree;
	}
	
	public JTree buildTreeDGR(){
		DefaultMutableTreeNode      root = new DefaultMutableTreeNode("Résultats");
        DefaultMutableTreeNode      parent;
        DefaultTreeCellRenderer		tcell = new DefaultTreeCellRenderer();
        JTree						tree;

        parent = new DefaultMutableTreeNode("Planification");
        root.add(parent);
        parent.add(new DefaultMutableTreeNode("Couverture"));
        parent.add(new DefaultMutableTreeNode("Capacité"));
        parent.add(new DefaultMutableTreeNode("Capex/Opex"));
        parent.add(new DefaultMutableTreeNode("Prospective"));

        parent = new DefaultMutableTreeNode("Dimensionnement");
        root.add(parent);
        parent.add(new DefaultMutableTreeNode("RAN"));
        parent.add(new DefaultMutableTreeNode("IMS"));
        
        parent = new DefaultMutableTreeNode("Bilan");
        root.add(parent);
        parent.add(new DefaultMutableTreeNode("P-RAN"));
        parent.add(new DefaultMutableTreeNode("Core Network"));
        
        tcell.setOpenIcon(new ImageIcon(Toolkit.getDefaultToolkit().
	  			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/result.png"))));
        tcell.setClosedIcon(new ImageIcon(Toolkit.getDefaultToolkit().
	  			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/result.png"))));
        tree = new JTree(root);
        tree.setCellRenderer(tcell);
        return tree;
	}
	
	public JTree buildTreeReport(){
		DefaultMutableTreeNode      root = new DefaultMutableTreeNode("Rapports");
        DefaultMutableTreeNode      parent;
        DefaultTreeCellRenderer		tcell = new DefaultTreeCellRenderer();
        JTree						tree;
        
        parent = new DefaultMutableTreeNode("Cartographie");
        root.add(parent);
        parent.add(new DefaultMutableTreeNode("Vue 2D"));
        parent.add(new DefaultMutableTreeNode("Vue 3D"));
        
        parent = new DefaultMutableTreeNode("Planification");
        root.add(parent);
        parent.add(new DefaultMutableTreeNode("Couverture"));
        parent.add(new DefaultMutableTreeNode("Capacité"));
        parent.add(new DefaultMutableTreeNode("Capex/Opex"));
        parent.add(new DefaultMutableTreeNode("Prospective"));

        parent = new DefaultMutableTreeNode("Dimensionnement");
        root.add(parent);
        parent.add(new DefaultMutableTreeNode("RAN"));
        parent.add(new DefaultMutableTreeNode("IMS"));
        
        tcell.setOpenIcon(new ImageIcon(Toolkit.getDefaultToolkit().
	  			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/report.png"))));
        tcell.setClosedIcon(new ImageIcon(Toolkit.getDefaultToolkit().
	  			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/report.png"))));
        tree = new JTree(root);
        tree.setCellRenderer(tcell);
        return tree;
	}
	
	public JTree buildTreeTx(){
		DefaultMutableTreeNode      root = new DefaultMutableTreeNode("Sites");
        DefaultMutableTreeNode      parent;
        DefaultTreeCellRenderer		tcell = new DefaultTreeCellRenderer();
        JTree						tree;

        parent = new DefaultMutableTreeNode("ENode-B");
        root.add(parent);
        parent.add(new DefaultMutableTreeNode("Aucun"));
        
        parent = new DefaultMutableTreeNode("BTS");
        root.add(parent);
        parent.add(new DefaultMutableTreeNode("Aucun"));
        
        parent = new DefaultMutableTreeNode("Station FH");
        root.add(parent);
        parent.add(new DefaultMutableTreeNode("Aucun"));
        
        tcell.setOpenIcon(new ImageIcon(Toolkit.getDefaultToolkit().
	  			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/tower.png"))));
        tcell.setClosedIcon(new ImageIcon(Toolkit.getDefaultToolkit().
	  			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/tower.png"))));
        tree = new JTree(root);
        tree.setCellRenderer(tcell);
        return tree;
	}
	
	public JTree buildTreeRx(){
		DefaultMutableTreeNode      root = new DefaultMutableTreeNode("Utilisateurs");
        DefaultMutableTreeNode      parent;
        DefaultTreeCellRenderer		tcell = new DefaultTreeCellRenderer();
        JTree						tree;

        parent = new DefaultMutableTreeNode("User Equipement");
        root.add(parent);
        parent.add(new DefaultMutableTreeNode("Aucun"));
        
        parent = new DefaultMutableTreeNode("Terminal");
        root.add(parent);
        parent.add(new DefaultMutableTreeNode("Aucun"));
        
        tcell.setOpenIcon(new ImageIcon(Toolkit.getDefaultToolkit().
	  			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/mobile.png"))));
        tcell.setClosedIcon(new ImageIcon(Toolkit.getDefaultToolkit().
	  			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/mobile.png"))));
        tree = new JTree(root);
        tree.setCellRenderer(tcell);
        return tree;
	}
	
	/*
	 * Handle Events of Component
	 */
	public void initEvent(){
		this.mainHE = new MainHE(this);
		this.dimHE = new DimenHE(this);
		this.mapHE = new MapHE(this);
		this.paramHE = new ParameterHE(this);		
		
		this.planFreqHE = new PlanningFreqHE(this);
		this.planCostHE = new PlanningCostHE(this);		
	}
	
	
	
	public String getDefTitle() {
		return defTitle;
	}

	public MapBean getPrevMap() {
		return prevMap;
	}

	public void setPrevMap(MapBean prevMap) {
		this.prevMap = prevMap;
	}

	public Hashtable<String, AbstractController> getListControler() {
		return listControler;
	}

	public void setListControler(Hashtable<String, AbstractController> listControler) {
		this.listControler = listControler;
	}

	public DialogManProject getDialog_manProj() {
		return dialog_manProj;
	}

	public void setDialog_manProj(DialogManProject dialog_manProj) {
		this.dialog_manProj = dialog_manProj;
	}

	public DialogNewProj getDialog_newProj() {
		return dialog_newProj;
	}

	public void setDialog_newProj(DialogNewProj dialog_newProj) {
		this.dialog_newProj = dialog_newProj;
	}

	public DialogPlanCov getDialog_planCov() {
		return dialog_planCov;
	}

	public void setDialog_planCov(DialogPlanCov dialog_planCov) {
		this.dialog_planCov = dialog_planCov;
	}

	public DialogPlanCap getDialog_planCap() {
		return dialog_planCap;
	}

	public void setDialog_planCap(DialogPlanCap dialog_planCap) {
		this.dialog_planCap = dialog_planCap;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SceneBean getOpsimScene() {
		return opsimScene;
	}

	public TOCBean getToc() {
		return toc;
	}

	public JToolBar getToolBar1() {
		return toolBar1;
	}

	public JToolBar getToolBar2() {
		return toolBar2;
	}

	public JToolBar getToolBar3() {
		return toolBar3;
	}

	public JToolBar getToolBar4() {
		return toolBar4;
	}

	public JToolBar getToolBar5() {
		return toolBar5;
	}

	public JTabbedPane getTabPane_left_top() {
		return tabPane_left_top;
	}

	public JTabbedPane getTabPane_left_bottom() {
		return tabPane_left_bottom;
	}

	public JTabbedPane getTabPane_right_top() {
		return tabPane_right_top;
	}

	public JTabbedPane getTabPane_right_bottom() {
		return tabPane_right_bottom;
	}

	public JTabbedPane getTabPane_map() {
		return tabPane_map;
	}

	public ToolbarBean getTb_ags_standard() {
		return tb_ags_standard;
	}

	public ToolbarBean getTb_ags_option_2D() {
		return tb_ags_option_2D;
	}

	public ToolbarBean getTb_ags_option_3D() {
		return tb_ags_option_3D;
	}

	public ToolbarBean getTb_ags_3danalyst() {
		return tb_ags_3danalyst;
	}

	public MapBean getOpsimMap() {
		return opsimMap;
	}

	public MapBean getS_opsimMap() {
		return s_opsimMap;
	}

	public JMenu getMen_File() {
		return men_File;
	}

	public JMenu getMen_Edit() {
		return men_Edit;
	}
	
	public JMenu getMen_Planning() {
		return men_Planning;
	}

	public JMenu getMen_Map() {
		return men_Map;
	}

	public JMenu getMen_Run() {
		return men_Run;
	}

	public JMenu getMen_Tool() {
		return men_Tool;
	}

	public JMenu getMen_Window() {
		return men_Window;
	}

	public JMenu getMen_Option() {
		return men_Option;
	}

	public JMenu getMen_Help() {
		return men_Help;
	}

	public JMenu getMen_Import() {
		return men_Import;
	}

	public JMenu getMen_Export() {
		return men_Export;
	}

	public JMenu getMen_Print() {
		return men_Print;
	}

	public JMenu getMen_PlanFreq() {
		return men_PlanFreq;
	}

	public JMenu getMen_Display3D() {
		return men_Display3D;
	}

	public JMenu getMen_Display2D() {
		return men_Display2D;
	}

	public JMenu getMen_PrefEquip() {
		return men_PrefEquip;
	}

	public JMenuItem getMenIt_ManProject() {
		return menIt_ManProject;
	}

	public JMenuItem getMenIt_AchProject() {
		return menIt_AchProject;
	}

	public JMenuItem getMenIt_ReloadProject() {
		return menIt_ReloadProject;
	}

	public JMenuItem getMenIt_Save() {
		return menIt_Save;
	}

	public JMenuItem getMenIt_SaveAs() {
		return menIt_SaveAs;
	}

	public JMenuItem getMenIt_Import_OnMap() {
		return menIt_Import_OnMap;
	}

	public JMenuItem getMenIt_Import_OnCover() {
		return menIt_Import_OnCover;
	}

	public JMenuItem getMenIt_Import_ListSite() {
		return menIt_Import_ListSite;
	}

	public JMenuItem getMenIt_Export_Map() {
		return menIt_Export_Map;
	}

	public JMenuItem getMenIt_Export_Cover() {
		return menIt_Export_Cover;
	}

	public JMenuItem getMenIt_Export_Planif() {
		return menIt_Export_Planif;
	}

	public JMenuItem getMenIt_Publish() {
		return menIt_Publish;
	}

	public JMenuItem getMenIt_Print_Report() {
		return menIt_Print_Report;
	}

	public JMenuItem getMenIt_Print_Map() {
		return menIt_Print_Map;
	}

	public JMenuItem getMenIt_Print_Cover() {
		return menIt_Print_Cover;
	}

	public JMenuItem getMenIt_Print_Site() {
		return menIt_Print_Site;
	}

	public JMenuItem getMenIt_Close() {
		return menIt_Close;
	}

	public JMenuItem getMenIt_Pref() {
		return menIt_Pref;
	}

	public JMenuItem getMenIt_Exit() {
		return menIt_Exit;
	}

	public JMenuItem getMenIt_Undo() {
		return menIt_Undo;
	}

	public JMenuItem getMenIt_Redo() {
		return menIt_Redo;
	}

	public JMenuItem getMenIt_Cut() {
		return menIt_Cut;
	}

	public JMenuItem getMenIt_Copy() {
		return menIt_Copy;
	}

	public JMenuItem getMenIt_Paste() {
		return menIt_Paste;
	}

	public JMenuItem getMenIt_Delete() {
		return menIt_Delete;
	}

	public JMenuItem getMenIt_Search() {
		return menIt_Search;
	}

	public JMenuItem getMenIt_PlanCover() {
		return menIt_PlanCover;
	}

	public JMenuItem getMenIt_PlanCap() {
		return menIt_PlanCap;
	}


	public JMenuItem getMenIt_PlanFreq_AssF() {
		return menIt_PlanFreq_AssF;
	}

	public JMenuItem getMenIt_PlanCost() {
		return menIt_PlanCost;
	}

	public JMenuItem getMenIt_PlanPros() {
		return menIt_PlanPros;
	}

	public JMenuItem getMenIt_Display3D_MneOnImg() {
		return menIt_Display3D_MneOnImg;
	}

	public JMenuItem getMenIt_Display3D_MneOnCov() {
		return menIt_Display3D_MneOnCov;
	}

	public JMenuItem getMenIt_Display3D_ClutOnMne() {
		return menIt_Display3D_ClutOnMne;
	}


	public JMenuItem getMenIt_Display2D_Img() {
		return menIt_Display2D_Img;
	}

	public JMenuItem getMenIt_Display2D_Clut() {
		return menIt_Display2D_Clut;
	}

	public JMenuItem getMenIt_Display2D_MNS() {
		return menIt_Display2D_MNS;
	}

	public JMenuItem getMenIt_Display2D_Field() {
		return menIt_Display2D_Field;
	}

	public JMenuItem getMenIt_Display2D_Power() {
		return menIt_Display2D_Power;
	}

	public JMenuItem getMenIt_Display2D_SIR() {
		return menIt_Display2D_SIR;
	}

	public JMenuItem getMenIt_Display2D_SINR() {
		return menIt_Display2D_SINR;
	}

	public JMenuItem getMenIt_Display2D_Site() {
		return menIt_Display2D_Site;
	}

	public JMenuItem getMenIt_PatColor() {
		return menIt_PatColor;
	}

	public JMenuItem getMenIt_NewWindow() {
		return menIt_NewWindow;
	}

	public JMenuItem getMenIt_NewMap() {
		return menIt_NewMap;
	}

	public JMenuItem getMenIt_CalPL() {
		return menIt_CalPL;
	}

	public JMenuItem getMenIt_SelSite() {
		return menIt_SelSite;
	}

	public JMenuItem getMenIt_CalCovTx() {
		return menIt_CalCovTx;
	}

	public JMenuItem getMenIt_DrawProfil() {
		return menIt_DrawProfil;
	}

	public JMenuItem getMenIt_AnaInterf() {
		return menIt_AnaInterf;
	}

	public JMenuItem getMenIt_AssignFreq() {
		return menIt_AssignFreq;
	}

	public JMenuItem getMenIt_generatePF() {
		return menIt_generatePF;
	}

	public JMenuItem getMenIt_CalMP() {
		return menIt_CalMP;
	}

	public JMenuItem getMenIt_Dimenensionning() {
		return menIt_Dimenensionning;
	}

	public JMenuItem getMenIt_PrefProject() {
		return menIt_PrefProject;
	}

	public JMenuItem getMenIt_PrefGen() {
		return menIt_PrefGen;
	}

	public JMenuItem getMenIt_PrefEquip_ENB() {
		return menIt_PrefEquip_ENB;
	}

	public JMenuItem getMenIt_PrefTargetZone() {
		return menIt_PrefTargetZone;
	}

	public JMenuItem getMenIt_PrefModelTraf() {
		return menIt_PrefModelTraf;
	}

	public JMenuItem getMenIt_PrefDriveTest() {
		return menIt_PrefDriveTest;
	}

	public JMenuItem getMenIt_PrefCost() {
		return menIt_PrefCost;
	}

	public JMenuItem getMenIt_OpsimHelp() {
		return menIt_OpsimHelp;
	}

	public JMenuItem getMenIt_OpsimAbout() {
		return menIt_OpsimAbout;
	}

	public JMenuItem getMenIt_OpsimTeam() {
		return menIt_OpsimTeam;
	}

	public JButton getTb_but_manProj() {
		return tb_but_manProj;
	}

	public JButton getTb_but_save() {
		return tb_but_save;
	}
	
	public JButton getTb_but_plancov() {
		return tb_but_plancov;
	}

	public JButton getTb_but_plancap() {
		return tb_but_plancap;
	}

	public JButton getTb_but_planfreq() {
		return tb_but_planfreq;
	}

	public JButton getTb_but_plancost() {
		return tb_but_plancost;
	}

	public JButton getTb_but_calpl() {
		return tb_but_calpl;
	}

	public JButton getTb_but_selsite() {
		return tb_but_selsite;
	}

	public JButton getTb_but_anainterf() {
		return tb_but_anainterf;
	}

	public JButton getTb_but_assignfreq() {
		return tb_but_assignfreq;
	}

	public JButton getTb_but_genpf() {
		return tb_but_genpf;
	}

	public CheckboxTree getTree_tabLayer() {
		return tree_tabLayer;
	}

	public JTree getTree_tabExplore() {
		return tree_tabExplore;
	}

	public JTree getTree_tabConfig() {
		return tree_tabConfig;
	}

	public JTree getTree_tabDGR() {
		return tree_tabDGR;
	}

	public JTree getTree_tabReport() {
		return tree_tabReport;
	}

	public JTree getTree_tabTx() {
		return tree_tabTx;
	}

	public JTree getTree_tabRx() {
		return tree_tabRx;
	}

	public JTextField getTf_state() {
		return tf_state;
	}

	public JTextField getTf_x() {
		return tf_x;
	}

	public JTextField getTf_y() {
		return tf_y;
	}

	public JTextField getTf_z() {
		return tf_z;
	}

	public JTextField getTf_clut() {
		return tf_clut;
	}

	public JTextField getTf_rscp() {
		return tf_rscp;
	}

	public JTextField getTf_sir() {
		return tf_sir;
	}

	public JTextField getTf_snir() {
		return tf_snir;
	}

	public void setMenIt_GeoProcessing(JMenuItem menIt_GeoProcessing) {
		this.menIt_GeoProcessing = menIt_GeoProcessing;
	}
	
	public JProgressBar getBar() {
		return bar;
	}
	
	public JButton getTb_tasks() {
		return tb_tasks;
	}
	
	public ToolbarMenu getTbm_toc() {
		return tbm_toc;
	}
	

	public DialogPlanCost getDialog_planCost() {
		return dialog_planCost;
	}

	public void setDialog_planCost(DialogPlanCost dialog_planCost) {
		this.dialog_planCost = dialog_planCost;
	}	

	public JMenuItem getMenIt_Display3D_MneOnSinr() {
		return menIt_Display3D_MneOnSinr;
	}

	public JMenuItem getMenIt_Display3D_MneOnMns() {
		return menIt_Display3D_MneOnMns;
	}

	public JMenuItem getMenIt_Display3D_MneOnSite() {
		return menIt_Display3D_MneOnSite;
	}
	
	public JMenuItem getMenIt_Display3D_MneOnCE() {
		return menIt_Display3D_MneOnCE;
	}
	
	public JMenuItem getMenIt_Gen3DModel_Basic() {
		return menIt_Gen3DModel_Basic;
	}

	public JMenuItem getMenIt_Gen3DModel_CE() {
		return menIt_Gen3DModel_CE;
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable observ) {
		System.out.println("OpsimGUI: on Observer >> update ");
		ProjectController projConWS = (ProjectController) this.listControler.get("ProjectController");
		Project projWS = (Project) projConWS.getModel();
		ArrayList<Map> mapList = (ArrayList<Map>) projWS.getMap();
		Hashtable<String,Layer> layer2DList = mapList.get(0).getLayer();
		Hashtable<String,Layer> layer3DList = mapList.get(1).getLayer();
		Layer layMXD = layer2DList.get("MXD");
		Layer laySXD = layer3DList.get("SXD");
		try {
			String opsim_home = System.getenv("OPSIM_HOME");
			
			// Loading MXD Document
			if(layer2DList.containsKey("MXD")){
				this.opsimMap.loadMxFile(layMXD.getDataSource(), null, null);
				this.opsimMap.setExtent(this.opsimMap.getFullExtent()); 
				this.opsimMap.refresh(esriViewDrawPhase.esriViewForeground, null, null);
				this.tb_but_save.setEnabled(true);
			}
			else {
			    String blankMxd_path = opsim_home;
			    blankMxd_path = blankMxd_path+File.separator+"/files/blank.mxd";
				this.opsimMap.loadMxFile(blankMxd_path, null, null);
				this.opsimMap.refresh(esriViewDrawPhase.esriViewForeground, null, null);
				//this.tb_but_save.setEnabled(false);
			}
			
			// Loading SXD Document
			if(layer3DList.containsKey("SXD")){
				this.opsimScene.loadSxFile(laySXD.getDataSource());
				this.tb_but_save.setEnabled(true);
			}
			else {
			    String blankSxd_path = opsim_home;
			    blankSxd_path = blankSxd_path+File.separator+"/files/blank.sxd";
				this.opsimScene.loadSxFile(blankSxd_path);
				//this.tb_but_save.setEnabled(false);
			}	
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: "+e+" caught update OpsimGUI");
			JOptionPane.showMessageDialog(this, "Erreur de chargement des contenus du projet.\n" +
					"Details: "+e, "Erreur",JOptionPane.ERROR_MESSAGE);
		}		
		
		Thread t = (new Thread(new Runnable(){
			public void run(){
				bar.setIndeterminate(true);
				ProjectController projConWS = (ProjectController) listControler.get("ProjectController");
				Project projWS = (Project) projConWS.getModel();
				createProjectFC(projWS);
				enableMenuProj(projWS);
				loadProjLayers(projWS);
				
				final ArrayList<Site> list_site = createListSite(projWS);
				projWS.setList_site(list_site);
				projConWS.setModel(projWS);
				getListControler().remove("ProjectController");
				getListControler().put("ProjectController", projConWS);
				
				final int n = list_site.size();
				if(n != 0){
					JTree treeTx = changeTreeTx(list_site);					
					tree_tabTx.setModel(treeTx.getModel());
					tree_tabTx.expandRow(1);
					tree_tabTx.expandRow(n+2);
					tree_tabTx.expandRow(n+4);
					
					TreeSelectionListener[] listeners = tree_tabTx.getTreeSelectionListeners();
					for(int i=0; i<listeners.length;i++){
						tree_tabTx.removeTreeSelectionListener(listeners[i]);
					}
					
					tree_tabTx.addTreeSelectionListener(new TreeSelectionListener(){
						public void valueChanged(TreeSelectionEvent event) {				
							if(tree_tabTx.getLastSelectedPathComponent() != null){
								String sel_node = tree_tabTx.getLastSelectedPathComponent().toString();
								for(int i=0; i<n; i++){
									Site site = list_site.get(i);
									String nodeName = "ENB-"+site.getId()+
											"["+round(site.getX(),3)+
											","+round(site.getY(),3)+"]";
									if(nodeName.equals(sel_node))
										JOptionPane.showMessageDialog(This, "l'Enode-B presente les données suivantes: \n" +
												"ID site: "+site.getPoint_id()+"\n" +
												"Nom site: E-NodeB-"+site.getId()+"\n" +
												"Coordonnées géographiques [X,Y]: ["+round(site.getX(),3)+
														","+round(site.getY(),3)+"]\n" +
												"Hauteur d'élévation (MNE): "+site.getH_dem()+" Mètres \n" +
												"Hauteur effective réelle: "+site.getH_eff_r()+" Mètres\n" +
												"ID du batiment (MNS): "+site.getSurface_id()+"\n" +
												"Hauteur du batiment (MNS): "+site.getSurface_h()+" Mètres\n" +
												"Hauteur du site sur le MNS: "+site.getSurf_eff()+" Mètres\n",
												"OPSIM: Informations détaillées",
												JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					});			
					
				}
				else if(n == 0){
					JTree treeTx = buildTreeTx();					
					tree_tabTx.setModel(treeTx.getModel());
					tree_tabTx.expandRow(1);
					tree_tabTx.expandRow(3);
					tree_tabTx.expandRow(5);
					TreeSelectionListener[] listeners = tree_tabTx.getTreeSelectionListeners();
					for(int i=0; i<listeners.length;i++){
						tree_tabTx.removeTreeSelectionListener(listeners[i]);
					}
				}				
				bar.setIndeterminate(false);				
			}
		}));		
		t.start();		
	}
	
	public void enableMenuProj(Project projWS){
		ArrayList<Map> map_List = (ArrayList<Map>) projWS.getMap();
		Hashtable<String,Layer> layer2D_list = map_List.get(0).getLayer();
		Hashtable<String,Layer> layer3D_list = map_List.get(1).getLayer();
		
		this.menIt_Exit.setEnabled(true);
//		this.menIt_AchProject.setEnabled(true);
//		this.menIt_ReloadProject.setEnabled(true);
//		this.menIt_Save.setEnabled(true);
//		this.menIt_SaveAs.setEnabled(true);
//		this.men_Import.setEnabled(true);
		this.men_Export.setEnabled(true);
		this.menIt_Publish.setEnabled(true);
//		this.men_Print.setEnabled(true);
//		
//		this.menIt_Undo.setEnabled(true);
//		this.menIt_Redo.setEnabled(true);
//		this.menIt_Cut.setEnabled(true);
//		this.menIt_Copy.setEnabled(true);
//		this.menIt_Paste.setEnabled(true);
//		this.menIt_Delete.setEnabled(true);
//		this.menIt_Search.setEnabled(true);
		
		this.men_Display2D.setEnabled(true);
		this.menIt_Display2D_Field.setEnabled(false);
		this.menIt_Display2D_Power.setEnabled(false);
		this.menIt_Display2D_SIR.setEnabled(false);
		this.menIt_Display2D_SINR.setEnabled(false);
		this.menIt_Display2D_Site.setEnabled(false);		
		this.men_Display3D.setEnabled(true);		
		
		//this.menIt_CalPL.setEnabled(true);
		//this.menIt_DrawProfil.setEnabled(true);
		this.menIt_SelSite.setEnabled(true);
		if(layer2D_list.containsKey("TX")){
			this.menIt_CalCovTx.setEnabled(true);
			this.menIt_Display2D_Site.setEnabled(true);
			this.menIt_Display3D_MneOnSite.setEnabled(true);
		}
		if(layer2D_list.containsKey("COV")){
			this.menIt_AnaInterf.setEnabled(true);
			this.tb_but_calpl.setEnabled(true);
			this.menIt_Display2D_Power.setEnabled(true);
			this.menIt_Display3D_MneOnCov.setEnabled(true);
		}
		if(layer2D_list.containsKey("SINR")){
			this.menIt_Display2D_SINR.setEnabled(true);
			this.menIt_Display3D_MneOnSinr.setEnabled(true);
		}
		if(layer2D_list.containsKey("MNS")){
			this.menIt_Display2D_MNS.setEnabled(true);
			this.menIt_Display3D_MneOnMns.setEnabled(true);
			this.menIt_Gen3DModel_Basic.setEnabled(true);
//			this.menIt_Gen3DModel_CE.setEnabled(false);
			this.men_Gen3DModel.setEnabled(true);
		}
		if(layer2D_list.containsKey("CLUT")){
			this.menIt_Display2D_Clut.setEnabled(true);
			this.menIt_Display3D_ClutOnMne.setEnabled(true);
		}
		if(layer2D_list.containsKey("IMG")){
			this.menIt_Display2D_Img.setEnabled(true);
			this.menIt_Display3D_MneOnImg.setEnabled(true);
		}
		if(layer3D_list.containsKey("CE")){
			this.menIt_Display3D_MneOnCE.setEnabled(true);
		}
		
		//this.menIt_AssignFreq.setEnabled(true);
		//this.menIt_generatePF.setEnabled(true);
		
		this.tb_but_plancov.setEnabled(true);
		this.tb_but_plancap.setEnabled(true);
		this.tb_but_planfreq.setEnabled(true);
		this.tb_but_plancost.setEnabled(true);		
		this.tb_but_selsite.setEnabled(true);
		
		this.menIt_PlanCover.setEnabled(true);
		this.menIt_PlanCap.setEnabled(true);
		this.menIt_PlanCost.setEnabled(true);
		this.men_PlanFreq.setEnabled(true);
		
		this.menIt_Close.setEnabled(true);
	}
	
	public void loadProjLayers(Project projWS){
		ArrayList<Map> map_List = (ArrayList<Map>) projWS.getMap();
		Hashtable<String,Layer> layer2D_list = map_List.get(0).getLayer();
		Hashtable<String,Layer> layer3D_list = map_List.get(1).getLayer();
		ArrayList<String> ds_data = new ArrayList<String>();
		
		try {			
			if(layer2D_list.containsKey("IMG")){
				ds_data = this.calPathFile(layer2D_list.get("IMG").getDataSource());
				this.load2DLayers(ds_data, opsimMap, s_opsimMap, "IMG");
				this.menIt_Display2D_Img.setEnabled(true);
				this.menIt_Display2D_Img.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().
						getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/check.png"))));
				
				if(layer3D_list.containsKey("MNE")){
					ArrayList<String> data = this.calPathFile(layer3D_list.get("MNE").getDataSource());
					ds_data.add(data.get(0));
					ds_data.add(data.get(1));
					this.load3DLayers(ds_data, opsimScene, "IMG_MNE");
					this.menIt_Display3D_MneOnImg.setEnabled(true);
					this.menIt_Display3D_MneOnImg.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().
							getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/check.png"))));
				}
				else {
					this.load3DLayers(ds_data, opsimScene, "IMG");
				}
			}
			
			if(layer2D_list.containsKey("CLUT")){
				ds_data = this.calPathFile(layer2D_list.get("CLUT").getDataSource());
				this.load2DLayers(ds_data, opsimMap, s_opsimMap, "CLUT");
				this.load3DLayers(ds_data, opsimScene, "CLUT");
				this.menIt_Display2D_Clut.setEnabled(true);
				this.menIt_Display2D_Clut.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().
						getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/check.png"))));
				if(layer3D_list.containsKey("MNE")){
					this.menIt_Display3D_ClutOnMne.setEnabled(true);
					this.menIt_Display3D_ClutOnMne.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().
							getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/check.png"))));
				}
			}
			
//			if(layer2D_list.containsKey("MNT")){
//				ds_data = this.calPathFile(layer2D_list.get("MNT").getDataSource());
//				this.load2DLayers(ds_data, opsimMap, s_opsimMap, "MNT");
//				this.load3DLayers(ds_data, opsimScene, "MNT");
//			}
			
			if(layer2D_list.containsKey("MNS")){
				ds_data = this.calPathFile(layer2D_list.get("MNS").getDataSource());
				this.load2DLayers(ds_data, opsimMap, s_opsimMap, "MNS");
				this.menIt_Display2D_MNS.setEnabled(true);
				this.menIt_Display2D_MNS.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().
						getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/check.png"))));
				if(!layer3D_list.containsKey("CE")){
					this.load3DLayers(ds_data, opsimScene, "MNS");
					this.menIt_Display3D_MneOnMns.setEnabled(true);
					this.menIt_Display3D_MneOnMns.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().
							getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/check.png"))));
				}	
				
			}					
			
			if(layer2D_list.containsKey("COV")){
				ds_data = this.calPathFile(layer2D_list.get("COV").getDataSource());
				this.load2DLayers(ds_data, opsimMap, s_opsimMap, "COV");
				this.load3DLayers(ds_data, opsimScene, "COV");
				this.menIt_Display2D_Power.setEnabled(true);
				this.menIt_Display2D_Power.setIcon(null);
				if(layer3D_list.containsKey("MNE")){
					this.menIt_Display3D_MneOnCov.setEnabled(true);
					this.menIt_Display3D_MneOnCov.setIcon(null);
				}
			}
			
			if(layer2D_list.containsKey("SINR")){
				ds_data = this.calPathFile(layer2D_list.get("SINR").getDataSource());
				this.load2DLayers(ds_data, opsimMap, s_opsimMap, "SINR");
				this.load3DLayers(ds_data, opsimScene, "SINR");
				this.menIt_Display2D_SINR.setEnabled(true);
				this.menIt_Display2D_SINR.setIcon(null);
				this.menIt_Display3D_MneOnSinr.setEnabled(true);
				this.menIt_Display3D_MneOnSinr.setIcon(null);
			}
			
			if(layer3D_list.containsKey("CE")){
				ds_data = this.calPathFile(layer3D_list.get("CE").getDataSource());
				this.load3DLayers(ds_data, opsimScene, "CE");
				this.menIt_Display3D_MneOnCE.setEnabled(true);
				this.menIt_Display3D_MneOnCE.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().
						getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/check.png"))));
			}
			
			if(layer2D_list.containsKey("TX")){
				ds_data = this.calPathFile(layer2D_list.get("TX").getDataSource());
				this.load2DLayers(ds_data, opsimMap, s_opsimMap, "TX");
				this.load3DLayers(ds_data, opsimScene, "TX");
				this.menIt_Display2D_Site.setEnabled(true);
				this.menIt_Display2D_Site.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().
						getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/check.png"))));
				this.menIt_Display3D_MneOnSite.setEnabled(true);
				this.menIt_Display3D_MneOnSite.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().
						getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/check.png"))));
			}
			// refresh map view
			//this.opsimMap.refresh(esriViewDrawPhase.esriViewForeground, null, null);
		} 
		catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Erreur lors du chargement d'une des couches du projet.\n" +
											   "Veuillez verifier que les fichiers liés à la couche sont" +
											   " existants et non bloqués.",
					"Erreur",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void load3DLayers(ArrayList<String> ds_data, SceneBean opsimScene, String nameLayer) throws Exception{
		ShapefileWorkspaceFactory sf_wsf = new ShapefileWorkspaceFactory();
		Workspace sf_ws = (Workspace) sf_wsf.openFromFile(ds_data.get(0), 0);	
		sf_ws.startEditing(false);
		sf_ws.startEditOperation();
		
		IFeatureLayer featureLayer = new FeatureLayer();
		RasterLayer rLayer_IMG = new RasterLayer();
		RasterLayer rLayer_MNE = new RasterLayer();
		IFeatureClass fc_layer = null;
		
		System.out.println("layer3D: loading "+nameLayer+"...");
		
		if(nameLayer == "MNS"){
			fc_layer = sf_ws.openFeatureClass(ds_data.get(1));
//			IFeatureClass fc_mp_dhm = this.buildMultiPathFC(fc_layer);
//			featureLayer.setFeatureClassByRef(fc_mp_dhm);
			featureLayer.setFeatureClassByRef(fc_layer);
		}
		else if(nameLayer == "CE"){
			IFeatureClass fc_ce3DModel = this.loadCEModel(ds_data.get(0)+File.separator+ds_data.get(1));
			featureLayer.setFeatureClassByRef(fc_ce3DModel);
		}
		else if(nameLayer == "IMG"){
			rLayer_IMG.createFromFilePath(ds_data.get(0)+File.separator+ds_data.get(1));			
		}
		else if(nameLayer == "IMG_MNE"){
			rLayer_IMG.createFromFilePath(ds_data.get(0)+File.separator+ds_data.get(1));
			rLayer_MNE.createFromFilePath(ds_data.get(2)+File.separator+ds_data.get(3));
		}
		else {
			fc_layer = sf_ws.openFeatureClass(ds_data.get(1));
			featureLayer.setFeatureClassByRef(fc_layer);
		}
		
		sf_ws.stopEditOperation();
		sf_ws.stopEditing(true);
		sf_ws = null;	
		
		ILayer layer = null;
		
		// customize renderer layers
		switch(nameLayer){
			case "MNT":
				layer = this.loadRendereredLayer(featureLayer, nameLayer, "3D");
				layer.setVisible(false);
				break;
			case "MNS":
				layer = this.loadRendereredLayer(featureLayer, nameLayer, "3D");				
				break;
			case "CLUT":
				layer = this.loadRendereredLayer(featureLayer, nameLayer, "3D");
//				layer.setVisible(false);
				break;
			case "TX":
				layer = this.loadRendereredLayer(featureLayer, nameLayer, "3D");
				break;
			case "COV":
				layer = this.loadRendereredLayer(featureLayer, nameLayer, "3D");
				layer.setVisible(false);
				break;
			case "SINR":
				layer = this.loadRendereredLayer(featureLayer, nameLayer, "3D");
				layer.setVisible(false);
				break;
			case "CE":
				layer = this.loadRendereredLayer(featureLayer, nameLayer, "3D");
				layer.setVisible(false);
				break;
			case "IMG":		
				layer = (ILayer)rLayer_IMG;
				break;
			case "IMG_MNE":		
				layer = this.loadRendereredIMG(rLayer_IMG, rLayer_MNE);
				//layer = (ILayer)rasterLayer;
				break;
			default:
				break;
		}
		
		IScene scene = opsimScene.getScene();
		scene.addLayer(layer, false);
		System.out.println("layer3D: "+layer.getName().toString()+" loaded");
		
		opsimScene.setSceneGraphByRef(scene.getSceneGraph());		
	}
	
	private void load2DLayers(ArrayList<String> ds_data, MapBean opsimMap, MapBean s_opsimMap, String nameLayer) throws Exception{
		ShapefileWorkspaceFactory sf_wsf = new ShapefileWorkspaceFactory();
		RasterWorkspaceFactory ras_wsf = new RasterWorkspaceFactory();
		Workspace sf_ws = (Workspace) sf_wsf.openFromFile(ds_data.get(0), 0);	
		sf_ws.startEditing(false);
		sf_ws.startEditOperation();
		
		System.out.println("layer2D: loading "+nameLayer+"...");
		ILayer layer = null;
		if(nameLayer == "IMG"){
			IRasterLayer rasterLayer = new RasterLayer();
			rasterLayer.createFromFilePath(ds_data.get(0)+File.separator+ds_data.get(1));
			layer = (ILayer)rasterLayer;
		}
		else{
			IFeatureLayer featureLayer = new FeatureLayer();
			featureLayer.setFeatureClassByRef(sf_ws.openFeatureClass(ds_data.get(1)));
			layer = this.loadRendereredLayer(featureLayer, nameLayer, "2D");
			if(nameLayer == "COV" || nameLayer == "SINR")layer.setVisible(false);
		}
		
		sf_ws.stopEditOperation();
		sf_ws.stopEditing(true);
		sf_ws = null;		
		
		System.out.println("layer2D: "+layer.getName().toString()+" loaded");
		opsimMap.addLayer(layer,0);
		s_opsimMap.addLayer(layer,0);
	}
	
	private ILayer loadRendereredIMG(RasterLayer rLayer_IMG, RasterLayer rLayer_MNE) throws Exception{	
		ILayer layer = null;
		I3DProperties p3DProp = Get3DPropsFromLayer(rLayer_MNE);
		RasterSurface pSurf = null;
		IRasterBandCollection pBands = null;
		ArrayList<String> ds_MNE = this.calPathFile(rLayer_MNE.getFilePath());
		
		System.out.println("Loading Renderer IMG: start");
		System.out.println("Raster IMG: "+rLayer_IMG.getName());
		System.out.println("Raster MNE: "+rLayer_MNE.getName());
		
		IWorkspaceFactory workspaceFactory = new RasterWorkspaceFactory();
		IRasterWorkspace rasterWorkspace = new IRasterWorkspaceProxy
		        (workspaceFactory.openFromFile(ds_MNE.get(0), 0));
		IRasterDataset rDataset_MNE = rasterWorkspace.openRasterDataset(ds_MNE.get(1));
		
		// Research Surface MNE
		//pSurf = (RasterSurface) p3DProp.getBaseSurface();
		if(pSurf == null){
			pSurf = new RasterSurface();
			pBands = (IRasterBandCollection) rDataset_MNE;
			pSurf.setRasterBand(pBands.item(0));
		}
		ISurface surfElev = pSurf;
		
		// Apply Surface to IMG 
		p3DProp = Get3DPropsFromLayer(rLayer_IMG);
		if(p3DProp ==  null) p3DProp = new Raster3DProperties();
		p3DProp.setBaseSurfaceByRef(surfElev);
		p3DProp.setBaseOption(esriBaseOption.esriBaseSurface);
		
		rLayer_IMG.addExtension(p3DProp);
		layer = (ILayer)rLayer_IMG;
		System.out.println("Loading Renderer IMG: stop");
		
		return layer;
	}
	
	private I3DProperties Get3DPropsFromLayer(ILayer layer){
		I3DProperties p3DProp = null;
		ILayerExtensions pLayerExts = null;
		pLayerExts = (ILayerExtensions) layer;
		
		try {
			for(int i=0; i<pLayerExts.getExtensionCount(); i++){		
				if((I3DProperties) pLayerExts.getExtension(i) != null)
					p3DProp = (I3DProperties) pLayerExts.getExtension(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p3DProp;
	}
	
	public ILayer loadRendereredLayer(IFeatureLayer featureLayer, String nameLayer, String typeLayer) throws Exception{	
		
		ILayer layer = (ILayer)featureLayer;
		layer.setName(((FeatureLayer) featureLayer).getFeatureClass().getAliasName());
		//System.out.println("layer load: "+layer.getName().toString());		
		IGeoFeatureLayer geoLayer = (IGeoFeatureLayer) layer;
		
		if(nameLayer == "TX"){
			if(typeLayer == "3D"){
				String runtimeHome = System.getenv("AGSENGINEJAVA");				
				if(runtimeHome == null){
					runtimeHome = System.getenv("AGSDESKTOPJAVA");
				}
				String stylePath = runtimeHome + "Styles" + File.separator + "3D Industrial.ServerStyle";
				
//				String opsim_home = System.getenv("OPSIM_HOME");
//				String stylePath = opsim_home+File.separator+"styles"+File.separator+"3D Industrial.ServerStyle";
				
				File styleFile = new File(stylePath);
		        //Test to make sure style file is presente
				if(!styleFile.exists()){
		        	System.err.println("The ESRI.ServerStyle was not found in the following location: " +
		                                                styleFile.getParent());
		            System.err.println("Verify that ESRI.ServerStyle can be located in the specified folder.");
		            System.err.println("If not present, try uninstalling your ArcGIS software and reinstalling it.");
		            System.err.println("Exiting execution of this sample...");
		            JOptionPane.showMessageDialog(null,"Une erreur s'est produite lors du chargement du style.\n" +
		            		"Verifier que le style <<3D Industrial.ServerStyle>> se trouve\n" +
		            		"à l'emplacement spécifié par ArcGIS.\n" +
		            		"S'il n'est pas disponible, essayer de reinstaller ArcGIS.",
							"OPSIM: Chargement du syles",JOptionPane.ERROR_MESSAGE);
		        }
				
				System.out.println("stylePath: "+stylePath);
				ISimpleRenderer simpleRenderer = new SimpleRenderer();				
				IStyleGallery styleGallery = new ServerStyleGallery(); 
				IStyleGalleryStorage styleStorage = (IStyleGalleryStorage) styleGallery;
//				styleStorage.setTargetFile(stylePath);
				styleStorage.addFile(stylePath);
				IEnumStyleGalleryItem enumStyleGalleryItem = styleGallery.getItems("Marker Symbols", stylePath, "");
				enumStyleGalleryItem.reset();
				
				IStyleGalleryItem styleItem = enumStyleGalleryItem.next();
				while(styleItem != null){
					if("Derrick 1".equals(styleItem.getName())){
						System.out.println("Style: Found");
						break;
					}
					else
						styleItem = enumStyleGalleryItem.next();
				}
				ISymbol pSymbol =  (ISymbol) styleItem.getItem();
								
				simpleRenderer.setSymbolByRef(pSymbol);					
				((IGeoFeatureLayer) layer).setRendererByRef((IFeatureRenderer) simpleRenderer);		
				
				Cleaner.release(pSymbol);
				Cleaner.release(styleItem);
				Cleaner.release(enumStyleGalleryItem);
			}
		}
		else if(nameLayer == "COV" || nameLayer == "SINR"){
			String nameFld = "";
			if(nameLayer == "COV")nameFld = "OPSIM_RSL";
			else if(nameLayer == "SINR")nameFld = "SINR";
			
			IField field = (featureLayer).getFeatureClass().getFields().getField(
					(featureLayer).getFeatureClass().findField(nameFld));
			// Get a feature cursor
			ICursor cursor = new ICursorProxy(geoLayer.search(null, false));
			// Create a DataStatistics object and initialize properties
			DataStatistics dataStatistics = new DataStatistics();
			dataStatistics.setField(field.getName());
			dataStatistics.setCursorByRef(cursor);
			IStatisticsResults statisticsResults = dataStatistics.getStatistics();
			double minVal = statisticsResults.getMinimum();
			double maxVal = statisticsResults.getMaximum();
			int nb_class = 10;
//			System.out.println("Val. min: "+minVal);
//			System.out.println("Val. Max: "+maxVal);
			
			ClassBreaksRenderer classBreaksRenderer = new ClassBreaksRenderer();
			classBreaksRenderer.setField(nameFld);
			classBreaksRenderer.setBreakCount(nb_class);
			classBreaksRenderer.setMinimumBreak(minVal);

			// Calculate the class interval by a simple mean value
			double interval = (maxVal - minVal)/nb_class;

			// Get the color ramp
			AlgorithmicColorRamp colorRamp = new AlgorithmicColorRamp();
			// color From: GREEN
			HsvColor col_green = new HsvColor();
			col_green.setHue(136);
			col_green.setSaturation(78);
			col_green.setValue(98);
			// Color To: RED
			HsvColor col_red = new HsvColor();
			col_red.setHue(11);
			col_red.setSaturation(99);
			col_red.setValue(96);
			
			colorRamp.setFromColor(col_red);
			colorRamp.setToColor(col_green);
			
			// Set the size of the color ramp and recreate it
			colorRamp.setSize(nb_class);
			boolean createRamp[] = { false };
			colorRamp.createRamp(createRamp);

			// Get the enumeration of colors from the color ramp
			IEnumColors enumColors = colorRamp.getColors();
			enumColors.reset();
			double currentBreak = classBreaksRenderer.getMinimumBreak()+interval;
			
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
			((IGeoFeatureLayer) layer).setRendererByRef(classBreaksRenderer);
		}	
		return layer;
	}
	
	public ArrayList<String> calPathFile(String ds){
		ArrayList<String> list = new ArrayList<String>();
		list.add(ds.substring(0, ds.lastIndexOf(File.separator)+1));
		list.add(ds.substring(ds.lastIndexOf(File.separator)+1, ds.length()));
		return list;
	}
	
	private IFeatureClass loadCEModel(String ceModel_gdb) throws Exception{
		IFeatureClass fc_ce3DModel = null;
		String ce_3DModel = "fc_mp_dhm";
		System.out.println("ceModel_gdb: "+ceModel_gdb);
		IPropertySet propertySet = new PropertySet();
	    propertySet.setProperty("DATABASE", ceModel_gdb);
	    IWorkspaceFactory workspaceFactory = new FileGDBWorkspaceFactory();
		Workspace  gd_ws = (Workspace) workspaceFactory.open(propertySet, 0);
		gd_ws.startEditing(false);
		gd_ws.startEditOperation();
		
		fc_ce3DModel = gd_ws.openFeatureClass(ce_3DModel);
		
		gd_ws.stopEditOperation();
		gd_ws.stopEditing(true);
		gd_ws = null;
		
		return fc_ce3DModel;
	}
	
	public ArrayList createListSite(Project projWS){		
		GeoProcessor GP = null;
		ArrayList<Site> list_site = new ArrayList<Site>();
		ArrayList<Map> map_List = (ArrayList<Map>) projWS.getMap();
		Hashtable<String,Layer> layer2D_list = map_List.get(0).getLayer();
		
		try{
			if(layer2D_list.containsKey("TX")){
				ArrayList<String> ds_data = this.calPathFile(layer2D_list.get("TX").getDataSource());
				ShapefileWorkspaceFactory sf_wsf = new ShapefileWorkspaceFactory();
				Workspace sf_ws = (Workspace) sf_wsf.openFromFile(ds_data.get(0), 0);	
				sf_ws.startEditing(false);
				sf_ws.startEditOperation();
				
				IFeatureClass fc_sites = sf_ws.openFeatureClass(ds_data.get(1));
				IFeatureCursor cur_sites = fc_sites.search(null, false);
				Feature f_sites = (Feature) cur_sites.nextFeature();
				while(f_sites != null){
					Point pt = (Point) f_sites.getShape();
					Site site = new Site();
					site.setId(Integer.parseInt(f_sites.getValue(fc_sites.findField("FID")).toString()));
					site.setX(pt.getX());
					site.setY(pt.getY());
					site.setZ(pt.getZ());
					site.setPoint_id(Long.parseLong(f_sites.getValue(fc_sites.findField("POINTID")).toString()));
					site.setH_point(Double.parseDouble(f_sites.getValue(fc_sites.findField("H_POINT")).toString()));
					site.setCluter_id(Long.parseLong(f_sites.getValue(fc_sites.findField("CLUTTER_ID")).toString()));
					site.setSurface_id(Long.parseLong(f_sites.getValue(fc_sites.findField("SURFACE_ID")).toString()));
					site.setSurface_h(Double.parseDouble(f_sites.getValue(fc_sites.findField("SURFACE_H")).toString()));
					site.setPxcenter(Double.parseDouble(f_sites.getValue(fc_sites.findField("PXCENTER")).toString()));
					site.setPycenter(Double.parseDouble(f_sites.getValue(fc_sites.findField("PYCENTER")).toString()));
					site.setH_dem(Double.parseDouble(f_sites.getValue(fc_sites.findField("H_DEM")).toString()));
					site.setH_eff_r(Double.parseDouble(f_sites.getValue(fc_sites.findField("H_eff_r")).toString()));
					site.setSurf_eff(Double.parseDouble(f_sites.getValue(fc_sites.findField("Surf_eff")).toString()));
					site.setTower_h(Double.parseDouble(f_sites.getValue(fc_sites.findField("Tower_H")).toString()));
					list_site.add(site);
					f_sites = (Feature) cur_sites.nextFeature();
				}
				Cleaner.release(f_sites);
				Cleaner.release(cur_sites);
				
				sf_ws.stopEditOperation();
				sf_ws.stopEditing(true);
				sf_ws = null;
			}			
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
		return list_site;
	}
	
	public void createProjectFC(Project projWS){
		GeoProcessor GP = null;		
		try{
			/*
			 *  Create Parameters FC Project: Param_Tx
			 */
			String ws_param = OCCToolRunner.getOutputDir("ws_param");
			File src = new File(ws_param);	
			OCCToolRunner.createFolder(OCCToolRunner.getOutputDir(""));
			OCCToolRunner.createFolder(ws_param);
			OCCToolRunner.emptyFolder(ws_param);
			
			GP = new GeoProcessor();
			GP.setOverwriteOutput(true);
			GP.resetEnvironments();
			GP.clearMessages();
			
			// Set FC: param_tx	
			System.out.println("Creating Feature class param_tx: Start");
			VarArray parameters = new VarArray();
			String param_tx = "param_tx.shp";
			parameters.add(ws_param);
			parameters.add(param_tx);
			parameters.add("POINT");
			parameters.add("#");
			parameters.add("DISABLED");
			parameters.add("ENABLED");			
			GP.execute("CreateFeatureclass_management", parameters, null);
			System.out.println("Creating Feature class param_tx: Done");
			
			System.out.println("Adding fields to param_tx: Start");
			AddField addField = new AddField(ws_param+File.separator+param_tx, "POINTID", "LONG");
			GP.execute(addField, null);			
			addField.setFieldName("Frequency");
			addField.setFieldType("DOUBLE");			
			GP.execute(addField, null);
			addField.setFieldName("Power");
			addField.setFieldType("DOUBLE");			
			GP.execute(addField, null);
			addField.setFieldName("SiteType");
			addField.setFieldType("TEXT");			
			GP.execute(addField, null);
			addField.setFieldName("Gain");
			addField.setFieldType("DOUBLE");			
			GP.execute(addField, null);
			addField.setFieldName("H_ant");
			addField.setFieldType("DOUBLE");			
			GP.execute(addField, null);
			System.out.println("Adding fields to param_tx: Done");
			
			Parameter param= projWS.getParameter();
			EnbParameter enbParam = param.getEnbParam();
			FreqBand freqBand = projWS.getFreqBand();
			String freq = freqBand.getDl();
			if(freq == null)freq = freqBand.getAllocation();
			System.out.println("Freq DL: "+freq);
			String[] parts = freq.split("-");
			double freq_start = Double.parseDouble(parts[0]);
			double freq_end = Double.parseDouble(parts[1]);
			//double freq_Tx = (freq_end+freq_start)/2;
			double freq_Tx = 0.0;
			
			double H_ant = 0.0;
			PmParameter pmParam = param.getEnvParam().getPmParameter();
			if(pmParam.getPm() instanceof OkumuraHata){
				OkumuraHata oku = (OkumuraHata) pmParam.getPm();
				H_ant = oku.getHeightENB();
				freq_Tx = oku.getFrequency();
			}
			else if(pmParam.getPm() instanceof Cost231Hata){
				Cost231Hata c231 = (Cost231Hata) pmParam.getPm();
				H_ant = c231.getHeightENB();
				freq_Tx = c231.getFrequency();
			}
			
			System.out.println("Setting values to param_tx: Start");
			ShapefileWorkspaceFactory sf_wsf = new ShapefileWorkspaceFactory();
			Workspace sf_ws = (Workspace) sf_wsf.openFromFile(ws_param, 0);	
			sf_ws.startEditing(false);
			sf_ws.startEditOperation();		
			
			IFeatureClass fc_paramTx = sf_ws.openFeatureClass(param_tx);
			IFeatureCursor cur_par = fc_paramTx.IFeatureClass_insert(true);
			IFeature f_paramTx =  fc_paramTx.createFeature();
			f_paramTx.setValue(fc_paramTx.findField("POINTID"), 0);
			f_paramTx.setValue(fc_paramTx.findField("Frequency"), freq_Tx);
			f_paramTx.setValue(fc_paramTx.findField("Power"), enbParam.getPowerTx());
			f_paramTx.setValue(fc_paramTx.findField("SiteType"), enbParam.getSiteType());
			f_paramTx.setValue(fc_paramTx.findField("Gain"), enbParam.getAntennaGain());
			f_paramTx.setValue(fc_paramTx.findField("H_ant"), H_ant);
			f_paramTx.store();
			Cleaner.release(fc_paramTx);
			Cleaner.release(f_paramTx);
			Cleaner.release(cur_par);
			
			sf_ws.stopEditOperation();
			sf_ws.stopEditing(true);
			Cleaner.release(sf_ws);
			System.out.println("Setting values to param_tx: Done");
			
			// Set FC: PlanResCov
			System.out.println("Creating Feature class planResCov: Start");
			parameters = new VarArray();
			String planResCov = "planResultCov.shp";
			parameters.add(ws_param);
			parameters.add(planResCov);
			parameters.add("POINT");
			parameters.add("#");
			parameters.add("DISABLED");
			parameters.add("ENABLED");			
			GP.execute("CreateFeatureclass_management", parameters, null);
			System.out.println("Creating Feature class planResCov: Done");
			
			System.out.println("Adding fields to planResCov: Start");
			addField = new AddField(ws_param+File.separator+planResCov, "PIRE", "DOUBLE");
			GP.execute(addField, null);			
			addField.setFieldName("S_UL");
			addField.setFieldType("DOUBLE");			
			GP.execute(addField, null);
			addField.setFieldName("S_DL");
			addField.setFieldType("DOUBLE");			
			GP.execute(addField, null);
			addField.setFieldName("PathLoss");
			addField.setFieldType("DOUBLE");			
			GP.execute(addField, null);
			addField.setFieldName("CellRadCov");
			addField.setFieldType("DOUBLE");			
			GP.execute(addField, null);
			addField.setFieldName("CellPrint");
			addField.setFieldType("DOUBLE");			
			GP.execute(addField, null);
			addField.setFieldName("SitePrint");
			addField.setFieldType("DOUBLE");			
			GP.execute(addField, null);
			addField.setFieldName("NumSite");
			addField.setFieldType("LONG");			
			GP.execute(addField, null);
			addField.setFieldName("IS_Dist");
			addField.setFieldType("DOUBLE");			
			GP.execute(addField, null);
			addField.setFieldName("PM_ID");
			addField.setFieldType("LONG");			
			GP.execute(addField, null);			
			System.out.println("Adding fields to planResCov: Done");
			
			RPlanCoverage resCov = projWS.getPlanification().getResult().getResCov();	
			int pm_id = 0;
			if(pmParam.getPm() instanceof OkumuraHata){
				OkumuraHata oku = (OkumuraHata) pmParam.getPm();
				pm_id = 0;
			}
			else if(pmParam.getPm() instanceof Cost231Hata){
				Cost231Hata c231 = (Cost231Hata) pmParam.getPm();
				pm_id = 1;
			}
			
			System.out.println("Setting values to planResCov: Start");
			sf_wsf = new ShapefileWorkspaceFactory();
			sf_ws = (Workspace) sf_wsf.openFromFile(ws_param, 0);	
			sf_ws.startEditing(false);
			sf_ws.startEditOperation();		
			
			IFeatureClass fc_planResCov = sf_ws.openFeatureClass(planResCov);
			IFeatureCursor cur_planRes = fc_planResCov.IFeatureClass_insert(true);
			IFeature f_planResCov =  fc_planResCov.createFeature();
			f_planResCov.setValue(fc_planResCov.findField("PIRE"), Math.max(resCov.getPireUl(),resCov.getPireDl()));
			f_planResCov.setValue(fc_planResCov.findField("S_UL"), resCov.getSensibilityUL());
			f_planResCov.setValue(fc_planResCov.findField("S_DL"), resCov.getSensibilityDL());
			f_planResCov.setValue(fc_planResCov.findField("PathLoss"), Math.max(resCov.getPathLossUL(), resCov.getPathLossDL()));
			f_planResCov.setValue(fc_planResCov.findField("CellRadCov"), Math.max(resCov.getCellRadiusUL()*1000, resCov.getCellRadiusDL()*1000));
			f_planResCov.setValue(fc_planResCov.findField("CellPrint"), Math.max(resCov.getCellPrintUL()*1000, resCov.getCellPrintDL()*1000));
			f_planResCov.setValue(fc_planResCov.findField("SitePrint"), Math.max(resCov.getSitePrintUL()*1000, resCov.getSitePrintDL()*1000));
			f_planResCov.setValue(fc_planResCov.findField("NumSite"), Math.max(resCov.getNumENBUl(), resCov.getNumENBDl()));
			f_planResCov.setValue(fc_planResCov.findField("IS_Dist"), Math.max(resCov.getInterSiteDistUL()*1000, resCov.getInterSiteDistDL())*1000);
			f_planResCov.setValue(fc_planResCov.findField("PM_ID"), pm_id);
			f_planResCov.store();
			Cleaner.release(fc_planResCov);
			Cleaner.release(f_planResCov);
			Cleaner.release(cur_planRes);
			
			sf_ws.stopEditOperation();
			sf_ws.stopEditing(true);
			Cleaner.release(sf_ws);
			System.out.println("Setting values to planResCov: Done");
			
			// Set FC: PropaModel
			switch(pm_id){
				case 0: //OkumuraHata
					OkumuraHata oku = (OkumuraHata) pmParam.getPm();
					String zoneType = oku.getZoneType();
					if(zoneType == "Urbain dense")zoneType = "urban";
					else if(zoneType == "Urbain")zoneType = "urban";
					else if(zoneType == "Sous urbain")zoneType = "sub_urban";
					else zoneType = "sub_urban";
					
					System.out.println("Creating Feature class Oku: Start");
					parameters = new VarArray();
					String oku_file = "okumuraHata.shp";
					parameters.add(ws_param);
					parameters.add(oku_file);
					parameters.add("POINT");
					parameters.add("#");
					parameters.add("DISABLED");
					parameters.add("ENABLED");			
					GP.execute("CreateFeatureclass_management", parameters, null);
					System.out.println("Creating Feature class oku: Done");
					
					System.out.println("Adding fields to oku: Start");
					addField = new AddField(ws_param+File.separator+oku_file, "PM_ID", "LONG");
					GP.execute(addField, null);			
					addField.setFieldName("Diffr_Meth");
					addField.setFieldType("TEXT");			
					GP.execute(addField, null);
					addField.setFieldName("Opt_clut");
					addField.setFieldType("TEXT");			
					GP.execute(addField, null);
					addField.setFieldName("Atten_clut");
					addField.setFieldType("TEXT");			
					GP.execute(addField, null);
					addField.setFieldName("zoneType");
					addField.setFieldType("TEXT");			
					GP.execute(addField, null);
					addField.setFieldName("MP_Meth");
					addField.setFieldType("TEXT");			
					GP.execute(addField, null);
					System.out.println("Adding fields to oku: Done");
					
					System.out.println("Setting values to oku: Start");
					sf_wsf = new ShapefileWorkspaceFactory();
					sf_ws = (Workspace) sf_wsf.openFromFile(ws_param, 0);	
					sf_ws.startEditing(false);
					sf_ws.startEditOperation();	
					
					IFeatureClass fc_oku = sf_ws.openFeatureClass(oku_file);
					IFeatureCursor cur_oku = fc_oku.IFeatureClass_insert(true);
					IFeature f_oku =  fc_oku.createFeature();
					f_oku.setValue(fc_oku.findField("PM_ID"), 0);
					f_oku.setValue(fc_oku.findField("Diffr_Meth"), "deyout1994");
					f_oku.setValue(fc_oku.findField("Opt_clut"), "over_clutter");
					f_oku.setValue(fc_oku.findField("Atten_clut"), "atten_UIT");
					f_oku.setValue(fc_oku.findField("zoneType"), zoneType);
					f_oku.setValue(fc_oku.findField("MP_Meth"), "standard");
					f_oku.store();
					Cleaner.release(fc_oku);
					Cleaner.release(f_oku);
					Cleaner.release(cur_oku);
					
					sf_ws.stopEditOperation();
					sf_ws.stopEditing(true);
					Cleaner.release(sf_ws);
					System.out.println("Setting values to oku: Done");
					break;
				case 1: //Cost231Hata
					Cost231Hata c231 = (Cost231Hata) pmParam.getPm();
					zoneType = c231.getZoneType();
					String opt_clut = "";
					if(zoneType == "Dense urbain"){
						opt_clut = "urban";
						zoneType = "large_city";
					}
					else if(zoneType == "Urbain"){
						opt_clut = "urban";
						zoneType = "large_city";
					}
					else if(zoneType == "Sous urbain"){
						opt_clut = "sub_urban";
						zoneType = "city";
					}
					else {
						opt_clut = "sub_urban";
						zoneType = "city";
					}
					
					System.out.println("Creating Feature class c231: Start");
					parameters = new VarArray();
					String c231_file = "cost231Hata.shp";
					parameters.add(ws_param);
					parameters.add(c231_file);
					parameters.add("POINT");
					parameters.add("#");
					parameters.add("DISABLED");
					parameters.add("ENABLED");			
					GP.execute("CreateFeatureclass_management", parameters, null);
					System.out.println("Creating Feature class c231: Done");
					
					System.out.println("Adding fields to oku: Start");
					addField = new AddField(ws_param+File.separator+c231_file, "PM_ID", "LONG");
					GP.execute(addField, null);
					addField.setFieldName("Opt_clut");
					addField.setFieldType("TEXT");			
					GP.execute(addField, null);
					addField.setFieldName("zoneType");
					addField.setFieldType("TEXT");			
					GP.execute(addField, null);
					System.out.println("Adding fields to oku: Done");
					
					System.out.println("Setting values to oku: Start");
					sf_wsf = new ShapefileWorkspaceFactory();
					sf_ws = (Workspace) sf_wsf.openFromFile(ws_param, 0);	
					sf_ws.startEditing(false);
					sf_ws.startEditOperation();	
					
					IFeatureClass fc_c231 = sf_ws.openFeatureClass(c231_file);
					IFeatureCursor cur_c231 = fc_c231.IFeatureClass_insert(true);
					IFeature f_c231 =  fc_c231.createFeature();
					f_c231.setValue(fc_c231.findField("PM_ID"), 0);
					f_c231.setValue(fc_c231.findField("Opt_clut"), opt_clut);
					f_c231.setValue(fc_c231.findField("zoneType"), zoneType);
					f_c231.store();
					Cleaner.release(fc_c231);
					Cleaner.release(f_c231);
					Cleaner.release(cur_c231);
					
					sf_ws.stopEditOperation();
					sf_ws.stopEditing(true);
					Cleaner.release(sf_ws);
					System.out.println("Setting values to oku: Done");
					break;
				default:
					break;
			}
			
		}
		catch (Exception e){
			try{
				IGPMessages gpMessages = GP.getReturnMessages();
				for (int i = 0; i < gpMessages.getCount(); i++)
					System.out.println(i+". "+gpMessages.getMessage(i).getDescription());
				
				JOptionPane.showMessageDialog(this, "Une erreur s'est produite lors du chargement des couches du projet.\n"+
						"Veuillez fermer la/les couche(s) en cours d'utilisation dans d'autres applications.", "Erreur de chargement",
						JOptionPane.ERROR_MESSAGE);
			}
			catch (Exception e2){}
			e.printStackTrace();
		}
	}

	private JDialog getProgressDialog(String title)
	{
		JDialog dialog = null;
		if (progressDialog == null)
		{
			dialog = new JDialog();
			dialog.setTitle(title);
			dialog.setIconImage(icone);
			
			dialog.setLocationRelativeTo(null);
			dialog.setLocation(this.getWidth() / 2, this.getHeight() / 2);
			
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
	
	class MapControlListener extends IMapControlEvents2Adapter
	{
		/**
		 * @see com.esri.arcgis.beans.map.IMapControlEvents2Adapter#onMouseMove(IMapControlEvents2OnMouseMoveEvent
		 *      theEvent)
		 * @param theEvent
		 */

		public void onMouseMove(IMapControlEvents2OnMouseMoveEvent theEvent)
		{
			try
			{
				DecimalFormat format = new DecimalFormat(".00");
				tf_x.setText(format.format(theEvent.getMapX()));
				tf_y.setText(format.format(theEvent.getMapY()));
			}
			catch (Exception ex)
			{
				System.out.println("Exception in MapControlListener#onMouseMove : " + ex);
				ex.printStackTrace();
			}
			
		}

		//opsimMap
		public void onDoubleClick(IMapControlEvents2OnDoubleClickEvent theEvent){
			try{
				int n = opsimMap.getLayerCount();
				ILayer layer = null;
				IFeatureSelection featureSelection = null;
				ISelectionSet selectionSet = null;
				boolean is_RSL = false;
				boolean is_SINR = false;
				
				for(int i=0; i<n; i++){
					layer = opsimMap.getLayer(i);					
					IFeatureLayer featureLayer = (IFeatureLayer) layer;						
					featureSelection = (IFeatureSelection)featureLayer;
					selectionSet = featureSelection.getSelectionSet();
										
					if (selectionSet.getCount() == 1)  
				    {
						System.out.println("Layer selected: "+layer.getName());	
						ICursor[] cursor = new ICursor[1];  
						selectionSet.search(null, true, cursor);
						
						int field_DEM = featureLayer.getFeatureClass().findField("H_DEM"); 
						int field_Clut = featureLayer.getFeatureClass().findField("CLUTTER_ID"); 
						int field_RSL = featureLayer.getFeatureClass().findField("OPSIM_RSL"); 
						int field_SINR = featureLayer.getFeatureClass().findField("SINR"); 
						
				        IRow row = cursor[0].nextRow();
				        String val = null;
				        if(field_DEM != -1){
				        	val = row.getValue(field_DEM).toString();
				        	tf_z.setText(val);
				        }
				        if(field_Clut != -1){
				        	val = row.getValue(field_Clut).toString();
				        	tf_clut.setText(val);
				        }
				        if(field_RSL != -1){
				        	val = row.getValue(field_RSL).toString();				        	
				        	if(!is_RSL)tf_rscp.setText(Double.toString(round(Double.parseDouble(val),3)));
				        	is_RSL = true;
				        }
				        if(field_SINR != -1){
				        	val = row.getValue(field_SINR).toString();
				        	if(!is_SINR)tf_snir.setText(Double.toString(round(Double.parseDouble(val),3)));
				        	is_SINR = true;
				        }				        
				     }
					 else{
						 System.out.println("Veuillez sélectionner juste un composant");
					 }													
				}			
			}
			catch (IOException ex)
			{
				System.out.println("Exception in MapControlListener#onDoubleClick : " + ex);
				ex.printStackTrace();
			}
			catch (ClassCastException ex)
			{
				System.out.println("Exception in Class Layer: selected layer is not a Feature");
				//ex.printStackTrace();
			}
		}		
	}
	
	public JTree changeTreeTx(ArrayList<Site> list_site){
        System.out.println("Update Tree TX");
        System.out.println("N. site: "+list_site.size());
        
        DefaultMutableTreeNode      root = new DefaultMutableTreeNode("Sites");
        DefaultMutableTreeNode      parent;
        DefaultTreeCellRenderer		tcell = new DefaultTreeCellRenderer();
        JTree						tree;

        parent = new DefaultMutableTreeNode("ENode-B");
        root.add(parent);
        for(Site site: list_site){
        	String nodeName = "ENB-"+site.getId()+"["+round(site.getX(),3)+","+round(site.getY(),3)+"]";
        	 parent.add(new DefaultMutableTreeNode(nodeName));
        }     
        
        parent = new DefaultMutableTreeNode("BTS");
        root.add(parent);
        parent.add(new DefaultMutableTreeNode("Aucun"));
        
        parent = new DefaultMutableTreeNode("Station FH");
        root.add(parent);
        parent.add(new DefaultMutableTreeNode("Aucun"));
        
        tcell.setOpenIcon(new ImageIcon(Toolkit.getDefaultToolkit().
	  			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/tower.png"))));
        tcell.setClosedIcon(new ImageIcon(Toolkit.getDefaultToolkit().
	  			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/tower.png"))));
        tree = new JTree(root);
        tree.setCellRenderer(tcell);
        
        return tree;
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
}
