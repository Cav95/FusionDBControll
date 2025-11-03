package descriptionupdate.data.api.dao;

import java.util.List;
import descriptionupdate.data.utils.DAOException;
import descriptionupdate.model.api.SinglePrintvalue;

/*
 * Data Access Object (DAO) interface for Print Values.
 * Provides methods to interact with the data source for print values.
 */
public interface PrintValueDAO {

    /**
     * Retrieves a description based on the provided parameters.
     *
     * @param itaDescription the Italian description
     * @param engDescription the English description
     * @param group          the group type
     * @return an Optional containing the description if found, or empty if not
     *         found
     * @throws DAOException if an error occurs while accessing the data
     */
    List<SinglePrintvalue> getPrintValue(String code, String endValue)
            throws DAOException;

    /**
     * Retrieves all unique codes from the data source.
     *
     * @return a list of all unique codes
     * @throws DAOException if an error occurs while accessing the data
     */
    List<String> getAllCodes(String code) throws DAOException;

    /**
     * Retrieves a single code value based on the provided parameters.
     *
     * @param code      the code to search for
     * @param endValue  the end value associated with the code
     * @param propValue the property value to retrieve
     * @return the code value as a String
     * @throws DAOException if an error occurs while accessing the data
     */
    String getOneCodeValue(String code, String endValue, String propValue) throws DAOException;

    /**
     * Retrieves all end values for a given code and start date.
     *
     * @param code      the code to search for
     * @param startDate the start date to filter end values
     * @return a list of all end values for the given code and start date
     * @throws DAOException if an error occurs while accessing the data
     */
    List<String> getAllEndValues(String code, String startDate) throws DAOException;

    List<String> getAllDate() throws DAOException;

    List<SinglePrintvalue> getAllValues() throws DAOException;

}
