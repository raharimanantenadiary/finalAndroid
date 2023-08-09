package com.example.myapplication.model;


public class Notification {
    private String username;
    private String titre;
    private String idNotification;

    public Notification() {
    }


    public Notification(String username, String titre, String idNotification) {
        this.username = username;
        this.titre = titre;
        this.idNotification = idNotification;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(String idNotification) {
        this.idNotification = idNotification;
    }
}
