package com.pix.main.client.domain.models;

import com.google.gson.annotations.SerializedName;
import com.pix.main.bank.domain.models.Bank;
import com.pix.main.bank.domain.models.BankStatement;

import java.util.ArrayList;

public class PixStorage {

    @SerializedName("bancos")
    private ArrayList<Bank> banks;

    @SerializedName("extratos")
    private ArrayList<BankStatement> bankStatements;

    @SerializedName("clientes")
    private ArrayList<BankClient> clients;

    public ArrayList<Bank> getBanks() {
        return banks;
    }

    public void setBanks(ArrayList<Bank> banks) {
        this.banks = banks;
    }

    public ArrayList<BankStatement> getStatements() {
        return bankStatements;
    }

    public void setStatements(ArrayList<BankStatement> bankStatements) {
        this.bankStatements = bankStatements;
    }

    public ArrayList<BankClient> getClients() {
        return clients;
    }

    public void setClients(ArrayList<BankClient> clients) {
        this.clients = clients;
    }
}