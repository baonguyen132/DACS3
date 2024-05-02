package com.example.bae.data.Carts;

import com.example.bae.data.SharedPreferences.DataLocalManager;

import java.util.HashMap;

public class CartOfUser {

    private static CartOfUser instance ;
    private HashMap<String , CartData> stringCartDataHashMap ;

    public static void init() {
        instance = DataLocalManager.getCartOfUser() ;
        if(instance == null){
            instance = new CartOfUser() ;
            instance.stringCartDataHashMap = new HashMap<>() ;
        }
    }

    public static CartOfUser getInstance(){
        if (instance == null){
            instance = new CartOfUser() ;
        }

        return instance;
    }

    public static void putItemCart(CartData cartData) {
        CartOfUser.getInstance().stringCartDataHashMap.put(cartData.getId() , cartData) ;
        DataLocalManager.setCartOfUser(CartOfUser.getInstance());
    }

    public static void removeItem(CartData cartData) {
        CartOfUser.getInstance().stringCartDataHashMap.remove(cartData.getId());
        DataLocalManager.setCartOfUser(CartOfUser.getInstance());
    }

    public static HashMap<String, CartData> getStringCartDataHashMap() {
        return CartOfUser.getInstance().stringCartDataHashMap;
    }

    public static void setStringCartDataHashMap(HashMap<String, CartData> stringCartDataHashMap) {
        CartOfUser.getInstance().stringCartDataHashMap = stringCartDataHashMap;
        DataLocalManager.setCartOfUser(CartOfUser.getInstance());
    }
    public static void removeCartOfUser(){
        DataLocalManager.removeCartOfUser();
        CartOfUser.init();
    }
}
