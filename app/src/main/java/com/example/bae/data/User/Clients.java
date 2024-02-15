package com.example.bae.data.User;

import android.content.Context;
import android.widget.Toast;

import java.util.Map;

public class Clients extends UserData{
    public Clients(Context context){
        super(context);
    } ;
    public Clients(String id , String name , String cccd , String dob , String gender , String pob , String address , int status , int point , Context context){
        super(id, name, cccd, dob, gender, pob, address, status, point , context);
    }

    public void signUp(String email){
        RequestData("auth/signup", new HandleRequest() {
            @Override
            public void hanldeRequest(String respone) {
                Toast.makeText(context , respone , Toast.LENGTH_LONG).show();
            }
        }, new setParams() {
            @Override
            public void setParams(Map<String, String> params) {
                params.put("email" , email) ;
            }
        }) ;
    }

}
