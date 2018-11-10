package com.projctwash.com.proyek2_carwash.Model;

import com.google.gson.annotations.SerializedName;

public class Transaksi {

    @SerializedName("id")
    private String id;
    @SerializedName("nopol")
    private String nopol;
    @SerializedName("kendaraan")
    private String kendaraan;
    @SerializedName("harga_kendaraan")
    private String harga_kendaraan;
    @SerializedName("kondisi")
    private String kondisi;
    @SerializedName("harga_kondisi")
    private String harga_kondisi;
    @SerializedName("total")
    private String total;
    @SerializedName("kasir")
    private String kasir;
    @SerializedName("tanggal")
    private String tanggal;

    public Transaksi() {}

    public Transaksi(String id, String nopol, String kendaraan, String harga_kendaraan, String kondisi, String harga_kondisi, String total, String kasir, String tanggal){
        this.id = id;
        this.nopol = nopol;
        this.kendaraan = kendaraan;
        this.harga_kendaraan = harga_kendaraan;
        this.kondisi = kondisi;
        this.harga_kondisi = harga_kondisi;
        this.total = total;
        this.kasir = kasir;
        this.tanggal = tanggal;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getNopol() {
        return nopol;
    }
    public void setNopol(String nopol) {
        this.nopol = nopol;
    }

    public String getKendaraan() {
        return kendaraan;
    }
    public void setKendaraan(String kendaraan) {
        this.kendaraan = kendaraan;
    }

    public String getHarga_kendaraan() {
        return harga_kendaraan;
    }
    public void setHarga_kendaraan(String harga_kendaraan) {
        this.harga_kendaraan = harga_kendaraan;
    }

    public String getKondisi() {
        return kondisi;
    }
    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public String getHarga_kondisi() {
        return harga_kondisi;
    }
    public void setHarga_kondisi(String harga_kondisi) {
        this.harga_kondisi = harga_kondisi;
    }

    public String getTotal() {
        return total;
    }
    public void setTotal(String total) {
        this.total = total;
    }

    public String getKasir() {
        return kasir;
    }
    public void setKasir(String kasir) {
        this.kasir = kasir;
    }

    public String getTanggal() {
        return tanggal;
    }
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}