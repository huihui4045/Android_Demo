package com.alizhezi.servicedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by gavin
 * Time 2017/12/1  15:23
 * Email:molu_clown@163.com
 */

public class StickyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("StickyBroadcastReceiver", "接收到外部应用的粘性广播：" + intent.getIntExtra("data", 0));
    }
}
