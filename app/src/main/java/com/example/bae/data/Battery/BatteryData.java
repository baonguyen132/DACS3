package com.example.bae.data.Battery;

public class BatteryData {
    private String id ;
    private String name_battery ;
    private String size ;
    private String shape ;
    private String point ;
    private String image ;

    public BatteryData(String id, String name_battery, String size, String shape, String point, String image) {
        this.id = id;
        this.name_battery = name_battery;
        this.size = size;
        this.shape = shape;
        this.point = point;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_battery() {
        return name_battery;
    }

    public void setName_battery(String name_battery) {
        this.name_battery = name_battery;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public int getPoint() {
        return Integer.parseInt(point);
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
