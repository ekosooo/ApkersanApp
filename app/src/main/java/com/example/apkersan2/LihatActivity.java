package com.example.apkersan2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apkersan2.adapter.PengaduanAdapter;
import com.example.apkersan2.api.BaseApiService;
import com.example.apkersan2.api.UtilsApi;
import com.example.apkersan2.model.DataPengaduan;
import com.example.apkersan2.model.ResponsePengaduan;
import com.facebook.shimmer.ShimmerFrameLayout;

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
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout shimmerFrameLayout;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.TvAduan)
    TextView TvAduan;
    @BindView(R.id.TvDiterima)
    TextView TvDiterima;
    @BindView(R.id.TvDitolak)
    TextView TvDitolak;
    @BindView(R.id.IvNoData)
    ImageView IvNoData;
    @BindView(R.id.TvTidakAdaData)
    TextView TvTidakAdaData;


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

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        loadDataPengaduan();
                    }
                }, 2000);
            }
        });
    }

    private void loadDataPengaduan() {
        shimmerFrameLayout.startShimmer();
        mApiService.getPengaduan(
               user_id
        ).enqueue(new Callback<ResponsePengaduan>() {
            @Override
            public void onResponse(Call<ResponsePengaduan> call, Response<ResponsePengaduan> response) {
                Log.d("Data pengaduan", String.valueOf(response));
                if (response.isSuccessful()){
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);

                    datapengaduan   = response.body().getData();

                    String aduan    = response.body().getAduan();
                    TvAduan.setText(aduan);
                    String diterima = response.body().getDiterima();
                    TvDiterima.setText(diterima);
                    String ditolak  = response.body().getDitolak();
                    TvDitolak.setText(ditolak);

                    if (aduan.equals("0")){
                        IvNoData.setVisibility(View.VISIBLE);
                        TvTidakAdaData.setVisibility(View.VISIBLE);
                    }else{
                        rvpengaduan.setVisibility(View.VISIBLE);
                    }

                    rvpengaduan.setAdapter(new PengaduanAdapter(mContext, datapengaduan));
                    pengaduanAdapter.notifyDataSetChanged();
                }else{
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);
                    Toast.makeText(mContext, "Gagal mengambil data pengaduan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePengaduan> call, Throwable t) {
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
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
