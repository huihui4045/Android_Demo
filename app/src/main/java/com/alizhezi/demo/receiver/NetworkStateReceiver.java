package com.alizhezi.demo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class NetworkStateReceiver extends BroadcastReceiver {

    private String TAG = "NetworkStateReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e(TAG, "监听到网络发生变化:action" + intent.getAction());

        Toast.makeText(context, intent.getAction(), Toast.LENGTH_LONG).show();


    }
}
