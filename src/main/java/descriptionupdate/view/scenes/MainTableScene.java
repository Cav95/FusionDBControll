package descriptionupdate.view.scenes;

import javax.swing.*;
import descriptionupdate.model.api.Description;
import descriptionupdate.view.View;
import descriptionupdate.view.factory.GuiFactory;
import java.awt.*;
import java.util.List;

/**
 * MainTableScene class that extends JPanel to create the main table scene for
 * the application.
 */
public class MainTableScene extends JPanel {
    private static final String ALL = "%";
    private static final int SIZE_FONT = 13;

    private static final long serialVersionUID = 1L;

    private JPanel northPanel = new JPanel();

    private List<String> listGroup;

    private TableScrollPane tableScrollPane;

    private JLabel titleLabel = GuiFactory.getLabel("Tabella Descrizioni",
            GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT),
            Color.BLACK);

    @SuppressWarnings("unused")
    private final View view;

    /**
     * Constructor for MainTableScene.
     *
     * @param view the main view of the application
     */
    public MainTableScene(View view) {
        this.view = view;

        this.listGroup = view.getController().getAllGroupTypeString();
        this.listGroup.add(0, "");
        this.setLayout(new BorderLayout());

        // North: Title panel

        this.northPanel.setLayout(new BoxLayout(this.northPanel, BoxLayout.Y_AXIS));
        this.northPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        this.titleLabel.setFont(GuiFactory.getFont(GuiFactory.FONT, 18));
        this.titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.northPanel.add(this.titleLabel);
        this.add(this.northPanel, BorderLayout.NORTH);

        // Center: JTable in JScrollPane

        final List<Description> des = view.getController().getListDescription(
                view.getController().getItaFilterTemp() + ALL,
                view.getController().getEngFilterTemp() + ALL, view.getController().getGroupFilterTemp() + ALL);

        this.add(new TableScrollPane(des), BorderLayout.CENTER);
        this.setBackground(Color.WHITE);
        this.add(new ButtomMainPannel(this, view, () -> {
            view.goToInitialScene();
            return null;
        }), BorderLayout.SOUTH);
    }

    public JTable getTable() {
        return tableScrollPane.getTable();
    }
}
