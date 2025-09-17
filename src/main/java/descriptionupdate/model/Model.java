package descriptionupdate.model;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import descriptionupdate.data.DescriptionDAOImpl;
import descriptionupdate.data.api.dao.DescriptionDAO;
import descriptionupdate.model.api.Description;

/**
 * The Model class represents the data and business logic of the application.
 * It interacts with the database through the DescriptionDAO to perform CRUD
 * operations
 * on descriptions.
 */
public final class Model {

    private final Connection connection;
    private final DescriptionDAO descriptionDAO;

    /**
     * Constructor for Model.
     * Initializes the model with a database connection and sets up the
     * DescriptionDAO.
     *
     * @param connection the database connection
     */
    public Model(Connection connection) {
        this.connection = connection;
        this.descriptionDAO = new DescriptionDAOImpl(connection);
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
     * Retrieves a description based on the provided parameters.
     *
     * @param itaDescription the Italian description
     * @param engDescription the English description
     * @param group          the group type
     * @return an Optional containing the description if found, or empty if not
     *         found
     */
    public Optional<Description> getDescription(Description description) {
        return descriptionDAO.getDescription(description.itaDescription(), description.engDescription(), description.group());
    }

    /**
     * Retrieves a list of descriptions based on the provided parameters.
     *
     * @param itaDescription the Italian description
     * @param engDescription the English description
     * @param group          the group type
     * @return a list of descriptions matching the provided parameters
     */
    public List<Description> getListDescription(Description description) {
        return descriptionDAO.getListDescription(description.itaDescription(), description.engDescription(), description.group());

    }

    /**
     * Adds a new description to the model.
     *
     * @param description the description to be added
     */
    public void addDescription(Description description) {
        descriptionDAO.addDescription(description.itaDescription(),
                description.engDescription(), description.group());
    }

    /**
     * Deletes a description from the model.
     *
     * @param description the description to be deleted
     */
    public void deleteDescription(Description description) {
        descriptionDAO.deleteDescription(description.itaDescription(),
                description.engDescription(), description.group());
    }

    /**
     * Updates an existing description in the model.
     *
     * @param oldDescription the existing description to be updated
     * @param newDescription the new description to replace the old one
     */
    public void updateDescription(Description oldDescription, Description newDescription) {
        descriptionDAO.updateDescription(oldDescription.itaDescription(), oldDescription.engDescription(),
                oldDescription.group(), newDescription.itaDescription(), newDescription.engDescription());
    }

    /**
     * Saves changes made to the model.
     * This method commits the current transaction to the database.
     * If an error occurs during commit, it rolls back the transaction.
     *
     * @throws RuntimeException if an error occurs while committing the transaction
     */
    public void save() {
        try {
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (Exception rollbackEx) {
                throw new RuntimeException("Failed to rollback transaction after commit failure", rollbackEx);
            }
            throw new RuntimeException("Failed to commit transaction", e);
        }
    }

    /**
     * Retrieves all group types as a list of strings.
     *
     * @return a list of group types
     * @throws RuntimeException if an error occurs while retrieving the group types
     */
    public List<String> getAllGroupTypeString() {
        return descriptionDAO.getAllGroupTypeString();
    }

    public List<Description> getSimilarItalianDescriptions(Description description) {
        return descriptionDAO.getSimilarItalianDescriptions(description.itaDescription(), description.engDescription(),
                description.group());
    }
}
