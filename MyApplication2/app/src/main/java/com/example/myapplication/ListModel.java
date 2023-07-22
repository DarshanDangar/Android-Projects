package com.example.myapplication;

public class ListModel {

    int mimage;
    String mname,mprice,mdescription;

    public ListModel() {

    }

    public ListModel(String name, String price, String description, int image) {
        this.mname = name;
        this.mprice = price;
        this.mdescription = description;
        this.mimage = image;
    }


    public String getName() {
        return mname;
    }

    public void setName(String name) {
        this.mname = name;
    }

    public String getPrice() {
        return mprice;
    }

    public void setPrice(String price) {
        this.mprice = price;
    }





    public String getDescription() {
        return mdescription;
    }

    public void setDescription(String description) {
        this.mdescription = description;
    }

    public int getImage() {
        return mimage;
    }

    public void setImage(int image) {
        this.mimage = image;
    }
}
