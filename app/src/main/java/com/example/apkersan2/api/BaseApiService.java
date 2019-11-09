package com.example.apkersan2.api;

import com.example.apkersan2.model.ResponseKekerasan;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface BaseApiService {

    @GET("kekerasan")
    Call<ResponseKekerasan> getKekerasan();
}
