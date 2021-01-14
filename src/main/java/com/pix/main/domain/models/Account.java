package com.pix.main.domain.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Account {

    @SerializedName("idConta")
    private String accountId;

    @SerializedName("idAgencia")
    private String agencyId;

    @SerializedName("saldo")
    private String balance;

    @SerializedName("chavesPix")
    private ArrayList<PixKey> pixKeys;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public ArrayList<PixKey> getPixKeys() {
        return pixKeys;
    }

    public void setPixKeys(ArrayList<PixKey> pixKeys) {
        this.pixKeys = pixKeys;
    }
}
