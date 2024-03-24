/**
 * 
 */
package cm.opsim.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import cm.opsim.controller.ProjectController;
import cm.opsim.dao.DAO;
import cm.opsim.dao.DAOFactory;
import cm.opsim.dao.FreqBandDAO;
import cm.opsim.model.FreqBand;
import cm.opsim.model.Project;
import cm.opsim.observer.Observable;
import cm.opsim.observer.Observer;

/**
 * @author Romuald FOTSO
 *
 */
public class DialogFreqBand extends JDialog implements Observable{
	// Variables declaration - do not modify                     
    private javax.swing.JButton but_selectFB;
    private javax.swing.JButton but_closeFB;
    private javax.swing.JButton but_newFB;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable tabFB;
    // End of variables declaration    
	private OpsimGUI opsimGUI;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private ArrayList<Observer> listObserver = new ArrayList<Observer>();
    private DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    private FreqBand freqBand;
    private Image icone =  Toolkit.getDefaultToolkit().getImage(OpsimGUI.class.getResource("/cm/opsim/view/img/logo.png"));

	public DialogFreqBand(JDialog parent, boolean modal, Observer obs, OpsimGUI gui) {
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tabFB = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        but_selectFB = new javax.swing.JButton();
        but_newFB = new javax.swing.JButton();
        but_closeFB = new javax.swing.JButton();
        
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Bande de fréquences");
        setModal(true);
        setResizable(false);
        
        DAO<FreqBand> fbDAO = DAOFactory.getFreqBandDAO();
        List<FreqBand> listFBand = fbDAO.findAll();        
        FreqBandTableModel modelFB = new FreqBandTableModel(listFBand);
        tabFB.setModel(modelFB);
        
        String[] toolTipStr = {
                "", "LTE Band ID", "Name of Frequency band", "Uplink (Mhz)", "Downlink (Mhz)", "Allocation (Mhz)",
                "Width of band (Mhz)", "Duplexing Method"
            }; 
        ToolTipHeader header = new ToolTipHeader(tabFB.getColumnModel());
        header.setToolTipStrings(toolTipStr);
        tabFB.setTableHeader(header);
        
        tabFB.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabFB.getColumnModel().getColumn(1).setPreferredWidth(70);
        tabFB.getColumnModel().getColumn(2).setPreferredWidth(150);
        tabFB.getColumnModel().getColumn(3).setPreferredWidth(90);
        tabFB.getColumnModel().getColumn(4).setPreferredWidth(90);
        tabFB.getColumnModel().getColumn(5).setPreferredWidth(90);
        tabFB.getColumnModel().getColumn(6).setPreferredWidth(80);
        tabFB.getColumnModel().getColumn(7).setPreferredWidth(80);
        
        Enumeration columns = tabFB.getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
        	   ((TableColumn) columns.nextElement()).
        	   setCellRenderer(centerRenderer);
        }
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabFB.getModel());
        tabFB.setRowSorter(sorter);
        
        
        
        jScrollPane1.setViewportView(tabFB);
        if (tabFB.getColumnModel().getColumnCount() > 0) {
            tabFB.getColumnModel().getColumn(0).setResizable(false);
            tabFB.getColumnModel().getColumn(0).setPreferredWidth(5);
        }

        but_selectFB.setText("Sélectionner");
        but_selectFB.setEnabled(false);

        but_newFB.setText("Nouveau");
        but_newFB.setEnabled(false);
        but_closeFB.setText("Femer");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(but_selectFB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(but_newFB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(but_closeFB)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(but_selectFB)
                    .addComponent(but_newFB)
                    .addComponent(but_closeFB))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
	}
	
	public void initEvents(){
		this.but_closeFB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	but_closeFBActionPerformed(evt);
            }
        });
		
		this.but_newFB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	but_newFBActionPerformed(evt);
            }
        });
		
		this.but_selectFB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	but_selectFBActionPerformed(evt);
            }
        });
		
		this.tabFB.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						tabFBValueChanged(event);  
					}
				}
		);
	}
	
	public void tabFBValueChanged(ListSelectionEvent evt){
		int viewRow = tabFB.getSelectedRow();
		this.but_selectFB.setEnabled(true);
	}
	
	public void but_closeFBActionPerformed(java.awt.event.ActionEvent evt){
		this.setVisible(false);
	}
	
	public void but_newFBActionPerformed(java.awt.event.ActionEvent evt){
		
	}
	
	public void but_selectFBActionPerformed(java.awt.event.ActionEvent evt){
		int viewRow = tabFB.getSelectedRow();
		String name_fb = tabFB.getModel().getValueAt(viewRow,2).toString();
		System.out.println("FB name = "+name_fb);
		
		FreqBandDAO fbDAO = (FreqBandDAO) DAOFactory.getFreqBandDAO();
		this.freqBand = fbDAO.find(name_fb);
		this.notifyObserver();
		this.setVisible(false);
	}
	
	public static void main(String args[]) {
		DialogFreqBand dialog = new DialogFreqBand(null,true,null,null);
	}
	
	/* (non-Javadoc)
	 * @see cm.opsim.observer.Observable#addObserver(cm.opsim.observer.Observer)
	 */
	@Override
	public void addObserver(Observer obs) {
		System.out.println("DialogFreqBand: Add obs called ");
		this.listObserver.add(obs);		
		System.out.println("DialogFreqBand: Nbre Obs after call = "+this.listObserver.size());
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
	
	public FreqBand getFreqBand() {
		return freqBand;
	}

	public void setFreqBand(FreqBand freqBand) {
		this.freqBand = freqBand;
	}

	class ToolTipHeader extends JTableHeader {
		    String[] toolTips;

		    public ToolTipHeader(TableColumnModel model) {
		      super(model);
		    }

		    public String getToolTipText(MouseEvent e) {
		      int col = columnAtPoint(e.getPoint());
		      int modelCol = getTable().convertColumnIndexToModel(col);
		      String retStr;
		      try {
		        retStr = toolTips[modelCol];
		      } catch (NullPointerException ex) {
		        retStr = "";
		      } catch (ArrayIndexOutOfBoundsException ex) {
		        retStr = "";
		      }
		      if (retStr.length() < 1) {
		        retStr = super.getToolTipText(e);
		      }
		      return retStr;
		    }

		    public void setToolTipStrings(String[] toolTips) {
		      this.toolTips = toolTips;
		    }
	}
}
