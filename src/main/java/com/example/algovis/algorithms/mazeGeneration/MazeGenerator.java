package com.example.algovis.algorithms.mazeGeneration;

import com.example.algovis.models.GridModel;

public interface MazeGenerator {
    void setGridModel(GridModel gridModel);
    void generate();
}
