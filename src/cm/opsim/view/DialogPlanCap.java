/**
 * 
 */
package cm.opsim.view;

import java.awt.CardLayout;
import java.awt.Image;
import java.awt.Toolkit;
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

import cm.opsim.controller.PlanCapController;
import cm.opsim.controller.ProjectController;
import cm.opsim.dao.AntennaTypeDAO;
import cm.opsim.dao.DAO;
import cm.opsim.dao.DAOFactory;
import cm.opsim.dao.FreqBandDAO;
import cm.opsim.event.AbstractHandleEvent;
import cm.opsim.event.PlanningCapHE;
import cm.opsim.model.AntennaParameter;
import cm.opsim.model.AntennaType;
import cm.opsim.model.CapParameter;
import cm.opsim.model.CapacityPlanif;
import cm.opsim.model.CustomerParameter;
import cm.opsim.model.FreqBand;
import cm.opsim.model.GenParameter;
import cm.opsim.model.OtherParameter;
import cm.opsim.model.Project;
import cm.opsim.model.RPlanCapacity;
import cm.opsim.model.Service;
import cm.opsim.model.Subscription;
import cm.opsim.model.TrafficModel;
import cm.opsim.observer.Observable;
import cm.opsim.observer.Observer;

/**
 * @author Romuald FOTSO
 *
 */
