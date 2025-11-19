package descriptionupdate.data.api.dao;

/**
 * Enum representing the column names for the description table.
 * This is used to standardize the column names across the application.
 */
public enum DescriptionColumnName {
    ITA_DES("DESCRIZIONE"),
    ENG_DES("INGLESE"),
    GROUP("Gruppo");

    private final String columnName;

    /**
     * Constructor for DescriptionColumnName enum.
     */
    DescriptionColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * Returns the column name as a string.
     *
     * @return the column name
     */
    public String getColumnName() {
        return columnName;
    }
}
