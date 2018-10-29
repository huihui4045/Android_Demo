package com.alizhezi.demo;

import com.alizhezi.demo.factory.Api;
import com.alizhezi.demo.factory.Factory;

import org.junit.Test;

public class FactoryTest {


    @Test
    public void test(){



        Api api= Factory.create(1);

        api.operator();

    }
}
