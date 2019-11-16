package com.example.apkersan2.api;

import com.example.apkersan2.SharedPrefManager;
import com.example.apkersan2.model.ResponseKasus;
import com.example.apkersan2.model.ResponseKekerasan;
import com.example.apkersan2.model.ResponsePengaduan;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BaseApiService {

    @GET("kekerasan")
    Call<ResponseKekerasan> getKekerasan();

    @GET("kasus")
    Call<ResponseKasus> getKasus();

    @GET("pengaduan/{id}")
    Call<ResponsePengaduan> getPengaduan(
            @Path("id") String user_id
    );

    @FormUrlEncoded
    @POST("register/user")
    Call<ResponseBody> registerRequest(
            @Field("user_nama") String user_nama,
            @Field("user_email") String user_email,
            @Field("password") String password,
            @Field("user_jk") String user_jk,
            @Field("user_alamat") String user_alamat,
            @Field("user_phone") String user_phone);

    @FormUrlEncoded
    @POST("login/user")
    Call<ResponseBody> loginRequest(
            @Field("user_email") String user_email,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("pengaduan")
    Call<ResponseBody> pengaduanPost(
            @Field("user_id") String user_id,
            @Field("kasus_id") String kasus_id,
            @Field("kekerasan_id") String kekerasan_id,
            @Field("ticket_number") String ticket_number,
            @Field("status_pelapor") String status_pelapor,
            @Field("disabilitas") String disabilitas,
            @Field("korban_nama") String korban_nama,
            @Field("korban_jk") String korban_jk,
            @Field("korban_usia") String korban_usia,
            @Field("korban_pendidikan") String korban_pendidikan,
            @Field("korban_bekerja") String korban_bekerja,
            @Field("korban_statuskawin") String korban_statuskawin,
            @Field("alamat_kejadian") String alamat_kejadian,
            @Field("waktu_kejadian") String waktu_kejadian,
            @Field("tempat_kejadian") String tempat_kejadian,
            @Field("kronologi") String kronologi,
            @Field("bukti") String bukti,
            @Field("lat") Double lat,
            @Field("lng") Double lng,
            @Field("status_pengaduan") String status_pengaduan);
}
