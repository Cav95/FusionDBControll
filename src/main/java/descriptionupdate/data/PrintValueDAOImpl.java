package descriptionupdate.data;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import descriptionupdate.data.api.dao.PrintValueColumn;
import descriptionupdate.data.api.dao.PrintValueDAO;
import descriptionupdate.data.queries.QueriesHistoryPrintFiles;
import descriptionupdate.data.utils.DAOException;
import descriptionupdate.data.utils.DAOUtils;
import descriptionupdate.model.api.SinglePrintvalue;

public class PrintValueDAOImpl implements PrintValueDAO {
    private final Connection connection;

/**
 * Constructor for PrintValueDAOImpl.
 * @param connection
 */
    public PrintValueDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<SinglePrintvalue> getPrintValue(String code, String endValue) throws DAOException {
        List<SinglePrintvalue> printValues = new ArrayList<>();
        try (
                var statement = DAOUtils.prepare(connection, QueriesHistoryPrintFiles.GET_CODE_VALUE, code, endValue);
                var resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                printValues.add(new SinglePrintvalue(resultSet.getString(PrintValueColumn.CODICE.getColumnName()),
                        resultSet.getString(PrintValueColumn.NOME_CAMPO.getColumnName()),
                        resultSet.getString(PrintValueColumn.VALORE_CAMPO.getColumnName())));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return printValues;
    }

    @Override
    public List<String> getAllCodes() throws DAOException {
        List<String> codes = new ArrayList<>();
        try (
                var statement = DAOUtils.prepare(connection, QueriesHistoryPrintFiles.GET_CODE);
                var resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                codes.add(resultSet.getString(PrintValueColumn.CODICE.getColumnName()));
            }
        } catch (Exception e) {
             throw new DAOException(e);
        }
        return codes;
    }

    @Override
    public String getOneCodeValue(String code, String endValue, String propValue) throws DAOException {
        try (
                var statement = DAOUtils.prepare(connection, QueriesHistoryPrintFiles.GET_ONE_CODE_VALUE, code, endValue, propValue);
                var resultSet = statement.executeQuery();) {
            if (resultSet.next()) {
                return resultSet.getString(PrintValueColumn.VALORE_CAMPO.getColumnName());
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return null;
    }

}