/**
 * 
 */
package cm.opsim.view;

import java.awt.CardLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import cm.opsim.controller.PlanCovController;
import cm.opsim.controller.ProjectController;
import cm.opsim.dao.AntennaTypeDAO;
import cm.opsim.dao.DAO;
import cm.opsim.dao.DAOFactory;
import cm.opsim.dao.FreqBandDAO;
import cm.opsim.event.AbstractHandleEvent;
import cm.opsim.event.PlanningCovHE;
import cm.opsim.model.AntennaType;
import cm.opsim.model.Cost231Hata;
import cm.opsim.model.CoveragePlanif;
import cm.opsim.model.EnbParameter;
import cm.opsim.model.EnvParameter;
import cm.opsim.model.FreqBand;
import cm.opsim.model.GenParameter;
import cm.opsim.model.OkumuraHata;
import cm.opsim.model.PmParameter;
import cm.opsim.model.Project;
import cm.opsim.model.RPlanCoverage;
import cm.opsim.model.UeParameter;
import cm.opsim.observer.Observable;
import cm.opsim.observer.Observer;

/**
 * @author Romuald FOTSO
 *
 */
public class DialogPlanCov extends JDialog implements Observer, Observable{
	// Variables declaration - do not modify                     
    private javax.swing.JButton but_browAnType;
    private javax.swing.JButton but_browFB;
    private javax.swing.JButton but_browOtherPM;
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
    private javax.swing.JComboBox cbo_siteType;
    private javax.swing.JComboBox cbo_typeZonePM1;
    private javax.swing.JComboBox cbo_typeZonePM2;
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel jLabel46;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable tabResCov;
    private javax.swing.JLabel lab_des_lte10;
    private javax.swing.JLabel lab_des_lte11;
    private javax.swing.JTextPane panDes_paramGen;
    private javax.swing.JTextPane panDes_paramGen1;
    private javax.swing.JRadioButton rad_pmOpt1;
    private javax.swing.JRadioButton rad_pmOpt2;
    private javax.swing.JRadioButton rad_pmOpt3;
    private javax.swing.JTextField tf_CCO;
    private javax.swing.JTextField tf_FB;
    private javax.swing.JTextField tf_MHAG;
    private javax.swing.JTextField tf_anType;
    private javax.swing.JTextField tf_angPM2;
    private javax.swing.JTextField tf_debitDL;
    private javax.swing.JTextField tf_debitUL;
    private javax.swing.JTextField tf_enbCableLoss;
    private javax.swing.JTextField tf_enbFactorFC;
    private javax.swing.JTextField tf_enbGain;
    private javax.swing.JTextField tf_enbJumper;
    private javax.swing.JTextField tf_enbPower;
    private javax.swing.JTextField tf_enbSNIR;
    private javax.swing.JTextField tf_enbSysCharge;
    private javax.swing.JTextField tf_enbThermNoise;
    private javax.swing.JTextField tf_fadingMargin;
    private javax.swing.JTextField tf_finalDistIS;
    private javax.swing.JTextField tf_finalNumEnb;
    private javax.swing.JTextField tf_finalRadCell;
    private javax.swing.JTextField tf_freqPM1;
    private javax.swing.JTextField tf_freqPM2;
    private javax.swing.JTextField tf_hmPM1;
    private javax.swing.JTextField tf_hmPM2;
    private javax.swing.JTextField tf_hsPM1;
    private javax.swing.JTextField tf_hsPM2;
    private javax.swing.JTextField tf_htBldPM2;
    private javax.swing.JTextField tf_interMargin;
    private javax.swing.JTextField tf_lossBld;
    private javax.swing.JTextField tf_lossBody;
    private javax.swing.JTextField tf_lossCar;
    private javax.swing.JTextField tf_margRoadPM2;
    private javax.swing.JTextField tf_marginBldPM2;
    private javax.swing.JTextField tf_noiseFig;
    private javax.swing.JTextField tf_numPRB;
    private javax.swing.JTextField tf_otherPM;
    private javax.swing.JTextField tf_ueFactorF;
    private javax.swing.JTextField tf_ueGain;
    private javax.swing.JTextField tf_ueJumper;
    private javax.swing.JTextField tf_uePower;
    private javax.swing.JTextField tf_ueSNIR;
    private javax.swing.JTextField tf_ueSysCharge;
    private javax.swing.JTextField tf_ueThermNoise;
    // End of variables declaration                     
    private int next_IDcard = 2;
    private Image icone =  Toolkit.getDefaultToolkit().getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/logo.png"));
    private ImageIcon IconPlanCov =  new ImageIcon(Toolkit.getDefaultToolkit().
			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/plancov.png")));
    private ImageIcon IconHelp =  new ImageIcon(Toolkit.getDefaultToolkit().
			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/help.png")));
    private AbstractHandleEvent planCovHE;
    private OpsimGUI opsimGUI;
    private ArrayList<Observer> listObserver = new ArrayList<Observer>();
    //private String probCov, chanModel;
	/**
	 * 
	 */
	public DialogPlanCov(OpsimGUI parent, boolean modal, boolean show_res) {
		super(parent, modal);
		this.opsimGUI = parent;
		this.addObserver(parent);
        this.initComponents();
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
 		if(projWS.getPlanification().getCovPlanif().getState().equals("DONE"))this.initDialogValues(projWS);
 		
 		if(show_res){
 			System.out.println("PlanCov: Show result");
 			this.planCov_result();
			CardLayout cl = (CardLayout)(this.conGroupCard.getLayout());
			cl.show(this.conGroupCard,"CARD_4");
			this.but_next.setEnabled(false);
            this.but_prev.setEnabled(false);
            this.but_finish.setEnabled(false);
            this.but_reportPlan.setEnabled(false);
            this.but_formPlan.setEnabled(false);
            this.but_defaultPlan.setEnabled(false);
 		}
 			
        this.setVisible(true);
	}
	public void initComponents(){
		this.setIconImage(icone);
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
        conCard_step2 = new javax.swing.JPanel();
        conCard_step1_left1 = new javax.swing.JPanel();
        but_icon2 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        conCard_step1_right1 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        tf_enbCableLoss = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        tf_enbGain = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel32 = new javax.swing.JLabel();
        tf_enbThermNoise = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        tf_enbPower = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        tf_enbSNIR = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        tf_enbSysCharge = new javax.swing.JTextField();
        tf_enbFactorFC = new javax.swing.JTextField();
        tf_enbJumper = new javax.swing.JTextField();
        cbo_siteType = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        tf_uePower = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        tf_ueGain = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel41 = new javax.swing.JLabel();
        tf_ueThermNoise = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        tf_ueSNIR = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        tf_ueSysCharge = new javax.swing.JTextField();
        tf_ueFactorF = new javax.swing.JTextField();
        tf_ueJumper = new javax.swing.JTextField();
        lab_des_lte11 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        panDes_paramGen1 = new javax.swing.JTextPane();
        conCard_step3 = new javax.swing.JPanel();
        conCard_step1_left2 = new javax.swing.JPanel();
        but_icon3 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        conCard_step1_right2 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        tf_interMargin = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        tf_MHAG = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        tf_CCO = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        tf_freqPM1 = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        tf_hmPM1 = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel57 = new javax.swing.JLabel();
        tf_hsPM1 = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        tf_freqPM2 = new javax.swing.JTextField();
        tf_hmPM2 = new javax.swing.JTextField();
        tf_htBldPM2 = new javax.swing.JTextField();
        rad_pmOpt1 = new javax.swing.JRadioButton();
        rad_pmOpt2 = new javax.swing.JRadioButton();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        rad_pmOpt3 = new javax.swing.JRadioButton();
        tf_otherPM = new javax.swing.JTextField();
        but_browOtherPM = new javax.swing.JButton();
        tf_hsPM2 = new javax.swing.JTextField();
        tf_angPM2 = new javax.swing.JTextField();
        tf_margRoadPM2 = new javax.swing.JTextField();
        tf_marginBldPM2 = new javax.swing.JTextField();
        cbo_typeZonePM2 = new javax.swing.JComboBox();
        cbo_typeZonePM1 = new javax.swing.JComboBox();
        conCard_step4 = new javax.swing.JPanel();
        conCard_step1_left3 = new javax.swing.JPanel();
        but_icon4 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        conCard_step1_right3 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        tf_finalRadCell = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        tf_finalNumEnb = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        tf_finalDistIS = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabResCov = new javax.swing.JTable();

        buttonGroup1.add(this.rad_pmOpt1);
        buttonGroup1.add(this.rad_pmOpt2);
        buttonGroup1.add(this.rad_pmOpt3);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Planification en Couverture");
        setPreferredSize(new java.awt.Dimension(785, 528));
        setResizable(false);

        conButton.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        conButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        but_prev.setText("< Précedent");
        but_prev.setToolTipText("Retour en arrière");
        but_prev.setEnabled(false);
        but_prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_prevActionPerformed(evt);
            }
        });

