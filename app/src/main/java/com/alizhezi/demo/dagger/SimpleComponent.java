package com.alizhezi.demo.dagger;

import dagger.Component;

@Component(modules = SimpleModule.class)
public interface SimpleComponent {


    void inject(DaggerDemoActivity activity);
}
