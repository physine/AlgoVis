package com.example.algovis.presentation;

import com.example.algovis.controllers.GridController;
import com.example.algovis.models.Cell;
import com.example.algovis.models.GridModel;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.*;

public class GridBuilder {

    GridController gridController;

    public void buildGridUI(GridPane gridPane, GridModel gridModel) {
        // Use Platform.runLater to ensure UI updates are done on the JavaFX Application Thread
        Platform.runLater(() -> {
            System.out.println("[i] in buildGridUI 1 gridPane: " + (gridPane == null));
            gridPane.getChildren().clear(); // Clear existing children
            System.out.println("[i] in buildGridUI 1.1");

            int rows = gridModel.getRows();
            int cols = gridModel.getColumns();

            // Set up column and row constraints to evenly distribute space
            gridPane.getColumnConstraints().clear();
            gridPane.getRowConstraints().clear();

            for (int i = 0; i < cols; i++) {
                ColumnConstraints colConst = new ColumnConstraints();
                colConst.setHgrow(Priority.ALWAYS);
                colConst.setFillWidth(true);
                gridPane.getColumnConstraints().add(colConst);
            }

            for (int i = 0; i < rows; i++) {
                RowConstraints rowConst = new RowConstraints();
                rowConst.setVgrow(Priority.ALWAYS);
                rowConst.setFillHeight(true);
                gridPane.getRowConstraints().add(rowConst);
            }

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    Pane pane = new Pane();
                    Cell cell = gridModel.getCell(i, j);
                    applyCellStyle(pane, cell); // Apply style based on cell type

                    int finalI = i;
                    int finalJ = j;
                    setupEventHandlers(pane, finalI, finalJ);
                    gridPane.add(pane, j, i);
                }
            }
        });
    }

    private void setupEventHandlers(Pane pane, int row, int col) {
        pane.setOnMousePressed(event -> gridController.handleGridPaneClick(event, row, col));
        pane.setOnMouseClicked(event -> gridController.handleGridPaneMouseStartDragging(event, row, col));
        pane.setOnMouseEntered(event -> gridController.handleGridPaneMouseEnter(event, row, col));
        pane.setOnMouseReleased(event -> gridController.handleGridPaneMouseStopDragging(event, row, col));
    }


    public void buildCellUI(GridPane gridPane, Cell cell) {
        int row = cell.getX();
        int col = cell.getY();

        for (Node node : gridPane.getChildren()) {
            // Default to 0 if getRowIndex or getColumnIndex returns null
            int nodeRow = GridPane.getRowIndex(node) != null ? GridPane.getRowIndex(node) : 0;
            int nodeCol = GridPane.getColumnIndex(node) != null ? GridPane.getColumnIndex(node) : 0;

            if (nodeRow == row && nodeCol == col && node instanceof Pane) {
                Pane pane = (Pane) node;
                applyCellStyle(pane, cell); // Apply the updated style
                break; // Break the loop once the specific cell is found and updated
            }
        }
    }

    private void applyCellStyle(Pane pane, Cell cell) {
        // Clear previous style classes
        pane.getStyleClass().clear();

        switch (cell.getState()) {
            case StartCell:
                pane.getStyleClass().add("start-cell-style");
                break;
            case EndCell:
                pane.getStyleClass().add("end-cell-style");
                break;
            case ObstacleCell:
                pane.getStyleClass().add("obstacle-cell-style");
                break;
            case EmptyCell:
            default:
                pane.getStyleClass().add("empty-cell-style");
                break;
        }
    }

    public void setGridController(GridController gridController) {
        this.gridController = gridController;
    }
}