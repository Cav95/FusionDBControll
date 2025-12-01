package descriptionupdate.view.scenes;

import javax.swing.*;

import descriptionupdate.model.api.PrintCodeValues;
import descriptionupdate.view.View;
import descriptionupdate.view.factory.GuiFactory;
import descriptionupdate.view.jjpannel.FilterPrintValuesPannel;
import descriptionupdate.view.jjpannel.TableScrollPanePrint;

import java.awt.*;
import java.util.List;

/**
 * HistoryTableScene class that extends JPanel to create the history table scene
 * for
 * the application.
 */
public class HistoryTableScene extends JPanel {

    private static final int SIZE_FONT = 13;

    private static final long serialVersionUID = 1L;

    private final JPanel northPanel = new JPanel();

    private TableScrollPanePrint tableScrollPanePrint;
    private JPanel buttonPanel;
    private List<PrintCodeValues> des;

    private JLabel titleLabel = GuiFactory.getLabel("Tabella Storico Stampe",
            GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT),
            Color.BLACK);

    private final View view;

    /**
     * Constructor for HistoryTableScene.
     *
     * @param view the main view of the application
     */
    public HistoryTableScene(View view) {
        this.view = view;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        // North: Title panel
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        titleLabel.setFont(GuiFactory.getFont(GuiFactory.FONT, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        northPanel.add(titleLabel);
        this.add(northPanel, BorderLayout.NORTH);

        // South: Button panel
        buttonPanel = new FilterPrintValuesPannel(this, view);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Constructor for HistoryTableScene with print code values.
     *
     * @param view            the main view of the application
     * @param printCodeValues the list of print code values to display in the table
     */
    public HistoryTableScene(View view, List<PrintCodeValues> printCodeValues) {
        this(view);
        this.des = printCodeValues;
        this.tableScrollPanePrint = new TableScrollPanePrint(des);
        this.add(tableScrollPanePrint, BorderLayout.CENTER);
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
     * Refreshes the table to display print history based on the provided filter.
     */
    public void refreshTablePrint() {
        this.tableScrollPanePrint = new TableScrollPanePrint(view.getController().getPrintHistory());
        this.add(this.tableScrollPanePrint, BorderLayout.CENTER);
        this.remove(this.buttonPanel);
        this.add(new FilterPrintValuesPannel(this, view), BorderLayout.SOUTH);
        this.revalidate();
        this.repaint();
    }

}
