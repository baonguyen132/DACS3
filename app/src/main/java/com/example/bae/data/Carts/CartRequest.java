package com.example.bae.data.Carts;

import com.example.bae.data.RequestCustome;

public class CartRequest {

    public void getDataCart(String id , RequestCustome.HandleResponeJSON handleResponeJSON){
        RequestCustome.ResponseData("cartapi/iduser="+id , handleResponeJSON);
    }

}
