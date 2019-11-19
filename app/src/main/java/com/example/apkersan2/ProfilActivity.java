package com.example.apkersan2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apkersan2.api.BaseApiService;
import com.example.apkersan2.api.UtilsApi;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProfilActivity extends AppCompatActivity {

    private TextView TvKembali;
    private EditText EtNama, EtEmail, EtAlamat, EtTlp;
    private Button BtLogout, BtUpdate;

    private String nama, email, alamat, tlp, user_id;

    SharedPrefManager sharedPrefManager;
    SweetAlertDialog pDialog;
    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        TvKembali   = (TextView) findViewById(R.id.TvProfilKembali);
        EtNama      = (EditText) findViewById(R.id.EtProfNama);
        EtEmail     = (EditText) findViewById(R.id.EtProfEmail);
        EtAlamat    = (EditText) findViewById(R.id.EtProfAlamat);
        EtTlp       = (EditText) findViewById(R.id.EtProfTlp);

        BtLogout    = (Button) findViewById(R.id.BtLogout);
        BtUpdate    = (Button) findViewById(R.id.BtUpdate);

        TvKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        BtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                startActivity(new Intent(ProfilActivity.this, LoginActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });

        BtUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog = new SweetAlertDialog(ProfilActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                updateProfile();
            }
        });

        mContext = this;
        mApiService = UtilsApi.getAPIService();

        sharedPrefManager = new SharedPrefManager(ProfilActivity.this.getApplicationContext());
        user_id     = sharedPrefManager.getSpId();
        nama        = sharedPrefManager.getSpNama();
        email       = sharedPrefManager.getSpEmail();
        alamat      = sharedPrefManager.getSpAlamat();
        tlp         = sharedPrefManager.getSpTelepon();

        EtNama.setText(nama);
        EtAlamat.setText(alamat);
        EtEmail.setText(email);
        EtTlp.setText(tlp);
    }

    private void updateProfile(){
        mApiService.updateProfileRequest(
             user_id,
             EtNama.getText().toString(),
             EtEmail.getText().toString(),
             EtAlamat.getText().toString(),
             EtTlp.getText().toString()
        ).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    pDialog.dismiss();
                    new SweetAlertDialog(ProfilActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Sukses")
                            .setContentText("Profile berhasil diupdate," +
                                    "Silahkan login kembali untuk melihat data yang telah terupdate.")
                            .setConfirmText("OK")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                    startActivity(new Intent(ProfilActivity.this, MainActivity.class)
                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                    finish();
                                }
                            })
                            .show();
                }else{
                    pDialog.dismiss();
                    new SweetAlertDialog(ProfilActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Gagal update profile!")
                            .show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
