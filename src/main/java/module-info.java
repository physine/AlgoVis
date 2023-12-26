module com.example.algovis {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.algovis;
    exports com.example.algovis.controllers;

    opens com.example.algovis.controllers to javafx.fxml;
}