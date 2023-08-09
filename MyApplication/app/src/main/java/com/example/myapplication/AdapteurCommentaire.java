package com.example.myapplication;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Interface.ApiSiteTouristique;
import com.example.myapplication.Interface.ApiUser;
import com.example.myapplication.model.Categorie;
import com.example.myapplication.model.Commentaire;
import com.example.myapplication.model.Galerie;
import com.example.myapplication.model.Sitetouristique;
import com.example.myapplication.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapteurCommentaire extends RecyclerView.Adapter<AdapteurCommentaire.ViewHolder>{
    List<Commentaire> liste_commentaire;
    Context context;

    public AdapteurCommentaire() {

    }

    public AdapteurCommentaire(Context context, List<Commentaire> liste_commentaire) {
        this.context = context;
        this.liste_commentaire = liste_commentaire;
    }

    @NonNull
    @Override
    public AdapteurCommentaire.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commentaire_layout, parent, false);
        AdapteurCommentaire.ViewHolder viewHolder = new AdapteurCommentaire.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Commentaire item = liste_commentaire.get(position);
        holder.contenu_commentaire.setText(item.getContenu());
        holder.id_commentaire.setText(item.getId());

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiUser.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiUser apiService = retrofit.create(ApiUser.class);
        Call<User> call = apiService.getUserInfo(item.getIdUser());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User us = response.body();
                    if(us == null){
                        holder.usr_commentaire.setText("Utilisateur");
                    }else{
                        holder.usr_commentaire.setText(us.getUsername());
                    }
                   // User user = response.body();
                   // holder.usr_commentaire.setText(user.getUsername());
                } else {

                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Traitement de l'erreur en cas d'échec de l'appel à l'API
            }
        });

    }

    @Override
    public int getItemCount() {
        return liste_commentaire.size(); // Utilisez le bon nom de la liste
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText  id_commentaire;
        TextView contenu_commentaire;
        TextView usr_commentaire;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contenu_commentaire = itemView.findViewById(R.id.commentaire_publication_utilisateur);
            usr_commentaire = itemView.findViewById(R.id.commentaire_nom_utilisateur);
            id_commentaire = itemView.findViewById(R.id.idCommentaire);
        }
    }
}
