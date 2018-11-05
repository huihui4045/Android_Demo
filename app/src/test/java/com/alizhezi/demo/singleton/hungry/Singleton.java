package com.alizhezi.demo.singleton.hungry;

/***
 * 饿汉式
 */
public class Singleton {


    private static final Singleton instance=new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance(){

        return  instance;
    }
}
