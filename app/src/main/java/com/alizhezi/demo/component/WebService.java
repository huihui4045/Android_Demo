package com.alizhezi.demo.component;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WebService {

    @GET("/users/{user}")
    Call<User> getUser(@Path("user") String userId);
}
