package descriptionupdate.view.scenes;

import javax.swing.*;

import descriptionupdate.model.api.PrintCodeValues;
import descriptionupdate.model.filter.FilterPrintImpl;
import descriptionupdate.view.View;
import descriptionupdate.view.factory.GuiFactory;
import descriptionupdate.view.jjpannel.FilterPrintValuesPannel;
import descriptionupdate.view.jjpannel.TableScrollPanePrint;

import java.awt.*;
import java.util.List;

/**
 * MainTableScene class that extends JPanel to create the main table scene for
 * the application.
 */
public class HistoryTableScene extends JPanel {

    private static final int SIZE_FONT = 13;

    private static final long serialVersionUID = 1L;

    private JPanel northPanel = new JPanel();

    // private List<String> listGroup;

    private TableScrollPanePrint tableScrollPanePrint;
    private JPanel buttonPanel;
    private List<PrintCodeValues> des;

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
    public HistoryTableScene(View view) {
        this.view = view;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        // Get descriptions based on filters
        des = view.getController().getPrintHistory();

        // North: Title panel
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        titleLabel.setFont(GuiFactory.getFont(GuiFactory.FONT, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        northPanel.add(titleLabel);
        this.add(northPanel, BorderLayout.NORTH);

        // Center: JTable in JScrollPane
        this.tableScrollPanePrint = new TableScrollPanePrint(des);
        this.add(tableScrollPanePrint, BorderLayout.CENTER);

        // South: Button panel
        buttonPanel = new FilterPrintValuesPannel(this, view);
        this.add(buttonPanel, BorderLayout.SOUTH);

        // this.add(new ButtonExtraPannel(this, view), BorderLayout.EAST);
    }

    /**
     * Returns the JTable contained in the TableScrollPane.
     *
     * @return the JTable
     */
    public JTable getTable() {
        return this.tableScrollPanePrint.getTable();
    }

        /**
     * Refreshes the button panel with a new button panel.
     * 
     * @param newButtonPanel the new button panel to be set
     */
    protected void refreshButtonPanel(FilterPrintValuesPannel newButtonPanel) {
        this.remove(this.buttonPanel);
        this.buttonPanel = newButtonPanel;
        this.add(this.buttonPanel, BorderLayout.SOUTH);
        this.revalidate();
        this.repaint();
    }

    /*
     *  Refreshes the table to display print history based on the provided filter.
     *  @param filter the filter to apply for displaying print history
     */
    protected void refreshTablePrint(FilterPrintImpl filter) {
        this.remove(this.tableScrollPanePrint);
        this.tableScrollPanePrint = new TableScrollPanePrint(view.getController().getPrintHistory());
        this.add(this.tableScrollPanePrint, BorderLayout.CENTER);
        this.remove(this.buttonPanel);
        this.add(new FilterPrintValuesPannel(this, view), BorderLayout.SOUTH);
        this.revalidate();
        this.repaint();
    }
}
