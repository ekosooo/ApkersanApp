package com.example.apkersan2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apkersan2.api.BaseApiService;
import com.example.apkersan2.api.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {

    EditText EtPassword, EtEmail;
    ImageView IvShow, IvHide;
    Button BtLogin;
    TextView TvDaftar;

    ProgressDialog loading;

    Context mContext;
    BaseApiService mApiService;

    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        BtLogin     = (Button) findViewById(R.id.BtLogin);
        EtEmail     = (EditText) findViewById(R.id.EtLogEmail);
        EtPassword  = (EditText) findViewById(R.id.EtPassword);
        IvShow      = (ImageView) findViewById(R.id.IvShow);
        IvHide      = (ImageView) findViewById(R.id.IvHide);
        TvDaftar    = (TextView) findViewById(R.id.TvDaftar);

        sharedPrefManager = new SharedPrefManager(this);

        if (sharedPrefManager.getSpSudahLogin()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        IvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IvShow.isClickable()){
                    EtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    IvHide.setVisibility(View.VISIBLE);
                    IvShow.setVisibility(View.INVISIBLE);
                }else{
                    EtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        IvHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IvHide.isClickable()){
                    EtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    IvHide.setVisibility(View.INVISIBLE);
                    IvShow.setVisibility(View.VISIBLE);
                }else{
                    EtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        TvDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        mContext = this;
        mApiService = UtilsApi.getAPIService();

        BtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (EtEmail.getText().toString().isEmpty() && EtPassword.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Masukkan alamat email dan password", Toast.LENGTH_SHORT).show();
                }else if (EtEmail.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Masukkan alamat email", Toast.LENGTH_SHORT).show();
                }else if (EtPassword.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Masukkan password", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (Patterns.EMAIL_ADDRESS.matcher(EtEmail.getText().toString().trim()).matches()){
                        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                        requestLogin();
                    }
                }

            }
        });

    }


    public void requestLogin(){
        mApiService.loginRequest(
                EtEmail.getText().toString(),
                EtPassword.getText().toString()
        ).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        Log.d("Hasil", String.valueOf(jsonObject));
                        if (jsonObject.getString("error").equals("false")){
                            Toast.makeText(mContext, "Berhasil Login", Toast.LENGTH_SHORT).show();

                            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);

                            String user_id = jsonObject.getJSONObject("data").getString("user_id");
                            String user_nama = jsonObject.getJSONObject("data").getString("user_nama");
                            String user_email = jsonObject.getJSONObject("data").getString("user_email");
                            String user_alamat = jsonObject.getJSONObject("data").getString("user_alamat");
                            String user_phone = jsonObject.getJSONObject("data").getString("user_phone");

                            //sharedPreferences
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_ID, user_id);
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, user_nama);
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_EMAIL, user_email);
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_ALAMAT, user_alamat);
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_TELEPON, user_phone);

                            startActivity(new Intent(mContext, MainActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            finish();
                        }else {
                            String error = jsonObject.getString("error");
                            Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("debug", "onFailure : ERROR > " + t.toString());
                loading.dismiss();
            }
        });
    }

    //font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
