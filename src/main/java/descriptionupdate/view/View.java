package descriptionupdate.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import javax.swing.JFrame;
import javax.swing.JPanel;

import descriptionupdate.controller.ControllerDAO;
import descriptionupdate.model.api.Description;
import descriptionupdate.model.filter.FilterPrintImpl;
import descriptionupdate.view.scenes.LogInScene;
import descriptionupdate.view.scenes.MainTableScene;
import descriptionupdate.view.scenes.MainTableSceneCustom;

/**
 * Main application view.
 */
public final class View {

    private static final String SN_WELCOME = "welcome";
    private static final String SN_SECOND = "SecondScene";

    private static final double FRAME_SIZE_FACTOR = 0.8;

    private Optional<ControllerDAO> controller;
    private final JFrame mainFrame;
    private final JPanel mainPanel;
    private final CardLayout cardLayout;

    /**
     * Constructs a new View instance.
     *
     * @param onClose a Runnable to be executed when the window is closed
     */
    public View() {
        this.controller = Optional.empty();
        this.mainFrame = new JFrame("Description Database");
        this.mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final var initWidth = (int) (screenSize.width * FRAME_SIZE_FACTOR);
        final var initHeight = (int) (screenSize.height * FRAME_SIZE_FACTOR);
        this.mainFrame.setSize(new Dimension(initWidth, initHeight));
        this.mainFrame.setMinimumSize(new Dimension(800, 600));

        this.cardLayout = new CardLayout();
        this.mainPanel = new JPanel(cardLayout);
        this.mainFrame.setContentPane(this.mainPanel);

        this.mainPanel.add(new LogInScene(this), SN_SECOND);

        this.mainFrame.setLocationByPlatform(true);
        this.mainFrame.setVisible(true);
    }

    /**
     * Returns the main frame of the application.
     * 
     * @return the main frame
     */
    public JFrame getMainFrame() {
        return this.mainFrame;
    }

    /**
     * Returns the Controller associated with this View.
     *
     * @return the Controller
     */
    public ControllerDAO getController() {
        if (this.controller.isPresent()) {
            return this.controller.get();
        } else {
            throw new IllegalStateException(
                    """
                            The View's Controller is undefined, did you remember to call
                            `setController` before starting the application?
                            Remeber that `View` needs a reference to the controller in order
                            to notify it of button clicks and other changes.
                            """);
        }
    }

    /**
     * Sets the Controller for this View.
     *
     * @param controller the Controller to set
     * @throws NullPointerException if the provided controller is null
     */
    public void setController(final ControllerDAO controller) {
        Objects.requireNonNull(controller, "Set null controller in view");
        this.controller = Optional.of(controller);
    }

    /**
     * Navigates to the initial scene of the application.
     */
    public void goToInitialScene() {
        this.mainPanel.add(new MainTableScene(this), SN_WELCOME);
        this.cardLayout.show(this.mainPanel, SN_WELCOME);
    }


    public void goToTableCustomScene(Supplier<List<Description>> descriptions) {
        this.mainPanel.add(new MainTableSceneCustom(this, descriptions), "TableCustomScene");
        this.cardLayout.show(this.mainPanel, "TableCustomScene");
    }

    public void goToTableCustomScenePrint() {
        this.mainPanel.add(new MainTableSceneCustom(this, getController().getFilterPrint()), "TableCustomScene");
        this.cardLayout.show(this.mainPanel, "TableCustomScene");
    }

    /**
     * Exits the application.
     */
    public void exitApplication() {
        System.exit(0);
    }

    /**
     * Navigates to the second scene of the application.
     */
    public void goToLoginScene() {
        this.mainPanel.add(new LogInScene(this), SN_SECOND);
        this.cardLayout.show(this.mainPanel, SN_SECOND);
    }

}
