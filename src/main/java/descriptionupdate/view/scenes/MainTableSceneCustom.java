package descriptionupdate.view.scenes;
import java.util.List;
import java.util.function.Supplier;

import descriptionupdate.model.api.Description;
import descriptionupdate.model.api.PrintCodeValues;
import descriptionupdate.view.View;

/**
 * MainTableSceneCustom class that extends MainTableScene to create a custom main table scene for
 * the application.
 */
public class MainTableSceneCustom  extends MainTableScene {

    private Supplier<Void> onBack;

    /**
     * Constructor for MainTableSceneCustom.
     *
     * @param view the main view of the application
     */
    public MainTableSceneCustom(View view, Supplier<List<Description>> descriptions) {
        super(view);
        this.onBack = () -> {
            view.goToTableCustomScene(descriptions);
            return null;
        };
        refreshButtonPanel(new ButtomMainPannel(this,view, onBack));
        refreshTable(descriptions.get());
    }

        /**
     * Constructor for MainTableSceneCustom.
     *
     * @param view the main view of the application
     */
    public MainTableSceneCustom(View view, Supplier<List<PrintCodeValues>> descriptions, Boolean isPrint) {
        super(view);
        this.onBack = () -> {
            view.goToTableCustomScenePrint(descriptions);
            return null;
        };
        refreshButtonPanel(new ButtomMainPannel(this,view, onBack));
        refreshTablePrint(descriptions.get());
    }


    
}
