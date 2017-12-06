package com.huihui.viewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.myview);


        //Activity
    }

    public void requestLayout(View view) {

        this.view.requestLayout();
    }

    public void invalidate(View view) {

        this.view.invalidate();

        view.postInvalidate();
    }
}
