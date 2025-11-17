package descriptionupdate;

import java.sql.SQLException;

import descriptionupdate.view.View;

/**
 * Main entry point for the Description Update application.
 */
public final class DescriptionUpdateMain {

    /**
     * Main method to launch the application.
     * 
     * @param args command line arguments
     * @throws SQLException if a database access error occurs
     */
    public static void main(String[] args) throws SQLException {
        new View();
    }
}