public class DialogPlanCap extends JDialog implements Observer{
	// Variables declaration - do not modify                     
    private javax.swing.JTextField CPI_DL;
    private javax.swing.JButton but_browAnType;
    private javax.swing.JButton but_browFB;
    private javax.swing.JButton but_cancel;
    private javax.swing.JButton but_defaultPlan;
    private javax.swing.JButton but_finish;
    private javax.swing.JButton but_formPlan;
    private javax.swing.JButton but_icon1;
    private javax.swing.JButton but_icon2;
    private javax.swing.JButton but_icon3;
    private javax.swing.JButton but_icon4;
    private javax.swing.JButton but_next;
    private javax.swing.JButton but_prev;
    private javax.swing.JButton but_reportPlan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbo_chanModel;
    private javax.swing.JComboBox cbo_probaCov;
    private javax.swing.JPanel conButton;
    private javax.swing.JPanel conCard_step1;
    private javax.swing.JPanel conCard_step1_left;
    private javax.swing.JPanel conCard_step1_left1;
    private javax.swing.JPanel conCard_step1_left2;
    private javax.swing.JPanel conCard_step1_left3;
    private javax.swing.JPanel conCard_step1_right;
    private javax.swing.JPanel conCard_step1_right1;
    private javax.swing.JPanel conCard_step1_right2;
    private javax.swing.JPanel conCard_step1_right3;
    private javax.swing.JPanel conCard_step2;
    private javax.swing.JPanel conCard_step3;
    private javax.swing.JPanel conCard_step4;
    private javax.swing.JPanel conGroupCard;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox cbo_bwFB;
    private javax.swing.JComboBox cbo_mimo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField tf_pcSub1;
    private javax.swing.JTextField tf_pcSub2;
    private javax.swing.JTextField tf_pcSub3;
    private javax.swing.JLabel lab_des_lte10;
    private javax.swing.JList listAbonBusiness;
    private javax.swing.JList listAbonPriv;
    private javax.swing.JList listAbonPublic;
    private javax.swing.JTextPane panDes_paramGen;
    private javax.swing.JTable tabModelTrafic;
    private javax.swing.JTextField tf_FB;
    private javax.swing.JTextField tf_OBF_DL;
    private javax.swing.JTextField tf_OBF_UL;
    private javax.swing.JTextField tf_anType;
    private javax.swing.JTextField tf_cpiUL;
    private javax.swing.JTextField tf_debitDL;
    private javax.swing.JTextField tf_debitUL;
    private javax.swing.JTextField tf_eff16QAM_DL;
    private javax.swing.JTextField tf_eff16QAM_UL;
    private javax.swing.JTextField tf_eff64QAM_DL;
    private javax.swing.JTextField tf_eff64QAM_UL;
    private javax.swing.JTextField tf_effQPSK_DL;
    private javax.swing.JTextField tf_effQPSK_UL;
    private javax.swing.JTextField tf_fadingMargin;
    private javax.swing.JTextField tf_growthRate;
    private javax.swing.JTextField tf_invTime;
    private javax.swing.JTextField tf_lossBld;
    private javax.swing.JTextField tf_lossBody;
    private javax.swing.JTextField tf_lossCar;
    private javax.swing.JTextField tf_marPart;
    private javax.swing.JTextField tf_noiseFig;
    private javax.swing.JTextField tf_finalCellRad;
    private javax.swing.JTextField tf_finalNumEnb;
    private javax.swing.JTextField tf_finalCapIS;
    private javax.swing.JTextField tf_numPRB;
    private javax.swing.JTextField tf_penRate;
    private javax.swing.JTextField tf_proba16QAM_DL;
    private javax.swing.JTextField tf_proba16QAM_UL;
    private javax.swing.JTextField tf_proba64QAM_DL;
    private javax.swing.JTextField tf_proba64QAM_UL;
    private javax.swing.JTextField tf_probaQPSK_DL;
    private javax.swing.JTextField tf_probaQPSK_UL;
    private javax.swing.JTextField tf_probaSMG1_DL;
    private javax.swing.JTextField tf_probaSMG1_UL;
    private javax.swing.JTextField tf_probaSMG2_DL;
    private javax.swing.JTextField tf_probaSMG2_UL;
    private javax.swing.JTextField tf_probaSMG4_DL;
    private javax.swing.JTextField tf_probaSMG4_UL;
    private javax.swing.JTextField tf_probaSMG8_DL;
    private javax.swing.JTextField tf_sohL1L2_DL;
    private javax.swing.JTextField tf_sohpbcchDL;
    private javax.swing.JTextField tf_sohprachUL;
    private javax.swing.JTextField tf_sohpssDL;
    private javax.swing.JTextField tf_sohpucchUL;
    private javax.swing.JTextField tf_sohrsUL;
    private javax.swing.JTextField tf_sohrs_DL;
    // End of variables declaration                    
    private int next_id_card = 2;
    private Image icone =  Toolkit.getDefaultToolkit().getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/logo.png"));
    private ImageIcon IconPlanCap =  new ImageIcon(Toolkit.getDefaultToolkit().
			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/plancap.png")));
    private ImageIcon IconHelp =  new ImageIcon(Toolkit.getDefaultToolkit().
			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/help.png")));
    private AbstractHandleEvent planCapHE;
    private OpsimGUI opsimGUI;
    private DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	/**
	 * 
	 */
	public DialogPlanCap(OpsimGUI parent, boolean modal, boolean show_res) {
		super(parent, modal);
		this.opsimGUI = parent;
        this.initComponents();
        
        if(show_res){
        	System.out.println("PlanCap: Show result");
 			this.planCap_result();
			CardLayout cl = (CardLayout)(this.conGroupCard.getLayout());
			cl.show(this.conGroupCard,"CARD_4");
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
	  	if(projWS.getPlanification().getCapPlanif().getState().equals("DONE"))this.initDialogValues(projWS); 
        
        this.setVisible(true);  
        
	}
	
	public void initComponents(){
			this.setIconImage(icone);
			centerRenderer.setHorizontalAlignment( JLabel.CENTER );
			
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
	        lab_des_lte10 = new javax.swing.JLabel();
	        jScrollPane4 = new javax.swing.JScrollPane();
	        panDes_paramGen = new javax.swing.JTextPane();
	        jLabel5 = new javax.swing.JLabel();
	        tf_FB = new javax.swing.JTextField();
	        but_browFB = new javax.swing.JButton();
	        jLabel6 = new javax.swing.JLabel();
	        jLabel7 = new javax.swing.JLabel();
	        tf_lossBody = new javax.swing.JTextField();
	        tf_debitDL = new javax.swing.JTextField();
	        jLabel8 = new javax.swing.JLabel();
	        tf_numPRB = new javax.swing.JTextField();
	        jLabel9 = new javax.swing.JLabel();
	        tf_anType = new javax.swing.JTextField();
	        but_browAnType = new javax.swing.JButton();
	        jLabel10 = new javax.swing.JLabel();
	        cbo_probaCov = new javax.swing.JComboBox();
	        jLabel11 = new javax.swing.JLabel();
	        cbo_chanModel = new javax.swing.JComboBox();
	        jLabel12 = new javax.swing.JLabel();
	        jLabel13 = new javax.swing.JLabel();
	        jLabel14 = new javax.swing.JLabel();
	        jLabel15 = new javax.swing.JLabel();
	        tf_debitUL = new javax.swing.JTextField();
	        tf_lossBld = new javax.swing.JTextField();
	        tf_lossCar = new javax.swing.JTextField();
	        tf_noiseFig = new javax.swing.JTextField();
	        jSeparator4 = new javax.swing.JSeparator();
	        jButton4 = new javax.swing.JButton();
	        jButton5 = new javax.swing.JButton();
	        jButton6 = new javax.swing.JButton();
	        jButton7 = new javax.swing.JButton();
	        jButton8 = new javax.swing.JButton();
	        jLabel67 = new javax.swing.JLabel();
	        tf_fadingMargin = new javax.swing.JTextField();
	        jLabel26 = new javax.swing.JLabel();
	        cbo_bwFB = new javax.swing.JComboBox();
	        jLabel45 = new javax.swing.JLabel();
	        cbo_mimo = new javax.swing.JComboBox();
	        conCard_step2 = new javax.swing.JPanel();
	        conCard_step1_left2 = new javax.swing.JPanel();
	        but_icon3 = new javax.swing.JButton();
	        jLabel21 = new javax.swing.JLabel();
	        jSeparator7 = new javax.swing.JSeparator();
	        jLabel22 = new javax.swing.JLabel();
	        jLabel23 = new javax.swing.JLabel();
	        jLabel24 = new javax.swing.JLabel();
	        conCard_step1_right2 = new javax.swing.JPanel();
	        jLabel25 = new javax.swing.JLabel();
	        jSeparator9 = new javax.swing.JSeparator();
	        jPanel2 = new javax.swing.JPanel();
	        jScrollPane3 = new javax.swing.JScrollPane();
	        tabModelTrafic = new javax.swing.JTable();
	        jPanel4 = new javax.swing.JPanel();
	        jLabel32 = new javax.swing.JLabel();
	        jScrollPane2 = new javax.swing.JScrollPane();
	        listAbonPublic = new javax.swing.JList();
	        jLabel33 = new javax.swing.JLabel();
	        jLabel34 = new javax.swing.JLabel();
	        jScrollPane5 = new javax.swing.JScrollPane();
	        listAbonPriv = new javax.swing.JList();
	        jScrollPane6 = new javax.swing.JScrollPane();
	        listAbonBusiness = new javax.swing.JList();
	        tf_pcSub1 = new javax.swing.JTextField();
	        jLabel41 = new javax.swing.JLabel();
	        tf_pcSub2 = new javax.swing.JTextField();
	        jLabel42 = new javax.swing.JLabel();
	        tf_pcSub3 = new javax.swing.JTextField();
	        jLabel44 = new javax.swing.JLabel();
	        tf_marPart = new javax.swing.JTextField();
	        jLabel37 = new javax.swing.JLabel();
	        jLabel38 = new javax.swing.JLabel();
	        tf_penRate = new javax.swing.JTextField();
	        jLabel39 = new javax.swing.JLabel();
	        tf_growthRate = new javax.swing.JTextField();
	        jLabel40 = new javax.swing.JLabel();
	        tf_invTime = new javax.swing.JTextField();
	        conCard_step3 = new javax.swing.JPanel();
	        conCard_step1_left3 = new javax.swing.JPanel();
	        but_icon4 = new javax.swing.JButton();
	        jLabel27 = new javax.swing.JLabel();
	        jSeparator8 = new javax.swing.JSeparator();
	        jLabel28 = new javax.swing.JLabel();
	        jLabel29 = new javax.swing.JLabel();
	        jLabel30 = new javax.swing.JLabel();
	        jLabel35 = new javax.swing.JLabel();
	        conCard_step1_right3 = new javax.swing.JPanel();
	        jLabel31 = new javax.swing.JLabel();
	        jSeparator12 = new javax.swing.JSeparator();
	        jPanel5 = new javax.swing.JPanel();
	        jLabel43 = new javax.swing.JLabel();
	        tf_sohrsUL = new javax.swing.JTextField();
	        jLabel47 = new javax.swing.JLabel();
	        tf_sohprachUL = new javax.swing.JTextField();
	        jSeparator13 = new javax.swing.JSeparator();
	        jLabel48 = new javax.swing.JLabel();
	        tf_sohpucchUL = new javax.swing.JTextField();
	        jLabel49 = new javax.swing.JLabel();
	        tf_cpiUL = new javax.swing.JTextField();
	        jLabel50 = new javax.swing.JLabel();
	        tf_probaQPSK_UL = new javax.swing.JTextField();
	        jLabel51 = new javax.swing.JLabel();
	        jLabel55 = new javax.swing.JLabel();
	        jLabel56 = new javax.swing.JLabel();
	        tf_proba16QAM_UL = new javax.swing.JTextField();
	        tf_eff16QAM_UL = new javax.swing.JTextField();
	        tf_proba64QAM_UL = new javax.swing.JTextField();
	        tf_effQPSK_UL = new javax.swing.JTextField();
	        tf_OBF_UL = new javax.swing.JTextField();
	        jLabel90 = new javax.swing.JLabel();
	        jLabel91 = new javax.swing.JLabel();
	        tf_eff64QAM_UL = new javax.swing.JTextField();
	        tf_probaSMG1_UL = new javax.swing.JTextField();
	        tf_probaSMG2_UL = new javax.swing.JTextField();
	        tf_probaSMG4_UL = new javax.swing.JTextField();
	        jLabel92 = new javax.swing.JLabel();
	        jLabel93 = new javax.swing.JLabel();
	        jLabel94 = new javax.swing.JLabel();
	        jLabel95 = new javax.swing.JLabel();
	        jPanel6 = new javax.swing.JPanel();
	        jLabel96 = new javax.swing.JLabel();
	        tf_sohrs_DL = new javax.swing.JTextField();
	        jLabel97 = new javax.swing.JLabel();
	        tf_sohpssDL = new javax.swing.JTextField();
	        jSeparator14 = new javax.swing.JSeparator();
	        jLabel98 = new javax.swing.JLabel();
	        tf_sohpbcchDL = new javax.swing.JTextField();
	        jLabel99 = new javax.swing.JLabel();
	        tf_sohL1L2_DL = new javax.swing.JTextField();
	        jLabel100 = new javax.swing.JLabel();
	        CPI_DL = new javax.swing.JTextField();
	        jLabel101 = new javax.swing.JLabel();
	        jLabel102 = new javax.swing.JLabel();
	        jLabel103 = new javax.swing.JLabel();
	        tf_proba16QAM_DL = new javax.swing.JTextField();
	        tf_eff16QAM_DL = new javax.swing.JTextField();
	        tf_proba64QAM_DL = new javax.swing.JTextField();
	        tf_probaQPSK_DL = new javax.swing.JTextField();
	        tf_effQPSK_DL = new javax.swing.JTextField();
	        jLabel104 = new javax.swing.JLabel();
	        jLabel105 = new javax.swing.JLabel();
	        tf_eff64QAM_DL = new javax.swing.JTextField();
	        tf_probaSMG1_DL = new javax.swing.JTextField();
	        tf_probaSMG2_DL = new javax.swing.JTextField();
	        tf_probaSMG4_DL = new javax.swing.JTextField();
	        jLabel106 = new javax.swing.JLabel();
	        jLabel107 = new javax.swing.JLabel();
	        jLabel108 = new javax.swing.JLabel();
	        jLabel109 = new javax.swing.JLabel();
	        tf_OBF_DL = new javax.swing.JTextField();
	        jLabel110 = new javax.swing.JLabel();
	        tf_probaSMG8_DL = new javax.swing.JTextField();
	        jLabel111 = new javax.swing.JLabel();
	        conCard_step4 = new javax.swing.JPanel();
	        conCard_step1_left1 = new javax.swing.JPanel();
	        but_icon2 = new javax.swing.JButton();
	        jLabel16 = new javax.swing.JLabel();
	        jSeparator3 = new javax.swing.JSeparator();
	        jLabel17 = new javax.swing.JLabel();
	        jLabel18 = new javax.swing.JLabel();
	        jLabel20 = new javax.swing.JLabel();
	        jLabel36 = new javax.swing.JLabel();
	        conCard_step1_right1 = new javax.swing.JPanel();
	        jLabel19 = new javax.swing.JLabel();
	        jSeparator5 = new javax.swing.JSeparator();
	        jPanel3 = new javax.swing.JPanel();
	        jLabel52 = new javax.swing.JLabel();
	        tf_finalCellRad = new javax.swing.JTextField();
	        jLabel53 = new javax.swing.JLabel();
	        tf_finalNumEnb = new javax.swing.JTextField();
	        jLabel54 = new javax.swing.JLabel();
	        tf_finalCapIS = new javax.swing.JTextField();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        jTable1 = new javax.swing.JTable();

	        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	        setTitle("Planification en Capacité");
	        setPreferredSize(new java.awt.Dimension(785, 560));
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
	                .addContainerGap(105, Short.MAX_VALUE)
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

	        but_icon1.setIcon(IconPlanCap); // NOI18N
	        but_icon1.setBorderPainted(false);
	        but_icon1.setContentAreaFilled(false);
	        but_icon1.setFocusPainted(false);

	        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jLabel1.setText("Etapes:");

	        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

	        jLabel2.setText("1.   Paramètres généraux");

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
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
	                .addComponent(but_icon1)
	                .addGap(46, 46, 46))
	        );

	        conCard_step1.add(conCard_step1_left, java.awt.BorderLayout.LINE_START);

	        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jLabel4.setText("Définisser les paramètres généraux:");

	        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

	        lab_des_lte10.setText("Description:");

	        panDes_paramGen.setEditable(false);
	        panDes_paramGen.setText("La planification de couverture  d’un réseau cellulaire permet essentiellement de calculer la taille de la cellule.\nLe rayon de cellule est obtenu suite à la réalisation d’un bilan de liaison (BL).");
	        panDes_paramGen.setMaximumSize(new java.awt.Dimension(528, 48));
	        panDes_paramGen.setMinimumSize(new java.awt.Dimension(528, 48));
	        panDes_paramGen.setPreferredSize(new java.awt.Dimension(528, 48));
	        jScrollPane4.setViewportView(panDes_paramGen);

	        jLabel5.setText("Bande de fréquence:");

	        tf_FB.setEditable(false);
	        tf_FB.setText("LTE ETRAN FB 41 - TDD");

	        but_browFB.setText("Charger ...");

	        jLabel6.setText("Débit au bord de la cellule UL (Kp/s):");

	        jLabel7.setText("Débit au bord de la cellule DL (Kp/s):");

	        jLabel8.setText("Nombre de PRB (UL):");

	        jLabel9.setText("Type d'antenne:");

	        tf_anType.setEditable(false);
	        tf_anType.setText("30deg 18dBi 0Tilt 1800Mhz");

	        but_browAnType.setText("Charger ...");

	        jLabel10.setText("Probabilité de couverture:");

	        cbo_probaCov.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "98%", "96%", "94%", "92%", "90%", "88%", "86%" }));

	        jLabel11.setText("Channel Model/Doppler Frequency:");

	        cbo_chanModel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "EPA5", "EVA70", "ETU300" }));

	        jLabel12.setText("Pertes du corps (dB):");

	        jLabel13.setText("Pertes de pénétration batiment (dB):");

	        jLabel14.setText("Pertes de pénétration voiture(dB):");

	        jLabel15.setText("Figure de bruit (dB):");

	        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

	        jButton4.setIcon(IconHelp); // NOI18N
	        jButton4.setToolTipText("Modèle de canaux");
	        jButton4.setBorderPainted(false);
	        jButton4.setContentAreaFilled(false);
	        jButton4.setFocusable(false);

	        jButton5.setIcon(IconHelp); // NOI18N
	        jButton5.setToolTipText("Bande de fréquence du système");
	        jButton5.setBorderPainted(false);
	        jButton5.setContentAreaFilled(false);
	        jButton5.setFocusable(false);

	        jButton6.setIcon(IconHelp); // NOI18N
	        jButton6.setToolTipText("Nombre de Physical Ressource Block (PRB)");
	        jButton6.setBorderPainted(false);
	        jButton6.setContentAreaFilled(false);
	        jButton6.setFocusable(false);

	        jButton7.setIcon(IconHelp); // NOI18N
	        jButton7.setToolTipText("Probabilité de couverture souhaité");
	        jButton7.setBorderPainted(false);
	        jButton7.setContentAreaFilled(false);
	        jButton7.setFocusable(false);

	        jButton8.setIcon(IconHelp); // NOI18N
	        jButton8.setToolTipText("Une des composantes de la sensibilité du récepteur");
	        jButton8.setBorderPainted(false);
	        jButton8.setContentAreaFilled(false);
	        jButton8.setFocusable(false);

	        jLabel67.setText("Marge de fading:");

	        jLabel26.setText("Largeur de bande (Mhz):");

	        cbo_bwFB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1.4", "3", "5", "10", "15", "20" }));
	        
	        jLabel45.setText("Multiplexage spatial:");

	        cbo_mimo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MIMO1x1", "MIMO2x2", "MIMO3x3", "MIMO4x4", "MIMO8x8" }));
	
	        javax.swing.GroupLayout conCard_step1_rightLayout = new javax.swing.GroupLayout(conCard_step1_right);
	        conCard_step1_right.setLayout(conCard_step1_rightLayout);
	        conCard_step1_rightLayout.setHorizontalGroup(
	            conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(conCard_step1_rightLayout.createSequentialGroup()
	                .addGap(21, 21, 21)
	                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(conCard_step1_rightLayout.createSequentialGroup()
	                        .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addGroup(conCard_step1_rightLayout.createSequentialGroup()
	                                .addComponent(jLabel6)
	                                .addGap(18, 18, 18)
	                                .addComponent(tf_debitUL, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
	                            .addGroup(conCard_step1_rightLayout.createSequentialGroup()
	                                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
	                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
	                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
	                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
	                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
	                                .addGap(18, 18, 18)
	                                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addGroup(conCard_step1_rightLayout.createSequentialGroup()
	                                        .addComponent(tf_numPRB, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                    .addComponent(tf_debitDL, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addGroup(conCard_step1_rightLayout.createSequentialGroup()
	                                        .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                                            .addGroup(conCard_step1_rightLayout.createSequentialGroup()
	                                                .addComponent(cbo_probaCov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                                .addComponent(jLabel45))
	                                            .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                                .addGroup(conCard_step1_rightLayout.createSequentialGroup()
	                                                    .addComponent(cbo_chanModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                                    .addComponent(jLabel26))
	                                                .addComponent(tf_anType, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                        .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                                            .addComponent(but_browAnType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                            .addComponent(cbo_bwFB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                            .addComponent(cbo_mimo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
	                            .addGroup(conCard_step1_rightLayout.createSequentialGroup()
	                                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                                    .addComponent(jLabel15)
	                                    .addComponent(jLabel12)
	                                    .addComponent(jLabel13)
	                                    .addComponent(jLabel14)
	                                    .addComponent(jLabel67))
	                                .addGap(18, 18, 18)
	                                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addComponent(tf_lossBody, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(tf_lossBld, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(tf_lossCar, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addGroup(conCard_step1_rightLayout.createSequentialGroup()
	                                        .addComponent(tf_noiseFig, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                    .addComponent(tf_fadingMargin, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))
	                        .addGap(0, 0, Short.MAX_VALUE))
	                    .addGroup(conCard_step1_rightLayout.createSequentialGroup()
	                        .addComponent(jLabel5)
	                        .addGap(18, 18, 18)
	                        .addComponent(tf_FB, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(30, 30, 30)
	                        .addComponent(but_browFB)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addContainerGap(20, Short.MAX_VALUE))))
	            .addGroup(conCard_step1_rightLayout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(lab_des_lte10)
	                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel4))
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        conCard_step1_rightLayout.setVerticalGroup(
	            conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(conCard_step1_rightLayout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel4)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addGroup(conCard_step1_rightLayout.createSequentialGroup()
	                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                                .addComponent(jLabel5)
	                                .addComponent(tf_FB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addComponent(but_browFB))
	                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(jLabel6)
	                            .addComponent(tf_debitUL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(jLabel7)
	                            .addComponent(tf_debitDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(conCard_step1_rightLayout.createSequentialGroup()
	                                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                                    .addComponent(jLabel8)
	                                    .addComponent(tf_numPRB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                                    .addComponent(jLabel9)
	                                    .addComponent(tf_anType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(but_browAnType)))
	                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                                .addComponent(jLabel10)
	                                .addComponent(cbo_probaCov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                    .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(jLabel45)
	                        .addComponent(cbo_mimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel11)
	                    .addComponent(cbo_chanModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(jLabel26)
	                        .addComponent(cbo_bwFB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(tf_lossBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(tf_lossBld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(tf_lossCar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(tf_noiseFig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(tf_fadingMargin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(lab_des_lte10)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(19, Short.MAX_VALUE))
	        );

	        conCard_step1.add(conCard_step1_right, java.awt.BorderLayout.CENTER);

	        conGroupCard.add(conCard_step1, "CARD_1");

	        conCard_step2.setPreferredSize(new java.awt.Dimension(790, 474));
	        conCard_step2.setLayout(new java.awt.BorderLayout());

	        conCard_step1_left2.setBackground(new java.awt.Color(255, 255, 255));
	        conCard_step1_left2.setPreferredSize(new java.awt.Dimension(200, 455));

	        but_icon3.setIcon(IconPlanCap); // NOI18N
	        but_icon3.setBorderPainted(false);
	        but_icon3.setContentAreaFilled(false);
	        but_icon3.setFocusPainted(false);


	        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jLabel21.setText("Etapes:");

	        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));

	        jLabel22.setText("1.   Paramètres généraux");

	        jLabel23.setText("2.   Paramètres trafic");

	        jLabel24.setText("3.    ...");

	        javax.swing.GroupLayout conCard_step1_left2Layout = new javax.swing.GroupLayout(conCard_step1_left2);
	        conCard_step1_left2.setLayout(conCard_step1_left2Layout);
	        conCard_step1_left2Layout.setHorizontalGroup(
	            conCard_step1_left2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(conCard_step1_left2Layout.createSequentialGroup()
	                .addGroup(conCard_step1_left2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(conCard_step1_left2Layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jLabel21))
	                    .addGroup(conCard_step1_left2Layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(conCard_step1_left2Layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jLabel22))
	                    .addGroup(conCard_step1_left2Layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jLabel23))
	                    .addGroup(conCard_step1_left2Layout.createSequentialGroup()
	                        .addGap(23, 23, 23)
	                        .addComponent(but_icon3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(conCard_step1_left2Layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jLabel24)))
	                .addContainerGap(13, Short.MAX_VALUE))
	        );
	        conCard_step1_left2Layout.setVerticalGroup(
	            conCard_step1_left2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, conCard_step1_left2Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel21)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel22)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel23)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel24)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
	                .addComponent(but_icon3)
	                .addGap(46, 46, 46))
	        );

	        conCard_step2.add(conCard_step1_left2, java.awt.BorderLayout.LINE_START);

	        conCard_step1_right2.setPreferredSize(new java.awt.Dimension(647, 504));

	        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jLabel25.setText("Définisser les paramètres de trafic:");

	        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));

	        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Modèle de trafic"));

	        tabModelTrafic.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {
	                {" VoIP", null, null, null, null},
	                {" HTTP Navigation", null, null, null, null},
	                {" FTP", null, null, null, null},
	                {" Interactive Streaming", null, null, null, null},
	                {" Background Services", null, null, null, null}
	            },
	            new String [] {
	                "Services", "Débit en DL (Kps)", "Débit en UL (kps)", "POH (%)", "TP (%)"
	            }
	        ) {
	            boolean[] canEdit = new boolean [] {
	                false, true, true, true, true
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        });
	        tabModelTrafic.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	        tabModelTrafic.getColumnModel().getColumn(0).setPreferredWidth(160);
	        tabModelTrafic.getColumnModel().getColumn(1).setPreferredWidth(100);
	        tabModelTrafic.getColumnModel().getColumn(2).setPreferredWidth(100);
	        tabModelTrafic.getColumnModel().getColumn(3).setPreferredWidth(80);
	        
	        tabModelTrafic.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
	        tabModelTrafic.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
	        tabModelTrafic.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
	        tabModelTrafic.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
	        
	        jScrollPane3.setViewportView(tabModelTrafic);
	        if (tabModelTrafic.getColumnModel().getColumnCount() > 0) {
	            tabModelTrafic.getColumnModel().getColumn(0).setResizable(false);
	            tabModelTrafic.getColumnModel().getColumn(1).setResizable(false);
	            tabModelTrafic.getColumnModel().getColumn(2).setResizable(false);
	            tabModelTrafic.getColumnModel().getColumn(3).setResizable(false);
	            tabModelTrafic.getColumnModel().getColumn(4).setResizable(false);
	        }

	        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
	        jPanel2.setLayout(jPanel2Layout);
	        jPanel2Layout.setHorizontalGroup(
	            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel2Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
	                .addContainerGap())
	        );
	        jPanel2Layout.setVerticalGroup(
	            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel2Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );

	        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Profil des usagers"));

	        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jLabel32.setText("Abonnement public:");

	        listAbonPublic.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
	        listAbonPublic.setModel(new javax.swing.AbstractListModel() {
	            String[] strings = { "VoIP", "Vidéo Streaming", "Jeu", "Web Browsing" };
	            public int getSize() { return strings.length; }
	            public Object getElementAt(int i) { return strings[i]; }
	        });
	        listAbonPublic.setToolTipText("");
	        listAbonPublic.setSelectedIndices(new int[] {0, 1, 2, 3});
	        jScrollPane2.setViewportView(listAbonPublic);

	        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jLabel33.setText("Abonnement privilégié:");

	        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jLabel34.setText("Abonnement Business:");

	        listAbonPriv.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
	        listAbonPriv.setModel(new javax.swing.AbstractListModel() {
	            String[] strings = { "VoIP", "Vidéo Streaming", "Jeu", "Web Browsing", "E-Mail" };
	            public int getSize() { return strings.length; }
	            public Object getElementAt(int i) { return strings[i]; }
	        });
	        listAbonPriv.setSelectedIndices(new int[] {0, 1, 2, 3, 4});
	        jScrollPane5.setViewportView(listAbonPriv);

	        listAbonBusiness.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
	        listAbonBusiness.setModel(new javax.swing.AbstractListModel() {
	            String[] strings = { "VoIP", "Vidéo Streaming", "Web Browsing", "E-Mail", "FTP", "Vidéo Conférence" };
	            public int getSize() { return strings.length; }
	            public Object getElementAt(int i) { return strings[i]; }
	        });
	        listAbonBusiness.setSelectedIndices(new int[] {0, 1, 2, 3, 4, 5});
	        jScrollPane6.setViewportView(listAbonBusiness);

	        tf_pcSub1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

	        jLabel41.setText("P.C. (%):");

	        tf_pcSub2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

	        jLabel42.setText("P.C. (%):");

	        tf_pcSub3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
	
	        jLabel44.setText("P.C. (%):");

	        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
	        jPanel4.setLayout(jPanel4Layout);
	        jPanel4Layout.setHorizontalGroup(
	            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
	                .addGap(29, 29, 29)
	                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel32)
	                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
	                            .addComponent(jLabel41)
	                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                            .addComponent(tf_pcSub1))
	                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addGap(52, 52, 52)
	                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel33)
	                    .addGroup(jPanel4Layout.createSequentialGroup()
	                        .addGap(10, 10, 10)
	                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                            .addGroup(jPanel4Layout.createSequentialGroup()
	                                .addComponent(jLabel42)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(tf_pcSub2))
	                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
	                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(jLabel34)
	                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                        .addGroup(jPanel4Layout.createSequentialGroup()
	                            .addComponent(jLabel44)
	                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                            .addComponent(tf_pcSub3))
	                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addContainerGap(44, Short.MAX_VALUE))
	        );
	        jPanel4Layout.setVerticalGroup(
	            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel4Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(jLabel32)
	                        .addComponent(jLabel33)))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
	                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
	                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(tf_pcSub3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addComponent(jLabel44))
	                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(tf_pcSub2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addComponent(jLabel42))
	                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(tf_pcSub1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addComponent(jLabel41)))
	                .addGap(18, 18, 18))
	        );

	        jLabel37.setText("Part du marché (%):");

	        jLabel38.setText("Taux de pénétration (%):");

	        jLabel39.setText("Taux de croisance (%):");

	        jLabel40.setText("Durée d'inv. (Année):");

	        javax.swing.GroupLayout conCard_step1_right2Layout = new javax.swing.GroupLayout(conCard_step1_right2);
	        conCard_step1_right2.setLayout(conCard_step1_right2Layout);
	        conCard_step1_right2Layout.setHorizontalGroup(
	            conCard_step1_right2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(conCard_step1_right2Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(conCard_step1_right2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(conCard_step1_right2Layout.createSequentialGroup()
	                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(0, 0, Short.MAX_VALUE))
	                    .addGroup(conCard_step1_right2Layout.createSequentialGroup()
	                        .addGroup(conCard_step1_right2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel25)
	                            .addGroup(conCard_step1_right2Layout.createSequentialGroup()
	                                .addGroup(conCard_step1_right2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                                    .addComponent(jLabel38)
	                                    .addComponent(jLabel37))
	                                .addGap(10, 10, 10)
	                                .addGroup(conCard_step1_right2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                                    .addComponent(tf_marPart, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
	                                    .addComponent(tf_penRate))
	                                .addGap(45, 45, 45)
	                                .addGroup(conCard_step1_right2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                                    .addComponent(jLabel39)
	                                    .addComponent(jLabel40))
	                                .addGap(18, 18, 18)
	                                .addGroup(conCard_step1_right2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                                    .addComponent(tf_growthRate, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
	                                    .addComponent(tf_invTime)))
	                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addContainerGap(29, Short.MAX_VALUE))))
	        );
	        conCard_step1_right2Layout.setVerticalGroup(
	            conCard_step1_right2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(conCard_step1_right2Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel25)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(12, 12, 12)
	                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(conCard_step1_right2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(tf_growthRate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addGroup(conCard_step1_right2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(jLabel37)
	                        .addComponent(tf_marPart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addComponent(jLabel39)))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(conCard_step1_right2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(jLabel38)
	                    .addGroup(conCard_step1_right2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(tf_penRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addComponent(jLabel40)
	                        .addComponent(tf_invTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addContainerGap())
	        );

	        conCard_step2.add(conCard_step1_right2, java.awt.BorderLayout.CENTER);

	        conGroupCard.add(conCard_step2, "CARD_2");

	        conCard_step3.setPreferredSize(new java.awt.Dimension(790, 474));
	        conCard_step3.setLayout(new java.awt.BorderLayout());

	        conCard_step1_left3.setBackground(new java.awt.Color(255, 255, 255));
	        conCard_step1_left3.setPreferredSize(new java.awt.Dimension(200, 455));

	        but_icon4.setIcon(IconPlanCap); // NOI18N
	        but_icon4.setBorderPainted(false);
	        but_icon4.setContentAreaFilled(false);
	        but_icon4.setFocusPainted(false);

	        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jLabel27.setText("Etapes:");

	        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));

	        jLabel28.setText("1.   Paramètres généraux");

	        jLabel29.setText("2.   Paramètres trafic");

	        jLabel30.setText("3.   Paramètres capacité");

	        jLabel35.setText("4.    ...");

	        javax.swing.GroupLayout conCard_step1_left3Layout = new javax.swing.GroupLayout(conCard_step1_left3);
	        conCard_step1_left3.setLayout(conCard_step1_left3Layout);
	        conCard_step1_left3Layout.setHorizontalGroup(
	            conCard_step1_left3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(conCard_step1_left3Layout.createSequentialGroup()
	                .addGroup(conCard_step1_left3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(conCard_step1_left3Layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jLabel27))
	                    .addGroup(conCard_step1_left3Layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(conCard_step1_left3Layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jLabel28))
	                    .addGroup(conCard_step1_left3Layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jLabel29))
	                    .addGroup(conCard_step1_left3Layout.createSequentialGroup()
	                        .addGap(23, 23, 23)
	                        .addComponent(but_icon4, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(conCard_step1_left3Layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jLabel30))
	                    .addGroup(conCard_step1_left3Layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jLabel35)))
	                .addContainerGap(13, Short.MAX_VALUE))
	        );
	        conCard_step1_left3Layout.setVerticalGroup(
	            conCard_step1_left3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, conCard_step1_left3Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel27)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel28)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel29)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel30)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel35)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
	                .addComponent(but_icon4)
	                .addGap(46, 46, 46))
	        );

	        conCard_step3.add(conCard_step1_left3, java.awt.BorderLayout.LINE_START);

	        conCard_step1_right3.setPreferredSize(new java.awt.Dimension(647, 504));

	        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jLabel31.setText("Définisser les paramètres de calcul des capacités:");

	        jSeparator12.setForeground(new java.awt.Color(0, 0, 0));

	        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Capacité en Uplink"));

	        jLabel43.setText("SOH RS:");

	        jLabel47.setText("SOH PRACH:");

	        jSeparator13.setOrientation(javax.swing.SwingConstants.VERTICAL);

	        jLabel48.setText("SOH PUCCH:");

	        jLabel49.setText("Cpi (Nombre de symbole/slot):");

	        jLabel50.setText("Probabilité d'opter pour QPSK1/2:");

	        jLabel51.setText("Probabilité d'opter pour 16-QAM2/3:");

	        jLabel55.setText("Efficacité 16-QAM2/3:");

	        jLabel56.setText("Probabilité d'opter pour 64-QAM5/6:");
	        
	        jLabel90.setText("Efficacité de QPSK1/2:");

	        jLabel91.setText("OBF (OverBlocking Factor):");

	        jLabel92.setText("Efficacité 64-QAM5/6:");

	        jLabel93.setText("Probabilité (SMgain = 1):");

	        jLabel94.setText("Probabilité (SMgain = 2):");

	        jLabel95.setText("Probabilité (SMgain = 4):");

	        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
	        jPanel5.setLayout(jPanel5Layout);
	        jPanel5Layout.setHorizontalGroup(
	            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
	                .addGap(2, 2, 2)
	                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel5Layout.createSequentialGroup()
	                        .addGap(98, 98, 98)
	                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(jLabel48)
	                            .addComponent(jLabel47)
	                            .addComponent(jLabel43)))
	                    .addGroup(jPanel5Layout.createSequentialGroup()
	                        .addGap(16, 16, 16)
	                        .addComponent(jLabel49))
	                    .addComponent(jLabel90, javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(jLabel91, javax.swing.GroupLayout.Alignment.TRAILING))
	                .addGap(18, 18, 18)
	                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                    .addComponent(tf_effQPSK_UL, javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(tf_probaQPSK_UL, javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(tf_cpiUL, javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(tf_sohpucchUL, javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(tf_sohprachUL, javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(tf_sohrsUL, javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(tf_OBF_UL, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(jLabel55)
	                    .addComponent(jLabel51)
	                    .addComponent(jLabel56)
	                    .addComponent(jLabel92)
	                    .addComponent(jLabel93)
	                    .addComponent(jLabel94)
	                    .addComponent(jLabel95))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                    .addComponent(tf_probaSMG2_UL, javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(tf_probaSMG1_UL, javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(tf_eff64QAM_UL, javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(tf_proba64QAM_UL, javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(tf_eff16QAM_UL, javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(tf_proba16QAM_UL, javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(tf_probaSMG4_UL, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addContainerGap())
	        );
	        jPanel5Layout.setVerticalGroup(
	            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel5Layout.createSequentialGroup()
	                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(tf_sohrsUL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(tf_sohprachUL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(tf_sohpucchUL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(tf_cpiUL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(tf_probaQPSK_UL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(tf_effQPSK_UL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(tf_OBF_UL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
	            .addGroup(jPanel5Layout.createSequentialGroup()
	                .addGap(3, 3, 3)
	                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel51)
	                    .addComponent(tf_proba16QAM_UL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel55)
	                    .addComponent(tf_eff16QAM_UL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel56)
	                    .addComponent(tf_proba64QAM_UL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(tf_eff64QAM_UL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel92))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(tf_probaSMG1_UL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel93))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(tf_probaSMG2_UL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel94))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(tf_probaSMG4_UL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel95)))
	            .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
	        );

	        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Capacité en Downlink"));

	        jLabel96.setText("SOH RS:");

	        jLabel97.setText("SOH PSS et SSS:");

	        jSeparator14.setOrientation(javax.swing.SwingConstants.VERTICAL);

	        jLabel98.setText("SOH PBCCH:");

	        jLabel99.setText("Cpi (Nombre de symbole/slot):");

	        jLabel100.setText("Probabilité d'opter pour QPSK1/2:");

	        jLabel101.setText("Probabilité d'opter pour 16-QAM2/3:");

	        jLabel102.setText("Efficacité 16-QAM2/3:");

	        jLabel103.setText("Probabilité d'opter pour 64-QAM5/6:");

	        jLabel104.setText("Efficacité de QPSK1/2:");

	        jLabel105.setText("OBF (OverBlocking Factor):");

	        jLabel106.setText("Efficacité 64-QAM5/6:");

	        jLabel107.setText("Probabilité (SMgain = 1):");

	        jLabel108.setText("Probabilité (SMgain = 2):");

	        jLabel109.setText("Probabilité (SMgain = 4):");

	        jLabel110.setText("SOH L1/L2:");

	        jLabel111.setText("Probabilité (SMgain = 8):");

	        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
	        jPanel6.setLayout(jPanel6Layout);
	        jPanel6Layout.setHorizontalGroup(
	            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel6Layout.createSequentialGroup()
	                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel6Layout.createSequentialGroup()
	                        .addGap(100, 100, 100)
	                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(jLabel98)
	                            .addComponent(jLabel96)))
	                    .addGroup(jPanel6Layout.createSequentialGroup()
	                        .addGap(2, 2, 2)
	                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel104, javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(jLabel100, javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(jLabel105, javax.swing.GroupLayout.Alignment.TRAILING)))
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel97, javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(jLabel99, javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(jLabel110, javax.swing.GroupLayout.Alignment.TRAILING))))
	                .addGap(18, 18, 18)
	                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(tf_OBF_DL, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                        .addComponent(tf_probaQPSK_DL, javax.swing.GroupLayout.Alignment.LEADING)
	                        .addComponent(CPI_DL, javax.swing.GroupLayout.Alignment.LEADING)
	                        .addComponent(tf_sohL1L2_DL, javax.swing.GroupLayout.Alignment.LEADING)
	                        .addComponent(tf_sohpbcchDL, javax.swing.GroupLayout.Alignment.LEADING)
	                        .addComponent(tf_sohpssDL, javax.swing.GroupLayout.Alignment.LEADING)
	                        .addComponent(tf_sohrs_DL, javax.swing.GroupLayout.Alignment.LEADING)
	                        .addComponent(tf_effQPSK_DL, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jSeparator14, javax.swing.GroupLayout.DEFAULT_SIZE, 8, Short.MAX_VALUE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(jLabel111)
	                    .addComponent(jLabel102)
	                    .addComponent(jLabel101)
	                    .addComponent(jLabel103)
	                    .addComponent(jLabel106)
	                    .addComponent(jLabel107)
	                    .addComponent(jLabel108)
	                    .addComponent(jLabel109))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                        .addComponent(tf_probaSMG2_DL, javax.swing.GroupLayout.Alignment.LEADING)
	                        .addComponent(tf_probaSMG1_DL, javax.swing.GroupLayout.Alignment.LEADING)
	                        .addComponent(tf_eff64QAM_DL, javax.swing.GroupLayout.Alignment.LEADING)
	                        .addComponent(tf_proba64QAM_DL, javax.swing.GroupLayout.Alignment.LEADING)
	                        .addComponent(tf_eff16QAM_DL, javax.swing.GroupLayout.Alignment.LEADING)
	                        .addComponent(tf_proba16QAM_DL, javax.swing.GroupLayout.Alignment.LEADING)
	                        .addComponent(tf_probaSMG4_DL, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addComponent(tf_probaSMG8_DL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addContainerGap())
	        );
	        jPanel6Layout.setVerticalGroup(
	            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel6Layout.createSequentialGroup()
	                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel6Layout.createSequentialGroup()
	                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(tf_sohrs_DL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(tf_sohpssDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(tf_sohpbcchDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(tf_sohL1L2_DL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(CPI_DL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(tf_probaQPSK_DL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(tf_effQPSK_DL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                    .addGroup(jPanel6Layout.createSequentialGroup()
	                        .addGap(3, 3, 3)
	                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(jLabel101)
	                            .addComponent(tf_proba16QAM_DL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(jLabel102)
	                            .addComponent(tf_eff16QAM_DL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(jLabel103)
	                            .addComponent(tf_proba64QAM_DL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(tf_eff64QAM_DL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jLabel106))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(tf_probaSMG1_DL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jLabel107))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(tf_probaSMG2_DL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jLabel108))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(tf_probaSMG4_DL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jLabel109))))
	                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel6Layout.createSequentialGroup()
	                        .addGap(0, 0, Short.MAX_VALUE)
	                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(tf_OBF_DL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(tf_probaSMG8_DL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addComponent(jLabel111))))
	            .addComponent(jSeparator14)
	        );

	        javax.swing.GroupLayout conCard_step1_right3Layout = new javax.swing.GroupLayout(conCard_step1_right3);
	        conCard_step1_right3.setLayout(conCard_step1_right3Layout);
	        conCard_step1_right3Layout.setHorizontalGroup(
	            conCard_step1_right3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(conCard_step1_right3Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(conCard_step1_right3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel31)
	                    .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        conCard_step1_right3Layout.setVerticalGroup(
	            conCard_step1_right3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(conCard_step1_right3Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel31)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );

	        conCard_step3.add(conCard_step1_right3, java.awt.BorderLayout.CENTER);

	        conGroupCard.add(conCard_step3, "CARD_3");

	        conCard_step4.setPreferredSize(new java.awt.Dimension(790, 474));
	        conCard_step4.setLayout(new java.awt.BorderLayout());

	        conCard_step1_left1.setBackground(new java.awt.Color(255, 255, 255));
	        conCard_step1_left1.setPreferredSize(new java.awt.Dimension(200, 455));

	        but_icon2.setIcon(IconPlanCap); // NOI18N
	        but_icon2.setBorderPainted(false);
	        but_icon2.setContentAreaFilled(false);
	        but_icon2.setFocusPainted(false);

	        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jLabel16.setText("Etapes:");

	        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

	        jLabel17.setText("1.   Paramètres généraux");

	        jLabel18.setText("2.   Paramètres trafic");

	        jLabel20.setText("3.   Paramètres capacité");

	        jLabel36.setText("4.   Résultats");

	        javax.swing.GroupLayout conCard_step1_left1Layout = new javax.swing.GroupLayout(conCard_step1_left1);
	        conCard_step1_left1.setLayout(conCard_step1_left1Layout);
	        conCard_step1_left1Layout.setHorizontalGroup(
	            conCard_step1_left1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(conCard_step1_left1Layout.createSequentialGroup()
	                .addGroup(conCard_step1_left1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(conCard_step1_left1Layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jLabel16))
	                    .addGroup(conCard_step1_left1Layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(conCard_step1_left1Layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jLabel17))
	                    .addGroup(conCard_step1_left1Layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jLabel18))
	                    .addGroup(conCard_step1_left1Layout.createSequentialGroup()
	                        .addGap(23, 23, 23)
	                        .addComponent(but_icon2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(conCard_step1_left1Layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jLabel20))
	                    .addGroup(conCard_step1_left1Layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jLabel36)))
	                .addContainerGap(13, Short.MAX_VALUE))
	        );
	        conCard_step1_left1Layout.setVerticalGroup(
	            conCard_step1_left1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, conCard_step1_left1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel16)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel17)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel18)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel20)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel36)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
	                .addComponent(but_icon2)
	                .addGap(46, 46, 46))
	        );

	        conCard_step4.add(conCard_step1_left1, java.awt.BorderLayout.LINE_START);

	        conCard_step1_right1.setPreferredSize(new java.awt.Dimension(647, 504));

	        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jLabel19.setText("Résultat de la planification en capacité:");

	        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

	        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Bilan"));

	        jLabel52.setText("Rayon de la cellule basé sur la capcité (km):");

	        jLabel53.setText("Nombre d'Enode-B basé sur la capacité:");

	        jLabel54.setText("Distance inter-site basée sur la capacité (km):");

	        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
	        jPanel3.setLayout(jPanel3Layout);
	        jPanel3Layout.setHorizontalGroup(
	            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel3Layout.createSequentialGroup()
	                .addGap(37, 37, 37)
	                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(jLabel54)
	                    .addComponent(jLabel53)
	                    .addComponent(jLabel52))
	                .addGap(18, 18, 18)
	                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addComponent(tf_finalCellRad)
	                    .addComponent(tf_finalNumEnb)
	                    .addComponent(tf_finalCapIS, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        jPanel3Layout.setVerticalGroup(
	            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel3Layout.createSequentialGroup()
	                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(tf_finalCellRad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(tf_finalNumEnb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(tf_finalCapIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );

	        jTable1.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {
	                {" Capacité d'une cellule (bps)", null, null},
	                {" Trafic (bps)", null, null},
	                {" Capacité en nombre d'abonnés", null, null},
	                {" Nombre d'Enode-B", null, null}
	            },
	            new String [] {
	                "Données", "Uplink", "Downlink"
	            }
	        ));
	        jScrollPane1.setViewportView(jTable1);

	        javax.swing.GroupLayout conCard_step1_right1Layout = new javax.swing.GroupLayout(conCard_step1_right1);
	        conCard_step1_right1.setLayout(conCard_step1_right1Layout);
	        conCard_step1_right1Layout.setHorizontalGroup(
	            conCard_step1_right1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(conCard_step1_right1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(conCard_step1_right1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addComponent(jLabel19)
	                    .addComponent(jSeparator5, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
	                    .addComponent(jScrollPane1)
	                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                .addContainerGap(29, Short.MAX_VALUE))
	        );
	        conCard_step1_right1Layout.setVerticalGroup(
	            conCard_step1_right1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(conCard_step1_right1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel19)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(17, 17, 17)
	                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(18, 18, 18)
	                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(202, Short.MAX_VALUE))
	        );

	        conCard_step4.add(conCard_step1_right1, java.awt.BorderLayout.CENTER);

	        conGroupCard.add(conCard_step4, "CARD_4");

	        getContentPane().add(conGroupCard, java.awt.BorderLayout.CENTER);

	        pack();
	}
	
	public void initEvents(){
		this.planCapHE = new PlanningCapHE(this);
		
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
        
        this.but_browFB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	but_browFBActionPerformed(evt);
            }
        });
       
       this.but_browAnType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	but_browAnTypeActionPerformed(evt);
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
			case 3:
				this.default_step3();
				break;
		}
	}
	
	private void but_browAnTypeActionPerformed(java.awt.event.ActionEvent evt) {   
		DialogAntenType dialog = new DialogAntenType(this,true,this,this.opsimGUI);
	}
	
	private void but_browFBActionPerformed(java.awt.event.ActionEvent evt) {   
		DialogFreqBand dialog = new DialogFreqBand(this,true,this,this.opsimGUI);
	}
	
	private void but_nextActionPerformed(java.awt.event.ActionEvent evt) {                                         
		//System.out.println("ID next card: "+this.next_id_card);
        CardLayout cl = (CardLayout)(this.conGroupCard.getLayout());
        if(this.next_id_card == 2){ 
        	if(!this.tf_FB.getText().isEmpty() && !this.tf_debitUL.getText().isEmpty() && !this.tf_debitDL.getText().isEmpty()
        			&& !this.tf_numPRB.getText().isEmpty() && !this.tf_anType.getText().isEmpty() && !this.tf_lossBody.getText().isEmpty()
        			&& !this.tf_lossBld.getText().isEmpty() && !this.tf_lossCar.getText().isEmpty() && !this.tf_noiseFig.getText().isEmpty()){
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
        	else JOptionPane.showMessageDialog(this, 
        			"Veuillez définir tous les paramètres généraux \n" +
        			"avant d'aller à l'étape suivante.",
        			"Planification en capacité: Erreur",JOptionPane.ERROR_MESSAGE);            
        }
        else if(this.next_id_card == 3){
        	if(!this.tf_pcSub1.getText().isEmpty() && !this.tf_pcSub2.getText().isEmpty() && !this.tf_pcSub3.getText().isEmpty()
        			&& !this.tf_marPart.getText().isEmpty() && !this.tf_penRate.getText().isEmpty() && !this.tf_growthRate.getText().isEmpty()
        			&& !this.tf_invTime.getText().isEmpty() && !this.isEmptyTabVal(tabModelTrafic)){
        		cl.show(this.conGroupCard,"CARD_3");
	            this.but_next.setEnabled(true);
	            this.but_prev.setEnabled(true);
	            this.but_finish.setEnabled(false);
	            this.but_reportPlan.setEnabled(false);
	            this.but_formPlan.setEnabled(false);
	            this.but_defaultPlan.setEnabled(true); 
	            this.planCov_step2();
	            this.next_id_card++;
        	}
        	else JOptionPane.showMessageDialog(this, 
        			"Veuillez définir tous les paramètres de trafic \n" +
        			"avant d'aller à l'étape suivante.",
        			"Planification en capacité: Erreur",JOptionPane.ERROR_MESSAGE);        	
        }
        else if(this.next_id_card == 4){
        	if(!this.tf_sohrsUL.getText().isEmpty() && !this.tf_sohprachUL.getText().isEmpty() && !this.tf_sohpucchUL.getText().isEmpty() &&
    		!this.tf_cpiUL.getText().isEmpty() && !this.tf_probaQPSK_UL.getText().isEmpty() && !this.tf_effQPSK_UL.getText().isEmpty() &&
    		!this.tf_OBF_UL.getText().isEmpty() && !this.tf_proba16QAM_UL.getText().isEmpty() && !this.tf_eff16QAM_UL.getText().isEmpty() &&
    		!this.tf_proba64QAM_UL.getText().isEmpty() && !this.tf_eff64QAM_UL.getText().isEmpty() && !this.tf_probaSMG1_UL.getText().isEmpty() &&
    		!this.tf_probaSMG2_UL.getText().isEmpty() && !this.tf_probaSMG4_UL.getText().isEmpty() && !this.tf_sohrs_DL.getText().isEmpty() &&
    		!this.tf_sohpssDL.getText().isEmpty() && !this.tf_sohpbcchDL.getText().isEmpty() && !this.tf_sohL1L2_DL.getText().isEmpty() &&
    		!this.CPI_DL.getText().isEmpty() && !this.tf_probaQPSK_DL.getText().isEmpty() && !this.tf_effQPSK_DL.getText().isEmpty() &&
    		!this.tf_OBF_DL.getText().isEmpty() && !this.tf_proba16QAM_DL.getText().isEmpty() && !this.tf_eff16QAM_DL.getText().isEmpty() &&
    		!this.tf_proba64QAM_DL.getText().isEmpty() && !this.tf_eff64QAM_DL.getText().isEmpty() && !this.tf_probaSMG1_DL.getText().isEmpty() &&
    		!this.tf_probaSMG2_DL.getText().isEmpty() && !this.tf_probaSMG4_DL.getText().isEmpty() && !this.tf_probaSMG8_DL.getText().isEmpty()){
        		cl.show(this.conGroupCard,"CARD_4");
	            this.but_next.setEnabled(false);
	            this.but_prev.setEnabled(true);
	            this.but_finish.setEnabled(true);
	            this.but_reportPlan.setEnabled(true);
	            this.but_formPlan.setEnabled(true);
	            this.but_defaultPlan.setEnabled(false);
	            this.planCov_step3();
	            this.planCap_result();
	            this.next_id_card++;
        	}
        	else JOptionPane.showMessageDialog(this, 
        			"Veuillez définir tous les paramètres de calcul des capcités \n" +
        			"avant d'aller à l'étape suivante.",
        			"Planification en capacité: Erreur",JOptionPane.ERROR_MESSAGE);
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
        else if(this.next_id_card == 4){
            cl.show(this.conGroupCard,"CARD_3");
            this.but_next.setEnabled(true);
            this.but_prev.setEnabled(true);
            this.but_finish.setEnabled(false);
            this.but_reportPlan.setEnabled(false);
            this.but_formPlan.setEnabled(false);
            this.but_defaultPlan.setEnabled(true);
        }
        else {            
            cl.show(this.conGroupCard,"CARD_4");
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
		projWS.getPlanification().getCapPlanif().setState("DONE");
		double progress = projWS.getProgress();
		if(progress < 29.0)projWS.setProgress(29.0);
		projWS = projDAO.update(projWS);
		projConWS.setModel(projWS);
		this.opsimGUI.getListControler().remove("ProjectController");
		this.opsimGUI.getListControler().put("ProjectController", projConWS);
    	this.setVisible(false);
    } 
    
    public void planCov_step1(){
    	FreqBandDAO fbDAO = (FreqBandDAO) DAOFactory.getFreqBandDAO();
		AntennaTypeDAO antDAO = (AntennaTypeDAO) DAOFactory.getAntennaTypeDAO();
		
		ProjectController projConWS = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
		Project projWS = (Project) projConWS.getModel();
		GenParameter genParam = projWS.getParameter().getGenParam();
		FreqBand freqBand = fbDAO.find(this.tf_FB.getText());
		genParam.setBwCellBoardUL(Double.parseDouble(this.tf_debitUL.getText()));
		genParam.setBwCellBoardDL(Double.parseDouble(this.tf_debitDL.getText()));
		genParam.setNumPRBUl(Integer.parseInt(this.tf_numPRB.getText()));
		AntennaType antenType = antDAO.find(this.tf_anType.getText());
		
		String probCov = this.cbo_probaCov.getSelectedItem().toString();
		System.out.println("Selcted Item before next: "+probCov);
		String[] parts = probCov.split("%");
		probCov = parts[0];
		genParam.setCoverageProba(Double.parseDouble(probCov));
		
		String chanModel = this.cbo_chanModel.getSelectedItem().toString();
		genParam.setChannelModel(chanModel);
		String mimo = this.cbo_mimo.getSelectedItem().toString();
		projWS.getParameter().getOtherParam().setMimoType(mimo);
		Double bwFB = Double.parseDouble(this.cbo_bwFB.getSelectedItem().toString());
		genParam.setBwFB(bwFB);
		
		genParam.setBodyLoss(Double.parseDouble(this.tf_lossBody.getText()));
		genParam.setBuildingLoss(Double.parseDouble(this.tf_lossBld.getText()));
		genParam.setCarLoss(Double.parseDouble(this.tf_lossCar.getText()));
		genParam.setFigureNoise(Double.parseDouble(this.tf_noiseFig.getText()));
		genParam.setFadingMargin(Double.parseDouble(this.tf_fadingMargin.getText()));
		
		projWS.setFreqBand(freqBand);
		projWS.getParameter().getAntParam().setAntennaType(antenType);		
		projWS.getParameter().setGenParam(genParam);
		
		projWS.setUpdatedDate(new Date());
		projConWS.setModel(projWS);
		this.opsimGUI.getListControler().remove("ProjectController");
		this.opsimGUI.getListControler().put("ProjectController", projConWS);
    }
    public void default_step1(){
    	FreqBandDAO fbDAO = (FreqBandDAO) DAOFactory.getFreqBandDAO();
		AntennaTypeDAO antDAO = (AntennaTypeDAO) DAOFactory.getAntennaTypeDAO();
		
		//FreqBand freqBand = fbDAO.find(1);
		this.tf_FB.setText("LTE ETRAN FB 41 - TDD");
		//this.tf_FB.repaint();		
		this.tf_debitUL.setText("500");
		this.tf_debitDL.setText("5000");
		this.tf_numPRB.setText("5");
		AntennaType antenType = antDAO.find(1);
		this.tf_anType.setText(antenType.getName());
		this.cbo_probaCov.setSelectedIndex(1);
		this.cbo_chanModel.setSelectedIndex(0);
		this.cbo_mimo.setSelectedIndex(0);
		this.cbo_bwFB.setSelectedIndex(0);
		this.tf_lossBody.setText("3");
		this.tf_lossBld.setText("18");
		this.tf_lossCar.setText("0");
		this.tf_noiseFig.setText("2.5");
		this.tf_fadingMargin.setText("4.2");
    }
    
    public void planCov_step2(){
    	ProjectController projConWS = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
		Project projWS = (Project) projConWS.getModel();
		GenParameter genParam = projWS.getParameter().getGenParam();
		CustomerParameter custParam = projWS.getParameter().getCustParam();
		
    	ArrayList<TrafficModel> listMT = new ArrayList<TrafficModel>();
    	ArrayList<Subscription> listSub = new ArrayList<Subscription>();
    	int n_col = tabModelTrafic.getColumnCount();
    	int n_row = tabModelTrafic.getRowCount();
    	
    	for(int i=0; i<n_row;i++){
    		TrafficModel tm = new TrafficModel();
    		tm.setClass_serv(tabModelTrafic.getModel().getValueAt(i, 0).toString());
    		tm.setBw_dl(Double.parseDouble(tabModelTrafic.getModel().getValueAt(i, 1).toString()));
    		tm.setBw_ul(Double.parseDouble(tabModelTrafic.getModel().getValueAt(i, 2).toString()));
    		tm.setPoh(Double.parseDouble(tabModelTrafic.getModel().getValueAt(i, 3).toString()));
    		tm.setTp(Double.parseDouble(tabModelTrafic.getModel().getValueAt(i, 4).toString()));
    		listMT.add(tm);
    	}    	
    	ArrayList listItems_sub1 = (ArrayList) this.listAbonPublic.getSelectedValuesList();
    	ArrayList listItems_sub2 = (ArrayList) this.listAbonPriv.getSelectedValuesList();
    	ArrayList listItems_sub3 = (ArrayList) this.listAbonBusiness.getSelectedValuesList();    	
    	ArrayList<Service> listServ_sub1 = new ArrayList<Service>();
    	ArrayList<Service> listServ_sub2 = new ArrayList<Service>();
    	ArrayList<Service> listServ_sub3 = new ArrayList<Service>();
    	
    	for(Object obj : listItems_sub1){
    		Service serv = new Service();
    		serv.setName(obj.toString());
    		listServ_sub1.add(serv);
    	}
    	for(Object obj : listItems_sub2){
    		Service serv = new Service();
    		serv.setName(obj.toString());
    		listServ_sub2.add(serv);
    	}
    	for(Object obj : listItems_sub3){
    		Service serv = new Service();
    		serv.setName(obj.toString());
    		listServ_sub3.add(serv);
    	}
    	
    	Subscription sub1 = new Subscription();    	
    	sub1.setCustParam_id(custParam.getId());
    	sub1.setName("Sub 1");
    	sub1.setDescription("Abonnement public");
    	sub1.setPc_sub(Double.parseDouble(this.tf_pcSub1.getText()));
    	sub1.setListServ(listServ_sub1);
    	
    	Subscription sub2 = new Subscription();
    	sub2.setCustParam_id(custParam.getId());
    	sub2.setName("Sub 2");
    	sub2.setDescription("Abonnement privilegie");
    	sub2.setPc_sub(Double.parseDouble(this.tf_pcSub2.getText()));
    	sub2.setListServ(listServ_sub2);
    	
    	Subscription sub3 = new Subscription();
    	sub3.setCustParam_id(custParam.getId());
    	sub3.setName("Sub 3");
    	sub3.setDescription("Abonnement Business");
    	sub3.setPc_sub(Double.parseDouble(this.tf_pcSub3.getText()));
    	sub3.setListServ(listServ_sub3);
    	
    	listSub.add(sub1);
    	listSub.add(sub2);
    	listSub.add(sub3);    	
    	custParam.setListSub(listSub);
    	custParam.setListTM(listMT);
    	custParam.setMarPart(Double.parseDouble(this.tf_marPart.getText()));
    	custParam.setPenRate(Double.parseDouble(this.tf_penRate.getText()));
    	custParam.setGrowthRate(Double.parseDouble(this.tf_growthRate.getText()));
    	custParam.setInvTime(Integer.parseInt(this.tf_invTime.getText()));
    	
    	projWS.getParameter().setCustParam(custParam);
    	projWS.setUpdatedDate(new Date());
		projConWS.setModel(projWS);
		this.opsimGUI.getListControler().remove("ProjectController");
		this.opsimGUI.getListControler().put("ProjectController", projConWS);
    }
    
    public void default_step2(){
    		tabModelTrafic.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {
	                {" VoIP", 26.90, 26.90, 3.63, 20},
	                {" HTTP Navigation", 250.11, 62.53, 5.3, 100},
	                {" FTP", 750.34, 140.69, 9.8, 20},
	                {" Interactive Streaming", 134.90, 134.90, 2.2, 10},
	                {" Background Services", 15.69, 15.69, 1, 50}
	            },
	            new String [] {
	                "Services", "Débit en DL (Kps)", "Débit en UL (kps)", "POH (%)", "TP (%)"
	            }
	        ) {
	            boolean[] canEdit = new boolean [] {
	                false, true, true, true, true
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        });
	        tabModelTrafic.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	        tabModelTrafic.getColumnModel().getColumn(0).setPreferredWidth(160);
	        tabModelTrafic.getColumnModel().getColumn(1).setPreferredWidth(100);
	        tabModelTrafic.getColumnModel().getColumn(2).setPreferredWidth(100);
	        tabModelTrafic.getColumnModel().getColumn(3).setPreferredWidth(80);
	        
	        tabModelTrafic.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
	        tabModelTrafic.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
	        tabModelTrafic.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
	        tabModelTrafic.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
	        
	        jScrollPane3.setViewportView(tabModelTrafic);
	        jScrollPane3.repaint();
	        if (tabModelTrafic.getColumnModel().getColumnCount() > 0) {
	            tabModelTrafic.getColumnModel().getColumn(0).setResizable(false);
	            tabModelTrafic.getColumnModel().getColumn(1).setResizable(false);
	            tabModelTrafic.getColumnModel().getColumn(2).setResizable(false);
	            tabModelTrafic.getColumnModel().getColumn(3).setResizable(false);
	            tabModelTrafic.getColumnModel().getColumn(4).setResizable(false);
	        }
	        
	        int[] list1 = {0,1,2,3};	        
	        int[] list2 = {0,1,2,3,4};
	        int[] list3 = {0,1,2,3,4,5};
	        this.listAbonPublic.setSelectedIndices(list1);
	        this.listAbonPriv.setSelectedIndices(list2);
	        this.listAbonBusiness.setSelectedIndices(list3);
	        
	        this.tf_pcSub1.setText("45");
	        this.tf_pcSub2.setText("35");
	        this.tf_pcSub3.setText("20");
	        
	        this.tf_marPart.setText("33");
	        this.tf_penRate.setText("9");
	        this.tf_growthRate.setText("30");
	        this.tf_invTime.setText("10");	        
    }
	
    public void planCov_step3(){
    	ProjectController projConWS = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
		Project projWS = (Project) projConWS.getModel();
		CapParameter capParam = projWS.getParameter().getCapParam();
		
		capParam.setSoh_RS_UL(Double.parseDouble(this.tf_sohrsUL.getText()));
		capParam.setSoh_PRACH_UL(Double.parseDouble(this.tf_sohprachUL.getText()));
		capParam.setSoh_PUCCH_UL(Double.parseDouble(this.tf_sohpucchUL.getText()));
		capParam.setCpi_UL(Double.parseDouble(this.tf_cpiUL.getText()));
		capParam.setProbaQPSK_1on2_UL(Double.parseDouble(this.tf_probaQPSK_UL.getText()));
		capParam.setEffQPSK_1on2_UL(Double.parseDouble(this.tf_effQPSK_UL.getText()));
		capParam.setObf_UL(Double.parseDouble(this.tf_OBF_UL.getText()));
		capParam.setProba16QAM_2on3_UL(Double.parseDouble(this.tf_proba16QAM_UL.getText()));
		capParam.setEff16QAM_2on3_UL(Double.parseDouble(this.tf_eff16QAM_UL.getText()));
		capParam.setProba64QAM_5on6_UL(Double.parseDouble(this.tf_proba64QAM_UL.getText()));
		capParam.setEff64QAM_5on6_UL(Double.parseDouble(this.tf_eff64QAM_UL.getText()));
		capParam.setProbaSMgain_1_UL(Double.parseDouble(this.tf_probaSMG1_UL.getText()));
		capParam.setProbaSMgain_2_UL(Double.parseDouble(this.tf_probaSMG2_UL.getText()));
		capParam.setProbaSMgain_4_UL(Double.parseDouble(this.tf_probaSMG4_UL.getText()));
		
		capParam.setSoh_RS_DL(Double.parseDouble(this.tf_sohrs_DL.getText()));
		capParam.setSoh_PSS_SSS_DL(Double.parseDouble(this.tf_sohpssDL.getText()));
		capParam.setSoh_PBCCH_DL(Double.parseDouble(this.tf_sohpbcchDL.getText()));
		capParam.setSoh_L1_L2_DL(Double.parseDouble(this.tf_sohL1L2_DL.getText()));
		capParam.setCpi_DL(Double.parseDouble(this.CPI_DL.getText()));
		capParam.setProbaQPSK_1on2_DL(Double.parseDouble(this.tf_probaQPSK_DL.getText()));
		capParam.setEffQPSK_1on2_DL(Double.parseDouble(this.tf_effQPSK_DL.getText()));
		capParam.setObf_DL(Double.parseDouble(this.tf_OBF_DL.getText()));
		capParam.setProba16QAM_2on3_DL(Double.parseDouble(this.tf_proba16QAM_DL.getText()));
		capParam.setEff16QAM_2on3_DL(Double.parseDouble(this.tf_eff16QAM_DL.getText()));
		capParam.setProba64QAM_5on6_DL(Double.parseDouble(this.tf_proba64QAM_DL.getText()));
		capParam.setEff64QAM_5on6_DL(Double.parseDouble(this.tf_eff64QAM_DL.getText()));
		capParam.setProbaSMgain_1_DL(Double.parseDouble(this.tf_probaSMG1_DL.getText()));
		capParam.setProbaSMgain_2_DL(Double.parseDouble(this.tf_probaSMG2_DL.getText()));
		capParam.setProbaSMgain_4_DL(Double.parseDouble(this.tf_probaSMG4_DL.getText()));
		capParam.setProbaSMgain_8_DL(Double.parseDouble(this.tf_probaSMG8_DL.getText()));
		
		projWS.getParameter().setCapParam(capParam);
		projWS.setUpdatedDate(new Date());
		projConWS.setModel(projWS);
		this.opsimGUI.getListControler().remove("ProjectController");
		this.opsimGUI.getListControler().put("ProjectController", projConWS);		
    }
    
    public void default_step3(){
    	this.tf_sohrsUL.setText("1");
		this.tf_sohprachUL.setText("6");
		this.tf_sohpucchUL.setText("1");
		this.tf_cpiUL.setText("6");
		this.tf_probaQPSK_UL.setText("0.6631");
		this.tf_effQPSK_UL.setText("1");
		this.tf_OBF_UL.setText("0.38");
		this.tf_proba16QAM_UL.setText("0.6426");
		this.tf_eff16QAM_UL.setText("1");
		this.tf_proba64QAM_UL.setText("0.9258");
		this.tf_eff64QAM_UL.setText("1");
		this.tf_probaSMG1_UL.setText("1");
		this.tf_probaSMG2_UL.setText("1");
		this.tf_probaSMG4_UL.setText("1");
		
		this.tf_sohrs_DL.setText("1");
		this.tf_sohpssDL.setText("1");
		this.tf_sohpbcchDL.setText("1");
		this.tf_sohL1L2_DL.setText("1");
		this.CPI_DL.setText("7");
		this.tf_probaQPSK_DL.setText("0.555");
		this.tf_effQPSK_DL.setText("1");
		this.tf_OBF_DL.setText("0.22");
		this.tf_proba16QAM_DL.setText("0.535");
		this.tf_eff16QAM_DL.setText("1");
		this.tf_proba64QAM_DL.setText("0.7717");
		this.tf_eff64QAM_DL.setText("1");
		this.tf_probaSMG1_DL.setText("1");
		this.tf_probaSMG2_DL.setText("1");
		this.tf_probaSMG4_DL.setText("1");
		this.tf_probaSMG8_DL.setText("1");
    }
    
    public void planCap_result(){
    	ProjectController projConWS = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
		Project projWS = (Project) projConWS.getModel();
		PlanCapController planCapConWS = (PlanCapController) this.opsimGUI.getListControler().get("PlanCapController");
		CapacityPlanif capPlanifWS = projWS.getPlanification().getCapPlanif();
		planCapConWS.setModel(capPlanifWS);
		RPlanCapacity resCap = planCapConWS.calResult(projWS).getResCap();	
		
		jTable1.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {
	                {" Capacité d'une cellule (Mbps)", round(resCap.getCellCapacityUL()/1000000,3), round(resCap.getCellCapacityDL()/1000000,3)},
	                {" Trafic Total à vehiculer(Mbps)", round(resCap.getTrafficUL()/1000000,3), round(resCap.getTrafficDL()/1000000,3)},
	                {" Capacité en nombre d'abonnés", round(resCap.getNumCustomerByCellUL(),3), round(resCap.getNumCustomerByCellDL(),3)},
	                {" Nombre d'Enode-B", resCap.getNumENBUl(), resCap.getNumENBDl()}
	            },
	            new String [] {
	                "Données", "Uplink", "Downlink"
	            }
	        ));
		jScrollPane1.setViewportView(jTable1);
		jScrollPane1.repaint();
		this.tf_finalCellRad.setText(Double.toString(round(resCap.getCellRadius(),3)));
		this.tf_finalNumEnb.setText(Integer.toString(Math.max(resCap.getNumENBUl(), resCap.getNumENBDl())));
		this.tf_finalCapIS.setText(Double.toString(round(resCap.getInterSiteDist(),3)));
		
		projWS.getPlanification().getResult().setResCap(resCap);
		projConWS.setModel(projWS);
		this.opsimGUI.getListControler().remove("ProjectController");
		this.opsimGUI.getListControler().put("ProjectController", projConWS);
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
		this.initStep3(proj);
		//this.initResut(proj);
	}
    
    public void initStep1(Project proj){
    	System.out.println("PlanCap-Dialog: initStep1");
    	GenParameter genParam = proj.getParameter().getGenParam();
    	AntennaParameter antParam = proj.getParameter().getAntParam();
    	OtherParameter otherParam = proj.getParameter().getOtherParam();
    	FreqBand freqBand = proj.getFreqBand();
		
		this.tf_FB.setText(freqBand.getName());
		this.tf_debitUL.setText(Double.toString(genParam.getBwCellBoardUL()));
		this.tf_debitDL.setText(Double.toString(genParam.getBwCellBoardDL()));
		this.tf_numPRB.setText(Integer.toString(genParam.getNumPRBUl()));
		AntennaType antenType = antParam.getAntennaType();
		this.tf_anType.setText(antenType.getName());
		int probaCov = (int)genParam.getCoverageProba();
		//System.out.println("probCov = "+probaCov);
		switch(probaCov){
			case 98:
				this.cbo_probaCov.setSelectedIndex(0);
				break;
			case 96:
				this.cbo_probaCov.setSelectedIndex(1);
				break;
			case 94:
				this.cbo_probaCov.setSelectedIndex(2);
				break;
			case 92:
				this.cbo_probaCov.setSelectedIndex(3);
				break;
			case 90:
				this.cbo_probaCov.setSelectedIndex(4);
				break;
			case 88:
				this.cbo_probaCov.setSelectedIndex(5);
				break;
			case 86:
				this.cbo_probaCov.setSelectedIndex(6);
				break;
			default:
				this.cbo_probaCov.setSelectedIndex(1);
				break;			
		}	
		
		String chanModel = genParam.getChannelModel();
		switch(chanModel){
			case "EPA5":
				this.cbo_chanModel.setSelectedIndex(0);
				break;	
			case "EVA70":
				this.cbo_chanModel.setSelectedIndex(1);
				break;
			case "ETU300":
				this.cbo_chanModel.setSelectedIndex(2);
				break;
			default:
				this.cbo_chanModel.setSelectedIndex(0);
				break;
		}
		
		String mimo = otherParam.getMimoType();
		switch(mimo){
			case "MIMO1x1":
				this.cbo_mimo.setSelectedIndex(0);
				break;	
			case "MIMO2x2":
				this.cbo_mimo.setSelectedIndex(1);
				break;
			case "MIMO3x3":
				this.cbo_mimo.setSelectedIndex(2);
				break;
			case "MIMO4x4":
				this.cbo_mimo.setSelectedIndex(3);
				break;
			case "MIMO8x8":
				this.cbo_mimo.setSelectedIndex(4);
				break;
			default:
				this.cbo_mimo.setSelectedIndex(0);
				break;
		}
		
		double bwFB = genParam.getBwFB();
		if(bwFB == 1.4)this.cbo_bwFB.setSelectedIndex(0);
		else if(bwFB == 3)this.cbo_bwFB.setSelectedIndex(1);
		else if(bwFB == 5)this.cbo_bwFB.setSelectedIndex(2);
		else if(bwFB == 10)this.cbo_bwFB.setSelectedIndex(3);
		else if(bwFB == 15)this.cbo_bwFB.setSelectedIndex(4);
		else if(bwFB == 20)this.cbo_bwFB.setSelectedIndex(5);
		else this.cbo_bwFB.setSelectedIndex(0);		
		
		this.tf_lossBody.setText(Double.toString(genParam.getBodyLoss()));
		this.tf_lossBld.setText(Double.toString(genParam.getBuildingLoss()));
		this.tf_lossCar.setText(Double.toString(genParam.getCarLoss()));
		this.tf_noiseFig.setText(Double.toString(genParam.getFigureNoise()));
		this.tf_fadingMargin.setText(Double.toString(genParam.getFadingMargin()));
    }
    
    public void initStep2(Project proj){
    	System.out.println("PlanCap-Dialog: initStep2");
    	GenParameter genParam = proj.getParameter().getGenParam();
		CustomerParameter custParam = proj.getParameter().getCustParam();
		
		ArrayList<TrafficModel> listMT = new ArrayList<TrafficModel>();
    	ArrayList<Subscription> listSub = new ArrayList<Subscription>();
    	ArrayList listItems_sub1 = new ArrayList();
    	ArrayList listItems_sub2 = new ArrayList();
    	ArrayList listItems_sub3 = new ArrayList();    	
    	ArrayList<Service> listServ_sub1 = new ArrayList<Service>();
    	ArrayList<Service> listServ_sub2 = new ArrayList<Service>();
    	ArrayList<Service> listServ_sub3 = new ArrayList<Service>();
    	
    	listMT = custParam.getListTM();
    	listSub = custParam.getListSub();
    	
    	listServ_sub1 = listSub.get(0).getListServ();
    	listServ_sub2 = listSub.get(1).getListServ();
    	listServ_sub3 = listSub.get(2).getListServ();
    	
    	for(Service serv : listServ_sub1){
    		listItems_sub1.add(serv.getName());
    	}
    	for(Service serv : listServ_sub2){
    		listItems_sub2.add(serv.getName());
    	}
    	for(Service serv : listServ_sub3){
    		listItems_sub3.add(serv.getName());
    	}
    	
    	tabModelTrafic.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {
	                {" VoIP", listMT.get(0).getBw_dl(), listMT.get(0).getBw_ul(), listMT.get(0).getPoh(), listMT.get(0).getTp()},
	                {" HTTP Navigation", listMT.get(1).getBw_dl(), listMT.get(1).getBw_ul(), listMT.get(1).getPoh(), listMT.get(1).getTp()},
	                {" FTP", listMT.get(2).getBw_dl(), listMT.get(2).getBw_ul(), listMT.get(2).getPoh(), listMT.get(2).getTp()},
	                {" Interactive Streaming", listMT.get(3).getBw_dl(), listMT.get(3).getBw_ul(), listMT.get(3).getPoh(), listMT.get(3).getTp()},
	                {" Background Services", listMT.get(4).getBw_dl(), listMT.get(4).getBw_ul(), listMT.get(4).getPoh(), listMT.get(4).getTp()}
	            },
	            new String [] {
	                "Services", "Débit en DL (Kps)", "Débit en UL (kps)", "POH (%)", "TP (%)"
	            }
	        ) {
	            boolean[] canEdit = new boolean [] {
	                false, true, true, true, true
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
    	});
	    tabModelTrafic.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tabModelTrafic.getColumnModel().getColumn(0).setPreferredWidth(160);
	    tabModelTrafic.getColumnModel().getColumn(1).setPreferredWidth(100);
	    tabModelTrafic.getColumnModel().getColumn(2).setPreferredWidth(100);
	    tabModelTrafic.getColumnModel().getColumn(3).setPreferredWidth(80);
	    
	    tabModelTrafic.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
	    tabModelTrafic.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
	    tabModelTrafic.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
	    tabModelTrafic.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
	    
	    jScrollPane3.setViewportView(tabModelTrafic);
	    jScrollPane3.repaint();
	    if (tabModelTrafic.getColumnModel().getColumnCount() > 0) {
	        tabModelTrafic.getColumnModel().getColumn(0).setResizable(false);
	        tabModelTrafic.getColumnModel().getColumn(1).setResizable(false);
	        tabModelTrafic.getColumnModel().getColumn(2).setResizable(false);
	        tabModelTrafic.getColumnModel().getColumn(3).setResizable(false);
	        tabModelTrafic.getColumnModel().getColumn(4).setResizable(false);
	    }
	    
	    int[] list1 = new int[4];	        
	    int[] list2 = new int[5];
	    int[] list3 = new int[6];
	    for(Object obj: listItems_sub1){
	    	if("VoIP".equals(obj.toString()))list1[0] = 0;
	    	else if("Vidéo Streaming".equals(obj.toString()))list1[1] = 1;
	    	else if("Jeu".equals(obj.toString()))list1[2] = 2;
	    	else if("Web Browsing".equals(obj.toString()))list1[3] = 3;
	    }
	    for(Object obj: listItems_sub2){
	    	if("VoIP".equals(obj.toString()))list2[0] = 0;
	    	else if("Vidéo Streaming".equals(obj.toString()))list2[1] = 1;
	    	else if("Jeu".equals(obj.toString()))list2[2] = 2;
	    	else if("Web Browsing".equals(obj.toString()))list2[3] = 3;
	    	else if("E-Mail".equals(obj.toString()))list2[4] = 4;
	    }
	    for(Object obj: listItems_sub3){
	    	if("VoIP".equals(obj.toString()))list3[0] = 0;
	    	else if("Vidéo Streaming".equals(obj.toString()))list3[1] = 1;
	    	else if("Web Browsing".equals(obj.toString()))list3[2] = 2;	        	
	    	else if("E-Mail".equals(obj.toString()))list3[3] = 3;
	    	else if("FTP".equals(obj.toString()))list3[4] = 4;
	    	else if("Vidéo Conférence".equals(obj.toString()))list3[5] = 5;
	    }
	    this.listAbonPublic.setSelectedIndices(list1);
	    this.listAbonPriv.setSelectedIndices(list2);
	    this.listAbonBusiness.setSelectedIndices(list3);
	    
	    this.tf_pcSub1.setText(Double.toString(listSub.get(0).getPc_sub()));
	    this.tf_pcSub2.setText(Double.toString(listSub.get(1).getPc_sub()));
	    this.tf_pcSub3.setText(Double.toString(listSub.get(2).getPc_sub()));
	    
	    this.tf_marPart.setText(Double.toString(custParam.getMarPart()));
	    this.tf_penRate.setText(Double.toString(custParam.getPenRate()));
	    this.tf_growthRate.setText(Double.toString(custParam.getGrowthRate()));
	    this.tf_invTime.setText(Integer.toString(custParam.getInvTime()));	
    }
    
    public void initStep3(Project proj){
    	System.out.println("PlanCap-Dialog: initStep2");
    	
    	CapParameter capParam = proj.getParameter().getCapParam();
    	this.tf_sohrsUL.setText(Double.toString(capParam.getSoh_RS_UL()));
		this.tf_sohprachUL.setText(Double.toString(capParam.getSoh_PRACH_UL()));
		this.tf_sohpucchUL.setText(Double.toString(capParam.getSoh_PUCCH_UL()));
		this.tf_cpiUL.setText(Double.toString(capParam.getCpi_UL()));
		this.tf_probaQPSK_UL.setText(Double.toString(capParam.getProbaQPSK_1on2_UL()));
		this.tf_effQPSK_UL.setText(Double.toString(capParam.getEffQPSK_1on2_UL()));
		this.tf_OBF_UL.setText(Double.toString(capParam.getObf_UL()));
		this.tf_proba16QAM_UL.setText(Double.toString(capParam.getProba16QAM_2on3_UL()));
		this.tf_eff16QAM_UL.setText(Double.toString(capParam.getEff16QAM_2on3_UL()));
		this.tf_proba64QAM_UL.setText(Double.toString(capParam.getProba64QAM_5on6_UL()));
		this.tf_eff64QAM_UL.setText(Double.toString(capParam.getEff64QAM_5on6_UL()));
		this.tf_probaSMG1_UL.setText(Double.toString(capParam.getProbaSMgain_1_UL()));
		this.tf_probaSMG2_UL.setText(Double.toString(capParam.getProbaSMgain_2_UL()));
		this.tf_probaSMG4_UL.setText(Double.toString(capParam.getProbaSMgain_4_UL()));
		
		this.tf_sohrs_DL.setText(Double.toString(capParam.getSoh_RS_DL()));
		this.tf_sohpssDL.setText(Double.toString(capParam.getSoh_PSS_SSS_DL()));
		this.tf_sohpbcchDL.setText(Double.toString(capParam.getSoh_PBCCH_DL()));
		this.tf_sohL1L2_DL.setText(Double.toString(capParam.getSoh_L1_L2_DL()));
		this.CPI_DL.setText(Double.toString(capParam.getCpi_DL()));
		this.tf_probaQPSK_DL.setText(Double.toString(capParam.getProbaQPSK_1on2_DL()));
		this.tf_effQPSK_DL.setText(Double.toString(capParam.getEffQPSK_1on2_DL()));
		this.tf_OBF_DL.setText(Double.toString(capParam.getObf_DL()));
		this.tf_proba16QAM_DL.setText(Double.toString(capParam.getProba16QAM_2on3_DL()));
		this.tf_eff16QAM_DL.setText(Double.toString(capParam.getEff16QAM_2on3_DL()));
		this.tf_proba64QAM_DL.setText(Double.toString(capParam.getProba64QAM_5on6_DL()));
		this.tf_eff64QAM_DL.setText(Double.toString(capParam.getEff64QAM_5on6_DL()));
		this.tf_probaSMG1_DL.setText(Double.toString(capParam.getProbaSMgain_1_DL()));
		this.tf_probaSMG2_DL.setText(Double.toString(capParam.getProbaSMgain_2_DL()));
		this.tf_probaSMG4_DL.setText(Double.toString(capParam.getProbaSMgain_4_DL()));
		this.tf_probaSMG8_DL.setText(Double.toString(capParam.getProbaSMgain_8_DL()));
    }
    
    public void initResut(Project proj){
    	
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DialogPlanCap dialog = new DialogPlanCap(null, true, false);

	}

	/* (non-Javadoc)
	 * @see cm.opsim.observer.Observer#update(cm.opsim.observer.Observable)
	 */
	@Override
	public void update(Observable observ) {
		ProjectController projConWS = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
		Project projWS = (Project) projConWS.getModel();
		
		if(observ instanceof DialogFreqBand){	
			DialogFreqBand dialog = (DialogFreqBand) observ;
			projWS.setFreqBand(dialog.getFreqBand());
			this.tf_FB.setText(dialog.getFreqBand().getName());
			this.tf_FB.repaint();
			projConWS.setModel(projWS);
			this.opsimGUI.getListControler().remove("ProjectController");
			this.opsimGUI.getListControler().put("ProjectController", projConWS);
		}
		else if(observ instanceof DialogAntenType){
			DialogAntenType dialog = (DialogAntenType) observ;
			projWS.getParameter().getAntParam().setAntennaType(dialog.getAntenType());
			this.tf_anType.setText(dialog.getAntenType().getName());
			this.tf_anType.repaint();
			projConWS.setModel(projWS);
			this.opsimGUI.getListControler().remove("ProjectController");
			this.opsimGUI.getListControler().put("ProjectController", projConWS);
		}
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
