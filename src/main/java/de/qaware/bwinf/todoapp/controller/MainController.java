package de.qaware.bwinf.todoapp.controller;

import de.qaware.bwinf.todoapp.WindowHelper;
import de.qaware.bwinf.todoapp.model.Entry;
import de.qaware.bwinf.todoapp.model.MainModel;
import de.qaware.bwinf.todoapp.model.Progress;
import de.qaware.bwinf.todoapp.view.EntryView;
import de.qaware.bwinf.todoapp.view.NewEntryView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import javax.inject.Inject;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Controller for the main view.
 */
public class MainController implements Initializable {
    @FXML
    private VBox todoContainer;
    @FXML
    private VBox inProgressContainer;
    @FXML
    private VBox doneContainer;
    @FXML
    private TextField searchTextbox;

    @Inject
    private MainModel mainModel;
    @Inject
    private WindowHelper windowHelper;

    /**
     * Maps from entry -> view.
     */
    private final Map<Entry, EntryView> entries = new HashMap<>();

    /**
     * Creates a view for the given entry.
     *
     * @param entry Entry.
     */
    private void createEntryView(Entry entry) {
        // Create the view and store it in the map
        EntryView view = new EntryView();
        entries.put(entry, view);
        EntryController controller = view.getPresenter();

        // Display the entry in the view
        controller.setEntry(entry);

        // Find the right container for the progress and add the entry view
        VBox container = getContainer(entry.getProgress());
        container.getChildren().add(view.getView());

        // Set the function which is called when the move button of that entry is pressed
        controller.setOnMoveButtonHandler(e -> {
                    // Progress the entry from one state to the next (Todo -> In Progress -> Done -> In Progress)
                    entry.setProgress(entry.getProgress().next());
                    // Refresh the view
                    controller.setEntry(entry);
                    // Remove the view from the old progress
                    container.getChildren().remove(view.getView());
                    // Add the view to the new progress
                    VBox newContainer = getContainer(entry.getProgress());
                    newContainer.getChildren().add(view.getView());
                }
        );
    }

    /**
     * Returns the container for the entry with the given progress.
     *
     * @param progress Progress.
     * @return Container.
     */
    private VBox getContainer(Progress progress) {
        switch (progress) {
            case TODO:
                return todoContainer;
            case IN_PROGRESS:
                return inProgressContainer;
            case DONE:
                return doneContainer;
            default:
                throw new IllegalStateException("Invalid entry progress: " + progress);
        }
    }

    @FXML
    public void onCreateNewClicked(ActionEvent event) {
        // Show the new entry view as modal window
        String title = windowHelper.showModal("Add new TODO", new NewEntryView(), NewEntryController.class).getTitle();

        // If title is null, user hasn't clicked okay, but closed the window or clicked cancel
        if (title != null) {
            // Add the new entry as todo
            Entry newEntry = new Entry(Progress.TODO, title);
            mainModel.getEntries().add(newEntry);
            createEntryView(newEntry);
        }
    }

    @FXML
    public void onSearch(ActionEvent event) {
        String text = searchTextbox.getText().toLowerCase();

        if (text.isEmpty()) {
            // If user cleared the text, show all entries
            for (EntryView view : entries.values()) {
                view.getView().setVisible(true);
            }
        } else {
            // User entered something, so filter the entries which label contains the text
            for (Map.Entry<Entry, EntryView> entryEntry : entries.entrySet()) {
                EntryView view = entryEntry.getValue();
                Entry entry = entryEntry.getKey();
                view.getView().setVisible(entry.getLabel().toLowerCase().contains(text));
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Entry entry : mainModel.getEntries()) {
            createEntryView(entry);
        }
    }
}
