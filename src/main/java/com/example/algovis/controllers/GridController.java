package com.example.algovis.controllers;

import com.example.algovis.algorithms.AlgorithmFactory;
import com.example.algovis.algorithms.SearchAlgorithm;
import com.example.algovis.models.GridModel;
import com.example.algovis.models.cell.Cell;
import com.example.algovis.models.cell.EndCell;
import com.example.algovis.models.cell.ObstacleCell;
import com.example.algovis.models.cell.StartCell;
import com.example.algovis.presentation.GridBuilder;
import com.example.algovis.services.GridSearchService;
import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class GridController implements CellClickHandler{



    public enum ActionState {
        PLACE_START, PLACE_END, PLACE_OBSTACLE, NONE;
    }
    private ActionState currentActionState = ActionState.NONE;

    private GridModel gridModel;

    private GridBuilder gridBuilder;

    private GridSearchService gridSearchService;

    @FXML
    private Button startBtn;

    @FXML
    private Button pauseBtn;


//    @FXML
//    private TextField rows;
//
//    @FXML
//    private TextField cols;
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
        int defaultGridRows = gridModel.getRows();
        int defaultGridCols = gridModel.getColumns();

//        rows.setText(String.valueOf(defaultGridRows));
//        cols.setText(String.valueOf(defaultGridCols));

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

//        rows.textProperty().addListener((observable, oldValue, newValue) -> {
//            if (!newValue.matches("\\d*")) { // Regular expression for numeric values
//                rows.setText(newValue.replaceAll("[^\\d]", "")); // Replace non-digits
//            }
//        });
//
//        cols.textProperty().addListener((observable, oldValue, newValue) -> {
//            if (!newValue.matches("\\d*")) { // Regular expression for numeric values
//                cols.setText(newValue.replaceAll("[^\\d]", "")); // Replace non-digits
//            }
//        });

        gridBuilder = new GridBuilder();

        gridBuilder.buildGrid(gridPane, gridModel, this);

        updateGridUI();
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
    public void onComboBoxClick(){
        String selectedAlgorithm = algorithmComboBox.getSelectionModel().getSelectedItem();
        SearchAlgorithm searchAlgorithm = AlgorithmFactory.getAlgorithm(selectedAlgorithm);
        gridSearchService.setSearchAlgorithm(searchAlgorithm);
        System.out.println("onComboBoxClick - "+searchAlgorithm);
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
//        gridModel.resizeGrid(Integer.valueOf(event.getText()), Integer.valueOf(cols.getText()));
//        System.out.println("onRowsChange" + rows.getText());
//        updateGridUI();
    }

    @FXML
    public void onColsChange(KeyEvent event){
//        gridModel.resizeGrid(Integer.valueOf(cols.getText()), Integer.valueOf(event.getText()));
//        System.out.println("onColsChange");
//        updateGridUI();
    }

    @FXML
    public void onSpeedChange(){
        System.out.println("onSpeedChange");
    }

    @FXML
    private void onStartButtonClicked() {
        currentActionState = ActionState.PLACE_START;
    }

    @FXML
    private void onEndButtonClicked() {
        currentActionState = ActionState.PLACE_END;
    }

    @FXML
    private void onObstacleButtonClicked() {
        currentActionState = ActionState.PLACE_OBSTACLE;
    }

    public void updateGridUI(){
        gridBuilder.buildGrid(gridPane, gridModel, this);
    }

//    public void handlePenMouseClicked(MouseEvent event, int row, int col, GridModel gridModel) {
//        System.out.println("GridBuilder - handleMouseClicked");
//        if (event.getButton() == MouseButton.PRIMARY) {
//            // Example: Update the cell based on the currentActionState
//            switch (currentActionState) {
//                case PLACE_START:
//                    // Set start cell
//                    gridModel.setCell(row, col, new StartCell());
//                    Cell cell = gridModel.getCell(row, col);
//
//                    break;
//                case PLACE_END:
//                    gridModel.setCell(row, col, new EndCell());
//                    // Set end cell
//                    break;
//                case PLACE_OBSTACLE:
//                    // Set obstacle cell
//                    gridModel.setCell(row, col, new ObstacleCell());
//                    break;
//                default:
//                    break;
//            }
//        }
//    }

    @Override
    public void handle(MouseEvent event, int row, int col, GridModel gridModel) {
        System.out.println("handle - row: " + row + " col: " + col);
        if (event.getButton() == MouseButton.PRIMARY) {
            switch (currentActionState) {
                case PLACE_START:
                    // Set start cell
                    gridModel.setCell(row, col, new StartCell());
                    break;

                case PLACE_END:
                    gridModel.setCell(row, col, new EndCell());
                    // Set end cell
                    break;

                case PLACE_OBSTACLE:
                    // Set obstacle cell
                    gridModel.setCell(row, col, new ObstacleCell());
                    break;

                default:
                    break;
            }
        }
        updateGridUI();
    }
}
