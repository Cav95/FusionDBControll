package descriptionupdate.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import org.slf4j.Logger;

import descriptionupdate.data.utils.DAOException;
import descriptionupdate.data.utils.DAOUtils;
import descriptionupdate.view.api.UserAdmit;
import descriptionupdate.view.utils.ConnectionFailureViewIni;

public class ConnectionFactory {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ConnectionFactory.class);
    private Connection connection;
    private static final String PDM_USER = "PDMUser";

    public boolean isUserAdmitted(final String username, final String psw) {
        return Arrays.asList(UserAdmit.values()).stream()
                .anyMatch(user -> user.getUsername().equals(username) && user.getPsw().equals(psw));
    }

    public Connection sqlProductionConnection(final String username, final String psw) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DAOUtils.localSqlServerConnection("EdmDb_2008_001",
                    PDM_USER, PDM_USER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Connection sqlTestConnection(final String username, final String psw) {

        return DAOUtils.localMySQLConnection("DesFusion",
                "root", "");
    }

    public Connection doConnection(final String username, final String psw) {
        LOGGER.info("Attempting connection for user: {}", username);
        try {
            if (username.equals("TEST")) {
                connection = sqlTestConnection(username, psw);
                LOGGER.info("Connection established for user: {}", username);
            } else if (username.equals("CEPIUT")) {
                connection = sqlProductionConnection(username, psw);

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
