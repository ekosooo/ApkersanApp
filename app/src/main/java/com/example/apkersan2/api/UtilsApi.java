package com.example.apkersan2.api;


public class UtilsApi {

    public static final String BASE_URL_API = "http://192.168.1.3:8000/api/";

    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }

}
