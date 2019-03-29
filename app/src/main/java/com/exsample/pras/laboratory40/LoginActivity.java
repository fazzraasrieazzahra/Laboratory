package com.exsample.pras.laboratory40;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.exsample.pras.laboratory40.Network.LoginService;
import com.exsample.pras.laboratory40.Util.PrefUtil;
import com.exsample.pras.laboratory40.model.BaseResponse;
import com.exsample.pras.laboratory40.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText emailinput;
    private EditText passwordinput;
    private Button btnSignIn;
    private Button btnSignUp;

    private LoginService loginService;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(isSessionLogin()) {
            MainActivity.start(this);
            LoginActivity.this.finish();
        }

        emailinput = (EditText) findViewById(R.id.emailinput);
        passwordinput = (EditText) findViewById(R.id.passwordinput);
        btnSignIn = (Button) findViewById(R.id.btSignIn);
        btnSignUp = (Button) findViewById(R.id.btSignUp);


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginAct();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Up = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(Up);
                finish();
            }
        });

//        String caption = "Dont have an account? <b>Register</b>";
//        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(Html.fromHtml(caption));
//        spannableStringBuilder.setSpan(new ClickableSpan() {
//            @Override
//            public void onClick(View view) {
//                RegisterActivity.start(LoginActivity.this);
//            }
//        }, caption.indexOf("Register") - 3, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.WHITE), caption
//                .indexOf("Register") - 3, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        btnSignUp.setText(spannableStringBuilder);
//        btnSignUp.setMovementMethod(LinkMovementMethod.getInstance());

    }

    void loginAct() {
        String email = emailinput.getText().toString();
        String password = passwordinput.getText().toString();

        if(TextUtils.isEmpty(email)) {
            emailinput.setError("Email cannot be empty!");
            return;
        }

        if(TextUtils.isEmpty(password)) {
            passwordinput.setError("Password cannot be empty");
            return;
        }

        loginService = new LoginService(this);
        loginService.doLogin(email, password, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                User user = (User) response.body();

                if(user != null) {
                    if(!user.isError()) {
                        PrefUtil.putUser(LoginActivity.this, PrefUtil.USER_SESSION, user);
                        MainActivity.start(LoginActivity.this);
                        LoginActivity.this.finish();
                    }

                    Toast.makeText(LoginActivity.this, user.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(LoginActivity.this, "An error occurred!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    boolean isSessionLogin() {
        return PrefUtil.getUser(this, PrefUtil.USER_SESSION) != null;
    }
}
