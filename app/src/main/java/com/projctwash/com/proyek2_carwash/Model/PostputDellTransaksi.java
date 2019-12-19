package com.projctwash.com.proyek2_carwash.Model;

import com.google.gson.annotations.SerializedName;

public class PostputDellTransaksi {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Transaksi mTransaksi;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Transaksi getmTransaksi() {
        return mTransaksi;
    }
    public void setmTransaksi(Transaksi mTransaksi) {
        this.mTransaksi = mTransaksi;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
