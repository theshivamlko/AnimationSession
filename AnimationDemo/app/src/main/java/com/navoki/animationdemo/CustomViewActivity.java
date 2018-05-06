package com.navoki.animationdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class CustomViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Utils.deviceHeight = metrics.heightPixels;
        Utils.deviceWidth = metrics.widthPixels;

        setContentView(new MySurfaceView(this));


    }
}
