package com.alizhezi.demo.base;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.alizhezi.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaseTabActivity extends BaseActivity {

    @BindView(R.id.viewPage)
    ViewPager viewPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_tab);
        ButterKnife.bind(this);
    }

    @OnClick({ R.id.tabLayout, R.id.viewPage })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tabLayout:
                break;
            case R.id.viewPage:
                break;
        }
    }
}
