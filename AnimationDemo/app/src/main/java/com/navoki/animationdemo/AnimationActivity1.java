package com.navoki.animationdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class AnimationActivity1 extends AppCompatActivity {

    private Button button2;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation1);


        button2 = (Button) findViewById(R.id.button2);
        imageView = (ImageView) findViewById(R.id.imageView);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AnimationActivity1.this,AnimationActivity2.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_up
                ,0);
            }
        });


        Animation animation= AnimationUtils.loadAnimation(AnimationActivity1.this,
                R.anim.anim_fade_in_rotate_clockwise);
        imageView.setAnimation(animation);
    }
}
