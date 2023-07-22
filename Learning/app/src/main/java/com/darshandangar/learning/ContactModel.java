package com.darshandangar.learning;

public class ContactModel {
    String name;
    String mono;
    int image;

    public ContactModel(String name, String mono, int image) {
        this.name = name;
        this.mono = mono;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMono() {
        return mono;
    }

    public void setMono(String mono) {
        this.mono = mono;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
