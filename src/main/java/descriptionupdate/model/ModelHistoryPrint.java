package descriptionupdate.model;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Stream;

import descriptionupdate.data.PrintValueDAOImpl;
import descriptionupdate.data.api.dao.PrintValueDAO;
import descriptionupdate.model.api.PrintCodeValues;
import descriptionupdate.model.api.Reparti;

public class ModelHistoryPrint {
    private final Connection connection;
    private final PrintValueDAO printValueDAO;

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
    }

    /**
     * Retrieves the database connection.
     *
     * @return the database connection
     */
    public Connection getConnection() {
        return connection;
    }

    private Stream<PrintCodeValues> getAllOneCodeValues(String code) {
        var endValues = printValueDAO.getAllEndValues(code);
        return endValues.stream()
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
        var codes = printValueDAO.getAllCodes();
        return codes.stream()
                .flatMap(this::getAllOneCodeValues)
                .toList();
    }
}
