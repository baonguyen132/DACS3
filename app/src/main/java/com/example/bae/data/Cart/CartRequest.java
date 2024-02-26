package com.example.bae.data.Cart;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.bae.data.RequestCustome;
import com.example.bae.data.SharedPreferences.DataLocalManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;

public class CartRequest extends RequestCustome {
    public CartRequest(Context context) {
        super(context);
    }

    public void ResquestCartData(RequestCustome.HandleRequest handleRequest){

        ArrayList<CartItemData> cartItemData = new ArrayList<CartItemData>(CartData.getCart().values());
        ArrayList<CartItemIdAndQuanity> quantity = new ArrayList<CartItemIdAndQuanity>() ;
        for (int i = 0; i < cartItemData.size() ; i++) {
            quantity.add(new CartItemIdAndQuanity(cartItemData.get(i).getBatteryData().getId() ,cartItemData.get(i).getQuantity() , cartItemData.get(i).getQuantity() * cartItemData.get(i).getBatteryData().getPoint() ));
        }
        Gson gson = new Gson() ;
        String data = gson.toJson(quantity);
        RequestData("cartapi/confirm" , handleRequest, new setParams() {
            @Override
            public void setParams(Map<String, String> params) {
                params.put("data" , data) ;
            }
        }); ;
    }
    private class CartItemIdAndQuanity {
        private String id ;
        private int quantity ;
        private int total ;

        public CartItemIdAndQuanity(String id, int quantity , int total) {
            this.id = id;
            this.quantity = quantity;
            this.total = total ;
        }
    }
}
