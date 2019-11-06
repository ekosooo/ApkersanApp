package com.example.apkersan2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class KonfirmasiActivity extends AppCompatActivity {

    private TextView TvTiket, TvStatusPelapor, TvJenisKasus, TvBentukKekerasan, TvNama, TvJenisKelamin, TvDisabilitas,
            TvUsia, TvPendidikan, TvBekerja, TvStatusKawin, TvAlamat, TvKronologi, TvTempat, TvWaktu;
    private String tiketExtra, statusExtra, jenisExtra, bentukExtra, namaExtra, jeniskelaminExtra, disabilitasExtra, usiaExtra,
            pendidikanExtra, bekerjaExtra, statuskawinExtra, alamatExtra, kronologiExtra, tempatExtra, waktuExtra;
    private Double latExtra, lngExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi);

        TvTiket             = (TextView) findViewById(R.id.TvNoTiket);
        TvStatusPelapor     = (TextView) findViewById(R.id.TvStatusPelapor);
        TvJenisKasus        = (TextView) findViewById(R.id.TvJenisKasus);
        TvBentukKekerasan   = (TextView) findViewById(R.id.TvBentukKekerasan);
        TvNama              = (TextView) findViewById(R.id.TvNama);
        TvJenisKelamin      = (TextView) findViewById(R.id.TvJenisKelamin);
        TvDisabilitas       = (TextView) findViewById(R.id.TvDisabilitas);
        TvUsia              = (TextView) findViewById(R.id.TvUsia);
        TvPendidikan        = (TextView) findViewById(R.id.TvPendidikan);
        TvBekerja           = (TextView) findViewById(R.id.TvBekerja);
        TvStatusKawin       = (TextView) findViewById(R.id.TvStatusKawin);
        TvAlamat            = (TextView) findViewById(R.id.TvAlamat);
        TvKronologi         = (TextView) findViewById(R.id.TvKronologi);
        TvTempat            = (TextView) findViewById(R.id.TvTempat);
        TvWaktu             = (TextView) findViewById(R.id.TvWaktu);


        Bundle extra = getIntent().getExtras();
        if (extra != null){

            tiketExtra          = extra.getString("tiket");
            statusExtra         = extra.getString("status");
            jenisExtra          = extra.getString("jenis");
            bentukExtra         = extra.getString("bentuk");

            namaExtra           = extra.getString("nama");
            jeniskelaminExtra   = extra.getString("jeniskelamin");
            disabilitasExtra    = extra.getString("disabilitas");
            usiaExtra           = extra.getString("usia");
            pendidikanExtra     = extra.getString("pendidikan");
            bekerjaExtra        = extra.getString("bekerja");
            statuskawinExtra    = extra.getString("statuskawin");

            alamatExtra         = extra.getString("alamat");
            kronologiExtra      = extra.getString("kronologi");
            tempatExtra         = extra.getString("tempat");
            waktuExtra          = extra.getString("waktu");
            latExtra            = extra.getDouble("lat", 0);
            lngExtra            = extra.getDouble("long", 0);
        }

        TvTiket.setText(tiketExtra);
        TvStatusPelapor.setText(statusExtra);
        TvJenisKasus.setText(jenisExtra);
        TvBentukKekerasan.setText(bentukExtra);
        TvNama.setText(namaExtra);
        TvJenisKelamin.setText(jeniskelaminExtra);
        TvDisabilitas.setText(disabilitasExtra);
        TvUsia.setText(usiaExtra);
        TvPendidikan.setText(pendidikanExtra);
        TvBekerja.setText(bekerjaExtra);
        TvStatusKawin.setText(statuskawinExtra);
        TvAlamat.setText(alamatExtra);
        TvKronologi.setText(kronologiExtra);
        TvTempat.setText(tempatExtra);
        TvWaktu.setText(waktuExtra);

    }

    //font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
