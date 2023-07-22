package com.darshandangar.dailykharch;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String,Object> users = new HashMap<>();
        users.put("firstname","EASY");
        users.put("lastname","TUTO");
        users.put("description","Subscribe");

        firestore.collection("users").add(users).addOnSuccessListener(documentReference -> Toast.makeText(MainActivity.this, "Sucess", Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show());

    }
}