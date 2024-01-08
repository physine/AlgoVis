package com.example.algovis.modules;

import com.example.algovis.services.GridSearchService;
import com.google.inject.AbstractModule;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
//        bind(GridSearchService.class).asEagerSingleton();
    }
}
