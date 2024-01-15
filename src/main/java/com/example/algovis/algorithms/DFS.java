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
        Cell cell = gridModel.getCell(10, 10);
        cell.setState(Cell.CellType.ObstacleCell);
    }

    @Override
    public void setGridModel(GridModel gridModel) {
        this.gridModel = gridModel;
    }
}
