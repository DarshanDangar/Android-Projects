package com.darshandangar.proximitysensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView taxvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taxvalue = findViewById(R.id.taxvalue);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager!=null){
            Sensor proxiSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            if (proxiSensor!=null){
                sensorManager.registerListener(this,proxiSensor,SensorManager.SENSOR_DELAY_NORMAL);

            }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType()==Sensor.TYPE_PROXIMITY){
            taxvalue.setText("Values" + sensorEvent.values[0] );
        }
        if (sensorEvent.values[0]>0){
            Toast.makeText(this, "Object is far", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Object is near", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}