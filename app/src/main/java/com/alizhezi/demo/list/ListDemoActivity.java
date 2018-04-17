package com.alizhezi.demo.list;

import android.os.Bundle;
import android.widget.ListView;

import com.alizhezi.demo.R;
import com.alizhezi.demo.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ListDemoActivity extends BaseActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_demo);

        mListView = ((ListView) findViewById(R.id.listView));

        List<String> data = new ArrayList<>();

        data.add("http://mmp.mmxyz.net/images/2018/04/498192135.jpg");
        data.add("http://mmp.mmxyz.net/images/2018/04/4030134971.jpg");
        data.add("http://mmp.mmxyz.net/images/2018/04/1580263130.jpg");
        data.add("http://mmp.mmxyz.net/images/2018/04/1602162931.jpg");
        data.add("http://mmp.mmxyz.net/images/2018/04/43067385.jpg");
        data.add("http://mmp.mmxyz.net/images/2018/04/2672826490.jpg");
        data.add("http://mmp.mmxyz.net/images/2018/04/4066750517.jpg");
        data.add("http://mmp.mmxyz.net/images/2018/04/723118034.jpg");

        data.add("http://mmp.mmxyz.net/images/2018/04/498192135.jpg");
        data.add("http://mmp.mmxyz.net/images/2018/04/4030134971.jpg");
        data.add("http://mmp.mmxyz.net/images/2018/04/1580263130.jpg");
        data.add("http://mmp.mmxyz.net/images/2018/04/1602162931.jpg");
        data.add("http://mmp.mmxyz.net/images/2018/04/43067385.jpg");
        data.add("http://mmp.mmxyz.net/images/2018/04/2672826490.jpg");
        data.add("http://mmp.mmxyz.net/images/2018/04/4066750517.jpg");
        data.add("http://mmp.mmxyz.net/images/2018/04/723118034.jpg");


        DemoAdapter demoAdapter = new DemoAdapter(data, this);

        mListView.setAdapter(demoAdapter);


    }
}
