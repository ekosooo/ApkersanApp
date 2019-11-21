package com.example.apkersan2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DataPelengkapActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private EditText EtDateResult, EtAlamat, EtKronologi, EtWaktu;
    private ImageView BtDatePicker, IvBukti;
    private VideoView VvBukti;
    private TextView TvUnggah;
    private Spinner SpTempat;
    private Button BtNextPelengkap, BtBackPelengkap;
    private FrameLayout FrLatarHitam;

    private String tiketExtra, statusExtra, jenisExtra, bentukExtra, namaExtra, jeniskelaminExtra, disabilitasExtra, usiaExtra,
            pendidikanExtra, bekerjaExtra, statuskawinExtra, KasusIdExtra, KekerasanIdExtra;
    private String alamatExtra;
    private Double latExtra, lngExtra;
    private int GALLERY = 1, CAMERA = 2, VIDEO = 3, AUDIO = 4;

    private Uri video, audio;
    private Bitmap gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pelengkap);

        BtNextPelengkap = (Button) findViewById(R.id.BtNextPelengkap);
        BtBackPelengkap = (Button) findViewById(R.id.BtBackPelengkap);
        FrLatarHitam    = (FrameLayout) findViewById(R.id.FrameLayar);

        dateFormatter   = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        EtDateResult    = (EditText) findViewById(R.id.EtTglKejadian);
        BtDatePicker    = (ImageView) findViewById(R.id.IvKalender);
        IvBukti         = (ImageView) findViewById(R.id.IvBuktiKekerasan);
        VvBukti         = (VideoView) findViewById(R.id.VvBuktiKekerasan);
        TvUnggah        = (TextView) findViewById(R.id.TvUnggah);

        EtKronologi     = (EditText) findViewById(R.id.EtKronologi);
        SpTempat        = (Spinner) findViewById(R.id.SpTempat);
        EtAlamat        = (EditText) findViewById(R.id.EtAlamatKejadian);

        //mengambildatadariputextra
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
            latExtra            = extra.getDouble("lat", 0);
            lngExtra            = extra.getDouble("long", 0);
        }

        EtAlamat.setText(alamatExtra);

        requestMultiplePermission();

        BtBackPelengkap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataPelengkapActivity.this, DataKorbanActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        BtNextPelengkap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EtKronologi.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Silahkan masukkan kronologi", Toast.LENGTH_SHORT).show();
                }else if (EtDateResult.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Silahkan masukkan waktu kejadian", Toast.LENGTH_SHORT).show();
                }else if (EtKronologi.getText().toString().isEmpty() && EtDateResult.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Silahkan masukkan kronologi dan waktu kejadian", Toast.LENGTH_SHORT).show();
                } else{
                    Intent intent = new Intent(DataPelengkapActivity.this, KonfirmasiActivity.class);
                    //kirim extra
                    intent.putExtra("tiket", tiketExtra);
                    intent.putExtra("status", statusExtra);
                    intent.putExtra("jenis", jenisExtra);
                    intent.putExtra("bentuk", bentukExtra);
                    intent.putExtra("kekerasanid", KekerasanIdExtra);
                    intent.putExtra("kasusid", KasusIdExtra);

                    intent.putExtra("nama", namaExtra);
                    intent.putExtra("jeniskelamin", jeniskelaminExtra);
                    intent.putExtra("disabilitas", disabilitasExtra);
                    intent.putExtra("usia", usiaExtra);
                    intent.putExtra("pendidikan", pendidikanExtra);
                    intent.putExtra("bekerja", bekerjaExtra);
                    intent.putExtra("statuskawin", statuskawinExtra);

                    intent.putExtra("kronologi", EtKronologi.getText().toString());
                    intent.putExtra("alamat", EtAlamat.getText().toString());
                    intent.putExtra("tempat", SpTempat.getSelectedItem().toString());
                    intent.putExtra("waktu", EtDateResult.getText().toString());

                    intent.putExtra("lat", latExtra);
                    intent.putExtra("lng", lngExtra);

                    if (gambar != null) {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        gambar.compress(Bitmap.CompressFormat.JPEG, 10, byteArrayOutputStream);
                        byte[] imgByte = byteArrayOutputStream.toByteArray();
                        intent.putExtra("gambar", imgByte);
                    } else if (video != null) {
                        intent.putExtra("video", video);
                    } else if (audio != null) {
                        intent.putExtra("audio", audio);
                    }

                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }
        });

        TvUnggah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTakeDialog();
            }
        });

        BtDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDate();
            }
        });

    }

    private void showDialogDate(){

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(DataPelengkapActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                EtDateResult.setText(dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void showTakeDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Bukti Kekerasan");
        String[] pictureDialogItems = {
                "Kamera",
                "Galeri",
//                "Video",
//                "Rekaman Suara"
        };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                takePhotoFromCamera();
                                break;
                            case 1:
                                choosePhotoFromGallary();
                                break;
                            case 2:
                                chooseVideoFromGallary();
                                break;
                            case 3:
                                chooseAudio();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    private void chooseAudio() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/*");
        startActivityForResult(intent, AUDIO);
    }

    public void choosePhotoFromGallary(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY);
    }

    public void chooseVideoFromGallary(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*");
        startActivityForResult(intent, VIDEO);
    }

    public void takePhotoFromCamera(){
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED){
            return;
        }
        if (requestCode == GALLERY && data != null){
            Uri path = data.getData();
            try {
                gambar = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                IvBukti.setImageBitmap(gambar);
                IvBukti.setVisibility(View.VISIBLE);
                VvBukti.setVisibility(View.GONE);
                FrLatarHitam.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(DataPelengkapActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == CAMERA){
            Uri path = data.getData();
            try {
                gambar = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
//                gambar = (Bitmap) data.getExtras().get("data");
                IvBukti.setImageBitmap(gambar);
                IvBukti.setVisibility(View.VISIBLE);
                VvBukti.setVisibility(View.GONE);
                FrLatarHitam.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == VIDEO && data != null){
            video = data.getData();
            VvBukti.setVisibility(View.VISIBLE);
            IvBukti.setVisibility(View.GONE);
            FrLatarHitam.setVisibility(View.VISIBLE);
            VvBukti.setVideoURI(video);
            VvBukti.setMediaController(new MediaController(this));
            VvBukti.start();
        }
        else if (requestCode == AUDIO && data != null){
            audio = data.getData();
            VvBukti.setVisibility(View.VISIBLE);
            IvBukti.setVisibility(View.GONE);
            FrLatarHitam.setVisibility(View.VISIBLE);
            VvBukti.setVideoURI(audio);
            VvBukti.setMediaController(new MediaController(this));
            VvBukti.start();
        }
    }

    private void requestMultiplePermission() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
//                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    //font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
