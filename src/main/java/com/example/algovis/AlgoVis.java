package com.example.algovis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AlgoVis extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AlgoVis.class.getResource("algoVis.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        stage.setMinWidth(850);
        stage.setMinHeight(500);
        stage.setTitle("AlgoVis");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}