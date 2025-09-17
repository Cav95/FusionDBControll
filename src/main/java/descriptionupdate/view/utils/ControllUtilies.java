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

        public static String controllBlankReturn(final JTextField textField) {
        return textField.getText().isBlank() ? ALL : textField.getText().toUpperCase();
    }

    public static String reversBlankReturn(final String text) {
        return text.equals(ALL) ? "" : text.toUpperCase();
    }

    public static String controllBlankGroup(final String group) {
        return group.isBlank() ? ALL : group;
    }

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
