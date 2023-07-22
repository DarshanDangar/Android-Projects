package com.darshandangar.bmi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        EditText edtweight,edtheight,edtinches;
        Button btnCalc;
        TextView txtresult;
        LinearLayout llmain;
        Toolbar toolbar;


        edtweight = findViewById(R.id.edtweight);
        edtheight = findViewById(R.id.edtheight);
        edtinches = findViewById(R.id.edtinches);
        btnCalc = findViewById(R.id.btnCalc);
        txtresult = findViewById(R.id.txtresult);
        llmain = findViewById(R.id.llmain);
        toolbar = findViewById(R.id.toolbar);





        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int wt = Integer.parseInt(edtweight.getText().toString());
                int ht = Integer.parseInt(edtheight.getText().toString());
                int hi = Integer.parseInt(edtinches.getText().toString());

                int totalin = ht*12 + hi;

                double totalcm = totalin*2.53;

                double totalm = totalcm/100;

                double bmi = wt/(totalm*totalm);

                if (bmi>25){
                    txtresult.setText("you are overweight");
                    llmain.setBackgroundColor(getResources().getColor(R.color.red));
                } else if (bmi<18){
                    txtresult.setText("you are underweight");
                    llmain.setBackgroundColor(getResources().getColor(R.color.yellow));
                } else{
                    txtresult.setText("you are healthy");
                    llmain.setBackgroundColor(getResources().getColor(R.color.green));
                }
            }
        });


        //step 1
        setSupportActionBar(toolbar);

        //step2
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("BMI Calculator");

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.opt_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId==R.id.opt_new){
            Toast.makeText(this, "Created new File", Toast.LENGTH_SHORT).show();
        } else if (itemId==R.id.opt_open){
            Toast.makeText(this, "File open", Toast.LENGTH_SHORT).show();
        } else if (itemId==android.R.id.home){
            super.onBackPressed();
        } else{
            Toast.makeText(this, "File saved", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}