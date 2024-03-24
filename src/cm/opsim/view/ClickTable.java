/**
 * 
 */
package cm.opsim.view;

/**
 * @author Romuald FOTSO
 *
 */
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.AbstractTableModel;

public class ClickTable {

    public static void main(String[] args) {
        new ClickTable();
    }

    public ClickTable() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                List<Click> clicks = new ArrayList<>(25);
                clicks.add(new Click(620, 1028));
                clicks.add(new Click(480, 230));
                ClickTableModel model = new ClickTableModel(clicks);
                JTable table = new JTable(model);

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new JScrollPane(table));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class Click {

        private int x;
        private int y;

        public Click(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

    }

    public class ClickTableModel extends AbstractTableModel {

        private List<Click> clicks;

        public ClickTableModel(List<Click> clicks) {
            this.clicks = new ArrayList<>(clicks);
        }

        @Override
        public int getRowCount() {
            return clicks.size();
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public String getColumnName(int column) {
            String name = "??";
            switch (column) {
                case 0:
                    name = "Mouse X";
                    break;
                case 1:
                    name = "Mouse Y";
                    break;
            }
            return name;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            Class type = String.class;
            switch (columnIndex) {
                case 0:
                case 1:
                    type = Integer.class;
                    break;
            }
            return type;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Click click = clicks.get(rowIndex);
            Object value = null;
            switch (columnIndex) {
                case 0:
                    value = click.getX();
                    break;
                case 1:
                    value = click.getY();
                    break;
            }
            return value;
        }            
    }        
}
