package com.example.bae.data.Details;

import com.example.bae.data.RequestCustome;

public class DetailRequest {
    public static void getDetailCart(String id , RequestCustome.HandleResponeJSON handleResponeJSON){
        RequestCustome.ResponseData("cartapi/detail/idcart="+id , handleResponeJSON);
    }
}
