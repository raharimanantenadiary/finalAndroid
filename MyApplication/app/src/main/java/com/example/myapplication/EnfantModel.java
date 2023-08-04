package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class EnfantModel {

    int idEnfantModel;
    String name;
    int idModel;
    int image;


    public EnfantModel() {

    }

    public int getIdEnfantModel() {
        return idEnfantModel;
    }

    public void setIdEnfantModel(int idEnfantModel) {
        this.idEnfantModel = idEnfantModel;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public EnfantModel(int idEnfantModel,int image, String name, int idModel) {
        this.idEnfantModel = idEnfantModel;
        this.name = name;
        this.image = image;
        this.idModel = idModel;
    }

    public int getIdModel() {
        return idModel;
    }

    public void setIdModel(int idModel) {
        this.idModel = idModel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EnfantModel> listeEnfantModel() {
        List<EnfantModel> itemList;
            itemList = new ArrayList<>();
            itemList.add(new EnfantModel(1,R.drawable.test,"Bartolome", 1));
            itemList.add(new EnfantModel(2,R.drawable.logo,"Simba",1));
            itemList.add(new EnfantModel(3,R.drawable.logo2,"varatra", 1));
            itemList.add(new EnfantModel(4,R.drawable.logo2,"tiana", 1));
            itemList.add(new EnfantModel(5,R.drawable.logo2,"solofo", 2));
            itemList.add(new EnfantModel(6,R.drawable.logo2,"Bera", 2));
        return itemList;
    }

    public List<EnfantModel> recherchelisteEnfantModel(int idModel) {
        List<EnfantModel> itemList = this.listeEnfantModel();
        List<EnfantModel> nouveau = new ArrayList<>(); // Initialisez la liste
        for (int i = 0; i < itemList.size(); i++) {
            if (idModel == itemList.get(i).getIdModel()) {
                nouveau.add(new EnfantModel(itemList.get(i).getIdEnfantModel(),itemList.get(i).getImage(), itemList.get(i).getName(), itemList.get(i).getIdModel()));
            }
        }
        return nouveau;
    }

    public EnfantModel getEnfantModel(int id) {
        List<EnfantModel> itemList = this.listeEnfantModel();
        EnfantModel enfantModel = null;
        for (int i = 0; i < itemList.size(); i++) {
            if (id == itemList.get(i).getIdEnfantModel()) {
                enfantModel = new EnfantModel(
                        itemList.get(i).getIdEnfantModel(),
                        itemList.get(i).getImage(),
                        itemList.get(i).getName(),
                        itemList.get(i).getIdModel()
                );
            }
        }
        return enfantModel;
    }







}
