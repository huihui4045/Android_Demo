package com.alizhezi.host;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alizhezi.pluginlibrary.IDynamic;

import dalvik.system.DexClassLoader;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private TextView mTvContent;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);

        try {

            AMSHookHelper.hookStartActivityByAMS();
            AMSHookHelper.hookActivityThreadH();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvContent = ((TextView) findViewById(R.id.tv_content));

        DexClassLoader dexClassLoader;

        

    }




    @Override
    public void onClick(View v) {

        Log.e(TAG,"onClick");

        switch (v.getId()){

            case R.id.button1:

                Log.e(TAG,"button1");

                loadResources();

                try {
                    Class<?> mLoadClassDynamic = dexClassLoader.loadClass("com.alizhezi.plugin.Dynamic");

                    Object object = mLoadClassDynamic.newInstance();
                    IDynamic dynamic= (IDynamic) object;

                    String stringForResId = dynamic.getStringForResId(MainActivity.this);

                    Log.e(TAG,"stringForResId:"+stringForResId);
                    Log.e(TAG,"content:"+dynamic.getContent());

                    mTvContent.setText(stringForResId);
                } catch (Exception e) {
                    e.printStackTrace();

                    Log.e(TAG,"error:"+e.getMessage());

                    Toast.makeText(this,"error:"+e.getMessage(),Toast.LENGTH_LONG).show();
                }


                break;


            case R.id.start_plugin:

                Intent t = new Intent();
                t.setComponent(
                        new ComponentName("com.alizhezi.plugin",
                                "com.alizhezi.plugin.MainActivity"));

                startActivity(t);

                break;
        }
    }
}
