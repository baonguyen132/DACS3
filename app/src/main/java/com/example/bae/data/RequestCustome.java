package com.example.bae.data;

import android.content.Context;

public abstract class RequestCustome {
    protected Context context ;
    protected String url = "https://60df-183-80-65-91.ngrok-free.app/APIDACS3/public/api/" ;


    public RequestCustome(Context context){
        this.context = context ;
    }




}
