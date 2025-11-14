package descriptionupdate.data.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Utility class for managing database connections and preparing SQL statements.
 * Provides methods to establish connections to MySQL and SQL Server databases,
 * as well as to prepare SQL statements with parameters.
 */
public final class DAOUtils {

    private static final String CONFIG_DB_CONNECTION_INI = "configDBConnection.ini";

    // Establishes a connection to a MySQL daemon running locally at port 3306.
    //
    /**
     * Establishes a connection to a MySQL database.
     * * @param database the name of the database
     * 
     * @param username the username for the database connection
     * @param password the password for the database connection
     * @return a Connection object to the MySQL database
     * @throws DAOException if an error occurs while establishing the connection
     */
    public static Connection localMySQLConnection(String database, String username, String password) {
        try {
            var host = "localhost";
            var port = "3306";
            var connectionString = "jdbc:mysql://" + host + ":" + port + "/" + database;
            return DriverManager.getConnection(connectionString, username, password);
        } catch (Exception e) {
            throw new DAOException(e);
        }

    }

    // Establishes a connection to a Microsoft SQL Server database.
    /**
     * Establishes a connection to a Microsoft SQL Server database.
     * * @param database the name of the database
     * 
     * @param username the username for the database connection
     * @param password the password for the database connection
     * @return a Connection object to the Microsoft SQL Server database
     * @throws DAOException if an error occurs while establishing the connection
     */
    public static Connection localSqlServerConnection(String database, String username, String password) {
        // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionUrl = "jdbc:sqlserver://DBSRV02:1433;"
                + "databaseName=" + database + ";"
                + "user=" + username + ";"
                + "password=" + password + ";"
                + "encrypt=false;"
                + "trustServerCertificate=true;" // <-- importante se encrypt è false
                + "loginTimeout=30;";
        try {
            return DriverManager.getConnection(connectionUrl);
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

/*
 * Establishes a connection to a database using connection details from an INI file.
 */
    public static Connection localIniStringConnection(String key) {
        Properties properties = new Properties();
        String iniFilePath = System.getProperty("user.dir") + System.getProperty("file.separator")
                + CONFIG_DB_CONNECTION_INI;

        try (FileInputStream fileInputStream = new FileInputStream(iniFilePath)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
        }

        // Leggi una singola chiave dal file INI
        String connectionString = properties.getProperty(key);
        String username = properties.getProperty("user");
        String password = properties.getProperty("psw");
        try {
            return DriverManager.getConnection(connectionString, username, password);
        } catch (Exception e) {

            try {
                return DriverManager.getConnection(connectionString);
            } catch (SQLException t) {
                throw new DAOException("Errore durante la connessione al database", t);
            }
            // throw new DAOException(e);
        }

        // System.out.println("Valore di chiave1: " + valore);
    }

    // We must always prepare a statement to make sure we do not fall victim to SQL
    // injection:
    // https://owasp.org/www-community/attacks/SQL_Injection
    //
    // This is a helper that prepares the statement with all the values we give it:
    //
    // prepare(connection, MY_QUERY, query_arg1, query_arg2, ...)
    //
    public static PreparedStatement prepare(Connection connection, String query, Object... values) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
            return statement;
        } catch (Exception e) {
            if (statement != null) {
                statement.close();
            }
            throw e;
        }
    }
}
