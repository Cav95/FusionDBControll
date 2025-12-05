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

    private static final String TEST = "TEST";
    private static final String CEPIUT = "CEPIUT";

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ConnectionFactory.class);

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
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DAOUtils.localSqlServerConnection(dbName, username, psw);
        } catch (ClassNotFoundException e) {
            LOGGER.error("JDBC driver for SQL Server not found", e);
        }
        return conn;
    }

    /**
     * Establishes a production SQL Server database connection using an ini file.
     *
     * @param key the key to identify the connection settings in the ini file
     * @return the established SQL Connection object
     */
    public Connection sqlProductionConnectionIni(final String key) {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DAOUtils.localIniStringConnection(key);
        } catch (ClassNotFoundException e) {
            LOGGER.error("JDBC driver for SQL Server not found", e);
        }
        return conn;
    }

    /**
     * Establishes a test MySQL database connection.
     *
     * @param username the username for the connection
     * @param psw      the password for the connection
     * @return the established SQL Connection object
     */
    public Connection sqlTestConnection() {
        // Use null password for test connection instead of an empty literal
        return DAOUtils.localMySQLConnection("DesFusion", "root", null);
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
        Connection conn = null;
        try {
            if (username.equals(TEST)) {
                conn = sqlTestConnection();
                LOGGER.info("Connection established for user TEST: {}", username);
            } else if (username.equals(CEPIUT)) {
                // connection = sqlProductionConnection(EDM_DB_2008_001, PDM_USER, PDM_USER);
                conn = sqlProductionConnectionIni("key1");
                LOGGER.info("Connection established for user in edm: {}", username);
            } else {
                throw new DAOException("Invalid username");
            }
            try {
                if (conn != null) {
                    LOGGER.info("Connection established in database: {}", conn.getCatalog());
                    conn.setAutoCommit(false);
                    LOGGER.info("Auto-commit disabled for the connection");
                }
            } catch (SQLException e) {
                LOGGER.error("Failed to retrieve database catalog", e);
            }
        } catch (DAOException ex) {
            LOGGER.warn("DAOException while establishing connection: {}", ex.getMessage());
            new ConnectionFailureViewIni();
        }
        return conn;

    }

    /**
     * Establishes a database connection for history using the provided username and
     * password.
     * Selects the connection type based on the username.
     *
     * @param username the username for the connection
     * @param psw      the password for the connection
     * @return the established SQL Connection object
     */
    public Connection doConnectionHistory(String username, String psw) {
        LOGGER.info("Attempting connection History for user: {}", username);
        Connection conn = null;
        try {
            if (username.equals(TEST)) {
                conn = sqlTestConnection();
                LOGGER.info("Connection established for user TEST: {}", username);
            } else if (username.equals(CEPIUT)) {
                // connection = sqlProductionConnection(BOMB_CONFINE, ADHOC, ADHOC);
                conn = sqlProductionConnectionIni("key2");
                LOGGER.info("Connection established for user in bomb_confine: {}", username);

            } else {
                throw new DAOException("Invalid username");
            }
            try {
                if (conn != null) {
                    LOGGER.info("Connection established in database: {}", conn.getCatalog());
                    conn.setAutoCommit(false);
                    LOGGER.info("Auto-commit disabled for the connection");
                }
            } catch (SQLException e) {
                LOGGER.error("Failed to retrieve database catalog", e);
            }
        } catch (DAOException ex) {
            LOGGER.warn("DAOException while establishing history connection: {}", ex.getMessage());
            new ConnectionFailureViewIni();
        }
        return conn;
    }

}
