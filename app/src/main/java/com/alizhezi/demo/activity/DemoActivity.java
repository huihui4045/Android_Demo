package com.alizhezi.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alizhezi.demo.R;
import com.alizhezi.demo.base.BaseActivity;

public class DemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
    }

    public void start(View view){

        startActivity(new Intent(this,BActivity.class));
    }
}
