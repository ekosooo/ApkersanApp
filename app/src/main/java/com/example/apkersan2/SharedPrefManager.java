package com.example.apkersan2;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    public static final String SP_APKERSAN_APP = "spApkersanApp";

    public static final String SP_ID        = "spID";
    public static final String SP_NAMA      = "spNama";
    public static final String SP_EMAIL     = "spEmail";
    public static final String SP_PASSWORD  = "spPassword";
    public static final String SP_ALAMAT    = "spAlamat";
    public static final String SP_TELEPON   = "spTelepon";

    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SP_APKERSAN_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

//    public Integer getSpId(){
//        return (Integer) sp.getInt(String.valueOf(SP_ID), 0);
//    }

    public String getSpId(){
        return sp.getString(SP_ID, "");
    }

    public String getSpNama(){
        return sp.getString(SP_NAMA, "");
    }

    public String getSpEmail(){
        return sp.getString(SP_EMAIL, "");
    }

    public String getSpPassword(){
        return sp.getString(SP_PASSWORD, "");
    }

    public String getSpAlamat(){
        return sp.getString(SP_ALAMAT, "");
    }

    public String getSpTelepon(){
        return sp.getString(SP_TELEPON, "");
    }

    public Boolean getSpSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }



}
