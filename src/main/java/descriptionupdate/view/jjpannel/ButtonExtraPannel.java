package descriptionupdate.view.jjpannel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import descriptionupdate.view.View;
import descriptionupdate.view.factory.GuiFactory;
import descriptionupdate.view.scenes.MainTableScene;

/**
 * A panel containing extra buttons for additional functionalities.
 */
public class ButtonExtraPannel extends JPanel {

    private JButton backButton;
    private JButton similarItalian;
    private JButton printHistory;

    /**
     * Constructor for ButtonExtraPannel.
     *
     * @param mainTableScene the main table scene
     * @param view           the main view of the application
     */
    public ButtonExtraPannel(MainTableScene mainTableScene, View view) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);
        similarItalian = GuiFactory.getButton("Simili Italiani", Color.GREEN, Color.BLACK,
                GuiFactory.getFont(GuiFactory.FONT, 10), new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        view.goToTableCustomScene(() -> view.getController().getSimilarItalianDescriptions(view.getController().getFilterDescription()));
                    }
                });
                
        backButton = GuiFactory.getButton("Return", Color.GREEN, Color.BLACK,
                GuiFactory.getFont(GuiFactory.FONT, 10), new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        view.goToInitialScene();
                    }
                });

         printHistory = GuiFactory.getButton("Print History", Color.GREEN, Color.BLACK,
                        GuiFactory.getFont(GuiFactory.FONT, 10), new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                view.goToTableCustomScenePrint(() -> view.getController().getPrintByCodeAndDate());
                            }
                        });

        this.add(similarItalian);
        this.add(backButton);
        this.add(printHistory);
    }

}
