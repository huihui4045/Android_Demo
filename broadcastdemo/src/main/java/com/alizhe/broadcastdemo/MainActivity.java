package com.alizhe.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    PersonBroadcastReceiver mReceiver;

    IntentFilter intent;

    private String TAG=this.getClass().getSimpleName();
    private String action="com.alizhezi.data.MY_BROADCAST";

    private String sticky_action="com.alizhezi.data.MY_STICKY";

    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocalBroadcastManager=LocalBroadcastManager.getInstance(this);


        mReceiver=new PersonBroadcastReceiver();


        intent=new IntentFilter(action);

        intent.setPriority(500);

    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(mReceiver,intent);

        //mLocalBroadcastManager.registerReceiver(mReceiver,intent);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(mReceiver);
       // mLocalBroadcastManager.unregisterReceiver(mReceiver);
    }

    public void sendBroadcast(View view){

        Intent intent=new Intent();
        intent.setAction(action);

        Bundle bundle= new Bundle();

        bundle.putString("data","有序广播");

        intent.putExtras(bundle);

        //sendBroadcast(intent);

        sendBroadcast(intent,"com.huihui.permission.XXXX");

       // sendOrderedBroadcast(intent,null);

        //mLocalBroadcastManager.sendBroadcast(intent);





    }

    int index=0;

    public  void sendStickyBroadcast(View view){

        Intent intent=new Intent(sticky_action);

        intent.putExtra("data",index++);


        sendStickyBroadcast(intent);
    }


    public  class PersonBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {


            //Toast.makeText(MainActivity.this,"PersonBroadcastReceiver 接受到了本地广播",Toast.LENGTH_LONG).show();

           // String msg=intent.getStringExtra("data");


            Log.e(TAG,"PersonBroadcastReceiver 动态注册接受到了本地广播  500:");

           /* Bundle bundle=new Bundle();
            bundle.putString("data","第一个PersonBroadcastReceiver:"+msg);

            setResultExtras(bundle);


            String resultData = getResultData();

            Log.e(TAG,"resultData:"+resultData);

            Bundle resultExtras = getResultExtras(true);

            Log.e(TAG,"resultExtras:"+resultExtras.getString("data"));*/
        }
    }

    public  static class ManBroadcastReceiver extends BroadcastReceiver{



        @Override
        public void onReceive(Context context, Intent intent) {

            Log.e("MainActivity","ManBroadcastReceiver 接受到了本地广播  999:");

            //Toast.makeText(MainActivity.this,"ManBroadcastReceiver 接受到了本地广播",Toast.LENGTH_LONG).show();

           /* String msg=intent.getStringExtra("data");

            Log.e("MainActivity","ManBroadcastReceiver 接受到了本地广播  999:"+msg);

            Bundle bundle=new Bundle();
            bundle.putString("data","第二个ManBroadcastReceiver:"+msg);

            setResultExtras(bundle);

            setResultData("这个数据来自ManBroadcastReceiver");*/
        }
    }



}
