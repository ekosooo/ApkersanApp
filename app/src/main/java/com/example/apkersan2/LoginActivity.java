package com.example.apkersan2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {

    EditText EtPassword;
    ImageView IvShow, IvHide;
    TextView TvDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EtPassword  = (EditText) findViewById(R.id.EtPassword);
        IvShow      = (ImageView) findViewById(R.id.IvShow);
        IvHide      = (ImageView) findViewById(R.id.IvHide);
        TvDaftar    = (TextView) findViewById(R.id.TvDaftar);

        IvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IvShow.isClickable()){
                    EtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    IvHide.setVisibility(View.VISIBLE);
                    IvShow.setVisibility(View.INVISIBLE);
                }else{
                    EtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        IvHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IvHide.isClickable()){
                    EtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    IvHide.setVisibility(View.INVISIBLE);
                    IvShow.setVisibility(View.VISIBLE);
                }else{
                    EtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        TvDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }


    //font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
