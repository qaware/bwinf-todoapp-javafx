package de.qaware.bwinf.todoapp;

import com.airhacks.afterburner.injection.Injector;
import de.qaware.bwinf.todoapp.model.MainModel;
import de.qaware.bwinf.todoapp.view.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * TODO application.
 */
public class TodoApp extends Application {
    /**
     * File to save the todo entries.
     */
    private static final Path MODEL_FILE = Paths.get("todo-data.csv");

    private MainModel model;

    /**
     * JVM entry function.
     *
     * @param args Commandline arguments.
     */
    public static void main(String[] args) {
        Application.launch(TodoApp.class, args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setupInjector();

        MainView mainView = new MainView();

        primaryStage.setTitle("BW-Inf Tag 2017 - Todo Application");
        primaryStage.setWidth(1024);
        primaryStage.setHeight(768);

        Scene scene = new Scene(mainView.getView());
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * Sets up dependency injection.
     *
     * @throws IOException If loading the model failed.
     */
    private void setupInjector() throws IOException {
        // Load model from file
        model = new MainModel();
        model.load(MODEL_FILE);

        Injector.setModelOrService(MainModel.class, model);
        Injector.setModelOrService(WindowHelper.class, new WindowHelper());
    }

    @Override
    public void stop() throws Exception {
        Injector.forgetAll();
        // Save model to file
        model.save(MODEL_FILE);
    }
}
