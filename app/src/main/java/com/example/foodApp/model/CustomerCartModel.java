package com.example.fooddeliverfortrain.model;

public class CustomerCartModel {
    private String foodImage;
    private String foodName;
    private String foodDesc;
    private String restName;
    private String foodPrice;
    private String id;
    private String userName;

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public void setFoodDesc(String foodDesc) {
        this.foodDesc = foodDesc;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public CustomerCartModel() {
    }

    public CustomerCartModel(String foodImage, String foodName, String foodDesc, String restName, String foodPrice, String id, String userName) {
        this.foodImage = foodImage;
        this.foodName = foodName;
        this.foodDesc = foodDesc;
        this.restName = restName;
        this.foodPrice = foodPrice;
        this.id = id;
        this.userName = userName;
    }
}
