/**
 * 
 */
package cm.opsim.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.TreePath;

import cm.opsim.dao.AntennaTypeDAO;
import cm.opsim.dao.DAOFactory;
import cm.opsim.model.AntennaType;
import cm.opsim.observer.Observable;
import cm.opsim.observer.Observer;

/**
 * @author Romuald FOTSO
 *
 */
public class DialogAntenType extends javax.swing.JDialog implements Observable{
	// Variables declaration - do not modify                     
    private javax.swing.JButton but_closeAT;
    private javax.swing.JButton but_newAT;
    private javax.swing.JButton but_selectAT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel panPH;
    private javax.swing.JPanel panPV;
    private javax.swing.JTree treeAnt;
    // End of variables declaration    
	private OpsimGUI opsimGUI;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private ArrayList<Observer> listObserver = new ArrayList<Observer>();
    private DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    private AntennaType antenType;
    private String name_Ant;
    private ImagePanel antPattern_VP = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().
			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/antPattern-VP.png"))).getImage());
    private ImagePanel antPattern_HP = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().
			getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/antPattern-HP.png"))).getImage());	
    private Image icone =  Toolkit.getDefaultToolkit().getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/logo.png"));
    
	public DialogAntenType(JDialog parent, boolean modal, Observer obs, OpsimGUI gui) {
		super(parent, modal);
        this.opsimGUI = gui;
        this.addObserver(obs);
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
		jSeparator3 = new javax.swing.JSeparator();
        but_selectAT = new javax.swing.JButton();
        but_newAT = new javax.swing.JButton();
        but_closeAT = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        treeAnt = new javax.swing.JTree();
        panPH = new javax.swing.JPanel();
        panPV = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Type d'antennes");
        setModal(true);
        setResizable(false);

        but_selectAT.setText("Sélectionner");
        but_selectAT.setEnabled(false);

        but_newAT.setText("Nouveau");
        but_newAT.setEnabled(false);

        but_closeAT.setText("Fermer");

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Antennas");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("SIMO1x2");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("TxDiv2x2");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("OLSM2x2");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("2600 Antenna");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("30deg 18dBi 0Tilt 1800Mhz");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("30deg 18dBi 0Tilt 900Mhz");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("33deg 21dBi 2Tilt 2100Mhz");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("65deg 17dBi 0Tilt 1800Mhz");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("65deg 17dBi 0Tilt 900Mhz");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("65deg 17dBi 2Tilt 1800Mhz");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("65deg 17dBi 2Tilt 900Mhz");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("65deg 17dBi 4Tilt 900Mhz");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("65deg 17dBi 6Tilt 1800Mhz");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("65deg 18.5dBi 5Tilt 1800Mhz");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("65deg 18dBi 0Tilt 2100Mhz");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("65deg 18dBi 2Tilt 2100Mhz");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("65deg 18dBi 4Tilt 2100Mhz");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Omni 18.5dBi 5Tilt 2600Mhz");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Omni 11dBi 0Tilt 1800Mhz");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Omni 11dBi 0Tilt 2100Mhz");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Omni 11dBi 0Tilt 900Mhz");
        treeNode1.add(treeNode2);
        treeAnt.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane2.setViewportView(treeAnt);

        panPH.setBackground(new java.awt.Color(255, 255, 255));
        panPH.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panPH.add(antPattern_HP);

        javax.swing.GroupLayout panPHLayout = new javax.swing.GroupLayout(panPH);
        panPH.setLayout(panPHLayout);
        panPHLayout.setHorizontalGroup(
            panPHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );
        panPHLayout.setVerticalGroup(
            panPHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panPV.setBackground(new java.awt.Color(255, 255, 255));
        panPV.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        //panPV.add(antPattern_VP);

        javax.swing.GroupLayout panPVLayout = new javax.swing.GroupLayout(panPV);
        panPV.setLayout(panPVLayout);
        panPVLayout.setHorizontalGroup(
            panPVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panPVLayout.setVerticalGroup(
            panPVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 242, Short.MAX_VALUE)
        );

        jLabel1.setText("Polarisation Horizontale:");

        jLabel2.setText("Polarisation Verticale:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panPH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 191, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panPV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(but_selectAT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(but_newAT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(but_closeAT)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panPV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panPH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(but_selectAT)
                    .addComponent(but_newAT)
                    .addComponent(but_closeAT))
                .addContainerGap())
        );

        pack();
	}
	
	public void initEvents(){
		this.but_closeAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	but_closeATActionPerformed(evt);
            }
        });
		
		this.treeAnt.addTreeSelectionListener(new TreeSelectionListener(){
	   		 public void valueChanged(TreeSelectionEvent event) {
	   			treeAntValueChanged(event);
	   		 }
	   	 });
		
		this.but_selectAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	but_selectATActionPerformed(evt);            	
            }
        });
	}
	
	public void but_closeATActionPerformed(java.awt.event.ActionEvent evt){
		this.setVisible(false);
	}
	
	public void but_selectATActionPerformed(java.awt.event.ActionEvent evt){
		AntennaTypeDAO antDAO = (AntennaTypeDAO) DAOFactory.getAntennaTypeDAO();
		this.antenType = antDAO.find(name_Ant);		
		this.notifyObserver();
		this.setVisible(false);
	}
	
	private void treeAntValueChanged(TreeSelectionEvent event){
		
		if(treeAnt.getLastSelectedPathComponent() != null){			
			String name = getAbsolutePath(event.getPath());	
			System.out.println("Node select = "+name);
			switch(name){
				case "AntennasSIMO1x2":this.name_Ant = "SIMO1x2";break;
				case "AntennasTxDiv2x2":this.name_Ant = "TxDiv2x2";break;
				case "AntennasOLSM2x2":this.name_Ant = "OLSM2x2";break;
				case "Antennas2600 Antenna":this.name_Ant = "2600 Antenna";break;
				case "Antennas30deg 18dBi 0Tilt 1800Mhz":this.name_Ant = "30deg 18dBi 0Tilt 1800Mhz";break;
				case "Antennas30deg 18dBi 0Tilt 900Mhz":this.name_Ant = "30deg 18dBi 0Tilt 900Mhz";break;
				case "Antennas33deg 21dBi 2Tilt 2100Mhz":this.name_Ant = "33deg 21dBi 2Tilt 2100Mhz";break;
				case "Antennas65deg 17dBi 0Tilt 1800Mhz":this.name_Ant = "65deg 17dBi 0Tilt 1800Mhz";break;
				case "Antennas65deg 17dBi 0Tilt 900Mhz":this.name_Ant = "65deg 17dBi 0Tilt 900Mhz";break;
				case "Antennas65deg 17dBi 2Tilt 1800Mhz":this.name_Ant = "65deg 17dBi 2Tilt 1800Mhz";break;
				case "Antennas65deg 17dBi 2Tilt 900Mhz":this.name_Ant = "65deg 17dBi 2Tilt 900Mhz";break;
				case "Antennas65deg 17dBi 4Tilt 900Mhz":this.name_Ant = "65deg 17dBi 4Tilt 900Mhz";break;
				case "Antennas65deg 17dBi 6Tilt 1800Mhz":this.name_Ant = "65deg 17dBi 6Tilt 1800Mhz";break;
				case "Antennas65deg 18.5dBi 5Tilt 1800Mhz":this.name_Ant = "65deg 18.5dBi 5Tilt 1800Mhz";break;
				case "Antennas65deg 18dBi 0Tilt 2100Mhz":this.name_Ant = "65deg 18dBi 0Tilt 2100Mhz";break;
				case "Antennas65deg 18dBi 2Tilt 2100Mhz":this.name_Ant = "65deg 18dBi 2Tilt 2100Mhz";break;
				case "Antennas65deg 18dBi 4Tilt 2100Mhz":this.name_Ant = "65deg 18dBi 4Tilt 2100Mhz";break;
				case "AntennasOmni 18.5dBi 5Tilt 2600Mhz":this.name_Ant = "Omni 18.5dBi 5Tilt 2600Mhz";break;
				case "AntennasOmni 11dBi 0Tilt 1800Mhz":this.name_Ant = "Omni 11dBi 0Tilt 1800Mhz";break;
				case "AntennasOmni 11dBi 0Tilt 2100Mhz":this.name_Ant = "Omni 11dBi 0Tilt 2100Mhz";break;
				case "AntennasOmni 11dBi 0Tilt 900Mhz":this.name_Ant = "Omni 11dBi 0Tilt 900Mhz";break;
				default:this.name_Ant = "30deg 18dBi 0Tilt 1800Mhz";break;
			}
			this.but_selectAT.setEnabled(true);			
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
	
	
	public static void main(String args[]) {
		DialogAntenType dialog = new DialogAntenType(null,true,null,null);
	}
	/* (non-Javadoc)
	 * @see cm.opsim.observer.Observable#addObserver(cm.opsim.observer.Observer)
	 */
	@Override
	public void addObserver(Observer obs) {
		System.out.println("DialogAntenType: Add obs called ");
		this.listObserver.add(obs);		
		System.out.println("DialogAntenType: Nbre Obs after call = "+this.listObserver.size());
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

	public AntennaType getAntenType() {
		return antenType;
	}

	public void setAntenType(AntennaType antenType) {
		this.antenType = antenType;
	}
	
}
