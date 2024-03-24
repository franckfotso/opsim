/**
 * 
 */
package cm.opsim.view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import cm.opsim.controller.ProjectController;
import cm.opsim.dao.DAO;
import cm.opsim.dao.DAOFactory;
import cm.opsim.dao.LayerDAO;
import cm.opsim.dao.ProjectDAO;
import cm.opsim.event.AbstractHandleEvent;
import cm.opsim.event.ProjectManHE;
import cm.opsim.model.Layer;
import cm.opsim.model.Map;
import cm.opsim.model.Profil;
import cm.opsim.model.Project;
import cm.opsim.observer.Observable;
import cm.opsim.observer.Observer;

import com.esri.arcgis.beans.map.MapBean;
import com.esri.arcgis.carto.FeatureLayer;
import com.esri.arcgis.carto.IFeatureLayer;
import com.esri.arcgis.carto.ILayer;
import com.esri.arcgis.carto.IRasterLayer;
import com.esri.arcgis.carto.RasterLayer;
import com.esri.arcgis.carto.esriViewDrawPhase;
import com.esri.arcgis.datasourcesfile.ShapefileWorkspaceFactory;
import com.esri.arcgis.datasourcesraster.RasterWorkspaceFactory;
import com.esri.arcgis.geodatabase.Workspace;

/**
 * @author Romuald FOTSO
 *
 */
public class DialogManProject extends JDialog implements Observer, Observable{
    // Variables declaration - do not modify                     
    private javax.swing.JButton but_browConP;
    private javax.swing.JButton but_cleanPE;
    private javax.swing.JButton but_delItemConP;
    private javax.swing.JButton but_delPE;
    private javax.swing.JButton but_delPI;
    private javax.swing.JButton but_exitMan;
    private javax.swing.JButton but_editPI;
    private javax.swing.JButton but_loadProj;
    private javax.swing.JButton but_newProj;
    private javax.swing.JButton but_openPE;
    private javax.swing.JButton but_saveProj;
    private javax.swing.JButton but_selectPI;
    private javax.swing.JCheckBox cb_manProj;
    private javax.swing.JPanel conPreviewMap;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lab_PE;
    private javax.swing.JLabel lab_PI;
    private javax.swing.JLabel lab_conP;
    private javax.swing.JLabel lab_previewProj;
    private javax.swing.JLabel lab_previewState;
    private javax.swing.JTable tabConP;
    private javax.swing.JTable tabPE;
    private javax.swing.JTable tabPI;
    private Image icone =  Toolkit.getDefaultToolkit().getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/logo.png"));
    private AbstractHandleEvent manProjHE;
    private OpsimGUI opsimGUI;
    
    private DialogManProject this_obj = this;
    private DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
    private File file;
    private FileSystemView systView = FileSystemView.getFileSystemView();
    private String laySelected = null;
    private ProjectController projController;
    private Project projModel = null;
    private ArrayList<Map> map_List = null;
    private Hashtable<String,Layer> layer2D_list = new Hashtable<String,Layer>();
    private Hashtable<String,Layer> layer3D_list = new Hashtable<String,Layer>();
    private String def_title = "Gestionnaire de projets";
    private ArrayList<Observer> listObserver = new ArrayList<Observer>();
    private MapBean prev_map = new MapBean();
	
	/**
	 * @param parent
	 * @param title
	 * @param modal
	 */
	public DialogManProject(OpsimGUI parent,boolean modal){		
		super(parent,modal);
		this.opsimGUI = parent;
		this.addObserver(parent);
		this.initComponent();
		this.setLocationRelativeTo(null);
		this.initEvent();
		try {
	           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	           SwingUtilities.updateComponentTreeUI(this);
	    } 
		catch (InstantiationException e) {} 
		catch (ClassNotFoundException e) {}
		catch (UnsupportedLookAndFeelException e) {} 
		catch (IllegalAccessException e) {}
		this.projController = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
		if(this.projModel != null){			
			this.projModel = (Project) this.projController.getModel();
			map_List = (ArrayList<Map>) this.projModel.getMap();
			layer2D_list = map_List.get(0).getLayer();
			layer3D_list = map_List.get(1).getLayer();
		}
		if(!this.opsimGUI.getUser().getProfil().isShowManProj())this.cb_manProj.setSelected(false);		
		this.setVisible(true);
	}
	
	/**
	 * 
	 */
	public void initComponent(){	
		
		this.setIconImage(icone);		
		lab_PI = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabPI = new javax.swing.JTable();
        lab_conP = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabConP = new javax.swing.JTable();
        but_delPI = new javax.swing.JButton();
        but_editPI = new javax.swing.JButton();
        but_selectPI = new javax.swing.JButton();
        but_delItemConP = new javax.swing.JButton();
        but_browConP = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lab_PE = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabPE = new javax.swing.JTable();
        lab_previewProj = new javax.swing.JLabel();
        conPreviewMap = new javax.swing.JPanel();
        lab_previewState = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        cb_manProj = new javax.swing.JCheckBox();
        but_newProj = new javax.swing.JButton();
        but_saveProj = new javax.swing.JButton();
        but_exitMan = new javax.swing.JButton();
        but_loadProj = new javax.swing.JButton();
        but_cleanPE = new javax.swing.JButton();
        but_delPE = new javax.swing.JButton();
        but_openPE = new javax.swing.JButton();
        
        but_loadProj.setEnabled(false);
        but_delItemConP.setEnabled(false);
        but_saveProj.setEnabled(false);
        rightRenderer.setHorizontalAlignment( JLabel.CENTER );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestionnaire de projets");
        setModal(true);
        setPreferredSize(new java.awt.Dimension(900, 570));
        setResizable(false);

        DAO<Project> projDAO = DAOFactory.getProjectDAO();
        List<Project> listProj = projDAO.findAll();

        ProjIntTableModel modelPI = new ProjIntTableModel(listProj);
        
        lab_PI.setText("Projects Internes:");        
        tabPI.setModel(modelPI);
        tabPI.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabPI.getColumnModel().getColumn(0).setPreferredWidth(140);
        tabPI.getColumnModel().getColumn(1).setPreferredWidth(80); 
        tabPI.getColumnModel().getColumn(1).setCellRenderer(rightRenderer); 
        tabPI.getColumnModel().getColumn(2).setPreferredWidth(50);
        tabPI.getColumnModel().getColumn(3).setPreferredWidth(142);        
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabPI.getModel());
        tabPI.setRowSorter(sorter);
        
        jScrollPane1.setViewportView(tabPI);

        lab_conP.setText("Contenus du projet:");

        tabConP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {" Modèle Numérique de Terrain (.shp)", null, "--"},
                {" Modèle Numérique de Surface (.shp)", null, "--"},
                {" Surface d'Elevation (.tif)", null, "--"},
                {" Couche Clutter (.shp)", null, "--"},
                {" Couche Sites (.shp)", null, "--"},
                {" Couche Image (.tif)", null, "--"},
                {" Couverture radio (.shp)", null, "--"},                
                {" Analyse d'Interférence (.shp)", null, "--"},
                {" Modèle 3D CityEngine (.gdb)", null, "--"},
                {" Palette de couleur(.shp)", null, "--"}
            },
            new String [] {
                "Element           ", "Nom du fichier", "Etat"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            public boolean isCellEditable(int row, int column) {
                return false;
             }
        });        
        tabConP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabConP.getColumnModel().getColumn(0).setPreferredWidth(200);
        tabConP.getColumnModel().getColumn(1).setPreferredWidth(137);   
        tabConP.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        tabConP.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        
        jScrollPane2.setViewportView(tabConP);

        but_delPI.setText("Supprimer");

        but_editPI.setText("Editer");

        but_selectPI.setText("Sélectionner");
        but_selectPI.setEnabled(false);

        but_delItemConP.setText("Supprimer");

        but_browConP.setText("Parcourir...");
        but_browConP.setEnabled(false);

        lab_PE.setText("Recents projets externes:");

        tabPE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nom du fichier", "Etat", "Modifié le"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Object.class
            };
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            public boolean isCellEditable(int row, int column) {
                return false;
             }
        });
        jScrollPane3.setViewportView(tabPE);

        lab_previewProj.setText("Aperçu du projet sélectionné ou en cours");

