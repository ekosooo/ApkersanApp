package com.example.apkersan2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataBerita {
    @SerializedName("berita_id")
    @Expose
    private Integer beritaId;
    @SerializedName("judul_berita")
    @Expose
    private String judulBerita;
    @SerializedName("konten_berita")
    @Expose
    private String kontenBerita;
    @SerializedName("penulis_berita")
    @Expose
    private String penulisBerita;
    @SerializedName("gambar")
    @Expose
    private String gambar;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public Integer getBeritaId() {
        return beritaId;
    }

    public void setBeritaId(Integer beritaId) {
        this.beritaId = beritaId;
    }

    public String getJudulBerita() {
        return judulBerita;
    }

    public void setJudulBerita(String judulBerita) {
        this.judulBerita = judulBerita;
    }

    public String getKontenBerita() {
        return kontenBerita;
    }

    public void setKontenBerita(String kontenBerita) {
        this.kontenBerita = kontenBerita;
    }

    public String getPenulisBerita() {
        return penulisBerita;
    }

    public void setPenulisBerita(String penulisBerita) {
        this.penulisBerita = penulisBerita;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
