package com.berkay22demirel.sinavpuanhesaplama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class StartAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_animation);
        getSupportActionBar().hide();
        startAnimation();
    }

    private void startAnimation() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.animation_start);
        ImageView logo = findViewById(R.id.imageViewStartAnimationLogo);
        anim.reset();
        logo.clearAnimation();
        logo.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(StartAnimationActivity.this, MainActivity.class);
                startActivity(intent);
                StartAnimationActivity.this.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
