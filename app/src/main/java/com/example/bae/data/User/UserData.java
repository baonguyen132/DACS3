package com.example.bae.data.User;

import android.content.Context;

public class UserData extends UserRequest{
    String  id , name, cccd , dob, gender, pob , address ;
    int status , point ;

    public UserData(Context context){
        super(context);
    }
    public UserData(String id , String name , String cccd , String dob , String gender , String pob , String address , int status , int point , Context context ) {
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



}
