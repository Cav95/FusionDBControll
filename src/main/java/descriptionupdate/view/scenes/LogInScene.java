package descriptionupdate.view.scenes;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import descriptionupdate.controller.Controller;
import descriptionupdate.view.View;
import descriptionupdate.view.factory.GuiFactory;
import descriptionupdate.view.factory.JOptionPaneFactory;

/**
 * LogInScene class that extends JPanel to create a login scene for the
 * application.
 */
public class LogInScene extends JPanel {

    private static final int SIZE_FONT = 18;

    private final JPanel northPanel = new JPanel();
    private final JPanel centerPanel = new JPanel();
    private final JPanel buttonPanel = new JPanel();
    private final JLabel titleLabel = new JLabel("Fusion database Utilities");
    private final JLabel userLabel = new JLabel("Username:");
    private final JLabel passLabel = new JLabel("Password:");
    private final JTextField userField = new JTextField(20);
    private final JPasswordField passField = new JPasswordField(20);

    private final JButton accediButton;
    private final JButton exitButton;

    private final Controller controller;

    /**
     * Constructor for LogInScene.
     *
     * @param view the main view of the application
     */
    public LogInScene(final View view) {
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

        userLabel.setFont(GuiFactory.getFont(GuiFactory.FONT, 18));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        userField.setMaximumSize(new Dimension(300, 30));
        userField.setAlignmentX(Component.CENTER_ALIGNMENT);

        passLabel.setFont(GuiFactory.getFont(GuiFactory.FONT, 18));
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        passField.setMaximumSize(new Dimension(300, 30));
        passField.setAlignmentX(Component.CENTER_ALIGNMENT);

        accediButton = GuiFactory.getButton(
                "Accedi",
                Color.GRAY,
                Color.BLACK,
                GuiFactory.getFont(GuiFactory.FONT, SIZE_FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {

                        if (controller.isUserAdmitted(userField.getText(), new String(passField.getPassword()))) {

                            view.goToChoiseScene(userField.getText(), new String(passField.getPassword()));

                        } else {
                            JOptionPaneFactory.denediedAccess(LogInScene.this);

                        }

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
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(accediButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(exitButton);
        buttonPanel.add(Box.createHorizontalGlue());

        centerPanel.add(userLabel);
        centerPanel.add(userField);
        centerPanel.add(passLabel);
        centerPanel.add(passField);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(buttonPanel);
        centerPanel.add(Box.createVerticalGlue());

        this.add(centerPanel, BorderLayout.CENTER);
    }

}
