package com.example.apkersan2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.apkersan2.api.BaseApiService;
import com.example.apkersan2.api.UtilsApi;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class KonfirmasiActivity extends AppCompatActivity {

    private TextView TvTiket, TvStatusPelapor, TvJenisKasus, TvBentukKekerasan, TvNama, TvJenisKelamin, TvDisabilitas,
            TvUsia, TvPendidikan, TvBekerja, TvStatusKawin, TvAlamat, TvKronologi, TvTempat, TvWaktu;
    private String tiketExtra, statusExtra, jenisExtra, bentukExtra, namaExtra, jeniskelaminExtra, disabilitasExtra, usiaExtra,
            pendidikanExtra, bekerjaExtra, statuskawinExtra, alamatExtra, kronologiExtra, tempatExtra, waktuExtra, KasusIdExtra, KekerasanIdExtra;
    private Bitmap gambarExtra;
    private Uri videoExtra, audioExtra;
    private Double latExtra, lngExtra;
    private VideoView VvBuktiKekerasan;
    private ImageView IvBuktiKekersan;
    private Button BtBackKonfirmasi, BtComplete;

    ProgressDialog loading;
    SweetAlertDialog pDialog;

    Context mContext;
    BaseApiService mApiService;

    SharedPrefManager sharedPrefManager;

    Bitmap bmp;
    String user_id, bukti;

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

        IvBuktiKekersan     = (ImageView) findViewById(R.id.IvGambarKekerasan);
        VvBuktiKekerasan    = (VideoView) findViewById(R.id.VvVideoKekerasan);

        BtBackKonfirmasi    = (Button) findViewById(R.id.BtBackKonfirmasi);
        BtComplete          = (Button) findViewById(R.id.BtComplete);

        Bundle extra = getIntent().getExtras();
        if (extra != null){

            tiketExtra          = extra.getString("tiket");
            statusExtra         = extra.getString("status");
            jenisExtra          = extra.getString("jenis");
            bentukExtra         = extra.getString("bentuk");
            KasusIdExtra        = extra.getString("kekerasanid");
            KekerasanIdExtra    = extra.getString("kasusid");

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
            lngExtra            = extra.getDouble("lng", 0);
        }

        mContext = this;
        mApiService = UtilsApi.getAPIService();

        BtBackKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KonfirmasiActivity.this, DataPelengkapActivity.class);
                getIntent().removeExtra("audio");
                getIntent().removeExtra("gambar");
                getIntent().removeExtra("video");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        BtComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog = new SweetAlertDialog(KonfirmasiActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#009B59"));
                pDialog.setTitleText("Loading ...");
                pDialog.show();
                pengaduanPost();
            }
        });

        Bundle bukti = getIntent().getExtras();
        if (bukti.get("video") != null){
            videoExtra = Uri.parse(bukti.get("video").toString());
            IvBuktiKekersan.setVisibility(View.GONE);
            VvBuktiKekerasan.setVisibility(View.VISIBLE);
            VvBuktiKekerasan.setVideoURI(videoExtra);
            VvBuktiKekerasan.setMediaController(new MediaController(this));
            VvBuktiKekerasan.start();
        }else if (bukti.get("audio") != null){
            audioExtra = Uri.parse(bukti.get("audio").toString());
            IvBuktiKekersan.setVisibility(View.GONE);
            VvBuktiKekerasan.setVisibility(View.VISIBLE);
            VvBuktiKekerasan.setVideoURI(audioExtra);
            VvBuktiKekerasan.setMediaController(new MediaController(this));
            VvBuktiKekerasan.start();
        }else if (bukti.get("gambar") != null){
            byte[] gambar   = bukti.getByteArray("gambar");
            bmp             = BitmapFactory.decodeByteArray(gambar, 0, gambar.length);
//            bmp     = (Bitmap) getIntent().getParcelableExtra("gambar");
            IvBuktiKekersan.setVisibility(View.VISIBLE);
            VvBuktiKekerasan.setVisibility(View.GONE);
            IvBuktiKekersan.setImageBitmap(bmp);
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

        sharedPrefManager = new SharedPrefManager(KonfirmasiActivity.this.getApplicationContext());
        user_id = sharedPrefManager.getSpId();
    }

    public String imageToString(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 10, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();

        String imgString = Base64.encodeToString(imgByte, Base64.NO_WRAP);

        return imgString;
    }

    private void pengaduanPost(){
        if (bmp != null){
            bukti = imageToString(bmp);
        }
        String status_pengaduan  = "Menunggu";
        mApiService.pengaduanPost(
                user_id,
                KasusIdExtra,
                KekerasanIdExtra,
                tiketExtra,
                statusExtra,
                disabilitasExtra,
                namaExtra,
                jeniskelaminExtra,
                usiaExtra,
                pendidikanExtra,
                bekerjaExtra,
                statuskawinExtra,
                alamatExtra,
                waktuExtra,
                tempatExtra,
                kronologiExtra,
                bukti,
                latExtra,
                lngExtra,
                status_pengaduan
        ).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    pDialog.dismiss();
                    new SweetAlertDialog(KonfirmasiActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Sukses")
                            .setContentText("Pengaduan berhasil dibuat...")
                            .setConfirmText("OK")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                    startActivity(new Intent(KonfirmasiActivity.this, MainActivity.class)
                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                    finish();
                                }
                            })
                            .show();
                }else{
                    pDialog.dismiss();
                    new SweetAlertDialog(KonfirmasiActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Gagal membuat pengaduan!")
                            .show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }

    //font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
