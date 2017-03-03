package de.qaware.bwinf.todoapp.controller;

import de.qaware.bwinf.todoapp.model.Entry;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Controller for an entry.
 */
public class EntryController {
    @FXML
    private Label label;
    @FXML
    private Button moveButton;

    /**
     * Sets the entry to display.
     *
     * @param entry Entry.
     */
    public void setEntry(Entry entry) {
        label.setText(entry.getLabel());
        switch (entry.getProgress()) {
            case TODO:
                moveButton.setText(">");
                break;
            case IN_PROGRESS:
                moveButton.setText(">");
                break;
            case DONE:
                moveButton.setText("<");
                break;
        }
    }

    /**
     * Sets the handler which is called when the move button is pressed.
     *
     * @param handler Handler.
     */
    public void setOnMoveButtonHandler(EventHandler<ActionEvent> handler) {
        moveButton.setOnAction(handler);
    }
}
