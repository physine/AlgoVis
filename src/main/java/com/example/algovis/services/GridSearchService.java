package com.example.algovis.services;

import com.example.algovis.algorithms.SearchAlgorithm;
import com.example.algovis.controllers.GridController;
import com.example.algovis.models.GridModel;
import com.google.inject.Singleton;
import javafx.concurrent.Task;

@Singleton
public class GridSearchService {

    static int i = 0;

    private GridModel gridModel;
    private GridController gridController;
    private SearchAlgorithm searchAlgorithm;
    private double delayCoefficient;
    private Task<Void> searchTask;

    public void startSearchTask() {
        System.out.println("\n[i] Starting Search");
        searchAlgorithm.setGridModel(gridModel);

        searchTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                System.out.println("[i] Starting Task");
                while (!isCancelled() && searchAlgorithm.hasNext()) {
                    System.out.println("[i] has next = true");
                    searchAlgorithm.nextState();
                    System.out.println("[i] Updating UI");
                    gridController.updateGridUI();
                    System.out.println("[i] Updated UI");
                    try {
                        int delay = 1000 - (int) (1000.0 * delayCoefficient);
                        System.out.println("[i] delayCoefficient "+delayCoefficient+" delay "+delay+" i "+i++);
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        throw e;
//                        if (isCancelled()) {
//                            break;
//                        }
                    }
                }
                return null;
            }
        };

        Thread searchThread = new Thread(searchTask);
        searchThread.setDaemon(true); // Set the thread as a daemon if it should not prevent the application from exiting
        searchThread.start();
    }

    public void setSearchAlgorithm(SearchAlgorithm searchAlgorithm) {
        this.searchAlgorithm = searchAlgorithm;
    }

    public void setDelayCoefficient(double delayCoefficient) {
        this.delayCoefficient = delayCoefficient;
    }

    public void setGridController(GridController gridController) {
        this.gridController = gridController;
    }

    public void setGridModel(GridModel gridModel) {
        this.gridModel = gridModel;
    }
}
