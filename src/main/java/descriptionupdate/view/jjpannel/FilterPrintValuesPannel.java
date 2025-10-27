package descriptionupdate.view.jjpannel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Supplier;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import descriptionupdate.model.filter.api.FilterPrintValues;
import descriptionupdate.view.View;
import descriptionupdate.view.factory.GuiFactory;
import descriptionupdate.view.scenes.MainTableScene;

public class FilterPrintValuesPannel extends JPanel {
    private static final String ALL = "%";

    private static final int SIZE_FONT = 13;

    private JLabel codeFilterJLabel = new JLabel("Codice");
    private JTextField codeTextField = GuiFactory.getTextField(20);
    private JLabel dateLabel = new JLabel("Data:");
    private JTextField dateTextField = GuiFactory.getTextField(20);

    private JComboBox<String> dateFilter;

    private final JButton filterButton;
    private final JButton resetButton;

    private final JPanel filterPanel = new JPanel();

    private Supplier<Void> refreshAction;

    /**
     * Constructor for FilterPrintValuesPannel.
     *
     * @param mainTableScene the main table scene
     * @param view           the main view of the application
     * @param refreshAction  a Supplier<Void> representing the action to be
     *                       performed on refresh
     */
    public FilterPrintValuesPannel(MainTableScene mainTableScene, View view) {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        // Create date filter with sample dates (you may want to modify this)
        List<String> dates = view.getController().getAvailableDates(ALL);
        this.dateFilter = GuiFactory.getComboBox(dates);

        filterButton = GuiFactory.getButton("Filtra", Color.ORANGE, Color.BLACK,
                GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        view.getController().setCurrentPrintCodeValues(new FilterPrintValues(
                                codeTextField.getText(),
                                dateFilter.getSelectedItem().toString()));
                        FilterPrintValuesPannel.this.refreshAction.get();
                    }
                });
        // Initialize buttons
        resetButton = GuiFactory.getButton("Reset", Color.ORANGE, Color.BLACK,
                GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        view.getController().setCurrentPrintCodeValues(new FilterPrintValues(ALL, ALL));
                        resetFilters();
                        FilterPrintValuesPannel.this.refreshAction.get();
                    }
                });

        // BoxLayout for filter components
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.X_AXIS));
        filterPanel.setOpaque(false);
        filterPanel.add(Box.createHorizontalGlue());
        filterPanel.add(codeFilterJLabel);
        filterPanel.add(codeTextField);
        filterPanel.add(Box.createHorizontalStrut(5));
        filterPanel.add(dateLabel);
        filterPanel.add(dateFilter);
        filterPanel.add(Box.createHorizontalStrut(10));
        filterPanel.add(filterButton);
        filterPanel.add(Box.createHorizontalStrut(5));
        filterPanel.add(resetButton);
        filterPanel.add(Box.createHorizontalGlue());

        this.add(Box.createVerticalStrut(10));
        this.add(filterPanel);
    }

    /**
     * Sets the refresh action to be performed when the refresh button is clicked.
     *
     * @param refreshAction a Supplier<Void> representing the action to be performed
     */
    public void setRefreshAction(Supplier<Void> refreshAction) {
        this.refreshAction = refreshAction;
    }

    /**
     * Resets all filter selections to their default values.
     */
    private void resetFilters() {

        codeTextField.setText("");
        dateTextField.setText("");
        dateFilter.setSelectedIndex(0);
    }

}
