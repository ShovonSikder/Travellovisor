package com.example.travellovisor;

public class User {
    String name;
    String imglink;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, String imglink) {
        this.name = name;
        this.imglink = imglink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImglink() {
        return imglink;
    }

    public void setImglink(String imglink) {
        this.imglink = imglink;
    }


}
