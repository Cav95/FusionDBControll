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
    final List<Description> des;

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

        listGroup = view.getController().getAllGroupTypeString();
        listGroup.add(0, "");
        des = view.getController().getListDescription(
                view.getController().getItaFilterTemp() + ALL,
                view.getController().getEngFilterTemp() + ALL, view.getController().getGroupFilterTemp() + ALL);

        initial(view);

    }

    private void initial(View view) {
        this.setLayout(new BorderLayout());

        // North: Title panel

        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        titleLabel.setFont(GuiFactory.getFont(GuiFactory.FONT, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        northPanel.add(titleLabel);
        this.add(northPanel, BorderLayout.NORTH);

        // Center: JTable in JScrollPane
        this.tableScrollPane = new TableScrollPane(des);
        this.add(tableScrollPane, BorderLayout.CENTER);
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
