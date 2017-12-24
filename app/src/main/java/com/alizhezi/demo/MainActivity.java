package com.alizhezi.demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alizhezi.demo.butterknife.ButterKnifeActivity;
import com.alizhezi.demo.imageload.ImageLoadActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    private String TAG="MainActivity";
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo);

       /* Uri uri = getIntent().getData();
        if (uri != null) {
            // 完整的url信息
            String url = uri.toString();
            Log.e(TAG, "url: " + uri);
            // scheme部分
            String scheme = uri.getScheme();
            Log.e(TAG, "scheme: " + scheme);
            // host部分
            String host = uri.getHost();
            Log.e(TAG, "host: " + host);
            //port部分
            int port = uri.getPort();
            Log.e(TAG, "host: " + port);
            // 访问路劲
            String path = uri.getPath();
            Log.e(TAG, "path: " + path);
            List<String> pathSegments = uri.getPathSegments();
            // Query部分
            String query = uri.getQuery();
            Log.e(TAG, "query: " + query);
            //获取指定参数值
            String goodsId = uri.getQueryParameter("goodsId");
            Log.e(TAG, "goodsId: " + goodsId);
        }
*/

        mListView = ((ListView) findViewById(R.id.listView));

        mListView.setOnItemClickListener(this);

        mListView.setAdapter(new DemoListAdapter());

        mListView.setOnScrollListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mListView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {


                }
            });
        }
    }

    private   DemoInfo[] DEMOS = {
            new DemoInfo("网络请求OKhttp使用与源码分析", "网络请求OKhttp使用与源码分析", OkhttpActivity.class),
            new DemoInfo("网络请求Retrofit使用与源码分析", "网络请求Retrofit使用与源码分析", RetrofitActivity.class),
            new DemoInfo("ButterKnifeActivity使用与源码分析", "ButterKnifeActivity使用与源码分析", ButterKnifeActivity.class),
            new DemoInfo("图片加载框架", "图片加载框架", ImageLoadActivity.class),
            new DemoInfo("测试", "测试", OkhttpActivity.class),
            new DemoInfo("测试", "测试", OkhttpActivity.class),
            new DemoInfo("测试", "测试", OkhttpActivity.class),
            new DemoInfo("测试", "测试", OkhttpActivity.class),
            new DemoInfo("测试", "测试", OkhttpActivity.class),
            new DemoInfo("测试", "测试", OkhttpActivity.class),
            new DemoInfo("测试", "测试", OkhttpActivity.class),
            new DemoInfo("测试", "测试", OkhttpActivity.class),
            new DemoInfo("测试", "测试", OkhttpActivity.class),
            new DemoInfo("测试", "测试", OkhttpActivity.class),
            new DemoInfo("测试", "测试", OkhttpActivity.class),
            new DemoInfo("测试", "测试", OkhttpActivity.class),
            new DemoInfo("测试", "测试", OkhttpActivity.class),
            new DemoInfo("测试", "测试", OkhttpActivity.class),
            new DemoInfo("测试", "测试", OkhttpActivity.class),
            new DemoInfo("测试", "测试", OkhttpActivity.class),
            new DemoInfo("测试", "测试", OkhttpActivity.class),
            new DemoInfo("测试", "测试", OkhttpActivity.class),
            new DemoInfo("测试", "测试", OkhttpActivity.class),

            //new DemoInfo("自定义View", "自定义View 使用demo", CustomActivity.class),
    };


    private DemoInfo [] getData(int size){
        DemoInfo [] data=new DemoInfo[size];




        return  data;
    }




    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        Class<? extends Activity> demoClass = DEMOS[position].demoClass;

        intent = new Intent(MainActivity.this, demoClass);


        this.startActivity(intent);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        switch (scrollState){

            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:

                Log.e(TAG,"滚动停止时的状态");

                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                Log.e(TAG,"触摸正在滚动，手指还没离开界面时的状态");

                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                Log.e(TAG,"用户在用力滑动后，ListView由于惯性将继续滑动时的状态");

                break;

        }
    }


    private int oldVisibleItem=0;

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem+visibleItemCount==totalItemCount&&totalItemCount>0){
            Log.e(TAG,"滑动到底部");
        }
        if (firstVisibleItem>oldVisibleItem){
            Log.e(TAG,"向上滑动");
        }
        if (firstVisibleItem<oldVisibleItem){
            Log.e(TAG,"向下滑动");
        }
        oldVisibleItem=firstVisibleItem;
    }



    private class DemoListAdapter extends BaseAdapter {
        public DemoListAdapter() {
            super();
        }

        @Override
        public View getView(int index, View convertView, ViewGroup parent) {
            convertView = View.inflate(MainActivity.this, R.layout.demo_info_item, null);
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
    }

    private static class DemoInfo {
        private final String title;
        private final String desc;
        private final Class<? extends Activity> demoClass;

        public DemoInfo(String title, String desc, Class<? extends Activity> demoClass) {
            this.title = title;
            this.desc = desc;
            this.demoClass = demoClass;
        }
    }
}
