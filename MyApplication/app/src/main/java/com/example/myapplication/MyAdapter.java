package com.example.myapplication;

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

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    List<Model> itemList;
    List<Categorie> liste_cat;
    Context context;

    public MyAdapter(Context context,List<Categorie> liste_cat){
        this.context = context;
        this.liste_cat = liste_cat;
    }

    private OnItemClickListener clickListener; // Interface pour gérer les clics

    public interface OnItemClickListener {
        void onItemClick(int idModel);
    }

    public MyAdapter(List<Model> itemList, OnItemClickListener listener) {
        this.itemList = itemList;
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorie_style, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        Categorie item = liste_cat.get(holder.getAdapterPosition());
        holder.intitule_cat.setText(item.getIntitule());
        int iconResourceId = context.getResources().getIdentifier(item.getIcone(), "drawable", context.getPackageName());
        if (iconResourceId != 0) {
            holder.image_cat.setImageResource(iconResourceId);
        } else {
            holder.image_cat.setImageResource(R.drawable.test);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onItemClick(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return liste_cat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_cat;
        TextView intitule_cat;
        EditText id_cat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_cat = itemView.findViewById(R.id.image_categorie);
            intitule_cat = itemView.findViewById(R.id.intitule_categorie);
            id_cat = itemView.findViewById(R.id.idCategorie);
        }
    }
}
