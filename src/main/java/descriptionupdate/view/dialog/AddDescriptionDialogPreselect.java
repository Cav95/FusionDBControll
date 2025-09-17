package descriptionupdate.view.dialog;

import java.util.function.Supplier;

import descriptionupdate.model.api.Description;
import descriptionupdate.view.View;
import descriptionupdate.view.dialog.api.AbstactChangeDialog;
import descriptionupdate.view.factory.JOptionPaneFactory;

/**
 * Dialog for adding a preselected description.
 */
public class AddDescriptionDialogPreselect extends AbstactChangeDialog {

    private static final long serialVersionUID = 1L;

    private final Supplier<Void> action;

    /**
     * Constructor for AddDescriptionScenePreselect.
     *
     * @param view  the main view of the application
     * @param group the group to preselect in the combo box
     */
    public AddDescriptionDialogPreselect(final View view, Description description, Supplier<Void> action) {
        super(view);
        itaTextField.setText(description.itaDescription());
        engTextField.setText(description.engDescription());
        groupTextField.setSelectedItem(description.group());
        this.action = action;
        refreshAction = action;

        titleLabel.setText("AGGIUNGI DESCRIZIONE");
        addButton.setText("Aggiungi");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void action(Description newDescription) {

        view.getController().addDescription(newDescription);
        JOptionPaneFactory.successfullyAddedDescription(AddDescriptionDialogPreselect.this,
                newDescription);
        view.getController().setSaved(false);
        // view.goToInitialSceneFiltered();
        action.get();
        AddDescriptionDialogPreselect.this.dispose();
    }

}
