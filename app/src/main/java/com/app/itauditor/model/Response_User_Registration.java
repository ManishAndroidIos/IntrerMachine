package com.app.itauditor.model;

/**
 * Created by Manish Ahire on 08,February,2020
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response_User_Registration {


    @SerializedName("message")
    @Expose
    private String message;


    @SerializedName("status")
    @Expose
    private String status;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
