package com.example.algovis.presentation;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class GridBuilder {
    public static void buildGrid(GridPane gridPane, int rows, int columns) {
        gridPane.getChildren().clear();
        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();

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

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Pane pane = new Pane();
                pane.getStyleClass().add("empty-cell-style");
                gridPane.add(pane, j, i);
            }
        }
    }
}
