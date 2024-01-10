package com.example.algovis.models.gridModleStates;

import com.example.algovis.models.GridModel;

public class PreSearchState implements GridState{
    @Override
    public void handleStartBtn(GridModel gridModel) {
        if (gridModel.isReadyToStartSearch())
            gridModel.setState(new SearchingState());
    }

    @Override
    public void handleResetBtn(GridModel gridModel) {
            gridModel.resetGrid();
    }
}
