package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Inscription extends Fragment {

    Button btn_redirection_login,btn_inscrip;
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
        btn_inscrip = rootView.findViewById(R.id.btn_inscription);

        // Ajouter un listener au bouton btnRedirectionInscription
        btn_inscrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Remplacer le fragment par le fragment Inscription
                //replaceFragment(new Login());
                EditText login_utilisateur = rootView.findViewById(R.id.login_ins);
                EditText mail_utilisateur = rootView.findViewById(R.id.mail);
                EditText mdp_utilisateur = rootView.findViewById(R.id.mdp_ins);

                String loginText = login_utilisateur.getText().toString();
                String mailText = mail_utilisateur.getText().toString();
                String mdpText = mdp_utilisateur.getText().toString();

                String message = "Login: " + loginText + "\nMail: " + mailText + "\nMDP: " + mdpText;
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

            }
        });

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