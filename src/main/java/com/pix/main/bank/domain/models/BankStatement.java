package com.pix.main.domain.models;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class BankStatement {

    @SerializedName("idConta")
    private String accountTransactionId;

    @SerializedName("idBanco")
    private String bankTransactionId;

    @SerializedName("valorEnviado")
    private BigDecimal value;


    public String getAccountTransactionId() {
        return accountTransactionId;
    }

    public void setAccountTransactionId(String accountTransactionId) {
        this.accountTransactionId = accountTransactionId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getBankTransactionId() {
        return bankTransactionId;
    }

    public void setBankTransactionId(String bankTransactionId) {
        this.bankTransactionId = bankTransactionId;
    }

}
