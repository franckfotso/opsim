/**
 * 
 */
package cm.opsim.view;

import java.awt.CardLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;

import cm.opsim.controller.PlanCostController;
import cm.opsim.controller.ProjectController;
import cm.opsim.dao.DAO;
import cm.opsim.dao.DAOFactory;
import cm.opsim.event.AbstractHandleEvent;
import cm.opsim.model.CapexOpexPlanif;
import cm.opsim.model.CapexParameter;
import cm.opsim.model.OpexParameter;
import cm.opsim.model.PrevIncomings;
import cm.opsim.model.Project;
import cm.opsim.model.RCapex;
import cm.opsim.model.ROpex;
import cm.opsim.model.Result;
import cm.opsim.observer.Observable;
import cm.opsim.observer.Observer;

/**
 * @author Romuald FOTSO
 *
 */
public class DialogPlanCost extends JDialog implements Observer{
	 private javax.swing.JButton but_cancel;
	    private javax.swing.JButton but_defaultPlan;
	    private javax.swing.JButton but_finish;
	    private javax.swing.JButton but_formPlan;
	    private javax.swing.JButton but_icon1;
	    private javax.swing.JButton but_icon5;
	    private javax.swing.JButton but_icon6;
	    private javax.swing.JButton but_next;
	    private javax.swing.JButton but_prev;
	    private javax.swing.JButton but_reportPlan;
	    private javax.swing.ButtonGroup buttonGroup1;
	    private javax.swing.JPanel conButton;
	    private javax.swing.JPanel conCard_step1;
	    private javax.swing.JPanel conCard_step1_left;
	    private javax.swing.JPanel conCard_step1_right;
	    private javax.swing.JPanel conCard_step2;
	    private javax.swing.JPanel conCard_step2_left;
	    private javax.swing.JPanel conCard_step2_right;
	    private javax.swing.JPanel conCard_step3;
	    private javax.swing.JPanel conCard_step3_left;
	    private javax.swing.JPanel conCard_step3_right;
	    private javax.swing.JPanel conGroupCard;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel10;
	    private javax.swing.JLabel jLabel11;
	    private javax.swing.JLabel jLabel12;
	    private javax.swing.JLabel jLabel13;
	    private javax.swing.JLabel jLabel14;
	    private javax.swing.JLabel jLabel15;
	    private javax.swing.JLabel jLabel2;
	    private javax.swing.JLabel jLabel26;
	    private javax.swing.JLabel jLabel3;
	    private javax.swing.JLabel jLabel4;
	    private javax.swing.JLabel jLabel45;
	    private javax.swing.JLabel jLabel46;
	    private javax.swing.JLabel jLabel5;
	    private javax.swing.JLabel jLabel57;
	    private javax.swing.JLabel jLabel58;
	    private javax.swing.JLabel jLabel59;
	    private javax.swing.JLabel jLabel6;
	    private javax.swing.JLabel jLabel60;
	    private javax.swing.JLabel jLabel61;
	    private javax.swing.JLabel jLabel62;
	    private javax.swing.JLabel jLabel63;
	    private javax.swing.JLabel jLabel64;
	    private javax.swing.JLabel jLabel65;
	    private javax.swing.JLabel jLabel66;
	    private javax.swing.JLabel jLabel67;
	    private javax.swing.JLabel jLabel68;
	    private javax.swing.JLabel jLabel69;
	    private javax.swing.JLabel jLabel7;
	    private javax.swing.JLabel jLabel70;
	    private javax.swing.JLabel jLabel71;
	    private javax.swing.JLabel jLabel72;
	    private javax.swing.JLabel jLabel73;
	    private javax.swing.JLabel jLabel74;
	    private javax.swing.JLabel jLabel79;
	    private javax.swing.JLabel jLabel8;
	    private javax.swing.JLabel jLabel80;
	    private javax.swing.JLabel jLabel81;
	    private javax.swing.JLabel jLabel82;
	    private javax.swing.JLabel jLabel83;
	    private javax.swing.JLabel jLabel84;
	    private javax.swing.JLabel jLabel85;
	    private javax.swing.JLabel jLabel86;
	    private javax.swing.JLabel jLabel87;
	    private javax.swing.JLabel jLabel88;
	    private javax.swing.JLabel jLabel89;
	    private javax.swing.JLabel jLabel9;
	    private javax.swing.JLabel jLabel90;
	    private javax.swing.JLabel jLabel91;
	    private javax.swing.JLabel jLabel92;
	    private javax.swing.JLabel jLabel93;
	    private javax.swing.JLabel jLabel94;
	    private javax.swing.JLabel jLabel95;
	    private javax.swing.JPanel jPanel1;
	    private javax.swing.JPanel jPanel10;
	    private javax.swing.JPanel jPanel11;
	    private javax.swing.JPanel jPanel13;
	    private javax.swing.JPanel jPanel7;
	    private javax.swing.JPanel jPanel8;
	    private javax.swing.JPanel jPanel9;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JScrollPane jScrollPane2;
	    private javax.swing.JScrollPane jScrollPane3;
	    private javax.swing.JSeparator jSeparator1;
	    private javax.swing.JSeparator jSeparator10;
	    private javax.swing.JSeparator jSeparator11;
	    private javax.swing.JSeparator jSeparator15;
	    private javax.swing.JSeparator jSeparator16;
	    private javax.swing.JSeparator jSeparator17;
	    private javax.swing.JSeparator jSeparator19;
	    private javax.swing.JSeparator jSeparator2;
	    private javax.swing.JSeparator jSeparator20;
	    private javax.swing.JSeparator jSeparator21;
	    private javax.swing.JSeparator jSeparator4;
	    private javax.swing.JSeparator jSeparator5;
	    private javax.swing.JSeparator jSeparator6;
	    private javax.swing.JTable tab_prevRec;
	    private javax.swing.JTable tab_rltCAPEX;
	    private javax.swing.JTable tab_rltOPEX;
	    private javax.swing.JTextField tf_adminFG;
	    private javax.swing.JTextField tf_assurance;
	    private javax.swing.JTextField tf_enerElec;
	    private javax.swing.JTextField tf_equipIP;
	    private javax.swing.JTextField tf_facturationAb;
	    private javax.swing.JTextField tf_gc;
	    private javax.swing.JTextField tf_infraIP;
	    private javax.swing.JTextField tf_infraRadio;
	    private javax.swing.JTextField tf_infraRx;
	    private javax.swing.JTextField tf_infraVOIP;
	    private javax.swing.JTextField tf_ingRadio;
	    private javax.swing.JTextField tf_ingReseau;
	    private javax.swing.JTextField tf_installStation;
	    private javax.swing.JTextField tf_interWeb;
	    private javax.swing.JTextField tf_locaFreq;
	    private javax.swing.JTextField tf_locaInfra;
	    private javax.swing.JTextField tf_locaSite;
	    private javax.swing.JTextField tf_locaSiteRadio;
	    private javax.swing.JTextField tf_logiApp;
	    private javax.swing.JTextField tf_marketing;
	    private javax.swing.JTextField tf_mobiler;
	    private javax.swing.JTextField tf_nbreHab;
	    private javax.swing.JTextField tf_percentAb;
	    private javax.swing.JTextField tf_personnel;
	    private javax.swing.JTextField tf_racElec;
	    private javax.swing.JTextField tf_racRxIP;
	    private javax.swing.JTextField tf_rechFrs;
	    private javax.swing.JTextField tf_rechSites;
	    private javax.swing.JTextField tf_suiviProj;
	    private javax.swing.JTextField tf_suiviRx;
	    private javax.swing.JTextField tf_txCroissance;
	    private javax.swing.JTextField tf_txImpots;
	    // End of variables declaration                   
	    private int next_id_card = 2;
	    private Image icone =  Toolkit.getDefaultToolkit().getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/logo.png"));
	    private ImageIcon IconPlanCost =  new ImageIcon(Toolkit.getDefaultToolkit().
				getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/plancost.png")));
	    private ImageIcon IconHelp =  new ImageIcon(Toolkit.getDefaultToolkit().
				getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/help.png")));
	    private AbstractHandleEvent planCostHE;
	    private OpsimGUI opsimGUI;
	    private DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    
	    public DialogPlanCost(OpsimGUI parent, boolean modal, boolean show_res) {
			super(parent, modal);
			this.opsimGUI = parent;
	        this.initComponents();
	        
	        if(show_res){
	 			this.planCov_result();
				CardLayout cl = (CardLayout)(this.conGroupCard.getLayout());
				cl.show(this.conGroupCard,"CARD_3");
				this.but_next.setEnabled(false);
	            this.but_prev.setEnabled(false);
	            this.but_finish.setEnabled(false);
	            this.but_reportPlan.setEnabled(false);
	            this.but_formPlan.setEnabled(false);
	            this.but_defaultPlan.setEnabled(false);
	 		}
	        
	        this.initEvents();
	        this.setLocationRelativeTo(null);
	         try {
		           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		           SwingUtilities.updateComponentTreeUI(this);
		    } 
			catch (InstantiationException e) {} 
			catch (ClassNotFoundException e) {}
			catch (UnsupportedLookAndFeelException e) {} 
			catch (IllegalAccessException e) {}        
	         
	        ProjectController projConWS = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
	  		Project projWS = (Project) projConWS.getModel();	
	  		try{
	  			if(projWS.getPlanification().getCoPlanif().getState().equals("DONE"))this.initDialogValues(projWS);
	  		}
	  		catch(NullPointerException e){
	  			projWS.getPlanification().setCoPlanif(new CapexOpexPlanif(projWS.getPlanification().getId(),"INIT"));
	  			projConWS.setModel(projWS);
	  			this.opsimGUI.getListControler().remove("ProjectController");
				this.opsimGUI.getListControler().put("ProjectController", projConWS);
	  		}         
	        this.setVisible(true);	        
		}
	    
	    public void initComponents(){
	    	this.setIconImage(icone);
			centerRenderer.setHorizontalAlignment( JLabel.CENTER );
			
			buttonGroup1 = new javax.swing.ButtonGroup();
	        conButton = new javax.swing.JPanel();
	        but_prev = new javax.swing.JButton();
	        but_next = new javax.swing.JButton();
	        but_cancel = new javax.swing.JButton();
	        but_finish = new javax.swing.JButton();
	        but_reportPlan = new javax.swing.JButton();
	        but_defaultPlan = new javax.swing.JButton();
	        but_formPlan = new javax.swing.JButton();
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
	        jPanel1 = new javax.swing.JPanel();
	        jLabel5 = new javax.swing.JLabel();
	        jSeparator6 = new javax.swing.JSeparator();
	        jLabel6 = new javax.swing.JLabel();
	        jLabel7 = new javax.swing.JLabel();
	        tf_ingRadio = new javax.swing.JTextField();
	        jLabel8 = new javax.swing.JLabel();
	        jLabel9 = new javax.swing.JLabel();
	        tf_ingReseau = new javax.swing.JTextField();
	        tf_rechFrs = new javax.swing.JTextField();
	        jLabel10 = new javax.swing.JLabel();
	        jSeparator10 = new javax.swing.JSeparator();
	        jLabel11 = new javax.swing.JLabel();
	        jLabel12 = new javax.swing.JLabel();
	        jLabel13 = new javax.swing.JLabel();
	        tf_locaSite = new javax.swing.JTextField();
	        tf_gc = new javax.swing.JTextField();
	        tf_racElec = new javax.swing.JTextField();
	        jLabel14 = new javax.swing.JLabel();
	        jLabel15 = new javax.swing.JLabel();
	        tf_installStation = new javax.swing.JTextField();
	        tf_racRxIP = new javax.swing.JTextField();
	        tf_rechSites = new javax.swing.JTextField();
	        jPanel7 = new javax.swing.JPanel();
	        jLabel26 = new javax.swing.JLabel();
	        jSeparator11 = new javax.swing.JSeparator();
	        jLabel45 = new javax.swing.JLabel();
	        jLabel46 = new javax.swing.JLabel();
	        tf_equipIP = new javax.swing.JTextField();
	        jLabel57 = new javax.swing.JLabel();
	        jLabel58 = new javax.swing.JLabel();
	        tf_infraIP = new javax.swing.JTextField();
	        tf_logiApp = new javax.swing.JTextField();
	        jLabel59 = new javax.swing.JLabel();
	        jSeparator15 = new javax.swing.JSeparator();
	        jLabel60 = new javax.swing.JLabel();
	        jLabel61 = new javax.swing.JLabel();
	        tf_infraRadio = new javax.swing.JTextField();
	        tf_infraRx = new javax.swing.JTextField();
	        tf_infraVOIP = new javax.swing.JTextField();
	        jPanel8 = new javax.swing.JPanel();
	        jLabel62 = new javax.swing.JLabel();
	        jLabel63 = new javax.swing.JLabel();
	        tf_mobiler = new javax.swing.JTextField();
	        tf_suiviProj = new javax.swing.JTextField();
	        conCard_step2 = new javax.swing.JPanel();
	        conCard_step2_left = new javax.swing.JPanel();
	        but_icon5 = new javax.swing.JButton();
	        jLabel64 = new javax.swing.JLabel();
	        jSeparator4 = new javax.swing.JSeparator();
	        jLabel65 = new javax.swing.JLabel();
	        jLabel66 = new javax.swing.JLabel();
	        jLabel89 = new javax.swing.JLabel();
	        conCard_step2_right = new javax.swing.JPanel();
	        jLabel67 = new javax.swing.JLabel();
	        jSeparator16 = new javax.swing.JSeparator();
	        jPanel9 = new javax.swing.JPanel();
	        jLabel69 = new javax.swing.JLabel();
	        jLabel70 = new javax.swing.JLabel();
	        tf_nbreHab = new javax.swing.JTextField();
	        jLabel71 = new javax.swing.JLabel();
	        tf_percentAb = new javax.swing.JTextField();
	        tf_txCroissance = new javax.swing.JTextField();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        tab_prevRec = new javax.swing.JTable();
	        jPanel10 = new javax.swing.JPanel();
	        jLabel79 = new javax.swing.JLabel();
	        jSeparator19 = new javax.swing.JSeparator();
	        jLabel80 = new javax.swing.JLabel();
	        jLabel81 = new javax.swing.JLabel();
	        tf_marketing = new javax.swing.JTextField();
	        jLabel82 = new javax.swing.JLabel();
	        jLabel83 = new javax.swing.JLabel();
	        tf_interWeb = new javax.swing.JTextField();
	        tf_adminFG = new javax.swing.JTextField();
	        jLabel84 = new javax.swing.JLabel();
	        jSeparator20 = new javax.swing.JSeparator();
	        jLabel85 = new javax.swing.JLabel();
	        jLabel86 = new javax.swing.JLabel();
	        tf_assurance = new javax.swing.JTextField();
	        tf_locaSiteRadio = new javax.swing.JTextField();
	        tf_locaFreq = new javax.swing.JTextField();
	        jLabel87 = new javax.swing.JLabel();
	        jLabel88 = new javax.swing.JLabel();
	        jLabel90 = new javax.swing.JLabel();
	        tf_enerElec = new javax.swing.JTextField();
	        jLabel91 = new javax.swing.JLabel();
	        tf_suiviRx = new javax.swing.JTextField();
	        tf_personnel = new javax.swing.JTextField();
	        tf_locaInfra = new javax.swing.JTextField();
	        jSeparator21 = new javax.swing.JSeparator();
	        jLabel92 = new javax.swing.JLabel();
	        jLabel93 = new javax.swing.JLabel();
	        jLabel94 = new javax.swing.JLabel();
	        tf_facturationAb = new javax.swing.JTextField();
	        tf_txImpots = new javax.swing.JTextField();
	        conCard_step3 = new javax.swing.JPanel();
	        conCard_step3_left = new javax.swing.JPanel();
	        but_icon6 = new javax.swing.JButton();
	        jLabel68 = new javax.swing.JLabel();
	        jSeparator5 = new javax.swing.JSeparator();
	        jLabel72 = new javax.swing.JLabel();
	        jLabel73 = new javax.swing.JLabel();
	        jLabel95 = new javax.swing.JLabel();
	        conCard_step3_right = new javax.swing.JPanel();
	        jLabel74 = new javax.swing.JLabel();
	        jSeparator17 = new javax.swing.JSeparator();
	        jPanel11 = new javax.swing.JPanel();
	        jScrollPane2 = new javax.swing.JScrollPane();
	        tab_rltCAPEX = new javax.swing.JTable();
	        jPanel13 = new javax.swing.JPanel();
	        jScrollPane3 = new javax.swing.JScrollPane();
	        tab_rltOPEX = new javax.swing.JTable();

	        //buttonGroup1.add(this.rad_pmOpt1);

	        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	        setTitle("Planification CAPEX/OPEX");
	        setPreferredSize(new java.awt.Dimension(785, 580));
	        setResizable(false);

	        conButton.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
	        conButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

	        but_prev.setText("< Précedent");
	        but_prev.setToolTipText("Retour en arrière");
	        but_prev.setEnabled(false);

	        but_next.setText("Suivant >");
	        but_next.setToolTipText("Aller à l'étape suivante");

	        but_cancel.setText("Annuler");
	        but_cancel.setToolTipText("Annuler l'opération");

	        but_finish.setText("Terminer");
	        but_finish.setToolTipText("Finaliser l'opération");
	        but_finish.setEnabled(false);

	        but_reportPlan.setText("Rapports");
	        but_reportPlan.setToolTipText("Générer rapports");
	        but_reportPlan.setEnabled(false);
	        
	        but_defaultPlan.setText("Par defaut");
	        but_defaultPlan.setToolTipText("Charger les paramètres par défaut");

	        but_formPlan.setText("Formules");
	        but_formPlan.setToolTipText("Voir les formules");
	        but_formPlan.setEnabled(false);

	        javax.swing.GroupLayout conButtonLayout = new javax.swing.GroupLayout(conButton);
	        conButton.setLayout(conButtonLayout);
	        conButtonLayout.setHorizontalGroup(
	            conButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, conButtonLayout.createSequentialGroup()
	                .addContainerGap(122, Short.MAX_VALUE)
	                .addComponent(but_formPlan)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(but_reportPlan)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(but_defaultPlan)
	                .addGap(65, 65, 65)
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
	                    .addComponent(but_prev)
	                    .addComponent(but_reportPlan)
	                    .addComponent(but_formPlan)
	                    .addComponent(but_defaultPlan))
	                .addContainerGap())
	        );

	        getContentPane().add(conButton, java.awt.BorderLayout.SOUTH);

	        conGroupCard.setLayout(new java.awt.CardLayout());

	        conCard_step1.setLayout(new java.awt.BorderLayout());

	        conCard_step1_left.setBackground(new java.awt.Color(255, 255, 255));
	        conCard_step1_left.setPreferredSize(new java.awt.Dimension(200, 455));

	        but_icon1.setIcon(IconPlanCost); // NOI18N
	        but_icon1.setBorderPainted(false);
	        but_icon1.setContentAreaFilled(false);
	        but_icon1.setFocusPainted(false);

	        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jLabel1.setText("Etapes:");

	        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

	        jLabel2.setText("1.   Paramètres CAPEX");

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
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 314, Short.MAX_VALUE)
	                .addComponent(but_icon1)
	                .addGap(46, 46, 46))
	        );

	        conCard_step1.add(conCard_step1_left, java.awt.BorderLayout.LINE_START);

	        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jLabel4.setText("Définisser les paramètres CAPEX:");

	        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

	        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Couts de conception &  de déploiement (CFA)"));

	        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jLabel5.setText("Conception");

	        jLabel6.setText("Ingénieurie radio:");

	        jLabel7.setText("Ingénieurie réseau:");

	        jLabel8.setText("Recherche fournisseur:");

	        jLabel9.setText("Recherche sites:");

	        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jLabel10.setText("Déploiement");

	        jLabel11.setText("Location de site:");

	        jLabel12.setText("Genie civil:");

	        jLabel13.setText("Raccordement électique:");

	        jLabel14.setText("Installation station:");

	        jLabel15.setText("Raccordement réseau IP:");

	        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addComponent(jLabel5)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(jSeparator6))
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel7)
	                            .addComponent(jLabel6))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(tf_ingReseau, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(tf_ingRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addGap(26, 26, 26)
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel8)
	                            .addComponent(jLabel9))
	                        .addGap(18, 18, 18)
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(tf_rechSites, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(tf_rechFrs, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addGap(28, 28, 28))
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
	                        .addComponent(jLabel10)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(jSeparator10))
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addComponent(jLabel13)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addComponent(tf_racElec, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel11)
	                            .addComponent(jLabel12))
	                        .addGap(50, 50, 50)
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(tf_gc, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(tf_locaSite, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addGap(18, 18, 18)
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel15)
	                            .addComponent(jLabel14))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(tf_installStation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(tf_racRxIP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addGap(29, 29, 29))))
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(jLabel5)
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel6)
	                    .addComponent(tf_ingRadio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel8)
	                    .addComponent(tf_rechFrs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel7)
	                    .addComponent(jLabel9)
	                    .addComponent(tf_ingReseau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(tf_rechSites, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(jLabel10)
	                    .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel11)
	                    .addComponent(tf_locaSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel14)
	                    .addComponent(tf_installStation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel12)
	                    .addComponent(tf_gc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel15)
	                    .addComponent(tf_racRxIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel13)
	                    .addComponent(tf_racElec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
	        );

	        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Investissements: Matériels & Logiciels (CFA)"));

	        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jLabel26.setText("Coeur du réseau");

	        jLabel45.setText("Routeurs & Serveurs:");

	        jLabel46.setText("Infrastructures IP:");

	        jLabel57.setText("Logiciels d'application:");

	        jLabel58.setText("Infrastructure VOIP:");

	        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jLabel59.setText("Infrastructures radio & Raccordement interne");

	        jLabel60.setText("Infrastructures radio (E-NodeB avec antenne y comprise):");

	        jLabel61.setText("Infractures réseau (FH, FO, câbles, etc.):");

	        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
	        jPanel7.setLayout(jPanel7Layout);
	        jPanel7Layout.setHorizontalGroup(
	            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel7Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel7Layout.createSequentialGroup()
	                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                            .addGroup(jPanel7Layout.createSequentialGroup()
	                                .addComponent(jLabel46)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                .addComponent(tf_infraIP, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
	                            .addGroup(jPanel7Layout.createSequentialGroup()
	                                .addComponent(jLabel45)
	                                .addGap(26, 26, 26)
	                                .addComponent(tf_equipIP, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel57)
	                            .addComponent(jLabel58))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(tf_infraVOIP, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(tf_logiApp, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addGap(28, 28, 28))
	                    .addGroup(jPanel7Layout.createSequentialGroup()
	                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel60)
	                            .addComponent(jLabel61))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                            .addComponent(tf_infraRadio, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
	                            .addComponent(tf_infraRx))
	                        .addGap(30, 30, 30))
	                    .addGroup(jPanel7Layout.createSequentialGroup()
	                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                            .addGroup(jPanel7Layout.createSequentialGroup()
	                                .addComponent(jLabel59)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(jSeparator15))
	                            .addGroup(jPanel7Layout.createSequentialGroup()
	                                .addComponent(jLabel26)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                        .addGap(0, 0, Short.MAX_VALUE))))
	        );
	        jPanel7Layout.setVerticalGroup(
	            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel7Layout.createSequentialGroup()
	                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addComponent(jLabel26)
	                    .addGroup(jPanel7Layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jSeparator11)))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel45)
	                    .addComponent(tf_equipIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel57)
	                    .addComponent(tf_logiApp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel46)
	                    .addComponent(jLabel58)
	                    .addComponent(tf_infraIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(tf_infraVOIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(jLabel59)
	                    .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel60)
	                    .addComponent(tf_infraRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel61)
	                    .addComponent(tf_infraRx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );

	        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Couts de logistique & suivi (CFA)"));

	        jLabel62.setText("Cout d'acquisition locaux et mobiliers:");

	        jLabel63.setText("Frais de suivi de projet:");

	        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
	        jPanel8.setLayout(jPanel8Layout);
	        jPanel8Layout.setHorizontalGroup(
	            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel8Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel8Layout.createSequentialGroup()
	                        .addComponent(jLabel62)
	                        .addGap(18, 18, 18)
	                        .addComponent(tf_mobiler))
	                    .addGroup(jPanel8Layout.createSequentialGroup()
	                        .addComponent(jLabel63)
	                        .addGap(84, 84, 84)
	                        .addComponent(tf_suiviProj)))
	                .addGap(142, 142, 142))
	        );
	        jPanel8Layout.setVerticalGroup(
	            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel8Layout.createSequentialGroup()
	                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(tf_mobiler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel62))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel63)
	                    .addComponent(tf_suiviProj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
	        );

	        javax.swing.GroupLayout conCard_step1_rightLayout = new javax.swing.GroupLayout(conCard_step1_right);
	        conCard_step1_right.setLayout(conCard_step1_rightLayout);
	        conCard_step1_rightLayout.setHorizontalGroup(
	            conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(conCard_step1_rightLayout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addComponent(jSeparator2)
	                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                .addContainerGap(23, Short.MAX_VALUE))
	        );
	        conCard_step1_rightLayout.setVerticalGroup(
	            conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(conCard_step1_rightLayout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel4)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addContainerGap())
	        );

	        conCard_step1.add(conCard_step1_right, java.awt.BorderLayout.CENTER);

	        conGroupCard.add(conCard_step1, "CARD_1");

	        conCard_step2.setLayout(new java.awt.BorderLayout());

	        conCard_step2_left.setBackground(new java.awt.Color(255, 255, 255));
	        conCard_step2_left.setPreferredSize(new java.awt.Dimension(200, 455));

	        but_icon5.setIcon(IconPlanCost); // NOI18N
	        but_icon5.setBorderPainted(false);
	        but_icon5.setContentAreaFilled(false);
	        but_icon5.setFocusPainted(false);

	        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jLabel64.setText("Etapes:");

	        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

	        jLabel65.setText("1.   Paramètres CAPEX");

	        jLabel66.setText("2.   Paramètres OPEX");

	        jLabel89.setText("3.    ...");

	        javax.swing.GroupLayout conCard_step2_leftLayout = new javax.swing.GroupLayout(conCard_step2_left);
	        conCard_step2_left.setLayout(conCard_step2_leftLayout);
	        conCard_step2_leftLayout.setHorizontalGroup(
	            conCard_step2_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(conCard_step2_leftLayout.createSequentialGroup()
	                .addGroup(conCard_step2_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(conCard_step2_leftLayout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jLabel64))
	                    .addGroup(conCard_step2_leftLayout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(conCard_step2_leftLayout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jLabel65))
	                    .addGroup(conCard_step2_leftLayout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jLabel66))
	                    .addGroup(conCard_step2_leftLayout.createSequentialGroup()
	                        .addGap(23, 23, 23)
	                        .addComponent(but_icon5, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(conCard_step2_leftLayout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jLabel89)))
	                .addContainerGap(13, Short.MAX_VALUE))
	        );
	        conCard_step2_leftLayout.setVerticalGroup(
	            conCard_step2_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, conCard_step2_leftLayout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel64)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel65)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel66)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel89)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 300, Short.MAX_VALUE)
	                .addComponent(but_icon5)
	                .addGap(46, 46, 46))
	        );

	        conCard_step2.add(conCard_step2_left, java.awt.BorderLayout.LINE_START);

	        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jLabel67.setText("Définisser les paramètres OPEX:");

	        jSeparator16.setForeground(new java.awt.Color(0, 0, 0));

	        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Prévision des recettes (CFA)"));

	        jLabel69.setText("Nombre d'habitants:");

	        jLabel70.setText("Pourcentage d'abonnés:");

	        jLabel71.setText("Taux de croissance par an:");

	        tab_prevRec.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {
	                {" Classe A", null, null},
	                {" Classe B", null, null},
	                {" Classe C", null, null},
	                {" Classe D", null, null}
	            },
	            new String [] {
	                "Classe d'abonnés", "Revenue par abonné et par classe", "Taux d'abonnés par abonné"
	            }
	        ) {
	            boolean[] canEdit = new boolean [] {
	                false, true, true
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        });
	        tab_prevRec.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	        tab_prevRec.getColumnModel().getColumn(0).setPreferredWidth(116);
	        tab_prevRec.getColumnModel().getColumn(1).setPreferredWidth(200);
	        tab_prevRec.getColumnModel().getColumn(2).setPreferredWidth(200);
	        
	        tab_prevRec.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
	        tab_prevRec.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
	        tab_prevRec.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
	        jScrollPane1.setViewportView(tab_prevRec);

	        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
	        jPanel9.setLayout(jPanel9Layout);
	        jPanel9Layout.setHorizontalGroup(
	            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel9Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel9Layout.createSequentialGroup()
	                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel70)
	                            .addComponent(jLabel69))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(jPanel9Layout.createSequentialGroup()
	                                .addComponent(tf_percentAb, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                            .addGroup(jPanel9Layout.createSequentialGroup()
	                                .addComponent(tf_nbreHab, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addGap(18, 18, 18)
	                                .addComponent(jLabel71)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                .addComponent(tf_txCroissance, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
	                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(0, 8, Short.MAX_VALUE))
	        );
	        jPanel9Layout.setVerticalGroup(
	            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel9Layout.createSequentialGroup()
	                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel69)
	                    .addComponent(tf_nbreHab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel71)
	                    .addComponent(tf_txCroissance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel70)
	                    .addComponent(tf_percentAb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(0, 8, Short.MAX_VALUE))
	        );

	        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("OPEX (CFA)"));

	        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jLabel79.setText("Frais commerçiaux et generaux (par an)");

	        jLabel80.setText("Dépenses marketing:");

	        jLabel81.setText("Interconnexion web:");

	        jLabel82.setText("Administration et frais generaux:");

	        jLabel83.setText("Assurance:");

	        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jLabel84.setText("Fonctions du réseau (par an)");

	        jLabel85.setText("Location des sites radio:");

	        jLabel86.setText("Location des fréquences:");

	        jLabel87.setText("Location d'infrastructure:");

	        jLabel88.setText("Formation du personnel:");

	        jLabel90.setText("Energie electrique:");

	        jLabel91.setText("Maintenance et suivi réseau:");

	        jLabel92.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jLabel92.setText("Fonctions des abonnés");

	        jLabel93.setText("Frais de facturation des abonnés:");

	        jLabel94.setText("Taux d'impôts sur les benéfices:");

	        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
	        jPanel10.setLayout(jPanel10Layout);
	        jPanel10Layout.setHorizontalGroup(
	            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel10Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
	                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel93)
	                            .addComponent(jLabel94))
	                        .addGap(18, 18, 18)
	                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(tf_txImpots, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(tf_facturationAb, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
	                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                            .addGroup(jPanel10Layout.createSequentialGroup()
	                                .addComponent(jLabel81)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                .addComponent(tf_interWeb, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
	                            .addGroup(jPanel10Layout.createSequentialGroup()
	                                .addComponent(jLabel80)
	                                .addGap(26, 26, 26)
	                                .addComponent(tf_marketing, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel82)
	                            .addComponent(jLabel83))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                            .addComponent(tf_assurance, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
	                            .addComponent(tf_adminFG))
	                        .addGap(44, 44, 44))
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
	                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel86)
	                            .addComponent(jLabel90))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(jPanel10Layout.createSequentialGroup()
	                                .addComponent(tf_locaFreq, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                .addComponent(jLabel88))
	                            .addGroup(jPanel10Layout.createSequentialGroup()
	                                .addComponent(tf_enerElec, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                .addComponent(jLabel91)))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                            .addComponent(tf_personnel, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
	                            .addComponent(tf_suiviRx))
	                        .addGap(41, 41, 41))
	                    .addGroup(jPanel10Layout.createSequentialGroup()
	                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
	                                .addComponent(jLabel92)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(jSeparator21, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
	                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
	                                    .addComponent(jLabel84)
	                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                    .addComponent(jSeparator20))
	                                .addGroup(jPanel10Layout.createSequentialGroup()
	                                    .addComponent(jLabel79)
	                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                    .addComponent(jSeparator19, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))))
	                        .addGap(0, 0, Short.MAX_VALUE))
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
	                        .addComponent(jLabel85)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addComponent(tf_locaSiteRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addComponent(jLabel87)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addComponent(tf_locaInfra, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(0, 0, Short.MAX_VALUE))))
	        );
	        jPanel10Layout.setVerticalGroup(
	            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel10Layout.createSequentialGroup()
	                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel79)
	                    .addGroup(jPanel10Layout.createSequentialGroup()
	                        .addGap(12, 12, 12)
	                        .addComponent(jSeparator19, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel80)
	                    .addComponent(tf_marketing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel82)
	                    .addComponent(tf_adminFG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel81)
	                    .addComponent(jLabel83)
	                    .addComponent(tf_interWeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(tf_assurance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(jLabel84)
	                    .addComponent(jSeparator20, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel85)
	                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(tf_locaSiteRadio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addComponent(jLabel87)
	                        .addComponent(tf_locaInfra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(jLabel86)
	                        .addComponent(tf_locaFreq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addComponent(jLabel88))
	                    .addComponent(tf_personnel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel90)
	                    .addComponent(tf_enerElec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel91)
	                    .addComponent(tf_suiviRx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(jLabel92)
	                    .addComponent(jSeparator21, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel93)
	                    .addComponent(tf_facturationAb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel94)
	                    .addComponent(tf_txImpots, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
	        );

	        javax.swing.GroupLayout conCard_step2_rightLayout = new javax.swing.GroupLayout(conCard_step2_right);
	        conCard_step2_right.setLayout(conCard_step2_rightLayout);
	        conCard_step2_rightLayout.setHorizontalGroup(
	            conCard_step2_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(conCard_step2_rightLayout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(conCard_step2_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addComponent(jLabel67)
	                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addComponent(jSeparator16)
	                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
	                .addContainerGap(28, Short.MAX_VALUE))
	        );
	        conCard_step2_rightLayout.setVerticalGroup(
	            conCard_step2_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(conCard_step2_rightLayout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel67)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addContainerGap())
	        );

	        conCard_step2.add(conCard_step2_right, java.awt.BorderLayout.CENTER);

	        conGroupCard.add(conCard_step2, "CARD_2");

	        conCard_step3.setLayout(new java.awt.BorderLayout());

	        conCard_step3_left.setBackground(new java.awt.Color(255, 255, 255));
	        conCard_step3_left.setPreferredSize(new java.awt.Dimension(200, 455));

	        but_icon6.setIcon(IconPlanCost); // NOI18N
	        but_icon6.setBorderPainted(false);
	        but_icon6.setContentAreaFilled(false);
	        but_icon6.setFocusPainted(false);

	        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jLabel68.setText("Etapes:");

	        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

	        jLabel72.setText("1.   Paramètres CAPEX");

	        jLabel73.setText("2.   Paramètres OPEX");

	        jLabel95.setText("3.   Résultats");

	        javax.swing.GroupLayout conCard_step3_leftLayout = new javax.swing.GroupLayout(conCard_step3_left);
	        conCard_step3_left.setLayout(conCard_step3_leftLayout);
	        conCard_step3_leftLayout.setHorizontalGroup(
	            conCard_step3_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(conCard_step3_leftLayout.createSequentialGroup()
	                .addGroup(conCard_step3_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(conCard_step3_leftLayout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jLabel68))
	                    .addGroup(conCard_step3_leftLayout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(conCard_step3_leftLayout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jLabel72))
	                    .addGroup(conCard_step3_leftLayout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jLabel73))
	                    .addGroup(conCard_step3_leftLayout.createSequentialGroup()
	                        .addGap(23, 23, 23)
	                        .addComponent(but_icon6, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(conCard_step3_leftLayout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jLabel95)))
	                .addContainerGap(13, Short.MAX_VALUE))
	        );
	        conCard_step3_leftLayout.setVerticalGroup(
	            conCard_step3_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, conCard_step3_leftLayout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel68)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel72)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel73)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel95)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 300, Short.MAX_VALUE)
	                .addComponent(but_icon6)
	                .addGap(46, 46, 46))
	        );

	        conCard_step3.add(conCard_step3_left, java.awt.BorderLayout.LINE_START);

	        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jLabel74.setText("Résultats des prévision et calculs des coûts");

	        jSeparator17.setForeground(new java.awt.Color(0, 0, 0));

	        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Résultats du CAPEX"));

	        tab_rltCAPEX.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {
	                {" Cout de conception", null},
	                {" Investissement pour matériels et logiciels", null},
	                {" Cout de déploiement", null},
	                {" Cout de logistique et mobilier", null},
	                {" TOTAL CAPEX", null}
	            },
	            new String [] {
	                "Données", "Montant (en FCFA)"
	            }
	        ) {
	            boolean[] canEdit = new boolean [] {
	                false, false
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        });
	        jScrollPane2.setViewportView(tab_rltCAPEX);

	        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
	        jPanel11.setLayout(jPanel11Layout);
	        jPanel11Layout.setHorizontalGroup(
	            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap())
	        );
	        jPanel11Layout.setVerticalGroup(
	            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel11Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );

	        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Résultats du OPEX"));

	        tab_rltOPEX.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {
	                {" 1", null, null, null},
	                {" 2", null, null, null},
	                {" 3", null, null, null},
	                {" 4", null, null, null},
	                {" 5", null, null, null}
	            },
	            new String [] {
	                "Année", "OPEX (CFA)", "Recettes (CFA)", "Benefices (CFA)"
	            }
	        ) {
	            boolean[] canEdit = new boolean [] {
	                false, false, true, true
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        });
	        jScrollPane3.setViewportView(tab_rltOPEX);

	        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
	        jPanel13.setLayout(jPanel13Layout);
	        jPanel13Layout.setHorizontalGroup(
	            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel13Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jScrollPane3)
	                .addContainerGap())
	        );
	        jPanel13Layout.setVerticalGroup(
	            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel13Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );

	        javax.swing.GroupLayout conCard_step3_rightLayout = new javax.swing.GroupLayout(conCard_step3_right);
	        conCard_step3_right.setLayout(conCard_step3_rightLayout);
	        conCard_step3_rightLayout.setHorizontalGroup(
	            conCard_step3_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(conCard_step3_rightLayout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(conCard_step3_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addComponent(jSeparator17)
	                    .addComponent(jLabel74)
	                    .addGroup(conCard_step3_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                        .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
	                .addContainerGap(31, Short.MAX_VALUE))
	        );
	        conCard_step3_rightLayout.setVerticalGroup(
	            conCard_step3_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(conCard_step3_rightLayout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel74)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(18, 18, 18)
	                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(131, Short.MAX_VALUE))
	        );

	        jPanel11.getAccessibleContext().setAccessibleName("Résultat du CAPEX");

	        conCard_step3.add(conCard_step3_right, java.awt.BorderLayout.CENTER);

	        conGroupCard.add(conCard_step3, "CARD_3");

	        getContentPane().add(conGroupCard, java.awt.BorderLayout.CENTER);

	        pack();
	    }
	    
	    public void initEvents(){
			//this.planCostHE = new PlanningCostHE(this);
	    	
	    	but_reportPlan.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                //but_reportPlanActionPerformed(evt);
	            }
	        });
			
			but_finish.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                but_finishActionPerformed(evt);
	            }
	        });		

	        but_cancel.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                but_cancelActionPerformed(evt);
	            }
	        });
	        
	        but_next.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                but_nextActionPerformed(evt);
	            }
	        });
	        
	        but_prev.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                but_prevActionPerformed(evt);
	            }
	        });
	        
	        this.but_defaultPlan.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	but_defaultPlanActionPerformed(evt);
	            }
	        });
	    }
	    
	    private void but_defaultPlanActionPerformed(java.awt.event.ActionEvent evt) {
			switch(this.next_id_card-1){
				case 1:
					this.default_step1();
					break;
				case 2:
					this.default_step2();
					break;
			}
		}
	    
	    private void but_nextActionPerformed(java.awt.event.ActionEvent evt) {                                         
			//System.out.println("ID next card: "+this.next_id_card);
	        CardLayout cl = (CardLayout)(this.conGroupCard.getLayout());
	        if(this.next_id_card == 2){ 
	        	if(!this.tf_ingRadio.getText().isEmpty() && !this.tf_ingReseau.getText().isEmpty() && !this.tf_rechFrs.getText().isEmpty() 
	        			&& !this.tf_rechSites.getText().isEmpty() && !this.tf_locaSite.getText().isEmpty() && !this.tf_gc.getText().isEmpty() 
	        			&& !this.tf_racElec.getText().isEmpty() && !this.tf_installStation.getText().isEmpty() && !this.tf_racRxIP.getText().isEmpty() 
	        			&& !this.tf_equipIP.getText().isEmpty() && !this.tf_infraIP.getText().isEmpty() && !this.tf_logiApp.getText().isEmpty() 
	        			&& !this.tf_infraVOIP.getText().isEmpty() && !this.tf_infraRadio.getText().isEmpty() && !this.tf_infraRx.getText().isEmpty() 
	        			&& !this.tf_mobiler.getText().isEmpty() && !this.tf_suiviProj.getText().isEmpty()){
	        		cl.show(this.conGroupCard,"CARD_2");
		        	this.but_next.setEnabled(true);
		            this.but_prev.setEnabled(true);
		            this.but_finish.setEnabled(false);
		            this.but_reportPlan.setEnabled(false);
		            this.but_formPlan.setEnabled(false);
		            this.but_defaultPlan.setEnabled(true);
		            this.planCov_step1();
		        	this.next_id_card++;
	        	}
	        	else{
	        			JOptionPane.showMessageDialog(this, 
	            			"Veuillez définir tous les paramètres CAPEX \n" +
	            			"avant d'aller à l'étape suivante.",
	            			"Planification CAPEX/OPEX: Erreur",JOptionPane.ERROR_MESSAGE); 
	        	}	        	
	        }
	        else if(this.next_id_card == 3){
	        	if(!this.tf_nbreHab.getText().isEmpty() && !this.tf_percentAb.getText().isEmpty() && !this.tf_txCroissance.getText().isEmpty()
	        			&& !this.tf_marketing.getText().isEmpty() && !this.tf_interWeb.getText().isEmpty() && !this.tf_adminFG.getText().isEmpty()
	        			&& !this.tf_assurance.getText().isEmpty() && !this.tf_locaSiteRadio.getText().isEmpty() && !this.tf_locaFreq.getText().isEmpty()
	        			&& !this.tf_enerElec.getText().isEmpty() && !this.tf_locaInfra.getText().isEmpty() && !this.tf_personnel.getText().isEmpty()
	        			&& !this.tf_suiviRx.getText().isEmpty() && !this.tf_facturationAb.getText().isEmpty() && !this.tf_txImpots.getText().isEmpty()
	        			&& !this.isEmptyTabVal(tab_prevRec)){
	        		cl.show(this.conGroupCard,"CARD_3");
		        	this.but_next.setEnabled(false);
		            this.but_prev.setEnabled(true);
		            this.but_finish.setEnabled(true);
		            this.but_reportPlan.setEnabled(true);
		            this.but_formPlan.setEnabled(true);
		            this.but_defaultPlan.setEnabled(false);
		            this.planCov_step2();
		            this.planCov_result();
		        	this.next_id_card++;
	        	}
	        	else{
	        		JOptionPane.showMessageDialog(this, 
	            			"Veuillez définir tous les paramètres OPEX \n" +
	            			"avant d'aller à l'étape suivante.",
	            			"Planification CAPEX/OPEX: Erreur",JOptionPane.ERROR_MESSAGE); 
	        	}
	        	
	        }
	        else {
	            cl.show(this.conGroupCard,"CARD_1");
	            this.but_next.setEnabled(true);
	            this.but_prev.setEnabled(true);
	            this.but_finish.setEnabled(false);
	            this.but_reportPlan.setEnabled(false);
	            this.but_formPlan.setEnabled(false);
	            this.but_defaultPlan.setEnabled(true);
	            this.next_id_card = 2;
	        }     
	        
	    } 
	    
	    private void but_prevActionPerformed(java.awt.event.ActionEvent evt) {                                         
			this.next_id_card--;
			//System.out.println("ID next card: "+this.next_id_card);
	        CardLayout cl = (CardLayout)(this.conGroupCard.getLayout());
	        if(this.next_id_card == 2){
	            cl.show(this.conGroupCard,"CARD_1");
	            this.but_next.setEnabled(true);
	            this.but_prev.setEnabled(false);
	            this.but_finish.setEnabled(false);
	            this.but_reportPlan.setEnabled(false);
	            this.but_formPlan.setEnabled(false);
	            this.but_defaultPlan.setEnabled(true);
	            
	        }
	        else if(this.next_id_card == 3){
	            cl.show(this.conGroupCard,"CARD_2");
	            this.but_next.setEnabled(true);
	            this.but_prev.setEnabled(true);
	            this.but_finish.setEnabled(false);
	            this.but_reportPlan.setEnabled(false);
	            this.but_formPlan.setEnabled(false);
	            this.but_defaultPlan.setEnabled(true);
	        }
	        else {            
	            cl.show(this.conGroupCard,"CARD_3");
	            this.but_next.setEnabled(false);
	            this.but_prev.setEnabled(true);
	            this.but_finish.setEnabled(true);
	            this.but_reportPlan.setEnabled(true);
	            this.but_formPlan.setEnabled(true);
	            this.but_defaultPlan.setEnabled(false);
	        }     
	    }
	    
	    private void but_cancelActionPerformed(java.awt.event.ActionEvent evt) {                                           
			this.setVisible(false);
	    } 
	    
	    private void but_finishActionPerformed(java.awt.event.ActionEvent evt) { 
	    	DAO<Project> projDAO = DAOFactory.getProjectDAO();
			ProjectController projConWS = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
			Project projWS = (Project) projConWS.getModel();
			projWS.getPlanification().getCoPlanif().setState("DONE");
			double progress = projWS.getProgress();
			if(progress < 43.0)projWS.setProgress(43.0);
			projWS = projDAO.update(projWS);
			projConWS.setModel(projWS);
			this.opsimGUI.getListControler().remove("ProjectController");
			this.opsimGUI.getListControler().put("ProjectController", projConWS);
	    	this.setVisible(false);
	    } 
	    
	    public void planCov_step1(){
	    	ProjectController projConWS = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
			Project projWS = (Project) projConWS.getModel();
			
			CapexParameter capexParam = projWS.getParameter().getCapexParam();
			capexParam.setIngRadio(Double.parseDouble(this.tf_ingRadio.getText().toString()));
			capexParam.setIngReseau(Double.parseDouble(this.tf_ingReseau.getText().toString()));
			capexParam.setRechFrs(Double.parseDouble(this.tf_rechFrs.getText().toString()));
			capexParam.setRechSites(Double.parseDouble(this.tf_rechSites.getText().toString()));
			capexParam.setLocaSites(Double.parseDouble(this.tf_locaSite.getText().toString()));
			capexParam.setGenieCivil(Double.parseDouble(this.tf_gc.getText().toString()));
			capexParam.setRacElec(Double.parseDouble(this.tf_racElec.getText().toString()));
			capexParam.setInstallStation(Double.parseDouble(this.tf_installStation.getText().toString()));
			capexParam.setRacRxIP(Double.parseDouble(this.tf_racRxIP.getText().toString()));
			capexParam.setEquipRx(Double.parseDouble(this.tf_equipIP.getText().toString()));
			capexParam.setInfraIP(Double.parseDouble(this.tf_infraIP.getText().toString()));
			capexParam.setLogiApps(Double.parseDouble(this.tf_logiApp.getText().toString()));
			capexParam.setInfraVOIP(Double.parseDouble(this.tf_infraVOIP.getText().toString()));
			capexParam.setInfraRadio(Double.parseDouble(this.tf_infraRadio.getText().toString()));
			capexParam.setInfraRx(Double.parseDouble(this.tf_infraRx.getText().toString()));
			capexParam.setLocauxMobilers(Double.parseDouble(this.tf_mobiler.getText().toString()));
			capexParam.setSuiviProj(Double.parseDouble(this.tf_suiviProj.getText().toString()));
			
			projWS.getParameter().setCapexParam(capexParam);
			projWS.setUpdatedDate(new Date());
			projConWS.setModel(projWS);
			this.opsimGUI.getListControler().remove("ProjectController");
			this.opsimGUI.getListControler().put("ProjectController", projConWS);			
	    }
	    
	    public void default_step1(){
	    	this.tf_ingRadio.setText("20000000");
	    	this.tf_ingReseau.setText("25000000");
	    	this.tf_rechFrs.setText("1000000");
			this.tf_rechSites.setText("1500000");
			this.tf_locaSite.setText("3500000");
			this.tf_gc.setText("50000000");
			this.tf_racElec.setText("200000");
			this.tf_installStation.setText("3000000");
			this.tf_racRxIP.setText("10000000");
			this.tf_equipIP.setText("2000000000");
			this.tf_infraIP.setText("250000000");
			this.tf_logiApp.setText("25000000");
			this.tf_infraVOIP.setText("250000000");
			this.tf_infraRadio.setText("75000000");
			this.tf_infraRx.setText("25000000");
			this.tf_mobiler.setText("1000000");
			this.tf_suiviProj.setText("10000000");
	    }
	    
	    public void planCov_step2(){
	    	ProjectController projConWS = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
			Project projWS = (Project) projConWS.getModel();
			
			OpexParameter opexParam = projWS.getParameter().getOpexParam();
			opexParam.setNbResident(Double.parseDouble(this.tf_nbreHab.getText().toString()));
			opexParam.setPercentCust(Double.parseDouble(this.tf_percentAb.getText().toString()));
			opexParam.setGrowingRate(Double.parseDouble(this.tf_txCroissance.getText().toString()));
			opexParam.setMarketing(Double.parseDouble(this.tf_marketing.getText().toString()));
			opexParam.setInterWeb(Double.parseDouble(this.tf_interWeb.getText().toString()));
			opexParam.setAdminFGen(Double.parseDouble(this.tf_adminFG.getText().toString()));
			opexParam.setAssurance(Double.parseDouble(this.tf_assurance.getText().toString()));
			opexParam.setLocaSiteRadio(Double.parseDouble(this.tf_locaSiteRadio.getText().toString()));
			opexParam.setLocaFreq(Double.parseDouble(this.tf_locaFreq.getText().toString()));
			opexParam.setEnerElec(Double.parseDouble(this.tf_enerElec.getText().toString()));
			opexParam.setLocaInfra(Double.parseDouble(this.tf_locaInfra.getText().toString()));
			opexParam.setFormaPerso(Double.parseDouble(this.tf_personnel.getText().toString()));
			opexParam.setSuiviRx(Double.parseDouble(this.tf_suiviRx.getText().toString()));
			opexParam.setFactAb(Double.parseDouble(this.tf_facturationAb.getText().toString()));
			opexParam.setTxImpot(Double.parseDouble(this.tf_txImpots.getText().toString()));
			
			ArrayList<PrevIncomings> listPrevIn = new ArrayList<PrevIncomings>();
			int n_row = tab_prevRec.getRowCount();
			
			System.out.println("N. row tab_prevRec: "+n_row);
			for(int i=0; i<n_row;i++){
				PrevIncomings prevIn = new PrevIncomings();
				prevIn.setInByCust(Double.parseDouble(tab_prevRec.getModel().getValueAt(i, 1).toString()));
				prevIn.setCustRate(Double.parseDouble(tab_prevRec.getModel().getValueAt(i, 2).toString()));
				listPrevIn.add(prevIn);
			}
			opexParam.setListPrevIncomings(listPrevIn);
			
			projWS.getParameter().setOpexParam(opexParam);
			projWS.setUpdatedDate(new Date());
			projConWS.setModel(projWS);
			this.opsimGUI.getListControler().remove("ProjectController");
			this.opsimGUI.getListControler().put("ProjectController", projConWS);			
			
	    }
	    
	    public void default_step2(){
	    	this.tf_nbreHab.setText("25000");
	    	this.tf_percentAb.setText("10");
	    	this.tf_txCroissance.setText("0.02");
			this.tf_marketing.setText("50000000");
			this.tf_interWeb.setText("7200000");
			this.tf_adminFG.setText("150000000");
			this.tf_assurance.setText("120000000");
			this.tf_locaSiteRadio.setText("150000000");
			this.tf_locaFreq.setText("120000000");
			this.tf_enerElec.setText("1000000000");
			this.tf_locaInfra.setText("20000000");
			this.tf_personnel.setText("12000000");
			this.tf_suiviRx.setText("100000000");
			this.tf_facturationAb.setText(Integer.toString(25000*10*10));
			this.tf_txImpots.setText("0.385");
			
			tab_prevRec.setModel(new javax.swing.table.DefaultTableModel(
		            new Object [][] {
		                {" Classe A", "7000", "0.3"},
		                {" Classe B", "7500", "0.025"},
		                {" Classe C", "5000", "0.05"},
		                {" Classe D", "6000", "0.04"}
		            },
		            new String [] {
		                "Classe d'abonnés", "Revenue par abonné et par classe", "Taux d'abonnés par abonné"
		            }
		        ) {
		            boolean[] canEdit = new boolean [] {
		                false, true, true
		            };

		            public boolean isCellEditable(int rowIndex, int columnIndex) {
		                return canEdit [columnIndex];
		            }
		        });
		        tab_prevRec.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		        tab_prevRec.getColumnModel().getColumn(0).setPreferredWidth(116);
		        tab_prevRec.getColumnModel().getColumn(1).setPreferredWidth(200);
		        tab_prevRec.getColumnModel().getColumn(2).setPreferredWidth(200);
		        
		        tab_prevRec.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		        tab_prevRec.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		        tab_prevRec.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		        jScrollPane1.setViewportView(tab_prevRec);
		        jScrollPane1.repaint();
		        if (tab_prevRec.getColumnModel().getColumnCount() > 0) {
		        	tab_prevRec.getColumnModel().getColumn(0).setResizable(false);
		        	tab_prevRec.getColumnModel().getColumn(1).setResizable(false);
		        	tab_prevRec.getColumnModel().getColumn(2).setResizable(false);
		        }
	    }
	    
	    public void planCov_result(){
	    	ProjectController projConWS = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
			Project projWS = (Project) projConWS.getModel();
			PlanCostController planCostConWS = (PlanCostController) this.opsimGUI.getListControler().get("PlanCostController");
			CapexOpexPlanif capexOpexPlanifWS = projWS.getPlanification().getCoPlanif();
			planCostConWS.setModel(capexOpexPlanifWS);
			
			Result res = planCostConWS.calResult(projWS);
			RCapex rcapex = res.getRcapex();
			ArrayList<ROpex> listROpex = res.getListROpex();
			
			tab_rltCAPEX.setModel(new javax.swing.table.DefaultTableModel(
		            new Object [][] {
		                {" Cout de conception", BigDecimal.valueOf(rcapex.getConceptCost()).toPlainString()},
		                {" Investissement pour matériels et logiciels", BigDecimal.valueOf(rcapex.getInvMatLogi()).toPlainString()},
		                {" Cout de déploiement", BigDecimal.valueOf(rcapex.getDeployCost()).toPlainString()},
		                {" Cout de logistique et mobilier", BigDecimal.valueOf(rcapex.getLogisCost()).toPlainString()},
		                {" TOTAL CAPEX", BigDecimal.valueOf(rcapex.getCapexTotal()).toPlainString()}
		            },
		            new String [] {
		                "Données", "Montant (en FCFA)"
		            }
		        ) {
		            boolean[] canEdit = new boolean [] {
		                false, false
		            };

		            public boolean isCellEditable(int rowIndex, int columnIndex) {
		                return canEdit [columnIndex];
		            }
		        });
			jScrollPane2.setViewportView(tab_rltCAPEX);
		    jScrollPane2.repaint();
		    
		    tab_rltOPEX.setModel(new javax.swing.table.DefaultTableModel(
		            new Object [][] {
		                {" 1", BigDecimal.valueOf(Math.round(listROpex.get(0).getOpex())).toPlainString(),
		                	BigDecimal.valueOf(Math.round(listROpex.get(0).getInCom())).toPlainString(),
		                	BigDecimal.valueOf(Math.round(listROpex.get(0).getBenef())).toPlainString()},
		                {" 2", BigDecimal.valueOf(Math.round(listROpex.get(1).getOpex())).toPlainString(),
		                	BigDecimal.valueOf(Math.round(listROpex.get(1).getInCom())).toPlainString(),
		                	BigDecimal.valueOf(Math.round(listROpex.get(1).getBenef())).toPlainString()},
		                {" 3", BigDecimal.valueOf(Math.round(listROpex.get(2).getOpex())).toPlainString(), 
		                	BigDecimal.valueOf(Math.round(listROpex.get(2).getInCom())).toPlainString(),
		                	BigDecimal.valueOf(Math.round(listROpex.get(2).getBenef())).toPlainString()},
		                {" 4", BigDecimal.valueOf(Math.round(listROpex.get(3).getOpex())).toPlainString(), 
		                	BigDecimal.valueOf(Math.round(listROpex.get(3).getInCom())).toPlainString(),
		                	BigDecimal.valueOf(Math.round(listROpex.get(3).getBenef())).toPlainString()},
		                {" 5", BigDecimal.valueOf(Math.round(listROpex.get(4).getOpex())).toPlainString(), 
		                	BigDecimal.valueOf(Math.round(listROpex.get(4).getInCom())).toPlainString(),
		                	BigDecimal.valueOf(Math.round(listROpex.get(4).getBenef())).toPlainString()}
		            },
		            new String [] {
		                "Année", "OPEX (CFA)", "Recettes (CFA)", "Benefices (CFA)"
		            }
		        ) {
		            boolean[] canEdit = new boolean [] {
		                false, false, true, true
		            };

		            public boolean isCellEditable(int rowIndex, int columnIndex) {
		                return canEdit [columnIndex];
		            }
		        });
		    jScrollPane3.setViewportView(tab_rltOPEX);
		    jScrollPane3.repaint();
		    
		    projWS.getPlanification().getResult().setRcapex(rcapex);
		    projWS.getPlanification().getResult().setListROpex(listROpex);
			projConWS.setModel(projWS);
			this.opsimGUI.getListControler().remove("ProjectController");
			this.opsimGUI.getListControler().put("ProjectController", projConWS);
	    }
	    
    public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
    
    public boolean isEmptyTabVal(JTable tab){
    	boolean empty = false;
    	int n_col = tab.getColumnCount();
    	int n_row = tab.getRowCount();
    	for(int i=0; i<n_row;i++){
    		for(int j=0; j<n_col;j++){
    			if(tab.getModel().getValueAt(i,j) == null)empty = true;
    			else if(tab.getModel().getValueAt(i,j).toString().isEmpty())empty = true;
    		}
    	}    	
    	return empty;
    }
    
    public void initDialogValues(Project proj){
		this.initStep1(proj);
		this.initStep2(proj);
		//this.initResut(proj);
	}
    
    public void initStep1(Project proj){
    	System.out.println("PlanCost-Dialog: initStep1");    	
    	CapexParameter capexParam = proj.getParameter().getCapexParam();
    	BigDecimal.valueOf(Math.round(capexParam.getIngRadio())).toPlainString();
    	this.tf_ingRadio.setText(BigDecimal.valueOf(Math.round(capexParam.getIngRadio())).toPlainString());
    	this.tf_ingReseau.setText(BigDecimal.valueOf(Math.round(capexParam.getIngReseau())).toPlainString());
    	this.tf_rechFrs.setText(BigDecimal.valueOf(Math.round(capexParam.getRechFrs())).toPlainString());
		this.tf_rechSites.setText(BigDecimal.valueOf(Math.round(capexParam.getRechSites())).toPlainString());
		this.tf_locaSite.setText(BigDecimal.valueOf(Math.round(capexParam.getLocaSites())).toPlainString());
		this.tf_gc.setText(BigDecimal.valueOf(Math.round(capexParam.getGenieCivil())).toPlainString());
		this.tf_racElec.setText(BigDecimal.valueOf(Math.round(capexParam.getRacElec())).toPlainString());
		this.tf_installStation.setText(BigDecimal.valueOf(Math.round(capexParam.getInstallStation())).toPlainString());
		this.tf_racRxIP.setText(BigDecimal.valueOf(Math.round(capexParam.getRacRxIP())).toPlainString());
		this.tf_equipIP.setText(BigDecimal.valueOf(Math.round(capexParam.getEquipRx())).toPlainString());
		this.tf_infraIP.setText(BigDecimal.valueOf(Math.round(capexParam.getInfraIP())).toPlainString());
		this.tf_logiApp.setText(BigDecimal.valueOf(Math.round(capexParam.getLogiApps())).toPlainString());
		this.tf_infraVOIP.setText(BigDecimal.valueOf(Math.round(capexParam.getInfraVOIP())).toPlainString());
		this.tf_infraRadio.setText(BigDecimal.valueOf(Math.round(capexParam.getInfraRadio())).toPlainString());
		this.tf_infraRx.setText(BigDecimal.valueOf(Math.round(capexParam.getInfraRx())).toPlainString());
		this.tf_mobiler.setText(BigDecimal.valueOf(Math.round(capexParam.getLocauxMobilers())).toPlainString());
		this.tf_suiviProj.setText(BigDecimal.valueOf(Math.round(capexParam.getSuiviProj())).toPlainString());
    }
    
    public void initStep2(Project proj){
    	System.out.println("PlanCost-Dialog: initStep2");
    	OpexParameter opexParam = proj.getParameter().getOpexParam();
    	ArrayList<PrevIncomings> listPrevIn = opexParam.getListPrevIncomings();
    	
    	this.tf_nbreHab.setText(BigDecimal.valueOf(Math.round(opexParam.getNbResident())).toPlainString());
    	this.tf_percentAb.setText(BigDecimal.valueOf(Math.round(opexParam.getPercentCust())).toPlainString());
    	this.tf_txCroissance.setText(Double.toString(opexParam.getGrowingRate()));
		this.tf_marketing.setText(BigDecimal.valueOf(Math.round(opexParam.getMarketing())).toPlainString());
		this.tf_interWeb.setText(BigDecimal.valueOf(Math.round(opexParam.getInterWeb())).toPlainString());
		this.tf_adminFG.setText(BigDecimal.valueOf(Math.round(opexParam.getAdminFGen())).toPlainString());
		this.tf_assurance.setText(BigDecimal.valueOf(Math.round(opexParam.getAssurance())).toPlainString());
		this.tf_locaSiteRadio.setText(BigDecimal.valueOf(Math.round(opexParam.getLocaSiteRadio())).toPlainString());
		this.tf_locaFreq.setText(BigDecimal.valueOf(Math.round(opexParam.getLocaFreq())).toPlainString());
		this.tf_enerElec.setText(BigDecimal.valueOf(Math.round(opexParam.getEnerElec())).toPlainString());
		this.tf_locaInfra.setText(BigDecimal.valueOf(Math.round(opexParam.getLocaInfra())).toPlainString());
		this.tf_personnel.setText(BigDecimal.valueOf(Math.round(opexParam.getFormaPerso())).toPlainString());
		this.tf_suiviRx.setText(BigDecimal.valueOf(Math.round(opexParam.getSuiviRx())).toPlainString());
		this.tf_facturationAb.setText(BigDecimal.valueOf(Math.round(opexParam.getFactAb())).toPlainString());
		this.tf_txImpots.setText(Double.toString(opexParam.getTxImpot()));
		
		tab_prevRec.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {
	                {" Classe A", BigDecimal.valueOf(Math.round(listPrevIn.get(0).getInByCust())).toPlainString(),
	                			listPrevIn.get(0).getCustRate()},
	                {" Classe B", BigDecimal.valueOf(Math.round(listPrevIn.get(1).getInByCust())).toPlainString(),
	                			listPrevIn.get(1).getCustRate()},
	                {" Classe C", BigDecimal.valueOf(Math.round(listPrevIn.get(2).getInByCust())).toPlainString(),
	                			listPrevIn.get(2).getCustRate()},
	                {" Classe D", BigDecimal.valueOf(Math.round(listPrevIn.get(3).getInByCust())).toPlainString(),
	                			listPrevIn.get(3).getCustRate()}
	            },
	            new String [] {
	                "Classe d'abonnés", "Revenue par abonné et par classe", "Taux d'abonnés par abonné"
	            }
	        ) {
	            boolean[] canEdit = new boolean [] {
	                false, true, true
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        });
	        tab_prevRec.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	        tab_prevRec.getColumnModel().getColumn(0).setPreferredWidth(116);
	        tab_prevRec.getColumnModel().getColumn(1).setPreferredWidth(200);
	        tab_prevRec.getColumnModel().getColumn(2).setPreferredWidth(200);
	        
	        tab_prevRec.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
	        tab_prevRec.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
	        tab_prevRec.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
	        jScrollPane1.setViewportView(tab_prevRec);
	        jScrollPane1.repaint();
	        if (tab_prevRec.getColumnModel().getColumnCount() > 0) {
	        	tab_prevRec.getColumnModel().getColumn(0).setResizable(false);
	        	tab_prevRec.getColumnModel().getColumn(1).setResizable(false);
	        	tab_prevRec.getColumnModel().getColumn(2).setResizable(false);
	        }
    }
            
    public void initResut(Project proj){
    	
    }

    public static void main(String[] args) {
		DialogPlanCost dialog = new DialogPlanCost(null, true, false);

	}
    
	@Override
	public void update(Observable observ) {
		// TODO Auto-generated method stub
		
	}

	public javax.swing.JPanel getConGroupCard() {
		return conGroupCard;
	}
	    
	
}
