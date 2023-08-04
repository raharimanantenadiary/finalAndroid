package com.example.myapplication.model;

import java.util.Date;

public class Commentaire {

    private String idCommentaire;
    private String idUser;
    private String contenu;
    private String date;

    public Commentaire() {
    }

    public Commentaire(String idCommentaire, String idUser, String contenu, String date) {
        this.idCommentaire = idCommentaire;
        this.idUser = idUser;
        this.contenu = contenu;
        this.date = date;
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
