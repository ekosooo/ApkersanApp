package com.example.apkersan2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseKekerasan {

    @SerializedName("error")
    private String error;
    @SerializedName("data")
    @Expose
    private List<Value> data = null;

    public List<Value> getData() {
        return data;
    }

    public String getEror(){
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setData(List<Value> data) {
        this.data = data;
    }
}

