package com.example.algovis.models;

import javafx.scene.paint.Color;

public class Cell {

    public enum CellType {
        EmptyCell,
        StartCell,
        ObstacleCell,
        EndCell
    }

    CellType state = CellType.EmptyCell;

    private Color color;
    private int x;
    private int y;

    public CellType getState() {
        return state;
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
