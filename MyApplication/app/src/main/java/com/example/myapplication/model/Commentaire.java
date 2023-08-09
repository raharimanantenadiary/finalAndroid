package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Commentaire {

    @SerializedName("_id")
    private String idCommentaire;
    @SerializedName("idUser")
    private String idUser;
    @SerializedName("contenu")
    private String contenu;
    @SerializedName("date")
    private String date;

    @SerializedName("idSite")
    private String idSite_touristique;

    public Commentaire() {
    }

    public Commentaire(String idCommentaire, String idUser, String contenu, String date) {
        this.idCommentaire = idCommentaire;
        this.idUser = idUser;
        this.contenu = contenu;
        this.date = date;

    }

    public Commentaire(String idSite_touristique,String idUser, String contenu, String date,String a) {
        this.idUser = idUser;
        this.contenu = contenu;
        this.date = date;
        this.idSite_touristique = idSite_touristique;
    }

    public String getId() {
        return idCommentaire;
    }

    public void setId(String id) {
        this.idCommentaire = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
