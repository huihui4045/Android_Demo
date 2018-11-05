package com.alizhezi.demo.singleton.senum;

public enum  SingleTon {

    instance(1);

    private int index;

     SingleTon (int index){

        this.index=index;
    }

    public void test(){

        System.out.printf("===index==:"+index);
    }
}
