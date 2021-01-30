package com.pix.main.domain.models;

public class BankStatementClientRefined {

    private String accountId;

    private String bankId;

    private String nameClient;

    public BankStatementClientRefined(String accountId, String bankId, String nameClient) {
        this.accountId = accountId;
        this.bankId = bankId;
        this.nameClient = nameClient;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }
}
