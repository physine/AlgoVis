module com.example.algovis {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.guice;

    exports com.example.algovis;
    exports com.example.algovis.controllers;
    exports com.example.algovis.models;

    exports com.example.algovis.modules to com.google.guice;
    exports com.example.algovis.services to com.google.guice;
    exports com.example.algovis.presentation to com.google.guice;

    opens com.example.algovis.controllers to javafx.fxml;
}