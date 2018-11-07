package com.projctwash.com.proyek2_carwash.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetKendaraan {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Kendaraan> listDataKendaraan;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<Kendaraan> getListDataKendaraan() {
        return listDataKendaraan;
    }
    public void setListDataKendaraan(List<Kendaraan> listDataKendaraan) {
        this.listDataKendaraan = listDataKendaraan;
    }
}
