package com.example.fooddeliverfortrain.model;

public class CustomerLocationModel {
    private String longitude;
    private String latitude;
    private String id;
    private String username;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CustomerLocationModel() {
    }

    public CustomerLocationModel(String longitude, String latitude, String id, String username) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.id = id;
        this.username = username;
    }
}
