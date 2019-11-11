package com.example.apkersan2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataKasus {

    @SerializedName("kasus_id")
    @Expose
    private Integer kasusId;
    @SerializedName("kasus_nama")
    @Expose
    private String kasusNama;
    @SerializedName("kasus_deskripsi")
    @Expose
    private String kasusDeskripsi;

    public Integer getKasusId() {
        return kasusId;
    }

    public void setKasusId(Integer kasusId) {
        this.kasusId = kasusId;
    }

    public String getKasusNama() {
        return kasusNama;
    }

    public void setKasusNama(String kasusNama) {
        this.kasusNama = kasusNama;
    }

    public String getKasusDeskripsi() {
        return kasusDeskripsi;
    }

    public void setKasusDeskripsi(String kasusDeskripsi) {
        this.kasusDeskripsi = kasusDeskripsi;
    }
}
