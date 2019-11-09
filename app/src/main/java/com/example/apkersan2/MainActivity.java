package com.example.apkersan2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Button BtBuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        BtBuat = (Button) findViewById(R.id.BtnBuat);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.btm_nav);

//        BtBuat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, BuatActivity.class);
//                startActivity(intent);
//            }
//        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Intent in;
                switch (item.getItemId()){
                    case R.id.action_home :
                        in = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.action_buat :
//                        in = new Intent(getBaseContext(), BuatActivity.class);
//                        startActivity(in);
//                        overridePendingTransition(0, 0);
                        break;
                    case R.id.action_bantuan:
                        Toast.makeText(MainActivity.this, "Bantuan clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_profil:
                        Toast.makeText(MainActivity.this, "Profil clicked", Toast.LENGTH_SHORT).show();
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
