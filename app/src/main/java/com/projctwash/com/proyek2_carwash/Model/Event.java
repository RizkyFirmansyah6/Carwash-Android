package com.projctwash.com.proyek2_carwash.Model;

import com.google.gson.annotations.SerializedName;

public class Event {
    @SerializedName("id")
    private String id;
    @SerializedName("nama_event")
    private String nama_event;
    @SerializedName("img")
    private String img;
    @SerializedName("detail")
    private String detail;
    @SerializedName("diskon")
    private String diskon;
    @SerializedName("required")
    private String required;
    @SerializedName("bulan")
    private String bulan;
    @SerializedName("status")
    private String status;

    public Event(String id, String nama_event, String img, String detail, String diskon, String required, String bulan, String status) {
        this.id = id;
        this.nama_event = nama_event;
        this.img = img;
        this.detail = detail;
        this.diskon = diskon;
        this.required = required;
        this.bulan = bulan;
        this.status = status;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getNama_event() {
        return nama_event;
    }
    public void setNama_event(String nama_event) {
        this.nama_event = nama_event;
    }

    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }

    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDiskon() {
        return diskon;
    }
    public void setDiskon(String diskon) {
        this.diskon = diskon;
    }

    public String getRequired() {
        return required;
    }
    public void setRequired(String required) {
        this.required = required;
    }

    public String getBulan() {
        return bulan;
    }
    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
