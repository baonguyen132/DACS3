package com.example.bae.data;

import android.app.Application;

import com.example.bae.data.CartNotConfirm.CartNotConfirm;
import com.example.bae.data.Carts.CartOfUser;
import com.example.bae.data.SharedPreferences.DataLocalManager;
import com.example.bae.data.Voucher.VoucherOfUser;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataLocalManager.init(getApplicationContext());
        CartNotConfirm.init();
        VoucherOfUser.init();
        CartOfUser.init();
        RequestCustome.init(getApplicationContext());

    }
}
