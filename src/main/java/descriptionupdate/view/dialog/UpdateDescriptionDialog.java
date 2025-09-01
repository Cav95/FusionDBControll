package descriptionupdate.view.dialog;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import descriptionupdate.model.api.Description;
import descriptionupdate.view.View;
import descriptionupdate.view.exception.BlankDescriptionException;
import descriptionupdate.view.exception.ExistentDescriptionException;
import descriptionupdate.view.factory.GuiFactory;
import descriptionupdate.view.factory.JOptionPaneFactory;
import descriptionupdate.view.utils.ControllUtilies;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ResultPane class that extends JDialog to display the results of the tubular
 * calculations.
 */
public class UpdateDescriptionDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private static final String FONT = "Roboto";
    private static final int SIZE_FONT = 13;

    private JLabel itaLabel = new JLabel("ITA Description:");
    private JLabel engLabel = new JLabel("ENG Description:");

    private JTextField itaTextField = GuiFactory.getTextField(20);
    private JTextField engTextField = GuiFactory.getTextField(20);

    private final JPanel mainPanel = new JPanel();
    private final JPanel titlePannel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private JLabel titleLabel = new JLabel();

    /**
     * Constructor for ResultPane.
     * 
     * @param view       the main view of the application
     * @param title      the title of the dialog
     * @param removeMode whether to remove the dialog after displaying results
     * @param result     the result string to be displayed
     */
    public UpdateDescriptionDialog(final View view, final String title, final String exIta, final String exEng,
            final String exGroup) {

        super(view.getMainFrame(), title, ModalityType.APPLICATION_MODAL);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800, 500);
        this.setMaximumSize(this.getSize());
        this.setLocationRelativeTo(view.getMainFrame());
        this.setResizable(true);

        this.setLayout(new BorderLayout());

        titlePannel.setLayout(new BorderLayout());
        titlePannel.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20));
        this.add(titlePannel, BorderLayout.NORTH);

        titleLabel = new JLabel(
                "Stai aggiornando la descrizione: " + exGroup + " - " + exIta + " - " + exEng);
        titleLabel.setFont(new Font(FONT, Font.BOLD, 15));
        titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        titlePannel.add(titleLabel, BorderLayout.CENTER);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));

        itaTextField.setText(exIta);
        engTextField.setText(exEng);

        mainPanel.add(itaLabel);
        mainPanel.add(itaTextField);

        mainPanel.add(engLabel);
        mainPanel.add(engTextField);

        this.add(mainPanel, BorderLayout.CENTER);

        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        mainPanel.add(bottomPanel, BorderLayout.EAST);

        JButton update = GuiFactory.getButtom("Aggiorna", Color.GRAY, Color.BLACK, GuiFactory.getFont(FONT, SIZE_FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e) {

                        try {
                            var newDescription = new Description(
                                    itaTextField.getText().toUpperCase(),
                                    engTextField.getText().toUpperCase(), exGroup);
                            var oldDescription = new Description(exIta, exEng, exGroup);

                            ControllUtilies.descriptionValidCaracter(newDescription);
                            ControllUtilies.descriptionNotBlank(newDescription);
                            view.getController().updateDescription(oldDescription,
                                    newDescription);
                            JOptionPaneFactory.successfullyAddedDescription(UpdateDescriptionDialog.this,
                                    newDescription);
                            view.getController().setSaved(false);
                            view.goToInitialSceneFiltered();
                            UpdateDescriptionDialog.this.dispose();
                        } catch (IllegalArgumentException t) {
                            JOptionPaneFactory.caractherInvalid(UpdateDescriptionDialog.this);
                        } catch (ExistentDescriptionException o) {
                            JOptionPaneFactory.existedDescription(UpdateDescriptionDialog.this);
                        } catch (BlankDescriptionException ej) {
                            JOptionPaneFactory.blankDescription(UpdateDescriptionDialog.this);
                        } catch (Exception ex) {
                            JOptionPaneFactory.generiError(UpdateDescriptionDialog.this);
                        }
                    }

                });
        bottomPanel.add(update);
    }

}
