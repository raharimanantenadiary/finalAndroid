package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ActionTypes;
import com.denzcoskun.imageslider.constants.AnimationTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemChangeListener;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.interfaces.TouchListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.myapplication.Interface.ApiSiteTouristique;
import com.example.myapplication.model.Commentaire;
import com.example.myapplication.model.Galerie;
import com.example.myapplication.model.SessionManager;
import com.example.myapplication.model.Sitetouristique;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Detail extends Fragment {

    View view;
    Sitetouristique sitetouristique;
    RecyclerView recyclerViewGallerie;
    RecyclerView recyclerViewCommentaire;
    List<Galerie> list_gallerie;
    List<Commentaire> list_commentaire;
    SessionManager sessionManager;

    public Detail() {
        // Required empty public constructor
    }

    public void setSiteTouristiqueData(Sitetouristique sitetouristique){
            this.sitetouristique = sitetouristique;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment4, container, false);
        SharedPreferences sp = getContext().getSharedPreferences("user_information", getContext().MODE_PRIVATE);
        String idUtilisateur = sp.getString("id_utilisateur", "false");
        // Site touristique
        TextView contact_texte_site = view.findViewById(R.id.detail_contact_site_touristique);



        TextView titre_texte_site = view.findViewById(R.id.detail_titre_site_touristique);
        TextView historique_texte_site = view.findViewById(R.id.detail_historique_site_touristique);
        TextView mail_texte_site = view.findViewById(R.id.detail_email_site_touristique);

        contact_texte_site.setText("Contact: "+sitetouristique.getContact());
        titre_texte_site.setText(sitetouristique.getTitre());
        historique_texte_site.setText("Historique: "+sitetouristique.getHistorique());
        mail_texte_site.setText("Email: "+sitetouristique.getEmail());

        // Gallerie
        RecyclerView recyclerViewGallerie = view.findViewById(R.id.recyclerview_galerie);
        recyclerViewGallerie.setHasFixedSize(true);
        recyclerViewGallerie.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        list_gallerie = sitetouristique.getGalerie();
        List<Galerie> liste_gal = new ArrayList<>();
        for (int i = 0; i < list_gallerie.size(); i++) {
            liste_gal.add(sitetouristique.getGalerie().get(i));
        }
        AdapterGallerie myAdapterGallerie = new AdapterGallerie(getContext(), liste_gal);
        recyclerViewGallerie.setAdapter(myAdapterGallerie);

        // Commentaire
        recyclerViewCommentaire = view.findViewById(R.id.recyclerview_commentaire);
        recyclerViewCommentaire.setHasFixedSize(true);
        recyclerViewCommentaire.setLayoutManager(new LinearLayoutManager(getContext()));
        list_commentaire = sitetouristique.getCommentaire();
        AdapteurCommentaire adapteurCommentaire = new AdapteurCommentaire(getContext(),list_commentaire);
        recyclerViewCommentaire.setAdapter(adapteurCommentaire);

        // Ajout Commentaire
        EditText comment_user = view.findViewById(R.id.commentaire_utilisateur);
        Button btn_add_comment = view.findViewById(R.id.btn_ajout_commentaire);

        if (idUtilisateur.equals("false")) {
            btn_add_comment.setEnabled(false);
        }else {
            btn_add_comment.setEnabled(true);
            // Ajout ecouteur boutton ajout commentaire
            btn_add_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String commentaire_utilisateur = comment_user.getText().toString();

                    Date currentDate = new Date();
                    long timestamp = currentDate.getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = sdf.format(currentDate);
                    Commentaire commentaire_objet = new Commentaire(sitetouristique.getIdSitetouristique(),idUtilisateur, commentaire_utilisateur, formattedDate,"");

                    Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiSiteTouristique.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    ApiSiteTouristique apiService = retrofit.create(ApiSiteTouristique.class);
                    Call<Commentaire> call = apiService.ajoutCommentaire(commentaire_objet);

                    call.enqueue(new Callback<Commentaire>() {
                        @Override
                        public void onResponse(Call<Commentaire> call, Response<Commentaire> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getContext(), "Commentaire ajouté", Toast.LENGTH_SHORT).show();
                                comment_user.setText("");
                                list_commentaire.add(commentaire_objet);
                                // Notifier l'adaptateur des changements
                                adapteurCommentaire.notifyDataSetChanged();

                            } else {
                                if (response.code() == 404) {
                                    Toast.makeText(getContext(), "API non trouvée", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getContext(), "Erreur de l'API", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        @Override
                        public void onFailure(Call<Commentaire> call, Throwable t) {
                            // Traitement de l'erreur en cas d'échec de l'appel à l'API
                            Toast.makeText(getContext(), "Erreur de l'api", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }




        return view;
    }
}