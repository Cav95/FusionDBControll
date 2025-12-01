package descriptionupdate.view.utils;

import java.util.function.Supplier;

import javax.swing.SwingUtilities;

import descriptionupdate.view.View;
import descriptionupdate.view.dialog.WaitDialog;

/*
 * A utility class to show a wait panel while performing a background action.
 */
public class WaitPanel {

    /**
     * Constructor for WaitPanel.
     *
     * @param action the action to be performed in the background
     * @param view   the main view
     */
    public WaitPanel(Supplier<Void> action, View view) {
        Thread process = new Thread(() -> {
            try {
                action.get();
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }

        });

        WaitDialog waitDialog = new WaitDialog(view);

        waitDialog.setVisible(true);

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                process.run();
                try {
                    process.join();
                    waitDialog.dispose();
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }

            }

        });

    }

}
