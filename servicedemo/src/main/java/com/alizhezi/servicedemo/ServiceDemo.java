package com.alizhezi.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by molu_ on 2017/11/28.
 */

public class ServiceDemo extends Service {
    private String TAG = this.getClass().getSimpleName();

    private LocalBinder mLocalBinder;

    private int count;

    private boolean isExit = false;

    private Thread mThread;

    public class LocalBinder extends Binder {

        public ServiceDemo getService() {

            return ServiceDemo.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Log.d(TAG, "onBind");
        return mLocalBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    public void stop() {
        isExit = true;
    }

    public int getCount() {

        return count;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate");

        mLocalBinder = new LocalBinder();

        mThread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (!isExit) {

                    count++;

                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Log.e(TAG,"数值："+count);
                    if (count==10){
                        stopSelf();
                    }

                }
            }
        });

        mThread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Log.d(TAG,"onStartCommand:"+intent.getStringExtra("data"));

        int flag = super.onStartCommand(intent, flags, startId);

        Log.d(TAG, "onStartCommand:" + intent.getStringExtra("data") + "   " + flag);
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {

        Log.d(TAG, "onDestroy");

        isExit=true;

        super.onDestroy();
    }
}
