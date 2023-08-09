package com.example.myapplication.model;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "user_data";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_MAIL = "mail";
    private static final String KEY_ID_UTILISATEUR = "idUtilisateur";

    private Context context;

    public SessionManager(Context context) {
        this.context = context;
    }

    public SessionManager() {

    }

    public void saveUserData(User user) {
        // Obtenez une référence aux SharedPreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Éditez le fichier SharedPreferences en utilisant un éditeur
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Ajoutez les informations de l'utilisateur dans les SharedPreferences
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putString(KEY_MAIL, user.getMail());
        editor.putString(KEY_ID_UTILISATEUR, user.getIdUtilisateur());

        // Appliquez les modifications
        editor.apply();
    }

    public User getSavedUserData() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Récupérez les informations de l'utilisateur depuis les SharedPreferences
        String username = sharedPreferences.getString(KEY_USERNAME, "");
        String mail = sharedPreferences.getString(KEY_MAIL, "");
        String idUtilisateur = sharedPreferences.getString(KEY_ID_UTILISATEUR, "");

        // Créez et retournez un nouvel objet User avec les informations récupérées
        return new User(username, mail, idUtilisateur);
    }

    public void clearUserData() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Éditez le fichier SharedPreferences en utilisant un éditeur pour supprimer les données de l'utilisateur
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_USERNAME);
        editor.remove(KEY_MAIL);
        editor.remove(KEY_ID_UTILISATEUR);

        // Appliquez les modifications
        editor.apply();
    }




}


