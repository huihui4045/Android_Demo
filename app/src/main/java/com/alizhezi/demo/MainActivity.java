package com.alizhezi.demo;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alizhezi.demo.activity.DemoActivity;
import com.alizhezi.demo.adapter.DemoListAdapter;
import com.alizhezi.demo.adapter.DemoListAdapter.DemoInfo;
import com.alizhezi.demo.base.BaseListActivity;
import com.alizhezi.demo.butterknife.ButterKnifeActivity;
import com.alizhezi.demo.fragment.FragmentDemoActivity;
import com.alizhezi.demo.imageload.ImageLoadActivity;
import com.alizhezi.demo.leak.LeakCanaryActivity;
import com.alizhezi.demo.retrofit.OkhttpActivity;
import com.alizhezi.demo.retrofit.RetrofitActivity;
import com.alizhezi.demo.rxjava.RxJavaActivity;
import com.alizhezi.demo.view.CustomBaseActivity;


public class MainActivity extends BaseListActivity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    private String TAG="MainActivity";
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityManager manager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        int heapSize = manager.getMemoryClass();

        Log.e(TAG,"最大内存："+heapSize);


        //LoginManager.getInstance(this).dealData();

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

       mListView=getListView();
        mListView.setOnScrollListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mListView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {


                }
            });
        }
    }

    @Override
    protected DemoInfo[] bindData() {
        return DEMOS;
    }

    private   DemoListAdapter.DemoInfo[] DEMOS = {
            new DemoInfo("网络请求OKhttp使用与源码分析", "网络请求OKhttp使用与源码分析", OkhttpActivity.class),
            new DemoInfo("网络请求Retrofit使用与源码分析", "网络请求Retrofit使用与源码分析", RetrofitActivity.class),
            new DemoInfo("ButterKnifeActivity使用与源码分析", "ButterKnifeActivity使用与源码分析", ButterKnifeActivity.class),
            new DemoInfo("图片加载框架", "图片加载框架", ImageLoadActivity.class),
            new DemoInfo("LeakCanary检测内存泄漏及解决办法", "LeakCanary检测内存泄漏及解决办法", LeakCanaryActivity.class),
            new DemoInfo("测试DemoActivity", "测试DemoActivity", DemoActivity.class),
            new DemoInfo("自定义View Demo", "自定义View多种例子", CustomBaseActivity.class),
            new DemoInfo("FragmentActivity测试", "FragmentActivity测试", FragmentDemoActivity.class),
            new DemoInfo("RxJavaActivity测试", "RxJavaActivity测试", RxJavaActivity.class),
            new DemoInfo("测试", "测试", MainActivity.class),
            new DemoInfo("测试", "测试", MainActivity.class),
            new DemoInfo("测试", "测试", MainActivity.class),
            new DemoInfo("测试", "测试", MainActivity.class),
            new DemoInfo("测试", "测试", MainActivity.class),
            new DemoInfo("测试", "测试", MainActivity.class),
            new DemoInfo("测试", "测试", MainActivity.class),
            new DemoInfo("测试", "测试", MainActivity.class),
            new DemoInfo("测试", "测试", MainActivity.class),
            new DemoInfo("测试", "测试", MainActivity.class),
            new DemoInfo("测试", "测试", MainActivity.class),
            new DemoInfo("测试", "测试", MainActivity.class),
            new DemoInfo("测试", "测试", MainActivity.class),
            new DemoInfo("测试", "测试", MainActivity.class),
            new DemoInfo("测试", "测试", MainActivity.class),

            //new DemoInfo("自定义View", "自定义View 使用demo", CustomActivity.class),
    };





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


}
