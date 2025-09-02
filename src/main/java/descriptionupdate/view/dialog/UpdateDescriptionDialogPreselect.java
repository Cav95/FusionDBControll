package descriptionupdate.view.dialog;

import descriptionupdate.model.api.Description;
import descriptionupdate.view.View;
import descriptionupdate.view.dialog.api.AbstactChangeDialog;
import descriptionupdate.view.factory.JOptionPaneFactory;
import descriptionupdate.view.utils.ControllUtilies;

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
    public UpdateDescriptionDialogPreselect(final View view, final String ita, final String eng, final String group) {
        super(view);
        itaTextField.setText(ita);
        engTextField.setText(eng);
        groupTextField.setSelectedItem(group);
        this.exIta = ita;
        this.exEng = eng;
        this.exGroup = group;

        titleLabel.setText("AGGIORNA DESCRIZIONE");
        groupTextField.setEnabled(false);
        addButton.setText("Aggiorna");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void action() {
        var newDescription = new Description(
                itaTextField.getText().toUpperCase(),
                engTextField.getText().toUpperCase(), exGroup);
        var oldDescription = new Description(exIta, exEng, exGroup);

        ControllUtilies.descriptionValidCaracter(newDescription);
        ControllUtilies.descriptionNotBlank(newDescription);
        this.view.getController().updateDescription(oldDescription,
                newDescription);
        JOptionPaneFactory.successfullyAddedDescription(UpdateDescriptionDialogPreselect.this,
                newDescription);
        this.view.getController().setSaved(false);
        this.view.goToInitialSceneFiltered();
        UpdateDescriptionDialogPreselect.this.dispose();
    }

}
