package com.pix.main.domain.models;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Account {

    @SerializedName("idConta")
    private String accountId;

    @SerializedName("idAgencia")
    private String agencyId;

    @SerializedName("idBanco")
    private String bankId;

    @SerializedName("saldo")
    private BigDecimal balance = new BigDecimal(0);

    @SerializedName("tipo")
    private String accountType;

    @SerializedName("idClient")
    private String clientId;

    @SerializedName("chavesPix")
    private ArrayList<PixKey> pixKeys = new ArrayList<>();

    @SerializedName("extratoBancario")
    private ArrayList<BankStatement> bankStatements = new ArrayList<>();

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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public ArrayList<PixKey> getPixKeys() {
        return pixKeys;
    }

    public void setPixKeys(ArrayList<PixKey> pixKeys) {
        this.pixKeys = pixKeys;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public ArrayList<BankStatement> getBankStatements() {
        return bankStatements;
    }

    public void setBankStatements(ArrayList<BankStatement> bankStatements) {
        this.bankStatements = bankStatements;
    }
}
