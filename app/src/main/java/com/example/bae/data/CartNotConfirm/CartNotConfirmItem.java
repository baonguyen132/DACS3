package com.example.bae.data.CartNotConfirm;

import com.example.bae.data.Battery.BatteryData;

public class CartNotConfirmItem {

    private BatteryData batteryData ;
    private int quantity ;

    public CartNotConfirmItem(BatteryData batteryData, int quantity) {
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
