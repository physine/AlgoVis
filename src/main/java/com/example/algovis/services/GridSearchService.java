package com.example.algovis.services;

import com.example.algovis.algorithms.SearchAlgorithm;
import com.example.algovis.models.GridModel;
import javafx.concurrent.Task;

public class GridSearchService {

    private GridModel gridModel;
    private SearchAlgorithm searchAlgorithm;
    private int delayInntravel;
    private Task<Void> searchTask;
    private boolean paused;

    public void startSearchTask() {
        System.out.println("GridSearchService - startSearch");

        searchTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                while (!isCancelled() && searchAlgorithm.hasNext()) {
                    if (paused) {
                        synchronized (this) {
                            wait();
                        }
                    }
                    performSearch();
                    try {
                        Thread.sleep(delayInntravel);
                    } catch (InterruptedException ex) {
                        if (isCancelled()) {
                            break;
                        }
                    }
                }
                return null;
            }
        };

        Thread searchThread = new Thread(searchTask);
        searchThread.setDaemon(true); // Set the thread as a daemon if it should not prevent the application from exiting
        searchThread.start();
    }

    public synchronized void togglePause() {
        paused = !paused;
        if (!paused) {
            notifyAll(); // Wake up the thread if it's now in the running state
        }
    }

    private void performSearch() {
        searchAlgorithm.nextState(gridModel);
        // Update grid model or UI based on the results of searchAlgorithm.nextState()
    }

    public void setSearchAlgorithm(SearchAlgorithm searchAlgorithm) {
        this.searchAlgorithm = searchAlgorithm;
    }

    public void setDelayInntravel(int delayInntravel) {
        this.delayInntravel = delayInntravel;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
