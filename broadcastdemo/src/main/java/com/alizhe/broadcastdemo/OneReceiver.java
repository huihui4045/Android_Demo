package com.alizhe.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class OneReceiver extends BroadcastReceiver {

    private String TAG = "OneReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e(TAG, "OneReceiver");
    }
}
