package com.example.bae.data;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

public class UserRequest extends RequestCustome {

    public UserRequest(Context context) {
        super(context);

    }

    public void ResponseData(String email , UserRequest.HandleRespone handle) {

        RequestQueue requestQueue = Volley.newRequestQueue(context) ;
        JsonObjectRequest requestUserData = new JsonObjectRequest(Request.Method.GET, url + "auth/email=" + email, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    handle.handleRespone(response);
                } catch (JSONException e) {

                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error" , error.toString());
            }
        });

        requestQueue.add(requestUserData);

    }

    @Override
    public void RequestData() {

    }

     public interface HandleRespone{
        void handleRespone(JSONObject response) throws JSONException;
    }

}
