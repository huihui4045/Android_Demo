package com.alizhezi.demo.factory;

public class Factory {

    public static Api  create(int type){

        switch (type){

            case 1:

                return new ImplA();

            case 2:

                return new ImplB();



                default:

                    return new ImplA();
        }
    }


    public  static <T extends Api> T create(Class<T> clz){

        Api api=null;

        try {
            api=(Api)Class.forName(clz.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return (T)api;


    }
}
