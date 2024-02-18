package com.example.bae.data.User;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bae.data.RequestCustome;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserRequest extends RequestCustome {


    public UserRequest(Context context) {
        super(context);
    }

    public void getDataFromServe(int id , UserRequest.HandleResponeJSON handle){
        ResponseData("auth/id=" +id , handle);
    }

    public void checkAccount(String email, String password , UserRequest.HandleResponeString handle){
        RequestData("auth/checkUser", new HandleRequest() {
            @Override
            public void hanldeRequest(String respone) throws JSONException {
                handle.handleResponeString(respone);
            }
        }, new setParams() {
            @Override
            public void setParams(Map<String, String> params) {
                params.put("email" , email.toString().trim() );
                params.put("password" , password.toString().trim());
            }
        });
    }



}
