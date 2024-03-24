/**
 * 
 */
package cm.opsim.view;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author Romuald FOTSO
 *
 */
public class DialogParamGen extends JDialog{
	
	private javax.swing.JButton but_cancel;
    private javax.swing.JButton but_save;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBox cb_is_checkState;
    private javax.swing.JCheckBox cb_is_manMNE;
    private javax.swing.JCheckBox cb_is_manProjStart;
    private javax.swing.JCheckBox cb_is_prevLay;
    private javax.swing.JCheckBox cb_is_prevSel;
    private javax.swing.JCheckBox cb_is_projExt;
    private javax.swing.JComboBox cb_methDiffr;
    private javax.swing.JComboBox cb_methMP;
    private javax.swing.JComboBox cb_typeClut;
    private javax.swing.JComboBox cb_typeProj;
    private javax.swing.JComboBox cb_typeRun;
    private javax.swing.JPanel conButton;
    private javax.swing.JPanel conCard_step1;
    private javax.swing.JPanel conCard_step1_right;
    private javax.swing.JPanel conGroupCard;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JList list_lay2Load;
    private javax.swing.JRadioButton rb_disable;
    private javax.swing.JRadioButton rb_enable;
    private javax.swing.JRadioButton rb_genAutoM3D;
    private javax.swing.JRadioButton rb_loadM3DCityEng;
    private javax.swing.JRadioButton rb_no_model3D;
    private javax.swing.JTextField tf_autorProj;
    private javax.swing.JTextField tf_keyProj;
    
    private Image icone =  Toolkit.getDefaultToolkit().getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/logo.png"));
    private OpsimGUI opsimGUI;
    private DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    
    public DialogParamGen(OpsimGUI parent, boolean modal) {
        super(parent, modal);
        this.opsimGUI = parent;
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
        
        this.setVisible(true);
    }
    
