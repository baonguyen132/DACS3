package com.example.bae.data.Carts;

import java.io.Serializable;

public class CartData implements Serializable {
    private String id , created_at  , token , address;


    private int total  ;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public CartData(String id, String created_at, String token, String address, int total) {
        this.id = id;
        this.created_at = created_at;
        this.token = token;
        this.address = address;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
