package com.example.myapplication.model;


public class Notification {
    private String idUser;
    private String idSite;
    private String idNotification;

    public Notification() {
    }

    public Notification(String idUser, String idSite, String idNotification) {
        this.idUser = idUser;
        this.idSite = idSite;
        this.idNotification = idNotification;
    }

    // Getters and Setters for Notification fields

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdSite() {
        return idSite;
    }

    public void setIdSite(String idSite) {
        this.idSite = idSite;
    }

    public String getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(String idNotification) {
        this.idNotification = idNotification;
    }


}
