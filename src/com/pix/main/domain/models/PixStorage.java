package com.pix.main.domain.models;

import java.util.ArrayList;

public class PixStorage {

    private ArrayList<Bank> banks;

    private ArrayList<Statement> statements;

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