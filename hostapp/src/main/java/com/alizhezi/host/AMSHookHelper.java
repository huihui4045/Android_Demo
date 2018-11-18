package com.alizhezi.host;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.alizhezi.host.activity.StubActivity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AMSHookHelper {

    private static String TAG="AMSHookHelper";

    private static final String EXTRA_TARGET_INTENT="extra_target_intent";

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

                            Intent raw=null;

                            int index=0;

                            for (int i = 0; i < args.length; i++) {

                                if (args[i] instanceof Intent){

                                    index=i;

                                    break;
                                }
                            }

                            raw= (Intent) args[index];

                            Intent newIntent=new Intent();


                            String stubPackage="com.alizhezi.host";

                            ComponentName componentName=new ComponentName(stubPackage,StubActivity.class.getName());

                            newIntent.setComponent(componentName);

                            newIntent.putExtra(EXTRA_TARGET_INTENT,raw);

                            args[index]=newIntent;


                            return method.invoke(mInstance, args);
                        }

                        return method.invoke(mInstance, args);
                    }
                });

        RefInvoke.setFieldObject("android.util.Singleton", IActivityManagerSingleton, "mInstance",
                proxy);
    }

    public static void hookActivityThreadH() {

        Object sCurrentActivityThread =
                RefInvoke.getStaticFieldObject("android.app.ActivityThread", "sCurrentActivityThread");

        Handler mH =
                (Handler)RefInvoke.getFieldObject("android.app.ActivityThread", sCurrentActivityThread,
                        "mH");

        RefInvoke.setFieldObject(Handler.class, mH, "mCallback", new MockCallback(mH));
    }
}
