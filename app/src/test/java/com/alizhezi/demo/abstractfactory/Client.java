package com.alizhezi.demo.abstractfactory;

import org.junit.Test;

public class Client {

    @Test
    public void test(){

        new AndroidFactory().create().show();
    }
}
