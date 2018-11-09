package com.alizhezi.demo.retrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.alizhezi.demo.R;
import com.alizhezi.demo.base.BaseActivity;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpActivity extends BaseActivity {

    private OkHttpClient mOkHttpClient = null;

    private final static String TAG="OkhttpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mOkHttpClient = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).
            cache(new Cache(new File(""), 24 * 1024 * 1024)).build();


        Request.Builder builder=new Request.Builder();

        builder.url("https://www.toutiao.com/i6498671685300912654/");

        Request request = builder.build();


        Call call = mOkHttpClient.newCall(request);

        //call.execute(); 同步方法

    }

    public void getExecuteHttp(View view) {

        Request.Builder builder = new Request.Builder();

        builder.url("https://www.toutiao.com/i6498671685300912654/");

        Request request = builder.build();

        Call call = mOkHttpClient.newCall(request);

        try {
            Response response = call.execute();

            Log.e(TAG, "getExecuteHttp:" + response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /****
     * 异步get请求
     */
    public void getAsynHttp(View view){

        Request.Builder builder=new Request.Builder();

        builder.url("https://www.toutiao.com/i6498671685300912654/");

        Request request = builder.build();


        Call call = mOkHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.e(TAG,"onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                Log.e(TAG,"返回的线程："+Thread.currentThread().getName());

                if (null!=response){

                    String s = response.body().string();

                    Log.e(TAG,"请求的结果："+s);
                }
            }
        });



    }
}
