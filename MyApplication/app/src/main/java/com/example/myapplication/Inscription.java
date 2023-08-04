package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Inscription extends Fragment {

    Button btn_redirection_login;
    public Inscription() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_inscription, container, false);

        // Récupérer les boutons depuis le layout fragment_login.xml
        btn_redirection_login = rootView.findViewById(R.id.btn_redirection_connexion);

        // Ajouter un listener au bouton btnRedirectionInscription
        btn_redirection_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Remplacer le fragment par le fragment Inscription
                replaceFragment(new Login());
            }
        });




        return rootView;
    }

    // Méthode pour remplacer le fragment actuel par un autre fragment
    private void replaceFragment(Fragment fragment) {
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.drawerLayout, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}