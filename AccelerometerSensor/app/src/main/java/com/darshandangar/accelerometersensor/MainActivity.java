package com.darshandangar.accelerometersensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import javax.sql.StatementEventListener;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView taxvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taxvalue = findViewById(R.id.taxValue);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager!=null) {
            Sensor accelerosensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (accelerosensor!=null) {
                sensorManager.registerListener(this,accelerosensor,SensorManager.SENSOR_DELAY_NORMAL);
            }

        } else{
            Toast.makeText(this, "Sensor service not detected", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            taxvalue.setText("X:" + sensorEvent.values[0] + "Y:" + sensorEvent.values[1] +"Z:" + sensorEvent.values[2]);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}