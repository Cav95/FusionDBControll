package main.template.data.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import descriptionupdate.data.DescriptionDAOImpl;
import descriptionupdate.data.utils.DAOUtils;
import descriptionupdate.model.api.Description;

/**
 * Test class for Description Data Access Object (DAO)
 */
class TestdescDao {

    private static Connection connection;
    private static Savepoint savepoint;

    @BeforeAll
    public static void setup() throws SQLException {
        connection = DAOUtils.localMySQLConnection("DesFusion", "root", "");
        connection.setAutoCommit(false);
        savepoint = connection.setSavepoint();
    }

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
        var descDao = new DescriptionDAOImpl(connection);
        descDao.addDescription("PIPPO", "PLUTO", "VTS");

        var actual = descDao.getDescription("PIPPO", "PLUTO", "VTS").get();
        var expected = new Description("PIPPO", "PLUTO", "VTS");
        assertEquals(expected, actual);
    }

    /**
     * Test the fromDescription method.
     */
    @Test
    public void fromDescription() {
        var descDao = new DescriptionDAOImpl(connection);
        descDao.addDescription("ANTIVIBRANTE", "ANTI-VIBRATION", "ANV_ANTIVIBRANTI");
        var actual = descDao.getDescription("ANTIVIBRANTE", "ANTI-VIBRATION", "ANV_ANTIVIBRANTI").get();
        var expected = new Description("ANTIVIBRANTE", "ANTI-VIBRATION", "ANV_ANTIVIBRANTI");
        assertEquals(expected, actual);
    }

    /**
     * Test the deleteDescription method.
     */
    @Test
    public void deleteDescription() {
        var descDao = new DescriptionDAOImpl(connection);
        descDao.addDescription("PIPPO", "PLUTO", "VTS");

        var actual = descDao.getDescription("PIPPO", "PLUTO", "VTS").get();
        var expected = new Description("PIPPO", "PLUTO", "VTS");
        assertEquals(expected, actual);

        descDao.deleteDescription("PIPPO", "PLUTO", "VTS");
        assertEquals(descDao.getDescription("PIPPO", "PLUTO", "VTS"), Optional.empty());
    }

    /**
     * Test the fixDescription method.
     */
    @Test
    public void fixDescription() {
        var descDao = new DescriptionDAOImpl(connection);
        descDao.addDescription("PIPPO", "PLUTO", "TVB");

        var actual = descDao.getDescription("PIPPO", "PLUTO", "TVB").get();
        var expected = new Description("PIPPO", "PLUTO", "TVB");
        assertEquals(expected, actual);

        descDao.updateDescription("PIPPO", "PLUTO", "TVB", "CIAO", "HELLO");
        assertEquals(descDao.getDescription("CIAO", "HELLO", "TVB").get(),
                new Description("CIAO", "HELLO", "TVB"));
    }

    @Test
    public void getAllGroupTypeString() {
        var descDao = new DescriptionDAOImpl(connection);
        descDao.addDescription("PIPPO", "PLUTO", "PROVA");
        var actual = descDao.getAllGroupTypeString();
        assertTrue(actual.contains("PROVA"));
    }

    @Test
    public void getSimilarItalianDescriptions() {
        var descDao = new DescriptionDAOImpl(connection);
        descDao.addDescription("TEST1", "TEST1-PLUTO", "VTS");
        descDao.addDescription("TEST1", "TEST2-PLUTO", "VTS");

        var actual = descDao.getSimilarItalianDescriptions();
        assertEquals(2, actual.stream().filter(d -> d.itaDescripion().equals("TEST1")).count());
    }
}