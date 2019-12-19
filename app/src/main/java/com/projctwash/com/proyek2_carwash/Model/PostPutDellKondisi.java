package com.projctwash.com.proyek2_carwash.Model;

import com.google.gson.annotations.SerializedName;

public class PostPutDellKondisi {

    @SerializedName("status")
    String status;
    @SerializedName("result")
    Kondisi mKondisi;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Kondisi getmKondisi() {
        return mKondisi;
    }
    public void setmKondisi(Kondisi mKondisi) {
        this.mKondisi = mKondisi;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
