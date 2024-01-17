package com.example.algovis.algorithms.pathFinding;

import com.example.algovis.models.Cell;
import com.example.algovis.models.GridModel;
import com.example.algovis.models.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFS implements SearchAlgorithm {

    private GridModel gridModel;
    private Stack<Point> stack;
    private boolean[][] visited;
    private boolean pathFound = false;
    private boolean isInitialized = false;

    @Override
    public boolean hasNext() {
        if (!isInitialized) {
            initializeSearch();
        }
        return !pathFound && !stack.isEmpty();
    }

    @Override
    public List<Cell> nextState() {
        List<Cell> changedCells = new ArrayList<>();

        if (stack.isEmpty()) {
            return changedCells;
        }

        Point current = stack.pop();
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

        // Update the cell state to Searched if it's not a special cell
        if (currentCell.getState() == Cell.CellType.EmptyCell) {
            currentCell.setState(Cell.CellType.Searched);
            currentCell.setSearchDepth(0); // Initial depth for starting cell
            changedCells.add(currentCell);
        }

        if (currentCell.getState() == Cell.CellType.EndCell) {
            pathFound = true;
            return changedCells;
        }

        // Continue searching in all four directions, avoiding obstacles
        pushIfValid(row + 1, col, current, changedCells); // Down
        pushIfValid(row - 1, col, current, changedCells); // Up
        pushIfValid(row, col + 1, current, changedCells); // Right
        pushIfValid(row, col - 1, current, changedCells); // Left

        return changedCells;
    }

    private void pushIfValid(int row, int col, Point parent, List<Cell> changedCells) {
        if (isValidPosition(row, col) && !visited[row][col] &&
                gridModel.getCell(row, col).getState() != Cell.CellType.ObstacleCell) {

            Cell parentCell = gridModel.getCell(parent.x, parent.y);
            int newDepth = parentCell.getSearchDepth() + 1;

            Cell nextCell = gridModel.getCell(row, col);
            if (nextCell.getState() == Cell.CellType.EmptyCell) {
                nextCell.setState(Cell.CellType.Searched);
                nextCell.setSearchDepth(newDepth); // Set the depth based on parent's depth
                changedCells.add(nextCell);
            }

            stack.push(new Point(row, col));
        }
    }

    private void initializeSearch() {
        if (gridModel == null) {
            return; // GridModel not set
        }

        Point startCellLocation = gridModel.getStartCellLocation();
        if (startCellLocation == null) {
            return; // Start cell not defined
        }

        stack = new Stack<>();
        visited = new boolean[gridModel.getRows()][gridModel.getColumns()];
        stack.push(startCellLocation);
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