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
public class DialogParamZE  extends javax.swing.JDialog {

	private javax.swing.JButton but_cancel;
    private javax.swing.JButton but_save;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cb_zone;
    private javax.swing.JPanel conButton;
    private javax.swing.JPanel conCard_step1;
    private javax.swing.JPanel conCard_step1_right;
    private javax.swing.JPanel conGroupCard;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton rad_newZone;
    private javax.swing.JRadioButton rad_oldZone;
    private javax.swing.JTextField tf_area;
    private javax.swing.JTextField tf_bottom;
    private javax.swing.JTextField tf_density;
    private javax.swing.JTextField tf_left;
    private javax.swing.JTextField tf_nameZone;
    private javax.swing.JTextField tf_right;
    private javax.swing.JTextField tf_top;
    
	private Image icone =  Toolkit.getDefaultToolkit().getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/logo.png"));
    private OpsimGUI opsimGUI;
    private DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    
    public DialogParamZE(OpsimGUI parent, boolean modal) {
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
        conButton = new javax.swing.JPanel();
        but_cancel = new javax.swing.JButton();
        but_save = new javax.swing.JButton();
        conGroupCard = new javax.swing.JPanel();
        conCard_step1 = new javax.swing.JPanel();
        conCard_step1_right = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        rad_oldZone = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        cb_zone = new javax.swing.JComboBox();
        rad_newZone = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        tf_nameZone = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        tf_top = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tf_left = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tf_right = new javax.swing.JTextField();
        tf_bottom = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tf_area = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tf_density = new javax.swing.JTextField();

        buttonGroup1.add(this.rad_oldZone);
        buttonGroup1.add(this.rad_newZone);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Paramètres zone d'étude");
        setPreferredSize(new java.awt.Dimension(472, 465));
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
                .addContainerGap(313, Short.MAX_VALUE)
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

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Données"));

        rad_oldZone.setText("Sélectionner une zone existante");

        jLabel1.setText("Zone:");

        cb_zone.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ngaoundéré", "Yaoundé", "Bertoua", "Maroua", "Douala", "Garoua", "Bamenda", "Bafoussam", "Ebolowa", "Buéa" }));
        cb_zone.setSelectedIndex(1);
        cb_zone.setToolTipText("");
        cb_zone.setEnabled(false);

        rad_newZone.setSelected(true);
        rad_newZone.setText("Créer une nouvelle zone");

        jLabel2.setText("Nom de la zone:");

        jLabel3.setText("Cadrer la zone d'étude");

        jLabel6.setText("Y Maximum (Top)");

        jLabel9.setText("X Minimum (Left)");

        jLabel10.setText("X Maximum (Right)");

        jLabel11.setText("Y Minimum (Bottom)");

        jLabel12.setText("Surface (Km²):");

        jLabel13.setText("Population:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10))))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(tf_top, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jSeparator1))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(160, 160, 160)
                                .addComponent(jLabel6))
                            .addComponent(rad_oldZone)
                            .addComponent(rad_newZone)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(tf_nameZone, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addGap(57, 57, 57)
                                            .addComponent(cb_zone, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel13))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tf_density, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tf_area, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addComponent(tf_bottom, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(162, 162, 162)
                                .addComponent(jLabel11)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(tf_left, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addComponent(tf_right, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(rad_oldZone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cb_zone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rad_newZone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tf_nameZone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_area, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(tf_density, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_top, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_left, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_right, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_bottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout conCard_step1_rightLayout = new javax.swing.GroupLayout(conCard_step1_right);
        conCard_step1_right.setLayout(conCard_step1_rightLayout);
        conCard_step1_rightLayout.setHorizontalGroup(
            conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );
        conCard_step1_rightLayout.setVerticalGroup(
            conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    	DialogParamZE dialog = new DialogParamZE(null, true);

	}
}
