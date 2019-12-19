package com.projctwash.com.proyek2_carwash.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetMember {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Member> listDataMember;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Member> getListDataMember() {
        return listDataMember;
    }

    public void setListDataMember(List<Member> listDataMember) {
        this.listDataMember = listDataMember;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
