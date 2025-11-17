package descriptionupdate.model.filter.api;

/**
 * Generic Filter interface for managing filter criteria.
 *
 * @param <X> the type of object to be filtered
 */
public interface Filter<X> {

    /**
     * Returns the current filter criteria.
     * 
     * @return the filter criteria
     */
    X getFilter();

    /**
     * Sets the filter criteria.
     * 
     * @param filter the new filter criteria
     */
    void setFilter(X filter);

    /**
     * Resets the filter criteria to its default state.
     */
    void resetFilter();

}
