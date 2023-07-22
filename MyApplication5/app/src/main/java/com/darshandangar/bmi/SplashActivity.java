package com.darshandangar.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView txtsplash;
        txtsplash = findViewById(R.id.txtsplash);
        Animation move = AnimationUtils.loadAnimation(this,R.anim.move);
        txtsplash.startAnimation(move);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent iHome = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(iHome);
                finish();
            }
        },5000);

    }
}