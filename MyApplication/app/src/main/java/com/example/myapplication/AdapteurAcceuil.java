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

public class AdapteurAcceuil extends RecyclerView.Adapter<AdapteurAcceuil.ViewHolder> {
    List<Categorie> liste_cat;
    Context context;
    public interface OnItemClickListener {
        void onItemClick(Categorie categorie);
    }

    private OnItemClickListener clickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    public AdapteurAcceuil(Context context, List<Categorie> liste_cat) {
        this.context = context;
        this.liste_cat = liste_cat;
    }

    @NonNull
    @Override
    public AdapteurAcceuil.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorie_style, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapteurAcceuil.ViewHolder holder, int position) {
        Categorie item = liste_cat.get(position);
        holder.intitule_cat.setText(item.getIntitule());
        holder.id_cat.setText(item.getIdCategorie());

        int iconResourceId = context.getResources().getIdentifier(item.getIcone().split("_")[1], "drawable", context.getPackageName());
        if (iconResourceId != 0) {
            holder.image_cat.setImageResource(iconResourceId);
        } else {
            holder.image_cat.setImageResource(R.drawable.logo);
        }

        // Ici, nous définissons le clic sur l'élément de la vue
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onItemClick(item); // Appel de l'interface pour gérer le clic
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
