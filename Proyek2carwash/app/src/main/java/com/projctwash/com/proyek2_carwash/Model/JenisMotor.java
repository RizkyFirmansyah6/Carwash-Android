package com.projctwash.com.proyek2_carwash.Model;

public class JenisMotor {

    private String nama;
    private Integer id,harga;

    public JenisMotor(String nama, Integer id, Integer harga) {
        this.nama = nama;
        this.id = id;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getHarga() {
        return harga;
    }
    public void setHarga(Integer harga) {
        this.harga = harga;
    }
}
