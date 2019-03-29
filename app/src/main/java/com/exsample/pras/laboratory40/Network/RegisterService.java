package com.exsample.pras.laboratory40.Network;

import android.content.Context;

import com.exsample.pras.laboratory40.Network.config.RetrofitBuilder;
import com.exsample.pras.laboratory40.Network.interfaces.RegisterInterface;

import retrofit2.Callback;

public class RegisterService {

    private RegisterInterface registerInterface;

    public RegisterService(Context context) {
        registerInterface = RetrofitBuilder.builder(context)
                .create(RegisterInterface.class);
    }

    public void doRegister(String firstname, String lastname, String email, String password, Callback callback) {
        registerInterface.register(firstname, lastname, email, password).enqueue(callback);
    }
}
