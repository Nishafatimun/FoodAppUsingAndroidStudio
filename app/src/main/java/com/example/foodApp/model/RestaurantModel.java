package com.example.fooddeliverfortrain.model;

public class RestaurantModel {
    private String id;
    private String username;
    private String imageUrl;
    private String search;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public RestaurantModel() {
    }

    public RestaurantModel(String id, String username, String imageUrl, String search) {
        this.id = id;
        this.username = username;
        this.imageUrl = imageUrl;
        this.search = search;
    }
}
