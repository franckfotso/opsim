/**
 * 
 */
package cm.opsim.view;

import java.awt.CardLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import cm.opsim.controller.ProjectController;
import cm.opsim.dao.DAO;
import cm.opsim.dao.DAOFactory;
import cm.opsim.dao.FreqBandDAO;
import cm.opsim.event.AbstractHandleEvent;
import cm.opsim.event.NewProjHE;
import cm.opsim.model.Configuration;
import cm.opsim.model.FreqBand;
import cm.opsim.model.GlobalDesignNet;
import cm.opsim.model.Map;
import cm.opsim.model.Parameter;
import cm.opsim.model.Planification;
import cm.opsim.model.ProjExterne;
import cm.opsim.model.Project;
import cm.opsim.model.Simulation;
import cm.opsim.model.SystemLog;
import cm.opsim.model.Template;
import cm.opsim.observer.Observable;
import cm.opsim.observer.Observer;

/**
 * @author Romuald FOTSO
 *
 */
public class DialogNewProj extends JDialog implements Observable{

	private javax.swing.JButton but_browLocation;
    private javax.swing.JButton but_cancel;
    private javax.swing.JButton but_finish;
    private javax.swing.JButton but_icon1;
    private javax.swing.JButton but_icon2;
    private javax.swing.JButton but_next;
    private javax.swing.JButton but_prev;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox cbo_zonEtude;
    private javax.swing.JPanel conButton;
    private javax.swing.JPanel conCard_step1;
    private javax.swing.JPanel conCard_step1_left;
    private javax.swing.JPanel conCard_step1_right;
    private javax.swing.JPanel conCard_step2;
    private javax.swing.JPanel conCard_step2_left;
    private javax.swing.JPanel conCard_step2_right;
    private javax.swing.JPanel conGroupCard;
    private javax.swing.JPanel conGroup_preveiw;
    private javax.swing.JPanel con_preview;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lab_des_lte10;
    private javax.swing.JLabel lab_lat;
    private javax.swing.JLabel lab_location;
    private javax.swing.JLabel lab_lon;
    private javax.swing.JLabel lab_nameProj;
    private javax.swing.JLabel lab_zonEtude;
    private javax.swing.JTextPane panDes_lte10;
    private javax.swing.JRadioButton rad_optPE;
    private javax.swing.JRadioButton rad_optPI;
    private javax.swing.JTextField tf_lat;
    private javax.swing.JTextField tf_location;
    private javax.swing.JTextField tf_lon;
    private javax.swing.JTextField tf_nameProj;
    private javax.swing.JLabel tf_previewState;
    private javax.swing.JTree tree_fam;
    private javax.swing.JTree tree_gen;
    private javax.swing.JTree tree_tech;
    private Image icone =  Toolkit.getDefaultToolkit().getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/logo.png"));
    private ImageIcon icon_newProj =  new ImageIcon(Toolkit.getDefaultToolkit().
			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/newfile.png")));
    private CardLayout cl = new CardLayout();
    private AbstractHandleEvent newProjHE;
    private OpsimGUI opsimGUI;
    private ProjectController projController;
    private Project projModel;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private ArrayList<Observer> listObserver = new ArrayList<Observer>();;
    
	
    public DialogNewProj(OpsimGUI parent, boolean modal, Observer obs) {
        super(parent, modal);
        this.opsimGUI = parent;
        this.addObserver(obs);
        this.initComponents();
        this.initEvents();
        this.initEventsTree();
        this.setLocationRelativeTo(null);
        try {
	           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	           SwingUtilities.updateComponentTreeUI(this);
	    } 
		catch (InstantiationException e) {} 
		catch (ClassNotFoundException e) {}
		catch (UnsupportedLookAndFeelException e) {} 
		catch (IllegalAccessException e) {}        
        this.projController = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
        this.projModel = new Project(0, null, 0.00, true, new Date(), new Date(), this.opsimGUI.getUser(), new Template(),
        								new GlobalDesignNet(), new FreqBand(), new ArrayList<Map>(), new ArrayList<SystemLog>(),
        								new Parameter(), new Configuration(), new Planification(), new ArrayList<Simulation>());      
        System.out.println("ID Planif: "+this.projModel.getPlanification().getId());
        //this.projController.setModel(this.projModel);
        this.setVisible(true);
    }
    
    public void initComponents(){    	
    	this.setIconImage(icone);   	
    	buttonGroup2 = new javax.swing.ButtonGroup();
        conButton = new javax.swing.JPanel();
        but_prev = new javax.swing.JButton();
        but_next = new javax.swing.JButton();
        but_cancel = new javax.swing.JButton();
        but_finish = new javax.swing.JButton();
        conGroupCard = new javax.swing.JPanel();
        conCard_step1 = new javax.swing.JPanel();
        conCard_step1_left = new javax.swing.JPanel();
        but_icon1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        conCard_step1_right = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tree_gen = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        tree_fam = new javax.swing.JTree();
        jScrollPane3 = new javax.swing.JScrollPane();
        tree_tech = new javax.swing.JTree();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lab_des_lte10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        panDes_lte10 = new javax.swing.JTextPane();
        conCard_step2 = new javax.swing.JPanel();
        conCard_step2_left = new javax.swing.JPanel();
        but_icon2 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        conCard_step2_right = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lab_nameProj = new javax.swing.JLabel();
        tf_nameProj = new javax.swing.JTextField();
        //tf_nameProj.setText(" ");
        rad_optPI = new javax.swing.JRadioButton();
        rad_optPE = new javax.swing.JRadioButton();
        lab_location = new javax.swing.JLabel();
        tf_location = new javax.swing.JTextField();
        but_browLocation = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        lab_zonEtude = new javax.swing.JLabel();
        cbo_zonEtude = new javax.swing.JComboBox();
        lab_lon = new javax.swing.JLabel();
        tf_lon = new javax.swing.JTextField();
        lab_lat = new javax.swing.JLabel();
        tf_lat = new javax.swing.JTextField();
        conGroup_preveiw = new javax.swing.JPanel();
        con_preview = new javax.swing.JPanel();
        tf_previewState = new javax.swing.JLabel();

        buttonGroup2.add(this.rad_optPI);
        buttonGroup2.add(this.rad_optPE);        
        
//    	this.prevMap = new JMap();
//    	this.con_preview.add(prevMap);
//    	ArcGISTiledMapServiceLayer tiledLayer = new ArcGISTiledMapServiceLayer(
//    			 "http://services.arcgisonline.com/ArcGIS/rest/services/NatGeo_World_Map/MapServer");
//    	prevMap.getLayers().add(tiledLayer);
//    	this.con_preview.add(this.opsimGUI.getPrevMap());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nouveau Projet");
        setPreferredSize(new java.awt.Dimension(700, 500));
        setResizable(false);

        conButton.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        conButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        but_prev.setText("< Précedent");
        but_prev.setEnabled(false);

        but_next.setText("Suivant >");

        but_cancel.setText("Annuler");

        but_finish.setText("Terminer");
        but_finish.setEnabled(false);

        javax.swing.GroupLayout conButtonLayout = new javax.swing.GroupLayout(conButton);
        conButton.setLayout(conButtonLayout);
        conButtonLayout.setHorizontalGroup(
            conButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, conButtonLayout.createSequentialGroup()
                .addContainerGap(344, Short.MAX_VALUE)
                .addComponent(but_prev)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(but_next)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(but_finish)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(but_cancel)
                .addContainerGap())
        );
        conButtonLayout.setVerticalGroup(
            conButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(conButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(but_cancel)
                    .addComponent(but_finish)
                    .addComponent(but_next)
                    .addComponent(but_prev))
                .addContainerGap())
        );

        

