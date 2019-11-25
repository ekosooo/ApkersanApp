package com.example.apkersan2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailActivity extends AppCompatActivity {

    private TextView TvTiket, TvKorban, TvKasus, TvBentuk, TvWaktu, TvAlamat, TvKronologi, TvStatus, TvTindak;
    private ImageButton BtBack;
    private ImageView IvBukti;

    private String tiketExtra, korbanExtra, kasusExtra, bentukExtra, waktuExtra, alamatExtra, kronologiExtra, statusExtra, tindakExtra, buktiExtra;

    private String path = "http://192.168.1.6:8000/bukti/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TvTiket      = (TextView) findViewById(R.id.TvDetTiket);
        TvKorban     = (TextView) findViewById(R.id.TvDetNama);
        TvKasus      = (TextView) findViewById(R.id.TvDetKasus);
        TvBentuk     = (TextView) findViewById(R.id.TvDetBentuk);
        TvWaktu      = (TextView) findViewById(R.id.TvDetWaktu);
        TvAlamat     = (TextView) findViewById(R.id.TvDetAlamat);
        TvKronologi  = (TextView) findViewById(R.id.TvDetKronologi);
        TvStatus     = (TextView) findViewById(R.id.TvDetStatus);
        TvTindak     = (TextView) findViewById(R.id.TvDetTindak);
        BtBack       = (ImageButton) findViewById(R.id.BtBackDetail);
        IvBukti      = (ImageView) findViewById(R.id.IvDetBukti);

        BtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle extra = getIntent().getExtras();
        if (extra != null){

            tiketExtra      = extra.getString("tiket");
            korbanExtra     = extra.getString("korban");
            kasusExtra      = extra.getString("kasus");
            bentukExtra     = extra.getString("kekerasan");
            waktuExtra      = extra.getString("waktu");
            alamatExtra     = extra.getString("alamat");
            kronologiExtra  = extra.getString("kronologi");
            statusExtra     = extra.getString("status");
            tindakExtra     = extra.getString("tindak");
            buktiExtra      = extra.getString("bukti");

        }

        TvTiket.setText(tiketExtra);
        TvKorban.setText(korbanExtra);
        TvKasus.setText(kasusExtra);
        TvBentuk.setText(bentukExtra);
        TvWaktu.setText(waktuExtra);
        TvAlamat.setText(alamatExtra);
        TvKronologi.setText(kronologiExtra);
        TvStatus.setText(statusExtra);
        TvTindak.setText(tindakExtra);
        Picasso.get().load(path + buktiExtra).into(IvBukti);

    }

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
