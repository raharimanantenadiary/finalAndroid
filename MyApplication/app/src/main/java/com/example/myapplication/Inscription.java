package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Interface.ApiUser;
import com.example.myapplication.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

                if (loginText.isEmpty() || mdpText.isEmpty() || mailText.isEmpty() ) {
                    // Si le login ou le mot de passe est vide, afficher un toast
                    Toast.makeText(getContext(), "Veuillez remplir le formulaire", Toast.LENGTH_SHORT).show();
                    return; // Quitter la méthode onClick sans exécuter le reste du code
                }

                String message = "Login: " + loginText + "\nMail: " + mailText + "\nMDP: " + mdpText;
                //Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

                Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiUser.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();



                User user = new User(mailText, mdpText,loginText);
                ApiUser apiService = retrofit.create(ApiUser.class);
                Call<User> call = apiService.signup(user);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            // Traitement de la réponse de l'API en cas de succès
                            Toast.makeText(getContext(), "SUCESS, vérifier votre email", Toast.LENGTH_SHORT).show();
                            replaceFragment(new Validation());
                        } else {
                            // Gérer les différentes raisons d'échec de l'appel
                            if (response.code() == 404) {
                                // API non trouvé
                                Toast.makeText(getContext(), "API non trouvée", Toast.LENGTH_SHORT).show();
                            } else {
                                // Autre erreur (code HTTP différent de 200)
                                Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    //crée une session de paire clé valeur (clé: id_utilisateur et valeur= actif.getIdUtilisateur())
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        // Traitement de l'erreur en cas d'échec de l'appel à l'API
                        Toast.makeText(getContext(), "Erreur de l'api", Toast.LENGTH_SHORT).show();
                    }
                });

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
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}