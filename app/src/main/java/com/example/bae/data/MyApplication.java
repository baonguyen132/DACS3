package com.example.bae.data;

import android.app.Application;

import com.example.bae.data.CartOfUser.CartOfUser;
import com.example.bae.data.SharedPreferences.DataLocalManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataLocalManager.init(getApplicationContext());
        CartOfUser.init();
        RequestCustome.init(getApplicationContext());
    }
}
