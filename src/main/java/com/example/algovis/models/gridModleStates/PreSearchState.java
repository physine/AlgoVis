package com.example.algovis.models.gridModleStates;

import com.example.algovis.eventMangment.EventManager;
import com.example.algovis.eventMangment.events.OnSearchingState;
import com.example.algovis.models.GridModel;
import com.google.inject.Injector;

import static com.example.algovis.AlgoVis.getInjector;

public class PreSearchState implements GridState{
    @Override
    public void handleStartBtn(GridModel gridModel) {
        if (gridModel.isReadyToStartSearch()){
            SearchingState nextState = getInjector().getInstance(SearchingState.class);
            gridModel.setState(nextState);
            EventManager eventManager = EventManager.getInstance();
            eventManager.register(nextState);
            eventManager.post(new OnSearchingState());
        }
    }

    @Override
    public void handleResetBtn(GridModel gridModel) {
            gridModel.resetGrid();
    }
}
