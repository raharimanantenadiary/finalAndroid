package com.example.myapplication.controller;

import com.example.myapplication.Service.ServiceCategorie;
import com.example.myapplication.model.Categorie;

public class RootController {

    ServiceCategorie serviceCategorie;

    public RootController() {
        this.serviceCategorie = new ServiceCategorie();
    }

    public ServiceCategorie getServiceCategorie() {
        return serviceCategorie;
    }

}
