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
     * 
     * @param connection
     */
    public PrintValueDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public List<SinglePrintvalue> getPrintValue(String code, String endValue) throws DAOException {
        List<SinglePrintvalue> printValues = new ArrayList<>();
        try (
                var statement = DAOUtils.prepare(connection, QueriesHistoryPrintFiles.GET_CODE_VALUE, code, endValue);
                var resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                printValues.add(new SinglePrintvalue(resultSet.getString(PrintValueColumn.CODICE.getColumnName()),
                        resultSet.getString(PrintValueColumn.NOME_CAMPO.getColumnName()),
                        resultSet.getString(PrintValueColumn.VALORE_CAMPO.getColumnName()),
                        resultSet.getString(PrintValueColumn.END_VALUE.getColumnName())));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return printValues;
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public List<String> getAllCodes(String code) throws DAOException {
        List<String> codes = new ArrayList<>();
        try (
                var statement = DAOUtils.prepare(connection, QueriesHistoryPrintFiles.GET_CODE, code + "%");
                var resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                codes.add(resultSet.getString(PrintValueColumn.CODICE.getColumnName()));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return codes;
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public String getOneCodeValue(String code, String endValue, String propValue) throws DAOException {
        try (
                var statement = DAOUtils.prepare(connection, QueriesHistoryPrintFiles.GET_ONE_CODE_VALUE, code + "%",
                        endValue, propValue);
                var resultSet = statement.executeQuery();) {
            if (resultSet.next()) {
                return resultSet.getString(PrintValueColumn.VALORE_CAMPO.getColumnName());
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return null;
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public List<String> getAllEndValues(String code, String startDate) throws DAOException {
        List<String> endValues = new ArrayList<>();
        try (
                var statement = DAOUtils.prepare(connection, QueriesHistoryPrintFiles.GET_ALL_END_DATE_STRING, code + "%",
                        startDate);
                var resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                endValues.add(resultSet.getString(PrintValueColumn.END_VALUE.getColumnName()));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return endValues;
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public List<String> getAllDate() throws DAOException {
        List<String> dates = new ArrayList<>();
        try (
                var statement = DAOUtils.prepare(connection, QueriesHistoryPrintFiles.GET_ALL_DATE);
                var resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                dates.add(resultSet.getString(PrintValueColumn.END_VALUE.getColumnName()));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return dates;

    }

    /*
     * {@inheritDoc}
     */
    @Override
    public List<SinglePrintvalue> getAllValues() throws DAOException {
        List<SinglePrintvalue> printValues = new ArrayList<>();
        try (
                var statement = DAOUtils.prepare(connection, QueriesHistoryPrintFiles.GET_VALUES);
                var resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                printValues.add(new SinglePrintvalue(resultSet.getString(PrintValueColumn.CODICE.getColumnName()),
                        resultSet.getString(PrintValueColumn.NOME_CAMPO.getColumnName()),
                        resultSet.getString(PrintValueColumn.VALORE_CAMPO.getColumnName()),
                        resultSet.getString(PrintValueColumn.END_VALUE.getColumnName())));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return printValues;
    }
}