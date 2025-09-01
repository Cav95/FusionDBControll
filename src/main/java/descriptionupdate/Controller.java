package descriptionupdate;

import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;

import descriptionupdate.model.Model;
import descriptionupdate.model.api.Description;
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
public final class Controller {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(Controller.class);

    private final Model model;
    private boolean isSaved;

    private String itaFilterTemp = "%";
    private String engFilterTemp = "%";
    private String groupFilterTemp = "%";

    public String getItaFilterTemp() {
        return itaFilterTemp;
    }

    private void setItaFilterTemp(String itaFilterTemp) {
        this.itaFilterTemp = itaFilterTemp;
    }

    public String getEngFilterTemp() {
        return engFilterTemp;
    }

    private void setEngFilterTemp(String engFilterTemp) {
        this.engFilterTemp = engFilterTemp;
    }

    public String getGroupFilterTemp() {
        return groupFilterTemp;
    }

    private void setGroupFilterTemp(String groupFilterTemp) {
        this.groupFilterTemp = groupFilterTemp;
    }

    public void setAllFilterTemp(String ita, String eng, String group) {
        setItaFilterTemp(ita);
        setEngFilterTemp(eng);
        setGroupFilterTemp(group);
    }

    public void resetFilterTemp() {
        setItaFilterTemp("%");
        setEngFilterTemp("%");
        setGroupFilterTemp("%");
    }

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
    public Controller(final Model model, final View view) {
        Objects.requireNonNull(model, "Controller created with null model");
        Objects.requireNonNull(view, "Controller created with null view");
        this.model = model;
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
    public Description getDescription(final String itaDescription, final String engDescription, final String group) {
        LOGGER.info("Getting description for: {}, {}, {}", itaDescription, engDescription, group);
        return model.getDescription(itaDescription, engDescription, group)
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
    public List<Description> getListDescription(final String itaDescription, final String engDescription,
            final String group) {
        LOGGER.info("Getting list of descriptions");
        return model.getListDescription(itaDescription, engDescription, group);

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
    public boolean checkExistent(Description des) {
        try {
            getDescription(des.itaDescripion(), des.engDescription(), des.group());
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }
}
