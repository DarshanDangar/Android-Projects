package com.darshandangar.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listview;
    Spinner spinner;
    AutoCompleteTextView actview;


    ArrayList<String> arrNames = new ArrayList<>();
    ArrayList<String> arrids = new ArrayList<>();
    ArrayList<String> arrLanguages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        listview = findViewById(R.id.listview);
        actview = findViewById(R.id.actv);

        arrNames.add("Darshan");
        arrNames.add("Hitesh");
        arrNames.add("Krushit");
        arrNames.add("Ronak");
        arrNames.add("Jay");
        arrNames.add("Smit");
        arrNames.add("Umang");
        arrNames.add("Ashish");
        arrNames.add("Ritul");
        arrNames.add("Darshan");
        arrNames.add("Hitesh");
        arrNames.add("Krushit");
        arrNames.add("Ronak");
        arrNames.add("Jay");
        arrNames.add("Smit");
        arrNames.add("Umang");
        arrNames.add("Ashish");
        arrNames.add("Ritul");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,arrNames);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position==0){
                    Toast.makeText(MainActivity.this,"clicked first iteam",Toast.LENGTH_SHORT).show();
                }

            }
        });


        //spinner
        arrids.add("Adhar card");
        arrids.add("PAN card");
        arrids.add("Voter card");
        arrids.add("Ration card");
        arrids.add("Driving Licience card");

        ArrayAdapter<String> adapterid = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,arrids);
        spinner.setAdapter(adapterid);

        //Autocomplete text view

        arrLanguages.add("c");
        arrLanguages.add("c++");
        arrLanguages.add("java");
        arrLanguages.add("c#");
        arrLanguages.add("cscript");

        ArrayAdapter<String> adapterlanguage = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrLanguages);
        actview.setAdapter(adapterlanguage);
        actview.setThreshold(2);






    }
}