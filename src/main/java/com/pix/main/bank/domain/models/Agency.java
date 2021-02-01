package com.pix.main.domain.models;

import com.google.gson.annotations.SerializedName;

public class Agency {

    @SerializedName("id")
    private String id;

    @SerializedName("nome")
    private String name;

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
}
