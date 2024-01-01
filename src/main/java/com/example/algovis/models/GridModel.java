package com.example.algovis.models;

import com.example.algovis.algorithms.SearchAlgorithm;
import com.example.algovis.models.cell.Cell;

public class GridaModel {

    private Cell[][] grid;
    private int rows;
    private int columns;

    private SearchAlgorithm searchAlgorithm;

    public GridaModel(Cell[][] grid, int rows, int columns, SearchAlgorithm searchAlgorithm) {
        this.grid = grid;
        this.rows = rows;
        this.columns = columns;
        this.searchAlgorithm = searchAlgorithm;
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = new Cell(CellType.EMPTY);
            }
        }
    }

    private boolean isValidPosition(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    public void resizeGrid(int newRows, int newColumns) {
        rows = newRows;
        columns = newColumns;
        grid = new Cell[newRows][newColumns];
        initializeGrid();
    }

    public void setCell(int row, int column, CellType type) {
        if (isValidPosition(row, column)) {
            grid[row][column] = new Cell(type);
        }
    }

    public Cell getCell(int row, int column) {
        if (isValidPosition(row, column)) {
            return grid[row][column];
        }
        return null;
    }

    public SearchAlgorithm getSearchAlgorithm() {
        return searchAlgorithm;
    }

    public void setSearchAlgorithm(SearchAlgorithm searchAlgorithm) {
        this.searchAlgorithm = searchAlgorithm;
    }
}
