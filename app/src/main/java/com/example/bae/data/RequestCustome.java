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


public class RequestCustome  {
    private Context context ;
    private static RequestCustome requestCustome ;
//    protected String link = "http://10.50.26.254/APIDACS3/public/" ;
    private static String link = "https:///2acc-14-176-232-239.ngrok-free.app/DACS2/public/" ;
    protected  String urlAPI = link+"api/" ;
    protected  String urlStorage = link+"storage/" ;

    public static void init(Context context){
        requestCustome = new RequestCustome() ;
        requestCustome.context = context ;
    }

    public static RequestCustome getInstance() {
        if(requestCustome == null){
            requestCustome = new RequestCustome() ;
        }
        return requestCustome ;
    }
    public static void ResponseData(String urlAdd , HandleResponeJSON handle) {

        RequestQueue requestQueue = Volley.newRequestQueue(RequestCustome.getInstance().context) ;
        JsonObjectRequest requestUserData = new JsonObjectRequest(Request.Method.GET, RequestCustome.getInstance().urlAPI+urlAdd, null, new Response.Listener<JSONObject>() {
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
    public static void RequestData(String urlAdd , HandleRequest handleRequest , setParams userSetParams) {
        RequestQueue requestQueue = Volley.newRequestQueue(RequestCustome.getInstance().context);
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, RequestCustome.getInstance().urlAPI+urlAdd, new Response.Listener<String>() {
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

    public static void setContext(Context context) {
        RequestCustome.getInstance().context = context ;
    }

    public static Context getContext() {
        return RequestCustome.getInstance().context;
    }

    public String getUrlAPI() {
        return RequestCustome.getInstance().urlAPI;
    }

    public void setUrlAPI(String urlAPI) {
        RequestCustome.getInstance().urlAPI = urlAPI;
    }
    public void setUrlStorage(String urlStorage) {
        RequestCustome.getInstance().urlStorage = urlStorage;
    }
    public String getUrlStorage() {
        return RequestCustome.getInstance().urlStorage;
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
