package com.alizhezi.demo.retrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.alizhezi.demo.R;
import com.alizhezi.demo.base.BaseActivity;
import com.alizhezi.demo.covert.FastJsonConverterFactory;
import com.alizhezi.demo.leak.IpService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitActivity extends BaseActivity {

    private static final String url="http://ip.taobao.com/service/getIpInfo.php?ip=63.223.108.42";

    //https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=18514476718

    private static final String BASE_URL="https://tcc.taobao.com/";
    private static final String TAG="RetrofitActivity";

    Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl(BASE_URL)

                .addConverterFactory(FastJsonConverterFactory.create());
                //;.addCallAdapterFactory()



         retrofit = builder.build();

    }

    public void getAsynRetrofit(View view){


        //Log.e(TAG,"RetrofitActivity:");

        IpService ipService = retrofit.create(IpService.class);

        Call<ResultBean> call = ipService.getIpInfo("18514476718");

        //call.execute();

        call.enqueue(new Callback<ResultBean>() {
            @Override
            public void onResponse(Call<ResultBean> call, Response<ResultBean> response) {

                Log.e(TAG,"thrad"+Thread.currentThread().getName());

                if (response!=null &&response.isSuccessful()){



                    if (response.body()!=null){

                        Log.e(TAG, response.body().toString());

                    }


                }
            }

            @Override
            public void onFailure(Call<ResultBean> call, Throwable t) {

            }
        });
    }
}
