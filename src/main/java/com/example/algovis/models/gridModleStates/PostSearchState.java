package com.example.algovis.models.gridModleStates;

import com.example.algovis.models.GridModel;

public class PostSearchState implements GridState {
    @Override
    public void handleStartBtn(GridModel gridModel) {

    }

    @Override
    public void handleResetBtn(GridModel gridModel) {
        gridModel.setState(new PreSearchState());
        gridModel.resetGrid();
    }
}
