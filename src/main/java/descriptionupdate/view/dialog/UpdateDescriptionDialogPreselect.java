package descriptionupdate.view.dialog;

import java.util.function.Supplier;

import descriptionupdate.model.api.Description;
import descriptionupdate.view.View;
import descriptionupdate.view.dialog.api.AbstactChangeDialog;
import descriptionupdate.view.factory.JOptionPaneFactory;

/**
 * Dialog for updating a preselected description.
 */
public class UpdateDescriptionDialogPreselect extends AbstactChangeDialog {

    private static final long serialVersionUID = 1L;
    private final String exIta;
    private final String exEng;
    private final String exGroup;

    private final Supplier<Void> action;

    /**
     * Constructor for AddDescriptionScenePreselect.
     *
     * @param view  the main view of the application
     * @param group the group to preselect in the combo box
     */
    public UpdateDescriptionDialogPreselect(final View view, final Description description, Supplier<Void> action) {
        super(view);
        itaTextField.setText(description.itaDescription());
        engTextField.setText(description.engDescription());
        groupTextField.setSelectedItem(description.group());
        this.exIta = description.itaDescription();
        this.exEng = description.engDescription();
        this.exGroup = description.group();
        this.action = action;
        refreshAction = action;

        titleLabel.setText("AGGIORNA DESCRIZIONE");
        groupTextField.setEnabled(false);
        addButton.setText("Aggiorna");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void action(Description newDescription) {
        var oldDescription = new Description(exIta, exEng, exGroup);
        this.view.getController().updateDescription(oldDescription,
                newDescription);
        JOptionPaneFactory.successfullyAddedDescription(UpdateDescriptionDialogPreselect.this,
                newDescription);
        this.view.getController().setSaved(false);
        // this.view.goToInitialSceneFiltered();
        action.get();
        UpdateDescriptionDialogPreselect.this.dispose();
    }

}
