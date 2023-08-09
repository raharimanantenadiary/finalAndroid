package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.myapplication.Interface.OnNightModeChangedListener;
import com.google.android.material.navigation.NavigationView;

public class BasculeMod extends Fragment {
    View view;
    SwitchCompat switchCompat;
    SharedPreferences sharedPreferences = null;
    public BasculeMod() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bascule_mod, container, false);

        // Obtenir le contexte de l'activité parente à partir de la vue du fragment
        if (getContext() != null) {
            sharedPreferences = getContext().getSharedPreferences("night", 0);
        }

        switchCompat = view.findViewById(R.id.switchCompattest);
        Boolean booleanValue = sharedPreferences.getBoolean("night_mode", true);
        switchCompat.setChecked(booleanValue); // Mettre à jour l'état du bouton en fonction du mode actuel

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("night_mode", true);
                    editor.apply();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("night_mode", false);
                    editor.apply();
                }
            }
        });

        return view;
    }


}