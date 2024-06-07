package com.app.dbug.model;

import com.google.gson.annotations.SerializedName;

public class DeviceToken {
    @SerializedName("packeg_name")
    String packeg_name;
    @SerializedName("device_token")
    String device_token;

    public DeviceToken(String packeg_name, String device_token) {
        this.packeg_name = packeg_name;
        this.device_token = device_token;
    }

    public String getPackeg_name() {
        return packeg_name;
    }

    public void setPackeg_name(String packeg_name) {
        this.packeg_name = packeg_name;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }
}
