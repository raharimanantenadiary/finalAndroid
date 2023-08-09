package com.example.myapplication;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.Categorie;
import com.example.myapplication.model.Galerie;
import com.example.myapplication.model.Sitetouristique;

import java.util.List;
public class AdapterGallerie extends RecyclerView.Adapter<AdapterGallerie.ViewHolder>{
    List<Galerie> liste_gallerie;
    Context context;

    public AdapterGallerie() {

    }

    public AdapterGallerie(Context context, List<Galerie> liste_gallerie) {
        this.context = context;
        this.liste_gallerie = liste_gallerie;
    }

    @NonNull
    @Override
    public AdapterGallerie.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.galerie_layout, parent, false);
        AdapterGallerie.ViewHolder viewHolder = new AdapterGallerie.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGallerie.ViewHolder holder, int position) {
        Galerie item = liste_gallerie.get(position);
        int iconResourceId = context.getResources().getIdentifier(item.getMedia().toLowerCase(), "drawable", context.getPackageName());
        if (iconResourceId != 0) {
            holder.titre_galerie.setImageResource(iconResourceId);
        } else {
            holder.titre_galerie.setImageResource(R.drawable.ambohimanga);
        }
    }

    @Override
    public int getItemCount() {
        return liste_gallerie.size(); // Utilisez le bon nom de la liste
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView titre_galerie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titre_galerie = itemView.findViewById(R.id.intitule_gallerie);
        }
    }
}
