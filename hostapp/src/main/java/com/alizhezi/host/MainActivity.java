package com.alizhezi.host;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AssetManager mAssetManager;

    private Resources mResources;

    private Resources.Theme mTheme;

    private String  pluginApkName="plugin.apk";
    private TextView mTvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvContent = ((TextView) findViewById(R.id.tv_content));
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);

        Utils.extractAssets(this,pluginApkName);
    }


    @Override
    public AssetManager getAssets() {
        if (mAssetManager==null){

            return super.getAssets();
        }


        return  mAssetManager;
    }

    @Override
    public Resources getResources() {
        if (mResources==null){

            return super.getResources();
        }

        return  mResources;
    }

    @Override
    public Resources.Theme getTheme() {
        if (mTheme==null){

            return super.getTheme();
        }

        return  mTheme;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button1:


                break;
        }
    }
}
