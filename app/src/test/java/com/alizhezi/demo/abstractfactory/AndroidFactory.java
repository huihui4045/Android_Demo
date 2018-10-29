package com.alizhezi.demo.abstractfactory;

public class AndroidFactory implements IFactory {
    @Override
    public IApi create() {
        return new AndroidApi();
    }
}
