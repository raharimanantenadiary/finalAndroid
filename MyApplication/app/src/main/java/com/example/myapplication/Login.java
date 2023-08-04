package com.example.myapplication;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Login extends Fragment {

    Button btn_redirection_visiter, btn_redirection_connecter, btn_redirection_inscription;

    public Login() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        // Récupérer les boutons depuis le layout fragment_login.xml
        btn_redirection_visiter = rootView.findViewById(R.id.btnRedirectionVisiter);
        btn_redirection_connecter = rootView.findViewById(R.id.btnConnecter);
        btn_redirection_inscription = rootView.findViewById(R.id.btnRedirectionInscription);

        // Ajouter un listener au bouton btnRedirectionInscription
        btn_redirection_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Remplacer le fragment par le fragment Inscription
                replaceFragment(new Inscription());
            }
        });

        btn_redirection_visiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Remplacer le fragment par le fragment Inscription
                replaceFragment(new fragment2());
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
