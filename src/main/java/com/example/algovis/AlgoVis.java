package com.example.algovis;

import com.example.algovis.controllers.GridController;
import com.example.algovis.modules.ServiceModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AlgoVis extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Injector injector = Guice.createInjector(new ServiceModule());
        GridController gridController = injector.getInstance(GridController.class);

        FXMLLoader fxmlLoader = new FXMLLoader(AlgoVis.class.getResource("algoVis.fxml"));
        // Set a custom controller factory to use Guice
        fxmlLoader.setControllerFactory(clazz -> injector.getInstance(clazz));

        Parent root = fxmlLoader.load(); // Load the FXML file
        Scene scene = new Scene(root, 320, 240);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        stage.setResizable(false);
        stage.setMinWidth(1000);
        stage.setMinHeight(1000);
        stage.setTitle("AlgoVis");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}