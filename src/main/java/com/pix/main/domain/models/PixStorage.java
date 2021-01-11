package com.pix.main.domain.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PixStorage {

    @SerializedName("bancos")
    private ArrayList<Bank> banks;

    @SerializedName("extratos")
    private ArrayList<Statement> statements;

    @SerializedName("clientes")
    private ArrayList<BankClient> clients;

    public ArrayList<Bank> getBanks() {
        return banks;
    }

    public void setBanks(ArrayList<Bank> banks) {
        this.banks = banks;
    }

    public ArrayList<Statement> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<Statement> statements) {
        this.statements = statements;
    }
}