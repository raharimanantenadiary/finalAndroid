package com.example.myapplication.Interface;

import com.example.myapplication.model.Categorie;
import com.example.myapplication.model.Commentaire;
import com.example.myapplication.model.Notification;
import com.example.myapplication.model.Sitetouristique;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiSiteTouristique {
    String BASE_URL = "https://androidservice.onrender.com/api/siteTouristique/";

    @GET("siteByCategorie/{idCategorie}")
    Call<List<Sitetouristique>> getSiteByCategorie(@Path("idCategorie") String categoryId);



    @POST("saveComs")
    Call<Commentaire> ajoutCommentaire(@Body Commentaire commentaire);

}
