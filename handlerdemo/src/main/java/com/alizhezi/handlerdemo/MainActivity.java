package com.alizhezi.handlerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private String TAG="MainActivity";

    private  int count;


    private Handler mHandler=null;

    private Runnable mRunnable = new Runnable() {

        public void run() {
            //为了方便 查看，我们用Log打印出来
            Log.e(TAG, Thread.currentThread().getName() + " " +count);
            count++;
//            setTitle("" +count);
            //每2秒执行一次
            mHandler.postDelayed(mRunnable, 2000);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(TAG, Thread.currentThread().getName() + " UI 线程" +count);
        //DownLoadAsyncTask task=new DownLoadAsyncTask();

       // task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");

        HandlerThread handlerThread=new HandlerThread("handlerThread");

        handlerThread.start();

        mHandler=new Handler(handlerThread.getLooper());

        mHandler.post(mRunnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mHandler.removeCallbacksAndMessages(null);
    }
}
