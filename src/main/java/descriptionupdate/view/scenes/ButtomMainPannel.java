package descriptionupdate.view.scenes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import descriptionupdate.model.api.Description;
import descriptionupdate.view.View;
import descriptionupdate.view.dialog.AddDescriptionDialogPreselect;
import descriptionupdate.view.dialog.UpdateDescriptionDialogPreselect;
import descriptionupdate.view.factory.GuiFactory;
import descriptionupdate.view.factory.JOptionPaneFactory;
import descriptionupdate.view.utils.ControllUtilies;

public class ButtomMainPannel extends JPanel {

    private static final int SIZE_FONT = 13;
    
    private JLabel desFilter = new JLabel("Filtro Descrizione:");
    private JTextField itaTextField = GuiFactory.getTextField(20);
    private JLabel engFilter = new JLabel("Filtro Inglese:");
    private JTextField engTextField = GuiFactory.getTextField(20);
    private JLabel groupFilter = new JLabel("Filtro Gruppo:");
    private List<String> listGroup;
    private JComboBox<String> groupTextField;

    private final JButton addButton;
    private final JButton deleteButton;
    private final JButton updateButton;
    private final JButton saveButton;
    private final JButton exitButton;
    private final JButton filterButton;
    private final JButton resetButton;

    private final JPanel greenButtonsPanel = new JPanel();
    private final JPanel filterPanel = new JPanel();

    public ButtomMainPannel(MainTableScene mainTableScene, View view) {

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        listGroup = view.getController().getAllGroupTypeString();
        listGroup.add(0, "");
        this.groupTextField = GuiFactory.getComboBox(listGroup);
        this.groupTextField
                .setSelectedItem(ControllUtilies.reversBlankReturn(view.getController().getGroupFilterTemp()));

        this.itaTextField.setText(ControllUtilies.reversBlankReturn(view.getController().getItaFilterTemp()));
        this.engTextField.setText(ControllUtilies.reversBlankReturn(view.getController().getEngFilterTemp()));

        addButton = GuiFactory.getButtom("Aggiungi", Color.GREEN, Color.BLACK,
                GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        try {
                            var dialog = new AddDescriptionDialogPreselect(view,
                                    ControllUtilies.getDescritionFromTable(mainTableScene.getTable()),
                                    () -> {
                                        view.goToInitialSceneFiltered();
                                        return null;
                                    });
                            dialog.setVisible(true);
                        } catch (Exception ex) {
                            var dialog = new AddDescriptionDialogPreselect(view, new Description("", "", ""), () -> {
                                view.goToInitialSceneFiltered();
                                return null;
                            });
                            dialog.setVisible(true);
                        }
                    }
                });
        deleteButton = GuiFactory.getButtom("Elimina", Color.GREEN, Color.BLACK,
                GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        var description = ControllUtilies.getDescritionFromTable(mainTableScene.getTable());
                        if (JOptionPaneFactory.askDeleteConfirm(mainTableScene,
                                description.itaDescripion() + " - " + description.engDescription() + " - "
                                        + description.group())
                                .equals(JOptionPane.YES_OPTION)) {
                            view.getController().deleteDescription(description);
                            view.goToInitialSceneFiltered();
                        }

                    }
                });
        updateButton = GuiFactory.getButtom("Aggiorna", Color.GREEN, Color.BLACK,
                GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            var description = ControllUtilies.getDescritionFromTable(mainTableScene.getTable());
                            var dialog = new UpdateDescriptionDialogPreselect(view, description,
                                    () -> {
                                        view.goToInitialSceneFiltered();
                                        return null;
                                    });
                            dialog.setVisible(true);
                        } catch (Exception ex) {
                            JOptionPaneFactory.errorNoSelection(mainTableScene);
                        }

                    }
                });
        saveButton = GuiFactory.getButtom("Salva", Color.GREEN, Color.BLACK,
                GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            view.getController().save();
                            JOptionPaneFactory.savedSuccessfully(mainTableScene);
                        } catch (Exception ex) {
                            JOptionPaneFactory.errorOnSave(mainTableScene, ex.getMessage());
                        }
                    }
                });
        exitButton = GuiFactory.getButtom("Exit", Color.GREEN, Color.BLACK,
                GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (view.getController().isSaved()) {
                            view.exitApplication();
                        } else {
                            var result = JOptionPaneFactory.askSaveConfirm(mainTableScene);
                            if (result.equals(JOptionPane.YES_OPTION)) {
                                view.getController().save();
                                JOptionPaneFactory.savedSuccessfully(mainTableScene);
                                view.exitApplication();
                            } else if (result.equals(JOptionPane.NO_OPTION)) {
                                JOptionPaneFactory.saveDiscarded(mainTableScene);
                                view.exitApplication();
                            }

                        }
                    }
                });

        filterButton = GuiFactory.getButtom("Filtra", Color.GRAY, Color.BLACK,
                GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        view.getController().setAllFilterTemp(ControllUtilies.controllBlankReturn(itaTextField),
                                ControllUtilies.controllBlankReturn(engTextField),
                                ControllUtilies
                                        .controllBlankGroup(groupTextField.getSelectedItem().toString().toUpperCase()));

                        view.goToInitialSceneFiltered();
                    }
                });
        resetButton = GuiFactory.getButtom("Reset Filtro", Color.GRAY, Color.BLACK,
                GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        itaTextField.setText("");
                        engTextField.setText("");
                        groupTextField.setSelectedIndex(0);
                        view.getController().resetFilterTemp();
                        view.getController().setSaved(true);
                        view.goToInitialScene();
                    }
                });

        // BoxLayout for green buttons
        
        greenButtonsPanel.setLayout(new BoxLayout(greenButtonsPanel, BoxLayout.X_AXIS));
        greenButtonsPanel.setOpaque(false);

        greenButtonsPanel.add(Box.createHorizontalStrut(10));
        greenButtonsPanel.add(addButton);
        greenButtonsPanel.add(deleteButton);
        greenButtonsPanel.add(updateButton);
        greenButtonsPanel.add(saveButton);
        greenButtonsPanel.add(exitButton);
        greenButtonsPanel.add(Box.createHorizontalStrut(10));

        // BoxLayout for filter components
        
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.X_AXIS));
        filterPanel.setOpaque(false);

        filterPanel.add(desFilter);
        filterPanel.add(itaTextField);
        filterPanel.add(engFilter);
        filterPanel.add(engTextField);
        filterPanel.add(groupFilter);
        filterPanel.add(groupTextField);
        filterPanel.add(filterButton);
        filterPanel.add(Box.createHorizontalStrut(10));
        filterPanel.add(resetButton);
        filterPanel.add(Box.createHorizontalGlue());

        // Add both panels to main panel
        this.add(greenButtonsPanel);
        this.add(Box.createHorizontalStrut(20));
        this.add(filterPanel);
    }
}
