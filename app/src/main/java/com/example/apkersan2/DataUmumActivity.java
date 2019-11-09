package com.example.apkersan2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
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
import com.example.apkersan2.model.ResponseKekerasan;
import com.example.apkersan2.model.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DataUmumActivity extends AppCompatActivity {

    private Button BtNext;
    private EditText EtTiket, EtDeskripsiKekerasan;
    private Spinner  SpJenisKasus, SpBentukKekerasan;
    private RadioGroup  RgStatusPelapor;
    private RadioButton RbSaksi, RbKorban;
    private int selectedId;
    private String status;
    private Random myRandom = new Random();

    ProgressDialog loading;

    Context mContext;
    BaseApiService mApiservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_umum);

        BtNext              = (Button) findViewById(R.id.BtNextUmum);
        EtTiket             = (EditText) findViewById(R.id.EtTiketKorban);
        EtDeskripsiKekerasan= (EditText) findViewById(R.id.EtDeskripsiKekerasan);
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

                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        ButterKnife.bind(this);
        mContext = this;
        mApiservice = UtilsApi.getAPIService();

        initSpinnerKekerasan();

        SpBentukKekerasan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedKekerasan = parent.getItemAtPosition(position).toString();
//                int count = parent.getId();
//                Value selectedKekerasan = (Value) parent.getItemAtPosition(position);
//                Log.d("Nama", selectedKekerasan.getKekerasanNama());
//                String KekerasanNama = selectedKekerasan.getKekerasanNama();
//                EtDeskripsiKekerasan.setText(position);
                Toast.makeText(mContext, selectedKekerasan, Toast.LENGTH_SHORT).show();
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
                    List<Value> kekerasanItems = response.body().getData();
                    List<String> listSpinner    = new ArrayList<String>();
                    List<String> listDeskripsi  = new ArrayList<String>();
                    List<Integer> listID         = new ArrayList<Integer>();
                    for (int i = 0; i < kekerasanItems.size(); i++) {
                        listSpinner.add(kekerasanItems.get(i).getKekerasanNama());
                        //mau ambil ini
                        listDeskripsi.add(kekerasanItems.get(i).getKekerasanDeksripsi());
                        listID.add(kekerasanItems.get(i).getKekerasanId());
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


    //font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
