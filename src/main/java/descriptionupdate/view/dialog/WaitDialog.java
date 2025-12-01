package descriptionupdate.view.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import descriptionupdate.view.View;
import descriptionupdate.view.factory.GuiFactory;

/*
 * A dialog that shows a waiting message.
 */
public class WaitDialog extends JDialog {
    private static final long serialVersionUID = 1L;

    private JPanel northPanel = new JPanel();
    private JLabel titleLabel = new JLabel();

    /**
     * Constructor for WaitDialog.
     * 
     * @param view the main view
     */
    public WaitDialog(final View view) {
        //var count = 1;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(300, 150);
        this.setAlwaysOnTop(true);
        this.setMaximumSize(this.getSize());
        this.setLocationRelativeTo(view.getMainFrame());
        this.setResizable(false);

        this.setLayout(new BorderLayout());

        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        titleLabel.setFont(GuiFactory.getFont(GuiFactory.FONT, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        northPanel.add(titleLabel);
        this.add(northPanel, BorderLayout.NORTH);
        titleLabel.setText("Attendere prego");


    }

}
