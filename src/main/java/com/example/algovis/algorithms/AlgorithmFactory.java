package com.example.algovis.algorithms;

import java.util.NoSuchElementException;

public class AlgorithmFactory {

    public static SearchAlgorithm getAlgorithm(String searchAlgorithm){
        switch (searchAlgorithm){
            case "Depth-First Search (DFS)":
                return new DFS();
            case "Breadth-First Search (BFS)":
                return new BFS();
            case "Dijkstra's Algorithm":
                return new Dijkstra();
            case "A* Search Algorithm":
                return new AStar();
            default:
                throw new NoSuchElementException(searchAlgorithm + " not in list of algorithm options.");
        }
    }
}
