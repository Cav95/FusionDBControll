package descriptionupdate.model;

import java.sql.Connection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import descriptionupdate.data.PrintValueDAOImpl;
import descriptionupdate.data.api.dao.PrintValueDAO;
import descriptionupdate.model.api.PrintCodeValues;
import descriptionupdate.model.api.Reparti;
import descriptionupdate.model.api.SinglePrintvalue;
import descriptionupdate.model.filter.FilterPrintImpl;
import descriptionupdate.model.filter.api.FilterPrintValues;

public class ModelHistoryPrint {
    private final Connection connection;
    private final PrintValueDAO printValueDAO;
    private FilterPrintImpl currentPrint;
    private HashMap<String ,List<SinglePrintvalue>> allValues = new HashMap<>();
    /**
     * Constructor for Model.
     * Initializes the model with a database connection and sets up the
     * PrintValueDAO.
     *
     * @param connection the database connection
     */
    public ModelHistoryPrint(Connection connection) {
        this.connection = connection;
        this.printValueDAO = new PrintValueDAOImpl(connection);
        this.currentPrint = new FilterPrintImpl();
       // this.allCodes = printValueDAO.getAllCodes("%");//List.of("AFF06520","AFF06529","AFF06529P1");//printValueDAO.getAllCodes("%");
    }

    /**
     * Retrieves the database connection.
     *
     * @return the database connection
     */
    public Connection getConnection() {
        return connection;
    }

    private Stream<PrintCodeValues> builtPrinCodesValues(String code) {
        var dates = printValueDAO.getAllEndValues(code, currentPrint.getFilter().dateValue());

        return dates.stream()
                .map(t -> new PrintCodeValues(code,
                        printValueDAO.getOneCodeValue(code, t, Reparti.OFFICINA.getRepartoName()),
                        printValueDAO.getOneCodeValue(code, t, Reparti.PREASSEMBLAGGIO.getRepartoName()),
                        printValueDAO.getOneCodeValue(code, t, Reparti.SARTORIA.getRepartoName()),
                        printValueDAO.getOneCodeValue(code, t, Reparti.PRODOTTO_FINITO.getRepartoName()),
                        printValueDAO.getOneCodeValue(code, t, Reparti.SPEDIZIONE.getRepartoName()),
                        printValueDAO.getOneCodeValue(code, t, Reparti.MONTATORI.getRepartoName()),
                        printValueDAO.getOneCodeValue(code, t, Reparti.UFFICIO_ACQUISTI.getRepartoName()),
                        t)).distinct();
    }

    public List<PrintCodeValues> getAllPrintCodeValues() {
        return printValueDAO.getAllCodes(currentPrint.getFilter().code()).stream().parallel()
                .flatMap(this::builtPrinCodesValues)
                .toList();
    }

    public List<String> getAvailableDates(String code) {
        return printValueDAO.getAllEndValues(code, currentPrint.getFilter().dateValue());
    }

    public FilterPrintImpl getCurrentPrintCodeValues() {
        return currentPrint;
    }

    public void setCurrentPrintCodeValues(FilterPrintValues print) {
        this.currentPrint.setFilter(print);
    }

    public List<String> getAllDate() {
        return printValueDAO.getAllDate();
    }

    @SuppressWarnings("unused")
    private String getOneCodeValue(String code, String endValue, String propValue) {
        return printValueDAO.getOneCodeValue(code, endValue, propValue);
    }
    @SuppressWarnings("unused")
    private String getOneValue(String code, String endValue, String propValue) {
        return allValues.get(code).stream()
                .filter(t -> t.endValue().equals(endValue) && t.nomeCampo().equals(propValue))
                .sorted(Comparator.comparing(h -> h.endValue()))
                .map(t -> t.valoreCampo())
                .findFirst()
                .orElse("");
    }
}
