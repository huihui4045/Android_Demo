package com.alizhezi.demo.hook;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class MockCallback implements Handler.Callback {

    private Handler mBase;

    public MockCallback(Handler base) {
        mBase = base;
    }

    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.what) {

            case 100:

                Log.e("AMSHookHelper", "ActivityThread hook " + msg.obj);

                break;
        }

        mBase.handleMessage(msg);
        return true;
    }
}
