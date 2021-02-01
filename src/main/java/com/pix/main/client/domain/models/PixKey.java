package com.pix.main.client.domain.models;

import com.google.gson.annotations.SerializedName;

public class PixKey {
    @SerializedName("idChave")
    private String keyId;

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }
}
