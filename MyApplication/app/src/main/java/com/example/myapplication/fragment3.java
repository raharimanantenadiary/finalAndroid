package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class fragment3 extends Fragment implements AdapterEnfantListe.OnItemClickListener {

    View view;
    EnfantModel enfantModel = new EnfantModel();
    int idModel_actif;
    RecyclerView recyclerView;

    public fragment3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment3, container, false);
        // Récupérer les données transmises depuis le fragment2
        Bundle bundle = getArguments();
        if (bundle != null) {
            int selectedId = bundle.getInt("selected_id", 0);
            idModel_actif = selectedId;
            recyclerView = view.findViewById(R.id.rcview3);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new AdapterEnfantListe(enfantModel.recherchelisteEnfantModel(idModel_actif), this)); // Passer "this" en tant que référence de l'interface
        }
        return view;
    }

    @Override
    public void onItemClick(int id_enfant_model) {
        // Créez une instance du nouveau fragment (fragment3)
        fragment4 newFragment = new fragment4();

        // Créez un Bundle pour transmettre les données du clic au nouveau fragment
        Bundle bundle = new Bundle();
        bundle.putInt("enfant_model", id_enfant_model); ;
        newFragment.setArguments(bundle);

        // Remplacez le fragment actuel par le nouveau fragment (fragment3)
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.drawerLayout, newFragment)
                .addToBackStack(null)
                .commit();
    }

    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        return super.getDefaultViewModelCreationExtras();
    }
}
