package descriptionupdate.view.scenes;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import descriptionupdate.model.api.Description;
import descriptionupdate.view.api.DescrizioneEnum;
import descriptionupdate.view.factory.GuiFactory;
import descriptionupdate.view.utils.SelectionTable;

/**
 * A scroll pane that contains a table for displaying descriptions.
 */
public class TableScrollPaneDes extends JScrollPane implements TableScrollPane {
    private static final long serialVersionUID = 1L;
    private static final int SIZE_FONT = 13;

    private Boolean isAcending;
    private JTable table;

    /**
     * Constructor for TableScrollPane.
     * 
     * @param table the JTable to be placed inside the scroll pane
     */
    public TableScrollPaneDes(List<Description> descriptions) {
        isAcending = true;
        this.table = new SelectionTable(
                descriptions.stream()
                        .map(desc -> new Object[] {
                                desc.group(),
                                desc.itaDescription(),
                                desc.engDescription()
                        })
                        .toArray(Object[][]::new),
                new String[] {
                        DescrizioneEnum.GROUP.getDescription(),
                        DescrizioneEnum.ITA.getDescription(),
                        DescrizioneEnum.ING.getDescription()
                });
        this.setViewportView(table);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.getVerticalScrollBar().setUnitIncrement(16);

        table.setFont(GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT));
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);

        sortColumns(table);
    }

    /**
     * Refreshes the table with new data.
     * 
     * @param descriptions the new list of descriptions to display
     */
    public JTable getTable() {
        return this.table;
    }

    
    private void sortColumns(JTable table) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        this.setPreferredSize(new Dimension(400, 200));
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int colIndex = table.columnAtPoint(e.getPoint());
                if (colIndex != -1) {
                    List<RowSorter.SortKey> sortKeys = new ArrayList<>();
                    if (isAcending)
                        sortKeys.add(new RowSorter.SortKey(colIndex, SortOrder.ASCENDING));
                    else
                        sortKeys.add(new RowSorter.SortKey(colIndex, SortOrder.DESCENDING));
                    isAcending = !isAcending;
                    sorter.setSortKeys(sortKeys);
                    sorter.sort();
                }
            }
        });

    }

}
