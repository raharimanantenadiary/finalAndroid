package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class Galerie {

    @SerializedName("_id")
    private String idGalerie;
    @SerializedName("media")
    private String media;

    public Galerie() {

    }

    public Galerie(String idGalerie, String media) {
        this.idGalerie = idGalerie;
        this.media = media;
    }

    public String getIdGalerie() {
        return idGalerie;
    }

    public void setIdGalerie(String idGalerie) {
        this.idGalerie = idGalerie;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }
}
