package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class User {
    private String username;
    private String mail;
    @SerializedName("code")
    private String validation;
    private String mdp;
    private String profile;
    @SerializedName("id")
    private String idUtilisateur;

    int activation_compte;

    public int getActivation_compte() {
        return activation_compte;
    }

    public void setActivation_compte(int activation_compte) {
        this.activation_compte = activation_compte;
    }

    public User() {
    }

    public User(String mail, String mdp) {
        this.mail = mail;
        this.mdp = mdp;
    }

    public User(String validation) {
        this.validation = validation;
    }

    public User(String mail, String motdepasse, String username) {
        this.mail = mail;
        this.mdp = motdepasse;
        this.username = username;
    }


    public User(String username, String mail, String idUtilisateur, String profile) {
        this.username = username;
        this.mail = mail;
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



