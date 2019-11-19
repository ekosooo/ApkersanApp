package com.example.apkersan2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePengaduan {
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("aduan")
    @Expose
    private String aduan;
    @SerializedName("diterima")
    @Expose
    private String diterima;
    @SerializedName("ditolak")
    @Expose
    private String ditolak;
    @SerializedName("data")
    @Expose
    private List<DataPengaduan> data = null;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getAduan() {
        return aduan;
    }

    public void setAduan(String aduan) {
        this.aduan = aduan;
    }

    public String getDiterima() {
        return diterima;
    }

    public void setDiterima(String diterima) {
        this.diterima = diterima;
    }

    public String getDitolak() {
        return ditolak;
    }

    public void setDitolak(String ditolak) {
        this.ditolak = ditolak;
    }

    public List<DataPengaduan> getData() {
        return data;
    }

    public void setData(List<DataPengaduan> data) {
        this.data = data;
    }
}
