package com.pix.main.domain.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Bank {

    @SerializedName("id")
    private String id;

    @SerializedName("nome")
    private String name;

    @SerializedName("agencias")
    private List<Agency> agencies;

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

    public List<Agency> getAgencies() {
        return agencies;
    }

    public void setAgencies(List<Agency> agencies) {
        this.agencies = agencies;
    }

}
