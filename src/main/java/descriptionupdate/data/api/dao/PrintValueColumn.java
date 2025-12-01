package descriptionupdate.data.api.dao;

/**
 * Enum representing the column names for the print value table.
 * This is used to standardize the column names across the application.
 */
public enum PrintValueColumn {
    ID("id"),
    CODICE("codCodice"),
    NOME_CAMPO("cpNome"),
    VALORE_CAMPO("propValore"),
    END_VALUE("endValue");

    private final String columnName;

    /**
     * Constructor for DescriptionColumnName enum.
     */
    PrintValueColumn(final String columnName) {
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
