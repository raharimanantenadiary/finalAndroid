package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class fragment4 extends Fragment {

    View view;

    public fragment4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment4, container, false);

        // Récupérer les données transmises depuis le fragment2
        Bundle bundle = getArguments();
        if (bundle != null) {
            int selectedId = bundle.getInt("enfant_model", 0);
            EnfantModel enfantModel=new EnfantModel().getEnfantModel(selectedId);
            // Trouver le TextView par son id
            TextView enfantModelTextView = view.findViewById(R.id.enfantModel);
            ImageView imageView = view.findViewById(R.id.imageenfantmodel);
            // Mettre à jour le texte du TextView avec les données transmises
            enfantModelTextView.setText(String.valueOf(enfantModel.getIdEnfantModel()));
            imageView.setImageResource(enfantModel.getImage());
        }
        return view;
    }
}