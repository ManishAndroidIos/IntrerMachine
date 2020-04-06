package com.app.itauditor.model;

/**
 * Created by Manish Ahire on 08,February,2020
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response_User_Login {

    @SerializedName("user_id")
    @Expose
    private int user_id;


    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("refresh_token")
    @Expose
    private String refresh_token;

    @SerializedName("expires_on")
    @Expose
    private String expires_on;

    @SerializedName("vmq_pass")
    @Expose
    private String vmq_pass;

    @SerializedName("default_site")
    @Expose
    private String default_site;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getExpires_on() {
        return expires_on;
    }

    public void setExpires_on(String expires_on) {
        this.expires_on = expires_on;
    }

    public String getVmq_pass() {
        return vmq_pass;
    }

    public void setVmq_pass(String vmq_pass) {
        this.vmq_pass = vmq_pass;
    }

    public String getDefault_site() {
        return default_site;
    }

    public void setDefault_site(String default_site) {
        this.default_site = default_site;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
