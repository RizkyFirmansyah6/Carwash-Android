package com.projctwash.com.proyek2_carwash.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetKaryawan {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Karyawan> listDataKaryawan;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public List<Karyawan> getListDataKaryawan() {
        return listDataKaryawan;
    }
    public void setListDataKaryawan(List<Karyawan> listDataKaryawan) {
        this.listDataKaryawan = listDataKaryawan;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
