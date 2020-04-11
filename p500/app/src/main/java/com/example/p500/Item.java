package com.example.p500;

public class Item {
    int img;
    String name;
    String phone;

    public Item() {
    }

    public Item(int img, String name, String phone) {
        this.img = img;
        this.name = name;
        this.phone = phone;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
