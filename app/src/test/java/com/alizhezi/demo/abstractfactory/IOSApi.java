package com.alizhezi.demo.abstractfactory;

public class IOSApi implements IApi {
    @Override
    public void show() {

        System.out.printf("生产IOS api");
    }
}
