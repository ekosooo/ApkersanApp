package com.example.apkersan2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apkersan2.R;
import com.example.apkersan2.model.DataPengaduan;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PengaduanAdapter extends RecyclerView.Adapter<PengaduanAdapter.PengaduanHolder> {

    List<DataPengaduan> PengaduanList;
    Context context;
    String ticket_number, kasus_nama, korban_nama, status_pengaduan;

    public PengaduanAdapter(Context context, List<DataPengaduan> listPengaduan){
        this.context = context;
        this.PengaduanList = listPengaduan;
    }

    @NonNull
    @Override
    public PengaduanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pengaduan, parent, false);
        final PengaduanHolder pengaduanHolder = new PengaduanHolder(view);
        return pengaduanHolder;
    }

    @Override
    public void onBindViewHolder(PengaduanHolder holder, int position){
        final DataPengaduan pengaduanItem = PengaduanList.get(position);
        holder.TvNoTiket.setText(pengaduanItem.getTicketNumber());
        holder.TvNamaKorban.setText(pengaduanItem.getKorbanNama());
        holder.TvStatusPengaduan.setText(pengaduanItem.getStatusPengaduan());
        holder.TvKasus.setText(pengaduanItem.getKasusNama());
    }

    @Override
    public int getItemCount(){
        return PengaduanList.size();
    }

    public class PengaduanHolder extends RecyclerView.ViewHolder{

        public TextView TvNoTiket, TvNamaKorban, TvStatusPengaduan, TvKasus;

        public PengaduanHolder(@NonNull View itemView){
            super(itemView);
            TvNoTiket           = itemView.findViewById(R.id.GetTvTiket);
            TvNamaKorban        = itemView.findViewById(R.id.GetTvNamaKorban);
            TvStatusPengaduan   = itemView.findViewById(R.id.GetTvStatusPengaduan);
            TvKasus             = itemView.findViewById(R.id.GetTvKasusNama);

            ButterKnife.bind(this, itemView);
        }
    }
}
