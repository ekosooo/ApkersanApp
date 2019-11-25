package com.example.apkersan2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataKekerasan {

	@SerializedName("kekerasan_id")
	@Expose
	private Integer kekerasanId;
	@SerializedName("kekerasan_nama")
	@Expose
	private String kekerasanNama;
	@SerializedName("kekerasan_deksripsi")
	@Expose
	private String kekerasanDeksripsi;

	public Integer getKekerasanId() {
		return kekerasanId;
	}

	public void setKekerasanId(Integer kekerasanId) {
		this.kekerasanId = kekerasanId;
	}

	public String getKekerasanNama() {
		return kekerasanNama;
	}

	public void setKekerasanNama(String kekerasanNama) {
		this.kekerasanNama = kekerasanNama;
	}

	public String getKekerasanDeksripsi() {
		return kekerasanDeksripsi;
	}

	public void setKekerasanDeksripsi(String kekerasanDeksripsi) {
		this.kekerasanDeksripsi = kekerasanDeksripsi;
	}

//	private String kekerasanNama;
//	private String kekerasanDeksripsi;
//	private int kekerasanId;
//
//	public void setKekerasanNama(String kekerasanNama){
//		this.kekerasanNama = kekerasanNama;
//	}
//
//	public String getKekerasanNama(){
//		return kekerasanNama;
//	}
//
//	public void setKekerasanDeksripsi(String kekerasanDeksripsi){
//		this.kekerasanDeksripsi = kekerasanDeksripsi;
//	}
//
//	public String getKekerasanDeksripsi(){
//		return kekerasanDeksripsi;
//	}
//
//	public void setKekerasanId(int kekerasanId){
//		this.kekerasanId = kekerasanId;
//	}
//
//	public int getKekerasanId(){
//		return kekerasanId;
//	}
//
//	@Override
// 	public String toString(){
//		return
//			"DataKekerasan{" +
//			"kekerasan_nama = '" + kekerasanNama + '\'' +
//			",kekerasan_deksripsi = '" + kekerasanDeksripsi + '\'' +
//			",kekerasan_id = '" + kekerasanId + '\'' +
//			"}";
//		}
}
