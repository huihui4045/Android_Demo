package com.alizhezi.demo.leak;

import com.alizhezi.demo.retrofit.ResultBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gavin
 * Time 2017/12/18  16:30
 * Email:molu_clown@163.com
 */

public interface IpService {


    @GET("cc/json/mobile_tel_segment.htm")
    Call<ResultBean> getIpInfo(@Query("tel")String ip);

  /*  @GET("cc/json/mobile_tel_segment.htm")
    Call<String> getIpInfo(@Body IP ip);*/
}
