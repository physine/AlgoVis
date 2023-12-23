module com.example.algovis {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.algovis to javafx.fxml;
    exports com.example.algovis;
}