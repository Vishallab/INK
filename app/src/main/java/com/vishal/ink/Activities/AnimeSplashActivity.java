package com.vishal.ink.Activities;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.vishal.ink.R;

public class AnimeSplashActivity extends AppCompatActivity {
    LottieAnimationView splashview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_splash);

        splashview = findViewById(R.id.splash);

        // Load the Lottie animation and handle errors


        splashview.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Intent intent = new Intent(AnimeSplashActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }
}
