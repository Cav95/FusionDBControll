package descriptionupdate.view.utils;

import java.io.FileInputStream;
import java.util.Properties;

import org.slf4j.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * A JFrame subclass that displays a connection failure message
 * with details from a configuration file.
 * This class reads the database connection details from an INI file
 * and shows them in a dialog when a connection failure occurs.
 */
public class ConnectionFailureViewIni extends JFrame {
        private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ConnectionFailureViewIni.class);
        private static final String CONFIG_DB_CONNECTION_INI = "configDBConnection.ini";

        /**
         * Constructs a ConnectionFailureViewIni that reads the database connection
         * details
         * from a configuration file and displays an error message.
         * If the configuration file cannot be read, it shows an error message and
         * disposes of the frame.
         */
        public ConnectionFailureViewIni() {
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setAlwaysOnTop(true);

                Properties properties = new Properties();
                String iniFilePath = System.getProperty("user.dir") + System.getProperty("file.separator")
                                + CONFIG_DB_CONNECTION_INI;
                try (FileInputStream fileInputStream = new FileInputStream(iniFilePath)) {
                        properties.load(fileInputStream);

                } catch (Exception e) {
                        LOGGER.error("Error reading INI file {}", iniFilePath, e);
                        JOptionPane.showMessageDialog(this,
                                        "Impossibile leggere il file di configurazione.\n" +
                                                        "Assicurarsi che il file " + CONFIG_DB_CONNECTION_INI
                                                        + " esista nella cartella di lavoro.",
                                        "Errore di configurazione",
                                        JOptionPane.ERROR_MESSAGE);
                        this.dispose();
                        return;
                }

                // Show a minimal, non-sensitive error message (avoid displaying passwords)
                String connInfo = properties.getProperty("key1");
                String user = properties.getProperty("user");
                String redactedPassword = properties.getProperty("psw") != null ? "(redacted)" : "(not provided)";

                JOptionPane.showMessageDialog(this,
                                "Impossibile connettersi al database.\n" +
                                                "Assicurarsi che il server selezionato sia in esecuzione e che esista" +
                                                "\n" + "Connection String: "
                                                + (connInfo != null ? connInfo : "(not provided)") +
                                                "\n" + "User: " + (user != null ? user : "(not provided)") +
                                                "\n" + "Password: " + redactedPassword,
                                "Errore di connessione al database",
                                JOptionPane.ERROR_MESSAGE);
                this.dispose();
        }

}
