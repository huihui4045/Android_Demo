package com.alizhezi.host;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class BaseActivity extends AppCompatActivity {


    private AssetManager mAssetManager;

    private Resources mResources;

    private Resources.Theme mTheme;

    private String  pluginApkName="plugin.apk";
    private TextView mTvContent;

    protected DexClassLoader dexClassLoader;

    private String dexPath=null;

    protected String TAG=this.getClass().getSimpleName();

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);

        Utils.extractAssets(this,pluginApkName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        File extractFile = this.getFileStreamPath(pluginApkName);

        dexPath=extractFile.getPath();

        File fileRelease = getDir("dex", Context.MODE_PRIVATE);

        dexClassLoader=new DexClassLoader(dexPath,fileRelease.getAbsolutePath(),null,getClassLoader());


    }

    protected void loadResources(){

        AssetManager assetManager = null;
        try {
            assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);

            addAssetPath.invoke(assetManager,dexPath);

            mAssetManager=assetManager;

        }  catch (Exception e) {
            e.printStackTrace();

            Log.e(TAG,"loadResources:"+e.getMessage());
            Toast.makeText(this,"loadResources:"+e.getMessage(),Toast.LENGTH_LONG).show();

        }

        mResources=new Resources(mAssetManager,super.getResources().getDisplayMetrics(),
                super.getResources().getConfiguration());


        mTheme=mResources.newTheme();

        mTheme.setTo(super.getTheme());


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
}
