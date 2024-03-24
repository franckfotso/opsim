/**
 * 
 */
package cm.opsim.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;

import cm.opsim.controller.ProjectController;
import cm.opsim.model.Project;

import com.esri.arcgis.carto.MapDocument;
import com.esri.arcgis.interop.AutomationException;

/**
 * @author Romuald FOTSO
 *
 */
public class DialogExportCarto extends javax.swing.JDialog {

	private javax.swing.JButton but_cancel;
    private javax.swing.JButton but_save;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel conButton;
    private javax.swing.JPanel conCard_step1;
    private javax.swing.JPanel conCard_step1_right;
    private javax.swing.JPanel conGroupCard;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JRadioButton rad_mxd_sxd;
    private javax.swing.JRadioButton rad_projOPSIM;
	
	private Image icone =  Toolkit.getDefaultToolkit().getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/logo.png"));
    private OpsimGUI opsimGUI;
    private DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	
	public DialogExportCarto(OpsimGUI parent, boolean modal) {
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
        jLabel4 = new javax.swing.JLabel();
        rad_projOPSIM = new javax.swing.JRadioButton();
        rad_mxd_sxd = new javax.swing.JRadioButton();

        buttonGroup1.add(this.rad_projOPSIM);
        buttonGroup1.add(this.rad_mxd_sxd);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Exporter la cartographie");
        setPreferredSize(new java.awt.Dimension(268, 166));
        setResizable(false);

        conButton.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        conButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        but_cancel.setText("Annuler");
        but_cancel.setToolTipText("Annuler l'opération");       

        but_save.setText("Ok");
        but_save.setToolTipText("Finaliser l'opération");       

        javax.swing.GroupLayout conButtonLayout = new javax.swing.GroupLayout(conButton);
        conButton.setLayout(conButtonLayout);
        conButtonLayout.setHorizontalGroup(
            conButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, conButtonLayout.createSequentialGroup()
                .addContainerGap(129, Short.MAX_VALUE)
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

        jLabel4.setText("Choisir le format de fichier pour l'exportation:");

        rad_projOPSIM.setSelected(true);
        rad_projOPSIM.setText("Projet OPSIM (.ops)");

        rad_mxd_sxd.setText("Document (.mxd) et/ou Scene (.sxd) ArcGIS");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rad_mxd_sxd)
                            .addComponent(rad_projOPSIM))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rad_projOPSIM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rad_mxd_sxd)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout conCard_step1_rightLayout = new javax.swing.GroupLayout(conCard_step1_right);
        conCard_step1_right.setLayout(conCard_step1_rightLayout);
        conCard_step1_rightLayout.setHorizontalGroup(
            conCard_step1_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conCard_step1_rightLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
	    if(this.rad_projOPSIM.isSelected()){
	    	ProjectController projConWS = (ProjectController) opsimGUI.getListControler().get("ProjectController");
			Project projWS = (Project) projConWS.getModel();
			
	    	FileSystemView systView = FileSystemView.getFileSystemView();			
        	JFileChooser defautChooser = new JFileChooser(systView.getDefaultDirectory());
			OpsimFilter opsFilter = new OpsimFilter("ops","OPSIM Project File");
			defautChooser.addChoosableFileFilter(opsFilter);
			defautChooser.setSelectedFile(new File(systView.getDefaultDirectory()+File.separator+projWS.getName()+".ops"));
			
			if(defautChooser.showSaveDialog(null) == defautChooser.APPROVE_OPTION){
				File file =  defautChooser.getSelectedFile();
				if(opsFilter.accept(file) && !file.exists()){
					ObjectInputStream ois;
					ObjectOutputStream oos;
					try{
						oos = new ObjectOutputStream(
								new BufferedOutputStream(
								new FileOutputStream(
										file)));						
						// Saving OPSIM Project
						oos.writeObject(projWS);
						oos.close();
						JOptionPane.showMessageDialog(null,"L'opération s'est déroulée avec succès.",
								"OPSIM: Exportation de la cartographie",JOptionPane.INFORMATION_MESSAGE);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null,"Une erreur s'est produite lors de l'exportation",
								"OPSIM: Exportation de la cartographie",JOptionPane.ERROR_MESSAGE);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null,"Une erreur s'est produite lors de l'exportation",
								"OPSIM: Exportation de la cartographie",JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}
				else JOptionPane.showMessageDialog(null,"Erreur sur l'extension ou le nom du fichier à créer.\n" +
														"Veuillez definir un nouveau et respecter l'extension (.ops)",
														"Erreur",JOptionPane.ERROR_MESSAGE);
			}
			else JOptionPane.showMessageDialog(null,"Veuillez definir le nom du fichier",
					"Erreur",JOptionPane.ERROR_MESSAGE);
	    	this.setVisible(false);
	    }
	    else if(this.rad_mxd_sxd.isSelected()){
	    	FileSystemView systView = FileSystemView.getFileSystemView();			
        	JFileChooser defautChooser = new JFileChooser(systView.getDefaultDirectory());
        	defautChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        	
        	if(defautChooser.showSaveDialog(null) == defautChooser.APPROVE_OPTION){
				File file =  defautChooser.getSelectedFile();
				if(file.exists()){
					try {
						ProjectController projConWS = (ProjectController) opsimGUI.getListControler().get("ProjectController");
						Project projWS = (Project) projConWS.getModel();
						
						MapDocument mapDoc = new MapDocument();
						String pathName = file.getPath()+File.separator+projWS.getName()+".mxd";

						mapDoc.esri_new(pathName);
						mapDoc.save(false, false);	
						mapDoc.close();
						
						mapDoc.open(pathName, null);
						mapDoc.replaceContents(this.opsimGUI.getOpsimMap().getMapControl());
						mapDoc.save(false, false);	
						mapDoc.close();						
						
						JOptionPane.showMessageDialog(null,"L'opération s'est déroulée avec succès.",
								"OPSIM: Exportation de la cartographie",JOptionPane.INFORMATION_MESSAGE);
					} catch (AutomationException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null,"Une erreur s'est produite lors de l'exportation",
								"OPSIM: Exportation de la cartographie",JOptionPane.ERROR_MESSAGE);
					} catch (IOException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null,"Une erreur s'est produite lors de l'exportation",
								"OPSIM: Exportation de la cartographie",JOptionPane.ERROR_MESSAGE);
					}
				}
				else JOptionPane.showMessageDialog(null,"Veuillez definir un répertoire existant pour l'exportation",
						"Erreur",JOptionPane.ERROR_MESSAGE);
        	}
        	else JOptionPane.showMessageDialog(null,"Veuillez definir un répertoire pour l'exportation",
					"Erreur",JOptionPane.ERROR_MESSAGE);
	    	this.setVisible(false);
	    }
	 } 
	 
	 public static void main(String[] args) {
		 DialogExportCarto dialog = new DialogExportCarto(null, true);
	}
}
