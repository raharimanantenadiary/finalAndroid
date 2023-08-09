package com.example.myapplication.model;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class Sitetouristique {

    @SerializedName("_id")
    private String idSitetouristique;
    private String idCategorie;
    @SerializedName("titre")
    private String titre;
    @SerializedName("localisation")
    private String localisation;
    private String historique;
    private String contact;
    private String email;
    private String video;
    private List<Galerie> galerie;
    private List<Commentaire> commentaire;

    public Sitetouristique() {
    }

    public Sitetouristique(String idSitetouristique, String idCategorie, String titre, String localisation,
                           String historique, String contact, String email, String video,
                           List<Galerie> galerie, List<Commentaire> commentaire) {
        this.idSitetouristique = idSitetouristique;
        this.idCategorie = idCategorie;
        this.titre = titre;
        this.localisation = localisation;
        this.historique = historique;
        this.contact = contact;
        this.email = email;
        this.video = video;
        this.galerie = galerie;
        this.commentaire = commentaire;
    }

    public String getIdSitetouristique() {
        return idSitetouristique;
    }

    public void setIdSitetouristique(String idSitetouristique) {
        this.idSitetouristique = idSitetouristique;
    }

    public String getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(String idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getHistorique() {
        return historique;
    }

    public void setHistorique(String historique) {
        this.historique = historique;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public List<Galerie> getGalerie() {
        return galerie;
    }

    public void setGalerie(List<Galerie> galerie) {
        this.galerie = galerie;
    }

    public List<Commentaire> getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(List<Commentaire> commentaire) {
        this.commentaire = commentaire;
    }
}
