package descriptionupdate.view.jjpannel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.function.Supplier;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import descriptionupdate.model.filter.api.FilterPrintValues;
import descriptionupdate.view.View;
import descriptionupdate.view.dialog.WaitDialog;
import descriptionupdate.view.exception.BlankDescriptionException;
import descriptionupdate.view.factory.GuiFactory;
import descriptionupdate.view.factory.JOptionPaneFactory;
import descriptionupdate.view.scenes.HistoryTableScene;
import descriptionupdate.view.utils.ControllUtilies;

import com.toedter.calendar.JCalendar;

/*
    * A panel for filtering print values.
 */
public class FilterPrintValuesPannel extends JPanel {
    private static final int SIZE_FONT = 13;

    private JLabel codeFilterJLabel = new JLabel("Codice");
    private JTextField codeTextField = GuiFactory.getTextField(20);
    private JLabel dateLabel = new JLabel("Data:");

    private final JButton filterButton;
    private final JButton resetButton;

    private final JPanel filterPanel = new JPanel();

    View view;

    /**
     * Constructor for FilterPrintValuesPannel.
     *
     * @param mainTableScene the main table scene
     * @param view           the main view of the application
     * @param refreshAction  a Supplier<Void> representing the action to be
     *                       performed on refresh
     */
    public FilterPrintValuesPannel(HistoryTableScene mainTableScene, View view) {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        this.view = view;

        codeTextField
                .setText(ControllUtilies.reversBlankReturn(view.getController().getFilterPrint().getFilter().code()));

        JCalendar calendar = new JCalendar();

        if (view.getController().getFilterPrint().getFilter().dateValue() != "2099-12-31") {
            calendar.setDate(Date.valueOf(view.getController().getFilterPrint().getFilter().dateValue()));
        }

        filterButton = GuiFactory.getButton("Filtra", Color.ORANGE, Color.BLACK,
                GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        var date = String.format("%1$tY-%1$tm-%1$td", calendar.getDate());
                        try {
                            if (codeTextField.getText().isBlank()) {
                                throw new BlankDescriptionException("Il campo codice non può esere vuoto");
                            }
                            view.getController().setCurrentPrintCodeValues(new FilterPrintValues(
                                    codeTextField.getText(),
                                    date));
                            goToNewFilteredScene();

                        } catch (BlankDescriptionException ex) {
                            JOptionPaneFactory.errorNoCodeSelection(FilterPrintValuesPannel.this);
                        }

                    }
                });
        // Initialize buttons
        resetButton = GuiFactory.getButton("Reset", Color.ORANGE, Color.BLACK,
                GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        view.getController().setCurrentPrintCodeValues(new FilterPrintValues());
                        view.goToTableCustomScenePrintClean();
                    }
                });

        JButton exitButton = GuiFactory.getButton("Exit", Color.ORANGE, Color.BLACK,
                GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
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
        filterPanel.add(calendar);
        filterPanel.add(Box.createHorizontalStrut(10));
        filterPanel.add(filterButton);
        filterPanel.add(Box.createHorizontalStrut(5));
        filterPanel.add(resetButton);
        filterPanel.add(Box.createHorizontalStrut(5));
        filterPanel.add(exitButton);
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
    }

    private void goToNewFilteredScene() {

        Thread process = new Thread(() -> {
            try {
                view.goToTableCustomScenePrint(view.getController().getPrintHistory());
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }

        });
        
        Thread wait = new Thread(() -> {
            var waitDialog = new WaitDialog(view);

            waitDialog.setVisible(true);

            while (process.isAlive()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            
            Thread.currentThread().interrupt();

        });
        wait.start();
        process.start();

        // JOptionPaneFactory.waitMessagge(this);

        try {
            process.join();

        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

}
