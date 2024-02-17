package com.example.bae.data.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {
    private static final String MY_SHARED_FREFERENCES = "MY_SHARED_FREFERENCES" ;
    private Context mContext ;

    public MySharedPreferences(Context context){
        this.mContext = context ;
    }
    public void putBooleanValue(String key,boolean value){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_SHARED_FREFERENCES , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key , value);
        editor.apply();

    }
    public boolean getBooleanValue(String key){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_SHARED_FREFERENCES , Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key , false) ;
    }
}
