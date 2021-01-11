package com.pix.main.domain.models;

import com.google.gson.annotations.SerializedName;

public class Statement {
    @SerializedName("idContaRecebedor")
    private String idAccountReceiver;

    @SerializedName("idContaEnvia")
    private String idAccountSender;

    @SerializedName("valorEnviado")
    private Float value;

    public String getIdAccountReceiver() {
        return idAccountReceiver;
    }

    public void setIdAccountReceiver(String idAccountReceiver) {
        this.idAccountReceiver = idAccountReceiver;
    }

    public String getIdAccountSender() {
        return idAccountSender;
    }

    public void setIdAccountSender(String idAccountSender) {
        this.idAccountSender = idAccountSender;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
