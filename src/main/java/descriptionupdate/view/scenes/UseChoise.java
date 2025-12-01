package descriptionupdate.view.scenes;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import descriptionupdate.controller.Controller;
import descriptionupdate.controller.ControllerDAO;
import descriptionupdate.model.ModelDescription;
import descriptionupdate.model.ModelHistoryPrint;
import descriptionupdate.view.View;
import descriptionupdate.view.factory.GuiFactory;
import descriptionupdate.view.factory.JOptionPaneFactory;

/**
 * LogInScene class that extends JPanel to create a login scene for the
 * application.
 */
public class UseChoise extends JPanel {

    private static final int SIZE_FONT = 18;

    private final JPanel northPanel = new JPanel();
    private final JPanel centerPanel = new JPanel();
    private final JPanel buttonPanel = new JPanel();
    private final JLabel titleLabel = new JLabel("Description Database");

    private final JButton descriptionButton;
    private final JButton historyButton;
    private final JButton exitButton;

    private final Controller controller;

    /**
     * Constructor for LogInScene.
     *
     * @param view the main view of the application
     */
    public UseChoise(final View view, final String username, final String password) {
        this.controller = new Controller();
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(600, 400)); // Pannello più grande

        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));

        titleLabel.setFont(GuiFactory.getFont(GuiFactory.FONT, 36));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        northPanel.add(titleLabel);
        this.add(northPanel, BorderLayout.NORTH);

        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(60, 20, 60, 20));
        centerPanel.setOpaque(false);

        descriptionButton = GuiFactory.getButton(
                "Descrizioni",
                Color.GRAY,
                Color.BLACK,
                GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        try {
                            var connection = controller.doConnectionDescription(username,
                                    password);
                            var connectionprint = controller.doConnectionHistory(username,
                                    password);
                            view.setController(new ControllerDAO(new ModelDescription(connection), view,
                                    new ModelHistoryPrint(connectionprint)));
                        } catch (Exception el) {
                            JOptionPaneFactory.connectionFailed(UseChoise.this);
                        }
                        view.goToInitialScene();

                    }
                });

        historyButton = GuiFactory.getButton("History", Color.GRAY, Color.BLACK,
                GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        try {
                            var connection = controller.doConnectionHistory(username,
                                    password);
                            view.setController(new ControllerDAO(new ModelDescription(connection), view,
                                    new ModelHistoryPrint(connection)));
                        } catch (Exception el) {
                            JOptionPaneFactory.connectionFailed(UseChoise.this);
                        }
                        view.goToTableCustomScenePrintClean();

                    }
                });

        exitButton = GuiFactory.getButton("Exit", Color.GRAY, Color.BLACK,
                GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        System.exit(0);
                    }
                });
        historyButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(descriptionButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(historyButton);
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(exitButton);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(buttonPanel);
        centerPanel.add(Box.createVerticalGlue());

        this.add(centerPanel, BorderLayout.CENTER);
    }

}
