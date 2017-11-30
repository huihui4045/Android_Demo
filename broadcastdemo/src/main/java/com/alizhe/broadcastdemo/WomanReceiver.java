package com.alizhe.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by molu_ on 2017/11/30.
 */

public class WomanReceiver extends BroadcastReceiver  {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("WomanReceiver","  WomanReceiver  onReceive");
    }
}
