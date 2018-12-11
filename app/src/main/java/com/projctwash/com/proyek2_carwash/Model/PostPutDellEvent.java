package com.projctwash.com.proyek2_carwash.Model;

import com.google.gson.annotations.SerializedName;

public class PostPutDellEvent {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Event mEvent;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Event getmEvent() {
        return mEvent;
    }
    public void setmEvent(Event mEvent) {
        this.mEvent = mEvent;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
