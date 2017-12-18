package com.alizhezi.demo;

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
    Call<UserBean> getIpInfo(@Query("tel")String ip);
}
