package com.example.algovis.algorithms;

import com.example.algovis.models.Cell;
import com.example.algovis.models.GridModel;

public class DFS implements SearchAlgorithm {

    private GridModel gridModel;

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public void nextState() {
        System.out.println("[i] changing cell state 1");
        Cell cell = gridModel.getCell(10, 10);
        System.out.println("[i] changing cell state 2");
        cell.setState(Cell.CellType.ObstacleCell);
        System.out.println("[i] done changing cell state");
    }

    @Override
    public void setGridModel(GridModel gridModel) {
        this.gridModel = gridModel;
    }
}
