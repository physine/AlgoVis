package com.example.algovis.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class MainController {

    @FXML
    private Button startBtn;

    @FXML
    private Button pauseBtn;

    @FXML
    private TextField rows;

    @FXML
    private TextField cols;

    @FXML
    private Slider speed;

    @FXML
    private GridPane grid;

    @FXML
    public void onStartButtonClick(ActionEvent event){
        System.out.println("onStartButtonClick");
    }

    @FXML
    public void onPauseButtonClick(ActionEvent event){
        System.out.println("onPauseButtonClick");
    }

    @FXML
    public void onRowsChange(KeyEvent event){
        System.out.println("onRowsChange"+rows.getText());
    }

    @FXML
    public void onColsChange(KeyEvent event){
        System.out.println("onColsChange");
    }

    @FXML
    public void onSpeedChange(){
        System.out.println("onSpeedChange");
    }
}
