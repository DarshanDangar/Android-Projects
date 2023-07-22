package com.example.recycle1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<TextModel> arrayText = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerTextAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for(int i=0;i<4;i++) {
            arrayText.add(new TextModel("Kankotrrrrr" + String.valueOf(i), "Abrrrrrc" + String.valueOf(i)));
        }

        adapter = new RecyclerTextAdapter(this,arrayText);
        recyclerView.setAdapter(adapter);


    }
}