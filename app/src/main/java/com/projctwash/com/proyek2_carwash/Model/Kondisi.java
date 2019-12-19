package com.projctwash.com.proyek2_carwash.Model;

import com.google.gson.annotations.SerializedName;

public class Kondisi {

    @SerializedName("id")
    private String id;
    @SerializedName("nama")
    private String nama;
    @SerializedName("harga")
    private String harga;
    @SerializedName("img")
    private String img;

    public Kondisi() { }
    public Kondisi(String id, String nama, String harga, String img) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.img = img;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }
    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
}
