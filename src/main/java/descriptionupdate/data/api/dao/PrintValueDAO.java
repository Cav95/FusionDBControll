package descriptionupdate.data.api.dao;

import java.util.List;
import java.util.Optional;

import descriptionupdate.data.utils.DAOException;
import descriptionupdate.model.api.Description;


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
        List<Description> getPrintValue(String itaDescription, String engDescription, String group)
                        throws DAOException;

       
}
