package com.example.algovis.controllers;

import com.example.algovis.models.GridModel;
import com.example.algovis.presentation.GridBuilder;
import com.example.algovis.services.GridSearchService;
import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class GridController {

    private GridModel gridModel;

    private GridSearchService gridSearchService;

    @FXML
    private Button startBtn;

    @FXML
    private Button pauseBtn;

    @FXML
    private TextField rows;

    @FXML
    private TextField cols;

    @FXML
    private Slider speedSlider;

    @FXML
    private Button startCellBtn;

    @FXML
    private Button obstacleBtn;

    @FXML
    private Button finalCellBtn;

    @FXML
    private GridPane gridPane;

    @FXML
    private ComboBox<String> algorithmComboBox;

    public GridController() {
    }

    @FXML
    public void initialize() {
        int defaultGridRows = 20;
        int defaultGridCols = 40;

        rows.setText(String.valueOf(defaultGridRows));
        cols.setText(String.valueOf(defaultGridCols));

        algorithmComboBox.getItems().addAll(
                "Depth-First Search (DFS)",
                "Breadth-First Search (BFS)",
                "Dijkstra's Algorithm",
                "A* Search Algorithm"
        );
        algorithmComboBox.setValue("Depth-First Search (DFS)");

        if (gridModel != null) {
            gridModel.setRows(defaultGridRows);
            gridModel.setColumns(defaultGridCols);
        }

        gridPane.getStyleClass().add("grid-style");
        GridBuilder.buildGrid(gridPane, defaultGridRows, defaultGridCols);
    }

    public void setGridModel(GridModel gridModel) {
        this.gridModel = gridModel;
    }

    @Inject
    public GridController(GridModel gridModel, GridSearchService gridSearchService) {
        this.gridModel = gridModel;
        this.gridSearchService = gridSearchService;
    }

    @FXML
    public void onStartButtonClick(ActionEvent event){
        System.out.println("onStartButtonClick");
        gridSearchService.startSearchTask();
    }

    @FXML
    public void onPauseButtonClick(ActionEvent event){
        gridSearchService.togglePause();
        System.out.println("onPauseButtonClick");
    }

    @FXML
    public void onRowsChange(KeyEvent event){
        System.out.println("onRowsChange" + rows.getText());
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
