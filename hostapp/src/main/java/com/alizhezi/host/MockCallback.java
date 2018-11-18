package com.alizhezi.host;

import android.content.Intent;
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


                Object o=msg.obj;

                Intent raw = (Intent) RefInvoke.getFieldObject(o, "intent");

                Intent target = raw.getParcelableExtra("extra_target_intent");

                raw.setComponent(target.getComponent());

                break;
        }

        mBase.handleMessage(msg);
        return true;
    }
}
