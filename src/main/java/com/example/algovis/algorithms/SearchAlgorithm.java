package com.example.algovis.algorithms;

import com.example.algovis.models.GridModel;

public interface SearchAlgorithm {
    boolean hasNext();
    void nextState();
    void setGridModel(GridModel gridModel);
}
