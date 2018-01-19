package com.alizhezi.demo.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.alizhezi.demo.adapter.DemoListAdapter;

/**
 * Created by gavin
 * Time 2018/1/12  11:06
 * Email:molu_clown@163.com
 */

public abstract class BaseListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mListView;

    private DemoListAdapter.DemoInfo[] DEMOS = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        intent = new Intent(BaseListActivity.this, demoClass);

        intent.putExtra("viewType",DEMOS[position].viewType);


        this.startActivity(intent);
    }



}
