package descriptionupdate.view.dialog.api;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import descriptionupdate.model.api.Description;
import descriptionupdate.view.View;
import descriptionupdate.view.api.DescrizioneEnum;
import descriptionupdate.view.exception.BlankDescriptionException;
import descriptionupdate.view.exception.ExistentDescriptionException;
import descriptionupdate.view.factory.GuiFactory;
import descriptionupdate.view.factory.JOptionPaneFactory;
import descriptionupdate.view.utils.ControllUtilies;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Supplier;

/**
 * Abstract class representing a dialog for adding or changing descriptions.
 * 
 */
public abstract class AbstactChangeDialog extends JDialog {

    private static final int SIZE_FONT = 13;

    final JPanel mainPanel = new JPanel();
    private JPanel northPanel = new JPanel();
    protected JLabel titleLabel = new JLabel("Scene");
    private JLabel itaLabel = GuiFactory.getLabel(DescrizioneEnum.ITA.getDescription(),
            GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT), Color.BLACK);
    private JLabel engLabel = GuiFactory.getLabel(DescrizioneEnum.ING.getDescription(),
            GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT), Color.BLACK);
    private JLabel groupLabel = GuiFactory.getLabel(DescrizioneEnum.GROUP.getDescription(),
            GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT), Color.BLACK);
    protected JTextField itaTextField = GuiFactory.getTextField(20);
    protected JTextField engTextField = GuiFactory.getTextField(20);
    protected JComboBox<String> groupTextField;

    protected JButton addButton;
    protected JButton cancelButton;
    protected final View view;

    protected Supplier<Void> refreshAction;

    /**
     * Constructor for AbstactChangeDialog.
     * 
     * @param view the main view of the application
     */
    public AbstactChangeDialog(final View view) {
        this.view = view;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1200, 500);
        this.setMaximumSize(this.getSize());
        this.setLocationRelativeTo(view.getMainFrame());
        this.setResizable(true);

        this.setLayout(new BorderLayout());

        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        titleLabel.setFont(GuiFactory.getFont(GuiFactory.FONT, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        northPanel.add(titleLabel);
        this.add(northPanel, BorderLayout.NORTH);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));

        groupTextField = GuiFactory.getComboBox(view.getController().getAllGroupTypeString());
        mainPanel.add(itaLabel);
        mainPanel.add(itaTextField);

        mainPanel.add(engLabel);
        mainPanel.add(engTextField);

        mainPanel.add(groupLabel);
        mainPanel.add(groupTextField);
        this.add(mainPanel, BorderLayout.CENTER);

        addButton = GuiFactory.getButton("Add", Color.GRAY, Color.BLACK, GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            var newDescription = new Description(itaTextField.getText().toUpperCase(),
                                    engTextField.getText().toUpperCase(),
                                    groupTextField.getSelectedItem().toString().toUpperCase());
                            ControllUtilies.descriptionValidCaracter(newDescription);
                            ControllUtilies.descriptionNotBlank(newDescription);
                            action(newDescription);

                        } catch (IllegalArgumentException t) {
                            JOptionPaneFactory.caractherInvalid(AbstactChangeDialog.this);
                        } catch (BlankDescriptionException o) {
                            JOptionPaneFactory.blankDescription(AbstactChangeDialog.this);
                        } catch (ExistentDescriptionException t) {
                            JOptionPaneFactory.existedDescription(AbstactChangeDialog.this);
                        } catch (Exception ex) {
                            JOptionPaneFactory.generiError(AbstactChangeDialog.this);

                        }

                    }
                });
        cancelButton = GuiFactory.getButton("Annulla", Color.RED, Color.WHITE,
                GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        refreshAction.get();
                        AbstactChangeDialog.this.dispose();
                    }
                });
        mainPanel.add(addButton);
        mainPanel.add(cancelButton);

    }

    /**
     * Abstract method to be implemented by subclasses for specific actions.
     *
     * @param newDescription the new description to process.
     */
    public abstract void action(Description newDescription);
}
