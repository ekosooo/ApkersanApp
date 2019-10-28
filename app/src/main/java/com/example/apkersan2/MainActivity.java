package com.example.apkersan2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.btm_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.action_home :
                        Toast.makeText(MainActivity.this, "Home clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_buat :
                        Toast.makeText(MainActivity.this, "Buat clicked", Toast.LENGTH_SHORT).show();
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
