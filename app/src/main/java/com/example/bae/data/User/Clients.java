package com.example.bae.data.User;

import android.content.Context;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Map;

public class Clients extends UserData   {
    public Clients(){

    } ;
    public Clients(String id , String email, String name , String cccd , String dob , String gender , String pob , String address , int status , int point ){
        super(id, email ,name, cccd, dob, gender, pob, address, status, point );
    }


    public void signUp(Context context){

        UserRequest request = new UserRequest(context) ;
        request.RequestData("auth/signup", new UserRequest.HandleRequest() {
            @Override
            public void hanldeRequest(String respone) {
                Toast.makeText(context , respone , Toast.LENGTH_LONG).show();
            }
        }, new UserRequest.setParams() {
            @Override
            public void setParams(Map<String, String> params) {
                params.put("email" , email) ;
            }
        }) ;
    }

}
