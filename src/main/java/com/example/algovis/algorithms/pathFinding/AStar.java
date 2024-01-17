package com.example.algovis.algorithms.pathFinding;

import com.example.algovis.models.Cell;
import com.example.algovis.models.GridModel;

import java.util.List;

public class AStar implements SearchAlgorithm {

    private GridModel gridModel;

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public List<Cell> nextState() {
        return null;
    }

    @Override
    public void setGridModel(GridModel gridModel) {
        this.gridModel = gridModel;
    }
}