        conGroupCard.setLayout(this.cl);

        conCard_step1.setLayout(new java.awt.BorderLayout());

        conCard_step1_left.setBackground(new java.awt.Color(255, 255, 255));
        conCard_step1_left.setPreferredSize(new java.awt.Dimension(200, 455));

        but_icon1.setIcon(this.icon_newProj); // NOI18N
        but_icon1.setBorderPainted(false);
        but_icon1.setContentAreaFilled(false);
        but_icon1.setFocusPainted(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Etapes:");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel2.setText("1.   Choix du template");

        jLabel3.setText("2.    ...");

        javax.swing.GroupLayout conCard_step1_leftLayout = new javax.swing.GroupLayout(conCard_step1_left);
        conCard_step1_left.setLayout(conCard_step1_leftLayout);
        conCard_step1_leftLayout.setHorizontalGroup(
            conCard_step1_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conCard_step1_leftLayout.createSequentialGroup()
                .addGroup(conCard_step1_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(conCard_step1_leftLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(conCard_step1_leftLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(conCard_step1_leftLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(conCard_step1_leftLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(conCard_step1_leftLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(but_icon1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        conCard_step1_leftLayout.setVerticalGroup(
            conCard_step1_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, conCard_step1_leftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                .addComponent(but_icon1)
                .addGap(46, 46, 46))
        );

        conCard_step1.add(conCard_step1_left, java.awt.BorderLayout.LINE_START);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Choisisser le template du projet:");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Standards");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("1G");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("2G");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("2G");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("2G Transitoire (2.5G,2.75G)");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("3G");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("3G (IMT-2000)");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("3G Transitoire (3.5G,3.75G,3.9G)");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("4G");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("4G (IMT-Advanced)");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        tree_gen.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane1.setViewportView(tree_gen);

        treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Standards");
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("3GPP");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("IEEE");
        treeNode1.add(treeNode2);
        tree_fam.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane2.setViewportView(tree_fam);

        treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Standards");
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("LTE");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("LTE-Rel. 8");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("LTE-Rel. 9");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("LTE-Advanced");
        javax.swing.tree.DefaultMutableTreeNode treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Rel.10");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Rel. 11");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        tree_tech.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane3.setViewportView(tree_tech);

        jLabel5.setText("Générations:");

        jLabel6.setText("Familles:");

        jLabel7.setText("Technologies:");

        lab_des_lte10.setText("Description:");

        panDes_lte10.setEditable(false);
        panDes_lte10.setText("Créer un nouveau projet basé sur le template LTE-Advanced (Rel.10). LTE-Advanced est un\n standard de communication mobile et le plus avancé des standards Long Term Evolution\n (LTE).\n Il a été soumis à l'UIT-T en 2009, comme système 4G candidat satisfaisant les \n contraintes des standards IMT-Advanced, et fut standardisé par la 3GPP en mars 2011\n sous le label 3GPP Release 10.");
        jScrollPane4.setViewportView(panDes_lte10);

        javax.swing.GroupLayout conCard_step1_rightLayout = new javax.swing.GroupLayout(conCard_step1_right);
        conCard_step1_right.setLayout(conCard_step1_rightLayout);
        conCard_step1_rightLayout.setHorizontalGroup(
            conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(80, 80, 80))
                                    .addComponent(jScrollPane3)))
                            .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane4)
                                .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                                    .addComponent(lab_des_lte10)
                                    .addGap(312, 312, 312))))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        conCard_step1_rightLayout.setVerticalGroup(
            conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lab_des_lte10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        conCard_step1.add(conCard_step1_right, java.awt.BorderLayout.CENTER);

        conGroupCard.add(conCard_step1, "CARD_1");

        conCard_step2.setLayout(new java.awt.BorderLayout());

        conCard_step2_left.setBackground(new java.awt.Color(255, 255, 255));
        conCard_step2_left.setPreferredSize(new java.awt.Dimension(200, 450));

        but_icon2.setIcon(this.icon_newProj); // NOI18N
        but_icon2.setBorderPainted(false);
        but_icon2.setContentAreaFilled(false);
        but_icon2.setFocusPainted(false);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Etapes:");

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        jLabel15.setText("1.   Choix du template");

        jLabel16.setText("2.   Nom et location");

        javax.swing.GroupLayout conCard_step2_leftLayout = new javax.swing.GroupLayout(conCard_step2_left);
        conCard_step2_left.setLayout(conCard_step2_leftLayout);
        conCard_step2_leftLayout.setHorizontalGroup(
            conCard_step2_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conCard_step2_leftLayout.createSequentialGroup()
                .addGroup(conCard_step2_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(conCard_step2_leftLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14))
                    .addGroup(conCard_step2_leftLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(conCard_step2_leftLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15))
                    .addGroup(conCard_step2_leftLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel16))
                    .addGroup(conCard_step2_leftLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(but_icon2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        conCard_step2_leftLayout.setVerticalGroup(
            conCard_step2_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, conCard_step2_leftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                .addComponent(but_icon2)
                .addGap(46, 46, 46))
        );

        conCard_step2.add(conCard_step2_left, java.awt.BorderLayout.LINE_START);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Nom et location du projet:");

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        lab_nameProj.setText("Nom du projet:");
        

        rad_optPI.setSelected(true);
        rad_optPI.setText("Projet interne (depuis base de données OPSIM)");
        

        rad_optPE.setText("Projet externe (depuis un fichier externe )");
        

        lab_location.setText("Location:");

        tf_location.setEnabled(false);

        but_browLocation.setText("Parcourir ...");
        but_browLocation.setEnabled(false);

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        lab_zonEtude.setText("Zone d'étude:");

        cbo_zonEtude.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ngaoundéré", "Yaoundé", "Bertoua", 
        		"Maroua", "Douala", "Garoua", "Bamenda", "Bafoussam", "Ebolowa", "Buéa", "Autre..." }));
        cbo_zonEtude.setSelectedIndex(1);

        lab_lon.setText("Lon:");

        tf_lon.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tf_lon.setText("11.51667");

        lab_lat.setText("Lat:");

        tf_lat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tf_lat.setText("3.86667");

        conGroup_preveiw.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Aperçu zone"));

        con_preview.setBackground(new java.awt.Color(0, 0, 0));

        tf_previewState.setForeground(new java.awt.Color(255, 255, 255));
        tf_previewState.setText("Aucun aperçu disponible");
        


        javax.swing.GroupLayout con_previewLayout = new javax.swing.GroupLayout(con_preview);
        con_preview.setLayout(con_previewLayout);
        con_previewLayout.setHorizontalGroup(
            con_previewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(con_previewLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(tf_previewState)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        con_previewLayout.setVerticalGroup(
            con_previewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(con_previewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tf_previewState)
                .addContainerGap(130, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout conGroup_preveiwLayout = new javax.swing.GroupLayout(conGroup_preveiw);
        conGroup_preveiw.setLayout(conGroup_preveiwLayout);
        conGroup_preveiwLayout.setHorizontalGroup(
            conGroup_preveiwLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(con_preview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        conGroup_preveiwLayout.setVerticalGroup(
            conGroup_preveiwLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(con_preview, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout conCard_step2_rightLayout = new javax.swing.GroupLayout(conCard_step2_right);
        conCard_step2_right.setLayout(conCard_step2_rightLayout);
        conCard_step2_rightLayout.setHorizontalGroup(
            conCard_step2_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conCard_step2_rightLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(conCard_step2_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(conGroup_preveiw, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rad_optPE, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rad_optPI, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, conCard_step2_rightLayout.createSequentialGroup()
                        .addGroup(conCard_step2_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, conCard_step2_rightLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(lab_location)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tf_location, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, conCard_step2_rightLayout.createSequentialGroup()
                                .addComponent(lab_nameProj)
                                .addGap(53, 53, 53)
                                .addComponent(tf_nameProj, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(but_browLocation))
                    .addComponent(jSeparator5, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, conCard_step2_rightLayout.createSequentialGroup()
                        .addComponent(lab_zonEtude)
                        .addGap(27, 27, 27)
                        .addComponent(cbo_zonEtude, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lab_lon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_lon, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lab_lat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_lat, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        conCard_step2_rightLayout.setVerticalGroup(
            conCard_step2_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conCard_step2_rightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(conCard_step2_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_nameProj)
                    .addComponent(tf_nameProj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(rad_optPI)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rad_optPE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(conCard_step2_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_location)
                    .addComponent(tf_location, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(but_browLocation))
                .addGap(18, 18, 18)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(conCard_step2_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_zonEtude)
                    .addComponent(lab_lat)
                    .addComponent(tf_lat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lab_lon)
                    .addComponent(tf_lon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_zonEtude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(conGroup_preveiw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        conCard_step2.add(conCard_step2_right, java.awt.BorderLayout.CENTER);
        conGroupCard.add(conCard_step2, "CARD_2");
        
        getContentPane().add(conGroupCard, java.awt.BorderLayout.CENTER);
        getContentPane().add(conButton, java.awt.BorderLayout.SOUTH);
        
        pack();
    }

    public void initEvents(){
    	this.newProjHE = new NewProjHE(this);
    	
    	but_prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_prevActionPerformed(evt);
            }
        });
    	
    	 but_next.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 but_nextActionPerformed(evt);
             }
         });
    	 
    	 but_cancel.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 but_cancelActionPerformed(evt);
             }
         });   
    	 
    	 but_finish.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 but_finishActionPerformed(evt);
             }
         }); 
    	 
    	 rad_optPI.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 rad_optPIActionPerformed(evt);
             }
         });
    	 
    	 rad_optPE.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 rad_optPEActionPerformed(evt);
             }
         });
    	 
    	 cbo_zonEtude.addItemListener(new ItemListener(){
    		 public void itemStateChanged(ItemEvent event) {
    			 cbo_zonEtudeItemStateChanged(event);
    		 }
    	 });
    	 
    }
    
    public void initEventsTree(){
    	tree_gen.addTreeSelectionListener(new TreeSelectionListener(){
    		 public void valueChanged(TreeSelectionEvent event) {
    			 tree_genValueChanged(event);
    		 }
    	 });
    	 tree_fam.addTreeSelectionListener(new TreeSelectionListener(){
    		 public void valueChanged(TreeSelectionEvent event) {
    			 tree_famValueChanged(event);
    		 }
    	 });
    	 tree_tech.addTreeSelectionListener(new TreeSelectionListener(){
    		 public void valueChanged(TreeSelectionEvent event) {
    			 tree_techValueChanged(event);
    		 }
    	 });    	
    }
    
    private void tree_genValueChanged(TreeSelectionEvent event){
    	if(tree_gen.getLastSelectedPathComponent() != null){
    		String gen = getAbsolutePath(event.getPath());
    		//System.out.println(gen);
    		
    		DefaultMutableTreeNode      root = new DefaultMutableTreeNode("Standards");
    		DefaultMutableTreeNode      root2 = new DefaultMutableTreeNode("Standards");
            DefaultMutableTreeNode      parent;
            DefaultMutableTreeNode      sub_parent;
    		
    		switch(gen){
    			case "Standards1G":
    				parent = new DefaultMutableTreeNode("AMPS");
    		        root.add(parent);
    		        parent = new DefaultMutableTreeNode("Other");
    		        root.add(parent);
    				this.tree_fam = new JTree(root);
    				jScrollPane2.setViewportView(tree_fam);
    				
    				parent = new DefaultMutableTreeNode("Non disponible");
    		        root2.add(parent);
    				this.tree_tech = new JTree(root2);
    				jScrollPane3.setViewportView(tree_tech);
    				
    				this.projModel.getTemplate().setGeneration("1G");
    				//this.projController.setModel(projModel);
    				break;
    			case "Standards2G2G":
    				parent = new DefaultMutableTreeNode("GSM/3GPP");
    		        root.add(parent);
    		        parent = new DefaultMutableTreeNode("3GPP2");
    		        root.add(parent);
    		        parent = new DefaultMutableTreeNode("AMPS");
    		        root.add(parent);
    		        parent = new DefaultMutableTreeNode("Other");
    		        root.add(parent);
    		        this.tree_fam = new JTree(root);
    				jScrollPane2.setViewportView(tree_fam);
    				
    				parent = new DefaultMutableTreeNode("GSM");
    				root2.add(parent);
    		        parent = new DefaultMutableTreeNode("CSM");
    		        root2.add(parent);
    				this.tree_tech = new JTree(root2);
    				jScrollPane3.setViewportView(tree_tech);
    				
    				this.projModel.getTemplate().setGeneration("2G");
    				//this.projController.setModel(projModel);
    				break;
    			case "Standards2G2G Transitoire (2.5G,2.75G)":
    				parent = new DefaultMutableTreeNode("GSM/3GPP");
    		        root.add(parent);
    		        parent = new DefaultMutableTreeNode("3GPP2");
    		        root.add(parent);
    		        parent = new DefaultMutableTreeNode("Other");
    		        root.add(parent);
    		        this.tree_fam = new JTree(root);
    				jScrollPane2.setViewportView(tree_fam);
    				
    				parent = new DefaultMutableTreeNode("HSCSD");
    				root2.add(parent);
    		        parent = new DefaultMutableTreeNode("GPRS");
    		        root2.add(parent);
    		        parent = new DefaultMutableTreeNode("EDGE/EGPRS");
    		        root2.add(parent);
    				this.tree_tech = new JTree(root2);
    				jScrollPane3.setViewportView(tree_tech);
    				
    				this.projModel.getTemplate().setGeneration("2G Transitoire");
    				//this.projController.setModel(projModel);
    				break;
    			case "Standards3G3G (IMT-2000)":
    				parent = new DefaultMutableTreeNode("3GPP");
    		        root.add(parent);
    		        parent = new DefaultMutableTreeNode("3GPP2");
    		        root.add(parent);
    		        this.tree_fam = new JTree(root);
    				jScrollPane2.setViewportView(tree_fam);
    				
    				parent = new DefaultMutableTreeNode("UMTS(UTRA-FDD/W-CDMA)");
    				root2.add(parent);
    		        parent = new DefaultMutableTreeNode("UMTS(UTRA-TDD/TD-SCDMA)");
    		        root2.add(parent);
    		        parent = new DefaultMutableTreeNode("UMTS(UTRA-TDD/TD-CDMA)");
    		        root2.add(parent);
    		        this.tree_tech = new JTree(root2);
    				jScrollPane3.setViewportView(tree_tech);
    				
    				this.projModel.getTemplate().setGeneration("3G (IMT-2000)");
    				//this.projController.setModel(projModel);
    				break;
    			case "Standards3G3G Transitoire (3.5G,3.75G,3.9G)":
    				parent = new DefaultMutableTreeNode("3GPP");
    		        root.add(parent);
    		        parent = new DefaultMutableTreeNode("3GPP2");
    		        root.add(parent);
    		        parent = new DefaultMutableTreeNode("IEEE");
    		        root.add(parent);
    		        this.tree_fam = new JTree(root);
    				jScrollPane2.setViewportView(tree_fam);
    				
    				parent = new DefaultMutableTreeNode("HSPA(HSDPA/HSUPA)");
    				root2.add(parent);
    		        parent = new DefaultMutableTreeNode("HSPA+");
    		        root2.add(parent);
    		        parent = new DefaultMutableTreeNode("LTE(E-UTRA)");
    		        root2.add(parent);
    		        this.tree_tech = new JTree(root2);
    				jScrollPane3.setViewportView(tree_tech);
    				
    				this.projModel.getTemplate().setGeneration("3G Transitoire");
    				//this.projController.setModel(projModel);
    				break;    
    			case "Standards4G4G (IMT-Advanced)":
    				parent = new DefaultMutableTreeNode("3GPP");
    		        root.add(parent);
    		        parent = new DefaultMutableTreeNode("IEEE");
    		        root.add(parent);
    		        this.tree_fam = new JTree(root);
    				jScrollPane2.setViewportView(tree_fam);
    				
    				parent = new DefaultMutableTreeNode("LTE");
					sub_parent = new DefaultMutableTreeNode("LTE-Rel.8");
					parent.add(sub_parent);
					sub_parent = new DefaultMutableTreeNode("LTE-Rel.9");
					parent.add(sub_parent);
					sub_parent = new DefaultMutableTreeNode("LTE-Advanced");
					sub_parent.add(new DefaultMutableTreeNode("Rel.10"));
					sub_parent.add(new DefaultMutableTreeNode("Rel.11"));
					parent.add(sub_parent);
					root2.add(parent);
    		        this.tree_tech = new JTree(root2);
    				jScrollPane3.setViewportView(tree_tech);
    				
    				this.projModel.getTemplate().setGeneration("4G (IMT-Advanced)");
    				//this.projController.setModel(projModel);
    				break; 
    		}
    		this.initEventsTree();
    	}
    }
    
    private void tree_famValueChanged(TreeSelectionEvent event){
    	if(tree_fam.getLastSelectedPathComponent() != null){
    		String gen = this.projModel.getTemplate().getGeneration();
    		String family = getAbsolutePath(event.getPath());
    		//System.out.println(family);
    		
    		DefaultMutableTreeNode      root = new DefaultMutableTreeNode("Standards");
            DefaultMutableTreeNode      parent;
            DefaultMutableTreeNode      sub_parent;
    		
    		switch(family){
    			case "StandardsAMPS":
    				switch(gen){
    					case "1G":
    						parent = new DefaultMutableTreeNode("Non disponible");
    	    		        root.add(parent);
    	    				this.tree_tech = new JTree(root);
    	    				jScrollPane3.setViewportView(tree_tech);
    	    				break;
    					case "2G":  
    						parent = new DefaultMutableTreeNode("Non disponible");
    	    		        root.add(parent);
    	    				this.tree_tech = new JTree(root);
    	    				jScrollPane3.setViewportView(tree_tech);
    						break;
    				} 
    				this.projModel.getTemplate().setFamily("AMPS");
    				//this.projController.setModel(projModel);
    				break;
    			case "StandardsOther":
    				switch(gen){
						case "1G":
							parent = new DefaultMutableTreeNode("Non disponible");
		    		        root.add(parent);
		    				this.tree_tech = new JTree(root);
		    				jScrollPane3.setViewportView(tree_tech);
							break;
						case "2G":  
							parent = new DefaultMutableTreeNode("Non disponible");
		    		        root.add(parent);
		    				this.tree_tech = new JTree(root);
		    				jScrollPane3.setViewportView(tree_tech);
							break;
						case "2G Transitoire":  
							parent = new DefaultMutableTreeNode("Non disponible");
		    		        root.add(parent);
		    				this.tree_tech = new JTree(root);
		    				jScrollPane3.setViewportView(tree_tech);
							break;
					}
    				this.projModel.getTemplate().setFamily("Other");
    				//this.projController.setModel(projModel);
    				break;
    			case "StandardsGSM/3GPP":
    				switch(gen){
	    				case "2G":
							parent = new DefaultMutableTreeNode("GSM");
		    		        root.add(parent);
		    		        parent = new DefaultMutableTreeNode("CSM");
		    		        root.add(parent);
		    				this.tree_tech = new JTree(root);
		    				jScrollPane3.setViewportView(tree_tech);
							break;
	    				case "2G Transitoire":
	    					parent = new DefaultMutableTreeNode("HSCSD");
		    		        root.add(parent);
		    		        parent = new DefaultMutableTreeNode("GPRS");
		    		        root.add(parent);
		    		        parent = new DefaultMutableTreeNode("EDGE/EGPRS");
		    		        root.add(parent);
		    				this.tree_tech = new JTree(root);
		    				jScrollPane3.setViewportView(tree_tech);
							break;
    				}
    				this.projModel.getTemplate().setFamily("GSM/3GPP");
    				//this.projController.setModel(projModel);
    				break;
    			case "Standards3GPP2":
    				switch(gen){
	    				case "2G":
							parent = new DefaultMutableTreeNode("CDMA-One");
		    		        root.add(parent);
		    				this.tree_tech = new JTree(root);
		    				jScrollPane3.setViewportView(tree_tech);
							break;
	    				case "2G Transitoire":
	    					parent = new DefaultMutableTreeNode("CDMA2000-1x");
		    		        root.add(parent);
		    				this.tree_tech = new JTree(root);
		    				jScrollPane3.setViewportView(tree_tech);
							break;
	    				case "3G (IMT-2000)":
	    					parent = new DefaultMutableTreeNode("CDMA2000");
		    		        root.add(parent);
		    		        parent = new DefaultMutableTreeNode("CDMA 1xEVDO Rel.0");
		    		        root.add(parent);	    					
		    				this.tree_tech = new JTree(root);
		    				jScrollPane3.setViewportView(tree_tech);
							break;
	    				case "3G Transitoire":
	    					parent = new DefaultMutableTreeNode("CDMA2000");
		    		        root.add(parent);
		    		        parent = new DefaultMutableTreeNode("CDMA 1xEVDO Rev.A");
		    		        root.add(parent);
		    		        parent = new DefaultMutableTreeNode("CDMA 1xEVDO Rev.B");
		    		        root.add(parent);
		    				this.tree_tech = new JTree(root);
		    				jScrollPane3.setViewportView(tree_tech);
							break;
					}
    				this.projModel.getTemplate().setFamily("3GPP2");
    				//this.projController.setModel(projModel);
    				break;
    			case "Standards3GPP":
    				switch(gen){
    					case "3G (IMT-2000)":
    						parent = new DefaultMutableTreeNode("UMTS(UTRA-FDD/W-CDMA)");
		    		        root.add(parent);
		    		        parent = new DefaultMutableTreeNode("UMTS(UTRA-TDD/TD-SCDMA)");
		    		        root.add(parent);
		    		        parent = new DefaultMutableTreeNode("UMTS(UTRA-TDD/TD-CDMA)");
		    		        root.add(parent);
		    		        this.tree_tech = new JTree(root);
		    				jScrollPane3.setViewportView(tree_tech);
    						break;
    					case "3G Transitoire":
    						parent = new DefaultMutableTreeNode("HSPA(HSDPA/HSUPA)");
		    		        root.add(parent);
		    		        parent = new DefaultMutableTreeNode("HSPA+");
		    		        root.add(parent);
		    		        parent = new DefaultMutableTreeNode("LTE(E-UTRA)");
		    		        root.add(parent);
		    		        this.tree_tech = new JTree(root);
		    				jScrollPane3.setViewportView(tree_tech);
    						break;
    					case "4G (IMT-Advanced)":
    						parent = new DefaultMutableTreeNode("LTE");
    						sub_parent = new DefaultMutableTreeNode("LTE-Rel.8");
    						parent.add(sub_parent);
    						sub_parent = new DefaultMutableTreeNode("LTE-Rel.9");
    						parent.add(sub_parent);
    						sub_parent = new DefaultMutableTreeNode("LTE-Advanced");
    						sub_parent.add(new DefaultMutableTreeNode("Rel.10"));
    						sub_parent.add(new DefaultMutableTreeNode("Rel.11"));
    						parent.add(sub_parent);
		    		        root.add(parent);
		    		        this.tree_tech = new JTree(root);
		    				jScrollPane3.setViewportView(tree_tech);
    						break;
    				}
    				this.projModel.getTemplate().setFamily("3GPP");
    				//this.projController.setModel(projModel);
    				break;
    			case "StandardsIEEE":
    				switch(gen){
    					case "3G Transitoire":
    						parent = new DefaultMutableTreeNode("Mobile WiMAX");
		    		        root.add(parent);
		    		        parent = new DefaultMutableTreeNode("Flash-OFDM");
		    		        root.add(parent);
		    		        this.tree_tech = new JTree(root);
		    				jScrollPane3.setViewportView(tree_tech);
    						break;
    					case "4G (IMT-Advanced)":
    						parent = new DefaultMutableTreeNode("WiMAX-Advanced");
		    		        root.add(parent);
		    		        this.tree_tech = new JTree(root);
		    				jScrollPane3.setViewportView(tree_tech);
    						break;
    				}    	
    				this.projModel.getTemplate().setFamily("IEEE");
    				//this.projController.setModel(projModel);
    				break;
    		}
    		this.initEventsTree();
    	}
    }
    
    private void tree_techValueChanged(TreeSelectionEvent event){
    	if(tree_tech.getLastSelectedPathComponent() != null){
    		if(tree_tech.getLastSelectedPathComponent().toString() != "Standards" &&
        			tree_tech.getLastSelectedPathComponent().toString() != "LTE" &&
        			tree_tech.getLastSelectedPathComponent().toString() != "LTE-Advanced"){
    			String tech  = tree_tech.getLastSelectedPathComponent().toString();
	    		//System.out.println(tech);
    			this.projModel.getTemplate().setName(tech);
	    		this.projModel.getTemplate().setNetwork(tech);
				//this.projController.setModel(projModel);    			
    		}
    		
    	}
    }
    
    private String getAbsolutePath(TreePath treePath){
    	String str = "";
    	for(Object name : treePath.getPath()){
    	if(name.toString() != null)
    		str += name.toString();
    	}
    	return str;
    }
    
    private void but_nextActionPerformed(java.awt.event.ActionEvent evt) {
    	String gen = this.projModel.getTemplate().getGeneration();
    	String fam = this.projModel.getTemplate().getFamily();
    	String tech = this.projModel.getTemplate().getNetwork();
    	//System.out.println("Click on next button");
    	System.out.println("Generation = "+gen);
    	System.out.println("Family = "+fam);
    	System.out.println("Template = "+tech);
    	if(gen != null &&  fam != null && tech != null){
    		cl.show(this.conGroupCard,"CARD_2");
	        this.but_next.setEnabled(false);
	        this.but_prev.setEnabled(true);
	        this.but_finish.setEnabled(true);
    	}
    	else{
    		JOptionPane.showMessageDialog(this, "Veuillez choisir la génération, la famille et la technologie relative à votre template.", "Erreur",
    		JOptionPane.ERROR_MESSAGE);
    	}
        
    }                                        

    private void but_prevActionPerformed(java.awt.event.ActionEvent evt) {   
        cl.show(this.conGroupCard,"CARD_1");
        this.but_prev.setEnabled(false);
        this.but_next.setEnabled(true);
        this.but_finish.setEnabled(false);
    }           
    
    private void rad_optPEActionPerformed(java.awt.event.ActionEvent evt) {                                          
        this.tf_location.setEnabled(true);
        this.but_browLocation.setEnabled(true);
    }                                         

    private void rad_optPIActionPerformed(java.awt.event.ActionEvent evt) {                                          
        this.tf_location.setEnabled(false);
        this.but_browLocation.setEnabled(false);
    }

    private void but_cancelActionPerformed(java.awt.event.ActionEvent evt) {                                           
        this.setVisible(false);
    } 
    
    private void but_finishActionPerformed(java.awt.event.ActionEvent evt) {
    	String nameProj = this.tf_nameProj.getText();
    	DAO<Project> projDAO = DAOFactory.getProjectDAO();
    	FreqBandDAO freqBandDAO = (FreqBandDAO) DAOFactory.getFreqBandDAO();
    	System.out.println("Project Name = "+nameProj);
    	if(!this.tf_nameProj.getText().isEmpty()){
    		if(this.tf_lon.getText() != "0.00000" && this.tf_lat.getText() != "0.00000"){
    			if(this.rad_optPE.isSelected() && !this.tf_location.getText().isEmpty()){
    				this.projModel.setProjExterne(new ProjExterne(this.projModel.getId(),this.tf_location.getText()));
	    		}
    			else if(this.rad_optPI.isSelected()){
    				String name_zone = this.cbo_zonEtude.getSelectedItem().toString();
    				this.projModel.setName(nameProj);
	    			this.projModel.getParameter().getZoneParam().getTargetZone().setLon(Double.parseDouble(this.tf_lon.getText()));
	    			this.projModel.getParameter().getZoneParam().getTargetZone().setLat(Double.parseDouble(this.tf_lat.getText()));
	    			
	    			switch(name_zone){
	    			case "Ngaoundéré":
	    				this.projModel.getParameter().getZoneParam().getTargetZone().setName(name_zone);
	    				this.projModel.getParameter().getZoneParam().getTargetZone().setCountry("Cameroon");
	    				this.projModel.getParameter().getZoneParam().getTargetZone().setProvince("Adamaoua");
	    				this.projModel.getParameter().getZoneParam().setArea(63701);
	    				this.projModel.getParameter().getZoneParam().setDensity(884289);
	        			break;
	        		case "Yaoundé":
	        			this.projModel.getParameter().getZoneParam().getTargetZone().setName(name_zone);
	    				this.projModel.getParameter().getZoneParam().getTargetZone().setCountry("Cameroon");
	    				this.projModel.getParameter().getZoneParam().getTargetZone().setProvince("Centre");
	    				this.projModel.getParameter().getZoneParam().setArea(68953);
	    				this.projModel.getParameter().getZoneParam().setDensity(3098044);
	        			break;
	        		case "Bertoua":
	        			this.projModel.getParameter().getZoneParam().getTargetZone().setName(name_zone);
	    				this.projModel.getParameter().getZoneParam().getTargetZone().setCountry("Cameroon");
	    				this.projModel.getParameter().getZoneParam().getTargetZone().setProvince("Est");
	    				this.projModel.getParameter().getZoneParam().setArea(109002);
	    				this.projModel.getParameter().getZoneParam().setDensity(771755);
	        			break;
	        		case "Maroua":
	        			this.projModel.getParameter().getZoneParam().getTargetZone().setName(name_zone);
	    				this.projModel.getParameter().getZoneParam().getTargetZone().setCountry("Cameroon");
	    				this.projModel.getParameter().getZoneParam().getTargetZone().setProvince("Extreme-Nord");
	    				this.projModel.getParameter().getZoneParam().setArea(34263);
	    				this.projModel.getParameter().getZoneParam().setDensity(3111792);
	        			break;
	        		case "Douala":
	        			this.projModel.getParameter().getZoneParam().getTargetZone().setName(name_zone);
	    				this.projModel.getParameter().getZoneParam().getTargetZone().setCountry("Cameroon");
	    				this.projModel.getParameter().getZoneParam().getTargetZone().setProvince("Littoral");
	    				this.projModel.getParameter().getZoneParam().setArea(20248);
	    				this.projModel.getParameter().getZoneParam().setDensity(2510263);
	        			break;
	        		case "Garoua":
	        			this.projModel.getParameter().getZoneParam().getTargetZone().setName(name_zone);
	    				this.projModel.getParameter().getZoneParam().getTargetZone().setCountry("Cameroon");
	    				this.projModel.getParameter().getZoneParam().getTargetZone().setProvince("Nord");
	    				this.projModel.getParameter().getZoneParam().setArea(66090);
	    				this.projModel.getParameter().getZoneParam().setDensity(1687959);
	        			break;
	        		case "Bamenda":
	        			this.projModel.getParameter().getZoneParam().getTargetZone().setName(name_zone);
	    				this.projModel.getParameter().getZoneParam().getTargetZone().setCountry("Cameroon");
	    				this.projModel.getParameter().getZoneParam().getTargetZone().setProvince("Nord-Ouest");
	    				this.projModel.getParameter().getZoneParam().setArea(17300);
	    				this.projModel.getParameter().getZoneParam().setDensity(1728953);
	        			break;
	        		case "Bafoussam":
	        			this.projModel.getParameter().getZoneParam().getTargetZone().setName(name_zone);
	    				this.projModel.getParameter().getZoneParam().getTargetZone().setCountry("Cameroon");
	    				this.projModel.getParameter().getZoneParam().getTargetZone().setProvince("Ouest");
	    				this.projModel.getParameter().getZoneParam().setArea(13892);
	    				this.projModel.getParameter().getZoneParam().setDensity(1720047);
	        			break;
	        		case "Ebolowa":
	        			this.projModel.getParameter().getZoneParam().getTargetZone().setName(name_zone);
	    				this.projModel.getParameter().getZoneParam().getTargetZone().setCountry("Cameroon");
	    				this.projModel.getParameter().getZoneParam().getTargetZone().setProvince("Sud");
	    				this.projModel.getParameter().getZoneParam().setArea(47191);
	    				this.projModel.getParameter().getZoneParam().setDensity(634655);
	        			break;
	        		case "Buéa":
	        			this.projModel.getParameter().getZoneParam().getTargetZone().setName(name_zone);
	    				this.projModel.getParameter().getZoneParam().getTargetZone().setCountry("Cameroon");
	    				this.projModel.getParameter().getZoneParam().getTargetZone().setProvince("Sud-Ouest");
	    				this.projModel.getParameter().getZoneParam().setArea(25410);
	    				this.projModel.getParameter().getZoneParam().setDensity(1316079);
	        			break;
		    		default:
		    			this.projModel.getParameter().getZoneParam().getTargetZone().setName("Yaoundé");
	    				this.projModel.getParameter().getZoneParam().getTargetZone().setCountry("Cameroon");
	    				this.projModel.getParameter().getZoneParam().getTargetZone().setProvince("Centre");
	    				this.projModel.getParameter().getZoneParam().setArea(68953);
	    				this.projModel.getParameter().getZoneParam().setDensity(3098044);
		    			break;
	    			}
	    			
//	    			this.opsimGUI.setTitle(this.opsimGUI.getDefTitle()+" - "+this.projModel.getName()+".ops"+
//	    					" - ["+this.opsimGUI.getUser().getName()+"]");
	    			// init project obj
	    			this.projModel.getGlobalDesignNet().setName(nameProj);
	    			this.projModel.setFreqBand(freqBandDAO.find("LTE ETRAN FB 41 - TDD"));
	    			this.projModel.setProgress(0.0);
	    			this.projModel.setOver(true);
	    			// Add 2D Map
	    			Map map2D = new Map();
	    			map2D.setName("2D Map");
	    			this.projModel.getMap().add(map2D);
	    			// Add 3D Map
	    			Map map3D = new Map();
	    			map3D.setName("3D Map");
	    			this.projModel.getMap().add(map3D);
	    			
	    			this.projModel = projDAO.create(this.projModel);
	    			this.projController.setModel(this.projModel);
	    			this.opsimGUI.getListControler().remove("ProjectController");
	    			this.opsimGUI.getListControler().put("ProjectController", this.projController);
	    			System.out.println("Nbre Obs before notify = "+this.listObserver.size());	    			
	    			this.setVisible(false);	    			
    			}
    			else JOptionPane.showMessageDialog(this, "Veuillez définir l'emplacement de votre projet", "Erreur",JOptionPane.ERROR_MESSAGE);    			
    		}
    		else JOptionPane.showMessageDialog(this, "Veuillez spécifier la zone d'étude de votre projet", "Erreur",JOptionPane.ERROR_MESSAGE);
    	}
    	else JOptionPane.showMessageDialog(this, "Veuillez définir le nom de votre projet", "Erreur",JOptionPane.ERROR_MESSAGE);
    	this.notifyObserver();
    }
    
    public void cbo_zonEtudeItemStateChanged(ItemEvent event){
    	String value = event.getItem().toString();
    	switch(value){
    		case "Ngaoundéré":
    			this.tf_lon.setText("14.01667");
    			this.tf_lat.setText("5.26667");
    			break;
    		case "Yaoundé":
    			this.tf_lon.setText("11.51667");
    			this.tf_lat.setText("3.86667");
    			break;
    		case "Bertoua":
    			this.tf_lon.setText("13.68333");
    			this.tf_lat.setText("4.58333");
    			break;
    		case "Maroua":
    			this.tf_lon.setText("14.31592");
    			this.tf_lat.setText("10.59095");
    			break;
    		case "Douala":
    			this.tf_lon.setText("9.70840");
    			this.tf_lat.setText("4.04690");
    			break;
    		case "Garoua":
    			this.tf_lon.setText("13.40000");
    			this.tf_lat.setText("9.30000");
    			break;
    		case "Bamenda":
    			this.tf_lon.setText("10.15824");
    			this.tf_lat.setText("5.95266");
    			break;
    		case "Bafoussam":
    			this.tf_lon.setText("10.41786");
    			this.tf_lat.setText("5.47366");
    			break;
    		case "Ebolowa":
    			this.tf_lon.setText("11.90000");
    			this.tf_lat.setText("3.90000");
    			break;
    		case "Buéa":
    			this.tf_lon.setText("9.24100");
    			this.tf_lat.setText("4.15270");
    			break;
    		default:
    			this.tf_lon.setText("0.00000");
    			this.tf_lat.setText("0.00000");
    			break;
    	}
    }
    
    public OpsimGUI getOpsimGUI() {
		return opsimGUI;
	}

	public void setOpsimGUI(OpsimGUI opsimGUI) {
		this.opsimGUI = opsimGUI;
	}

	public static void main(String[] args) {
		//DialogNewProj dialog = new DialogNewProj(null, true);
	}

	/* (non-Javadoc)
	 * @see cm.opsim.observer.Observable#addObserver(cm.opsim.observer.Observer)
	 */
	@Override
	public void addObserver(Observer obs) {
		System.out.println("DialogNewProj: Add obs called ");
		this.listObserver.add(obs);		
		System.out.println("DialogNewProj: Nbre Obs after call = "+this.listObserver.size());
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
}