    public void initComponents(){
    	this.setIconImage(icone);
    	buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        conButton = new javax.swing.JPanel();
        but_cancel = new javax.swing.JButton();
        but_save = new javax.swing.JButton();
        conGroupCard = new javax.swing.JPanel();
        conCard_step1 = new javax.swing.JPanel();
        conCard_step1_right = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        list_lay2Load = new javax.swing.JList();
        cb_is_manMNE = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        rb_genAutoM3D = new javax.swing.JRadioButton();
        rb_loadM3DCityEng = new javax.swing.JRadioButton();
        rb_no_model3D = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        cb_is_projExt = new javax.swing.JCheckBox();
        cb_is_prevSel = new javax.swing.JCheckBox();
        cb_is_prevLay = new javax.swing.JCheckBox();
        cb_is_checkState = new javax.swing.JCheckBox();
        cb_is_manProjStart = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cb_typeProj = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        tf_autorProj = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        rb_enable = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        tf_keyProj = new javax.swing.JTextField();
        rb_disable = new javax.swing.JRadioButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        cb_methDiffr = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cb_methMP = new javax.swing.JComboBox();
        cb_typeClut = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        cb_typeRun = new javax.swing.JComboBox();

        buttonGroup1.add(this.rb_enable);
        buttonGroup1.add(this.rb_disable);

        buttonGroup2.add(this.rb_genAutoM3D);
        buttonGroup2.add(this.rb_loadM3DCityEng);
        buttonGroup2.add(this.rb_no_model3D);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Paramètres généraux");
        setPreferredSize(new java.awt.Dimension(535, 500));
        setResizable(false);

        conButton.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        conButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        but_cancel.setText("Annuler");
        but_cancel.setToolTipText("Annuler l'opération");

        but_save.setText("Valider");
        but_save.setToolTipText("Finaliser l'opération");

        javax.swing.GroupLayout conButtonLayout = new javax.swing.GroupLayout(conButton);
        conButton.setLayout(conButtonLayout);
        conButtonLayout.setHorizontalGroup(
            conButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, conButtonLayout.createSequentialGroup()
                .addContainerGap(371, Short.MAX_VALUE)
                .addComponent(but_save)
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
                    .addComponent(but_save))
                .addContainerGap())
        );

        getContentPane().add(conButton, java.awt.BorderLayout.SOUTH);

        conGroupCard.setLayout(new java.awt.CardLayout());

        conCard_step1.setLayout(new java.awt.BorderLayout());

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Rendu(2D/3D)"));

        jLabel5.setText("Sélectionner les couches à charger:");

        list_lay2Load.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { " Modèle Numerique de Terrain (MNT)", " Modèle Numerique de Surface (MNS)", " Surface d'elevation (MNE)", " Couche clutter (CLUT)", " Couche Image (IMG)", " Couche Couverture (COV)", " Analyse d'Interférence (SINR)", " Modèle 3D CityEngine (CE)" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        list_lay2Load.setSelectedIndices(new int[] {1, 2, 3, 4, 5, 6});
        jScrollPane1.setViewportView(list_lay2Load);

        cb_is_manMNE.setSelected(true);
        cb_is_manMNE.setText("Prise en charge de la surface d'élevation");

        jLabel6.setText("Modèle 3D:");

        rb_genAutoM3D.setText("Géneration automatique du modèle 3D");

        rb_loadM3DCityEng.setText("Chargement du modèle 3D CityEngine");

        rb_no_model3D.setSelected(true);
        rb_no_model3D.setText("Chargement de la couche sans modèle 3D");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rb_loadM3DCityEng)
                            .addComponent(rb_genAutoM3D)
                            .addComponent(rb_no_model3D)))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cb_is_manMNE, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_is_manMNE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(4, 4, 4)
                .addComponent(rb_no_model3D)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_genAutoM3D)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_loadM3DCityEng)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Gestionnaire de projet"));

        cb_is_projExt.setText("Prise en charge des projets exterieurs");
        cb_is_projExt.setEnabled(false);

        cb_is_prevSel.setSelected(true);
        cb_is_prevSel.setText("Aperçu lors de la sélection de projet");

        cb_is_prevLay.setSelected(true);
        cb_is_prevLay.setText("Aperçu des couches ajoutées");

        cb_is_checkState.setSelected(true);
        cb_is_checkState.setText("Verification de l'état des couches");
        cb_is_manProjStart.setText("Ouverture du gestionnaire au démarrage");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_is_manProjStart)
                    .addComponent(cb_is_checkState)
                    .addComponent(cb_is_prevLay)
                    .addComponent(cb_is_prevSel)
                    .addComponent(cb_is_projExt))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(cb_is_projExt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_is_prevSel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_is_prevLay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_is_checkState)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_is_manProjStart)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Projet"));

        jLabel1.setText("Type de projet:");

        cb_typeProj.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Académique", "Non Lucratif", "Commerciale", "Inconnu" }));

        jLabel2.setText("Auteur du projet:");

        jLabel3.setText("Protection du projet");

        rb_enable.setText("Activer");
        jLabel4.setText("Clé:");

        tf_keyProj.setEnabled(false);

        rb_disable.setSelected(true);
        rb_disable.setText("Désactiver");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_autorProj))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18)
                                    .addComponent(cb_typeProj, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3)
                                        .addComponent(rb_enable))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                    .addGap(33, 33, 33)
                                    .addComponent(rb_disable)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tf_keyProj, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cb_typeProj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(tf_autorProj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rb_enable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_keyProj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rb_disable))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("GeoProcessing"));

        jLabel7.setText("Méthode de diffraction:");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        cb_methDiffr.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bullington", "Deygout94", "Round Mask", "Cylinder" }));

        jLabel8.setText("Type de trajets multiples:");

        jLabel9.setText("Type de cluster:");

        cb_methMP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Standard", "Coarse Integration", "Fine Integration", "Area", "Free Ellipsoid" }));

        cb_typeClut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rx over clutter", "Rx over ground" }));

        jLabel10.setText("Type d'éxecution:");

        cb_typeRun.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Standard", "Multi-tache", "Multi-processing" }));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb_methMP, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb_typeClut, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(cb_methDiffr, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_typeRun, 0, 139, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cb_methDiffr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cb_methMP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cb_typeClut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel10)
                .addComponent(cb_typeRun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout conCard_step1_rightLayout = new javax.swing.GroupLayout(conCard_step1_right);
        conCard_step1_right.setLayout(conCard_step1_rightLayout);
        conCard_step1_rightLayout.setHorizontalGroup(
            conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                        .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(3, 3, 3)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        conCard_step1_rightLayout.setVerticalGroup(
            conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        conCard_step1.add(conCard_step1_right, java.awt.BorderLayout.PAGE_END);

        conGroupCard.add(conCard_step1, "CARD_1");

        getContentPane().add(conGroupCard, java.awt.BorderLayout.CENTER);

        pack();
    }
    
    public void initEvents(){
    	but_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_cancelActionPerformed(evt);
            }
        });
    	
    	but_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_saveActionPerformed(evt);
            }
        });
    }
    
    private void but_cancelActionPerformed(java.awt.event.ActionEvent evt) {                                           
        this.setVisible(false);
    }                                          

    private void but_saveActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	this.setVisible(false);
    } 
    
    public static void main(String[] args) {
		DialogParamGen dialog = new DialogParamGen(null, true);

	}
}
