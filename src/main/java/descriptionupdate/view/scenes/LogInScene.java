package descriptionupdate.view.scenes;

import javax.swing.*;

import org.slf4j.Logger;

import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import descriptionupdate.Controller;
import descriptionupdate.data.utils.DAOException;
import descriptionupdate.data.utils.DAOUtils;
import descriptionupdate.model.Model;
import descriptionupdate.view.View;
import descriptionupdate.view.api.UserAdmit;
import descriptionupdate.view.factory.GuiFactory;
import descriptionupdate.view.factory.JOptionPaneFactory;
import descriptionupdate.view.utils.ConnectionFailureViewIni;

/**
 * LogInScene class that extends JPanel to create a login scene for the
 * application.
 */
public class LogInScene extends JPanel {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(LogInScene.class);

    private static final String PDM_USER = "PDMUser";

    private static final String FONT = "Roboto";
    private static final int SIZE_FONT = 18;

    private JPanel northPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JLabel titleLabel = new JLabel("Description Database");
    private JLabel userLabel = new JLabel("Username:");
    private JLabel passLabel = new JLabel("Password:");
    private JTextField userField = new JTextField(20);
    private JPasswordField passField = new JPasswordField(20);
    private JButton accediButton;

    private Connection connection;

    /**
     * Constructor for LogInScene.
     *
     * @param view the main view of the application
     */
    public LogInScene(View view) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(600, 400)); // Pannello più grande

        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));

        titleLabel.setFont(new Font(FONT, Font.BOLD, 32));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        northPanel.add(titleLabel);
        this.add(northPanel, BorderLayout.NORTH);

        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(60, 20, 60, 20));
        centerPanel.setOpaque(false);

        userLabel.setFont(new Font(FONT, Font.PLAIN, 18));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        userField.setMaximumSize(new Dimension(300, 30));
        userField.setAlignmentX(Component.CENTER_ALIGNMENT);


        passLabel.setFont(new Font(FONT, Font.PLAIN, 18));
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        passField.setMaximumSize(new Dimension(300, 30));
        passField.setAlignmentX(Component.CENTER_ALIGNMENT);

        accediButton = GuiFactory.getButtom(
                "Accedi",
                Color.GRAY,
                Color.BLACK,
                GuiFactory.getFont(FONT, SIZE_FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {

                        if (isUserAdmitted(userField.getText(), new String(passField.getPassword()))) {
                            var connection = doConnection(PDM_USER, PDM_USER);

                            view.setController(new Controller(new Model(connection), view));
                            view.goToInitialScene();

                        } else {
                            JOptionPaneFactory.denediedAccess(LogInScene.this);

                        }

                    }
                });

        JButton exitButton = GuiFactory.getButtom("Exit", Color.GRAY, Color.BLACK, GuiFactory.getFont(FONT, SIZE_FONT),
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

    private boolean isUserAdmitted(String username, String psw) {
        return Arrays.asList(UserAdmit.values()).stream()
                .anyMatch(user -> user.getUsername().equals(username) && user.getPsw().equals(psw));
    }

    private Connection doConnection(String username, String psw) {
        LOGGER.info("Attempting connection for user: {}", username);
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // connection = DAOUtils.localMySQLConnection("DesFusion",
            // userField.getText(), new String(passField.getPassword()));
            connection = DAOUtils.localSqlServerConnection("EdmDb_2008_001",
                    username, psw);
            // connection = DAOUtils.localIniStringConnection();

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPaneFactory.connectionFailed(LogInScene.this);
        } catch (DAOException ex) {
            new ConnectionFailureViewIni();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return connection;

    }
}
