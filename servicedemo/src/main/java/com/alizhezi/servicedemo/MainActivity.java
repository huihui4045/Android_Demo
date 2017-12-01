package com.alizhezi.servicedemo;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    private ServiceConnection connection;

    private ServiceDemo serviceDemo;

    private String TAG=this.getClass().getSimpleName();
    private TextView viewById;


    private StickyBroadcastReceiver mStickyBroadcastReceiver;


    private Intent intentService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewById = ((TextView) findViewById(R.id.text));

        intent = new Intent(this, ServiceDemo.class);

        intentService=new Intent(MainActivity.this,MyIntentService.class);

        intent.putExtra("data", "来自Activity");


        connection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

                 serviceDemo = ((ServiceDemo.LocalBinder) service).getService();

                Log.d(TAG,"onServiceConnected");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

                Log.d(TAG,"onServiceDisconnected:"+name.getClassName());
            }
        };


        mStickyBroadcastReceiver=new StickyBroadcastReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter intentFilter=new IntentFilter("com.alizhezi.data.MY_STICKY");

        registerReceiver(mStickyBroadcastReceiver,intentFilter);
    }



    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(mStickyBroadcastReceiver);
    }

    public void startService(View view) {

        //startService(intent);

        startService(intentService);
    }

    public void stopService(View view) {
       // stopService(intent);

        stopService(intentService);

    }

    public void bindService(View view){

        bindService(intent,connection,BIND_AUTO_CREATE);
    }

    public void unBindService(View view){

        unbindService(connection);
    }

    public void getData(View view){

        viewById.setText(""+serviceDemo.getCount());
    }

    public static class UserBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {


            Log.e("MainActivity","收到了夸应用的广播");


        }
    }
}
