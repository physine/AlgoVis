package com.example.algovis.presentation;

import com.example.algovis.controllers.GridController;
import com.example.algovis.models.Cell;
import com.example.algovis.models.GridModel;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.List;

public class GridBuilder {

    GridController gridController;

    public void buildGridUI(GridPane gridPane, GridModel gridModel) {
        // Use Platform.runLater to ensure UI updates are done on the JavaFX Application Thread
        Platform.runLater(() -> {
            gridPane.getChildren().clear();

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
                    applyCellStyle(pane, cell);

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
            case Searched:
                Color color = calculateColorBasedOnDepth(cell.getSearchDepth());
                pane.setStyle("-fx-background-color: " + toRgbString(color) + ";");
                break;
            case EmptyCell:
            default:
                pane.getStyleClass().add("empty-cell-style");
                break;
        }
    }

    private Color calculateColorBasedOnDepth(int depth) {
        // Define a list of colors for the gradient
        List<Color> colors = List.of(
                Color.DEEPSKYBLUE, Color.LIMEGREEN, Color.CORAL, Color.MEDIUMPURPLE,
                Color.CYAN, Color.GOLDENROD, Color.TOMATO, Color.PLUM, Color.TURQUOISE
        );

        // Increase depthPerColor for slower color transitions
        double depthPerColor = 800.0; // Adjust this value for desired effect

        // Calculate total depth range and normalized depth
        double totalDepthRange = depthPerColor * (colors.size() - 1);
        double normalizedDepth = Math.min(1.0, depth / totalDepthRange);

        // Determine which two colors to interpolate between
        int colorIndex = (int) (normalizedDepth * (colors.size() - 1));
        Color startColor = colors.get(colorIndex);
        Color endColor = colors.get(Math.min(colorIndex + 1, colors.size() - 1));

        // Calculate the interpolation factor within the current color range
        double localInterpolation = (normalizedDepth % (1.0 / (colors.size() - 1))) * (colors.size() - 1);

        // Interpolate between the two colors
        return startColor.interpolate(endColor, localInterpolation);
    }


    private String toRgbString(Color color) {
        return String.format("rgba(%d, %d, %d, %f)",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255),
                color.getOpacity());
    }

    public void setGridController(GridController gridController) {
        this.gridController = gridController;
    }
}