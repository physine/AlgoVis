package com.example.algovis.services;

import com.example.algovis.algorithms.mazeGeneration.MazeGenerator;
import com.google.inject.Inject;

public class MazeGenerationService {

    private MazeGenerator mazeGenerator;

    @Inject
    public MazeGenerationService(MazeGenerator mazeGenerator) {
        this.mazeGenerator = mazeGenerator;
    }
}
