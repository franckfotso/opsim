/**
 * 
 */
package cm.opsim.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import cm.opsim.model.Project;

/**
 * @author Romuald FOTSO
 *
 */
public class ProjIntTableModel extends AbstractTableModel {

	private List<Project> listProj;
	
	public ProjIntTableModel(List<Project> listProj) {
		this.listProj = new ArrayList<Project>(listProj);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {		
		return 4;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return listProj.size();
	}
	
	@Override
    public String getColumnName(int column) {
        String name = "??";
        switch (column) {
            case 0:
                name = "Name";
                break;
            case 1:
                name = "Progression";
                break;
            case 2:
                name = "Actif";
                break;
            case 3:
                name = "Modifié le";
                break;
        }
        return name;
    }
	
	@Override
    public Class<?> getColumnClass(int columnIndex) {
        Class type = String.class;
        switch (columnIndex) {
            case 0:
            	type = String.class;
            	break;
            case 1:
                type = java.lang.Object.class;
                break;
            case 2:
                type = java.lang.Object.class;
                break;
            case 3:
                type = java.lang.Object.class;
                break;
        }
        //System.out.println("Type for Col: "+columnIndex+" = "+type);
        return type;
    }
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Project proj = listProj.get(rowIndex);
		Object value = null;
        switch (columnIndex) {
            case 0:
                value = proj.getName();
                break;
            case 1:
                value = proj.getProgress()+" %";
                break;
            case 2:
                value = " "+proj.getIsOver();
                break;
            case 3:
            	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                value = dateFormat.format(proj.getUpdatedDate());
                break;
        }        
		return value;
	}

}
