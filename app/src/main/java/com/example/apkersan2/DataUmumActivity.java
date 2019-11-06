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

import java.util.Random;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DataUmumActivity extends AppCompatActivity {

    private Button BtNext;
    private EditText EtTiket;
    private Spinner  SpJenisKasus, SpBentukKekerasan;
    private RadioGroup  RgStatusPelapor;
    private RadioButton RbSaksi, RbKorban;
    private int selectedId;
    private String status;
    private Random myRandom = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_umum);

        BtNext              = (Button) findViewById(R.id.BtNextUmum);
        EtTiket             = (EditText) findViewById(R.id.EtTiketKorban);
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

    }

    //font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
