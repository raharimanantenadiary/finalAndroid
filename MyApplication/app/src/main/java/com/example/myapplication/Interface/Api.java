package com.example.myapplication.Interface;

import com.example.myapplication.model.Categorie;

import java.util.List;

import retrofit2.*;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    String BASE_URL = "https://androidservice.onrender.com/api/siteTouristique/";
    @GET("categorie")
    Call<List<Categorie>> getAllcategorie();

    @GET("categorie/{idCategorie}")
    Call<Categorie> getCategorieById(@Path("idCategorie") String categoryId);


}
