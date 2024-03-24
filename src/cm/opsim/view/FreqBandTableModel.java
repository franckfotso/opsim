/**
 * 
 */
package cm.opsim.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import cm.opsim.model.FreqBand;
import cm.opsim.model.Project;

/**
 * @author Romuald FOTSO
 *
 */
public class FreqBandTableModel extends AbstractTableModel {

	private List<FreqBand> listFBand;
	
	public FreqBandTableModel(List<FreqBand> listFBand) {
		this.listFBand = listFBand;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {		
		return 8;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return this.listFBand.size();
	}
	
	@Override
    public String getColumnName(int column) {
        String name = "??";
        switch (column) {
            case 0: name = ""; break;
            case 1: name = "Band ID"; break;
            case 2: name = "Name"; break;
            case 3: name = "UL(Mhz)"; break;
            case 4: name = "DL(Mhz)"; break;
            case 5: name = "Allocation(Mhz)"; break;
            case 6: name = "Width of Band"; break;
            case 7: name = "D. Method"; break;
        }
        return name;
    }
	
	@Override
    public Class<?> getColumnClass(int columnIndex) {
        Class type = String.class;
        switch (columnIndex) {
            case 0: type = String.class;break;
            case 1: type = java.lang.Object.class;break;
            case 2: type = java.lang.Object.class;break;
            case 3: type = java.lang.Object.class;break;
            case 4: type = java.lang.Object.class;break;
            case 5: type = java.lang.Object.class;break;
            case 6: type = java.lang.Object.class;break;
            case 7: type = java.lang.Object.class;break;
        }
        return type;
    }
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		FreqBand freqBand = this.listFBand.get(rowIndex);
		Object value = null;
        switch (columnIndex) {
            case 0: value = ""; break;
            case 1: value = freqBand.getBandNum();break;
            case 2: value = freqBand.getName();break;
            case 3: value = freqBand.getUl();break;
            case 4: value = freqBand.getDl();break;
            case 5: value = freqBand.getAllocation();break;
            case 6: value = freqBand.getBandWidth();break;
            case 7: value = freqBand.getDuplexingMethod();break;
        }        
		return value;
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}

}
