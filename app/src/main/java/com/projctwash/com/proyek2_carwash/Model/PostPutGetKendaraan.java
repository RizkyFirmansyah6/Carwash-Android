package com.projctwash.com.proyek2_carwash.Model;

import com.google.gson.annotations.SerializedName;

public class PostPutGetKendaraan {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Kendaraan mKendaraan;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Kendaraan getmKendaraan() {
        return mKendaraan;
    }
    public void setmKendaraan(Kendaraan mKendaraan) {
        this.mKendaraan = mKendaraan;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
