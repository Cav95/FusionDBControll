package descriptionupdate.controller;

import java.sql.Connection;
import descriptionupdate.model.ConnectionFactory;
import descriptionupdate.model.FilterDesImpl;
import descriptionupdate.model.api.Description;
import descriptionupdate.model.api.Filter;

/**
 * Controller class that manages interactions between the view and the model.
 * It handles user actions, updates the model, and refreshes the view as needed.
 */
public class Controller {

    private final ConnectionFactory connectionFactory = new ConnectionFactory();
    private final Filter<Description> filter = new FilterDesImpl();

    /**
     * Constructor for Controller.
     */
    public Controller() {
    }

    /**
     * Sets all temporary filter values for Italian, English, and group.
     *
     * @param ita   the Italian filter string
     * @param eng   the English filter string
     * @param group the group filter string
     */
    public void setAllFilterTemp(final String ita, final String eng, final String group) {
        filter.setFilter(new Description(ita, eng, group));
    }

    /**
     * Resets all temporary filter values to their default state.
     */
    public void resetFilterTemp() {
        filter.resetFilter();
    }

    /**
     * Checks if the user is admitted based on username and password.
     *
     * @param username the username to check
     * @param psw      the password to check
     * @return true if the user is admitted, false otherwise
     */
    public boolean isUserAdmitted(final String username, final String psw) {
        return connectionFactory.isUserAdmitted(username, psw);
    }

    /**
     * Establishes a database connection using the provided username and password.
     *
     * @param username the username for the connection
     * @param psw      the password for the connection
     * @return the established SQL Connection object
     */
    public Connection doConnection(final String username, final String psw) {
        return connectionFactory.doConnection(username, psw);
    }

    /**
     * Retrieves a list of descriptions based on the provided filter criteria.
     *
     * @param filter the filter criteria encapsulated in a Description object
     * @return a list of descriptions matching the filter criteria
     */
    public Description getFilterDescription() {
        return filter.getFilter();
    }

}
