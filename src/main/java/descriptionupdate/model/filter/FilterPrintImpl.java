package descriptionupdate.model.filter;

import descriptionupdate.model.filter.api.Filter;
import descriptionupdate.model.filter.api.FilterPrintValues;

/**
 * Implementation of the Filter interface for FilterPrintValues objects.
 * Manages filter criteria for code and date value.
 */
public class FilterPrintImpl implements Filter<FilterPrintValues> {

    private static final String DEFAULT_DATE = "2099-12-31";
    private static final String ALL = "";
    private FilterPrintValues filter;

    /**
     * Constructor initializes the filter with default values.
     */
    public FilterPrintImpl() {
        this.filter = new FilterPrintValues(ALL, DEFAULT_DATE);
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public FilterPrintValues getFilter() {
        return new FilterPrintValues(filter.code() + ALL, filter.dateValue());
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public void setFilter(final FilterPrintValues filter) {
        this.filter = filter;
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public void resetFilter() {
        this.filter = new FilterPrintValues(ALL, DEFAULT_DATE);
    }

}
