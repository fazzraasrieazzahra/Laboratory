package com.exsample.pras.laboratory40.Network;

import android.content.Context;

import com.exsample.pras.laboratory40.Network.config.RetrofitBuilder;
import com.exsample.pras.laboratory40.Network.interfaces.LoginInterfece;

import retrofit2.Callback;

public class LoginService {

    private LoginInterfece loginInterface;

    public LoginService(Context context) {
        loginInterface = RetrofitBuilder.builder(context)
                .create(LoginInterfece.class);
    }

    public void doLogin(String email, String password, Callback callback) {
        loginInterface.login(email, password).enqueue(callback);
    }

}
