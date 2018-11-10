package com.projctwash.com.proyek2_carwash.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetKondisi {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Kondisi> listDataKondisi;
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

    public List<Kondisi> getListDataKondisi() {
        return listDataKondisi;
    }
    public void setListDataKondisi(List<Kondisi> listDataKondisi) {
        this.listDataKondisi = listDataKondisi;
    }
}
