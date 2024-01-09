package com.example.algovis.controllers;

import com.example.algovis.models.GridModel;
import javafx.scene.input.MouseEvent;

public interface CellClickHandler {
    void handle(MouseEvent event, int row, int col, GridModel gridModel);
}
