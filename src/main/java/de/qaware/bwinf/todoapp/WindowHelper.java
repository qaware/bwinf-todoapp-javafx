package de.qaware.bwinf.todoapp;

import com.airhacks.afterburner.views.FXMLView;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Helper class for windows.
 */
public class WindowHelper {
    /**
     * Shows a modal window. Blocks until the window has been closed.
     *
     * @param title           Title of the new window.
     * @param view            View for that window.
     * @param controllerClass Class of the controller.
     * @param <T>             Class of the controller.
     * @return Controller, after window has been closed.
     */
    public <T> T showModal(String title, FXMLView view, Class<T> controllerClass) {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(view.getView()));
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        return controllerClass.cast(view.getPresenter());
    }
}
