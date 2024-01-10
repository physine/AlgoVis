package com.example.algovis.models;


import com.example.algovis.models.gridModleStates.GridState;
import com.example.algovis.models.gridModleStates.PreSearchState;

public class GridModel {

    private GridState state;
    private final int rows = 70;
    private final int columns = 70;
    private Point startCellLocation;
    private Point endCellLocation;

    public GridModel() {
        state = new PreSearchState();
    }

    public void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

            }
        }
    }

    // =============== Data Operations =============== //

    public void resetGrid(){
    }

    private boolean isValidPosition(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    public boolean isReadyToStartSearch(){
        return startCellLocation != null && endCellLocation != null;
    }

    // =============== View Event Handlers =============== //

    public void handleStart(){
        state.handleStartBtn(this);
    }

    public void handleReset(){
        state.handleResetBtn(this);
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
