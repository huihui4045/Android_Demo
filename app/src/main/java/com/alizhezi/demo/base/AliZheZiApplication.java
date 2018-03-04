package com.alizhezi.demo.base;

import android.app.Application;
import android.os.StrictMode;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by gavin
 * Time 2018/1/3  9:45
 * Email:molu_clown@163.com
 */

public class AliZheZiApplication extends Application {

    private static RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

       // enabledStrictMode();

       // refWatcher = LeakCanary.install(this);


        Log.e("AliZheZiApplication","thread:"+Thread.currentThread().getName());

    }


    public static RefWatcher getRefWatcher() {
        return refWatcher;
    }

    private static void enabledStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
                .detectAll() //
                .penaltyLog() //
                .penaltyDeath() //
                .build());
    }
}
