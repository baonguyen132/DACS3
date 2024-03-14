package com.example.bae.data.Details;

import com.example.bae.data.Battery.BatteryData;

public class DetailData {
    String id , count;
    BatteryData batteryData ;

    public DetailData(String id, String count, BatteryData batteryData) {
        this.id = id;
        this.count = count;
        this.batteryData = batteryData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public BatteryData getBatteryData() {
        return batteryData;
    }

    public void setBatteryData(BatteryData batteryData) {
        this.batteryData = batteryData;
    }
}
