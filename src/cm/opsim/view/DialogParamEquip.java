/**
 * 
 */
package cm.opsim.view;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author Romuald FOTSO
 *
 */
public class DialogParamEquip extends javax.swing.JDialog  {
    private javax.swing.JButton but_cancel;
    private javax.swing.JButton but_save;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JCheckBox cb_addTxFromList;
    private javax.swing.JCheckBox cb_autoAjustAzim;
    private javax.swing.JCheckBox cb_autoAjustTilt;
    private javax.swing.JCheckBox cb_is_ghostTx;
    private javax.swing.JCheckBox cb_is_manInterf;
    private javax.swing.JCheckBox cb_is_usingAntInt;
    private javax.swing.JCheckBox cb_majTx;
    private javax.swing.JCheckBox cb_prevLink;
    private javax.swing.JPanel conButton;
    private javax.swing.JPanel conCard_step1;
    private javax.swing.JPanel conCard_step1_right;
    private javax.swing.JPanel conGroupCard;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lab_majTx;
    private javax.swing.JLabel lab_prevLink;
    private javax.swing.JRadioButton rad_05Degree;
    private javax.swing.JRadioButton rad_100m25000m;
    private javax.swing.JRadioButton rad_10m2500m;
    private javax.swing.JRadioButton rad_1Degree;
    private javax.swing.JRadioButton rad_1m250m;
    private javax.swing.JRadioButton rad_5Degree;
    private javax.swing.JRadioButton rad_allCan;
    private javax.swing.JRadioButton rad_pilotCan;
    private javax.swing.JRadioButton rad_rotatif;
    private javax.swing.JRadioButton rad_tiltElec;
    private javax.swing.JRadioButton rad_tiltMeca;
    private javax.swing.JRadioButton rad_tiltRes;
    private javax.swing.JTextField tf_corrCI_IRF;
    private javax.swing.JTextField tf_distMax;
    private javax.swing.JTextField tf_interfCo;
    private javax.swing.JTextField tf_numCanAdj;
	private Image icone =  Toolkit.getDefaultToolkit().getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/logo.png"));
    private OpsimGUI opsimGUI;
    private DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    
    public DialogParamEquip(OpsimGUI parent, boolean modal) {
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
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        conButton = new javax.swing.JPanel();
        but_cancel = new javax.swing.JButton();
        but_save = new javax.swing.JButton();
        conGroupCard = new javax.swing.JPanel();
        conCard_step1 = new javax.swing.JPanel();
        conCard_step1_right = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        cb_addTxFromList = new javax.swing.JCheckBox();
        cb_is_ghostTx = new javax.swing.JCheckBox();
        cb_autoAjustAzim = new javax.swing.JCheckBox();
        cb_autoAjustTilt = new javax.swing.JCheckBox();
        cb_prevLink = new javax.swing.JCheckBox();
        lab_prevLink = new javax.swing.JLabel();
        cb_majTx = new javax.swing.JCheckBox();
        lab_majTx = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        rad_1m250m = new javax.swing.JRadioButton();
        rad_10m2500m = new javax.swing.JRadioButton();
        rad_100m25000m = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        cb_is_manInterf = new javax.swing.JCheckBox();
        cb_is_usingAntInt = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        rad_tiltRes = new javax.swing.JRadioButton();
        rad_tiltMeca = new javax.swing.JRadioButton();
        rad_tiltElec = new javax.swing.JRadioButton();
        rad_rotatif = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        rad_5Degree = new javax.swing.JRadioButton();
        rad_1Degree = new javax.swing.JRadioButton();
        rad_05Degree = new javax.swing.JRadioButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        tf_interfCo = new javax.swing.JTextField();
        tf_numCanAdj = new javax.swing.JTextField();
        rad_allCan = new javax.swing.JRadioButton();
        rad_pilotCan = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tf_distMax = new javax.swing.JTextField();
        tf_corrCI_IRF = new javax.swing.JTextField();

        buttonGroup1.add(this.rad_tiltRes);
        buttonGroup1.add(this.rad_tiltMeca);
        buttonGroup1.add(this.rad_tiltElec);
        buttonGroup1.add(this.rad_rotatif);

        buttonGroup2.add(this.rad_05Degree);
        buttonGroup2.add(this.rad_1Degree);
        buttonGroup2.add(this.rad_05Degree);

        buttonGroup3.add(this.rad_allCan);
        buttonGroup3.add(this.rad_pilotCan);

        buttonGroup4.add(this.rad_1m250m);
        buttonGroup4.add(this.rad_10m2500m);
        buttonGroup4.add(this.rad_100m25000m);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Paramètres equipements");
        setPreferredSize(new java.awt.Dimension(610, 530));
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
                .addContainerGap(449, Short.MAX_VALUE)
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

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Sites"));

        cb_addTxFromList.setSelected(true);
        cb_addTxFromList.setText("Ajout de Tx depuis la liste");

        cb_is_ghostTx.setText("Accepter les sites fantomes (Tx hors de la carte)");
        cb_is_ghostTx.setEnabled(false);

        cb_autoAjustAzim.setText("Auto ajustement de l'azimult pour les sites reliés");

        cb_autoAjustTilt.setText("Auto Ajustement du tilt pour les stations reliées");

        cb_prevLink.setText("Prévision de création des liens lorque les données");
        cb_prevLink.setEnabled(false);

        lab_prevLink.setText("radio sont non compatibles...");
        lab_prevLink.setEnabled(false);

        cb_majTx.setText("Mise à jour des données de localisation depuis les");
        cb_majTx.setEnabled(false);

        lab_majTx.setText("coordonnées...");
        lab_majTx.setEnabled(false);

        jLabel3.setText("Hauteur des sites:");

        rad_1m250m.setText("De 1 M à 250 M.");

        rad_10m2500m.setSelected(true);
        rad_10m2500m.setText("De 10 M à 2500 M.");

        rad_100m25000m.setText("De 100 M à 25000 M.");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_majTx)
                            .addComponent(cb_prevLink)
                            .addComponent(cb_autoAjustTilt)
                            .addComponent(cb_autoAjustAzim)
                            .addComponent(cb_is_ghostTx)
                            .addComponent(cb_addTxFromList)
                            .addComponent(jLabel3)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lab_majTx)
                                    .addComponent(lab_prevLink)))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rad_10m2500m)
                            .addComponent(rad_1m250m)
                            .addComponent(rad_100m25000m))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(cb_addTxFromList)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_is_ghostTx)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_autoAjustAzim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_autoAjustTilt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_prevLink)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lab_prevLink)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_majTx)
                .addGap(1, 1, 1)
                .addComponent(lab_majTx)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rad_1m250m)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rad_10m2500m)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rad_100m25000m)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Antennes"));

        cb_is_manInterf.setSelected(true);
        cb_is_manInterf.setText("Prise  en charge des données d'interférence");

        cb_is_usingAntInt.setSelected(true);
        cb_is_usingAntInt.setText("Utilisation d'antennes intélligentes");

        jLabel11.setText("Positionnement des antennes:");

        rad_tiltRes.setSelected(true);
        rad_tiltRes.setText("Tilt et résolution depuis les paramètres Tx/Rx");

        rad_tiltMeca.setText("Tilt Mecanique");

        rad_tiltElec.setText("Tilt Electronique");

        rad_rotatif.setText("Rotatif");

        jLabel12.setText("Résolution de l'antenne:");

        rad_5Degree.setSelected(true);
        rad_5Degree.setText("5 degree de résolution");

        rad_1Degree.setText("1 degree de résolution");

        rad_05Degree.setText("0.5 degree de résolution");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(cb_is_usingAntInt)
                    .addComponent(cb_is_manInterf)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rad_5Degree)
                            .addComponent(rad_1Degree)
                            .addComponent(rad_05Degree)
                            .addComponent(rad_tiltMeca)
                            .addComponent(rad_tiltRes)
                            .addComponent(rad_tiltElec)
                            .addComponent(rad_rotatif))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(cb_is_manInterf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_is_usingAntInt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rad_tiltRes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rad_tiltMeca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rad_tiltElec)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rad_rotatif)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rad_5Degree)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rad_1Degree)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rad_05Degree)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Interférences"));

        jLabel7.setText("Interférence co-canal (C/Ic):");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel8.setText("Nombre de canaux adjacents:");

        rad_allCan.setSelected(true);
        rad_allCan.setText("Recherche: sur tous les canaux (C/I)");

        rad_pilotCan.setText("Recherche: sur le canl pilote uniquement");

        jLabel4.setText("Distance de recheche Max (M.) :");

        jLabel5.setText("Correction C/I et IRF:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf_interfCo, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(tf_numCanAdj)))
                    .addComponent(rad_allCan)
                    .addComponent(rad_pilotCan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_corrCI_IRF)
                    .addComponent(tf_distMax))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tf_interfCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tf_numCanAdj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rad_allCan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rad_pilotCan)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tf_distMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tf_corrCI_IRF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout conCard_step1_rightLayout = new javax.swing.GroupLayout(conCard_step1_right);
        conCard_step1_right.setLayout(conCard_step1_rightLayout);
        conCard_step1_rightLayout.setHorizontalGroup(
            conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        conCard_step1_rightLayout.setVerticalGroup(
            conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    	DialogParamEquip dialog = new DialogParamEquip(null, true);

	}
}
