package com.example.apkersan2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataPengaduan {
    @SerializedName("pengaduan_id")
    @Expose
    private Integer pengaduanId;
    @SerializedName("kasus_nama")
    @Expose
    private String kasusNama;
    @SerializedName("kekerasan_nama")
    @Expose
    private String kekerasanNama;
    @SerializedName("ticket_number")
    @Expose
    private String ticketNumber;
    @SerializedName("korban_nama")
    @Expose
    private String korbanNama;
    @SerializedName("alamat_kejadian")
    @Expose
    private String alamatKejadian;
    @SerializedName("waktu_kejadian")
    @Expose
    private String waktuKejadian;
    @SerializedName("kronologi")
    @Expose
    private String kronologi;
    @SerializedName("bukti")
    @Expose
    private String bukti;
    @SerializedName("status_pengaduan")
    @Expose
    private String statusPengaduan;
    @SerializedName("tindak_lanjut")
    @Expose
    private String tindakLanjut;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public Integer getPengaduanId() {
        return pengaduanId;
    }

    public void setPengaduanId(Integer pengaduanId) {
        this.pengaduanId = pengaduanId;
    }

    public String getKasusNama() {
        return kasusNama;
    }

    public void setKasusNama(String kasusNama) {
        this.kasusNama = kasusNama;
    }

    public String getKekerasanNama() {
        return kekerasanNama;
    }

    public void setKekerasanNama(String kekerasanNama) {
        this.kekerasanNama = kekerasanNama;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getKorbanNama() {
        return korbanNama;
    }

    public void setKorbanNama(String korbanNama) {
        this.korbanNama = korbanNama;
    }

    public String getAlamatKejadian() {
        return alamatKejadian;
    }

    public void setAlamatKejadian(String alamatKejadian) {
        this.alamatKejadian = alamatKejadian;
    }

    public String getWaktuKejadian() {
        return waktuKejadian;
    }

    public void setWaktuKejadian(String waktuKejadian) {
        this.waktuKejadian = waktuKejadian;
    }

    public String getKronologi() {
        return kronologi;
    }

    public void setKronologi(String kronologi) {
        this.kronologi = kronologi;
    }

    public String getBukti() {
        return bukti;
    }

    public void setBukti(String bukti) {
        this.bukti = bukti;
    }

    public String getStatusPengaduan() {
        return statusPengaduan;
    }

    public void setStatusPengaduan(String statusPengaduan) {
        this.statusPengaduan = statusPengaduan;
    }

    public String getTindakLanjut() {
        return tindakLanjut;
    }

    public void setTindakLanjut(String tindakLanjut) {
        this.tindakLanjut = tindakLanjut;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
