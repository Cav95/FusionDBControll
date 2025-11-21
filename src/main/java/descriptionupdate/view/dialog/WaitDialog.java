package descriptionupdate.view.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
import descriptionupdate.view.factory.GuiFactory;

public class WaitDialog  extends JDialog {
    private static final long serialVersionUID = 1L;

    private JPanel northPanel = new JPanel();
    protected JLabel titleLabel = new JLabel("Attendere prego...");

    /**
     * Constructor for WaitDialog.
     *
     * @param owner the owner frame of the dialog
     * @param title the title of the dialog
     * @param modal whether the dialog is modal
     */
    public WaitDialog(final View view) {
        //this.view = view;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(50, 50);
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

    }
    
}
