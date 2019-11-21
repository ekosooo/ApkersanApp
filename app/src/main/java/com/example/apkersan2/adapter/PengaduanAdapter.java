package com.example.apkersan2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apkersan2.DetailActivity;
import com.example.apkersan2.R;
import com.example.apkersan2.model.DataPengaduan;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PengaduanAdapter extends RecyclerView.Adapter<PengaduanAdapter.PengaduanHolder> {

    List<DataPengaduan> PengaduanList;
    Context context;
    String ticket_number, kasus_nama, bentuk_kekerasan, korban_nama, waktu_kejadian, status_pengaduan, alamat_kejadian, kronologi, tindak_lanjut, bukti;

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

        holder.ItemClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);

                ticket_number       = pengaduanItem.getTicketNumber();
                korban_nama         = pengaduanItem.getKorbanNama();
                kasus_nama          = pengaduanItem.getKasusNama();
                bentuk_kekerasan    = pengaduanItem.getKekerasanNama();
                waktu_kejadian      = pengaduanItem.getWaktuKejadian();
                alamat_kejadian     = pengaduanItem.getAlamatKejadian();
                kronologi           = pengaduanItem.getKronologi();
                status_pengaduan    = pengaduanItem.getStatusPengaduan();
                tindak_lanjut       = pengaduanItem.getTindakLanjut();
                bukti               = pengaduanItem.getBukti();



                intent.putExtra("tiket", ticket_number);
                intent.putExtra("korban", korban_nama);
                intent.putExtra("kasus", kasus_nama);
                intent.putExtra("kekerasan", bentuk_kekerasan);
                intent.putExtra("waktu", waktu_kejadian);
                intent.putExtra("alamat", alamat_kejadian);
                intent.putExtra("kronologi", kronologi);
                intent.putExtra("status", status_pengaduan);
                intent.putExtra("tindak", tindak_lanjut);
                intent.putExtra("bukti", bukti);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){
        return PengaduanList.size();
    }

    public class PengaduanHolder extends RecyclerView.ViewHolder{

        public TextView TvNoTiket, TvNamaKorban, TvStatusPengaduan, TvKasus;
        public CardView ItemClick;

        public PengaduanHolder(@NonNull View itemView){
            super(itemView);
            TvNoTiket           = itemView.findViewById(R.id.GetTvTiket);
            TvNamaKorban        = itemView.findViewById(R.id.GetTvNamaKorban);
            TvStatusPengaduan   = itemView.findViewById(R.id.GetTvStatusPengaduan);
            TvKasus             = itemView.findViewById(R.id.GetTvKasusNama);
            ItemClick           = itemView.findViewById(R.id.item);

            ButterKnife.bind(this, itemView);
        }
    }
}
