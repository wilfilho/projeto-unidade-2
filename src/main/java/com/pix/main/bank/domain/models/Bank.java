package com.pix.main.domain.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Bank {

    @SerializedName("id")
    private String id;

    @SerializedName("nome")
    private String name;

    @SerializedName("agencias")
    private ArrayList<Agency> agencies = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Agency> getAgencies() {
        return agencies;
    }

    public void setAgencies(ArrayList<Agency> agencies) {
        this.agencies = agencies;
    }

}
