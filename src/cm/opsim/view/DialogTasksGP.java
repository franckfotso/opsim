/**
 * 
 */
package cm.opsim.view;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Romuald FOTSO
 *
 */
public class DialogTasksGP extends javax.swing.JDialog {

	// Variables declaration - do not modify                     
    private javax.swing.JButton but_cancel;
    private javax.swing.JButton but_exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea ta_output;
    // End of variables declaration 
    private Image icone =  Toolkit.getDefaultToolkit().getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/logo.png"));
    
	public DialogTasksGP(OpsimGUI parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        try {
        	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        SwingUtilities.updateComponentTreeUI(this);
	    } 
		catch (InstantiationException e) {} 
		catch (ClassNotFoundException e) {}
		catch (UnsupportedLookAndFeelException e) {} 
		catch (IllegalAccessException e) {} 
        //this.setVisible(true);
    }
	
	public void initComponents(){ 
		this.setIconImage(icone);  
		jScrollPane1 = new javax.swing.JScrollPane();
        ta_output = new javax.swing.JTextArea();
        but_cancel = new javax.swing.JButton();
        but_exit = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("OPSIM-GeoProcessing: Détails d'exécution");
        setResizable(true);

        ta_output.setEditable(false);
        ta_output.setColumns(20);
        ta_output.setRows(5);
        jScrollPane1.setViewportView(ta_output);

        but_cancel.setText("Interrompre");
        but_cancel.setEnabled(false);

        but_exit.setText("Fermer");

        jLabel1.setText("Exécution des tâches en cours ...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(but_cancel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(but_exit))
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(but_cancel)
                    .addComponent(but_exit))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
	}

	public static void main(String[] args) {
		DialogTasksGP dialog = new DialogTasksGP(null, false);
	}	

	public javax.swing.JButton getBut_cancel() {
		return but_cancel;
	}

	public javax.swing.JButton getBut_exit() {
		return but_exit;
	}

	public javax.swing.JTextArea getTa_output() {
		return ta_output;
	}

	public javax.swing.JProgressBar getjProgressBar1() {
		return jProgressBar1;
	}
	
	
	
	
}
