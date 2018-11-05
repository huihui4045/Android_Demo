package com.alizhezi.demo.singleton;

import com.alizhezi.demo.singleton.pool.Connection;

import org.junit.Test;

public class Client {

    @Test
    public void test(){

       // SingleTon.instance.test();

        //Singleton instance = Singleton.getInstance();


        for (int i = 0; i < 20; i++) {

            Connection connection = Connection.getConnection();


            System.out.println("con:"+connection);
        }
    }
}
