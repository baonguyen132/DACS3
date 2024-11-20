package com.example.bae.data.CartNotConfirm;

import android.util.Log;
import android.widget.Toast;

import com.example.bae.data.Carts.CartData;
import com.example.bae.data.Carts.CartOfUser;
import com.example.bae.data.RequestCustome;
import com.example.bae.data.SharedPreferences.DataLocalManager;
import com.example.bae.data.User.UserData;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class CartNotConfirm {


    private static CartNotConfirm instance ;
    private HashMap<String, CartNotConfirmItem> cart ;
    private String namefile  ;
    private JsonObject objectCart ;
    public static void init(){
        instance = DataLocalManager.getCart() ;
        if(instance == null){
            instance = new CartNotConfirm() ;
            instance.cart = new HashMap<String , CartNotConfirmItem>() ;
            instance.namefile = "" ;
            instance.objectCart = null ;
        }

    }

    public static CartNotConfirm getInstance() {
        if(instance == null){
            instance = new CartNotConfirm() ;
        }
        return instance ;
    }

    public static String getNamefile() {
        return CartNotConfirm.getInstance().namefile;
    }

    public static void setNamefile(String namefile) {
        CartNotConfirm.getInstance().namefile = namefile;
    }

    public static JsonObject getObjectCart() {
        return CartNotConfirm.getInstance().objectCart;
    }

    public static void setObjectCart(JsonObject objectCart) {
        CartNotConfirm.getInstance().objectCart = objectCart;
    }

    public static void putCart(String idKey , CartNotConfirmItem cartNotConfirmItem){
        if (cartNotConfirmItem.getQuantity() <= 0 ){
            return;
        }


        if(CartNotConfirm.getInstance().cart == null){
            CartNotConfirm.getInstance().cart.put(idKey , cartNotConfirmItem) ;
        }
        else {
            CartNotConfirmItem item = CartNotConfirm.getInstance().cart.get(idKey) ;
            if(item == null){
                CartNotConfirm.getInstance().cart.put(idKey , cartNotConfirmItem) ;
            }
            else {
                item.setQuantity(item.getQuantity() + cartNotConfirmItem.getQuantity());
                CartNotConfirm.getInstance().cart.put(idKey , item) ;
            }
        }
        DataLocalManager.setCart(CartNotConfirm.getInstance());
    }

    public static void changeQuantityOfItem(CartNotConfirmItem cartNotConfirmItem, int changeQuantity){

        if(changeQuantity > 0){
            cartNotConfirmItem.setQuantity(changeQuantity);
        }
        else {
            CartNotConfirm.removeItem(cartNotConfirmItem.getBatteryData().getId());
        }

        DataLocalManager.setCart(CartNotConfirm.getInstance());
    }

    public static void removeItem(String idKey){
        CartNotConfirm.getInstance().cart.remove(idKey) ;
    }
    public static HashMap<String, CartNotConfirmItem>  getCart() {

        return CartNotConfirm.getInstance().cart ;
    }

    public static void removeCart(){
        DataLocalManager.removeCartNotConfirm();
        CartNotConfirm.init();
    }

    public static void confirmCart(String address , UserData userData , String namefile){
        CartNotConfirmRequest cartNotConfirmRequest = new CartNotConfirmRequest() ;
        cartNotConfirmRequest.ResquestCartData(new RequestCustome.HandleRequest() {
            @Override
            public void hanldeRequest(String respone) throws JSONException {
                CartNotConfirm.removeCart();
                if(!respone.equals("NotSuccessful")){
                    Toast.makeText(RequestCustome.getContext() , "Thêm giỏ hàng thành công" , Toast.LENGTH_LONG).show();

                    String[] result = respone.split("@");
                    CartOfUser.putItemCart(new CartData(result[0] , result[3] , result[1] , address ,Integer.parseInt(result[2]) , result[4] ));

                }
                Log.d("Result" , respone) ;
            }
        } , address , namefile , userData);
    }



}
