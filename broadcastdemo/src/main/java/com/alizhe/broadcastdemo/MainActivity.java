package com.alizhe.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    PersonBroadcastReceiver mReceiver;

    IntentFilter intent;

    private String TAG=this.getClass().getSimpleName();
    private String action="com.alizhezi.data.MY_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mReceiver=new PersonBroadcastReceiver();


        intent=new IntentFilter(action);

    }

    @Override
    protected void onResume() {
        super.onResume();

       // registerReceiver(mReceiver,intent);
    }

    @Override
    protected void onPause() {
        super.onPause();

       // unregisterReceiver(mReceiver);
    }

    public void sendBroadcast(View view){

        Intent intent=new Intent();
        intent.setAction(action);

        sendBroadcast(intent);
    }


    public  class PersonBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

            Log.e(TAG,"PersonBroadcastReceiver 接受到了本地广播");

            //Toast.makeText(MainActivity.this,"PersonBroadcastReceiver 接受到了本地广播",Toast.LENGTH_LONG).show();
        }
    }

    public  static class ManBroadcastReceiver extends BroadcastReceiver{



        @Override
        public void onReceive(Context context, Intent intent) {

            Log.e("MainActivity","ManBroadcastReceiver 接受到了本地广播");

            //Toast.makeText(MainActivity.this,"ManBroadcastReceiver 接受到了本地广播",Toast.LENGTH_LONG).show();
        }
    }



}
