package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Interface.ApiSiteTouristique;
import com.example.myapplication.model.Sitetouristique;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Parc extends Fragment implements AdapterParc.OnItemClickListener  {

    private String intitule;
    private String icone;
    private String idCategorie;

    View view;
    RecyclerView recyclerView;
    List<Sitetouristique> list_sites;
    AdapterParc myAdapter;


    public Parc() {
        // Required empty public constructor
    }

    // ... Vos membres et méthodes existants

    // Ajoutez une méthode pour récupérer les données de l'objet Categorie
    public void setCategorieData(String idCategorie,String intitule, String icone) {
        this.intitule = intitule;
        this.icone = icone;
        this.idCategorie = idCategorie;
    }

    private void filter(String text) {
        ArrayList<Sitetouristique> filteredList = new ArrayList<>();

        for (Sitetouristique item : list_sites) {
            if (item.getTitre().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        // Assurez-vous que l'adaptateur n'est pas nul avant d'appeler filterList
        if (myAdapter != null) {
            myAdapter.filterList(filteredList);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment3, container, false);
        //System.out.println("**********************************************************************");


        EditText editText = view.findViewById(R.id.edittext);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Vérifiez si la liste list_sites est null, puis initialisez-la si c'est le cas
                if (list_sites == null) {
                    list_sites = new ArrayList<>();
                }
                filter(s.toString());
            }
        });

        recyclerView = view.findViewById(R.id.rcview3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Utilisez les données de l'objet Categorie pour afficher les informations dans la vue
        EditText textView = view.findViewById(R.id.id_categorie);
        textView.setText(idCategorie);
       // System.out.println("ID du categorie: " +idCategorie);
        // Le reste de votre code pour le fragment3

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiSiteTouristique.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiSiteTouristique api = retrofit.create(ApiSiteTouristique.class);

        Call<List<Sitetouristique>> call = api.getSiteByCategorie(idCategorie); // Utilisez List<Sitetouristique> au lieu de Sitetouristique
        final Parc fragment = this; // Utilisez le bon nom de la classe du fragment
        call.enqueue(new Callback<List<Sitetouristique>>() { // Utilisez List<Sitetouristique> au lieu de Sitetouristique
            @Override
            public void onResponse(Call<List<Sitetouristique>> call, Response<List<Sitetouristique>> response) { // Utilisez List<Sitetouristique> au lieu de Sitetouristique
                if (response.isSuccessful()) {
                        list_sites = response.body();
                        myAdapter = new AdapterParc(getContext(), list_sites);
                        myAdapter.setOnItemClickListener(fragment); // Utiliser la référence du fragment pour passer l'instance correcte
                        recyclerView.setAdapter(myAdapter);
                    }else{
                    // Gérer les différentes raisons d'échec de l'appel
                    if (response.code() == 404) {
                        // API non trouvée
                    } else {
                        // Autre erreur (code HTTP différent de 200)
                    }
                }
            }
            @Override
            public void onFailure(Call <List<Sitetouristique>> call, Throwable t) {
                // Gérez les erreurs de l'appel ici
            }
        });
        return view;
    }


    @Override
    public void onItemClick(Sitetouristique sitetouristique) {
        // Gérer le clic sur l'élément ici, par exemple, afficher un toast avec l'intitulé de la catégorie
       // Toast.makeText(getContext(), "Site sélectionnée : " + sitetouristique.getIdSitetouristique(), Toast.LENGTH_SHORT).show();

        // Ouvrir le fragment3 en passant les données de l'objet Categorie
        Detail newFragment = new Detail();
        newFragment.setSiteTouristiqueData(sitetouristique);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, newFragment)
                .addToBackStack(null)
                .commit();
    }
}
