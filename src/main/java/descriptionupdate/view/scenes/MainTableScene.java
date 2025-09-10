package descriptionupdate.view.scenes;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import descriptionupdate.model.api.Description;
import descriptionupdate.view.View;
import descriptionupdate.view.api.DescrizioneEnum;
import descriptionupdate.view.factory.GuiFactory;
import descriptionupdate.view.utils.SelectionTable;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * MainTableScene class that extends JPanel to create the main table scene for
 * the application.
 */
public class MainTableScene extends JPanel {
    private static final String ALL = "%";
    private static final int SIZE_FONT = 13;

    private static final long serialVersionUID = 1L;

    private String itaDescription;
    private String engDescription;
    private String group;

    private Boolean isAcending;

    private JPanel northPanel = new JPanel();

    private List<String> listGroup;

    private JTable table;

    private JLabel titleLabel = GuiFactory.getLabel("Tabella Descrizioni",
            GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT),
            Color.BLACK);

    @SuppressWarnings("unused")
    private final View view;

    /**
     * Constructor for MainTableScene.
     *
     * @param view the main view of the application
     */
    public MainTableScene(View view) {
        this.view = view;
        this.itaDescription = ALL;
        this.engDescription = ALL;
        this.group = ALL;

        listGroup = view.getController().getAllGroupTypeString();
        listGroup.add(0, "");
        initial(view);

    }

    /**
     * Constructor for MainTableScene with specific descriptions and group.
     *
     * @param view           the main view of the application
     * @param itaDescription Italian description to filter
     * @param engDescription English description to filter
     * @param group          group to filter
     */
    public MainTableScene(View view, String itaDescription, String engDescription, String group) {
        this.view = view;
        this.itaDescription = itaDescription;
        this.engDescription = engDescription;
        this.group = group;
        this.table = new JTable();
        initial(view);
    }

    private void initial(View view) {
        isAcending = true;

        this.setLayout(new BorderLayout());

        // North: Title panel

        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        titleLabel.setFont(GuiFactory.getFont(GuiFactory.FONT, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        northPanel.add(titleLabel);
        this.add(northPanel, BorderLayout.NORTH);

        // Center: JTable in JScrollPane

        final List<Description> des = view.getController().getListDescription(itaDescription + ALL,
                engDescription + ALL, group + ALL);

        table = new SelectionTable(
                des.stream()
                        .map(desc -> new Object[] {
                                desc.group(),
                                desc.itaDescripion(),
                                desc.engDescription()
                        })
                        .toArray(Object[][]::new),
                new String[] {
                        DescrizioneEnum.GROUP.getDescription(),
                        DescrizioneEnum.ITA.getDescription(),
                        DescrizioneEnum.ING.getDescription()
                });
        table.setFont(GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT));
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        this.add(scrollPane, BorderLayout.CENTER);
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

        this.setBackground(Color.WHITE);
        this.add(new ButtomMainPannel(this, view, itaDescription, engDescription, group), BorderLayout.SOUTH);
    }

    public JTable getTable() {
        return this.table;

    }
}
