package com.alizhezi.demo.hook;

import android.os.Handler;
import android.util.Log;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AMSHookHelper {

    private static String TAG = "AMSHookHelper";

    public static void hookActivityThreadH() {

        Object sCurrentActivityThread =
            RefInvoke.getStaticFieldObject("android.app.ActivityThread", "sCurrentActivityThread");

        Handler mH =
            (Handler)RefInvoke.getFieldObject("android.app.ActivityThread", sCurrentActivityThread,
                                              "mH");

        RefInvoke.setFieldObject(Handler.class, mH, "mCallback", new MockCallback(mH));
    }

    public static void hookStartActivityByAMS() throws Exception {

        Object IActivityManagerSingleton =
            RefInvoke.getStaticFieldObject("android.app.ActivityManager",
                                           "IActivityManagerSingleton");

        final Object mInstance =
            RefInvoke.getFieldObject("android.util.Singleton", IActivityManagerSingleton,
                                     "mInstance");

        Class proxyClass = Class.forName("android.app.IActivityManager");

        Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                                              new Class[] { proxyClass }, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                    //Log.e(TAG,"method:"+method.getName());

                    if (method.getName().equals("startActivity")) {

                        Log.e(TAG, "hook AMS  startActivity");

                        return method.invoke(mInstance, args);
                    }

                    return method.invoke(mInstance, args);
                }
            });

        RefInvoke.setFieldObject("android.util.Singleton", IActivityManagerSingleton, "mInstance",
                                 proxy);
    }
}
