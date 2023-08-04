package com.example.myapplication.Interface;

import java.util.List;

import com.example.myapplication.model.Categorie;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryAPI {
    @GET("/categorie")
    Call<List<Categorie>> getAll();

}
