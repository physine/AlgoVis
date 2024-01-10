package com.example.algovis.controllers;

import com.example.algovis.algorithms.AlgorithmFactory;
import com.example.algovis.algorithms.SearchAlgorithm;
import com.example.algovis.models.GridModel;
import com.example.algovis.presentation.GridBuilder;
import com.example.algovis.services.GridSearchService;
import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;

public class GridController{

    private boolean obstacleModeActive = false;
    private boolean leftMouseButtonPressed = false;
    private GridModel gridModel;
    private GridBuilder gridBuilder;
    private GridSearchService gridSearchService;

    @FXML
    private Button startBtn;

    @FXML
    private Button resetBtn;

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

    @Inject
    public GridController(GridModel gridModel, GridSearchService gridSearchService) {
        this.gridModel = gridModel;
        this.gridSearchService = gridSearchService;
    }

    @FXML
    public void initialize() {
        int defaultGridRows = gridModel.getRows();
        int defaultGridCols = gridModel.getColumns();

        String[] algorithms = {
                "Depth-First Search (DFS)",
                "Breadth-First Search (BFS)",
                "Dijkstra's Algorithm",
                "A* Search Algorithm"
        };
        algorithmComboBox.getItems().addAll(algorithms[0], algorithms[1], algorithms[2], algorithms[3]);
        algorithmComboBox.setValue(algorithms[0]);

        gridPane.getStyleClass().add("grid-style");
        gridBuilder = new GridBuilder();
//        gridBuilder.buildGrid(gridPane, gridModel, this);

//        updateGridUI();
    }

    @FXML
    public void onStartButtonClick(){
        gridModel.handleStart();

    }

    @FXML
    public void onResetButtonClick(){
        gridModel.handleReset();
    }

    public void setGridModel(GridModel gridModel) {
        this.gridModel = gridModel;
    }

    @FXML
    public void onComboBoxClick(){
        String selectedAlgorithm = algorithmComboBox.getSelectionModel().getSelectedItem();
        SearchAlgorithm searchAlgorithm = AlgorithmFactory.getAlgorithm(selectedAlgorithm);
        gridSearchService.setSearchAlgorithm(searchAlgorithm);
    }

    @FXML
    public void onSpeedChange(){
    }

    @FXML
    private void onStartButtonClicked() {
//        currentActionState = ActionState.PLACE_START;
    }

    @FXML
    private void onEndButtonClicked() {
//        currentActionState = ActionState.PLACE_END;
    }

    @FXML
    private void onObstacleButtonClicked() {

    }
}
