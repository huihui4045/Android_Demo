package com.alizhezi.demo.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class EvilInstrumentation extends Instrumentation {

    private final String TAG = "EvilInstrumentation";

    Instrumentation mBase;

    public EvilInstrumentation(Instrumentation base) {
        mBase = base;
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token,
        Activity target, Intent intent, int requestCode, Bundle options) {

        Log.e(TAG, "hook startActivity");

        Class[] pareTypes = new Class[] {
            Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, int.class,
            Bundle.class
        };

        Object[] pareValues =
            new Object[] { who, contextThread, token, target, intent, requestCode, options };

        ActivityResult result =
            (ActivityResult)RefInvoke.invokeInstanceMethod(mBase, "execStartActivity", pareTypes,
                                                           pareValues);

        return result;
    }
}
