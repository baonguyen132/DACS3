package com.example.bae.data;

import android.content.Context;

public abstract class RequestCustome {
    protected Context context ;
    protected String url = "https://9803-14-233-118-150.ngrok-free.app/APIDACS3/public/api/" ;


    public RequestCustome(Context context){
        this.context = context ;
    }




}
