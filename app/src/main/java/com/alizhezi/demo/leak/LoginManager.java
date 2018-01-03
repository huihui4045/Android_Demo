package com.alizhezi.demo.leak;

import android.content.Context;

/**
 * Created by gavin
 * Time 2018/1/3  10:00
 * Email:molu_clown@163.com
 */

public class LoginManager {

    private static LoginManager mInstance;
    private Context mContext;





    private LoginManager(Context context) {
        this.mContext = context.getApplicationContext();
    }


    public static LoginManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (LoginManager.class) {
                if (mInstance == null) {
                    mInstance = new LoginManager(context);
                }
            }
        }
        return mInstance;
    }

    public void dealData() {
    }
}
