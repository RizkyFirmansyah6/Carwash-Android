package com.projctwash.com.proyek2_carwash.Model;

import com.google.gson.annotations.SerializedName;

public class EventCheckUser {
    @SerializedName("status")
    String status;
    @SerializedName("jumlah")
    String jumlah;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getJumlah() {
        return jumlah;
    }
    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
