package com.example.myapplication;

public class ProductList {
    int image;
    String name,price,description;

    public ProductList() {

    }

    public ProductList(String name, String price, String description, int image) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }


}