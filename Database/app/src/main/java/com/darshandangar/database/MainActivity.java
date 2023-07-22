package com.darshandangar.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDBHelper dbHelper = new MyDBHelper(this);

        dbHelper.addContact("Ram","9684528489");
        dbHelper.addContact("Ramad","9684128489");
        dbHelper.addContact("Ramdev","9684528349");
        dbHelper.addContact("Raman","9685628489");
    }
}