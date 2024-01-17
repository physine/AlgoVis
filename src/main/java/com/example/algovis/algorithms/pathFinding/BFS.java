package com.example.algovis.algorithms.pathFinding;

import com.example.algovis.models.Cell;
import com.example.algovis.models.GridModel;
import com.example.algovis.models.Point;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BFS implements SearchAlgorithm {

    private GridModel gridModel;
    private Queue<Point> queue;
    private boolean[][] visited;
    private boolean pathFound = false;
    private boolean isInitialized = false;



    @Override
    public boolean hasNext() {
        if (!isInitialized) {
            initializeSearch();
        }
        return !pathFound && !queue.isEmpty();
    }

    @Override
    public List<Cell> nextState() {
        List<Cell> changedCells = new ArrayList<>();

        if (queue.isEmpty()) {
            return changedCells;
        }

        Point current = queue.poll(); // Using poll instead of pop
        int row = current.x;
        int col = current.y;

        if (!isValidPosition(row, col) || visited[row][col]) {
            return changedCells; // Ignore invalid or already visited cells
        }

        visited[row][col] = true;
        Cell currentCell = gridModel.getCell(row, col);

        if (currentCell.getState() == Cell.CellType.ObstacleCell) {
            return changedCells; // Skip obstacle cells
        }

        if (currentCell.getState() == Cell.CellType.EmptyCell) {
            currentCell.setState(Cell.CellType.Searched);
            currentCell.setSearchDepth(0); // Initial depth for starting cell
            changedCells.add(currentCell);
        }

        if (currentCell.getState() == Cell.CellType.EndCell) {
            pathFound = true;
            return changedCells;
        }

        enqueueIfValid(row + 1, col); // Down
        enqueueIfValid(row - 1, col); // Up
        enqueueIfValid(row, col + 1); // Right
        enqueueIfValid(row, col - 1); // Left

        return changedCells;
    }

    private void enqueueIfValid(int row, int col) {
        if (isValidPosition(row, col) && !visited[row][col] &&
                gridModel.getCell(row, col).getState() != Cell.CellType.ObstacleCell) {
            queue.offer(new Point(row, col));
        }
    }

    private void initializeSearch() {
        if (gridModel == null)
        {
            return; // GridModel not set
        }
        Point startCellLocation = gridModel.getStartCellLocation();
        if (startCellLocation == null) {
            return; // Start cell not defined
        }

        queue = new ArrayDeque<>();
        visited = new boolean[gridModel.getRows()][gridModel.getColumns()];
        queue.offer(startCellLocation);
        isInitialized = true;
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < gridModel.getRows() && col >= 0 && col < gridModel.getColumns();
    }

    @Override
    public void setGridModel(GridModel gridModel) {
        this.gridModel = gridModel;
        isInitialized = false;
    }
}

