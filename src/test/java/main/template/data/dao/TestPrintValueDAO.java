package main.template.data.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import descriptionupdate.data.PrintValueDAOImpl;
import descriptionupdate.data.utils.DAOUtils;

/**
 * Test class for Print Value Data Access Object (DAO)
 */
class TestPrintValueDAO {

    private static Connection connection;
    private static Savepoint savepoint;

    /**
     * Setup method to initialize database connection and savepoint before all
     * tests.
     *
     * @throws SQLException if a database access error occurs
     */
    @BeforeAll
    public static void setup() throws SQLException {
        connection = DAOUtils.localMySQLConnection("DesFusion", "root", "");
        connection.setAutoCommit(false);
        savepoint = connection.setSavepoint();
    }

    /**
     * Cleanup method to rollback to savepoint and close the database connection
     * after all tests.
     *
     * @throws SQLException if a database access error occurs
     */
    @AfterAll
    public static void cleanup() throws SQLException {
        if (connection != null) {
            if (savepoint != null) {
                connection.rollback(savepoint);
            }
            connection.close();
        }
    }

    /**
     * Test if the database connection is valid.
     */
    @Test
    public void isConnected() throws SQLException {
        assertEquals(true, connection.isValid(2));
    }

    /**
     * Test the addDescription method.
     */
    @Test
    public void addDescription() {
        var valDao = new PrintValueDAOImpl(connection);
        var actual = valDao.getPrintValue("AFF06520", "2099-12-31");
        var value = actual.stream().filter(v -> v.nomeCampo().equals("Officina")).findFirst();
        assertTrue(value.isPresent());
    }

    @Test
    public void getAllCodes() {
        var valDao = new PrintValueDAOImpl(connection);
        var actual = valDao.getAllCodes();
        assertTrue(actual.size() > 0);
    }

    @Test
    public void getOneCodeValue() {
        var valDao = new PrintValueDAOImpl(connection);
        var actual = valDao.getOneCodeValue("AFF06520", "2099-12-31", "Officina");
        assertTrue(actual.contains("NO"));
    }

    @Test
    public void getAllEndValues() {
        var valDao = new PrintValueDAOImpl(connection);
        var actual = valDao.getAllEndValues("AFF06520");
        assertTrue(actual.size() > 0);
    }

}