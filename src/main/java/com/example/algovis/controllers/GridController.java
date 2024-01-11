package com.example.algovis.controllers;

import com.example.algovis.algorithms.AlgorithmFactory;
import com.example.algovis.algorithms.SearchAlgorithm;
import com.example.algovis.models.Cell;
import com.example.algovis.models.GridModel;
import com.example.algovis.models.gridModleStates.PreSearchState;
import com.example.algovis.presentation.GridBuilder;
import com.example.algovis.services.GridSearchService;
import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class GridController{

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
    private Button obstacleCellBtn;

    @FXML
    private Button finalCellBtn;

    @FXML
    private GridPane gridView;

    @FXML
    private ComboBox<String> algorithmComboBox;

    public enum CellMarkerState {
        None,
        Start,
        Obstacle,
        End
    }

    private CellMarkerState cellMarker = CellMarkerState.None;

    private boolean isDragging = false;

    public GridController() {
    }

    @Inject
    public GridController(GridModel gridModel, GridSearchService gridSearchService, GridBuilder gridBuilder) {
        this.gridModel = gridModel;
        this.gridSearchService = gridSearchService;
        this.gridBuilder = gridBuilder;
        gridBuilder.setGridController(this);
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

        gridView.getStyleClass().add("grid-style");

        updateGridUI();
    }

    @FXML
    public void onStartButtonClick(){
        gridModel.handleStart();
    }

    @FXML
    public void onResetButtonClick(){
        gridModel.handleReset();
        updateGridUI();
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
    private void onStartCellButtonClicked() {
        cellMarker = CellMarkerState.Start;
    }

    @FXML
    private void onEndCellButtonClicked() {
        cellMarker = CellMarkerState.End;
    }

    @FXML
    private void onObstacleCellButtonClicked() {
           cellMarker = CellMarkerState.Obstacle;
    }

    public void handleGridPaneClick(MouseEvent event, int row, int col) {
        System.out.println("Pane Click");
        if ((gridModel.getState() instanceof PreSearchState) && cellMarker != CellMarkerState.None){
            gridModel.handleGridPaneClick(cellMarker, row, col);
            updateCellUI(gridModel.getCell(row, col));
        }
    }

    public void handleGridPaneMouseEnter(MouseEvent event, int row, int col) {
//        System.out.println("Mouse Enter: row: "+row+"   "+"col: "+col+"     "+"event.isPrimaryButtonDown(): "+event.isPrimaryButtonDown());
        if (cellMarker == CellMarkerState.Obstacle ){
//            System.out.println("Mouse Enter in if");
            gridModel.handleGridPaneHover(row, col);
            updateCellUI(gridModel.getCell(row, col));
//            updateGridUI();
        }
    }

    public void handleGridPaneMouseStartDragging(MouseEvent event, int row, int col) {
//        System.out.println("Mouse Dragged start");
        if (cellMarker == CellMarkerState.Obstacle && event.isPrimaryButtonDown()){
            isDragging = true;
            gridModel.handleGridPaneHover(row, col);
            updateCellUI(gridModel.getCell(row, col));
//            updateGridUI();
        }
    }

    public void handleGridPaneMouseStopDragging(MouseEvent event, int row, int col) {
//        System.out.println("Mouse Dragged stop");
        if (cellMarker == CellMarkerState.Obstacle && event.isPrimaryButtonDown()){
            isDragging = false;
            gridModel.handleGridPaneHover(row, col);
            updateCellUI(gridModel.getCell(row, col));
//            updateGridUI();
        }
    }

    public void updateGridUI(){
        gridBuilder.buildGridUI(gridView, gridModel);
    }

    public void updateCellUI(Cell cell){
        gridBuilder.buildCellUI(gridView, cell);
    }
}
