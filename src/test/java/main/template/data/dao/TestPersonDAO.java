package main.template.data.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    public void isConnected() throws SQLException {
        assertEquals(true, connection.isValid(2));
    }

    @Test
    public void fromDescription() {
        var descDao = new DescriptionDAOImpl(connection);
        var actual = descDao.getDescription("ANTIVIBRANTE", "ANTI-VIBRATION", "ANV_ANTIVIBRANTI").get();
        var expected = new Description("ANTIVIBRANTE", "ANTI-VIBRATION", "ANV_ANTIVIBRANTI");
        assertEquals(expected, actual);
    }

    @Test
    public void addDescription() {
        var descDao = new DescriptionDAOImpl(connection);
        descDao.addDescription("PIPPO", "PLUTO", "VTS");

        var actual = descDao.getDescription("PIPPO", "PLUTO", "VTS").get();
        var expected = new Description("PIPPO", "PLUTO", "VTS");
        assertEquals(expected, actual);
    }

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

}
