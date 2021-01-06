package com.pix.main.domain.models;

public class Statement {

    private String idAccountReceiver;

    private String idAccountSender;

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
