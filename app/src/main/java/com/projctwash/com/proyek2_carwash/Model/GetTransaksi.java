package com.projctwash.com.proyek2_carwash.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetTransaksi {

    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Transaksi> listDataTransaksi;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public List<Transaksi> getListDataTransaksi() {
        return listDataTransaksi;
    }
    public void setListDataTransaksi(List<Transaksi> listDataTransaksi) {
        this.listDataTransaksi = listDataTransaksi;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
