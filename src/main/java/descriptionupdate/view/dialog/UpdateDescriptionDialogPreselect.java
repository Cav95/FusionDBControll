package descriptionupdate.view.dialog;

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

    /**
     * Constructor for AddDescriptionScenePreselect.
     *
     * @param view  the main view of the application
     * @param group the group to preselect in the combo box
     */
    public UpdateDescriptionDialogPreselect(final View view, final Description description) {
        super(view);
        itaTextField.setText(description.itaDescripion());
        engTextField.setText(description.engDescription());
        groupTextField.setSelectedItem(description.group());
        this.exIta = description.itaDescripion();
        this.exEng = description.engDescription();
        this.exGroup = description.group();

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
        this.view.goToInitialSceneFiltered();
        UpdateDescriptionDialogPreselect.this.dispose();
    }

}
