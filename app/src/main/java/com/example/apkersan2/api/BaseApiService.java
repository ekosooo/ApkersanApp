package com.example.apkersan2.api;

import com.example.apkersan2.model.ResponseKasus;
import com.example.apkersan2.model.ResponseKekerasan;
import com.example.apkersan2.model.ResponseRegister;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BaseApiService {

    @GET("kekerasan")
    Call<ResponseKekerasan> getKekerasan();

    @GET("kasus")
    Call<ResponseKasus> getKasus();

    @FormUrlEncoded
    @POST("register/user")
    Call<ResponseRegister> registerRequest(
            @Field("user_nama") String user_nama,
            @Field("user_email") String user_email,
            @Field("password") String password,
            @Field("user_jk") String user_jk,
            @Field("user_alamat") String user_alamat,
            @Field("user_phone") String user_phone);
}
