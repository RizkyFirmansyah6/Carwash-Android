package com.projctwash.com.proyek2_carwash.Model;

import com.google.gson.annotations.SerializedName;

public class Member {
    @SerializedName("id")
    private String id;
    @SerializedName("nama")
    private String nama;
    @SerializedName("no_telp")
    private String no_telp;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("jenis")
    private  String jenis;

    public Member() {
    }

    public Member(String id, String nama, String no_telp, String alamat, String jenis) {
        this.id = id;
        this.nama = nama;
        this.no_telp = no_telp;
        this.alamat = alamat;
        this.jenis = jenis;
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

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
}
