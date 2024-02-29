package com.example.bae.data.CartOfUser;

import android.util.Log;
import android.widget.Toast;

import com.example.bae.data.RequestCustome;
import com.example.bae.data.SharedPreferences.DataLocalManager;
import com.example.bae.data.User.UserData;

import org.json.JSONException;

import java.util.HashMap;

public class CartOfUser {


    private static CartOfUser instance ;
    private HashMap<String, CartOfUserItem> cart ;
    public static void init(){
        instance = DataLocalManager.getCart() ;
        if(instance == null){
            instance = new CartOfUser() ;
            instance.cart = new HashMap<String , CartOfUserItem>() ;
        }

    }

    public static CartOfUser getInstance() {
        if(instance == null){
            instance = new CartOfUser() ;
        }
        return instance ;
    }




    public static void putCart(String idKey , CartOfUserItem cartOfUserItem){
        if (cartOfUserItem.getQuantity() <= 0 ){
            return;
        }


        if(CartOfUser.getInstance().cart == null){
            CartOfUser.getInstance().cart.put(idKey , cartOfUserItem) ;
        }
        else {
            CartOfUserItem item = CartOfUser.getInstance().cart.get(idKey) ;
            if(item == null){
                CartOfUser.getInstance().cart.put(idKey , cartOfUserItem) ;
            }
            else {
                item.setQuantity(item.getQuantity() + cartOfUserItem.getQuantity());
                CartOfUser.getInstance().cart.put(idKey , item) ;
            }
        }
        DataLocalManager.setCart(CartOfUser.getInstance());
    }

    public static void changeQuantityOfItem(CartOfUserItem cartOfUserItem, int changeQuantity){

        if(changeQuantity > 0){
            cartOfUserItem.setQuantity(changeQuantity);
        }
        else {
            CartOfUser.removeItem(cartOfUserItem.getBatteryData().getId());
        }

        DataLocalManager.setCart(CartOfUser.getInstance());
    }

    public static void removeItem(String idKey){
        CartOfUser.getInstance().cart.remove(idKey) ;
    }
    public static HashMap<String, CartOfUserItem>  getCart() {

        return CartOfUser.getInstance().cart ;
    }

    public static void confirmCart(String address , UserData userData){
        CartOfUserRequest cartOfUserRequest = new CartOfUserRequest() ;
        cartOfUserRequest.ResquestCartData(new RequestCustome.HandleRequest() {
            @Override
            public void hanldeRequest(String respone) throws JSONException {
                DataLocalManager.removeCart();
                CartOfUser.init();
                if(!respone.equals("NotSuccessful")){
                    Toast.makeText(RequestCustome.getContext() , "Thêm giỏ hàng thành công" , Toast.LENGTH_LONG).show();
                }
                Log.d("Result" , respone) ;
            }
        } , address , userData);
    }



}
