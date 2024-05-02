package com.example.bae.data.SharedPreferences;

import android.content.Context;


import com.example.bae.data.CartNotConfirm.CartNotConfirm;

import com.example.bae.data.Carts.CartOfUser;
import com.example.bae.data.User.UserData;

import com.example.bae.data.Voucher.VoucherOfUser;
import com.google.gson.Gson;



public class DataLocalManager {
    private static final String PREF_FIRST_INSTALL = "PREF_FIRST_INSTALL";
    private static final String PREF_NAME_USER = "PREF_NAME_USER";
    private static final String PREF_LOGIN_USERNAME = "PREF_LOGIN_USERNAME";
    private static final String PREF_LOGIN_PASSWORD = "PREF_LOGIN_PASSWORD";
    private static final String PREF_OBJECT_USER = "PREF_OBJECT_USER";
    private static final String PREF_HASHMAP_CART = "PREF_HASHMAP_CART";
    private static final String PREF_HASHMAP_VOUCHEROFUSER = "PREF_HASHMAP_VOUCHEROFUSER";
    private static final String PREF_HASHMAP_CARTOFUSER = "PREF_HASHMAP_CARTOFUSER";
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
    public static void setCart(CartNotConfirm carts){
        Gson gson = new Gson() ;
        String strJsonUser = gson.toJson(carts);
        DataLocalManager.getInstance().sharedPreferences.putStringValue(PREF_HASHMAP_CART , strJsonUser);
    }

    public static CartNotConfirm getCart(){
        String strJsonUser = DataLocalManager.getInstance().sharedPreferences.getStringValue(PREF_HASHMAP_CART) ;
        Gson gson = new Gson() ;

        return gson.fromJson(strJsonUser , CartNotConfirm.class);
    }

    public static void removeCartNotConfirm(){
        DataLocalManager.getInstance().sharedPreferences.removeDataValue(PREF_HASHMAP_CART);
    }

    public static void removeUser(){
        DataLocalManager.getInstance().sharedPreferences.removeDataValue(PREF_OBJECT_USER);
    }


    public static void setVoucherOfUser(VoucherOfUser voucherOfUser){
        Gson gson = new Gson() ;
        String strJsonUser = gson.toJson(voucherOfUser);
        DataLocalManager.getInstance().sharedPreferences.putStringValue(PREF_HASHMAP_VOUCHEROFUSER , strJsonUser);
    }
    public static VoucherOfUser getVoucherOfUser(){
        String strJsonUser = DataLocalManager.getInstance().sharedPreferences.getStringValue(PREF_HASHMAP_VOUCHEROFUSER) ;
        Gson gson = new Gson() ;

        return gson.fromJson(strJsonUser , VoucherOfUser.class);
    }
    public static void removeVoucherOfUser(){
        DataLocalManager.getInstance().sharedPreferences.removeDataValue(PREF_HASHMAP_VOUCHEROFUSER);
    }

    public static void setCartOfUser(CartOfUser cartOfUser){
        Gson gson = new Gson() ;
        String stringJSON = gson.toJson(cartOfUser);
        DataLocalManager.getInstance().sharedPreferences.putStringValue(PREF_HASHMAP_CARTOFUSER , stringJSON);
    }
    public static CartOfUser getCartOfUser() {
        Gson gson = new Gson() ;
        String stringJson = DataLocalManager.getInstance().sharedPreferences.getStringValue(PREF_HASHMAP_CARTOFUSER);

        return gson.fromJson(stringJson , CartOfUser.class);
    }
    public static void removeCartOfUser() {
        DataLocalManager.getInstance().sharedPreferences.removeDataValue(PREF_HASHMAP_CARTOFUSER);
    }



}
