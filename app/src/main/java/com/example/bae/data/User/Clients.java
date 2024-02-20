package com.example.bae.data.User;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.bae.ui.Login_SignUp.LoginActivity;

import java.io.Serializable;
import java.util.Map;

public class Clients extends UserData   {

    private String password ;
    public Clients(){} ;
    public Clients(String id , String email, String name , String cccd , String dob , String gender , String pob , String address , int status , int point ){
        super(id, email ,name, cccd, dob, gender, pob, address, status, point );
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void signUp(Activity activity){
        Context context = activity.getApplicationContext() ;

        UserRequest request = new UserRequest(context) ;
        request.RequestData("auth/signup", new UserRequest.HandleRequest() {
            @Override
            public void hanldeRequest(String respone) {

                if(respone.equals("sucessful")){
                    Toast.makeText(context , "Đăng kí thành công" , Toast.LENGTH_LONG).show();
                    activity.finish();
                    activity.startActivity(new Intent(activity , LoginActivity.class));

                }
                else {
                    Toast.makeText(context , "Đăng kí không thành công" , Toast.LENGTH_LONG).show();

                }
            }
        }, new UserRequest.setParams() {
            @Override
            public void setParams(Map<String, String> params) {
                params.put("name" , getName()) ;
                params.put("email" , getEmail()) ;
                params.put("password" , getPassword()) ;
                params.put("cid" , getCccd()) ;
                params.put("date" , getDob()) ;
                params.put("gender" , getGender()) ;
                params.put("address" , getAddress()) ;
            }
        }) ;
    }

}
