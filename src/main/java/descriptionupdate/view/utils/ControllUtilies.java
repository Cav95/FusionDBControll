package descriptionupdate.view.utils;

import java.util.Arrays;

import javax.swing.JTable;
import javax.swing.JTextField;

import descriptionupdate.model.api.Description;
import descriptionupdate.view.api.ProibenCaratter;
import descriptionupdate.view.exception.BlankDescriptionException;

/**
 * Utility class for controlling and validating descriptions.
 */
public class ControllUtilies {
    private static final String ALL = "%";

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
        if (ControllUtilies.isProhibitedCharacter(description.itaDescription())
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
        if (description.itaDescription().isBlank() || description.engDescription().isBlank()) {
            throw new BlankDescriptionException("Description cannot be blank");
        }
    }

    /**
     * Validates that the given text field is not blank.
     *
     * @param textField the text field to validate
     * @return the trimmed text from the text field
     */
    public static String controllBlankReturn(final JTextField textField) {
        return textField.getText().isBlank() ? ALL : textField.getText().toUpperCase();
    }

    /**
     * Removes wildcard characters from the given text.
     *
     * @param text the text to process
     * @return the processed text without wildcard characters
     */
    public static String reversBlankReturn(final String text) {
        return text.replace(ALL, "").toUpperCase();
    }

    /**
     * Validates that the given group is not blank.
     *
     * @param group the group to validate
     * @return the validated group or a wildcard if blank
     */
    public static String controllBlankGroup(final String group) {
        return group.isBlank() ? ALL : group;
    }

    /**
     * Extracts a Description object from the selected row of the given JTable.
     *
     * @param table the JTable to extract the description from
     * @return the extracted Description object
     * @throws IllegalStateException if no row is selected in the table
     */
    public static Description getDescritionFromTable(final JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String group = (String) table.getValueAt(selectedRow, 0);
            String ita = (String) table.getValueAt(selectedRow, 1);
            String eng = (String) table.getValueAt(selectedRow, 2);
            return new Description(ita, eng, group);
        } else {
            throw new IllegalStateException("No request selected for management");
        }
    }
}
