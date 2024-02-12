package com.example.bae.data;

import android.content.Context;

public abstract class RequestCustome {
    protected Context context ;
    protected String url = "https://2b6d-2405-4802-7074-e680-2c9e-94fb-dda9-9e81.ngrok-free.app/APIDACS3/public/api/" ;

    public RequestCustome(Context context){
        this.context = context ;
    }

    public void ResponseData() {}
    public abstract void RequestData();


}
