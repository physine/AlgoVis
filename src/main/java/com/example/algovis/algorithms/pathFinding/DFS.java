package com.example.algovis.algorithms.pathFinding;

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
        Cell cell = gridModel.getCell(0, 1);
        cell.setState(Cell.CellType.ObstacleCell);
    }

    @Override
    public void setGridModel(GridModel gridModel) {
        this.gridModel = gridModel;
    }
}
