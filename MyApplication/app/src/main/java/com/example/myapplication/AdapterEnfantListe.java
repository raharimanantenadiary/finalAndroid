package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterEnfantListe extends RecyclerView.Adapter<AdapterEnfantListe.ViewHolder>{
    List<EnfantModel> itemList;
    private AdapterEnfantListe.OnItemClickListener clickListener; // Interface pour gérer les clics

    public interface OnItemClickListener {
        void onItemClick(int id);
    }

    public AdapterEnfantListe(List<EnfantModel> itemList, AdapterEnfantListe.OnItemClickListener listener) {
        this.itemList = itemList;
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public AdapterEnfantListe.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorie_style_fragment3, parent, false);
        AdapterEnfantListe.ViewHolder viewHolder = new AdapterEnfantListe.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterEnfantListe.ViewHolder holder, int position) {
        EnfantModel item = itemList.get(position);
        holder.imageView.setImageResource(item.getImage());
        holder.itemText.setText(item.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onItemClick(item.getIdEnfantModel()); // Appel de la méthode de l'interface avec le nom de l'élément cliqué
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView itemText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImg3);
            itemText = itemView.findViewById(R.id.itemName3);
        }
    }
}
