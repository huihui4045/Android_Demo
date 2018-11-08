package com.alizhezi.demo.hook;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public class HookHelper {

    private static String TAG = "HookHelper";

    public static void hookPackageManager(Context context) {

        //获取全局的CurrentActivityThread
        Object sCurrentActivityThread =
            RefInvoke.getStaticFieldObject("android.app.ActivityThread", "sCurrentActivityThread");

        //获取ActivityThread里面的sPackageManager
        Object sPackageManager =
            RefInvoke.getFieldObject("android.app.ActivityThread", sCurrentActivityThread,
                                     "sPackageManager");

        try {
            //创建一个代理对象

            Class<?> iPackageManagerInterface = Class.forName("android.content.pm.IPackageManager");

            Object proxy = Proxy.newProxyInstance(iPackageManagerInterface.getClassLoader(),
                                                  new Class[] { iPackageManagerInterface },
                                                  new HookHandler(sPackageManager));

            RefInvoke.setFieldObject(sCurrentActivityThread, "sPackageManager", proxy);

            PackageManager pm = context.getPackageManager();

            RefInvoke.setFieldObject(pm, "mPM", proxy);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

            Log.e(TAG, "e:" + e.getMessage());
        }
    }

    public static void hookActivityManager() {

        try {
            Class<?> clz = Class.forName("android.app.ActivityManagerNative");

            Field field = clz.getDeclaredField("getDefault");

            Object getDefault = field.get(null);

            Class<?> aClass = Class.forName("android.util.Singleton");

            Field sField = aClass.getDeclaredField("mInstance");

            Object rawActivityManager = sField.get(getDefault);

            Class<?> iActivityManagerInterface = Class.forName("android.app.IActivityManager");

            Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                                                  new Class[] { iActivityManagerInterface },
                                                  new HookHandler(rawActivityManager));

            RefInvoke.setFieldObject("android.util.Singleton", getDefault, "mInstance", proxy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
