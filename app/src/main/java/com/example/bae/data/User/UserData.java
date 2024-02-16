package com.example.bae.data.User;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.Map;

public class UserData extends UserRequest{
    String  id , name, cccd , dob, gender, pob , address , email ;
    int status , point ;

    public UserData(Context context){
        super(context);
    }
    public UserData(String id , String email , String name , String cccd , String dob , String gender , String pob , String address , int status , int point , Context context ) {
        super(context);
        this.id = id ;
        this.name = name ;
        this.cccd = cccd ;
        this.dob = dob ;
        this.gender = gender ;
        this.pob = pob ;
        this.address = address ;
        this.status = status ;
        this.point = point ;
        this.email = email ;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPob(String pob) {
        this.pob = pob;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public int getPoint() {
        return point;
    }

    public int getStatus() {
        return status;
    }

    public String getAddress() {
        return address;
    }

    public String getCccd() {
        return cccd;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getId() {
        return id;
    }

    public String getPob() {
        return pob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void checkAccount(String email, String password , UserRequest.HandleResponeString handle){
        RequestQueue requestQueue = Volley.newRequestQueue(context) ;

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
