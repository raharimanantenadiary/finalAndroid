package com.example.myapplication.Interface;

import com.example.myapplication.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.*;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
public interface ApiUser {
    String BASE_URL = "https://androidservice.onrender.com/api/";

    @POST("auth/signin")
    Call<User> signin(@Body User user);

    @GET("users/userInfo/{idUser}")
    Call<User> getUserInfo(@Path("idUser") String idUtilisateur);

    @POST("auth/signup")
    Call<User> signup(@Body User user);

    @PUT("auth/activation")
    Call<ResponseBody> validation_compte(@Body User user);


}
