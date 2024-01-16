package com.example.algovis.algorithms.pathFinding;

import com.example.algovis.models.GridModel;

public class BFS implements SearchAlgorithm {

    private GridModel gridModel;

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public void nextState() {

    }

    @Override
    public void setGridModel(GridModel gridModel) {
        this.gridModel = gridModel;
    }
}
