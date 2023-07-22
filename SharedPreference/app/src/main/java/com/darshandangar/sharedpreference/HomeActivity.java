package com.darshandangar.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AppCompatButton logoutbtn;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logoutbtn = findViewById(R.id.logout);

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                editor.putBoolean("flag",false);
                editor.apply();

                Intent iLogin = new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(iLogin);


            }
        });
    }
}