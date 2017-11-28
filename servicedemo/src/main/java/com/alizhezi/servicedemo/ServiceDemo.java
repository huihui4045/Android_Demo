package com.alizhezi.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by molu_ on 2017/11/28.
 */

public class ServiceDemo extends Service {
    private String TAG=this.getClass().getSimpleName();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG,"onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Log.d(TAG,"onStartCommand:"+intent.getStringExtra("data"));

        int flag=super.onStartCommand(intent, flags, startId);

        Log.d(TAG,"onStartCommand:"+intent.getStringExtra("data")+"   "+flag);
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {

        Log.d(TAG,"onDestroy");

        super.onDestroy();
    }
}