        but_next.setText("Suivant >");
        but_next.setToolTipText("Aller à l'étape suivante");
        but_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_nextActionPerformed(evt);
            }
        });

        but_cancel.setText("Annuler");
        but_cancel.setToolTipText("Annuler l'opération");
        but_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_cancelActionPerformed(evt);
            }
        });

        but_finish.setText("Terminer");
        but_finish.setToolTipText("Finaliser l'opération");
        but_finish.setEnabled(false);

        but_reportPlan.setText("Rapports");
        but_reportPlan.setToolTipText("Générer rapports");
        but_reportPlan.setEnabled(false);

        but_defaultPlan.setText("Par defaut");
        but_defaultPlan.setToolTipText("Charger les paramètres par défaut");
        //but_defaultPlan.setEnabled(false);

        but_formPlan.setText("Formules");
        but_formPlan.setToolTipText("Voir les formules");
        but_formPlan.setEnabled(false);

        javax.swing.GroupLayout conButtonLayout = new javax.swing.GroupLayout(conButton);
        conButton.setLayout(conButtonLayout);
        conButtonLayout.setHorizontalGroup(
            conButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, conButtonLayout.createSequentialGroup()
                .addContainerGap(104, Short.MAX_VALUE)
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

        but_icon1.setIcon(IconPlanCov); // NOI18N
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 211, Short.MAX_VALUE)
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
        tf_anType.setText("SIMO1x2");

        but_browAnType.setText("Charger ...");

        jLabel10.setText("Probabilité de couverture:");

        cbo_probaCov.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "98%", "96%", "94%", "92%", "90%", "88%", "86%" }));
        cbo_probaCov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_probaCovActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout conCard_step1_rightLayout = new javax.swing.GroupLayout(conCard_step1_right);
        conCard_step1_right.setLayout(conCard_step1_rightLayout);
        conCard_step1_rightLayout.setHorizontalGroup(
            conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lab_des_lte10)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                        .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(tf_FB, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(but_browFB, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                        .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(tf_anType, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(but_browAnType, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                                        .addComponent(tf_numPRB, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tf_debitDL, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                                        .addComponent(cbo_probaCov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                                        .addComponent(cbo_chanModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14))
                                .addGap(18, 18, 18)
                                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_lossBody, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_lossBld, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_lossCar, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                                        .addComponent(tf_noiseFig, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, conCard_step1_rightLayout.createSequentialGroup()
                                .addComponent(jLabel67)
                                .addGap(18, 18, 18)
                                .addComponent(tf_fadingMargin, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(148, 148, 148)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        conCard_step1_rightLayout.setVerticalGroup(
            conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                            .addComponent(but_browAnType))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(cbo_probaCov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(cbo_chanModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        conCard_step1.add(conCard_step1_right, java.awt.BorderLayout.CENTER);

        conGroupCard.add(conCard_step1, "CARD_1");

        conCard_step2.setPreferredSize(new java.awt.Dimension(790, 474));
        conCard_step2.setLayout(new java.awt.BorderLayout());

        conCard_step1_left1.setBackground(new java.awt.Color(255, 255, 255));
        conCard_step1_left1.setPreferredSize(new java.awt.Dimension(200, 455));

        but_icon2.setIcon(IconPlanCov); // NOI18N
        but_icon2.setBorderPainted(false);
        but_icon2.setContentAreaFilled(false);
        but_icon2.setFocusPainted(false);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Etapes:");

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        jLabel17.setText("1.   Paramètres généraux");

        jLabel18.setText("2.   Paramètres du bilan de liaison");

        jLabel20.setText("3.    ...");

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
                        .addComponent(jLabel20)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
                .addComponent(but_icon2)
                .addGap(46, 46, 46))
        );

        conCard_step2.add(conCard_step1_left1, java.awt.BorderLayout.LINE_START);

        conCard_step1_right1.setPreferredSize(new java.awt.Dimension(647, 504));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Définisser les paramètres du bilan de liaison:");

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Enode-B"));

        jLabel30.setText("Pertes des câbles (dB):");

        jLabel31.setText("Gain (dB):");

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel32.setText("Bruit Thermique par PRB (dBm):");

        jLabel33.setText("Puissance de l'antenne (dBm):");

        jLabel34.setText("SNIR au borde de la cellule (dBm):");

        jLabel35.setText("Charge du système:");

        jLabel36.setText("Facture FC:");

        jLabel37.setText("Jumper et TMA:");

        jLabel38.setText("Type de site:");
        
        cbo_siteType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mono-sectorise", "Bi-sectorie", "Tri-sectorise" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel33)
                        .addGap(18, 18, 18)
                        .addComponent(tf_enbPower, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel31))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_enbGain, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tf_enbThermNoise)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel30)
                        .addGap(18, 18, 18)
                        .addComponent(tf_enbCableLoss))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addGap(17, 17, 17)
                        .addComponent(tf_enbSNIR, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel38)
                    .addComponent(jLabel36)
                    .addComponent(jLabel35)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_enbSysCharge)
                    .addComponent(tf_enbFactorFC, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tf_enbJumper)
                    .addComponent(cbo_siteType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_enbCableLoss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_enbGain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_enbThermNoise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_enbPower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_enbSNIR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(tf_enbSysCharge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(tf_enbFactorFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(tf_enbJumper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(cbo_siteType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "UE"));

        jLabel39.setText("Puissance (dBm):");

        jLabel40.setText("Gain (dB):");


        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel41.setText("Bruit Thermique par PRB (dBm):");

        jLabel42.setText("SNIR cible (dBm):");


        jLabel44.setText("Charge du système:");

        jLabel45.setText("Facture F:");

        jLabel46.setText("Jumper et TMA:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel41)
                            .addComponent(jLabel40)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_ueSNIR)
                    .addComponent(tf_uePower)
                    .addComponent(tf_ueGain, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tf_ueThermNoise))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel45)
                    .addComponent(jLabel44)
                    .addComponent(jLabel46))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_ueSysCharge)
                    .addComponent(tf_ueFactorF, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tf_ueJumper))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_uePower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_ueGain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_ueThermNoise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_ueSNIR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44)
                            .addComponent(tf_ueSysCharge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel45)
                            .addComponent(tf_ueFactorF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(tf_ueJumper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        lab_des_lte11.setText("Description:");

        panDes_paramGen1.setEditable(false);
        panDes_paramGen1.setText("Le bilan de liaison permet de déterminer l’affaiblissement maximal alloué MAPL (Maximum Allowable Pathloss).\nCette valeur (MAPL) sera utilisée par le modèle de propagation pour déterminer le rayon de la cellule. Et sachant \nla taille de la cellule, on pourra donc déterminer pour la zone d'étude le nombre de sites nécessaires.");
        jScrollPane5.setViewportView(panDes_paramGen1);

        javax.swing.GroupLayout conCard_step1_right1Layout = new javax.swing.GroupLayout(conCard_step1_right1);
        conCard_step1_right1.setLayout(conCard_step1_right1Layout);
        conCard_step1_right1Layout.setHorizontalGroup(
            conCard_step1_right1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conCard_step1_right1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(conCard_step1_right1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lab_des_lte11)
                    .addGroup(conCard_step1_right1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        conCard_step1_right1Layout.setVerticalGroup(
            conCard_step1_right1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conCard_step1_right1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lab_des_lte11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        conCard_step2.add(conCard_step1_right1, java.awt.BorderLayout.CENTER);

        conGroupCard.add(conCard_step2, "CARD_2");

        conCard_step3.setPreferredSize(new java.awt.Dimension(790, 474));
        conCard_step3.setLayout(new java.awt.BorderLayout());

        conCard_step1_left2.setBackground(new java.awt.Color(255, 255, 255));
        conCard_step1_left2.setPreferredSize(new java.awt.Dimension(200, 455));

        but_icon3.setIcon(IconPlanCov); // NOI18N
        but_icon3.setBorderPainted(false);
        but_icon3.setContentAreaFilled(false);
        but_icon3.setFocusPainted(false);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Etapes:");

        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));

        jLabel22.setText("1.   Paramètres généraux");

        jLabel23.setText("2.   Paramètres du bilan de liaison");

        jLabel24.setText("3.   Paramètres de l'environnement");

        jLabel26.setText("4.    ...");

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
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(jLabel24))
                    .addGroup(conCard_step1_left2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel26)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        conCard_step1_left2Layout.setVerticalGroup(
            conCard_step1_left2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, conCard_step1_left2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 171, Short.MAX_VALUE)
                .addComponent(but_icon3)
                .addGap(46, 46, 46))
        );

        conCard_step3.add(conCard_step1_left2, java.awt.BorderLayout.LINE_START);

        conCard_step1_right2.setPreferredSize(new java.awt.Dimension(647, 504));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Définisser les paramètres de l'environnement:");

        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Milieu de propagation"));

        jLabel43.setText("Marge d'interférence (dBm):");

        jLabel47.setText("Mast head amplifier gain (dB):");

        jLabel48.setText("Control channel overheard (dB):");


        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel43)
                    .addComponent(jLabel48)
                    .addComponent(jLabel47))
                .addGap(43, 43, 43)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tf_MHAG, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                    .addComponent(tf_interMargin)
                    .addComponent(tf_CCO))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_interMargin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_MHAG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_CCO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Modèle de propagation"));

        jLabel55.setText("Fréquence (Mhz):");



        jLabel56.setText("Hauteur du mobile (m):");



        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel57.setText("Hauteur de la station (m):");


        jLabel58.setText("Nature de la zone:");

        jLabel59.setText("Fréquence (Mhz):");

        jLabel60.setText("Hauteur du mobile (m):");

        jLabel61.setText("Hauteur toit batiment (m):");

        tf_freqPM2.setEnabled(false);
   

        tf_hmPM2.setEnabled(false);
    

        tf_htBldPM2.setEnabled(false);
      

        rad_pmOpt1.setSelected(true);
        rad_pmOpt1.setText("Okumura-Hata");
        rad_pmOpt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rad_pmOpt1ActionPerformed(evt);
            }
        });

        rad_pmOpt2.setText("Cost 231 Walfish-Ikegami");
        rad_pmOpt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rad_pmOpt2ActionPerformed(evt);
            }
        });

        jLabel62.setText("Hauteur de la station (m):");

        jLabel63.setText("Angle d'incidence (°):");

        jLabel64.setText("Séparation batiment (m):");

        jLabel65.setText("Largeur de la rue (m):");

        jLabel66.setText("Natures des sites:");

        rad_pmOpt3.setText("Autres modèles propagation");
        rad_pmOpt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rad_pmOpt3ActionPerformed(evt);
            }
        });

        tf_otherPM.setEditable(false);
     

        but_browOtherPM.setText("Charger ...");
        but_browOtherPM.setToolTipText("Charger d'autres modèles de propagation");
        but_browOtherPM.setEnabled(false);

        tf_hsPM2.setEnabled(false);


        tf_angPM2.setEnabled(false);


        tf_margRoadPM2.setEnabled(false);
   

        tf_marginBldPM2.setEnabled(false);


        cbo_typeZonePM2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dense urbain", "Urbain", "Sous urbain" }));
        cbo_typeZonePM2.setEnabled(false);

        cbo_typeZonePM1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Urbain dense", "Urbain", "Sous urbain", "Rural" }));
        cbo_typeZonePM1.setEnabled(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel57)
                                .addComponent(jLabel56))
                            .addComponent(jLabel55, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel58, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tf_hmPM1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_freqPM1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_hsPM1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbo_typeZonePM1, javax.swing.GroupLayout.Alignment.LEADING, 0, 124, Short.MAX_VALUE)))
                    .addComponent(rad_pmOpt1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(tf_otherPM))
                            .addComponent(rad_pmOpt3, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(but_browOtherPM, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rad_pmOpt2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel60)
                                    .addComponent(jLabel59)
                                    .addComponent(jLabel61)
                                    .addComponent(jLabel62))
                                .addComponent(jLabel63, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel64)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel66)
                                        .addComponent(jLabel65)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tf_margRoadPM2)
                            .addComponent(tf_marginBldPM2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_angPM2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_hsPM2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_htBldPM2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_hmPM2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_freqPM2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbo_typeZonePM2, javax.swing.GroupLayout.Alignment.LEADING, 0, 70, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(rad_pmOpt2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel59)
                                    .addComponent(tf_freqPM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel60)
                                    .addComponent(tf_hmPM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel61)
                                    .addComponent(tf_htBldPM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tf_hsPM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel62))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tf_angPM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel63))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tf_marginBldPM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel64))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tf_margRoadPM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel65))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel66)
                                    .addComponent(cbo_typeZonePM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(rad_pmOpt1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_freqPM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_hmPM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_hsPM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbo_typeZonePM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addComponent(rad_pmOpt3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_otherPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(but_browOtherPM))))
                .addContainerGap())
        );

        javax.swing.GroupLayout conCard_step1_right2Layout = new javax.swing.GroupLayout(conCard_step1_right2);
        conCard_step1_right2.setLayout(conCard_step1_right2Layout);
        conCard_step1_right2Layout.setHorizontalGroup(
            conCard_step1_right2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conCard_step1_right2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(conCard_step1_right2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel25)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator9, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        conCard_step1_right2Layout.setVerticalGroup(
            conCard_step1_right2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conCard_step1_right2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel3.getAccessibleContext().setAccessibleName("");

        conCard_step3.add(conCard_step1_right2, java.awt.BorderLayout.CENTER);

        conGroupCard.add(conCard_step3, "CARD_3");

        conCard_step4.setPreferredSize(new java.awt.Dimension(790, 474));
        conCard_step4.setLayout(new java.awt.BorderLayout());

        conCard_step1_left3.setBackground(new java.awt.Color(255, 255, 255));
        conCard_step1_left3.setPreferredSize(new java.awt.Dimension(200, 455));

        but_icon4.setIcon(IconPlanCov); // NOI18N
        but_icon4.setBorderPainted(false);
        but_icon4.setContentAreaFilled(false);
        but_icon4.setFocusPainted(false);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Etapes:");

        jSeparator10.setForeground(new java.awt.Color(0, 0, 0));

        jLabel28.setText("1.   Paramètres généraux");

        jLabel29.setText("2.   Paramètres du bilan de liaison");

        jLabel49.setText("3.   Paramètres de l'environnement");

        jLabel50.setText("4.   Résultats");

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
                        .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(jLabel49))
                    .addGroup(conCard_step1_left3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel50)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        conCard_step1_left3Layout.setVerticalGroup(
            conCard_step1_left3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, conCard_step1_left3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 171, Short.MAX_VALUE)
                .addComponent(but_icon4)
                .addGap(46, 46, 46))
        );

        conCard_step4.add(conCard_step1_left3, java.awt.BorderLayout.LINE_START);

        conCard_step1_right3.setPreferredSize(new java.awt.Dimension(647, 504));

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel51.setText("Résultats de la planification orientée couverture:");

        jSeparator12.setForeground(new java.awt.Color(0, 0, 0));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Bilan"));

        jLabel52.setText("Rayon de la cellule finale (km):");

        tf_finalRadCell.setEditable(false);
        tf_finalRadCell.setBackground(new java.awt.Color(255, 255, 255)); 

        jLabel53.setText("Nombre d'Enode-B final:");

        tf_finalNumEnb.setEditable(false);
        tf_finalNumEnb.setBackground(new java.awt.Color(255, 255, 255));   

        jLabel54.setText("Distance inter-sites finales (km):");

        tf_finalDistIS.setEditable(false);
        tf_finalDistIS.setBackground(new java.awt.Color(255, 255, 255));
     

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel52)
                    .addComponent(jLabel54)
                    .addComponent(jLabel53))
                .addGap(43, 43, 43)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tf_finalNumEnb, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                    .addComponent(tf_finalRadCell)
                    .addComponent(tf_finalDistIS))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_finalRadCell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_finalNumEnb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_finalDistIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabResCov.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {" PIRE (dBm)", null, null},
                {" Sensibilié (dBm)", null, null},
                {" Bruit termique (dB)", null, null},
                {" Path Loss (dBm)", null, null},
                {" Rayon d'une cellule (km)", null, null},
                {" Empreinte d'une cellule (km²)", null, null},
                {" Empreinte d'un site (km²)", null, null},
                {" Nombre d'Enode-B", null, null},
                {"Distance inter-sites finale (km)", null, null}
            },
            new String [] {
                "Données", "UL", "DL"
            }        )
        {
			public boolean isCellEditable(int row, int column) {
                return false;
             }
		});
        jScrollPane1.setViewportView(tabResCov);

        javax.swing.GroupLayout conCard_step1_right3Layout = new javax.swing.GroupLayout(conCard_step1_right3);
        conCard_step1_right3.setLayout(conCard_step1_right3Layout);
        conCard_step1_right3Layout.setHorizontalGroup(
            conCard_step1_right3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conCard_step1_right3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(conCard_step1_right3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(conCard_step1_right3Layout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addGap(183, 183, 183))
                    .addComponent(jSeparator12)
                    .addGroup(conCard_step1_right3Layout.createSequentialGroup()
                        .addGroup(conCard_step1_right3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 19, Short.MAX_VALUE))))
        );
        conCard_step1_right3Layout.setVerticalGroup(
            conCard_step1_right3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conCard_step1_right3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );

        conCard_step4.add(conCard_step1_right3, java.awt.BorderLayout.CENTER);

        conGroupCard.add(conCard_step4, "CARD_4");

        getContentPane().add(conGroupCard, java.awt.BorderLayout.CENTER);

        pack();
	}
	
	public void initEvents(){
		this.planCovHE = new PlanningCovHE(this);
		
		but_browOtherPM.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            //but_browOtherPMActionPerformed(evt);
	        }
	    });
		
		rad_pmOpt3.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            rad_pmOpt3ActionPerformed(evt);
	        }
	    });
		
		 rad_pmOpt2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                rad_pmOpt2ActionPerformed(evt);
	            }
	        });
		 
	 rad_pmOpt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rad_pmOpt1ActionPerformed(evt);
            }
        });
		 
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
       
       this.cbo_probaCov.addActionListener(new java.awt.event.ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt) {
				cbo_probaCovActionPerformed(evt);
			}	    	   
       });
       
       this.but_defaultPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	but_defaultPlanActionPerformed(evt);
            }
        });

	}
	
	private void but_finishActionPerformed(java.awt.event.ActionEvent evt) {
		DAO<Project> projDAO = DAOFactory.getProjectDAO();
		ProjectController projConWS = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
		Project projWS = (Project) projConWS.getModel();
		Project projDBB = projDAO.find(projWS.getId());
		
		RPlanCoverage resCov_WS = projWS.getPlanification().getResult().getResCov();
		RPlanCoverage resCov_DBB = projDBB.getPlanification().getResult().getResCov();
		
		double cell_rad_ws = Math.max(resCov_WS.getCellRadiusUL()*1000, resCov_WS.getCellRadiusDL()*1000);
		double cell_rad_dbb = Math.max(resCov_DBB.getCellRadiusUL()*1000, resCov_DBB.getCellRadiusDL()*1000);
		
		System.out.println("cell_rad_ws: "+cell_rad_ws);
		System.out.println("cell_rad_dbb: "+cell_rad_dbb);
		
 		if(cell_rad_ws != cell_rad_dbb)
 			this.opsimGUI.createProjectFC(projWS);
		
		projWS.getPlanification().getCovPlanif().setState("DONE");
		double progress = projWS.getProgress();
		if(progress < 15.0)projWS.setProgress(15.0);		
		projWS = projDAO.update(projWS);
		projConWS.setModel(projWS);
		this.opsimGUI.getListControler().remove("ProjectController");
		this.opsimGUI.getListControler().put("ProjectController", projConWS);
		this.setVisible(false);
		
	}
	
	private void but_defaultPlanActionPerformed(java.awt.event.ActionEvent evt) {
		switch(this.next_IDcard-1){
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
	
	private void cbo_probaCovActionPerformed(java.awt.event.ActionEvent evt) {
		//System.out.println("ProbCov selected : "+cbo_probaCov.getSelectedItem());
	}
	
	private void but_browAnTypeActionPerformed(java.awt.event.ActionEvent evt) {   
		DialogAntenType dialog = new DialogAntenType(this,true,this,this.opsimGUI);
	}
	
	private void but_browFBActionPerformed(java.awt.event.ActionEvent evt) {   
		DialogFreqBand dialog = new DialogFreqBand(this,true,this,this.opsimGUI);
	}
	
	private void but_nextActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
        CardLayout cl = (CardLayout)(this.conGroupCard.getLayout());
        if(this.next_IDcard == 2){
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
	            this.next_IDcard++;
        	}
        	else JOptionPane.showMessageDialog(this, 
        			"Veuillez définir tous les paramètres généraux \n" +
        			"avant d'aller à l'étape suivante.",
        			"Planification en couverture: Erreur",JOptionPane.ERROR_MESSAGE);
        }
        else if(this.next_IDcard == 3){
        	if(!this.tf_enbCableLoss.getText().isEmpty() && !this.tf_enbGain.getText().isEmpty() && !this.tf_enbThermNoise.getText().isEmpty() &&
    		!this.tf_enbPower.getText().isEmpty() && !this.tf_enbSNIR.getText().isEmpty() && !this.tf_enbSysCharge.getText().isEmpty() &&
    		!this.tf_enbFactorFC.getText().isEmpty() && !this.tf_enbJumper.getText().isEmpty() && !this.tf_uePower.getText().isEmpty() &&
    		!this.tf_ueGain.getText().isEmpty() && !this.tf_ueThermNoise.getText().isEmpty() && !this.tf_ueSNIR.getText().isEmpty() &&
    		!this.tf_ueSysCharge.getText().isEmpty() && !this.tf_ueFactorF.getText().isEmpty() && !this.tf_ueJumper.getText().isEmpty()){        		
        		cl.show(this.conGroupCard,"CARD_3");
	            this.but_next.setEnabled(true);
	            this.but_prev.setEnabled(true);
	            this.but_finish.setEnabled(false);
	            this.but_reportPlan.setEnabled(false);
	            this.but_formPlan.setEnabled(false);
	            this.but_defaultPlan.setEnabled(true);
	            this.planCov_step2();
	            this.next_IDcard++;
	            
        	}
        	else JOptionPane.showMessageDialog(this, 
        			"Veuillez définir tous les paramètres du bilan de liaison \n" +
        			"avant d'aller à l'étape suivante.",
        			"Planification en couverture: Erreur",JOptionPane.ERROR_MESSAGE);
        }
        else if(this.next_IDcard == 4){
        	if(!this.tf_interMargin.getText().isEmpty() && !this.tf_MHAG.getText().isEmpty() && !this.tf_CCO.getText().isEmpty()){
        		if(this.rad_pmOpt1.isSelected() && !this.tf_hmPM1.getText().isEmpty() &&
        				!this.tf_hsPM1.getText().isEmpty() && !this.tf_freqPM1.getText().isEmpty()){
        				this.next_IDcard++;
        			 	cl.show(this.conGroupCard,"CARD_4");
        	         	this.but_next.setEnabled(false);
        	         	this.but_prev.setEnabled(true);
        	            this.but_finish.setEnabled(true);
        	            this.but_reportPlan.setEnabled(true);
        	            this.but_formPlan.setEnabled(true);
        	            this.but_defaultPlan.setEnabled(false);
        	            this.planCov_step3();
        	            this.planCov_result();
        		}
        		else if(this.rad_pmOpt2.isSelected() && !this.tf_hmPM2.getText().isEmpty() &&
        				!this.tf_hsPM2.getText().isEmpty() && !this.tf_freqPM2.getText().isEmpty() && 
        				!this.tf_htBldPM2.getText().isEmpty() && !this.tf_angPM2.getText().isEmpty() &&
        				!this.tf_marginBldPM2.getText().isEmpty() && !this.tf_margRoadPM2.getText().isEmpty()){
        			 	cl.show(this.conGroupCard,"CARD_4");
        	            this.but_next.setEnabled(false);
        	            this.but_prev.setEnabled(true);
        	            this.but_finish.setEnabled(true);
        	            this.but_reportPlan.setEnabled(true);
        	            this.but_formPlan.setEnabled(true);
        	            this.but_defaultPlan.setEnabled(false);
        	            this.planCov_step3();
        	            this.planCov_result();
        		}
        		else JOptionPane.showMessageDialog(this, 
            			"Veuillez définir tous les paramètres du modèle de propagation \n" +
            			"avant d'aller à l'étape suivante.",
            			"Planification en couverture: Erreur",JOptionPane.ERROR_MESSAGE);
        		
        	}
        	else JOptionPane.showMessageDialog(this, 
        			"Veuillez définir tous les paramètres de l'environnement \n" +
        			"avant d'aller à l'étape suivante.",
        			"Planification en couverture: Erreur",JOptionPane.ERROR_MESSAGE);           
        }
        else {
        	this.next_IDcard = 2;
            cl.show(this.conGroupCard,"CARD_1");
            this.but_next.setEnabled(true);
            this.but_prev.setEnabled(true);
            this.but_finish.setEnabled(false);
            this.but_reportPlan.setEnabled(false);
            this.but_formPlan.setEnabled(false);
            this.but_defaultPlan.setEnabled(true);
        }     
        
    }
	
	private void but_prevActionPerformed(java.awt.event.ActionEvent evt) {                                         
        this.next_IDcard--;
        CardLayout cl = (CardLayout)(this.conGroupCard.getLayout());
        if(this.next_IDcard == 2){
            cl.show(this.conGroupCard,"CARD_1");
            this.but_next.setEnabled(true);
            this.but_prev.setEnabled(false);
            this.but_finish.setEnabled(false);
            this.but_reportPlan.setEnabled(false);
            this.but_formPlan.setEnabled(false);
            this.but_defaultPlan.setEnabled(true);
        }
        else if(this.next_IDcard == 3){
            cl.show(this.conGroupCard,"CARD_2");
            this.but_next.setEnabled(true);
            this.but_prev.setEnabled(true);
            this.but_finish.setEnabled(false);
            this.but_reportPlan.setEnabled(false);
            this.but_formPlan.setEnabled(false);
            this.but_defaultPlan.setEnabled(true);
        }
        else if(this.next_IDcard == 4){
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
	
	private void rad_pmOpt1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        this.tf_freqPM1.setEnabled(true);
        this.tf_hmPM1.setEnabled(true);
        this.tf_hsPM1.setEnabled(true);
        this.cbo_typeZonePM1.setEnabled(true);
        this.tf_freqPM2.setEnabled(false);
        this.tf_hmPM2.setEnabled(false);
        this.tf_hsPM2.setEnabled(false);
        this.tf_htBldPM2.setEnabled(false);
        this.tf_angPM2.setEnabled(false);
        this.tf_margRoadPM2.setEnabled(false);
        this.tf_marginBldPM2.setEnabled(false);
        this.cbo_typeZonePM2.setEnabled(false);
        this.but_browOtherPM.setEnabled(false);
    }
	
	private void rad_pmOpt3ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        this.tf_freqPM1.setEnabled(false);
        this.tf_hmPM1.setEnabled(false);
        this.tf_hsPM1.setEnabled(false);
        this.cbo_typeZonePM1.setEnabled(false);
        this.tf_freqPM2.setEnabled(false);
        this.tf_hmPM2.setEnabled(false);
        this.tf_hsPM2.setEnabled(false);
        this.tf_htBldPM2.setEnabled(false);
        this.tf_angPM2.setEnabled(false);
        this.tf_margRoadPM2.setEnabled(false);
        this.tf_marginBldPM2.setEnabled(false);
        this.cbo_typeZonePM2.setEnabled(false);
        this.but_browOtherPM.setEnabled(true);
    } 
	
	private void rad_pmOpt2ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        this.tf_freqPM1.setEnabled(false);
        this.tf_hmPM1.setEnabled(false);
        this.tf_hsPM1.setEnabled(false);
        this.cbo_typeZonePM1.setEnabled(false);
        this.tf_freqPM2.setEnabled(true);
        this.tf_hmPM2.setEnabled(true);
        this.tf_hsPM2.setEnabled(true);
        this.tf_htBldPM2.setEnabled(true);
        this.tf_angPM2.setEnabled(true);
        this.tf_margRoadPM2.setEnabled(true);
        this.tf_marginBldPM2.setEnabled(true);
        this.cbo_typeZonePM2.setEnabled(true);
        this.but_browOtherPM.setEnabled(false);
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
		
		genParam.setBodyLoss(Double.parseDouble(this.tf_lossBody.getText()));
		genParam.setBuildingLoss(Double.parseDouble(this.tf_lossBld.getText()));
		genParam.setCarLoss(Double.parseDouble(this.tf_lossCar.getText()));
		genParam.setFigureNoise(Double.parseDouble(this.tf_noiseFig.getText()));
		genParam.setFadingMargin(Double.parseDouble(this.tf_fadingMargin.getText()));
		
		projWS.setFreqBand(freqBand);
		projWS.getParameter().getAntParam().setAntennaType(antenType);
		projWS.getParameter().getOtherParam().setMimoType("MIMO1x1");
		projWS.getParameter().setGenParam(genParam);
		
		projWS.setUpdatedDate(new Date());
		projConWS.setModel(projWS);
		this.opsimGUI.getListControler().remove("ProjectController");
		this.opsimGUI.getListControler().put("ProjectController", projConWS);
	}
	public void default_step1(){
		System.out.println("default 1");
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
		this.tf_lossBody.setText("3");
		this.tf_lossBld.setText("18");
		this.tf_lossCar.setText("0");
		this.tf_noiseFig.setText("2.5");
		this.tf_fadingMargin.setText("4.2");
	}
	
	public void planCov_step2(){
		ProjectController projConWS = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
		Project projWS = (Project) projConWS.getModel();		
		EnbParameter enbParam = projWS.getParameter().getEnbParam();
		UeParameter ueParam = projWS.getParameter().getUeParam();
		
		enbParam.setCableLoss(Double.parseDouble(this.tf_enbCableLoss.getText()));
		enbParam.setAntennaGain(Double.parseDouble(this.tf_enbGain.getText()));
		enbParam.setThermicNoiseByRB(Double.parseDouble(this.tf_enbThermNoise.getText()));
		enbParam.setPowerTx(Double.parseDouble(this.tf_enbPower.getText()));
		enbParam.setCellBoardSNIR(Double.parseDouble(this.tf_enbSNIR.getText()));
		enbParam.setSystemCharge(Double.parseDouble(this.tf_enbSysCharge.getText()));
		enbParam.setFactorFC(Double.parseDouble(this.tf_enbFactorFC.getText()));
		enbParam.setJumperTmaLoss(Double.parseDouble(this.tf_enbJumper.getText()));
		enbParam.setSiteType(this.cbo_siteType.getSelectedItem().toString());
		
		ueParam.setPowerUE(Double.parseDouble(this.tf_uePower.getText()));
		ueParam.setAntennaGain(Double.parseDouble(this.tf_ueGain.getText()));
		ueParam.setThermicNoiseByRB(Double.parseDouble(this.tf_ueThermNoise.getText()));
		ueParam.setTargetSNIR(Double.parseDouble(this.tf_ueSNIR.getText()));
		ueParam.setSystCharge(Double.parseDouble(this.tf_ueSysCharge.getText()));
		ueParam.setFactorF(Double.parseDouble(this.tf_ueFactorF.getText()));
		ueParam.setJumperTmaLoss(Double.parseDouble(this.tf_ueJumper.getText()));
		
		projWS.getParameter().setEnbParam(enbParam);
		projWS.getParameter().setUeParam(ueParam);
		
		projWS.setUpdatedDate(new Date());
		projConWS.setModel(projWS);
		this.opsimGUI.getListControler().remove("ProjectController");
		this.opsimGUI.getListControler().put("ProjectController", projConWS);
	}
	public void default_step2(){
		this.tf_enbCableLoss.setText("2");
		this.tf_enbGain.setText("18.5");
		this.tf_enbThermNoise.setText("-114.4");
		this.tf_enbPower.setText("46.02");
		this.tf_enbSNIR.setText("-5.32");
		this.tf_enbSysCharge.setText("0.35");
		this.tf_enbFactorFC.setText("2.3");
		this.tf_enbJumper.setText("0");
		this.cbo_siteType.setSelectedIndex(0);
		
		this.tf_uePower.setText("23");
		this.tf_ueGain.setText("18.5");
		this.tf_ueThermNoise.setText("-118.9");
		this.tf_ueSNIR.setText("1.06");
		this.tf_ueSysCharge.setText("0.32");
		this.tf_ueFactorF.setText("0.7");
		this.tf_ueJumper.setText("0");
	}
	
	public void planCov_step3(){
		ProjectController projConWS = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
		Project projWS = (Project) projConWS.getModel();
		EnvParameter envParam = projWS.getParameter().getEnvParam();
		PmParameter pmParam = envParam.getPmParameter();
		
		envParam.setMarginInterf(Double.parseDouble(this.tf_interMargin.getText()));
		envParam.setMhag(Double.parseDouble(this.tf_MHAG.getText()));
		envParam.setCco(Double.parseDouble(this.tf_CCO.getText()));
		if(this.rad_pmOpt1.isSelected()){
			// PM: okumura
			OkumuraHata oku = null;
			oku = (OkumuraHata) pmParam.getPm();
			if(oku == null)oku = new OkumuraHata();
			oku.setFrequency(Double.parseDouble(this.tf_freqPM1.getText()));
			oku.setHeightUE(Double.parseDouble(this.tf_hmPM1.getText()));
			oku.setHeightENB(Double.parseDouble(this.tf_hsPM1.getText()));
			oku.setZoneType(this.cbo_typeZonePM1.getSelectedItem().toString());
			pmParam.setPm(oku);
		}
		else if(this.rad_pmOpt2.isSelected()){
			// PM: Cost231
			Cost231Hata c231 = null;
			c231 = (Cost231Hata) pmParam.getPm();
			if(c231 == null)c231 = new Cost231Hata();
			c231.setFrequency(Double.parseDouble(this.tf_freqPM2.getText()));
			c231.setHeightUE(Double.parseDouble(this.tf_hmPM2.getText()));
			c231.setHeightRoof(Double.parseDouble(this.tf_htBldPM2.getText()));
			c231.setHeightENB(Double.parseDouble(this.tf_hsPM2.getText()));
			c231.setIncidenceAngle(Double.parseDouble(this.tf_angPM2.getText()));
			c231.setBldSeparation(Double.parseDouble(this.tf_marginBldPM2.getText()));
			c231.setWidthString(Double.parseDouble(this.tf_margRoadPM2.getText()));
			c231.setZoneType(this.cbo_typeZonePM2.getSelectedItem().toString());
			pmParam.setPm(c231);
		}
		else{
			// PM: other
			OkumuraHata oku = new OkumuraHata();
			pmParam.setPm(oku);
		}
		envParam.setPmParameter(pmParam);		
		projWS.getParameter().setEnvParam(envParam);
		
		projWS.setUpdatedDate(new Date());
		projConWS.setModel(projWS);
		this.opsimGUI.getListControler().remove("ProjectController");
		this.opsimGUI.getListControler().put("ProjectController", projConWS);		
	}
	public void default_step3(){
		this.tf_interMargin.setText("12");
		this.tf_MHAG.setText("0");
		this.tf_CCO.setText("0");
		this.rad_pmOpt1.setSelected(true);
		this.rad_pmOpt2.setSelected(false);
		this.rad_pmOpt3.setSelected(false);
		this.tf_freqPM1.setText("150");
		this.tf_hmPM1.setText("1.5");
		this.tf_hsPM1.setText("60");
		this.cbo_typeZonePM1.setSelectedIndex(0);
	}
	
	public void planCov_result(){		
		ProjectController projConWS = (ProjectController) this.opsimGUI.getListControler().get("ProjectController");
		Project projWS = (Project) projConWS.getModel();
		PlanCovController planCovConWS = (PlanCovController) this.opsimGUI.getListControler().get("PlanCovController");
		CoveragePlanif covPlanifWS = projWS.getPlanification().getCovPlanif();
		planCovConWS.setModel(covPlanifWS);
		RPlanCoverage resCov = planCovConWS.calResult(projWS).getResCov();	
		
		tabResCov.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {
	                {" PIRE (dBm)", round(resCov.getPireUl(),3), round(resCov.getPireDl(),3)},
	                {" Sensibilié (dBm)", round(resCov.getSensibilityUL(),3), round(resCov.getSensibilityDL(),3)},
	                {" Bruit termique (dB)", round(resCov.getThermicNoiseUL(),3), round(resCov.getThermicNoiseDL(),3)},
	                {" Path Loss (dBm)", round(resCov.getPathLossUL(),3), round(resCov.getPathLossDL(),3)},
	                {" Rayon d'une cellule (km)", round(resCov.getCellRadiusUL(),3), round(resCov.getCellRadiusDL(),3)},
	                {" Empreinte d'une cellule (km²)", round(resCov.getCellPrintUL(),3), round(resCov.getCellPrintDL(),3)},
	                {" Empreinte d'un site (km²)", round(resCov.getSitePrintUL(),3), round(resCov.getSitePrintDL(),3)},
	                {" Nombre d'Enode-B", resCov.getNumENBUl(), resCov.getNumENBDl()},
	                {"Distance inter-sites finale (km)", round(resCov.getInterSiteDistUL(),3), round(resCov.getInterSiteDistDL(),3)}
	            },
	            new String [] {
	                "Données", "UL", "DL"
	            }	            
	        ){
				public boolean isCellEditable(int row, int column) {
	                return false;
	             }
			}
		);
		jScrollPane1.setViewportView(tabResCov);
		jScrollPane1.repaint();		
		this.tf_finalRadCell.setText(Double.toString(Math.max(round(resCov.getCellRadiusUL(),3),round(resCov.getCellRadiusDL(),3))));
		this.tf_finalNumEnb.setText(Integer.toString(Math.max(resCov.getNumENBUl(),resCov.getNumENBDl())));	
		this.tf_finalDistIS.setText(Double.toString(Math.max(round(resCov.getInterSiteDistUL(),3),round(resCov.getInterSiteDistDL(),3))));
		
		projWS.getPlanification().getResult().setResCov(resCov);
		projConWS.setModel(projWS);
		this.opsimGUI.getListControler().remove("ProjectController");
		this.opsimGUI.getListControler().put("ProjectController", projConWS);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DialogPlanCov dialog = new DialogPlanCov(null, true, false);

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
	
	public void initDialogValues(Project proj){
		this.initStep1(proj);
		this.initStep2(proj);
		this.initStep3(proj);
		this.initResut(proj);
	}
	public void initStep1(Project proj){
		System.out.println("Dialog: initStep1");
		FreqBand freqBand = proj.getFreqBand();
		AntennaType antenType = proj.getParameter().getAntParam().getAntennaType();
		GenParameter genParam = proj.getParameter().getGenParam();
		
		this.tf_FB.setText(freqBand.getName());
		this.tf_debitUL.setText(Double.toString(genParam.getBwCellBoardUL()));
		this.tf_debitDL.setText(Double.toString(genParam.getBwCellBoardDL()));
		this.tf_numPRB.setText(Integer.toString(genParam.getNumPRBUl()));
		this.tf_anType.setText(antenType.getName());
		
		//System.out.println("Dialog: probaCov = "+(int)genParam.getCoverageProba()+"%");
		this.cbo_probaCov.setSelectedItem((int)genParam.getCoverageProba()+"%");
		this.cbo_chanModel.setSelectedItem(genParam.getChannelModel());
		this.tf_lossBody.setText(Double.toString(genParam.getBodyLoss()));
		this.tf_lossBld.setText(Double.toString(genParam.getBuildingLoss()));
		this.tf_lossCar.setText(Double.toString(genParam.getCarLoss()));
		this.tf_noiseFig.setText(Double.toString(genParam.getFigureNoise()));
		this.tf_fadingMargin.setText(Double.toString(genParam.getFadingMargin()));
	}
	public void initStep2(Project proj){
		System.out.println("Dialog: initStep2");		
		EnbParameter enbParam = proj.getParameter().getEnbParam();
		UeParameter ueParam = proj.getParameter().getUeParam();
		
		this.tf_enbCableLoss.setText(Double.toString(enbParam.getCableLoss()));
		this.tf_enbGain.setText(Double.toString(enbParam.getAntennaGain()));
		this.tf_enbThermNoise.setText(Double.toString(enbParam.getThermicNoiseByRB()));
		this.tf_enbPower.setText(Double.toString(enbParam.getPowerTx()));
		this.tf_enbSNIR.setText(Double.toString(enbParam.getCellBoardSNIR()));
		this.tf_enbSysCharge.setText(Double.toString(enbParam.getSystemCharge()));
		this.tf_enbFactorFC.setText(Double.toString(enbParam.getFactorFC()));
		this.tf_enbJumper.setText(Double.toString(enbParam.getJumperTmaLoss()));
		this.cbo_siteType.setSelectedItem(enbParam.getSiteType());
		
		this.tf_uePower.setText(Double.toString(ueParam.getPowerUE()));
		this.tf_ueGain.setText(Double.toString(ueParam.getAntennaGain()));
		this.tf_ueThermNoise.setText(Double.toString(ueParam.getThermicNoiseByRB()));
		this.tf_ueSNIR.setText(Double.toString(ueParam.getTargetSNIR()));
		this.tf_ueSysCharge.setText(Double.toString(ueParam.getSystCharge()));
		this.tf_ueFactorF.setText(Double.toString(ueParam.getFactorF()));
		this.tf_ueJumper.setText(Double.toString(ueParam.getJumperTmaLoss()));		
	}
	public void initStep3(Project proj){	
		EnvParameter envParam = proj.getParameter().getEnvParam();
		PmParameter pmParam = envParam.getPmParameter();
		String propaModel = pmParam.getName();
		
		this.tf_interMargin.setText(Double.toString(envParam.getMarginInterf()));
		this.tf_MHAG.setText(Double.toString(envParam.getMhag()));
		this.tf_CCO.setText(Double.toString(envParam.getCco()));
		
		switch(propaModel){
			case "okumuraHata":
				OkumuraHata oku = (OkumuraHata) pmParam.getPm();
				this.rad_pmOpt1.setSelected(true);
				this.rad_pmOpt2.setSelected(false);
				this.rad_pmOpt3.setSelected(false);
				this.tf_freqPM1.setText(Double.toString(oku.getFrequency()));
				this.tf_hmPM1.setText(Double.toString(oku.getHeightUE()));
				this.tf_hsPM1.setText(Double.toString(oku.getHeightENB()));
				this.cbo_typeZonePM1.setSelectedItem(oku.getZoneType());
				break;
			case "cost231Hata":
				Cost231Hata c231 = (Cost231Hata) pmParam.getPm();
				this.rad_pmOpt1.setSelected(false);
				this.rad_pmOpt2.setSelected(true);
				this.rad_pmOpt3.setSelected(false);
				this.tf_freqPM2.setText(Double.toString(c231.getFrequency()));
				this.tf_hmPM2.setText(Double.toString(c231.getHeightUE()));
				this.tf_htBldPM2.setText(Double.toString(c231.getHeightRoof()));
				this.tf_hsPM2.setText(Double.toString(c231.getHeightUE()));
				this.tf_angPM2.setText(Double.toString(c231.getIncidenceAngle()));
				this.tf_marginBldPM2.setText(Double.toString(c231.getBldSeparation()));
				this.tf_margRoadPM2.setText(Double.toString(c231.getWidthString()));
				this.cbo_typeZonePM2.setSelectedItem(c231.getZoneType());
				break;
			case "cost231WI":
				this.rad_pmOpt1.setSelected(false);
				this.rad_pmOpt2.setSelected(false);
				this.rad_pmOpt3.setSelected(true);
				break;
			case "kfactors":
				this.rad_pmOpt1.setSelected(true);
				this.rad_pmOpt2.setSelected(false);
				this.rad_pmOpt3.setSelected(true);
				break;			
		}		
	}
	public void initResut(Project proj){
		RPlanCoverage resCov = proj.getPlanification().getResult().getResCov();	
		
		tabResCov.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {
	                {" PIRE (dBm)", round(resCov.getPireUl(),3), round(resCov.getPireDl(),3)},
	                {" Sensibilié (dBm)", round(resCov.getSensibilityUL(),3), round(resCov.getSensibilityDL(),3)},
	                {" Bruit termique (dB)", round(resCov.getThermicNoiseUL(),3), round(resCov.getThermicNoiseDL(),3)},
	                {" Path Loss (dBm)", round(resCov.getPathLossUL(),3), round(resCov.getPathLossDL(),3)},
	                {" Rayon d'une cellule (km)", round(resCov.getCellRadiusUL(),3), round(resCov.getCellRadiusDL(),3)},
	                {" Empreinte d'une cellule (km²)", round(resCov.getCellPrintUL(),3), round(resCov.getCellPrintDL(),3)},
	                {" Empreinte d'un site (km²)", round(resCov.getSitePrintUL(),3), round(resCov.getSitePrintDL(),3)},
	                {" Nombre d'Enode-B", resCov.getNumENBUl(), resCov.getNumENBDl()},
	                {"Distance inter-sites finale (km)", round(resCov.getInterSiteDistUL(),3), round(resCov.getInterSiteDistDL(),3)}
	            },
	            new String [] {
	                "Données", "UL", "DL"
	            }	            
	        ){
				public boolean isCellEditable(int row, int column) {
	                return false;
	             }
			}
		);
		jScrollPane1.setViewportView(tabResCov);
		jScrollPane1.repaint();		
		this.tf_finalRadCell.setText(Double.toString(Math.max(round(resCov.getCellRadiusUL(),3),round(resCov.getCellRadiusDL(),3))));
		this.tf_finalNumEnb.setText(Integer.toString(Math.max(resCov.getNumENBUl(),resCov.getNumENBDl())));	
		this.tf_finalDistIS.setText(Double.toString(Math.max(round(resCov.getInterSiteDistUL(),3),round(resCov.getInterSiteDistDL(),3))));
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	public javax.swing.JPanel getConGroupCard() {
		return conGroupCard;
	}
	/* (non-Javadoc)
	 * @see cm.opsim.observer.Observable#addObserver(cm.opsim.observer.Observer)
	 */
	@Override
	public void addObserver(Observer obs) {
		System.out.println("DialogPlanCov: Add obs called ");
		this.listObserver.add(obs);		
		System.out.println("DialogPlanCov: Nbre Obs after call = "+this.listObserver.size());
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
