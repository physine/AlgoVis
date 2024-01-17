package com.example.algovis.algorithms.pathFinding;

import com.example.algovis.models.Cell;
import com.example.algovis.models.GridModel;

import java.util.List;

public interface SearchAlgorithm {
    boolean hasNext();
    List<Cell> nextState();
    void setGridModel(GridModel gridModel);
}
