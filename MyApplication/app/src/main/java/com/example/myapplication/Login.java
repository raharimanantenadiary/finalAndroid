package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.Interface.ApiUser;
import com.example.myapplication.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends Fragment {

    Button btn_redirection_visiter, btn_login, btn_redirection_inscription;

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
        btn_login = rootView.findViewById(R.id.btnConnecter);
        btn_redirection_inscription = rootView.findViewById(R.id.btnRedirectionInscription);
        ProgressBar progressBar = rootView.findViewById(R.id.progressBar);

        // Ajouter un listener au bouton btnRedirectionInscription
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                // Remplacer le fragment par le fragment Inscription
                //replaceFragment(new Inscription());
                EditText login = rootView.findViewById(R.id.login_utilisateur);
                EditText mdp = rootView.findViewById(R.id.mdp_utilisateur);

                String loginText = login.getText().toString();
                String mdpText = mdp.getText().toString();

                if (loginText.isEmpty() || mdpText.isEmpty()) {
                    // Si le login ou le mot de passe est vide, afficher un toast
                    Toast.makeText(getContext(), "Veuillez saisir votre mail et mot de passe", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    return; // Quitter la méthode onClick sans exécuter le reste du code
                }

                String message = "Login: " + loginText + "\nMDP: " + mdpText;
               // Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

                Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiUser.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();


                User user = new User(loginText, mdpText);
                ApiUser apiService = retrofit.create(ApiUser.class);
                Call<User> call = apiService.signin(user);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            progressBar.setVisibility(View.INVISIBLE);
                            User utilisateur = response.body();
                            if(utilisateur.getActivation_compte() == 0){
                                 System.out.println("Compte existant et activé  ------> "+utilisateur.getActivation_compte() );
                                User actif = new User(utilisateur.getUsername(), utilisateur.getMail(), utilisateur.getIdUtilisateur(),"profile");
                                SharedPreferences sp = getContext().getSharedPreferences("user_information", getActivity().MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("id_utilisateur", utilisateur.getIdUtilisateur());
                                editor.apply();
                                editor.commit();
                                replaceFragment(new Acceuil());
                                String toastMessage = String.format("Bienvenue, %s", actif.getUsername());
                                Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
                            }
                            if (utilisateur.getActivation_compte() == 1) {
                                System.out.println("Compte existant mais non activé ------>"+utilisateur.getActivation_compte());
                                replaceFragment(new Validation());
                            }
                            if (utilisateur.getActivation_compte() == 2) {
                                Toast.makeText(getContext(), "Compte introuvable", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            if (response.code() == 404) {
                                Toast.makeText(getContext(), "API non trouvée", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Erreur de l'API", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    //crée une session de paire clé valeur (clé: id_utilisateur et valeur= actif.getIdUtilisateur())
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        // Traitement de l'erreur en cas d'échec de l'appel à l'API
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getContext(), "Erreur de l'api", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


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
                replaceFragment(new Acceuil());
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
