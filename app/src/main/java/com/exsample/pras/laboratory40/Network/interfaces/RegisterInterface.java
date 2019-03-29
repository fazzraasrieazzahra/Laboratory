package com.exsample.pras.laboratory40.Network.interfaces;

import com.exsample.pras.laboratory40.Network.config.Config;
import com.exsample.pras.laboratory40.model.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterInterface {

    @FormUrlEncoded
    @POST(Config.API_REGISTER)
    Call<BaseResponse> register(
            @Field("firstname") String firstname,
            @Field("lastname") String lastname,
            @Field("email") String email,
            @Field("password") String password);
}
