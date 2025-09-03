package descriptionupdate.view.utils;

import java.util.Arrays;

import descriptionupdate.model.api.Description;
import descriptionupdate.view.api.ProibenCaratter;
import descriptionupdate.view.exception.BlankDescriptionException;

/**
 * Utility class for controlling and validating descriptions.
 */
public class ControllUtilies {

    /**
     * Checks if the given character contains any prohibited characters.
     *
     * @param character the character to check
     * @return true if the character contains prohibited characters, false otherwise
     */
    public static boolean isProhibitedCharacter(String character) {
        return Arrays.asList(ProibenCaratter.values()).stream()
                .anyMatch(c -> character.contains(c.getCharacter()));
    }

    /**
     * Validates the characters in the given description.
     *
     * @param description the description to validate
     */
    public static void descriptionValidCaracter(Description description) {
        if (ControllUtilies.isProhibitedCharacter(description.itaDescripion())
                || ControllUtilies.isProhibitedCharacter(description.engDescription())) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Validates that the given description is not blank.
     *
     * @param description the description to validate
     */
    public static void descriptionNotBlank(Description description) {
        if (description.itaDescripion().isBlank() || description.engDescription().isBlank()) {
            throw new BlankDescriptionException("Description cannot be blank");
        }
    }
}
