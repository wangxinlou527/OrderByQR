package com.jydp.obqr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.jydp.obqr.R;
import com.jydp.obqr.util.ISharedPreference;

import org.jetbrains.annotations.Nullable;


public class WelcomeActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.welcome_page);
//        //两秒延迟进入主页面
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startMainActivity();
//            }
//        }, 2000);

        startMainActivity();
    }

    private void startMainActivity() {
        if(TextUtils.isEmpty(ISharedPreference.getInstance(getApplication()).getToken())){
            Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
