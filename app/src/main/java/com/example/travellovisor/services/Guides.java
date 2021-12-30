package com.example.travellovisor.services;

public class Guides {
    String title;
    String description;
    String imglink="";

    //no value found
    public Guides() {
    }

    public Guides(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Guides(String title, String description, String imglink) {
        this.title = title;
        this.description = description;
        this.imglink = imglink;
    }

    public String getImglink() {
        return imglink;
    }

    public void setImglink(String imglink) {
        this.imglink = imglink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
