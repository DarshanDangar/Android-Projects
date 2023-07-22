package com.darshandangar.customtoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //custom toast

        Button button;
        button = findViewById(R.id.btntoast);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast = new Toast(getApplicationContext());
                View view = getLayoutInflater().inflate(R.layout.custom_layout,(ViewGroup) findViewById(R.id.customlayoutlinear));

                toast.setView(view);
                TextView txtmsg = view.findViewById(R.id.txtmsg);
                txtmsg.setText("Message sent successfully");
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.RIGHT,0,0);
                toast.show();

            }
        });


    }
}