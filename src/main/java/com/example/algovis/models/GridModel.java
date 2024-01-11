package com.example.algovis.models;


import com.example.algovis.controllers.GridController;
import com.example.algovis.models.gridModleStates.GridState;
import com.example.algovis.models.gridModleStates.PreSearchState;

public class GridModel {

    private Cell[][] cells;
    private GridState state;
    private final int rows = 70;
    private final int columns = 70;
    private Point startCellLocation;
    private Point endCellLocation;

    public GridModel() {
        cells = new Cell[rows][columns];
        state = new PreSearchState();
        resetGrid();
    }

    // =============== Data Operations =============== //

    public void resetGrid(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = new Cell();
                cells[i][j].setX(i);
                cells[i][j].setY(j);
            }
        }
    }

    private boolean isValidPosition(int row, int column) {
        return row >= 0 && row < this.rows && column >= 0 && column < this.columns;
    }

    public boolean isReadyToStartSearch(){
        return startCellLocation != null && endCellLocation != null;
    }

    private void changeToStartCell(int row, int col){
        Cell cell = getCell(row, col);
        cell.setState(Cell.CellType.StartCell);
    }

    private void changeToEndCell(int row, int col){
        Cell cell = getCell(row, col);
        cell.setState(Cell.CellType.EndCell);
    }

    private void changeToObstacleCell(int row, int col){
        Cell cell = getCell(row, col);
        cell.setState(Cell.CellType.ObstacleCell);
    }

    // =============== Event Management =============== //

    public void handleStart(){
        state.handleStartBtn(this);
    }

    public void handleReset(){
        state.handleResetBtn(this);
    }

    public void handleGridPaneClick(GridController.CellMarkerState cellMarker, int row, int col){
        switch (cellMarker){
            case Start -> {
                // make sure there is only one start cell
                changeToStartCell(row, col);
            }
            case Obstacle -> {
                // make cell as obstacle
                changeToObstacleCell(row, col);
            }
            case End -> {
                // make sure there is only one end cell
                changeToEndCell(row, col);
            }
            default -> {

            }
        }
    }

    public void handleGridPaneHover(int row, int col){
        Cell cell = getCell(row, col);
        cell.setState(Cell.CellType.ObstacleCell);
    }

    // =============== Getter/Setters =============== //

    public void setState(GridState state){
        this.state = state;
    }

    public GridState getState(){
        return state;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Cell getCell(int row, int col) {
        if (isValidPosition(row, col)) {
            return cells[row][col];
        } else {
            throw new IllegalArgumentException("Cell position out of bounds: row " + row + ", col " + col);
        }
    }

    // =============== Other Data =============== //

    private static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
