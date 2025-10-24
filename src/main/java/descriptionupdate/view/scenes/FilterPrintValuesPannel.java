package descriptionupdate.view.scenes;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import descriptionupdate.model.api.Description;
import descriptionupdate.view.View;
import descriptionupdate.view.factory.GuiFactory;

public class FilterPrintValuesPannel extends JPanel {
    private static final int SIZE_FONT = 13;

    private JLabel codeFilterh = new JLabel("Codice");
    private JTextField codeTextField = GuiFactory.getTextField(20);
    private JLabel desFilter = new JLabel("Filtro Codice:");
    private JLabel officinaLabel = new JLabel("Officina:");
    private JLabel spedizioneLabel = new JLabel("Spedizione:");
    private JLabel montatoriLabel = new JLabel("Montatori:");
    private JLabel preassembleggioLabel = new JLabel("Preassembleggio:");
    private JLabel prodottoFinitoLabel = new JLabel("Prodotto Finito:");
    private JLabel ufficioAcquistiLabel = new JLabel("Ufficio Acquisti:");
    private JLabel dateLabel = new JLabel("Data:");

    

    private List<String> codeFilter = List.of("", "SI", "NO");
    private JComboBox<String> officinaSelect;
    private JComboBox<String> spedizioneSelect;
    private JComboBox<String> montatoriSelect;
    private JComboBox<String> preassembleggioSelect;
    private JComboBox<String> prodottoFinitoSelect;
    private JComboBox<String> ufficioAcquistiSelect;
    private JComboBox<String> dateFilter;

    private final JButton filterButton;
    private final JButton resetButton;
    
    private final JPanel greenButtonsPanel = new JPanel();
    private final JPanel filterPanel = new JPanel();

    private Supplier<Void> refreshAction;

    /**
     * Constructor for FilterPrintValuesPannel.
     *
     * @param mainTableScene the main table scene
     * @param view           the main view of the application
     * @param refreshAction  a Supplier<Void> representing the action to be performed on refresh
     */
    public FilterPrintValuesPannel(MainTableScene mainTableScene, View view) {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        // Initialize combo boxes with default values
        this.officinaSelect = GuiFactory.getComboBox(codeFilter);
        this.spedizioneSelect = GuiFactory.getComboBox(codeFilter);
        this.montatoriSelect = GuiFactory.getComboBox(codeFilter);
        this.preassembleggioSelect = GuiFactory.getComboBox(codeFilter);
        this.prodottoFinitoSelect = GuiFactory.getComboBox(codeFilter);
        this.ufficioAcquistiSelect = GuiFactory.getComboBox(codeFilter);
        
        // Create date filter with sample dates (you may want to modify this)
        List<String> dates = view.getController().getAvailableDates("%");
        this.dateFilter = GuiFactory.getComboBox(dates);

        filterButton = GuiFactory.getButton("Filtra", Color.ORANGE, Color.BLACK,
                GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        FilterPrintValuesPannel.this.refreshAction.get();
                    }
                });
        // Initialize buttons
        resetButton = GuiFactory.getButton("Reset", Color.ORANGE, Color.BLACK,
                GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        resetFilters();
                        FilterPrintValuesPannel.this.refreshAction.get();
                    }
                });

        // Setup green buttons panel
        greenButtonsPanel.setLayout(new BoxLayout(greenButtonsPanel, BoxLayout.X_AXIS));
        greenButtonsPanel.setOpaque(false);
        greenButtonsPanel.add(Box.createHorizontalStrut(10));

        // BoxLayout for filter components
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.X_AXIS));
        filterPanel.setOpaque(false);

        filterPanel.add(desFilter);
        filterPanel.add(Box.createHorizontalStrut(5));
        filterPanel.add(officinaLabel);
        filterPanel.add(officinaSelect);
        filterPanel.add(Box.createHorizontalStrut(5));
        filterPanel.add(spedizioneLabel);
        filterPanel.add(spedizioneSelect);
        filterPanel.add(Box.createHorizontalStrut(5));
        filterPanel.add(montatoriLabel);
        filterPanel.add(montatoriSelect);
        filterPanel.add(Box.createHorizontalStrut(5));
        filterPanel.add(preassembleggioLabel);
        filterPanel.add(preassembleggioSelect);
        filterPanel.add(Box.createHorizontalStrut(5));
        filterPanel.add(prodottoFinitoLabel);
        filterPanel.add(prodottoFinitoSelect);
        filterPanel.add(Box.createHorizontalStrut(5));
        filterPanel.add(ufficioAcquistiLabel);
        filterPanel.add(ufficioAcquistiSelect);
        filterPanel.add(Box.createHorizontalStrut(5));
        filterPanel.add(dateLabel);
        filterPanel.add(dateFilter);
        filterPanel.add(Box.createHorizontalStrut(10));
        filterPanel.add(filterButton);
        filterPanel.add(Box.createHorizontalStrut(5));
        filterPanel.add(resetButton);
        filterPanel.add(Box.createHorizontalGlue());

        // Add both panels to main panel
        this.add(greenButtonsPanel);
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
        officinaSelect.setSelectedIndex(0);
        spedizioneSelect.setSelectedIndex(0);
        montatoriSelect.setSelectedIndex(0);
        preassembleggioSelect.setSelectedIndex(0);
        prodottoFinitoSelect.setSelectedIndex(0);
        ufficioAcquistiSelect.setSelectedIndex(0);
        dateFilter.setSelectedIndex(0);
    }


    /**
     * Gets the currently selected filter values.
     * 
     * @return a Description object representing the current filter state
     */
    public Description getCurrentFilter() {
        // This is a placeholder - you might want to create a more specific filter object
        String officina = (String) officinaSelect.getSelectedItem();
        String spedizione = (String) spedizioneSelect.getSelectedItem();
        String montatori = (String) montatoriSelect.getSelectedItem();
        
        // Return a description with concatenated filter values
        return new Description(
            officina != null ? officina : "",
            spedizione != null ? spedizione : "",
            montatori != null ? montatori : ""
        );
    }
}
