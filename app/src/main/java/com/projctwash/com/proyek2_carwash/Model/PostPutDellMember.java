package com.projctwash.com.proyek2_carwash.Model;

import com.google.gson.annotations.SerializedName;

public class PostPutDellMember {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Member mMember;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Member getmMember() {
        return mMember;
    }

    public void setmMember(Member mMember) {
        this.mMember = mMember;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
