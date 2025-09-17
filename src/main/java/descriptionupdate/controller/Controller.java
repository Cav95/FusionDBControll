package descriptionupdate.controller;

import java.sql.Connection;
import descriptionupdate.model.ConnectionFactory;
import descriptionupdate.model.FilterManager;
import descriptionupdate.model.api.Description;

/**
 * Controller class that manages interactions between the view and the model.
 * It handles user actions, updates the model, and refreshes the view as needed.
 */
public class Controller {

    private final FilterManager filterManager = new FilterManager();
    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    /**
     * Constructor for Controller.
     */
    public Controller() {
    }

    /**
     * Returns the temporary Italian filter value.
     *
     * @return the Italian filter string
     */
    public String getItaFilterTemp() {
        return filterManager.getItaFilterTemp();
    }

    /**
     * Returns the temporary English filter value.
     *
     * @return the English filter string
     */
    public String getEngFilterTemp() {
        return filterManager.getEngFilterTemp();
    }

    /**
     * Returns the temporary group filter value.
     *
     * @return the group filter string
     */
    public String getGroupFilterTemp() {
        return filterManager.getGroupFilterTemp();
    }

    /**
     * Sets all temporary filter values for Italian, English, and group.
     *
     * @param ita   the Italian filter string
     * @param eng   the English filter string
     * @param group the group filter string
     */
    public void setAllFilterTemp(final String ita, final String eng, final String group) {
        filterManager.setAllFilterTemp(ita, eng, group);
    }

    /**
     * Resets all temporary filter values to their default state.
     */
    public void resetFilterTemp() {
        filterManager.resetFilterTemp();
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
        return filterManager.getFilterDescription();
    }

}
