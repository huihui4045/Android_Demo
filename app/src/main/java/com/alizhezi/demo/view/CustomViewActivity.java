package com.alizhezi.demo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.alizhezi.demo.view.widget.BezierWaveView;

public class CustomViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getViewFromType(getIntent().getIntExtra("viewType", ViewType.TYPE_BEZIER)),
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

    }


    private View getViewFromType(int viewType) {

        switch (viewType) {

            case ViewType.TYPE_BEZIER:

                return new BezierWaveView(this);
        }


        return null;
    }
}
