package com.example.apkersan2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DataKorbanActivity extends AppCompatActivity {

    private Button BtBack, BtNext;
    private String tiketExtra, statusExtra, jenisExtra, bentukExtra, KekerasanIdExtra, KasusIdExtra;
    private EditText EtNama, EtUsia;
    private RadioGroup RgJenisKelamin, RgDisabilitas, RgBekerja;
    private RadioButton RbLaki, RbPerempuan, RbYaDisabilitas, RbTidakDisabilitas, RbYaBekerja, RbTidakBekerja;
    private Spinner SpPendidikan, SpStatusKawin;
    private int selectJenisKelamin, selectDisabilitas, selectBekerja;

    private String JenisKelamin, Bekerja, Disablitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_korban);

        BtBack              = (Button) findViewById(R.id.BtBackKorban);
        BtNext              = (Button) findViewById(R.id.BtNextKorban);

        EtNama              = (EditText) findViewById(R.id.EtNama);
        EtUsia              = (EditText) findViewById(R.id.EtUsia);
        RgJenisKelamin      = (RadioGroup) findViewById(R.id.RgJenisKelamin);
        RgDisabilitas       = (RadioGroup) findViewById(R.id.RgDisabilitas);
        RgBekerja           = (RadioGroup) findViewById(R.id.RgBekerja);
        RbLaki              = (RadioButton) findViewById(R.id.RbLaki);
        RbPerempuan         = (RadioButton) findViewById(R.id.RbPerempuan);
        RbYaDisabilitas     = (RadioButton) findViewById(R.id.RbYaDisabilitas);
        RbTidakDisabilitas  = (RadioButton) findViewById(R.id.RbTidakDisabilitas);
        RbYaBekerja         = (RadioButton) findViewById(R.id.RbYaBekerja);
        RbTidakBekerja      = (RadioButton) findViewById(R.id.RbTidakBekerja);
        SpPendidikan        = (Spinner) findViewById(R.id.SpPendidikan);
        SpStatusKawin       = (Spinner) findViewById(R.id.SpStatusKawin);

        Bundle extra = getIntent().getExtras();
        if (extra != null){
            tiketExtra      = extra.getString("tiket");
            statusExtra     = extra.getString("status");
            jenisExtra      = extra.getString("jenis");
            bentukExtra     = extra.getString("bentuk");
            KasusIdExtra    = extra.getString("kekerasanid");
            KekerasanIdExtra= extra.getString("kasusid");
        }

        selectJenisKelamin  = RgJenisKelamin.getCheckedRadioButtonId();
        selectBekerja       = RgBekerja.getCheckedRadioButtonId();
        selectDisabilitas   = RgDisabilitas.getCheckedRadioButtonId();

        if (selectJenisKelamin == RbLaki.getId()){
            JenisKelamin = RbLaki.getText().toString();
        }else if(selectJenisKelamin == RbPerempuan.getId()){
            JenisKelamin = RbPerempuan.getText().toString();
        }

        if(selectBekerja == RbYaBekerja.getId()){
            Bekerja = RbYaBekerja.getText().toString();
        }else if (selectBekerja == RbTidakBekerja.getId()){
            Bekerja = RbTidakBekerja.getText().toString();
        }

        if (selectDisabilitas == RbYaDisabilitas.getId()){
            Disablitas = RbYaDisabilitas.getText().toString();
        }else if (selectDisabilitas == RbTidakDisabilitas.getId()){
            Disablitas = RbTidakDisabilitas.getText().toString();
        }

        BtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataKorbanActivity.this, DataUmumActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        BtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataKorbanActivity.this, MapActivity.class);

                //kirim extra
                intent.putExtra("tiket", tiketExtra);
                intent.putExtra("status", statusExtra);
                intent.putExtra("jenis", jenisExtra);
                intent.putExtra("bentuk", bentukExtra);
                intent.putExtra("kekerasanid", KekerasanIdExtra);
                intent.putExtra("kasusid", KasusIdExtra);

                intent.putExtra("nama", EtNama.getText().toString());
                intent.putExtra("jeniskelamin", JenisKelamin);
                intent.putExtra("disabilitas", Disablitas);
                intent.putExtra("usia", EtUsia.getText().toString());
                intent.putExtra("pendidikan", SpPendidikan.getSelectedItem().toString());
                intent.putExtra("bekerja", Bekerja);
                intent.putExtra("statuskawin", SpStatusKawin.getSelectedItem().toString());

                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

    }


    //font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}

