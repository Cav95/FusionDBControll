package descriptionupdate.data.queries;

/**
 * A utility class that contains SQL query strings used for database operations.
 */
public final class QueriesHistoryPrintFiles {

        /**
         * SQL query to retrieve all records from the DescrizioniGruppi table.
         */
        public static final String GET_CODE_VALUE = """
                        select *
                        from printhistory
                        where codCodice like ?;
                                    """;

}
