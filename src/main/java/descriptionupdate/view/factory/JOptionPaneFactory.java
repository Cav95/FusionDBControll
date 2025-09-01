package descriptionupdate.view.factory;

import java.awt.Component;

import javax.swing.JOptionPane;

import descriptionupdate.model.api.Description;

/**
 * Utility class for displaying various optional dialog panes.
 */
public class JOptionPaneFactory {

    /**
     * Displays a message dialog indicating invalid characters were used.
     */
    public static void caractherInvalid(Component dialog) {
        JOptionPane.showMessageDialog(dialog,
                "Usati caratteri non validi");
    }

    /**
     * Displays a message dialog indicating access was denied.
     * 
     * @param dialog the parent component for the dialog
     */
    public static void denediedAccess(Component dialog) {
        JOptionPane.showMessageDialog(dialog,
                "Accesso negato.", "Errore",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows a message indicating the save was successful.
     * 
     * @param dialog the parent component for the dialog
     */
    public static void savedSuccessfully(Component dialog) {
        JOptionPane.showMessageDialog(dialog,
                "Salvataggio avvenuto con successo", "Info",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Shows a message indicating that changes were not saved.
     * 
     * @param dialog the parent component for the dialog
     */
    public static void saveDiscarded(Component dialog) {
        JOptionPane.showMessageDialog(dialog,
                "Modifiche non salvate", "Info",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Shows an error message when saving fails.
     * 
     * @param dialog  the parent component for the dialog
     * @param message the error message to display
     */
    public static void errorOnSave(Component dialog, String message) {
        JOptionPane.showMessageDialog(dialog,
                "Errore durante il salvataggio: " + message, "Errore",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Asks the user to confirm saving changes before exiting.
     * 
     * @param dialog the parent component for the dialog
     * @return JOptionPane.YES_OPTION or JOptionPane.NO_OPTION
     */
    public static Integer askSaveConfirm(Component dialog) {
        return JOptionPane.showConfirmDialog(dialog,
                "Ci sono modifiche non salvate.\nVuoi salvare prima di uscire?",
                "Conferma Salvataggio", JOptionPane.YES_NO_OPTION);
    }

    /**
     * Asks the user to confirm deletion of a description.
     * 
     * @param dialog      the parent component for the dialog
     * @param description the description to delete
     * @return JOptionPane.YES_OPTION or JOptionPane.NO_OPTION
     */
    public static Integer askDeleteConfirm(Component dialog, String description) {
        String item = String.join("\n- ", description);
        return JOptionPane.showConfirmDialog(dialog,
                "Sei sicuro di voler eliminare i seguenti elementi?\n- " + item,
                "Conferma Eliminazione", JOptionPane.YES_NO_OPTION);
    }

    /**
     * Shows an error message if the description already exists.
     * 
     * @param dialog the parent component for the dialog
     */
    public static void existedDescription(Component dialog) {
        JOptionPane.showMessageDialog(dialog,
                "Descrizione Già Presente", "Errore",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows a success message for adding a description.
     * 
     * @param dialog      the parent component for the dialog
     * @param description the added description
     */
    public static void successfullyAddedDescription(Component dialog, Description description) {
        JOptionPane.showMessageDialog(dialog,
                "Descrizione Aggiunta con Successo\n" + description.itaDescripion() + " - "
                        + description.engDescription() + " - " + description.group());
    }

    /**
     * Shows an error message when the database connection fails.
     * 
     * @param dialog the parent component for the dialog
     */
    public static void connectionFailed(Component dialog) {
        JOptionPane.showMessageDialog(dialog,
                "Connessione al database fallita", "Errore",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void generiError(Component dialog) {
        JOptionPane.showMessageDialog(dialog,
                "Errore generico", "Errore",
                JOptionPane.ERROR_MESSAGE);
    }

    private JOptionPaneFactory() {
        // Prevent instantiation
    }

    public static void blankDescription(Component dialog) {
        JOptionPane.showMessageDialog(dialog,
                "La descrizione non può essere vuota", "Errore",
                JOptionPane.ERROR_MESSAGE);
    }

}
