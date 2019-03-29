package com.exsample.pras.laboratory40.Network.interfaces;

import com.exsample.pras.laboratory40.Network.config.Config;
import com.exsample.pras.laboratory40.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginInterfece {

    @FormUrlEncoded
    @POST(Config.API_LOGIN)
    Call<User> login(
            @Field("email") String email,
            @Field("password") String password);
}
