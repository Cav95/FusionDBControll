package descriptionupdate.model;

import java.sql.Connection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import descriptionupdate.data.PrintValueDAOImpl;
import descriptionupdate.data.api.dao.PrintValueDAO;
import descriptionupdate.model.api.PrintCodeValues;
import descriptionupdate.model.api.Reparti;
import descriptionupdate.model.api.SinglePrintvalue;
import descriptionupdate.model.filter.FilterPrintImpl;
import descriptionupdate.model.filter.api.FilterPrintValues;

/**
 * The Model class represents the data and business logic of the application.
 * It interacts with the database through the PrintValueDAO to perform CRUD
 * operations
 * on print values.
 */
public class ModelHistoryPrint {
    private final Connection connection;
    private final PrintValueDAO printValueDAO;
    private FilterPrintImpl currentPrint;

    private Map<String, Set<SinglePrintvalue>> cacheCodeValuesMap = new HashMap<>();

    /**
     * Constructor for Model.
     * Initializes the model with a database connection and sets up the
     * PrintValueDAO.
     *
     * @param connection the database connection
     */
    public ModelHistoryPrint(final Connection connection) {
        this.connection = connection;
        this.printValueDAO = new PrintValueDAOImpl(connection);
        this.currentPrint = new FilterPrintImpl();
    }

    /**
     * Retrieves the database connection.
     *
     * @return the database connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Builds a stream of PrintCodeValues for a given code.
     *
     * @param code the code to build PrintCodeValues for
     * @return a stream of PrintCodeValues
     */
    private Stream<PrintCodeValues> builtPrinCodesValues(final String code) {
        var dates = printValueDAO.getAllEndValues(code, currentPrint.getFilter().dateValue());

        return dates.stream().parallel()
                .map(t -> new PrintCodeValues(code,
                        getCachedCodes(code, t, Reparti.OFFICINA.getRepartoName()),
                        getCachedCodes(code, t, Reparti.PREASSEMBLAGGIO.getRepartoName()),
                        getCachedCodes(code, t, Reparti.SARTORIA.getRepartoName()),
                        getCachedCodes(code, t, Reparti.PRODOTTO_FINITO.getRepartoName()),
                        getCachedCodes(code, t, Reparti.SPEDIZIONE.getRepartoName()),
                        getCachedCodes(code, t, Reparti.MONTATORI.getRepartoName()),
                        getCachedCodes(code, t, Reparti.UFFICIO_ACQUISTI.getRepartoName()),
                        t))
                .distinct();
    }

    /**
     * Retrieves all print code values based on the current filter.
     *
     * @return a list of PrintCodeValues
     */
    public List<PrintCodeValues> getAllPrintCodeValues() {
        return printValueDAO.getAllCodes(currentPrint.getFilter().code()).stream().parallel()
                .flatMap(this::builtPrinCodesValues)
                .toList();
    }

    /**
     * Retrieves available end dates for a given code based on the current filter.
     *
     * @param code the code to retrieve end dates for
     * @return a list of available end dates
     */
    public List<String> getAvailableDates(final String code) {
        return printValueDAO.getAllEndValues(code, currentPrint.getFilter().dateValue());
    }

    public FilterPrintImpl getCurrentPrintCodeValues() {
        return currentPrint;
    }

    /**
     * Sets the current print code values filter.
     *
     * @param print the new filter to set
     */
    public void setCurrentPrintCodeValues(final FilterPrintValues print) {
        this.currentPrint.setFilter(print);
    }

    /**
     * Retrieves all available end dates from the print value DAO.
     *
     * @return a list of all end dates
     */
    public List<String> getAllDate() {
        return printValueDAO.getAllDate();
    }

    private String getCachedCodes(String codeValue, String date, String reparto) {
        if (cacheCodeValuesMap.containsKey(codeValue)) {
            var cachedSet = cacheCodeValuesMap.get(codeValue);
            try {
                return cachedSet.stream().parallel()
                        .filter(t -> t.codice().equals(codeValue)
                                && t.endValue().equals(date)
                                && t.nomeCampo().equals(reparto))
                        .findFirst()
                        .map(SinglePrintvalue::valoreCampo).get();
            } catch (Exception ex) {
                var valore = printValueDAO.getOneCodeValue(codeValue, date, reparto);
                cachedSet.add(new SinglePrintvalue(codeValue, reparto, valore, date));
                return valore;
            }
        } else {
            var valore = printValueDAO.getOneCodeValue(codeValue, date, reparto);
            var newSet = new HashSet<SinglePrintvalue>();
            newSet.add(new SinglePrintvalue(codeValue, reparto, valore, date));
            cacheCodeValuesMap.put(codeValue, newSet);
            return valore;
        }

    }
}
