package com.example.bae.data.Cart;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.bae.data.RequestCustome;
import com.example.bae.data.SharedPreferences.DataLocalManager;
import com.example.bae.data.User.UserData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class CartData {


    private static CartData instance ;
    private HashMap<String, CartItemData> cart ;
    public static void init(){
        instance = DataLocalManager.getCart() ;
        if(instance == null){
            instance = new CartData() ;
            instance.cart = new HashMap<String , CartItemData>() ;
        }

    }

    public static CartData getInstance() {
        if(instance == null){
            instance = new CartData() ;
        }
        return instance ;
    }




    public static void putCart(String idKey , CartItemData cartItemData){
        if (cartItemData.getQuantity() <= 0 ){
            return;
        }


        if(CartData.getInstance().cart == null){
            CartData.getInstance().cart.put(idKey , cartItemData) ;
        }
        else {
            CartItemData item = CartData.getInstance().cart.get(idKey) ;
            if(item == null){
                CartData.getInstance().cart.put(idKey , cartItemData) ;
            }
            else {
                item.setQuantity(item.getQuantity() + cartItemData.getQuantity());
                CartData.getInstance().cart.put(idKey , item) ;
            }
        }
        DataLocalManager.setCart(CartData.getInstance());
    }

    public static void changeQuantityOfItem(CartItemData cartItemData , int changeQuantity){

        if(changeQuantity > 0){
            cartItemData.setQuantity(changeQuantity);
        }
        else {
            CartData.removeItem(cartItemData.getBatteryData().getId());
        }

        DataLocalManager.setCart(CartData.getInstance());
    }

    public static void removeItem(String idKey){
        CartData.getInstance().cart.remove(idKey) ;
    }
    public static HashMap<String, CartItemData>  getCart() {

        return CartData.getInstance().cart ;
    }

    public static void confirmCart(String address , UserData userData){
        CartRequest cartRequest = new CartRequest() ;
        cartRequest.ResquestCartData(new RequestCustome.HandleRequest() {
            @Override
            public void hanldeRequest(String respone) throws JSONException {
                DataLocalManager.removeCart();
                CartData.init();
                if(respone.equals("successful")){
                    Toast.makeText(RequestCustome.getContext() , "Thêm giỏ hàng thành công" , Toast.LENGTH_LONG).show();
                }
                Log.d("Result" , respone) ;
            }
        } , address , userData);
    }

}
