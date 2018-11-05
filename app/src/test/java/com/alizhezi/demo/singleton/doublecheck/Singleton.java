package com.alizhezi.demo.singleton.doublecheck;

/***
 * 双重校验
 *
 * 　当一个共享变量被volatile修饰时，它会保证修改的值立即被更新到主存“
 */
public class Singleton {

    /***
     * 　当一个共享变量被volatile修饰时，它会保证修改的值立即被更新到主存“
     *   变量修改，所有线程都可以访问最新的数据
     *
     *   会屏蔽虚拟机一些优化代码
     */
    private volatile static Singleton instance=null;

    private Singleton() {
    }

    public static Singleton getInstance(){

        if (instance==null){

            synchronized (Singleton.class){

                if (instance==null){

                    instance=new Singleton();
                }

            }

        }

        return  instance;
    }
}
