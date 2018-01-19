package com.alizhezi.demo.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alizhezi.demo.R;

/**
 * Created by gavin
 * Time 2018/1/12  11:26
 * Email:molu_clown@163.com
 */

public class DemoListAdapter extends BaseAdapter {

    private Context mContext;

    private DemoInfo [] DEMOS;

    public DemoListAdapter(Context mContext, DemoInfo[] DEMOS) {
        this.mContext = mContext;
        this.DEMOS = DEMOS;
    }


    @Override
    public View getView(int index, View convertView, ViewGroup parent) {
        convertView = View.inflate(mContext, R.layout.demo_info_item, null);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        //TextView desc = (TextView) convertView.findViewById(R.id.desc);
        title.setText(DEMOS[index].title);
        //desc.setText(DEMOS[index].desc);
        if (index >= 25) {
            title.setTextColor(Color.YELLOW);
        }
        return convertView;
    }

    @Override
    public int getCount() {
        return DEMOS.length;
    }

    @Override
    public Object getItem(int index) {
        return DEMOS[index];
    }

    @Override
    public long getItemId(int id) {
        return id;
    }


    public static class DemoInfo {
        private final String title;
        private final String desc;
        public final Class<? extends Activity> demoClass;

        public int viewType;

        public DemoInfo(String title, String desc, Class<? extends Activity> demoClass) {
            this.title = title;
            this.desc = desc;
            this.demoClass = demoClass;
        }

        public DemoInfo(String title, String desc, Class<? extends Activity> demoClass, int viewType) {
            this.title = title;
            this.desc = desc;
            this.demoClass = demoClass;
            this.viewType = viewType;
        }

        public void setViewType(int viewType) {
            this.viewType = viewType;
        }
    }
}
