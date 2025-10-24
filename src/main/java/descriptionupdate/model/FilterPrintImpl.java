package descriptionupdate.model;

import descriptionupdate.model.api.Filter;
import descriptionupdate.model.api.PrintCodeValues;

public class FilterPrintImpl implements Filter<PrintCodeValues> {

    private static final String ALL = "%";
    private PrintCodeValues filter;

    /**
     * Constructor initializes the filter with default values.
     */
    public FilterPrintImpl() {
        this.filter = new PrintCodeValues(ALL, ALL, ALL, ALL, ALL, ALL, ALL, ALL, ALL);
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public PrintCodeValues getFilter() {
        return filter;
    }

        /*
     * {@inheritDoc}
     */
    @Override
    public void setFilter(PrintCodeValues filter) {
        this.filter = filter;
    }

        /*
     * {@inheritDoc}
     */
    @Override
    public void resetFilter() {
        this.filter = new PrintCodeValues(ALL, ALL, ALL, ALL, ALL, ALL, ALL, ALL, ALL);
    }
    
}
