package com.example.bae.data.User;

import android.content.Context;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Map;

public class Clients extends UserData implements Serializable  {
    public Clients(Context context){
        super(context);
    } ;
    public Clients(String id , String email, String name , String cccd , String dob , String gender , String pob , String address , int status , int point , Context context){
        super(id, email ,name, cccd, dob, gender, pob, address, status, point , context);
    }

    public void sendOtp(String codeOtp){
        RequestData("auth/sendOtp", new HandleRequest() {
            @Override
            public void hanldeRequest(String respone) {
                Toast.makeText(context , respone , Toast.LENGTH_LONG).show();
            }
        }, new setParams() {
            @Override
            public void setParams(Map<String, String> params) {
                params.put("email" , getEmail());
                params.put("codeOtp" , codeOtp) ;
            }
        }) ;
    }
    public void signUp(){
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
