package com.alizhezi.demo.proxy;

import org.junit.Test;

public class TestProxyFactory {

    @Test
    public void test() {

        IUserDao mUserDao = new UserDao();

        IUserDao proxyInstance = (IUserDao)new ProxyFactory(mUserDao).getProxyInstance();

        proxyInstance.save();
    }
}
