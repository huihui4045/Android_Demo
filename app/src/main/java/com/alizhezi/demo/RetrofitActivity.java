package com.alizhezi.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

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

                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());



         retrofit = builder.build();

    }

    public void getAsynRetrofit(View view){


        //Log.e(TAG,"RetrofitActivity:");

        IpService ipService = retrofit.create(IpService.class);

        Call<String> call = ipService.getIpInfo("18514476718");

        //call.execute();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {


                if (response!=null &&response.isSuccessful()){



                    if (response.body()!=null){

                        Log.e(TAG, response.body().toString());

                        Log.e(TAG,"thrad"+Thread.currentThread().getName());
                    }


                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
