package de.qaware.bwinf.todoapp.view;

import com.airhacks.afterburner.views.FXMLView;
import de.qaware.bwinf.todoapp.controller.EntryController;

/**
 * Entry view.
 * <p>
 * The related FXML file is in the resources folder, entry.fxml.
 */
public class EntryView extends FXMLView {
    @Override
    public EntryController getPresenter() {
        return (EntryController) super.getPresenter();
    }
}
