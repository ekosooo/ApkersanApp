package com.example.apkersan2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataRegister {
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("user_nama")
    @Expose
    private String userNama;
    @SerializedName("user_jk")
    @Expose
    private String userJk;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_phone")
    @Expose
    private String userPhone;
    @SerializedName("user_alamat")
    @Expose
    private String userAlamat;
    @SerializedName("role")
    @Expose
    private String role;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserNama() {
        return userNama;
    }

    public void setUserNama(String userNama) {
        this.userNama = userNama;
    }

    public String getUserJk() {
        return userJk;
    }

    public void setUserJk(String userJk) {
        this.userJk = userJk;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAlamat() {
        return userAlamat;
    }

    public void setUserAlamat(String userAlamat) {
        this.userAlamat = userAlamat;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
