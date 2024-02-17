package com.example.bae.data;

import android.content.Context;



public abstract class RequestCustome  {
    protected Context context ;
    protected String url = "https://e490-2405-4802-69af-b30-9c6c-a228-e176-55b7.ngrok-free.app/APIDACS3/public/api/" ;


    public RequestCustome(Context context){
        this.context = context ;
    }


    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }
}
