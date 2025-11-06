package descriptionupdate.view.jjpannel;

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

import descriptionupdate.model.api.PrintCodeValues;
import descriptionupdate.view.factory.GuiFactory;
import descriptionupdate.view.utils.SelectionTable;

/**
 * TableScrollPanePrint class that extends JScrollPane to create a scrollable table for
 * displaying print code values.
 */
public class TableScrollPanePrint extends JScrollPane implements TableScrollPane {
    private static final long serialVersionUID = 1L;
    private static final int SIZE_FONT = 13;

    private Boolean isAcending;
    private JTable table;


    public TableScrollPanePrint(List<PrintCodeValues> printCodeValues) {
        isAcending = true;
        this.table = new SelectionTable(
                printCodeValues.stream()
                        .map(desc -> new Object[] {
                                desc.codice(),
                                desc.officina(),
                                desc.preassemblaggio(),
                                desc.sartoria(),
                                desc.prodottoFinito(),
                                desc.spedizione(),
                                desc.montatori(),
                                desc.ufficioAcquisti(),
                                desc.dataValidita()
                        })
                        .toArray(Object[][]::new),
                new String[] {
                        "Code",
                        "Officina",
                        "Preassemblaggio",
                        "Sartoria",
                        "Prodotto Finito",
                        "Spedizione",
                        "Montatori",
                        "Ufficio Acquisti",
                        "Data Fine Validita"
                });
        this.setViewportView(table);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.getVerticalScrollBar().setUnitIncrement(16);

        table.setFont(GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT));
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);
        table.getColumnModel().getColumn(6).setPreferredWidth(150);
        table.getColumnModel().getColumn(7).setPreferredWidth(150);
        table.getColumnModel().getColumn(8).setPreferredWidth(150);

        sortColumns(table);
    };

    /**
     * Returns the JTable contained in this scroll pane.
     *
     * @return the JTable
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
