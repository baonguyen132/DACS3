package com.example.bae.data.Cart;

import com.example.bae.data.Battery.BatteryData;

public class CartItemData {

    private BatteryData batteryData ;
    private int quantity ;

    public CartItemData(BatteryData batteryData, int quantity) {
        this.batteryData = batteryData;
        this.quantity = quantity;
    }

    public BatteryData getBatteryData() {
        return batteryData;
    }

    public void setBatteryData(BatteryData batteryData) {
        this.batteryData = batteryData;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
