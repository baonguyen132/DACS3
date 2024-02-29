package com.example.bae.data.Carts;

public class CartData {
    private String id , address , token ;
    private int total  ;

    public CartData(String id, String address, String token, int total) {
        this.id = id;
        this.address = address;
        this.token = token;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
