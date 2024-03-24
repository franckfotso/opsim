/**
 * 
 */
package cm.opsim.event;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.concurrent.ExecutionException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import cm.opsim.controller.ProjectController;
import cm.opsim.dao.DAO;
import cm.opsim.dao.DAOFactory;
import cm.opsim.dao.ProjectDAO;
import cm.opsim.geoprocessing.OCCToolRunner;
import cm.opsim.model.Layer;
import cm.opsim.model.Map;
import cm.opsim.model.Project;
import cm.opsim.view.DialogExportCarto;
import cm.opsim.view.DialogManProject;
import cm.opsim.view.DialogParamCost;
import cm.opsim.view.DialogParamEquip;
import cm.opsim.view.DialogParamGen;
import cm.opsim.view.DialogParamTrafic;
import cm.opsim.view.DialogParamZE;
import cm.opsim.view.DialogPlanCap;
import cm.opsim.view.DialogPlanCost;
import cm.opsim.view.DialogPlanCov;
import cm.opsim.view.DialogStartGP;
import cm.opsim.view.OpsimGUI;

import com.esri.arcgis.analyst3d.IScene;
import com.esri.arcgis.carto.IBasicMap;
import com.esri.arcgis.carto.IGeoFeatureLayer;
import com.esri.arcgis.carto.ILayer;
import com.esri.arcgis.carto.esriViewDrawPhase;
import com.esri.arcgis.controls.ITOCControlEventsAdapter;
import com.esri.arcgis.geometry.esriGeometryType;

/**
 * @author Romuald FOTSO
 *
 */
public class MainHE extends AbstractHandleEvent {

	KeyboardFocusManager focusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	Component compOnFocus = null;
	private ProjectController projController;
    private Project projModel;
    private ArrayList<Map> map_List = null;
    private Hashtable<String,Layer> layer2D_list = new Hashtable<String,Layer>();
    private Hashtable<String,Layer> layer3D_list = new Hashtable<String,Layer>();
    private OCCToolRunner toolRunner = null;
    private DialogStartGP dialogStartGP = null;
	
	/**
	 * 
	 */
	public MainHE() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param opsimGUI
	 */
	public MainHE(OpsimGUI opsimGUI) {
		super(opsimGUI);
		this.handleMainEvent();
		this.projController = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
	}	
	
