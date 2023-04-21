package com.example.fooddeliverfortrain.model;

public class RiderFood {
    private String foodImage;
    private String foodName;
    private String foodDesc;
    private String restName;
    private String foodPrice;
    private String id;
    private String userName;
    private String foodId;

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

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
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

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public RiderFood(String foodImage, String foodName, String foodDesc, String foodPrice, String restName, String id, String userName, String foodId) {
        this.foodImage = foodImage;
        this.foodName = foodName;
        this.foodDesc = foodDesc;
        this.foodPrice = foodPrice;
        this.restName = restName;
        this.id = id;
        this.userName = userName;
        this.foodId = foodId;
    }

    public RiderFood() {
    }
}
