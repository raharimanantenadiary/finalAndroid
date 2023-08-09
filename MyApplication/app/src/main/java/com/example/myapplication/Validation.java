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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Validation extends Fragment {

    View view;
    Button boutton_valider_compte;


    public Validation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_fragment1, container, false);
        boutton_valider_compte = view.findViewById(R.id.btn_valider_compte);
        EditText code = view.findViewById(R.id.code_validation);
        System.out.println("code "+code);

        boutton_valider_compte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code_val = code.getText().toString();

                if (code_val.isEmpty()) {
                    // Si le login ou le mot de passe est vide, afficher un toast
                    Toast.makeText(getContext(), "Veuillez Saisir le code de validation", Toast.LENGTH_SHORT).show();
                    return; // Quitter la méthode onClick sans exécuter le reste du code
                }

                Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiUser.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiUser apiService = retrofit.create(ApiUser.class);
                User user_activation = new User(code_val);
                Call<ResponseBody> call = apiService.validation_compte(user_activation);
                System.out.println("code de validation ============>"+code_val);
                Gson gson = new GsonBuilder().create();
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            ResponseBody responseBody = response.body();
                            try {
                                String jsonResponse = responseBody.string();
                                User data = gson.fromJson(jsonResponse, User.class);
                              if(data.getUsername() != null){
                                  Toast.makeText(getContext(), "Compte validé", Toast.LENGTH_SHORT).show();
                                  replaceFragment(new Login());
                              }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //replaceFragment(new Login());
                        } else {
                            // Gérer les différentes raisons d'échec de l'appel
                            if (response.code() == 404) {
                                // API non trouvé
                                Toast.makeText(getContext(), "Introuvable", Toast.LENGTH_SHORT).show();
                            } else {
                                // Autre erreur (code HTTP différent de 200)
                                Toast.makeText(getContext(), "Erreur", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    //crée une session de paire clé valeur (clé: id_utilisateur et valeur= actif.getIdUtilisateur())
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        // Traitement de l'erreur en cas d'échec de l'appel à l'API
                        Toast.makeText(getContext(), "Erreur de l'api", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
       return view;
    }

    private void replaceFragment(Fragment fragment) {
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}