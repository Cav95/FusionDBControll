package descriptionupdate.model.filter;

import descriptionupdate.model.filter.api.Filter;
import descriptionupdate.model.filter.api.FilterPrintValues;

public class FilterPrintImpl implements Filter<FilterPrintValues> {

    private static final String ALL = "";
    private FilterPrintValues filter;



    /**
     * Constructor initializes the filter with default values.
     */
    public FilterPrintImpl() {
        this.filter = new FilterPrintValues(ALL, ALL);
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public FilterPrintValues getFilter() {
        return filter;
    }

        /*
     * {@inheritDoc}
     */
    @Override
    public void setFilter(FilterPrintValues filter) {
        this.filter = filter;
    }

        /*
     * {@inheritDoc}
     */
    @Override
    public void resetFilter() {
        this.filter = new FilterPrintValues(ALL, ALL);
    }
    
}
