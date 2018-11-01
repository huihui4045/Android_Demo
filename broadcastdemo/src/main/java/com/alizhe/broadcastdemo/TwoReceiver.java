package com.alizhe.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TwoReceiver extends BroadcastReceiver {

    private String TAG = "TwoReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e(TAG, "TwoReceiver");
    }
}
