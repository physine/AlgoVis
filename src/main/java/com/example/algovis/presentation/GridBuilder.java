package com.example.algovis.presentation;

import com.example.algovis.controllers.CellClickHandler;
import com.example.algovis.models.GridModel;
import com.example.algovis.models.cell.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class GridBuilder {

    // This field could be managed outside GridBuilder, in the controller for instance
//    private static ActionState currentActionState = ActionState.NONE;

    public void buildGrid(GridPane gridPane, GridModel gridModel, CellClickHandler clickHandler) {
        int rows = gridModel.getRows();
        int columns = gridModel.getColumns();

        // Clear existing setup
        gridPane.getChildren().clear();
        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();

        // Set up the column and row constraints
        for (int i = 0; i < columns; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / columns);
            gridPane.getColumnConstraints().add(colConst);
        }

        for (int i = 0; i < rows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / rows);
            gridPane.getRowConstraints().add(rowConst);
        }

        // Populate the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cell cell = gridModel.getCell(i, j);
                Pane pane = new Pane();

                // Apply styles based on the cell type
                pane.getStyleClass().add(cell.getStyle());
                System.out.println(pane.getStyleClass().toString());

                // Add event listeners
                addEventListeners(pane, i, j, gridModel, clickHandler);

                gridPane.add(pane, j, i);
            }
        }
    }

    private static void addEventListeners(Pane pane, int row, int col, GridModel gridModel, CellClickHandler clickHandler) {
        pane.setOnMouseClicked(event -> clickHandler.handle(event, row, col, gridModel));
        // Add other event listeners as needed
    }
}