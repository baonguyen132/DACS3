package com.example.bae.data.CartOfUser;

import com.example.bae.data.RequestCustome;
import com.example.bae.data.User.UserData;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Map;

public class CartOfUserRequest {
    public CartOfUserRequest() {
    }

    public void ResquestCartData(RequestCustome.HandleRequest handleRequest , String address , UserData userData){

        ArrayList<CartOfUserItem> cartOfUserItemData = new ArrayList<CartOfUserItem>(CartOfUser.getCart().values());

        Gson gson = new Gson() ;
        String dataCart = gson.toJson(cartOfUserItemData);
        String dataUser = gson.toJson(userData) ;

        double randomDouble = Math.random();
        randomDouble = randomDouble * 1000000000 + 100000000;
        int randomInt = (int) randomDouble;
        String token = randomInt+userData.getId() ;

        RequestCustome.RequestData("cartapi/confirm" , handleRequest, new RequestCustome.setParams() {
            @Override
            public void setParams(Map<String, String> params) {
                params.put("data" , dataCart) ;
                params.put("address" , address) ;
                params.put("user" , dataUser);
                params.put("token" , token) ;


            }
        });

        RequestCustome.RequestData("cartapi/emailConfirm", new RequestCustome.HandleRequest() {
            @Override
            public void hanldeRequest(String respone) throws JSONException {}
        }, new RequestCustome.setParams() {
            @Override
            public void setParams(Map<String, String> params) {
                params.put("data" , dataCart) ;
                params.put("address" , address) ;
                params.put("user" , dataUser);
                params.put("token" , token) ;
            }
        });



    }

}
