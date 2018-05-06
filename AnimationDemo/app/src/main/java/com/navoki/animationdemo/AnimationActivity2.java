package com.navoki.animationdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AnimationActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation2);
    }


    @Override
    public void onBackPressed() {


        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_from_left,
                R.anim.anim_slide_out_to_right);
    }
}
