package com.example.apkersan2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.example.apkersan2.adapter.BeritaAdapter;
import com.example.apkersan2.api.BaseApiService;
import com.example.apkersan2.api.UtilsApi;
import com.example.apkersan2.model.DataBerita;
import com.example.apkersan2.model.ResponseBerita;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Button BtBuat, BtLihat, BtBantuan, BtProfil;
    private TextView TvNama;

    SharedPrefManager sharedPrefManager;
    private List<DataBerita> databerita =  new ArrayList<>();
    private RecyclerView.Adapter beritaAdapter;
    BaseApiService mApiService;
    Context mContext;

    @BindView(R.id.rvBerita)
    RecyclerView rvberita;
    @BindView(R.id.shimmer_berita)
    ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BtBuat                  = (Button) findViewById(R.id.BtnBuat);
        BtLihat                 = (Button) findViewById(R.id.BtLihat);
        BtBantuan               = (Button) findViewById(R.id.BtBantuan);
        BtProfil                = (Button) findViewById(R.id.BtProfil);

        bottomNavigationView    = (BottomNavigationView) findViewById(R.id.btm_nav);
        TvNama                  = (TextView) findViewById(R.id.TvNamaUser);

        ButterKnife.bind(this);
        beritaAdapter   = new BeritaAdapter(mContext, databerita);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvberita.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvberita.setItemAnimator(new DefaultItemAnimator());
        rvberita.setAdapter(beritaAdapter);

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

        String CaptilazeNama = capitalize(nama);
        TvNama.setText(CaptilazeNama);

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

        mContext = this;
        mApiService = UtilsApi.getAPIService();
        loadDataBerita();
    }

    public void loadDataBerita(){
        mApiService.getBerita()
                .enqueue(new Callback<ResponseBerita>() {
                    @Override
                    public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                        if (response.isSuccessful()){
                            shimmerFrameLayout.stopShimmer();
                            shimmerFrameLayout.setVisibility(View.GONE);
                            rvberita.setVisibility(View.VISIBLE);
                            databerita = response.body().getData();
                            rvberita.setAdapter(new BeritaAdapter(mContext, databerita));
                            beritaAdapter.notifyDataSetChanged();
                        }else{
                            Toast.makeText(mContext, "Gagal mengambil data berita", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBerita> call, Throwable t) {
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //fungsi Capitalize Each Word
    private String capitalize(String capString){
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()){
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }

        return capMatcher.appendTail(capBuffer).toString();
    }

    //font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
