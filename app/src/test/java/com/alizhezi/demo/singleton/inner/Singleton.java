package com.alizhezi.demo.singleton.inner;

/***
 * 匿名内部类
 */
public class Singleton {

    private Singleton() {

        System.out.println("Singleton");
    }

    /***
     * 只有调用getInstance 方法之后，才会实例化Singleton
     * @return
     */
    public static Singleton getInstance() {

        System.out.println("getInstance");

        return SingletonHolder.instance;
    }

    private static class SingletonHolder {

        /***
         * 虚拟机保证实例唯一性
         */
        private static Singleton instance = new Singleton();

        static {

            System.out.println("static SingletonHolder");
        }

        private SingletonHolder() {

        }
    }
}
