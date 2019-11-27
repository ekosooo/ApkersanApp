package com.example.apkersan2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BeritaActivity extends AppCompatActivity {

    private TextView TvJudul, TvPenulis, TvBuat, TvKonten;
    private ImageView IvBerita;
    private ImageButton BtBack;
    private String judulExtra, kontenExtra, penulisExtra, gambarExtra, created_atExtra;

    String path = "http://192.168.1.6:8000/berita/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita);

        TvJudul         = (TextView) findViewById(R.id.TvBerJudul);
        TvPenulis       = (TextView) findViewById(R.id.TvPenulis);
        TvBuat          = (TextView) findViewById(R.id.TvBuat);
        TvKonten        = (TextView) findViewById(R.id.TvKonten);
        IvBerita        = (ImageView) findViewById(R.id.IvBerGambar);
        BtBack          = (ImageButton) findViewById(R.id.BtBackBerita);

        BtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle extra = getIntent().getExtras();
        if (extra != null){

            judulExtra      = extra.getString("judul");
            penulisExtra    = extra.getString("penulis");
            kontenExtra     = extra.getString("konten");
            created_atExtra = extra.getString("created_at");
            gambarExtra     = extra.getString("gambar");

        }
        TvJudul.setText(judulExtra);
        TvBuat.setText(created_atExtra);
        TvPenulis.setText(penulisExtra);
        TvKonten.setText(kontenExtra);
        Picasso.get().load(path + gambarExtra).into(IvBerita);
    }

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
