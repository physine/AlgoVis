package com.example.algovis.models.gridModleStates;

import com.example.algovis.eventMangment.events.OnSearchingState;
import com.example.algovis.models.GridModel;
import com.example.algovis.services.GridSearchService;
import com.google.common.eventbus.Subscribe;

import static com.example.algovis.AlgoVis.getInjector;

public class SearchingState implements GridState{

    @Subscribe
    public void onEnterState(OnSearchingState event) {
        GridSearchService gridSearchService = getInjector().getInstance(GridSearchService.class);
        gridSearchService.startSearchTask();
    }

    @Override
    public void handleStartBtn(GridModel gridModel) {

    }

    @Override
    public void handleResetBtn(GridModel gridModel) {

    }
}
