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
    private Button BtNextPelengkap;

    private String tiketExtra, statusExtra, jenisExtra, bentukExtra, namaExtra, jeniskelaminExtra, disabilitasExtra, usiaExtra,
            pendidikanExtra, bekerjaExtra, statuskawinExtra;
    private String alamatExtra;
    private Double latExtra, lngExtra;
    private int GALLERY = 1, CAMERA = 2, VIDEO = 3, AUDIO = 4;

    private Uri contentURI;
    private Bitmap gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pelengkap);

        BtNextPelengkap = (Button) findViewById(R.id.BtNextPelengkap);
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

        BtNextPelengkap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataPelengkapActivity.this, KonfirmasiActivity.class);

                //kirim extra
                intent.putExtra("tiket", tiketExtra);
                intent.putExtra("status", statusExtra);
                intent.putExtra("jenis", jenisExtra);
                intent.putExtra("bentuk", bentukExtra);

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

                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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
                "Galeri",
                "Kamera",
                "Video",
                "Rekaman Suara"
        };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
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
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("images/*");
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
            Uri contentURI = data.getData();
            try {
                gambar = MediaStore.Images.Media.getBitmap(getContentResolver(), contentURI);
                IvBukti.setImageBitmap(gambar);
                IvBukti.setVisibility(View.VISIBLE);
                VvBukti.setVisibility(View.GONE);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(DataPelengkapActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == VIDEO && data != null){
            contentURI = data.getData();
            VvBukti.setVisibility(View.VISIBLE);
            IvBukti.setVisibility(View.GONE);
            VvBukti.setVideoURI(contentURI);
            VvBukti.setMediaController(new MediaController(this));
            VvBukti.start();
        }
        else if (requestCode == AUDIO && data != null){
            contentURI = data.getData();
            VvBukti.setVisibility(View.VISIBLE);
            IvBukti.setVisibility(View.GONE);
            VvBukti.setVideoURI(contentURI);
            VvBukti.setMediaController(new MediaController(this));
            VvBukti.start();
        }
        else if (requestCode == CAMERA){
            gambar = (Bitmap) data.getExtras().get("data");
            IvBukti.setImageBitmap(gambar);
            IvBukti.setVisibility(View.VISIBLE);
            VvBukti.setVisibility(View.GONE);
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