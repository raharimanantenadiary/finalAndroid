package com.example.myapplication;

import java.util.List;

public class Model {

    int image;
    int idModel;
    String name;
    public Model(int idModel,int image,String name) {
        this.idModel = idModel;
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public int getIdModel() {
        return idModel;
    }

    public void setIdModel(int idModel) {
        this.idModel = idModel;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
