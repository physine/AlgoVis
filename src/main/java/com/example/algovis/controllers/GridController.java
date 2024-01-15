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

import java.util.ArrayList;

public class GridController{

    private GridModel gridModel;

    private GridBuilder gridBuilder;

    private GridSearchService gridSearchService;

    @FXML
    private Button startBtn;

    @FXML
    private Button resetBtn;

    @FXML
    private Slider speedCoefficientSlider;

    @FXML
    private Button startCellBtn;

    @FXML
    private Button obstacleCellBtn;

    @FXML
    private Button finalCellBtn;

    @FXML
    public GridPane gridView;

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
        gridSearchService.setGridController(this);
        this.gridSearchService.setGridModel(gridModel);
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
        SearchAlgorithm selectedAlgorithm = AlgorithmFactory.getAlgorithm(algorithms[0]);
        gridSearchService.setSearchAlgorithm(selectedAlgorithm);

        gridView.getStyleClass().add("grid-style");

        speedCoefficientSlider.setMin(0.1);
        speedCoefficientSlider.setMax(1.0);

        updateGridUI();
    }

    @FXML
    public void onStartButtonClick(){
        gridModel.handleStart();
    }

    public GridSearchService getGridSearchService() {
        return gridSearchService;
    }

    public void onSearchCompleted(){
        gridModel.setState(new PreSearchState());
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
        gridSearchService.setDelayCoefficient(speedCoefficientSlider.getValue());
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
        if ((gridModel.getState() instanceof PreSearchState) && cellMarker != CellMarkerState.None){
            gridModel.handleGridPaneClick(cellMarker, row, col);
            updateGridUI();
        }
    }

    public void handleGridPaneMouseEnter(MouseEvent event, int row, int col) {
        if ((gridModel.getState() instanceof PreSearchState) && cellMarker == CellMarkerState.Obstacle){
            gridModel.handleGridPaneHover(row, col);
            updateGridCellUI(gridModel.getCell(row, col));
        }
    }

    public void handleGridPaneMouseStartDragging(MouseEvent event, int row, int col) {
        if ((gridModel.getState() instanceof PreSearchState) && cellMarker == CellMarkerState.Obstacle){
            isDragging = true;
            gridModel.handleGridPaneHover(row, col);
            updateGridCellUI(gridModel.getCell(row, col));
        }
    }

    public void handleGridPaneMouseStopDragging(MouseEvent event, int row, int col) {
        if ((gridModel.getState() instanceof PreSearchState) && cellMarker == CellMarkerState.Obstacle){
            isDragging = false;
            gridModel.handleGridPaneHover(row, col);
            updateGridCellUI(gridModel.getCell(row, col));
        }
    }

    public void updateGridUI(){
        System.out.println("[i] Updating UI (controller) gridModel: "+gridModel);
        gridBuilder.buildGridUI(gridView, gridModel);
        System.out.println("[i] Updated UI (controller)");
    }

    public void updateGridCellUI(Cell cell){
        gridBuilder.buildCellUI(gridView, cell);
    }

    public void updateGridCellUI(ArrayList<Cell> cells){
        for (Cell cell : cells) {
            gridBuilder.buildCellUI(gridView, cell);
        }
    }
}
