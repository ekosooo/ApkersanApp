package com.example.apkersan2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Button BtBuat, BtLihat, BtBantuan, BtProfil;
    private TextView TvNama;

    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        BtBuat                  = (Button) findViewById(R.id.BtnBuat);
        BtLihat                 = (Button) findViewById(R.id.BtLihat);
        BtBantuan               = (Button) findViewById(R.id.BtBantuan);
        BtProfil                = (Button) findViewById(R.id.BtProfil);

        bottomNavigationView    = (BottomNavigationView) findViewById(R.id.btm_nav);
        TvNama                  = (TextView) findViewById(R.id.TvNamaUser);

        BtBuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DataUmumActivity.class);
                startActivity(intent);
            }
        });

        BtLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LihatActivity.class);
                startActivity(intent);
            }
        });

        BtBantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BantuanActivity.class);
                startActivity(intent);
            }
        });

        BtProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfilActivity.class);
                startActivity(intent);
            }
        });

        sharedPrefManager = new SharedPrefManager(MainActivity.this.getApplicationContext());
        String nama = sharedPrefManager.getSpNama();

        TvNama.setText(nama);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Intent intent;
                switch (item.getItemId()){
                    case R.id.action_home :

                        break;
                    case R.id.action_buat :
                        intent = new Intent(MainActivity.this, DataUmumActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_bantuan:
                        intent = new Intent(MainActivity.this, BantuanActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_profil:
                        intent = new Intent(MainActivity.this, ProfilActivity.class);
                        startActivity(intent);
                        break;
                }

                return true;
            }
        });


        super.onCreate(savedInstanceState);
    }

    //font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
