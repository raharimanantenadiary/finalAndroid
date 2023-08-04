package com.example.myapplication.model;

public class User {
    private String username;
    private String mail;
    private String validation;
    private String mdp;
    private String profile;
    private String idUtilisateur;

    public User() {
    }

    public User(String username, String mail, String validation, String mdp, String profile, String idUtilisateur) {
        this.username = username;
        this.mail = mail;
        this.validation = validation;
        this.mdp = mdp;
        this.profile = profile;
        this.idUtilisateur = idUtilisateur;
    }

    // Getters and Setters for User fields

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getIdUtilisateur() {
        return idUtilisateur;
    }
}



