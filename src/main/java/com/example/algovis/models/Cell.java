package com.example.algovis.models;

import javafx.scene.paint.Color;

public class Cell {

    public enum CellType {
        EmptyCell,
        StartCell,
        ObstacleCell,
        EndCell,
        Searched,
        Head,
    }

    CellType state = CellType.EmptyCell;

    private Color color;
    private int searchDepth = 0;
    private int x;
    private int y;

    public CellType getState() {
        return state;
    }

    public int getSearchDepth() {
        return searchDepth;
    }

    public void setSearchDepth(int searchDepth) {
        this.searchDepth = searchDepth;
    }

    public void setState(CellType state) {
        this.state = state;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
