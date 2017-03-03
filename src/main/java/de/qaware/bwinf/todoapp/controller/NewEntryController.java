package de.qaware.bwinf.todoapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.annotation.Nullable;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the new entry view.
 */
public class NewEntryController implements Initializable {
    @FXML
    private TextField titleTextbox;
    private String title;

    @FXML
    public void onOkClicked(ActionEvent e) {
        title = titleTextbox.getText();
        close();
    }

    @FXML
    public void onCancelClicked(ActionEvent e) {
        title = null;
        close();
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    /**
     * Closes the window.
     */
    private void close() {
        ((Stage) titleTextbox.getScene().getWindow()).close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleTextbox.requestFocus();
    }
}
