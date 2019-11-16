package com.example.apkersan2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.apkersan2.adapter.PengaduanAdapter;
import com.example.apkersan2.api.BaseApiService;
import com.example.apkersan2.api.UtilsApi;
import com.example.apkersan2.model.DataPengaduan;
import com.example.apkersan2.model.ResponsePengaduan;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LihatActivity extends AppCompatActivity {

    private List<DataPengaduan> datapengaduan = new ArrayList<>();
    private RecyclerView.Adapter pengaduanAdapter;
    BaseApiService mApiService;
    Context mContext;
    ProgressDialog progressDialog;

    SharedPrefManager sharedPrefManager;
    String user_id;

    @BindView(R.id.rvPengaduan) RecyclerView rvpengaduan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat);

        ButterKnife.bind(this);
        pengaduanAdapter = new PengaduanAdapter(mContext, datapengaduan);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvpengaduan.setLayoutManager(mLayoutManager);
        rvpengaduan.setItemAnimator(new DefaultItemAnimator());
        rvpengaduan.setAdapter(pengaduanAdapter);

        sharedPrefManager = new SharedPrefManager(LihatActivity.this.getApplicationContext());
        user_id = sharedPrefManager.getSpId();


        mContext = this;
        mApiService = UtilsApi.getAPIService();
        loadDataPengaduan();
    }

    private void loadDataPengaduan() {
        progressDialog = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);

        mApiService.getPengaduan(
                user_id
        ).enqueue(new Callback<ResponsePengaduan>() {
            @Override
            public void onResponse(Call<ResponsePengaduan> call, Response<ResponsePengaduan> response) {
                Log.d("Data pengaduan", String.valueOf(response));
                if (response.isSuccessful()){
                    progressDialog.dismiss();
                    datapengaduan = response.body().getData();
                    rvpengaduan.setAdapter(new PengaduanAdapter(mContext, datapengaduan));
                    pengaduanAdapter.notifyDataSetChanged();
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(mContext, "Gagal mengambil data pengaduan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePengaduan> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
