package com.example.bae.data.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

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

    public void putStringValue(String key , String value){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_SHARED_FREFERENCES , Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor = sharedPreferences.edit() ;
        editor.putString(key , value) ;
        editor.apply();
    }

    public String  getStringValue(String key){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_SHARED_FREFERENCES , Context.MODE_PRIVATE);
        return sharedPreferences.getString(key , "") ;
    }

    public void putStringSetValue(String key , Set<String> values){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_SHARED_FREFERENCES , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(key ,values) ;
        editor.apply();
    }
    public Set<String> getStringSetValue(String  key){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_SHARED_FREFERENCES , Context.MODE_PRIVATE);
        Set<String> valueDefault = new HashSet<>();
        return sharedPreferences.getStringSet(key , valueDefault);
    }


    public void removeDataValue(String key){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_SHARED_FREFERENCES , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit() ;
        editor.remove(key);
        editor.apply();
    }
}
