package com.example.apkersan2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseKasus {

    @SerializedName("data")
    @Expose
    private List<DataKasus> data = null;

    public List<DataKasus> getData() {
        return data;
    }

    public void setData(List<DataKasus> data) {
        this.data = data;
    }

}