	/*
	 * Handle All Main Events
	 */
	public void handleMainEvent(){
		focusManager.addPropertyChangeListener(
			    new PropertyChangeListener() {
			        public void propertyChange(PropertyChangeEvent e) {
			            String properties = e.getPropertyName();
			            if (("focusOwner".equals(properties)) && (e.getNewValue() != null)) {
			                Component component = (Component)e.getNewValue();
			                String name = component.getName();
			                if(name != null)compOnFocus = component;
			            }
			        }
		});
		
		
		this.opsimGUI.getTb_but_manProj().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_but_manProjActionPerformed(evt);            	
            }
        });
				
		this.opsimGUI.getTb_but_plancov().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	tb_but_plancovActionPerformed(evt);            	
            }
        });
		
		this.opsimGUI.getTb_but_plancap().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	tb_but_plancapActionPerformed(evt);            	
            }
        });
		
		this.opsimGUI.getTb_but_plancost().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	tb_but_plancostActionPerformed(evt);            	
            }
        });
		
		this.opsimGUI.getTabPane_map().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				tabPane_mapStateChanged(e);            	
            }
        });
		
		this.opsimGUI.getTb_but_save().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	tb_but_saveActionPerformed(evt);            	
            }
        });
		
		this.opsimGUI.getMenIt_CalPL().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	menIt_CalPLActionPerformed(evt);            	
            }
        });
		
		this.opsimGUI.getMenIt_DrawProfil().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	menIt_DrawProfilActionPerformed(evt);            	
            }
        });
		
		this.opsimGUI.getMenIt_SelSite().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
					menIt_SelSiteActionPerformed(evt);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(opsimGUI,"Impossible d'exécution l'opération pour l'une des raisons suivantes:\n" +
							"- Un ou plusieurs fichiers(.shp) d'entrées sont introuvables;\n" +
							"- Le projet en cours ne dispose pas de données de planification\n" +
							"  en couverture ou en capacité;\n" +
							"Si vous ne disposez pas encore de ces fichiers ou paramètres,\n" +
							"Veuillez lancer les outils appropriés pour leur création.",
							"Erreur",JOptionPane.ERROR_MESSAGE);
				}            	
            }
        });
		
		this.opsimGUI.getMenIt_CalCovTx().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
					menIt_CalCovTxActionPerformed(evt);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(opsimGUI,"Impossible d'exécuter l'opération pour l'une des raisons suivantes:\n" +
							"- Un ou plusieurs fichiers(.shp) d'entrées sont introuvables;\n" +
							"- Le projet en cours ne dispose pas de données de planification\n" +
							"  en couverture ou en capacité;\n" +
							"Si vous ne disposez pas encore de ces fichiers ou paramètres,\n" +
							"Veuillez lancer les outils appropriés pour leur création.",
							"Erreur",JOptionPane.ERROR_MESSAGE);
				}            	
            }
        });
		
		this.opsimGUI.getMenIt_AnaInterf().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
            		menIt_AnaInterfActionPerformed(evt); 
				} catch (Exception e) {
					JOptionPane.showMessageDialog(opsimGUI,"Impossible d'exécuter l'opération pour l'une des raisons suivantes:\n" +
							"- Un ou plusieurs fichiers(.shp) d'entrées sont introuvables;\n" +
							"- Le projet en cours ne dispose pas de données de planification\n" +
							"  en couverture ou en capacité;\n" +
							"Si vous ne disposez pas encore de ces fichiers ou paramètres,\n" +
							"Veuillez lancer les outils appropriés pour leur création.",
							"Erreur",JOptionPane.ERROR_MESSAGE);
				}            	           	
            }
        });
		
		this.opsimGUI.getMenIt_Gen3DModel_Basic().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
            		menIt_Gen3DModel_BasicActionPerformed(evt); 
				} catch (Exception e) {
					JOptionPane.showMessageDialog(opsimGUI,"Impossible d'exécuter l'opération pour l'une des raisons suivantes:\n" +
							"- Un ou plusieurs fichiers(.shp) d'entrées sont introuvables;\n" +
							"- Le projet en cours ne dispose pas de données de planification\n" +
							"  en couverture ou en capacité;\n" +
							"Si vous ne disposez pas encore de ces fichiers ou paramètres,\n" +
							"Veuillez lancer les outils appropriés pour leur création.",
							"Erreur",JOptionPane.ERROR_MESSAGE);
				}            	           	
            }
        });
		
		this.opsimGUI.getMenIt_AssignFreq().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	menIt_AssignFreqActionPerformed(evt);            	
            }
        });
		
		this.opsimGUI.getMenIt_generatePF().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	menIt_generatePFActionPerformed(evt);            	
            }
        });
		
		this.opsimGUI.getTb_tasks().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	tb_tasksActionPerformed(evt);            	
            }
        });
		
		try {
			this.opsimGUI.getToc().addITOCControlEventsListener(new ITOCControlEventsAdapter(){
				private static final long serialVersionUID = 1L;

				public void onMouseDown(com.esri.arcgis.controls.ITOCControlEventsOnMouseDownEvent e)
				{
					IBasicMap map[] =
					{ null };
					ILayer layer[] =
					{ null };
					Object other[] =
					{ null };
					int itemType[] =
					{ 0 };
					Object index[] =
					{ null };
					// Determine what kind of item has been clicked on
					try
					{
						opsimGUI.getToc().hitTest(e.getX(), e.getY(), itemType, map, layer, other, index);
						ILayer selLayer = layer[0];

						if (selLayer instanceof IGeoFeatureLayer)
						{
							IGeoFeatureLayer geoLayer = (IGeoFeatureLayer) selLayer;
							if (geoLayer.getFeatureClass().getShapeType() == esriGeometryType.esriGeometryPolygon ||
									geoLayer.getFeatureClass().getShapeType() == esriGeometryType.esriGeometryPoint)
							{
								opsimGUI.getToc().selectItem(selLayer, null);
								opsimGUI.getOpsimMap().setCustomProperty(selLayer);
								if (e.getButton() == 2)
								{
									opsimGUI.getTbm_toc().popupMenu(e.getX(), e.getY(), opsimGUI.getToc().getHWnd());
								}
							}
						}
					}
					catch (Exception ex)
					{
						ex.printStackTrace();
					}
				}
			});
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		this.opsimGUI.getTree_tabConfig().addTreeSelectionListener(new TreeSelectionListener(){
			public void valueChanged(TreeSelectionEvent event) {
				if(opsimGUI.getTree_tabConfig().getLastSelectedPathComponent() != null){
					String sel_node = opsimGUI.getTree_tabConfig().getLastSelectedPathComponent().toString();
					//System.out.println(sel_node);
					if("Generaux".equals(sel_node)){
						DialogParamGen dialog = new DialogParamGen(opsimGUI, true);
					}
					else if("Projet".equals(sel_node)){
						DialogParamGen dialog = new DialogParamGen(opsimGUI, true);
					}
					else if("Antennes".equals(sel_node)){
						DialogParamEquip dialog = new DialogParamEquip(opsimGUI, true);
					}
					else if("Sites".equals(sel_node)){
						DialogParamEquip dialog = new DialogParamEquip(opsimGUI, true);
					}
					else if("Zone d'étude".equals(sel_node)){
						DialogParamZE dialog = new DialogParamZE(opsimGUI, true);
					}
					else if("Modèle de trafic".equals(sel_node)){
						DialogParamTrafic dialog = new DialogParamTrafic(opsimGUI, true);
					}
					else if("Mesures Drive Test".equals(sel_node)){
						JOptionPane.showMessageDialog(opsimGUI, "Le module \"Mesures Drive Test\" est non installé ou inaccessible.",
								"OPSIM: Paramètres",
								JOptionPane.INFORMATION_MESSAGE);
					}
					else if("Couts".equals(sel_node)){
						DialogParamCost dialog = new DialogParamCost(opsimGUI, true);
					}
					else if("Profil".equals(sel_node)){
						JOptionPane.showMessageDialog(opsimGUI, "Le module \"Profil\" est non installé ou inaccessible.",
								"OPSIM: Paramètres",
								JOptionPane.INFORMATION_MESSAGE);
					}
					else if("Modèle de propagation".equals(sel_node)){
						JOptionPane.showMessageDialog(opsimGUI, "Le module \"Modèle de propagation\" est non installé ou inaccessible.",
								"OPSIM: Paramètres",
								JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
			}
		});
		
		this.opsimGUI.getTree_tabDGR().addTreeSelectionListener(new TreeSelectionListener(){
			public void valueChanged(TreeSelectionEvent event) {				
				if(opsimGUI.getTree_tabDGR().getLastSelectedPathComponent() != null){
					String sel_node = opsimGUI.getTree_tabDGR().getLastSelectedPathComponent().toString();
					//System.out.println(sel_node);
					
					ProjectController projConWS = (ProjectController) opsimGUI.getListControler().get("ProjectController");
			  		Project projWS = (Project) projConWS.getModel();
					
					if("Couverture".equals(sel_node)){
						if(projWS != null && projWS.getPlanification().getCovPlanif().getState().equals("DONE")){							
							DialogPlanCov dialog = new DialogPlanCov(opsimGUI,true, true);
							opsimGUI.setDialog_planCov(dialog);							
						}
						else JOptionPane.showMessageDialog(opsimGUI, "Veuillez lancer et terminer l'opération de planification\n" +
																	 "avant de visualer les résultats générés ici.",
								"OPSIM: Résultats",
								JOptionPane.INFORMATION_MESSAGE);
							
					}
					else if("Capacité".equals(sel_node)){
						if(projWS != null && projWS.getPlanification().getCapPlanif().getState().equals("DONE")){							
							DialogPlanCap dialog = new DialogPlanCap(opsimGUI,true, true);
							opsimGUI.setDialog_planCap(dialog);							
						}
						else JOptionPane.showMessageDialog(opsimGUI, "Veuillez lancer et terminer l'opération de planification\n" +
																	 "avant de visualer les résultats générés ici.",
								"OPSIM: Résultats",
								JOptionPane.INFORMATION_MESSAGE);
					}
					else if("Capex/Opex".equals(sel_node)){
						if(projWS != null && projWS.getPlanification().getCoPlanif().getState().equals("DONE")){							
							DialogPlanCost dialog = new DialogPlanCost(opsimGUI, true, true);
							opsimGUI.setDialog_planCost(dialog);							
						}
						else JOptionPane.showMessageDialog(opsimGUI, "Veuillez lancer et terminer l'opération de planification\n" +
																	 "avant de visualer les résultats générés ici.",
								"OPSIM: Résultats",
								JOptionPane.INFORMATION_MESSAGE);
					}
					else if("Prospective".equals(sel_node)){
						JOptionPane.showMessageDialog(opsimGUI, "Le module \"Planification Prospective\" est non installé ou inaccessible.",
								"OPSIM: Résultats",
								JOptionPane.INFORMATION_MESSAGE);
					}
					else if("RAN".equals(sel_node)){						
						JOptionPane.showMessageDialog(opsimGUI, "Le module \"Dimensionnement\" est non installé ou inaccessible.",
								"OPSIM: Résultats",
								JOptionPane.INFORMATION_MESSAGE);
					}
					else if("IMS".equals(sel_node)){
						JOptionPane.showMessageDialog(opsimGUI, "Le module \"Dimensionnement\" est non installé ou inaccessible.",
								"OPSIM: Résultats",
								JOptionPane.INFORMATION_MESSAGE);
					}
					else if("P-RAN".equals(sel_node)){
						
					}
					else if("Core Network".equals(sel_node)){
						JOptionPane.showMessageDialog(opsimGUI, "Le module \"Dimensionnement\" est non installé ou inaccessible.",
								"OPSIM: Résultats",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
	
		this.opsimGUI.getTree_tabReport().addTreeSelectionListener(new TreeSelectionListener(){
			public void valueChanged(TreeSelectionEvent event) {				
				if(opsimGUI.getTree_tabReport().getLastSelectedPathComponent() != null){
					String sel_node = opsimGUI.getTree_tabReport().getLastSelectedPathComponent().toString();
					if("Vue 2D".equals(sel_node)){
						JOptionPane.showMessageDialog(opsimGUI, "Le module \"G. Rapport\" est non installé ou inaccessible.",
								"OPSIM: Rapports",
								JOptionPane.INFORMATION_MESSAGE);
					}
					else if("Vue 3D".equals(sel_node)){
						JOptionPane.showMessageDialog(opsimGUI, "Le module \"G. Rapport\" est non installé ou inaccessible.",
								"OPSIM: Rapports",
								JOptionPane.INFORMATION_MESSAGE);
					}
					else if("Couverture".equals(sel_node)){
						JOptionPane.showMessageDialog(opsimGUI, "Le module \"G. Rapport\" est non installé ou inaccessible.",
								"OPSIM: Rapports",
								JOptionPane.INFORMATION_MESSAGE);
					}
					else if("Capacité".equals(sel_node)){
						JOptionPane.showMessageDialog(opsimGUI, "Le module \"G. Rapport\" est non installé ou inaccessible.",
								"OPSIM: Rapports",
								JOptionPane.INFORMATION_MESSAGE);
					}
					else if("Capex/Opex".equals(sel_node)){
						JOptionPane.showMessageDialog(opsimGUI, "Le module \"G. Rapport\" est non installé ou inaccessible.",
								"OPSIM: Rapports",
								JOptionPane.INFORMATION_MESSAGE);
					}
					else if("Prospective".equals(sel_node)){
						JOptionPane.showMessageDialog(opsimGUI, "Le module \"G. Rapport\" est non installé ou inaccessible.",
								"OPSIM: Rapports",
								JOptionPane.INFORMATION_MESSAGE);
					}
					else if("RAN".equals(sel_node)){
						JOptionPane.showMessageDialog(opsimGUI, "Le module \"G. Rapport\" est non installé ou inaccessible.",
								"OPSIM: Rapports",
								JOptionPane.INFORMATION_MESSAGE);
					}
					else if("IMS".equals(sel_node)){
						JOptionPane.showMessageDialog(opsimGUI, "Le module \"G. Rapport\" est non installé ou inaccessible.",
								"OPSIM: Rapports",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});		
	
		this.opsimGUI.getMenIt_PrefGen().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	DialogParamGen dialog = new DialogParamGen(opsimGUI, true);
            }
		});
		
		this.opsimGUI.getMenIt_PrefProject().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	DialogParamGen dialog = new DialogParamGen(opsimGUI, true);
            }
		});
		
		this.opsimGUI.getMenIt_PrefEquip_ENB().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	DialogParamEquip dialog = new DialogParamEquip(opsimGUI, true);
            }
		});
		
		this.opsimGUI.getMenIt_PrefTargetZone().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	DialogParamZE dialog = new DialogParamZE(opsimGUI, true);
            }
		});
		
		this.opsimGUI.getMenIt_PrefDriveTest().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	JOptionPane.showMessageDialog(opsimGUI, "Le module \"Mesures Drive Test\" est non installé ou inaccessible.",
						"OPSIM: Paramètres",
						JOptionPane.INFORMATION_MESSAGE);
            }
		});
		
		this.opsimGUI.getMenIt_PrefModelTraf().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	DialogParamTrafic dialog = new DialogParamTrafic(opsimGUI, true);
            }
		});
		
		this.opsimGUI.getMenIt_PrefCost().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	DialogParamCost dialog = new DialogParamCost(opsimGUI, true);
            }
		});
		
		this.opsimGUI.getMenIt_OpsimHelp().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	JOptionPane.showMessageDialog(opsimGUI, "Vous avez besoin d'aide ?\n" +
            			"Le module \"Aide\" est cours de réalisation.",
						"OPSIM: aide",
						JOptionPane.INFORMATION_MESSAGE);
            }
		});
		
		this.opsimGUI.getMenIt_OpsimTeam().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	JOptionPane.showMessageDialog(opsimGUI, "Nous sommes un groupe de jeunes developpeurs travaillant\n" +
            											"sous le label MTOPUS Inc pour faire valoir nos compétences\n" +
            											"dans le domaine des télécoms et des TIC.\n" +
            											"But: mettre sur pieds des outils à caractère innovants et \n" +
            											"répondant aux problématiques en vigeur dans notre secteur de\n" +
            											"recherche (Télécoms & TIC).\n" +
            											"Contact: Tel: +23770141462; Mail: romuald.fotso09@live.fr;\n" +
            											"POB: 4077 Douala",
						"OPSIM: Qui sommes nous ?",
						JOptionPane.INFORMATION_MESSAGE);
            }
		});
		
		this.opsimGUI.getMenIt_OpsimAbout().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	ImageIcon img = new ImageIcon(Toolkit.getDefaultToolkit().
            			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/logo.png")));
            	String msg = "";
            	msg += "Merci d'utiliser OPSIM Pro (Produit de MTOPUS Inc.).\n" +
            			"OPSIM pour << Optimization and Planning Simulator >> est un outil de simulation\n" +
            			"des technologies réseaux mobiles 3G/4G en vue d'une planification, dimensionnement\n" +
            			"et optimisation des ressources réseaux.\n\n" +
            			"But: OPSIM vise la mise à la disposition des ingénieurs de réseaux mobiles, un outil\n" +
            			"de SIG orienté télécommunications, c'est à dire un outil offrant des fonctions de\n" +
            			"manipulations des données géographiques et des fonctions d'ingénieurie réseaux\n" +
            			"mobiles telles que: Planification, Dimensionnement, Optimisation, etc.\n\n" +
            			"Fonctions majeures:\n" +
            			" 1-Modélisation d’un réseau de télécoms 3G/4G;\n" +
            			" 2-Simulation de couverture (niveau de puissance) sur la zone d’étude;\n" +
            			" 3-Simulation des interférences et génération des cartes SINR;\n" +
            			" 4-Visualisation des scènes 3D des cartes de couverture RF et des cartes SINR;\n" +
            			" 5-Visualisation du profil de liaison entre des points donnés;\n" +
            			" 6-Génération d’un design RF global du réseau;\n" +
            			" 7-Publication des rendus obtenus en ligne.\n\n" +
            			"Versions & Corrections:\n" +
            			"1.0 - OPSIM: Version de base avec mise sur pieds du noyau système;\n" +
            			"1.1 - OPSIM: Correction du rendu 2D & 3D pour les couches RSL & SINR;\n" +
            			"1.2 - OPSIM: Ajout de sélection de composants sur le rendu 2D;\n" +
            			"1.3 - OPSIM: Intégration de modèles 3D (Basic) des bâtiments;\n" +
            			"1.4 - OPSIM: Nouveaux geoprocessing (calcul de C/I et de modèles 3D);\n" +
            			"1.5 - OPSIM: Prise en compte de surface d'élévation dans le rendu 3D;\n" +
            			"1.6 - OPSIM: Optimisation de la durée de chargement des couches d'un projet.";
            	JOptionPane.showMessageDialog(opsimGUI, msg, "OPSIM: A propos", JOptionPane.INFORMATION_MESSAGE, img);
            }
		});
		
		
		this.opsimGUI.getMenIt_ManProject().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	tb_but_manProjActionPerformed(evt);      	
            }
        });
		
		this.opsimGUI.getMenIt_Pref().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	menIt_PrefActionPerformed(evt);      	
            }
        });
		
		this.opsimGUI.getMenIt_Close().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
            		DAO<Project> projDAO = DAOFactory.getProjectDAO();
            		ProjectController projConWS = (ProjectController) opsimGUI.getListControler().get("ProjectController");
            		Project projWS = (Project) projConWS.getModel();
            		if(projWS != null){
            			Project projDBB = projDAO.find(projWS.getId());
            			System.out.println("WS:0- Proj Date = "+projWS.getUpdatedDate());
            			System.out.println("DBB:0- Proj Date = "+projDBB.getUpdatedDate());
            			if(!projDBB.getUpdatedDate().equals(projWS.getUpdatedDate())){
            				int option = 99;
            				Toolkit.getDefaultToolkit().beep();
            				option = JOptionPane.showConfirmDialog(null, "Le projet en cours dispose des données non enregistrées.\n" +
            						 "Voulez-vous les enregistrer avant de fermer ?", "Fermer le projet en cours",
            						 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            				System.out.println("WS:1- Proj Date = "+projWS.getUpdatedDate());
            				System.out.println("DBB:1- Proj Date = "+projDBB.getUpdatedDate());
            				if(option != JOptionPane.NO_OPTION &&
            						option != JOptionPane.CANCEL_OPTION &&
            						option != JOptionPane.CLOSED_OPTION){						
            						projWS = projDAO.update(projWS);
            						menIt_CloseActionPerformed(evt);
            				}
            				else if(option == JOptionPane.NO_OPTION ){
            					menIt_CloseActionPerformed(evt);
            				}
            				else {
            					projWS.setUpdatedDate(projDBB.getUpdatedDate());
            					projConWS.setModel(projWS);
            					opsimGUI.getListControler().remove("ProjectController");
            					opsimGUI.getListControler().put("ProjectController", projConWS);
            				}
            				
            			}
            			else{
            				int option = 99;
            				Toolkit.getDefaultToolkit().beep();
            				option = JOptionPane.showConfirmDialog(null, "Le projet en cours va être fermer.\n" +
            						 "Etes-vous sure de vouloir continuer ?", "Fermer le projet en cours",
            						 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            				System.out.println("WS:1- Proj Date = "+projWS.getUpdatedDate());
            				System.out.println("DBB:1- Proj Date = "+projDBB.getUpdatedDate());
            				if(option != JOptionPane.NO_OPTION &&
            						option != JOptionPane.CANCEL_OPTION &&
            						option != JOptionPane.CLOSED_OPTION){						
            					menIt_CloseActionPerformed(evt);
            				}
            				else {
            					projWS.setUpdatedDate(projDBB.getUpdatedDate());
            					projConWS.setModel(projWS);
            					opsimGUI.getListControler().remove("ProjectController");
            					opsimGUI.getListControler().put("ProjectController", projConWS);
            				}
            			}
            		}
				}
            	catch (Exception e) {
					e.printStackTrace();
				}      	
            }
        });
		
		this.opsimGUI.getMenIt_Exit().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	Toolkit.getDefaultToolkit().beep();
            	menIt_ExitActionPerformed(evt);
            }
		});	
		
		this.opsimGUI.getMenIt_PlanCover().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	tb_but_plancovActionPerformed(evt); 
            }
		});	
		
		this.opsimGUI.getMenIt_PlanCap().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	tb_but_plancapActionPerformed(evt); 
            }
		});	
		
		this.opsimGUI.getMenIt_PlanCost().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	tb_but_plancostActionPerformed(evt); 
            }
		});	
		
		this.opsimGUI.getMenIt_Export_Map().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	menIt_Export_MapActionPerformed(evt); 
            }
		});		
		
		this.opsimGUI.getMenIt_Display2D_Img().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
            		menIt_Display2D_ActionPerformed(evt, "IMG");
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Impossible d'activer ou désactiver cette couche.",
							"Erreur",JOptionPane.ERROR_MESSAGE);
				} 
            }
		});
		
		this.opsimGUI.getMenIt_Display2D_Clut().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
            		menIt_Display2D_ActionPerformed(evt, "CLUT");
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Impossible d'activer ou désactiver cette couche.",
							"Erreur",JOptionPane.ERROR_MESSAGE);
				} 
            }
		});
		
		this.opsimGUI.getMenIt_Display2D_MNS().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
            		menIt_Display2D_ActionPerformed(evt, "MNS");
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Impossible d'activer ou désactiver cette couche.",
							"Erreur",JOptionPane.ERROR_MESSAGE);
				} 
            }
		});
		
		this.opsimGUI.getMenIt_Display2D_Power().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
            		menIt_Display2D_ActionPerformed(evt, "COV");
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Impossible d'activer ou désactiver cette couche.",
							"Erreur",JOptionPane.ERROR_MESSAGE);
				} 
            }
		});
		
		this.opsimGUI.getMenIt_Display2D_SINR().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
            		menIt_Display2D_ActionPerformed(evt, "SINR");
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Impossible d'activer ou désactiver cette couche.",
							"Erreur",JOptionPane.ERROR_MESSAGE);
				} 
            }
		});
		
		this.opsimGUI.getMenIt_Display2D_Site().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
            		menIt_Display2D_ActionPerformed(evt, "TX");
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Impossible d'activer ou désactiver cette couche.",
							"Erreur",JOptionPane.ERROR_MESSAGE);
				} 
            }
		});
		
		this.opsimGUI.getMenIt_Display3D_MneOnImg().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
            		menIt_Display3D_ActionPerformed(evt, "IMG");
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Impossible d'activer ou désactiver cette couche.",
							"Erreur",JOptionPane.ERROR_MESSAGE);
				} 
            }
		});
		
		this.opsimGUI.getMenIt_Display3D_MneOnCov().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
            		menIt_Display3D_ActionPerformed(evt, "COV");
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Impossible d'activer ou désactiver cette couche.",
							"Erreur",JOptionPane.ERROR_MESSAGE);
				} 
            }
		});
		
		this.opsimGUI.getMenIt_Display3D_ClutOnMne().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
            		menIt_Display3D_ActionPerformed(evt, "CLUT");
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Impossible d'activer ou désactiver cette couche.",
							"Erreur",JOptionPane.ERROR_MESSAGE);
				} 
            }
		});
		
		this.opsimGUI.getMenIt_Display3D_MneOnSinr().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
            		menIt_Display3D_ActionPerformed(evt, "SINR");
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Impossible d'activer ou désactiver cette couche.",
							"Erreur",JOptionPane.ERROR_MESSAGE);
				} 
            }
		});
		
		this.opsimGUI.getMenIt_Display3D_MneOnMns().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
            		menIt_Display3D_ActionPerformed(evt, "MNS");
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Impossible d'activer ou désactiver cette couche.",
							"Erreur",JOptionPane.ERROR_MESSAGE);
				} 
            }
		});
		
		this.opsimGUI.getMenIt_Display3D_MneOnSite().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
            		menIt_Display3D_ActionPerformed(evt, "TX");
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Impossible d'activer ou désactiver cette couche.",
							"Erreur",JOptionPane.ERROR_MESSAGE);
				} 
            }
		});
		
		this.opsimGUI.getMenIt_Display3D_MneOnCE().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
            		menIt_Display3D_ActionPerformed(evt, "CE");
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Impossible d'activer ou désactiver cette couche.",
							"Erreur",JOptionPane.ERROR_MESSAGE);
				} 
            }
		});
		
	}
	
	public void menIt_Display3D_ActionPerformed(java.awt.event.ActionEvent evt, String typeLay) throws Exception{
		ProjectController projConWS = (ProjectController) opsimGUI.getListControler().get("ProjectController");
		Project projWS = (Project) projConWS.getModel();
		ArrayList<Map> map_List = (ArrayList<Map>) projWS.getMap();
		Hashtable<String,Layer> layer2D_list = map_List.get(0).getLayer();
		Hashtable<String,Layer> layer3D_list = map_List.get(1).getLayer();
		ArrayList<String> ds_data = new ArrayList<String>();
		int n = opsimGUI.getOpsimScene().getScene().getLayerCount();
		boolean isAction = false;
		
		if(layer3D_list.containsKey(typeLay)){
			ds_data = opsimGUI.calPathFile(layer3D_list.get(typeLay).getDataSource());
			String layerName = "";
			if(typeLay == "CE")layerName = "fc_mp_dhm";
			else layerName = ds_data.get(1);
			System.out.println("Layer in Proj = "+layerName);
			for(int i=0; i<n; i++){
				ILayer layer = opsimGUI.getOpsimScene().getScene().getLayer(i);
				System.out.println("Layer in SceneBean = "+layer.getName());
				if((layerName.equals(layer.getName()) || layerName.equals(layer.getName()+".shp"))
						&& !isAction){
					System.out.println("Find on Index = "+i);
					if(layer.isVisible()){
						layer.setVisible(false);
						IScene scene = opsimGUI.getOpsimScene().getScene();
						scene.deleteLayer(layer);
						scene.addLayer(layer, false);
						opsimGUI.getOpsimScene().setSceneGraphByRef(scene.getSceneGraph());
						if(typeLay == "CE")opsimGUI.getMenIt_Display3D_MneOnCE().setIcon(null);
						isAction = true;
					}
					else{
						layer.setVisible(true);
						IScene scene = opsimGUI.getOpsimScene().getScene();
						scene.deleteLayer(layer);
						scene.addLayer(layer, false);
						opsimGUI.getOpsimScene().setSceneGraphByRef(scene.getSceneGraph());
						if(typeLay == "CE")opsimGUI.getMenIt_Display3D_MneOnCE().setIcon(new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/check.png"))));
						isAction = true;
					}
				}
			}
		}
		
		if(layer2D_list.containsKey(typeLay)){
			ds_data = opsimGUI.calPathFile(layer2D_list.get(typeLay).getDataSource());
			String layerName = ds_data.get(1);
			System.out.println("Layer in Proj = "+layerName);
			for(int i=0; i<n; i++){
				ILayer layer = opsimGUI.getOpsimScene().getScene().getLayer(i);
				System.out.println("Layer in SceneBean = "+layer.getName());
				if((layerName.equals(layer.getName()) || layerName.equals(layer.getName()+".shp"))
						&& !isAction){
					System.out.println("Find on Index = "+i);
					if(layer.isVisible()){
						layer.setVisible(false);
						IScene scene = opsimGUI.getOpsimScene().getScene();
						scene.deleteLayer(layer);
						scene.addLayer(layer, false);
						opsimGUI.getOpsimScene().setSceneGraphByRef(scene.getSceneGraph());
						if(typeLay == "IMG")opsimGUI.getMenIt_Display3D_MneOnImg().setIcon(null);
						else if(typeLay == "COV")opsimGUI.getMenIt_Display3D_MneOnCov().setIcon(null);
						else if(typeLay == "SINR")opsimGUI.getMenIt_Display3D_MneOnSinr().setIcon(null);
						else if(typeLay == "CLUT")opsimGUI.getMenIt_Display3D_ClutOnMne().setIcon(null);
						else if(typeLay == "MNS")opsimGUI.getMenIt_Display3D_MneOnMns().setIcon(null);
						else if(typeLay == "TX")opsimGUI.getMenIt_Display3D_MneOnSite().setIcon(null);
						
						isAction = true;
					}
					else{
						layer.setVisible(true);
						IScene scene = opsimGUI.getOpsimScene().getScene();
						scene.deleteLayer(layer);
						scene.addLayer(layer, false);
						opsimGUI.getOpsimScene().setSceneGraphByRef(scene.getSceneGraph());
						if(typeLay == "IMG")opsimGUI.getMenIt_Display3D_MneOnImg().setIcon(new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/check.png"))));
						else if(typeLay == "COV")opsimGUI.getMenIt_Display3D_MneOnCov().setIcon(new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/check.png"))));
						else if(typeLay == "SINR")opsimGUI.getMenIt_Display3D_MneOnSinr().setIcon(new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/check.png"))));
						else if(typeLay == "CLUT")opsimGUI.getMenIt_Display3D_ClutOnMne().setIcon(new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/check.png"))));
						else if(typeLay == "MNS")opsimGUI.getMenIt_Display3D_MneOnMns().setIcon(new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/check.png"))));
						else if(typeLay == "TX")opsimGUI.getMenIt_Display3D_MneOnSite().setIcon(new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/check.png"))));
						isAction = true;
					}
				}
			}
		}
	}
	
	public void menIt_Display2D_ActionPerformed(java.awt.event.ActionEvent evt, String typeLay) throws Exception{
		ProjectController projConWS = (ProjectController) opsimGUI.getListControler().get("ProjectController");
		Project projWS = (Project) projConWS.getModel();
		ArrayList<Map> map_List = (ArrayList<Map>) projWS.getMap();
		Hashtable<String,Layer> layer2D_list = map_List.get(0).getLayer();
		ArrayList<String> ds_data = new ArrayList<String>();
		int n = opsimGUI.getOpsimMap().getLayerCount();
		boolean isAction = false;
		
		if(layer2D_list.containsKey(typeLay)){
			ds_data = opsimGUI.calPathFile(layer2D_list.get(typeLay).getDataSource());
			String layerName = ds_data.get(1);
			System.out.println("Layer in Proj = "+layerName);
			for(int i=0; i<n; i++){
				ILayer layer = opsimGUI.getOpsimMap().getLayer(i);
				System.out.println("Layer in MapBean = "+layer.getName());
				if((layerName.equals(layer.getName()) || layerName.equals(layer.getName()+".shp"))
						&& !isAction){
					System.out.println("Find on Index = "+i);
					if(layer.isVisible()){
						layer.setVisible(false);
						opsimGUI.getOpsimMap().deleteLayer(i);
						opsimGUI.getOpsimMap().addLayer(layer, i);
						opsimGUI.getS_opsimMap().deleteLayer(i);
						opsimGUI.getS_opsimMap().addLayer(layer, i);
						if(typeLay == "IMG")opsimGUI.getMenIt_Display2D_Img().setIcon(null);
						else if(typeLay == "CLUT")opsimGUI.getMenIt_Display2D_Clut().setIcon(null);
						else if(typeLay == "MNS")opsimGUI.getMenIt_Display2D_MNS().setIcon(null);
						else if(typeLay == "COV")opsimGUI.getMenIt_Display2D_Power().setIcon(null);
						else if(typeLay == "SINR")opsimGUI.getMenIt_Display2D_SINR().setIcon(null);
						else if(typeLay == "TX")opsimGUI.getMenIt_Display2D_Site().setIcon(null);
						isAction = true;
					}
					else{
						layer.setVisible(true);
						opsimGUI.getOpsimMap().deleteLayer(i);
						opsimGUI.getOpsimMap().addLayer(layer, i);
						opsimGUI.getS_opsimMap().deleteLayer(i);
						opsimGUI.getS_opsimMap().addLayer(layer, i);
						if(typeLay == "IMG")opsimGUI.getMenIt_Display2D_Img().setIcon(new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/check.png"))));
						else if(typeLay == "CLUT")opsimGUI.getMenIt_Display2D_Clut().setIcon(new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/check.png"))));
						else if(typeLay == "MNS")opsimGUI.getMenIt_Display2D_MNS().setIcon(new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/check.png"))));
						else if(typeLay == "COV")opsimGUI.getMenIt_Display2D_Power().setIcon(new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/check.png"))));
						else if(typeLay == "SINR")opsimGUI.getMenIt_Display2D_SINR().setIcon(new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/check.png"))));
						else if(typeLay == "TX")opsimGUI.getMenIt_Display2D_Site().setIcon(new ImageIcon(Toolkit.getDefaultToolkit().
								getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/menuItems/check.png"))));
						isAction = true;
					}
					
				}
			}
		}
		else JOptionPane.showMessageDialog(null,"Couche non disponible.",
				"Erreur",JOptionPane.ERROR_MESSAGE);
	}
	
	public void menIt_Export_MapActionPerformed(java.awt.event.ActionEvent evt){
		DialogExportCarto dialog = new DialogExportCarto(opsimGUI, true);
	}
	
	public void menIt_ExitActionPerformed(java.awt.event.ActionEvent evt){
		DAO<Project> projDAO = DAOFactory.getProjectDAO();
		ProjectController projConWS = (ProjectController) opsimGUI.getListControler().get("ProjectController");
		Project projWS = (Project) projConWS.getModel();
		if(projWS != null){
			Project projDBB = projDAO.find(projWS.getId());
			System.out.println("WS:0- Proj Date = "+projWS.getUpdatedDate());
			System.out.println("DBB:0- Proj Date = "+projDBB.getUpdatedDate());
			if(!projDBB.getUpdatedDate().equals(projWS.getUpdatedDate())){
				int option = 99;
				option = JOptionPane.showConfirmDialog(null, "Le projet en cours dispose des données non enregistrées.\n" +
						 "Voulez-vous les enregistrer avant de fermer OPSIM ?", "Fermeture de OPSIM",
						 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				System.out.println("WS:1- Proj Date = "+projWS.getUpdatedDate());
				System.out.println("DBB:1- Proj Date = "+projDBB.getUpdatedDate());
				if(option != JOptionPane.NO_OPTION &&
						option != JOptionPane.CLOSED_OPTION){						
						projWS = projDAO.update(projWS);
						System.exit(0);
				}
			}
			else{
				int option = 99;
				option = JOptionPane.showConfirmDialog(null, "Etes-vous sure de vouloir quitter OPSIM ?", "Fermeture de OPSIM",
						 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(option != JOptionPane.NO_OPTION &&
						option != JOptionPane.CLOSED_OPTION){						
					System.exit(0);
				}
			}
		}
		else{
			int option = 99;
			option = JOptionPane.showConfirmDialog(null, "Etes-vous sure de vouloir quitter OPSIM ?", "Fermeture de OPSIM",
					 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(option != JOptionPane.NO_OPTION &&
					option != JOptionPane.CLOSED_OPTION){						
				System.exit(0);
			}
		}
	}

	public void menIt_CloseActionPerformed(java.awt.event.ActionEvent evt) throws Exception{
		this.opsimGUI.getListControler().remove("ProjectController");
		this.opsimGUI.getListControler().put("ProjectController", new ProjectController(null));
		this.opsimGUI.setTitle("OPSIM Pro 1.6.1 - ["+this.opsimGUI.getUser().getName()+"]");
		
		String opsim_home = System.getenv("OPSIM_HOME");
	    String blankMxd_path = opsim_home+File.separator+"/files/blank.mxd";
	    String blankSxd_path = opsim_home+File.separator+"/files/blank.sxd";
	    
	    this.opsimGUI.getOpsimMap().loadMxFile(blankMxd_path, null, null);
		this.opsimGUI.getOpsimMap().refresh(esriViewDrawPhase.esriViewForeground, null, null);
		this.opsimGUI.getOpsimScene().loadSxFile(blankSxd_path);
		
		JTree treeTx = this.opsimGUI.buildTreeTx();
		this.opsimGUI.getTree_tabTx().setModel(treeTx.getModel());
		this.opsimGUI.getTree_tabTx().expandRow(1);
		this.opsimGUI.getTree_tabTx().expandRow(3);
		this.opsimGUI.getTree_tabTx().expandRow(5);
		
		this.opsimGUI.getMenIt_AchProject().setEnabled(false);
		this.opsimGUI.getMen_Export().setEnabled(false);
		this.opsimGUI.getMenIt_Publish().setEnabled(false);
		this.opsimGUI.getMen_Display2D().setEnabled(false);
		this.opsimGUI.getMenIt_Display2D_Field().setEnabled(false);
		this.opsimGUI.getMenIt_Display2D_Power().setEnabled(false);
		this.opsimGUI.getMenIt_Display2D_SIR().setEnabled(false);
		this.opsimGUI.getMenIt_Display2D_SINR().setEnabled(false);
		this.opsimGUI.getMenIt_Display2D_Site().setEnabled(false);		
		this.opsimGUI.getMen_Display3D().setEnabled(false);
		this.opsimGUI.getMenIt_Display3D_MneOnCov().setEnabled(false);	
		this.opsimGUI.getMenIt_PatColor().setEnabled(false);
		this.opsimGUI.getMenIt_SelSite().setEnabled(false);
		this.opsimGUI.getMenIt_CalCovTx().setEnabled(false);
		this.opsimGUI.getMenIt_AnaInterf().setEnabled(false);
		this.opsimGUI.getTb_but_calpl().setEnabled(false);
		this.opsimGUI.getTb_but_plancov().setEnabled(false);
		this.opsimGUI.getTb_but_plancap().setEnabled(false);
		this.opsimGUI.getTb_but_planfreq().setEnabled(false);
		this.opsimGUI.getTb_but_plancost().setEnabled(false);		
		this.opsimGUI.getTb_but_selsite().setEnabled(false);		
		this.opsimGUI.getMenIt_Close().setEnabled(false);
	}
	
	public void menIt_PrefActionPerformed(java.awt.event.ActionEvent evt){
		DialogParamGen dialog = new DialogParamGen(opsimGUI, true);
	}
	
	public void tb_tasksActionPerformed(java.awt.event.ActionEvent evt){
		if(this.dialogStartGP.getdialogTasksGP() != null){
			if(this.dialogStartGP.getdialogTasksGP().isVisible())this.dialogStartGP.getdialogTasksGP().setVisible(false);
			else this.dialogStartGP.getdialogTasksGP().setVisible(true);
		}
	}
	
	public void menIt_generatePFActionPerformed(java.awt.event.ActionEvent evt){
		
	}
	
	public void menIt_AssignFreqActionPerformed(java.awt.event.ActionEvent evt){
		
	}
	
	public void menIt_Gen3DModel_BasicActionPerformed(java.awt.event.ActionEvent evt) throws Exception{
		// Launch GP: Opsim Generate Basic 3D Model
		ProjectDAO projDAO = (ProjectDAO) DAOFactory.getProjectDAO();
		ProjectController projConWS = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
		Project projWS = (Project) projConWS.getModel();
		map_List = (ArrayList<Map>) projWS.getMap();
		layer2D_list = map_List.get(0).getLayer();
		layer3D_list = map_List.get(1).getLayer();
		
		if(this.layer2D_list.containsKey("MNT") && this.layer2D_list.containsKey("MNS")){
			if(!(new File(layer2D_list.get("TX").getDataSource())).exists() || !(new File(layer2D_list.get("MNT").getDataSource())).exists() 
					|| !(new File(layer2D_list.get("MNS").getDataSource())).exists())
				throw new Exception();
			System.out.println("Check Map Layyers: OK");
			
			if(this.layer3D_list.containsKey("CE")){
				int option = 99;
				option = JOptionPane.showConfirmDialog(null, "Le projet en cours contient déjà une couche de modèle 3D.\n" +
															"Voulez-vous vraiment ecraser celle existante ?", "Génération de modèle 3D",
															 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(option != JOptionPane.NO_OPTION &&
						option != JOptionPane.CANCEL_OPTION &&
						option != JOptionPane.CLOSED_OPTION){
					ArrayList listParam = new ArrayList();
					listParam.add(map_List);
					listParam.add(layer2D_list);
					listParam.add(layer3D_list);
					listParam.add(toolRunner);
					
					this.dialogStartGP = new DialogStartGP(this.opsimGUI, true, listParam, "OccCal3DModel_basic");			
					
					projConWS.setModel(projWS);
					this.opsimGUI.getListControler().remove("ProjectController");
					this.opsimGUI.getListControler().put("ProjectController", projConWS);
				}
			}
			else{
				ArrayList listParam = new ArrayList();
				listParam.add(map_List);
				listParam.add(layer2D_list);
				listParam.add(layer3D_list);
				listParam.add(toolRunner);
				
				this.dialogStartGP = new DialogStartGP(this.opsimGUI, true, listParam, "OccCal3DModel_basic");			
				
				projConWS.setModel(projWS);
				this.opsimGUI.getListControler().remove("ProjectController");
				this.opsimGUI.getListControler().put("ProjectController", projConWS);
			}
		}
	}
	
	public void menIt_AnaInterfActionPerformed(java.awt.event.ActionEvent evt) throws Exception{
		// Launch GP: Opsim Calculate Interference
		ProjectDAO projDAO = (ProjectDAO) DAOFactory.getProjectDAO();
		ProjectController projConWS = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
		Project projWS = (Project) projConWS.getModel();
		map_List = (ArrayList<Map>) projWS.getMap();
		layer2D_list = map_List.get(0).getLayer();
		layer3D_list = map_List.get(1).getLayer();
		
		if(this.layer2D_list.containsKey("MNT") && this.layer2D_list.containsKey("MNS") 
				&& this.layer2D_list.containsKey("CLUT") && this.layer2D_list.containsKey("TX")){
			
			String ws_param = OCCToolRunner.getOutputDir("ws_param");
			if(!(new File(layer2D_list.get("TX").getDataSource())).exists() || !(new File(layer2D_list.get("MNT").getDataSource())).exists() 
					|| !(new File(layer2D_list.get("MNS").getDataSource())).exists() || !(new File(layer2D_list.get("CLUT").getDataSource())).exists() 
					|| !(new File(ws_param+File.separator+"param_tx.shp")).exists()  || !(new File(ws_param+File.separator+"planResultCov.shp")).exists()
					|| !(projWS.getPlanification().getCovPlanif().getState().equals("DONE")))
				throw new Exception();
			System.out.println("Check Map Layyers: OK");
			
			if(this.layer2D_list.containsKey("SINR")){
				int option = 99;
				option = JOptionPane.showConfirmDialog(null, "Le projet en cours contient déjà une couche d'interférence.\n" +
															"Voulez-vous vraiment ecraser celle existante ?", "Analyse d'interférence",
															 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(option != JOptionPane.NO_OPTION &&
						option != JOptionPane.CANCEL_OPTION &&
						option != JOptionPane.CLOSED_OPTION){
					ArrayList listParam = new ArrayList();
					listParam.add(map_List);
					listParam.add(layer2D_list);
					listParam.add(layer3D_list);
					listParam.add(toolRunner);
					
					this.dialogStartGP = new DialogStartGP(this.opsimGUI, true, listParam, "OccInterference");			
					
					projConWS.setModel(projWS);
					this.opsimGUI.getListControler().remove("ProjectController");
					this.opsimGUI.getListControler().put("ProjectController", projConWS);
				}
			}
			else{
				ArrayList listParam = new ArrayList();
				listParam.add(map_List);
				listParam.add(layer2D_list);
				listParam.add(layer3D_list);
				listParam.add(toolRunner);
				
				this.dialogStartGP = new DialogStartGP(this.opsimGUI, true, listParam, "OccInterference");
				
				projConWS.setModel(projWS);
				this.opsimGUI.getListControler().remove("ProjectController");
				this.opsimGUI.getListControler().put("ProjectController", projConWS);
			}
		}
		else if(this.layer2D_list.containsKey("MXD") || this.layer3D_list.containsKey("SXD")){
			System.out.println("Check Map or Scene Document: OK");
		}
		else{
			JOptionPane.showMessageDialog(this.opsimGUI, "Impossible de démarrer l'exécution du script Tool << OpsimCalInterference >>\n"+
					"Veuillez-vous rassurer que le projet en cours contient toutes les couches nécessaires pour cette opération",
					"Erreur",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void menIt_CalCovTxActionPerformed(java.awt.event.ActionEvent evt) throws Exception{
		// Launch GP: Opsim Calculate Coverage
		ProjectDAO projDAO = (ProjectDAO) DAOFactory.getProjectDAO();
		ProjectController projConWS = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
		Project projWS = (Project) projConWS.getModel();
		map_List = (ArrayList<Map>) projWS.getMap();
		layer2D_list = map_List.get(0).getLayer();
		layer3D_list = map_List.get(1).getLayer();
				
		if(this.layer2D_list.containsKey("MNT") && this.layer2D_list.containsKey("MNS") 
				&& this.layer2D_list.containsKey("CLUT") && this.layer2D_list.containsKey("TX")){
			
			String ws_param = OCCToolRunner.getOutputDir("ws_param");
			if(!(new File(layer2D_list.get("TX").getDataSource())).exists() || !(new File(layer2D_list.get("MNT").getDataSource())).exists() 
					|| !(new File(layer2D_list.get("MNS").getDataSource())).exists() || !(new File(layer2D_list.get("CLUT").getDataSource())).exists() 
					|| !(new File(ws_param+File.separator+"param_tx.shp")).exists()  || !(new File(ws_param+File.separator+"planResultCov.shp")).exists()
					|| !(projWS.getPlanification().getCovPlanif().getState().equals("DONE")))
				throw new Exception();
			System.out.println("Check Map Layyers: OK");
			
			if(this.layer2D_list.containsKey("COV")){
				int option = 99;
				option = JOptionPane.showConfirmDialog(null, "Le projet en cours contient déjà une couche de couverture radio.\n" +
															"Voulez-vous vraiment ecraser celle existante ?", "Prédiction de couverture",
															 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(option != JOptionPane.NO_OPTION &&
						option != JOptionPane.CANCEL_OPTION &&
						option != JOptionPane.CLOSED_OPTION){
					ArrayList listParam = new ArrayList();
					listParam.add(map_List);
					listParam.add(layer2D_list);
					listParam.add(layer3D_list);
					listParam.add(toolRunner);
					
					this.dialogStartGP = new DialogStartGP(this.opsimGUI, true, listParam, "OccMultiSite");			
					
					projConWS.setModel(projWS);
					this.opsimGUI.getListControler().remove("ProjectController");
					this.opsimGUI.getListControler().put("ProjectController", projConWS);
				}
			}
			else {
				ArrayList listParam = new ArrayList();
				listParam.add(map_List);
				listParam.add(layer2D_list);
				listParam.add(layer3D_list);
				listParam.add(toolRunner);
				
				this.dialogStartGP = new DialogStartGP(this.opsimGUI, true, listParam, "OccMultiSite");
				
				projConWS.setModel(projWS);
				this.opsimGUI.getListControler().remove("ProjectController");
				this.opsimGUI.getListControler().put("ProjectController", projConWS);
			}		
		}
		else if(this.layer2D_list.containsKey("MXD") || this.layer3D_list.containsKey("SXD")){
			System.out.println("Check Map or Scene Document: OK");
		}
		else{
			JOptionPane.showMessageDialog(this.opsimGUI, "Impossible de démarrer l'exécution du script Tool << OpsimCalCoverage >>\n"+
					"Veuillez-vous rassurer que le projet en cours contient toutes les couches nécessaires pour cette opération",
					"Erreur",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void menIt_SelSiteActionPerformed(java.awt.event.ActionEvent evt) throws Exception{
		// Launch GP: Opsim Site Position
		ProjectDAO projDAO = (ProjectDAO) DAOFactory.getProjectDAO();
		ProjectController projConWS = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
		Project projWS = (Project) projConWS.getModel();
		map_List = (ArrayList<Map>) projWS.getMap();
		layer2D_list = map_List.get(0).getLayer();
		layer3D_list = map_List.get(1).getLayer();
		
		if(this.layer2D_list.containsKey("MNT") && this.layer2D_list.containsKey("MNS") && this.layer2D_list.containsKey("CLUT")){
			
			String ws_param = OCCToolRunner.getOutputDir("ws_param");
			if(!(new File(layer2D_list.get("MNT").getDataSource())).exists() 
				|| !(new File(layer2D_list.get("MNS").getDataSource())).exists() || !(new File(layer2D_list.get("CLUT").getDataSource())).exists() 
				|| !(new File(ws_param+File.separator+"param_tx.shp")).exists()  || !(new File(ws_param+File.separator+"planResultCov.shp")).exists()
				|| !(projWS.getPlanification().getCovPlanif().getState().equals("DONE")))
				throw new Exception();
			System.out.println("Check Map Layer: OK");
			
			if(this.layer2D_list.containsKey("TX")){
				int option = 99;
				option = JOptionPane.showConfirmDialog(null, "Le projet en cours contient déjà une couche des sites.\n" +
															"Voulez-vous vraiment ecraser celle existante ?", "Calcul position des sites",
															 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(option != JOptionPane.NO_OPTION &&
						option != JOptionPane.CANCEL_OPTION &&
						option != JOptionPane.CLOSED_OPTION){
					
					ArrayList listParam = new ArrayList();
					listParam.add(map_List);
					listParam.add(layer2D_list);
					listParam.add(layer3D_list);
					listParam.add(toolRunner);
					this.dialogStartGP = new DialogStartGP(this.opsimGUI, true, listParam, "OccSitePosition");
					
					projWS.setMap(map_List);
					projWS.setUpdatedDate(new Date());					
					projConWS.setModel(projWS);
					this.opsimGUI.getListControler().remove("ProjectController");
					this.opsimGUI.getListControler().put("ProjectController", projConWS);
				}
			}
			else {
				ArrayList listParam = new ArrayList();
				listParam.add(map_List);
				listParam.add(layer2D_list);
				listParam.add(layer3D_list);
				listParam.add(toolRunner);
				this.dialogStartGP = new DialogStartGP(this.opsimGUI, true, listParam, "OccSitePosition");
				
				projWS.setMap(map_List);
				projWS.setUpdatedDate(new Date());					
				projConWS.setModel(projWS);
				this.opsimGUI.getListControler().remove("ProjectController");
				this.opsimGUI.getListControler().put("ProjectController", projConWS);
			}
			
		}
		else if(this.layer2D_list.containsKey("MXD") || this.layer3D_list.containsKey("SXD")){
			System.out.println("Check Map Document: OK");
		}
		else{
			JOptionPane.showMessageDialog(this.opsimGUI, "Impossible de démarrer l'exécution du script Tool << OpsimSitePosition >>\n"+
					"Veuillez-vous rassurer que le projet en cours contient toutes les couches nécessaires pour cette opération", "Erreur",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void menIt_DrawProfilActionPerformed(java.awt.event.ActionEvent evt){
		
	}
	
	public void menIt_CalPLActionPerformed(java.awt.event.ActionEvent evt){
		
	}
	
	public void tb_but_saveActionPerformed(java.awt.event.ActionEvent evt){
		ProjectDAO projDAO = (ProjectDAO) DAOFactory.getProjectDAO();
		ProjectController projConWS = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
		Project projWS = (Project) projConWS.getModel();
		projWS = projDAO.update(projWS);
		this.opsimGUI.getTb_but_save().setEnabled(false);
	}
	
	public void tb_but_manProjActionPerformed(java.awt.event.ActionEvent evt){
		ProjectController projConWS = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
		Project projWS = (Project) projConWS.getModel();
		if(projWS == null)this.opsimGUI.setDialog_manProj(new DialogManProject(this.opsimGUI,true));
		else {
			this.opsimGUI.getDialog_manProj().setVisible(true);			
		}			
	}
	
	public void tb_but_plancovActionPerformed(java.awt.event.ActionEvent evt){
		ProjectController projConWS = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
		Project projWS = (Project) projConWS.getModel();
		if(projWS != null){
			System.out.println("ID Planif = "+projWS.getPlanification().getId());
			System.out.println("ID CovPlanif = "+projWS.getPlanification().getCovPlanif().getId());
			System.out.println("State CovPlanif = "+projWS.getPlanification().getCovPlanif().getState());
			this.opsimGUI.setDialog_planCov(new DialogPlanCov(this.opsimGUI,true, false));
		}
		else JOptionPane.showMessageDialog(this.opsimGUI, "Veuillez charger un nouveau projet depuis le gestionnaire de projets\n" +
				" avant de lancer l'opération de planification en couverture.", "Erreur - Planification en couverture",JOptionPane.ERROR_MESSAGE);
		//this.opsimGUI.setDialog_planCov(dialog);
	}
	
	public void tb_but_plancapActionPerformed(java.awt.event.ActionEvent evt){
		ProjectController projConWS = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
		Project projWS = (Project) projConWS.getModel();
		if(projWS != null){
			if(projWS.getPlanification().getCovPlanif().getState().equals("DONE"))
				this.opsimGUI.setDialog_planCap(new DialogPlanCap(this.opsimGUI, true, false));
			else JOptionPane.showMessageDialog(this.opsimGUI, "Veuillez lancer l'opération de planification en couverture\n" +
					" avant de lancer l'opération de planification en capacité.", "Erreur - Planification en capacité",JOptionPane.ERROR_MESSAGE);
		}
		else JOptionPane.showMessageDialog(this.opsimGUI, "Veuillez charger un nouveau projet depuis le gestionnaire de projets\n" +
				" avant de lancer l'opération de planification en capacité.", "Erreur - Planification en capacité",JOptionPane.ERROR_MESSAGE);
		
	}
	
	public void tb_but_plancostActionPerformed(java.awt.event.ActionEvent evt){
		ProjectController projConWS = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
		Project projWS = (Project) projConWS.getModel();
		if(projWS != null){
			if(projWS.getPlanification().getCapPlanif().getState().equals("DONE"))
				this.opsimGUI.setDialog_planCost(new DialogPlanCost(this.opsimGUI, true, false));
			else JOptionPane.showMessageDialog(this.opsimGUI, "Veuillez lancer l'opération de planification en capacité\n" +
					" avant de lancer l'opération de planification CAPEX/OPEX.", "Erreur - Planification CAPEX/OPEX",JOptionPane.ERROR_MESSAGE);
		}
		else JOptionPane.showMessageDialog(this.opsimGUI, "Veuillez charger un nouveau projet depuis le gestionnaire de projets\n" +
				" avant de lancer l'opération de planification CAPEX/OPEX.", "Erreur - Planification CAPEX/OPEX",JOptionPane.ERROR_MESSAGE);		
	}
	
	public void tabPane_mapStateChanged(ChangeEvent e){
		int selected = this.opsimGUI.getTabPane_map().getSelectedIndex();
		//System.out.println("Selected tab : "+selected);
		
		if (selected == 1){
			new SwingWorker<Boolean, Void>() {
                @Override
                protected void done() {
                    try {
                        boolean loggedIn = get();

                        if (loggedIn) {
                        	try {
                				opsimGUI.getTb_ags_standard().setBuddyControl(opsimGUI.getOpsimScene());
                				opsimGUI.getTb_ags_option_3D().setBuddyControl(opsimGUI.getOpsimScene());
                				opsimGUI.getToc().setBuddyControl(opsimGUI.getOpsimScene());
                			} catch (IOException e1) {
                				System.out.println("Error: "+e1+" catch on Change state TabbedPane Map 1");
                			}
                			opsimGUI.getToolBar3().remove(opsimGUI.getTb_ags_option_2D());
                			opsimGUI.getToolBar3().add(opsimGUI.getTb_ags_option_3D());
                			opsimGUI.getTabPane_map().requestFocus();
                			opsimGUI.revalidate();                			
                        }
                    } catch (InterruptedException e1) {
                        e1.printStackTrace(); // Handle this.
                    } catch (ExecutionException e1) {
                        e1.printStackTrace(); // Handle this.
                    }
                }

                protected Boolean doInBackground() throws Exception {
                    // Perform login on background thread.  Return true if successful.
                    return true;
                }
            }.execute();
			
		}
		else{
			new SwingWorker<Boolean, Void>() {
                @Override
                protected void done() {
                    try {
                        boolean loggedIn = get();

                        if (loggedIn) {
                        	try {
                				opsimGUI.getTb_ags_standard().setBuddyControl(opsimGUI.getOpsimMap());
                				opsimGUI.getTb_ags_option_2D().setBuddyControl(opsimGUI.getOpsimMap());
                				opsimGUI.getToc().setBuddyControl(opsimGUI.getOpsimMap());
                			} catch (IOException e1) {
                				System.out.println("Error: "+e1+" catch on Change state TabbedPane Map 0");
                			}
                			opsimGUI.getToolBar3().remove(opsimGUI.getTb_ags_option_3D());
                			opsimGUI.getToolBar3().add(opsimGUI.getTb_ags_option_2D());
                			opsimGUI.getTabPane_map().requestFocus();
                			opsimGUI.revalidate();
                			
                        }
                    } catch (InterruptedException e1) {
                        e1.printStackTrace(); // Handle this.
                    } catch (ExecutionException e1) {
                        e1.printStackTrace(); // Handle this.
                    }
                }
                protected Boolean doInBackground() throws Exception {
                    // Perform login on background thread.  Return true if successful.
                    return true;
                }
            }.execute();			
		}
	}

	public DialogStartGP getdialogStartGP() {
		return dialogStartGP;
	}	
	
}
