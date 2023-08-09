package com.example.myapplication;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.Interface.Api;
import com.example.myapplication.model.Categorie;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Acceuil extends Fragment  implements AdapteurAcceuil.OnItemClickListener {

    View view;
    RecyclerView recyclerView;
    public Acceuil() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        recyclerView = view.findViewById(R.id.rcview2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ProgressBar loadingProgressBar = view.findViewById(R.id.loadingProgressBar);
        loadingProgressBar.setVisibility(View.VISIBLE); // Show the ProgressBar before making the API call

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(30, TimeUnit.SECONDS); // 30 secondes de timeout pour la connexion
        httpClientBuilder.readTimeout(30, TimeUnit.SECONDS); // 30 secondes de timeout pour la lecture des données
        httpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Categorie>> call = api.getAllcategorie();
        // Créer une référence à l'instance du fragment pour utiliser dans la méthode onResponse
        final Acceuil fragment = this;
        call.enqueue(new Callback<List<Categorie>>() {
            @Override
            public void onResponse(Call<List<Categorie>> call, Response<List<Categorie>> response) {
                loadingProgressBar.setVisibility(View.GONE); // Hide the ProgressBar on successful response
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Categorie> cat_list = response.body();
                AdapteurAcceuil myAdapter = new AdapteurAcceuil(getContext(), cat_list);
                myAdapter.setOnItemClickListener(fragment); // Utiliser la référence du fragment pour passer l'instance correcte
                recyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onFailure(Call<List<Categorie>> call, Throwable t) {
                loadingProgressBar.setVisibility(View.GONE); // Hide the ProgressBar on failure
                Log.e("API Call Error", t.getMessage()); // Afficher l'erreur dans les logs pour le débogage
                // Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onItemClick(Categorie categorie) {
        // Gérer le clic sur l'élément ici, par exemple, afficher un toast avec l'intitulé de la catégorie
        Toast.makeText(getContext(), "Catégorie sélectionnée : " + categorie.getIntitule(), Toast.LENGTH_SHORT).show();

        // Ouvrir le fragment3 en passant les données de l'objet Categorie
        Parc newFragment = new Parc();
        newFragment.setCategorieData(categorie.getIdCategorie(),categorie.getIntitule(), categorie.getIcone());
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, newFragment)
                .addToBackStack(null)
                .commit();
    }


}