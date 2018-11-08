package com.projctwash.com.proyek2_carwash.Model;

import com.google.gson.annotations.SerializedName;

public class PostPutDellKaryawan {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Karyawan mKaryawan;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Karyawan getmKaryawan() {
        return mKaryawan;
    }
    public void setmKaryawan(Karyawan mKaryawan) {
        this.mKaryawan = mKaryawan;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
