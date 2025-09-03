package descriptionupdate.view.utils;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import descriptionupdate.view.factory.GuiFactory;

/**
 * A JTable subclass for displaying data in a non-editable format.
 * This table is used for viewing purposes only, with specific styling and
 * selection behavior.
 */
public class ViewingTable extends JTable {

    /**
     * Constructs a ViewingTable with the specified data and column names.
     * The table is set to be non-editable, with a specific font and row height.
     *
     * @param data        the data to be displayed in the table
     * @param columnNames the names of the columns in the table
     */
    public ViewingTable(Object[][] data, Object[] columnNames) {
        super(
                new DefaultTableModel(data, columnNames) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                });
        this.setFont(GuiFactory.getFont(GuiFactory.FONT, 12));
        this.setRowHeight(30);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setRowSelectionAllowed(false);
        this.setColumnSelectionAllowed(false);
        this.setEnabled(false);
        this.setVisible(true);
    }
}
