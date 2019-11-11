package com.example.apkersan2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.apkersan2.api.BaseApiService;
import com.example.apkersan2.api.UtilsApi;
import com.example.apkersan2.model.DataKasus;
import com.example.apkersan2.model.DataKekerasan;
import com.example.apkersan2.model.ResponseKasus;
import com.example.apkersan2.model.ResponseKekerasan;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DataUmumActivity extends AppCompatActivity {

    private Button BtNext;
    private EditText EtTiket, EtDeskripsiKekerasan, EtDeskripsiKasus;
    private Spinner  SpJenisKasus, SpBentukKekerasan;
    private RadioGroup  RgStatusPelapor;
    private RadioButton RbSaksi, RbKorban;
    private int selectedId;
    private String SpKasusId, SpKekerasanId;
    private String status;
    private Random myRandom = new Random();

    ProgressDialog loading;

    Context mContext;
    BaseApiService mApiservice;

    List<String> listSpinner    = new ArrayList<String>();
    List<Integer> listId        = new ArrayList<Integer>();
    List<String> listDeskripsi  = new ArrayList<String>();

    List<String> listKasus          = new ArrayList<String>();
    List<String> listDeskripsiKasus = new ArrayList<String>();
    List<Integer> listIdKasus       = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_umum);

        BtNext              = (Button) findViewById(R.id.BtNextUmum);
        EtTiket             = (EditText) findViewById(R.id.EtTiketKorban);
        EtDeskripsiKekerasan= (EditText) findViewById(R.id.EtDeskripsiKekerasan);
        EtDeskripsiKasus    = (EditText) findViewById(R.id.EtDeskripsiKasus);
        SpJenisKasus        = (Spinner) findViewById(R.id.SpJenisKasus);
        SpBentukKekerasan   = (Spinner) findViewById(R.id.SpBentukKekerasan);
        RgStatusPelapor     = (RadioGroup) findViewById(R.id.RgStatusPelapor);
        RbSaksi             = (RadioButton) findViewById(R.id.RbSaksi);
        RbKorban            = (RadioButton) findViewById(R.id.RbKorban);

        EtTiket.setText("TK-" + String.valueOf(myRandom.nextInt(1000000)));



        BtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataUmumActivity.this, DataKorbanActivity.class);

                selectedId = RgStatusPelapor.getCheckedRadioButtonId();
                if (selectedId == RbSaksi.getId()){
                    status = RbSaksi.getText().toString();
                }else if (selectedId == RbKorban.getId()){
                    status = RbKorban.getText().toString();
                }

                //kirim put extra
                intent.putExtra("tiket", EtTiket.getText().toString());
                intent.putExtra("status", status);
                intent.putExtra("jenis", SpJenisKasus.getSelectedItem().toString());
                intent.putExtra("bentuk", SpBentukKekerasan.getSelectedItem().toString());
                intent.putExtra("kekerasanid", SpKekerasanId);
                intent.putExtra("kasusid", SpKasusId);

                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        ButterKnife.bind(this);
        mContext = this;
        mApiservice = UtilsApi.getAPIService();

        initSpinnerKekerasan();
        initSpinnerKasus();

        SpBentukKekerasan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String KekerasanDeskripsi     = listDeskripsi.get(position);
                SpKekerasanId                 = listId.get(position).toString();
                EtDeskripsiKekerasan.setText(KekerasanDeskripsi);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpJenisKasus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String KasusDeskripsi         = listDeskripsiKasus.get(position);
                SpKasusId                     = listIdKasus.get(position).toString();

                EtDeskripsiKasus.setText(KasusDeskripsi);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void initSpinnerKekerasan(){
        loading = ProgressDialog.show(mContext, null, "harap tunggu..", true, false);

        mApiservice.getKekerasan().enqueue(new Callback<ResponseKekerasan>() {
            @Override
            public void onResponse(Call<ResponseKekerasan> call, Response<ResponseKekerasan> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    List<DataKekerasan> kekerasanItems  = response.body().getData();
                    for (int i = 0; i < kekerasanItems.size(); i++) {
                        listSpinner.add(kekerasanItems.get(i).getKekerasanNama());
                        Integer KekerasanId = kekerasanItems.get(i).getKekerasanId();
                        listId.add(KekerasanId);
                        String Deskripsi    = kekerasanItems.get(i).getKekerasanDeksripsi();
                        listDeskripsi.add(Deskripsi);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    SpBentukKekerasan.setAdapter(adapter);
                } else {
                    loading.dismiss();
                    Toast.makeText(mContext, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKekerasan> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initSpinnerKasus(){

        mApiservice.getKasus().enqueue(new Callback<ResponseKasus>() {
            @Override
            public void onResponse(Call<ResponseKasus> call, Response<ResponseKasus> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    List<DataKasus> KasusItems = response.body().getData();
                    for (int i = 0; i < KasusItems.size(); i++){
                        listKasus.add(KasusItems.get(i).getKasusNama());
                        Integer KasusId = KasusItems.get(i).getKasusId();
                        listIdKasus.add(KasusId);
                        String DekripsiKasus     = KasusItems.get(i).getKasusDeskripsi();
                        listDeskripsiKasus.add(DekripsiKasus);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listKasus);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    SpJenisKasus.setAdapter(adapter);
                } else{
                    loading.dismiss();
                    Toast.makeText(mContext, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKasus> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
