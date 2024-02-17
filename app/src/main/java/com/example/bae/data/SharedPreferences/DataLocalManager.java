package com.example.bae.data.SharedPreferences;

import android.content.Context;

public class DataLocalManager {
    private static final String PREF_FIRST_INSTALL = "PREF_FIRST_INSTALL";
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

}
