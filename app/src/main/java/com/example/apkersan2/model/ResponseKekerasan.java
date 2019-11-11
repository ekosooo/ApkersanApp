package com.example.apkersan2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseKekerasan{

	@SerializedName("kekerasan_deksripsi")
	@Expose
	private String kekerasanDeksripsi;

	@SerializedName("data")
	@Expose
	private List<DataKekerasan> data = null;

	public List<DataKekerasan> getData() {
		return data;
	}

	public String getKekerasanDeksripsi() {
		return kekerasanDeksripsi;
	}

	public void setData(List<DataKekerasan> data) {
		this.data = data;
	}


//	private List<DataKekerasan> data;
//
//	public void setData(List<DataKekerasan> data){
//		this.data = data;
//	}
//
//	public List<DataKekerasan> getData(){
//		return data;
//	}
//
//	@Override
// 	public String toString(){
//		return
//			"ResponseKekerasan{" +
//			"data = '" + data + '\'' +
//			"}";
//		}
}