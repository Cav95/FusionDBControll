package descriptionupdate.view.dialog;

import descriptionupdate.model.api.Description;
import descriptionupdate.view.View;
import descriptionupdate.view.dialog.api.AbstactChangeDialog;
import descriptionupdate.view.factory.JOptionPaneFactory;

/**
 * Dialog for adding a preselected description.
 */
public class AddDescriptionDialogPreselect extends AbstactChangeDialog {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for AddDescriptionScenePreselect.
     *
     * @param view  the main view of the application
     * @param group the group to preselect in the combo box
     */
    public AddDescriptionDialogPreselect(final View view, final String ita, final String eng, final String group) {
        super(view);
        itaTextField.setText(ita);
        engTextField.setText(eng);
        groupTextField.setSelectedItem(group);

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
        view.goToInitialSceneFiltered();
        AddDescriptionDialogPreselect.this.dispose();
    }

}
