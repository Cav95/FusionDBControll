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

import descriptionupdate.view.View;
import descriptionupdate.view.exception.BlankDescriptionException;
import descriptionupdate.view.factory.GuiFactory;
import descriptionupdate.view.factory.JOptionPaneFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * AddDescriptionScene class that extends JDialog to allow users to add a new
 * description.
 */
public abstract class AbstactChangeDialog extends JDialog {

    private static final int SIZE_FONT = 13;

    private static final String FONT = "Roboto";

    final JPanel mainPanel = new JPanel();
    private JPanel northPanel = new JPanel();
    protected JLabel titleLabel = new JLabel("Scene");
    private JLabel itaLabel = GuiFactory.getLabel("ITA Description:", GuiFactory.getFont(FONT, SIZE_FONT), Color.BLACK);
    private JLabel engLabel = GuiFactory.getLabel("ENG Description:", GuiFactory.getFont(FONT, SIZE_FONT), Color.BLACK);
    private JLabel groupLabel = GuiFactory.getLabel("Group", GuiFactory.getFont(FONT, SIZE_FONT), Color.BLACK);
    protected JTextField itaTextField = GuiFactory.getTextField(20);
    protected JTextField engTextField = GuiFactory.getTextField(20);
    protected JComboBox<String> groupTextField;

    protected JButton addButton;
    protected JButton cancelButton;
    protected final View view;

    /**
     * Constructor for AddDescriptionScene.
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

        titleLabel.setFont(new Font(FONT, Font.BOLD, 24));
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

        addButton = GuiFactory.getButtom("Add", Color.GRAY, Color.BLACK, GuiFactory.getFont(FONT, SIZE_FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            action();

                        } catch (IllegalArgumentException t) {
                            JOptionPaneFactory.caractherInvalid(AbstactChangeDialog.this);
                        } catch (BlankDescriptionException o) {
                            JOptionPaneFactory.blankDescription(AbstactChangeDialog.this);
                        } catch (Exception ex) {
                            JOptionPaneFactory.generiError(AbstactChangeDialog.this);

                        }

                    }
                });
        cancelButton = GuiFactory.getButtom("Annulla", Color.RED, Color.WHITE, GuiFactory.getFont(FONT, SIZE_FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        view.goToInitialSceneFiltered();
                        AbstactChangeDialog.this.dispose();
                    }
                });
        mainPanel.add(addButton);
        mainPanel.add(cancelButton);

    }

    public abstract void action();
}
