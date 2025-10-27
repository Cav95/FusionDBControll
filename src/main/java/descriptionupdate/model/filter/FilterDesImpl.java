package descriptionupdate.model.filter;

import descriptionupdate.model.api.Description;
import descriptionupdate.model.filter.api.Filter;

/**
 * Implementation of the Filter interface for Description objects.
 * Manages filter criteria for Italian description, English description, and group.
 */
public class FilterDesImpl implements Filter<Description> {

    private static final String ALL = "%";
    private Description filter;

    /**
     * Constructor initializes the filter with default values.
     */
    public FilterDesImpl() {
        this.filter = new Description(ALL, ALL, ALL);
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public Description getFilter() {
        return filter;
    }

        /*
     * {@inheritDoc}
     */
    @Override
    public void setFilter(Description filter) {
        this.filter = new Description(filter.itaDescription() + ALL, filter.engDescription() + ALL,
                filter.group() + ALL);
    }

        /*
     * {@inheritDoc}
     */
    @Override
    public void resetFilter() {
        this.filter = new Description(ALL, ALL, ALL);
    }

}
