package descriptionupdate.data.api.dao;

import java.util.List;
import descriptionupdate.data.utils.DAOException;
import descriptionupdate.model.api.SinglePrintvalue;


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

        List<String> getAllCodes() throws DAOException;

        String getOneCodeValue(String code, String endValue , String propValue) throws DAOException;

        List<String> getAllEndValues(String code) throws DAOException;

       
}
