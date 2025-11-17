package descriptionupdate.model.filter.api;

/**
 * Record representing filter print values with default values.
 *
 * @param code      the code filter value
 * @param dateValue the date value filter
 */
public record FilterPrintValues(String code, String dateValue) {

    /**
     * Constructs a FilterPrintValues with default values.
     */
    public FilterPrintValues() {
        this("%", "2099-12-31");
    }
}