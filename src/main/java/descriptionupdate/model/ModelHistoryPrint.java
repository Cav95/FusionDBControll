package descriptionupdate.model;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Stream;

import descriptionupdate.data.PrintValueDAOImpl;
import descriptionupdate.data.api.dao.PrintValueDAO;
import descriptionupdate.model.api.PrintCodeValues;
import descriptionupdate.model.api.Reparti;
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
                        printValueDAO.getOneCodeValue(code, t, Reparti.OFFICINA.getRepartoName()),
                        printValueDAO.getOneCodeValue(code, t, Reparti.PREASSEMBLAGGIO.getRepartoName()),
                        printValueDAO.getOneCodeValue(code, t, Reparti.SARTORIA.getRepartoName()),
                        printValueDAO.getOneCodeValue(code, t, Reparti.PRODOTTO_FINITO.getRepartoName()),
                        printValueDAO.getOneCodeValue(code, t, Reparti.SPEDIZIONE.getRepartoName()),
                        printValueDAO.getOneCodeValue(code, t, Reparti.MONTATORI.getRepartoName()),
                        printValueDAO.getOneCodeValue(code, t, Reparti.UFFICIO_ACQUISTI.getRepartoName()),
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
}
