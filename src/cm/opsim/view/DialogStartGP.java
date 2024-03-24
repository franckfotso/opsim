/**
 * 
 */
package cm.opsim.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;

import com.esri.arcgis.interop.AutomationException;

import cm.opsim.controller.ProjectController;
import cm.opsim.dao.DAOFactory;
import cm.opsim.dao.ProjectDAO;
import cm.opsim.event.MainHE;
import cm.opsim.geoprocessing.OCCToolRunner;
import cm.opsim.model.Layer;
import cm.opsim.model.Map;
import cm.opsim.model.Project;
import cm.opsim.model.Site;

/**
 * @author Romuald FOTSO
 *
 */
public class DialogStartGP extends javax.swing.JDialog{
	// Variables declaration - do not modify                     
    private javax.swing.JButton but_brow;
    private javax.swing.JButton but_start;
    private javax.swing.JButton but_exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField tf_filePath;
    // End of variables declaration
    private Image icone =  Toolkit.getDefaultToolkit().getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/logo.png"));
    private FileSystemView systView = FileSystemView.getFileSystemView();
    private File file;
    private DialogStartGP This = this;
    private ArrayList<Map> map_List = null;
    private Hashtable<String,Layer> layer2D_list = new Hashtable<String,Layer>();
    private Hashtable<String,Layer> layer3D_list = new Hashtable<String,Layer>();
    private DialogTasksGP dialogTasksGP = null;
    private OCCToolRunner toolRunner = null;
    private OpsimGUI opsimGUI = null;
    private String toolName = "";

	public DialogStartGP(OpsimGUI parent, boolean modal, ArrayList listParam, String toolname) {
        super(parent, modal);
        map_List = (ArrayList<Map>) listParam.get(0);
        layer2D_list = (Hashtable<String, Layer>) listParam.get(1);
        layer3D_list = (Hashtable<String, Layer>) listParam.get(2);
        toolRunner = (OCCToolRunner) listParam.get(3);
        opsimGUI = parent;
        toolName = toolname;
        initComponents();
        initEvents();
        this.setLocationRelativeTo(null);
        try {
        	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        SwingUtilities.updateComponentTreeUI(this);
	    } 
		catch (InstantiationException e) {} 
		catch (ClassNotFoundException e) {}
		catch (UnsupportedLookAndFeelException e) {} 
		catch (IllegalAccessException e) {}
        this.setVisible(true);
    }
	
