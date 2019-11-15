package com.example.apkersan2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.apkersan2.api.BaseApiService;
import com.example.apkersan2.api.UtilsApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RegisterActivity extends AppCompatActivity {

    private EditText EtNama, EtEmail, EtPassword, EtAlamat, EtIDN, EtTlp;
    private RadioGroup RgJk;
    private RadioButton RbLaki, RbPerempuan;
    private Button BtReg;

    private int selectedId;
    private String JK;

    ProgressDialog loading;

    Context mContext;
    BaseApiService mApiService;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EtNama      = (EditText) findViewById(R.id.EtRegNama);
        EtEmail     = (EditText) findViewById(R.id.EtRegEmail);
        EtPassword  = (EditText) findViewById(R.id.EtRegPass);
        EtAlamat    = (EditText) findViewById(R.id.EtRegAlamat);
        EtIDN       = (EditText) findViewById(R.id.EtIND);
        EtTlp       = (EditText) findViewById(R.id.EtRegTlp);
        BtReg       = (Button) findViewById(R.id.BtRegister);
        RgJk        = (RadioGroup) findViewById(R.id.RgRegJk);
        RbLaki      = (RadioButton) findViewById(R.id.RbRegLaki);
        RbPerempuan = (RadioButton) findViewById(R.id.RbRegPerempuan);

        mContext = this;
        mApiService = UtilsApi.getAPIService();

        BtReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //validasi
                if (EtNama.getText().toString().isEmpty() && EtPassword.getText().toString().isEmpty() && EtAlamat.getText().toString().isEmpty() && EtEmail.getText().toString().isEmpty() && EtTlp.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Masukkan nama, email, password, alamat dan nomor hp", Toast.LENGTH_SHORT).show();
                }else if (EtNama.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Masukkan Nama", Toast.LENGTH_SHORT).show();
                }else if (EtPassword.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Masukkan Password", Toast.LENGTH_SHORT).show();
                }else if (EtAlamat.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Masukkan Alamat", Toast.LENGTH_SHORT).show();
                }else if (EtEmail.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Masukkan Email", Toast.LENGTH_SHORT).show();
                }else if (EtTlp.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Masukkan No Handphone", Toast.LENGTH_SHORT).show();
                }else if (EtTlp.getText().toString().length()<10 && EtTlp.getText().toString().length()>=13){
                    Toast.makeText(getApplicationContext(), "No Handphone Tidak Sesuai", Toast.LENGTH_SHORT).show();
                }else if (EtPassword.getText().toString().length()<6){
                    Toast.makeText(getApplicationContext(), "Password minimal 6 karakter", Toast.LENGTH_SHORT).show();
                }else if (!EtEmail.getText().toString().trim().matches(emailPattern)){
                    Toast.makeText(getApplicationContext(), "Email tidak sesuai", Toast.LENGTH_SHORT).show();
                }else{
                    if (Patterns.EMAIL_ADDRESS.matcher(EtEmail.getText().toString().trim()).matches()){
                        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                        requestRegister();
                    }
                }
            }
        });

    }


    private void requestRegister(){

        selectedId = RgJk.getCheckedRadioButtonId();
        if (selectedId == RbLaki.getId()){
            JK = RbLaki.getText().toString();
        }else if (selectedId == RbPerempuan.getId()){
            JK = RbPerempuan.getText().toString();
        }

        mApiService.registerRequest(
                EtNama.getText().toString(),
                EtEmail.getText().toString(),
                EtPassword.getText().toString(),
                JK,
                EtAlamat.getText().toString(),
                EtIDN.getText().toString()+EtTlp.getText().toString()
        ).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    Log.i("debug", "onResponse: BERHASIL");
                    loading.dismiss();
                    Toast.makeText(mContext, "Berhasil Registrasi", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(mContext, LoginActivity.class));
                }else {
                    Log.i("debug", "OnResponse : Gagal");
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
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
