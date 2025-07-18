package com.example.bae.data.CartNotConfirm;

import com.example.bae.data.RequestCustome;
import com.example.bae.data.User.UserData;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Map;

public class CartNotConfirmRequest {
    public CartNotConfirmRequest() {
    }

    public void ResquestCartData(RequestCustome.HandleRequest handleRequest , String address , String namefile ,UserData userData){

        ArrayList<CartNotConfirmItem> cartNotConfirmItemData = new ArrayList<CartNotConfirmItem>(CartNotConfirm.getCart().values());

        Gson gson = new Gson() ;
        String dataCart = gson.toJson(cartNotConfirmItemData);
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
                params.put("namefile" , namefile);


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
