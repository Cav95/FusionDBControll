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
                        where codCodice like ?
                        and endValue like ?;
                                    """;

        /**
         * SQL query to retrieve distinct codCodice values from the printhistory table.
         */
        public static final String GET_CODE = """
                        select distinct codCodice
                        from printhistory
                        where codCodice like ?
                        ORDER BY codCodice asc;
                                    """;

        /**
         * SQL query to retrieve distinct endValue values from the printhistory table
         * filtered by codCodice and endValue.
         */
        public static final String GET_ALL_END_DATE_STRING = """
                        select distinct endValue
                        from printhistory
                        where codCodice like ?
                        and endValue >= ?
                        ORDER BY endValue asc;
                                    """;
        /**
         * SQL query to retrieve all distinct endValue values from the printhistory
         * table.
         */
        public static final String GET_ALL_DATE = """
                        select distinct endValue
                        from printhistory;
                                    """;

        /**
         * SQL query to retrieve a single propValore from the printhistory table based
         * on codCodice, endValue, and cpNome.
         */
        public static final String GET_ONE_CODE_VALUE = """
                        select top 1propValore
                        from printhistory
                        where codCodice like ?
                        and endValue >= ?
                        and cpNome like ?
                        order by endValue asc;
                                    """;
        /**
         * SQL query to retrieve all records from the printhistory table.
         */
        public static final String GET_VALUES = """
                        select *
                        from printhistory;
                                    """;

}
