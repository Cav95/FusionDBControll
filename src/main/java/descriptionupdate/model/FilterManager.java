package descriptionupdate.model;

import descriptionupdate.model.api.Description;

/**
 * Manages temporary filter values for Italian, English, and group fields.
 */
public class FilterManager {
    private static final String ALL = "%";

    private String itaFilterTemp = "%";
    private String engFilterTemp = "%";
    private String groupFilterTemp = "%";

    /**
     * Gets the temporary Italian filter value.
     *
     * @return the Italian filter string
     */
    public String getItaFilterTemp() {
        return itaFilterTemp;
    }

    /**
     * Sets the temporary Italian filter value.
     *
     * @param itaFilterTemp the Italian filter string
     */
    private void setItaFilterTemp(String itaFilterTemp) {
        this.itaFilterTemp = itaFilterTemp;
    }

    /**
     * Gets the temporary English filter value.
     *
     * @return the English filter string
     */
    public String getEngFilterTemp() {
        return engFilterTemp;
    }

    /**
     * Sets the temporary English filter value.
     *
     * @param engFilterTemp the English filter string
     */
    private void setEngFilterTemp(String engFilterTemp) {
        this.engFilterTemp = engFilterTemp;
    }

    /**
     * Gets the temporary group filter value.
     *
     * @return the group filter string
     */
    public String getGroupFilterTemp() {
        return groupFilterTemp;
    }

    /**
     * Sets the temporary group filter value.
     *
     * @param groupFilterTemp the group filter string
     */
    private void setGroupFilterTemp(String groupFilterTemp) {
        this.groupFilterTemp = groupFilterTemp;
    }

    /**
     * Sets all temporary filter values for Italian, English, and group.
     *
     * @param ita   the Italian filter string
     * @param eng   the English filter string
     * @param group the group filter string
     */
    public void setAllFilterTemp(String ita, String eng, String group) {
        setItaFilterTemp(ita);
        setEngFilterTemp(eng);
        setGroupFilterTemp(group);
    }

    /**
     * Resets all temporary filter values to their default state ("%").
     */
    public void resetFilterTemp() {
        setItaFilterTemp("%");
        setEngFilterTemp("%");
        setGroupFilterTemp("%");
    }

    /**
     * Retrieves a Description object representing the current filter criteria.
     * Each field in the Description object is appended with "%" to facilitate
     * SQL LIKE queries.
     *
     * @return a Description object with filter criteria
     */
    public Description getFilterDescription() {
        return new Description(this.itaFilterTemp + ALL, this.engFilterTemp + ALL, this.groupFilterTemp + ALL);
    }

}
