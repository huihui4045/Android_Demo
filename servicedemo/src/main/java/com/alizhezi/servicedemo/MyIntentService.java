package com.alizhezi.servicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by gavin
 * Time 2017/12/1  17:19
 * Email:molu_clown@163.com
 */

public class MyIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param
     */
    public MyIntentService() {
        super("MyIntentService");

        Log.e("MyIntentService","MyIntentService当前线城："+Thread.currentThread().getName());
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("MyIntentService","onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("MyIntentService","onDestroy");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {



        Log.e("MyIntentService","onHandleIntent当前线城："+Thread.currentThread().getName());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
