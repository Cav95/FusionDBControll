package descriptionupdate.view.utils;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * ConnectionFailureView class that extends JFrame to display a connection
 * failure message.
 * This view is shown when the application cannot connect to the database.
 */
public class ConnectionFailureView extends JFrame {

    /**
     * Constructs a ConnectionFailureView that displays an error message
     * indicating a failure to connect to the database.
     * The message is shown in a dialog box, and the frame is disposed of after the
     * message is acknowledged.
     */
    public ConnectionFailureView() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
        JOptionPane.showMessageDialog(this,
                "Impossibile connettersi al database.\n" +
                        "Assicurarsi che il server selezionato sia in esecuzione e che esista",
                "Errore di connessione al database",
                JOptionPane.ERROR_MESSAGE);
        this.dispose();
    }

}
