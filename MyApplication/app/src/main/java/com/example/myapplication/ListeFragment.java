package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class ListeFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    List<EnfantModel> itemList;

    public ListeFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_liste, container, false);

        recyclerView = view.findViewById(R.id.rcviewliste);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(new AdapterEnfantListe(null, (AdapterEnfantListe.OnItemClickListener) this)); // Passer "this" en tant que référence de l'interface

        return view;
    }


}