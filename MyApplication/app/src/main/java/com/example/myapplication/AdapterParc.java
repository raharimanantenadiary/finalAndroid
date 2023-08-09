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

import com.example.myapplication.model.Sitetouristique;

import java.util.List;

public class AdapterParc extends RecyclerView.Adapter<AdapterParc.ViewHolder>{
    List<Sitetouristique> liste_touristique;
    Context context;

    public AdapterParc() {

    }

    public void filterList(List<Sitetouristique> filteredList) {
        this.liste_touristique = filteredList;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Sitetouristique sitetouristique);
    }

    private OnItemClickListener clickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    public AdapterParc(Context context, List<Sitetouristique> liste_touristique) {
        this.context = context;
        this.liste_touristique = liste_touristique;
    }

    @NonNull
    @Override
    public AdapterParc.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorie_style_fragment3, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterParc.ViewHolder holder, int position) {
        Sitetouristique item = liste_touristique.get(position);
        int iconResourceId = context.getResources().getIdentifier(item.getGalerie().get(1).getMedia().toLowerCase(), "drawable", context.getPackageName());
        if (iconResourceId != 0) {
            holder.image_site.setImageResource(iconResourceId);
        } else {
            holder.image_site.setImageResource(R.drawable.parc);
        }

        holder.titre_site.setText(item.getTitre());
        holder.lieu_site.setText("Lieu: "+item.getLocalisation());
        holder.contact_site.setText("Contact: "+item.getContact());
        holder.id_site.setText(item.getIdSitetouristique());

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
        return liste_touristique.size(); // Utilisez le bon nom de la liste
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_site;
        EditText  id_site;
        TextView titre_site;
        TextView lieu_site;
        TextView contact_site;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_site = itemView.findViewById(R.id.image_site_touristique);
            titre_site = itemView.findViewById(R.id.titre_site_touristique);
            id_site = itemView.findViewById(R.id.id_site_touristique);
            lieu_site = itemView.findViewById(R.id.localisation_site_touristique);
            contact_site = itemView.findViewById(R.id.contact_site_touristique);
        }
    }
}
