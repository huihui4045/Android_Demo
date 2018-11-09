package com.alizhezi.demo.leak;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.TextView;
import com.alizhezi.demo.R;
import com.alizhezi.demo.base.BaseActivity;

public class LeakCanaryActivity extends BaseActivity {

   // private Handler mHandler=new Handler();
    private TextView textView;


    private AsyncTask<Void, Void, Integer> asyncTask;


    private static Config mConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_canary);

        textView = ((TextView) findViewById(R.id.text));


        LoginManager.getInstance(this).dealData();

       /* mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                textView.setText("abc");

            }
        },3*10*60);*/

        //finish();


        //模拟内存泄露
        if (mConfig == null) {
            mConfig = new Config();
            mConfig.setSize(18);
            mConfig.setTitle("老九门");
        }
       // testAsyncTask();


    }


    private void testAsyncTask() {
        asyncTask = new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... params) {
                int i = 0;
                SystemClock.sleep(20000);
                return i;
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                textView.setText(String.valueOf(integer));
            }
        };
        asyncTask.execute();

    }

    private void destroyAsyncTask() {
        if (asyncTask != null && !asyncTask.isCancelled()) {
            asyncTask.cancel(true);
        }
        asyncTask = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

       // AliZheZiApplication.getRefWatcher().watch(this);

       //mHandler.removeCallbacksAndMessages(null);

        //destroyAsyncTask();
    }


    static class Config {
        private int size;
        private String title;

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
