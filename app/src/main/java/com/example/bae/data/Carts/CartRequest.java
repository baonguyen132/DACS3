package com.example.bae.data.Carts;

import com.example.bae.data.RequestCustome;

public class CartRequest {

    public void getDataCart(String id , RequestCustome.HandleResponeJSON handleResponeJSON){
        RequestCustome.ResponseData("cartapi/iduser="+id , handleResponeJSON);
    }
    public void getDataCartConfirmed(String id , RequestCustome.HandleResponeJSON handleResponeJSON){
        RequestCustome.ResponseData("cartapi/confirmed/iduser="+id , handleResponeJSON);
    }
    public void getDataCartNotConfirm(String id , RequestCustome.HandleResponeJSON handleResponeJSON){
        RequestCustome.ResponseData("cartapi/notconfirm/iduser="+id , handleResponeJSON);
    }
    public void getCount(String id , RequestCustome.HandleResponeJSON handleResponeJSON){
        RequestCustome.ResponseData("cartapi/getnumber/iduser="+id , handleResponeJSON);
    }
    public void getAllNotConfirm(RequestCustome.HandleResponeJSON handleResponeJSON){
        RequestCustome.ResponseData("cartapi/getAll" , handleResponeJSON);
    }
    public void getInformation(String id , RequestCustome.HandleResponeJSON handleResponeJSON){
        RequestCustome.ResponseData("cartapi/getItem/idcart="+id , handleResponeJSON);
    }

    public void confirmCartAddPoint(RequestCustome.HandleRequest handle , RequestCustome.setParams setParams){
        RequestCustome.RequestData("cartapi/confirmbyadmin" , handle , setParams);
    }

}
