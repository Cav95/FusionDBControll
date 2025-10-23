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


        public static final String GET_CODE = """
                        select distinct codCodice
                        from printhistory;
                                    """;

        public static final String GET_ONE_CODE_VALUE = """
                        select propValore
                        from printhistory
                        where codCodice like ?
                        and endValue >= ?
                        and cpNome like ?
                        order by endValue asc
                        limit 1;
                                    """;

}
