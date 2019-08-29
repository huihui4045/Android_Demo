package com.alizhezi.demo.dagger;


import dagger.Module;

@Module
public class SimpleModule {

    private DaggerDemoActivity activity;


    public SimpleModule(DaggerDemoActivity activity) {
        this.activity = activity;
    }
}
