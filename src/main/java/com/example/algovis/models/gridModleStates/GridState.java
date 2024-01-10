package com.example.algovis.models.gridModleStates;

import com.example.algovis.models.GridModel;

public interface GridState {
    void handleStartBtn(GridModel gridModel);
    void handleResetBtn(GridModel gridModel);
}
