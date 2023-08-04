package com.example.myapplication.Service;

import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Interface.CategoryAPI;
import com.example.myapplication.MyAdapter;
import com.example.myapplication.Retrofit.RetrofitClient;
import com.example.myapplication.model.Categorie;

import java.io.IOException;
import java.util.List;

import retrofit2.*;


public class ServiceCategorie {

   /** private void getAllcategories() {
        Call<List<Categorie>> call = RetrofitClient.getInstance().getMyApi().getAllcategorie();
        call.enqueue(new Callback<List<Categorie>>() {
            @Override
            public void onResponse(Call<List<Categorie>> call, Response<List<Categorie>> response) {
                List<Categorie> myheroList = response.body();
                String[] oneHeroes = new String[myheroList.size()];

                for (int i = 0; i < myheroList.size(); i++) {
                    oneHeroes[i] = myheroList.get(i).getIntitule();
                }

                //superListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, oneHeroes));
            }

            @Override
            public void onFailure(Call<List<Categorie>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

            private Context getApplicationContext() {
                return null;
            }

        });
    }**/

}
