package com.example.apkersan;

import android.app.Application;

import com.example.apkersan2.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class FontChange extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/SFProDisplay-Black.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/SFProDisplay-Bold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/SFProDisplay-Heavy.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/SFProDisplay-Light.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/SFProDisplay-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/SFProDisplay-Semibold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/SFProDisplay-Thin.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/SFProText-Bold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/SFProText-Heavy.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/SFProText-Light.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/SFProText-Medium.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/SFProText-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/SFProText-Semibold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );




    }
}

