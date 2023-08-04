package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    List<Model> itemList;
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
        Model item = itemList.get(position);
        holder.imageView.setImageResource(item.getImage());
        holder.itemText.setText(item.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onItemClick(item.getIdModel()); // Appel de la méthode de l'interface avec le nom de l'élément cliqué
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
        EditText editText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImg);
            itemText = itemView.findViewById(R.id.itemName);
            editText = itemView.findViewById(R.id.idModel);
        }
    }
}
