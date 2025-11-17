package descriptionupdate.view.utils;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * A JTable subclass that allows
 * selection of rows but does not allow editing.
 * This table is used for selection purposes only,
 * with specific styling and selection behavior.
 */
public class SelectionTable extends JTable {

    /**
     * Constructs a SelectionTable with the specified data and column names.
     * The table is set to be non-editable, with a specific font and row height.
     *
     * @param data        the data to be displayed in the table
     * @param columnNames the names of the columns in the table
     */
    public SelectionTable(Object[][] data, Object[] columnNames) {
        super(
                new DefaultTableModel(data, columnNames) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                });
        this.setFont(new Font("Roboto", Font.PLAIN, 16));
        this.setRowHeight(30);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setRowSelectionAllowed(true);
        this.setColumnSelectionAllowed(false);
        this.setEnabled(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setModel(TableModel model) {
        super.setModel(model);
    }
}