package com.alizhezi.demo.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.alizhezi.demo.MainActivity;
import com.alizhezi.demo.adapter.DemoListAdapter;

/**
 * Created by gavin
 * Time 2018/1/12  11:06
 * Email:molu_clown@163.com
 */

public abstract class BaseListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mListView;

    private DemoListAdapter.DemoInfo[] DEMOS = null;

    private String TAG=this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"onCreate");

        mListView = new ListView(this);

        setContentView(mListView,new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

        DEMOS = bindData();

        mListView.setOnItemClickListener(this);

        mListView.setAdapter(new DemoListAdapter(this,DEMOS));


    }


    protected ListView getListView() {

        return mListView;
    }


    protected abstract DemoListAdapter.DemoInfo[] bindData();

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent;
        Class<? extends Activity> demoClass = DEMOS[position].demoClass;

        if (demoClass!=null){

            intent = new Intent(BaseListActivity.this, demoClass);

            intent.putExtra("viewType",DEMOS[position].viewType);
        }else {

            intent=new Intent(BaseListActivity.this, MainActivity.class);
        }



        this.startActivity(intent);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }

    /***
     * Activity从后台重新回到前台时被调用
     */
    @Override
    protected void onRestart() {
        super.onRestart();

        Log.e(TAG,"onRestart");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Log.e(TAG,"onNewIntent");
    }

    private int param = 1;

    /** 例如:屏幕方向改变时,Activity被销毁再重建;
     * 当前Activity处于后台,系统资源紧张将其杀死.
     * 另外,当跳转到其他Activity或者按Home键回到主屏时该方法也会被调用,系统是为了保存当前View组件的状态.
     * 在onPause之后被调用.
     * http://blog.csdn.net/liuhe688/article/details/6733407
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("param", param);
        Log.e(TAG, "onSaveInstanceState called. put param: " + param);
        super.onSaveInstanceState(outState);
    }

    /**
     * Activity被系统杀死后再重建时被调用.
     * 例如:屏幕方向改变时,Activity被销毁再重建;
     * 当前Activity处于后台,系统资源紧张将其杀死,用户又启动该Activity.
     * 这两种情况下onRestoreInstanceState都会被调用,在onStart之后.
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        param = savedInstanceState.getInt("param");
        Log.e(TAG, "onRestoreInstanceState called. get param: " + param);
        super.onRestoreInstanceState(savedInstanceState);
    }


}
