package com.example.bae.data.Battery;

import android.content.Context;

import com.example.bae.data.RequestCustome;
import com.example.bae.data.User.UserRequest;

public class BatteryRequest  {
    public BatteryRequest() {
    }

    public void getDataFromServe(RequestCustome.HandleResponeJSON handle){
        RequestCustome.ResponseData("batteryapi", handle);
    }
}
