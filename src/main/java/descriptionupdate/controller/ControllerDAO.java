package descriptionupdate.controller;

import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;

import descriptionupdate.model.ModelDescription;
import descriptionupdate.model.ModelHistoryPrint;
import descriptionupdate.model.api.Description;
import descriptionupdate.model.api.PrintCodeValues;
import descriptionupdate.model.filter.FilterPrintImpl;
import descriptionupdate.model.filter.api.FilterPrintValues;
import descriptionupdate.view.View;
import descriptionupdate.view.exception.ExistentDescriptionException;

/**
 * Controller class that manages the interaction between the model and the view.
 * It handles user actions and updates the view accordingly.
 * This class is responsible for coordinating the flow of data and actions
 * between the model and the view, ensuring that the application behaves as
 * expected.
 * It provides methods to initialize scenes, retrieve and manipulate
 * descriptions,
 * and manage the application's state.
 */
public final class ControllerDAO extends Controller {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ControllerDAO.class);

    private final ModelDescription model;
    private final ModelHistoryPrint modelHistoryPrint;
    private boolean isSaved;

    /**
     * Indicates whether the current scene is saved or not.
     *
     * @return true if the scene is saved, false otherwise
     */
    public boolean isSaved() {
        return isSaved;
    }

    /**
     * Sets the saved state of the current scene.
     *
     * @param isSaved true if the scene is saved, false otherwise
     */
    public void setSaved(boolean isSaved) {
        this.isSaved = isSaved;
    }

    /**
     * Constructs a Controller with the specified model and view.
     *
     * @param model the model to be used by the controller
     * @param view  the view to be managed by the controller
     * @throws NullPointerException if model or view is null
     */
    public ControllerDAO(final ModelDescription model, final View view, final ModelHistoryPrint modelHistoryPrint) {
        Objects.requireNonNull(model, "Controller created with null model");
        Objects.requireNonNull(view, "Controller created with null view");
        Objects.requireNonNull(modelHistoryPrint, "Controller created with null modelHistoryPrint");
        this.model = model;
        this.modelHistoryPrint = modelHistoryPrint;
        this.isSaved = true;
    }

    /**
     * Retrieves a description based on the provided parameters.
     *
     * @param itaDescription the Italian description
     * @param engDescription the English description
     * @param group          the group type
     * @return the description matching the provided parameters
     * @throws IllegalArgumentException if no description is found for the provided
     *                                  parameters
     */
    public Description getDescription(Description description) {
        LOGGER.info("Getting description for: {}, {}, {}", description.itaDescription(), description.engDescription(),
                description.group());
        return model.getDescription(description)
                .orElseThrow(() -> new IllegalArgumentException("No description found for the provided parameters"));
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
        LOGGER.info("Getting list of descriptions");
        return model.getListDescription(description);

    }

    /**
     * Adds a new description to the model.
     *
     * @param description the description to be added
     */
    public void addDescription(final Description description) {
        LOGGER.info("Adding description: {}", description);
        if (checkExistent(description)) {
            model.addDescription(description);
        } else {
            throw new ExistentDescriptionException("Description already exists");
        }
    }

    /**
     * Deletes a description from the model.
     *
     * @param description the description to be deleted
     */
    public void deleteDescription(final Description description) {
        LOGGER.info("Delete description: {}", description);
        model.deleteDescription(description);
        setSaved(false);
    }

    public void updateDescription(final Description oldDescription, final Description newDescription) {
        LOGGER.info("Updating description from: {}, {}, {} to: {}, {}, {}", oldDescription, newDescription);
        if (checkExistent(newDescription)) {
            model.updateDescription(oldDescription, newDescription);
        } else {
            throw new ExistentDescriptionException("Description already exists");
        }
    }

    /**
     * Saves changes made to the model.
     */
    public void save() {
        LOGGER.info("Saving changes to the database");
        model.save();
        setSaved(true);
    }

    /**
     * Retrieves all group types as a list of strings.
     *
     * @return a list of all group type strings
     */
    public List<String> getAllGroupTypeString() {
        LOGGER.info("Getting all group types");
        return model.getAllGroupTypeString();
    }

    /**
     * Checks if a description already exists in the model.
     *
     * @param des the description to check.
     * @return true if the description exists, false otherwise.
     */
    public boolean checkExistent(final Description des) {
        try {
            getDescription(des);
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    /**
     * Retrieves a list of descriptions with similar Italian descriptions.
     *
     * @param description the description containing the Italian description to match
     * @return a list of descriptions with similar Italian descriptions
     */
    public List<Description> getSimilarItalianDescriptions(Description description) {
        return model.getSimilarItalianDescriptions(description);
    }

    public List<PrintCodeValues> getPrintHistory() {
        return modelHistoryPrint.getAllPrintCodeValues();
    }

    public List<String> getAvailableDates(String code) {
        return modelHistoryPrint.getAvailableDates(code);
    }

    public FilterPrintImpl getFilterPrint() {
        return modelHistoryPrint.getCurrentPrintCodeValues();
    }
    public void setCurrentPrintCodeValues(FilterPrintValues print) {
        modelHistoryPrint.setCurrentPrintCodeValues(print);
    }
}
