package com.alizhezi.plugin;

import android.content.Context;

import com.alizhezi.pluginlibrary.IDynamic;

public class Dynamic implements IDynamic {
    @Override
    public String getStringForResId(Context context) {
        return context.getResources().getString(R.string.plugin_app_hello_world);
    }

    @Override
    public String getContent(){


        return "来自插件APK";
    }
}
