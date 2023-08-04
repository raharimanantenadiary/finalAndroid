package com.example.myapplication.model;

public class Galerie {

    private String idGalerie;
    private String titre;
    private String details;
    private String image;

    public Galerie() {
    }

    public Galerie(String idGalerie, String titre, String details, String image) {
        this.idGalerie = idGalerie;
        this.titre = titre;
        this.details = details;
        this.image = image;
    }

    public String getId() {
        return idGalerie;
    }

    public void setId(String id) {
        this.idGalerie = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
