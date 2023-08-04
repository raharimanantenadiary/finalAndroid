package com.example.myapplication;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.model.Categorie;

import java.util.ArrayList;
import java.util.List;

public class fragment2 extends Fragment implements MyAdapter.OnItemClickListener {

    View view;
    RecyclerView recyclerView;
    List<Model> itemList;


    public fragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        recyclerView = view.findViewById(R.id.rcview2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MyAdapter(initData(), this)); // Passer "this" en tant que référence de l'interface
        return view;
    }




    private List<Model> initData() {
        itemList = new ArrayList<>();
        itemList.add(new Model(1,R.drawable.test, "Radich"));
        itemList.add(new Model(2,R.drawable.logo, "Bart"));
        itemList.add(new Model(3,R.drawable.logo2, "Simpson"));
        itemList.add(new Model(4,R.drawable.logo2, "Leo"));
        itemList.add(new Model(5,R.drawable.logo2, "NOums"));
        itemList.add(new Model(6,R.drawable.logo2, "Koto"));
        return itemList;
    }

    @Override
    public void onItemClick(int idModel) {
        // Créez une instance du nouveau fragment (fragment3)
        fragment3 newFragment = new fragment3();

        // Créez un Bundle pour transmettre les données du clic au nouveau fragment
        Bundle bundle = new Bundle();
        bundle.putInt("selected_id", idModel) ;
        newFragment.setArguments(bundle);

        // Remplacez le fragment actuel par le nouveau fragment (fragment3)
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.drawerLayout, newFragment)
                .addToBackStack(null)
                .commit();
    }


}
