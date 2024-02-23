package com.example.bae.data;

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
import com.example.bae.data.User.UserRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public abstract class RequestCustome  {
    protected Context context ;
    protected String url = "https:///bd35-113-160-225-45.ngrok-free.app/DACS2/public/api/" ;
//        protected String url = "http://192.168.1.101/APIDACS3/public/api/" ;

    public RequestCustome(Context context){
        this.context = context ;
    }


    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }


    public void ResponseData(String urlAdd , UserRequest.HandleResponeJSON handle) {

        RequestQueue requestQueue = Volley.newRequestQueue(context) ;
        JsonObjectRequest requestUserData = new JsonObjectRequest(Request.Method.GET, url+urlAdd, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    handle.handleResponeJSON(response);
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

    public void RequestData(String urlAdd , UserRequest.HandleRequest handleRequest , UserRequest.setParams userSetParams) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, url+urlAdd, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    handleRequest.hanldeRequest(response);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error" , error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> params = new HashMap<>() ;
                userSetParams.setParams(params);
                return params;
            }
        };

        requestQueue.add(stringRequest);

    }


    public interface HandleResponeJSON{
        void handleResponeJSON(JSONObject response) throws JSONException;
    }
    public interface HandleResponeString{
        void handleResponeString(String response) throws JSONException;
    }
    public interface HandleRequest{
        void hanldeRequest(String respone) throws JSONException;
    }

    public interface setParams {
        void setParams(Map<String , String> params) ;
    }
}
