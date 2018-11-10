package com.projctwash.com.proyek2_carwash.Model;

import com.google.gson.annotations.SerializedName;

public class Karyawan {

    @SerializedName("id")
    private String id;
    @SerializedName("nama")
    private String nama;
    @SerializedName("nohp")
    private String nohp;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("password")
    private  String password;
    @SerializedName("level")
    private  String level;

    public Karyawan() {
    }

    public Karyawan(String id, String nama, String nohp, String alamat, String password, String level) {
        this.id = id;
        this.nama = nama;
        this.nohp = nohp;
        this.alamat = alamat;
        this.password = password;
        this.level = level;
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

    public String getNohp() {
        return nohp;
    }
    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
}
