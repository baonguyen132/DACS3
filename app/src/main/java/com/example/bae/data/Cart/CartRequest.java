package com.example.bae.data.Cart;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.bae.data.RequestCustome;
import com.example.bae.data.SharedPreferences.DataLocalManager;
import com.example.bae.data.User.UserData;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;

public class CartRequest {
    public CartRequest() {
    }

    public void ResquestCartData(RequestCustome.HandleRequest handleRequest , String address , UserData userData){

        ArrayList<CartItemData> cartItemData = new ArrayList<CartItemData>(CartData.getCart().values());

        Gson gson = new Gson() ;
        String dataCart = gson.toJson(cartItemData);
        String dataUser = gson.toJson(userData) ;
        RequestCustome.RequestData("cartapi/confirm" , handleRequest, new RequestCustome.setParams() {
            @Override
            public void setParams(Map<String, String> params) {
                params.put("data" , dataCart) ;
                params.put("address" , address) ;
                params.put("user" , dataUser);


            }
        }); ;
    }

}
