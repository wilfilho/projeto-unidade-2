package com.pix.main.domain.models;

import java.util.ArrayList;

public class Bank {

    private String id;

    private String name;

    private ArrayList<Agency> agencies;

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