//        conPreviewMap.setBackground(new java.awt.Color(0, 0, 0));
//        conPreviewMap.setBorder(javax.swing.BorderFactory.
//        		createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 204, 255), null, null));
        prev_map.setBackground(new java.awt.Color(0, 0, 0));
        prev_map.setBorder(javax.swing.BorderFactory.
        		createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 204, 255), null, null));
        prev_map.setPreferredSize(new Dimension(250, 170));

        lab_previewState.setForeground(new java.awt.Color(255, 255, 255));
        lab_previewState.setText("Aucun aperçu disponible");

        javax.swing.GroupLayout conPreviewMapLayout = new javax.swing.GroupLayout(prev_map);
        prev_map.setLayout(conPreviewMapLayout);

        cb_manProj.setSelected(true);
        cb_manProj.setText("Ouvrir le Gestionnaire de Projets au démarrage");

        but_newProj.setText("Nouveau Projet");

        but_saveProj.setText("Sauver Projet");

        but_exitMan.setText("Annuler");

        but_loadProj.setText("Charger");

        but_cleanPE.setText("Vider Liste");

        but_delPE.setText("Supprimer");

        but_openPE.setText("Ouvrir projet extérieur...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(but_selectPI)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(but_editPI)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(but_delPI))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lab_PE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lab_PI)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                            .addComponent(jSeparator1))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(but_openPE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(but_delPE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(but_cleanPE)))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lab_conP)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(but_browConP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(but_delItemConP))
                    .addComponent(lab_previewProj)
                    .addComponent(jSeparator2)
                    .addComponent(prev_map, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator3)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(cb_manProj)
                .addGap(205, 205, 205)
                .addComponent(but_newProj)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(but_saveProj)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(but_exitMan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(but_loadProj)
                .addGap(0, 58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_PI)
                    .addComponent(lab_conP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(but_delPI)
                    .addComponent(but_editPI)
                    .addComponent(but_selectPI)
                    .addComponent(but_delItemConP)
                    .addComponent(but_browConP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 10, Short.MAX_VALUE)
                    .addComponent(jSeparator2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_PE)
                    .addComponent(lab_previewProj))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(prev_map, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(but_cleanPE)
                            .addComponent(but_delPE)
                            .addComponent(but_openPE))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_manProj)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(but_newProj)
                        .addComponent(but_saveProj)
                        .addComponent(but_exitMan)
                        .addComponent(but_loadProj)))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
	}
	
	/**
	 * 
	 */
	public void initEvent(){
		this.manProjHE = new ProjectManHE(this);
		
		this.but_exitMan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	but_exitManActionPerformed(evt);            	
            }
        });
		
		this.but_newProj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	but_newProjActionPerformed(evt);            	
            }
        });
		
		this.tabConP.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						tabConPValueChanged(event);  
					}
				}
		);
		
		this.but_browConP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	but_browConPActionPerformed(evt);            	
            }
        });
		
		this.but_selectPI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	but_selectPIActionPerformed(evt);            	
            }
        });
		
		this.tabPI.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						tabPIValueChanged(event);  
					}
				}
		);
		
		this.but_delPI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	but_delPIActionPerformed(evt);            	
            }
        });
		
		this.but_delItemConP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	but_delItemConPActionPerformed(evt);            	
            }
        });
		
		this.but_saveProj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	but_saveProjActionPerformed(evt);            	
            }
        });
		
		this.but_loadProj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	but_loadProjActionPerformed(evt);            	
            }
        });
		
		this.but_editPI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	but_editPIActionPerformed(evt);            	
            }
        });
		
	}
	
	public void but_editPIActionPerformed(java.awt.event.ActionEvent evt){
		int row_select = this.tabPI.getSelectedRow();
		String name_proj = tabPI.getModel().getValueAt(row_select,0).toString();
		String newName = "";
		newName = JOptionPane.showInputDialog(this.opsimGUI, "Veuillez définir un nouveau nom:",
				"OPSIM: Editer nom du projet", JOptionPane.QUESTION_MESSAGE, null, null, name_proj).toString();
		
		if(newName != ""){
			ProjectDAO projDAO = (ProjectDAO) DAOFactory.getProjectDAO();
			Project proj = projDAO.find(name_proj);
			proj.setName(newName);
			proj.setUpdatedDate(new Date());
			proj = projDAO.update(proj);			
			
			List<Project> listProj = projDAO.findAll();
	        ProjIntTableModel modelPI = new ProjIntTableModel(listProj); 
	        tabPI = new javax.swing.JTable();
	        tabPI.setModel(modelPI);
	        tabPI.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	        tabPI.getColumnModel().getColumn(0).setPreferredWidth(140);
	        tabPI.getColumnModel().getColumn(1).setPreferredWidth(80); 
	        tabPI.getColumnModel().getColumn(1).setCellRenderer(rightRenderer); 
	        tabPI.getColumnModel().getColumn(2).setPreferredWidth(50);
	        tabPI.getColumnModel().getColumn(3).setPreferredWidth(142);
	        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabPI.getModel());
	        tabPI.setRowSorter(sorter);
	        tabPI.getSelectionModel().addListSelectionListener(
					new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent event) {
							tabPIValueChanged(event);  
						}
					}
			);
	        jScrollPane1.setViewportView(tabPI);
			this.jScrollPane1.repaint();
		}
		else JOptionPane.showMessageDialog(null,"Veuillez définir un nom de projet correct.",
				"OPSIM: Editer nom du projet",JOptionPane.ERROR_MESSAGE);		
	}
	
	public void but_loadProjActionPerformed(java.awt.event.ActionEvent evt){
		this.projController.setModel(this.projModel);
		this.opsimGUI.getListControler().remove("ProjectController");
		this.opsimGUI.getListControler().put("ProjectController", this.projController);
		this.opsimGUI.setTitle(this.opsimGUI.getDefTitle()+
				" - "+this.projModel.getName()+".ops"+
				" - ["+this.opsimGUI.getUser().getName()+"]");
		this.setVisible(false);
		this.notifyObserver();
	}
	
	public void but_saveProjActionPerformed(java.awt.event.ActionEvent evt){
		ProjectDAO projDAO = (ProjectDAO) DAOFactory.getProjectDAO();
		ProjectController projConWS = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
		Project projWS = (Project) projConWS.getModel();
		projWS = projDAO.update(projWS);
		this.but_saveProj.setEnabled(false);
	}
	
	public void but_delItemConPActionPerformed(java.awt.event.ActionEvent evt){
		ProjectDAO projDAO = (ProjectDAO) DAOFactory.getProjectDAO();
		LayerDAO layerDAO = (LayerDAO) DAOFactory.getLayerDAO();
		int row_select = this.tabPI.getSelectedRow();
		String name_proj = tabPI.getModel().getValueAt(row_select,0).toString();
		
		if(this.projModel != null){
			int option = 99;
			option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer ce contenu ?\n", "Supprimer le contenu",
					 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(option != JOptionPane.NO_OPTION &&
					option != JOptionPane.CANCEL_OPTION &&
					option != JOptionPane.CLOSED_OPTION){
				Project proj = projDAO.find(name_proj);
				ArrayList<Map> mapList = (ArrayList<Map>) proj.getMap();
				Hashtable<String,Layer> layer2DList = mapList.get(0).getLayer();
				Hashtable<String,Layer> layer3DList = mapList.get(1).getLayer();
				Layer layer = null;
				if(this.laySelected == "MNE" || this.laySelected == "CE"){
					layer = layer3DList.get(this.laySelected);
				}
				else layer = layer2DList.get(this.laySelected);
				
				layerDAO.delete(layer);
				this.projModel = projDAO.find(name_proj);
				this.updateTabConP();
			}
		}
		else JOptionPane.showMessageDialog(this, "Veuillez sélectionner ou créer un nouveau projet", "Erreur",JOptionPane.ERROR_MESSAGE); 
				
	}
	
	public void but_delPIActionPerformed(java.awt.event.ActionEvent evt){
		ProjectDAO projDAO = (ProjectDAO) DAOFactory.getProjectDAO();
		int row_select = this.tabPI.getSelectedRow();
		String name_proj = tabPI.getModel().getValueAt(row_select,0).toString();
		
		int option = 99;
		option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer ce projet ?\n", "Supprimer le projet",
				 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(option != JOptionPane.NO_OPTION &&
				option != JOptionPane.CANCEL_OPTION &&
				option != JOptionPane.CLOSED_OPTION){						
			Project proj = projDAO.find(name_proj);
			projDAO.delete(proj);
			
			List<Project> listProj = projDAO.findAll();
			//System.out.println("Num Proj = "+listProj.size());
	        ProjIntTableModel modelPI = new ProjIntTableModel(listProj); 
	        tabPI = new javax.swing.JTable();
	        tabPI.setModel(modelPI);
	        tabPI.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	        tabPI.getColumnModel().getColumn(0).setPreferredWidth(140);
	        tabPI.getColumnModel().getColumn(1).setPreferredWidth(80); 
	        tabPI.getColumnModel().getColumn(1).setCellRenderer(rightRenderer); 
	        tabPI.getColumnModel().getColumn(2).setPreferredWidth(50);
	        tabPI.getColumnModel().getColumn(3).setPreferredWidth(142);
	        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabPI.getModel());
	        tabPI.setRowSorter(sorter);
	        tabPI.getSelectionModel().addListSelectionListener(
					new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent event) {
							tabPIValueChanged(event);  
						}
					}
			);
	        jScrollPane1.setViewportView(tabPI);
			this.jScrollPane1.repaint();				
		}		
	}
	
	public void but_selectPIActionPerformed(java.awt.event.ActionEvent evt){
		int row_select = this.tabPI.getSelectedRow();
		String name_proj = tabPI.getModel().getValueAt(row_select,0).toString();
		//System.out.println("Project Name="+name_proj);
		ProjectDAO projDAO = (ProjectDAO) DAOFactory.getProjectDAO();
		this.projModel = projDAO.find(name_proj);
		this.setTitle(this.def_title+" - "+name_proj);
		this.but_loadProj.setEnabled(true);		
		this.updateTabConP();
		
		String opsim_home = System.getenv("OPSIM_HOME");
		String blankMxd_path = opsim_home+File.separator+"files/blank.mxd";	    
//	    System.out.println("blankMxd_path: "+blankMxd_path);
	    
	    try {
			this.map_List = (ArrayList<Map>) this.projModel.getMap();
			if(!this.map_List.isEmpty()){
				this.layer2D_list = this.map_List.get(0).getLayer();
				if(!this.layer2D_list.isEmpty()){
					// Load layers on prev_map					
					ArrayList<String> ds_data = new ArrayList<String>();					
					prev_map.loadMxFile(blankMxd_path, null, null);
					
//    		        if(layer2D_list.containsKey("IMG")){
//    		        	ds_data = this.opsimGUI.calPathFile(layer2D_list.get("IMG").getDataSource());
//        		        this.load2DLayersOnPrev(ds_data, prev_map, "IMG");
//    		        }
//    		        if(layer2D_list.containsKey("CLUT")){
//    		        	ds_data = this.opsimGUI.calPathFile(layer2D_list.get("CLUT").getDataSource());
//        		        this.load2DLayersOnPrev(ds_data, prev_map, "CLUT");
//    		        }
//    		        if(layer2D_list.containsKey("MNT")){
//    		        	ds_data = this.opsimGUI.calPathFile(layer2D_list.get("MNT").getDataSource());
//        		        this.load2DLayersOnPrev(ds_data, prev_map, "MNT");
//    		        }
					 if(layer2D_list.containsKey("MNS")){
						 ds_data = this.opsimGUI.calPathFile(layer2D_list.get("MNS").getDataSource());
	        		     this.load2DLayersOnPrev(ds_data, prev_map, "MNS");
					 }
//					 if(layer2D_list.containsKey("COV")){
//						 ds_data = this.opsimGUI.calPathFile(layer2D_list.get("COV").getDataSource());
//	        		     this.load2DLayersOnPrev(ds_data, prev_map, "COV");
//					 }
//					 if(layer2D_list.containsKey("TX")){
//						 ds_data = this.opsimGUI.calPathFile(layer2D_list.get("TX").getDataSource());
//	        		     this.load2DLayersOnPrev(ds_data, prev_map, "TX");
//					 }
					 
					 prev_map.setExtent(this.prev_map.getFullExtent()); 
					 prev_map.refresh(esriViewDrawPhase.esriViewForeground, null, null);	
				}
				else{
					prev_map.loadMxFile(blankMxd_path, null, null);
				    prev_map.setExtent(this.prev_map.getFullExtent()); 
				    prev_map.refresh(esriViewDrawPhase.esriViewForeground, null, null);	
				}
			}
			else{				
			    prev_map.loadMxFile(blankMxd_path, null, null);
			    prev_map.setExtent(this.prev_map.getFullExtent()); 
			    prev_map.refresh(esriViewDrawPhase.esriViewForeground, null, null);				
			}
	    }
		catch (IOException e) {
			System.out.println("Error caughted in loading proj layers: "+e+" ");
		}
	}
	
	public void but_exitManActionPerformed(java.awt.event.ActionEvent evt){
		if(this.cb_manProj.isSelected())this.opsimGUI.getUser().getProfil().setShowManProj(true);
		else this.opsimGUI.getUser().getProfil().setShowManProj(false);
		DAO<Profil> profilDAO = DAOFactory.getProfilDAO();
		profilDAO.update(this.opsimGUI.getUser().getProfil());

		// check if Object & Table have same Data
		DAO<Project> projDAO = DAOFactory.getProjectDAO();
		ProjectController projConWS = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
		Project projWS = (Project) projConWS.getModel();
		if(projWS != null){
			Project projDBB = projDAO.find(projWS.getId());
			System.out.println("WS:0- Proj Date = "+projWS.getUpdatedDate());
			System.out.println("DBB:0- Proj Date = "+projDBB.getUpdatedDate());
			if(!projDBB.getUpdatedDate().equals(projWS.getUpdatedDate())){
				int option = 99;
				option = JOptionPane.showConfirmDialog(null, "Le projet crée ou sélectionné dispose des données non enregistrées.\n" +
						 "Voulez-vous les enregistrer avant de fermer ?", "Fermer le gestionnaire de projets",
						 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				System.out.println("WS:1- Proj Date = "+projWS.getUpdatedDate());
				System.out.println("DBB:1- Proj Date = "+projDBB.getUpdatedDate());
				if(option != JOptionPane.NO_OPTION &&
						option != JOptionPane.CANCEL_OPTION &&
						option != JOptionPane.CLOSED_OPTION){						
						projWS = projDAO.update(projWS);
				}
				else {
					projWS.setUpdatedDate(projDBB.getUpdatedDate());
					projConWS.setModel(projWS);
					this.opsimGUI.getListControler().remove("ProjectController");
					this.opsimGUI.getListControler().put("ProjectController", projConWS);
				}
				this.setVisible(false);
			}
			else this.setVisible(false);
		}
		else this.setVisible(false);		
	}
	
	public void but_newProjActionPerformed(java.awt.event.ActionEvent evt){
		//this.opsimGUI.setDialog_newProj(new DialogNewProj(this.opsimGUI,true));		
		(new Thread(new Runnable(){
			public void run(){
				DAO<Project> projDAO = DAOFactory.getProjectDAO();
				ProjectController projConWS = (ProjectController) this_obj.opsimGUI.getListControler().get("ProjectController");
				Project projWS = (Project) projConWS.getModel();
				if(projWS != null){
					int option = 99;
					option = JOptionPane.showConfirmDialog(null, "Voulez-vous enregistrer le projet en cours ?\n",
							"Créer un nouveau projet",
							 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(option != JOptionPane.NO_OPTION &&
							option != JOptionPane.CANCEL_OPTION &&
							option != JOptionPane.CLOSED_OPTION){						
							projWS = projDAO.update(projWS);
							DialogNewProj dialog = new DialogNewProj(opsimGUI, true,this_obj);
					}
					else if(option == JOptionPane.NO_OPTION){
						DialogNewProj dialog = new DialogNewProj(opsimGUI, true,this_obj);
					}
				}
				else {
					DialogNewProj dialog = new DialogNewProj(opsimGUI, true,this_obj);
				}
				
				System.out.println("End in Thread But_newProj ");
			}
		})).start();
		System.out.println("Back on but_newProj");
	}
	
	public void but_browConPActionPerformed(java.awt.event.ActionEvent evt){		
		switch(this.laySelected){			
			case "MNT":				
        		if(this.layer2D_list.containsKey("MXD") || this.layer3D_list.containsKey("SXD") ){
	        		// Throw JOptionPane Error Message
        			JOptionPane.showMessageDialog(this, "Ce projet est déjà lié à une couche MXD et/ou une couche SXD \n"+
        												"Veuillez le dissocier avant d'ajouter un nouvelle couche Raster ou Vecteur", "Erreur",JOptionPane.ERROR_MESSAGE);
				}
        		else {
        			// Add layer MNT
        			Layer lay_MNT;
    				if(this.projModel != null){
    					this.map_List = (ArrayList<Map>) this.projModel.getMap();
    					if(!this.map_List.isEmpty()){
    						this.layer2D_list = this.map_List.get(0).getLayer();
    						this.layer3D_list = this.map_List.get(1).getLayer();
    					}
    					else {
    						// Add 2D Map
    						Map map2D = new Map();
    						map2D.setName("2D Map");
    						this.map_List.add(map2D);
    						// Add 3D Map
    						Map map3D = new Map();
    						map3D.setName("3D Map");
    						this.map_List.add(map3D);
    						this.layer2D_list = new Hashtable<String,Layer>();
    						this.layer3D_list = new Hashtable<String,Layer>();
    						this.map_List.get(0).setLayer(layer2D_list);
    						this.map_List.get(1).setLayer(layer3D_list);
    					}
    					
    					JFileChooser defautChooser = new JFileChooser(this.systView.getDefaultDirectory());
    					OpsimFilter mntFilter = new OpsimFilter("shp","Shapefile - Feature Class");
    					defautChooser.addChoosableFileFilter(mntFilter);
    					if(defautChooser.showOpenDialog(null) == defautChooser.APPROVE_OPTION){
    						this.file =  defautChooser.getSelectedFile();
    						System.out.println("Pathname File: "+file.getPath());
    						if(mntFilter.accept(file)){
    							if(this.layer2D_list.containsKey("MNT"))lay_MNT = this.layer2D_list.get("MNT");
    							else lay_MNT = new Layer();
    							lay_MNT.setName(file.getName());
    							lay_MNT.setDataSource(file.getAbsolutePath());
    							lay_MNT.setDescription(mntFilter.getDescription());
    							lay_MNT.setType("MNT");
    							this.layer2D_list.put("MNT", lay_MNT);
    							this.map_List.get(0).setLayer(layer2D_list);
    							this.map_List.get(0).setNumLayer(layer2D_list.size());
    							
    							this.projModel.setMap(map_List);
    							this.projModel.setUpdatedDate(new Date());
    							this.projController.setModel(this.projModel);
    							this.opsimGUI.getListControler().remove("ProjectController");
    			    			this.opsimGUI.getListControler().put("ProjectController", this.projController);
    			    			
    			    			System.out.println("N. Layer2D: "+this.layer2D_list.size());
    			    			System.out.println("N. Layer3D: "+this.layer3D_list.size());
    			    			// update view
    			    			tabConP.setModel(new javax.swing.table.DefaultTableModel(
    			    					new Object [][] {
    				    		                {" Modèle Numérique de Terrain (.shp)",
    				    		                	(this.layer2D_list.containsKey("MNT"))?file.getAbsolutePath():null,
    		    		                			(this.layer2D_list.containsKey("MNT"))?((file.exists())?"OK":"WRONG"):"--"},
    				    		                {" Modèle Numérique de Surface (.shp)",
    		    			    		    	    (this.layer2D_list.containsKey("MNS"))?this.layer2D_list.get("MNS").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("MNS"))?
    		        			    		    			((new File(this.layer2D_list.get("MNS").getDataSource()).exists())?"OK":"WRONG"):"--"},
        			    		    			{" Surface d'Elevation (.tif)",
    		        			    		    	(this.layer3D_list.containsKey("MNE"))?this.layer3D_list.get("MNE").getDataSource():null,
    		            				    		(this.layer3D_list.containsKey("MNE"))?
    		            				    		   		((new File(this.layer3D_list.get("MNE").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Couche Clutter (.shp)",
    		        			    		    	(this.layer2D_list.containsKey("CLUT"))?this.layer2D_list.get("CLUT").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("CLUT"))?
    		        			    		    			((new File(this.layer2D_list.get("CLUT").getDataSource()).exists())?"OK":"WRONG"):"--"},
        			    		    			{" Couche Sites (.shp)",
    		        			    		    	(this.layer2D_list.containsKey("TX"))?this.layer2D_list.get("TX").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("TX"))?
    		        			    		    			((new File(this.layer2D_list.get("TX").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Couche Image (.tif)",
    		        			    		    	(this.layer2D_list.containsKey("IMG"))?this.layer2D_list.get("IMG").getDataSource():null,
    		        	        			    	(this.layer2D_list.containsKey("IMG"))?
    		        	        			    			((new File(this.layer2D_list.get("IMG").getDataSource()).exists())?"OK":"WRONG"):"--"},				    		    	            
    				    		                {" Couverture radio (.shp)",
    				    		    	            (this.layer2D_list.containsKey("COV"))?this.layer2D_list.get("COV").getDataSource():null,
    				        	        			(this.layer2D_list.containsKey("COV"))?
    				        	        					((new File(this.layer2D_list.get("COV").getDataSource()).exists())?"OK":"WRONG"):"--"},
		        	        					{" Analyse d'Interférence (.shp)",
    				        	        			(this.layer2D_list.containsKey("SINR"))?this.layer2D_list.get("SINR").getDataSource():null,
    			    			        	        (this.layer2D_list.containsKey("SINR"))?
    			    			        	        		((new File(this.layer2D_list.get("SINR").getDataSource()).exists())?"OK":"WRONG"):"--"},
		        	        					{" Modèle 3D CityEngine (.gdb)",
					    		    	            (this.layer3D_list.containsKey("CE"))?this.layer3D_list.get("CE").getDataSource():null,
					        	        			(this.layer3D_list.containsKey("CE"))?
    				    		    	            		((new File(this.layer3D_list.get("CE").getDataSource()).exists())?"OK":"WRONG"):"--"},    				    		               
    				    		                {" Palette de couleur(.shp)",
    			    			        	        (this.layer2D_list.containsKey("COL"))?this.layer2D_list.get("COL").getDataSource():null,
    			    		    			        (this.layer2D_list.containsKey("COL"))?
    			    		    			        		((new File(this.layer2D_list.get("COL").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		            },
    			    		            new String [] {
    			    		                "Element", "Nom du fichier", "Etat"
    			    		            }
    			    		     ){			    		            
    									private static final long serialVersionUID = 1L;
    									Class[] types = new Class [] {
    			    		                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
    			    		            };
    			    		            public Class getColumnClass(int columnIndex) {
    			    		                return types [columnIndex];
    			    		            }
    			    		            public boolean isCellEditable(int row, int column) {
    			    		                return false;
    			    		             }
    			    		        });        
    			    		        tabConP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    			    		        tabConP.getColumnModel().getColumn(0).setPreferredWidth(200);
    			    		        tabConP.getColumnModel().getColumn(1).setPreferredWidth(137);        
    			    		        tabConP.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
    			    		        tabConP.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
    			    		        
    			    		        jScrollPane2.setViewportView(tabConP);
    			    		        this.jScrollPane2.repaint();
    			    		        this.but_saveProj.setEnabled(true);
    			    		        
    			    		        ArrayList<String> ds_data = this.opsimGUI.calPathFile(layer2D_list.get("MNT").getDataSource());
    			    		        this.load2DLayersOnPrev(ds_data, prev_map, "MNT");
    						}
    						else JOptionPane.showMessageDialog(this, "Veuillez charger un fichier de type Shapefile", "Erreur",JOptionPane.ERROR_MESSAGE);
    						System.out.println("Open file Done");
    					}					
    				}
    				else JOptionPane.showMessageDialog(this, "Veuillez sélectionner ou créer un nouveau projet", "Erreur",JOptionPane.ERROR_MESSAGE); 
        		}
				break;
			case "MNS":
				if(this.layer2D_list.containsKey("MXD") || this.layer3D_list.containsKey("SXD") ){
	        		// Throw JOptionPane Error Message
        			JOptionPane.showMessageDialog(this, "Ce projet est déjà lié à un document MXD et/ou un document SXD \n"+
        												"Veuillez le dissocier avant d'ajouter un nouvelle couche Raster ou Vecteur", "Erreur",JOptionPane.ERROR_MESSAGE);
				}
        		else {
        			// Add layer
        			Layer lay_MNS;
    				if(this.projModel != null){
    					this.map_List = (ArrayList<Map>) this.projModel.getMap();
    					if(!this.map_List.isEmpty()){
    						this.layer2D_list = this.map_List.get(0).getLayer();
    						this.layer3D_list = this.map_List.get(1).getLayer();
    					}
    					else {
    						// Add 2D Map
    						Map map2D = new Map();
    						map2D.setName("2D Map");
    						this.map_List.add(map2D);
    						// Add 3D Map
    						Map map3D = new Map();
    						map3D.setName("3D Map");
    						this.map_List.add(map3D);
    						this.layer2D_list = new Hashtable<String,Layer>();
    						this.layer3D_list = new Hashtable<String,Layer>();
    						this.map_List.get(0).setLayer(layer2D_list);
    						this.map_List.get(1).setLayer(layer3D_list);
    					}
    					
    					JFileChooser defautChooser = new JFileChooser(this.systView.getDefaultDirectory());
    					OpsimFilter mnsFilter = new OpsimFilter("shp","Shapefile - Feature Class");
    					defautChooser.addChoosableFileFilter(mnsFilter);
    					if(defautChooser.showOpenDialog(null) == defautChooser.APPROVE_OPTION){
    						this.file =  defautChooser.getSelectedFile();
    						System.out.println("Pathname File: "+file.getPath());
    						if(mnsFilter.accept(file)){
    							if(this.layer2D_list.containsKey("MNS"))lay_MNS = this.layer2D_list.get("MNS");
    							else lay_MNS = new Layer();
    							lay_MNS.setName(file.getName());
    							lay_MNS.setDataSource(file.getAbsolutePath());
    							lay_MNS.setDescription(mnsFilter.getDescription());
    							lay_MNS.setType("MNS");
    							this.layer2D_list.put("MNS", lay_MNS);
    							this.map_List.get(0).setLayer(layer2D_list);
    							this.map_List.get(0).setNumLayer(layer2D_list.size());
    							
    							this.projModel.setMap(map_List);
    							this.projModel.setUpdatedDate(new Date());
    							this.projController.setModel(this.projModel);
    							this.opsimGUI.getListControler().remove("ProjectController");
    			    			this.opsimGUI.getListControler().put("ProjectController", this.projController);
    			    			
    			    			System.out.println("N. Layer2D: "+this.layer2D_list.size());
    			    			System.out.println("N. Layer3D: "+this.layer3D_list.size());
    			    			// update view
    			    			tabConP.setModel(new javax.swing.table.DefaultTableModel(
    			    					new Object [][] {
    				    		                {" Modèle Numérique de Terrain (.shp)",
    				    		                	(this.layer2D_list.containsKey("MNT"))?this.layer2D_list.get("MNT").getDataSource():null,
    		    		                			(this.layer2D_list.containsKey("MNT"))?
    		    		                					((new File(this.layer2D_list.get("MNT").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Modèle Numérique de Surface (.shp)",
    		    			    		    	    (this.layer2D_list.containsKey("MNS"))?file.getAbsolutePath():null,
    		        			    		    	(this.layer2D_list.containsKey("MNS"))?((file.exists())?"OK":"WRONG"):"--"},
		        			    		    	{" Surface d'Elevation (.tif)",
    		        			    		    	(this.layer3D_list.containsKey("MNE"))?this.layer3D_list.get("MNE").getDataSource():null,
    		            				    		(this.layer3D_list.containsKey("MNE"))?
    		            				    		   		((new File(this.layer3D_list.get("MNE").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Couche Clutter (.shp)",
    		        			    		    	(this.layer2D_list.containsKey("CLUT"))?this.layer2D_list.get("CLUT").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("CLUT"))?
    		        			    		    			((new File(this.layer2D_list.get("CLUT").getDataSource()).exists())?"OK":"WRONG"):"--"},
        			    		    			{" Couche Sites (.shp)",
    		        			    		    	(this.layer2D_list.containsKey("TX"))?this.layer2D_list.get("TX").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("TX"))?
    		        			    		    			((new File(this.layer2D_list.get("TX").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Couche Image (.tif)",
    		        			    		    	(this.layer2D_list.containsKey("IMG"))?this.layer2D_list.get("IMG").getDataSource():null,
    		        	        			    	(this.layer2D_list.containsKey("IMG"))?
    		        	        			    			((new File(this.layer2D_list.get("IMG").getDataSource()).exists())?"OK":"WRONG"):"--"},    				    		                			    		    	            
    				    		                {" Couverture radio (.shp)",
    				    		    	            (this.layer2D_list.containsKey("COV"))?this.layer2D_list.get("COV").getDataSource():null,
    				        	        			(this.layer2D_list.containsKey("COV"))?
    				        	        					((new File(this.layer2D_list.get("COV").getDataSource()).exists())?"OK":"WRONG"):"--"},		        	        					
    				    		                {" Analyse d'Interférence (.shp)",
    				        	        			(this.layer2D_list.containsKey("SINR"))?this.layer2D_list.get("SINR").getDataSource():null,
    			    			        	        (this.layer2D_list.containsKey("SINR"))?
    			    			        	        		((new File(this.layer2D_list.get("SINR").getDataSource()).exists())?"OK":"WRONG"):"--"},
	    			        	        		{" Modèle 3D CityEngine (.gdb)",
					    		    	            (this.layer3D_list.containsKey("CE"))?this.layer3D_list.get("CE").getDataSource():null,
					        	        			(this.layer3D_list.containsKey("CE"))?
    				    		    	            		((new File(this.layer3D_list.get("CE").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Palette de couleur(.shp)",
    			    			        	        (this.layer2D_list.containsKey("COL"))?this.layer2D_list.get("COL").getDataSource():null,
    			    		    			        (this.layer2D_list.containsKey("COL"))?
    			    		    			        		((new File(this.layer2D_list.get("COL").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		            },
    			    		            new String [] {
    			    		                "Element", "Nom du fichier", "Etat"
    			    		            }
    			    		     ){			    		            
    									private static final long serialVersionUID = 1L;
    									Class[] types = new Class [] {
    			    		                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
    			    		            };
    			    		            public Class getColumnClass(int columnIndex) {
    			    		                return types [columnIndex];
    			    		            }
    			    		            public boolean isCellEditable(int row, int column) {
    			    		                return false;
    			    		             }
    			    		        });        
    			    		        tabConP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    			    		        tabConP.getColumnModel().getColumn(0).setPreferredWidth(200);
    			    		        tabConP.getColumnModel().getColumn(1).setPreferredWidth(137);        
    			    		        tabConP.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
    			    		        tabConP.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
    			    		        
    			    		        jScrollPane2.setViewportView(tabConP);
    			    		        this.jScrollPane2.repaint();
    			    		        this.but_saveProj.setEnabled(true);
    			    		        
    			    		        ArrayList<String> ds_data = this.opsimGUI.calPathFile(layer2D_list.get("MNS").getDataSource());
    			    		        this.load2DLayersOnPrev(ds_data, prev_map, "MNS");
    						}
    						else JOptionPane.showMessageDialog(this, "Veuillez charger un fichier de type Shapefile", "Erreur",JOptionPane.ERROR_MESSAGE);
    						System.out.println("Open file Done");
    					}					
    				}
    				else JOptionPane.showMessageDialog(this, "Veuillez sélectionner ou créer un nouveau projet", "Erreur",JOptionPane.ERROR_MESSAGE);
        		}
				break;
			case "MNE":
				if(this.layer2D_list.containsKey("MXD") || this.layer3D_list.containsKey("SXD") ){
	        		// Throw JOptionPane Error Message
        			JOptionPane.showMessageDialog(this, "Ce projet est déjà lié à un document MXD et/ou un document SXD \n"+
        												"Veuillez le dissocier avant d'ajouter un nouvelle couche Raster ou Vecteur", "Erreur",JOptionPane.ERROR_MESSAGE);
				}
        		else {
        			// Add layer 
        			Layer lay_MNE;
    				if(this.projModel != null){
    					this.map_List = (ArrayList<Map>) this.projModel.getMap();
    					if(!this.map_List.isEmpty()){
    						this.layer2D_list = this.map_List.get(0).getLayer();
    						this.layer3D_list = this.map_List.get(1).getLayer();
    					}
    					else {
    						// Add 2D Map
    						Map map2D = new Map();
    						map2D.setName("2D Map");
    						this.map_List.add(map2D);
    						// Add 3D Map
    						Map map3D = new Map();
    						map3D.setName("3D Map");
    						this.map_List.add(map3D);
    						this.layer2D_list = new Hashtable<String,Layer>();
    						this.layer3D_list = new Hashtable<String,Layer>();
    						this.map_List.get(0).setLayer(layer2D_list);
    						this.map_List.get(1).setLayer(layer3D_list);
    					}
    					
    					JFileChooser defautChooser = new JFileChooser(this.systView.getDefaultDirectory());
    					OpsimFilter mneFilter = new OpsimFilter("tif","Fichier TIF");
    					defautChooser.addChoosableFileFilter(mneFilter);
    					if(defautChooser.showOpenDialog(null) == defautChooser.APPROVE_OPTION){
    						this.file =  defautChooser.getSelectedFile();
    						System.out.println("Pathname File: "+file.getPath());
    						if(mneFilter.accept(file)){
    							if(this.layer3D_list.containsKey("MNE"))lay_MNE = this.layer3D_list.get("MNE");
    							else lay_MNE = new Layer();
    							lay_MNE.setName(file.getName());
    							lay_MNE.setDataSource(file.getAbsolutePath());
    							lay_MNE.setDescription(mneFilter.getDescription());
    							lay_MNE.setType("MNE");
    							this.layer3D_list.put("MNE", lay_MNE);
    							this.map_List.get(1).setLayer(layer3D_list);
    							this.map_List.get(1).setNumLayer(layer3D_list.size());
    							
    							this.projModel.setMap(map_List);
    							this.projModel.setUpdatedDate(new Date());
    							this.projController.setModel(this.projModel);
    							this.opsimGUI.getListControler().remove("ProjectController");
    			    			this.opsimGUI.getListControler().put("ProjectController", this.projController);
    			    			
    			    			System.out.println("N. Layer2D: "+this.layer2D_list.size());
    			    			System.out.println("N. Layer3D: "+this.layer3D_list.size());
    			    			// update view
    			    			tabConP.setModel(new javax.swing.table.DefaultTableModel(
    			    					new Object [][] {
    				    		                {" Modèle Numérique de Terrain (.shp)",
    				    		                	(this.layer2D_list.containsKey("MNT"))?this.layer2D_list.get("MNT").getDataSource():null,
    		    		                			(this.layer2D_list.containsKey("MNT"))?
    		    		                					((new File(this.layer2D_list.get("MNT").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Modèle Numérique de Surface (.shp)",
    		    			    		    	    (this.layer2D_list.containsKey("MNS"))?this.layer2D_list.get("MNS").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("MNS"))?
    		        			    		    			((new File(this.layer2D_list.get("MNS").getDataSource()).exists())?"OK":"WRONG"):"--"},
        			    		    			{" Surface d'Elevation (.tif)",
    		        			    		    	(this.layer3D_list.containsKey("MNE"))?file.getAbsolutePath():null,
    		            				    		(this.layer3D_list.containsKey("MNE"))?((file.exists())?"OK":"WRONG"):"--"},
    				    		                {" Couche Clutter (.shp)",
    		        			    		    	(this.layer2D_list.containsKey("CLUT"))?this.layer2D_list.get("CLUT").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("CLUT"))?
    		        			    		    			((new File(this.layer2D_list.get("CLUT").getDataSource()).exists())?"OK":"WRONG"):"--"},
        			    		    			{" Couche Sites (.shp)",
    		        			    		    	(this.layer2D_list.containsKey("TX"))?this.layer2D_list.get("TX").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("TX"))?
    		        			    		    			((new File(this.layer2D_list.get("TX").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Couche Image (.tif)",
    		        			    		    	(this.layer2D_list.containsKey("IMG"))?this.layer2D_list.get("IMG").getDataSource():null,
    		        	        			    	(this.layer2D_list.containsKey("IMG"))?
    		        	        			    			((new File(this.layer2D_list.get("IMG").getDataSource()).exists())?"OK":"WRONG"):"--"},    				    		               				    		    	            
    				    		                {" Couverture radio (.shp)",
    				    		    	            (this.layer2D_list.containsKey("COV"))?this.layer2D_list.get("COV").getDataSource():null,
    				        	        			(this.layer2D_list.containsKey("COV"))?
    				        	        					((new File(this.layer2D_list.get("COV").getDataSource()).exists())?"OK":"WRONG"):"--"},		        	        					
    				    		                {" Analyse d'Interférence (.shp)",
    				        	        			(this.layer2D_list.containsKey("SINR"))?this.layer2D_list.get("SINR").getDataSource():null,
    			    			        	        (this.layer2D_list.containsKey("SINR"))?
    			    			        	        		((new File(this.layer2D_list.get("SINR").getDataSource()).exists())?"OK":"WRONG"):"--"},
	    			        	        		{" Modèle 3D CityEngine (.gdb)",
					    		    	            (this.layer3D_list.containsKey("CE"))?this.layer3D_list.get("CE").getDataSource():null,
					        	        			(this.layer3D_list.containsKey("CE"))?
    				    		    	            		((new File(this.layer3D_list.get("CE").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Palette de couleur(.shp)",
    			    			        	        (this.layer2D_list.containsKey("COL"))?this.layer2D_list.get("COL").getDataSource():null,
    			    		    			        (this.layer2D_list.containsKey("COL"))?
    			    		    			        		((new File(this.layer2D_list.get("COL").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		            },
    			    		            new String [] {
    			    		                "Element", "Nom du fichier", "Etat"
    			    		            }
    			    		     ){			    		            
    									private static final long serialVersionUID = 1L;
    									Class[] types = new Class [] {
    			    		                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
    			    		            };
    			    		            public Class getColumnClass(int columnIndex) {
    			    		                return types [columnIndex];
    			    		            }
    			    		            public boolean isCellEditable(int row, int column) {
    			    		                return false;
    			    		             }
    			    		        });        
    			    		        tabConP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    			    		        tabConP.getColumnModel().getColumn(0).setPreferredWidth(200);
    			    		        tabConP.getColumnModel().getColumn(1).setPreferredWidth(137);        
    			    		        tabConP.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
    			    		        tabConP.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
    			    		        
    			    		        jScrollPane2.setViewportView(tabConP);
    			    		        this.jScrollPane2.repaint();
    			    		        this.but_saveProj.setEnabled(true);
    						}
    						else JOptionPane.showMessageDialog(this, "Veuillez charger un fichier de type Image(.tif)", "Erreur",JOptionPane.ERROR_MESSAGE);
    						System.out.println("Open file Done");
    					}					
    				}
    				else JOptionPane.showMessageDialog(this, "Veuillez sélectionner ou créer un nouveau projet", "Erreur",JOptionPane.ERROR_MESSAGE);
        		}
				break;
			case "CLUT":
				if(this.layer2D_list.containsKey("MXD") || this.layer3D_list.containsKey("SXD") ){
	        		// Throw JOptionPane Error Message
        			JOptionPane.showMessageDialog(this, "Ce projet est déjà lié à un document MXD et/ou un document SXD \n"+
        												"Veuillez le dissocier avant d'ajouter un nouvelle couche Raster ou Vecteur", "Erreur",JOptionPane.ERROR_MESSAGE);
				}
        		else {
        			// Add layer
        			Layer lay_CLUT;
    				if(this.projModel != null){
    					this.map_List = (ArrayList<Map>) this.projModel.getMap();
    					if(!this.map_List.isEmpty()){
    						this.layer2D_list = this.map_List.get(0).getLayer();
    						this.layer3D_list = this.map_List.get(1).getLayer();
    					}
    					else {
    						// Add 2D Map
    						Map map2D = new Map();
    						map2D.setName("2D Map");
    						this.map_List.add(map2D);
    						// Add 3D Map
    						Map map3D = new Map();
    						map3D.setName("3D Map");
    						this.map_List.add(map3D);
    						this.layer2D_list = new Hashtable<String,Layer>();
    						this.layer3D_list = new Hashtable<String,Layer>();
    						this.map_List.get(0).setLayer(layer2D_list);
    						this.map_List.get(1).setLayer(layer3D_list);
    					}
    					
    					JFileChooser defautChooser = new JFileChooser(this.systView.getDefaultDirectory());
    					OpsimFilter clutFilter = new OpsimFilter("shp","Shapefile - Feature Class");
    					defautChooser.addChoosableFileFilter(clutFilter);
    					if(defautChooser.showOpenDialog(null) == defautChooser.APPROVE_OPTION){
    						this.file =  defautChooser.getSelectedFile();
    						System.out.println("Pathname File: "+file.getPath());
    						if(clutFilter.accept(file)){
    							if(this.layer2D_list.containsKey("CLUT"))lay_CLUT = this.layer2D_list.get("CLUT");
    							else lay_CLUT = new Layer();
    							lay_CLUT.setName(file.getName());
    							lay_CLUT.setDataSource(file.getAbsolutePath());
    							lay_CLUT.setDescription(clutFilter.getDescription());
    							lay_CLUT.setType("CLUT");
    							this.layer2D_list.put("CLUT", lay_CLUT);
    							this.map_List.get(0).setLayer(layer2D_list);
    							this.map_List.get(0).setNumLayer(layer2D_list.size());
    							
    							this.projModel.setMap(map_List);
    							this.projModel.setUpdatedDate(new Date());
    							this.projController.setModel(this.projModel);
    							this.opsimGUI.getListControler().remove("ProjectController");
    			    			this.opsimGUI.getListControler().put("ProjectController", this.projController);
    			    			
    			    			System.out.println("N. Layer2D: "+this.layer2D_list.size());
    			    			System.out.println("N. Layer3D: "+this.layer3D_list.size());
    			    			// update view
    			    			tabConP.setModel(new javax.swing.table.DefaultTableModel(
    			    					new Object [][] {
    				    		                {" Modèle Numérique de Terrain (.shp)",
    				    		                	(this.layer2D_list.containsKey("MNT"))?this.layer2D_list.get("MNT").getDataSource():null,
    		    		                			(this.layer2D_list.containsKey("MNT"))?
    		    		                					((new File(this.layer2D_list.get("MNT").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Modèle Numérique de Surface (.shp)",
    		    			    		    	    (this.layer2D_list.containsKey("MNS"))?this.layer2D_list.get("MNS").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("MNS"))?
    		        			    		    			((new File(this.layer2D_list.get("MNS").getDataSource()).exists())?"OK":"WRONG"):"--"},
        			    		    			{" Surface d'Elevation (.tif)",
    		        			    		    	(this.layer3D_list.containsKey("MNE"))?this.layer3D_list.get("MNE").getDataSource():null,
    		            				    		(this.layer3D_list.containsKey("MNE"))?
    		            				    		   		((new File(this.layer3D_list.get("MNE").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Couche Clutter (.shp)",
    		        			    		    	(this.layer2D_list.containsKey("CLUT"))?file.getAbsolutePath():null,
    		        			    		    	(this.layer2D_list.containsKey("CLUT"))?((file.exists())?"OK":"WRONG"):"--"},
		        			    		    	{" Couche Sites (.shp)",
    		        			    		    	(this.layer2D_list.containsKey("TX"))?this.layer2D_list.get("TX").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("TX"))?
    		        			    		    			((new File(this.layer2D_list.get("TX").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Couche Image (.tif)",
    		        			    		    	(this.layer2D_list.containsKey("IMG"))?this.layer2D_list.get("IMG").getDataSource():null,
    		        	        			    	(this.layer2D_list.containsKey("IMG"))?
    		        	        			    			((new File(this.layer2D_list.get("IMG").getDataSource()).exists())?"OK":"WRONG"):"--"},    				    		               				    		    	            
    				    		                {" Couverture radio (.shp)",
    				    		    	            (this.layer2D_list.containsKey("COV"))?this.layer2D_list.get("COV").getDataSource():null,
    				        	        			(this.layer2D_list.containsKey("COV"))?
    				        	        					((new File(this.layer2D_list.get("COV").getDataSource()).exists())?"OK":"WRONG"):"--"},		        	        					
    				    		                {" Analyse d'Interférence (.shp)",
    				        	        			(this.layer2D_list.containsKey("SINR"))?this.layer2D_list.get("SINR").getDataSource():null,
    			    			        	        (this.layer2D_list.containsKey("SINR"))?
    			    			        	        		((new File(this.layer2D_list.get("SINR").getDataSource()).exists())?"OK":"WRONG"):"--"},
	    			        	        		{" Modèle 3D CityEngine (.gdb)",
					    		    	            (this.layer3D_list.containsKey("CE"))?this.layer3D_list.get("CE").getDataSource():null,
					        	        			(this.layer3D_list.containsKey("CE"))?
    				    		    	            		((new File(this.layer3D_list.get("CE").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Palette de couleur(.shp)",
    			    			        	        (this.layer2D_list.containsKey("COL"))?this.layer2D_list.get("COL").getDataSource():null,
    			    		    			        (this.layer2D_list.containsKey("COL"))?
    			    		    			        		((new File(this.layer2D_list.get("COL").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		            },
    			    		            new String [] {
    			    		                "Element", "Nom du fichier", "Etat"
    			    		            }
    			    		     ){			    		            
    									private static final long serialVersionUID = 1L;
    									Class[] types = new Class [] {
    			    		                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
    			    		            };
    			    		            public Class getColumnClass(int columnIndex) {
    			    		                return types [columnIndex];
    			    		            }
    			    		            public boolean isCellEditable(int row, int column) {
    			    		                return false;
    			    		             }
    			    		        });        
    			    		        tabConP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    			    		        tabConP.getColumnModel().getColumn(0).setPreferredWidth(200);
    			    		        tabConP.getColumnModel().getColumn(1).setPreferredWidth(137);        
    			    		        tabConP.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
    			    		        tabConP.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
    			    		        
    			    		        jScrollPane2.setViewportView(tabConP);
    			    		        this.jScrollPane2.repaint();
    			    		        this.but_saveProj.setEnabled(true);
    			    		        
    			    		        ArrayList<String> ds_data = this.opsimGUI.calPathFile(layer2D_list.get("CLUT").getDataSource());
    			    		        this.load2DLayersOnPrev(ds_data, prev_map, "CLUT");
    						}
    						else JOptionPane.showMessageDialog(this, "Veuillez charger un fichier de type Shapefile", "Erreur",JOptionPane.ERROR_MESSAGE);
    						System.out.println("Open file Done");
    					}					
    				}
    				else JOptionPane.showMessageDialog(this, "Veuillez sélectionner ou créer un nouveau projet", "Erreur",JOptionPane.ERROR_MESSAGE);
        		}
				break;
			case "TX":
				if(this.layer2D_list.containsKey("MXD") || this.layer3D_list.containsKey("SXD") ){
	        		// Throw JOptionPane Error Message
        			JOptionPane.showMessageDialog(this, "Ce projet est déjà lié à un document MXD et/ou un document SXD \n"+
        												"Veuillez le dissocier avant d'ajouter un nouvelle couche Raster ou Vecteur", "Erreur",JOptionPane.ERROR_MESSAGE);
				}
        		else {
        			// Add layer
        			Layer lay_TX;
    				if(this.projModel != null){
    					this.map_List = (ArrayList<Map>) this.projModel.getMap();
    					if(!this.map_List.isEmpty()){
    						this.layer2D_list = this.map_List.get(0).getLayer();
    						this.layer3D_list = this.map_List.get(1).getLayer();
    					}
    					else {
    						// Add 2D Map
    						Map map2D = new Map();
    						map2D.setName("2D Map");
    						this.map_List.add(map2D);
    						// Add 3D Map
    						Map map3D = new Map();
    						map3D.setName("3D Map");
    						this.map_List.add(map3D);
    						this.layer2D_list = new Hashtable<String,Layer>();
    						this.layer3D_list = new Hashtable<String,Layer>();
    						this.map_List.get(0).setLayer(layer2D_list);
    						this.map_List.get(1).setLayer(layer3D_list);
    					}
    					
    					JFileChooser defautChooser = new JFileChooser(this.systView.getDefaultDirectory());
    					OpsimFilter clutFilter = new OpsimFilter("shp","Shapefile - Feature Class");
    					defautChooser.addChoosableFileFilter(clutFilter);
    					if(defautChooser.showOpenDialog(null) == defautChooser.APPROVE_OPTION){
    						this.file =  defautChooser.getSelectedFile();
    						System.out.println("Pathname File: "+file.getPath());
    						if(clutFilter.accept(file)){
    							if(this.layer2D_list.containsKey("TX"))lay_TX = this.layer2D_list.get("TX");
    							else lay_TX = new Layer();
    							lay_TX.setName(file.getName());
    							lay_TX.setDataSource(file.getAbsolutePath());
    							lay_TX.setDescription(clutFilter.getDescription());
    							lay_TX.setType("TX");
    							this.layer2D_list.put("TX", lay_TX);
    							this.map_List.get(0).setLayer(layer2D_list);
    							this.map_List.get(0).setNumLayer(layer2D_list.size());
    							
    							this.projModel.setMap(map_List);
    							this.projModel.setUpdatedDate(new Date());
    							this.projController.setModel(this.projModel);
    							this.opsimGUI.getListControler().remove("ProjectController");
    			    			this.opsimGUI.getListControler().put("ProjectController", this.projController);
    			    			
    			    			System.out.println("N. Layer2D: "+this.layer2D_list.size());
    			    			System.out.println("N. Layer3D: "+this.layer3D_list.size());
    			    			// update view
    			    			tabConP.setModel(new javax.swing.table.DefaultTableModel(
    			    					new Object [][] {
    				    		                {" Modèle Numérique de Terrain (.shp)",
    				    		                	(this.layer2D_list.containsKey("MNT"))?this.layer2D_list.get("MNT").getDataSource():null,
    		    		                			(this.layer2D_list.containsKey("MNT"))?
    		    		                					((new File(this.layer2D_list.get("MNT").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Modèle Numérique de Surface (.shp)",
    		    			    		    	    (this.layer2D_list.containsKey("MNS"))?this.layer2D_list.get("MNS").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("MNS"))?
    		        			    		    			((new File(this.layer2D_list.get("MNS").getDataSource()).exists())?"OK":"WRONG"):"--"},
        			    		    			{" Surface d'Elevation (.tif)",
    		        			    		    	(this.layer3D_list.containsKey("MNE"))?this.layer3D_list.get("MNE").getDataSource():null,
    		            				    		(this.layer3D_list.containsKey("MNE"))?
    		            				    		   		((new File(this.layer3D_list.get("MNE").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Couche Clutter (.shp)",
            				    		   			(this.layer2D_list.containsKey("CLUT"))?this.layer2D_list.get("CLUT").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("CLUT"))?
    		        			    		    			((new File(this.layer2D_list.get("CLUT").getDataSource()).exists())?"OK":"WRONG"):"--"},    		        			    		    	
		        			    		    	{" Couche Sites (.shp)",
    		        			    		    	(this.layer2D_list.containsKey("TX"))?file.getAbsolutePath():null,
    		        			    		    	(this.layer2D_list.containsKey("TX"))?((file.exists())?"OK":"WRONG"):"--"},
    				    		                {" Couche Image (.tif)",
    		        			    		    	(this.layer2D_list.containsKey("IMG"))?this.layer2D_list.get("IMG").getDataSource():null,
    		        	        			    	(this.layer2D_list.containsKey("IMG"))?
    		        	        			    			((new File(this.layer2D_list.get("IMG").getDataSource()).exists())?"OK":"WRONG"):"--"},    				    		               				    		    	            
    				    		                {" Couverture radio (.shp)",
    				    		    	            (this.layer2D_list.containsKey("COV"))?this.layer2D_list.get("COV").getDataSource():null,
    				        	        			(this.layer2D_list.containsKey("COV"))?
    				        	        					((new File(this.layer2D_list.get("COV").getDataSource()).exists())?"OK":"WRONG"):"--"},		        	        					
    				    		                {" Analyse d'Interférence (.shp)",
    				        	        			(this.layer2D_list.containsKey("SINR"))?this.layer2D_list.get("SINR").getDataSource():null,
    			    			        	        (this.layer2D_list.containsKey("SINR"))?
    			    			        	        		((new File(this.layer2D_list.get("SINR").getDataSource()).exists())?"OK":"WRONG"):"--"},
	    			        	        		{" Modèle 3D CityEngine (.gdb)",
					    		    	            (this.layer3D_list.containsKey("CE"))?this.layer3D_list.get("CE").getDataSource():null,
					        	        			(this.layer3D_list.containsKey("CE"))?
    				    		    	            		((new File(this.layer3D_list.get("CE").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Palette de couleur(.shp)",
    			    			        	        (this.layer2D_list.containsKey("COL"))?this.layer2D_list.get("COL").getDataSource():null,
    			    		    			        (this.layer2D_list.containsKey("COL"))?
    			    		    			        		((new File(this.layer2D_list.get("COL").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		            },
    			    		            new String [] {
    			    		                "Element", "Nom du fichier", "Etat"
    			    		            }
    			    		     ){			    		            
    									private static final long serialVersionUID = 1L;
    									Class[] types = new Class [] {
    			    		                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
    			    		            };
    			    		            public Class getColumnClass(int columnIndex) {
    			    		                return types [columnIndex];
    			    		            }
    			    		            public boolean isCellEditable(int row, int column) {
    			    		                return false;
    			    		             }
    			    		        });        
    			    		        tabConP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    			    		        tabConP.getColumnModel().getColumn(0).setPreferredWidth(200);
    			    		        tabConP.getColumnModel().getColumn(1).setPreferredWidth(137);        
    			    		        tabConP.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
    			    		        tabConP.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
    			    		        
    			    		        jScrollPane2.setViewportView(tabConP);
    			    		        this.jScrollPane2.repaint();
    			    		        this.but_saveProj.setEnabled(true);
    			    		        
    			    		        ArrayList<String> ds_data = this.opsimGUI.calPathFile(layer2D_list.get("CLUT").getDataSource());
    			    		        this.load2DLayersOnPrev(ds_data, prev_map, "CLUT");
    						}
    						else JOptionPane.showMessageDialog(this, "Veuillez charger un fichier de type Shapefile", "Erreur",JOptionPane.ERROR_MESSAGE);
    						System.out.println("Open file Done");
    					}					
    				}
    				else JOptionPane.showMessageDialog(this, "Veuillez sélectionner ou créer un nouveau projet", "Erreur",JOptionPane.ERROR_MESSAGE);
        		}
				break;
			case "IMG":
				if(this.layer2D_list.containsKey("MXD") || this.layer3D_list.containsKey("SXD") ){
	        		// Throw JOptionPane Error Message
        			JOptionPane.showMessageDialog(this, "Ce projet est déjà lié à un document MXD et/ou un document SXD \n"+
        												"Veuillez le dissocier avant d'ajouter un nouvelle couche Raster ou Vecteur", "Erreur",JOptionPane.ERROR_MESSAGE);
				}
        		else {
        			// Add layer
        			Layer lay_IMG;
    				if(this.projModel != null){
    					this.map_List = (ArrayList<Map>) this.projModel.getMap();
    					if(!this.map_List.isEmpty()){
    						this.layer2D_list = this.map_List.get(0).getLayer();
    						this.layer3D_list = this.map_List.get(1).getLayer();
    					}
    					else {
    						// Add 2D Map
    						Map map2D = new Map();
    						map2D.setName("2D Map");
    						this.map_List.add(map2D);
    						// Add 3D Map
    						Map map3D = new Map();
    						map3D.setName("3D Map");
    						this.map_List.add(map3D);
    						this.layer2D_list = new Hashtable<String,Layer>();
    						this.layer3D_list = new Hashtable<String,Layer>();
    						this.map_List.get(0).setLayer(layer2D_list);
    						this.map_List.get(1).setLayer(layer3D_list);
    					}
    					
    					JFileChooser defautChooser = new JFileChooser(this.systView.getDefaultDirectory());
    					OpsimFilter imgFilter = new OpsimFilter("tif","Fichier TIF");
    					defautChooser.addChoosableFileFilter(imgFilter);
    					if(defautChooser.showOpenDialog(null) == defautChooser.APPROVE_OPTION){
    						this.file =  defautChooser.getSelectedFile();
    						System.out.println("Pathname File: "+file.getPath());
    						if(imgFilter.accept(file)){
    							if(this.layer2D_list.containsKey("IMG"))lay_IMG = this.layer2D_list.get("IMG");
    							else lay_IMG = new Layer();
    							lay_IMG.setName(file.getName());
    							lay_IMG.setDataSource(file.getAbsolutePath());
    							lay_IMG.setDescription(imgFilter.getDescription());
    							lay_IMG.setType("IMG");
    							this.layer2D_list.put("IMG", lay_IMG);
    							this.map_List.get(0).setLayer(layer2D_list);
    							this.map_List.get(0).setNumLayer(layer2D_list.size());
    							
    							this.projModel.setMap(map_List);
    							this.projModel.setUpdatedDate(new Date());
    							this.projController.setModel(this.projModel);
    							this.opsimGUI.getListControler().remove("ProjectController");
    			    			this.opsimGUI.getListControler().put("ProjectController", this.projController);
    			    			// update view
    			    			tabConP.setModel(new javax.swing.table.DefaultTableModel(
    			    					new Object [][] {
    				    		                {" Modèle Numérique de Terrain (.shp)",
    				    		                	(this.layer2D_list.containsKey("MNT"))?this.layer2D_list.get("MNT").getDataSource():null,
    		    		                			(this.layer2D_list.containsKey("MNT"))?
    		    		                					((new File(this.layer2D_list.get("MNT").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Modèle Numérique de Surface (.shp)",
    		    			    		    	    (this.layer2D_list.containsKey("MNS"))?this.layer2D_list.get("MNS").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("MNS"))?
    		        			    		    			((new File(this.layer2D_list.get("MNS").getDataSource()).exists())?"OK":"WRONG"):"--"},
        			    		    			{" Surface d'Elevation (.tif)",
    		        			    		    	(this.layer3D_list.containsKey("MNE"))?this.layer3D_list.get("MNE").getDataSource():null,
    		            				    		(this.layer3D_list.containsKey("MNE"))?
    		            				    		   		((new File(this.layer3D_list.get("MNE").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Couche Clutter (.shp)",
    		        			    		    	(this.layer2D_list.containsKey("CLUT"))?this.layer2D_list.get("CLUT").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("CLUT"))?
    		        			    		    			((new File(this.layer2D_list.get("CLUT").getDataSource()).exists())?"OK":"WRONG"):"--"},
        			    		    			{" Couche Sites (.shp)",
    		        			    		    	(this.layer2D_list.containsKey("TX"))?this.layer2D_list.get("TX").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("TX"))?
    		        			    		    			((new File(this.layer2D_list.get("TX").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Couche Image (.tif)",
    		        			    		    	(this.layer2D_list.containsKey("IMG"))?file.getAbsolutePath():null,
    		        	        			    	(this.layer2D_list.containsKey("IMG"))?((file.exists())?"OK":"WRONG"):"--"},    				    		                			    		    	            
    				    		                {" Couverture radio (.shp)",
    				    		    	            (this.layer2D_list.containsKey("COV"))?this.layer2D_list.get("COV").getDataSource():null,
    				        	        			(this.layer2D_list.containsKey("COV"))?
    				        	        					((new File(this.layer2D_list.get("COV").getDataSource()).exists())?"OK":"WRONG"):"--"},		        	        					
    				    		                {" Analyse d'Interférence (.shp)",
    				        	        			(this.layer2D_list.containsKey("SINR"))?this.layer2D_list.get("SINR").getDataSource():null,
    			    			        	        (this.layer2D_list.containsKey("SINR"))?
    			    			        	        		((new File(this.layer2D_list.get("SINR").getDataSource()).exists())?"OK":"WRONG"):"--"},
	    			        	        		{" Modèle 3D CityEngine (.gdb)",
					    		    	            (this.layer3D_list.containsKey("CE"))?this.layer3D_list.get("CE").getDataSource():null,
					        	        			(this.layer3D_list.containsKey("CE"))?
    				    		    	            		((new File(this.layer3D_list.get("CE").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Palette de couleur(.shp)",
    			    			        	        (this.layer2D_list.containsKey("COL"))?this.layer2D_list.get("COL").getDataSource():null,
    			    		    			        (this.layer2D_list.containsKey("COL"))?
    			    		    			        		((new File(this.layer2D_list.get("COL").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		            },
    			    		            new String [] {
    			    		                "Element", "Nom du fichier", "Etat"
    			    		            }
    			    		     ){			    		            
    									private static final long serialVersionUID = 1L;
    									Class[] types = new Class [] {
    			    		                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
    			    		            };
    			    		            public Class getColumnClass(int columnIndex) {
    			    		                return types [columnIndex];
    			    		            }
    			    		            public boolean isCellEditable(int row, int column) {
    			    		                return false;
    			    		             }
    			    		        });        
    			    		        tabConP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    			    		        tabConP.getColumnModel().getColumn(0).setPreferredWidth(200);
    			    		        tabConP.getColumnModel().getColumn(1).setPreferredWidth(137);        
    			    		        tabConP.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
    			    		        tabConP.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
    			    		        
    			    		        jScrollPane2.setViewportView(tabConP);
    			    		        this.jScrollPane2.repaint();
    			    		        this.but_saveProj.setEnabled(true);
    			    		        
    			    		        ArrayList<String> ds_data = this.opsimGUI.calPathFile(layer2D_list.get("IMG").getDataSource());
    			    		        this.load2DLayersOnPrev(ds_data, prev_map, "IMG");
    						}
    						else JOptionPane.showMessageDialog(this, "Veuillez charger un fichier de type Image(.tif)", "Erreur",JOptionPane.ERROR_MESSAGE);
    						System.out.println("Open file Done");
    					}					
    				}
    				else JOptionPane.showMessageDialog(this, "Veuillez sélectionner ou créer un nouveau projet", "Erreur",JOptionPane.ERROR_MESSAGE);
        		}
				break;
			case "COV":
				if(this.layer2D_list.containsKey("MXD") || this.layer3D_list.containsKey("SXD") ){
	        		// Throw JOptionPane Error Message
        			JOptionPane.showMessageDialog(this, "Ce projet est déjà lié à un document MXD et/ou un document SXD \n"+
        												"Veuillez le dissocier avant d'ajouter un nouvelle couche Raster ou Vecteur", "Erreur",JOptionPane.ERROR_MESSAGE);
				}
        		else {
        			// Add layer
        			Layer lay_COV;
    				if(this.projModel != null){
    					this.map_List = (ArrayList<Map>) this.projModel.getMap();
    					if(!this.map_List.isEmpty()){
    						this.layer2D_list = this.map_List.get(0).getLayer();
    						this.layer3D_list = this.map_List.get(1).getLayer();
    					}
    					else {
    						// Add 2D Map
    						Map map2D = new Map();
    						map2D.setName("2D Map");
    						this.map_List.add(map2D);
    						// Add 3D Map
    						Map map3D = new Map();
    						map3D.setName("3D Map");
    						this.map_List.add(map3D);
    						this.layer2D_list = new Hashtable<String,Layer>();
    						this.layer3D_list = new Hashtable<String,Layer>();
    						this.map_List.get(0).setLayer(layer2D_list);
    						this.map_List.get(1).setLayer(layer3D_list);
    					}
    					
    					JFileChooser defautChooser = new JFileChooser(this.systView.getDefaultDirectory());
    					OpsimFilter covFilter = new OpsimFilter("shp","Shapefile - Feature Class");
    					defautChooser.addChoosableFileFilter(covFilter);
    					if(defautChooser.showOpenDialog(null) == defautChooser.APPROVE_OPTION){
    						this.file =  defautChooser.getSelectedFile();
    						System.out.println("Pathname File: "+file.getPath());
    						if(covFilter.accept(file)){
    							if(this.layer2D_list.containsKey("COV"))lay_COV = this.layer2D_list.get("COV");
    							else lay_COV = new Layer();
    							lay_COV.setName(file.getName());
    							lay_COV.setDataSource(file.getAbsolutePath());
    							lay_COV.setDescription(covFilter.getDescription());
    							lay_COV.setType("COV");
    							this.layer2D_list.put("COV", lay_COV);
    							this.map_List.get(0).setLayer(layer2D_list);
    							this.map_List.get(0).setNumLayer(layer2D_list.size());
    							
    							this.projModel.setMap(map_List);
    							this.projModel.setUpdatedDate(new Date());
    							this.projController.setModel(this.projModel);
    							this.opsimGUI.getListControler().remove("ProjectController");
    			    			this.opsimGUI.getListControler().put("ProjectController", this.projController);
    			    			// update view
    			    			tabConP.setModel(new javax.swing.table.DefaultTableModel(
    			    					new Object [][] {
    				    		                {" Modèle Numérique de Terrain (.shp)",
    				    		                	(this.layer2D_list.containsKey("MNT"))?this.layer2D_list.get("MNT").getDataSource():null,
    		    		                			(this.layer2D_list.containsKey("MNT"))?
    		    		                					((new File(this.layer2D_list.get("MNT").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Modèle Numérique de Surface (.shp)",
    		    			    		    	    (this.layer2D_list.containsKey("MNS"))?this.layer2D_list.get("MNS").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("MNS"))?
    		        			    		    			((new File(this.layer2D_list.get("MNS").getDataSource()).exists())?"OK":"WRONG"):"--"},
        			    		    			{" Surface d'Elevation (.tif)",
    		        			    		    	(this.layer3D_list.containsKey("MNE"))?this.layer3D_list.get("MNE").getDataSource():null,
    		            				    		(this.layer3D_list.containsKey("MNE"))?
    		            				    		   		((new File(this.layer3D_list.get("MNE").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Couche Clutter (.shp)",
    		        			    		    	(this.layer2D_list.containsKey("CLUT"))?this.layer2D_list.get("CLUT").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("CLUT"))?
    		        			    		    			((new File(this.layer2D_list.get("CLUT").getDataSource()).exists())?"OK":"WRONG"):"--"},
        			    		    			{" Couche Sites (.shp)",
    		        			    		    	(this.layer2D_list.containsKey("TX"))?this.layer2D_list.get("TX").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("TX"))?
    		        			    		    			((new File(this.layer2D_list.get("TX").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Couche Image (.tif)",
    		        			    		    	(this.layer2D_list.containsKey("IMG"))?this.layer2D_list.get("IMG").getDataSource():null,
    		        	        			    	(this.layer2D_list.containsKey("IMG"))?
    		        	        			    			((new File(this.layer2D_list.get("IMG").getDataSource()).exists())?"OK":"WRONG"):"--"},    				    		                			    		    	            
    				    		                {" Couverture radio (.shp)",
    				    		    	            (this.layer2D_list.containsKey("COV"))?file.getAbsolutePath():null,
    				        	        			(this.layer2D_list.containsKey("COV"))?((file.exists())?"OK":"WRONG"):"--"},				        	        			
    				    		                {" Analyse d'Interférence (.shp)",
    				        	        			(this.layer2D_list.containsKey("SINR"))?this.layer2D_list.get("SINR").getDataSource():null,
    			    			        	        (this.layer2D_list.containsKey("SINR"))?
    			    			        	        		((new File(this.layer2D_list.get("SINR").getDataSource()).exists())?"OK":"WRONG"):"--"},
	    			        	        		{" Modèle 3D CityEngine (.gdb)",
					    		    	            (this.layer3D_list.containsKey("CE"))?this.layer3D_list.get("CE").getDataSource():null,
					        	        			(this.layer3D_list.containsKey("CE"))?
					    		    	            		((new File(this.layer3D_list.get("CE").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Palette de couleur(.shp)",
    			    			        	        (this.layer2D_list.containsKey("COL"))?this.layer2D_list.get("COL").getDataSource():null,
    			    		    			        (this.layer2D_list.containsKey("COL"))?
    			    		    			        		((new File(this.layer2D_list.get("COL").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		            },
    			    		            new String [] {
    			    		                "Element", "Nom du fichier", "Etat"
    			    		            }
    			    		     ){			    		            
    									private static final long serialVersionUID = 1L;
    									Class[] types = new Class [] {
    			    		                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
    			    		            };
    			    		            public Class getColumnClass(int columnIndex) {
    			    		                return types [columnIndex];
    			    		            }
    			    		            public boolean isCellEditable(int row, int column) {
    			    		                return false;
    			    		             }
    			    		        });        
    			    		        tabConP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    			    		        tabConP.getColumnModel().getColumn(0).setPreferredWidth(200);
    			    		        tabConP.getColumnModel().getColumn(1).setPreferredWidth(137);        
    			    		        tabConP.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
    			    		        tabConP.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
    			    		        
    			    		        jScrollPane2.setViewportView(tabConP);
    			    		        this.jScrollPane2.repaint();
    			    		        this.but_saveProj.setEnabled(true);
    			    		        
    			    		        ArrayList<String> ds_data = this.opsimGUI.calPathFile(layer2D_list.get("COV").getDataSource());
    			    		        this.load2DLayersOnPrev(ds_data, prev_map, "COV");
    						}
    						else JOptionPane.showMessageDialog(this, "Veuillez charger un fichier de type Shapefile", "Erreur",JOptionPane.ERROR_MESSAGE);
    						System.out.println("Open file Done");
    					}					
    				}
    				else JOptionPane.showMessageDialog(this, "Veuillez sélectionner ou créer un nouveau projet", "Erreur",JOptionPane.ERROR_MESSAGE);
        		}
				break;
			case "CE":
				if(this.layer2D_list.containsKey("MXD") || this.layer3D_list.containsKey("SXD") ){
	        		// Throw JOptionPane Error Message
        			JOptionPane.showMessageDialog(this, "Ce projet est déjà lié à un document MXD et/ou un document SXD \n"+
        												"Veuillez le dissocier avant d'ajouter un nouvelle couche Raster ou Vecteur", "Erreur",JOptionPane.ERROR_MESSAGE);
				}
        		else {
        			// Add Layer CE
        			Layer lay_CE;
    				if(this.projModel != null){
    					this.map_List = (ArrayList<Map>) this.projModel.getMap();
    					if(!this.map_List.isEmpty()){
    						this.layer2D_list = this.map_List.get(0).getLayer();
    						this.layer3D_list = this.map_List.get(1).getLayer();
    					}
    					else {
    						// Add 2D Map
    						Map map2D = new Map();
    						map2D.setName("2D Map");
    						this.map_List.add(map2D);
    						// Add 3D Map
    						Map map3D = new Map();
    						map3D.setName("3D Map");
    						this.map_List.add(map3D);
    						this.layer2D_list = new Hashtable<String,Layer>();
    						this.layer3D_list = new Hashtable<String,Layer>();
    						this.map_List.get(0).setLayer(layer2D_list);
    						this.map_List.get(1).setLayer(layer3D_list);
    					}
    					
    					JFileChooser defautChooser = new JFileChooser(this.systView.getDefaultDirectory());
    					OpsimFilter gdb_Filter = new OpsimFilter(".gdb","Esri GeoDatabase");
    					defautChooser.addChoosableFileFilter(gdb_Filter);
    					defautChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    					if(defautChooser.showOpenDialog(null) == defautChooser.APPROVE_OPTION){
    						this.file =  defautChooser.getSelectedFile();
    						String pathName = file.getPath();
    						String extFile = pathName.substring(pathName.length()-4);
    						System.out.println("Pathname File: "+pathName);
    						System.out.println("Ext File: "+extFile);
    						if(".gdb".equals(extFile)){
    							if(this.layer3D_list.containsKey("CE"))lay_CE = this.layer3D_list.get("CE");
    							else lay_CE = new Layer();
    							lay_CE.setName(file.getName());
    							lay_CE.setDataSource(file.getAbsolutePath());
    							lay_CE.setDescription(gdb_Filter.getDescription());
    							lay_CE.setType("CE");
    							this.layer3D_list.put("CE", lay_CE);
    							this.map_List.get(1).setLayer(layer3D_list);
    							this.map_List.get(1).setNumLayer(layer3D_list.size());
    							
    							this.projModel.setMap(map_List);
    							//System.out.println("WS:1-Num Map = "+projModel.getMap().size());
    							this.projModel.setUpdatedDate(new Date());
    							this.projController.setModel(this.projModel);
    							this.opsimGUI.getListControler().remove("ProjectController");
    			    			this.opsimGUI.getListControler().put("ProjectController", this.projController);
    			    			// update view
    			    			tabConP.setModel(new javax.swing.table.DefaultTableModel(
    			    					new Object [][] {
    				    		                {" Modèle Numérique de Terrain (.shp)",
    				    		                	(this.layer2D_list.containsKey("MNT"))?this.layer2D_list.get("MNT").getDataSource():null,
    		    		                			(this.layer2D_list.containsKey("MNT"))?
    		    		                					((new File(this.layer2D_list.get("MNT").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Modèle Numérique de Surface (.shp)",
    		    			    		    	    (this.layer2D_list.containsKey("MNS"))?this.layer2D_list.get("MNS").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("MNS"))?
    		        			    		    			((new File(this.layer2D_list.get("MNS").getDataSource()).exists())?"OK":"WRONG"):"--"},
        			    		    			{" Surface d'Elevation (.tif)",
    		        			    		    	(this.layer3D_list.containsKey("MNE"))?this.layer3D_list.get("MNE").getDataSource():null,
    		            				    		(this.layer3D_list.containsKey("MNE"))?
    		            				    		   		((new File(this.layer3D_list.get("MNE").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Couche Clutter (.shp)",
    		        			    		    	(this.layer2D_list.containsKey("CLUT"))?this.layer2D_list.get("CLUT").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("CLUT"))?
    		        			    		    			((new File(this.layer2D_list.get("CLUT").getDataSource()).exists())?"OK":"WRONG"):"--"},
        			    		    			{" Couche Sites (.shp)",
    		        			    		    	(this.layer2D_list.containsKey("TX"))?this.layer2D_list.get("TX").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("TX"))?
    		        			    		    			((new File(this.layer2D_list.get("TX").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Couche Image (.tif)",
    		        			    		    	(this.layer2D_list.containsKey("IMG"))?this.layer2D_list.get("IMG").getDataSource():null,
    		        	        			    	(this.layer2D_list.containsKey("IMG"))?
    		        	        			    			((new File(this.layer2D_list.get("IMG").getDataSource()).exists())?"OK":"WRONG"):"--"},        	        			    						    		    	            
    				    		                {" Couverture radio (.shp)",
    				    		    	            (this.layer2D_list.containsKey("COV"))?this.layer2D_list.get("COV").getDataSource():null,
    				        	        			(this.layer2D_list.containsKey("COV"))?
    				        	        					((new File(this.layer2D_list.get("COV").getDataSource()).exists())?"OK":"WRONG"):"--"},		        	        					
    				    		                {" Analyse d'Interférence (.shp)",
    				        	        			(this.layer2D_list.containsKey("SINR"))?this.layer2D_list.get("SINR").getDataSource():null,
    			    			        	        (this.layer2D_list.containsKey("SINR"))?
    			    			        	        		((new File(this.layer2D_list.get("SINR").getDataSource()).exists())?"OK":"WRONG"):"--"},
	    			        	        		{" Modèle 3D CityEngine (.gdb)",
    				    		    	            (this.layer3D_list.containsKey("CE"))?file.getAbsolutePath():null,
    				        	        			(this.layer3D_list.containsKey("CE"))?((file.exists())?"OK":"WRONG"):"--"},
    				    		                {" Palette de couleur(.shp)",
    			    			        	        (this.layer2D_list.containsKey("COL"))?this.layer2D_list.get("COL").getDataSource():null,
    			    		    			        (this.layer2D_list.containsKey("COL"))?
    			    		    			        		((new File(this.layer2D_list.get("COL").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		            },
    			    		            new String [] {
    			    		                "Element", "Nom du fichier", "Etat"
    			    		            }
    			    		     ){			    		            
    									private static final long serialVersionUID = 1L;
    									Class[] types = new Class [] {
    			    		                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
    			    		            };
    			    		            public Class getColumnClass(int columnIndex) {
    			    		                return types [columnIndex];
    			    		            }
    			    		            public boolean isCellEditable(int row, int column) {
    			    		                return false;
    			    		             }
    			    		        });        
    			    		        tabConP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    			    		        tabConP.getColumnModel().getColumn(0).setPreferredWidth(200);
    			    		        tabConP.getColumnModel().getColumn(1).setPreferredWidth(137);        
    			    		        tabConP.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
    			    		        tabConP.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
    			    		        
    			    		        jScrollPane2.setViewportView(tabConP);
    			    		        this.jScrollPane2.repaint();
    			    		        this.but_saveProj.setEnabled(true);
    						}
    						else JOptionPane.showMessageDialog(this, "Veuillez sélectionner une GeoDatabase(.gdb)", "Erreur",JOptionPane.ERROR_MESSAGE);
    						System.out.println("Select folder Done");
    					}					
    				}
    				else JOptionPane.showMessageDialog(this, "Veuillez sélectionner ou créer un nouveau projet", "Erreur",JOptionPane.ERROR_MESSAGE); 
        		}				
				break;
			case "SINR":
				if(this.layer2D_list.containsKey("MXD") || this.layer3D_list.containsKey("SXD") ){
	        		// Throw JOptionPane Error Message
        			JOptionPane.showMessageDialog(this, "Ce projet est déjà lié à un document MXD et/ou un document SXD \n"+
        												"Veuillez le dissocier avant d'ajouter un nouvelle couche Raster ou Vecteur", "Erreur",JOptionPane.ERROR_MESSAGE);
				}
        		else {
        			// Add layer 
        			Layer lay_SINR;
    				if(this.projModel != null){
    					this.map_List = (ArrayList<Map>) this.projModel.getMap();
    					if(!this.map_List.isEmpty()){
    						this.layer2D_list = this.map_List.get(0).getLayer();
    						this.layer3D_list = this.map_List.get(1).getLayer();
    					}
    					else {
    						// Add 2D Map
    						Map map2D = new Map();
    						map2D.setName("2D Map");
    						this.map_List.add(map2D);
    						// Add 3D Map
    						Map map3D = new Map();
    						map3D.setName("3D Map");
    						this.map_List.add(map3D);
    						this.layer2D_list = new Hashtable<String,Layer>();
    						this.layer3D_list = new Hashtable<String,Layer>();
    						this.map_List.get(0).setLayer(layer2D_list);
    						this.map_List.get(1).setLayer(layer3D_list);
    					}
    					
    					JFileChooser defautChooser = new JFileChooser(this.systView.getDefaultDirectory());
    					OpsimFilter mxdFilter = new OpsimFilter("shp","Shapefile - Feature Class");
    					defautChooser.addChoosableFileFilter(mxdFilter);
    					if(defautChooser.showOpenDialog(null) == defautChooser.APPROVE_OPTION){
    						this.file =  defautChooser.getSelectedFile();
    						System.out.println("Pathname File: "+file.getPath());
    						if(mxdFilter.accept(file)){
    							if(this.layer2D_list.containsKey("SINR"))lay_SINR = this.layer2D_list.get("SINR");
    							else lay_SINR = new Layer();
    							lay_SINR.setName(file.getName());
    							lay_SINR.setDataSource(file.getAbsolutePath());
    							lay_SINR.setDescription(mxdFilter.getDescription());
    							lay_SINR.setType("SINR");
    							this.layer2D_list.put("SINR", lay_SINR);
    							this.map_List.get(0).setLayer(layer2D_list);
    							this.map_List.get(0).setNumLayer(layer2D_list.size());
    							
    							this.projModel.setMap(map_List);
    							this.projModel.setUpdatedDate(new Date());
    							this.projController.setModel(this.projModel);
    							this.opsimGUI.getListControler().remove("ProjectController");
    			    			this.opsimGUI.getListControler().put("ProjectController", this.projController);
    			    			// update view
    			    			tabConP.setModel(new javax.swing.table.DefaultTableModel(
    			    					new Object [][] {
    				    		                {" Modèle Numérique de Terrain (.shp)",
    				    		                	(this.layer2D_list.containsKey("MNT"))?this.layer2D_list.get("MNT").getDataSource():null,
    		    		                			(this.layer2D_list.containsKey("MNT"))?
    		    		                					((new File(this.layer2D_list.get("MNT").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Modèle Numérique de Surface (.shp)",
    		    			    		    	    (this.layer2D_list.containsKey("MNS"))?this.layer2D_list.get("MNS").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("MNS"))?
    		        			    		    			((new File(this.layer2D_list.get("MNS").getDataSource()).exists())?"OK":"WRONG"):"--"},
        			    		    			{" Surface d'Elevation (.tif)",
    		        			    		    	(this.layer3D_list.containsKey("MNE"))?this.layer3D_list.get("MNE").getDataSource():null,
    		            				    		(this.layer3D_list.containsKey("MNE"))?
    		            				    		   		((new File(this.layer3D_list.get("MNE").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Couche Clutter (.shp)",
    		        			    		    	(this.layer2D_list.containsKey("CLUT"))?this.layer2D_list.get("CLUT").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("CLUT"))?
    		        			    		    			((new File(this.layer2D_list.get("CLUT").getDataSource()).exists())?"OK":"WRONG"):"--"},
        			    		    			{" Couche Sites (.shp)",
    		        			    		    	(this.layer2D_list.containsKey("TX"))?this.layer2D_list.get("TX").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("TX"))?
    		        			    		    			((new File(this.layer2D_list.get("TX").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Couche Image (.tif)",
    		        			    		    	(this.layer2D_list.containsKey("IMG"))?this.layer2D_list.get("IMG").getDataSource():null,
    		        	        			    	(this.layer2D_list.containsKey("IMG"))?
    		        	        			    			((new File(this.layer2D_list.get("IMG").getDataSource()).exists())?"OK":"WRONG"):"--"},    				    		                			    		    	            
    				    		                {" Couverture radio (.shp)",
    				    		    	            (this.layer2D_list.containsKey("COV"))?this.layer2D_list.get("COV").getDataSource():null,
    				        	        			(this.layer2D_list.containsKey("COV"))?
    				        	        					((new File(this.layer2D_list.get("COV").getDataSource()).exists())?"OK":"WRONG"):"--"},		        	        					
    				    		                {" Analyse d'Interférence (.shp)",
    				        	        			(this.layer2D_list.containsKey("SINR"))?file.getAbsolutePath():null,
    			    			        	        (this.layer2D_list.containsKey("SINR"))?((file.exists())?"OK":"WRONG"):"--"},
			    			        	        {" Modèle 3D CityEngine (.gdb)",
					    		    	            (this.layer3D_list.containsKey("CE"))?this.layer3D_list.get("CE").getDataSource():null,
					        	        			(this.layer3D_list.containsKey("CE"))?
    				    		    	            		((new File(this.layer3D_list.get("CE").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Palette de couleur(.shp)",
    			    			        	        (this.layer2D_list.containsKey("COL"))?this.layer2D_list.get("COL").getDataSource():null,
    			    		    			        (this.layer2D_list.containsKey("COL"))?
    			    		    			        		((new File(this.layer2D_list.get("COL").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		            },
    			    		            new String [] {
    			    		                "Element", "Nom du fichier", "Etat"
    			    		            }
    			    		     ){			    		            
    									private static final long serialVersionUID = 1L;
    									Class[] types = new Class [] {
    			    		                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
    			    		            };
    			    		            public Class getColumnClass(int columnIndex) {
    			    		                return types [columnIndex];
    			    		            }
    			    		            public boolean isCellEditable(int row, int column) {
    			    		                return false;
    			    		             }
    			    		        });        
    			    		        tabConP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    			    		        tabConP.getColumnModel().getColumn(0).setPreferredWidth(200);
    			    		        tabConP.getColumnModel().getColumn(1).setPreferredWidth(137);        
    			    		        tabConP.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
    			    		        tabConP.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
    			    		        
    			    		        jScrollPane2.setViewportView(tabConP);
    			    		        this.jScrollPane2.repaint();
    			    		        this.but_saveProj.setEnabled(true);
    			    		        
    			    		        ArrayList<String> ds_data = this.opsimGUI.calPathFile(layer2D_list.get("SINR").getDataSource());
    			    		        this.load2DLayersOnPrev(ds_data, prev_map, "SINR");
    						}
    						else JOptionPane.showMessageDialog(this, "Veuillez charger un fichier de type Shapefile", "Erreur",JOptionPane.ERROR_MESSAGE);
    						System.out.println("Open file Done");
    					}					
    				}
    				else JOptionPane.showMessageDialog(this, "Veuillez sélectionner ou créer un nouveau projet", "Erreur",JOptionPane.ERROR_MESSAGE);
        		}
				break;
			case "COL":
				if(this.layer2D_list.containsKey("MXD") || this.layer3D_list.containsKey("SXD") ){
	        		// Throw JOptionPane Error Message
        			JOptionPane.showMessageDialog(this, "Ce projet est déjà lié à un document MXD et/ou un document SXD \n"+
        												"Veuillez le dissocier avant d'ajouter un nouvelle couche Raster ou Vecteur", "Erreur",JOptionPane.ERROR_MESSAGE);
				}
        		else {
        			// Add layer 
        			Layer lay_COL;
    				if(this.projModel != null){
    					this.map_List = (ArrayList<Map>) this.projModel.getMap();
    					if(!this.map_List.isEmpty()){
    						this.layer2D_list = this.map_List.get(0).getLayer();
    						this.layer3D_list = this.map_List.get(1).getLayer();
    					}
    					else {
    						// Add 2D Map
    						Map map2D = new Map();
    						map2D.setName("2D Map");
    						this.map_List.add(map2D);
    						// Add 3D Map
    						Map map3D = new Map();
    						map3D.setName("3D Map");
    						this.map_List.add(map3D);
    						this.layer2D_list = new Hashtable<String,Layer>();
    						this.layer3D_list = new Hashtable<String,Layer>();
    						this.map_List.get(0).setLayer(layer2D_list);
    						this.map_List.get(1).setLayer(layer3D_list);
    					}
    					
    					JFileChooser defautChooser = new JFileChooser(this.systView.getDefaultDirectory());
    					OpsimFilter mxdFilter = new OpsimFilter("shp","Shapefile - Feature Class");
    					defautChooser.addChoosableFileFilter(mxdFilter);
    					if(defautChooser.showOpenDialog(null) == defautChooser.APPROVE_OPTION){
    						this.file =  defautChooser.getSelectedFile();
    						System.out.println("Pathname File: "+file.getPath());
    						if(mxdFilter.accept(file)){
    							if(this.layer2D_list.containsKey("COL"))lay_COL = this.layer2D_list.get("COL");
    							else lay_COL = new Layer();
    							lay_COL.setName(file.getName());
    							lay_COL.setDataSource(file.getAbsolutePath());
    							lay_COL.setDescription(mxdFilter.getDescription());
    							lay_COL.setType("COL");
    							this.layer2D_list.put("COL", lay_COL);
    							this.map_List.get(0).setLayer(layer2D_list);
    							this.map_List.get(0).setNumLayer(layer2D_list.size());
    							
    							this.projModel.setMap(map_List);
    							this.projModel.setUpdatedDate(new Date());
    							this.projController.setModel(this.projModel);
    							this.opsimGUI.getListControler().remove("ProjectController");
    			    			this.opsimGUI.getListControler().put("ProjectController", this.projController);
    			    			// update view
    			    			tabConP.setModel(new javax.swing.table.DefaultTableModel(
    			    					new Object [][] {
    				    		                {" Modèle Numérique de Terrain (.shp)",
    				    		                	(this.layer2D_list.containsKey("MNT"))?this.layer2D_list.get("MNT").getDataSource():null,
    		    		                			(this.layer2D_list.containsKey("MNT"))?
    		    		                					((new File(this.layer2D_list.get("MNT").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Modèle Numérique de Surface (.shp)",
    		    			    		    	    (this.layer2D_list.containsKey("MNS"))?this.layer2D_list.get("MNS").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("MNS"))?
    		        			    		    			((new File(this.layer2D_list.get("MNS").getDataSource()).exists())?"OK":"WRONG"):"--"},
        			    		    			{" Surface d'Elevation (.tif)",
    		        			    		    	(this.layer3D_list.containsKey("MNE"))?this.layer3D_list.get("MNE").getDataSource():null,
    		            				    		(this.layer3D_list.containsKey("MNE"))?
    		            				    		   		((new File(this.layer3D_list.get("MNE").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Couche Clutter (.shp)",
    		        			    		    	(this.layer2D_list.containsKey("CLUT"))?this.layer2D_list.get("CLUT").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("CLUT"))?
    		        			    		    			((new File(this.layer2D_list.get("CLUT").getDataSource()).exists())?"OK":"WRONG"):"--"},
        			    		    			{" Couche Sites (.shp)",
    		        			    		    	(this.layer2D_list.containsKey("TX"))?this.layer2D_list.get("TX").getDataSource():null,
    		        			    		    	(this.layer2D_list.containsKey("TX"))?
    		        			    		    			((new File(this.layer2D_list.get("TX").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Couche Image (.tif)",
    		        			    		    	(this.layer2D_list.containsKey("IMG"))?this.layer2D_list.get("IMG").getDataSource():null,
    		        	        			    	(this.layer2D_list.containsKey("IMG"))?
    		        	        			    			((new File(this.layer2D_list.get("IMG").getDataSource()).exists())?"OK":"WRONG"):"--"},    				    		                				    		    	            
    				    		                {" Couverture radio (.shp)",
    				    		    	            (this.layer2D_list.containsKey("COV"))?this.layer2D_list.get("COV").getDataSource():null,
    				        	        			(this.layer2D_list.containsKey("COV"))?
    				        	        					((new File(this.layer2D_list.get("COV").getDataSource()).exists())?"OK":"WRONG"):"--"},		        	        					
    				    		                {" Analyse d'Interférence (.shp)",
    				        	        			(this.layer2D_list.containsKey("SINR"))?this.layer2D_list.get("SINR").getDataSource():null,
    			    			        	        (this.layer2D_list.containsKey("SINR"))?
    			    			        	        		((new File(this.layer2D_list.get("SINR").getDataSource()).exists())?"OK":"WRONG"):"--"},
	    			        	        		{" Modèle 3D CityEngine (.gdb)",
					    		    	            (this.layer3D_list.containsKey("CE"))?this.layer3D_list.get("CE").getDataSource():null,
					        	        			(this.layer3D_list.containsKey("CE"))?
    				    		    	            		((new File(this.layer3D_list.get("CE").getDataSource()).exists())?"OK":"WRONG"):"--"},
    				    		                {" Palette de couleur(.shp)",
    			    			        	        (this.layer2D_list.containsKey("COL"))?file.getAbsolutePath():null,
    			    		    			        (this.layer2D_list.containsKey("COL"))?((file.exists())?"OK":"WRONG"):"--"},
    				    		            },
    			    		            new String [] {
    			    		                "Element", "Nom du fichier", "Etat"
    			    		            }
    			    		     ){			    		            
    									private static final long serialVersionUID = 1L;
    									Class[] types = new Class [] {
    			    		                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
    			    		            };
    			    		            public Class getColumnClass(int columnIndex) {
    			    		                return types [columnIndex];
    			    		            }
    			    		            public boolean isCellEditable(int row, int column) {
    			    		                return false;
    			    		             }
    			    		        });        
    			    		        tabConP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    			    		        tabConP.getColumnModel().getColumn(0).setPreferredWidth(200);
    			    		        tabConP.getColumnModel().getColumn(1).setPreferredWidth(137);        
    			    		        tabConP.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
    			    		        tabConP.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
    			    		        
    			    		        jScrollPane2.setViewportView(tabConP);
    			    		        this.jScrollPane2.repaint();
    			    		        this.but_saveProj.setEnabled(true);
    						}
    						else JOptionPane.showMessageDialog(this, "Veuillez charger un fichier de type Shapefile", "Erreur",JOptionPane.ERROR_MESSAGE);
    						System.out.println("Open file Done");
    					}					
    				}
    				else JOptionPane.showMessageDialog(this, "Veuillez sélectionner ou créer un nouveau projet", "Erreur",JOptionPane.ERROR_MESSAGE);
        		}
				break;
			default:
				break;				
		}
	}
	
	public void tabPIValueChanged(ListSelectionEvent evt){
		int viewRow = tabPI.getSelectedRow();
		this.but_selectPI.setEnabled(true);	
	}
	
	public void tabConPValueChanged(ListSelectionEvent evt){
		int viewRow = tabConP.getSelectedRow();
		ArrayList<Map> mapList;
        switch(viewRow){
	        case 0:
	        	this.laySelected = "MNT";
	        	this.but_browConP.setEnabled(true);
	        	if(this.projModel != null){
	        		if(!this.map_List.isEmpty()){
		        		if(this.layer2D_list.containsKey("MNT"))this.but_delItemConP.setEnabled(true);
		        		else this.but_delItemConP.setEnabled(false);
					}else this.but_delItemConP.setEnabled(false);
	        	}
	        	break;
	        case 1:
	        	this.laySelected = "MNS";
	        	this.but_browConP.setEnabled(true);
	        	if(this.projModel != null){
	        		if(!this.map_List.isEmpty()){
		        		if(this.layer2D_list.containsKey("MNS"))this.but_delItemConP.setEnabled(true);
		        		else this.but_delItemConP.setEnabled(false);
					}else this.but_delItemConP.setEnabled(false);
	        	}
	        	break;
	        case 2:
	        	this.laySelected = "MNE";
	        	this.but_browConP.setEnabled(true);
	        	if(this.projModel != null){
	        		if(!this.map_List.isEmpty()){
		        		if(this.layer3D_list.containsKey("MNE"))this.but_delItemConP.setEnabled(true);
		        		else this.but_delItemConP.setEnabled(false);
					}else this.but_delItemConP.setEnabled(false);
	        	}
	        	break;
	        case 3:
	        	this.laySelected = "CLUT";
	        	this.but_browConP.setEnabled(true);
	        	if(this.projModel != null){
	        		if(!this.map_List.isEmpty()){
		        		if(this.layer2D_list.containsKey("CLUT"))this.but_delItemConP.setEnabled(true);
		        		else this.but_delItemConP.setEnabled(false);
					}else this.but_delItemConP.setEnabled(false);
	        	}
	        	break;
	        case 4:
	        	this.laySelected = "TX";
	        	this.but_browConP.setEnabled(true);
	        	if(this.projModel != null){
	        		if(!this.map_List.isEmpty()){
		        		if(this.layer2D_list.containsKey("TX"))this.but_delItemConP.setEnabled(true);
		        		else this.but_delItemConP.setEnabled(false);
					}else this.but_delItemConP.setEnabled(false);
	        	}
	        	break;
	        case 5:
	        	this.laySelected = "IMG";
	        	this.but_browConP.setEnabled(true);
	        	if(this.projModel != null){
	        		if(!this.map_List.isEmpty()){
		        		if(this.layer2D_list.containsKey("IMG"))this.but_delItemConP.setEnabled(true);
		        		else this.but_delItemConP.setEnabled(false);
					}else this.but_delItemConP.setEnabled(false);
	        	}
	        	break;
	        case 6:
	        	this.laySelected = "COV";
	        	this.but_browConP.setEnabled(true);
	        	if(this.projModel != null){
	        		if(!this.map_List.isEmpty()){
		        		if(this.layer2D_list.containsKey("COV"))this.but_delItemConP.setEnabled(true);
		        		else this.but_delItemConP.setEnabled(false);
					}else this.but_delItemConP.setEnabled(false);
	        	}
	        	break;
	        
	        case 7:
	        	this.laySelected = "SINR";
	        	this.but_browConP.setEnabled(true);
	        	if(this.projModel != null){
	        		if(!this.map_List.isEmpty()){
		        		if(this.layer2D_list.containsKey("SINR"))this.but_delItemConP.setEnabled(true);
		        		else this.but_delItemConP.setEnabled(false);
					}else this.but_delItemConP.setEnabled(false);
	        	}	        	
	        	break;
	        case 8:
	        	this.laySelected = "CE";
	        	this.but_browConP.setEnabled(true);
	        	if(this.projModel != null){
	        		if(!this.map_List.isEmpty()){
		        		if(this.layer3D_list.containsKey("CE"))this.but_delItemConP.setEnabled(true);
		        		else this.but_delItemConP.setEnabled(false);
					}else this.but_delItemConP.setEnabled(false);
	        	}
	        	break;
	        case 9:
	        	this.laySelected = "COL";
	        	this.but_browConP.setEnabled(true);
	        	if(this.projModel != null){
	        		if(!this.map_List.isEmpty()){
		        		if(this.layer2D_list.containsKey("COL"))this.but_delItemConP.setEnabled(true);
		        		else this.but_delItemConP.setEnabled(false);
					}else this.but_delItemConP.setEnabled(false);
	        	}
	        	break;
	        default:
	        	this.but_browConP.setEnabled(false);
	        	this.but_delItemConP.setEnabled(false);
	        	break;
        }
	}
	
	public OpsimGUI getOpsimGUI() {
		return opsimGUI;
	}

	public void setOpsimGUI(OpsimGUI opsimGUI) {
		this.opsimGUI = opsimGUI;
	}
	
	public void updateTabConP(){
		this.map_List = (ArrayList<Map>) this.projModel.getMap();
		if(!this.map_List.isEmpty()){			
			this.layer2D_list = this.map_List.get(0).getLayer();
			this.layer3D_list = this.map_List.get(1).getLayer();
		}
		else{
			// Add 2D Map
			Map map2D = new Map();
			map2D.setName("2D Map");
			this.map_List.add(map2D);
			// Add 3D Map
			Map map3D = new Map();
			map3D.setName("3D Map");
			this.map_List.add(map3D);
			this.layer2D_list = new Hashtable<String,Layer>();
			this.layer3D_list = new Hashtable<String,Layer>();
			this.map_List.get(0).setLayer(layer2D_list);
			this.map_List.get(1).setLayer(layer3D_list);
		}
		
		File fileMNT, fileMNS, fileMNE, fileCLUT, fileTX, fileIMG, fileCOV, fileSINR, fileCOL, fileCE;
		fileMNT = (this.layer2D_list.containsKey("MNT"))?new File(layer2D_list.get("MNT").getDataSource()):new File("");
		fileMNS = (this.layer2D_list.containsKey("MNS"))?new File(layer2D_list.get("MNS").getDataSource()):new File("");
		fileMNE = (this.layer3D_list.containsKey("MNE"))?new File(layer3D_list.get("MNE").getDataSource()):new File("");
		fileCLUT = (this.layer2D_list.containsKey("CLUT"))?new File(layer2D_list.get("CLUT").getDataSource()):new File("");
		fileTX = (this.layer2D_list.containsKey("TX"))?new File(layer2D_list.get("TX").getDataSource()):new File("");
		fileIMG = (this.layer2D_list.containsKey("IMG"))?new File(layer2D_list.get("IMG").getDataSource()):new File("");
		fileCOV = (this.layer2D_list.containsKey("COV"))?new File(layer2D_list.get("COV").getDataSource()):new File("");
		fileCE = (this.layer3D_list.containsKey("CE"))?new File(layer3D_list.get("CE").getDataSource()):new File("");
		fileSINR = (this.layer2D_list.containsKey("SINR"))?new File(layer2D_list.get("SINR").getDataSource()):new File("");
		fileCOL = (this.layer2D_list.containsKey("COL"))?new File(layer2D_list.get("COL").getDataSource()):new File("");
		
		
		tabConP.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {
	                {" Modèle Numérique de Terrain (.shp)",
	                	(this.layer2D_list.containsKey("MNT"))?fileMNT.getPath():null,
	    	            (this.layer2D_list.containsKey("MNT"))?((fileMNT.exists())?"OK":"WRONG"):"--"},
	                {" Modèle Numérique de Surface (.shp)",
	    	            (this.layer2D_list.containsKey("MNS"))?fileMNS.getPath():null,
	    	        	(this.layer2D_list.containsKey("MNS"))?((fileMNS.exists())?"OK":"WRONG"):"--"},
    	        	{" Surface d'Elevation (.tif)",
	    		    	(this.layer3D_list.containsKey("MNE"))?fileMNE.getPath():null,
			    		(this.layer3D_list.containsKey("MNE"))?((fileMNE.exists())?"OK":"WRONG"):"--"},
	                {" Couche Clutter (.shp)",
	    	        	(this.layer2D_list.containsKey("CLUT"))?fileCLUT.getPath():null,
	    		    	(this.layer2D_list.containsKey("CLUT"))?((fileCLUT.exists())?"OK":"WRONG"):"--"},
    		    	{" Couche Sites (.shp)",
	    	        	(this.layer2D_list.containsKey("TX"))?fileTX.getPath():null,
	    		    	(this.layer2D_list.containsKey("TX"))?((fileTX.exists())?"OK":"WRONG"):"--"},
	                {" Couche Image (.tif)",
	    		    	(this.layer2D_list.containsKey("IMG"))?fileIMG.getPath():null,
	    		    	(this.layer2D_list.containsKey("IMG"))?((fileIMG.exists())?"OK":"WRONG"):"--"},
	                {" Couverture radio (.shp)",
	    		    	(this.layer2D_list.containsKey("COV"))?fileCOV.getPath():null,
	    		    	(this.layer2D_list.containsKey("COV"))?((fileCOV.exists())?"OK":"WRONG"):"--"},    		    	
	                {" Analyse d'Interférence (.shp)",
	    		    	(this.layer2D_list.containsKey("SINR"))?fileSINR.getPath():null,
	    		    	(this.layer2D_list.containsKey("SINR"))?((fileSINR.exists())?"OK":"WRONG"):"--"},
    		    	{" Modèle 3D CityEngine (.gdb)",
	    	            (this.layer3D_list.containsKey("CE"))?fileCE.getPath():null,
	        			(this.layer3D_list.containsKey("CE"))?((fileCE.exists())?"OK":"WRONG"):"--"},
	                {" Palette de couleur(.shp)",
	    		    	(this.layer2D_list.containsKey("COL"))?fileCOL.getPath():null,
	    		    	(this.layer2D_list.containsKey("COL"))?((fileCOL.exists())?"OK":"WRONG"):"--"},
	            },
	            new String [] {
	                "Element", "Nom du fichier", "Etat"
	            }
	     ){			    		            
				private static final long serialVersionUID = 1L;
				Class[] types = new Class [] {
	                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
	            };
	            public Class getColumnClass(int columnIndex) {
	                return types [columnIndex];
	            }
	            public boolean isCellEditable(int row, int column) {
	                return false;
	             }
	        });        
	        tabConP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	        tabConP.getColumnModel().getColumn(0).setPreferredWidth(200);
	        tabConP.getColumnModel().getColumn(1).setPreferredWidth(137);        
	        tabConP.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
	        tabConP.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);			    		        
	        jScrollPane2.setViewportView(tabConP);
	        this.jScrollPane2.repaint();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DialogManProject dialog = new DialogManProject(null, true);
	}

	public javax.swing.JCheckBox getCb_manProj() {
		return cb_manProj;
	}

	/* (non-Javadoc)
	 * @see cm.opsim.observer.Observer#update(cm.opsim.observer.Observable)
	 */
	@Override
	public void update(Observable observ) {
		System.out.println("DialogManProj: on Observer >> update ");
		DAO<Project> projDAO = DAOFactory.getProjectDAO();
        List<Project> listProj = projDAO.findAll();

        ProjIntTableModel modelPI = new ProjIntTableModel(listProj); 
        tabPI = new javax.swing.JTable();
        tabPI.setModel(modelPI);
        tabPI.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabPI.getColumnModel().getColumn(0).setPreferredWidth(140);
        tabPI.getColumnModel().getColumn(1).setPreferredWidth(80); 
        tabPI.getColumnModel().getColumn(1).setCellRenderer(rightRenderer); 
        tabPI.getColumnModel().getColumn(2).setPreferredWidth(50);
        tabPI.getColumnModel().getColumn(3).setPreferredWidth(142);   
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabPI.getModel());
        tabPI.setRowSorter(sorter);
        tabPI.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						tabPIValueChanged(event);  
					}
				}
		);
        jScrollPane1.setViewportView(tabPI);
		this.jScrollPane1.repaint();
	}

	/* (non-Javadoc)
	 * @see cm.opsim.observer.Observable#addObserver(cm.opsim.observer.Observer)
	 */
	@Override
	public void addObserver(Observer obs) {
		System.out.println("DialogManProj: Add obs called ");
		this.listObserver.add(obs);		
		System.out.println("DialogManProj: Nbre Obs after call = "+this.listObserver.size());
	}

	/* (non-Javadoc)
	 * @see cm.opsim.observer.Observable#removeObserver()
	 */
	@Override
	public void removeObserver() {
		this.listObserver = new ArrayList<Observer>();
	}

	/* (non-Javadoc)
	 * @see cm.opsim.observer.Observable#notifyObserver()
	 */
	@Override
	public void notifyObserver() {
		for(Observer obs : this.listObserver )
			obs.update(this);
	}
	
	private void load2DLayersOnPrev(ArrayList<String> ds_data, MapBean map, String nameLayer){
		ShapefileWorkspaceFactory sf_wsf;
		try {
			sf_wsf = new ShapefileWorkspaceFactory();
			RasterWorkspaceFactory ras_wsf = new RasterWorkspaceFactory();
			Workspace sf_ws = (Workspace) sf_wsf.openFromFile(ds_data.get(0), 0);	
			sf_ws.startEditing(false);
			sf_ws.startEditOperation();
			
			System.out.println("Prev_layer2D: loading "+nameLayer+"...");
			ILayer layer = null;
			if(nameLayer == "IMG"){
				IRasterLayer rasterLayer = new RasterLayer();
				rasterLayer.createFromFilePath(ds_data.get(0)+File.separator+ds_data.get(1));
				layer = (ILayer)rasterLayer;
			}
			else{
				IFeatureLayer featureLayer = new FeatureLayer();
				featureLayer.setFeatureClassByRef(sf_ws.openFeatureClass(ds_data.get(1)));
				layer = this.opsimGUI.loadRendereredLayer(featureLayer, nameLayer, "2D");
			}	
			
			sf_ws.stopEditOperation();
			sf_ws.stopEditing(true);
			sf_ws = null;		
			
			System.out.println("Prev_layer2D: "+layer.getName().toString()+" loaded");
			map.addLayer(layer,0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
