package com.example.algovis.algorithms.mazeGeneration;

import com.example.algovis.models.Cell;
import com.example.algovis.models.GridModel;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomizedDFS implements MazeGenerator {

    private GridModel gridModel;

    @Inject
    public RandomizedDFS(GridModel gridModel) {
        this.gridModel = gridModel;
    }

    @Override
    public void setGridModel(GridModel gridModel) {
        this.gridModel = gridModel;
    }

    @Override
    public void generate() {
        // Initialize the grid as walls
        for (int i = 0; i < gridModel.getRows(); i++) {
            for (int j = 0; j < gridModel.getColumns(); j++) {
                gridModel.getCell(i, j).setState(Cell.CellType.ObstacleCell);
            }
        }

        // Start the maze generation from a random cell
        int startX = (int) (Math.random() * gridModel.getRows());
        int startY = (int) (Math.random() * gridModel.getColumns());
        carvePath(startX, startY);
    }

    private void carvePath(int x, int y) {
        if (!isValidPosition(x, y) || gridModel.getCell(x, y).getState() == Cell.CellType.EmptyCell) {
            return; // Stop if out of bounds or already visited
        }

        // Mark the current cell as a path
        gridModel.getCell(x, y).setState(Cell.CellType.EmptyCell);

        // Create a random order for directions
        List<int[]> directions = new ArrayList<>(List.of(
                new int[]{1, 0}, // Down
                new int[]{-1, 0}, // Up
                new int[]{0, 1}, // Right
                new int[]{0, -1}  // Left
        ));
        Collections.shuffle(directions);

        // Explore in random directions
        for (int[] dir : directions) {
            int nextX = x + dir[0] * 2;
            int nextY = y + dir[1] * 2;

            // Check if the next cell is valid and not already part of the path
            if (isValidPosition(nextX, nextY) && gridModel.getCell(nextX, nextY).getState() == Cell.CellType.ObstacleCell) {
                // Carve a path between the current and next cell
                gridModel.getCell(x + dir[0], y + dir[1]).setState(Cell.CellType.EmptyCell);
                carvePath(nextX, nextY);
            }
        }
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < gridModel.getRows() && y >= 0 && y < gridModel.getColumns();
    }
}
