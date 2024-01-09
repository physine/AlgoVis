package com.example.algovis.models;

import com.example.algovis.models.cell.Cell;
import com.example.algovis.models.cell.EmptyCell;

public class GridModel {

    private Cell[][] grid;
    private int rows = 80;
    private int columns = 80;

    public GridModel() {
        grid = new Cell[rows][columns];
        initializeGrid();
    }

    public void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = new EmptyCell(); // Initialize with default cell type
            }
        }
    }

//    public void resizeGrid(int newRows, int newColumns) {
//        Cell[][] newGrid = new Cell[newRows][newColumns];
//
//        // Determine the bounds for the copy operation
//        int maxRows = Math.min(newRows, rows);
//        int maxColumns = Math.min(newColumns, columns);
//
//        for (int i = 0; i < newRows; i++) {
//            for (int j = 0; j < newColumns; j++) {
//                if (i < maxRows && j < maxColumns) {
//                    newGrid[i][j] = grid[i][j]; // Copy existing cell
//                } else {
//                    newGrid[i][j] = new EmptyCell(); // Fill remainder with empty cells
//                }
//            }
//        }
//
//        rows = newRows;
//        columns = newColumns;
//        grid = newGrid;
//    }

    public Cell getCell(int row, int column) {
        if (isValidPosition(row, column)) {
            return grid[row][column];
        }
        return null;
    }

    public void setCell(int row, int column, Cell cell) {
        if (isValidPosition(row, column)) {
            grid[row][column] = cell;
        }
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    private boolean isValidPosition(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }
}
