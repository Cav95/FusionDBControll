package descriptionupdate.controller;

import java.sql.Connection;
import descriptionupdate.model.ConnectionFactory;
import descriptionupdate.model.FilterManager;

public class Controller {

    private FilterManager filterManager = new FilterManager();
    private ConnectionFactory connectionFactory = new ConnectionFactory();

    public Controller() {
    }

    public String getItaFilterTemp() {
        return filterManager.getItaFilterTemp();
    }

    public String getEngFilterTemp() {
        return filterManager.getEngFilterTemp();
    }

    public String getGroupFilterTemp() {
        return filterManager.getGroupFilterTemp();
    }

    public void setAllFilterTemp(String ita, String eng, String group) {
        filterManager.setAllFilterTemp(ita, eng, group);
    }

    public void resetFilterTemp() {
        filterManager.resetFilterTemp();
    }

    public boolean isUserAdmitted(final String username, final String psw) {
        return connectionFactory.isUserAdmitted(username, psw);
    }

    public Connection doConnection(final String username, final String psw) {
        return connectionFactory.doConnection(username, psw);
    }

}
