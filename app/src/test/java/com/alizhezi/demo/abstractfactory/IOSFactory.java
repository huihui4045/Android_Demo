package com.alizhezi.demo.abstractfactory;

public class IOSFactory implements IFactory {
    @Override
    public IApi create() {
        return new IOSApi();
    }
}
