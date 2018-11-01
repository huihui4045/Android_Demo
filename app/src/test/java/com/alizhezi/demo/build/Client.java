package com.alizhezi.demo.build;

import org.junit.Test;

public class Client {

    @Test
    public void test(){

//        WorkBuilder workBuilder=new WorkBuilder();
//
//        workBuilder.makeFloor("欧式地板");
//        workBuilder.makeWindow("法式窗户");
//
//        Room room = workBuilder.build();

        Room room=new WorkBuilder().makeFloor("1").makeWindow("2").build();

        System.out.printf(room.toString());
    }
}
