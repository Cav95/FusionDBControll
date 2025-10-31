package descriptionupdate.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import org.slf4j.Logger;

import descriptionupdate.data.utils.DAOException;
import descriptionupdate.data.utils.DAOUtils;
import descriptionupdate.view.api.UserAdmit;
import descriptionupdate.view.utils.ConnectionFailureViewIni;

/**
 * Factory class for managing database connections and user admission.
 */
public class ConnectionFactory {

    private static final String BOMB_CONFINE = "bomb_Confine";
    private static final String CEPIUT = "CEPIUT";
    private static final String EDM_DB_2008_001 = "EdmDb_2008_001";
    private static final String ADHOC = "adhoc";
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ConnectionFactory.class);
    private Connection connection;
    private static final String PDM_USER = "PDMUser";

    /**
     * Checks if the user is admitted based on username and password.
     *
     * @param username the username to check
     * @param psw      the password to check
     * @return true if the user is admitted, false otherwise
     */
    public boolean isUserAdmitted(final String username, final String psw) {
        return Arrays.asList(UserAdmit.values()).stream()
                .anyMatch(user -> user.getUsername().equals(username) && user.getPsw().equals(psw));
    }

    /**
     * Establishes a production SQL Server database connection.
     *
     * @param username the username for the connection
     * @param psw      the password for the connection
     * @return the established SQL Connection object
     */
    public Connection sqlProductionConnection(final String dbName, final String username, final String psw) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DAOUtils.localSqlServerConnection(dbName, username, psw);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Establishes a test MySQL database connection.
     *
     * @param username the username for the connection
     * @param psw      the password for the connection
     * @return the established SQL Connection object
     */
    public Connection sqlTestConnection(final String username, final String psw) {

        return DAOUtils.localMySQLConnection("DesFusion",
                "root", "");
    }

    /**
     * Establishes a database connection using the provided username and password.
     * Selects the connection type based on the username.
     *
     * @param username the username for the connection
     * @param psw      the password for the connection
     * @return the established SQL Connection object
     */
    public Connection doConnectionDescription(final String username, final String psw) {
        LOGGER.info("Attempting connection Description for user: {}", username);
        try {
            if (username.equals("TEST")) {
                connection = sqlTestConnection(username, psw);
                LOGGER.info("Connection established for user: {}", username);
            } else if (username.equals(CEPIUT)) {
                connection = sqlProductionConnection(EDM_DB_2008_001, PDM_USER, PDM_USER);
                LOGGER.info("Connection established for user in edm: {}", username);
            } else {
                throw new DAOException("Invalid username");
            }
            try {
                LOGGER.info("Connection established in database: {}", connection.getCatalog());
                connection.setAutoCommit(false);
                LOGGER.info("Auto-commit disabled for the connection");
            } catch (SQLException e) {
                LOGGER.error("Failed to retrieve database catalog", e);
            }
        } catch (DAOException ex) {
            new ConnectionFailureViewIni();
        }
        return connection;

    }

    public Connection doConnectionHistory(String username, String psw) {
        LOGGER.info("Attempting connection History for user: {}", username);
        try {
            if (username.equals("TEST")) {
                connection = sqlTestConnection(username, psw);
                LOGGER.info("Connection established for user: {}", username);
            } else if (username.equals(CEPIUT)) {
                connection = sqlProductionConnection(BOMB_CONFINE, ADHOC, ADHOC);
                LOGGER.info("Connection established for user in bomb_confine: {}", username);

            } else {
                throw new DAOException("Invalid username");
            }
            try {
                LOGGER.info("Connection established in database: {}", connection.getCatalog());
                connection.setAutoCommit(false);
                LOGGER.info("Auto-commit disabled for the connection");
            } catch (SQLException e) {
                LOGGER.error("Failed to retrieve database catalog", e);
            }
        } catch (DAOException ex) {
            new ConnectionFailureViewIni();
        }
        return connection;
    }

}
