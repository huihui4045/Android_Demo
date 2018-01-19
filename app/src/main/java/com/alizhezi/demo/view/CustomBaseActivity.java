package com.alizhezi.demo.view;

import com.alizhezi.demo.adapter.DemoListAdapter;
import com.alizhezi.demo.base.BaseListActivity;

/****
 * 多种自定义View
 */
public class CustomBaseActivity extends BaseListActivity {


    @Override
    protected DemoListAdapter.DemoInfo[] bindData() {
        return new DemoListAdapter.DemoInfo[]{

                new DemoListAdapter.DemoInfo("简单的贝塞尔曲线", "简单的贝塞尔曲线", CustomViewActivity.class, ViewType.TYPE_BEZIER)
        };
    }


}
