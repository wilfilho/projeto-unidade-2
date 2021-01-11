package com.pix.main.domain.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BankClient {

    @SerializedName("id")
    private String id;

    @SerializedName("nome")
    private String name;

    @SerializedName("contas")
    private List<Account> accounts;
    
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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
