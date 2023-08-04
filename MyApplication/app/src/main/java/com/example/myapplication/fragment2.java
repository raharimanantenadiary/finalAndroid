package com.example.myapplication;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.Interface.Api;
import com.example.myapplication.model.Categorie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.widget.Toast;

public class fragment2 extends Fragment implements MyAdapter.OnItemClickListener {

    View view;
    RecyclerView recyclerView;
    List<Model> itemList;


    public fragment2() {
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

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Categorie>> call = api.getAllcategorie();
        call.enqueue(new Callback<List<Categorie>>() {
            @Override
            public void onResponse(Call<List<Categorie>> call, Response<List<Categorie>> response) {
                loadingProgressBar.setVisibility(View.GONE); // Hide the ProgressBar on successful response
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Categorie> cat_list = response.body();
                MyAdapter myAdapter = new MyAdapter(getContext(), cat_list);
                recyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onFailure(Call<List<Categorie>> call, Throwable t) {
                loadingProgressBar.setVisibility(View.GONE); // Hide the ProgressBar on failure
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


    @Override
    public void onItemClick(int idModel) {
        // Créez une instance du nouveau fragment (fragment3)
        fragment3 newFragment = new fragment3();

        // Créez un Bundle pour transmettre les données du clic au nouveau fragment
        Bundle bundle = new Bundle();
        bundle.putInt("selected_id", idModel) ;
        newFragment.setArguments(bundle);

        // Remplacez le fragment actuel par le nouveau fragment (fragment3)
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.drawerLayout, newFragment)
                .addToBackStack(null)
                .commit();
    }


}
