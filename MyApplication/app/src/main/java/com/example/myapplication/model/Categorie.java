package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class Categorie {
    @SerializedName("_id")
    private String idCategorie;
    @SerializedName("intitule")
    private String intitule;

    @SerializedName("icone")
    private String icone;

    public Categorie(String idCategorie, String intitule, String icone) {
        this.idCategorie = idCategorie;
        this.intitule = intitule;
        this.icone = icone;
    }

    public String getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(String idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }
}
