package com.example.travellovisor.services;

import java.io.Serializable;

public class PackageTour implements Serializable {
    String pkgid;
    String pkgname;
    String pkgcost;
    String pkgduration;
    String traveldate;
    String hotel;
    String minimumperson;
    String imglink;

    public PackageTour() {
    }

    public PackageTour(String pkgid, String pkgname, String pkgcost, String pkgduration, String traveldate, String hotel, String minimumperson,String imglink) {
        this.pkgid = pkgid;
        this.pkgname = pkgname;
        this.pkgcost = pkgcost;
        this.pkgduration = pkgduration;
        this.traveldate = traveldate;
        this.hotel = hotel;
        this.minimumperson = minimumperson;
        this.imglink=imglink;
    }

    public String getPkgid() {
        return pkgid;
    }

    public void setPkgid(String pkgid) {
        this.pkgid = pkgid;
    }

    public String getPkgname() {
        return pkgname;
    }

    public void setPkgname(String pkgname) {
        this.pkgname = pkgname;
    }

    public String getPkgcost() {
        return pkgcost;
    }

    public void setPkgcost(String pkgcost) {
        this.pkgcost = pkgcost;
    }

    public String getPkgduration() {
        return pkgduration;
    }

    public void setPkgduration(String pkgduration) {
        this.pkgduration = pkgduration;
    }

    public String getTraveldate() {
        return traveldate;
    }

    public void setTraveldate(String traveldate) {
        this.traveldate = traveldate;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getMinimumperson() {
        return minimumperson;
    }

    public void setMinimumperson(String minimumperson) {
        this.minimumperson = minimumperson;
    }

    public String getImglink() {
        return imglink;
    }

    public void setImglink(String imglink) {
        this.imglink = imglink;
    }
}
