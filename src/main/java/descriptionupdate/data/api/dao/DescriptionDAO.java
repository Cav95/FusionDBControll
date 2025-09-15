package descriptionupdate.data.api.dao;

import java.util.List;
import java.util.Optional;

import descriptionupdate.data.utils.DAOException;
import descriptionupdate.model.api.Description;

/**
 * Interface for Data Access Object (DAO) that provides methods to interact with
 * Person data.
 * This interface defines methods for retrieving a person by their CUIPerson.
 */
public interface DescriptionDAO {

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
    Optional<Description> getDescription(String itaDescription, String engDescription, String group)
            throws DAOException;

    /**
     * Adds a new description to the data store.
     *
     * @param itaDescription the Italian description
     * @param engDescription the English description
     * @param group          the group type
     * @throws DAOException if an error occurs while adding the description
     */
    void addDescription(String itaDescription, String engDescription, String group);

    /**
     * Deletes a description from the data store.
     *
     * @param itaDescription the Italian description
     * @param engDescription the English description
     * @param group          the group type
     * @throws DAOException if an error occurs while deleting the description
     */
    void deleteDescription(String itaDescription, String engDescription, String group);

    /**
     * Updates an existing description in the data store.
     *
     * @param exItaDescription  the existing Italian description
     * @param exEngDescription  the existing English description
     * @param exGroup           the existing group type
     * @param newItaDescription the new Italian description
     * @param newEngDescription the new English description
     * @throws DAOException if an error occurs while updating the description
     */
    void updateDescription(String exItaDescription, String exEngDescription, String exGroup, String newItaDescription,
            String newEngDescription);

    /**
     * Retrieves all group types as a list of strings.
     * 
     * @return a list of group type strings.
     * @throws DAOException
     */
    List<String> getAllGroupTypeString() throws DAOException;

    /**
     * Retrieves a list of descriptions based on the provided parameters.
     *
     * @param itaDescription the Italian description
     * @param engDescription the English description
     * @param group          the group type
     * @return a list of descriptions matching the provided parameters
     */
    List<Description> getListDescription(String itaDescription, String engDescription, String group);

    List<Description> getSimilarItalianDescriptions() throws DAOException;
}