	private void initComponents() {
		this.setIconImage(icone);  
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        tf_filePath = new javax.swing.JTextField();
        but_brow = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        but_start = new javax.swing.JButton();
        but_exit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Exécuter une opération de GeoProcessing");
        setResizable(false);

        jLabel1.setText("Définissez un fichier de sortie pour l'opération:");

        tf_filePath.setEditable(false);

        but_brow.setText("Parcourir...");
        but_start.setText("Démarrer");
        but_exit.setText("Fermer");
        
        but_start.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tf_filePath, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(but_brow, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(but_start)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(but_exit)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_filePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(but_brow))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(but_exit)
                    .addComponent(but_start))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }
	
	private void initEvents(){
		this.but_brow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	//tb_tasksActionPerformed(evt);
            	String ws_result = OCCToolRunner.getOutputDir("ws_result");
    			OCCToolRunner.createFolder(ws_result);
    			
            	JFileChooser defautChooser = new JFileChooser(ws_result);
				OpsimFilter shpFilter = new OpsimFilter("shp","Shapefile - Feature Class");
				if(toolName != "OccCal3DModel_basic")defautChooser.addChoosableFileFilter(shpFilter);
				
				switch(toolName){
					case "OccSitePosition":
						defautChooser.setSelectedFile(new File(ws_result+File.separator+"OpsimSitePosition.shp"));
						break;
					case "OccMultiSite":
						defautChooser.setSelectedFile(new File(ws_result+File.separator+"OpsimCalCovOutput.shp"));
						break;
					case "OccInterference":
						defautChooser.setSelectedFile(new File(ws_result+File.separator+"OpsimCalInterference.shp"));
						break;
					case "OccLinkProfile":
						break;
					case "OccCal3DModel_basic":
						break;
					default:
						break;
				}
				
				if(toolName == "OccCal3DModel_basic")defautChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if(toolName == "OccCal3DModel_basic" && defautChooser.showSaveDialog(null) == defautChooser.APPROVE_OPTION){
					file =  defautChooser.getSelectedFile();
					if(toolName == "OccCal3DModel_basic" && !file.exists()){
						tf_filePath.setText(file.getPath());
						but_start.setEnabled(true);
					}
					else{
						JOptionPane.showMessageDialog(null,"Erreur sur le choix du repertoire pour l'opération.\n" +
															"Veuillez sélectionner un repertoire non existant",
															"Erreur",JOptionPane.ERROR_MESSAGE);
					}
				}				
				else if(defautChooser.showSaveDialog(null) == defautChooser.APPROVE_OPTION){
					file =  defautChooser.getSelectedFile();
					System.out.println("Pathname File: "+file.getPath());
					if(shpFilter.accept(file) && !file.exists()){
						//System.out.println("New file selected");
						tf_filePath.setText(file.getPath());
						but_start.setEnabled(true);
					}					
					else{
						JOptionPane.showMessageDialog(null,"Erreur sur l'extension ou le nom du fichier à créer.\n" +
															"Veuillez definir un nouveau et respecter l'extension (.shp)",
															"Erreur",JOptionPane.ERROR_MESSAGE);
					}
				}
            }
        });
		
		this.but_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	This.setVisible(false);
            }
        });
		
		this.but_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	System.out.println("dialogSP: Start Event");            	
            	but_startActionPerformed(evt);
            }
        });
	}
	
	public void but_startActionPerformed(java.awt.event.ActionEvent evt){
		
		Thread t = (new Thread(new Runnable(){
			boolean isCancel = false;
			
			public void run(){
				This.setVisible(false);
				
				ArrayList<String> params = new ArrayList<String>();
				String ws_param = OCCToolRunner.getOutputDir("ws_param");
				String ws_interf = OCCToolRunner.getOutputDir("ws_interf");
				String ws_out = OCCToolRunner.getOutputDir("ws_out");
				String ws_main = OCCToolRunner.getOutputDir("ws_main");
				String ws_result = OCCToolRunner.getOutputDir("ws_result");	
				
				switch(toolName){
					case "OccSitePosition":
						// check inputs data:						
						params.add(layer2D_list.get("MNT").getDataSource());
						params.add(layer2D_list.get("MNS").getDataSource());
						params.add(layer2D_list.get("CLUT").getDataSource());
						params.add(ws_param+File.separator+"param_tx.shp");
						params.add(ws_param+File.separator+"planResultCov.shp");
						params.add("None");
						params.add(This.getFile().getPath());
						params.add(ws_out);
						opsimGUI.getMenIt_SelSite().setEnabled(false);
						break;
					case "OccMultiSite":
						params.add(layer2D_list.get("TX").getDataSource());
						params.add(layer2D_list.get("MNT").getDataSource());
						params.add(layer2D_list.get("MNS").getDataSource());
						params.add(layer2D_list.get("CLUT").getDataSource());
						params.add(ws_param+File.separator+"param_tx.shp");
						params.add(ws_param+File.separator+"planResultCov.shp");
						params.add(This.getFile().getPath());
						params.add(ws_out);
						params.add(ws_main);
						params.add(ws_param);
						opsimGUI.getMenIt_CalCovTx().setEnabled(false);
						break;
					case "OccInterference":
						params.add(layer2D_list.get("TX").getDataSource());
						params.add(layer2D_list.get("MNT").getDataSource());
						params.add(layer2D_list.get("MNS").getDataSource());
						params.add(layer2D_list.get("CLUT").getDataSource());
						params.add(ws_param+File.separator+"param_tx.shp");
						params.add(ws_param+File.separator+"planResultCov.shp");
						params.add(This.getFile().getPath());
						params.add(ws_out);
						params.add(ws_param);
						params.add(ws_main);						
						params.add(ws_interf);
						opsimGUI.getMenIt_AnaInterf().setEnabled(false);
						break;
					case "OccLinkProfile":
						break;
					case "OccCal3DModel_basic":
						String opsim_home = System.getenv("OPSIM_HOME");
						String gdb_in = opsim_home;
						gdb_in = gdb_in+File.separator+"gdb/opsim.gdb";	
						String gdb_out = "opsim.gdb";
						String folder_out = This.getFile().getPath();
						params.add(layer2D_list.get("MNT").getDataSource());
						params.add(layer2D_list.get("MNS").getDataSource());
						params.add(gdb_in);
						params.add(gdb_out);
						params.add(folder_out);
						opsimGUI.getMenIt_Gen3DModel_Basic().setEnabled(false);
						break;						
					default:
						break;
				}				
				
				opsimGUI.getBar().setIndeterminate(true);				
				dialogTasksGP = new DialogTasksGP(opsimGUI, false);
				dialogTasksGP.getjProgressBar1().setIndeterminate(true);
				dialogTasksGP.getBut_cancel().setEnabled(true);
				dialogTasksGP.getBut_exit().addActionListener(new java.awt.event.ActionListener() {
		            public void actionPerformed(java.awt.event.ActionEvent evt) {
		            	dialogTasksGP.setVisible(false);         	
		            }
		        });
				dialogTasksGP.getBut_cancel().addActionListener(new java.awt.event.ActionListener() {
		            public void actionPerformed(java.awt.event.ActionEvent evt) {
		            	toolRunner.stopTool();
		            	Toolkit.getDefaultToolkit().beep();
		            	dialogTasksGP.getTa_output().append("---------------------------------------------\n");
		            	dialogTasksGP.getTa_output().append("[~_~]:Traitement interrompu par l'utilisateur !\n");
		            	dialogTasksGP.getTa_output().append("---------------------------------------------\n");
		            	isCancel = true;
		            }
		        });
				
				opsimGUI.getTb_tasks().setEnabled(true);
				// Running GP
				toolRunner = new OCCToolRunner(toolName, params, dialogTasksGP);		
				toolRunner.runTool();
				dialogTasksGP.getjProgressBar1().setIndeterminate(false);
				dialogTasksGP.getBut_cancel().setEnabled(false);
				
				opsimGUI.getMenIt_SelSite().setEnabled(true);
				opsimGUI.getMenIt_CalCovTx().setEnabled(true);
				opsimGUI.getMenIt_AnaInterf().setEnabled(true);
				
				opsimGUI.getBar().setIndeterminate(false);
				opsimGUI.getTb_tasks().setEnabled(false);
				
				Toolkit.getDefaultToolkit().beep();				
				if(!isCancel && This.file.exists()){
					dialogTasksGP.getTa_output().append("---------------------------------------------\n");
	            	dialogTasksGP.getTa_output().append("[^_^]:Traitement terminé avec succès !\n");
	            	dialogTasksGP.getTa_output().append("---------------------------------------------\n");
	            	
	            	// Save new layer calculate
	            	ProjectDAO projDAO = (ProjectDAO) DAOFactory.getProjectDAO();
	            	ProjectController projConWS = (ProjectController) opsimGUI.getListControler().get("ProjectController");
	        		Project projWS = (Project) projConWS.getModel();
	            	if(toolName == "OccSitePosition"){
	            		double progress = projWS.getProgress();
	        			if(progress < 58.0)projWS.setProgress(58.0);
	            	}
	            	else if(toolName == "OccMultiSite"){
	            		double progress = projWS.getProgress();
	        			if(progress < 72.0)projWS.setProgress(72.0);
	            	}
	            	else if(toolName == "OccInterference"){
	            		double progress = projWS.getProgress();
//	        			if(progress < 86.0)projWS.setProgress(86.0);
	            		if(progress < 100.0)projWS.setProgress(100.0);
	            	}
	            	else if(toolName == "OccLinkProfile"){
	            		double progress = projWS.getProgress();
	        			if(progress < 100.0)projWS.setProgress(100.0);
	            	}
					
	            	
					switch(toolName){
						case "OccCal3DModel_basic":
							Layer lay_CE;
							if(layer3D_list.containsKey("CE"))lay_CE = layer3D_list.get("CE");
							else lay_CE = new Layer();
							lay_CE.setName("opsim.gdb");
							lay_CE.setDataSource(file.getAbsolutePath()+File.separator+"opsim.gdb");
							lay_CE.setDescription("CE - Basic 3D Model");
							lay_CE.setType("CE");
							layer3D_list.put("CE", lay_CE);
							map_List.get(1).setLayer(layer3D_list);
							map_List.get(1).setNumLayer(layer3D_list.size());
							JOptionPane.showMessageDialog(opsimGUI, "Création du modèle 3D basique terminé avec succès.\n" +
									"Veuillez actualiser le rendu ou consulter le dossier des resultats:\n" +ws_result,
									"OPSIM-CP: Génération du modèle 3D basique",
									JOptionPane.INFORMATION_MESSAGE);
							
							projWS.setMap(map_List);
							projWS.setUpdatedDate(new Date());
							projWS = projDAO.update(projWS);
							break;
						case "OccSitePosition":
							Layer lay_TX;
							if(layer2D_list.containsKey("TX"))lay_TX = layer2D_list.get("TX");
							else lay_TX = new Layer();
							lay_TX.setName(file.getName());
							lay_TX.setDataSource(file.getAbsolutePath());
							lay_TX.setDescription("TX - Site Layer");
							lay_TX.setType("TX");
							layer2D_list.put("TX", lay_TX);
							map_List.get(0).setLayer(layer2D_list);
							map_List.get(0).setNumLayer(layer2D_list.size());
							JOptionPane.showMessageDialog(opsimGUI, "Calcul des positions optimales des sites terminé avec succès.\n" +
									"Veuillez actualiser le rendu ou consulter le dossier des resultats:\n" +ws_result,
									"OPSIM-CP: Calcul des positions des sites",
									JOptionPane.INFORMATION_MESSAGE);
						
							projWS.setMap(map_List);
							projWS.setUpdatedDate(new Date());
							projWS = projDAO.update(projWS);
							break;
						case "OccMultiSite":
							Layer lay_COV;
							if(layer2D_list.containsKey("COV"))lay_COV = layer2D_list.get("COV");
							else lay_COV = new Layer();
							lay_COV.setName(file.getName());
							lay_COV.setDataSource(file.getAbsolutePath());
							lay_COV.setDescription("COV - RSL Coverage Layer");
							lay_COV.setType("COV");
							layer2D_list.put("COV", lay_COV);
							map_List.get(0).setLayer(layer2D_list);
							map_List.get(0).setNumLayer(layer2D_list.size());
							JOptionPane.showMessageDialog(opsimGUI, "Calcul de la prédiction de couverture radio (RSL) terminé avec succès.\n" +
									"Veuillez actualiser le rendu ou consulter le dossier des resultats:\n" +ws_result,
									"OPSIM-CP: Prédiction de couverture radio.",
									JOptionPane.INFORMATION_MESSAGE);							

							projWS.setMap(map_List);
							projWS.setUpdatedDate(new Date());
							projWS = projDAO.update(projWS);
							break;
						case "OccInterference":
							Layer lay_SINR;
							if(layer2D_list.containsKey("SINR"))lay_SINR = layer2D_list.get("SINR");
							else lay_SINR = new Layer();
							lay_SINR.setName(file.getName());
							lay_SINR.setDataSource(file.getAbsolutePath());
							lay_SINR.setDescription("SINR - C/I Interference Layer");
							lay_SINR.setType("SINR");
							layer2D_list.put("SINR", lay_SINR);
							map_List.get(0).setLayer(layer2D_list);
							map_List.get(0).setNumLayer(layer2D_list.size());
							JOptionPane.showMessageDialog(opsimGUI, "Calcul du niveau d'interférences (C/I) terminé avec succès.\n" +
									"Veuillez actualiser le rendu ou consulter le dossier des resultats:\n" +ws_result,
									"OPSIM-CP: Analyse d'interférence radio.",
									JOptionPane.INFORMATION_MESSAGE);							

							projWS.setMap(map_List);
							projWS.setUpdatedDate(new Date());
							projWS = projDAO.update(projWS);
							break;
					}
					
					// reload project layers on Map & Scene
					String opsim_home = System.getenv("OPSIM_HOME");
				    String blankMxd_path = opsim_home+File.separator+"/files/blank.mxd";
				    String blankSxd_path = opsim_home+File.separator+"/files/blank.sxd";
				  
				    try{
						opsimGUI.getOpsimMap().loadMxFile(blankMxd_path, null, null);
						opsimGUI.getOpsimScene().loadSxFile(blankSxd_path);
					} catch (Exception e) {
						e.printStackTrace();
					}
				    opsimGUI.getBar().setIndeterminate(true);
					opsimGUI.loadProjLayers(projWS);
					opsimGUI.enableMenuProj(projWS);
					
					final ArrayList<Site> list_site = opsimGUI.createListSite(projWS);
					projWS.setList_site(list_site);
					projConWS.setModel(projWS);
					opsimGUI.getListControler().remove("ProjectController");
					opsimGUI.getListControler().put("ProjectController", projConWS);
					
					final int n = list_site.size();
					if(n != 0){
						JTree treeTx = opsimGUI.changeTreeTx(list_site);					
						opsimGUI.getTree_tabTx().setModel(treeTx.getModel());
						opsimGUI.getTree_tabTx().expandRow(1);
						opsimGUI.getTree_tabTx().expandRow(n+2);
						opsimGUI.getTree_tabTx().expandRow(n+4);
						
						TreeSelectionListener[] listeners = opsimGUI.getTree_tabTx().getTreeSelectionListeners();
						for(int i=0; i<listeners.length;i++){
							opsimGUI.getTree_tabTx().removeTreeSelectionListener(listeners[i]);
						}
						
						opsimGUI.getTree_tabTx().addTreeSelectionListener(new TreeSelectionListener(){
							public void valueChanged(TreeSelectionEvent event) {				
								if(opsimGUI.getTree_tabTx().getLastSelectedPathComponent() != null){
									String sel_node = opsimGUI.getTree_tabTx().getLastSelectedPathComponent().toString();
									for(int i=0; i<n; i++){
										Site site = list_site.get(i);
										String nodeName = "ENB-"+site.getId()+
												"["+opsimGUI.round(site.getX(),3)+
												","+opsimGUI.round(site.getY(),3)+"]";
										if(nodeName.equals(sel_node))
											JOptionPane.showMessageDialog(This, "l'Enode-B presente les données suivantes: \n" +
													"ID site: "+site.getPoint_id()+"\n" +
													"Nom site: E-NodeB-"+site.getId()+"\n" +
													"Coordonnées géographiques [X,Y]: ["+opsimGUI.round(site.getX(),3)+
															","+opsimGUI.round(site.getY(),3)+"]\n" +
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
						JTree treeTx = opsimGUI.buildTreeTx();					
						opsimGUI.getTree_tabTx().setModel(treeTx.getModel());
						opsimGUI.getTree_tabTx().expandRow(1);
						opsimGUI.getTree_tabTx().expandRow(3);
						opsimGUI.getTree_tabTx().expandRow(5);
						TreeSelectionListener[] listeners = opsimGUI.getTree_tabTx().getTreeSelectionListeners();
						for(int i=0; i<listeners.length;i++){
							opsimGUI.getTree_tabTx().removeTreeSelectionListener(listeners[i]);
						}
					}					
					opsimGUI.getBar().setIndeterminate(false);
				}
				else if(!isCancel)
				{
					toolRunner.stopTool();
	            	Toolkit.getDefaultToolkit().beep();
	            	dialogTasksGP.getTa_output().append("---------------------------------------------\n");
	            	dialogTasksGP.getTa_output().append("[~_~]:Traitement interrompu par le système !\n");
	            	dialogTasksGP.getTa_output().append("---------------------------------------------\n");
	            	
	            	JOptionPane.showMessageDialog(null,"Une erreur s'est produite lors de l'exécution de l'opération.",
							"OPSIM: Exécution d'une opération",JOptionPane.ERROR_MESSAGE);
				}
				
				//dialogTasksGP.setVisible(false);
			}
		}));		
		t.start();	
	}
	
	public static void main(String[] args) {
		DialogStartGP dialogSP = new DialogStartGP(null, false, null, null);		
	}

	public javax.swing.JButton getBut_brow() {
		return but_brow;
	}

	public javax.swing.JButton getBut_start() {
		return but_start;
	}

	public javax.swing.JButton getBut_exit() {
		return but_exit;
	}

	public javax.swing.JTextField getTf_filePath() {
		return tf_filePath;
	}

	public File getFile() {
		return file;
	}

	public DialogTasksGP getdialogTasksGP() {
		return dialogTasksGP;
	}
	
	
	
		
}

