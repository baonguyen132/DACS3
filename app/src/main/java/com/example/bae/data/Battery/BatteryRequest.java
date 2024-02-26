package com.example.bae.data.Battery;

import android.content.Context;

import com.example.bae.data.RequestCustome;
import com.example.bae.data.User.UserRequest;

public class BatteryRequest extends RequestCustome {
    public BatteryRequest(Context context) {
        super(context);
    }

    public void getDataFromServe(UserRequest.HandleResponeJSON handle){
        ResponseData("batteryapi", handle);
    }
}
