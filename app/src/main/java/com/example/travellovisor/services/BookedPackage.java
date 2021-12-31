package com.example.travellovisor.services;

public class BookedPackage {
    String userId;
    String pkgId;
    String paymentStatus;

    public BookedPackage() {
    }

    public BookedPackage(String userId, String pkgId, String paymentStatus) {
        this.userId = userId;
        this.pkgId = pkgId;
        this.paymentStatus = paymentStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPkgId() {
        return pkgId;
    }

    public void setPkgId(String pkgId) {
        this.pkgId = pkgId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
