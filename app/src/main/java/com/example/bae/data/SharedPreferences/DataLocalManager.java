package com.example.bae.data.SharedPreferences;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.bae.data.Cart.CartData;
import com.example.bae.data.Cart.CartItemData;
import com.example.bae.data.User.UserData;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DataLocalManager {
    private static final String PREF_FIRST_INSTALL = "PREF_FIRST_INSTALL";
    private static final String PREF_NAME_USER = "PREF_NAME_USER";
    private static final String PREF_LOGIN_USERNAME = "PREF_LOGIN_USERNAME";
    private static final String PREF_LOGIN_PASSWORD = "PREF_LOGIN_PASSWORD";
    private static final String PREF_OBJECT_USER = "PREF_OBJECT_USER";
    private static final String PREF_HASHMAP_CART = "PREF_HASHMAP_CART";
    private static  DataLocalManager instance ;
    private  MySharedPreferences sharedPreferences ;
    public static void init(Context context){
        instance = new DataLocalManager() ;
        instance.sharedPreferences = new MySharedPreferences(context);
    }

    public static DataLocalManager getInstance() {
        if(instance == null){
            instance = new DataLocalManager() ;
        }
        return instance ;
    }

    public static void setFirstInstalled(boolean isFirst){
        DataLocalManager.getInstance().sharedPreferences.putBooleanValue(PREF_FIRST_INSTALL , isFirst); ;
    }
    public static boolean getFirstInstalled(){
        return DataLocalManager.getInstance().sharedPreferences.getBooleanValue(PREF_FIRST_INSTALL);
    }

    public static void setUsename(String value){
        DataLocalManager.getInstance().sharedPreferences.putStringValue(PREF_LOGIN_USERNAME, value);
    }
    public static String getUsename(){
        return DataLocalManager.getInstance().sharedPreferences.getStringValue(PREF_LOGIN_USERNAME);
    }
    public static void setPassword(String value){
        DataLocalManager.getInstance().sharedPreferences.putStringValue(PREF_LOGIN_PASSWORD, value);
    }
    public static String getPassword(){
        return DataLocalManager.getInstance().sharedPreferences.getStringValue(PREF_LOGIN_PASSWORD) ;
    }

    public static void setUser(UserData user){
        Gson gson = new Gson() ;
        String strJsonUser = gson.toJson(user);
        DataLocalManager.getInstance().sharedPreferences.putStringValue(PREF_OBJECT_USER , strJsonUser);
    }

    public static UserData getUser(){
        String strJsonUser = DataLocalManager.getInstance().sharedPreferences.getStringValue(PREF_OBJECT_USER) ;
        Gson gson = new Gson() ;
        return gson.fromJson(strJsonUser , UserData.class);
    }
    public static void setCart(CartData carts){
        Gson gson = new Gson() ;
        String strJsonUser = gson.toJson(carts);
        DataLocalManager.getInstance().sharedPreferences.putStringValue(PREF_HASHMAP_CART , strJsonUser);
    }

    public static CartData getCart(){
        String strJsonUser = DataLocalManager.getInstance().sharedPreferences.getStringValue(PREF_HASHMAP_CART) ;
        Gson gson = new Gson() ;

        return gson.fromJson(strJsonUser , CartData.class);
    }

    public static void removeCart(){
        DataLocalManager.getInstance().sharedPreferences.removeDataValue(PREF_HASHMAP_CART);
    }




    public static void removeUser(){
        DataLocalManager.getInstance().sharedPreferences.removeDataValue(PREF_OBJECT_USER);
    }

}
