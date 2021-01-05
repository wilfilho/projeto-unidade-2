package com.pix.main.models;

public class Agency {

    private Integer id;

    private String name;

    public Agency(Integer _id, String _name) {
        this.id = _id;
        this.name = _name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
